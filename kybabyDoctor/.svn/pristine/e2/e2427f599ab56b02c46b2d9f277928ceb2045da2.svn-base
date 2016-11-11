package com.kybaby.newbussiness.doctorclinic.dao;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicOtherContactsInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;

/**
 * 医生门诊订单管理Dao接口类
 * @author xiongchao
 *
 */
public interface DoctorClinicOrderDao {
	
	/**
	 * 获取订单信息
	 * @param orderInfoClinic
	 * @return
	 */
	List<OrderInfoClinic> getOrderInfoClinicList(OrderInfoClinic orderInfoClinic);
	
	/**
	 * 根据id得到门诊订单信息
	 * @param id
	 * @return
	 */
	OrderInfoClinic getOrderInfoClinicById(Long id);
	
	/**
	 * 根据订单id得到联系人信息
	 * @param orderId
	 * @return
	 */
	ClinicOtherContactsInfo getOrderContactsInfoByOrderId(Long orderId);
	
	/**
	 * 根据id更新订单的状态
	 * @param id
	 * @param orderStatus
	 * @return
	 */
	OrderInfoClinic updateOrderStatusById(Long id, String orderStatus);
	
	/**
	 * 得到医生的订单门诊费用
	 * @param doctorInfo
	 * @return
	 */
	HospitalPosition getHospitalPositionByDoctor(DoctorInfo doctorInfo,OrderInfoClinic orderInfoClinic);
	/**
	 * 根据订单编号得到订单信息
	 * @param orderNum
	 * @return
	 */
	OrderInfoClinic getOrderInfoClinicByOrderNum(String orderNum);
}
