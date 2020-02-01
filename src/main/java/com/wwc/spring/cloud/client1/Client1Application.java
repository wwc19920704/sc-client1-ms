package com.wwc.spring.cloud.client1;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
/**
 *	 去除加载dataSource
 * @author wwc
 *
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@SpringBootApplication
public class Client1Application {

	public static void main(String[] args) {
		SpringApplication.run(Client1Application.class, args);
	}
	
//	@Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
// 
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
// 
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
// 
//        };
//    }

}
