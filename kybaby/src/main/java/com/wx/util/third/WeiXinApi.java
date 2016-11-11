package com.wx.util.third;

import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import com.wx.BaseAction;
import com.wx.util.finals.Const;
import com.wx.bo.WxBo;

public class WeiXinApi extends BaseAction{

	 public static String getCodeRequest(){
		 
		 String  codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=gc#wechat_redirect";
		 String result = "";
		 result  = String.format(codeUrl, urlEnodeUTF8(Const.AppId),urlEnodeUTF8(Const.REDIRECT_URI),Const.SCOPE);
		 
		 try
		 {
		// 创建HttpClient实例     
	        HttpClient httpClient = new HttpClient();  
	        // 创建Get方法实例     
	        GetMethod method = new GetMethod(result);
	        httpClient.executeMethod(method);
	        String responseString = method.getResponseBodyAsString();		        
	        method.releaseConnection();
	        System.out.println("get weixin ..."+responseString);
	        JSONObject obj = JSONObject.fromObject(responseString);
			 if (obj != null)
			 {
				 String code = obj.getString("code");
				 System.out.println("get weixin code..."+code);
				 return code;
			 }
		 }catch(Exception ex)
		 {
			 System.out.println(ex.getMessage());			 
		 }
			 
	        return "";
	    }
	    private static String urlEnodeUTF8(String str){
	        String result = str;
	        try {
	            result = URLEncoder.encode(str,"UTF-8");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }

	    public static WeiXin getWeiXin(String code)
	    {    
	    	 String openIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
	    	 String appId=wxBo.findPropertyValueByKey("corpId");
	    	 String appSecret=wxBo.findPropertyValueByKey("corpSecret");
	    	 String result = null;
	    	 
			 //result  = String.format(openIdUrl, urlEnodeUTF8(Const.AppId),urlEnodeUTF8(Const.AppSecrect),code);
	    	 result=String.format(openIdUrl, appId, appSecret, code);
			 System.out.println("result ..."+result);
			 try
			 {
			 // 创建HttpClient实例     
		        HttpClient httpClient = new HttpClient();  
		        // 创建Get方法实例     
		        GetMethod method = new GetMethod(result);
		        httpClient.executeMethod(method);
		        String responseString = method.getResponseBodyAsString();		        
		        method.releaseConnection();
		        System.out.println("get weixinnumber ..."+responseString);
		        JSONObject obj = JSONObject.fromObject(responseString);
				 if (obj != null)
				 {
					 String openId = obj.getString("openid");
					 String token = obj.getString("access_token");
					 
					 //String userInfoUrl="https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
					 //result = String.format(userInfoUrl, token,openId);
					 
					// httpClient = new HttpClient();  
				        // 创建Get方法实例     
				        //method = new GetMethod(result);
				       // httpClient.executeMethod(method);
				        //responseString = method.getResponseBodyAsString();		        
				        //method.releaseConnection();
				        
				       // JSONObject userInfo = JSONObject.fromObject(responseString);
				        //System.out.println("get weixinToken ..."+responseString);
				        
				       // if (userInfo != null)
				        //{
				        	WeiXin wei = new WeiXin();
				        	//wei.setNickName(userInfo.getString("nickname"));
				        	//wei.setCity(userInfo.getString("city"));
				        	wei.setOpenId(openId);
				        	wei.setToken(token);
				        	//wei.setHeadimgurl(userInfo.getString("headimgurl"));
				        	//wei.setSex(userInfo.getString("sex"));
				        	
				        	//System.out.println("get nickName ..."+wei.getNickName());
				        	return wei;
				        //}
				 }
				 
			 }catch(Exception ex)
			 {
				 System.out.println(ex.getMessage());
			 }
		        
			 return null;
			 
			 
	    }
	    

}
