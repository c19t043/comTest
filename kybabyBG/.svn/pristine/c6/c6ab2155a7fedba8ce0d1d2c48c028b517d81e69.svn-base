package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.RoleList;

public class RoleManage extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String action;	
	private String roleName;
	private String functionList;
	private Long roleId=0L;
	private List<RoleList> roleList = new ArrayList<RoleList>();
	private List<String> nameList = new ArrayList<String>();
    private String mes;
    private String roleFunction="";
    

	public String execute(){

		
		if(action.equals("add")){
			RoleList role=adminBo.findRoleByName(roleName);
			if (role!=null){
				mes="该角色已经存在";
				return "success";
			} else{
			    adminBo.addRole(roleName, functionList);
				mes="角色添加成功";
				return "success";
			}
			
		} 
		
		else if(action.equals("update")){			
			RoleList role=adminBo.findRoleByName(roleName);
			if(role!=null){
				if(role.getId().longValue()!=roleId.longValue()){
					mes="该角色已经存在";
					return "success";
				} else{
					adminBo.updateRoleById(roleId, roleName, roleFunction);
					mes="更新成功";
					return "success";
				}
			} else{
				adminBo.updateRoleById(roleId, roleName, roleFunction);
				mes="更新成功";
				return "success";
			}
		
			
		} 
		return "success";
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}

	public List<RoleList> getRoleList() {
		return roleList;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setFunctionList(String functionList) {
		this.functionList = functionList;
	}

	public List<String> getNameList() {
		return nameList;
	}

	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setRoleFunction(String roleFunction) {
		this.roleFunction = roleFunction;
	}

	public String getRoleName() {
		return roleName;
	}

}
