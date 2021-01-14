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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
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
    public ResponseDto upload(@RequestParam MultipartFile file, String use) throws IOException {
        LOG.info(file.getOriginalFilename());
        LOG.info(String.valueOf(file.getSize()));

        // 保存文件到本地
        FileUseEnum useEnum = FileUseEnum.getByCode(use);
        String key = UuidUtil.getShortUuid();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        // 如果文件夹不存在则创建
        String dir = useEnum.name().toLowerCase();
        File fullDir = new File(FILE_PATH + dir);
        if(!fullDir.exists()){
            fullDir.mkdir();
        }

        String path = dir + File.separator + key + "." + suffix;
        String fullPath = FILE_PATH + path;
        File dest = new File(fullPath);
        file.transferTo(dest);
        LOG.info(dest.getAbsolutePath());

        LOG.info("保存文件记录开始");
        FileDto fileDto = new FileDto();
        fileDto.setPath(path);
        fileDto.setName(fileName);
        fileDto.setSize(Math.toIntExact(file.getSize()));
        fileDto.setSuffix(suffix);
        fileDto.setUse(use);
        fileService.save(fileDto);

        ResponseDto responseDto = new ResponseDto();
        fileDto.setPath(FILE_DOMAIN + path);
        // 返回给前端 上传的头像在服务器本地的路径
        responseDto.setContent(fileDto);
        return responseDto;
    }
}
