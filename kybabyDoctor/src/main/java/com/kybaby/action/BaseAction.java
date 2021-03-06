package com.kybaby.action;

import java.util.Map;

import com.kybaby.bo.AccountBo;
import com.kybaby.bo.AddressManageBo;
import com.kybaby.bo.ArticleBo;
import com.kybaby.bo.ConsultBo;
import com.kybaby.bo.DoctorIdentifyBo;
import com.kybaby.bo.DoctorInfoBo;
import com.kybaby.bo.HomePageManageBo;
import com.kybaby.bo.ModifyBo;
import com.kybaby.bo.OrderBo;
import com.kybaby.bo.RoleSelectBo;
import com.kybaby.bo.SetServiceTimeBo;
import com.kybaby.bo.VersionManageBo;
import com.wx.bo.WxBo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	private static final long serialVersionUID = 1222L;
	protected String action;
	protected String mes;
	protected Map session ;
	protected Map application;
//	protected UserBo userBo;
//	protected AdminBo adminBo;
	protected static WxBo wxBo;
	protected ConsultBo consultBo;
	protected OrderBo orderBo;
	protected ModifyBo modifyBo;
	protected AccountBo accountBo;
	protected DoctorInfoBo doctorInfoBo;
	protected HomePageManageBo homePageManageBo;
	protected DoctorIdentifyBo doctorIdentifyBo;
	protected AddressManageBo addressManageBo;
	protected SetServiceTimeBo setServiceTimeBo;
	protected ArticleBo articleBo;
	protected RoleSelectBo roleSelectBo;
	protected VersionManageBo versionManageBo;
	
	public Map getSession() {
        return ActionContext.getContext().getSession();
    }
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
	public void setApplication(Map<String, Object> arg0) {
		this.application = arg0;
	}
//	public UserBo getUserBo() {
//		return userBo;
//	}
//	public void setUserBo(UserBo userBo) {
//		this.userBo = userBo;
//	}
	
//	public AdminBo getAdminBo() {
//		return adminBo;
//	}
//	public void setAdminBo(AdminBo adminBo) {
//		this.adminBo = adminBo;
//	}
	public WxBo getWxBo() {
		return wxBo;
	}
	public void setWxBo(WxBo wxBo) {
		BaseAction.wxBo = wxBo;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public ConsultBo getConsultBo() {
		return consultBo;
	}
	public void setConsultBo(ConsultBo consultBo) {
		this.consultBo = consultBo;
	}
	public String getAction() {
		return action;
	}
	public String getMes() {
		return mes;
	}
	public OrderBo getOrderBo() {
		return orderBo;
	}
	public void setOrderBo(OrderBo orderBo) {
		this.orderBo = orderBo;
	}
	public AccountBo getAccountBo() {
		return accountBo;
	}
	public void setAccountBo(AccountBo accountBo) {
		this.accountBo = accountBo;
	}
	public DoctorInfoBo getDoctorInfoBo() {
		return doctorInfoBo;
	}
	public void setDoctorInfoBo(DoctorInfoBo doctorInfoBo) {
		this.doctorInfoBo = doctorInfoBo;
	}
	public ModifyBo getModifyBo() {
		return modifyBo;
	}
	public void setModifyBo(ModifyBo modifyBo) {
		this.modifyBo = modifyBo;
	}
	public HomePageManageBo getHomePageManageBo() {
		return homePageManageBo;
	}
	public void setHomePageManageBo(HomePageManageBo homePageManageBo) {
		this.homePageManageBo = homePageManageBo;
	}
	public DoctorIdentifyBo getDoctorIdentifyBo() {
		return doctorIdentifyBo;
	}
	public void setDoctorIdentifyBo(DoctorIdentifyBo doctorIdentifyBo) {
		this.doctorIdentifyBo = doctorIdentifyBo;
	}
	public AddressManageBo getAddressManageBo() {
		return addressManageBo;
	}
	public void setAddressManageBo(AddressManageBo addressManageBo) {
		this.addressManageBo = addressManageBo;
	}
	public SetServiceTimeBo getSetServiceTimeBo() {
		return setServiceTimeBo;
	}
	public void setSetServiceTimeBo(SetServiceTimeBo setServiceTimeBo) {
		this.setServiceTimeBo = setServiceTimeBo;
	}
	public ArticleBo getArticleBo() {
		return articleBo;
	}
	public void setArticleBo(ArticleBo articleBo) {
		this.articleBo = articleBo;
	}
	public RoleSelectBo getRoleSelectBo() {
		return roleSelectBo;
	}
	public void setRoleSelectBo(RoleSelectBo roleSelectBo) {
		this.roleSelectBo = roleSelectBo;
	}
	public VersionManageBo getVersionManageBo() {
		return versionManageBo;
	}
	public void setVersionManageBo(VersionManageBo versionManageBo) {
		this.versionManageBo = versionManageBo;
	}
	
}

