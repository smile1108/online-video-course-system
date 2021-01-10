package com.jiac.generator.server;

import com.jiac.generator.test.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: ServerGenerator
 * Author: Jiac
 * Date: 2021/1/10 13:41
 */
public class ServerGenerator {

    static String toServicePath =
            "server\\src\\main\\java\\com\\jiac\\server\\service\\";

    static String toControllerPath =
            "business\\src\\main\\java\\com\\jiac\\business\\controller\\admin\\";

    public static void main(String[] args) throws IOException, TemplateException {
        String Domain = "Section";
        String domain = "section";
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);

        // 生成service
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath + Domain + "Service.java", map);

        // 生成controller
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath + Domain + "Controller.java", map);
    }
}
