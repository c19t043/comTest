package com.kybaby.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorPointsDao;
import com.kybaby.domain.DoctorPoints;

/**
 * @ClassName:DoctorPointsDaoImpl
 * @Description:医生积分数据管理实现
 * @author Hoolee
 * @date 2015年9月28日上午10:44:48
 */
public class DoctorPointsDaoImpl extends HibernateDaoSupport implements DoctorPointsDao {

	public void addNewUserPoints(DoctorPoints doctorPoints) {
		getHibernateTemplate().save(doctorPoints);
	}

}
