package com.jiac.generator.server;

import com.jiac.generator.util.DbUtil;
import com.jiac.generator.util.Field;
import com.jiac.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.*;

/**
 * FileName: ServerGenerator
 * Author: Jiac
 * Date: 2021/1/10 13:41
 */
public class ServerGenerator {

    static String MODULE = "business";

    static String toServicePath =
            "server\\src\\main\\java\\com\\jiac\\server\\service\\";

    static String toDtoPath =
            "server\\src\\main\\java\\com\\jiac\\server\\dto\\";

    static String toControllerPath =
            MODULE + "\\src\\main\\java\\com\\jiac\\" + MODULE + "\\controller\\admin\\";


    public static void main(String[] args) throws Exception {
        String Domain = "Section";
        String domain = "section";
        String tableNameCn = "小节";
        String module = MODULE;
        List<Field> fieldList = DbUtil.getColumnByTableName(domain);
        Set<String> typeSet = getJavaTypes(fieldList);
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("tableNameCn", tableNameCn);
        map.put("module", module);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);

        // 生成dto
        FreemarkerUtil.initConfig("dto.ftl");
        FreemarkerUtil.generator(toDtoPath + Domain + "Dto.java", map);

        // 生成service
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath + Domain + "Service.java", map);

        // 生成controller
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath + Domain + "Controller.java", map);
    }

    private static Set<String> getJavaTypes(List<Field> fieldList){
        Set<String> set = new HashSet<>();
        for(int i = 0; i < fieldList.size(); i++){
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }
}
