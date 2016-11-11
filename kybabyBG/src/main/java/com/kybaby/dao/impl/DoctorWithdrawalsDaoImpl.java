package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorWithdrawalsDao;
import com.kybaby.domain.Balance;
import com.kybaby.domain.DoctorWithdrawals;

public class DoctorWithdrawalsDaoImpl extends HibernateDaoSupport implements DoctorWithdrawalsDao {

	@Override
	public double getCurrentMonthWithdrawalsNum(String status) {
		String hql="SELECT SUM(amount) FROM DoctorWithdrawals   WHERE MONTH(updateTime)=MONTH(CURDATE()) AND YEAR(updateTime)=YEAR(CURDATE()) AND applyStatus='"+status+"'";
		@SuppressWarnings("rawtypes")
		List list=getHibernateTemplate().find(hql); 
		if(list.isEmpty()==true||list.get(0)==null)
		{
			return 0;
		}
		return Double.valueOf(String.valueOf(list.get(0)));
	}

	
	@Override
	public double getAllWithdrawalsNum(String status) {
		String hql="SELECT SUM(amount) FROM DoctorWithdrawals  WHERE applyStatus='"+status+"'";

		@SuppressWarnings("rawtypes")
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true||list.get(0)==null)
		{
			return 0;
		}
		return Double.valueOf(String.valueOf(list.get(0)));
	}

	@Override
	public List getAllDoctorWithdrawalsInfo() {
		String hql="SELECT a.id,a.updateTime, b.doctorName,b.doctorPhone,a.amount,a.applyStatus,a.applyTime FROM DoctorWithdrawals a,DoctorInfo b WHERE a.doctorId=b.id ORDER BY a.updateTime DESC";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
		return null;
		else return list;
	}

	@Override
	public DoctorWithdrawals getDoctorWithdrawalsById(long id) {
		List list =getHibernateTemplate().find("FROM DoctorWithdrawals WHERE id = ?",id);
		if(list.isEmpty()==true)
		return null;
		else return (DoctorWithdrawals)list.get(0);
	}

	@Override
	public List getOneDoctorWithdrawalsInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List getCheckedBalance(String status) {
		String hql="SELECT a.doctorName, a.doctorPhone,  a.bankAccountName, a.bankCard,  b.amount, b.updateTime,b.id FROM DoctorInfo a, DoctorWithdrawals b WHERE a.id = b.doctorId    AND b.applyStatus = '"+status+"' ORDER BY b.updateTime";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
		{
			return null ;
		}
		else return list;
	}


	@Override
	public Balance getBalanceById(long id) {
		List list=getHibernateTemplate().find("FROM Balance WHERE id=?",id);
		if(list.isEmpty()==true)
		{
			return null;
		}
		else
			return (Balance)list.get(0);
	}


	@Override
	public List<Balance> getAllBalance() {
		List list=getHibernateTemplate().find("FROM Balance ORDER BY time DESC");
		if(list.isEmpty()==true)
		{
			return null;
		}
		else return list;
	}
	

}
