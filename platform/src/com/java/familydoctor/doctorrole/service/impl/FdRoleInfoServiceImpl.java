package com.java.familydoctor.doctorrole.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.doctorrole.service.FdRoleInfoService;
import com.java.familydoctor.doctorrole.vo.FdRoleInfo;
import com.java.platform.user.service.ServiceImpl;

public class FdRoleInfoServiceImpl extends ServiceImpl implements FdRoleInfoService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		
		return null;
	}

	@Override
	public Long saveOrUpdateFdRoleInfo(FdRoleInfo fdRoleInfo) {
		if(fdRoleInfo == null){
			return null;
		}
		
		Long id = null;
		if(fdRoleInfo.getId() == null){
			id = (Long) super.add(fdRoleInfo);
		}else{
			id = fdRoleInfo.getId();
			super.edit(fdRoleInfo);
		}
		return id;
	}

	@Override
	public FdRoleInfo getFdRoleInfoById(Long id) {
		
		return super.get(id, FdRoleInfo.class);
	}

	@Override
	public List<FdRoleInfo> getFdRoleInfoListByPage(PageSortModel psm,
			FdRoleInfo fdRoleInfo) {
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuffer hql = new StringBuffer("from FdRoleInfo f where 1=1");
		//条件查询
		if(fdRoleInfo != null){
			if(fdRoleInfo.getRoleName() != null && !"".equals(fdRoleInfo.getRoleName().trim())){
				params.put("roleName", "%" + fdRoleInfo.getRoleName().trim() + "%");
				hql.append("AND f.roleName LIKE :roleName");
			}
		}
		List<FdRoleInfo> list = (List<FdRoleInfo>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}

}
