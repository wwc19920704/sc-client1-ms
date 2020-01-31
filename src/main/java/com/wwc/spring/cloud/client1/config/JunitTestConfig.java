package com.wwc.spring.cloud.client1.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class JunitTestConfig {

}
