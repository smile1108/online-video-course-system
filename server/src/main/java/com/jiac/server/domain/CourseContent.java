package com.jiac.server.domain;

public class CourseContent {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_content.id
     *
     * @mbg.generated Wed Jan 13 14:23:45 GMT+08:00 2021
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_content.content
     *
     * @mbg.generated Wed Jan 13 14:23:45 GMT+08:00 2021
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_content.id
     *
     * @return the value of course_content.id
     *
     * @mbg.generated Wed Jan 13 14:23:45 GMT+08:00 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_content.id
     *
     * @param id the value for course_content.id
     *
     * @mbg.generated Wed Jan 13 14:23:45 GMT+08:00 2021
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_content.content
     *
     * @return the value of course_content.content
     *
     * @mbg.generated Wed Jan 13 14:23:45 GMT+08:00 2021
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_content.content
     *
     * @param content the value for course_content.content
     *
     * @mbg.generated Wed Jan 13 14:23:45 GMT+08:00 2021
     */
    public void setContent(String content) {
        this.content = content;
    }
}