package com.jiac.business.controller.web;

import com.jiac.server.dto.MemberDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.dto.ResponseDto;
import com.jiac.server.service.MemberService;
import com.jiac.server.util.ValidatorUtil;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * FileName: MemberController
 * Author: Jiac
 * Date: 2021/1/6 16:21
 */
@RestController("webMemberController")
@RequestMapping("/web/member")
public class MemberController {

    public static final String BUSINESS_NAME = "会员";

    @Resource
    private MemberService memberService;

    @PostMapping("/register")
    public ResponseDto register(@RequestBody MemberDto memberDto) {
        // 保存校验
        ValidatorUtil.require(memberDto.getMobile(), "手机号");
        ValidatorUtil.length(memberDto.getMobile(), "手机号", 11, 11);
        ValidatorUtil.require(memberDto.getPassword(), "密码");
        ValidatorUtil.length(memberDto.getName(), "昵称", 1, 50);
        ValidatorUtil.length(memberDto.getPhoto(), "头像url", 1, 200);

        // 密码加密
        memberDto.setPassword(DigestUtils.md5DigestAsHex(memberDto.getPassword().getBytes()));

        ResponseDto responseDto = new ResponseDto();
        memberService.save(memberDto);
        responseDto.setContent(memberDto);
        return responseDto;
    }
}
