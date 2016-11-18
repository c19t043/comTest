package com.java.orgManager.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.java.doctorinfo.vo.OrganOperator;
import com.java.ec.common.PageSortModel;
import com.java.orgManager.service.OrganRoleService;
import com.java.orgManager.vo.OrganModuleInfo;
import com.java.orgManager.vo.OrganOperatorRole;
import com.java.orgManager.vo.OrganRole;
import com.java.orgManager.vo.OrganRoleModule;
import com.java.platform.user.service.ServiceImpl;

public class OrganRoleServiceImpl  extends ServiceImpl implements OrganRoleService {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}

	@Override
	public List<OrganRole> getOrganRoleByPage(PageSortModel model,
			OrganRole organRole) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM OrganRole u where 1=1 ");
		if(organRole != null){
			if(StringUtils.isNotEmpty(organRole.getRoleName())){
				hql.append(" and u.roleName like :roleName");
				param.put("roleName","%"+ organRole.getRoleName()+"%");
			}
			if(StringUtils.isNotEmpty(organRole.getRoleCode())){
				hql.append(" and u.roleCode like :roleCode");
				param.put("roleCode","%"+ organRole.getRoleCode()+"%");
			}
		}
		List<OrganRole> list = (List<OrganRole>) listForEc(hql.toString(),model, param);
		return list;
	}

	@Override
	public OrganRole saveOrUpdateOrganRole(OrganRole organRole) {
		Long id = organRole.getId();
		if(id==null){
			super.add(organRole);
		}else{
			OrganRole qryor = super.get(id, OrganRole.class);
			BeanUtils.copyProperties(organRole, qryor, new String[]{"id"});
			super.edit(qryor);
			return qryor;
		}
		return organRole;
	}

	@Override
	public List<OrganOperator> queryRoleOwnedUsers(Long roleID) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("select o.organOperator FROM OrganRole u,OrganOperatorRole o where 1=1 ");
		hql.append(" and u.id = o.organRole.id");
		hql.append(" and u.id = :rid");
		param.put("rid", roleID);
		return super.list(hql.toString(), -1, -1, param);
	}

	@Override
	public void saveRoleOwnedUsers(Long roleID,String operatorIDs) {
		//删除角色的所有分配用户
		deleteRoleOwnedUsers(roleID);
		if(StringUtils.isBlank(operatorIDs)) return;
		String[] operatorIDArr = (operatorIDs+",").split(",");
		for (String operatorID : operatorIDArr) {
			super.add(new OrganOperatorRole(new OrganOperator(Long.parseLong(operatorID)),new OrganRole(roleID)));
		}
	}
	/**
	 * 删除角色的所有分配用户
	 * @param roleID
	 */
	private void deleteRoleOwnedUsers(final Long roleID){
		super.getPersistProxy().getOrmPersistence().getHibernateTemp().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql = "delete OrganOperatorRole c where c.organRole.id = :rid";
				Query createQuery = session.createQuery(hql);
				createQuery.setLong("rid", roleID);
				return createQuery.executeUpdate();
			}
		});
	}

	@Override
	public List<OrganModuleInfo> queryRoleOwnedModules(Long roleID) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("select o.organModuleInfo FROM OrganRole u,OrganRoleModule o where 1=1 ");
		hql.append(" and u.id = o.organRole.id");
		hql.append(" and u.id = :rid");
		param.put("rid", roleID);
		List<OrganModuleInfo> list = super.list(hql.toString(), -1, -1, param);
		return list;
	}

	@Override
	public void saveRoleOwnedModules(Long roleID, String moduleIDs) {
		//删除角色的所有分配模块
		deleteRoleOwnedModules(roleID);
		if(StringUtils.isBlank(moduleIDs)) return;
		String[] operatorIDArr = (moduleIDs+",").split(",");
		for (String operatorID : operatorIDArr) {
			super.add(new OrganRoleModule(new OrganModuleInfo(Long.parseLong(operatorID)),new OrganRole(roleID)));
		}
	}
	/**
	 * 删除角色的所有分配模块
	 * @param roleID
	 */
	private void deleteRoleOwnedModules(final Long roleID){
		super.getPersistProxy().getOrmPersistence().getHibernateTemp().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql = "delete OrganRoleModule c where c.organRole.id = :rid";
				Query createQuery = session.createQuery(hql);
				createQuery.setLong("rid", roleID);
				return createQuery.executeUpdate();
			}
		});
	}
}
