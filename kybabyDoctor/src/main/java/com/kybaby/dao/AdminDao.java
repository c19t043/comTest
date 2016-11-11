package com.kybaby.dao;

import com.kybaby.domain.Admin;

public interface AdminDao {
	Admin findAdminByAdminName(String adminName);
}
