package com.kybaby.action.main;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author sujiantang
 *
 */
public class ModifyPhone extends BaseAction{
	
	private DoctorInfo doctorInfo;
	private String newPhone;
	private String mes;

	@Override
	public String execute(){
		if(action.equals("modifyPhone")){
			String phone = (String)ActionContext.getContext().getSession().get("Phone");
			if(phone!=null){
				if(!newPhone.equals(phone)){
					mes="手机号与获取验证码手机号不匹配";
					return "fail";
				}
				doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
				doctorInfo.setDoctorPhone(newPhone);
				modifyBo.updateDoctorInfo(doctorInfo);
				mes="成功";
				return "success";
			}
			mes="网络异常";
			return "fail";
		}
		return "success";
		
	}

	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}

	@Override
	public String getMes() {
		return mes;
	}
}
