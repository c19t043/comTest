package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorInfoDao;
import com.kybaby.domain.DoctorInfo;

/**
 * @author sujiantang
 *
 */
public class DoctorInfoDaoImpl extends HibernateDaoSupport implements DoctorInfoDao{

	@Override
	public DoctorInfo getDoctorInfoByPhone(String phone) {
		List list=getHibernateTemplate().find("from DoctorInfo where doctorPhone=? and doctorStatus='Y'",phone);
		if(list.isEmpty()==true){
			return null;
		}
		return (DoctorInfo)list.get(0);
	}
	//注册专用
	@Override
	public DoctorInfo getDoctorInfoByPhoneNum(String phone) {
		List list=getHibernateTemplate().find("from DoctorInfo where doctorPhone=?",phone);
		if(list.isEmpty()==true){
			return null;
		}
		return (DoctorInfo)list.get(0);
	}

	@Override
	public void save(DoctorInfo doctorInfo) {
		getHibernateTemplate().save(doctorInfo);
	}

	@Override
	public void update(DoctorInfo doctorInfo) {
		getHibernateTemplate().update(doctorInfo);
	}

	@Override
	public DoctorInfo getDoctorInfoByOpenId(String openId) {
		List list=getHibernateTemplate().find("from DoctorInfo where openId=?",openId);
		if(list.isEmpty()==true){
			return null;
		}
		return (DoctorInfo)list.get(0);
	}
	
	@Override
	public DoctorInfo getDoctorInfoBoById(long id) {
		List<DoctorInfo> list=getHibernateTemplate().find("from DoctorInfo where id=?", id);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

}
