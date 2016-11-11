package com.kybaby.newbussiness.doctorring.bo.impl;

import com.kybaby.domain.SubscribeUser;
import com.kybaby.newbussiness.doctorring.bo.SubscribeUserBo;
import com.kybaby.newbussiness.doctorring.dao.SubscribeUserDao;
import com.kybaby.newbussiness.doctorring.util.DateManage;

/**
 * @ClassName:SubscribeUserBoImpl
 * @Description:医生圈订阅医生事物管理接口实现
 * @author Hoolee
 * @date 2015年12月11日下午5:01:04
 */
public class SubscribeUserBoImpl implements SubscribeUserBo {

	SubscribeUserDao subscribeUserDao;

	@Override
	public SubscribeUser getSomeUserSubscribeUser(long doctorId, long ringTypeId) {
		return subscribeUserDao.getSomeUserSubscribeUser(doctorId, ringTypeId);
	}

	@Override
	public void addSomeUserSubscribeUser(long doctorId, long ringTypeId) {
		SubscribeUser subscribeUser=subscribeUserDao.getSomeUserSubscribeUser(doctorId, ringTypeId);
		if(subscribeUser==null){
			subscribeUser=new SubscribeUser();
			subscribeUser.setDoctorId(doctorId);
			subscribeUser.setTypeId(ringTypeId);
			String rightNow=DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
			subscribeUser.setSubscribeTime(rightNow);
			subscribeUserDao.addSomeUserSubscribeUser(subscribeUser);
		}
	}

	@Override
	public boolean deleteSomeUserSubscribeUser(long doctorId, long ringTypeId) {
		boolean flag=false;
		SubscribeUser subscribeUser=subscribeUserDao.getSomeUserSubscribeUser(doctorId, ringTypeId);
		if(subscribeUser!=null){
			subscribeUserDao.deleteSomeUserSubscribeUser(subscribeUser);
			flag=true;
		}
		return flag;
	}

	public SubscribeUserDao getSubscribeUserDao() {
		return subscribeUserDao;
	}

	public void setSubscribeUserDao(SubscribeUserDao subscribeUserDao) {
		this.subscribeUserDao = subscribeUserDao;
	}
}
