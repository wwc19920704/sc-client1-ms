package com.wwc.spring.cloud.client1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wwc.spring.cloud.client1.bean.ConfigBean;

@RestController
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private ConfigBean configBean;
	
	@RequestMapping("test")
	public String getTest1() {
		return configBean.getTestKey();
	}
	
	@RequestMapping("test2")
	public String getTest2() {
		return configBean.getTestKey2();
	}
}
