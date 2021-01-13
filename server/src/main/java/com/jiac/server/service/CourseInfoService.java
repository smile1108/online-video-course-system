package com.jiac.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiac.server.domain.CourseInfo;
import com.jiac.server.domain.CourseInfoExample;
import com.jiac.server.dto.CourseInfoDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.mapper.CourseInfoMapper;
import com.jiac.server.mapper.my.MyCourseMapper;
import com.jiac.server.util.CopyUtil;
import com.jiac.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    private static final Logger LOG = LoggerFactory.getLogger(CourseInfoService.class);

    @Resource
    private CourseInfoMapper courseInfoMapper;

    @Resource
    private MyCourseMapper myCourseMapper;

    @Resource
    private CourseCategoryService courseCategoryService;

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

    @Transactional
    public void save(CourseInfoDto courseInfoDto){
        CourseInfo courseInfo = CopyUtil.copy(courseInfoDto, CourseInfo.class);
        if(StringUtils.isEmpty(courseInfoDto.getId())){
            this.insert(courseInfo);
        } else {
            this.update(courseInfo);
        }

        // 批量保存课程分类
        courseCategoryService.saveBatch(courseInfoDto.getId(), courseInfoDto.getCategorys());
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

    /**
     * 更新课程时长
     * @param courseId
     */
    public void updateTime(String courseId){
        LOG.info("更新课程时长: {}", courseId);
        myCourseMapper.updateTime(courseId);
    }
}
