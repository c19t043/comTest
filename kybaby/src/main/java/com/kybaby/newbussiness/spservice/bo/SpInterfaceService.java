package com.kybaby.newbussiness.spservice.bo;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.spservice.common.BooleanMsg;
import com.kybaby.newbussiness.spservice.domain.SpCheckRecord;
import com.kybaby.newbussiness.spservice.domain.SpCostInfo;
import com.kybaby.newbussiness.spservice.domain.SpDoctorAdviceInfo;
import com.kybaby.newbussiness.spservice.domain.SpHealthcardManager;
import com.kybaby.newbussiness.spservice.domain.SpInspectRecord;
import com.kybaby.newbussiness.spservice.domain.SpRegisterOrderDetail;
import com.kybaby.newbussiness.spservice.domain.SpVisitRecord;

public interface SpInterfaceService {
	/**
	 * 通过身份证号查询用户信息,并如果有用户信息,则保存
	 * @param visitCardInfo 查询信息 
	 * @return
	 */
	public BooleanMsg registerWithNoCard(SpHealthcardManager visitCardInfo);
	/**
	 * 获取用户绑定的所有健康卡号信息
	 * @param userID 用户ID
	 * @return 
	 * <p>1.如果返回的集合为空,用户没有绑定就诊卡</p>
	 */
	public List<SpHealthcardManager> getHealthCardOfUserOwneds(Long userID);
	/**
	 * 绑定用户健康卡号 
	 * @param userID 用户ID
	 * @param visitCardInfo 就诊卡信息
	 * @param spOrgID 苏坡机构ID
	 * @param visitCardPrefix 就诊卡前缀
	 * @return 成功true,失败false
	 */
	public BooleanMsg saveHealthCard(Long userID,SpHealthcardManager visitCardInfo);
	/**
	 * 修改就诊卡
	 * <p>1.传入就诊卡信息,是修改对应就诊卡信息</p>
	 * <p>2.不传入就诊卡信息,是修改用户默认就诊卡信息</p>
	 * @param userID 用户ID
	 * @param visitCardInfo	就诊卡信息
	 * @return 成功true,失败false
	 */
	public boolean updateHealthCardOfUserUsed(Long userID,SpHealthcardManager visitCardInfo);
	/**
	 * 判断是否有号源
	 * @param orgID 苏坡机构ID
	 * @param deptName 苏坡部门名称
	 * @param doctorName 医生名称
	 * @return
	 */
	public boolean hasSourseWithRegister(String orgID,String deptName,String doctorName);
	/**
	 * 保存苏坡挂号订单明细数据
	 * @param id 订单id
	 * @param deptFlag 部门标识：1为儿保2为儿科
	 * @param spOrgID 苏坡机构ID
	 * @return
	 */
	public BooleanMsg saveRegisterOrderData(Long id,String deptFlag,String spOrgID);
	/**
	 * 创建挂号记录
	 * <P>1.无卡挂号:不传入就诊卡ID</P>
	 * <P>1.有卡挂号:传入visitCardInfo中的个人ID,和用户名字</P>
	 * @param orderID 订单ID
	 * @param deptFlag 科室标识
	 * @param visitCardID 健康卡表ID
	 * @param visitCardInfo 挂号信息
	 * @return 创建成功true,失败false
	 */
	public BooleanMsg saveSpAppointmentRecord(Long orderID,String deptFlag,Long visitCardID,SpHealthcardManager visitCardInfo);
	/**
	 * 获取挂号订单明细 
	 * @param orderID 本地订单ID
	 * @param deptFlag 部门标识（1儿保，2儿科）
	 * @return
	 */
	public SpRegisterOrderDetail getSpRegisterOrderDetail(Long orderID,String deptFlag);

	/**
	 * 获取用户就诊记录列表 
	 * @param visitCardID 就诊卡ID
	 * @param pageNo 要选的页号
	 * @param userID 用户ID
	 * @param timeFlag 查询时间标识,本月记录1,历史记录0
	 * @param spOrgID 苏坡机构ID
	 * @return
	 * <p>1.返回结果为空,标识没有就诊记录</p>
	 */
	public List<SpVisitRecord> saveSpVisitRecords(Long visitCardID,Integer pageNo,Long userID,String timeFlag,String spOrgID);
	/**
	 * 查看就诊记录详细内容 
	 * @param spQueryMessageID 就诊记录表ID
	 */
	public SpVisitRecord getSpVisitRecord(Long spQueryMessageID);
	
	/**
	 * 获取医嘱信息列表
	 * @param visitCardID 就诊卡ID
	 * @param pageNo 页号
	 * @param userID 用户ID
	 * @param timeFlag  查询时间标识,本月记录1,历史记录0
	 * @param orgID 苏坡机构ID
	 * @return
	 */
	public List<SpDoctorAdviceInfo> saveSpDoctorAdviceInfos(Long visitCardID,Integer pageNo,Long userID,String timeFlag,String orgID);
	/**
	 * 获取医嘱信息详情
	 * @param spQueryMessageID 医嘱表ID
	 */
	public SpDoctorAdviceInfo getDoctorAdviseInfo(Long spQueryMessageID);
	/**
	 * 获取费用信息列表
	 * @param visitCardID 就诊卡ID
	 * @param pageNo 页号
	 * @param userID 用户ID
	 * @param timeFlag  查询时间标识,本月记录1,历史记录0
	 * @param costType 费用类型(1已收费,0未收费)
	 * @param orgID 苏坡机构ID
	 * @return
	 */
	public List<SpCostInfo> saveSpCostInfos(Long visitCardID,Integer pageNo,Long userID,String timeFlag,String costType,String orgID);
	/**
	 * 执行门诊收费 
	 * @param spQueryMessageID 费用表主键ID,
	 * @return
	 */
	public boolean saveSpClinicChargeInfo(Long spQueryMessageID);
	/**
	 * 获取费用详情
	 * @param spQueryMessageID 费用表ID
	 * @return
	 */
	public SpCostInfo getSpCostInfo(Long spQueryMessageID);
	/**
	 * 判断费用记录是否已缴费
	 * @param spQueryMessageID 费用表ID
	 * @return
	 */
	public boolean querySpCostInfo_IsPaid(Long spQueryMessageID);
	/**   
	 * 获取检验记录列表
	 * @param visitCardID 就诊卡ID
	 * @param pageNo 要选的页号
	 * @param userID 用户ID
	 * @param timeFlag 查询时间标识,本月记录1,历史记录0
	 * @param orgID 苏坡机构ID
	 */
	public List<SpInspectRecord> saveSpInspectRecords(Long visitCardID,Integer pageNo,Long userID,String timeFlag,String orgID);
	/**   
	 * 获取检验记录和检验结果
	 * @param spQueryMessageID 检验表ID
	 */
	public SpInspectRecord saveSpInspectInfo(Long spQueryMessageID);
	/**
	 * 获取检查记录列表
	 * @param visitCardID 就诊卡ID
	 * @param pageNo 页码
	 * @param userID 用户ID
	 * @param timeFlag 查询时间标识,本月记录1,历史记录0
	 * @param orgID 苏坡机构ID
	 * @return
	 */
	public List<SpCheckRecord> saveSpCheckRecords(Long visitCardID,Integer pageNo,Long userID,String timeFlag,String orgID);
	/**   
	 * 获取检查记录详情和检查报告
	 * @param spQueryMessageID 检查记录表ID
	 */
	public SpCheckRecord saveSpCheckInfo(Long spQueryMessageID);
	/**
	 * 取消挂号记录
	 * @param orderID 本地订单ID
	 * @param deptFlag 科室标识（1儿保，2儿科）
	 */
	public boolean cancelRegister(Long orderID,String deptFlag);
	
	public <T> T getObject(Long id,Class<?> clazz);
	
	/**
	 * 取消儿科订单状态
	 * @param userId 用户ID
	 * @param userInfo 用户信息
	 * @param oldOrder 儿科门诊订单
	 * @param orderStatus 修改后订单的状态
	 */
	public void cancelPeadiatricsOrder(Long userId, UserInfo userInfo,DoctorInfo doctorInfo,
			OrderInfoClinic oldOrder,String orderStatus);
	/**
	 * 取消儿保订单状态
	 * @param userId 用户ID
	 * @param userInfo 用户信息
	 * @param oldOrder 儿保订单
	 * @param orderStatus 订单状态
	 */
	public Long cancelChildCareOrder(Long userId, UserInfo userInfo,
			UserChildcareAppointmentInfo oldOrder
			,String orderStatus) ;
}
