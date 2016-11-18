package com.kybaby.action.main;

import com.bcloud.msg.http.HttpSender;
import com.kybaby.action.NewBaseAction;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicOtherContactsInfo;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;

/**
 * @ClassName:SendMessageByClinic
 * @Description:向用户或者医生发送短信信息（门诊和多点执业）
 * @author xiongchao
 * @date 2015年2月20日下午2:41:43
 */
public class SendMessageByClinic extends NewBaseAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到页面的提示信息
	private OrderInfoClinic orderInfoClinic;//门诊订单对象
	private String sendWords;//页面传递的消息,用于区分是取消订单还是下单
	
	String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
	String account = "003024";//账号
	String pswd = "KYbaby_qiyico_19";//密码

	String contecnt="";
	boolean needstatus = true;//是否需要状态报告，需要true，不需要false
	String product = "";//产品ID(不用填写)
	String extno = "";//扩展码(不用填写)
	
	public String execute(){
		System.out.println("门诊预约发短信action="+action + "****sendWords="+sendWords + "**orderInfoClinic.getId()="+orderInfoClinic.getId());
		OrderInfoClinic getOrderInfoClinic = clinicOrderService.getOrderInfoClinicById(orderInfoClinic.getId());
		if(action.equals("toUser")){
			if(sendWords.equals("预约门诊")){
				String serviceDate = getOrderInfoClinic.getAppointmentDate();
				String serviceTime = getOrderInfoClinic.getAppointmentBeganTime();
				String doctorName = getOrderInfoClinic.getDoctorInfo().getDoctorName();
				ClinicOtherContactsInfo clinicOtherContactsInfo = clinicOrderService.getClinicOtherContactsInfoByOrderId(orderInfoClinic.getId());
				//String userName = clinicOtherContactsInfo.getOtherName();
				String userPhone = clinicOtherContactsInfo.getOtherPhone();
				
				contecnt="亲爱的用户，您预约" + doctorName + "医生的门诊服务，时间：" + serviceDate 
						+ " " + serviceTime + "，地址：" 
						//+ getOrderInfoClinic.getDoctorInfo().getDoctorEmployer() + " " 
						+ getOrderInfoClinic.getClinicAddress()
					    + "。详情请查'我的订单'";
				try {
					String returnString = HttpSender.send(uri, account, pswd, userPhone, contecnt, needstatus, product, extno);
				} catch (Exception e) {
					e.printStackTrace();
				}
				mes="操作成功";
				return "success";
			}
		}else if(action.equals("toDoctor")){
			if(sendWords.equals("预约门诊")){
				String serviceDate = getOrderInfoClinic.getAppointmentDate();
				String serviceTime = getOrderInfoClinic.getAppointmentBeganTime();
				String doctorPhone = getOrderInfoClinic.getDoctorInfo().getDoctorPhone();
				String doctorName = getOrderInfoClinic.getDoctorInfo().getDoctorName();
				ClinicOtherContactsInfo clinicOtherContactsInfo = clinicOrderService.getClinicOtherContactsInfoByOrderId(orderInfoClinic.getId());
				String userName = clinicOtherContactsInfo.getOtherName();
				//String userPhone = clinicOtherContactsInfo.getOtherPhone();
				
				contecnt = "亲爱的" + doctorName + "医生，" + userName+ "预约您"
						+ serviceDate + " " + serviceTime
						+ "的门诊服务。详情请查'我的订单'";
				try {
					String returnString = HttpSender.send(uri, account, pswd, doctorPhone, contecnt, needstatus, product, extno);
				} catch (Exception e) {
					e.printStackTrace();
				}
				mes="操作成功";
				return "success";
			}
		}
		return "success";
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public OrderInfoClinic getOrderInfoClinic() {
		return orderInfoClinic;
	}

	public void setOrderInfoClinic(OrderInfoClinic orderInfoClinic) {
		this.orderInfoClinic = orderInfoClinic;
	}

	public String getSendWords() {
		return sendWords;
	}

	public void setSendWords(String sendWords) {
		this.sendWords = sendWords;
	}
}
