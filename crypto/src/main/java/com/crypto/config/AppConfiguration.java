package com.crypto.config;


import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author semih on Eylül, 2020, 24.09.2020, 22:55:16
 * <p>github: <a href="https://github.com/SemihSz ">
 */

@Configuration
@EnableRetry
public class AppConfiguration extends CachingConfigurerSupport {

	@Bean
	public MessageSource messageSource() {

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:Messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
