package com.kybaby.newbussiness.doctorclinic.bo;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Position;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicDiscountInfo;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicMedicalRecords;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicOtherContactsInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorClinicTimeSegment;
import com.kybaby.newbussiness.doctorclinic.domain.DrugInfo;
import com.kybaby.newbussiness.doctorclinic.domain.EvaluateClinic;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;

public interface ClinicOrderService {
	/**
	 * 保存门诊订单信息
	 * @param orderInfoClinic
	 * @return
	 */
	Long saveClinicOrder(OrderInfoClinic orderInfoClinic);
	/**
	 * 更新门诊订单信息
	 * @param orderInfoClinic
	 * @return
	 */
	Long updateClinicOrder(OrderInfoClinic orderInfoClinic);
	/**
	 * 根据id得到门诊订单信息
	 * @param id
	 * @return
	 */
	OrderInfoClinic getOrderInfoClinicById(Long id);
	/**
	 * 得到就诊记录信息
	 * @param id
	 * @return
	 */
	ClinicMedicalRecords getClinicMedicalRecordsById(Long id);
	/**
	 * 保存或更新门诊联系人信息
	 * @param clinicOtherContactsInfo
	 */
	void saveOrUpdateClinicOtherContactsInfo(ClinicOtherContactsInfo clinicOtherContactsInfo);
	/**
	 * 得到医生的订单门诊费用
	 * @param doctorInfo
	 * @return
	 */
	HospitalPosition getHospitalPositionByDoctor(DoctorInfo doctorInfo,OrderInfoClinic orderInfoClinic);
	/**
	 * 获取订单信息
	 * @param orderInfoClinic
	 * @return
	 */
	List<OrderInfoClinic> getOrderInfoClinicList(OrderInfoClinic orderInfoClinic);
	/**
	 * 得到医生(可用)时间段集合
	 * @param doctorClinicTimeSegment
	 * @param doctorInfo
	 * @return
	 */
	List<DoctorClinicTimeSegment> getDoctorClinicTimeSegmentList(DoctorClinicTimeSegment doctorClinicTimeSegment,DoctorInfo doctorInfo);
	/**
	 * 更新预约时间段状态
	 * @param doctorInfo 医生信息
	 * @param isCanUse 是否可用状态
	 * @param clinicDate 门诊日期
	 * @param segment 门诊时间
	 */
	
	void updateDoctorClinicTimeSegmentStatus(DoctorInfo doctorInfo,String isCanUse,String clinicDate,String segment);
	/**
	 * 得到其他联系人列表
	 * @param clinicOtherContactsInfo
	 * @param orderInfoClinic
	 * @return
	 */
	List<ClinicOtherContactsInfo> getClinicOtherContactsInfoList(ClinicOtherContactsInfo clinicOtherContactsInfo,OrderInfoClinic orderInfoClinic);
	/**
	 * 得到门诊福利信息
	 * @return
	 */
	ClinicDiscountInfo getClinicDiscountInfo();
	/**
	 * 检查预约时间是否被占用
	 * @param date
	 * @param time
	 * @param doctorInfo
	 * @return true表示占用了    false表示没占用
	 */
	Boolean checkTimeIsUsed(String date,String time,DoctorInfo doctorInfo);
	/**
	 * 检查未付款订单是否存在
	 * @param date
	 * @param time
	 * @param doctorInfo
	 * @return true表示已有    false表示没有
	 */
	List<OrderInfoClinic> checkOrderIsExist(String date,String time,String clinicAddress,DoctorInfo doctorInfo,UserInfo userInfo);
	/**
	 * 保存评价信息
	 * @param evaluateClinic
	 */
	void saveEvaluateClinic(EvaluateClinic evaluateClinic);
	/**
	 * 根据id更新订单的状态
	 * @param id
	 * @param orderStatus
	 * @return
	 */
	OrderInfoClinic updateOrderStatusById(Long id, String orderStatus);
	/**
	 * 根据订单编号得到订单信息
	 * @param orderNum
	 * @return
	 */
	OrderInfoClinic getOrderInfoClinicByOrderNum(String orderNum);
	/**
	 * 得到用户就诊记录列表
	 * @param clinicMedicalRecords
	 * @param userInfo
	 * @return
	 */
	List<ClinicMedicalRecords> getClinicMedicalRecordsList(
			ClinicMedicalRecords clinicMedicalRecords, UserInfo userInfo);
	/**
	 * 根据订单ID得到其他联系人
	 * @param orderId
	 * @return
	 */
	ClinicOtherContactsInfo getClinicOtherContactsInfoByOrderId(Long orderId);
	/**
	 * 得到药品信息
	 * @param id
	 * @return
	 */
	DrugInfo getDrugInfoById(Long id);
}
