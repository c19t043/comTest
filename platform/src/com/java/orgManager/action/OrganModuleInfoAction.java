package com.java.orgManager.action;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.orgManager.service.OrganModuleInfoService;
import com.java.orgManager.vo.OrganModuleInfo;
import com.java.platform.core.Action;

@SuppressWarnings("serial")
public class OrganModuleInfoAction extends Action {
	private OrganModuleInfoService organModuleInfoService;
	private OrganModuleInfo organModuleInfo;
	private String action;
	
	@Override
	public String execute() throws Exception {
		switch(action){
		//获取机构角色分页列表
		case "queryOrganModuleInfoByPage":
			queryOrganModuleInfoByPage();return "list";
		//添加or保存机构角色
		case "saveOrUpdateOrganModuleInfo":
			saveOrUpdateOrganModuleInfo();return redirectActionResult("list");
		//跳转到添加or编辑页面
		case "toAddOrUpdatePage":
			toAddOrUpdatePage();return "toAddOrUpdate";
		}
		return SUCCESS;
	}
	/**
	 * 获取机构角色分页列表
	 */
	private void queryOrganModuleInfoByPage(){
		PageSortModel model = new PageSortModel(getHttpServletRequest(), "list");
		List<OrganModuleInfo> organOperatorByPage = organModuleInfoService.getOrganModuleInfoByPage(model, organModuleInfo);
		this.putToRequest("list", organOperatorByPage);
	}
	/**
	 * 添加or保存机构角色
	 */
	private void saveOrUpdateOrganModuleInfo(){
		organModuleInfoService.saveOrUpdateOrganModuleInfo(organModuleInfo);
	}
	/**
	 * 跳转到添加or编辑页面
	 */
	private void toAddOrUpdatePage(){
		if(organModuleInfo!=null){
			Long id = organModuleInfo.getId();
			if(id!=null) {
				organModuleInfo = organModuleInfoService.get(id, OrganModuleInfo.class);
			}
		}
	}

	
	//=====================setter/getter
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public OrganModuleInfo getOrganModuleInfo() {
		return organModuleInfo;
	}
	public void setOrganModuleInfo(OrganModuleInfo organModuleInfo) {
		this.organModuleInfo = organModuleInfo;
	}
	public void setOrganModuleInfoService(
			OrganModuleInfoService organModuleInfoService) {
		this.organModuleInfoService = organModuleInfoService;
	}
}
