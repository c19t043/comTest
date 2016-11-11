package com.java.userinfo.action;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.vo.OrderInfoClinic;
import com.java.platform.core.Action;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.userinfo.service.KyUserInfoService;

public class KyUserInfoAction extends Action{
	private static final long serialVersionUID = 1L;
	private KyUserInfoService kyUserInfoService;
	private KyUserInfo kyUserInfo;
	public KyUserInfoService getKyUserInfoService() {
		return kyUserInfoService;
	}
	public void setKyUserInfoService(KyUserInfoService kyUserInfoService) {
		this.kyUserInfoService = kyUserInfoService;
	}
	public KyUserInfo getKyUserInfo() {
		return kyUserInfo;
	}
	public void setKyUserInfo(KyUserInfo kyUserInfo) {
		this.kyUserInfo = kyUserInfo;
	}
	/**
	 * 注册用户信息列表
	 * @return
	 */
	public String getKyUserInfoList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(kyUserInfo == null){
			kyUserInfo = new KyUserInfo();
		}
		List<KyUserInfo> list = kyUserInfoService.getKyUserInfoListByPage(psm, kyUserInfo);
		this.putToRequest("list", list);
		return SUCCESS;
	}
}
