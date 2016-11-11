package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.AdminDao;
import com.kybaby.domain.Admin;

public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {
	public Admin findAdminByAdminName(String adminName) {
		List list=getHibernateTemplate().find("from Admin where name=?", adminName);
		if(list.isEmpty()==true){
			return null;
		}
		return (Admin)list.get(0);
	}

}
