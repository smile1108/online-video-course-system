package com.jiac.system.service;

import com.jiac.system.domain.Test;
import com.jiac.system.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TestMapper testMapper;

    public List<Test> list(){
        return testMapper.list();
    }
}
