package com.kybaby.newbussiness.doctorclinic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.dao.DoctorClinicDao;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePracticeOrgInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;
import com.kybaby.util.ConstantManage;
/**
 * 医生门诊及多点执业持久层
 * @author lihao
 *
 */
public class DoctorClinicDaoImpl extends HibernateDaoSupport implements DoctorClinicDao {

	@Override
	public Long saveOrUpdateDoctorMorePractice(
			DoctorMorePractice doctorMorePractice) {
		Long id = null;
		if (doctorMorePractice == null) return id;
		if(doctorMorePractice.getId() == null){
			id = (Long) this.getHibernateTemplate().save(doctorMorePractice);
		}else{
			id = doctorMorePractice.getId();
			DoctorMorePractice old = this.getDoctorMorePracticeById(id);
			BeanUtils.copyProperties(doctorMorePractice, old, new String[]{"id"});
			this.getHibernateTemplate().update(old);
		}
		return id;
	}

	@Override
	public DoctorMorePractice getDoctorMorePracticeById(Long id) {
		return this.getHibernateTemplate().get(DoctorMorePractice.class, id);
	}

	@Override
	public List<DoctorMorePractice> getDoctorMorePracticeList(
			DoctorMorePractice doctorMorePractice,DoctorInfo doctorInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from DoctorMorePractice p where 1=1 ");
		hql.append(" and p.orgClinicStatus <> ?");
		params.add(ConstantManage.HASE_CANCEL_VACCINE);
		hql.append(" and TO_DAYS(p.clinicDate) - TO_DAYS(NOW()) >= 0 and TO_DAYS(p.clinicDate) - TO_DAYS(NOW()) <=13");
		if(doctorInfo != null){
			if(doctorInfo.getId() != null){
				hql.append(" and p.doctorInfo.id=?");
				params.add(doctorInfo.getId());
			}
		}
		if(doctorMorePractice != null){
			if(doctorMorePractice.getClinicOrgType() != null && !"".equals(doctorMorePractice.getClinicOrgType().trim())){
				hql.append(" and p.clinicOrgType=?");
				params.add(doctorMorePractice.getClinicOrgType().trim());
			}
			if(doctorMorePractice.getClinicDate() != null && !"".equals(doctorMorePractice.getClinicDate().trim())){//门诊时间
				hql.append(" and p.clinicDate=?");
				params.add(doctorMorePractice.getClinicDate().trim());
			}
			if(doctorMorePractice.getClinicAddress() != null && !"".equals(doctorMorePractice.getClinicAddress().trim())){//门诊地址
				hql.append(" and p.clinicAddress=?");
				params.add(doctorMorePractice.getClinicAddress().trim());
			}
			if(doctorMorePractice.getClinicOrg() != null && !"".equals(doctorMorePractice.getClinicOrg().trim())){//门诊地址
				hql.append(" and p.clinicOrg=?");
				params.add(doctorMorePractice.getClinicOrg().trim());
			}
			if(doctorMorePractice.getDoctorMorePracticeOrgInfo() != null){//多点机构信息
				if(doctorMorePractice.getDoctorMorePracticeOrgInfo().getHospitalBasicInfo() != null){
					if(doctorMorePractice.getDoctorMorePracticeOrgInfo().getHospitalBasicInfo().getId() != null){
						hql.append(" and p.doctorMorePracticeOrgInfo.hospitalBasicInfo.id=?");
						params.add(doctorMorePractice.getDoctorMorePracticeOrgInfo().getHospitalBasicInfo().getId());
					}
				}
			}
			
		}
		hql.append(" order by p.clinicDate");
		List<DoctorMorePractice> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<DoctorServiceType> getDoctorServiceTypeByDoctorId(
			DoctorInfo doctorInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("select p.doctorServiceType from DoctorServiceContent p where 1=1 and p.doctorServiceType.parentDoctorServiceType.serviceTypeName='多点执业'");
		if(doctorInfo != null){
			if(doctorInfo.getId() != null){
				hql.append(" and p.doctorInfo.id=?");
				params.add(doctorInfo.getId());
			}
		}
		List<DoctorServiceType> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		return null;
	}

	@Override
	/**
	 * 得到推荐医生信息列表
	 * @return
	 */
	public List<DoctorInfo> getDoctorInfoListRecommended() {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from DoctorInfo p where 1=1 and p.isRecommend='Y' and p.doctorStatus='Y' and p.authentication='已通过' ");
		List<DoctorInfo> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	
	public List<DoctorInfo> getClinicDoctorInfoList(DoctorInfo doctorInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("select distinct a")
		.append(" from DoctorInfo a,DoctorServiceContent b where 1=1")
		.append(" and a.id=b.doctorInfo.id and b.doctorServiceType.parentDoctorServiceType.serviceTypeName='多点执业'")
		.append(" and b.doctorServiceType.parentDoctorServiceType.isAvailable='Y'")
		.append(" and b.doctorServiceType.isAvailable='Y'")
		.append(" and a.doctorStatus='Y' and a.authentication='已通过' ");
		if(doctorInfo != null){
			if(doctorInfo.getId() != null){
				hql.append(" and a.id=?");
				params.add(doctorInfo.getId());
			}
			if(doctorInfo.getDoctorEmployer() != null && !"".equals(doctorInfo.getDoctorEmployer().trim())){
				hql.append(" and( a.doctorEmployer like ?");
				params.add("%"+doctorInfo.getDoctorEmployer().trim()+"%");
			}
			if(doctorInfo.getDoctorName() != null && !"".equals(doctorInfo.getDoctorName().trim())){
				hql.append(" or a.doctorName like ?)");
				params.add("%"+doctorInfo.getDoctorName().trim()+"%");
			}
		}
		List<DoctorInfo> list=getHibernateTemplate().find(hql.toString(),params.toArray());
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	@Override
	public HospitalBasicInfo getHospitalBasicInfoById(Long id) {
		return this.getHibernateTemplate().get(HospitalBasicInfo.class, id);
	}

	@Override
	public List<UserType> getUserTypeList(UserType userType) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from UserType p where 1=1 ");
		if(userType != null){
			if(StringUtils.isNotEmpty(userType.getTypeName())){
				hql.append(" and p.typeName = ?");
				params.add(userType.getTypeName());
			}
		}
		List<UserType> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	
}
