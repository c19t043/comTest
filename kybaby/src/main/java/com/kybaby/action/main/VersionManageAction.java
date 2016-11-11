package com.kybaby.action.main;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.VersionManage;

/**
 * 版本管理接口
 * @author xiongchao
 */
public class VersionManageAction extends BaseAction{

	private static final long serialVersionUID = 764164381211831392L;
	
	private VersionManage versionManage;
	
	private String mes;

	public String execute() {
		if(action.equals("getNewVersionCode")) {
			versionManage = versionManageBo.getNewVersionCode();
			mes="成功";
		}
		return "success";
	}

	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}

	public VersionManage getVersionManage() {
		return versionManage;
	}

	public void setVersionManage(VersionManage versionManage) {
		this.versionManage = versionManage;
	}
	
}
