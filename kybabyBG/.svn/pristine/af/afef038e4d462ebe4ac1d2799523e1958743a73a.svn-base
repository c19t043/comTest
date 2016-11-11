package com.kybaby.newbussiness.medicalorgandbusiness.bo;

import java.util.List;

import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;

/**
 * 机构业务订单管理（计免和儿保）
 * @author lihao
 *
 */
public interface OrganOrderService {
	/**
	 * 儿保订单列表
	 * @param userChildcareAppointmentInfo
	 * @return
	 */
	List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList(OrganChildcareOpenResources organChildcareOpenResources);
	/**
	 * 计免订单列表
	 * @param userInoculationAppointmentInfo
	 * @return
	 */
	List<UserInoculationAppointmentInfo> getUserInoculationAppointmentInfoList(OrganInoculationOpenResources organInoculationOpenResources);
	/**
	 * 计免儿保定时发短信
	 */
	void organBussinessPromptTask();
}
