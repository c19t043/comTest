package com.java.spinterface.action;

import java.util.List;

import net.sf.json.JSONObject;

import com.java.ec.common.PageSortModel;
import com.java.platform.core.Action;
import com.java.spinterface.service.SpInterfaceService;
import com.java.spinterface.vo.SpAppointmentSchedule;
import com.java.util.JsonUtil;

@SuppressWarnings("serial")
public class SpInterfaceAction extends Action {
	private SpInterfaceService spInterfaceService;
	private SpAppointmentSchedule spAppointmentSchedule;
	private List<String> doctorNames;
	/** 获取苏坡挂号排班  
	 * @throws Exception */
	public String querySpAppointmentSchedules(){
		List<SpAppointmentSchedule> registerSchedule = spInterfaceService.saveSpAppointmentSchedules();
		
		JSONObject jo = new JSONObject();
		String msg = "";
		if(registerSchedule.isEmpty()){
			 msg = "没有排班信息";
		}else{
			 msg = "成功";
		}
		jo.put("msg", msg);
		JsonUtil.writeJson(getResponseValue(), jo.toString());
		return NONE;
	}
	
	/**   查询苏坡挂号信息   */
	public String queryRegisterSchedule(){
		String tableID = "list";
		PageSortModel model = new PageSortModel(getHttpServletRequest(), tableID);
		doctorNames = spInterfaceService.getNameOfDoctorInfos();
		List<SpAppointmentSchedule> spAppointmentSchedules = spInterfaceService.getRegisterSchedules(model,spAppointmentSchedule);
		for (SpAppointmentSchedule spAppointmentSchedule : spAppointmentSchedules) {
			String orgID = spAppointmentSchedule.getOrgID();
			if(SpInterfaceService.SP_HOSPITAL_CODE.equals(orgID)){
				spAppointmentSchedule.setOrgName("苏坡社区卫生服务中心");
			}
			else if(SpInterfaceService.NINTH_HOSPITAL_CODE.equals(orgID)){
				spAppointmentSchedule.setOrgName("成都市青羊区第九人民医院");
			}
		}
		this.putToRequest("list", spAppointmentSchedules);
		return SUCCESS;
	}
	
	/**  同步医生数据   */
	public String queryDoctorInfos(){
		spInterfaceService.saveSPDoctorInfos();
		JSONObject jo = new JSONObject();
		jo.put("msg", "成功");
		JsonUtil.writeJson(getResponseValue(), jo.toString());
		return NONE;
	}
	public String queryDeptNames(){
		//List<String> deptNames = spInterfaceService.getdeptNames();
		//JSONOArray jo = JSONObject.fromObject(deptNames);
		//JsonUtil.writeJson(getResponseValue(), jo.toString());
		return NONE;
	}
	
	
	
	
	
	
	
	public List<String> getDoctorNames() {
		return doctorNames;
	}

	public void setDoctorNames(List<String> doctorNames) {
		this.doctorNames = doctorNames;
	}

	public SpAppointmentSchedule getSpAppointmentSchedule() {
		return spAppointmentSchedule;
	}
	public void setSpAppointmentSchedule(SpAppointmentSchedule spAppointmentSchedule) {
		this.spAppointmentSchedule = spAppointmentSchedule;
	}
	public void setSpInterfaceService(SpInterfaceService spInterfaceService) {
		this.spInterfaceService = spInterfaceService;
	}
}
