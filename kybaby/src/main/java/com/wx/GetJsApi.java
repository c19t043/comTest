package com.wx;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.wx.domain.WxToken;
import com.wx.util.CommonUtil;
import com.wx.util.third.HttpPost;

public class GetJsApi extends BaseAction {
	/**
	 * 调用微信统一支付接口,获取prepayId
	 */
	private static final long serialVersionUID = 5179862059368466962L;
	private String mes;
	private String action;
    private String result="";
    private String jsApiTicket="";
    
	
	public String execute() throws JSONException{
		System.out.println("*************get js api*****************");
		if(action.equals("getJsApiTicket")){			
			String corpId=wxBo.findPropertyValueByKey("corpId");
			String corpSecret=wxBo.findPropertyValueByKey("corpSecret");
			
            WxToken tk = wxBo.getAccessToken(corpId);			
			WxToken tk1 =  CommonUtil.getToken(corpId, corpSecret, tk);
			
			String strUrl="https://api.weixin.qq.com/cgi-bin/ticket/getticket";
			
			String accessToken=tk1.getAccessToken();
			Map<String, String> requestMap = new HashMap<String,String>();
			requestMap.put("access_token",accessToken);
			requestMap.put("type", "jsapi");
			
			//result = HttpPost.doPostXml("https://api.mch.weixin.qq.com/pay/unifiedorder",xmlStr, "UTF-8");
			result=HttpPost.doGet(strUrl, requestMap, "UTF-8");
			JSONObject objResult=new JSONObject(result);
			
			System.out.println(objResult.get("ticket"));
			jsApiTicket=(String) objResult.get("ticket");
		    return "success";
		}
		return "success";
		
	}
	
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public String getJsApiTicket() {
		return jsApiTicket;
	}

	public void setJsApiTicket(String jsApiTicket) {
		this.jsApiTicket = jsApiTicket;
	}

	
}
