package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.Admin;

public class GetAdminInfo extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String action;	
	private List<Admin> adminList = new ArrayList<Admin>();
    private String adminName;
    private String mes;
    private List<String> allianceName = new ArrayList<String>();
    private List<String> roleNameList = new ArrayList<String>();
    private Admin admin;
    private Long roleId=0L;
    
    private String roleNameTemp="";
  

	public String execute(){

		
		if(action.equals("all")){
			System.out.println("get admin account start....");
			adminList=adminBo.getAdminList();
			if(adminList!=null){
				for(int i=0;i<adminList.size();i++){
					if(adminList.get(i).getRoleId()!=null){
						roleNameTemp=adminBo.getRoleNameById(adminList.get(i).getRoleId());
						if(roleNameTemp!=null){
							roleNameList.add(roleNameTemp);
						}
						else{
							roleNameList.add("");
						}
					} else {
						roleNameList.add("");
					}				
				}
			}
			return "success";
		}	
		else if(action.equals("getAdminByRoleId")){
			adminList=adminBo.getAdminByRoleId(roleId);			
			if(adminList!=null){
				for(int i=0;i<adminList.size();i++){
					if(adminList.get(i).getRoleId()!=null){
						roleNameTemp=adminBo.getRoleNameById(adminList.get(i).getRoleId());
						if(roleNameTemp!=null){
							roleNameList.add(roleNameTemp);
						}
						else{
							roleNameList.add("");
						}
					} else {
						roleNameList.add("");
					}					
				}
			}
		}
		else if(action.equals("getAdminByName")){
		    	admin=adminBo.findAdminByAdminNmame(adminName);
		    	if(admin!=null){
		    		mes="已经存在";
		    	}else{
		    		mes="";
		    	}
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
	

	public List<Admin> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<Admin> adminList) {
		this.adminList = adminList;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public List<String> getAllianceName() {
		return allianceName;
	}

	public void setAllianceName(List<String> allianceName) {
		this.allianceName = allianceName;
	}

	public List<String> getRoleNameList() {
		return roleNameList;
	}

	public void setRoleNameList(List<String> roleNameList) {
		this.roleNameList = roleNameList;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
