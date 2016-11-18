package com.java.orgManager.action;

import java.util.List;

import net.sf.json.JSONArray;

import com.java.doctorinfo.vo.OrganOperator;
import com.java.ec.common.PageSortModel;
import com.java.orgManager.service.OrgOperatorManagerService;
import com.java.orgManager.service.OrganModuleInfoService;
import com.java.orgManager.service.OrganRoleService;
import com.java.orgManager.vo.OrganModuleInfo;
import com.java.orgManager.vo.OrganRole;
import com.java.platform.core.Action;
import com.java.util.JsonUtil;

@SuppressWarnings("serial")
public class OrganRoleAction extends Action {
	private OrganRoleService organRoleService;
	private OrgOperatorManagerService orgOperatorManagerService;
	private OrganModuleInfoService organModuleInfoService;
	private OrganModuleInfo organModuleInfo;
	private OrganRole organRole;
	private OrganOperator organOperator;
	private String action;
	private String operatorIDs;
	private String moduleIDs;
	
	@Override
	public String execute() throws Exception {
		switch(action){
		//获取机构角色分页列表
		case "queryOrganRoleByPage":
			queryOrganRoleByPage();return "list";
		//添加or保存机构角色
		case "saveOrUpdateOrganRole":
			saveOrUpdateOrganRole();return redirectActionResult("list");
		//跳转到添加or编辑页面
		case "toAddOrUpdatePage":
			toAddOrUpdatePage();return "toAddOrUpdate";
		//跳转到用户弹出框list页面
		case "toOperatorPage": 
			toOperatorPage();return "selectOperatorPage";
		//保存角色分配的用户
		case "saveRoleOwnedUsers":
			saveRoleOwnedUsers();return NONE;
		//查询角色分配的用户
		case "queryRoleOwnedUsers":
			queryRoleOwnedUsers();return NONE;
		//跳转到模块弹出框list页面
		case "toModulePage": 
			toModulePage();return "selectModulePage";
		//保存角色分配的用户
		case "saveRoleOwnedModule":
			saveRoleOwnedModule();return NONE;
		//查询角色分配的用户
		case "queryRoleOwnedModule":
			queryRoleOwnedModule();return NONE;
		}
		return SUCCESS;
	}
	/**
	 * 查询角色分配的用户
	 */
	private void queryRoleOwnedModule(){
		List<OrganModuleInfo> queryRoleOwnedModules = organRoleService.queryRoleOwnedModules(organRole.getId());
		JSONArray ja = JSONArray.fromObject(queryRoleOwnedModules);
		JsonUtil.writeJson(getResponseValue(), ja.toString());
	}
	/**
	 * 保存角色分配的用户
	 */
	private void saveRoleOwnedModule(){
		organRoleService.saveRoleOwnedModules(organRole.getId(), moduleIDs);
	}
	/**
	 * 跳转到模块弹出框list页面
	 */
	private void toModulePage(){
		PageSortModel model = new PageSortModel(getHttpServletRequest(), "list");
		List<OrganModuleInfo> organOperatorByPage = organModuleInfoService.getOrganModuleInfoByPage(model, organModuleInfo);
		this.putToRequest("list", organOperatorByPage);
	}
	/**
	 * 跳转到用户弹出框list页面
	 */
	private void toOperatorPage(){
		PageSortModel model = new PageSortModel(getHttpServletRequest(), "list");
		List<OrganOperator> organOperatorByPage = orgOperatorManagerService.getOrganOperatorByPage(model, organOperator);
		this.putToRequest("list", organOperatorByPage);
	}
	/**
	 * 保存角色分配的用户
	 */
	private void saveRoleOwnedUsers(){
		organRoleService.saveRoleOwnedUsers(organRole.getId(), operatorIDs);
	}
	/**
	 * 查询角色分配的用户
	 */
	private void queryRoleOwnedUsers(){
		List<OrganOperator> queryRoleOwnedUsers = organRoleService.queryRoleOwnedUsers(organRole.getId());
		JSONArray ja = JSONArray.fromObject(queryRoleOwnedUsers);
		JsonUtil.writeJson(getResponseValue(), ja.toString());
	}
	/**
	 * 获取机构角色分页列表
	 */
	private void queryOrganRoleByPage(){
		PageSortModel model = new PageSortModel(getHttpServletRequest(), "list");
		List<OrganRole> organOperatorByPage = organRoleService.getOrganRoleByPage(model, organRole);
		this.putToRequest("list", organOperatorByPage);
	}
	/**
	 * 添加or保存机构角色
	 */
	private void saveOrUpdateOrganRole(){
		organRoleService.saveOrUpdateOrganRole(organRole);
	}
	/**
	 * 跳转到添加or编辑页面
	 */
	private void toAddOrUpdatePage(){
		if(organRole!=null){
			Long id = organRole.getId();
			if(id!=null) {
				organRole = organRoleService.get(id, OrganRole.class);
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
	public void setOrganRoleService(OrganRoleService organRoleService) {
		this.organRoleService = organRoleService;
	}
	public OrganRole getOrganRole() {
		return organRole;
	}
	public void setOrganRole(OrganRole organRole) {
		this.organRole = organRole;
	}
	public String getOperatorIDs() {
		return operatorIDs;
	}
	public void setOperatorIDs(String operatorIDs) {
		this.operatorIDs = operatorIDs;
	}
	public OrganOperator getOrganOperator() {
		return organOperator;
	}
	public void setOrganOperator(OrganOperator organOperator) {
		this.organOperator = organOperator;
	}
	public OrganRoleService getOrganRoleService() {
		return organRoleService;
	}
	public OrgOperatorManagerService getOrgOperatorManagerService() {
		return orgOperatorManagerService;
	}
	public void setOrgOperatorManagerService(
			OrgOperatorManagerService orgOperatorManagerService) {
		this.orgOperatorManagerService = orgOperatorManagerService;
	}
	public OrganModuleInfoService getOrganModuleInfoService() {
		return organModuleInfoService;
	}
	public void setOrganModuleInfoService(
			OrganModuleInfoService organModuleInfoService) {
		this.organModuleInfoService = organModuleInfoService;
	}
	public OrganModuleInfo getOrganModuleInfo() {
		return organModuleInfo;
	}
	public void setOrganModuleInfo(OrganModuleInfo organModuleInfo) {
		this.organModuleInfo = organModuleInfo;
	}
	public String getModuleIDs() {
		return moduleIDs;
	}
	public void setModuleIDs(String moduleIDs) {
		this.moduleIDs = moduleIDs;
	}
}
