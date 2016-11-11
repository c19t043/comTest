package com.kybaby.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.OrderResultsDao;

public class OrderResultsDaoImpl extends HibernateDaoSupport implements OrderResultsDao {

	@SuppressWarnings("rawtypes")
	@Override
	public List getOrderAndPathResult(long userId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
	    String sqlQueryStr="SELECT a.orderNum,a.updateTime,b.name,f.itemId,c.itemName,d.healthPlan,f.itemResultName,f.resultValue,f.resultUnit,GROUP_CONCAT(DISTINCT(e.healthPathName) SEPARATOR '::') AS pathNames ,CAST(GROUP_CONCAT(LENGTH(g.executeResult)-LENGTH(REPLACE(g.executeResult,'Y','')) SEPARATOR '::')  AS CHAR) AS executeNums   FROM order_info a, product b, product_item c, health_plan  d ,health_path e , order_results f, health_plan_remind g WHERE a.orderNum IN (SELECT orderNum FROM order_info  WHERE userId='"+userId+"') AND a.orderNum=f.orderNum AND a.orderNum=g.orderNum  AND a.orderNum=g.orderNum AND c.id=f.itemId AND d.id=f.planId AND e.id=g.healthPathId  AND f.planId=g.healthPlanId  AND f.itemId=c.id AND b.id=a.productId GROUP BY g.healthPlanId,a.orderNum ORDER BY a.updateTime ";
	   Query query = session.createSQLQuery(sqlQueryStr);
	   List list=query.list();
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}

	@Override
	public List getUserOrderNumListString(long userId) {
		List<String> list=getHibernateTemplate().find("SELECT orderNum from OrderInfo WHERE userId = ? ",userId);
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}

}
