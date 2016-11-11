package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.UserDao;
import com.kybaby.domain.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public User findUserByUserName(String userName) {
		List list=getHibernateTemplate().find("from User where userName=?",userName);
		if(list.isEmpty()==true){
			return null;
		}
		return (User)list.get(0);
	}

	public void save(User user) {
		getHibernateTemplate().save(user);
	}
	
	

}
