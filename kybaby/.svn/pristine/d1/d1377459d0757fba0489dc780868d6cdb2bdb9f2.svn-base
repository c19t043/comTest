package com.wx;

import com.wx.util.third.HttpPost;

public class GetPrepayId extends BaseAction {
	/**
	 * 调用微信统一支付接口,获取prepayId
	 */
	private static final long serialVersionUID = 5179862059368466962L;
	private String mes;
	private String action;
    private String tradeNo="";
    private String remoteIp = "";
    private String nonceStr = "";
    private String signStr = "";
    private String userOpenId = "";
    private String body="";
    private String mchId="";
    private String totalFee="";
    private String result="";
    
	
	public String execute(){
		System.out.println("*************get prepay id*****************");
		if(action.equals("getPrepayId")){
			String appId=wxBo.findPropertyValueByKey("corpId");
			String notifyUrl=wxBo.findPropertyValueByKey("notify_url");
			
			String xmlStr = "<xml><appid>"+appId+"</appid><body>"+body+"</body><mch_id>"+mchId+"</mch_id><nonce_str>"+nonceStr+"</nonce_str><notify_url>"+notifyUrl+"</notify_url><openid>"+userOpenId+"</openid><out_trade_no>"+tradeNo+"</out_trade_no><spbill_create_ip>"+remoteIp+"</spbill_create_ip><total_fee>"+totalFee+"</total_fee><trade_type>JSAPI</trade_type><sign>"+signStr+"</sign></xml>";
			System.out.println(xmlStr);
			result = HttpPost.doPostXml("https://api.mch.weixin.qq.com/pay/unifiedorder",xmlStr, "UTF-8");
			System.out.println(result);
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

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public void setSignStr(String signStr) {
		this.signStr = signStr;
	}

	public void setUserOpenId(String userOpenId) {
		this.userOpenId = userOpenId;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	
}
