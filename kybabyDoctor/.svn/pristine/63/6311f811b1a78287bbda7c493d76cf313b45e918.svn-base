package com.kybaby.bo.impl;

import com.kybaby.bo.RoleSelectBo;
import com.kybaby.dao.RoleSelectDao;
import com.kybaby.newbussiness.doctorclinic.domain.RoleSelect;

public class RoleSelectBoImpl implements RoleSelectBo {
	
	private RoleSelectDao roleSelectDao;

	public RoleSelectDao getRoleSelectDao() {
		return roleSelectDao;
	}
	public void setRoleSelectDao(RoleSelectDao roleSelectDao) {
		this.roleSelectDao = roleSelectDao;
	}

	@Override
	public RoleSelect getRoleSelectByPhone(String phone) {
		return roleSelectDao.getRoleSelectByPhone(phone);
	}
	
	@Override
	public void save(RoleSelect roleSelect) {
		roleSelectDao.save(roleSelect);
	}


}
