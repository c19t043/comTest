package com.kybaby.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.ItemResultDao;
import com.kybaby.domain.ItemResult;

public class ItemResultDaoImpl extends HibernateDaoSupport implements ItemResultDao {

	@Override
	public List  getAllItemResult() {
		Session session=getHibernateTemplate().getSessionFactory().openSession();
		String sqlQueryStr=" SELECT  a.id,a.itemResultNum,a.itemResultName,"
				+ "a.resultUnit,a.resultType,a.options,a.comments,a.isNeedRemind,"
				+ "a.maxValue,a.maxValueRemind,a.minValue,a.minValueRemind,a.result_remark_flag ,"
				+ "b.itemName FROM  item_result a LEFT JOIN  product_item b ON a.id=b.itemResultId";
		Query query=session.createSQLQuery(sqlQueryStr);
		List list=query.list();
		if(list.isEmpty()==true)
		return null;
		else return list;
	}

	@Override
	public ItemResult getItemResultByName(String name) {
		List list=getHibernateTemplate().find("FROM ItemResult WHERE itemResultName = ?  ", name);
		if(list.isEmpty()==true)
			return null;
			else return (ItemResult)list.get(0);
	}

	@Override
	public ItemResult getItemResultById(long id) {
		List list=getHibernateTemplate().find("FROM ItemResult WHERE id = ? ", id);
		if(list.isEmpty()==true)
			return null;
			else return (ItemResult)list.get(0);
	}

}
