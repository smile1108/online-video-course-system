package com.jiac.server.mapper.my;

import com.jiac.server.dto.CourseInfoDto;
import com.jiac.server.dto.CoursePageDto;
import com.jiac.server.dto.SortDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * FileName: MyCourseMapper
 * Author: Jiac
 * Date: 2021/1/13 9:03
 */
public interface MyCourseMapper {

    List<CourseInfoDto> list(@Param("pageDto")CoursePageDto pageDto);

    int updateTime(@Param("courseId") String courseId);

    int updateSort(SortDto sortDto);

    int moveSortsBackward(SortDto sortDto);

    int moveSortsForward(SortDto sortDto);
}
