package com.kybaby.newbussiness.doctorring.main;

import com.kybaby.newbussiness.doctorring.action.NewBaseAction;

/**
 * @ClassName:DoctorRingPropertyManage
 * @Description:医生圈全局参数设置相关
 * @author Hoolee
 * @date 2015年12月21日上午10:51:23
 */
public class DoctorRingPropertyManage extends NewBaseAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	
	public String execute(){
		return "fail";
	}

	public String getMes() {
		return mes;
	}
}
