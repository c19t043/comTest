package com.java.familydoctor.teamrole.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.doctorrole.vo.FdRoleInfo;
import com.java.familydoctor.memberskill.vo.FdMemberSkill;
import com.java.familydoctor.serviceteams.vo.FdServiceTeamRole;
import com.java.familydoctor.serviceteams.vo.FdServiceTeams;
import com.java.familydoctor.teamrole.vo.FdServiceMember;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.user.service.Service;

public interface TeamRoleService extends Service{
	/**
	 * 分页列表
	 * @param psm
	 * @param FdServiceTeamRole
	 * @return
	 */
	List<FdServiceTeamRole> getFdServiceTeamRoleListByPage(PageSortModel psm,FdServiceTeamRole fdServiceTeamRole);
	/**
	 * 得到医生列表
	 * @param psm
	 * @param doctorInfo
	 * @return
	 */
	List<DoctorInfo> getDoctorInfoListByPage(PageSortModel psm,DoctorInfo doctorInfo);
	/**
	 * 保存或更新家庭医生成员
	 * @param FdServiceTeamRole
	 * @return
	 */
	void saveOrUpdateFdServiceMember(String doctorIds, FdServiceTeams fdServiceTeams, FdRoleInfo fdRoleInfo);
	/**
	 * 根据id得到
	 * @param id
	 * @return
	 */
	FdServiceTeamRole getFdServiceTeamRoleById(Long id);
	/**
	 * 得到团队成员列表
	 * @param fdServiceMember
	 * @param fdServiceTeams
	 * @param fdRoleInfo
	 * @return
	 */
	List<FdServiceMember> getFdServiceMemberByPage(PageSortModel psm,FdServiceMember fdServiceMember,
			FdServiceTeams fdServiceTeams, FdRoleInfo fdRoleInfo);
	
	/**
	 * 根据id查询一条家庭医生成员的列表信息
	 * @param id
	 * @return
	 */
	FdServiceMember getFdServiceMemberById(Long id);
	
	/**
	 * 修改服务技能方法
	 */
	void updateServiceSkil(FdServiceMember fdServiceMember);
	
	/**
	 * 查询服务技能列表方法
	 * @param psm
	 * @param fdMemberSkill
	 * @return
	 */
	List<FdMemberSkill> getFdMemberSkillByPage(PageSortModel psm,FdMemberSkill fdMemberSkill);
}
