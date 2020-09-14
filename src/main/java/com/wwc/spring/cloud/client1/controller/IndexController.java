package com.wwc.spring.cloud.client1.controller;

import com.wwc.spring.cloud.client1.bean.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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
	public String getTest2(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		Cookie[] cookies=httpServletRequest.getCookies();
		if(cookies==null||cookies.length<=0) {
			String myToken=UUID.randomUUID().toString();
			Cookie cookie=new Cookie("myToken", myToken);
			cookie.setPath("/");
			httpServletResponse.addCookie(cookie);
			return configBean.getTestKey2();
		}
		for (Cookie cookie : cookies) {
			if("myToken".equals(cookie.getName())) {
				
			}
		}
		return configBean.getTestKey2();
	}
}
