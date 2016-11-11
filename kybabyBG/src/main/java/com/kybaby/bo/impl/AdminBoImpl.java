package com.kybaby.bo.impl;

import java.sql.Timestamp;
import java.util.List;

import com.kybaby.bo.AdminBo;
import com.kybaby.dao.AdminDao;
import com.kybaby.domain.Admin;
import com.kybaby.domain.FunctionList;
import com.kybaby.domain.RoleList;

public class AdminBoImpl implements AdminBo {
	AdminDao adminDao;
	public AdminDao getAdminDao() {
		return adminDao;
	}
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	public void save(Admin admin) {
		adminDao.save(admin);
	}
	
	public void update(Admin admin) {
		adminDao.update(admin);
	}
	
	public void save(FunctionList function){
		adminDao.save(function);
	}
	
	public void save(RoleList role){
		adminDao.save(role);
	}
	
	public void update(FunctionList functionList){
		adminDao.update(functionList);
	}
	
	public void update(RoleList roleList){
		adminDao.update(roleList);
	}
	
	public Admin findAdminByAdminNmame(String adminName) {
		return adminDao.findAdminByAdminName(adminName);
	}
	
	//status=Y or N
	public Admin findAdminByNmame(String adminName){
		return adminDao.findAdminByNmame(adminName);
	}
	
	public Admin findAdminById(Long adminId){
		return adminDao.findAdminById(adminId);
	}
	
	public List<Admin> getAdminList() {
		return adminDao.getAdminList();
	}
	
	public List<Admin> getAdminByRoleId(Long roleId){
		return adminDao.getAdminByRoleId(roleId);
	}
    
	/*
	public boolean add(String adminName, String name, String phone, String password){
		Timestamp curr = new Timestamp(System.currentTimeMillis());
		
		Admin newAdmin = new Admin();
		newAdmin.setName(adminName);
		newAdmin.setContactName(name);
		newAdmin.setContactPhone(phone);
		newAdmin.setPassword(password);
		newAdmin.setRegistTime(curr);
		newAdmin.setStatus("N");
		newAdmin.setUpdateTime(curr);
		
		adminDao.save(newAdmin);
		return true;
	}*/
	public boolean add(String adminName, String password, Long roleId, String functionList, String status, String contactName, String contactPhone){
		Timestamp curr = new Timestamp(System.currentTimeMillis());
		
		Admin newAdmin = new Admin();
		newAdmin.setName(adminName);
		newAdmin.setPassword(password);
		newAdmin.setRegistTime(curr);
		newAdmin.setStatus(status);
		newAdmin.setUpdateTime(curr);
		newAdmin.setRoleId(roleId);
		newAdmin.setContactName(contactName);
		newAdmin.setContactPhone(contactPhone);
		newAdmin.setFunctionList(functionList);
		
		adminDao.save(newAdmin);
		return true;
	}
	
	public void update(Long adminId, String adminName, String password, Long roleId, String functionList, String status, String contactName, String contactPhone){
		Timestamp curr = new Timestamp(System.currentTimeMillis());
		Admin admin=adminDao.findAdminById(adminId);
		admin.setFunctionList(functionList);
		admin.setName(adminName);
		admin.setPassword(password);
		admin.setRoleId(roleId);
		admin.setStatus(status);
		admin.setUpdateTime(curr);
		admin.setContactName(contactName);
		admin.setContactPhone(contactPhone);
		adminDao.update(admin);
	}
	
	//修改个人信息
	public void updateMyInfo(Long adminId, String adminName, String adminPassword){
		Timestamp curr = new Timestamp(System.currentTimeMillis());
		Admin admin=adminDao.findAdminById(adminId);
		admin.setName(adminName);
		admin.setPassword(adminPassword);
		admin.setUpdateTime(curr);
		adminDao.update(admin);
	}
	
	
	public List<FunctionList> getFunctionList(){
		return adminDao.getFunctionList();
	}
	
	//获取一级模块
    public List<FunctionList> getParentFunctionList(){
    	return adminDao.getParentFunctionList();
    }
	
	public FunctionList findFunctionByName(String functionName){
		return adminDao.findFunctionByName(functionName);
	}
	
	public FunctionList findFunctionById(Long functionId){
		return adminDao.findFunctionById(functionId);
	}
	
	public List<String> getFunctionNameByIdList(String idList){
		return adminDao.getFunctionNameByIdList(idList);
	}
	
	public String getFunctionNameById(Long functionId){
		return adminDao.getFunctionNameById(functionId);
	}
	
	public boolean addFunction(String functionName){
		
		FunctionList newFunction = new FunctionList();
		newFunction.setName(functionName);
		newFunction.setStatus("Y");
		
		adminDao.save(newFunction);
		return true;
	}
	
	public String getFunctionByRole(String roleName){
		return adminDao.getFunctionByRole(roleName);
	}
	
	public void updateFunctionByFunctionId(Long functionId, String functionStatus){
		FunctionList newFunction = adminDao.findFunctionById(functionId);
		newFunction.setStatus(functionStatus);
		
		adminDao.update(newFunction);
	}
	
	public List<RoleList> getRoleList(){
		return adminDao.getRoleList();
	}
	
	public boolean addRole(String roleName, String functionList){
		RoleList newRole = new RoleList();
		newRole.setName(roleName);
		newRole.setFunctionList(functionList);
		newRole.setStatus("Y");
		adminDao.save(newRole);
		return true;
	}
	
	public RoleList findRoleByName(String roleName){
		return adminDao.findRoleByName(roleName);
	}
	
	public RoleList findRoleById(Long roleId){
		return adminDao.findRoleById(roleId);
	}
	
	public String getRoleNameById(Long roleId){
		return adminDao.getRoleNameById(roleId);
	}
	
	public void updateRoleById(Long roleId, String roleName, String roleFunction){
		RoleList role=adminDao.findRoleById(roleId);
		role.setName(roleName);
		role.setFunctionList(roleFunction);
		adminDao.update(role);
	}
	
	//获取所有 角色列表
	public List<String> getRoleNameAndIdList(){
		return adminDao.getRoleNameAndIdList();
	}
	@Override
	public String getRoleByRoleId(long roleId) {
		return adminDao.getRoleByRoleId(roleId);
	}
	@Override
	public String getFunctionByFunctionId(long functionId) {
		
		return adminDao.getFunctionByFunctionId(functionId);
	}
	@Override
	public String getAllianceByAllianceId(long allianceId) {
		
		return adminDao.getAllianceByAllianceId(allianceId);
	}
	//获取管理员权限名称
	public String findFunctionNameById(Long functionId) {
		return adminDao.findFunctionNameById(functionId);
	}
	@Override
	public boolean add(String adminName, String name, String phone,
			String password) {
		
		return false;
	}
	@Override
	public List getAllFunction() {
		// TODO Auto-generated method stub
		return adminDao.getAllFunction();
	}

}
