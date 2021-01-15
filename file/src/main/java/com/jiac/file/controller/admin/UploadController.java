package com.jiac.file.controller.admin;

import com.jiac.server.domain.Test;
import com.jiac.server.dto.FileDto;
import com.jiac.server.dto.ResponseDto;
import com.jiac.server.enums.FileUseEnum;
import com.jiac.server.service.FileService;
import com.jiac.server.service.TestService;
import com.jiac.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * FileName: TestController
 * Author: Jiac
 * Date: 2021/1/6 16:21
 */
@RestController
@RequestMapping("/admin")
public class UploadController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);

    public static final String BUSINESS_NAME = "文件上传";

    @Resource
    private FileService fileService;

    @Value("${file.domain}")
    private String FILE_DOMAIN;

    @Value("${file.path}")
    private String FILE_PATH;

    @RequestMapping("/upload")
    public ResponseDto upload(@RequestParam MultipartFile shard,
                              String use,
                              String name,
                              String suffix,
                              Integer size,
                              Integer shardIndex,
                              Integer shardSize,
                              Integer shardTotal,
                              String key) throws Exception {

        // 保存文件到本地
        FileUseEnum useEnum = FileUseEnum.getByCode(use);

        // 如果文件夹不存在则创建
        String dir = useEnum.name().toLowerCase();
        File fullDir = new File(FILE_PATH + dir);
        if(!fullDir.exists()){
            fullDir.mkdir();
        }
        String path = new StringBuffer(dir).append(File.separator).append(key).append(".").append(suffix)
                .toString();

        String localPath = new StringBuffer(path)
                .append(".").append(shardIndex).toString();
        String fullPath = FILE_PATH + localPath;
        File dest = new File(fullPath);
        shard.transferTo(dest);
        LOG.info(dest.getAbsolutePath());

        LOG.info("保存文件记录开始");
        FileDto fileDto = new FileDto();
        fileDto.setPath(path);
        fileDto.setName(name);
        fileDto.setSize(size);
        fileDto.setSuffix(suffix);
        fileDto.setUse(use);
        fileDto.setShardIndex(shardIndex);
        fileDto.setShardSize(shardSize);
        fileDto.setShardTotal(shardTotal);
        fileDto.setKey(key);
        fileService.save(fileDto);

        ResponseDto responseDto = new ResponseDto();
        fileDto.setPath(FILE_DOMAIN + path);
        // 返回给前端 上传的头像在服务器本地的路径
        responseDto.setContent(fileDto);

        if(shardIndex.equals(shardTotal)){
            this.merge(path, shardTotal);
        }
        return responseDto;
    }


    public void merge(String path, Integer shardTotal) throws Exception {
        LOG.info("合并分片开始");
        path = path.replace(FILE_DOMAIN, "");
        File newFile = new File(FILE_PATH + path);
        FileOutputStream outputStream = new FileOutputStream(newFile, true);// 文件追加写入
        FileInputStream fileInputStream = null;
        byte[] byt = new byte[10 * 1024 * 1024];
        int len;

        try {

            for(int i = 0; i < shardTotal; i++) {
                // 读取第i个分片  我们的分片索引是从1开始的
                fileInputStream = new FileInputStream(new File(FILE_PATH + path + "." + (i + 1)));

                while((len = fileInputStream.read(byt)) != -1){
                    outputStream.write(byt, 0, len);
                }
            }
        } catch (IOException e){
            LOG.error("分片合并异常", e);
        } finally {
            try {
                if(fileInputStream != null) {
                    fileInputStream.close();
                }
                outputStream.close();
                LOG.info("IO流关闭");
            } catch (Exception e) {
                LOG.error("IO流关闭", e);
            }
        }
        LOG.info("合并分片结束");

        System.gc();
        Thread.sleep(100);

        // 删除分片
        LOG.info("删除分片开始");
        for(int i = 0; i < shardTotal; i++){
            String filePath = FILE_PATH + path + "." + (i + 1);
            File file = new File(filePath);
            boolean result = file.delete();
            LOG.info("删除{}, {}", filePath, result ? "成功" : "失败");
        }
        LOG.info("删除分片结束");
    }

    @GetMapping("/check/{key}")
    public ResponseDto check(@PathVariable String key){
        LOG.info("检查上传分片开始：{}", key);
        ResponseDto responseDto = new ResponseDto();
        FileDto fileDto = fileService.findByKey(key);
        if(fileDto != null){
            fileDto.setPath(FILE_DOMAIN + fileDto.getPath());
        }
        responseDto.setContent(fileDto);
        return responseDto;
    }
}
