package com.kybaby.bo.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.kybaby.bo.UserConsultDoctorBo;
import com.kybaby.dao.UserConsultDoctorDao;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserConsultDoctor;
import com.kybaby.newbussiness.commondiseaseanddrug.domain.CommonDisease;
import com.kybaby.newbussiness.familydoctor.domain.DoctorWorkTime;

/**
 * @ClassName:UserConsultDoctorBoImpl
 * @Description:用户咨询医生事物管理接口实现
 * @author Hoolee
 * @date 2015年10月12日下午2:17:46
 */
public class UserConsultDoctorBoImpl implements UserConsultDoctorBo {

	UserConsultDoctorDao userConsultDoctorDao;
	
	public boolean isConsultationEnd(long userId, long doctorId) {
		UserConsultDoctor cosult=userConsultDoctorDao.getLastUserConsultDoctor(userId, doctorId);
		if(cosult!=null){//存在最后一条咨询
			String isEnd=cosult.getIsEnd();
			if(isEnd.equals("Y")){
				return false;
			}
			return true;
		}
		return false;
	}

	public long lastConsultationLogId(long userId, long doctorId) {
		UserConsultDoctor cosult=userConsultDoctorDao.lastConsultationLogId(userId, doctorId);
		if(cosult!=null){
			return cosult.getLogId();
		}
		return 0;
	}

	public List<UserConsultDoctor> lastConsultationLog(long userId,long doctorId, long logId) {
		return userConsultDoctorDao.lastConsultationLog(userId, doctorId, logId);
	}

	public void addNewUserConsultDoctor(long userId, long doctorId,
			String symptomTagIds, String symptomImage, String symptomDescribe,
			String msgType, long logId,String isBefore,String userType,Long fdPackageId) {
		UserConsultDoctor consult=new UserConsultDoctor();
		consult.setUserId(userId);
		consult.setDoctorId(doctorId);
		consult.setSymptomTagIds(symptomTagIds);
		consult.setSymptomImage(symptomImage);
		consult.setSymptomDescribe(symptomDescribe);
		consult.setMsgType(msgType);
		consult.setLogId(logId);
		consult.setIsBefore(isBefore);
		consult.setDoctorReply("");
		consult.setUserType(userType);
		consult.setFdPackageId(fdPackageId);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date rightNow=new Date(System.currentTimeMillis());
		String submitTime=sdf.format(rightNow);
		
		consult.setSubmitTime(submitTime);
		consult.setIsEnd("N");
		consult.setIsReply("N");
		userConsultDoctorDao.addNewConsult(consult);
	}

	public void addEndFlag(long userId, long doctorId, long logId, String isEnd) {
		
	}
	//modify by sjt
	public List<Long> getHistoryDoctorIdList(long userId) {
		return userConsultDoctorDao.getHistoryDoctorIdList(userId);
	}

	public List<UserConsultDoctor> getAllConsultHist(long userId, long doctorId) {
		return null;
	}
	
	public UserConsultDoctor getLastUserConsultDoctor(long userId, long doctorId) {
		return userConsultDoctorDao.getLastUserConsultDoctor(userId, doctorId);
	}
	
	public UserConsultDoctor getLastUserConsultDoctorSession(long userId, long doctorId) {
		return userConsultDoctorDao.getLastUserConsultDoctorSession(userId, doctorId);
	}

	public UserConsultDoctor getLastUserAfterConsultDoctor(long userId,long doctorId) {
		return userConsultDoctorDao.getLastUserAfterConsultDoctor(userId, doctorId);
	}

	public List<UserConsultDoctor> lastAfterConsultationLog(long userId,long doctorId, long logId) {
		return userConsultDoctorDao.lastAfterConsultationLog(userId, doctorId, logId);
	}

	public List<UserConsultDoctor> lastConsultationLogAfterSomeId(long userId,long doctorId, long logId, long consutId) {
		return userConsultDoctorDao.lastConsultationLogAfterSomeId(userId, doctorId, logId, consutId);
	}

	public List<UserConsultDoctor> lastAfterConsultationLogAfterSomeId(long userId, long doctorId, long logId, long consultId) {
		return userConsultDoctorDao.lastAfterConsultationLogAfterSomeId(userId, doctorId, logId, consultId);
	}

	public void updateSomeConsult(UserConsultDoctor consult) {
		userConsultDoctorDao.updateSomeConsult(consult);
	}

	public UserConsultDoctorDao getUserConsultDoctorDao() {
		return userConsultDoctorDao;
	}

	public void setUserConsultDoctorDao(UserConsultDoctorDao userConsultDoctorDao) {
		this.userConsultDoctorDao = userConsultDoctorDao;
	}

	//add by sjt
	public Object getSomeUserConsultDoctor(Long userId, Long doctorId) {
		return userConsultDoctorDao.getSomeUserConsultDoctor(userId, doctorId);
	}

	public long countNewMessage(Long userId, Long doctorId, String time) {
		return userConsultDoctorDao.countNewMessage(userId, doctorId, time);
	}

	public String getLastDoctorTime(Long userId, Long doctorId) {
		return userConsultDoctorDao.getLastDoctorTime(userId, doctorId);
	}

	public List getDoctorNameAndImage(Long doctorId) {
		return userConsultDoctorDao.getDoctorNameAndImage(doctorId);
	}

	public List getSomeUserConsultDoctor(Long logId) {
		return userConsultDoctorDao.getSomeUserConsultDoctor(logId);
	}

	public String getSymptomTagNameById(Long id) {
		return userConsultDoctorDao.getSymptomTagNameById(id);
	}

	public String getUserImgByUserId(Long usId) {
		return userConsultDoctorDao.getUserImgByUserId(usId);
	}

	public void addNewUserConsult(long userId, long doctorId,
			String symptomTagIds, String symptomImage, String symptomDescribe,
			String msgType, String isBefore,String userType,Long fdPackageId) {
		UserConsultDoctor consult=new UserConsultDoctor();
		consult.setUserId(userId);
		consult.setDoctorId(doctorId);
		consult.setSymptomTagIds(symptomTagIds);
		consult.setSymptomImage(symptomImage);
		consult.setSymptomDescribe(symptomDescribe);
		consult.setMsgType(msgType);
		consult.setIsBefore(isBefore);
		consult.setDoctorReply("");
		consult.setUserType(userType);
		consult.setFdPackageId(fdPackageId);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date rightNow=new Date(System.currentTimeMillis());
		String submitTime=sdf.format(rightNow);
		
		consult.setSubmitTime(submitTime);
		consult.setIsEnd("N");
		consult.setIsReply("N");
		userConsultDoctorDao.addNewConsult(consult);
		//上面部分向数据表中插入了一条新的记录
		
		UserConsultDoctor newConsult=userConsultDoctorDao.getSomeTimeUserConsultDoctor(userId, doctorId, submitTime);
		if(newConsult!=null){
			newConsult.setLogId(newConsult.getId());
			userConsultDoctorDao.updateSomeConsult(newConsult);
		}
	}

	@Override
	public List getDoctorIdAndNameAndImage(Long doctorId) {
		// TODO Auto-generated method stub
		return userConsultDoctorDao.getDoctorIdAndNameAndImage(doctorId);
	}



	@Override
	public String getLastDoctorTimeReplace(Long userId, Long doctorId) {
		// TODO Auto-generated method stub
		return userConsultDoctorDao.getLastDoctorTimeReplace(userId, doctorId);
	}

	@Override
	public long countNewMessageReplace(Long userId, Long doctorId, String time) {
		// TODO Auto-generated method stub
		return userConsultDoctorDao.countNewMessageReplace(userId, doctorId, time);
	}

	@Override
	public List getAllLastMsgInfo(Long userId) {
		// TODO Auto-generated method stub
		return userConsultDoctorDao.getAllLastMsgInfo(userId);
	}

	@Override
	public String getLastDoctorTimeByType(Long userId, Long doctorId,String type) {
		// TODO Auto-generated method stub
		return userConsultDoctorDao.getLastDoctorTimeByType(userId, doctorId, type);
	}

	@Override
	public long countNewMessageByType(Long userId, Long long1,
			String doctorTime, String type) {
		// TODO Auto-generated method stub
		return userConsultDoctorDao.countNewMessageByType(userId, long1, doctorTime, type);
	}

	@Override
	public UserConsultDoctor getLastUserConsultDoctorSessionByType(long userId,long doctorId) {
		// TODO Auto-generated method stub
		return userConsultDoctorDao.getLastUserConsultDoctorSessionByType(userId, doctorId);
	}

	@Override
	public UserConsultDoctor getLastUserConsultDoctorSessionByTypeN(long userId, long doctorId) {
		// TODO Auto-generated method stub
		return userConsultDoctorDao.getLastUserConsultDoctorSessionByTypeN(userId, doctorId);
	}

	public List<Object[]> getAllOrderNumDoctorList(long userId) {
		return userConsultDoctorDao.getAllOrderNumDoctorList(userId);
	}

	public UserConsultDoctor getLastAfterConsult(long userId, long doctorId) {
		return userConsultDoctorDao.getLastAfterConsult(userId, doctorId);
	}

	@Override
	public List<UserConsultDoctor> getConsultListBySomething(Long userId,
			Long doctorId, Long logId, String userType, String isEnd,String isReply,String sortType,Long fdPackageId) {
		return userConsultDoctorDao.getConsultListBySomething(userId, doctorId, logId, userType, isEnd, isReply, sortType,fdPackageId);
	}

	@Override
	public List<CommonDisease> getCommonDiseaseListByLogId(Long logId) {
		return userConsultDoctorDao.getCommonDiseaseListByLogId(logId);
	}

	@Override
	public List<DoctorWorkTime> getDoctorWorkTimeList(
			DoctorWorkTime doctorWorkTime,DoctorInfo doctorInfo) {
		return userConsultDoctorDao.getDoctorWorkTimeList(doctorWorkTime, doctorInfo);
	}


}
