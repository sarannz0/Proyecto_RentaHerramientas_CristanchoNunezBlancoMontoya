package com.bkseducate.securityapp.infrastructure.config;

import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = Paths.get("uploads").toFile().getAbsolutePath();
        
        registry.addResourceHandler("/imagenes/**")
                .addResourceLocations("file:" + path + "/");
    }
}