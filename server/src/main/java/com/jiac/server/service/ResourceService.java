package com.jiac.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiac.server.domain.Resource;
import com.jiac.server.domain.ResourceExample;
import com.jiac.server.dto.ResourceDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.mapper.ResourceMapper;
import com.jiac.server.util.CopyUtil;
import com.jiac.server.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: ResourceService
 * Author: Jiac
 * Date: 2021/1/7 10:22
 */
@Service
public class ResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ResourceExample resourceExample = new ResourceExample();
        List<Resource> resourceList = resourceMapper.selectByExample(resourceExample);
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);
        pageDto.setTotal(pageInfo.getTotal());
        List<ResourceDto> resourceDtoList = new ArrayList<>();
        for(int i = 0, l = resourceList.size(); i < l; i++){
            Resource resource = resourceList.get(i);
            ResourceDto resourceDto = new ResourceDto();
            BeanUtils.copyProperties(resource, resourceDto);
            resourceDtoList.add(resourceDto);
        }
        pageDto.setList(resourceDtoList);
    }

    public void save(ResourceDto resourceDto){
        Resource resource = CopyUtil.copy(resourceDto, Resource.class);
        if(StringUtils.isEmpty(resourceDto.getId())){
            this.insert(resource);
        } else {
            this.update(resource);
        }
    }

    private void insert(Resource resource){
        resource.setId(UuidUtil.getShortUuid());
        resourceMapper.insert(resource);
    }

    private void update(Resource resource){
        resourceMapper.updateByPrimaryKey(resource);
    }

    public void delete(String id) {
        resourceMapper.deleteByPrimaryKey(id);
    }
}
