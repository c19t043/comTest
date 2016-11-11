package com.kybaby.newbussiness.b2cgoods.bo.impl;

import java.util.List;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.b2cgoods.bo.GoodsOrderService;
import com.kybaby.newbussiness.b2cgoods.dao.GoodsOrderDao;
import com.kybaby.newbussiness.b2cgoods.domain.B2cAddress;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoods;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsDeliver;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrder;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrderDetail;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrderPromotion;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsSku;
/**
 * 商品订单相关处理
 * @author lihao
 *
 */
public class GoodsOrderServiceImpl implements GoodsOrderService{
	private GoodsOrderDao goodsOrderDao;

	public GoodsOrderDao getGoodsOrderDao() {
		return goodsOrderDao;
	}

	public void setGoodsOrderDao(GoodsOrderDao goodsOrderDao) {
		this.goodsOrderDao = goodsOrderDao;
	}

	@Override
	public B2cGoodsOrder getB2cGoodsOrderById(Long id) {
		return goodsOrderDao.getB2cGoodsOrderById(id);
	}

	@Override
	public Long saveOrUpdateB2cGoodsOrder(B2cGoodsOrder b2cGoodsOrder) {
		return goodsOrderDao.saveOrUpdateB2cGoodsOrder(b2cGoodsOrder);
	}

	@Override
	public List<B2cGoodsDeliver> getB2cGoodsDeliverList(
			B2cGoodsDeliver b2cGoodsDeliver) {
		return goodsOrderDao.getB2cGoodsDeliverList(b2cGoodsDeliver);
	}

	@Override
	public List<B2cGoodsOrderDetail> getB2cGoodsOrderDetailList(
			B2cGoodsOrder b2cGoodsOrder, B2cGoods b2cGoods,
			B2cGoodsSku b2cGoodsSku) {
		return goodsOrderDao.getB2cGoodsOrderDetailList(b2cGoodsOrder, b2cGoods, b2cGoodsSku);
	}

	@Override
	public List<B2cAddress> getB2cAddressList(UserInfo userInfo,String isMain) {
		return goodsOrderDao.getB2cAddressList(userInfo,isMain);
	}

	@Override
	public Long saveOrUpdateB2cAddress(B2cAddress b2cAddress) {
		return goodsOrderDao.saveOrUpdateB2cAddress(b2cAddress);
	}

	@Override
	public Long saveOrUpdateB2cGoodsOrderPromotion(
			B2cGoodsOrderPromotion b2cGoodsOrderPromotion) {
		return goodsOrderDao.saveOrUpdateB2cGoodsOrderPromotion(b2cGoodsOrderPromotion);
	}

	@Override
	public B2cAddress getB2cAddressById(Long id) {
		return goodsOrderDao.getB2cAddressById(id);
	}

	@Override
	public List<B2cGoodsOrder> getB2cGoodsOrderList(B2cGoodsOrder b2cGoodsOrder) {
		return goodsOrderDao.getB2cGoodsOrderList(b2cGoodsOrder);
	}
}
