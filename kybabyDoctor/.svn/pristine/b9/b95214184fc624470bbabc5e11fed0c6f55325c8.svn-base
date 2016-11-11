package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.WxBo;
import com.kybaby.dao.WxDao;
import com.wx.domain.WxMenu;
import com.wx.domain.WxToken;

public class WxBoImpl implements WxBo {
	WxDao wxDao;
	@Override
	public void tokenUpdate (WxToken token){
		wxDao.tokenUpdate(token);
	}
	
	@Override
	public String findPropertyValueByKey(String proKey){
		return wxDao.findPropertyValueByKey(proKey);
	}
	
	@Override
	public WxToken getAccessToken(String appId){
		return wxDao.getAccessToken(appId);
	}
	
	@Override
	public List<WxMenu> getMenuListByParentId(Long parentId){
		return wxDao.getMenuListByParentId(parentId);
	}

	public WxDao getWxDao() {
		return wxDao;
	}

	public void setWxDao(WxDao wxDao) {
		this.wxDao = wxDao;
	}


}
