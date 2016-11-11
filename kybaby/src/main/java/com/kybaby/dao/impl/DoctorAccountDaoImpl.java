package com.kybaby.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorAccountDao;
import com.kybaby.domain.DoctorAccount;

/**
 * @ClassName:DoctorAccountDaoImpl
 * @Description:医生账户变动数据管理接口实现
 * @author Hoolee
 * @date 2015年9月28日上午11:26:27
 */
public class DoctorAccountDaoImpl extends HibernateDaoSupport implements DoctorAccountDao {

	public void addNewDoctorAccount(DoctorAccount doctorAccount) {
		getHibernateTemplate().save(doctorAccount);
	}
	
}
