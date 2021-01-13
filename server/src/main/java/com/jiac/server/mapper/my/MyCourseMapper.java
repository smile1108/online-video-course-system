package com.jiac.server.mapper.my;

import org.apache.ibatis.annotations.Param;

/**
 * FileName: MyCourseMapper
 * Author: Jiac
 * Date: 2021/1/13 9:03
 */
public interface MyCourseMapper {

    int updateTime(@Param("courseId") String courseId);
}
