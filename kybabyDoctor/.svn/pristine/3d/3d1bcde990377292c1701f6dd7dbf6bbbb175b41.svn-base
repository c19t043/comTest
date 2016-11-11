package com.kybaby.bo.impl;

import com.kybaby.bo.AdminBo;
import com.kybaby.dao.AdminDao;
import com.kybaby.domain.Admin;

public class AdminBoImpl implements AdminBo {
	AdminDao adminDao;
	public AdminDao getAdminDao() {
		return adminDao;
	}
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	@Override
	public Admin findAdminByAdminNmame(String adminName) {
		return adminDao.findAdminByAdminName(adminName);
	}

}
