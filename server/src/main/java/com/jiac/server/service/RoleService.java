package com.jiac.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiac.server.domain.Role;
import com.jiac.server.domain.RoleExample;
import com.jiac.server.dto.RoleDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.mapper.RoleMapper;
import com.jiac.server.util.CopyUtil;
import com.jiac.server.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: RoleService
 * Author: Jiac
 * Date: 2021/1/7 10:22
 */
@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        RoleExample roleExample = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        pageDto.setTotal(pageInfo.getTotal());
        List<RoleDto> roleDtoList = new ArrayList<>();
        for(int i = 0, l = roleList.size(); i < l; i++){
            Role role = roleList.get(i);
            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(role, roleDto);
            roleDtoList.add(roleDto);
        }
        pageDto.setList(roleDtoList);
    }

    public void save(RoleDto roleDto){
        Role role = CopyUtil.copy(roleDto, Role.class);
        if(StringUtils.isEmpty(roleDto.getId())){
            this.insert(role);
        } else {
            this.update(role);
        }
    }

    private void insert(Role role){
        role.setId(UuidUtil.getShortUuid());
        roleMapper.insert(role);
    }

    private void update(Role role){
        roleMapper.updateByPrimaryKey(role);
    }

    public void delete(String id) {
        roleMapper.deleteByPrimaryKey(id);
    }
}
