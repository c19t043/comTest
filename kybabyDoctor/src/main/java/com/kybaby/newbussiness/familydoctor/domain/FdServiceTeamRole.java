package com.kybaby.newbussiness.familydoctor.domain;

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


}