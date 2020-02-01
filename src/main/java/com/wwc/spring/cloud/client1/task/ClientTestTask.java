package com.wwc.spring.cloud.client1.task;

import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;

@Component
public class ClientTestTask{

	@XxlJob("clientTestTask1")
	public ReturnT<String> clientTestTask1(String param) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.err.println("线程休息2s");
			Thread.currentThread().sleep(2000);
		} catch (Exception e) {
			System.err.println("任务执行失败");
			throw e;
		}
		return ReturnT.SUCCESS;
	}
	
	@XxlJob("clientTestTask2")
	public ReturnT<String> clientTestTask2(String param) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.err.println("线程休息3s");
			Thread.currentThread().sleep(3000);
		} catch (Exception e) {
			System.err.println("任务执行失败");
			throw e;
		}
		return ReturnT.SUCCESS;
	}
}
