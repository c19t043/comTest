package com.kybaby.bo.impl;

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
	public Admin findAdminByAdminNmame(String adminName) {
		return adminDao.findAdminByAdminName(adminName);
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#save(com.kybaby.domain.Admin)
	 */
	@Override
	public void save(Admin admin) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#update(com.kybaby.domain.Admin)
	 */
	@Override
	public void update(Admin admin) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#save(com.kybaby.domain.FunctionList)
	 */
	@Override
	public void save(FunctionList function) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#save(com.kybaby.domain.RoleList)
	 */
	@Override
	public void save(RoleList role) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#update(com.kybaby.domain.FunctionList)
	 */
	@Override
	public void update(FunctionList functionList) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#update(com.kybaby.domain.RoleList)
	 */
	@Override
	public void update(RoleList roleList) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#findAdminByNmame(java.lang.String)
	 */
	@Override
	public Admin findAdminByNmame(String adminName) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#findAdminById(java.lang.Long)
	 */
	@Override
	public Admin findAdminById(Long adminId) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#getAdminList()
	 */
	@Override
	public List<Admin> getAdminList() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#getAdminByRoleId(java.lang.Long)
	 */
	@Override
	public List<Admin> getAdminByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#add(java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean add(String adminName, String password, Long roleId,
			String functionList, String status) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#update(java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public void update(Long adminId, String adminName, String password,
			Long roleId, String functionList, String status) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#updateMyInfo(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateMyInfo(Long adminId, String adminName,
			String adminPassword) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#getFunctionList()
	 */
	@Override
	public List<FunctionList> getFunctionList() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#getParentFunctionList()
	 */
	@Override
	public List<FunctionList> getParentFunctionList() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#findFunctionByName(java.lang.String)
	 */
	@Override
	public FunctionList findFunctionByName(String functionName) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#findFunctionById(java.lang.Long)
	 */
	@Override
	public FunctionList findFunctionById(Long functionId) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#getFunctionNameByIdList(java.lang.String)
	 */
	@Override
	public List<String> getFunctionNameByIdList(String idList) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#getFunctionNameById(java.lang.Long)
	 */
	@Override
	public String getFunctionNameById(Long functionId) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#addFunction(java.lang.String)
	 */
	@Override
	public boolean addFunction(String functionName) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#getFunctionByRole(java.lang.String)
	 */
	@Override
	public String getFunctionByRole(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#updateFunctionByFunctionId(java.lang.Long, java.lang.String)
	 */
	@Override
	public void updateFunctionByFunctionId(Long functionId,
			String functionStatus) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#getRoleList()
	 */
	@Override
	public List<RoleList> getRoleList() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#addRole(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean addRole(String roleName, String functionList) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#findRoleByName(java.lang.String)
	 */
	@Override
	public RoleList findRoleByName(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#findRoleById(java.lang.Long)
	 */
	@Override
	public RoleList findRoleById(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#getRoleNameById(java.lang.Long)
	 */
	@Override
	public String getRoleNameById(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#updateRoleById(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateRoleById(Long roleId, String roleName, String roleFunction) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#getRoleNameAndIdList()
	 */
	@Override
	public List<String> getRoleNameAndIdList() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#getRoleByRoleId(long)
	 */
	@Override
	public String getRoleByRoleId(long roleId) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#getFunctionByFunctionId(long)
	 */
	@Override
	public String getFunctionByFunctionId(long functionId) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#getAllianceByAllianceId(long)
	 */
	@Override
	public String getAllianceByAllianceId(long allianceId) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.kybaby.bo.AdminBo#findFunctionNameById(java.lang.Long)
	 */
	@Override
	public String findFunctionNameById(Long functionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
