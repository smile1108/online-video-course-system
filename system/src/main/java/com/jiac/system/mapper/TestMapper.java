package com.jiac.system.mapper;

import com.jiac.system.domain.Test;
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
