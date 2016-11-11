package com.kybaby.newbussiness.familydoctor.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.familydoctor.dao.FamilyDoctorDao;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.util.ConstantManage;

public class FamilyDoctorDaoImpl extends HibernateDaoSupport implements FamilyDoctorDao{

	@Override
	public FdServicePackage getFdServicePackageById(Long id) {
		return this.getHibernateTemplate().get(FdServicePackage.class, id);
	}

	@Override
	public List<HospitalBasicInfo> getHospitalBasicInfoListByPackage(
			FdServicePackage fdServicePackage) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer("select distinct p.hospitalBasicInfo from FdServicePackage p where 1=1");
		if(fdServicePackage != null){
		}
		List<HospitalBasicInfo> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public Boolean checkOrderIsExist(String date, String time,
			String clinicAddress, DoctorInfo doctorInfo, UserInfo userInfo) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from OrderInfoClinic p where 1=1");
		hql.append(" and p.payMethod = ?");
		params.add(ConstantManage.FD_PAY);
		hql.append(" and p.orderStatus = ?");
		params.add(ConstantManage.HASE_BOOKED_CLINIC_ORDER);
		hql.append(" and p.appointmentDate=?");
		params.add(date);
		hql.append(" and p.appointmentBeganTime=?");
		params.add(time);
		hql.append(" and p.clinicAddress=?");
		params.add(clinicAddress);
		hql.append(" and p.doctorInfo.id=?");
		params.add(doctorInfo.getId());
		hql.append(" and p.userInfo.id=?");
		params.add(userInfo.getId());
		List<OrderInfoClinic> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return true;
		}
		return false;
	}
	
}
