package com.java.b2cGoods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.java.b2cGoods.service.GoodsPropAndValService;
import com.java.b2cGoods.vo.B2cGoodsProperty;
import com.java.b2cGoods.vo.B2cGoodsPropertyValue;
import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.ServiceImpl;
import com.java.util.DateManage;

public class GoodsPropAndValServiceImpl extends ServiceImpl implements GoodsPropAndValService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<B2cGoodsProperty> getB2cGoodsPropertyByPage(PageSortModel psm,
			B2cGoodsProperty b2cGoodsProperty) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM B2cGoodsProperty u where 1=1");
		if(b2cGoodsProperty != null){
			if(StringUtils.isNotEmpty(b2cGoodsProperty.getPropName())){
				param.put("propName","%"+ b2cGoodsProperty.getPropName() + "%");
				hql.append(" and u.propName like :propName");
			}
		}
		hql.append(" order by u.createTime desc");
		List<B2cGoodsProperty> list = (List<B2cGoodsProperty>) listForEc(hql.toString(),psm, param);
		return list;
	}

	@Override
	public B2cGoodsProperty getB2cGoodsPropertyById(Long id) {
		return this.get(id, B2cGoodsProperty.class);
	}

	@Override
	public Long saveOrUpdateB2cGoodsProperty(B2cGoodsProperty b2cGoodsProperty) {
		if(b2cGoodsProperty == null) return null;
		Long id = null;
		b2cGoodsProperty.setCreateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
		if(b2cGoodsProperty.getId() == null){
			id = (Long) this.add(b2cGoodsProperty);
		}else{
			id = b2cGoodsProperty.getId();
			this.edit(b2cGoodsProperty);
		}
		return id;
	}

	@Override
	public List<B2cGoodsPropertyValue> getB2cGoodsPropertyValueByPage(
			PageSortModel psm, B2cGoodsPropertyValue b2cGoodsPropertyValue) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM B2cGoodsPropertyValue u where 1=1");
		if(b2cGoodsPropertyValue != null){
			if(StringUtils.isNotEmpty(b2cGoodsPropertyValue.getValName())){
				param.put("valName","%"+ b2cGoodsPropertyValue.getValName() + "%");
				hql.append(" and u.valName like :valName");
			}
		}
		hql.append(" order by u.b2cGoodsProperty.createTime desc");
		List<B2cGoodsPropertyValue> list = (List<B2cGoodsPropertyValue>) listForEc(hql.toString(),psm, param);
		return list;
	}

}
