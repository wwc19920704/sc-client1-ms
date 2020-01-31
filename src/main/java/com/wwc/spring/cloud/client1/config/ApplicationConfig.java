package com.wwc.spring.cloud.client1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wwc.spring.cloud.client1.bean.ConfigBean;

@Configuration
public class ApplicationConfig {

	@Value("${test_key}")
	private String testKey;
	
	@Value("${test_key2}")
	private String testKey2;
	
	@Bean
	public ConfigBean getConfigBean() {
		ConfigBean bean=new ConfigBean();
		bean.setTestKey(testKey);
		bean.setTestKey2(testKey2);
		return bean;
	}
}
