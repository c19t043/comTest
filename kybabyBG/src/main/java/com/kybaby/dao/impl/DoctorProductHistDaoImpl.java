package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorProductHistDao;

public class DoctorProductHistDaoImpl extends HibernateDaoSupport implements DoctorProductHistDao {

	@Override
	public long getNumOfEveryServiceHour(String someDate, String someTime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List getEveryHourAndNumOfDoctor(String someDay) {
		String hql="SELECT a.serviceTimes, COUNT(a.serviceTimes) FROM DoctorProductHist a WHERE a.serviceDate='"+someDay+"'GROUP BY a.serviceTimes ORDER BY a.serviceTimes"; 
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
		{
			return null;
		}
			
		else return list;
	}

}
