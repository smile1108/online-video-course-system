package com.jiac.file.controller.admin;

import com.jiac.server.domain.File;
import com.jiac.server.dto.FileDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.dto.ResponseDto;
import com.jiac.server.service.FileService;
import com.jiac.server.util.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: FileController
 * Author: Jiac
 * Date: 2021/1/6 16:21
 */
@RestController
@RequestMapping("/admin/file")
public class FileController {

    public static final String BUSINESS_NAME = "文件";

    @Resource
    private FileService fileService;

    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){
        ResponseDto responseDto = new ResponseDto<>();
        fileService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

}
