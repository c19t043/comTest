package com.kybaby.action.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.GuidePage;

/**
 * @ClassName:GetGuideInfo
 * @Description:获取引导页
 * @author Hoolee
 * @date 2015年9月29日上午11:10:17
 */
public class GetGuideInfo extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String mes;//反馈给页面的提示信息

	List<GuidePage> guidePictureList=new ArrayList<GuidePage>();

	public String execute(){
		if(action.equals("getGuidePage")){
			guidePictureList=guidePageBo.getGuidePictureList();
			if(guidePictureList==null){
				mes="无引导";
				return "fail";
			}
			mes="操作成功";
			return "success";
		} 
		//add by HooLee 2015年10月31日11:48:09 增加记录用户是否已经登录的cookie
		else if(action.equals("addGuideCookie")){
			System.out.println("AddGuideCookie is begining...");
			//首先读取记录的cookie，如果cookie存在就提示存在，否则进入引导页
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpServletRequest request = ServletActionContext.getRequest();
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			boolean flag=false;
			if (cookies != null) {
				javax.servlet.http.Cookie cs = null;
				for (int i = 0; i < cookies.length; i++) {
					cs = cookies[i];
					if (cs != null) {
						if (cs.getName().equals("kybaby_guide")) {
							flag=true;
							break;
						}
					}
					cs = null;
				}
				if(flag){
					mes="操作成功";
					return "success";
				}else{
					addGuideCookie();
					mes="无记录";
					return "success";
				}
			}
			addGuideCookie();
			mes="无记录";
			return "fail";
		}
		return "fail";
	}
	
	private static void addGuideCookie(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Cookie guideCookie=new Cookie("kybaby_guide","kybaby_guide");
		guideCookie.setMaxAge(60*60*24*14);
		guideCookie.setPath("/");
		response.addCookie(guideCookie);
	}

	public String getMes() {
		return mes;
	}

	public List<GuidePage> getGuidePictureList() {
		return guidePictureList;
	}

}
