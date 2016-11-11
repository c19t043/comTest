package com.kybaby.newbussiness.doctorclinic.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.util.ConstantManage;
/**
 * 新产生的类，用于解决扫描二维码跨域问题
 * @author xiongchao
 *
 */
public class ClinicOrderStatusAction extends NewBaseAction {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	
	/**
	 * 门诊订单信息
	 */
	private OrderInfoClinic orderInfoClinic;
	
	@Override
	public String execute() {
		if (action.equals("updateClinicOrderStatus")) {
			orderInfoClinic = this.doctorClinicOrderService.updateOrderStatusById(orderInfoClinic.getId(), orderInfoClinic.getOrderStatus());
			//给医生支付费用
			if ("已完成".equals(orderInfoClinic.getOrderStatus())) {
				//本单位门诊，立即结算
				if (ConstantManage.CLINIC_ORG_TYPE_0.equals(orderInfoClinic.getClinicOrgType())) {
					DoctorInfo doctorInfo = this.doctorInfoBo.getDoctorInfoBoById(orderInfoClinic.getDoctorInfo().getId());
					
					//订单提成
					double commissionMoney = Double.valueOf(orderInfoClinic.getTotalPrice())*Double.valueOf(orderInfoClinic.getCommission());
					//添加医生账户记录
					DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time=format.format(new Date());
					DoctorAccount doctorAccount = new DoctorAccount();
					doctorAccount.setAccountDesc("门诊服务");
					doctorAccount.setAmount(commissionMoney);
					doctorAccount.setDoctorId(doctorInfo.getId());
					doctorAccount.setType("+");
					doctorAccount.setUpdateTime(time);
					accountBo.saveDoctorAccount(doctorAccount);
					//医生余额
					double doctorBalance = doctorInfo.getDoctorBalance() + commissionMoney;
					doctorInfo.setDoctorBalance(doctorBalance);
					//更新医生余额信息
					doctorInfoBo.update(doctorInfo);
				} else {
					//当外单位坐诊，完成要求的门诊数量后，每增加一例门诊，立即结算
				}
			}
			
		}
		return "success";
	}
	
	public OrderInfoClinic getOrderInfoClinic() {
		return orderInfoClinic;
	}
	public void setOrderInfoClinic(OrderInfoClinic orderInfoClinic) {
		this.orderInfoClinic = orderInfoClinic;
	}
	@Override
	public String getMes() {
		return mes;
	}
	@Override
	public void setMes(String mes) {
		this.mes = mes;
	}
}
