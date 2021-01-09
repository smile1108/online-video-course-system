package com.jiac.business.controller.admin;

import com.jiac.server.domain.Chapter;
import com.jiac.server.dto.ChapterDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.dto.ResponseDto;
import com.jiac.server.service.ChapterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: ChapterController
 * Author: Jiac
 * Date: 2021/1/6 16:21
 */
@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){
        ResponseDto responseDto = new ResponseDto<>();
        chapterService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto){
        ResponseDto responseDto = new ResponseDto<>();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto<>();
        chapterService.delete(id);
        return responseDto;
    }
}
