package com.wwc.spring.cloud.client1.test;

import org.junit.Test;
import org.junit.runner.RunWith;
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
	
	
	@Test
	public void test() {
		System.err.println("test_key="+testKey);
		System.err.println("test_key2="+testKey2);
	}
}
