package com.jiac.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * FileName: SpringMvcConfig
 * Author: Jiac
 * Date: 2021/1/14 12:06
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    // 静态资源的配置
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/f/**")
                .addResourceLocations("file:C:/Users/jc/Desktop/github/online_video_course_system/");
    }
}
