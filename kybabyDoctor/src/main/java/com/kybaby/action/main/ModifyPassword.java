package com.kybaby.action.main;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.util.EncryptUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author sujiantang
 *
 */
public class ModifyPassword extends BaseAction{
	
	private DoctorInfo doctorInfo;
	private String password;
	private String mes;

	@Override
	public String execute(){
		if(action.equals("modifyPassword")){
//			String phone = (String)ActionContext.getContext().getSession().get("Phone");
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo!=null){
				password = EncryptUtil.getMD5Str(password);
				doctorInfo.setDoctorPassword(password);
				modifyBo.updateDoctorInfo(doctorInfo);
				mes="成功";
				return "success";
			}
			mes="请登录";
			return "fail";
		}
		if(action.equals("forgetPassword")){
			String phone = (String)ActionContext.getContext().getSession().get("Phone");
			if(phone!=null){
				doctorInfo = modifyBo.getDoctorInfoByPhone(phone);
				if(doctorInfo!=null){
					password = EncryptUtil.getMD5Str(password);
					doctorInfo.setDoctorPassword(password);
					modifyBo.updateDoctorInfo(doctorInfo);
					mes="成功";
					return "success";
				}
				mes="用户不存在";
				return "fail";
			}
			mes="网络异常";
			return "fail";
		}
		return "success";
		
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getMes() {
		return mes;
	}
}
