package com.java.familydoctor.serviceteams.vo;

import com.java.familydoctor.doctorrole.vo.FdRoleInfo;

/**
 * 团队角色关系信息
 * @author lihao
 *
 */

public class FdServiceTeamRole implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private FdServiceTeams fdServiceTeams;
	private FdRoleInfo fdRoleInfo;
	//表单使用
	private String memberIds;
	private String memberNames;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getMemberIds() {
		return memberIds;
	}
	public void setMemberIds(String memberIds) {
		this.memberIds = memberIds;
	}
	public String getMemberNames() {
		return memberNames;
	}
	public void setMemberNames(String memberNames) {
		this.memberNames = memberNames;
	}


}