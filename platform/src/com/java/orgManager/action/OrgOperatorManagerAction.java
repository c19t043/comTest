package com.java.orgManager.action;


import java.util.List;

import com.java.doctorinfo.vo.OrganOperator;
import com.java.ec.common.PageSortModel;
import com.java.orgManager.service.OrgOperatorManagerService;
import com.java.platform.core.Action;

@SuppressWarnings("serial")
public class OrgOperatorManagerAction extends Action  {
	private OrgOperatorManagerService orgOperatorManagerService;
	private OrganOperator organOperator;
	private String action;
	
	@Override
	public String execute() throws Exception {
		switch(action){
		//获取机构操作人员分页列表
		case "queryOrganOperatorByPage":
			queryOrganOperatorByPage();return "list";
		//添加or保存机构操作人员
		case "saveOrUpdateOrganOperator":
			saveOrUpdateOrganOperator();return redirectActionResult("list");
		//跳转到添加or编辑页面
		case "toAddOrUpdatePage":
			toAddOrUpdatePage();return "toAddOrUpdate";
		}
		return SUCCESS;
	}
	/**
	 * 获取机构操作人员分页列表
	 */
	private void queryOrganOperatorByPage(){
		PageSortModel model = new PageSortModel(getHttpServletRequest(), "list");
		List<OrganOperator> organOperatorByPage = orgOperatorManagerService.getOrganOperatorByPage(model, organOperator);
		this.putToRequest("list", organOperatorByPage);
	}
	/**
	 * 跳转到添加or编辑页面
	 */
	private void toAddOrUpdatePage(){
		if(organOperator!=null){
			Long id = organOperator.getId();
			if(id!=null) {
				organOperator = orgOperatorManagerService.get(id, OrganOperator.class);
			}
		}
	}
	/**
	 * 添加or保存机构操作人员
	 */
	private void saveOrUpdateOrganOperator(){
		if(organOperator.getHospitalBasicInfo()!=null&&organOperator.getHospitalBasicInfo().getId()==null){
			organOperator.setHospitalBasicInfo(null);
		}
		orgOperatorManagerService.saveOrUpdateOrganOperator(organOperator);
	}
	
	
	
	
	//-----------------------------------------setter/getter
	public OrganOperator getOrganOperator() {
		return organOperator;
	}
	public void setOrganOperator(OrganOperator organOperator) {
		this.organOperator = organOperator;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public void setOrgOperatorManagerService(
			OrgOperatorManagerService orgOperatorManagerService) {
		this.orgOperatorManagerService = orgOperatorManagerService;
	}
}
