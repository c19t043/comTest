package com.wx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.BaseAction;
import com.wx.util.CommonUtil;
import com.wx.domain.WxToken;




public class SendMessage extends BaseAction {
	/**
	 * 给openId发送消息，可以群发，也可以给单个发送
	 * 传入参数：1.接收消息的用户openId 2.需要发送的内容
	 * 
	 */
	private static final long serialVersionUID = 5179862059368466962L;
	private String mes;
	private String action="";
	private String openId="";
	private HttpServletResponse response;
	protected HttpServletRequest request;
	private String content="";
	
	@Override
	public String execute() throws Exception{
		if(action.equals("sendMany")){
			System.out.println("send many Message is starting...");
			String sendMessageUrl=wxBo.findPropertyValueByKey("sendMsgUrl");

			String corpId=wxBo.findPropertyValueByKey("corpId");
			String corpSecret=wxBo.findPropertyValueByKey("corpSecret");
			
            WxToken tk = wxBo.getAccessToken(corpId);			
			WxToken tk1 =  CommonUtil.getToken(corpId, corpSecret, tk);
			
		    content = "{"+
		    		          "\"touser\":["+
		    		          "\"onEEEsyJnQlSuhF8PyCNoe1KBSh4\","+
		    		          "\"onEEEsyJnQlSuhF8PyCNoe1KBSh4\""+
		    		          "],"+
		    		          "\"msgtype\": \"text\","+
		    		          "\"text\": { \"content\": \"hello from boxer.\"}}";
			
			Boolean result=CommonUtil.sendToServer(sendMessageUrl,tk1.getAccessToken(),content);
			
			System.out.println("send many Message is ending....." + result);

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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
