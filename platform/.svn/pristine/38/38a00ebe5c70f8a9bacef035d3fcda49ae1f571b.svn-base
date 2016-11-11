package com.java.familydoctor.teamrole.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.doctorrole.vo.FdRoleInfo;
import com.java.familydoctor.memberskill.vo.FdMemberSkill;
import com.java.familydoctor.serviceteams.vo.FdServiceTeamRole;
import com.java.familydoctor.serviceteams.vo.FdServiceTeams;
import com.java.familydoctor.teamrole.service.TeamRoleService;
import com.java.familydoctor.teamrole.vo.FdServiceMember;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.core.Action;

public class TeamRoleAction extends Action{
	private static final long serialVersionUID = 1L;
	private FdServiceTeamRole fdServiceTeamRole;
	private TeamRoleService teamRoleService;
	private FdServiceMember fdServiceMember;
	private FdServiceTeams fdServiceTeams;
	private FdRoleInfo fdRoleInfo;
	private FdMemberSkill fdMemberSkill;

	private DoctorInfo doctorInfo;
	public FdServiceTeamRole getFdServiceTeamRole() {
		return fdServiceTeamRole;
	}
	public void setFdServiceTeamRole(FdServiceTeamRole fdServiceTeamRole) {
		this.fdServiceTeamRole = fdServiceTeamRole;
	}
	public TeamRoleService getTeamRoleService() {
		return teamRoleService;
	}
	public void setTeamRoleService(TeamRoleService teamRoleService) {
		this.teamRoleService = teamRoleService;
	}
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	public FdServiceMember getFdServiceMember() {
		return fdServiceMember;
	}
	public void setFdServiceMember(FdServiceMember fdServiceMember) {
		this.fdServiceMember = fdServiceMember;
	}
	public FdServiceTeams getFdServiceTeams() {
		return fdServiceTeams;
	}
	public void setFdServiceTeams(FdServiceTeams fdServiceTeams) {
		this.fdServiceTeams = fdServiceTeams;
	}
	public FdRoleInfo getFdRoleInfo() {
		return fdRoleInfo;
	}
	public void setFdRoleInfo(FdRoleInfo fdRoleInfo) {
		this.fdRoleInfo = fdRoleInfo;
	}
	public FdMemberSkill getFdMemberSkill() {
		return fdMemberSkill;
	}
	public void setFdMemberSkill(FdMemberSkill fdMemberSkill) {
		this.fdMemberSkill = fdMemberSkill;
	}
	/**
	 * 跳转页面
	 * @return
	 */
	public String toJumpFdServiceTeamRole(){
		if(fdServiceTeamRole != null && fdServiceTeamRole.getId() != null){
			this.fdServiceTeamRole = this.teamRoleService.getFdServiceTeamRoleById(fdServiceTeamRole.getId());
		}
		return SUCCESS;
	}
	
	/**
	 * 得到团队角色关系列表
	 * @return
	 */
	public String getFdServiceTeamRoleList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		if(fdServiceTeamRole == null){
			fdServiceTeamRole = new FdServiceTeamRole();
		}
		List<FdServiceTeamRole> list = 
			this.teamRoleService.getFdServiceTeamRoleListByPage(psm, fdServiceTeamRole);
		if(!list.isEmpty()){
			for(FdServiceTeamRole teamRole : list){
				String memberIds ="";
				String memberNames="";
				List<FdServiceMember> memList = this.teamRoleService.
						getFdServiceMemberByPage(null, null, 
								teamRole.getFdServiceTeams(), teamRole.getFdRoleInfo());
				if(!memList.isEmpty()){
					for(FdServiceMember mem : memList){
						memberIds += mem.getDoctorInfo().getId() + ",";
						memberNames += mem.getDoctorInfo().getDoctorName() + ",";
					}
				}
				teamRole.setMemberIds(memberIds);
				teamRole.setMemberNames(memberNames);
			}
		}
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 得到医生信息列表
	 * @return
	 */
	public String getDoctorInfoList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		if(doctorInfo == null){
			doctorInfo = new DoctorInfo();
		}
		List<DoctorInfo> list = 
			this.teamRoleService.getDoctorInfoListByPage(psm, doctorInfo);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 得到添加了的成员信息列表
	 * @return
	 */
	public String getFdServiceMemberList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		List<FdServiceMember> list = 
				this.teamRoleService.getFdServiceMemberByPage(psm, fdServiceMember, fdServiceTeams, fdRoleInfo);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 添加团队角色成员
	 * @return
	 */
	public String addFdServiceMemberList(){
		if(fdServiceTeamRole.getMemberIds() != null && !"".equals(fdServiceTeamRole.getMemberIds().trim())){
			this.teamRoleService.saveOrUpdateFdServiceMember(fdServiceTeamRole.getMemberIds(), fdServiceTeams, fdRoleInfo);
		}
		addActionMessage("操作成功");
		return redirectActionResult("teamRoleList");
	}
	
	/**
	 * 跳转页面
	 * @return
	 */
	public String toJumpFdServiceMember(){
		if(fdServiceMember != null && fdServiceMember.getId() != null){
			this.fdServiceMember = this.teamRoleService.getFdServiceMemberById(fdServiceMember.getId());
		}
		return SUCCESS;
	}
	
	/**
	 * 更新技能方法
	 * @return
	 */
	public String updateServiceskill(){
		this.teamRoleService.updateServiceSkil(fdServiceMember);
		addActionMessage("操作成功");
		return redirectActionResult("getFdServiceMemberList");
	}
	
	public String getFdMemberSkillList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		
		if(fdMemberSkill == null){
			fdMemberSkill = new FdMemberSkill();
		}
		List<FdMemberSkill> list = 
			this.teamRoleService.getFdMemberSkillByPage(psm, fdMemberSkill);
		this.putToRequest("list", list);
		return SUCCESS;
	}
}
