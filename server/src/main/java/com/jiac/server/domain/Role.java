package com.jiac.server.domain;

public class Role {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.id
     *
     * @mbg.generated Mon Jan 18 10:47:07 GMT+08:00 2021
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.name
     *
     * @mbg.generated Mon Jan 18 10:47:07 GMT+08:00 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.desc
     *
     * @mbg.generated Mon Jan 18 10:47:07 GMT+08:00 2021
     */
    private String desc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.id
     *
     * @return the value of role.id
     *
     * @mbg.generated Mon Jan 18 10:47:07 GMT+08:00 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.id
     *
     * @param id the value for role.id
     *
     * @mbg.generated Mon Jan 18 10:47:07 GMT+08:00 2021
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.name
     *
     * @return the value of role.name
     *
     * @mbg.generated Mon Jan 18 10:47:07 GMT+08:00 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.name
     *
     * @param name the value for role.name
     *
     * @mbg.generated Mon Jan 18 10:47:07 GMT+08:00 2021
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.desc
     *
     * @return the value of role.desc
     *
     * @mbg.generated Mon Jan 18 10:47:07 GMT+08:00 2021
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.desc
     *
     * @param desc the value for role.desc
     *
     * @mbg.generated Mon Jan 18 10:47:07 GMT+08:00 2021
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}