package com.jiac.server.mapper;

import com.jiac.server.domain.CourseCategory;
import com.jiac.server.domain.CourseCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbg.generated Wed Jan 13 13:48:49 GMT+08:00 2021
     */
    long countByExample(CourseCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbg.generated Wed Jan 13 13:48:49 GMT+08:00 2021
     */
    int deleteByExample(CourseCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbg.generated Wed Jan 13 13:48:49 GMT+08:00 2021
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbg.generated Wed Jan 13 13:48:49 GMT+08:00 2021
     */
    int insert(CourseCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbg.generated Wed Jan 13 13:48:49 GMT+08:00 2021
     */
    int insertSelective(CourseCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbg.generated Wed Jan 13 13:48:49 GMT+08:00 2021
     */
    List<CourseCategory> selectByExample(CourseCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbg.generated Wed Jan 13 13:48:49 GMT+08:00 2021
     */
    CourseCategory selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbg.generated Wed Jan 13 13:48:49 GMT+08:00 2021
     */
    int updateByExampleSelective(@Param("record") CourseCategory record, @Param("example") CourseCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbg.generated Wed Jan 13 13:48:49 GMT+08:00 2021
     */
    int updateByExample(@Param("record") CourseCategory record, @Param("example") CourseCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbg.generated Wed Jan 13 13:48:49 GMT+08:00 2021
     */
    int updateByPrimaryKeySelective(CourseCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbg.generated Wed Jan 13 13:48:49 GMT+08:00 2021
     */
    int updateByPrimaryKey(CourseCategory record);
}