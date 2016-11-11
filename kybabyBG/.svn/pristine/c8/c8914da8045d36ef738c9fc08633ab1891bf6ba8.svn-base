package com.kybaby.kyinterface.bo.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.Product;
import com.kybaby.domain.UserInfo;
import com.kybaby.kyinterface.bo.KyInterfaceBo;
import com.kybaby.kyinterface.dao.KyInterfaceDao;
import com.kybaby.kyinterface.domain.UserInfoCustom;
import com.kybaby.kyinterface.util.HttpClientUtil;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResourcesDetail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;
import com.kybaby.util.EncryptUtil;

public class KyInterfaceBoImpl implements KyInterfaceBo {
	

	KyInterfaceDao kyInterfaceDao;
	
	@Override
	public Product getProductByID(Long productId) {
		return kyInterfaceDao.getProductByID(productId);
	}
	
	//private final String USER_SOURCE = "sichuanky";//用户来源--》四川快医公司

	public String updateBSKYOrder(String orderNum,String doctorId) {
		
		String orderSourceId = kyInterfaceDao.findBSKYOrderSource(orderNum);
		if(null==orderSourceId) return null;
		
		try {
			//return HttpClientUtil.getInstance().SMFWEdit(orderSourceId, doctorId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 根据订单编号，查找订单的来源，如果是来之巴蜀快医，返回true,否则返回false
	 * @param orderNum
	 * @return
	 */
	public boolean findBSKYOrderByOrderNum(String orderNum){
		String findBSKYOrder = kyInterfaceDao.findBSKYOrderSource(orderNum);
		if(StringUtils.isNotBlank(findBSKYOrder)) return true;
		else return false;
	}
	
	/**
	 * 添加用户对象
	 */
	public Long addUserInfo(UserInfoCustom userInfo) {

		UserInfoCustom findUserInfo = kyInterfaceDao.findUserInfo(userInfo);

		if (findUserInfo != null) {
			/*
			 * 1.如果查询出来的用户信息中，详细地址为空，
			 * 2.传入的用户信息，详细地址不为空
			 * 3.修改用户信息的地址
			 */
			if(StringUtils.isNotBlank(userInfo.getDetailAddress())){
				findUserInfo.setDetailAddress(userInfo.getDetailAddress());
			}
			findUserInfo.setParentName(userInfo.getParentName());
			kyInterfaceDao.updateUserInfo(findUserInfo);
			return findUserInfo.getId();
		}
		
		/*userInfo.set*/
		userInfo.setPassword(EncryptUtil.getMD5Str("123"));
		userInfo.setComments("快医公司用户同步");
		
		kyInterfaceDao.addUserInfo(userInfo);
		Long id = userInfo.getId();
		if (id != null) {
			return id;
		} else {
			return null;
		}
	}

	private String getOrderStatus(String orderStatus) {
		String Order_Status = "";
		switch (orderStatus) {
		case "11":
			Order_Status = "已签到";
			break;
		case "12":
			Order_Status = "已完成";
			break;
		case "2":
			Order_Status = "已确认";
			break;
		default:
			Order_Status = null;
		}
		return Order_Status;
	}

	/**
	 * 上门服务订单回执信息接口 返回指定状态的订单信息（根据场景实现已签到、已完成等状态的转化）
	 */
	public boolean orderStatusTransform(String kyOrderId, String orderStatus) {
		// 订单id,订单要修改的状态有值
		if (StringUtils.isNotBlank(kyOrderId)
				&& StringUtils.isNotBlank(orderStatus)) {
			// 根据快医上门服务订单id，获取订单id
			String orderId = kyInterfaceDao.getOrderIdByKyOrderId(kyOrderId);
			if(StringUtils.isBlank(orderId)) return false;
			
			String order_status = getOrderStatus(orderStatus);
			if(StringUtils.isBlank(order_status)) return false;
			
			return kyInterfaceDao.orderStatusTransform(orderId,
					order_status);
		} else {
			return false;
		}
	}

	/**
	 * 计免预约服务接口》》 创建计免订单
	 */
	public boolean addInoculationOrderInfo(UserInoculationAppointmentInfo info,
			UserInfoCustom userInfo, String kyOrderId) {
		// 1.保存用户信息
		Long userid = this.addUserInfo(userInfo);
		if (userid==null)
			return false;

		// 2.保存计免订单
		UserInfo userInfo_query = kyInterfaceDao.getUserInfo(userid);
		info.setUserInfo(userInfo_query);
		kyInterfaceDao.addInoculationOrderInfo(info);
		Long inoculation_id = info.getId();
		if (inoculation_id == null)
			return false;

		// 3.保存快医计免订单Id与康优宝贝计免订单id的对应关系
		boolean addInocalutionOrder2myOrder = kyInterfaceDao
				.addInocalutionOrder2myOrder(kyOrderId, inoculation_id);
		if(!addInocalutionOrder2myOrder) return false;
		
		return true;
		// ..........
	}

	/**
	 * 用户完成计免业务回执接口,查看订单状态
	 */
	public String userCompleteOrderByReturnReceipt(String orderId) {
		// 用户id,订单要修改的状态有值
		if (StringUtils.isNotBlank(orderId)) {
			UserInoculationAppointmentInfo info = kyInterfaceDao
					.userCompleteOrderByReturnReceipt(orderId);
			return info.getStatus();
		}
		return null;
	}

	/**
	 * 上门服务订单创建传输接口
	 * 
	 * 1.保存用户对象 2.创建上门服务订单
	 */
	public boolean addOrderInfo(OrderInfo orderInfo, UserInfoCustom userInfo,
			String kyOrderId) {

		// 1.保存用户信息
		Long userid = this.addUserInfo(userInfo);
		if (userid==null)
			return false;

		// 2.保存上门服务订单
		orderInfo.setUserId(userid);
		orderInfo.setComments(userInfo.getParentName()+"@"+userInfo.getDetailAddress());
		
		kyInterfaceDao.addOrderInfo(orderInfo);
		Long flag = orderInfo.getId();
		if (flag == null)
			return false;
		// 3.保存快医上门服务订单Id与康优宝贝上门服务订单id的对应关系
		boolean addKy2Order = kyInterfaceDao.addKy2Order(kyOrderId,
				orderInfo.getId());
		if (!addKy2Order)
			return false;

		return true;
	}

	
	/**
	 * 获取计免开放资源
	 * @param open_resources_id
	 * @return
	 */
	public  OrganInoculationOpenResources getOrgInnoculationOpenResourceByID(
			Long open_resources_id){
		return kyInterfaceDao.getOrgInnoculationOpenResourceByID(open_resources_id);
	}


	/**
	 * 获取计免资源明细
	 * @param open_resources_detail_id
	 * @return
	 */
	public OrganInoculationOpenResourcesDetail getOrgInnoculationOpenResourceDetailByID(
			Long open_resources_detail_id){
		return kyInterfaceDao.getOrgInnoculationOpenResourceDetailByID(open_resources_detail_id);
	}


	/**
	 * 获取机构信息
	 * @param ascription_organ
	 * @return
	 */
	public HospitalBasicInfo getOrgInfoByID(Long ascription_organ){
		return kyInterfaceDao.getOrgInfoByID(ascription_organ);
	}
	
	
	
	@Override
	public List<DoctorInfo> getDoctorInfoListByInterface() {
		return kyInterfaceDao.getDoctorInfoListByInterface();
	}

	@Override
	public List<HospitalBasicInfo> getHospitalBasicInfoList() {
		return kyInterfaceDao.getHospitalBasicInfoList();
	}

	@Override
	public List<Product> getProductList() {
		return kyInterfaceDao.getProductList();
	}

	public List<OrganInoculationOpenResourcesDetail> getHospitalSchedulingInfo(
			String orgId) {
		return kyInterfaceDao.getHospitalSchedulingInfo(orgId);
	}

	public KyInterfaceDao getKyInterfaceDao() {
		return kyInterfaceDao;
	}

	public void setKyInterfaceDao(KyInterfaceDao kyInterfaceDao) {
		this.kyInterfaceDao = kyInterfaceDao;
	}




}
