package com.kybaby.kyinterface.pagetest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.kybaby.domain.OrderInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;
import com.opensymphony.xwork2.ActionSupport;

public class PageTestAction extends ActionSupport{
	
	private static final long serialVersionUID = -2249185920044387436L;

	private OrderInfo orderInfo;
	
	private UserInoculationAppointmentInfo innoculationInfo;
	
	private PageTestServiceImpl pageTestServiceImpl;
	
	public String list(){
		List<Object[]> orderInfoList = pageTestServiceImpl.getOrderInfo(orderInfo);
		JSONArray ja = new JSONArray();
		for(int i=0,len=orderInfoList.size();i<len;i++){
			Object[] info = orderInfoList.get(i);
			JSONObject jo = new JSONObject();
			jo.put("id", info[0]);
			jo.put("orderNum", info[1]);
			jo.put("submitTime", info[4]);
			jo.put("workDate", info[5]);
			jo.put("workTime", info[6]);
			jo.put("doctorID", info[3]);
			jo.put("productID", info[7]);
			jo.put("totalMoney", info[8]);
			jo.put("payType", info[15]);
			jo.put("phone", info[28]);
			jo.put("userName", info[44]);
			jo.put("orderStatus", info[10]);
			jo.put("address", info[42]);
			ja.add(jo);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		try {
			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		};
		return null;
	}
	
	public String doSMFWOrder(){
		String info = pageTestServiceImpl.doSMFWOrder(orderInfo);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		try {
			response.getWriter().write(info);
		} catch (IOException e) {
			System.out.println(e.toString());
		};
		return null;
	}
	
	public String innoculation_list(){
		List<Object[]> innoculationInfoList = pageTestServiceImpl.getInnoculationInfo(innoculationInfo);
		JSONArray ja = new JSONArray();
		for(int i=0,len=innoculationInfoList.size();i<len;i++){
			Object[] info = innoculationInfoList.get(i);
			JSONObject jo = new JSONObject();
			jo.put("id", info[0]);
			jo.put("username", info[1]);
			jo.put("submitDate", info[2]);
			jo.put("openDate", info[3]);
			jo.put("bespeak", info[4]);
			jo.put("orgName", info[5]);
			jo.put("orderStatus", info[6]);
			ja.add(jo);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		try {
			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		};
		return null;
	}
	
	public String doInnoculationOrder(){
		String info = pageTestServiceImpl.doInnoculationOrder(innoculationInfo);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		try {
			response.getWriter().write(info);
		} catch (IOException e) {
			System.out.println(e.toString());
		};
		return null;
	}
	
	
	public String org_list(){
		List<Object> OrgNameList = pageTestServiceImpl.getOrgName();
		JSONArray ja = new JSONArray();
		for(int i=0,len=OrgNameList.size();i<len;i++){
			Object info = OrgNameList.get(i);
			JSONObject jo = new JSONObject();
			jo.put("orgName", info);
			ja.add(jo);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		try {
			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		};
		return null;
	}
	
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public UserInoculationAppointmentInfo getInnoculationInfo() {
		return innoculationInfo;
	}

	public void setInnoculationInfo(UserInoculationAppointmentInfo innoculationInfo) {
		this.innoculationInfo = innoculationInfo;
	}

	public void setPageTestServiceImpl(PageTestServiceImpl pageTestServiceImpl) {
		this.pageTestServiceImpl = pageTestServiceImpl;
	}
}
