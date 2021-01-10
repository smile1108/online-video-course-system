package com.jiac.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiac.server.domain.CourseInfo;
import com.jiac.server.domain.CourseInfoExample;
import com.jiac.server.dto.CourseInfoDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.mapper.CourseInfoMapper;
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
 * FileName: CourseInfoService
 * Author: Jiac
 * Date: 2021/1/7 10:22
 */
@Service
public class CourseInfoService {

    @Resource
    private CourseInfoMapper courseInfoMapper;

    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseInfoExample courseInfoExample = new CourseInfoExample();
        courseInfoExample.setOrderByClause("sort asc");
        List<CourseInfo> courseInfoList = courseInfoMapper.selectByExample(courseInfoExample);
        PageInfo<CourseInfo> pageInfo = new PageInfo<>(courseInfoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<CourseInfoDto> courseInfoDtoList = new ArrayList<>();
        for(int i = 0, l = courseInfoList.size(); i < l; i++){
            CourseInfo courseInfo = courseInfoList.get(i);
            CourseInfoDto courseInfoDto = new CourseInfoDto();
            BeanUtils.copyProperties(courseInfo, courseInfoDto);
            courseInfoDtoList.add(courseInfoDto);
        }
        pageDto.setList(courseInfoDtoList);
    }

    public void save(CourseInfoDto courseInfoDto){
        CourseInfo courseInfo = CopyUtil.copy(courseInfoDto, CourseInfo.class);
        if(StringUtils.isEmpty(courseInfoDto.getId())){
            this.insert(courseInfo);
        } else {
            this.update(courseInfo);
        }
    }

    private void insert(CourseInfo courseInfo){
        Date now = new Date();
        courseInfo.setCreatedAt(now);
        courseInfo.setUpdatedAt(now);
        courseInfo.setId(UuidUtil.getShortUuid());
        courseInfoMapper.insert(courseInfo);
    }

    private void update(CourseInfo courseInfo){
        courseInfo.setUpdatedAt(new Date());
        courseInfoMapper.updateByPrimaryKey(courseInfo);
    }

    public void delete(String id) {
        courseInfoMapper.deleteByPrimaryKey(id);
    }
}
