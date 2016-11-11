package com.kybaby.newbussiness.medicalorgandbusiness.fo;

import java.util.List;

import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;

public class UserInoculationAppointmentInfoFo {
	private String openDate;
	private String openStartTime;
	private String openEndTime;
	/**
	 * 计免预约列表
	 */
	private List<UserInoculationAppointmentInfo> userInoculationAppointmentInfoList;
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getOpenStartTime() {
		return openStartTime;
	}
	public void setOpenStartTime(String openStartTime) {
		this.openStartTime = openStartTime;
	}
	public String getOpenEndTime() {
		return openEndTime;
	}
	public void setOpenEndTime(String openEndTime) {
		this.openEndTime = openEndTime;
	}
	public List<UserInoculationAppointmentInfo> getUserInoculationAppointmentInfoList() {
		return userInoculationAppointmentInfoList;
	}
	public void setUserInoculationAppointmentInfoList(
			List<UserInoculationAppointmentInfo> userInoculationAppointmentInfoList) {
		this.userInoculationAppointmentInfoList = userInoculationAppointmentInfoList;
	}
}
