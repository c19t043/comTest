package com.kybaby.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.OrderResultsDao;

/**
 * @ClassName:OrderResultsDaoImpl
 * @Description:订单结果数据管理接口实现
 * @author Hoolee
 * @date 2015年10月13日下午4:09:32
 */
public class OrderResultsDaoImpl extends HibernateDaoSupport implements OrderResultsDao {

	public List<String> getWriteDateListByUserId(long userId) {
		return null;
	}

	public String getWridateByOrderNumUserIdAndProdcutId(String orderNum,long userId, long productId) {
		List<String> list=getHibernateTemplate().find("select writeDate from OrderResults where  orderNum=? and userId=? and productId=? group by orderNum", new Object[]{orderNum,userId,productId});
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public List<Object[]> getItemIdAndResultValueAndUnit(String orderNum,long userId, long productId) {
		List<Object[]> list=getHibernateTemplate().find("select itemId , resultValue , resultUnit from OrderResults where  orderNum=? and userId=? and productId=? ", new Object[]{orderNum,userId,productId});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<String> getHealthPlanId(String orderNum, long userId,long productId) {
		List<String> list=getHibernateTemplate().find("select planId from OrderResults where orderNum=? and userId=? and productId=? ", new Object[]{orderNum,userId,productId});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public String getLastOrderNumByUserId(long userId) {
		List<String> list=getHibernateTemplate().find("select orderNum from OrderResults where userId=? order by id desc", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public List<String> getSomeOrderPlanIdList(long userId, String orderNum) {
		List<String> list=getHibernateTemplate().find("select planId from OrderResults where userId=? and orderNum=? group by itemId order by id ", new Object[]{userId,orderNum});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public Long getDoctorIdByOrderNum(String orderNum, long userId) {
		List<Long> list=getHibernateTemplate().find("select doctorId from OrderResults where orderNum=? and userId=? group by orderNum ", new Object[]{orderNum,userId});
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public List<Object[]> getSomeOrderResult(String orderNum, long userId) {
		String sqlUrl="SELECT b.itemName , a.itemResultName,CONCAT(a.resultValue,a.resultUnit) FROM kybaby.order_results a , kybaby.product_item b WHERE a.orderNum="+orderNum+" AND a.userId="+userId+" AND a.itemResultName<>' ' AND a.itemId=b.id  GROUP BY a.itemId ORDER BY a.id DESC;";
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(sqlUrl);
		List<Object[]> list=query.list();
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}
	
}
