package com.kybaby.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorIdentifyDao;
import com.kybaby.domain.DoctorGoodField;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Major;
import com.kybaby.domain.Position;
import com.kybaby.domain.SymptomTag;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceContent;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;

/**
 * @author sujiantang
 *
 */
public class DoctorIdentifyDaoImpl extends HibernateDaoSupport implements DoctorIdentifyDao{

	@Override
	public void update(DoctorInfo doctorInfo) {
		getHibernateTemplate().update(doctorInfo);
	}

	@Override
	public DoctorInfo getDoctorInfoByDoctorId() {
		return null;
	}

	@Override
	public List<Position> getAllPosition() {
		List list=getHibernateTemplate().find("from Position where positionStatus='Y' order by rank desc");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public List getProductNameByRank(Long rank) {
		List list=getHibernateTemplate().find("select a.id, a.name from Product a, Position b where a.productStatus='Y' and b.positionStatus='Y' and b.id=a.positionId and b.rank<=?",rank);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public List<Major> getAllMajor() {
		List list=getHibernateTemplate().find("from Major where majorStatus='Y'");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public List<SymptomTag> getAllSymptomTag() {
		List list=getHibernateTemplate().find("from SymptomTag where symptomStatus='Y'");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public String getOptionById(Long doctorTitle) {
		List list=getHibernateTemplate().find("select name from Position where rank=?",doctorTitle);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

	@Override
	public List<HospitalBasicInfo> getHospitalBasicInfoList(
			HospitalBasicInfo hospitalBasicInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer(" from HospitalBasicInfo p where 1=1");
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getHospitalLname() != null && !"".equals(hospitalBasicInfo.getHospitalLname().trim())){
				hql.append(" and p.hospitalLname like ?");
				params.add('%' + hospitalBasicInfo.getHospitalLname().trim() + '%' );
			}
		}
		List<HospitalBasicInfo> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<DoctorServiceType> getAllDoctorServiceType(DoctorServiceType doctorServiceType,
			Long parentId,Long id){
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer(" from DoctorServiceType p where 1=1 and Is_available='Y'");
		if(doctorServiceType != null){
			if(doctorServiceType.getServiceTypeName() != null && !"".equals(doctorServiceType.getServiceTypeName().trim())){
				hql.append(" and p.serviceTypeName like ?");
				params.add('%' + doctorServiceType.getServiceTypeName().trim() + '%' );
			}
		}
		if(parentId != null){
			hql.append(" and p.parentDoctorServiceType.id = ?");
			params.add(parentId);
		}
		if(id != null){
			hql.append(" and p.id = ?");
			params.add(id);
		}
		List<DoctorServiceType> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public void saveDoctorServiceContent(
			DoctorServiceContent doctorServiceContent) {
		this.getHibernateTemplate().save(doctorServiceContent);
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
	public List<DoctorGoodField> getAllDoctorGoodField() {
		List list=getHibernateTemplate().find("from DoctorGoodField where isStart='Y'");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

}
