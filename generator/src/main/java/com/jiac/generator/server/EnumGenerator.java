package com.jiac.generator.server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FileName: EnumGenerator
 * Author: Jiac
 * Date: 2021/1/10 16:23
 */
public class EnumGenerator {

    public static String toUnderline(String str){
        String result = underline(str).toString();
        return result.substring(1, result.length()).toUpperCase().replace("_ENUM", "");
    }

    private static StringBuffer underline(String str){
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if(matcher.find()){
            sb = new StringBuffer();
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
            matcher.appendTail(sb);
        } else {
            return sb;
        }
        return underline(sb.toString());
    }
}
