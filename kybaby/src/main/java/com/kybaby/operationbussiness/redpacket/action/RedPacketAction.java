package com.kybaby.operationbussiness.redpacket.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import com.kybaby.action.NewBaseAction;
import com.kybaby.operationbussiness.redpacket.domain.RedPacket;
import com.kybaby.operationbussiness.redpacket.domain.RedPacketSet;
import com.opensymphony.xwork2.ActionContext;
import com.wx.util.third.WeiXin;

public class RedPacketAction extends NewBaseAction {

	private static final long serialVersionUID = 6594694092294992247L;
	private String mes="成功";//反馈到前端的提示信息
	private String openId;//反馈到页面的openId
	private String token="";//反馈到页面的token
	private String sendAmount;//前台显示，给用户红包的发放金额

	public String execute() {
		openId = (String) ActionContext.getContext().getSession().get("operationbussiness_openid");
		token = (String) ActionContext.getContext().getSession().get("operationbussiness_access_token");
		if (openId==null || "".equals(openId)) {
			mes = "openId为空";
			return "fail";
		}
		getUserOpenIdInfo(openId, token);
		if("sendRedPacket".equals(action)) {
			List<RedPacket> redPacketList = redPacketBo.getSendRedPacketListByUser(openId);
			if (redPacketList!=null && redPacketList.size() > 0) {
				mes = "红包已领取";
				return "success";
			}
			RedPacketSet redPacketSet = redPacketBo.getRedPacketSetInfo();
			float moneyFloatAmount = redPacketBo.getGrantAmount(redPacketSet);
			//判定剩余金额是否大于需要发放的金额
			if (moneyFloatAmount==0) {
				mes = "红包发放完毕";
				return "success";
			}
			sendAmount = moneyFloatAmount + "";
			int moneyIntAmount = (int)(moneyFloatAmount*100);
			String result = redPacketBo.sendRedpacket(openId, moneyIntAmount);
			if (result!=null && result.indexOf("发放成功")!=-1) {
				redPacketBo.saveUserSendRedPacketInfo(openId, moneyFloatAmount, redPacketSet);
				mes = "发放成功";
			} else {
				mes = "发放失败";
			}
		}
		return "success";
	}
	
	/**
	 * 得到用户的openId
	 * @return
	 */
	public void getUserOpenIdInfo(String openId, String token) {
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN"; 
		//String url = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN"; 
    	String urlFormat = String.format(url, token, openId);
    	System.out.println("urlFormat ..." + urlFormat);
    	try {
    		// 创建HttpClient实例     
    		HttpClient httpClient = new HttpClient();  
	        // 创建Get方法实例     
	        GetMethod method = new GetMethod(urlFormat);
	        httpClient.executeMethod(method);
	        String responseString = method.getResponseBodyAsString();		        
	        method.releaseConnection();
	        System.out.println("responseString=============" + responseString);
	        JSONObject obj = JSONObject.fromObject(responseString);
	        if (obj != null) {
	        	//int subscribe = obj.getInt("subscribe");
	        	String nickname = obj.getString("nickname");
	        	String city = obj.getString("city");
	        	//System.out.println("=====" + subscribe + "=====" + nickname + "=====" + city);
	        	System.out.println("=====" + nickname + "=====" + city);
	        }
		} catch(Exception e) {
			System.out.println("getUserOpenId error=======" + e.getMessage());
		}
	}
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getSendAmount() {
		return sendAmount;
	}
	public void setSendAmount(String sendAmount) {
		this.sendAmount = sendAmount;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
