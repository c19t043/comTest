package com.kybaby.newbussiness.userconsult.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.userconsult.dao.ConsultDoctorInfoDao;
import com.kybaby.newbussiness.userconsult.domain.ConsultBabySet;
import com.kybaby.newbussiness.userconsult.domain.ConsultDoctorInfo;
import com.kybaby.newbussiness.userconsult.domain.ConsultOrderInfo;
import com.kybaby.util.DateManage;
@SuppressWarnings("all")
public class ConsultDoctorInfoDaoImpl extends HibernateDaoSupport implements ConsultDoctorInfoDao{

	@Override
	public List<ConsultDoctorInfo> getConsultDoctorInfoList(
			ConsultDoctorInfo consultDoctorInfo,DoctorInfo doctorInfo) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from ConsultDoctorInfo p where 1=1 and p.isEnable='Y' ");
		if(consultDoctorInfo != null){
			if(consultDoctorInfo.getDoctorInfo() != null){
				
			}
		}
		if(doctorInfo != null){
			if(doctorInfo.getDepartment() != null && !"".equals(doctorInfo.getDepartment().trim()) && 
					!"全部".equals(doctorInfo.getDepartment().trim())){
				hql.append(" and p.doctorInfo.department like ?");
				params.add("%"+doctorInfo.getDepartment().trim()+"%");
			}
		}
		List<ConsultDoctorInfo> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public Long getSumConsultServiceStar(DoctorInfo doctorInfo,String comment) {
		StringBuffer sql = new StringBuffer("SELECT cast(SUM(a.dutyStarLevel+a.onTimeStarLevel+a.serviceStarLevel) as char) FROM evaluate a WHERE a.comments= :comments AND a.doctorId=:doctorId ");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql.toString());
		query.setString("comments", comment);
		query.setLong("doctorId", doctorInfo.getId());
		List list = query.list();
		session.close();
		if(list.isEmpty()) return 0L;
		return list.get(0)==null?0L:Long.valueOf(list.get(0).toString());
	}

	@Override
	public Long saveOrUpdateConsultOrderInfo(ConsultOrderInfo consultOrderInfo) {
		Long id = null;
		if(consultOrderInfo == null){
			return id;
		}
		if(consultOrderInfo.getId() == null){
			id = (Long) this.getHibernateTemplate().save(consultOrderInfo);
		}else{
			id = consultOrderInfo.getId() ;
			this.getHibernateTemplate().update(consultOrderInfo);
		}
		return id;
	}

	@Override
	public ConsultOrderInfo getConsultOrderInfoById(Long id) {
		return getHibernateTemplate().get(ConsultOrderInfo.class, id);
	}

	@Override
	public ConsultDoctorInfo getConsultDoctorInfoById(Long consultDoctorId,Long doctorId) {
		ConsultDoctorInfo obj = null;
		if(consultDoctorId != null){
			obj = getHibernateTemplate().get(ConsultDoctorInfo.class, consultDoctorId);
		}else if(doctorId != null){
			StringBuffer hql = new StringBuffer(" from ConsultDoctorInfo p where 1=1 and p.doctorInfo.id= ").append(doctorId);
			Session session = this.getHibernateTemplate().getSessionFactory().openSession();
			obj = (ConsultDoctorInfo) session.createQuery(hql.toString()).uniqueResult();
			session.close();
		}
		return obj;
	}

	@Override
	public void addConsultBabySet(ConsultBabySet consultBabySet) {
		this.getHibernateTemplate().save(consultBabySet);
	}

	@Override
	public List<Long> getConsultDoctorIdsByUser(Long userId,String userType,String isEnd) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer("select distinct logId from UserConsultDoctor where 1=1 ");
		if(userId != null){
			hql.append(" and userId=?");
			params.add(userId);
		}
		if(StringUtils.isNotEmpty(userType)){
			hql.append(" and userType=?");
			params.add(userType);
		}
		if(StringUtils.isNotEmpty(isEnd)){
			hql.append(" and isEnd=?");
			params.add(isEnd);
		}
		hql.append(" order by submitTime desc");
		List list=getHibernateTemplate().find(hql.toString(),params.toArray());
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}
	
	@Override
	public long countNewMessage(Long userId, Long doctorId, String time,String userType) {
		List list=getHibernateTemplate().find("SELECT COUNT(id) FROM UserConsultDoctor WHERE userId=? AND doctorId=? AND submitTime>?  and userType=? AND isEnd='N' and isReply='Y' and isUserAlreadyRead is null ",userId,doctorId,time,userType);
		if(list.isEmpty()==true){
			return 0;
		}
		return (long)list.get(0);
	}

	@Override
	public void closeConsultOrderPromptTask(Long logId) {
		//StringBuffer sql = new StringBuffer("update consult_order_info set order_status='待评价' WHERE effective_end_time BETWEEN NOW()-INTERVAL 60 MINUTE AND NOW() AND order_status = '已付款' ");
		//String sql2 = "UPDATE consult_order_info a,user_consult_doctor b  SET b.isEnd='Y',b.is_system_end='Y' WHERE a.id = b.logId AND a.effective_end_time BETWEEN NOW()-INTERVAL 60 MINUTE AND NOW() AND a.order_status  = '已付款'";
		String sql2 = "UPDATE user_consult_doctor b  SET b.isEnd='Y',b.is_system_end='Y' WHERE b.logId="+logId;
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		//int ret1 = session.createSQLQuery(sql.toString()).executeUpdate();
		int ret2 = session.createSQLQuery(sql2).executeUpdate();
		session.close();
		//System.out.println("更新表consult_order_info定时任务执行，更新"+ret1+"条数据");
		System.out.println("更新表user_consult_doctor定时任务执行，更新"+ret2+"条数据");
	}

	@Override
	public List<ConsultOrderInfo> getConsultOrderInfoList(
			ConsultOrderInfo consultOrderInfo) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from ConsultOrderInfo where 1=1 ");
		if(consultOrderInfo != null){
			if(StringUtils.isNotEmpty(consultOrderInfo.getOrderStatus())){
				hql.append(" and orderStatus=?");
				params.add(consultOrderInfo.getOrderStatus().trim());
			}
			if(StringUtils.isNotEmpty(consultOrderInfo.getEffectiveEndTime())){
				String before = DateManage.getBeforeOrAfterMinute(-60);
				hql.append(" and effectiveEndTime>=  '"+before+"' AND effectiveEndTime <=NOW())");
			}
			if(consultOrderInfo.getUserInfo() != null && consultOrderInfo.getUserInfo().getId() != null){
				hql.append(" and userInfo.id = ? ");
				params.add(consultOrderInfo.getUserInfo().getId());
			}
		}
		List<ConsultOrderInfo> list=getHibernateTemplate().find(hql.toString(),params.toArray());
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	@Override
	public void addNewDoctorAccount(long doctorId, double amount, String type,
			String accountDesc) {
		DoctorAccount doctorAccount=new DoctorAccount();
		doctorAccount.setDoctorId(doctorId);
		doctorAccount.setAmount(amount);
		doctorAccount.setType(type);
		doctorAccount.setAccountDesc(accountDesc);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date rightNow=new Date(System.currentTimeMillis());
		String submitTime=sdf.format(rightNow);
		doctorAccount.setUpdateTime(submitTime);
		getHibernateTemplate().save(doctorAccount);
	}

	@Override
	public void updateDoctor(DoctorInfo doctorInfo) {
		getHibernateTemplate().update(doctorInfo);
	}

	@Override
	public DoctorInfo getDoctorInfoById(Long doctorId) {
		return getHibernateTemplate().get(DoctorInfo.class,doctorId);
	}
	
}
