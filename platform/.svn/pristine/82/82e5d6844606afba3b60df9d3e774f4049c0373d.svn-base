package com.java.familydoctor.serviceteams.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.classic.Session;
import org.springframework.beans.BeanUtils;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.doctorrole.vo.FdRoleInfo;
import com.java.familydoctor.servicepackage.vo.FdServicePackage;
import com.java.familydoctor.serviceteams.service.FdServiceTeamsService;
import com.java.familydoctor.serviceteams.vo.FdServiceTeamRole;
import com.java.familydoctor.serviceteams.vo.FdServiceTeams;
import com.java.platform.user.service.ServiceImpl;

public class FdServiceTeamsServiceImpl extends ServiceImpl implements FdServiceTeamsService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}

	@Override
	public Long saveOrUpdateFdServiceTeams(FdServiceTeams fdServiceTeams) {
		if(fdServiceTeams == null){
			return null;
		}
		
		//获取角色id
		String doctorRoleIds = fdServiceTeams.getDoctorRoleIds();
		//把角色id以逗号分割成数组
		String[] split = doctorRoleIds.split(",");
		
		Long id = null;
		
		//fdServiceTeams.setPackageIds(fdServiceTeams.getPackageIds());
		
		if(fdServiceTeams.getId() == null){
			//添加服务包与医生团队的关系
			if(StringUtils.isNotEmpty(fdServiceTeams.getPackageIds())){
				String[] packageIds = fdServiceTeams.getPackageIds().split(",");
				for (String packageId : packageIds) {
					FdServicePackage fdPackage = this.get(Long.valueOf(packageId), FdServicePackage.class);
					fdServiceTeams.getFdServicePackageSet().add(fdPackage);
				}
			}
			
			id = (Long) super.add(fdServiceTeams);
			
			//添加医生与团队关系
			FdServiceTeams serviceTeams = new FdServiceTeams();
			serviceTeams.setId(id);
			for (int i = 0; i < split.length; i++) {
				FdServiceTeamRole fdServiceTeamRole = new FdServiceTeamRole();
				FdRoleInfo fdRoleInfo = new FdRoleInfo();
				fdRoleInfo.setId(Long.valueOf(split[i]));
				fdServiceTeamRole.setFdRoleInfo(fdRoleInfo);
				fdServiceTeamRole.setFdServiceTeams(serviceTeams);
				super.add(fdServiceTeamRole);
			}
		}else{
			id = fdServiceTeams.getId();
			FdServiceTeams fdServiceTeamsById = getFdServiceTeamsById(id);
			
			//添加服务包与医生团队的关系
			fdServiceTeamsById.getFdServicePackageSet().clear();
			if(StringUtils.isNotEmpty(fdServiceTeams.getPackageIds())){
				String[] packageIds = fdServiceTeams.getPackageIds().split(",");
				for (String packageId : packageIds) {
					FdServicePackage fdPackage = this.get(Long.valueOf(packageId), FdServicePackage.class);
					fdServiceTeams.getFdServicePackageSet().add(fdPackage);
				}
			}
			
			//添加医生与团队关系
			if(!fdServiceTeamsById.getDoctorRoleIds().equals(fdServiceTeams.getDoctorRoleIds())){
				this.deleteFdServiceTeamRole(id);
				FdServiceTeams serviceTeams = new FdServiceTeams();
				serviceTeams.setId(id);
				for (int i = 0; i < split.length; i++) {
					FdServiceTeamRole fdServiceTeamRole = new FdServiceTeamRole();
					FdRoleInfo fdRoleInfo = new FdRoleInfo();
					fdRoleInfo.setId(Long.valueOf(split[i]));
					fdServiceTeamRole.setFdRoleInfo(fdRoleInfo);
					fdServiceTeamRole.setFdServiceTeams(serviceTeams);
					super.add(fdServiceTeamRole);
				}
			}
			
			
			BeanUtils.copyProperties(fdServiceTeams, fdServiceTeamsById,new String[]{"id"});
			super.edit(fdServiceTeamsById);
		}
		return id;
	}

	@Override
	public FdServiceTeams getFdServiceTeamsById(Long id) {
		return super.get(id, FdServiceTeams.class);
	}

	@Override
	public List<FdServiceTeams> getFdServiceTeamsByPage(PageSortModel psm,FdServiceTeams fdServiceTeams) {
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM FdServiceTeams f WHERE 1=1 ");
		params.put("isEnable","Y");
		hql.append("AND f.isEnable = :isEnable");
		if(fdServiceTeams != null){
			if(fdServiceTeams.getTeamName() != null && !"".equals(fdServiceTeams.getTeamName())){
				params.put("teamName","%" + fdServiceTeams.getTeamName().trim() + "%");
				hql.append(" AND f.teamName LIKE :teamName");
			}
		}
		List<FdServiceTeams> list = (List<FdServiceTeams>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}

	@Override
	public List<FdServiceTeamRole> getFdServiceTeamRoleList(
			Long fdServiceTeamsId, Long doctorRoleId) {
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM FdServiceTeamRole f WHERE 1=1 ");
		if(fdServiceTeamsId != null){
			params.put("fdServiceTeamsId",fdServiceTeamsId);
			hql.append(" AND f.fdServiceTeams.id = :fdServiceTeamsId");
		}
		if(doctorRoleId != null){
			params.put("doctorRoleId", doctorRoleId);
			hql.append(" AND f.fdRoleInfo.id = :doctorRoleId");
		}
		List<FdServiceTeamRole> list = list(hql.toString(), -1, -1, params);
		return list;
	}
	
	@Override
	public void deleteFdServiceTeamRole(Long fdServiceTeamsId) {
		//删除中间表团队角色
		String hql = "delete from fd_service_team_role  where service_teams_id="+fdServiceTeamsId;
		//删除成员
		String hql1 = "delete from fd_service_member  where teams_id="+fdServiceTeamsId;
		//级联删除
		Session openSession = this.persistProxy.getOrmPersistence().getHibernateTemp().getSessionFactory().openSession();
		openSession.createSQLQuery(hql).executeUpdate();
		openSession.createSQLQuery(hql1).executeUpdate();
		openSession.close();
	}
}
