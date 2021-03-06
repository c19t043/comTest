package com.kybaby.action.main;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.RoleSelect;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author sujiantang
 *
 */
public class PersonZone extends BaseAction{
	
	private DoctorInfo doctorInfo;
	private String headMessage = "";
	private String mes;

	@Override
	public String execute(){
		if(action.equals("headMes")){
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo!=null){
				/*
				 * update by HooLee
				 * 2015年11月30日15:49:27
				 * 个人中心的数据需要重新从数据库中获取，以便于及时查看到状态
				 * */
				long doctorId=doctorInfo.getId();
				doctorInfo=doctorInfoBo.getDoctorInfoBoById(doctorId);
				RoleSelect roleSelect = this.roleSelectBo.getRoleSelectByPhone(doctorInfo.getDoctorPhone());
				if(roleSelect != null){
					doctorInfo.setDoctorType(roleSelect.getRoleType());
				}
				headMessage = doctorInfo.getDoctorName();
				headMessage += "::"+doctorInfo.getDoctorImage();
				headMessage += "::"+doctorInfo.getDoctorTitle();
				headMessage += "::"+doctorInfo.getDoctorEmployer();
				headMessage += "::"+doctorInfo.getAuthentication();
				headMessage += "::"+doctorInfo.getDoctorPhone();
				System.out.println(headMessage);
				mes="成功";
				return "success";
			}
			mes="请登录";
			return "fail";
		}
		if(action.equals("recommend")){
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo!=null){
				/*
				 * update by HooLee
				 * 2015年11月30日15:49:27
				 * 个人中心的数据需要重新从数据库中获取，以便于及时查看到状态
				 * */
				long doctorId=doctorInfo.getId();
				doctorInfo=doctorInfoBo.getDoctorInfoBoById(doctorId);
				
				headMessage = doctorInfo.getDoctorImage();
				headMessage += "::"+doctorInfo.getDoctorName();
				headMessage += "::"+doctorInfo.getDoctorPhone();
				System.out.println(headMessage);
				mes="成功";
				return "success";
			}
			mes="请登录";
			return "fail";
		}
		return "success";
		
	}

	public String getHeadMessage() {
		return headMessage;
	}

	@Override
	public String getMes() {
		return mes;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
}
