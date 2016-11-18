package com.java.doctormanager.action;

import com.java.doctormanager.service.DoctorOrderGathrerService;
import com.java.platform.core.Action;
import com.java.util.JsonUtil;

@SuppressWarnings("serial")
public class DoctorOrderGathrerAction extends  Action{
	private DoctorOrderGathrerService doctorOrderGathrerService;
	
	public String doctorOrderGather(){
		doctorOrderGathrerService.saveGatherDoctorOrderData();
		JsonUtil.writeText(getResponseValue(), "成功");
		return NONE;
	}
	
	
	
	
	
	
	
	//-----------------------------------setter/getter
	public void setDoctorOrderGathrerService(
			DoctorOrderGathrerService doctorOrderGathrerService) {
		this.doctorOrderGathrerService = doctorOrderGathrerService;
	}
}
