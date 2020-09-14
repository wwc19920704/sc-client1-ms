package com.wwc.spring.cloud.client1.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wwc.spring.cloud.client1.config.JunitTestConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes=JunitTestConfig.class)
public class BaseTest {
	
	@Value("${test_key}")
	private String testKey;
	
	@Value("${test_key2}")
	private String testKey2;

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	RabbitAdmin rabbitAdmin;
	
	@Test
	public void test() {
		/**
		 * Exchange 操作
		 */
		//创建四种类型的 Exchange，均为持久化，不自动删除
//		rabbitAdmin.declareExchange(new DirectExchange("direct.exchange",true,false));
//		rabbitAdmin.declareExchange(new TopicExchange("topic.exchange",true,false));
//		rabbitAdmin.declareExchange(new FanoutExchange("fanout.exchange",true,false));
//		rabbitAdmin.declareExchange(new HeadersExchange("header.exchange",true,false));
		//删除 Exchange
//		rabbitAdmin.deleteExchange("header.exchange");

		//定义队列，均为持久化
		rabbitAdmin.declareQueue(new Queue("mytest",true));
	}
}
