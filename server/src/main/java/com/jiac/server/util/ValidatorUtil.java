package com.jiac.server.util;

import com.jiac.server.exception.ValidatorException;
import org.springframework.util.StringUtils;

/**
 * FileName: ValidatorUtil
 * Author: Jiac
 * Date: 2021/1/9 15:38
 */
public class ValidatorUtil {

    // 空校验
    public static void require(String str, String fieldName){
        if(StringUtils.isEmpty(str)){
            throw new ValidatorException(fieldName + "不能为空");
        }
    }

    // 长度校验
    public static void length(String str, String fieldName, int min, int max){
        if(StringUtils.isEmpty(str)){
            return;
        }
        int length = 0;
        if(!StringUtils.isEmpty(str)){
            length = str.length();
        }
        if(length < min || length > max){
            throw new ValidatorException(fieldName + "长度" + min + "~" + max + "位");
        }
    }
}
