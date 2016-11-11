package com.java.familydoctor.userbuyrecord.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.userbuyrecord.vo.FdServiceOrder;
import com.java.familydoctor.userbuyrecord.vo.FdUserBuyRecord;
import com.java.platform.user.service.Service;


public interface FdServiceOrderService extends Service{
	
	
	/**
	 * 订单记录修改方法
	 */
	Long updateFdServiceOrder(FdServiceOrder fdServiceOrder,FdUserBuyRecord fdUserBuyRecord);
	
	/**
	 * 订单记录通过id查询一条数据
	 */
	FdServiceOrder getFdServiceOrderById(Long id);
	
	/**
	 * 订单记录列表方法
	 */
	List<FdServiceOrder> getFdServiceOrderByPage(PageSortModel psm,FdServiceOrder fdServiceOrder);
	
	/**
	 * 通过购买订单的id去查询购买记录是否存在
	 */
	List<FdUserBuyRecord> getFdUserBuyRecordIdByFdServiceOrder(FdServiceOrder fdServiceOrder);
}
