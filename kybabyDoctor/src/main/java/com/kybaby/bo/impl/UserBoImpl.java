package com.kybaby.bo.impl;

import java.sql.Timestamp;

import com.kybaby.bo.UserBo;
import com.kybaby.dao.UserDao;
import com.kybaby.domain.User;

public class UserBoImpl implements UserBo {
	UserDao userDao;

	@Override
	public User findUserByUsrName(String userName) {
		return userDao.findUserByUserName(userName);
	}

	@Override
	public Boolean userRegist(String userName, String userPassword) {
		User newUser=new User();
		newUser=userDao.findUserByUserName(userName);
		if(newUser==null){
			return false;
		}
		newUser.setUserName(userName);
		newUser.setUserPassword(userPassword);
		Timestamp registDatetime=new Timestamp(System.currentTimeMillis());
		newUser.setRegistDatetime(registDatetime);
		userDao.save(newUser);
		return true;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


}
