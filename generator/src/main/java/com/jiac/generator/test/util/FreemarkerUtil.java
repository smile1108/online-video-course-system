package com.jiac.generator.test.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileName: FreemarkerUtil
 * Author: Jiac
 * Date: 2021/1/10 13:36
 */
public class FreemarkerUtil {

    static String ftlPath =
            "generator\\src\\main\\java\\com\\jiac\\generator\\ftl\\";

    static Template template;

    public static void initConfig(String ftlName) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setDirectoryForTemplateLoading(new File(ftlPath));
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
        template = cfg.getTemplate(ftlName);
    }


    public static void generator(String fileName) throws IOException, TemplateException {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        template.process(null, bw);
        bw.flush();
        fw.close();
    }
}
