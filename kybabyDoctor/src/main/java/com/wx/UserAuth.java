package com.wx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionContext;
import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;
import com.wx.util.third.WeiXin;
import com.wx.util.third.WeiXinApi;




public class UserAuth extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5179862059368466962L;
	private String mes;
	private String action="";
	private String openId="";
	private HttpServletResponse response;
	protected HttpServletRequest request;
	private String code;

	@Override
	public String execute() throws Exception{
		System.out.println("Authoriztion is starting...");
		//System.out.println(ActionContext.getContext().getSession().get("openId"));
		//openId = ActionContext.getContext().getSession().get("openId");
		//System.out.println(ActionContext.getContext().getSession().get("openId"));
		if(action.equals("getCode")){
			String authUrl=wxBo.findPropertyValueByKey("authUrl");
			
			System.out.println("Auto to get weixin code2....." + authUrl);
			//HttpServletResponse response = null;
			response.sendRedirect(authUrl);
			//response.sendRedirect("http://kybabydev.yishangkeji.cn/kybaby/wx/userAuth.action");
			//HttpClient httpClient = new HttpClient(); 
			//GetMethod method = new GetMethod(authUrl);

			return "success";	
		} else {
			//System.out.println("Code Authoriztion is starting..." + code);
			//code = this.request.getParameter("code");
			if (code != null) {
				//System.out.println("Code is..." + code);
				WeiXin wei = WeiXinApi.getWeiXin(code);
				if (wei != null) {
					openId=wei.getOpenId();
					System.out.println("Get WeiXin Code:" + wei.getOpenId());
					ActionContext.getContext().getSession().put("OpenId",openId);
					DoctorInfo doctorInfo = doctorInfoBo.getDoctorInfoByOpenId(openId);
					if(doctorInfo!=null){
						System.out.println("isLogin=="+doctorInfo.getIsLogin().equals("Y"));
						if(doctorInfo.getIsLogin().equals("Y")){
							ActionContext.getContext().getSession().put("Doctor",doctorInfo);
							return "redirect";
						}
						else{
							return "login";
						}
					}
					return "regist";
				}
				return "login";
			}
		}
        return "success";
		
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
