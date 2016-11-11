package com.kybaby.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.MajorDao;
import com.kybaby.domain.DoctorGoodField;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Major;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceContent;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;

public class MajorDaoImpl extends HibernateDaoSupport implements MajorDao {

	@Override
	public List<Major> getAllMajor() {
		List list=getHibernateTemplate().find("from Major");
		if(list.isEmpty()==true)
			return null;
		else return list;
	}

	@Override
	public Major getMajorByName(String name) {
		List list=getHibernateTemplate().find("from Major where major=?",name);
		if(list.isEmpty()==true)
			return null;
		else return (Major)list.get(0);
	}

	@Override
	public Major getMajorById(long id) {
		List list=getHibernateTemplate().find("from Major where id=?",id);
		if(list.isEmpty()==true)
			return null;
		else return (Major)list.get(0);
	}

	@Override
	public List<DoctorGoodField> getAllGoodField(DoctorGoodField doctorGoodField) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from DoctorGoodField where 1=1 ");
		if(doctorGoodField != null){
			if(doctorGoodField.getIsStart() != null && !"".equals(doctorGoodField.getIsStart().trim())){
				hql.append(" and isStart=?");
				params.add(doctorGoodField.getIsStart().trim());
			}
		}
		List<DoctorGoodField> list=getHibernateTemplate().find(hql.toString(),params.toArray());
		if(list.isEmpty()==true)
			return null;
		else return list;
	}

	@Override
	public Long saveOrupdateGoodField(DoctorGoodField doctorGoodField) {
		Long id = null;
		if(doctorGoodField == null){
			return id;
		}
		if(doctorGoodField.getId() == null){
			id = (Long) this.getHibernateTemplate().save(doctorGoodField);
		}else{
			id = doctorGoodField.getId();
			this.getHibernateTemplate().update(doctorGoodField);
		}
		return id;
	}

	@Override
	public List<DoctorServiceType> getDoctorServiceTypeList(
			DoctorServiceType doctorServiceType) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from DoctorServiceType where 1=1 ");
		if(doctorServiceType != null){
		}
		List<DoctorServiceType> list=getHibernateTemplate().find(hql.toString(),params.toArray());
		if(list.isEmpty())
			return null;
		return list;
	}

	@Override
	public void saveDoctorServiceContent(DoctorInfo doctorInfo,
			String serviceTypeIds) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteDoctorServiceContent(Long doctorId, Long serviceTypeId) {
		//StringBuffer sql = new StringBuffer("delete from doctor_service_content where service_type_id=:serviceTypeId and doctor_id=:doctorId");
		StringBuffer sql = new StringBuffer("delete from doctor_service_content where doctor_id=:doctorId");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query queryupdate = session.createSQLQuery(sql.toString());
		//queryupdate.setLong("serviceTypeId", serviceTypeId);
		queryupdate.setLong("doctorId", doctorId);
		queryupdate.executeUpdate();
		session.close();
	}
	@Override
	public void saveDoctorServiceContent(
			DoctorServiceContent doctorServiceContent) {
		this.getHibernateTemplate().save(doctorServiceContent);
	}

}
