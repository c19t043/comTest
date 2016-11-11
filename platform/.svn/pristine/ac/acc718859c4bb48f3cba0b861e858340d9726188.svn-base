package com.java.b2cGoods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.java.b2cGoods.service.GoodsTypeService;
import com.java.b2cGoods.vo.B2cGoodsType;
import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.ServiceImpl;

public class GoodsTypeServiceImpl extends ServiceImpl implements GoodsTypeService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2cGoodsType> getB2cGoodsTypeByPage(PageSortModel psm,
			B2cGoodsType b2cGoodsType) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM B2cGoodsType u where 1=1");
		if(b2cGoodsType != null){
			if(StringUtils.isNotEmpty(b2cGoodsType.getTypeName())){
				param.put("typeName","%"+ b2cGoodsType.getTypeName() + "%");
				hql.append(" and u.typeName like :typeName");
			}
		}
		List<B2cGoodsType> list = (List<B2cGoodsType>) listForEc(hql.toString(),psm, param);
		return list;
	}

	@Override
	public B2cGoodsType getB2cGoodsTypeById(Long id) {
		return this.get(id, B2cGoodsType.class);
	}

	@Override
	public Long saveOrUpdateB2cGoodsType(B2cGoodsType b2cGoodsType) {
		if(b2cGoodsType == null) return null;
		Long id = null;
		if(b2cGoodsType.getId() == null){
			id = (Long) this.add(b2cGoodsType);
		}else{
			id = b2cGoodsType.getId();
			this.edit(b2cGoodsType);
		}
		return id;
	}

}
