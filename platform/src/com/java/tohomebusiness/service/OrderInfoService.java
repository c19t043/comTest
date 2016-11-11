package com.java.tohomebusiness.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.tohomebusiness.vo.OrderInfo;

public interface OrderInfoService {
	/**
	 * 分页列表
	 * @param psm
	 * @param orderInfo
	 * @return
	 */
	List<OrderInfo> getOrderInfoListByPage(PageSortModel psm,OrderInfo orderInfo);
}
