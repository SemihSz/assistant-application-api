package com.earthquake.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableEurekaClient
public class EarthQuakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EarthQuakeApplication.class, args);
	}

}
