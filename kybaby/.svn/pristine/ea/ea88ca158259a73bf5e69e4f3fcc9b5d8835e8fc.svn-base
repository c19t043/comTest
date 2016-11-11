package com.kybaby.action.main;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.kybaby.util.EncryptUtil;
import com.wx.BaseAction;
import com.wx.domain.WxToken;
import com.wx.util.CommonUtil;
import com.wx.util.third.HttpPost;

public class CardManage extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5179862059368466962L;
	private String mes;
	private String action;
	private String strAuthCode="";
	
	private String strOrderNum="";
	private String totalFee="";
	
	private String sTransationId="";//验证订单
	
	public String execute(){
		System.out.println("*************card manage begin****************:"+action);
		if(action.equals("createCard")){
			String strUrl= "https://api.weixin.qq.com/card/create?access_token=ACCESS_TOKEN";
			String startTime = String.valueOf(System.currentTimeMillis());
			String endTime = String.valueOf(System.currentTimeMillis()+3600*1000*240);
			startTime=startTime.substring(0, startTime.length()-3);
			endTime=endTime.substring(0, endTime.length()-3);
			String strJson = 
					
				"{ "+
					"\"card\": {"+
					 "  \"card_type\": \"GROUPON\","+
					 "  \"groupon\": {"+
					 "      \"base_info\": {"+
					"           \"logo_url\": "+
				"	\"http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0\","+
				"	           \"brand_name\":\"海底捞\","+
				"	           \"code_type\":\"CODE_TYPE_TEXT\","+
				"	           \"title\": \"132元双人火锅套餐\","+
				"	           \"sub_title\": \"周末狂欢必备\","+
				"	           \"color\": \"Color010\","+
				"	           \"notice\": \"使用时向服务员出示此券\","+
				"	           \"service_phone\": \"020-88888888\","+
				"	           \"description\": \"不可与其他优惠同享\n如需团购券发票，请在消费时向商户提出\n店内均可使用，仅限堂食\","+
				"	           \"date_info\": {"+
				"	               \"type\": \"DATE_TYPE_FIX_TIME_RANGE\","+
				"	               \"begin_timestamp\": "+startTime+" ,"+
				"	               \"end_timestamp\": "+endTime+" "+
				"	           },"+
				"	           \"sku\": {"+
				"	               \"quantity\": 500000"+
				"	           },"+
				"	           \"get_limit\": 3,"+
				"	           \"use_custom_code\": false,"+
				"	           \"bind_openid\": false,"+
				"	           \"can_share\": true,"+
				"	         \"can_give_friend\": true,"+
				"	           \"location_id_list\" : [123, 12321, 345345],"+
				"	           \"custom_url_name\": \"立即使用\","+
				"	           \"custom_url\": \"http://www.qq.com\","+
				"	           \"custom_url_sub_title\": \"6个汉字tips\","+
				"	           \"promotion_url_name\": \"更多优惠\","+
				"	         \"promotion_url\": \"http://www.qq.com\","+
				"	           \"source\": \"大众点评\"   "+
				"	       },"+
				"	       \"deal_detail\": \"以下锅底2选1（有菌王锅、麻辣锅、大骨锅、番茄锅、清补凉锅、酸菜鱼锅可选）：\n大锅1份 12元\n小锅2份 16元 \"}"+
				"	 }"+
				"	}" ;
			
			String corpId=wxBo.findPropertyValueByKey("corpId");
			String corpSecret=wxBo.findPropertyValueByKey("corpSecret");
			WxToken tk = wxBo.getAccessToken(corpId);			
			WxToken tk1 =  CommonUtil.getToken(corpId, corpSecret, tk);
			
			Boolean result=CommonUtil.sendToServer(strUrl,tk1.getAccessToken(),strJson);
			
		    return "success";
		} else if(action.equals("micropay")){
			
			String requestUrl="https://api.mch.weixin.qq.com/pay/micropay";
			String strAppId=wxBo.findPropertyValueByKey("corpId");
			String mchId=wxBo.findPropertyValueByKey("mchId");
			String strAPISecret=wxBo.findPropertyValueByKey("APISecret");
			//String strAuthCode="130078952890464389";
			String strIp="";
			InetAddress addr;
			try {
				addr = InetAddress.getLocalHost();
				strIp=addr.getHostAddress().toString();//获得本机IP
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 String strTradeNum=String.valueOf(System.currentTimeMillis())+strOrderNum;
			
			 //String signString="appid="+strAppId+"&auth_code="+strAuthCode+"&body=microPay&mch_id="+mchId+"&nonce_str=8aaee146b1dee7cec9100add9b96cbe2&out_trade_no=1415757673&spbill_create_ip="+strIp+"&total_fee=1&key=jiofapefamfaoekfpakfmpoakepfafaf";    
			 String signString="appid="+strAppId+"&auth_code="+strAuthCode+"&body=weiwuye&mch_id="+mchId+"&nonce_str=8aaee146b1dee7cec9100add9b96cbe2&out_trade_no="+strTradeNum+"&spbill_create_ip="+strIp+"&total_fee="+totalFee+"&key="+strAPISecret;
			 System.out.println("signString1:"+signString);
			 signString=EncryptUtil.getMD5Str(signString); 
		     signString=signString.toUpperCase();
		     System.out.println("signString2:"+signString);
		     
			String xmlStr="<xml>"+
					"<appid>"+strAppId+"</appid>"+
					"<auth_code>"+strAuthCode+"</auth_code>"+
					"<body>weiwuye</body>"+
					"<mch_id>"+mchId+"</mch_id>"+
					"<nonce_str>8aaee146b1dee7cec9100add9b96cbe2</nonce_str>"+
					"<out_trade_no>"+strTradeNum+"</out_trade_no>"+
					"<spbill_create_ip>"+strIp+"</spbill_create_ip>"+
					"<total_fee>"+totalFee+"</total_fee>"+
					"<sign>"+signString+"</sign>"+
					"</xml> ";
			 System.out.println("xmlStr:"+xmlStr);
			//JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
			String result = HttpPost.doPostXml(requestUrl,xmlStr, "UTF-8");
			mes=result;
			System.out.println(result);
			return "success";
			
		} else if(action.equals("testNameList")){
			String strUrl= "https://api.weixin.qq.com/card/testwhitelist/set?access_token=TOKEN";
			String strJson = 
				   "{" +
					"  \"openid\": [" +
					"             \"onEEEsyJnQlSuhF8PyCNoe1KBSh4\", " +
					"             \"onEEEs7WdfpmkiOtE3GfcHrc5wt0\"" +
					"                      ]," +
					"\"username\": [" +
					 "            \"wo181767739\"" +
					"                       ]" +
					"}";
			
			String corpId=wxBo.findPropertyValueByKey("corpId");
			String corpSecret=wxBo.findPropertyValueByKey("corpSecret");
			WxToken tk = wxBo.getAccessToken(corpId);			
			WxToken tk1 =  CommonUtil.getToken(corpId, corpSecret, tk);
			
            Boolean result=CommonUtil.sendToServer(strUrl,tk1.getAccessToken(),strJson);
			
		    return "success";
		} else if(action.equals("checkOrderStatus")){
			String strCheckUrl="https://api.mch.weixin.qq.com/pay/orderquery";
			System.out.println("check order status to wx loading.............");
			String strAppId=wxBo.findPropertyValueByKey("kybabyAppId");
			String mchId=wxBo.findPropertyValueByKey("kybabyMchId");
			String strAPISecret=wxBo.findPropertyValueByKey("kybabyAPISecret");
		    
			String signString="appid="+strAppId+"&mch_id="+mchId+"&nonce_str=8aaee146b1dee7cec9100add9b96cbe2&out_trade_no="+strOrderNum+"&key="+strAPISecret;
			signString=EncryptUtil.getMD5Str(signString); 
		    signString=signString.toUpperCase();
		    
		    String xmlStr="<xml>"+
					"<appid>"+strAppId+"</appid>"+
					"<mch_id>"+mchId+"</mch_id>"+
					"<nonce_str>8aaee146b1dee7cec9100add9b96cbe2</nonce_str>"+
					"<out_trade_no>"+strOrderNum+"</out_trade_no>"+
					"<sign>"+signString+"</sign>"+
					"</xml> ";
		    
			String result = HttpPost.doPostXml(strCheckUrl,xmlStr, "UTF-8");
			mes=result;
			System.out.println("checkOrder's result is"+result);
			return "success";
		}
		
		else if(action.equals("getWxNotice"))
		{
			
			
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

	public void setStrAuthCode(String strAuthCode) {
		this.strAuthCode = strAuthCode;
	}

	public String getStrOrderNum() {
		return strOrderNum;
	}

	public void setStrOrderNum(String strOrderNum) {
		this.strOrderNum = strOrderNum;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public void setsTransationId(String sTransationId) {
		this.sTransationId = sTransationId;
	}
	
}
