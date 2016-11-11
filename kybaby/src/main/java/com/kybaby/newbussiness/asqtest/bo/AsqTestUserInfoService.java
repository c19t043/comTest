package com.kybaby.newbussiness.asqtest.bo;

import com.kybaby.newbussiness.asqtest.domain.AsqTestUserInfo;

public interface AsqTestUserInfoService {
	/**
	 * 保存或更新测试用户信息
	 * @param asqTestUserInfo
	 * @return
	 */
	Long saveOrUpdateAsqTestUserInfo(AsqTestUserInfo asqTestUserInfo);
	/**
	 * 得到测试用户信息
	 * @param asqUserId
	 * @return
	 */
	AsqTestUserInfo getAsqTestUserInfoById(Long asqUserId);
}
