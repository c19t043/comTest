package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.RoleList;

public class GetRoleInfo extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String action;	
	private String roleName;
	private Long roleId=0L;
	private List<RoleList> roleList = new ArrayList<RoleList>();
	private List<String> nameList = new ArrayList<String>();
    private String mes;
    
    private String idList="";
    

	public String execute(){

		
		if(action.equals("all")){
			System.out.println("role manage start....");
			roleList = adminBo.getRoleList();
			return "success";
		} else if(action.equals("getNameListById")){
			nameList=adminBo.getFunctionNameByIdList(idList);
			return "success";
		} else if(action.equals("getNameById")){
			roleName=adminBo.getRoleNameById(roleId);
			return "success";
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

	public void setIdList(String idList) {
		this.idList = idList;
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

	public String getRoleName() {
		return roleName;
	}

}
