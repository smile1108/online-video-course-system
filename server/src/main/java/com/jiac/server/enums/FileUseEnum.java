package com.jiac.server.enums;

/**
 * FileName: FileUseEnum
 * Author: Jiac
 * Date: 2021/1/14 12:40
 */
public enum FileUseEnum {

    COURSE("C", "课程"),
    TEACHER("T", "讲师");

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

    public static FileUseEnum getByCode(String code){
        for(FileUseEnum e : FileUseEnum.values()){
            if(code.equals(e.getCode())){
                return e;
            }
        }
        return null;
    }
}
