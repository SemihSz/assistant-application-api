package com.earthquake.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * Created by Semih, 11.10.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Configuration
public class FreeMarkerConfig {

    @Bean
    @Primary
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfigurationFactoryBean(){
       final FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:/freemarker/"); //defines the classpath location of the freemarker templates
        bean.setDefaultEncoding("UTF-8"); // Default encoding of the template files
        return bean;
    }

}
