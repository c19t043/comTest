package com.kybaby.newbussiness.familydoctor.bo;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.familydoctor.domain.ConsultDoctorInfo;
import com.kybaby.newbussiness.familydoctor.domain.ConsultOrderInfo;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceMember;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.familydoctor.domain.OpenClinicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;

public interface FdServiceMemberService {

	
	/**
	 * 查询团队成员下面有那些服务包
	 * @param fdServiceMember
	 * @return
	 */
	List<FdServicePackage> getFdServiceMemberList(DoctorInfo doctorInfo);
	/**
	 * 得到医生所在的团队
	 * @param doctorInfo
	 * @return
	 */
	List<FdServiceMember> getAllFdServiceMember(DoctorInfo doctorInfo);
	
	/**
	 * 查询服务包下面的签约用户
	 * @param fdUserBuyRecord
	 * @return
	 */
	List<UserInfo> getUserInfoList(FdServicePackage fdServicePackage);
	
	/**
	 * 查询线上讲座约稿线下讲座的列表
	 * @param doctorInfo
	 * @return
	 */
	List<OpenClinicInfo> getOpenClinicInfoList(DoctorInfo doctorInfo,OpenClinicInfo openClinicInfo);

	/**
	 * 更新 查询线上讲座约稿线下讲座任务状态方法
	 * @param openClinicInfo
	 */
	void updateOpenClinicInfoState(OpenClinicInfo openClinicInfo);
	
	/**
	 * 根据页面上传回来的id查询一条数据
	 * @param id
	 * @return
	 */
	OpenClinicInfo getOpenClinicInfoById(Long id);
	
	/**
	 * 查询儿保订单列表
	 * @param doctorInfo
	 * @return
	 */
	List<UserChildcareAppointmentInfo> getChildcareOrderList(DoctorInfo doctorInfo);
	/**
	 * 得到收费咨询医生信息
	 * @param consultDoctorId
	 * @param doctorId
	 * @return
	 */
	ConsultDoctorInfo getConsultDoctorInfoById(Long consultDoctorId,Long doctorId);
	/**
	 * 得到咨询订单信息
	 * @param id
	 * @return
	 */
	ConsultOrderInfo getConsultOrderInfoById(Long id);
	/**
	 * 保存更新咨询订单信息
	 * @param consultOrderInfo
	 */
	void saveOrUpdateConsultOrderInfo(ConsultOrderInfo consultOrderInfo);
}
