package com.java.userinfo.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.publichealth.residentsfile.vo.KyUserInfo;

public interface KyUserInfoService {
	/**
	 * 分页列表
	 * @param psm
	 * @param doctorInfo
	 * @return
	 */
	List<KyUserInfo> getKyUserInfoListByPage(PageSortModel psm,KyUserInfo kyUserInfo);
}
