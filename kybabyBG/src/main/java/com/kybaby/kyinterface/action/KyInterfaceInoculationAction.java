package com.kybaby.kyinterface.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kybaby.kyinterface.domain.InoculationOrderCustom;
import com.kybaby.kyinterface.domain.UserInfoCustom;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResourcesDetail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;
import com.opensymphony.xwork2.ModelDriven;


//InoculationOrderCustom,UserInoculationAppointmentInfo
public class KyInterfaceInoculationAction extends NewBaseAction implements
		ModelDriven<InoculationOrderCustom> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2457372078745309632L;
	
	
	private InoculationOrderCustom info = new InoculationOrderCustom();
	
	private final String USER_SOURCE = "sichuanky";//用户来源--》四川快医公司

	@Override
	public String execute() throws Exception {

		//验证请求数据
		Map<String, String> map = validateRequestData(info);
		if(map.isEmpty()){
			//接受请求数据，并对请求的数据进行解析
			//1.获取用户数据
			UserInfoCustom userInfo = parseRequestData2UserInfo(info);
			userInfo.setUserSource(USER_SOURCE);
			//2。获取计免订单数据
			UserInoculationAppointmentInfo inoculationInfo = parseRequestData(info);
	
			//3.创建计免订单,成功返回true,失败返回false
			boolean createSuccess = kyInterfaceBo.addInoculationOrderInfo(inoculationInfo,userInfo,info.getKyOrderId());
			
			if (createSuccess) {
				map.put("message", "订单创建成功");
			}
			else{
				map.put("message", "订单创建失败");
			}
		}
		
		String json = format2JsonWithMap(map);
		//返回json数据
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
		return null;
	}


	private String format2JsonWithMap(Map<String, String> map) {

		JSONArray ja = new JSONArray();

		JSONObject jo = null;
		for (Entry<String, String> entry : map.entrySet()) {
			jo = new JSONObject();
			jo.put(entry.getKey(), entry.getValue());
			ja.add(jo);
		}

		return ja.toString();
	}

	/**
	 * 验证请求数据是否为空
	 * @param info
	 * @return
	 */
	private Map<String, String> validateRequestData(InoculationOrderCustom info) {
		Map<String, String> map = new HashMap<String, String>();

		// 用户
		if (StringUtils.isBlank(info.getKyOrderId())) {
			map.put("kyOrderId", "计免订单id不能为空");
		}
		// 计免订单
		if (StringUtils.isBlank(info.getKyUserId())) {
			map.put("kyUserId", "用户id不能为空");
		}
		
		
		if (StringUtils.isBlank(info.getUserName())) {
			map.put("userName", "用户名称不能为空");
		}
		if (StringUtils.isBlank(info.getUserPhone())) {
			map.put("userPhone", "用户联系电话不能为空");
		}
/*		if (StringUtils.isBlank(info.getUserAddress())) {//快医公司解释：计免不用传用户地址
			map.put("userAddress", "用户联系地址不能为空");
		}*/
		
		
		if (StringUtils.isBlank(info.getOpen_resources_id()==null?"":info.getOpen_resources_id()+"")) {
			map.put("open_resources_id", "开放资源外键不能为空");
		}
		if (StringUtils.isBlank(info.getOpen_resources_detail_id()==null?"":info.getOpen_resources_detail_id()+"")) {
			map.put("open_resources_detail_id", "开放资源明细外键不能为空");
		}
		
		if (StringUtils.isBlank(info.getAscription_organ()==null?"":info.getAscription_organ()+"")) {
			map.put("ascription_organ", "归属机构ID不能为空");
		}
		if (StringUtils.isBlank(info.getAppointment_code())) {
			map.put("appointment_code", "预约编码不能为空");
		}
		if (StringUtils.isBlank(info.getStatus())) {
			map.put("status", "订单状态不能为空");
		}
		if (StringUtils.isBlank(info.getOpt_time())) {
			map.put("opt_time", "操作时间不能为空,如yyyy-MM-dd HH:mm:ss");
		}
		
		//判断数据逻辑
		if(map.isEmpty()){
			/*
			 * 1.判断计免开放资源,明细,机构是否存在
			 */
			OrganInoculationOpenResources mainResource = 
					kyInterfaceBo.getOrgInnoculationOpenResourceByID(info.getOpen_resources_id());
			OrganInoculationOpenResourcesDetail detail = 
					kyInterfaceBo.getOrgInnoculationOpenResourceDetailByID(info.getOpen_resources_detail_id());
			HospitalBasicInfo org = kyInterfaceBo.getOrgInfoByID(info.getAscription_organ());
			
			if(mainResource==null){
				map.put("open_resources_id", "不存在该计免开放资源id");
			}
			if(detail==null){
				map.put("open_resources_detail_id", "不存在该计免开放资源明细id");
			}
			if(mainResource!=null&&detail!=null){
				if(!mainResource.getId().toString().equals(
						detail.getOrganInoculationOpenResources().getId().toString())){
					map.put("open_resources_detail_id&&open_resources_id", "计免开放资源与明细资源不存在关联关系");
				}
			}
			
			if(org==null){
				map.put("open_resources_detail_id", "不存在该计免开放资源明细id");
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try{
				String time1 = info.getOpt_time();
				Date date1 = sdf.parse(info.getOpt_time());
				String time2 = sdf.format(date1);
				if(!time1.equals(time2)){
					map.put("opt_time", "操作时间格式不正确,如yyyy-MM-dd HH:mm:ss");
				}
			}catch(Exception e){
				map.put("opt_time", "操作时间格式不正确,如yyyy-MM-dd HH:mm:ss");
			}
			
			
			//状态转换
			switch (info.getStatus()) {
			case "1":info.setStatus("已预约");
				break;
			/*case "2":info.setStatus("已取消");
				break;
			case "3":info.setStatus("已登记");
				break;*/
			default:
				map.put("status", "未知状态");
				break;
			}
		}
		
		return map;
	}
	
	//解析请求数据，返回用户对象
	private UserInfoCustom parseRequestData2UserInfo(InoculationOrderCustom info) throws UnsupportedEncodingException{
		UserInfoCustom userInfo = new UserInfoCustom();
		
		userInfo.setUserSourceId(info.getKyUserId());
		
		userInfo.setParentName(info.getUserName());
		//userInfo.setDetailAddress(info.getUserAddress());
		userInfo.setPhone(info.getUserPhone());
		
		
		return userInfo;
	}
	
	//解析数据，返回几面订单数据
	private UserInoculationAppointmentInfo parseRequestData(InoculationOrderCustom info){
		UserInoculationAppointmentInfo inoculationInfo = new UserInoculationAppointmentInfo();
		//inoculationInfo.setUserInfo(userInfo);
		
		OrganInoculationOpenResources organInoculationOpenResources = new OrganInoculationOpenResources();
		organInoculationOpenResources.setId(info.getOpen_resources_id());
		inoculationInfo.setOrganInoculationOpenResources(organInoculationOpenResources);
		
		OrganInoculationOpenResourcesDetail inoculationOpenResourcesDetail = new OrganInoculationOpenResourcesDetail();
		inoculationOpenResourcesDetail.setId(info.getOpen_resources_detail_id());
		inoculationInfo.setOrganInoculationOpenResourcesDetail(inoculationOpenResourcesDetail);
		
		HospitalBasicInfo hospitalBasicInfo = new HospitalBasicInfo();
		hospitalBasicInfo.setId(info.getAscription_organ());
		inoculationInfo.setHospitalBasicInfo(hospitalBasicInfo);
		
		inoculationInfo.setAppointmentCode(info.getAppointment_code());
		inoculationInfo.setOptTime(info.getOpt_time());
		inoculationInfo.setStatus(info.getStatus());
		return inoculationInfo;
	}
	
	@Override
	public InoculationOrderCustom getModel() {
		return info;
	}
}
