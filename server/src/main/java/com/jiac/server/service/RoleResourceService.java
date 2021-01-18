package com.jiac.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiac.server.domain.RoleResource;
import com.jiac.server.domain.RoleResourceExample;
import com.jiac.server.dto.RoleResourceDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.mapper.RoleResourceMapper;
import com.jiac.server.util.CopyUtil;
import com.jiac.server.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: RoleResourceService
 * Author: Jiac
 * Date: 2021/1/7 10:22
 */
@Service
public class RoleResourceService {

    @Resource
    private RoleResourceMapper roleResourceMapper;

    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        RoleResourceExample roleResourceExample = new RoleResourceExample();
        List<RoleResource> roleResourceList = roleResourceMapper.selectByExample(roleResourceExample);
        PageInfo<RoleResource> pageInfo = new PageInfo<>(roleResourceList);
        pageDto.setTotal(pageInfo.getTotal());
        List<RoleResourceDto> roleResourceDtoList = new ArrayList<>();
        for(int i = 0, l = roleResourceList.size(); i < l; i++){
            RoleResource roleResource = roleResourceList.get(i);
            RoleResourceDto roleResourceDto = new RoleResourceDto();
            BeanUtils.copyProperties(roleResource, roleResourceDto);
            roleResourceDtoList.add(roleResourceDto);
        }
        pageDto.setList(roleResourceDtoList);
    }

    public void save(RoleResourceDto roleResourceDto){
        RoleResource roleResource = CopyUtil.copy(roleResourceDto, RoleResource.class);
        if(StringUtils.isEmpty(roleResourceDto.getId())){
            this.insert(roleResource);
        } else {
            this.update(roleResource);
        }
    }

    private void insert(RoleResource roleResource){
        roleResource.setId(UuidUtil.getShortUuid());
        roleResourceMapper.insert(roleResource);
    }

    private void update(RoleResource roleResource){
        roleResourceMapper.updateByPrimaryKey(roleResource);
    }

    public void delete(String id) {
        roleResourceMapper.deleteByPrimaryKey(id);
    }
}
