package com.kybaby.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorProductDao;

public class DoctorProductDaoImpl extends HibernateDaoSupport implements DoctorProductDao {

	@Override
	public long getNumOfEveryServiceHour(String someDate, String someTime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getResultByDateAndTime(long doctorId, String date, long time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getEveryHourAndNumOfDoctor(String someDay) {
		String hql="SELECT a.serviceTimes, COUNT(a.serviceTimes) FROM DoctorProduct a WHERE a.serviceDate='"+someDay+"'GROUP BY a.serviceTimes ORDER BY a.serviceTimes"; 
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
		{
			return null;
		}
			
		else return list;
	}

	
	@Override
	public List getDoctorServiceTime(long doctorId, String startDate, String endDate) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
	   
		String sqlQueryStr=
	    " SELECT A.doctorName,A.doctorPhone,A.serviceDate,CAST(A.timeList AS CHAR) "+
	    " FROM ("+
	    				" ( " +
	    				 " SELECT b.doctorName,b.doctorPhone, a.serviceDate,CAST(GROUP_CONCAT(DISTINCT(CAST(a.serviceTimes AS CHAR)) SEPARATOR '::') AS CHAR) AS timeList "+
	    				     " FROM " +
	    				 " doctor_product a , doctor_info b " + 
	    				 " WHERE a.doctorId='"+doctorId+"' AND a.serviceDate>='"+startDate+"' AND a.serviceDate<='"+endDate+"' AND b.id=a.doctorId"+
	    				 " GROUP BY a.serviceDate "+
	    				" ) " +
	    				" UNION " +
	    				" ( " +
	    				" SELECT d.doctorName,d.doctorPhone, c.serviceDate,CAST(GROUP_CONCAT(DISTINCT(CAST(c.serviceTimes AS CHAR)) SEPARATOR '::') AS CHAR) "+ 
	    				" FROM " +
	    				" doctor_product_hist c , doctor_info d " +
	    				" WHERE c.doctorId='"+doctorId+"' AND c.serviceDate>='"+startDate+"' AND c.serviceDate<='"+endDate+"' AND d.id=c.doctorId "+
	    				" GROUP BY c.serviceDate " +
	    				" ) "+
	    				" ORDER BY serviceDate DESC "+         
	    				") "+
	    "AS A";
	   Query query = session.createSQLQuery(sqlQueryStr);
	   List list=query.list();
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}

}
