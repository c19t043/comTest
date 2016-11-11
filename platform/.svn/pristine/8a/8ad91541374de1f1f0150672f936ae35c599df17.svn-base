package com.java.consultmanager.consultdoctormanager.action;

import java.util.List;

import com.java.consultmanager.consultdoctormanager.service.ConsultDoctorInfoService;
import com.java.consultmanager.consultdoctormanager.vo.ConsultDoctorInfo;
import com.java.ec.common.PageSortModel;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.core.Action;

public class ConsultDoctorInfoAction extends Action{

	private static final long serialVersionUID = -7637293250027889902L;
	
	private ConsultDoctorInfoService consultDoctorInfoService;

	private ConsultDoctorInfo consultDoctorInfo;
	
	public String toDoctorList(){
		PageSortModel model = new PageSortModel(this.getHttpServletRequest(),"list");
		List<ConsultDoctorInfo> list = consultDoctorInfoService.getConsultDoctorInfoList(model,consultDoctorInfo);
		putToRequest("list", list);
		return SUCCESS;
	}
	
	public String toDoctorAdd(){
		List<DoctorInfo> doctorInfos = consultDoctorInfoService.getDoctorInfos();
		this.putToRequest("doctorInfos", doctorInfos);
		return SUCCESS;
	}
	
	public String toDoctorEdit(){
		consultDoctorInfo = consultDoctorInfoService.getConsultDoctorInfoById(consultDoctorInfo.getId());
		putToRequest("consultDoctorInfos", consultDoctorInfo);
		return SUCCESS;
	}
	
	public String doctorSaveOrUpdate(){
		if(consultDoctorInfo.getId()==null)
			consultDoctorInfoService.addConsultDoctorInfo(consultDoctorInfo);
		else 
			consultDoctorInfoService.updateConsultDoctorInfo(consultDoctorInfo);
		return redirectActionResult("toDoctorList");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setConsultDoctorInfoService(
			ConsultDoctorInfoService consultDoctorInfoService) {
		this.consultDoctorInfoService = consultDoctorInfoService;
	}

	public ConsultDoctorInfo getConsultDoctorInfo() {
		return consultDoctorInfo;
	}

	public void setConsultDoctorInfo(ConsultDoctorInfo consultDoctorInfo) {
		this.consultDoctorInfo = consultDoctorInfo;
	}
}
