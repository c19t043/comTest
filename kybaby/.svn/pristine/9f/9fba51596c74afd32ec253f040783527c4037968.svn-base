package com.kybaby.newbussiness.mommyring.bo.impl;

import com.kybaby.newbussiness.mommyring.bo.MommySubscribeUserBo;
import com.kybaby.newbussiness.mommyring.dao.MommySubscribeUserDao;
import com.kybaby.newbussiness.mommyring.domain.MommySubscribeUser;
import com.kybaby.util.DateManage;

/**
 * @ClassName:SubscribeUserBoImpl
 * @Description:医生圈订阅医生事物管理接口实现
 * @author Hoolee
 * @date 2015年12月11日下午5:01:04
 */
public class MommySubscribeUserBoImpl implements MommySubscribeUserBo {

	MommySubscribeUserDao mommySubscribeUserDao;

	public MommySubscribeUser getSomeUserSubscribeUser(long doctorId, long ringTypeId) {
		return mommySubscribeUserDao.getSomeUserSubscribeUser(doctorId, ringTypeId);
	}

	public void addSomeUserSubscribeUser(long userId, long ringTypeId) {
		MommySubscribeUser subscribeUser=mommySubscribeUserDao.getSomeUserSubscribeUser(userId, ringTypeId);
		if(subscribeUser==null){
			subscribeUser=new MommySubscribeUser();
			subscribeUser.setUserId(userId);
			subscribeUser.setTypeId(ringTypeId);
			String rightNow=DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
			subscribeUser.setSubscribeTime(rightNow);
			mommySubscribeUserDao.addSomeUserSubscribeUser(subscribeUser);
		}
	}

	public boolean deleteSomeUserSubscribeUser(long userId, long ringTypeId) {
		boolean flag=false;
		MommySubscribeUser subscribeUser=mommySubscribeUserDao.getSomeUserSubscribeUser(userId, ringTypeId);
		if(subscribeUser!=null){
			mommySubscribeUserDao.deleteSomeUserSubscribeUser(subscribeUser);
			flag=true;
		}
		return flag;
	}

	public MommySubscribeUserDao getMommySubscribeUserDao() {
		return mommySubscribeUserDao;
	}

	public void setMommySubscribeUserDao(MommySubscribeUserDao mommySubscribeUserDao) {
		this.mommySubscribeUserDao = mommySubscribeUserDao;
	}
	
}
