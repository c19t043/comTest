package com.java.b2cGoods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.java.b2cGoods.service.GoodsDeliverService;
import com.java.b2cGoods.vo.B2cGoodsDeliver;
import com.java.b2cGoods.vo.B2cGoodsOrder;
import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.ServiceImpl;

public class GoodsDeliverServiceImpl extends ServiceImpl implements GoodsDeliverService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2cGoodsDeliver> getB2cGoodsDeliverByPage(PageSortModel psm,
			B2cGoodsDeliver b2cGoodsDeliver) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM B2cGoodsDeliver u where 1=1");
		if(b2cGoodsDeliver != null){
			if(StringUtils.isNotEmpty(b2cGoodsDeliver.getDname())){
				param.put("dname","%"+ b2cGoodsDeliver.getDname() + "%");
				hql.append(" and u.dname like :dname");
			}
		}
		List<B2cGoodsDeliver> list = (List<B2cGoodsDeliver>) listForEc(hql.toString(),psm, param);
		return list;
	}

	@Override
	public B2cGoodsDeliver getB2cGoodsDeliverById(Long id) {
		return this.get(id, B2cGoodsDeliver.class);
	}

	@Override
	public Long saveOrUpdateB2cGoodsDeliver(B2cGoodsDeliver b2cGoodsDeliver) {
		if(b2cGoodsDeliver == null) return null;
		Long id = null;
		if(b2cGoodsDeliver.getId() == null){
			id = (Long) this.add(b2cGoodsDeliver);
		}else{
			id = b2cGoodsDeliver.getId();
			this.edit(b2cGoodsDeliver);
		}
		return id;
	}

}
