package com.kybaby.newbussiness.medicalorgandbusiness.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.OrgBusinessManageDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgBusinessRelation;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganModuleInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganOperator;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.PageBean;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.VaccineInfo;
import com.kybaby.util.CalculationMethod;
import com.kybaby.util.EncryptUtil;
@SuppressWarnings("all")
public class OrgBusinessManageDaoImpl extends HibernateDaoSupport implements OrgBusinessManageDao {
	@Override
	public List<HospitalBasicInfo> getHospitalBasicInfoList(
			HospitalBasicInfo hospitalBasicInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("select distinct p.hospitalBasicInfo from OrgBusinessRelation p where 1=1 ");
		if(hospitalBasicInfo != null){
//			if(hospitalBasicInfo.getHospitalType() != null && !"".equals(hospitalBasicInfo.getHospitalType().trim())){
//				hql.append(" and p.hospitalType=?");
//				params.add(hospitalBasicInfo.getHospitalType().trim());
//			}
		}
		hql.append(" order by p.hospitalBasicInfo.hospitalLname");
		List<HospitalBasicInfo> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public OrganOperator getOrganOperator(OrganOperator organOperator) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from OrganOperator p where 1=1 ");
		if(organOperator != null){
			if(organOperator.getHospitalBasicInfo() != null){
				if(organOperator.getHospitalBasicInfo().getId() != null ){
					hql.append(" and p.hospitalBasicInfo.id=?");
					params.add(organOperator.getHospitalBasicInfo().getId());
				}
			}
			if(organOperator.getLoginName() != null && !"".equals(organOperator.getLoginName().trim())){
				hql.append(" and p.loginName=?");
				params.add(organOperator.getLoginName().trim());
			}
			if(organOperator.getPassword() != null && !"".equals(organOperator.getPassword().trim())){
				hql.append(" and p.password=?");
				params.add(EncryptUtil.getMD5Str(organOperator.getPassword().trim()));
			}
			
		}
		List<OrganOperator> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	@Override
	public List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList(
			HospitalBasicInfo hospitalBasicInfo,UserChildcareAppointmentInfo userChildcareAppointmentInfo,
			Boolean isNowDate,PageBean pageBean) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from UserChildcareAppointmentInfo p where 1=1 ");
		hql.append("and p.id not in (select t.id from UserChildcareAppointmentInfo t where t.orderNum like '%.')");
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null){
				hql.append(" and p.hospitalBasicInfo.id=?");
				params.add(hospitalBasicInfo.getId());
			}
		}
		if(userChildcareAppointmentInfo != null){
			//添加查询条件
			if(userChildcareAppointmentInfo.getStatus() != null && !"".equals(userChildcareAppointmentInfo.getStatus().trim())){
				hql.append(" and p.status =?");
				params.add(userChildcareAppointmentInfo.getStatus().trim());
			}
			if(userChildcareAppointmentInfo.getPreEncoding() != null && !"".equals(userChildcareAppointmentInfo.getPreEncoding().trim())){
				hql.append(" and p.preEncoding like ?");
				params.add("%"+userChildcareAppointmentInfo.getPreEncoding().trim()+"%");
			}
			if(userChildcareAppointmentInfo.getOpenBeginDate() != null && !"".equals(userChildcareAppointmentInfo.getOpenBeginDate().trim())){
				hql.append(" and p.organChildcareOpenResources.openDate  >= ?");
				params.add(userChildcareAppointmentInfo.getOpenBeginDate().trim());
			}
			if(userChildcareAppointmentInfo.getOpenEndDate() != null && !"".equals(userChildcareAppointmentInfo.getOpenEndDate().trim())){
				hql.append(" and p.organChildcareOpenResources.openDate  <= ?");
				params.add(userChildcareAppointmentInfo.getOpenEndDate().trim());
			}
			if(userChildcareAppointmentInfo.getIsMoney() != null && !"".equals(userChildcareAppointmentInfo.getIsMoney().trim())){
				hql.append(" and p.organChildcareOpenResources.isMoney  = ?");
				params.add(userChildcareAppointmentInfo.getIsMoney().trim());
			}
			if(userChildcareAppointmentInfo.getDoctorInfo() != null && userChildcareAppointmentInfo.getDoctorInfo().getId() != null){
				hql.append(" and p.doctorInfo.id  = ?");
				params.add(userChildcareAppointmentInfo.getDoctorInfo().getId());
			}
			if(userChildcareAppointmentInfo.getUserInfo() != null && userChildcareAppointmentInfo.getUserInfo().getId() != null){
				hql.append(" and p.userInfo.id  = ?");
				params.add(userChildcareAppointmentInfo.getUserInfo().getId());
			}
			if(userChildcareAppointmentInfo.getDoctorInfo() != null && 
					userChildcareAppointmentInfo.getDoctorInfo().getDoctorName() != null &&
					!"".equals(userChildcareAppointmentInfo.getDoctorInfo().getDoctorName().trim())){
				hql.append(" and p.doctorInfo.doctorName  like ?");
				params.add("%"+userChildcareAppointmentInfo.getDoctorInfo().getDoctorName().trim()+"%");
			}
		}
//		String status = "('"+ConstantManage.HASE_BOOKED_CLINIC_ORDER+"','"
//				+"','"+ConstantManage.HASE_MEET_CLINIC_ORDER+"')";
		if(isNowDate){
			if(userChildcareAppointmentInfo != null && 
					userChildcareAppointmentInfo.getOrganChildcareOpenResources() != null && 
					userChildcareAppointmentInfo.getOrganChildcareOpenResources().getOpenDate() != null &&
					!"".equals(userChildcareAppointmentInfo.getOrganChildcareOpenResources().getOpenDate().trim())){
				hql.append(" and p.organChildcareOpenResources.openDate = ?");
				params.add(userChildcareAppointmentInfo.getOrganChildcareOpenResources().getOpenDate().trim());
			}else{
				hql.append(" and p.organChildcareOpenResources.openDate = curdate()");
			}
			hql.append(" order by p.organChildcareOpenResourcesDatail.openStartTime ,p.preEncoding");
		}else{
			hql.append(" order by p.organChildcareOpenResources.openDate desc,p.organChildcareOpenResourcesDatail.openStartTime ,p.preEncoding");
		}
		List<UserChildcareAppointmentInfo> list = new ArrayList<>();
		//得到总条数
		if(pageBean != null){
			Session session = this.getHibernateTemplate().getSessionFactory().openSession();
			//分页数据
			Query query=session.createQuery(hql.toString());
			//得到总条数
			String hqlCount = "select count(*) as allRows " + hql;
			Query queryCount = session.createQuery(hqlCount);
			for (int i = 0 ; i < params.size() ; i++){
				query.setParameter( i, params.get(i));
				queryCount.setParameter( i, params.get(i));
			}
			List listCount = queryCount.list();
			Iterator iter = listCount.iterator();  
			if(iter.hasNext()){ 
				Integer rows = Integer.valueOf(iter.next().toString());
				pageBean.setRowsCount(rows.intValue());
			}
			
			query.setFirstResult((pageBean.getCurPage()-1)*pageBean.getPageSize());
			query.setMaxResults(pageBean.getPageSize());
			list = query.list();
			session.close();
		}else{
			list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		}
		if(!list.isEmpty()){
			return list;
		}
		return null;

	}

	@Override
	public void saveOrUpdateUserChildcareAppointmentInfo(
		UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		Long id = null;
		if(userChildcareAppointmentInfo.getId() == null){
			id = (Long) this.getHibernateTemplate().save(userChildcareAppointmentInfo);
		}else{
			id = userChildcareAppointmentInfo.getId();
			this.getHibernateTemplate().update(userChildcareAppointmentInfo);
		}
	}
	@Override
	public UserChildcareAppointmentInfo getUserChildcareAppointmentInfoById(
			Long id) {
		return this.getHibernateTemplate().get(UserChildcareAppointmentInfo.class, id);
	}

	@Override
	public List<OrgBusinessRelation> getOrgBusinessRelationList(
			HospitalBasicInfo hospitalBasicInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from OrgBusinessRelation p where 1=1 ");
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null){
				hql.append(" and p.hospitalBasicInfo.id=?");
				params.add(hospitalBasicInfo.getId());
			}
		}
		hql.append(" order by p.orgOpenBusiness.sort");
		List<OrgBusinessRelation> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<UserInoculationAppointmentInfo> getUserInoculationAppointmentInfoList(
			HospitalBasicInfo hospitalBasicInfo,
			UserInoculationAppointmentInfo userInoculationAppointmentInfo,Boolean isNowDate) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from UserInoculationAppointmentInfo p where 1=1 ");
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null){
				hql.append(" and p.hospitalBasicInfo.id=?");
				params.add(hospitalBasicInfo.getId());
			}
		}
		if(userInoculationAppointmentInfo != null){
			//添加查询条件
			if(userInoculationAppointmentInfo.getStatus() != null && !"".equals(userInoculationAppointmentInfo.getStatus().trim())){
				if(userInoculationAppointmentInfo.getStatus().indexOf(",") > -1){
					String statu[] = userInoculationAppointmentInfo.getStatus().split(",");
					String strtus = "'";
					for(int i=0;i<statu.length;i++){
						strtus += statu[i] + "','";
					}
					hql.append(" and p.status in ("+strtus.substring(0, strtus.lastIndexOf(",'"))+")");
				}else{
					hql.append(" and p.status = ?");
					params.add(userInoculationAppointmentInfo.getStatus().trim());
				}
				
			}
			if(userInoculationAppointmentInfo.getAppointmentCode() != null && !"".equals(userInoculationAppointmentInfo.getAppointmentCode().trim())){
				hql.append(" and p.appointmentCode like ?");
				params.add("%"+userInoculationAppointmentInfo.getAppointmentCode().trim()+"%");
			}
			if(userInoculationAppointmentInfo.getOpenBeginDate() != null && !"".equals(userInoculationAppointmentInfo.getOpenBeginDate().trim())){
				hql.append(" and p.organInoculationOpenResources.openDate >= ?");
				params.add(userInoculationAppointmentInfo.getOpenBeginDate().trim());
			}
			if(userInoculationAppointmentInfo.getOpenEndDate() != null && !"".equals(userInoculationAppointmentInfo.getOpenEndDate().trim())){
				hql.append(" and p.organInoculationOpenResources.openDate <= ?");
				params.add(userInoculationAppointmentInfo.getOpenEndDate().trim());
			}
			if(userInoculationAppointmentInfo.getUserInfo() != null && userInoculationAppointmentInfo.getUserInfo().getId() != null){
				hql.append(" and p.userInfo.id = ?");
				params.add(userInoculationAppointmentInfo.getUserInfo().getId());
			}
		}
		if(isNowDate){
			if(userInoculationAppointmentInfo != null && userInoculationAppointmentInfo.getOrganInoculationOpenResources() != null &&
					userInoculationAppointmentInfo.getOrganInoculationOpenResources().getOpenDate() != null &&
					!"".equals(userInoculationAppointmentInfo.getOrganInoculationOpenResources().getOpenDate().trim())
					){
				hql.append(" and p.organInoculationOpenResources.openDate = ?");
				params.add(userInoculationAppointmentInfo.getOrganInoculationOpenResources().getOpenDate().trim());
			}else{
				hql.append(" and p.organInoculationOpenResources.openDate = curdate()");
			}
			hql.append(" order by p.organInoculationOpenResourcesDetail.openStartTime,p.appointmentCode");
		}else{
			hql.append(" order by p.organInoculationOpenResources.openDate,p.organInoculationOpenResourcesDetail.openStartTime,p.appointmentCode");
		}
		List<UserInoculationAppointmentInfo> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public void saveOrUpdateUserInoculationAppointmentInfo(
			UserInoculationAppointmentInfo userInoculationAppointmentInfo) {
		Long id = null;
		if(userInoculationAppointmentInfo.getId() == null){
			id = (Long) this.getHibernateTemplate().save(userInoculationAppointmentInfo);
		}else{
			id = userInoculationAppointmentInfo.getId();
			this.getHibernateTemplate().update(userInoculationAppointmentInfo);
		}
	}

	@Override
	public UserInoculationAppointmentInfo getUserInoculationAppointmentInfoById(
			Long id) {
		return this.getHibernateTemplate().get(UserInoculationAppointmentInfo.class, id);
	}

	@Override
	public List<VaccineInfo> getVaccineInfoList(
			UserInoculationAppointmentInfo userInoculationAppointmentInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("from VaccineInfo p where 1=1 ");
		if(userInoculationAppointmentInfo != null){
			if(userInoculationAppointmentInfo.getUserInfo() != null){
				//得到宝宝的月龄（在用户计免注册信息里得到准确生日）
				int babyMonthYear=0;
				ArchivesInfo archivesInfo = this.getCurrentUserIdentity(userInoculationAppointmentInfo.getUserInfo().getId(), null);
				String babyBirthday = archivesInfo.getChildrenBirthday();//usr.getBirthday();
				String rightNow = CalculationMethod.rightNowDate().toString();
				try {
					babyMonthYear =CalculationMethod.getMonthSpace(babyBirthday, rightNow);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				hql.append(" and p.inoculationMonth >= ?");
				params.add(String.valueOf(babyMonthYear-1));
				hql.append(" and p.inoculationMonth <= ?");
				params.add(String.valueOf(babyMonthYear+1));
			}
		}
		hql.append(" order by p.inoculationMonth");
		List<VaccineInfo> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	/**
	 * 得到用户计免注册信息
	 * @param userId
	 * @param mobile
	 * @return
	 */
	private ArchivesInfo getCurrentUserIdentity(Long userId, String mobile) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" from ArchivesInfo a where 1=1");
		if(userId != null) {
			hql.append(" and a.userInfo.id=?");
			params.add(userId);
		}
		if(mobile != null) {
			hql.append(" or a.archivesMobile=?");
			params.add(mobile);
		}
		List<ArchivesInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<OrganModuleInfo> getOrganRoleModuleList(
			OrganOperator organOperator) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" select a.organModuleInfo from OrganRoleModule a,OrganOperatorRole b where 1=1");
		if(organOperator != null) {
			if(organOperator.getId() != null){
				hql.append(" and a.organRole.id=b.organRole.id");
				hql.append(" and b.organOperator.id=?");
				params.add(organOperator.getId());
			}
		}
		List<OrganModuleInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()) {
			return list;
		}
		return null;
	}

	@Override
	public Object isKyInnoculationOrder(Long id) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		String sql = "SELECT kyid FROM kyinoculationorder2myorder WHERE id = :id";
		SQLQuery createSQLQuery = session.createSQLQuery(sql);
		createSQLQuery.setLong("id", id);
		Object uniqueResult = createSQLQuery.uniqueResult();
		return uniqueResult;
	}
}
