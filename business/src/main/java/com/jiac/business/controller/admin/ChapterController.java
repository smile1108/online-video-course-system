package com.jiac.business.controller.admin;

import com.jiac.server.domain.Chapter;
import com.jiac.server.dto.ChapterDto;
import com.jiac.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/list")
    public List<ChapterDto> list(){
        return chapterService.list();
    }
}