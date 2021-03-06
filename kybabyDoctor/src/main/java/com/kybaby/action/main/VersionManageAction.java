package com.kybaby.action.main;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.VersionManage;

/**
 * 版本管理接口
 * @author xiongchao
 */
public class VersionManageAction extends BaseAction{

	private static final long serialVersionUID = 164164381222831303L;
	
	private VersionManage versionManage;
	
	private String mes;

	@Override
	public String execute() {
		/**
		 * 医生端更新
		 */
		if(action.equals("getNewVersionCode")) {
			versionManage = versionManageBo.getNewVersionCode(VersionManage.VERSION_TYPE_DOCTOR);
			mes="成功";
		}
		/**
		 * 机构端更新
		 */
		else if(action.equals("getNewVersionCodeByOrgan")) {
			versionManage = versionManageBo.getNewVersionCode(VersionManage.VERSION_TYPE_ORGAN);
			mes="成功";
		}
		return "success";
	}

	@Override
	public String getMes() {
		return mes;
	}
	@Override
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
