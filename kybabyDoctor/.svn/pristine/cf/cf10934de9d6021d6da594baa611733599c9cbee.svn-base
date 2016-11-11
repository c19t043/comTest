package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.ConsultBo;
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
public class ConsultBoImpl implements ConsultBo{

	private ConsultDao consultDao;

	//获取未结束的咨询记录
	@Override
	public List<Object[]> getConsultByDoctorId(Long id,String isEnd,String userType) {
		return consultDao.getConsultByDoctorId(id, isEnd,userType);
	}

	
	//获取某条咨询的详细内容
	@Override
	public UserConsultDoctor getConsultByConsultId(Long userConsultDoctorId) {
		return consultDao.getConsultByConsultId( userConsultDoctorId);
	}
	
	//获取用户的健康档案记录
	@Override
	public List<HealthRecord> getHealthRecordByUserId() {
		return consultDao.getHealthRecordByUserId();
	}
	
	public ConsultDao getConsultDao() {
		return consultDao;
	}

	public void setConsultDao(ConsultDao consultDao) {
		this.consultDao = consultDao;
	}


	@Override
	public String getLatistTime(Long doctorId, Long userId,String isEnd,String userType) {
		return consultDao.getLatistTime(doctorId, userId, isEnd, userType);
	}

	@Override
	public Long getNewMes(Long doctorId, Long userId,String isEnd,String userType) {
		return consultDao.getNewMes(doctorId, userId, isEnd, userType);
	}

	@Override
	public Long getNewMes(Long doctorId, Long userId, String latistDoctor,String isEnd,String userType) {
		return consultDao.getNewMes(doctorId, userId, latistDoctor, isEnd, userType);
	}


	@Override
	public UserInfo getSomeUserInfoById(Long userId) {
		return consultDao.getSomeUserInfoById(userId);
	}


	@Override
	public UserConsultDoctor getOneConsultByDoctorAndUserId(Long doctorId,
			Long userId, Long logId,String isEnd) {
		return consultDao.getOneConsultByDoctorAndUserId(doctorId, userId, logId, isEnd);
	}


	@Override
	public List<UserConsultDoctor> getSomeUserConsultDoctor(Long logId,String isEnd) {
		return consultDao.getSomeUserConsultDoctor(logId, isEnd);
	}


	@Override
	public String getDoctorImgByDoctorId(Long doctId) {
		return consultDao.getDoctorImgByDoctorId(doctId);
	}


	@Override
	public String getUserImgByUserId(Long usId) {
		UserInfo userInfo = consultDao.getUserImgByUserId(usId);
		if(userInfo==null){
			return null;
		}
		else{
			String userInfoStr = userInfo.getUserImage()+"::"+userInfo.getBabyName()+"::"+userInfo.getSex()+"::"+userInfo.getBirthday()+"::"+userInfo.getParentName();
			return userInfoStr;
		}
		
	}


	@Override
	public String getSymptomTagNameById(Long tagId) {
		return consultDao.getSymptomTagNameById(tagId);
	}


	@Override
	public BabyBasicData getBabyBasicDataByUserId(Long id) {
		return consultDao.getBabyBasicDataByUserId(id);
	}


	@Override
	public UserConsultDoctor getUserConsultDoctorByLogId(Long logId) {
		return consultDao.getUserConsultDoctorByLogId(logId);
	}


	@Override
	public Long save(UserConsultDoctor userConsultDoctor) {
		return consultDao.save(userConsultDoctor);
	}

	@Override
	public List<UserConsultDoctor> getAllConsulation(long doctorId, long logId,String msgType) {
		return consultDao.getAllConsulation(doctorId, logId, msgType);
	}

	@Override
	public void updateSomeConsultById(UserConsultDoctor consult) {
		if(consult!=null){
			consult.setIsEnd("Y");
			consultDao.updateSomeConsultById(consult);
		}
	}
	@Override
	public void updateUserConsultDoctor(UserConsultDoctor consult){
		consultDao.updateSomeConsultById(consult);
	}

	@Override
	public UserConsultDoctor getLastUserConsultDoctorSessionByType(long userId,
			long doctorId,String isEnd) {
		return consultDao.getLastUserConsultDoctorSessionByType(userId, doctorId, isEnd);
	}


	@Override
	public ConsultBabyInfo getConsultBabyInfoByLogId(Long logId) {
		return consultDao.getConsultBabyInfoByLogId(logId);
	}


	@Override
	public List<CommonDisease> getAllCommonDisease() {
		return consultDao.getAllCommonDisease();
	}


	@Override
	public void addConsultIllRecord(ConsultIllRecord consultIllRecord) {
		consultDao.addConsultIllRecord(consultIllRecord);
	}


	@Override
	public List<ConsultIllRecord> getConsultIllRecordList(Long logId) {
		return consultDao.getConsultIllRecordList(logId);
	}


	@Override
	public List<ConsultFastReplay> getConsultFastReplayList(
			DoctorInfo doctorInfo) {
		return consultDao.getConsultFastReplayList(doctorInfo);
	}


	@Override
	public void saveOrUpdateConsultFastReplay(
			ConsultFastReplay consultFastReplay) {
		consultDao.saveOrUpdateConsultFastReplay(consultFastReplay);
	}


	@Override
	public void saveOrUpdateConsultDoctorInfo(
			ConsultDoctorInfo consultDoctorInfo) {
		consultDao.saveOrUpdateConsultDoctorInfo(consultDoctorInfo);
	}


	@Override
	public List<ConsultDoctorInfo> getConsultDoctorInfoList(
			DoctorInfo doctorInfo) {
		return consultDao.getConsultDoctorInfoList(doctorInfo);
	}


	@Override
	public ConsultDoctorInfo getConsultDoctorInfoById(Long id) {
		return consultDao.getConsultDoctorInfoById(id);
	}

}
