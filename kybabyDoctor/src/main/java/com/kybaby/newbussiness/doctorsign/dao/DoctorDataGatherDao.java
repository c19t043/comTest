package com.kybaby.newbussiness.doctorsign.dao;

import java.util.List;

import com.kybaby.common.CommonDao;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.RoleSelect;
import com.kybaby.newbussiness.doctorsign.domain.DoctorOrderSummary;
import com.kybaby.newbussiness.doctorsign.domain.DoctorRegisterMaintenance;
import com.kybaby.newbussiness.doctorsign.domain.DoctorSignApprovalFlowRecord;

public interface DoctorDataGatherDao extends CommonDao{
	/**
	 * 根据医生ID删除服务类容
	 * @param dctID
	 */
	void deleteDoctorServiceContent(Long dctID);
	/**
	 * 根据医生id串获取医生订单汇总数据
	 * @param ids
	 */
	List<DoctorOrderSummary> getDoctorOrderGather(String ids);
	/**
	 * 获取最新审批流程
	 * @param doctorID 医生ID
	 */
	DoctorSignApprovalFlowRecord getLaterFlowRecord(Long doctorID);
	/**
	 * 获取当前操作人签约的医生信息
	 * @param myUserID 当前用户ID
	 */
	List<DoctorInfo> getMySignDoctorInfos(Long myUserID);
	/**
	 * 查询当前用户被分配维护的医生信息ID
	 * @param myUserID 当前用户ID
	 */
	List<DoctorRegisterMaintenance> getDistribute2MeOfDoctorInfos(Long myUserID);
	/**
	 * 根据医生ID串获取医生信息
	 * @param DoctorIDs 医生ID串
	 */
	List<DoctorInfo> getDoctorInfosByIDs(String DoctorIDs);
	/**
	 * 查询对应注册医生(相应专业)的所有电话
	 * @param professionalFlag 专业类型（ 医生  ，护士   ，技师）
	 */
	List<RoleSelect> getRoleSelects(String professionalFlag);
	/**
	 * 根据医生电话串获取医生信息
	 * @param DoctorIDs 医生电话串
	 */
	List<DoctorInfo> getDoctorInfosByPhone(String phones);
}
