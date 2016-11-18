package com.java.doctorinfo.service;

import java.util.List;

import com.java.consultmanager.consultdoctormanager.vo.Position;
import com.java.doctorinfo.vo.DoctorCardInfo;
import com.java.doctorinfo.vo.DoctorLifeInfo;
import com.java.doctorinfo.vo.DoctorServiceType;
import com.java.doctorinfo.vo.OrganOperator;
import com.java.doctorinfo.vo.RecommendRule;
import com.java.doctorinfo.vo.RecommentAwardRecord;
import com.java.doctormanager.vo.DoctorMajor;
import com.java.doctormanager.vo.DoctorSignApprovalFlowRecord;
import com.java.ec.common.PageSortModel;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.platform.user.service.Service;

public interface DoctorInfoService extends Service{
	/**
	 * 根据操作人ID批量操作维护人
	 * @param doctorID 医生id
	 * @param ids
	 */
	void saveMaintenanceWithBath(Long doctorID,String ids);
	/**
	 * 获取医生数据维护人
	 * @param doctorID
	 * @return
	 */
	List<OrganOperator> getDoctorRegisterMaintenance(Long doctorID);
	/**
	 * 获取机构操作人
	 * @param model
	 * @param organOperator
	 * @return
	 */
	List<OrganOperator> getOrganOperator(PageSortModel model,OrganOperator organOperator);
	<T> T save(T entity);
	<T> T update(T entity);
	/**
	 * 获取最新审批流程
	 * @param doctorID 医生ID
	 */
	DoctorSignApprovalFlowRecord getLaterFlowRecord(Long doctorID);
	/**
	 * 查询医生生活信息
	 * @param doctorID 医生id
	 */
	List<DoctorLifeInfo> getDoctorLifeInfos(Long doctorID);
	/**
	 * 查询对应医生的身份证明
	 * @doctorID 医生ID
	 */
	List<DoctorCardInfo> getDoctorCardInfos(Long doctorID);
	/**
	 * 根据条件查询医生主专业
	 * @major 专业对象
	 * @param professionalFlag 专业标识(主专业first;亚专业second;病种third)
	 */
	List<DoctorMajor> getMajors(DoctorMajor major,String professionFlag);
	/**
	 * 获取开通服务
	 */
	List<DoctorServiceType> getAllDoctorServiceTypes();
	/**
	 * 根据条件查询医院信息
	 * @hospitalBasicInfo 
	 */
	List<HospitalBasicInfo> getHospitalBasicInfos(HospitalBasicInfo hospitalBasicInfo);
	//获取医生所有职称
	List<Position> getAllPosition();
	DoctorCardInfo saveOrUpdateDoctorCardInfo(DoctorCardInfo doctorCardInfo);
	/**
	 * 删除资格证书记录
	 * @id 资格证书记录id
	 */
	void deleteDoctorCardInfo(Long id);
	/**
	 * 检查医生电话是否重复
	 * @param doctorPhone
	 * @return
	 */
	boolean checkDoctorPhone(String doctorPhone);
	/**
	 * 分页列表
	 * @param psm
	 * @param doctorInfo
	 * @return
	 */
	List<DoctorInfo> getDoctorInfoListByPage(PageSortModel psm,DoctorInfo doctorInfo);
	/**
	 * 添加or修改医生
	 * @param doctorInfo
	 * @return
	 */
	DoctorInfo saveOrUpdateDoctorInfo(DoctorInfo doctorInfo);
	DoctorLifeInfo saveOrUpdateDoctorLifeInfo(DoctorLifeInfo doctorLifeInfo);
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过推荐规则的名称获取到推荐规则的实例
	 * @data: 2015年11月10日14:27:14
	 * @param ruleName 推荐规则名称
	 * @return 推荐规则的实例
	 */
	RecommendRule getSomeCanUseRule(String ruleName);
	//注册专用
	DoctorInfo getDoctorInfoByPhoneNum(String phone);
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加新的奖励记录 
	 * @data: 2015年11月10日下午2:55:24
	 * @param recommentAwardRecord 需要增加的奖励记录实例
	 */
	void saveRecommentAwardRecord(RecommentAwardRecord recommentAwardRecord);
}
