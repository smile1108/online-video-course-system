package com.jiac.server.domain;

import java.util.Date;

public class Member {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member.id
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member.mobile
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member.password
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member.name
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member.photo
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    private String photo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column member.register_time
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    private Date registerTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member.id
     *
     * @return the value of member.id
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member.id
     *
     * @param id the value for member.id
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member.mobile
     *
     * @return the value of member.mobile
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member.mobile
     *
     * @param mobile the value for member.mobile
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member.password
     *
     * @return the value of member.password
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member.password
     *
     * @param password the value for member.password
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member.name
     *
     * @return the value of member.name
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member.name
     *
     * @param name the value for member.name
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member.photo
     *
     * @return the value of member.photo
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member.photo
     *
     * @param photo the value for member.photo
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column member.register_time
     *
     * @return the value of member.register_time
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column member.register_time
     *
     * @param registerTime the value for member.register_time
     *
     * @mbg.generated Tue Jan 19 10:28:34 GMT+08:00 2021
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}