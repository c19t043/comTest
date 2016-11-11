package com.kybaby.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.OrderInfoDao;
import com.kybaby.domain.OrderInfo;
import com.kybaby.util.EncryptUtil;

public class OrderInfoDaoImpl extends HibernateDaoSupport implements OrderInfoDao {

	@Override
	public long getCurrentMonthOrderInfoNum() {
		String hql="SELECT COUNT(id) FROM OrderInfo WHERE MONTH(submitTime)=MONTH(CURDATE()) AND YEAR(submitTime)=YEAR(CURDATE())";
		List list=getHibernateTemplate().find(hql);
		return Long.valueOf(String.valueOf(list.get(0)));
	}

	@Override
	public long getAllOrderInfoNum() {
		String hql="SELECT COUNT(id) FROM OrderInfo ";
		List list=getHibernateTemplate().find(hql);
		return Long.valueOf(String.valueOf(list.get(0)));
	}

	@Override
	public long getNumOfOrder(String startDate, String endDate) {
		String hql="SELECT COUNT(id) FROM OrderInfo WHERE submitTime >'"+ startDate+"' AND submitTime<'"+endDate+"' ";
		List list=getHibernateTemplate().find(hql);
		return Long.valueOf(String.valueOf(list.get(0)));
	}

	@Override
	public double getTradeMoney(String startDate, String endDate) {
		String hql="SELECT SUM(totalPrice) FROM OrderInfo WHERE submitTime >'"+ startDate+"' AND submitTime<'"+endDate+"' AND orderStatus NOT IN ('null','用户取消','已退款') ";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true||list.get(0)==null)
		{
			return 0;
		}
		return EncryptUtil.getSecondBits(Double.valueOf(String.valueOf(list.get(0))));
	}

	@Override
	public long getTotalServiceTimeOfDoctor(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List getAllOrderInfo() {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
	   String sqlQueryStr=
	  " SELECT A.*,g.*,CAST(GROUP_CONCAT(f.tagName SEPARATOR '::') AS CHAR)AS evaluateTags " + 
	  "  FROM " +
	      " (SELECT a.*,b.parentName,b.babyName,b.userProvince,b.userCity,b.userArea,"
	      + "b.userStreet,b.detailAddress,b.phone,(case when a.doctorId !='' then c.doctorName else '' end) doctorName,d.name,"
	      + "CAST(GROUP_CONCAT(e.itemName SEPARATOR '::') AS CHAR) AS itemNames"+
	        " FROM " +
                 " order_info a , user_info b, doctor_info c, product d, product_item e " +
             "WHERE b.id=a.userId AND (c.id=a.doctorId or a.doctorId is null) AND d.id=a.productId AND FIND_IN_SET(e.id,REPLACE(d.itemIds,'::',','))  GROUP BY a.orderNum) A " +
      " LEFT JOIN " +
      "  evaluate g " +
      " ON A.orderNum=g.orderNum " +
      " LEFT JOIN " +
      "  assessment_tag f " +
      " ON FIND_IN_SET(f.id,REPLACE(g.tagIds,'::',',')) GROUP BY A.orderNum ORDER BY A.submitTime desc ";
	   Query query = session.createSQLQuery(sqlQueryStr);
	   List list=query.list();
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}

	@Override
	public List getSomeOrderInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderInfo getOrderInfoById(long id) {
		List list=getHibernateTemplate().find("FROM OrderInfo WHERE id=?",id);
		if(list.isEmpty()==true)
		{return null;}
		else
		{
			return (OrderInfo)list.get(0);
		}
	}

	@Override
	public long getActiveUserNum(String startDate, String endDate) {
		String hql="SELECT COUNT(DISTINCT userId) FROM OrderInfo WHERE submitTime >'"+ startDate+"' AND submitTime<'"+endDate+"' ";
		List list=getHibernateTemplate().find(hql);
		return Long.valueOf(String.valueOf(list.get(0)));
	}

	@Override
	public long getActiveDoctorNum(String startDate, String endDate) {
		String hql="SELECT COUNT(DISTINCT doctorId) FROM OrderInfo WHERE submitTime >'"+ startDate+"' AND submitTime<'"+endDate+"' ";
		List list=getHibernateTemplate().find(hql);
		return Long.valueOf(String.valueOf(list.get(0)));
	}

	@Override
	public List getSearchOrderBy(String babyName, String doctorName,String productName, String orderStatus, String startTime,String endTime) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		   String sqlQueryStr=
		  " SELECT A.*,g.*,CAST(GROUP_CONCAT(f.tagName SEPARATOR '::') AS CHAR)AS evaluateTags " + 
		  "  FROM " +
		      " (SELECT a.*,b.parentName,b.babyName,b.userProvince,b.userCity,b.userArea,b.userStreet,b.detailAddress,b.phone,c.doctorName,d.name,GROUP_CONCAT(e.itemName SEPARATOR '::')"+
		        " FROM " +
	                 " order_info a , user_info b, doctor_info c, product d, product_item e " +
	             "WHERE b.id=a.userId AND c.id=a.doctorId AND d.id=a.productId AND FIND_IN_SET(e.id,REPLACE(d.itemIds,'::',',')) AND b.babyName LIKE '%"+babyName+"%' AND c.doctorName LIKE '%"+doctorName+"%' AND d.name LIKE '%"+productName+"%' AND a.orderStatus LIKE '%"+orderStatus+"%' AND a.updateTime>='"+startTime+"' AND a.updateTime<'"+endTime+"' GROUP BY a.orderNum) A " +
	      " LEFT JOIN " +
	      "  evaluate g " +
	      " ON A.orderNum=g.orderNum " +
	      " LEFT JOIN " +
	      "  assessment_tag f " +
	      " ON FIND_IN_SET(f.id,REPLACE(g.tagIds,'::',',')) GROUP BY A.orderNum ORDER BY A.submitTime";
		   Query query = session.createSQLQuery(sqlQueryStr);
		   List list=query.list();
			if(list.isEmpty()==true)
				return null;	
			else return list;
	}

	@Override
	public List<String> getProductName() {
		List<String> list=getHibernateTemplate().find("SELECT name FROM Product ");
		if(list.isEmpty()==true)
		{
			return null;
		}
		else 
			{
			return list;
			}
			
	}

	@Override
	public List queryOrderInfoByStatus(String status) {
		StringBuffer hql = new StringBuffer("from OrderInfo o where 1=1 and o.doctorId is not null");
		if(status != null && !"".equals(status)){
			hql.append(" and  o.orderStatus in (").append(status).append(")");
		}
		return getHibernateTemplate().find(hql.toString());
	}



}
