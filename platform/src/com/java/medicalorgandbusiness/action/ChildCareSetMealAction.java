package com.java.medicalorgandbusiness.action;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.archivesinfo.vo.ArchivesInfo;
import com.java.medicalorgandbusiness.service.ChildCareSetMealService;
import com.java.medicalorgandbusiness.vo.ChildcareProject;
import com.java.medicalorgandbusiness.vo.OrderInfoClinic;
import com.java.medicalorgandbusiness.vo.OrganSetChildCareRecode;
import com.java.medicalorgandbusiness.vo.UserChildcareAppointmentInfo;
import com.java.platform.core.Action;

public class ChildCareSetMealAction extends Action  {

	private static final long serialVersionUID = -7316749825733174987L;
	
	private ChildCareSetMealService childCareSetMealService;

	private UserChildcareAppointmentInfo userChildcareAppointmentInfo;
	
	private OrganSetChildCareRecode organSetChildCareRecode;
	
	private String message;
	
	private String ids;
	
	private List<ChildcareProject> childcareProjectList;
	
	public String doBatchEdit(){
		
		String[] idArr = ids.split(",");
		
		for(int i=0;i<idArr.length;i++){
			UserChildcareAppointmentInfo info_query = childCareSetMealService.get(Long.parseLong(idArr[i]), UserChildcareAppointmentInfo.class);
			info_query.setStatus("已完成");
			childCareSetMealService.edit(info_query);
		}
		
		return redirectActionResult("toList");
	}
	
	
	public String toList(){
		
		PageSortModel model = new PageSortModel(this.getHttpServletRequest(), "list");
		
		List<UserChildcareAppointmentInfo> list = childCareSetMealService.getUserChildcareAppointmentInfoList(model, userChildcareAppointmentInfo);
		
		for(int i=0;i<list.size();i++){
			UserChildcareAppointmentInfo info_query = list.get(i);
			OrganSetChildCareRecode recode_query = childCareSetMealService.getOrganSetChildCareRecode(info_query.getId());
			info_query.setOrganSetChildCareRecode(recode_query==null?new OrganSetChildCareRecode():recode_query); 
			
			StringBuilder sb = new StringBuilder();
			sb.append("from ArchivesInfo a where 1=1")
				.append(" and a.userInfo.id ="+info_query.getUserInfo().getId())
				.append(" and a.userInfo.birthday = '"+info_query.getUserInfo().getBirthday()+"'");
			List<ArchivesInfo> archivesInfoList  = childCareSetMealService.list(sb.toString(), -1, -1, null);
			if(archivesInfoList.isEmpty()||archivesInfoList==null) info_query.setArchivesInfo(new ArchivesInfo());
			else info_query.setArchivesInfo(archivesInfoList.get(0));
		}		
		putToRequest("list", list);
		
		return SUCCESS;
	}
	
	
	public String updateOrderStatus(){
		if(userChildcareAppointmentInfo.getId()!=null&&userChildcareAppointmentInfo.getStatus()!=null){
			UserChildcareAppointmentInfo info_old = childCareSetMealService.get(userChildcareAppointmentInfo.getId(), UserChildcareAppointmentInfo.class);
			info_old.setStatus(userChildcareAppointmentInfo.getStatus());
			childCareSetMealService.edit(info_old);
		}
		return redirectActionResult("toList");
	}
	
	public String toDetail(){
		this.userChildcareAppointmentInfo =  childCareSetMealService.get(userChildcareAppointmentInfo.getId(), UserChildcareAppointmentInfo.class);
		
		this.organSetChildCareRecode = childCareSetMealService.getOrganSetChildCareRecode(userChildcareAppointmentInfo.getId());
		
		this.childcareProjectList = childCareSetMealService.list("from ChildcareProject", -1, -1, null);
		
		return SUCCESS;
	}
	
	
	public String updateChildCareDetail(){
		if(organSetChildCareRecode!=null){
			childCareSetMealService.updateChildCareDetail(organSetChildCareRecode);
		}
		this.message = "修改成功";
		return redirectActionResult("toList");
	}
	
	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public void setChildCareSetMealService(
			ChildCareSetMealService childCareSetMealService) {
		this.childCareSetMealService = childCareSetMealService;
	}



	public UserChildcareAppointmentInfo getUserChildcareAppointmentInfo() {
		return userChildcareAppointmentInfo;
	}



	public void setUserChildcareAppointmentInfo(
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		this.userChildcareAppointmentInfo = userChildcareAppointmentInfo;
	}


	public OrganSetChildCareRecode getOrganSetChildCareRecode() {
		return organSetChildCareRecode;
	}


	public void setOrganSetChildCareRecode(
			OrganSetChildCareRecode organSetChildCareRecode) {
		this.organSetChildCareRecode = organSetChildCareRecode;
	}

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public List<ChildcareProject> getChildcareProjectList() {
		return childcareProjectList;
	}


	public void setChildcareProjectList(List<ChildcareProject> childcareProjectList) {
		this.childcareProjectList = childcareProjectList;
	}

}
