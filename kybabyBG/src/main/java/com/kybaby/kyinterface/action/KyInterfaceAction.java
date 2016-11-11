package com.kybaby.kyinterface.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.struts2.ServletActionContext;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Product;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResourcesDetail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;
import com.sun.imageio.plugins.common.InputStreamAdapter;

//192.168.0.139:8082
public class KyInterfaceAction extends NewBaseAction {

	private static final long serialVersionUID = 8679646097495753255L;

	private String orderId;
	private String orderStatus;// 上门服务订单状态，快医端，传递的是数字

	private String orgId;// 机构id

	private String kyOrderId;// 快医端，订单id

	private String orderNum;// 订单编号
	
	private String doctorId;//医生Id

	/**
	 * 上门服务订单回执信息接口
	 */
	private final String ORDER_STATUS_TRANSFORM = "OrderStatusTransform";

	/**
	 * 用户完成计免业务回执接口
	 */
	private final String INOCULAT_IONORDER_COMPLETE = "InoculationOrderComplete";

	/**
	 * 机构计免排班信息接口
	 */
	private final String HOSPITAL_SCHEDULING_INFO = "hospitalSchedulingInfo";

	/**
	 * 医生列表接口 获取医生信息
	 */
	private final String DOCTOR_INFO_LIST = "doctorInfoList";

	/**
	 * 社区医院接口
	 */
	private final String HOSPITAL_BASIC_INFO_LIST = "hospitalBasicInfoList";

	/**
	 * 产品列表接口
	 */
	private final String PRODUCT_LIST = "productList";

	/**
	 * 巴蜀快医订单查询
	 */
	private final String SCKY_ORDER = "sckyOrder";

	/**
	 * 修改巴蜀快医上门服务订单中医生数据
	 */
	private final String SCKY_ORDER_EDIT = "sckyOrderEdit";

	/**
	 * 跳转到上门服务订单action
	 */
	private final String SMFW_ORDER_CREATE = "SMFWOrder";
	private final String SMFW_ORDER_CREATE_ACTION = "kyInterfaceOrderAction";
	
	/**
	 * 跳转到计免订单action
	 */
	private final String INOCULATION_ORDER = "inoculationOrder";
	private final String INOCULATION_ORDER_ACTION = "kyInterfaceInoculationAction";
	
	public String execute() throws Exception {
		System.out.println("======KyInterfaceAction执行execute()方法======");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		String json = "";

		switch (action) {
		case SMFW_ORDER_CREATE:return SMFW_ORDER_CREATE_ACTION;
		case INOCULATION_ORDER:return INOCULATION_ORDER_ACTION;
		/*
		 * 修改巴蜀快医上门服务订单中医生数据
		 */
		case SCKY_ORDER_EDIT:
			json = kyInterfaceBo.updateBSKYOrder(orderNum,doctorId);
			break;
		/*
		 * 巴蜀快医订单查询
		 */
		case SCKY_ORDER:
			json = kyInterfaceBo.findBSKYOrderByOrderNum(orderNum) ? "true" : "false";
			break;
		/*
		 * 上门服务订单回执信息接口---》修改订单状态，返回是否操作成功
		 */
		case ORDER_STATUS_TRANSFORM:
			json = kyInterfaceBo.orderStatusTransform(kyOrderId, orderStatus) ? "{'message':'修改订单状态成功'}"
					: "{'message':'修改订单状态失败'}";
			break;
		/*
		 * 用户完成计免业务回执接口
		 */
		case INOCULAT_IONORDER_COMPLETE:
			String orderStatus = kyInterfaceBo
					.userCompleteOrderByReturnReceipt(orderId);
			if(StringUtils.isBlank(orderStatus)){
				json = "{'message':'订单不存在'}";
				break;
			}
			json = "{'status':'" + orderStatus + "'}";
			break;
		/*
		 * 机构计免排班信息接口
		 */
		case HOSPITAL_SCHEDULING_INFO:
			json = getJson(kyInterfaceBo.getHospitalSchedulingInfo(orgId),
					HOSPITAL_SCHEDULING_INFO);
			break;
		/*
		 * 医生列表接口 获取医生信息
		 */
		case DOCTOR_INFO_LIST:
			
			/*
			 * 从json文件中获取医生信息
			 */
			String filePath = "com"+File.separator+"kybaby"+File.separator+"kyinterface"+File.separator+"file"+File.separator+"doctor.json";
			String url = this.getClass().getClassLoader().getResource("/").getPath();
			File doctorFile = new File(url+filePath);
			StringBuilder sb = new StringBuilder();
			if(doctorFile.exists()){
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(doctorFile),Consts.UTF_8));
				//String readFileToString = FileUtils.readFileToString(doctorFile, Consts.UTF_8);
				String temp = "";
				while((temp=br.readLine())!=null){
					sb.append(temp);
				}
				br.close();
			}
			/*json = getJson(kyInterfaceBo.getDoctorInfoListByInterface(),
					DOCTOR_INFO_LIST);*/
			json = sb.toString();
			break;
		/*
		 * 社区医院接口 获取社区医院信息
		 */
		case HOSPITAL_BASIC_INFO_LIST:
/*			json = getJson(kyInterfaceBo.getHospitalBasicInfoList(),
					HOSPITAL_BASIC_INFO_LIST);*/
			String filePath_org = "com"+File.separator+"kybaby"+File.separator+"kyinterface"+File.separator+"file"+File.separator+"org.json";
			String url_org = this.getClass().getClassLoader().getResource("/").getPath();
			File doctorFile_org = new File(url_org+filePath_org);
			StringBuilder sb_org = new StringBuilder();
			if(doctorFile_org.exists()){
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(doctorFile_org),Consts.UTF_8));
				//String readFileToString = FileUtils.readFileToString(doctorFile, Consts.UTF_8);
				String temp = "";
				while((temp=br.readLine())!=null){
					sb_org.append(temp);
				}
				br.close();
			}
			json = sb_org.toString();
			break;
		/*
		 * 产品信息接口 获取产品信息
		 */
		case PRODUCT_LIST:
			json = getJson(kyInterfaceBo.getProductList(), PRODUCT_LIST);
			break;
		}

		response.getWriter().write(StringUtils.isBlank(json) ? "{'message':'获取失败'}" : json);
		return null;
	}

	private String getJson(List list, String flag) {
		if (list != null && list.size() > 0) {
			JSONArray json = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				json.add(toJsonObject(list.get(i), flag));
			}
			return json.toString();
		} else
			return null;
	}

	private JSONObject toJsonObject(Object obj, String flag) {
		JSONObject jo = new JSONObject();
		switch (flag) {
		/*
		 * 用户完成计免业务回执接口
		 */
		case INOCULAT_IONORDER_COMPLETE:
			InoculationOrderComplete(jo, obj);
			break;
		/*
		 * 机构计免排班信息接口
		 */
		case HOSPITAL_SCHEDULING_INFO:
			hospitalSchedulingInfo2Json(jo, obj);
			break;
		/*
		 * 医生列表
		 */
		case DOCTOR_INFO_LIST:
			doctorInfoList2Json(jo, obj);
			break;
		/*
		 * 社区医院接口
		 */
		case HOSPITAL_BASIC_INFO_LIST:
			hospitalBasicInfoList2Json(jo, obj);
			break;
		/*
		 * 产品列表
		 */
		case PRODUCT_LIST:
			productList2Json(jo, obj);
			break;
		}
		return jo;
	}

	private void InoculationOrderComplete(JSONObject jo, Object obj) {
		UserInoculationAppointmentInfo info = (UserInoculationAppointmentInfo) obj;
		jo.put("id", info.getId());
		jo.put("user_id", info.getUserInfo().getId());
		jo.put("open_resources_id", info.getOrganInoculationOpenResources()
				.getId());
		jo.put("open_resources_detail_id", info
				.getOrganInoculationOpenResourcesDetail().getId());
		jo.put("ascription_organ", info.getHospitalBasicInfo().getId());
		jo.put("appointment_code", info.getAppointmentCode());
		jo.put("status", info.getStatus());
		jo.put("opt_time", info.getOptTime());
	}

	private void hospitalSchedulingInfo2Json(JSONObject jo, Object obj) {
		OrganInoculationOpenResourcesDetail detail = (OrganInoculationOpenResourcesDetail) obj;

		jo.put("id", detail.getId());
		jo.put("open_resources_id", detail.getOrganInoculationOpenResources()
				.getId());
		jo.put("ascription_organ", detail.getOrganInoculationOpenResources()
				.getHospitalBasicInfo().getId());
		jo.put("open_date", detail.getOpenDate());
		jo.put("open_start_time", detail.getOpenStartTime());
		jo.put("open_end_time", detail.getOpenEndTime());
		jo.put("green_channel_num", detail.getGreenChannelNum());
		jo.put("green_channel_surplus_num", detail.getGreenChannelSurplusNum());
	}

	private void productList2Json(JSONObject jo, Object obj) {
		Product product = (Product) obj;
		jo.put("id", product.getId());
		jo.put("productNum", product.getProductNum());
		jo.put("name", product.getName());
		jo.put("totalPrice", product.getTotalPrice());
		jo.put("introduction",
				StringUtils.isNotBlank(product.getIntroduction()) ? product
						.getIntroduction() : "");
		jo.put("smallPicture",
				StringUtils.isNotBlank(product.getSmallPicture()) ? product
						.getSmallPicture() : "");
		jo.put("short_introduction", StringUtils.isNotBlank(product
				.getShortIntroduction()) ? product.getShortIntroduction() : "");
	}

	private void hospitalBasicInfoList2Json(JSONObject jo, Object obj) {
		HospitalBasicInfo hospitalBasicInfo = (HospitalBasicInfo) obj;
		jo.put("id", hospitalBasicInfo.getId());
		jo.put("hospital_lname", hospitalBasicInfo.getHospitalLname());
		jo.put("address",
				StringUtils.isNotBlank(hospitalBasicInfo.getAddress()) ? hospitalBasicInfo
						.getAddress() : "");
		jo.put("introduction", StringUtils.isNotBlank(hospitalBasicInfo
				.getIntroduction()) ? hospitalBasicInfo.getIntroduction() : "");
	}

	private void doctorInfoList2Json(JSONObject jo, Object obj) {
		DoctorInfo doctorInfo = (DoctorInfo) obj;
		jo.put("id", doctorInfo.getId());
		jo.put("doctorName", doctorInfo.getDoctorName());
		jo.put("doctorSex", doctorInfo.getDoctorSex());
		jo.put("doctorImage", doctorInfo.getDoctorImage());
		jo.put("doctorTitle", doctorInfo.getDoctorTitle());
		jo.put("doctorEmployer", doctorInfo.getDoctorEmployer());
		jo.put("doctor_comment", doctorInfo.getDoctorComment());
		jo.put("hospital_id", doctorInfo.getHospitalId());
		jo.put("department", doctorInfo.getDepartment());
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getKyOrderId() {
		return kyOrderId;
	}

	public void setKyOrderId(String kyOrderId) {
		this.kyOrderId = kyOrderId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
}
