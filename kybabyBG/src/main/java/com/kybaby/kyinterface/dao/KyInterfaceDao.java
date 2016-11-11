package com.kybaby.kyinterface.dao;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.Product;
import com.kybaby.domain.UserInfo;
import com.kybaby.kyinterface.domain.UserInfoCustom;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResourcesDetail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;

public interface KyInterfaceDao {
	
	public void updateUserInfo(UserInfoCustom userinfo);
	
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public UserInfo getUserInfo(Long id);
	
	/**
	 * 根据订单id获取上门服务订单记录
	 */
	public OrderInfo getOrderInfoById(Long orderId);
	
	/**
	 * 根据订单编号，查找订单的来源，返回巴蜀快医上门服务订单id
	 * @param orderNum
	 * @return
	 */
	public String findBSKYOrderSource(String orderNum);

	/**
	 * 添加用户对象
	 */
	public void addUserInfo(UserInfoCustom userInfo);

	/**
	 * 查询用户
	 */
	public UserInfoCustom findUserInfo(UserInfoCustom userInfo);

	/**
	 * 用户完成计免业务回执接口(用户线下，是否完整疫苗接种) 获取订单
	 */
	public UserInoculationAppointmentInfo userCompleteOrderByReturnReceipt(
			String orderId);

	/**
	 * 根据快医上门服务订单id查找康优宝贝上门服务订单id
	 * @param kyOrderId 快医上门服务订单id
	 * @return
	 */
	public String getOrderIdByKyOrderId(String kyOrderId);
	
	/**
	 * 上门服务订单创建传输接口》》 创建上门服务订单
	 */
	public void addOrderInfo(OrderInfo orderInfo);

	/**
	 * 快医计免订单id与我方计免订单id对应关系表
	 * <p>添加成功，返回true,失败返回false</p>
	 * @param kyOrderId
	 *            快医上门服务订单id
	 * @param qycOrderId
	 *            我方上门服务订单id
	 */
	public boolean addInocalutionOrder2myOrder(String kyOrderId, Long qycOrderId);

	/**
	 * 快医订单id与我方上门服务订单id对应关系表
	 * <p>添加成功，返回true,失败返回false</p>
	 * @param kyOrderId
	 *            快医上门服务订单id
	 * @param qycOrderId
	 *            我方上门服务订单id
	 */
	public boolean addKy2Order(String kyOrderId, Long qycOrderId);

	/**
	 * 计免预约服务接口》》 创建计免订单
	 */
	public void addInoculationOrderInfo(UserInoculationAppointmentInfo info);

	/**
	 * 上门服务订单回执信息接口
	 */
	public boolean orderStatusTransform(String orderId, String orderStatus);

	/**
	 * 机构计免排班信息接口
	 */
	public List<OrganInoculationOpenResourcesDetail> getHospitalSchedulingInfo(
			String orgId);

	/**
	 * 获取医生列表
	 * 
	 * @return
	 */
	List<DoctorInfo> getDoctorInfoListByInterface();

	/**
	 * 社区医院接口 获取社区医院信息
	 */
	public List<HospitalBasicInfo> getHospitalBasicInfoList();

	/**
	 * 获取产品信息列表
	 */
	public List<Product> getProductList();

	public OrganInoculationOpenResources getOrgInnoculationOpenResourceByID(
			Long open_resources_id);

	public OrganInoculationOpenResourcesDetail getOrgInnoculationOpenResourceDetailByID(
			Long open_resources_detail_id);

	public HospitalBasicInfo getOrgInfoByID(Long ascription_organ);

	public Product getProductByID(Long productId);
}
