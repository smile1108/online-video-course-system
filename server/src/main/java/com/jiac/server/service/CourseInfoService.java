package com.jiac.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiac.server.domain.CourseContent;
import com.jiac.server.domain.CourseInfo;
import com.jiac.server.domain.CourseInfoExample;
import com.jiac.server.dto.*;
import com.jiac.server.enums.CourseStatusEnum;
import com.jiac.server.mapper.CourseContentMapper;
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

    @Resource
    private TeacherService teacherService;

    @Resource
    private ChapterService chapterService;

    @Resource
    private SectionService sectionService;

    @Resource
    private CourseContentMapper courseContentMapper;

    public void list(CoursePageDto pageDto){
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        List<CourseInfoDto> courseInfoDtoList = myCourseMapper.list(pageDto);
        PageInfo<CourseInfoDto> pageInfo = new PageInfo<>(courseInfoDtoList);
        pageDto.setTotal(pageInfo.getTotal());
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
        courseCategoryService.saveBatch(courseInfo.getId(), courseInfoDto.getCategorys());
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

    /**
     * 查找课程内容
     * @param id
     * @return
     */
    public CourseContentDto findContent(String id){
        CourseContent content = courseContentMapper.selectByPrimaryKey(id);
        if(content == null){
            return null;
        }
        return CopyUtil.copy(content, CourseContentDto.class);
    }

    /**
     * 保存课程内容 包含新增和修改
     */
    public int saveContent(CourseContentDto contentDto){
        CourseContent content = CopyUtil.copy(contentDto, CourseContent.class);
        int i = courseContentMapper.updateByPrimaryKeyWithBLOBs(content);
        if(i == 0){
            i= courseContentMapper.insert(content);
        }
        return i;
    }

    /**
     * 排序
     * @param sortDto
     */
    @Transactional
    public void sort(SortDto sortDto){
        // 修改当前记录的排序值
        myCourseMapper.updateSort(sortDto);

        // 如果排序值变大
        if(sortDto.getNewSort() > sortDto.getOldSort()){
            myCourseMapper.moveSortsForward(sortDto);
        }

        // 如果排序值变小
        if(sortDto.getNewSort() < sortDto.getOldSort()){
            myCourseMapper.moveSortsBackward(sortDto);
        }
    }

    /**
     * 新课列表查询，只查询已发布的，按创建日期倒序
     * @param pageDto
     * @return
     */
    public List<CourseInfoDto> listNew(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseInfoExample courseInfoExample = new CourseInfoExample();
        courseInfoExample.createCriteria().andStatusEqualTo(CourseStatusEnum.PUBLISH.getCode());
        courseInfoExample.setOrderByClause("created_at desc");
        List<CourseInfo> courseList = courseInfoMapper.selectByExample(courseInfoExample);
        return CopyUtil.copyList(courseList, CourseInfoDto.class);
    }

    public CourseInfoDto findCourse(String id) {
        CourseInfo courseInfo = courseInfoMapper.selectByPrimaryKey(id);
        if(courseInfo == null || !CourseStatusEnum.PUBLISH.getCode().equals(courseInfo.getStatus())) {
            return null;
        }
        CourseInfoDto courseInfoDto = CopyUtil.copy(courseInfo, CourseInfoDto.class);

        // 查询内容
        CourseContent content = courseContentMapper.selectByPrimaryKey(id);
        if(content != null){
            courseInfoDto.setContent(content.getContent());
        }

        // 查找讲师信息
        TeacherDto teacherDto = teacherService.findById(courseInfoDto.getTeacherId());
        courseInfoDto.setTeacher(teacherDto);

        // 查找章信息
        List<ChapterDto> chapterDtoList = chapterService.listByCourse(id);
        courseInfoDto.setChapters(chapterDtoList);

        // 查找节信息
        List<SectionDto> sectionDtoList = sectionService.listByCourse(id);
        courseInfoDto.setSections(sectionDtoList);

        return courseInfoDto;
    }
}
