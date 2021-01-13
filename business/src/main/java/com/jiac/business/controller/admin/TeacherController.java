package com.jiac.business.controller.admin;

import com.jiac.server.dto.TeacherDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.dto.ResponseDto;
import com.jiac.server.service.TeacherService;
import com.jiac.server.util.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: TeacherController
 * Author: Jiac
 * Date: 2021/1/6 16:21
 */
@RestController
@RequestMapping("/admin/teacher")
public class TeacherController {

    public static final String BUSINESS_NAME = "讲师";

    @Resource
    private TeacherService teacherService;

    @PostMapping("/all")
    public ResponseDto all(){
        ResponseDto responseDto = new ResponseDto<>();
        List<TeacherDto> teacherDtoList = teacherService.all();
        responseDto.setContent(teacherDtoList);
        return responseDto;
    }

    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){
        ResponseDto responseDto = new ResponseDto<>();
        teacherService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody TeacherDto teacherDto){
        // 保存校验
        ValidatorUtil.require(teacherDto.getName(), "姓名");
        ValidatorUtil.length(teacherDto.getName(), "姓名", 1, 50);
        ValidatorUtil.length(teacherDto.getNickname(), "昵称", 1, 50);
        ValidatorUtil.length(teacherDto.getImage(), "头像", 1, 100);
        ValidatorUtil.length(teacherDto.getPosition(), "职位", 1, 50);
        ValidatorUtil.length(teacherDto.getMotto(), "座右铭", 1, 50);
        ValidatorUtil.length(teacherDto.getIntro(), "简介", 1, 500);

        ResponseDto responseDto = new ResponseDto<>();
        teacherService.save(teacherDto);
        responseDto.setContent(teacherDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto<>();
        teacherService.delete(id);
        return responseDto;
    }
}
