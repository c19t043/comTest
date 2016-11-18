package com.kybaby.util;


import com.bcloud.msg.http.HttpSender;

/**
 * 短信提醒类
 * @author lihao
 *
 */
public class SendSms{

	private static final long serialVersionUID = 1L;

	String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
	String account = "003024";//账号
	String pswd = "KYbaby_qiyico_19";//密码

	boolean needstatus = true;//是否需要状态报告，需要true，不需要false
	String product = "";//产品ID(不用填写)
	String extno = "";//扩展码(不用填写)
	/**
	 * 发送短信提醒
	 * @param userPhone 电话号码
	 * @param contecnt 短信内容
	 * @return
	 */
	public String sendInfo(String userPhone, String contecnt){
		try {
			String returnString = HttpSender.send(uri, account, pswd, userPhone, contecnt, needstatus, product, extno);
			System.out.println(returnString);
			return returnString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
