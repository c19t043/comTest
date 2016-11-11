package com.wx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.wx.domain.WxToken;
import com.wx.util.CommonUtil;
import com.wx.util.third.HttpPost;


public class GetMediaFile extends BaseAction {
	/**
	 * 调用微信统一支付接口,获取prepayId
	 */
	private static final long serialVersionUID = 5179862059368466962L;
	private String mes;
	private String action;
    private String result="";
    private String MEDIA_ID="";
    FileOutputStream out=null;
    private String dir="../kybabyBG/admin/voice";
    private String uniqueId="";
    private String fileName="";
    
	
	public String execute() throws JSONException, UnsupportedEncodingException, IOException{
		System.out.println("*************get media file*****************");
		if(action.equals("getMediaFile")){			
			String corpId=wxBo.findPropertyValueByKey("corpId");
			String corpSecret=wxBo.findPropertyValueByKey("corpSecret");
			
            WxToken tk = wxBo.getAccessToken(corpId);			
			WxToken tk1 =  CommonUtil.getToken(corpId, corpSecret, tk);
			
			String strUrl="http://file.api.weixin.qq.com/cgi-bin/media/get";
			
			Map<String, String> requestMap = new HashMap<String,String>();
			requestMap.put("access_token",tk1.getAccessToken());
			requestMap.put("media_id", MEDIA_ID);
			
			//result = HttpPost.doPostXml("https://api.mch.weixin.qq.com/pay/unifiedorder",xmlStr, "UTF-8");
			fileName=uniqueId+String.valueOf(System.currentTimeMillis());
			result=HttpPost.doGetWxMedia(strUrl, requestMap, "UTF-8",dir,fileName);	
			
			//String tempDir="main/images"+"/"+"test.amr";
			//String dir=ServletActionContext.getServletContext().getRealPath(tempDir);
			//File pageFile=new File(dir);
			//writeFile(result,pageFile);
			
			
			
			//JSONObject objResult=new JSONObject(result);
			System.out.println(result);
		    return "success";
		}
		return "success";
		
	}
	
	public void writeFile(String filecontent,File filename) throws UnsupportedEncodingException, IOException {
		out = new FileOutputStream(filename);
		out.write(filecontent.getBytes("utf8"));  
		out.flush();
	    out.close();  
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

	public void setMEDIA_ID(String mEDIA_ID) {
		MEDIA_ID = mEDIA_ID;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}
