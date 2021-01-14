package com.jiac.business.controller.admin;

import com.jiac.server.domain.CourseContentFile;
import com.jiac.server.dto.CourseContentFileDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.dto.ResponseDto;
import com.jiac.server.service.CourseContentFileService;
import com.jiac.server.util.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: CourseContentFileController
 * Author: Jiac
 * Date: 2021/1/6 16:21
 */
@RestController
@RequestMapping("/admin/course-content-file")
public class CourseContentFileController {

    public static final String BUSINESS_NAME = "课程内容文件";

    @Resource
    private CourseContentFileService courseContentFileService;

    @GetMapping("/list/{courseId}")
    public ResponseDto list(@PathVariable String courseId){
        ResponseDto responseDto = new ResponseDto<>();
        List<CourseContentFileDto> fileDtoList = courseContentFileService.list(courseId);
        responseDto.setContent(fileDtoList);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseContentFileDto courseContentFileDto){
        // 保存校验
        ValidatorUtil.require(courseContentFileDto.getCourseId(), "课程id");
        ValidatorUtil.length(courseContentFileDto.getUrl(), "地址", 1, 100);
        ValidatorUtil.length(courseContentFileDto.getName(), "文件名", 1, 100);

        ResponseDto responseDto = new ResponseDto<>();
        courseContentFileService.save(courseContentFileDto);
        responseDto.setContent(courseContentFileDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto<>();
        courseContentFileService.delete(id);
        return responseDto;
    }
}
