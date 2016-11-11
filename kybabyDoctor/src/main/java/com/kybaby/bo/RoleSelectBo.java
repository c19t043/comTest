package com.kybaby.bo;

import com.kybaby.newbussiness.doctorclinic.domain.RoleSelect;

public interface RoleSelectBo {
	//通过电话查找角色信息
	RoleSelect getRoleSelectByPhone(String phone);
	
	//保存角色信息，在第一次提交信息前生效
	void save(RoleSelect roleSelect);
}
