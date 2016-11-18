package com.java.orgManager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.java.doctorinfo.vo.OrganOperator;
import com.java.ec.common.PageSortModel;
import com.java.orgManager.service.OrgOperatorManagerService;
import com.java.orgManager.vo.OrganRole;
import com.java.platform.user.service.ServiceImpl;
import com.java.util.EncryptUtil;

public class OrgOperatorManagerServiceImpl extends ServiceImpl implements OrgOperatorManagerService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<OrganOperator> getOrganOperatorByPage(PageSortModel model,
			OrganOperator organOperator) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM OrganOperator u where 1=1 ");
		if(organOperator != null){
			if(StringUtils.isNotEmpty(organOperator.getShowName())){
				hql.append(" and u.showName like :showName");
				param.put("showName","%"+ organOperator.getShowName()+"%");
			}
			if(StringUtils.isNotEmpty(organOperator.getLoginName())){
				hql.append(" and u.loginName like :loginName");
				param.put("loginName","%"+ organOperator.getLoginName()+"%");
			}
			if(organOperator.getHospitalBasicInfo()!=null&&
					StringUtils.isNotEmpty(organOperator.getHospitalBasicInfo().getHospitalLname())){
				hql.append(" and u.hospitalBasicInfo.hospitalLname like :hospitalLname");
				param.put("hospitalLname","%"+ organOperator.getHospitalBasicInfo().getHospitalLname()+"%");
			}
			if(StringUtils.isNotEmpty(organOperator.getIsEnable())){
				hql.append(" and u.isEnable like :isEnable");
				param.put("isEnable",organOperator.getIsEnable());
			}
		}
		List<OrganOperator> list = (List<OrganOperator>) listForEc(hql.toString(),model, param);
		for (OrganOperator opera : list) {
			List<OrganRole> organRoleByOperaID = getOrganRoleByOperaID(opera.getId());
			opera.setOrganRoles(organRoleByOperaID);
		}
		return list;
	}
	private List<OrganRole> getOrganRoleByOperaID(Long operaID){
		Map<String,Object> param = new HashMap<String,Object>();
		String hql = "select o.organRole from OrganOperatorRole o where o.organOperator.id = :oid";
		param.put("oid", operaID);
		return super.list(hql, -1, -1, param);
	}
	
	@Override
	public OrganOperator saveOrUpdateOrganOperator(OrganOperator organOperator) {
		Long id = organOperator.getId();
		if(id==null){
			if(StringUtils.isNotBlank(organOperator.getPassword())){
				organOperator.setPassword(EncryptUtil.getMD5Str(organOperator.getPassword()));
			}
			super.add(organOperator);
		}else{
			OrganOperator opera = super.get(id, OrganOperator.class);
			if(StringUtils.isNotBlank(organOperator.getPassword())&&!organOperator.getPassword().equals(opera.getPassword())){
				opera.setPassword(EncryptUtil.getMD5Str(organOperator.getPassword()));
			}
			BeanUtils.copyProperties(organOperator, opera, new String[]{"id","password"});
			super.edit(opera);
			return opera;
		}
		return organOperator;
	}

}
