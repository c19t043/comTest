package com.kybaby.newbussiness.medicalorgandbusiness.bo.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.OrganOrderService;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.OrganOrderDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;
import com.kybaby.util.SendSms;

public class OrganOrderServiceImpl implements OrganOrderService{
	private OrganOrderDao organOrderDao;
	/**
	 * 已预约（接种疫苗业务）
	 */
	public static final String HASE_BOOKED_VACCINE="已预约";
	/**
	 * 已取消（接种疫苗业务）
	 */
	public static final String HASE_CANCEL_VACCINE="已取消";
	/**
	 * 已预检（接种疫苗业务）
	 */
	public static final String HASE_PREVIEW_VACCINE="已预检";
	/**
	 * 已登记（接种疫苗业务）
	 */
	public static final String HASE_REGISTER_VACCINE="已登记";
	public OrganOrderDao getOrganOrderDao() {
		return organOrderDao;
	}

	public void setOrganOrderDao(OrganOrderDao organOrderDao) {
		this.organOrderDao = organOrderDao;
	}

	@Override
	public List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList(
			OrganChildcareOpenResources organChildcareOpenResources) {
		return this.organOrderDao.getUserChildcareAppointmentInfoList(organChildcareOpenResources);
	}

	@Override
	public List<UserInoculationAppointmentInfo> getUserInoculationAppointmentInfoList(
			OrganInoculationOpenResources organInoculationOpenResources) {
		return this.organOrderDao.getUserInoculationAppointmentInfoList(organInoculationOpenResources);
	}

	@Override
	public void organBussinessPromptTask() {
		System.out.println("计免儿保定时任务");
		Calendar c = Calendar.getInstance();
		int nowHour = c.get(Calendar.HOUR_OF_DAY);
		//儿保
		OrganChildcareOpenResources organChildcareOpenResources = new OrganChildcareOpenResources();
		organChildcareOpenResources.setOpenDate(DateManage.getDateStr("yyyy-MM-dd"));
		List<UserChildcareAppointmentInfo> userChildcareAppointmentInfoList = 
				this.organOrderDao.getUserChildcareAppointmentInfoList(organChildcareOpenResources);
		if(userChildcareAppointmentInfoList != null){
			String status = OrderInfoClinic.HASE_MEET_CLINIC_ORDER + "," + OrderInfoClinic.HASE_FINISHED_CLINIC_ORDER;
			SendSms ss = new SendSms();
			for(UserChildcareAppointmentInfo uca : userChildcareAppointmentInfoList){
				if(status.indexOf(uca.getStatus()) > -1){//结束后一小时发短信
					int endTime = 0;
					if("时间点".equals(uca.getOrganChildcareOpenResources().getTimeDivisionNeed())){
						endTime = Integer.valueOf(uca.getOrganChildcareOpenResourcesDatail().getSegment().split(":")[0]);
					}else if("时间段".equals(uca.getOrganChildcareOpenResources().getTimeDivisionNeed())){
						endTime = Integer.valueOf(uca.getOrganChildcareOpenResourcesDatail().getOpenEndTime().split(":")[0]);
					}
					if(endTime != 0 && (endTime+1) == nowHour){
						UserInfo user = uca.getUserInfo();
						String content = "亲，感谢您在康优专家咨询，如有任何问题可关注“康优宝贝”官方微信在线咨询，祝宝宝健康成长。";
						ss.sendInfo(user.getPhone(), content.toString());
					}
				}
			}
		}
		//计免
		OrganInoculationOpenResources organInoculationOpenResources = new OrganInoculationOpenResources();
		organInoculationOpenResources.setOpenDate(DateManage.getDateStr("yyyy-MM-dd"));
		List<UserInoculationAppointmentInfo> userInoculationAppointmentInfoList =
				this.organOrderDao.getUserInoculationAppointmentInfoList(organInoculationOpenResources);
		if(userInoculationAppointmentInfoList != null){
			String status = HASE_PREVIEW_VACCINE + "," + HASE_REGISTER_VACCINE;
			SendSms ss = new SendSms();
			for(UserInoculationAppointmentInfo uia : userInoculationAppointmentInfoList){
				if(status.indexOf(uia.getStatus()) > -1){//结束后一小时发短信
					int endTime = Integer.valueOf(uia.getOrganInoculationOpenResourcesDetail().getOpenEndTime().split(":")[0]);
					if((endTime+1) == nowHour){
						UserInfo user = uia.getUserInfo();
						String content = "亲，感谢您在康优专家咨询，如有任何问题可关注“康优宝贝”官方微信在线咨询，祝宝宝健康成长。";
						ss.sendInfo(user.getPhone(), content.toString());
					}
				}
			}
		}
	}

}
