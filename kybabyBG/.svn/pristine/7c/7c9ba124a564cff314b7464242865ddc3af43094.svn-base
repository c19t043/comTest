package com.kybaby.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.UserConsultDoctorDao;

public class UserConsultDoctorDaoImpl extends HibernateDaoSupport implements
		UserConsultDoctorDao {

	@Override
	public long getCurrentMonthConsultationNum() {
		String hql="SELECT COUNT(DISTINCT logId) FROM UserConsultDoctor WHERE MONTH(submitTime)=MONTH(CURDATE()) AND YEAR(submitTime)=YEAR(CURDATE()) ";
		List list=getHibernateTemplate().find(hql);
		return Long.valueOf(String.valueOf(list.get(0)));
	}

	@Override
	public long getAllConsultationNum() {
		String hql="SELECT COUNT(DISTINCT logId) FROM UserConsultDoctor ";
		List list=getHibernateTemplate().find(hql);
		return Long.valueOf(String.valueOf(list.get(0)));
	}

	@Override
	public List getAllConsult() {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
	    String sqlQueryStr=
        " SELECT * FROM ( "+
        " SELECT A.* FROM "+
        " ("+
        " SELECT a.*,d.babyName,e.doctorName,GROUP_CONCAT(b.symptomName SEPARATOR '::') FROM "+
        " user_consult_doctor a, symptom_tag b , user_info d, doctor_info e "+
        " WHERE FIND_IN_SET(b.id, REPLACE(a.symptomTagIds,'::',',')) AND d.id=a.userId AND e.id=a.doctorId GROUP BY a.logId"+
        " )  A"+
        " )  C"+
        " LEFT JOIN"+
        " ( "+
        " SELECT B.* FROM "+
        " ( "+
        " SELECT c.logId,c.submitTime FROM (SELECT b.logId,b.submitTime FROM   user_consult_doctor b ORDER BY  b.submitTime DESC) AS c GROUP  BY c.logId "+ 
        " ) B"+
        " ) D ON C.logId=D.logId ";
	   Query query = session.createSQLQuery(sqlQueryStr);
	   List list=query.list();
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}

	@Override
	public List getConsultByLogId(long logId) {
		String hql="SELECT a,b.parentName,c.doctorName FROM UserConsultDoctor a ,UserInfo  b, DoctorInfo c WHERE  a.userId=b.id AND a.doctorId=c.id AND a.logId='"+logId+"' ORDER BY a.submitTime";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}

	@Override
	public List getSearchList(String startTime, String endTime, String tagName,String babyName, String doctorName) {
		
		Session session = getHibernateTemplate().getSessionFactory().openSession();
	    String sqlQueryStr=
	    		" SELECT * FROM (  "+ 
	    				   " SELECT  * "+
	    			   " FROM (SELECT  " +
	    				      "  A.* " +
	    				    "  FROM (SELECT " +
	    				           "    a.*, " +
	    				           "   d.babyName, " +
	    				           "  e.doctorName, " +
	    				           "  GROUP_CONCAT(DISTINCT(b.symptomName) SEPARATOR '::') AS symNames " +
	    				           " FROM  user_consult_doctor a, " +
	    				           "    symptom_tag b, " +
	    				           "    user_info d, " +
	    				           "    doctor_info e " +
	    				           " WHERE FIND_IN_SET(b.id, REPLACE(a.symptomTagIds,'::',',')) " +
	    				           "     AND d.id = a.userId " + 
	    				           "     AND e.id = a.doctorId " +
	    				           " GROUP BY a.logId) A) C " +
	    				  " LEFT JOIN (SELECT " +
	    				           "  B.* " +
	    				           "  FROM (SELECT " +
	    				                   "  c.sameLogId, " +
	    				                   "  c.updateTime " +
	    				                   " FROM (SELECT " +
	    				                          "  b.logId AS sameLogId, " +
	    				                          "  b.submitTime AS updateTime " +
	    				                          " FROM  user_consult_doctor b " +
	    				                        " ORDER BY b.submitTime DESC) AS c " +
	    				                  " GROUP BY c.sameLogId) B) D " +
	    				    " ON C.logId = D.sameLogId ) E  WHERE  " +
	    				    " E.babyName LIKE  '%"+babyName+"%' AND E.doctorName LIKE '%"+doctorName+"%' AND " +
	    				    " E.submitTime>='"+startTime+"' AND E.updateTime <='"+endTime+"' " +
	    				    " AND symNames LIKE '%"+tagName+"%'  ";
	   Query query = session.createSQLQuery(sqlQueryStr);
	   List list=query.list();
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}

}
