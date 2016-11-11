package com.java.operationmanage.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.archivesinfo.vo.UserType;
import com.java.operationmanage.common.BooleanMsg;
import com.java.operationmanage.vo.Customer;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.operationmanage.vo.OpenClinicInfo;
import com.java.operationmanage.vo.OperaBaseSchedule;
import com.java.operationmanage.vo.OperaBusinessType;
import com.java.operationmanage.vo.OperaDoctorSchedule;
import com.java.operationmanage.vo.OperaWorkerSchedule;
import com.java.platform.user.service.Service;
import com.java.platform.user.vo.User;

public interface OperationmanageService extends Service {
	
	/**
	 * 发布医生排班
	 * @param doctorScheduleID 医生排班ID
	 * @return
	 */
	public BooleanMsg savePublishDoctorSchedule(Long doctorScheduleID);
	/*
	 * 1.排班基本信息,增改查
	 */
	/**
	 * 获取基础排班信息
	 * @param model
	 * @param operaBaseSchedule 基础排班信息
	 * @return
	 */
	public List<OperaBaseSchedule> getOperaBaseSchedules(PageSortModel model,OperaBaseSchedule operaBaseSchedule);
	/**
	 * 判断排班基础信息是否存在
	 * @param operaBaseSchedule
	 * @return
	 */
	BooleanMsg isExistOfBaseSchedule(OperaBaseSchedule operaBaseSchedule);
	/**
	 * 保存or更新基础排班信息
	 * @param operaBaseSchedule 基础排班信息
	 * @return
	 */
	public OperaBaseSchedule saveOrUpdateOperaBaseSchedule(OperaBaseSchedule operaBaseSchedule);
	/*
	 * 2.排班医生信息,增改查
	 */
	/**
	 * 获取医生排班信息
	 * @param baseID 基础排班信息ID
	 * <p>1.如果有基础排班信息ID,获取基础排班信息相关的医生排班信息</p>
	 * <p>2.如果没有有基础排班信息ID,获取所有医生排班信息</p>
	 * @return
	 */
	public List<OperaDoctorSchedule> getOperaDoctorSchedules(Long baseID);
	/**
	 * 保存or更新医生排班信息
	 * @param operaDoctorSchedule 医生排班信息
	 * @return
	 */
	public OperaDoctorSchedule saveOrUpdateOperaDoctorSchedule(OperaDoctorSchedule operaDoctorSchedule);
	/*
	 * 3.排班工作人员信息,增改查
	 */
	/**
	 * 获取医助排班信息
	 * @param baseID 基础排班信息ID
	 * <p>1.如果有基础排班信息ID,获取基础排班信息相关的医助排班信息</p>
	 * <p>2.如果没有有基础排班信息ID,获取所有医助排班信息</p>
	 * @return
	 */
	public List<OperaWorkerSchedule> getOperaWorkerSchedules(Long baseID);
	/**
	 * 保存or更新医助排班信息
	 * @param operaWorkerSchedule 医助排班信息
	 * @return
	 */
	public OperaWorkerSchedule saveOrUpdateOperaWorkerSchedule(OperaWorkerSchedule operaWorkerSchedule);
	/*
	 * 4.排班业务类型信息,增改查
	 */
	public List<OperaBusinessType> getOperaBusinessTypes();
	public OperaBusinessType saveOrUpdateOperaBusinessType(OperaBusinessType operaBusinessType);
	//获取用户类型
	public List<UserType> getUserTypes();
	/**
	 * 导出excel
	 * @param operaBaseSchedule
	 * @return
	 */
	public String exportDoctorSchedule2Excel(OperaBaseSchedule operaBaseSchedule);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//--------------------------------------------------------------------------------------------------
	/***
	 * 开通的业务信息列表，带分页的信息
	 * @param psm
	 * @param openClinicInfo
	 * @return
	 */
	List<OpenClinicInfo> getOpenClinicInfoPageList(PageSortModel psm, OpenClinicInfo openClinicInfo);
	
	/***
	 * 客户信息列表，带分页的信息
	 * @param psm
	 * @param customer
	 * @return
	 */
	List<Customer> getCustomerPageList(PageSortModel psm, Customer customer);
	
	/**
	 * 得到医院列表
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<HospitalBasicInfo> getHospitalBasicInfoList(HospitalBasicInfo hospitalBasicInfo);

	/**
	 * 查询医生列表
	 * @param psm
	 * @param doctorInfo
	 * @return
	 */
	List<DoctorInfo> getDoctorInfoList(PageSortModel psm, DoctorInfo doctorInfo);
	
	/**
	 * 保存开通的业务
	 * @param openClinicInfo
	 * @param doctorInfo
	 * @param amDoctorHelperUser
	 * @param amExtensionUser
	 * @param pmDoctorHelperUser
	 * @param pmExtensionUser
	 * @return
	 */
	String saveOpenClinicInfo(OpenClinicInfo openClinicInfo, DoctorInfo doctorInfo, 
			User amDoctorHelperUser, User amExtensionUser, User pmDoctorHelperUser, User pmExtensionUser);
	
	/**
	 * 保存客户信息
	 * @param customer
	 * @return
	 */
	String saveCustomer(Customer customer);
	
	/**
	 * 更新客户信息
	 * @param customer
	 * @return
	 */
	String updateCustomer(Customer customer);
	
	/**
	 * 编辑保存开通的业务
	 * @param openClinicInfo
	 * @param doctorInfo
	 * @param amDoctorHelperUser
	 * @param amExtensionUser
	 * @param pmDoctorHelperUser
	 * @param pmExtensionUser
	 * @return
	 */
	String updateOpenClinicInfo(OpenClinicInfo openClinicInfo, DoctorInfo doctorInfo, 
			User amDoctorHelperUser, User amExtensionUser, User pmDoctorHelperUser, User pmExtensionUser);
}
