package com.kybaby.newbussiness.doctorclinic.dao.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.dao.DoctorClinicDao;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorClinicTimeSegment;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgClinicdate;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgTimeSetting;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePracticeOrgInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
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
	public Long saveDoctorMorePracticeByRecord(DoctorMorePractice doctorMorePractice) {
		if(doctorMorePractice.getId() != null) {
			DoctorMorePractice old = this.getDoctorMorePracticeById(doctorMorePractice.getId());
			/**
			 * 得到系统当前时间
			 */
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currentDate = new Date(System.currentTimeMillis());
			String currentDateStr = sdf.format(currentDate);
			
			//上班操作
			if (DoctorMorePractice.START_WORK.equals(doctorMorePractice.getStartEndFlag())) {
				old.setOrgClinicStartTime(currentDateStr);
				old.setOrgClinicStartAddress(doctorMorePractice.getOrgClinicStartAddress());
				//修改状态
				old.setOrgClinicStatus(ConstantManage.DOCTOR_START_WORK);
			} 
			//下班操作
			else if (DoctorMorePractice.END_WORK.equals(doctorMorePractice.getStartEndFlag())) {
				old.setOrgClinicEndTime(currentDateStr);
				old.setOrgClinicEndAddress(doctorMorePractice.getOrgClinicEndAddress());
				//修改状态
				old.setOrgClinicStatus(ConstantManage.HASE_FINISHED_CLINIC_ORDER);
				//建议给医生支付保底薪酬，由线下操作
			}
			
			this.getHibernateTemplate().update(old);
		}
		return doctorMorePractice.getId();
	}
	@Override
	public Long saveOrUpdateDoctorClinicTimeSegment(DoctorClinicTimeSegment doctorClinicTimeSegment){
		Long id = null;
		if (doctorClinicTimeSegment == null) return id;
		if(doctorClinicTimeSegment.getId() == null){
			id = (Long) this.getHibernateTemplate().save(doctorClinicTimeSegment);
		}else{
			id = doctorClinicTimeSegment.getId();
			DoctorClinicTimeSegment old = this.getHibernateTemplate().get(DoctorClinicTimeSegment.class, id);
			BeanUtils.copyProperties(doctorClinicTimeSegment, old, new String[]{"id","doctorMorePractice"});
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
		StringBuffer hql = new StringBuffer("from DoctorMorePractice p where 1=1");
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
			if(doctorMorePractice.getClinicDate() != null && !"".equals(doctorMorePractice.getClinicDate().trim())){
				hql.append(" and p.clinicDate=?");
				params.add(doctorMorePractice.getClinicDate().trim());
			}
			if(doctorMorePractice.getClinicBeganTime() != null && !"".equals(doctorMorePractice.getClinicBeganTime().trim())){
				hql.append(" and p.clinicBeganTime=?");
				params.add(doctorMorePractice.getClinicBeganTime().trim());
			}
			if(doctorMorePractice.getClinicEndTime() != null && !"".equals(doctorMorePractice.getClinicEndTime().trim())){
				hql.append(" and p.clinicEndTime=?");
				params.add(doctorMorePractice.getClinicEndTime().trim());
			}
			if(doctorMorePractice.getDoctorMorePracticeOrgInfo() != null && !"".equals(doctorMorePractice.getDoctorMorePracticeOrgInfo().getId())){
				hql.append(" and p.doctorMorePracticeOrgInfo.id=?");
				params.add(doctorMorePractice.getDoctorMorePracticeOrgInfo().getId());
			}
			if(doctorMorePractice.getOrgClinicTimeIds() != null && !"".equals(doctorMorePractice.getOrgClinicTimeIds().trim())){
				hql.append(" and  find_in_set('"+doctorMorePractice.getOrgClinicTimeIds().trim()+"',p.orgClinicTimeIds) > 0");
			}
		}
		hql.append(" order by p.clinicDate desc");
		List<DoctorMorePractice> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			//显示分段时间和要求门诊人数（供前台显示）
			for (DoctorMorePractice d : list) {
				if (d.getOrgClinicTimeIds()!=null) {
					String[] arrayOrgClinicTime = d.getOrgClinicTimeIds().split(",");
					StringBuffer joinStr = new StringBuffer("");
					int countStr = 0;
					for (int i=0; i<arrayOrgClinicTime.length; i++) {
						DoctorMoreOrgTimeSetting dmot = getDoctorMoreOrgTimeSettingById(Long.valueOf(arrayOrgClinicTime[i]));
						if (dmot!=null && dmot.getTimeName()!=null) {
							joinStr.append(dmot.getTimeName()).append("  ");
						}
						if (dmot!=null && dmot.getRequireClinicNum()!=null) {
							countStr += Integer.parseInt(dmot.getRequireClinicNum());
						}
					}
					d.setOrgClinicTimeShow(joinStr.toString());
					d.setRequireClinicNumShow(String.valueOf(countStr));
				}
			}
			return list;
		}
		return null;
	}

	@Override
	public List<DoctorServiceType> getDoctorServiceTypeByDoctorId(
			DoctorInfo doctorInfo) {
		List params = new ArrayList<>();
		/**
		======此代码等DoctorServiceContent里保存了东西再用，在医生认证的时候将医生选择的服务保存到DoctorServiceContent
		StringBuffer hql = new StringBuffer("select p.doctorServiceType from DoctorServiceContent p where 1=1 and p.doctorServiceType.parentDoctorServiceType.serviceTypeName='多点执业'");
		if(doctorInfo != null){
			if(doctorInfo.getId() != null){
				hql.append(" and p.doctorInfo.id=?");
				params.add(doctorInfo.getId());
			}
		}
		 */
		String ids = doctorInfo.getServiceTypeIds()==null?"":doctorInfo.getServiceTypeIds();
		ids = ids.replaceAll("::", ",");
		StringBuffer hql = new StringBuffer(" from DoctorServiceType p where p.id in ("+ids+")");
		List<DoctorServiceType> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<DoctorMorePracticeOrgInfo> getDoctorMorePracticeOrgInfoList() {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from DoctorMorePracticeOrgInfo where 1=1");
		List<DoctorMorePracticeOrgInfo> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public Boolean checkTimeIsUsed(String date, String time,
			DoctorInfo doctorInfo,String clinicOrgType) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from OrderInfoClinic p where 1=1");
		String status = "('"+ConstantManage.HASE_BOOKED_CLINIC_ORDER+"','"+ConstantManage.HASE_FINISHED_CLINIC_ORDER
						+"','"+ConstantManage.HASE_MEET_CLINIC_ORDER
						+"','"+ConstantManage.HASE_EVALUATED_CLINIC_ORDER+"')";
		hql.append(" and p.orderStatus in "+status);
		hql.append(" and p.appointmentDate=?");
		params.add(date);
		hql.append(" and p.doctorInfo.id=?");
		params.add(doctorInfo.getId());
		hql.append(" and p.clinicOrgType=?");
		params.add(clinicOrgType);
		List<OrderInfoClinic> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return true;
		}
		return false;
	}
	@Override
	public void deleteClinicTimeSegment(DoctorMorePractice doctorMorePractice) {
		StringBuffer sql = new StringBuffer("delete from doctor_clinic_time_segment where more_practice_id=:more_practice_id");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query queryupdate = session.createSQLQuery(sql.toString());
		queryupdate.setLong("more_practice_id", doctorMorePractice.getId());
		queryupdate.executeUpdate();
		session.close();
	}
	@Override
	public List<DoctorMoreOrgTimeSetting> getMoreOrgTimeSettingList(
			DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from DoctorMoreOrgTimeSetting p where 1=1");
		if(doctorMorePracticeOrgInfo != null){
			if(doctorMorePracticeOrgInfo.getId() != null){
				hql.append(" and p.doctorMorePracticeOrgInfo.id = ?");
				params.add(doctorMorePracticeOrgInfo.getId());
			}
		}
		List<DoctorMoreOrgTimeSetting> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	@Override
	public DoctorMorePracticeOrgInfo getDoctorMorePracticeOrgInfoByid(Long id) {
		return this.getHibernateTemplate().get(DoctorMorePracticeOrgInfo.class, id);
	}
	@Override
	public DoctorMoreOrgTimeSetting getDoctorMoreOrgTimeSettingById(Long id) {
		return this.getHibernateTemplate().get(DoctorMoreOrgTimeSetting.class, id);
	}
	@Override
	public Long getOrderCountByDoctor(DoctorInfo doctorInfo,String appointmentDate) {
		StringBuffer sql = new StringBuffer("SELECT COUNT(1) FROM order_info_clinic o WHERE 1=1 ");
		sql.append(" AND o.clinic_org_type=1 ");
		sql.append(" AND o.appointment_date= :appointment_date");
		sql.append(" AND o.doctor_id = :doctor_id");
		sql.append("  AND o.order_status IN('已评价','已完成')");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql.toString());
		query.setString("appointment_date", appointmentDate);
		query.setLong("doctor_id", doctorInfo.getId());
		List list = query.list();
		return Long.valueOf(list.get(0).toString());
	}
	@Override
	public HospitalBasicInfo getHospitalBasicInfoByNameOrId(String name, Long id) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from HospitalBasicInfo p where 1=1");
		if(name != null){
			hql.append(" and p.hospitalLname = ?");
			params.add(name);
		}
		if(id != null){
			hql.append(" and p.id = ?");
			params.add(id);
		}
		List<HospitalBasicInfo> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	@Override
	public List<DoctorMoreOrgClinicdate> getDoctorMoreOrgClinicdateList() {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from DoctorMoreOrgClinicdate p where 1=1 and p.doctorMorePracticeOrgInfo.isAvailable='Y'");
		hql.append(" and p.canClinicDate >= CURDATE()");
		List<DoctorMoreOrgClinicdate> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	
}
