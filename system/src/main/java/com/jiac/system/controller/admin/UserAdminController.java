package com.jiac.system.controller.admin;

import com.jiac.server.domain.UserAdmin;
import com.jiac.server.dto.*;
import com.jiac.server.service.UserAdminService;
import com.jiac.server.util.UuidUtil;
import com.jiac.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * FileName: UserAdminController
 * Author: Jiac
 * Date: 2021/1/6 16:21
 */
@RestController
@RequestMapping("/admin/userAdmin")
public class UserAdminController {

    public static final String BUSINESS_NAME = "用户";

    private static final Logger LOG = LoggerFactory.getLogger(UserAdminController.class);

    @Resource
    private UserAdminService userAdminService;

    @Resource
    private RedisTemplate redisTemplate;

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

    /**
     * 重置密码
     * @param userAdminDto
     * @return
     */
    @PostMapping("/save-password")
    public ResponseDto savePassword(@RequestBody UserAdminDto userAdminDto){
        userAdminDto.setPassword(DigestUtils.md5DigestAsHex(userAdminDto.getPassword().getBytes()));

        ResponseDto responseDto = new ResponseDto<>();
        userAdminService.savePassword(userAdminDto);
        responseDto.setContent(userAdminDto);
        return responseDto;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseDto login(@RequestBody UserAdminDto userAdminDto, HttpServletRequest request){
        LOG.info("用户登录开始");
        userAdminDto.setPassword(DigestUtils.md5DigestAsHex(userAdminDto.getPassword().getBytes()));

        ResponseDto responseDto = new ResponseDto<>();

        // 根据验证码token去获取缓存中的验证码，和用户输入的验证码是否一致
//        String imageCode = (String) request.getSession().getAttribute(userAdminDto.getImageCode());
        String imageCode = (String) redisTemplate.opsForValue().get(userAdminDto.getImageCodeToken());
        LOG.info("从redis中获取到的验证码：{}", imageCode);
        if(StringUtils.isEmpty(imageCode)) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码已过期");
            LOG.info("用户登录失败");
            return responseDto;
        }
        if(!imageCode.toLowerCase().equals(userAdminDto.getImageCode().toLowerCase())) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码不正确");
            LOG.info("用户登录失败");
            return responseDto;
        } else {
            // 验证通过后，移除验证码
//            request.getSession().removeAttribute(userAdminDto.getImageCodeToken());
            redisTemplate.delete(userAdminDto.getImageCodeToken());
        }
        LoginUserAdminDto loginUserAdminDto = userAdminService.login(userAdminDto);
        String token = UuidUtil.getShortUuid();
        loginUserAdminDto.setToken(token);
        redisTemplate.opsForValue().set(token, loginUserAdminDto.toString(), 3600, TimeUnit.SECONDS);
//        request.getSession().setAttribute(Constants.LOGIN_USER, loginUserAdminDto);
        responseDto.setContent(loginUserAdminDto);
        return responseDto;
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout/{token}")
    public ResponseDto logout(@PathVariable String token){
        ResponseDto responseDto = new ResponseDto<>();
        redisTemplate.delete(token);
        LOG.info("从redis中删除token：{}", token);
        return responseDto;
    }
}
