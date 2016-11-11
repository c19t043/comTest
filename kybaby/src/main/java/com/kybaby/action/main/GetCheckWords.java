package com.kybaby.action.main;

import java.util.Random;


import com.kybaby.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import com.bcloud.msg.http.HttpSender;

/**
 * @ClassName:GetCheckWords
 * @Description:获取验证码
 * @author Hoolee
 * @date 2015年9月26日下午10:07:26
 */
public class GetCheckWords extends BaseAction {

	private static final long serialVersionUID = 1L;

	//通用属性
	private String mes;//反馈给前端的提示信息

	private String userPhone;//需要获取验证码的电话号码

	private String checkWords="";//反馈给前端的验证码

	String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
	String account = "003024";//账号
	String pswd = "Qiyiguo1919";//密码

	//String mobiles = "13541280713,15208361596";//手机号码，多个号码使用","分割
	//String content = "i love you  forever";//短信内容
	String contecnt="";
	boolean needstatus = true;//是否需要状态报告，需要true，不需要false
	String product = "";//产品ID(不用填写)
	String extno = "";//扩展码(不用填写)

	public String execute(){
		if(action.equals("regist")){
			System.out.println("Regist getCheckWords is begining...");
			if(userInfoBo.isRegist(userPhone)!=null){//未注册用户返回true
				mes="已注册";
				return "fail";
			}
		}

		if(action.equals("forgetPassword")){
			if(!(userInfoBo.isRegist(userPhone)!=null)){//未注册用户返回true
				mes="未注册";
				return "fail";
			}
		}

		if(action.equals("checkUserOldPhone")){
			System.out.println("CheckUserOldPhone is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				if(!(userInfoBo.userPhoneIsTrue(userId, userPhone))){
					mes="错误";
					return "fail";
				}
			}else{
				mes="未登录";
				return "fail";
			}
		}

		ActionContext.getContext().getSession().put("userPhone", userPhone);//记录发送验证码手机号的session用于验证用户在登录过程中是否更换手机号

		Random random = new Random();
		for(int i=0;i<6;i++){
			checkWords += random.nextInt(10);
		}
		System.out.print("checkWords=="+checkWords);
		contecnt="亲爱的用户，您的验证码是："+checkWords+"，请尽快输入验证！";
		System.out.println("content=="+contecnt);
		try {
			String returnString = HttpSender.send(uri, account, pswd, userPhone, contecnt, needstatus, product, extno);
			System.out.println(returnString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mes="操作成功";
		return "success";
	}

	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * @return the userPhone
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * @param userPhone the userPhone to set
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	/**
	 * @return the checkWords
	 */
	public String getCheckWords() {
		return checkWords;
	}

}
