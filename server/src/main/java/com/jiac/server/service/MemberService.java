package com.jiac.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiac.server.domain.Member;
import com.jiac.server.domain.MemberExample;
import com.jiac.server.dto.MemberDto;
import com.jiac.server.dto.PageDto;
import com.jiac.server.mapper.MemberMapper;
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
 * FileName: MemberService
 * Author: Jiac
 * Date: 2021/1/7 10:22
 */
@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        MemberExample memberExample = new MemberExample();
        List<Member> memberList = memberMapper.selectByExample(memberExample);
        PageInfo<Member> pageInfo = new PageInfo<>(memberList);
        pageDto.setTotal(pageInfo.getTotal());
        List<MemberDto> memberDtoList = new ArrayList<>();
        for(int i = 0, l = memberList.size(); i < l; i++){
            Member member = memberList.get(i);
            MemberDto memberDto = new MemberDto();
            BeanUtils.copyProperties(member, memberDto);
            memberDtoList.add(memberDto);
        }
        pageDto.setList(memberDtoList);
    }

    public void save(MemberDto memberDto){
        Member member = CopyUtil.copy(memberDto, Member.class);
        if(StringUtils.isEmpty(memberDto.getId())){
            this.insert(member);
        } else {
            this.update(member);
        }
    }

    private void insert(Member member){
        Date now = new Date();
        member.setId(UuidUtil.getShortUuid());
        memberMapper.insert(member);
    }

    private void update(Member member){
        memberMapper.updateByPrimaryKey(member);
    }

    public void delete(String id) {
        memberMapper.deleteByPrimaryKey(id);
    }
}
