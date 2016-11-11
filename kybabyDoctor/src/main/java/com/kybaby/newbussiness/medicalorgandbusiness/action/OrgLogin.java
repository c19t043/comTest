package com.kybaby.newbussiness.medicalorgandbusiness.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganModuleInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganOperator;
import com.opensymphony.xwork2.ActionContext;

public class OrgLogin extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 登录人信息
	 */
	private OrganOperator organOperator;
	/**
	 * 机构列表
	 */
	private List<HospitalBasicInfo> hospitalBasicInfoList = new ArrayList<HospitalBasicInfo>();
	/**
	 * 功能菜单列表
	 */
	private List<OrganModuleInfo> organModuleInfoList = new ArrayList<OrganModuleInfo>();
	
	@Override
	public String execute() {
		System.out.println("1111111111111");
		/**
		 * 登录
		 */
		if(action.equals("orgLogin")){
			String pwd = organOperator.getPassword();
			this.organOperator = this.orgBusinessManageService.getOrganOperator(organOperator);
			if(organOperator == null){
				this.mes = "登录信息不正确！";
				return "fail";
			}else{
				ActionContext.getContext().getSession().put("organOperator", organOperator);
				HttpServletResponse response = ServletActionContext.getResponse();

				//创建Cookie
				Cookie pwdCookie = new Cookie("userpwd",pwd);
				Cookie loginNameCookie = new Cookie("loginName",organOperator.getLoginName());

				//设置Cookie的生命周期
				pwdCookie.setMaxAge(60*60*24*14);
				loginNameCookie.setMaxAge(60*60*24*14);

				pwdCookie.setPath("/");
				loginNameCookie.setPath("/");

				System.out.println("wd:"+pwdCookie.getValue());
				System.out.println("loginNameCookie:"+loginNameCookie.getValue());

				response.addCookie(pwdCookie);
				response.addCookie(loginNameCookie);
				
				if(organOperator.getHospitalBasicInfo()!=null){
					Cookie organIdCookie = new Cookie("organId",organOperator.getHospitalBasicInfo().getId().toString());
					organIdCookie.setMaxAge(60*60*24*14);
					organIdCookie.setPath("/");
					System.out.println("organId:"+organIdCookie.getValue());
					response.addCookie(organIdCookie);
				}
			}
		}
		/**
		 * 自动登录
		 */
		else if(action.equals("orgAutoLogin")){
			System.out.println("机构端自动登录。。。。。。。。。。。");
			if(ActionContext.getContext().getSession().get("organOperator")!=null){
				organOperator = (OrganOperator) ActionContext.getContext().getSession().get("organOperator");
				return "success";
			}else{
				HttpServletRequest request = ServletActionContext.getRequest();
				javax.servlet.http.Cookie[] cookies = request.getCookies();
				String Original_CookiesPwd = null;//cookie中的密码
				String Original_CookiesLoginName=null;//cookie中的电话号码
				String organId=null;//cookie中的电话号码
				javax.servlet.http.Cookie c = null;
				if(cookies!=null){//是否存在cookie
					for(int i =0;i<cookies.length;i++){
						c=cookies[i];
						if(c!=null){
							if (c.getName().equalsIgnoreCase("userpwd")&& c.getValue() != null) {
								Original_CookiesPwd = c.getValue();
							}
							if(c.getName().equalsIgnoreCase("loginName")&&c.getValue()!=null){
								Original_CookiesLoginName=c.getValue();
							}
							if(c.getName().equalsIgnoreCase("organId")&&c.getValue()!=null){
								organId=c.getValue();
							}
						}
						c=null;
					}
				}
				
				System.out.println("Original_CookiesPwd："+Original_CookiesPwd);
				System.out.println("Original_CookiesLoginName："+Original_CookiesLoginName);
				System.out.println("organId："+organId);
				if(Original_CookiesPwd!=null && Original_CookiesLoginName!=null && organId != null){
					OrganOperator oo = new OrganOperator();
					HospitalBasicInfo hb = new HospitalBasicInfo();
					hb.setId(Long.valueOf(organId));
					oo.setHospitalBasicInfo(hb);
					oo.setLoginName(Original_CookiesLoginName);
					oo.setPassword(Original_CookiesPwd);
					this.organOperator=orgBusinessManageService.getOrganOperator(oo);
					if(organOperator != null){
						organOperator = (OrganOperator) ActionContext.getContext().getSession().get("organOperator");
						return "success";
					}
				}
				mes="未登录";
				return "success";
			}
		}
		/**
		 * 退出系统
		 */
		else if(action.equals("orgOutLogin")){
			removeCookie();
			ActionContext.getContext().getSession().remove("organOperator");
			return "success";
		}
		/**
		 * 得到菜单（功能模块）列表
		 */
		if(action.equals("getModuleList")){
			organOperator = (OrganOperator)ActionContext.getContext().getSession().get("organOperator");
			if(organOperator==null){
				mes="请登录";
				return "fail";
			}
			//得到登录人的角色页面
			this.organModuleInfoList = this.orgBusinessManageService.getOrganRoleModuleList(organOperator);
		}
		/**
		 * 得到开展了业务的机构列表
		 */
		else if(action.equals("getHospitalBasicInfoList")){
			this.hospitalBasicInfoList = this.orgBusinessManageService.getHospitalBasicInfoList(null);
		}
		return "success";
	}
	private static void removeCookie(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		javax.servlet.http.Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			javax.servlet.http.Cookie cs = null;
			for (int i = 0; i < cookies.length; i++) {
				cs = cookies[i];
				if (cs != null) {
					if (cs.getName().equals(
							"userpwd")) {
						cs=new javax.servlet.http.Cookie(cs.getName(),null);
						cs.setMaxAge(0);
						cs.setPath("/");
						response.addCookie(cs);
					}
					if (cs.getName().equals(
							"loginName")) {
						cs=new javax.servlet.http.Cookie(cs.getName(),null);
						cs.setMaxAge(0);
						cs.setPath("/");
						response.addCookie(cs);
					}
					if (cs.getName().equals(
							"organId")) {
						cs=new javax.servlet.http.Cookie(cs.getName(),null);
						cs.setMaxAge(0);
						cs.setPath("/");
						response.addCookie(cs);
					}
				}
				cs = null;
			}
		}

	}
	@Override
	public String getMes() {
		return mes;
	}
	@Override
	public void setMes(String mes) {
		this.mes = mes;
	}
	public OrganOperator getOrganOperator() {
		return organOperator;
	}
	public void setOrganOperator(OrganOperator organOperator) {
		this.organOperator = organOperator;
	}
	public List<HospitalBasicInfo> getHospitalBasicInfoList() {
		return hospitalBasicInfoList;
	}
	public void setHospitalBasicInfoList(
			List<HospitalBasicInfo> hospitalBasicInfoList) {
		this.hospitalBasicInfoList = hospitalBasicInfoList;
	}
	public List<OrganModuleInfo> getOrganModuleInfoList() {
		return organModuleInfoList;
	}
	public void setOrganModuleInfoList(List<OrganModuleInfo> organModuleInfoList) {
		this.organModuleInfoList = organModuleInfoList;
	}
}
