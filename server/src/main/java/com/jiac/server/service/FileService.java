package com.jiac.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiac.server.domain.File;
import com.jiac.server.domain.FileExample;
import com.jiac.server.dto.FileDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.mapper.FileMapper;
import com.jiac.server.util.CopyUtil;
import com.jiac.server.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * FileName: FileService
 * Author: Jiac
 * Date: 2021/1/7 10:22
 */
@Service
public class FileService {

    @Resource
    private FileMapper fileMapper;

    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        FileExample fileExample = new FileExample();
        List<File> fileList = fileMapper.selectByExample(fileExample);
        PageInfo<File> pageInfo = new PageInfo<>(fileList);
        pageDto.setTotal(pageInfo.getTotal());
        List<FileDto> fileDtoList = new ArrayList<>();
        for(int i = 0, l = fileList.size(); i < l; i++){
            File file = fileList.get(i);
            FileDto fileDto = new FileDto();
            BeanUtils.copyProperties(file, fileDto);
            fileDtoList.add(fileDto);
        }
        pageDto.setList(fileDtoList);
    }

    public void save(FileDto fileDto){
        File file = CopyUtil.copy(fileDto, File.class);
        if(StringUtils.isEmpty(fileDto.getId())){
            this.insert(file);
        } else {
            this.update(file);
        }
    }

    private void insert(File file){
        Date now = new Date();
        file.setCreatedAt(now);
        file.setUpdatedAt(now);
        file.setId(UuidUtil.getShortUuid());
        fileMapper.insert(file);
    }

    private void update(File file){
        file.setUpdatedAt(new Date());
        fileMapper.updateByPrimaryKey(file);
    }

    public void delete(String id) {
        fileMapper.deleteByPrimaryKey(id);
    }
}
