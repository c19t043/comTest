package com.java.familydoctor.memberskill.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.memberskill.vo.FdMemberSkill;
import com.java.platform.user.service.Service;

public interface FdMemberSkillService extends Service{
	/**
	 * 服务技能信息添加和修改方法
	 */
	Long saveOrUpdateFdMemberSkill(FdMemberSkill fdMemberSkill);
	
	/**
	 *服务技能信息通过id查询一条数据
	 */
	FdMemberSkill getFdMemberSkillById(Long id);
	
	/**
	 * 服务技能信息列表方法
	 */
	List<FdMemberSkill> getFdMemberSkillByPage(PageSortModel psm,FdMemberSkill fdMemberSkill);
}
