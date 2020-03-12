package com.wwc.spring.cloud.client1.pageHelper;

import java.util.List;

/**
 * 查询到结果集之后的回调函数,需要开发人员自己去实现
 * @author wwc
 *
 */
public abstract class PageCallBack {

	/**
	 * 分页查询得到结果集之后的操作
	 * @param result 实体结果集
	 * @param ids	主键集合
	 * @param 返回值
	 */
	public abstract void excute(List result,List<Long> ids,Object ret) throws Exception;
}
