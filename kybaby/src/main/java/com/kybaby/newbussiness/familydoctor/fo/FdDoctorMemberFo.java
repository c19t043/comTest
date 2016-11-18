package com.kybaby.newbussiness.familydoctor.fo;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.familydoctor.domain.FdRoleInfo;

public class FdDoctorMemberFo {
	private DoctorInfo doctorInfo;
	private List<FdRoleInfo> roleList ;
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	public List<FdRoleInfo> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<FdRoleInfo> roleList) {
		this.roleList = roleList;
	}
}
