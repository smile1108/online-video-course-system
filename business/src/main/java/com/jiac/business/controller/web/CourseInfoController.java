package com.jiac.business.controller.web;

import com.jiac.server.dto.CourseInfoDto;
import com.jiac.server.dto.CoursePageDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.dto.ResponseDto;
import com.jiac.server.enums.CourseStatusEnum;
import com.jiac.server.service.CourseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: CourseInfoController
 * Author: Jiac
 * Date: 2021/1/19 9:11
 */
@RestController("webCourseController")
@RequestMapping("/web/course")
public class CourseInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseInfoController.class);
    public static final String BUSINESS_NAME = "课程";

    @Resource
    private CourseInfoService courseInfoService;

    /**
     * 列表查询，查询最新的3门已发布的课程
     * @return
     */
    @GetMapping("/list-new")
    public ResponseDto listNew(){
        PageDto pageDto = new PageDto();
        pageDto.setPage(1);
        pageDto.setSize(3);
        ResponseDto responseDto = new ResponseDto();
        List<CourseInfoDto> courseDtoList = courseInfoService.listNew(pageDto);
        responseDto.setContent(courseDtoList);
        return responseDto;
    }

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody CoursePageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        pageDto.setStatus(CourseStatusEnum.PUBLISH.getCode());
        courseInfoService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }
}
