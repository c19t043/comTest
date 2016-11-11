package com.java.familydoctor.teamrole.vo;

import com.java.familydoctor.doctorrole.vo.FdRoleInfo;
import com.java.familydoctor.serviceteams.vo.FdServiceTeams;
import com.java.operationmanage.vo.DoctorInfo;


/**
 * 家庭医生服务团队成员信息
 * @author lihao
 *
 */

public class FdServiceMember implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private DoctorInfo doctorInfo;
	private FdServiceTeams fdServiceTeams;
	private FdRoleInfo fdRoleInfo;
	private String skillIds;
	private String skilNames;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	public String getSkillIds() {
		return skillIds;
	}
	public void setSkillIds(String skillIds) {
		this.skillIds = skillIds;
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
	public String getSkilNames() {
		return skilNames;
	}
	public void setSkilNames(String skilNames) {
		this.skilNames = skilNames;
	}
}