package com.wx;

import java.io.IOException;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.kybaby.action.BaseAction;
import com.wx.util.CommonUtil;
import com.wx.util.wechat.AesException;
import com.wx.util.wechat.WXBizMsgCrypt;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class WeChatApp extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3407416234366032985L;
	private String userName;
	private String userPassword;
	private String mes;
	private String action;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	protected HttpSession httpSession;
	

	
	@Override
	public String execute(){
		System.out.println("*************weChatApp*****************");		
		
		String method = request.getMethod();
		if(method.equals("POST")){
			System.out.println("weChat POST request");
  		    //从request请求中获取postData报文
  			
  			String postData;
			try {
				postData = CommonUtil.getPostData(request);
				Map<String, String> requestMap =CommonUtil.parseXmlString(postData.toString());
				// 发送方帐号
	  			String fromUserName = requestMap.get("FromUserName");
	  			String msgType = requestMap.get("MsgType");
	  			String eventType = requestMap.get("Event");
	  			System.out.println("requestMap:"+requestMap);
	  			//获取用户地位位置:requestMap:{FromUserName=onEEEsyJnQlSuhF8PyCNoe1KBSh4, Event=LOCATION, Precision=1260.000000, CreateTime=1434270521, Latitude=30.550592, Longitude=104.044067, ToUserName=gh_a19b161b30ec, MsgType=event}
	  			//获取地理位置的模式(1)用户进行对话时上报一次 :每次进入公众号会话时(打开公众号或从应用退回到公众号)，都会在进入时上报地理位置(2)用户进行对话后每隔5s上报一次 
	  			if (eventType.equals("LOCATION")){
	  				System.out.println(requestMap.get("Latitude"));
	  			}
	  			
	  			ActionContext.getContext().getSession().put("openId", fromUserName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			
  				
  			
		}else{//为get请求，进行校验
  			wechatConnCheck();
  		}
		
		return "fail";
		
	}
	
	public void wechatConnCheck() {
		System.out.println("weChat GET request");
  		String result="";
  		         // 微信加密签名
  		String msg_signature = request.getParameter("signature");
  				// 时间戳
  		String timestamp = request.getParameter("timestamp");
  				// 随机数
  		String nonce = request.getParameter("nonce");
  				// 随机字符串
  		String echostr = request.getParameter("echostr");
  		String sEchoStr; //需要返回的明文
		String sToken = wxBo.findPropertyValueByKey("sToken");
		String encodingAESKey=wxBo.findPropertyValueByKey("encodingAESKey");
		String corpId=wxBo.findPropertyValueByKey("corpId");
  		
  				
  		try{
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken,encodingAESKey, corpId);

  			sEchoStr = wxcpt.VerifyURL(msg_signature, timestamp,nonce, echostr);

  			if(null==sEchoStr){

          	   result=sToken;
             }else{
          	   result = sEchoStr;

             }
  	    }catch (AesException e){
  	    	System.out.println(e.getStackTrace().toString());
  	    	e.printStackTrace();
  	    	result=sToken;
  	    }finally{
  			try{
  				response.setContentType("json; charset=utf-8");  
  				response.setCharacterEncoding("UTF-8");
  				response.getWriter().write(result);
  				response.getWriter().flush();
  				response.getWriter().close();
  			}catch(IOException e){
  				//logger.error("IO错误", e);
  			}
  			
  		}	
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	@Override
	public String getMes() {
		return mes;
	}
	@Override
	public void setMes(String mes) {
		this.mes = mes;
	}
	@Override
	public String getAction() {
		return action;
	}
	@Override
	public void setAction(String action) {
		this.action = action;
	}

//	public WxBo getWxBo() {
//		return wxBo;
//	}
//
//	public void setWxBo(WxBo wxBo) {
//		this.wxBo = wxBo;
//	}
	
}
