package com.jiac.generator.test;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileName: TestUtil
 * Author: Jiac
 * Date: 2021/1/10 9:04
 */
public class TestUtil {
    static String ftlPath =
            "generator\\src\\main\\java\\com\\jiac\\generator\\test\\";
    static String toPath =
            "generator\\src\\main\\java\\com\\jiac\\generator\\test\\";

    public static void main(String[] args) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setDirectoryForTemplateLoading(new File(ftlPath));
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
        Template template = cfg.getTemplate("test.ftl");

        FileWriter fw = new FileWriter(toPath + "Test.java");
        BufferedWriter bw = new BufferedWriter(fw);
        template.process(null, bw);
        bw.flush();
        fw.close();
    }
}
