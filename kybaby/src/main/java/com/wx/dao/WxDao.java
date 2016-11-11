package com.wx.dao;

import java.util.List;

import com.wx.domain.WxMenu;
import com.wx.domain.WxToken;


public interface WxDao {
	void tokenUpdate (WxToken token);
	
	String findPropertyValueByKey(String proKey);
	
	WxToken getAccessToken(String appId);
	
	List<WxMenu> getMenuListByParentId(Long parentId);
}
