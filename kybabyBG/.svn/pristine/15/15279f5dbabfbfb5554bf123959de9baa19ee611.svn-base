package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.Admin;
import com.kybaby.domain.FunctionList;
import com.kybaby.domain.RoleList;

public interface AdminDao {
	void save(Admin admin);
	
	void update(Admin admin);
	
	void save(FunctionList function);
	
	void save(RoleList role);
	
	void update(FunctionList functionList);
	
	void update(RoleList roleList);
	
	Admin findAdminByAdminName(String adminName);
	
	//status=Y or N
	Admin findAdminByNmame(String adminName);
	
	Admin findAdminById(Long adminId);
	
	List<Admin> getAdminList();
	
	List<Admin> getAdminByRoleId(Long roleId);
	
	List<FunctionList> getFunctionList();
	
	//获取一级模块
    List<FunctionList> getParentFunctionList();
	
	FunctionList findFunctionByName(String functionName);
	
	FunctionList findFunctionById(Long functionId);
	
	List<String> getFunctionNameByIdList(String idList);
	
	String getFunctionNameById(Long functionId);	
	
	String getFunctionByRole(String roleName);
	
	List<RoleList> getRoleList();
	
	RoleList findRoleByName(String roleName);
	
	String getRoleNameById(Long roleId);
	
	RoleList findRoleById(Long roleId);
	
	//获取所有 角色列表
	List<String> getRoleNameAndIdList();
	
	//获取当前登录管理员的角色
	String getRoleByRoleId(long roleId);
	//获取当前登录管理员功能
	String getFunctionByFunctionId(long functionId);
	//获取管理员所属加盟商
	String getAllianceByAllianceId(long allianceId);
	//获取管理员权限名称
	String findFunctionNameById(Long functionId);
	
	List getAllFunction();
}
