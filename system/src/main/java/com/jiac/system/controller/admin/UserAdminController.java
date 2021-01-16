package com.jiac.system.controller.admin;

import com.jiac.server.domain.UserAdmin;
import com.jiac.server.dto.UserAdminDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.dto.ResponseDto;
import com.jiac.server.service.UserAdminService;
import com.jiac.server.util.ValidatorUtil;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: UserAdminController
 * Author: Jiac
 * Date: 2021/1/6 16:21
 */
@RestController
@RequestMapping("/admin/userAdmin")
public class UserAdminController {

    public static final String BUSINESS_NAME = "用户";

    @Resource
    private UserAdminService userAdminService;

    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){
        ResponseDto responseDto = new ResponseDto<>();
        userAdminService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody UserAdminDto userAdminDto){
        userAdminDto.setPassword(DigestUtils.md5DigestAsHex(userAdminDto.getPassword().getBytes()));
        // 保存校验
        ValidatorUtil.require(userAdminDto.getLoginName(), "登录名");
        ValidatorUtil.length(userAdminDto.getLoginName(), "登录名", 1, 50);
        ValidatorUtil.length(userAdminDto.getName(), "昵称", 1, 50);
        ValidatorUtil.require(userAdminDto.getPassword(), "密码");

        ResponseDto responseDto = new ResponseDto<>();
        userAdminService.save(userAdminDto);
        responseDto.setContent(userAdminDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto<>();
        userAdminService.delete(id);
        return responseDto;
    }
}
