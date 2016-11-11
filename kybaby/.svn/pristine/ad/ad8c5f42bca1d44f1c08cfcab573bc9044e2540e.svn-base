package com.wx;

import java.util.ArrayList;
import java.util.List;

import com.wx.domain.WxToken;
import com.wx.persistence.beans.SysMenu;
import com.wx.persistence.beans.Token;
import com.wx.persistence.beans.WechatMenu;
import com.wx.service.SysMenuService;
import com.wx.service.TokenService;
import com.wx.util.CommonUtil;
import com.wx.util.Tools;
import com.wx.util.ehcache.EhcacheUtil;
import com.wx.BaseAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


public class MenuManage extends BaseAction {
	/**
	 * 生成微信菜单
	 */
	private static final long serialVersionUID = 5179862059368466962L;
	private String userName;
	private String userPassword;
	private String mes;
	private String action;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	private SysMenuService menuService;
	

	
	public String execute(){
		System.out.println("*************menu manage*****************");
		if(action.equals("createMenu")){
			
			String sToken=wxBo.findPropertyValueByKey("sToken");
			String corpSecret=wxBo.findPropertyValueByKey("corpSecret");
			String corpId=wxBo.findPropertyValueByKey("corpId");
			String createMenu_Url=wxBo.findPropertyValueByKey("createMenu_Url");
			
			WxToken tk = wxBo.getAccessToken(corpId);
			
			WxToken tk1 =  CommonUtil.getToken(corpId, corpSecret, tk);
			
			//Long ParentId = -1L;
			//List<WxMenu> menuList = wxBo.getMenuListByParentId(ParentId);
			String menuList="{\"button\":["+
					"{\"name\":\"果非果\",\"sub_button\":["+
                    "{\"type\":\"view\",\"name\":\"商城首页\",\"url\":\"http://www.guofeiguo.com/wx/index.html\"},"+
                     "{\"type\":\"view\",\"name\":\"商城活动\",\"url\":\"http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzAwNTIwNDM4Nw==#wechat_webview_type=1&wechat_redirect\"}]"+
                 "},"+
                 "{\"name\":\"个人中心\",\"sub_button\":["+
                 "{\"type\":\"view\",\"name\":\"我的购物车\",\"url\":\"http://www.guofeiguo.com/wx/index.html?mycart\"},"+
                  "{\"type\":\"view\",\"name\":\"我的订单\",\"url\":\"http://www.guofeiguo.com/wx/index.html?myorder\"}]"+
              "},"+
			                        "{\"name\":\"关于\",\"sub_button\":["+
				                    "{\"type\":\"view\",\"name\":\"关于商城\",\"url\":\"http://www.guofeiguo.com/wx/index.html?contact\"},"+
				                     "{\"type\":\"view\",\"name\":\"趣洗\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxef7c4035759df877&redirect_uri=http%3a%2f%2fenjoyxiyidev.yishangkeji.cn%2fwx%2fuserAuth.action&response_type=code&scope=snsapi_base&state=gc#wechat_redirect\"}]"+
				                 "}"+
				                     "]}";
			
			//Boolean result=CommonUtil.sendToServer(createMenu_Url,tk1.getAccessToken() , getMenuString(menuList));
			Boolean result=CommonUtil.sendToServer(createMenu_Url,tk1.getAccessToken(),menuList);
			System.out.println("test");
		}		
		return "fail";
		
	}
	
	   /**
	    * 构造出自定义菜单格式上发报文数据
	    * @param menuList
	    * @return
	    */
	/*
	   public String getMenuString(List<WxMenu> menuList){
		  
		   List<WechatMenu> weList = new ArrayList<WechatMenu>();
		   
		   for (WxMenu m : menuList) {//构造一级菜单
		   WechatMenu wm = new WechatMenu();
		   wm.setName(m.getMenuName());
		   wm.setKey(m.getMenuKey());
		   wm.setUrl(m.getMenuUrl());
		   wm.setType(m.getMenuIcon());
		   List<Object> subWeList =  new ArrayList<Object>();
		   for(Object mm :  m.getListTree()){//构造二级菜单
			   WxMenu sm = new SysMenu();
			   sm=(SysMenu)mm;
			   WechatMenu wmButton = new WechatMenu();
			   sm.setParentId(sm.getId());
			   sm = this.menuService.load(sm);
			   wmButton.setName(sm.getText());
			   wmButton.setKey(sm.getMenuKey());
			   wmButton.setUrl(sm.getMenuUrl());
			   wmButton.setType(sm.getIconCls());
			   subWeList.add(wmButton);
		   }
		   wm.setSub_button(subWeList);
		   weList.add(wm);
		   }
		  // System.out.println(Tools.toJson(weList));
		   return  "{\"button\":"+Tools.toJson(weList)+"}";
	   }*/

	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
