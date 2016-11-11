package com.kybaby.kyinterface.bo;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.Product;
import com.kybaby.kyinterface.domain.UserInfoCustom;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResourcesDetail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;

public interface KyInterfaceBo {
	
	/**
	 * 修改巴蜀快医，上门服务订单中医生数据
	 * @param doctorId
	 */
	public String updateBSKYOrder(String orderNum,String doctorId);
	
	
	
	/**
	 * 根据订单编号，查找订单的来源，如果是来之巴蜀快医，返回true,否则返回false
	 * @param orderNum
	 * @return
	 */
	public boolean findBSKYOrderByOrderNum(String orderNum);
	
	/**
	 * 添加用户对象
	 * 1.根据用户来源Id判断用户是否已存在，存在则，不用添加，返回true
	 */
	public Long addUserInfo(UserInfoCustom userInfo);
	
	/**
	 * 计免预约服务接口》》 创建计免订单
	 * 1.保存用户对象，
	 * 2.创建计免订单
	 */
	public boolean addInoculationOrderInfo(UserInoculationAppointmentInfo info,UserInfoCustom userInfo,String kyOrderId);
	
	/**
	 * 用户完成计免业务回执接口,查看订单状态
	 */
	public String userCompleteOrderByReturnReceipt(String orderId);
	/**
	 * 上门服务订单回执信息接口
	 */
	public boolean orderStatusTransform(String orderId,String orderStatus);
	/**
	 * 上门服务订单创建传输接口
	 *1.保存用户对象
	 *2.创建上门服务订单
	 */
	public boolean addOrderInfo(OrderInfo orderInfo, UserInfoCustom userInfo,String kyOrderId);
	
	/**
	 * 机构计免排班信息接口
	 */
	public List<OrganInoculationOpenResourcesDetail> getHospitalSchedulingInfo(String orgId);
	
	/**
	 * 获取医生列表
	 * @return
	 */
	List<DoctorInfo> getDoctorInfoListByInterface();
	/**
	 * 社区医院接口
	 * 获取社区医院信息
	 */
	List<HospitalBasicInfo> getHospitalBasicInfoList();
	/**
	 * 获取产品信息列表
	 */
	public List<Product> getProductList();

	/**
	 * 获取计免开放资源
	 * @param open_resources_id
	 * @return
	 */
	public  OrganInoculationOpenResources getOrgInnoculationOpenResourceByID(
			Long open_resources_id);


	/**
	 * 获取计免资源明细
	 * @param open_resources_detail_id
	 * @return
	 */
	public OrganInoculationOpenResourcesDetail getOrgInnoculationOpenResourceDetailByID(
			Long open_resources_detail_id);


	/**
	 * 获取机构信息
	 * @param ascription_organ
	 * @return
	 */
	public HospitalBasicInfo getOrgInfoByID(Long ascription_organ);



	public Product getProductByID(Long productId);
}
