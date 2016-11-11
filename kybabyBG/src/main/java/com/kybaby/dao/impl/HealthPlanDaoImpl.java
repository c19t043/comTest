package com.kybaby.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.HealthPlanDao;
import com.kybaby.domain.HealthPlan;

public class HealthPlanDaoImpl extends HibernateDaoSupport implements HealthPlanDao {

	@Override
	public List getAllHealthPlan() {
		Session session=getHibernateTemplate().getSessionFactory().openSession();
		String sqlQueryStr=" SELECT a.*,b.itemName,GROUP_CONCAT(DISTINCT(CAST(c.healthPathName AS CHAR)) SEPARATOR '::') FROM  health_plan a LEFT JOIN  product_item b ON a.id=b.healthPlanId  LEFT JOIN  health_path c ON FIND_IN_SET(c.id,REPLACE(a.healthPathIds,'::',',')) GROUP BY a.id";
		Query query=session.createSQLQuery(sqlQueryStr);
		List list=query.list();
		if(list.isEmpty()==true)
		return null;
		else return list;
	}

	@Override
	public HealthPlan getHealthPlanByName(String name) {
		List list =getHibernateTemplate().find("FROM HealthPlan WHERE healthPlan = ? " , name);
		if(list.isEmpty()==true)
			return null;
		else return (HealthPlan)list.get(0);
	}

	@Override
	public HealthPlan getHealthPlanById(long id) {
		List list =getHibernateTemplate().find("FROM HealthPlan WHERE id = ? " , id);
		if(list.isEmpty()==true)
			return null;
		else return (HealthPlan)list.get(0);
	}

}
