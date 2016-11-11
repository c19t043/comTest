package com.kybaby.newbussiness.yjh.action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.yjh.urlutil.HttpURLConnectionTest;
import com.kybaby.util.CookieManage;
import com.opensymphony.xwork2.ActionContext;
/**
 * 金控对接相关处理
 * @author lihao
 *
 */
public class YjhManageAction extends NewBaseAction{
	private static final long serialVersionUID = 1L;
//	private String YJHurl = "http://118.123.173.101:7001/api";
	private String YJHurl = "http://phr.care4u.cn/api";
	/**
	 * 金控授权码
	 */
	private String userToken="";
	private String extraList="";
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	private UserInfo userInfo;
	private String userPhone;
	private String password;
	private String babyName;
	private String babySex;
	private String babyBirthDay;
	private String userLng;
	private String userLat;
	private String userProvince;
	private String userCity;
	private String userArea;
	private String userStreet;
	private String detailAddress;
	private String comments;
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String execute(){
		/**
		 * 金控用户同步，有自动登录，没有添加信息然后
		 */
		if("yjhUserLogin".equals(action)){
			System.out.println("yjhUser==="+userPhone);
			UserInfo usr = this.userInfoBo.getUserInfoByPhone(userPhone);
			if(usr == null){
				System.out.println("yjhUser===insert");
				//将金控用户初始化到系统
				userInfoBo.userRegist(null, password, babyBirthDay, babySex, userPhone, userLng, userLat, userProvince, userCity, userArea, userStreet, detailAddress, babyName);
				usr = this.userInfoBo.getUserInfoByPhone(userPhone);
				usr.setComments(comments);
				usr.setRecommendNum(userInfo.getRecommendNum());//用来放金控的userId
				userInfoBo.updateUser(usr);
				ActionContext.getContext().getSession().put("userId",usr.getId());
				CookieManage.pwdAndPhoneCookie(password, userPhone);
			}else{
				usr.setRecommendNum(userInfo.getRecommendNum());//用来放金控的userId
				usr.setComments(comments);//放金控的user token
				userInfoBo.updateUser(usr);
				System.out.println("yjhUser===putsession");
				ActionContext.getContext().getSession().put("userId",usr.getId());
				CookieManage.pwdAndPhoneCookieMd5(usr.getPassword(), usr.getPhone());
			}
			this.userInfo = usr;
			System.out.println("yjhUser fangjinqu jiu qu ==="+ActionContext.getContext().getSession().get("userId"));
		}
		/**
		 * 获取应用授权码
		 */
		else if(action.equals("getAppToken")){
			try {
				String appToken = HttpURLConnectionTest.httpURLConectionGET(YJHurl+"/auth.do?appkey=KYBB");
				if(appToken != null && !"".equals(appToken)){
				    JSONObject myjObject = new JSONObject(appToken);
				    String token = myjObject.getString("token");
				    System.out.println(token);
				    this.userToken = token;
				    extraList = HttpURLConnectionTest.httpURLConectionGET(YJHurl+"/extra/list.do?token="+token+"&userToken="+comments);
				    extraList = URLEncoder.encode(extraList, "UTF-8");  
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return SUCCESS;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}
	public void setBabySex(String babySex) {
		this.babySex = babySex;
	}
	public void setBabyBirthDay(String babyBirthDay) {
		this.babyBirthDay = babyBirthDay;
	}
	public void setUserLng(String userLng) {
		this.userLng = userLng;
	}
	public void setUserLat(String userLat) {
		this.userLat = userLat;
	}
	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}
	public void setUserStreet(String userStreet) {
		this.userStreet = userStreet;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getUserToken() {
		return userToken;
	}
	public String getExtraList() {
		return extraList;
	}
}
