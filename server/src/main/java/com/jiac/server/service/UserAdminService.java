package com.jiac.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiac.server.domain.UserAdmin;
import com.jiac.server.domain.UserAdminExample;
import com.jiac.server.dto.LoginUserAdminDto;
import com.jiac.server.dto.UserAdminDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.exception.BusinessException;
import com.jiac.server.exception.BusinessExceptionCode;
import com.jiac.server.mapper.UserAdminMapper;
import com.jiac.server.util.CopyUtil;
import com.jiac.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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

    private static final Logger LOG = LoggerFactory.getLogger(UserAdminService.class);

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
        UserAdmin userDb = this.selectByLoginName(userAdmin.getLoginName());
        if(userDb != null){
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
        }
        userAdminMapper.insert(userAdmin);
    }

    private void update(UserAdmin userAdmin){
        userAdmin.setPassword(null);
        userAdminMapper.updateByPrimaryKeySelective(userAdmin);
    }

    public void delete(String id) {
        userAdminMapper.deleteByPrimaryKey(id);
    }

    public UserAdmin selectByLoginName(String loginName){
        UserAdminExample userAdminExample = new UserAdminExample();
        userAdminExample.createCriteria().andLoginNameEqualTo(loginName);
        List<UserAdmin> userList = userAdminMapper.selectByExample(userAdminExample);
        if(CollectionUtils.isEmpty(userList)){
            return null;
        } else {
            return userList.get(0);
        }
    }

    /**
     * 重置密码
     * @param userAdminDto
     */
    public void savePassword(UserAdminDto userAdminDto){
        UserAdmin userAdmin = new UserAdmin();
        userAdmin.setId(userAdminDto.getId());
        userAdmin.setPassword(userAdminDto.getPassword());
        userAdminMapper.updateByPrimaryKeySelective(userAdmin);
    }

    /**
     * 登录
     * @param userAdminDto
     */
    public LoginUserAdminDto login(UserAdminDto userAdminDto){
        UserAdmin userAdmin = selectByLoginName(userAdminDto.getLoginName());
        if(userAdmin == null) {
            // 用户名不存在
            LOG.info("用户名不存在");
            throw new BusinessException(BusinessExceptionCode.LOGIN_ERROR);
        } else {
            if(userAdmin.getPassword().equals(userAdminDto.getPassword())) {
                // 登录成功
                return CopyUtil.copy(userAdmin, LoginUserAdminDto.class);
            } else {
                // 密码不正确
                LOG.info("密码不正确, 输入密码：{}， 数据库密码：{}", userAdminDto.getPassword(), userAdmin.getPassword());
                throw new BusinessException(BusinessExceptionCode.LOGIN_ERROR);
            }
        }
    }
}
