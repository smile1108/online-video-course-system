package com.jiac.server.service;

import com.jiac.server.domain.Chapter;
import com.jiac.server.domain.ChapterExample;
import com.jiac.server.mapper.ChapterMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: ChapterService
 * Author: Jiac
 * Date: 2021/1/7 10:22
 */
@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public List<Chapter> list(){
        ChapterExample chapterExample = new ChapterExample();
        chapterExample.createCriteria().andIdEqualTo("1");
        chapterExample.setOrderByClause("id asc");
        return chapterMapper.selectByExample(chapterExample);
    }
}
