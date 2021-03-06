package com.kybaby.action.main;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import com.opensymphony.xwork2.ActionContext;
import com.bcloud.msg.http.HttpSender;
import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;

public class HttpPost extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String mobile;
	private String randomNum = "";
	private String mes;
	private DoctorInfo doctorInfo;
	
	String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
	String account = "003024";//账号
	String pswd = "KYbaby_qiyico_19";//密码
	
	String contecnt="";
	boolean needstatus = true;//是否需要状态报告，需要true，不需要false
	String product = "";//产品ID(不用填写)
	String extno = "";//扩展码(不用填写)
	
	@Override
	public String execute() throws UnsupportedEncodingException {
		
		if(action.equals("others")) {
			//增加对发送验证码号码的预先验证，如果电话号码没有注册的话，就不向该电话号码发送验证码，如果可以发送的话，就将电话号码写入session中
			doctorInfo = doctorInfoBo.getDoctorInfoByPhone(mobile);
			if(doctorInfo==null){
				mes="未注册";
				return "success";
			}
			ActionContext.getContext().getSession().put("Phone", mobile);
			/*doctorInfo = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			if(!doctorInfo.getDoctorPhone().equals(mobile)){
				mes="请输入您的手机号";
				return "success";
			}
			ActionContext.getContext().getSession().put("Phone", mobile);*/
		}
		
		if(action.equals("change")){
			doctorInfo = doctorInfoBo.getDoctorInfoByPhone(mobile);
			if(doctorInfo==null){
				mes="未注册";
				return "success";
			}
			doctorInfo = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo==null||!doctorInfo.getDoctorPhone().equals(mobile)){
				mes="请输入您的手机号";
				return "success";
			}
			ActionContext.getContext().getSession().put("Phone", mobile);
		}
		
		if(action.equals("regist")){
			//增加对发送验证码号码的预先验证，如果号码已经注册的话，就不向该电话号码发送验证码
			doctorInfo = doctorInfoBo.getDoctorInfoByPhone(mobile);
			if(doctorInfo!=null){
				mes="已注册";
				return "success";
			}
			ActionContext.getContext().getSession().put("Phone", mobile);
		}
		Random random = new Random();
		for(int i=0;i<6;i++){
		    randomNum += random.nextInt(10);
		}
		System.out.print(randomNum);
		
		System.out.print("checkWords="+randomNum);
		contecnt="亲爱的用户，您的验证码是："+randomNum+"，请尽快输入验证！";
		try {
			String returnString = HttpSender.send(uri, account, pswd, mobile, contecnt, needstatus, product, extno);
			System.out.println(returnString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mes="操作成功";
		return "success";
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRandomNum() {
		return randomNum;
	}

	public void setRandomNum(String randomNum) {
		this.randomNum = randomNum;
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
