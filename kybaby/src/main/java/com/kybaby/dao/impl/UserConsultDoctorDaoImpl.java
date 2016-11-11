package com.kybaby.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.UserConsultDoctorDao;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserConsultDoctor;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.commondiseaseanddrug.domain.CommonDisease;
import com.kybaby.newbussiness.familydoctor.domain.DoctorWorkTime;

/**
 * @ClassName:UserConsultDoctorDaoImpl
 * @Description:用户咨询医生数据管理接口实现
 * @author Hoolee
 * @date 2015年10月12日下午2:19:14
 */
public class UserConsultDoctorDaoImpl extends HibernateDaoSupport implements UserConsultDoctorDao {

	public UserConsultDoctor getLastUserConsultDoctor(long userId, long doctorId) {
		List<UserConsultDoctor> list=getHibernateTemplate().find("from UserConsultDoctor where userId=? and doctorId=? and isBefore='Y' order by submitTime desc ", new Object[]{userId,doctorId});
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}
	
	public UserConsultDoctor getLastUserConsultDoctorSession(long userId, long doctorId) {
		List<UserConsultDoctor> list=getHibernateTemplate().find("from UserConsultDoctor where userId=? and doctorId=? and isBefore='Y' and isEnd='N' order by submitTime", new Object[]{userId,doctorId});
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public List<UserConsultDoctor> lastConsultationLog(long userId,long doctorId, long logId) {
		List<UserConsultDoctor> list=getHibernateTemplate().find("from UserConsultDoctor where logId=? and userId=? and doctorId=? and isBefore='Y' order by submitTime ", new Object[]{logId,userId,doctorId});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public UserConsultDoctor lastConsultationLogId(long userId, long doctorId) {
		List<UserConsultDoctor> list=getHibernateTemplate().find("from UserConsultDoctor where userId=? and doctorId=? and isBefore='Y' order by submitTime desc ", new Object[]{userId,doctorId});
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public UserConsultDoctor getLastUserAfterConsultDoctor(long userId,long doctorId) {
		List<UserConsultDoctor> list=getHibernateTemplate().find("from UserConsultDoctor where userId=? and doctorId=? and isBefore='N' order by submitTime desc ", new Object[]{userId,doctorId});
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public List<UserConsultDoctor> lastAfterConsultationLog(long userId,long doctorId, long logId) {
		List<UserConsultDoctor> list=getHibernateTemplate().find("from UserConsultDoctor where logId=? and userId=? and doctorId=? and isBefore='N' order by submitTime ", new Object[]{logId,userId,doctorId});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<UserConsultDoctor> lastConsultationLogAfterSomeId(long userId,long doctorId, long logId, long consutId) {
		List<UserConsultDoctor> list=getHibernateTemplate().find("from UserConsultDoctor where  logId=? and id>? and userId=? and doctorId=? and isBefore='Y' order by submitTime ", new Object[]{logId,consutId,userId,doctorId});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<UserConsultDoctor> lastAfterConsultationLogAfterSomeId(long userId, long doctorId, long logId, long consultId) {
		List<UserConsultDoctor> list=getHibernateTemplate().find("from UserConsultDoctor where  logId=? and id>? and userId=? and doctorId=? and isBefore='N' order by submitTime ", new Object[]{logId,consultId,userId,doctorId});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public void updateSomeConsult(UserConsultDoctor consult) {
		getHibernateTemplate().update(consult);
	}

	//add by sjt
	public List<Long> getHistoryDoctorIdList(long userId) {
		List list=getHibernateTemplate().find("select doctorId from UserConsultDoctor where userId=? order by doctorId desc",userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}


	//add by sjt
	public Object getSomeUserConsultDoctor(Long userId, Long doctorId) {
		List list=getHibernateTemplate().find("select symptomDescribe, submitTime, doctorReply, msgType, logId from UserConsultDoctor where userId=? and doctorId=? order by submitTime desc",userId,doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return (Object) list.get(0);
	}

	public long countNewMessage(Long userId, Long doctorId, String time) {
		List list=getHibernateTemplate().find("SELECT COUNT(id) FROM UserConsultDoctor WHERE userId=? AND doctorId=? AND submitTime>?  AND isEnd='N' and isUserAlreadyRead is null ",userId,doctorId,time);
		if(list.isEmpty()==true){
			return 0;
		}
		return (long)list.get(0);
	}

	public String getLastDoctorTime(Long userId, Long doctorId) {
		List list=getHibernateTemplate().find("select submitTime from UserConsultDoctor where userId=? and doctorId=? and isReply='N' and isEnd='N' order by submitTime desc",userId,doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

	public List getDoctorNameAndImage(Long doctorId) {
		List list=getHibernateTemplate().find("select doctorName, doctorImage from DoctorInfo where id=? and doctorStatus='Y'",doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List getSomeUserConsultDoctor(Long logId) {
		List list=getHibernateTemplate().find("from UserConsultDoctor where logId=? and isEnd='N' ORDER BY submitTime",logId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public String getSymptomTagNameById(Long id) {
		List list=getHibernateTemplate().find("SELECT symptomName FROM SymptomTag WHERE id=?",id);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

	public String getUserImgByUserId(Long usId) {
		List list=getHibernateTemplate().find("SELECT userImage FROM UserInfo WHERE id=? AND userStatus='Y'",usId);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

	public void addNewConsult(UserConsultDoctor consult) {
		getHibernateTemplate().save(consult);
	}

	public UserConsultDoctor getSomeTimeUserConsultDoctor(long userId,
			long doctorId, String submitTime) {
		List<UserConsultDoctor> list=getHibernateTemplate().find("from UserConsultDoctor where submitTime=? and doctorId=? and  userId=? ", new Object[]{submitTime,doctorId,userId});
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List getDoctorIdAndNameAndImage(Long doctorId) {
		List list=getHibernateTemplate().find("select doctorName, doctorImage,id from DoctorInfo where id=? and doctorStatus='Y'",doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public String getLastDoctorTimeReplace(Long userId, Long doctorId) {
		List list=getHibernateTemplate().find("select submitTime from UserConsultDoctor where userId=? and doctorId=? and isReply='N' and isEnd='N' order by submitTime desc",userId,doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}
	
	public long countNewMessageReplace(Long userId, Long doctorId, String time) {
		List list=getHibernateTemplate().find("SELECT COUNT(id) FROM UserConsultDoctor WHERE userId=? AND doctorId=? AND submitTime>?  AND isEnd='N'",userId,doctorId,time);
		if(list.isEmpty()==true){
			return 0;
		}
		return (long)list.get(0);
	}

	@Override
	public List getAllLastMsgInfo(Long userId) {
		Session session=getHibernateTemplate().getSessionFactory().openSession();
		String sqlQuery="SELECT symptomDescribe,submitTime,doctorReply,msgType,logId,doctorId,isBefore,isEnd FROM (SELECT * FROM  user_consult_doctor  ORDER BY submitTime DESC  ) A WHERE userId="+userId+" and isEnd='Y' GROUP BY doctorId,isbefore  ORDER BY submitTime DESC";
        Query query=session.createSQLQuery(sqlQuery);
        List list=query.list();
        if(list.isEmpty()==true)
        {
        	return null;
        }
        else
        {
        	return list;
        }
	}

	@Override
	public String getLastDoctorTimeByType(Long userId, Long doctorId,String type) {
		List list=getHibernateTemplate().find("select submitTime from UserConsultDoctor where userId=? and doctorId=? and isReply='N' and isBefore=?  order by submitTime desc",userId,doctorId,type);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

	@Override
	public long countNewMessageByType(Long userId, Long doctorId,String doctorTime, String type) {
		List list=getHibernateTemplate().find("SELECT COUNT(id) FROM UserConsultDoctor WHERE userId=? AND doctorId=? AND submitTime>?  AND isBefore=? AND isEnd='N'",userId,doctorId,doctorTime,type);
		if(list.isEmpty()==true){
			return 0;
		}
		return (long)list.get(0);
	}

	@Override
	public UserConsultDoctor getLastUserConsultDoctorSessionByType(long userId,long doctorId) {
		List<UserConsultDoctor> list=getHibernateTemplate().find("from UserConsultDoctor where userId=? and doctorId=? and isBefore='Y'  order by submitTime desc", new Object[]{userId,doctorId});
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	@Override
	public UserConsultDoctor getLastUserConsultDoctorSessionByTypeN(long userId, long doctorId) {
		List<UserConsultDoctor> list=getHibernateTemplate().find("from UserConsultDoctor where userId=? and doctorId=? and isBefore='N' and isEnd='N' order by submitTime desc", new Object[]{userId,doctorId});
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public List<Object[]> getAllOrderNumDoctorList(long userId) {
		List<Object[]> list=getHibernateTemplate().find("SELECT a.doctorId,a.bespokeDate,b.afterServiceTime FROM OrderInfo a , Product b WHERE a.userId=? AND a.orderStatus IN('已评价','已完成') AND a.productId=b.id ",userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public UserConsultDoctor getLastAfterConsult(long userId, long doctorId) {
		List<UserConsultDoctor> list=getHibernateTemplate().find("from UserConsultDoctor where userId=? and doctorId=? and isEnd='N' order by submitTime desc", new Object[]{userId,doctorId});
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<UserConsultDoctor> getConsultListBySomething(Long userId,
			Long doctorId, Long logId, String userType,String isEnd,String isReply,String sortType,Long fdPackageId) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer(" from UserConsultDoctor where 1=1");
		if(userId != null){
			hql.append(" and userId=? ");
			params.add(userId);
		}
		if(doctorId != null){
			hql.append(" and doctorId=? ");
			params.add(doctorId);
		}
		if(logId != null){
			hql.append(" and logId=? ");
			params.add(logId);
		}
		if(StringUtils.isNotBlank(userType)){
			hql.append(" and userType=? ");
			params.add(userType);
		}
		if(StringUtils.isNotBlank(isEnd)){
			hql.append(" and isEnd=? ");
			params.add(isEnd);
		}
		if(StringUtils.isNotBlank(isReply)){
			hql.append(" and isReply=? ");
			params.add(isReply);
		}
		if(fdPackageId != null){
			hql.append(" and fdPackageId=? ");
			params.add(fdPackageId);
		}
		hql.append(" order by submitTime "+ sortType);
		List<UserConsultDoctor> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public List<CommonDisease> getCommonDiseaseListByLogId(Long logId) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer("select p.commonDisease from ConsultIllRecord p where 1=1");
		if(logId != null){
			hql.append(" and p.logId=? ");
			params.add(logId);
		}
		hql.append(" order by p.createTime ");
		List<CommonDisease> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public List<DoctorWorkTime> getDoctorWorkTimeList(
			DoctorWorkTime doctorWorkTime,DoctorInfo doctorInfo) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from DoctorWorkTime p where 1=1 and p.isEnable='Y' ");
		if(doctorInfo != null){
			if(doctorInfo.getId() != null){
				params.add(doctorInfo.getId());
				hql.append(" and p.doctorInfo.id = ?");
			}
		}
		List<DoctorWorkTime> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}
	
}
