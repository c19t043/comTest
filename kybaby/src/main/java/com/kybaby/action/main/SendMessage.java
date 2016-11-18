package com.kybaby.action.main;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.bcloud.msg.http.HttpSender;
import com.kybaby.action.BaseAction;
import com.kybaby.domain.Admin;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.UserInfo;

/**
 * @ClassName:SendMessage
 * @Description:向用户或者医生发送短信信息
 * @author Hoolee
 * @date 2015年10月9日下午2:41:43
 */
public class SendMessage extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到页面的提示信息
	private String orderNum;//页面传递的订单编号
	private String sendWords;//页面传递的消息,用于区分是取消订单还是下单
	
	String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
	String account = "003024";//账号
	String pswd = "KYbaby_qiyico_19";//密码

	//String mobiles = "13541280713,15208361596";//手机号码，多个号码使用","分割
	//String content = "i love you  forever";//短信内容
	String contecnt="";
	boolean needstatus = true;//是否需要状态报告，需要true，不需要false
	String product = "";//产品ID(不用填写)
	String extno = "";//扩展码(不用填写)
	
	public String execute(){
		OrderInfo order=orderInfoBo.getOrderInstanceByOrderNum(orderNum);
		if(action.equals("toUser")){
			System.out.println("SendMsg to user!");
			if(sendWords.equals("预约")){
//				long doctorId=order.getDoctorId();
//				DoctorInfo doctor=doctorInfoBo.getDoctorInfoByDoctorId(doctorId);
//				String doctorName=doctor.getDoctorName();
				String serviceDate=order.getBespokeDate();
				String serviceTime=order.getBespokeTime();
				long productId=order.getProductId();
				String productName=productBo.getProductNameById(productId);
				
				//"亲爱的用户，您所预约专业医生将于X年XX月XX日 XX-XX点上门服务。详情请查“我的订单”
				//contecnt="亲爱的用户，您已预约"+doctorName+"医生"+serviceDate+" "+serviceTime+"为您的孩子服务"+productName+"。订单编号为："+orderNum+"。";
				contecnt="亲爱的用户，您所预约专业医生将于"+serviceDate+" "+serviceTime+"上门服务。详情请查'我的订单'";
				long userId=order.getUserId();
				UserInfo user=userInfoBo.getUserById(userId);
				String userPhone=user.getPhone();
				try {
					if(order.getDoctorId() != null){
						String returnString = HttpSender.send(uri, account, pswd, userPhone, contecnt, needstatus, product, extno);
						System.out.println(returnString);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				mes="操作成功";
				return "success";
				
			}
		}else if(action.equals("toDoctor")){
			System.out.println("SendMsg to doctor!");
			if(sendWords.equals("预约")){
				String serviceDate=order.getBespokeDate();
				String serviceTime=order.getBespokeTime();
				long productId=order.getProductId();
				String productName=productBo.getProductNameById(productId);
				long userId=order.getUserId();
				UserInfo user=userInfoBo.getUserById(userId);
				String userName=user.getParentName();
				
				if(order.getDoctorId() == null){//没有医生，给运营和用户发短信提醒
					String adminName = "jack";
					String phone = "13541280713";
					Admin admin = this.adminBo.findAdminByAdminNmame(adminName);
					if(admin != null){
						phone = admin.getContactPhone();
					}
					String contecntToAdmin="订单号："+order.getOrderNum()+"未指派服务医生，请尽快处理。";
					String contecntToUser="亲爱的用户，您已下单成功。详情请查'我的订单'";
					try {
						String returnString1 = HttpSender.send(uri, account, pswd, phone, contecntToAdmin, needstatus, product, extno);
						String returnString2 = HttpSender.send(uri, account, pswd, user.getPhone(), contecntToUser, needstatus, product, extno);
						System.out.println(returnString1);
						System.out.println(returnString2);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{//有医生正常发送
					long doctorId=order.getDoctorId();
					DoctorInfo doctor=doctorInfoBo.getDoctorInfoByDoctorId(doctorId);
					String userPhone=doctor.getDoctorPhone();
					String doctorName=doctor.getDoctorName();
					//"亲爱的XXX医生，XXX预约您XX年XX月XX日 XX-XX点上门服务，请注意查看订单。
					//contecnt="亲爱的"+doctorName+"医生，"+userName+"预约您"+serviceDate+" "+serviceTime+"上门服务，请注意查看订单，订单编号为："+orderNum+"。";
					contecnt="亲爱的"+doctorName+"医生，"+userName+"预约您"+serviceDate+" "+serviceTime+"上门服务，请注意查看订单。";
					try {
						String returnString = HttpSender.send(uri, account, pswd, userPhone, contecnt, needstatus, product, extno);
						System.out.println(returnString);
					} catch (Exception e) {
						e.printStackTrace();
					}
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

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public void setSendWords(String sendWords) {
		this.sendWords = sendWords;
	}
	
}
