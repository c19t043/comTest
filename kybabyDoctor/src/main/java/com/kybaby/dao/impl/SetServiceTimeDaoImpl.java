package com.kybaby.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.SetServiceTimeDao;
import com.kybaby.domain.DoctorAddress;
import com.kybaby.domain.DoctorProduct;
import com.kybaby.domain.TimeInit;

/**
 * @author sujiantang
 *
 */
public class SetServiceTimeDaoImpl extends HibernateDaoSupport implements SetServiceTimeDao{

	@Override
	public List<TimeInit> getServiceTimeList() {
		List list=getHibernateTemplate().find("from TimeInit where status='Y'");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public List<DoctorAddress> getDoctorAddressByDoctorId(Long id) {
		List list=getHibernateTemplate().find("from DoctorAddress where doctorId=? and addressStatus!='0'",id);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public List<DoctorProduct> getInUseTimeByAddressIdAndDoctorId(
			Long addressId, Long doctorId) {
		List list=getHibernateTemplate().find("from DoctorProduct where addressId=? and doctorId=?",addressId,doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public List<DoctorProduct> getDoctorProduct(Long id, Long addressId) {
		List list=getHibernateTemplate().find("from DoctorProduct where addressId=? and doctorId=?",addressId,id);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}
	
	@Override
	public List<DoctorProduct> getDoctorServiceTime(Long doctorId){
		List list=getHibernateTemplate().find("from DoctorProduct where doctorId=?",doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public TimeInit getSomeTimeInitById(Long timeId) {
		List list=getHibernateTemplate().find("from TimeInit where id=?",timeId);
		if(list.isEmpty()==true){
			return null;
		}
		return (TimeInit)list.get(0);
	}

	@Override
	public void saveDoctorProduct(DoctorProduct doctorProduct) {
		getHibernateTemplate().save(doctorProduct);
	}

	@Override
	public void deleteSomeTime(Long addressId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		// 开始事务
		Transaction tx = session.beginTransaction();
		// 定义批量删除的HQL语句
		String hqlUpdate = "delete from DoctorProduct where addressId="+addressId;
		// 执行批量删除
		int updatedEntities = session.createQuery(hqlUpdate).executeUpdate();
		System.out.println(updatedEntities);
		// 提交事务
		tx.commit();
		// 关闭Session
		session.close();
	}

}
