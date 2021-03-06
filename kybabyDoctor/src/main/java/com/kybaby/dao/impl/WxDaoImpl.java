package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.WxDao;
import com.wx.domain.WxMenu;
import com.wx.domain.WxToken;

public class WxDaoImpl extends HibernateDaoSupport implements WxDao {	
	
	@Override
	public void tokenUpdate (WxToken token){
		getHibernateTemplate().update(token);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public String findPropertyValueByKey(String proKey){
		List<String> list = getHibernateTemplate().find("select proValue from WxProperties where proKey=?",proKey);
		if(list.isEmpty()==true){
			return null;
		} else {
		return list.get(0);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public WxToken getAccessToken(String appId){
		List<WxToken> token=getHibernateTemplate().find("from WxToken where appId=?",appId);
		if(token.isEmpty()==true){
			return null;
		}else {
			return token.get(0);
		}
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<WxMenu> getMenuListByParentId(Long parentId){
		List<WxMenu> menuList=getHibernateTemplate().find("from WxMenu where parentId=?",parentId);
		if(menuList.isEmpty()==true){
			return null;
		}
		return menuList;
	}
	
}
