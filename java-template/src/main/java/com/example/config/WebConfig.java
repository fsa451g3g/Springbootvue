package com.example.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Component
public class WebConfig implements WebMvcConfigurer {

    public static final String FILEPATH = System.getProperty("user.dir") + File.separator + "file";

    public static final String LOGFILEPATH = FILEPATH + File.separator + "log";

    public static final String UPLOADFILEPATH = FILEPATH + File.separator + "upload";

    public static final String CAROUSELPATH = FILEPATH + File.separator + "carousel";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/v/**")
                .addResourceLocations("file:" + FILEPATH + File.separator);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
