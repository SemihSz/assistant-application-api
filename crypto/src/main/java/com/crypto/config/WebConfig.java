package com.crypto.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Semih, 8.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.crypto")
public class WebConfig implements WebMvcConfigurer {


    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return new DefaultMessageCodesResolver() {
            @Override
            public String[] resolveMessageCodes(String errorCode, String objectName, String field, Class<?> fieldType) {
                return new String[] {errorCode};
            }
        };
    }

    @Bean
    public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestResponseLoggingFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
