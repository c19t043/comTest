package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.RoleSelectDao;
import com.kybaby.newbussiness.doctorclinic.domain.RoleSelect;

public class RoleSelectDaoImpl extends HibernateDaoSupport implements RoleSelectDao {

	@Override
	public RoleSelect getRoleSelectByPhone(String phone) {
		@SuppressWarnings("unchecked")
		List<RoleSelect> list = getHibernateTemplate().find("from RoleSelect where phone=? ",phone);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}
	
	@Override
	public void save(RoleSelect roleSelect) {
		getHibernateTemplate().save(roleSelect);
	}
}
