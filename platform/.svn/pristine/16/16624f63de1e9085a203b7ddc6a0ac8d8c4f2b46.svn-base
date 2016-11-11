package com.java.b2cGoods.service;

import java.util.List;

import com.java.b2cGoods.vo.B2cGoodsOrder;
import com.java.ec.common.PageSortModel;
/**
 * 商品订单处理服务
 * @author lihao
 *
 */
public interface GoodsOrderService {
	/**
	 * 保存或更新商品订单信息
	 * @param b2cGoodsOrder
	 * @return
	 */
	public Long saveOrUpdateB2cGoodsOrder(B2cGoodsOrder b2cGoodsOrder) ;
	/**
	 * 商品订单列表分页
	 * @param psm
	 * @param b2cGoods
	 * @return
	 */
	List<B2cGoodsOrder> getB2cGoodsOrderListByPage(PageSortModel psm,B2cGoodsOrder b2cGoodsOrder);
	/**
	 * 根据id得到商品订单信息
	 * @param b2cGoods
	 * @return
	 */
	B2cGoodsOrder getB2cGoodsOrderById(B2cGoodsOrder b2cGoodsOrder);
}
