package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.UserInfoDao;

public class UserInfoDaoImpl extends HibernateDaoSupport implements UserInfoDao {

	@Override
	public long getNumOfUser(String startDate, String endDate) {
		String hql="SELECT COUNT(id) FROM UserInfo WHERE registerTime >'"+ startDate+"' AND registerTime<'"+endDate+"' ";
		List list=getHibernateTemplate().find(hql);
		return Long.valueOf(String.valueOf(list.get(0)));
	}

	@Override
	public long getUseAppTimes(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List getUserBabyInfo() {
		String hql="SELECT id,babyName,sex,birthday,userProvince,userCity,userArea,userStreet,detailAddress,parentName,phone FROM UserInfo ";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
			return null;
		else return list;
	}

	@Override
	public List getBasicInfoByUserId(long userId) {
		String hql="SELECT a.id,a.babyName,a.sex,a.birthday,b FROM UserInfo a,BabyBasicData b WHERE a.id=b.userId AND a.id='"+ userId +"'";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}

	@Override
	public List<com.kybaby.domain.UserInfo> UserInfo() {
		String hql=" FROM UserInfo";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}

	@Override
	public com.kybaby.domain.UserInfo getUserInfoById(long id) {
		String hql=" FROM UserInfo WHERE id="+id;
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
			return null;	
		else return (com.kybaby.domain.UserInfo)list.get(0);
	}

	@Override
	public List<com.kybaby.domain.UserInfo> searchUser(String phone,String parentName,String babyName,String startTime,String endTime) {
		String hql="FROM UserInfo WHERE phone LIKE '%"+phone+"%'AND parentName LIKE '%"+parentName+"%' AND babyName LIKE '%"+babyName+"%' AND registerTime >= '"+startTime+"' AND registerTime <= '"+endTime+"'";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}

	@Override
	public List<Long> getUserIdByStatus(String status) {
	    List<Long> list=getHibernateTemplate().find("SELECT id FROM UserInfo WHERE userStatus=? ",status);
		if (list.isEmpty()==true)
			return null;
		else return (List<Long>)list;
	}

	@Override
	public com.kybaby.domain.UserInfo getUserInfoByPhone(String phone) {
		List list=getHibernateTemplate().find("FROM UserInfo WHRER phone = ? ",phone);
		if (list.isEmpty()==true)
			return null;
		else return (com.kybaby.domain.UserInfo)list.get(0);
	}

}
