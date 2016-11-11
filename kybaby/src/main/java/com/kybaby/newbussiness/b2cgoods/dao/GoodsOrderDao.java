package com.kybaby.newbussiness.b2cgoods.dao;

import java.util.List;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.b2cgoods.domain.B2cAddress;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoods;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsDeliver;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrder;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrderDetail;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrderPromotion;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsSku;

public interface GoodsOrderDao {
	/**
	 * 得到订单信息根据id
	 * @param Id
	 * @return
	 */
	B2cGoodsOrder getB2cGoodsOrderById(Long id);
	/**
	 * 保存或更新订单信息
	 * @param b2cGoodsOrder
	 * @return
	 */
	Long saveOrUpdateB2cGoodsOrder(B2cGoodsOrder b2cGoodsOrder);
	/**
	 * 获取送货方式信息
	 * @param b2cGoodsDeliver
	 * @return
	 */
	List<B2cGoodsDeliver> getB2cGoodsDeliverList(B2cGoodsDeliver b2cGoodsDeliver);
	/**
	 * 收货地址列表
	 * @param b2cAddress
	 * @return
	 */
	List<B2cAddress> getB2cAddressList(B2cAddress b2cAddress);
	/**
	 * 得到订单列表
	 * @param b2cGoodsOrder
	 * @param b2cGoods
	 * @param b2cGoodsSku
	 * @return
	 */
	List<B2cGoodsOrderDetail> getB2cGoodsOrderDetailList(B2cGoodsOrder b2cGoodsOrder,B2cGoods b2cGoods,B2cGoodsSku b2cGoodsSku);
	/**
	 * 得到订单列表(直接从订单查)
	 * @param b2cGoodsOrder
	 * @return
	 */
	List<B2cGoodsOrder> getB2cGoodsOrderList(B2cGoodsOrder b2cGoodsOrder);
	/**
	 * 根据id得到地址
	 * @param id
	 * @return
	 */
	B2cAddress getB2cAddressById(Long id);
	/**
	 * 得到 用户的收货地址列表
	 * @param userInfo
	 * @return
	 */
	List<B2cAddress> getB2cAddressList(UserInfo userInfo,String isMain);
	/**
	 * 保存更新地址
	 * @param b2cAddress
	 * @return
	 */
	Long saveOrUpdateB2cAddress(B2cAddress b2cAddress);
	/**
	 * 保存或更新订单对应的促销信息
	 * @param b2cGoodsOrderPromotion
	 * @return
	 */
	Long saveOrUpdateB2cGoodsOrderPromotion(B2cGoodsOrderPromotion b2cGoodsOrderPromotion);
}
