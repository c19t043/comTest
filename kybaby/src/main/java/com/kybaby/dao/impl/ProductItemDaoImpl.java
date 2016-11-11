package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.ProductItemDao;
import com.kybaby.domain.ProductItem;

/**
 * @ClassName:ProductItemDaoImpl
 * @Description:服务项目数据管理接口实现
 * @author Hoolee
 * @date 2015年10月5日上午11:35:49
 */
public class ProductItemDaoImpl extends HibernateDaoSupport implements ProductItemDao {

	public String getItemNameById(long itemId) {
		List<String> list=getHibernateTemplate().find(" select itemName from ProductItem where id=? ", itemId);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public String getItemShowNameById(long itemId) {
		List<String> list=getHibernateTemplate().find(" select comments from ProductItem where id=? ", itemId);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public ProductItem getProductItemInstanceByName(String itemNmae) {
		List<ProductItem> list=getHibernateTemplate().find("from ProductItem where itemName=?",itemNmae);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	
}
