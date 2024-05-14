package com.example.datacollection_microservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MvcConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        LOGGER.info("Configuring resource handler for file access...");
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:///C:/Users/Emma/Desktop/HA%20Prediction%20ML/Backend/DataCollection_Microservice/uploads/");
    }
}
