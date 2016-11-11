package com.wx.bo.impl;

import java.util.List;

import com.wx.bo.WxBo;
import com.wx.dao.WxDao;
import com.wx.domain.WxMenu;
import com.wx.domain.WxToken;

public class WxBoImpl implements WxBo {
	WxDao wxDao;
	public void tokenUpdate (WxToken token){
		wxDao.tokenUpdate(token);
	}
	
	public String findPropertyValueByKey(String proKey){
		return wxDao.findPropertyValueByKey(proKey);
	}
	
	public WxToken getAccessToken(String appId){
		return wxDao.getAccessToken(appId);
	}
	
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
