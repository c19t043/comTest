package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.GrowthRecordDao;
import com.kybaby.domain.GrowthRecord;

public class GrowthRecordDaoImpl extends HibernateDaoSupport implements GrowthRecordDao {

	@Override
	public List<GrowthRecord> getGrowthRecordByUserId(long userId) {
		String hql="FROM GrowthRecord WHERE userId='"+userId+"' ORDER BY recordDate DESC";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}

}
