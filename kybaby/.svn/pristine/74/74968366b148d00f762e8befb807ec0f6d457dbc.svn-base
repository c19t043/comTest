package com.kybaby.newbussiness.spservice.dao;

import java.util.List;

import com.kybaby.common.CommonDao;
import com.kybaby.newbussiness.spservice.domain.SpAppointmentSchedule;
import com.kybaby.newbussiness.spservice.domain.SpCheckRecord;
import com.kybaby.newbussiness.spservice.domain.SpCheckReport;
import com.kybaby.newbussiness.spservice.domain.SpCostInfo;
import com.kybaby.newbussiness.spservice.domain.SpDoctorAdviceInfo;
import com.kybaby.newbussiness.spservice.domain.SpDoctorAlias;
import com.kybaby.newbussiness.spservice.domain.SpDoctorInfo;
import com.kybaby.newbussiness.spservice.domain.SpHealthcardManager;
import com.kybaby.newbussiness.spservice.domain.SpInspectInfo;
import com.kybaby.newbussiness.spservice.domain.SpInspectRecord;
import com.kybaby.newbussiness.spservice.domain.SpRegisterOrderDetail;
import com.kybaby.newbussiness.spservice.domain.SpVisitRecord;

public interface SpInterfaceDao extends CommonDao{
	
	/**
	 * 根据用户ID获取特定对象集合
	 * @param userID
	 * @param clazz
	 * @param spOrgID 苏坡机构ID
	 * @return
	 */
	public <T> List<T> getObjectByUserID(Long userID,Class<T> clazz,String spOrgID);
	/**
	 * 获取苏坡医生数据
	 * @param orgID 苏坡机构ID
	 * @param deptName 部门名称
	 * @param doctorName 医生名字
	 * @return
	 */
	public SpDoctorInfo getSpDoctorInfo(String orgID,String deptName,String doctorName);
	/**
	 * 获取挂号排班信息
	 * @param spOrgID 苏坡机构ID
	 * @param spDeptName 部门名称
	 * <!-- @param spDoctorID 医生ID -->
	 * @param spDoctorName 医生名字
	 * @return
	 */
	public SpAppointmentSchedule getRegisterScheduleInfo(String spOrgID,String spDeptName,String spDoctorName);
	/**
	 * 保存or更新医嘱信息
	 * @param doctorAdviseInfos
	 * @param pageNo
	 * @return
	 */
	public List<SpDoctorAdviceInfo> saveOrUpdateDoctorAdviseInfo(List<SpDoctorAdviceInfo> doctorAdviseInfos
			,Long userID,Integer pageNo,String opTime);
	/**  获取用户的挂号订单信息  */
	public List<SpRegisterOrderDetail> getSpRegisterOrderDetails(Long userID);
	/**
	 * 保存or更新费用信息
	 * @param spCostInfos
	 * @param pageNo
	 * @return
	 */
	public List<SpCostInfo> saveOrUpdateCostInfo(List<SpCostInfo> spCostInfos,Long userID,Integer pageNo,String opTime);
	/**  获取用户绑定的所有健康卡号信息  */
	public List<SpHealthcardManager> getHealthCardOfUserOwneds(Long userID);
	/**
	 * 保存or更新就诊记录
	 * @param spVisitRecords
	 * @param pageNo 页号
	 * @param userID
	 * @return
	 */
	public List<SpVisitRecord> saveOrUpdateSpVisitRecords(List<SpVisitRecord> spVisitRecords,Long userID,Integer pageNo);
	/**  通过就诊ID，获取挂号记录明细  */
	public SpRegisterOrderDetail getSPSpRegisterOrderDetail(String eventID);
	/**  通过就诊ID，获取就诊记录明细  */
	public SpVisitRecord getSpVisitRecord(String eventID);
	/**
	 * 保存or更新检验记录 
	 * @param spInspectRecords
	 * @param pageNo
	 * @param userID
	 * @return
	 */
	public List<SpInspectRecord> saveOrUpdateSpInspectRecords(List<SpInspectRecord> spInspectRecords,Long userID,Integer pageNo);
	/**  保存or更新检验结果 */
	public List<SpInspectInfo> saveOrUpdateSpInspectInfos(List<SpInspectInfo> spInspectInfos,SpInspectRecord inspectRecord);
	/**
	 * 保存or更新检查记录
	 * @param spCheckRecords
	 * @param pageNo
	 * @return
	 */
	public List<SpCheckRecord> saveOrUpdateSpCheckRecords(List<SpCheckRecord> spCheckRecords,Long userID,Integer pageNo);
	/**  保存or更新检查报告 */
	public void saveOrUpdatespInspectReports(List<SpCheckReport> spCheckReports,SpCheckRecord spCheckRecord);
	/**  获取苏坡机构的虚拟医生名字   */
	public SpDoctorAlias getSpDoctorAlias(String orgID);
}
