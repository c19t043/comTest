package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.Admin;
import com.kybaby.util.EncryptUtil;
import com.opensymphony.xwork2.ActionContext;

public class AdminManage extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String action;	
	private List<Admin> adminList = new ArrayList<Admin>();
    private String adminName;
    private String functionId;
    private String status;
    private String password;
    private String contactName;
    private String contactPhone;
    private String mes;
    private Long roleId;
    private Long adminId=0L;
    private List<String> allianceName = new ArrayList<String>();
    private List<String> roleNameList = new ArrayList<String>();
    private Admin admin;
    private String roleName;
    private List<String> func = new ArrayList<String>();//反馈给前台的管理员功能 
    
    private String myName;
  

	public String execute(){

		if(action.equals("logout")){
			ActionContext.getContext().getSession().remove("landUser");
			return "success";
		}		
		
		else if(action.equals("login")){
			System.out.println("login start....");
			admin=adminBo.findAdminByAdminNmame(adminName);
			if(admin==null){
				mes="账号不存在";
				return "fail";
			}else if((EncryptUtil.getMD5Str(password)).equals(admin.getPassword())){
				mes="登陆成功";
				ActionContext.getContext().getSession().put("landUser", admin);
				return "success";
			}
			mes="密码错误";
			return "fail";
		}
		else if(action.equals("add")){
			Admin admin=adminBo.findAdminByAdminNmame(adminName);
			if (admin!=null){
				mes="该用户名已被占用";
				return "success";
			} else{
				password=EncryptUtil.getMD5Str(password);
				adminBo.add(adminName, password, roleId, functionId, status,contactName,contactPhone);
				mes="用户添加成功";
				return "success";
			}
			
		    }
		
		else if(action.equals("hadLogin")){
			admin = (Admin)ActionContext.getContext().getSession().get("landUser");
			if(admin!=null){
				String functions = admin.getFunctionList();
				if(functions!=null){
					String[] functionIds = functions.split(":");
					for(int i=0;i<functionIds.length;i++){
						Long functionId = Long.valueOf(functionIds[i]);
						
						//updated by zhong at 20180820:过滤无效的功能，只显示有效功能
						//String function = adminBo.findFunctionNameById(functionId);
						String function = adminBo.getFunctionByFunctionId(functionId);
						if(function!=null){
							func.add(function);
						}						
						function="";
					}
					Long roleIdSu = admin.getRoleId();
					roleName = adminBo.getRoleNameById(roleIdSu);
					mes="已登录";
					return"success";
				}
				return"success";
			}
		}
		
		else if(action.equals("update")){
		    	Admin admin=adminBo.findAdminByNmame(adminName);
		    	if(admin==null){
		    		admin=adminBo.findAdminById(adminId);
		    		if(!admin.getPassword().equals(password)){
		    			password=EncryptUtil.getMD5Str(password);
		    		}
		    		adminBo.update(adminId, adminName, password, roleId, functionId, status,contactName,contactPhone);
		    		mes="更新成功";
			    	return "success";	
		    	}else if(admin.getId().equals(adminId)){
		    		if(!admin.getPassword().equals(password)){
		    			password=EncryptUtil.getMD5Str(password);
		    		}
		    		adminBo.update(adminId, adminName, password, roleId, functionId, status,contactName,contactPhone);
		    		mes="更新成功";
			    	return "success";
		    	} else {
		    		mes="该用户名已被占用";
		    	    return "success";
		    	}
		    	
		    }	
		    else if(action.equals("updateMyInfo")){
		    	Admin admin=adminBo.findAdminByAdminNmame(adminName);
		    	String sPassword=password;
		    	if(admin==null){
		    		admin=adminBo.findAdminById(adminId);
		    		if(!admin.getPassword().equals(password)){
		    			sPassword=EncryptUtil.getMD5Str(password);
		    		}
		    		adminBo.updateMyInfo(adminId, adminName, sPassword);
			    	mes="更新成功";
			    	return "success";
		    	}
		    	else if(admin.getId().equals(adminId)){
		    		    if(!admin.getPassword().equals(password)){
		    			    sPassword=EncryptUtil.getMD5Str(password);
		    		    }
			    		adminBo.updateMyInfo(adminId, adminName, sPassword);
				    	mes="更新成功";
				    	return "success";		    		
			    	}else{
			    	    mes="该用户名已被占用";
			    	    return "success";
			    	}
		    	

		    }
		    else if(action.equals("showMyName"))
		    {
		    	admin=(Admin)ActionContext.getContext().getSession().get("landUser");
		    	if(admin!=null)
		    	{
		    		String ad=admin.getContactName();
		    		if(ad==null)
		    		{
		    	      myName=admin.getName();
		    		}
		    		
		    		else myName=ad;
		    	}
		    	else
		    	myName="";
		    	
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

	public void setPassword(String password) {
		this.password = password;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
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

	public String getRoleName() {
		return roleName;
	}

	public List<String> getFunc() {
		return func;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setFunc(List<String> func) {
		this.func = func;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getMyName() {
		return myName;
	}

}
