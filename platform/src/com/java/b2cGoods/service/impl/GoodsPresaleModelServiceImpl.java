package com.java.b2cGoods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.java.b2cGoods.service.GoodsPresaleModelService;
import com.java.b2cGoods.vo.B2cGoodsPresaleModel;
import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.ServiceImpl;

public class GoodsPresaleModelServiceImpl extends ServiceImpl implements GoodsPresaleModelService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2cGoodsPresaleModel> getb2cGoodsPresaleModelByPage(
			PageSortModel psm, B2cGoodsPresaleModel b2cGoodsPresaleModel) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM B2cGoodsPresaleModel u where 1=1");
		if(b2cGoodsPresaleModel != null){
//			if(StringUtils.isNotEmpty(b2cGoodsPresaleModel.getDname())){
//				param.put("dname","%"+ b2cGoodsPresaleModel.getDname() + "%");
//				hql.append(" and u.dname like :dname");
//			}
		}
		List<B2cGoodsPresaleModel> list = (List<B2cGoodsPresaleModel>) listForEc(hql.toString(),psm, param);
		return list;
	}

	@Override
	public B2cGoodsPresaleModel getb2cGoodsPresaleModelById(Long id) {
		return this.get(id, B2cGoodsPresaleModel.class);
	}

	@Override
	public Long saveOrUpdateb2cGoodsPresaleModel(
			B2cGoodsPresaleModel b2cGoodsPresaleModel) {
		Long id = null;
		if(b2cGoodsPresaleModel.getId() == null){
			id = (Long) this.add(b2cGoodsPresaleModel);
		}else{
			id = b2cGoodsPresaleModel.getId();
			this.edit(b2cGoodsPresaleModel);
		}
		return id;
	}

}
