package com.kybaby.bo;

import com.kybaby.domain.OrderInfo;

/**
 * @ClassName:OrderArchiveBo
 * @Description:删除订单的事物管理
 * @author Hoolee
 * @date 2015年9月21日下午3:40:10
 */
public interface OrderArchiveBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:添加删除的订单实例
	 * @data: 2015年9月21日下午3:41:07
	 * @param order 待添加的被删除掉的订单
	 */
	void addNewOrderArchive(OrderInfo order);
	
}
