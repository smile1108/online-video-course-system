package com.jiac.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiac.server.domain.CourseContentFile;
import com.jiac.server.domain.CourseContentFileExample;
import com.jiac.server.dto.CourseContentFileDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.mapper.CourseContentFileMapper;
import com.jiac.server.util.CopyUtil;
import com.jiac.server.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: CourseContentFileService
 * Author: Jiac
 * Date: 2021/1/7 10:22
 */
@Service
public class CourseContentFileService {

    @Resource
    private CourseContentFileMapper courseContentFileMapper;

    public List<CourseContentFileDto> list(String courseId){
        CourseContentFileExample example = new CourseContentFileExample();
        CourseContentFileExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(courseId);
        List<CourseContentFile> fileList = courseContentFileMapper.selectByExample(example);
        return CopyUtil.copyList(fileList, CourseContentFileDto.class);
    }

    public void save(CourseContentFileDto courseContentFileDto){
        CourseContentFile courseContentFile = CopyUtil.copy(courseContentFileDto, CourseContentFile.class);
        if(StringUtils.isEmpty(courseContentFileDto.getId())){
            this.insert(courseContentFile);
        } else {
            this.update(courseContentFile);
        }
    }

    private void insert(CourseContentFile courseContentFile){
        courseContentFile.setId(UuidUtil.getShortUuid());
        courseContentFileMapper.insert(courseContentFile);
    }

    private void update(CourseContentFile courseContentFile){
        courseContentFileMapper.updateByPrimaryKey(courseContentFile);
    }

    public void delete(String id) {
        courseContentFileMapper.deleteByPrimaryKey(id);
    }
}
