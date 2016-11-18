package com.java.familydoctor.teamrole.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.BeanUtils;

import com.java.common.lang.StringUtils;
import com.java.ec.common.PageSortModel;
import com.java.familydoctor.doctorrole.vo.FdRoleInfo;
import com.java.familydoctor.memberskill.vo.FdMemberSkill;
import com.java.familydoctor.serviceitem.vo.FdServiceItems;
import com.java.familydoctor.serviceteams.vo.FdServiceTeamRole;
import com.java.familydoctor.serviceteams.vo.FdServiceTeams;
import com.java.familydoctor.teamrole.service.TeamRoleService;
import com.java.familydoctor.teamrole.vo.FdServiceMember;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.user.service.ServiceImpl;

public class TeamRoleServiceImpl extends ServiceImpl implements TeamRoleService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}

	@Override
	public List<FdServiceTeamRole> getFdServiceTeamRoleListByPage(
			PageSortModel psm, FdServiceTeamRole fdServiceTeamRole) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM FdServiceTeamRole c WHERE 1=1 ");
		// 条件查询
		if (fdServiceTeamRole != null) {
			if (fdServiceTeamRole.getFdServiceTeams()!= null 
					&& StringUtils.isNotBlank(fdServiceTeamRole.getFdServiceTeams().getTeamName())) {
				params.put("teamName", "%" + fdServiceTeamRole.getFdServiceTeams().getTeamName() + "%");
				hql.append(" AND c.fdServiceTeams.teamName LIKE :teamName");
			}
	
		}
		List<FdServiceTeamRole> list = (List<FdServiceTeamRole>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}

	@Override
	public void saveOrUpdateFdServiceMember(String doctorIds, FdServiceTeams fdServiceTeams, FdRoleInfo fdRoleInfo) {
		String sql = "delete from fd_service_member where role_id="+fdRoleInfo.getId() + " and teams_id=" + fdServiceTeams.getId();
		Session session = this.persistProxy.getOrmPersistence().getHibernateTemp().getSessionFactory().openSession();
		session.createSQLQuery(sql).executeUpdate();
		session.close();
		String [] memIds = doctorIds.split(",");
		for (int i = 0; i < memIds.length; i++) {
			FdServiceMember fdServiceMember = new FdServiceMember();
			DoctorInfo doctorInfo = new DoctorInfo();
			doctorInfo.setId(Long.valueOf(memIds[i]));
			fdServiceMember.setDoctorInfo(doctorInfo);
			fdServiceMember.setFdRoleInfo(fdRoleInfo);
			fdServiceMember.setFdServiceTeams(fdServiceTeams);
			super.add(fdServiceMember);
		}
	}

	@Override
	public FdServiceTeamRole getFdServiceTeamRoleById(Long id) {
		return super.get(id, FdServiceTeamRole.class);
	}

	@Override
	public List<DoctorInfo> getDoctorInfoListByPage(PageSortModel psm,
			DoctorInfo doctorInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM DoctorInfo c WHERE 1=1 ");
		hql.append(" and c.authentication='已通过' ");
		// 条件查
		if (doctorInfo != null) {
			if (doctorInfo.getDoctorName() != null && !"".equals(doctorInfo.getDoctorName().trim())) {
				params.put("doctorName", "%" + doctorInfo.getDoctorName().trim() + "%");
				hql.append(" AND c.doctorName LIKE :doctorName");
			}
		}
		List<DoctorInfo> list = (List<DoctorInfo>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}

	@Override
	public List<FdServiceMember> getFdServiceMemberByPage(PageSortModel psm,
			FdServiceMember fdServiceMember, FdServiceTeams fdServiceTeams,
			FdRoleInfo fdRoleInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM FdServiceMember c WHERE 1=1 ");
		// 条件查询
		if(fdServiceMember != null){
			if(fdServiceMember.getDoctorInfo() != null){
				if(fdServiceMember.getDoctorInfo().getDoctorName() != null && 
						!"".equals(fdServiceMember.getDoctorInfo().getDoctorName().trim())){
					params.put("doctorName", "%"+fdServiceMember.getDoctorInfo().getDoctorName().trim()+"%");
					hql.append(" AND c.doctorInfo.doctorName like :doctorName");
				}
			}
			if(StringUtils.isNotEmpty(fdServiceMember.getSkilNames())){
				params.put("skilNames", "%"+fdServiceMember.getSkilNames().trim()+"%");
				hql.append(" AND c.skilNames like :skilNames");
			}
		}
		if (fdServiceTeams != null) {
			if (fdServiceTeams.getId() != null) {
				params.put("teamId", fdServiceTeams.getId());
				hql.append(" AND c.fdServiceTeams.id = :teamId");
			}
			if (StringUtils.isNotEmpty(fdServiceTeams.getTeamName())) {
				params.put("teamName","%"+ fdServiceTeams.getTeamName()+"%");
				hql.append(" AND c.fdServiceTeams.teamName like :teamName");
			}
			if(fdServiceTeams.getFdServicePackage() != null){
				if(fdServiceTeams.getFdServicePackage().getHospitalBasicInfo() != null
						&& StringUtils.isNotEmpty(fdServiceTeams.getFdServicePackage().getHospitalBasicInfo().getHospitalLname())){
					params.put("hospitalLname","%"+ fdServiceTeams.getFdServicePackage().getHospitalBasicInfo().getHospitalLname()+"%");
					hql.append(" AND c.fdServiceTeams.fdServicePackage.hospitalBasicInfo.hospitalLname like :hospitalLname");
				}
				if(StringUtils.isNotEmpty(fdServiceTeams.getFdServicePackage().getPackageShowName())){
					params.put("packageShowName","%"+ fdServiceTeams.getFdServicePackage().getPackageShowName()+"%");
					hql.append(" AND c.fdServiceTeams.fdServicePackage.packageShowName like :packageShowName");
				}
			}
		}
		if (fdRoleInfo != null) {
			if (fdRoleInfo.getId() != null) {
				params.put("roleId", fdRoleInfo.getId());
				hql.append(" AND c.fdRoleInfo.id = :roleId");
			}
			if (StringUtils.isNotEmpty(fdRoleInfo.getRoleName())) {
				params.put("roleName", "%"+fdRoleInfo.getRoleName()+"%");
				hql.append(" AND c.fdRoleInfo.roleName like :roleName");
			}
		}
		hql.append(" order by c.fdServiceTeams.id");
		List<FdServiceMember> list = new ArrayList<>();
		if(psm == null){
			list = list(String.valueOf(hql), -1, -1, params);
		}else{
			list = (List<FdServiceMember>) listForEc(String.valueOf(hql), psm, params);
		}
		return list;
	}

	@Override
	public FdServiceMember getFdServiceMemberById(Long id) {
		
		return super.get(id, FdServiceMember.class);
	}

	@Override
	public void updateServiceSkil(FdServiceMember fdServiceMember) {
		if(fdServiceMember.getId() != null){
			FdServiceMember fdServiceMemberOld = getFdServiceMemberById(fdServiceMember.getId());
			fdServiceMemberOld.setSkilNames(fdServiceMember.getSkilNames());
			fdServiceMemberOld.setSkillIds(fdServiceMember.getSkillIds());
			super.edit(fdServiceMemberOld);
		}
	}

	@Override
	public List<FdMemberSkill> getFdMemberSkillByPage(PageSortModel psm,FdMemberSkill fdMemberSkill) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuffer hql = new StringBuffer("from FdMemberSkill f where 1=1");
		//条件查询
		if(fdMemberSkill != null){
			if(fdMemberSkill.getSkillName() != null && !"".equals(fdMemberSkill.getSkillName().trim())){
				params.put("skillName", "%" + fdMemberSkill.getSkillName().trim()+ "%");
				hql.append("AND f.skillName LIKE :skillName");
			}
		}
		List<FdMemberSkill> list = (List<FdMemberSkill>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}
}
