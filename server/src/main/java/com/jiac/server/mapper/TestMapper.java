package com.jiac.server.mapper;

import com.jiac.server.domain.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FileName: TestMapper
 * Author: Jiac
 * Date: 2021/1/7 10:16
 */
@Repository
public interface TestMapper {

    List<Test> list();
}
