package com.java.doctorinfo.action;

import java.util.List;

import org.omg.PortableInterceptor.SUCCESSFUL;

import com.java.b2cGoods.vo.B2cGoods;
import com.java.doctorinfo.service.DoctorInfoService;
import com.java.ec.common.PageSortModel;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.core.Action;

public class DoctorInfoAction extends  Action{
	private static final long serialVersionUID = 1852094731493920752L;
	private DoctorInfoService doctorInfoService;
	private DoctorInfo doctorInfo;
	public DoctorInfoService getDoctorInfoService() {
		return doctorInfoService;
	}
	public void setDoctorInfoService(DoctorInfoService doctorInfoService) {
		this.doctorInfoService = doctorInfoService;
	}
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	/**
	 * 医生基本信息列表
	 * @return
	 */
	public String getDoctorInfoList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(doctorInfo == null){
			doctorInfo = new DoctorInfo();
		}
		List<DoctorInfo> list = doctorInfoService.getDoctorInfoListByPage(psm, doctorInfo);
		this.putToRequest("list", list);
		return SUCCESS;
	}
}
