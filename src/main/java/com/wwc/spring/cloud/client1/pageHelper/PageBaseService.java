package com.wwc.spring.cloud.client1.pageHelper;

import java.util.List;

/**
 * 查询基础service,需要开发者自己去实现
 * @author wwc
 *
 */
public interface PageBaseService{
	
	/**
	 * 根据条件查询主键最大或者最小的那一个
	 * @param pageDto
	 * @return
	 */
	 List<Long> queryExtremesIdByCons(PageDto pageDto);
	
	 /**
	  * 根据主键查询实例
	  * @param ids
	  * @return
	  */
	 List queryByIds(List<Long> ids);
}
