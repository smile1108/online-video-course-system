package com.jiac.server.enums;

/**
 * FileName: FileUseEnum
 * Author: Jiac
 * Date: 2021/1/14 12:40
 */
public enum FileUseEnum {

    COURSE("C", "讲师"),
    TEACHER("T", "课程");

    private String code;

    private String desc;

    FileUseEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
