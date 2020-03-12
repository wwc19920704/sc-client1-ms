package com.wwc.spring.cloud.client1.pageHelper;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;

import com.alibaba.fastjson.JSON;

/**
 * 	分页组件,仅适合单表查询,若在实现基础service时,进行多表关联查询时,性能不佳
 * @author wwc
 *
 */
public class PageUtils {
	
	protected static Logger logger = org.slf4j.LoggerFactory.getLogger(PageUtils.class);
	
	/**
	 * 基础查询方法,
	 * @param baseService  实现BaseService中的queryExtremesIdByCons,queryByIds两个方法的实现类
	 * @param pageDto	继承PageDto的参数
	 * @param callBack  查询到结果的处理回调函数
	 * @param param 额外需要传递的对象
	 * @throws Exception
	 */
	public static void queryAndExcute(PageBaseService baseService,
			PageDto pageDto,PageCallBack callBack,Object param)throws Exception {
		logger.debug("start_page,page_cons=="+JSON.toJSONString(pageDto)+",baseDao="+baseService);
		//初始化最大id
		Long maxId=0l;
		//初始化最小id
		Long minId=0l;
		//查询最大值时，每次只能取一个
		pageDto.setPageSize(PageDto.ONE);
		//找最大的倒序
		pageDto.setOrder(PageDto.ORDER_DESC);
		List<Long> maxIdReult=baseService.queryExtremesIdByCons(pageDto);
		if(maxIdReult!=null&&!maxIdReult.isEmpty()) {
			maxId=maxIdReult.get(0);
		}
		logger.debug("page_maxId ="+maxId);
		//找最小的主键,正序
		pageDto.setOrder(PageDto.ORDER_ASC);
		List<Long> minIdresults=baseService.queryExtremesIdByCons(pageDto);
		if(minIdresults!=null&& !minIdresults.isEmpty()) {
			minId=minIdresults.get(0);
		}
		logger.debug("page_minId="+minId);
		//循环查..
		//头指针
		Long headIndex=minId;
		//尾指针
		Long tailIndex=maxId+1;
		//循环次数
		int queryTimes=1;
		//本次查询的最大index
		Long lastIndexInList=0l;
		//开始循环
		while(circuitCondition(pageDto, queryTimes, headIndex, tailIndex)) {
			//组装查询条件
			pageDto.setHeadIndex(headIndex);
			pageDto.setTailIndex(tailIndex);
			pageDto.setOrder(null);
			pageDto.setPageSize(pageDto.getQueryLimitCustomize());
			///查询出符合条件的id集合
			List<Long> idList=baseService.queryExtremesIdByCons(pageDto);
			if(idList!=null&& !idList.isEmpty()) {
				//正序排序;升序排序
				Collections.sort(idList);
				//获取最大的元素
				lastIndexInList=idList.get(idList.size() - 1);
				//头index设为本次循环的最大id+1,下次循环从id+1开始[lastIndexInList+1,tailIndex)
				lastIndexInList++;
				//对象结果集
				List objs=null;
				if(pageDto.isNeedObjList()) {
					objs=baseService.queryByIds(idList);
					if(objs!=null && !objs.isEmpty()) {
						//执行回调函数的方法
						callBack.excute(objs,idList,param);
					}
				}else {
					//执行混调函数里的方法
					callBack.excute(null,idList,null);
				}
			}
			headIndex+=pageDto.getPrimaryDifference();
			if(headIndex<lastIndexInList) {
				headIndex=lastIndexInList;
			}
			queryTimes++;
		}
		logger.debug("end_page,queryTimes=="+queryTimes);
	}
	
	/**
	 * 判断循环条件是否成立
	 * @param pageDto 分页参数
	 * @param queryTimes 当前查询次数
	 * @param headIndex
	 * @param headIndex
	 * @return
	 */
	private static boolean circuitCondition(PageDto pageDto,int queryTimes,Long headIndex,Long tailIndex) {
		//查询次数限制不为空或者查询次数大于0,则判断条件,优先使用此
		if(null!=pageDto.getQueryTimes()
		  || 0<pageDto.getQueryTimes()) {
			if(pageDto.getQueryTimes()<queryTimes) {
				return false;
			}
			if(headIndex>tailIndex) {
				return false;
			}
			return true;
		}
		return (headIndex<=tailIndex)?true:false; 
	}
}
