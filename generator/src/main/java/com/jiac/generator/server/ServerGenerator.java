package com.jiac.generator.server;

import com.jiac.generator.test.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * FileName: ServerGenerator
 * Author: Jiac
 * Date: 2021/1/10 13:41
 */
public class ServerGenerator {

    static String toPath =
            "generator\\src\\main\\java\\com\\jiac\\generator\\test\\";

    public static void main(String[] args) throws IOException, TemplateException {
        FreemarkerUtil.initConfig("test.ftl");
        FreemarkerUtil.generator(toPath + "Test.java");
    }
}
