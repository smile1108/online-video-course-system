package com.jiac.server.mapper;

import com.jiac.server.domain.CourseInfo;
import com.jiac.server.domain.CourseInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_info
     *
     * @mbg.generated Wed Jan 13 15:11:51 GMT+08:00 2021
     */
    long countByExample(CourseInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_info
     *
     * @mbg.generated Wed Jan 13 15:11:51 GMT+08:00 2021
     */
    int deleteByExample(CourseInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_info
     *
     * @mbg.generated Wed Jan 13 15:11:51 GMT+08:00 2021
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_info
     *
     * @mbg.generated Wed Jan 13 15:11:51 GMT+08:00 2021
     */
    int insert(CourseInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_info
     *
     * @mbg.generated Wed Jan 13 15:11:51 GMT+08:00 2021
     */
    int insertSelective(CourseInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_info
     *
     * @mbg.generated Wed Jan 13 15:11:51 GMT+08:00 2021
     */
    List<CourseInfo> selectByExample(CourseInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_info
     *
     * @mbg.generated Wed Jan 13 15:11:51 GMT+08:00 2021
     */
    CourseInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_info
     *
     * @mbg.generated Wed Jan 13 15:11:51 GMT+08:00 2021
     */
    int updateByExampleSelective(@Param("record") CourseInfo record, @Param("example") CourseInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_info
     *
     * @mbg.generated Wed Jan 13 15:11:51 GMT+08:00 2021
     */
    int updateByExample(@Param("record") CourseInfo record, @Param("example") CourseInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_info
     *
     * @mbg.generated Wed Jan 13 15:11:51 GMT+08:00 2021
     */
    int updateByPrimaryKeySelective(CourseInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_info
     *
     * @mbg.generated Wed Jan 13 15:11:51 GMT+08:00 2021
     */
    int updateByPrimaryKey(CourseInfo record);
}