package com.wx;

import com.wx.domain.WxToken;
import com.wx.service.SysMenuService;
import com.wx.util.CommonUtil;
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
	

	
	@Override
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
					"{\"name\":\"活动\",\"sub_button\":["+
                    "{\"type\":\"view\",\"name\":\"健康管理\",\"url\":\"http://kybabydev.yishangkeji.cn/main/testPage.html\"}"+
                     "{\"type\":\"view\",\"name\":\"家长沙龙\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx039ad537c0a70c1b&redirect_uri=http%3a%2f%2fweiwuyedev.yishangkeji.cn%2fwx%2fadminIndexUserAuth.action&response_type=code&scope=snsapi_base&state=gc#wechat_redirect\"},"+
                     "{\"type\":\"view\",\"name\":\"医生沙龙\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx039ad537c0a70c1b&redirect_uri=http%3a%2f%2fweiwuyedev.yishangkeji.cn%2fwx%2findexUserAuth.action&response_type=code&scope=snsapi_base&state=gc#wechat_redirect\"},"+
//                     "{\"type\":\"view\",\"name\":\"天天享吃UAT\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx039ad537c0a70c1b&redirect_uri=http%3a%2f%2f121.40.108.73%2fwx%2findexUserAuth.action&response_type=code&scope=snsapi_base&state=gc#wechat_redirect\"},"+
//                     "{\"type\":\"view\",\"name\":\"未用\",\"url\":\"http://tiantianxcdev.yishangkeji.cn/map/showMap.html\"}
					"]"+
                 "},"+
			                        "{\"name\":\"服务\",\"sub_button\":["+
				                    "{\"type\":\"view\",\"name\":\"我是医生\",\"url\":\"http://open.weixin.qq.com/connect/oauth2/authorize?appid=wx039ad537c0a70c1b&redirect_uri=http%3a%2f%2fkybabydev.yishangkeji.cn%2fwx%2findexUserAuth.action&response_type=code&scope=snsapi_base&state=gc#wechat_redirect\"},"+
				                     "{\"type\":\"view\",\"name\":\"我是家长\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx039ad537c0a70c1b&redirect_uri=http%3a%2f%2fkybabydev.yishangkeji.cn%2fkybaby%2fwx%2fuserAuth.action&response_type=code&scope=snsapi_base&state=gc#wechat_redirect\"}"+
//				                     "{\"type\":\"view\",\"name\":\"临时测试LHH\",\"url\":\"http://tiantianxcdev.yishangkeji.cn/wx/getUserOpenId.jsp\"},"+
//				                     "{\"type\":\"view\",\"name\":\"支付测试\",\"url\":\"http://tiantianxcdev.yishangkeji.cn/main/someOthers.jsp\"},"+
//				                     "{\"type\":\"view\",\"name\":\"地图\",\"url\":\"http://tiantianxcdev.yishangkeji.cn/map/showMap.html\}
									"]"+
				                 "}"+
									
									 "{\"name\":\"指南\",\"sub_button\":["+
									 "{\"type\":\"view\",\"name\":\"在线客服\",\"url\":\"http://open.weixin.qq.com/connect/oauth2/authorize?appid=wx039ad537c0a70c1b&redirect_uri=http%3a%2f%2fkybabydev.yishangkeji.cn%2fwx%2findexUserAuth.action&response_type=code&scope=snsapi_base&state=gc#wechat_redirect\"},"+
									  "{\"type\":\"view\",\"name\":\"APP下载\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx039ad537c0a70c1b&redirect_uri=http%3a%2f%2fkybabydev.yishangkeji.cn%2fkybaby%2fwx%2fuserAuth.action&response_type=code&scope=snsapi_base&state=gc#wechat_redirect\"}"+
									  "{\"type\":\"view\",\"name\":\"关于我们\",\"url\":\"http://tiantianxcdev.yishangkeji.cn/wx/getUserOpenId.jsp\"},"+
									//  "{\"type\":\"view\",\"name\":\"支付测试\",\"url\":\"http://tiantianxcdev.yishangkeji.cn/main/someOthers.jsp\"},"+
									//  "{\"type\":\"view\",\"name\":\"地图\",\"url\":\"http://tiantianxcdev.yishangkeji.cn/map/showMap.html\}
										"]"+
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
