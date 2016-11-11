package com.java.familydoctor.archivesinfo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.archivesinfo.service.UserTypeService;
import com.java.familydoctor.archivesinfo.vo.UserType;
import com.java.platform.user.service.ServiceImpl;

public class UserTypeServiceImpl extends ServiceImpl implements UserTypeService {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		
		return null;
	}

	@SuppressWarnings("unused")
	@Override
	public Long saveOrUpdateUserType(UserType userType) {
		if(userType == null){
			return null;
		}
		Long id = null;
		if(userType.getId() == null){
			id = (Long)super.add(userType);
		}else{
			id = userType.getId();
			super.edit(userType);
		}
		return id;
	}

	@Override
	public UserType getUserTypeById(Long id) {
		
		return super.get(id, UserType.class);
	}

	@Override
	public List<UserType> getUserTypePage(PageSortModel psm, UserType userType) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM UserType u where 1=1");
		hql.append(" and u.isEnable = 'Y'");
		if(userType != null){
			if(userType.getTypeName() != null && !"".equals(userType.getTypeName())){
				param.put("typeName", "%" + userType.getTypeName()+ "%");
				hql.append(" and u.typeName LIKE :typeName");
			}
		}
		List<UserType> list = (List<UserType>) listForEc(hql.toString(),psm, param);
		return list;
	}

}
