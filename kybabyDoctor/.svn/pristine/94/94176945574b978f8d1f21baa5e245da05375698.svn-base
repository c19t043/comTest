package com.kybaby.dao.impl;

import java.util.ArrayList;
import java.util.List;


























import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.ConsultDao;
import com.kybaby.domain.BabyBasicData;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.HealthRecord;
import com.kybaby.domain.UserConsultDoctor;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.commondiseaseanddrug.domain.CommonDisease;
import com.kybaby.newbussiness.familydoctor.domain.ConsultBabyInfo;
import com.kybaby.newbussiness.familydoctor.domain.ConsultDoctorInfo;
import com.kybaby.newbussiness.familydoctor.domain.ConsultFastReplay;
import com.kybaby.newbussiness.familydoctor.domain.ConsultIllRecord;

/**
 * @author sujiantang
 *
 */
public class ConsultDaoImpl extends HibernateDaoSupport implements ConsultDao{
	@Override
	public UserConsultDoctor getLastUserConsultDoctorSessionByType(long userId,long doctorId,String isEnd) {
		List<UserConsultDoctor> list=getHibernateTemplate().find("from UserConsultDoctor where userId=? and doctorId=? and userType='1' and  isEnd=? order by submitTime desc", new Object[]{userId,doctorId,isEnd});
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	//获取未结束的咨询记录
	@Override
	public List<Object[]> getConsultByDoctorId(Long id,String isEnd,String userType) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String sqlQury = "SELECT * FROM (SELECT * FROM user_consult_doctor ORDER BY submitTime DESC) a WHERE 1=1 ";
		if(StringUtils.isNotEmpty(isEnd)){
			sqlQury += " and isEnd='"+isEnd+"' ";
		}
		sqlQury += "AND doctorId = "+id+" and user_type='"+userType+"' GROUP BY logId ORDER BY submitTime DESC";
		Query query = session.createSQLQuery(sqlQury);
		List list=query.list();
//		List list=getHibernateTemplate().find("SELECT a FROM (SELECT UserConsultDoctor FROM UserConsultDoctor ORDER BY submitTime DESC) a WHERE isEnd='N' AND doctorId =? GROUP BY logId ORDER BY submitTime DESC",id);
		if(list.isEmpty()==true){
			return null;
		}
		
		return list;
	}

	//获取某条咨询的详细内容
	@Override
	public UserConsultDoctor getConsultByConsultId(Long userConsultDoctorId) {
		return getHibernateTemplate().get(UserConsultDoctor.class, userConsultDoctorId);
	}

	//获取用户的健康档案记录
	@Override
	public List<HealthRecord> getHealthRecordByUserId() {
		
		return null;
	}

	@Override
	public String getLatistTime(Long doctorId, Long userId,String isEnd,String userType) {
		List list=getHibernateTemplate().find("SELECT submitTime FROM UserConsultDoctor WHERE doctorId=? AND userId=? AND isReply='Y' AND isEnd=? and userType=? ORDER BY submitTime DESC",doctorId,userId,isEnd, userType);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

	@Override
	public Long getNewMes(Long doctorId, Long userId,String isEnd,String userType) {
		List list=getHibernateTemplate().find("SELECT COUNT(*) FROM UserConsultDoctor WHERE doctorId=? AND userId=? AND isEnd=? and userType=? and isDoctorAlreadyRead is null ",doctorId,userId, isEnd,userType);
		if(list.isEmpty()==true){
			return null;
		}
		return (Long)list.get(0);
	}
	@Override
	public Long getNewMes(Long doctorId, Long userId, String latistDoctor,String isEnd,String userType) {
		List list=getHibernateTemplate().find("SELECT COUNT(*) FROM UserConsultDoctor WHERE doctorId=? AND userId=? AND submitTime>? AND isEnd=? and userType=? and isDoctorAlreadyRead is null ",doctorId,userId,latistDoctor,isEnd,userType);
		if(list.isEmpty()==true){
			return null;
		}
		return (Long)list.get(0);
	}

	@Override
	public UserInfo getSomeUserInfoById(Long userId) {
		List list=getHibernateTemplate().find("from UserInfo where id=? and userStatus='Y'",userId);
		if(list.isEmpty()==true){
			return null;
		}
		return (UserInfo)list.get(0);
	}

	@Override
	public UserConsultDoctor getOneConsultByDoctorAndUserId(Long doctorId,
			Long userId, Long logId,String isEnd) {
		List list=getHibernateTemplate().find("from UserConsultDoctor where doctorId=? and userId=? and logId=? and isEnd=? ORDER BY submitTime DESC",doctorId,userId,logId, isEnd);
		if(list.isEmpty()==true){
			return null;
		}
		return (UserConsultDoctor)list.get(0);
	}

	@Override
	public List<UserConsultDoctor> getSomeUserConsultDoctor(Long logId,String isEnd) {
		List list=getHibernateTemplate().find("from UserConsultDoctor where logId=? and isEnd=? ORDER BY submitTime",logId, isEnd);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public String getDoctorImgByDoctorId(Long doctId) {
		List list=getHibernateTemplate().find("SELECT doctorImage FROM DoctorInfo WHERE id=?",doctId);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

	@Override
	public UserInfo getUserImgByUserId(Long usId) {
		List list=getHibernateTemplate().find("FROM UserInfo WHERE id=? AND userStatus='Y'",usId);
		if(list.isEmpty()==true){
			return null;
		}
		return (UserInfo)list.get(0);
	}

	@Override
	public String getSymptomTagNameById(Long tagId) {
		List list=getHibernateTemplate().find("SELECT symptomName FROM SymptomTag WHERE id=?",tagId);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

	@Override
	public BabyBasicData getBabyBasicDataByUserId(Long id) {
		List list=getHibernateTemplate().find("FROM BabyBasicData WHERE userId=?",id);
		if(list.isEmpty()==true){
			return null;
		}
		return (BabyBasicData)list.get(0);
	}

	@Override
	public UserConsultDoctor getUserConsultDoctorByLogId(Long logId) {
		List list=getHibernateTemplate().find("FROM UserConsultDoctor WHERE logId=? AND isEnd='N'",logId);
		if(list.isEmpty()==true){
			return null;
		}
		return (UserConsultDoctor)list.get(0);
	}

	@Override
	public Long save(UserConsultDoctor userConsultDoctor) {
		return (Long) getHibernateTemplate().save(userConsultDoctor);
	}

	@Override
	public List<UserConsultDoctor> getAllConsulation(long doctorId, long logId,String msgType) {
		List<UserConsultDoctor> list=getHibernateTemplate().find("from UserConsultDoctor where doctorId=? and logId=? and userType=? ", new Object[]{doctorId,logId,msgType});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public void updateSomeConsultById(UserConsultDoctor consult) {
		getHibernateTemplate().update(consult);
	}

	@Override
	public ConsultBabyInfo getConsultBabyInfoByLogId(Long logId) {
		List<ConsultBabyInfo> list=getHibernateTemplate().find("select a.consultBabyInfo from ConsultBabySet a where a.logId=? ", new Object[]{logId});
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<CommonDisease> getAllCommonDisease() {
		List<CommonDisease> list=getHibernateTemplate().find("from CommonDisease  ");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public void addConsultIllRecord(ConsultIllRecord consultIllRecord) {
		getHibernateTemplate().save(consultIllRecord);
	}

	@Override
	public List<ConsultIllRecord> getConsultIllRecordList(Long logId) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer(" from ConsultIllRecord p where 1=1");
		if(logId != null){
			hql.append(" and p.logId = ?");
			params.add(logId);
		}
		List list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public List<ConsultFastReplay> getConsultFastReplayList(
			DoctorInfo doctorInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer(" from ConsultFastReplay p where 1=1 and p.isEffect='Y' ");
		if(doctorInfo != null){
			if(doctorInfo.getId() != null){
				hql.append(" and p.doctorInfo.id = ?");
				params.add(doctorInfo.getId());
			}
		}
		List<ConsultFastReplay> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public void saveOrUpdateConsultFastReplay(
			ConsultFastReplay consultFastReplay) {
		if(consultFastReplay == null) return;
		if(consultFastReplay.getId() == null){
			this.getHibernateTemplate().save(consultFastReplay);
		}else{
			this.getHibernateTemplate().update(consultFastReplay);
		}
	}

	@Override
	public void saveOrUpdateConsultDoctorInfo(
			ConsultDoctorInfo consultDoctorInfo) {
		if(consultDoctorInfo == null) return;
		if(consultDoctorInfo.getId() == null){
			this.getHibernateTemplate().save(consultDoctorInfo);
		}else{
			this.getHibernateTemplate().update(consultDoctorInfo);
		}
	}

	@Override
	public List<ConsultDoctorInfo> getConsultDoctorInfoList(
			DoctorInfo doctorInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer(" from ConsultDoctorInfo p where 1=1");
		if(doctorInfo != null && doctorInfo.getId() != null){
			hql.append(" and p.doctorInfo.id = ?");
			params.add(doctorInfo.getId());
		}
		List list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public ConsultDoctorInfo getConsultDoctorInfoById(Long id) {
		return this.getHibernateTemplate().get(ConsultDoctorInfo.class, id);
	}

}
