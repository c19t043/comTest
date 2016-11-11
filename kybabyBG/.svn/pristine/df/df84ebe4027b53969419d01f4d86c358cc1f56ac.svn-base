package com.kybaby.newbussiness.mommyring.bo.impl;

import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.mommyring.bo.MommySubscribeUserBo;
import com.kybaby.newbussiness.mommyring.dao.MommySubscribeUserDao;
import com.kybaby.newbussiness.mommyring.domain.MommySubscribeUser;

/**
 * @ClassName:MommySubscribeUserBoImpl
 * @Description:医生圈订阅医生事物管理接口实现
 * @author Hoolee
 * @date 2015年12月11日下午5:01:04
 */
public class MommySubscribeUserBoImpl implements MommySubscribeUserBo {

	MommySubscribeUserDao subscribeUserDao;

	public MommySubscribeUser getSomeUserMommySubscribeUser(long userId, long ringTypeId) {
		return subscribeUserDao.getSomeUserMommySubscribeUser(userId, ringTypeId);
	}

	public void addSomeUserMommySubscribeUser(long userId, long ringTypeId) {
		MommySubscribeUser subscribeUser=subscribeUserDao.getSomeUserMommySubscribeUser(userId, ringTypeId);
		if(subscribeUser==null){
			subscribeUser=new MommySubscribeUser();
			subscribeUser.setUserId(userId);
			subscribeUser.setTypeId(ringTypeId);
			String rightNow=DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
			subscribeUser.setSubscribeTime(rightNow);
			subscribeUserDao.addSomeUserMommySubscribeUser(subscribeUser);
		}
	}

	public boolean deleteSomeUserMommySubscribeUser(long userId, long ringTypeId) {
		boolean flag=false;
		MommySubscribeUser subscribeUser=subscribeUserDao.getSomeUserMommySubscribeUser(userId, ringTypeId);
		if(subscribeUser!=null){
			subscribeUserDao.deleteSomeUserMommySubscribeUser(subscribeUser);
			flag=true;
		}
		return flag;
	}

	public MommySubscribeUserDao getMommySubscribeUserDao() {
		return subscribeUserDao;
	}

	public void setMommySubscribeUserDao(MommySubscribeUserDao subscribeUserDao) {
		this.subscribeUserDao = subscribeUserDao;
	}
}
