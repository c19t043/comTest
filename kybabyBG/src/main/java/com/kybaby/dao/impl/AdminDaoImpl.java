package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.AdminDao;
import com.kybaby.domain.Admin;
import com.kybaby.domain.FunctionList;
import com.kybaby.domain.RoleList;

public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {
	
	public void save(Admin admin){
		getHibernateTemplate().save(admin);
	}
	
	public void update(Admin admin){
		getHibernateTemplate().update(admin);
	}
	
	public void save(FunctionList function){
		getHibernateTemplate().save(function);
	}
	
	public void save(RoleList role){
		getHibernateTemplate().save(role);
	}
	
	public void update(FunctionList functionList){
		getHibernateTemplate().update(functionList);
	}
	
	public void update(RoleList roleList){
		getHibernateTemplate().update(roleList);
	}
	
	@SuppressWarnings("rawtypes")
	public Admin findAdminByAdminName(String adminName) {
		List list=getHibernateTemplate().find("from Admin where name=? and status='Y'", adminName);
		if(list.isEmpty()==true){
			return null;
		}
		return (Admin)list.get(0);
	}
	
	//status=Y or N
	@SuppressWarnings("rawtypes")
	public Admin findAdminByNmame(String adminName){
		List list=getHibernateTemplate().find("from Admin where name=?", adminName);
		if(list.isEmpty()==true){
			return null;
		}
		return (Admin)list.get(0);
	}
	
	@SuppressWarnings("rawtypes")
	public Admin findAdminById(Long adminId){
		List list=getHibernateTemplate().find("from Admin where id=?", adminId);
		if(list.isEmpty()==true){
			return null;
		}
		return (Admin)list.get(0);
	}
	

	
	@SuppressWarnings("unchecked")
	public List<Admin> getAdminList(){
		List<Admin> list=getHibernateTemplate().find("from Admin");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
				
	}
	
	@SuppressWarnings("unchecked")
	public List<Admin> getAdminByRoleId(Long roleId){
		List<Admin> list=getHibernateTemplate().find("from Admin where roleId=?",roleId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<FunctionList> getFunctionList(){
		List<FunctionList> list=getHibernateTemplate().find("from FunctionList where parentId!=0");
		//final String strSql="SELECT aa.id, aa.name,bb.name, aa.status from FunctionList as aa, (SELECT id, name FROM FunctionList WHERE parentId=0) as bb WHERE aa.parentId !=0 AND aa.parentId=bb.id";
		//List<FunctionList> list=getHibernateTemplate().find(strSql);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}
	
	//获取一级模块
    @SuppressWarnings("unchecked")
	public List<FunctionList> getParentFunctionList(){
		List<FunctionList> list=getHibernateTemplate().find("from FunctionList where parentId=0");
		//final String strSql="SELECT aa.id, aa.name,bb.name, aa.status from FunctionList as aa, (SELECT id, name FROM FunctionList WHERE parentId=0) as bb WHERE aa.parentId !=0 AND aa.parentId=bb.id";
		//List<FunctionList> list=getHibernateTemplate().find(strSql);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
    }
	
	@SuppressWarnings("rawtypes")
	public FunctionList findFunctionByName(String functionName){
		List list=getHibernateTemplate().find("from FunctionList where name=?", functionName);
		if(list.isEmpty()==true){
			return null;
		}
		return (FunctionList)list.get(0);
	}
	
	
	@SuppressWarnings("rawtypes")
	public FunctionList findFunctionById(Long functionId){
		List list=getHibernateTemplate().find("from FunctionList where id=?", functionId);
		if(list.isEmpty()==true){
			return null;
		}
		return (FunctionList)list.get(0);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> getFunctionNameByIdList(String idList){
		String strSql="select * from FunctionList where id in ("+idList+")";
		//List list=getHibernateTemplate().find("from FunctionList where id in (?)", idList);
		List list=getHibernateTemplate().find(strSql);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public String getFunctionNameById(Long functionId){
		List list=getHibernateTemplate().find("select name from FunctionList where id = ?", functionId);
		if(list.isEmpty()==true){
			return null;
		}
		return (String) list.get(0);
	}
	
	@SuppressWarnings("rawtypes")
	public String getFunctionByRole(String roleName){
		List list=getHibernateTemplate().find("select functionList from RoleList where name=?", roleName);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<RoleList> getRoleList(){
		List<RoleList> list=getHibernateTemplate().find("from RoleList");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}
	
	
	@SuppressWarnings("rawtypes")
	public RoleList findRoleByName(String roleName){
		List list=getHibernateTemplate().find("from RoleList where name=?", roleName);
		if(list.isEmpty()==true){
			return null;
		}
		return (RoleList)list.get(0);
	}
	
	@SuppressWarnings("rawtypes")
	public String getRoleNameById(Long roleId){
		List list=getHibernateTemplate().find("select name from RoleList where id=?", roleId);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}
	
	@SuppressWarnings("rawtypes")
	public RoleList findRoleById(Long roleId){
		List list=getHibernateTemplate().find("from RoleList where id=?", roleId);
		if(list.isEmpty()==true){
			return null;
		}
		return (RoleList)list.get(0);
	}
	
	//获取所有 角色列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<String> getRoleNameAndIdList(){
		List list=getHibernateTemplate().find("select id, name from RoleList");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public String getRoleByRoleId(long roleId) {
		List list=getHibernateTemplate().find("select name from RoleList where id = ?",roleId);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

	@Override
	public String getFunctionByFunctionId(long functionId) {
		List list=getHibernateTemplate().find("select name from FunctionList where id=? and status='Y'",functionId);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0); 
	}

	@Override
	public String getAllianceByAllianceId(long allianceId) {
		List list=getHibernateTemplate().find("select name from Alliance where id=?",allianceId);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

	@Override
	public String findFunctionNameById(Long functionId) {
		List list=getHibernateTemplate().find("select name from FunctionList where id=?",functionId);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

	@Override
	public List getAllFunction() {
		String hql="SELECT a.name,b.id,b.name,b.status FROM FunctionParent a,FunctionList b WHERE a.id=b.parentId";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
		{
			return null;
		}
		return list;
	}
}
