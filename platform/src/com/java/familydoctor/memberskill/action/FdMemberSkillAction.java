package com.java.familydoctor.memberskill.action;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.memberskill.service.FdMemberSkillService;
import com.java.familydoctor.memberskill.vo.FdMemberSkill;
import com.java.platform.core.Action;

public class FdMemberSkillAction extends Action{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 服务技能信息服务方法
	 */
	private FdMemberSkillService fdMemberSkillService;
	
	/**
	 * 服务技能信息实体类
	 */
	private FdMemberSkill fdMemberSkill;

	public FdMemberSkillService getFdMemberSkillService() {
		return fdMemberSkillService;
	}

	public void setFdMemberSkillService(FdMemberSkillService fdMemberSkillService) {
		this.fdMemberSkillService = fdMemberSkillService;
	}

	public FdMemberSkill getFdMemberSkill() {
		return fdMemberSkill;
	}

	public void setFdMemberSkill(FdMemberSkill fdMemberSkill) {
		this.fdMemberSkill = fdMemberSkill;
	}
	
	
	/**
	 *  跳转添加/编辑页面
	 */
	public String toJumpFdMemberSkill(){
		if(fdMemberSkill != null && fdMemberSkill.getId() != null){
			this.fdMemberSkill = fdMemberSkillService.getFdMemberSkillById(fdMemberSkill.getId());
		}
		return SUCCESS;
	}
	
	
	/**
	 * 服务技能信息的添加方法和修改
	 */
	public String saveOrUpdateFdMemberSkill(){
		this.fdMemberSkillService.saveOrUpdateFdMemberSkill(fdMemberSkill);
		return redirectActionResult("getFdMemberSkillList");
	}
	
	/**
	 * 服务包服务时间信息列表页面
	 */
	public String getFdMemberSkillList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(fdMemberSkill == null){
			fdMemberSkill = new FdMemberSkill();
		}
		List<FdMemberSkill> list = this.fdMemberSkillService.getFdMemberSkillByPage(psm, fdMemberSkill);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	
}
