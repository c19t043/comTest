package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.Admin;
import com.kybaby.domain.FunctionList;
import com.kybaby.domain.RoleList;

public interface AdminBo {
	void save(Admin admin);
	
	void update(Admin admin);
	
	void save(FunctionList function);
	
	void save(RoleList role);
	
	void update(FunctionList functionList);
	
	void update(RoleList roleList);
	
	//status=Y
	Admin findAdminByAdminNmame(String adminName);
	
	//status=Y or N
	Admin findAdminByNmame(String adminName);
	
	Admin findAdminById(Long adminId);
	
	List<Admin> getAdminList();
	
	List<Admin> getAdminByRoleId(Long roleId);
	
	boolean add(String adminName, String name, String phone, String password);
	boolean add(String adminName, String password, Long roleId, String functionList, String status, String contactName, String contactPhone);
	
	void update(Long adminId, String adminName, String password, Long roleId, String functionList, String status, String contactName, String contactPhone);
	
	//修改个人信息
	void updateMyInfo(Long adminId, String adminName, String adminPassword);
	
	//获取二级模块
    List<FunctionList> getFunctionList();
    
    //获取一级模块
    List<FunctionList> getParentFunctionList();
	
	FunctionList findFunctionByName(String functionName);
	
	FunctionList findFunctionById(Long functionId);
	
	List<String> getFunctionNameByIdList(String idList);
	
	String getFunctionNameById(Long functionId);
	
	boolean addFunction(String functionName);
	
	String getFunctionByRole(String roleName);
	
	void updateFunctionByFunctionId(Long functionId, String functionStatus);
	
	List<RoleList> getRoleList();
	
	boolean addRole(String roleName, String functionList);
	
	RoleList findRoleByName(String roleName);
	
	RoleList findRoleById(Long roleId);
	
	String getRoleNameById(Long roleId);
	
	void updateRoleById(Long roleId, String roleName, String roleFunction);
	
	//获取所有 角色列表
	List<String> getRoleNameAndIdList();
	//获取当前登录管理员角色
	String getRoleByRoleId(long roleId);
	//获取当前登录管理员功能
	String getFunctionByFunctionId(long functionId);
	//获取管理员所属加盟商
	String getAllianceByAllianceId(long allianceId);
	//获取管理员权限名称
	String findFunctionNameById(Long functionId);
	
	List getAllFunction();
	
}
