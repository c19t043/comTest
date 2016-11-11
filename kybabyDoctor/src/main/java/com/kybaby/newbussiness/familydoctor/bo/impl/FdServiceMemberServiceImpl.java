package com.kybaby.newbussiness.familydoctor.bo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.familydoctor.bo.FdServiceMemberService;
import com.kybaby.newbussiness.familydoctor.dao.FdServiceMemberDao;
import com.kybaby.newbussiness.familydoctor.domain.ConsultDoctorInfo;
import com.kybaby.newbussiness.familydoctor.domain.ConsultOrderInfo;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceMember;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.familydoctor.domain.FdUserBuyRecord;
import com.kybaby.newbussiness.familydoctor.domain.OpenClinicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;

public class FdServiceMemberServiceImpl implements FdServiceMemberService{

	
	/**
	 * 注入成员的dao层
	 */
	private FdServiceMemberDao fdServiceMemberDao;
	
	public void setFdServiceMemberDao(FdServiceMemberDao fdServiceMemberDao) {
		this.fdServiceMemberDao = fdServiceMemberDao;
	}
	
	@Override
	public List<FdServicePackage> getFdServiceMemberList(DoctorInfo doctorInfo) {
		
		return fdServiceMemberDao.getFdServiceMemberList(doctorInfo);
	}

	@Override
	public List<UserInfo> getUserInfoList(FdServicePackage fdServicePackage) {
		List<FdUserBuyRecord> fdUserBuyRecordList = fdServiceMemberDao.getUserInfoList(fdServicePackage);
		//把过期的用户放入集合
		List<UserInfo> userInfolist = new ArrayList<>();
		//把user里面的重复出掉，然后放入集合，在返回
		List<UserInfo> list = new ArrayList<>();
		if(fdUserBuyRecordList!=null){
			for (FdUserBuyRecord fdUserBuyRecord : fdUserBuyRecordList) {
				UserInfo userInfo = fdUserBuyRecord.getUserInfo();
				String serviceEndTime = fdUserBuyRecord.getServiceEndTime();
				Date strToDateTime = DateManage.getStrToDateTime(serviceEndTime+" 00:00:00");
				if(DateManage.isCompareDates(strToDateTime,new Date()) ){
					userInfo.setFdServicePast("false");
				}
				userInfolist.add(userInfo);
			}
		}
		//去重复
		if(userInfolist != null){
			for (UserInfo userInfo : userInfolist) {
				if(Collections.frequency(list, userInfo) < 1) list.add(userInfo);
			}
		}
		return list;
	}

	@Override
	public List<OpenClinicInfo> getOpenClinicInfoList(DoctorInfo doctorInfo,OpenClinicInfo openClinicInfo) {
		List<OpenClinicInfo> openClinicInfoList = fdServiceMemberDao.getOpenClinicInfoList(openClinicInfo);
		List<OpenClinicInfo> list = new ArrayList<>(); 
		if(list == null){
			return null;
		}
		for (OpenClinicInfo openClinicInfo1 : openClinicInfoList) {
			Set<DoctorInfo> doctorSet = openClinicInfo1.getDoctorSet();
			//迭代set集合
			Iterator it = doctorSet.iterator();
			while (it.hasNext()) {
				DoctorInfo next = (DoctorInfo) it.next();
				if(next.getId().longValue() == doctorInfo.getId().longValue()){
					list.add(openClinicInfo1); 
				}
			}
		}
		return list;
	}

	@Override
	public void updateOpenClinicInfoState(OpenClinicInfo openClinicInfo) {
		
		fdServiceMemberDao.updateOpenClinicInfoState(openClinicInfo);
	}

	@Override
	public OpenClinicInfo getOpenClinicInfoById(Long id) {
		
		return fdServiceMemberDao.getOpenClinicInfoById(id);
	}

	@Override
	public List<UserChildcareAppointmentInfo> getChildcareOrderList(
			DoctorInfo doctorInfo) {
		
		return fdServiceMemberDao.getChildcareOrderList(doctorInfo);
	}

	@Override
	public ConsultDoctorInfo getConsultDoctorInfoById(Long consultDoctorId,
			Long doctorId) {
		return fdServiceMemberDao.getConsultDoctorInfoById(consultDoctorId, doctorId);
	}

	@Override
	public ConsultOrderInfo getConsultOrderInfoById(Long id) {
		return fdServiceMemberDao.getConsultOrderInfoById(id);
	}

	@Override
	public void saveOrUpdateConsultOrderInfo(ConsultOrderInfo consultOrderInfo) {
		fdServiceMemberDao.saveOrUpdateConsultOrderInfo(consultOrderInfo);
	}

	@Override
	public List<FdServiceMember> getAllFdServiceMember(DoctorInfo doctorInfo) {
		return fdServiceMemberDao.getAllFdServiceMember(doctorInfo);
	}
}
