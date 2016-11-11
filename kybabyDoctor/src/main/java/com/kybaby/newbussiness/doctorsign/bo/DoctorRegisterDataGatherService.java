package com.kybaby.newbussiness.doctorsign.bo;

import java.util.List;

import com.kybaby.common.CommonService;
import com.kybaby.domain.DoctorGoodField;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Position;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorsign.domain.DoctorCardInfo;
import com.kybaby.newbussiness.doctorsign.domain.DoctorLifeInfo;
import com.kybaby.newbussiness.doctorsign.domain.DoctorMajor;
import com.kybaby.newbussiness.doctorsign.domain.DoctorOrderSummary;
import com.kybaby.newbussiness.doctorsign.domain.DoctorSignApprovalFlowRecord;

public interface DoctorRegisterDataGatherService extends CommonService{
	
	/**
	 * 添加or修改医生
	 * @param doctorInfo
	 * @return
	 */
	DoctorInfo saveOrUpdateDoctorInfo(DoctorInfo doctorInfo);
	/*
	 * 医生身份证明,CRU
	 */
	List<DoctorCardInfo> getDoctorCardInfos(DoctorCardInfo doctorCardInfo);
	DoctorCardInfo saveOrUpdateDoctorCardInfo(DoctorCardInfo doctorCardInfo);
	/*
	 * 医生生活信息,CRU
	 */
	List<DoctorLifeInfo> getDoctorLifeInfos(DoctorLifeInfo doctorLifeInfo);
	DoctorLifeInfo saveOrUpdateDoctorLifeInfo(DoctorLifeInfo doctorLifeInfo);
	/*
	 * 医生认证信息审核,CRU
	 */
	/**
	 * 获取最新审批流程
	 * @param doctorID 医生ID
	 */
	DoctorSignApprovalFlowRecord getLaterFlowRecord(Long doctorID);
	DoctorSignApprovalFlowRecord saveOrUpdateDoctorSignApprovalFlowRecord(DoctorSignApprovalFlowRecord doctorSignApprovalFlowRecord);
	/**
	 * 根据条件查询医院信息
	 * @hospitalBasicInfo 
	 */
	List<HospitalBasicInfo> getHospitalBasicInfos(HospitalBasicInfo hospitalBasicInfo);
	/**
	 * 根据条件查询职称
	 * @position 职称对象
	 */
	List<Position> getPositions(Position position);
	/**
	 * 根据条件查询医生主专业
	 * @major 专业对象
	 * @param professionalFlag 专业标识(主专业first;亚专业second;病种third)
	 */
	List<DoctorMajor> getMajors(DoctorMajor major,String professionFlag);
	/**
	 * 查询不同专业签约的人
	 * @doctorInfo 查询条件
	 * @professionalFlag ("医生","护士","技师")
	 */
	/*List<DoctorInfo> getDoctorinfos(DoctorInfo doctorInfo,String professionFlag);*/
	/**
	 * 查询当前用户签约的医生数据
	 * @param logonUserID 登陆人ID
	 * @return
	 */
	List<DoctorInfo> getMySignDoctorInfos(Long logonUserID);
	/**
	 * 获取开通服务
	 */
	List<DoctorServiceType> getAllDoctorServiceTypes();
	/**
	 * 查询对应医生的身份证明
	 * @doctorID 医生ID
	 */
	List<DoctorCardInfo> getDoctorCardInfos(Long doctorID);
	/**
	 * 查询医生生活信息
	 * @param doctorID 医生id
	 */
	List<DoctorLifeInfo> getDoctorLifeInfos(Long doctorID);
	/**
	 * 查询擅长领域
	 * @professionFlag 专业标识("医生","护士","技师")
	 */
	List<DoctorGoodField> getDoctorGoodFields(String professionFlag);
	/**
	 * 删除资格证书记录
	 * @id 资格证书记录id
	 */
	void deleteDoctorCardInfo(Long id);
	/**
	 * 查询医生接单记录
	 * @logonUserID 登陆人ID
	 */
	List<DoctorOrderSummary> getDoctorOrderSummarys(Long logonUserID);
	/**
	 * 检查医生电话是否重复
	 * @param doctorPhone
	 * @return
	 */
	boolean checkDoctorPhone(String doctorPhone);
	/**
	 * 获取当前操作人有关联的医生数据
	 * @logonUserID 登陆人ID
	 */
	List<DoctorInfo> queryViewableDoctorInfos(Long logonUserID);
	/**
	 * 获取当前操作人可以维护的医生信息
	 * @logonUserID 登陆人ID
	 */
	List<DoctorInfo> queryMaintenanceAbleDoctorInfos(Long logonUserID);
}
