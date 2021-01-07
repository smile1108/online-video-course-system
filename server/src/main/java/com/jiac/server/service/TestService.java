package com.jiac.server.service;

import com.jiac.server.domain.Test;
import com.jiac.server.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: TestService
 * Author: Jiac
 * Date: 2021/1/7 10:22
 */
@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list(){
        return null;
    }
}
