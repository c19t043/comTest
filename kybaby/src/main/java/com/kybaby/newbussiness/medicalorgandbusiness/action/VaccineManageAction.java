package com.kybaby.newbussiness.medicalorgandbusiness.action;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.AppointmentInitInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.HospitalBanner;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResourcesDetail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.VaccineInfo;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.SendSms;
import com.opensymphony.xwork2.ActionContext;

/**
 * 疫苗管理Action
 * @author xiongchao
 */
public class VaccineManageAction extends NewBaseAction {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 机构接种疫苗开放资源明细列表
	 */
	private List<OrganInoculationOpenResourcesDetail> organInoculationOpenResourcesDetailList = new ArrayList<OrganInoculationOpenResourcesDetail>();
	/**
	 * 机构接种疫苗开放资源列表
	 */
	private List<OrganInoculationOpenResources> organInoculationOpenResourcesList = new ArrayList<OrganInoculationOpenResources>();
	/**
	 * 机构接种疫苗开放资源明细对象
	 */
	private OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail;
	/**
	 * 机构接种疫苗开放资源对象
	 */
	private OrganInoculationOpenResources organInoculationOpenResources;
	/**
	 * 当前用户的身份
	 */
	private String currentUserIdentity = "";
	/**
	 * 用户接种疫苗预约信息
	 */
	private UserInoculationAppointmentInfo userInoculationAppointmentInfo;
	/**
	 * 用户接种疫苗预约信息列表
	 */
	private List<UserInoculationAppointmentInfo> userInoculationAppointmentInfoList = new ArrayList<UserInoculationAppointmentInfo>();
	/**
	 * 预约疫苗初始化信息
	 */
	private AppointmentInitInfo appointmentInitInfo;
	/**
	 * 建档信息
	 */
	private ArchivesInfo archivesInfo;
	/**
	 * 疫苗信息
	 */
	private VaccineInfo vaccineInfo;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 儿保是否预约过
	 */
	private String childCared = "";
	/**
	 * 计免是否预约过
	 */
	private String  inoculationed = "";
	/**
	 * 儿保预约信息
	 */
	private UserChildcareAppointmentInfo userChildcareAppointmentInfo;
	/**
	 * 用户信息
	 */
	private UserInfo userInfo;
	/**
	 * 家庭医生服务包
	 */
	private FdServicePackage fdServicePackage;
	
	public String execute() {
		Long userId = (Long) ActionContext.getContext().getSession().get("userId");
		if(userId!=null){
			this.userInfo = this.userInfoBo.getUserById(userId);
		}
		if(userId==null) {
			mes="请登录";
			return "fail";
		}
		/**
		 * 获取当前用户的身份
		 */
		else if("getCurrentUserIdentity".equals(action)) {
			archivesInfo = this.vaccineManageService.getCurrentUserIdentity(userId, null);
			if(archivesInfo == null){
				return "fail";
			}else if(archivesInfo.getHospitalBasicInfo() == null){
				return "fail";
			}
			if (archivesInfo != null) {
				currentUserIdentity = archivesInfo.getUserType();
			}
			UserInfo user = this.userInfoBo.getUserById(userId);
			//首先判断当前用户是否已经预约过，约过的不能再去约
			List<UserChildcareAppointmentInfo> childCareappointmentList = 
					this.openBusinessManagerService.getUserChildcareAppointmentInfoList(user, null,null);
			Boolean flag = false;
			if(childCareappointmentList != null){
				for(UserChildcareAppointmentInfo ua : childCareappointmentList){
					if("N".equals(ua.getOrganChildcareOpenResources().getIsMoney()) && 
							ConstantManage.HASE_BOOKED_CLINIC_ORDER.equals(ua.getStatus())){
						this.userChildcareAppointmentInfo = ua;
						flag = true;
						break;
					}
				}
			}
			if(flag){
				this.childCared = "儿保已约过";
			}
			this.appointmentInitInfo = this.vaccineManageService.getAppointmentInitInfoByUserId(userId,fdServicePackage);
			if(AppointmentInitInfo.NOT_FINISH_APPOINTMENT.equals(appointmentInitInfo.getFlagStatus())){
				this.inoculationed = "计免约过";
			}
		}
		/**
		 * 当前机构当前时间的疫苗接种开放时间段及每个时间段的剩余号源
		 */
		else if("openTimeDetail".equals(action)) {
			organInoculationOpenResourcesDetailList = this.vaccineManageService.getOrganInoculationOpenResourcesDetailList(organInoculationOpenResourcesDetail);
		}
		/**
		 * 当前机构的疫苗接种开放时间及每天的剩余号源
		 */
		else if("openTime".equals(action)) {
			organInoculationOpenResourcesList = this.vaccineManageService.getOrganInoculationOpenResourcesList(organInoculationOpenResources);
		}
		/**
		 * 保存用户的疫苗预约信息
		 */
		else if("saveAppointment".equals(action)) {
			Long id = userInoculationAppointmentInfo.getOrganInoculationOpenResourcesDetail().getId();
			OrganInoculationOpenResourcesDetail oldDetail = this.vaccineManageService.getOrganInoculationOpenResourcesDetailById(id);
			//先得到用户身份
			ArchivesInfo archivesInfo = new ArchivesInfo();
			if(this.fdServicePackage == null){
				archivesInfo = this.vaccineManageService.getCurrentUserIdentity(userId,null);
			}else{
				archivesInfo.setUserType(ArchivesInfo.USER_TYPE_RETAIL);
			}
					
			//判断是否有可用号源
			if(ArchivesInfo.USER_TYPE_GOLDEN_CARD.equals(archivesInfo.getUserType()) || 
					ArchivesInfo.USER_TYPE_PUKA.equals(archivesInfo.getUserType())){//金卡,普卡看绿色资源
				if("0".equals(oldDetail.getGreenChannelSurplusNum())){
					this.mes = "没有号源可约";
					return "fail";
				}
			}
//			else if(ArchivesInfo.USER_TYPE_RETAIL.equals(archivesInfo.getUserType())){//散户，看普通资源
			else{
				if("0".equals(oldDetail.getGeneralSurplusNum())){
					this.mes = "没有号源可约";
					return "fail";
				}
			}
			Long oldId = this.vaccineManageService.saveUserInoculationAppointmentInfo(userId, userInoculationAppointmentInfo,fdServicePackage);
			UserInoculationAppointmentInfo oldInfo = this.vaccineManageService.getAppointmentById(oldId);
			//发送短信通知
			UserInfo user = this.userInfoBo.getUserById(userId);
			SendSms ss = new SendSms();
			String contecnt = "亲爱的用户，您已预约"+oldInfo.getHospitalBasicInfo().getHospitalLname()+"的计免服务，请于"+
					oldInfo.getOrganInoculationOpenResources().getOpenDate() + " "
				 + oldInfo.getOrganInoculationOpenResourcesDetail().getOpenStartTime() + "到" +
					oldInfo.getOrganInoculationOpenResourcesDetail().getOpenEndTime() + "之间"
					+ "前往，地址：" + oldInfo.getHospitalBasicInfo().getAddress()+"。详情请查‘我的预约’";	
			ss.sendInfo(user.getPhone(), contecnt.toString());
		}
		/**
		 * 得到当前用户最近已预约的疫苗信息
		 */
		else if("getHaveAppointment".equals(action)) {
			userInoculationAppointmentInfo = this.vaccineManageService.getFirstAppointmentByUser(userId, ConstantManage.HASE_BOOKED_VACCINE);
		}
		/**
		 * 得到当前用户最新的接种疫苗数据
		 */
		else if("getFirstAppointment".equals(action)) {
			userInoculationAppointmentInfo = this.vaccineManageService.getFirstAppointmentByUser(userId, null);
		}
		/**
		 * 根据ID得到用户预约的接种疫苗信息
		 */
		else if("getAppointmentById".equals(action)) {
			userInoculationAppointmentInfo = this.vaccineManageService.getAppointmentById(userInoculationAppointmentInfo.getId());
		}
		/**
		 * 获取用户初始化接种信息
		 */
		else if("getUserInitInoculationInfo".equals(action)) {
			appointmentInitInfo = this.vaccineManageService.getAppointmentInitInfoByUserId(userId,fdServicePackage);
		}
		/**
		 * 取消疫苗接种预约
		 */
		else if("cancelAppointment".equals(action)) {
			this.vaccineManageService.cancelUserInoculationAppointmentInfo(userInoculationAppointmentInfo.getId());
		}
		/**
		 * 根据ID查询疫苗信息
		 */
		else if("getVaccineInfoById".equals(action)) {
			vaccineInfo = this.vaccineManageService.getVaccineInfoById(vaccineInfo.getId());
		}
		/**
		 * 保存关联的建档信息
		 */
		else if("saveRelationArchivesInfo".equals(action)) {
			//先判断是否是散户，如果是散户直接初始化进建档信息（基础数据里现在没有散户信息）
			UserInfo user = this.userInfoBo.getUserById(userId);
			ArchivesInfo ai = vaccineManageService.getCurrentUserIdentity(null, archivesInfo.getArchivesMobile().trim());
			if(ai == null){//散户
				//给散户添加档案信息
				archivesInfo.setChildrenName(user.getBabyName());
				archivesInfo.setIsRelation("Y");
				archivesInfo.setUserType(ArchivesInfo.USER_TYPE_RETAIL);
				archivesInfo.setUserInfo(user);
				this.vaccineManageService.saveOrUpdateArchivesInfo(archivesInfo);
			}else if(ArchivesInfo.USER_TYPE_RETAIL.equals(ai.getUserType())){//散户
				//给散户添加档案信息
				ai.setChildrenName(user.getBabyName());
				ai.setIsRelation("Y");
				ai.setUserType(ArchivesInfo.USER_TYPE_RETAIL);
				ai.setUserInfo(user);
				ai.setHospitalBasicInfo(archivesInfo.getHospitalBasicInfo());
				ai.setChildrenBirthday(archivesInfo.getChildrenBirthday());
				ai.setArchivesMobile(archivesInfo.getArchivesMobile());
				this.vaccineManageService.saveOrUpdateArchivesInfo(ai);
			}else{
				mes = this.vaccineManageService.saveRelationArchivesInfo(userId, archivesInfo);
			}
			
		}
		/**
		 * 得到当前用户的计免预约列表
		 */
		else if("getUserInoculationAppointmentInfoList".equals(action)) {
			this.userInoculationAppointmentInfoList = this.vaccineManageService.getUserInoculationAppointmentInfoList(userId);
			if(userInoculationAppointmentInfoList != null){
				for(UserInoculationAppointmentInfo uca : userInoculationAppointmentInfoList){
					//得到默认显示图片
					HospitalBanner banner = new HospitalBanner();
					banner.setIsMainImg("Y");
					List<HospitalBanner> bannerList = this.organManagerService.getHospitalBannerList(uca.getHospitalBasicInfo(), banner);
					if(bannerList != null){
						uca.getHospitalBasicInfo().setShowImgPath(bannerList.get(0).getImgPath());
					}
				}
			}
		}
		return "success";
	}

	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public List<OrganInoculationOpenResourcesDetail> getOrganInoculationOpenResourcesDetailList() {
		return organInoculationOpenResourcesDetailList;
	}
	public void setOrganInoculationOpenResourcesDetailList(
			List<OrganInoculationOpenResourcesDetail> organInoculationOpenResourcesDetailList) {
		this.organInoculationOpenResourcesDetailList = organInoculationOpenResourcesDetailList;
	}
	public OrganInoculationOpenResourcesDetail getOrganInoculationOpenResourcesDetail() {
		return organInoculationOpenResourcesDetail;
	}
	public void setOrganInoculationOpenResourcesDetail(
			OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail) {
		this.organInoculationOpenResourcesDetail = organInoculationOpenResourcesDetail;
	}
	public List<OrganInoculationOpenResources> getOrganInoculationOpenResourcesList() {
		return organInoculationOpenResourcesList;
	}
	public void setOrganInoculationOpenResourcesList(
			List<OrganInoculationOpenResources> organInoculationOpenResourcesList) {
		this.organInoculationOpenResourcesList = organInoculationOpenResourcesList;
	}
	public OrganInoculationOpenResources getOrganInoculationOpenResources() {
		return organInoculationOpenResources;
	}
	public void setOrganInoculationOpenResources(
			OrganInoculationOpenResources organInoculationOpenResources) {
		this.organInoculationOpenResources = organInoculationOpenResources;
	}
	public String getCurrentUserIdentity() {
		return currentUserIdentity;
	}
	public void setCurrentUserIdentity(String currentUserIdentity) {
		this.currentUserIdentity = currentUserIdentity;
	}
	public UserInoculationAppointmentInfo getUserInoculationAppointmentInfo() {
		return userInoculationAppointmentInfo;
	}
	public void setUserInoculationAppointmentInfo(
			UserInoculationAppointmentInfo userInoculationAppointmentInfo) {
		this.userInoculationAppointmentInfo = userInoculationAppointmentInfo;
	}
	public AppointmentInitInfo getAppointmentInitInfo() {
		return appointmentInitInfo;
	}
	public void setAppointmentInitInfo(AppointmentInitInfo appointmentInitInfo) {
		this.appointmentInitInfo = appointmentInitInfo;
	}
	public ArchivesInfo getArchivesInfo() {
		return archivesInfo;
	}
	public void setArchivesInfo(ArchivesInfo archivesInfo) {
		this.archivesInfo = archivesInfo;
	}
	public VaccineInfo getVaccineInfo() {
		return vaccineInfo;
	}
	public void setVaccineInfo(VaccineInfo vaccineInfo) {
		this.vaccineInfo = vaccineInfo;
	}

	public List<UserInoculationAppointmentInfo> getUserInoculationAppointmentInfoList() {
		return userInoculationAppointmentInfoList;
	}

	public void setUserInoculationAppointmentInfoList(
			List<UserInoculationAppointmentInfo> userInoculationAppointmentInfoList) {
		this.userInoculationAppointmentInfoList = userInoculationAppointmentInfoList;
	}

	public String getChildCared() {
		return childCared;
	}

	public void setChildCared(String childCared) {
		this.childCared = childCared;
	}

	public String getInoculationed() {
		return inoculationed;
	}

	public void setInoculationed(String inoculationed) {
		this.inoculationed = inoculationed;
	}

	public UserChildcareAppointmentInfo getUserChildcareAppointmentInfo() {
		return userChildcareAppointmentInfo;
	}

	public void setUserChildcareAppointmentInfo(
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		this.userChildcareAppointmentInfo = userChildcareAppointmentInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public void setFdServicePackage(FdServicePackage fdServicePackage) {
		this.fdServicePackage = fdServicePackage;
	}
}
