package com.java.consultmanager.consultdoctormanager.service;

import java.util.List;

import com.java.consultmanager.consultdoctormanager.vo.ConsultDoctorInfo;
import com.java.ec.common.PageSortModel;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.user.service.Service;

public interface ConsultDoctorInfoService extends Service {
	
	/**
	 * 获取所有医生
	 * @return
	 */
	public List<DoctorInfo> getDoctorInfos();
	
	/**
	 * 添加一条咨询医生信息
	 */
	public boolean addConsultDoctorInfo(ConsultDoctorInfo consultDoctorInfo);
	
	/**
	 * 修改一条咨询医生信息
	 * @param consultDoctorInfo
	 * @return
	 */
	public boolean updateConsultDoctorInfo(ConsultDoctorInfo consultDoctorInfo);
	
	/**
	 * 根据id,获取一条咨询医生信息
	 * @param id
	 * @return
	 */
	public ConsultDoctorInfo getConsultDoctorInfoById(Long id);
	
	/**
	 * 获取所有咨询医生信息
	 * @return
	 */
	public List<ConsultDoctorInfo> getConsultDoctorInfoList(PageSortModel model,ConsultDoctorInfo consultDoctorInfo);
}
