package com.java.b2cGoods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.java.b2cGoods.service.GoodsOrderService;
import com.java.b2cGoods.vo.B2cGoods;
import com.java.b2cGoods.vo.B2cGoodsOrder;
import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.ServiceImpl;

public class GoodsOrderServiceImpl extends ServiceImpl implements GoodsOrderService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long saveOrUpdateB2cGoodsOrder(B2cGoodsOrder b2cGoodsOrder) {
		if(b2cGoodsOrder == null){
			return null;
		}
		Long id = null;
		if(b2cGoodsOrder.getId() == null){
			id = (Long)super.add(b2cGoodsOrder);
		}else{
			id = b2cGoodsOrder.getId();
			super.edit(b2cGoodsOrder);
		}
		return id;
	}

	@Override
	public List<B2cGoodsOrder> getB2cGoodsOrderListByPage(PageSortModel psm,
			B2cGoodsOrder b2cGoodsOrder) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM B2cGoodsOrder u where 1=1");
		if(b2cGoodsOrder != null){
			if(StringUtils.isNotEmpty(b2cGoodsOrder.getOrderNum())){
				param.put("orderNum", "%" + b2cGoodsOrder.getOrderNum() + "%");
				hql.append(" and u.orderNum LIKE :orderNum");
			}
			if(StringUtils.isNotEmpty(b2cGoodsOrder.getBphone())){
				param.put("bphone", "%" + b2cGoodsOrder.getBphone() + "%");
				hql.append(" and u.bphone LIKE :bphone");
			}
			if(StringUtils.isNotEmpty(b2cGoodsOrder.getOrderStatus())){
				param.put("orderStatus", "%" + b2cGoodsOrder.getOrderStatus() + "%");
				hql.append(" and u.orderStatus LIKE :orderStatus");
			}
			if(b2cGoodsOrder.getUserInfo() != null){
				if(StringUtils.isNotEmpty(b2cGoodsOrder.getUserInfo().getPhone())){
					param.put("phone", "%" + b2cGoodsOrder.getUserInfo().getPhone() + "%");
					hql.append(" and u.userInfo.phone LIKE :phone");
				}
			}
		}
		hql.append(" order by u.submitTime desc,u.updateTime desc");
		List<B2cGoodsOrder> list = (List<B2cGoodsOrder>) listForEc(hql.toString(),psm, param);
		return list;
	}

	@Override
	public B2cGoodsOrder getB2cGoodsOrderById(B2cGoodsOrder b2cGoodsOrder) {
		return this.get(b2cGoodsOrder.getId(),B2cGoodsOrder.class);
	}

}
