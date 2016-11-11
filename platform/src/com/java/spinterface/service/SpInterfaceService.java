package com.java.spinterface.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.Service;
import com.java.spinterface.vo.SpAppointmentSchedule;
import com.java.spinterface.vo.SpDoctorInfo;

public interface SpInterfaceService extends Service{
	/**  获取苏坡挂号排班信息  */
	public List<SpAppointmentSchedule> saveSpAppointmentSchedules();
	/**  获取苏坡医生信息  */
	public List<SpDoctorInfo> saveSPDoctorInfos();
	
	
	//-------------------------------------------------------------------
	/**
	 * 苏坡社区卫生服务中心
	 */
	final String SP_HOSPITAL_CODE = "3d715fb3-5fd4-4f36-9be9-7cca29de01ca";
	/**
	 * 成都市青羊区第九人民医院
	 */
	final String NINTH_HOSPITAL_CODE = "1cc69208-1d1d-43f8-925b-39fc437e320d";
	
	/**
	 * 操作人员：康优宝贝
	 */
	final String KYBABY_NAME = "康优宝贝";
	/**
	 * 操作人员ID：康优宝贝ID
	 */
	final String KYBABY_ID = "0c432843-0613-4869-b18a-d8718e818c7f";
	/**
	 * 儿保
	 */
	public final String CHILD_CARE = "1";
	/**
	 * 儿科
	 */
	public final String PEADIATRICS = "2";
	/**   查询苏坡挂号信息   */
	public List<SpAppointmentSchedule> getRegisterSchedules(
			PageSortModel model, SpAppointmentSchedule spAppointmentSchedule);
	
	/**
	 * 获取苏坡所有科室名字
	 */
	public List<String> getdeptNames();
	
	/**
	 * 获取所有医生名字
	 */
	public List<String> getNameOfDoctorInfos();
}
