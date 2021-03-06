package com.kybaby.newbussiness.doctorclinic.action;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicOtherContactsInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgTimeSetting;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePracticeOrgInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.doctorclinic.domain.OrgSetShow;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.util.ConstantManage;
import com.opensymphony.xwork2.ActionContext;

public class DoctorClinicOrderAction extends NewBaseAction {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 医生信息
	 */
	private DoctorInfo doctorInfo;
	/**
	 * 多点执业规则显示对象
	 */
	private OrgSetShow orgSetShow;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 门诊订单信息
	 */
	private List<OrderInfoClinic> orderInfoClinicList = new ArrayList<OrderInfoClinic>();
	/**
	 * 门诊订单信息
	 */
	private OrderInfoClinic orderInfoClinic;
	/**
	 * 联系人信息
	 */
	private ClinicOtherContactsInfo clinicOtherContactsInfo;
	
	@Override
	public String execute() {
		doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
		if(doctorInfo==null){
			mes="请登录";
			return "fail";
		}
		doctorInfo = this.doctorInfoBo.getDoctorInfoBoById(doctorInfo.getId());
		if(action.equals("getClinicOrderList")) {
			//获取当前医生的订单列表
			if (orderInfoClinic==null) {
				orderInfoClinic = new OrderInfoClinic();
			}
			orderInfoClinic.setDoctorInfo(doctorInfo);
			this.orderInfoClinicList = this.doctorClinicOrderService.getOrderInfoClinicList(orderInfoClinic);
			if(orderInfoClinicList != null){
				for(OrderInfoClinic orderInfoClinic : orderInfoClinicList){
					//获取订单联系人信息
					ClinicOtherContactsInfo coci = 
							this.doctorClinicOrderService.getOrderContactsInfoByOrderId(orderInfoClinic.getId());
					orderInfoClinic.getUserInfo().setParentName(coci.getOtherName());
				}
			}
		} else if (action.equals("getClinicOrderDetail")) {
			//获取订单详情
			orderInfoClinic = this.doctorClinicOrderService.getOrderInfoClinicById(orderInfoClinic.getId());
			//获取订单联系人信息
			clinicOtherContactsInfo = this.doctorClinicOrderService.getOrderContactsInfoByOrderId(orderInfoClinic.getId());
		} else if (action.equals("orgSetShow")) {
			//获取订单详情
			DoctorMorePracticeOrgInfo moreOrgInfo = 
					this.doctorClinicService.getDoctorMorePracticeOrgInfoByid(orgSetShow.getDoctorMorePracticeOrgInfo().getId());
			OrderInfoClinic order = new OrderInfoClinic();
			order.setClinicOrgType(ConstantManage.CLINIC_ORG_TYPE_1);
			order.setClinicAddress(moreOrgInfo.getId().toString());//地址当id用
			HospitalPosition currentDoctorPosition = 
					doctorClinicOrderService.getHospitalPositionByDoctor(doctorInfo,order);
			List<DoctorMoreOrgTimeSetting> timeList = doctorClinicService.getMoreOrgTimeSettingList(orgSetShow.getDoctorMorePracticeOrgInfo());
			orgSetShow.setClinicMoneyOut(currentDoctorPosition.getClinicMoneyOut());
			orgSetShow.setHalfDayMoney(currentDoctorPosition.getHalfDayMoney());
			orgSetShow.setBaseSalary(currentDoctorPosition.getBaseSalary());
			orgSetShow.setCommissionPerCase(currentDoctorPosition.getCommissionPerCase());
			
			if(timeList != null) {
				for(DoctorMoreOrgTimeSetting dmots : timeList) {
					if (ConstantManage.AM_SET.equals(dmots.getTimeName())) {
						orgSetShow.setAmCount(dmots.getRequireClinicNum());
						orgSetShow.setAmStartTime(dmots.getStartTime());
						orgSetShow.setAmEndTime(dmots.getEndTime());
					} else if (ConstantManage.PM_SET.equals(dmots.getTimeName())) {
						orgSetShow.setPmCount(dmots.getRequireClinicNum());
						orgSetShow.setPmStartTime(dmots.getStartTime());
						orgSetShow.setPmEndTime(dmots.getEndTime());
					}
				}
			}
			
		}
		return "success";
	}
	
	
	public List<OrderInfoClinic> getOrderInfoClinicList() {
		return orderInfoClinicList;
	}
	public void setOrderInfoClinicList(List<OrderInfoClinic> orderInfoClinicList) {
		this.orderInfoClinicList = orderInfoClinicList;
	}
	public OrderInfoClinic getOrderInfoClinic() {
		return orderInfoClinic;
	}
	public void setOrderInfoClinic(OrderInfoClinic orderInfoClinic) {
		this.orderInfoClinic = orderInfoClinic;
	}
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	@Override
	public String getMes() {
		return mes;
	}
	@Override
	public void setMes(String mes) {
		this.mes = mes;
	}
	public ClinicOtherContactsInfo getClinicOtherContactsInfo() {
		return clinicOtherContactsInfo;
	}
	public void setClinicOtherContactsInfo(
			ClinicOtherContactsInfo clinicOtherContactsInfo) {
		this.clinicOtherContactsInfo = clinicOtherContactsInfo;
	}
	public OrgSetShow getOrgSetShow() {
		return orgSetShow;
	}
	public void setOrgSetShow(OrgSetShow orgSetShow) {
		this.orgSetShow = orgSetShow;
	}
	
}
