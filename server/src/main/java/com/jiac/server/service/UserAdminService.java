package com.jiac.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiac.server.domain.UserAdmin;
import com.jiac.server.domain.UserAdminExample;
import com.jiac.server.dto.UserAdminDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.mapper.UserAdminMapper;
import com.jiac.server.util.CopyUtil;
import com.jiac.server.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: UserAdminService
 * Author: Jiac
 * Date: 2021/1/7 10:22
 */
@Service
public class UserAdminService {

    @Resource
    private UserAdminMapper userAdminMapper;

    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        UserAdminExample userAdminExample = new UserAdminExample();
        List<UserAdmin> userAdminList = userAdminMapper.selectByExample(userAdminExample);
        PageInfo<UserAdmin> pageInfo = new PageInfo<>(userAdminList);
        pageDto.setTotal(pageInfo.getTotal());
        List<UserAdminDto> userAdminDtoList = new ArrayList<>();
        for(int i = 0, l = userAdminList.size(); i < l; i++){
            UserAdmin userAdmin = userAdminList.get(i);
            UserAdminDto userAdminDto = new UserAdminDto();
            BeanUtils.copyProperties(userAdmin, userAdminDto);
            userAdminDtoList.add(userAdminDto);
        }
        pageDto.setList(userAdminDtoList);
    }

    public void save(UserAdminDto userAdminDto){
        UserAdmin userAdmin = CopyUtil.copy(userAdminDto, UserAdmin.class);
        if(StringUtils.isEmpty(userAdminDto.getId())){
            this.insert(userAdmin);
        } else {
            this.update(userAdmin);
        }
    }

    private void insert(UserAdmin userAdmin){
        userAdmin.setId(UuidUtil.getShortUuid());
        userAdminMapper.insert(userAdmin);
    }

    private void update(UserAdmin userAdmin){
        userAdminMapper.updateByPrimaryKey(userAdmin);
    }

    public void delete(String id) {
        userAdminMapper.deleteByPrimaryKey(id);
    }
}
