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

import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.Product;
import com.kybaby.kyinterface.domain.SMFWOrderCustom;
import com.kybaby.kyinterface.domain.UserInfoCustom;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.opensymphony.xwork2.ModelDriven;

public class KyInterfaceOrderAction extends NewBaseAction implements
		ModelDriven<SMFWOrderCustom> {

	private SMFWOrderCustom info = new SMFWOrderCustom();

	private static final long serialVersionUID = -9104634591003908634L;

	private final String USER_SOURCE = "sichuanky";// 用户来源--》四川快医公司

	@Override
	public String execute() throws Exception {

		// 验证请求数据
		Map<String, String> map = validateRequestData(info);

		if(map.isEmpty()){
			// 接受请求数据，并对请求的数据进行解析
			// 1.获取用户数据
			UserInfoCustom userInfo = parseRequestData2UserInfo(info);
			userInfo.setUserSource(USER_SOURCE);
			// 2。获取上门服务订单数据
			OrderInfo orderInfo = parseRequestData(info);
	
			boolean createSuccess = kyInterfaceBo.addOrderInfo(orderInfo, userInfo,
					info.getKyOrderId());
			if (createSuccess){
				map.put("message", "订单创建成功");
			}
			else{
				map.put("message", "订单创建失败");
			}
		}
		
		String json = format2JsonWithMap(map);
		
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

	private Map<String, String> validateRequestData(SMFWOrderCustom info) {
		Map<String, String> map = new HashMap<String, String>();

		// 用户
		if (StringUtils.isBlank(info.getKyOrderId())) {
			map.put("kyOrderId", "上门服务订单id不能为空");
		}
		if (StringUtils.isBlank(info.getKyUserId())) {
			map.put("kyUserId", "用户id不能为空");
		}
		
		if (StringUtils.isBlank(info.getUserName())) {
			map.put("userName", "用户名称不能为空");
		}
		if (StringUtils.isBlank(info.getUserPhone())) {
			map.put("userPhone", "用户联系电话不能为空");
		}
		if (StringUtils.isBlank(info.getUserAddress())) {
			map.put("userAddress", "用户联系地址不能为空");
		}
		
		// 上门服务订单
		if (StringUtils.isBlank(info.getOrderNum())) {
			map.put("orderNum", "上门服务订单编号不能为空");
		}
		if (StringUtils.isBlank(info.getSubmitTime())) {
			map.put("submitTime", "下单时间不能为空,如yyyy-MM-dd HH:mm:ss");
		}
		if (StringUtils.isBlank(info.getBespokeDate())) {
			map.put("bespokeDate", "预约日期(如2015-09-09)不能为空");
		}
		if (StringUtils.isBlank(info.getBespokeTime())) {
			map.put("bespokeTime", "预约时间段（09:00-10:00）不能为空");
		}
		if (StringUtils.isBlank(info.getProductId()==null?"":info.getProductId()+"")) {
			map.put("productId", "产品ID不能为空");
		}
		if (StringUtils.isBlank(info.getTotalPrice()==null?"":info.getTotalPrice()+"")) {
			map.put("totalPrice", "订单金额不能为空");
		}
		if (StringUtils.isBlank(info.getUpdateTime())) {
			map.put("updateTime", "更新时间不能为空,如yyyy-MM-dd HH:mm:ss");
		}
		if (StringUtils.isBlank(info.getOrderStatus())) {
			map.put("orderStatus", "订单状态 不能为空");
		}
		if (StringUtils.isBlank(info.getPayMethod())) {
			map.put("payMethod", "支付方式不能为空");
		}
		
		/*
		 * 验证数据的逻辑性
		 */
		if(map.isEmpty()){
			
			Product pro = kyInterfaceBo.getProductByID(info.getProductId());
			if(pro==null){
				map.put("productId", "不存在该产品");
			}
			SimpleDateFormat sdf = null;
			
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			try{
				String time1 = info.getBespokeDate();
				Date date1 = sdf.parse(info.getBespokeDate());
				String time2 = sdf.format(date1);
				if(!time1.equals(time2)){
					map.put("bespokeDate", "格式不正确,如yyyy-MM-dd");
				}
			}catch(Exception e){
				map.put("bespokeDate", "格式不正确,如yyyy-MM-dd");
			}

			/*sdf = new SimpleDateFormat("HH:mm");
			try{
				String time1 = info.getBespokeTime();
				
				String[] timeArr = time1.split("-");
				Date timeArr_1 = sdf.parse(timeArr[0].trim());
				Date timeArr_2 = sdf.parse(timeArr[1].trim());
				
				String time_1 = sdf.format(timeArr_1);
				String time_2 = sdf.format(timeArr_2);
				String time3 = time_1+"-"+time_2;
				
				if(!time1.equals(time3)){
					map.put("bespokeTime", "格式不正确,如09:00-10:00");
				}
			}catch(Exception e){
				map.put("bespokeTime", "格式不正确,如09:00-10:00");
			}*/
			
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try{
				String time1 = info.getUpdateTime();
				Date date1 = sdf.parse(info.getUpdateTime());
				String time2 = sdf.format(date1);
				if(!time1.equals(time2)){
					map.put("updateTime", "格式不正确,如yyyy-MM-dd HH:mm:ss");
				}
			}catch(Exception e){
				map.put("updateTime", "格式不正确,如yyyy-MM-dd HH:mm:ss");
			}
			try{
				String time1 = info.getSubmitTime();
				Date date1 = sdf.parse(info.getSubmitTime());
				String time2 = sdf.format(date1);
				if(!time1.equals(time2)){
					map.put("submitTime", "格式不正确,如yyyy-MM-dd HH:mm:ss");
				}
			}catch(Exception e){
				map.put("submitTime", "格式不正确,如yyyy-MM-dd HH:mm:ss");
			}
			
			//---------------创建订单状态
			info.setOrderStatus("已下单");
			//----------------
			switch(info.getPayMethod()){
			case "1":
				info.setPayMethod("银联");
				break;
			case "2":
				info.setPayMethod("支付宝");
				break;
			case "3":
				info.setPayMethod("现场支付");
				break;
			case "4":
				info.setPayMethod("医保账户");
				break;
			case "5":
				info.setPayMethod("微信");
				break;
			case "6":
				info.setPayMethod("建行");
				break;
			case "7":
				info.setPayMethod("中行");
				break;
			//case "8":break;
			default:
				map.put("payMethod", "未知支付方式");
			}
		}
		
		return map;
	}

	// 解析数据，返回几面订单数据
	private OrderInfo parseRequestData(SMFWOrderCustom info) {
		OrderInfo orderInfo = new OrderInfo();

		orderInfo.setOrderNum(info.getOrderNum());
		orderInfo.setSubmitTime(info.getSubmitTime());
		orderInfo.setBespokeDate(info.getBespokeDate());
		orderInfo.setBespokeTime(info.getBespokeTime());
		orderInfo.setProductId(info.getProductId());
		orderInfo.setTotalPrice(info.getTotalPrice());
		orderInfo.setUpdateTime(info.getUpdateTime());
		orderInfo.setOrderStatus(info.getOrderStatus());
		orderInfo.setPayMethod(info.getPayMethod());

		return orderInfo;
	}

	// 解析请求数据，返回用户对象
	private UserInfoCustom parseRequestData2UserInfo(SMFWOrderCustom info)
			throws UnsupportedEncodingException {
		UserInfoCustom userInfo = new UserInfoCustom();

		userInfo.setUserSourceId(info.getKyUserId());
		
		userInfo.setParentName(info.getUserName());
		userInfo.setDetailAddress(info.getUserAddress());
		userInfo.setPhone(info.getUserPhone());
		
		return userInfo;
		/*userInfo.setOpenId(info.getOpenId());
		userInfo.setNickName(info.getNickName());
		userInfo.setPassword(info.getPassword());
		userInfo.setBirthday(info.getBirthday());
		userInfo.setSex(info.getSex());
		
		userInfo.setAccountBalance(info.getAccountBalance());
		userInfo.setAccountPoints(info.getAccountPoints());
		userInfo.setRegisterTime(info.getRegisterTime());
		userInfo.setUserStatus(info.getUserStatus());
		userInfo.setLastestPayTime(info.getLastestPayTime());
		userInfo.setRecommendNum(info.getRecommendNum());
		userInfo.setTotalConsume(info.getTotalConsume());
		userInfo.setUserLng(info.getUserLng());
		userInfo.setUserLat(info.getUserLat());
		userInfo.setUserProvince(info.getUserProvince());
		userInfo.setUserCity(info.getUserCity());
		userInfo.setUserArea(info.getUserArea());
		userInfo.setUserStreet(info.getUserStreet());
		
		
		
		userInfo.setBabyName(info.getBabyName());
		userInfo.setUseAppTimes(info.getUseAppTimes());
		userInfo.setIsLogin(info.getIsLogin());
		userInfo.setUserImage(info.getUserImage());*/

	}

	@Override
	public SMFWOrderCustom getModel() {
		return info;
	}

}
