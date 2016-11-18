package com.java.orgManager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.java.ec.common.PageSortModel;
import com.java.orgManager.service.OrganModuleInfoService;
import com.java.orgManager.vo.OrganModuleInfo;
import com.java.orgManager.vo.OrganRole;
import com.java.platform.user.service.ServiceImpl;

public class OrganModuleInfoServiceImpl  extends ServiceImpl implements OrganModuleInfoService {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}

	@Override
	public List<OrganModuleInfo> getOrganModuleInfoByPage(PageSortModel model,OrganModuleInfo organModuleInfo) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM OrganModuleInfo u where 1=1 ");
		if(organModuleInfo != null){
			if(StringUtils.isNotEmpty(organModuleInfo.getModuleName())){
				hql.append(" and u.moduleName like :moduleName");
				param.put("moduleName","%"+ organModuleInfo.getModuleName()+"%");
			}
		}
		List<OrganModuleInfo> list = (List<OrganModuleInfo>) listForEc(hql.toString(),model, param);
		for (OrganModuleInfo om : list) {
			List<OrganRole> organRoleByModuleID = getOrganRoleByModuleID(om.getId());
			om.setOrganRoles(organRoleByModuleID);
		}
		return list;
	}
	private List<OrganRole> getOrganRoleByModuleID(Long moduleID){
		Map<String,Object> param = new HashMap<String,Object>();
		String hql = "select o.organRole from OrganRoleModule o where o.organModuleInfo.id = :mid";
		param.put("mid", moduleID);
		return super.list(hql, -1, -1, param);
	}

	@Override
	public OrganModuleInfo saveOrUpdateOrganModuleInfo(OrganModuleInfo organModuleInfo) {
		Long id = organModuleInfo.getId();
		if(id==null){
			super.add(organModuleInfo);
		}else{
			OrganModuleInfo qryomi = super.get(id, OrganModuleInfo.class);
			BeanUtils.copyProperties(organModuleInfo, qryomi, new String[]{"id"});
			super.edit(qryomi);
			return qryomi;
		}
		return organModuleInfo;
	}

}
