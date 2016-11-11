package com.java.familydoctor.serviceteams.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.serviceteams.vo.FdServiceTeamRole;
import com.java.familydoctor.serviceteams.vo.FdServiceTeams;
import com.java.platform.user.service.Service;
/**
 * 家庭医生服务团队信息服务方法
 *
 */
public interface FdServiceTeamsService extends Service{
	
	
	/**
	 * 家庭医生服务团队信息添加和修改方法
	 */
	Long saveOrUpdateFdServiceTeams(FdServiceTeams fdServiceTeams);
	
	/**
	 * 家庭医生服务团队信息通过id查询一条数据
	 */
	FdServiceTeams getFdServiceTeamsById(Long id);
	
	/**
	 * 家庭医生服务团队信息列表方法
	 */
	List<FdServiceTeams> getFdServiceTeamsByPage(PageSortModel psm,FdServiceTeams fdServiceTeams);
	
	/**
	 * 查询当前团队角色关系表中是否存在
	 */
	List<FdServiceTeamRole> getFdServiceTeamRoleList(Long fdServiceTeamsId,Long doctorRoleId);

	/**
	 * 删除所以关联
	 */
	void deleteFdServiceTeamRole(Long fdServiceTeamsId);
	
}
