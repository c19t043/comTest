package com.kybaby.action.main;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.util.CookieManage;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author sujiantang
 *
 */
public class DoctorLogout extends BaseAction{
	
	private DoctorInfo doctorInfo;
	private String mes;
	
	@Override
	public String execute(){
		if(action.equals("logout")){
			doctorInfo=(DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo!=null){
				String phone=doctorInfo.getDoctorPhone();
				doctorInfo=doctorInfoBo.getDoctorInfoByPhoneNum(phone);
				doctorInfo.setIsLogin("N");
				doctorInfoBo.update(doctorInfo);
				CookieManage.removeCookie();
				ActionContext.getContext().getSession().remove("Doctor");
				mes="成功";
				return "success";
			}
			mes="请登录";
			return "fail";
		}
		return "success";
	}

	@Override
	public String getMes() {
		return mes;
	}

}
