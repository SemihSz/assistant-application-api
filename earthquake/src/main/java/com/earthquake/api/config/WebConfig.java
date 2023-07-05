package com.earthquake.api.config;

import com.earthquake.api.service.logger.LogClientRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Semih, 8.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogClientRequestInterceptor());
    }
}
