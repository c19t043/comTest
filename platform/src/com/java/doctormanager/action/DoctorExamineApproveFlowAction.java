package com.java.doctormanager.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.java.doctormanager.service.MajorService;
import com.java.doctormanager.vo.DoctorMajor;
import com.java.ec.common.PageSortModel;
import com.java.platform.core.Action;
import com.java.util.JsonUtil;

@SuppressWarnings("serial")
public class DoctorExamineApproveFlowAction extends  Action{
	private MajorService majorService;
	
	private DoctorMajor major;
	private String reload;
	/*
	 * 专业
	 */
	public String queryMajorsList(){
		return SUCCESS;
	}
	public String queryMajors(){
		PageSortModel model = new PageSortModel(this.getHttpServletRequest(),"list");
		List<DoctorMajor> majors = majorService.getMajors(model, major);
		if(major==null){
			if(!majors.isEmpty()){
				major = new DoctorMajor();
				major.setParent(majors.get(0));
			}
		}else{
			major.setParent(majorService.get(major.getParent().getId(), DoctorMajor.class));
		}
		this.putToRequest("list", majors);
		return SUCCESS;
	}
	public String toAddMajor(){
		if(major!=null){
			Long id = major.getId();
			if(id!=null) {
				major = majorService.get(id, DoctorMajor.class);
			}else{
				if(major.getParent()!=null&&major.getParent().getId()!=null){
					DoctorMajor parent = majorService.get(major.getParent().getId(), DoctorMajor.class);
					major.setParent(parent);
				}
			}
		}
		return SUCCESS;
	}
	public String saveOrUpdateMajor(){
		majorService.saveOrUpdateMajor(major);
		reload = "true";
		return SUCCESS;
		//return redirectActionResult("major");
	}
	/**
	 * 专业目录树
	 * @return
	 */
	public String queryMajorsTrees(){
		List<Map<String,Object>> tree = majorService.getIndexTree();
		JSONArray ja = JSONArray.fromObject(tree);
		JsonUtil.writeJson(getResponseValue(), ja.toString());
		//setOutData(tree);
		return NONE;
	}
	
	
	
	
	
	
	
	
	
	
	public void setMajorService(MajorService majorService) {
		this.majorService = majorService;
	}
	public DoctorMajor getMajor() {
		return major;
	}
	public void setMajor(DoctorMajor major) {
		this.major = major;
	}
	public String getReload() {
		return reload;
	}
	public void setReload(String reload) {
		this.reload = reload;
	}
}
