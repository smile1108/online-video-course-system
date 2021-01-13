package com.jiac.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiac.server.domain.Section;
import com.jiac.server.domain.SectionExample;
import com.jiac.server.dto.SectionDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.dto.SectionPageDto;
import com.jiac.server.mapper.SectionMapper;
import com.jiac.server.util.CopyUtil;
import com.jiac.server.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * FileName: SectionService
 * Author: Jiac
 * Date: 2021/1/7 10:22
 */
@Service
public class SectionService {

    @Resource
    private SectionMapper sectionMapper;

    public void list(SectionPageDto sectionPageDto){
        PageHelper.startPage(sectionPageDto.getPage(), sectionPageDto.getSize());
        SectionExample sectionExample = new SectionExample();
        SectionExample.Criteria criteria = sectionExample.createCriteria();
        if(!StringUtils.isEmpty(sectionPageDto.getCourseId())){
            criteria.andCourseIdEqualTo(sectionPageDto.getCourseId());
        }
        if(!StringUtils.isEmpty(sectionPageDto.getChapterId())){
            criteria.andChapterIdEqualTo(sectionPageDto.getChapterId());
        }
        sectionExample.setOrderByClause("sort asc");
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        sectionPageDto.setTotal(pageInfo.getTotal());
        List<SectionDto> sectionDtoList = new ArrayList<>();
        for(int i = 0, l = sectionList.size(); i < l; i++){
            Section section = sectionList.get(i);
            SectionDto sectionDto = new SectionDto();
            BeanUtils.copyProperties(section, sectionDto);
            sectionDtoList.add(sectionDto);
        }
        sectionPageDto.setList(sectionDtoList);
    }

    public void save(SectionDto sectionDto){
        Section section = CopyUtil.copy(sectionDto, Section.class);
        if(StringUtils.isEmpty(sectionDto.getId())){
            this.insert(section);
        } else {
            this.update(section);
        }
    }

    private void insert(Section section){
        Date now = new Date();
        section.setCreatedAt(now);
        section.setUpdatedAt(now);
        section.setId(UuidUtil.getShortUuid());
        sectionMapper.insert(section);
    }

    private void update(Section section){
        section.setUpdatedAt(new Date());
        sectionMapper.updateByPrimaryKey(section);
    }

    public void delete(String id) {
        sectionMapper.deleteByPrimaryKey(id);
    }
}
