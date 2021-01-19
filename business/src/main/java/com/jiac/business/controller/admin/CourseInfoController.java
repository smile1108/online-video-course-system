package com.jiac.business.controller.admin;

import com.jiac.server.domain.CourseInfo;
import com.jiac.server.dto.*;
import com.jiac.server.service.CourseCategoryService;
import com.jiac.server.service.CourseInfoService;
import com.jiac.server.util.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: CourseInfoController
 * Author: Jiac
 * Date: 2021/1/6 16:21
 */
@RestController
@RequestMapping("/admin/courseInfo")
public class CourseInfoController {

    public static final String BUSINESS_NAME = "课程表";

    @Resource
    private CourseInfoService courseInfoService;

    @Resource
    private CourseCategoryService courseCategoryService;

    @PostMapping("/list")
    public ResponseDto list(@RequestBody CoursePageDto pageDto){
        ResponseDto responseDto = new ResponseDto<>();
        courseInfoService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseInfoDto courseInfoDto){
        // 保存校验
        ValidatorUtil.require(courseInfoDto.getName(), "名称");
        ValidatorUtil.length(courseInfoDto.getName(), "名称", 1, 50);
        ValidatorUtil.length(courseInfoDto.getSummary(), "概述", 1, 2000);
        ValidatorUtil.length(courseInfoDto.getImage(), "封面", 1, 100);

        ResponseDto responseDto = new ResponseDto<>();
        courseInfoService.save(courseInfoDto);
        responseDto.setContent(courseInfoDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto<>();
        courseInfoService.delete(id);
        return responseDto;
    }

    @PostMapping("/list-category/{courseId}")
    public ResponseDto listCategory(@PathVariable(value = "courseId") String courseId){
        ResponseDto responseDto = new ResponseDto();
        List<CourseCategoryDto> dtoList = courseCategoryService.listByCourse(courseId);
        responseDto.setContent(dtoList);
        return responseDto;
    }

    @GetMapping("/find-content/{id}")
    public ResponseDto findContent(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        CourseContentDto contentDto = courseInfoService.findContent(id);
        responseDto.setContent(contentDto);
        return responseDto;
    }

    @PostMapping("/save-content")
    public ResponseDto saveContent(@RequestBody CourseContentDto contentDto){
        ResponseDto responseDto = new ResponseDto();
        courseInfoService.saveContent(contentDto);
        return responseDto;
    }

    @RequestMapping(value = "/sort")
    public ResponseDto sort(@RequestBody SortDto sortDto){
        ResponseDto responseDto = new ResponseDto();
        courseInfoService.sort(sortDto);
        return responseDto;
    }
}
