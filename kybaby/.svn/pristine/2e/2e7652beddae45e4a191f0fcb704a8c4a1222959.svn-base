package com.kybaby.newbussiness.medicalorgandbusiness.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.HospitalBanner;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgBusinessRelation;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgOpenBusiness;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserFollowHospital;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.GetDistance;
import com.kybaby.util.SortListUtil;
import com.opensymphony.xwork2.ActionContext;

public class OrganManagerAction extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 医院机构信息
	 */
	private HospitalBasicInfo hospitalBasicInfo;
	/**
	 * 医院机构列表
	 */
	private List<HospitalBasicInfo> hospitalBasicInfoList = new ArrayList<HospitalBasicInfo>();
	/**
	 * 医院机构banner
	 */
	private List<HospitalBanner> hospitalBannerList = new ArrayList<HospitalBanner>();
	/**
	 * 医院机构开展业务集合
	 */
	private List<OrgOpenBusiness> orgOpenBusinessList = new ArrayList<OrgOpenBusiness>();
	/**
	 * 用户关注机构信息
	 */
	private UserFollowHospital userFollowHospital;
	/**
	 * 用户关注机构信息列表
	 */
	private List<UserFollowHospital> userFollowHospitalList = new ArrayList<UserFollowHospital>();
	/**
	 * 粉丝数
	 */
	private Long follows;
	/**
	 * 用户身份
	 */
	private ArchivesInfo archivesInfo;
	/**
	 * 用户信息
	 */
	private UserInfo userInfo;
	/**
	 * 经度
	 */
	private Double lng_current;
	/**
	 * 纬度
	 */
	private Double lat_current;
	/**
	 * 业务名称
	 */
	private String businessName;
	
	public String execute(){
		Long userId = (Long) ActionContext.getContext().getSession().get("userId");
		if(userId!=null){
			this.userInfo = this.userInfoBo.getUserById(userId);
		}
		if(userId==null){
			mes="请登录";
			return "fail";
		}
		/**
		 * 得到社区机构列表
		 */
		else if(action.equals("getHospitalInfoList")){
			this.hospitalBasicInfoList = this.organManagerService.getHospitalBasicInfoList(hospitalBasicInfo);
			List<HospitalBasicInfo> openServiceHosList = new ArrayList<HospitalBasicInfo>();
			if(hospitalBasicInfoList != null){
				UserInfo user = this.userInfoBo.getUserById(userId);
				double lng1 = Double.valueOf(user.getUserLng()==null?"0":user.getUserLng());
				double lat1 = Double.valueOf(user.getUserLat()==null?"0":user.getUserLat());
//				System.out.println("lng_current===="+lng_current);
//				System.out.println("lat_current===="+lat_current);
				HospitalBanner banner = new HospitalBanner();
				banner.setIsMainImg("Y");
				for(HospitalBasicInfo hospital : hospitalBasicInfoList){
					//计算与当前用户的距离
					double lng2 = Double.valueOf(hospital.getHospitalLng()==null?"0":hospital.getHospitalLng());
					double lat2 = Double.valueOf(hospital.getHospitalLat()==null?"0":hospital.getHospitalLat());
					double distance = 
							GetDistance.GetDistanceMethod(lng_current==null?lng1:lng_current, lat_current==null?lat1:lat_current,
									lng2, lat2);
					hospital.setToUserDistance(distance);
					//得到默认显示图片
					List<HospitalBanner> bannerList = this.organManagerService.getHospitalBannerList(hospital, banner);
					if(bannerList != null){
						hospital.setShowImgPath(bannerList.get(0).getImgPath());
					}
					//得到机构开展业务
					List<OrgBusinessRelation> orgBusinessList = this.organManagerService.getOrgBusinessRelationList(hospital);
					List<String> businessNameList = new ArrayList<String>();
					if(orgBusinessList != null){
						for(OrgBusinessRelation obr : orgBusinessList){
							OrgOpenBusiness openBuniss = obr.getOrgOpenBusiness();
							businessNameList.add(openBuniss.getBusinessName());
						}
					}else{
						businessNameList.add("暂无服务");
					}
					hospital.setBusinessNameList(businessNameList);
					//得到机构能够坐诊的日期
					List<String> clinicDateList = this.getBookingDate(hospital,userId);
					hospital.setClinicBookingDate(clinicDateList);
					if(orgBusinessList != null){
						openServiceHosList.add(hospital);
					}
				}
				this.hospitalBasicInfoList = openServiceHosList;
				// 按距离顺序  
		        Collections.sort(hospitalBasicInfoList, new Comparator<HospitalBasicInfo>() {  
		            public int compare(HospitalBasicInfo arg0, HospitalBasicInfo arg1) {  
		                double hits0 = arg0.getToUserDistance();  
		                double hits1 = arg1.getToUserDistance();  
		                if (hits1 > hits0) {  
		                    return -1;  
		                } else if (hits1 == hits0) {  
		                    return 0;  
		                } else {  
		                    return 1;  
		                }  
		            }  
		        }); 
		        HttpServletRequest request = ServletActionContext.getRequest();
		        String listNum = request.getParameter("listNum");
		        if(listNum != null && !"".equals(listNum)){
		        	List<HospitalBasicInfo> hospitalBasicInfoList_new = new ArrayList<HospitalBasicInfo>();
		        	int count = 0;
		        	for(HospitalBasicInfo hospital : hospitalBasicInfoList){
		        		if(count < Integer.valueOf(listNum).intValue()){
		        			hospitalBasicInfoList_new.add(hospital);
		        		}
		        		count++;
		        	}
		        	this.hospitalBasicInfoList = hospitalBasicInfoList_new;
		        }
			}
		}
		/**
		 * 得到社区机构详细信息
		 */
		else if(action.equals("getHospitalInfo")){
			UserInfo user = this.userInfoBo.getUserById(userId);
			//得到用户身份
			this.archivesInfo = this.organManagerService.getCurrentUserIdentity(userId,hospitalBasicInfo);
			ArchivesInfo ai = this.organManagerService.getCurrentUserIdentity(userId,null);
			if(ai == null){//没有绑定或没有身份信息
				//再判断是否注册过
				ArchivesInfo aiByPhone = this.vaccineManageService.getCurrentUserIdentity(null,user.getPhone());
				if(aiByPhone == null){//没有身份信息，系统自动初始化为散户
					ArchivesInfo aiNew = new ArchivesInfo();
					aiNew.setArchivesMobile(user.getPhone());
					aiNew.setChildrenBirthday(user.getBirthday());
					aiNew.setChildrenName(user.getBabyName());
					aiNew.setIsRelation("N");
					aiNew.setUserInfo(user);
					aiNew.setUserType(ArchivesInfo.USER_TYPE_RETAIL);
					this.vaccineManageService.saveOrUpdateArchivesInfo(aiNew);
					this.archivesInfo = aiNew;
				}else{
					this.archivesInfo = aiByPhone;
				}
			}else{
				this.archivesInfo = ai;
			}
			this.hospitalBasicInfo = this.organManagerService.getHospitalBasicInfoById(hospitalBasicInfo.getId());
			//机构开展业务集合
			List<OrgBusinessRelation> orgBusinessList = this.organManagerService.getOrgBusinessRelationList(hospitalBasicInfo);
			if(orgBusinessList != null){
				for(OrgBusinessRelation obr : orgBusinessList){
					OrgOpenBusiness openBuniss = obr.getOrgOpenBusiness();
					if(obr.getUserTypeIds() != null){//判断服务的用户类型是什么
						List<String> userTypeNameList = this.getUserTypeList(obr.getUserTypeIds());
						if(archivesInfo != null && userTypeNameList.contains(archivesInfo.getUserType())){
							this.orgOpenBusinessList.add(openBuniss);
						}
					}else{//没有配置用户类型的默认为都可以
						this.orgOpenBusinessList.add(openBuniss);
					}
				}
			}
			//得到社区机构banner
			this.hospitalBannerList = this.organManagerService.getHospitalBannerList(hospitalBasicInfo, null);
			//当前用户是否关注该机构
			List<UserFollowHospital> userFollowList_one = this.organManagerService.getUserFollowHospitalList(hospitalBasicInfo,user);
			if(userFollowList_one != null){
				this.userFollowHospital = userFollowList_one.get(0);
			}
			//机构粉丝数
			List<UserFollowHospital> userFollowList = this.organManagerService.getUserFollowHospitalList(hospitalBasicInfo,null);
			if(userFollowList != null){
				this.hospitalBasicInfo.setUserFollows(String.valueOf(userFollowList.size()));
			}else{
				this.hospitalBasicInfo.setUserFollows("0");
			}
			//机构门诊次数
			OrderInfoClinic orderClinic = new OrderInfoClinic();
			orderClinic.setClinicAddress(hospitalBasicInfo.getHospitalLname());
			List<OrderInfoClinic> orderList = this.clinicOrderService.getOrderInfoClinicList(orderClinic);
			int clinics = 0;
			if(orderList != null){
				String not = ConstantManage.NO_PAYMENT_CLINIC_ORDER+ConstantManage.USER_CANCLE_CLINIC_ORDER;
				for(OrderInfoClinic order : orderList){
					if(not.indexOf(order.getOrderStatus()) < 0){
						clinics ++;
					}
				}
			}
			this.hospitalBasicInfo.setClinics(String.valueOf(clinics));
			//服务医生数
			DoctorMorePractice doctorMorePractice = new DoctorMorePractice();
			doctorMorePractice.setClinicOrg(hospitalBasicInfo.getHospitalLname());
			Long doctorNum = this.organManagerService.getClinicDoctorNumByOrg(doctorMorePractice);
			this.hospitalBasicInfo.setDoctors(doctorNum.toString());
		}
		
		/**
		 * 用户取消/关注机构
		 */
		else if(action.equals("saveOrUpdateUserFollowHospital")){
			UserInfo user = this.userInfoBo.getUserById(userId);
			this.userFollowHospital = 
					this.organManagerService.saveOrUpdateUserFollowHospital(hospitalBasicInfo, user,userFollowHospital);
			//机构粉丝数
			List<UserFollowHospital> userFollowList = this.organManagerService.getUserFollowHospitalList(hospitalBasicInfo,null);
			if(userFollowList != null){
				follows = Long.valueOf(userFollowList.size());
			}else{
				follows = 0L;
			}
		}
		/**
		 * 用户关注机构列表
		 */
		else if(action.equals("getUserFollowHospitalList")){
			UserInfo user = this.userInfoBo.getUserById(userId);
			this.userFollowHospitalList = this.organManagerService.getUserFollowHospitalList(null,user);
			if(userFollowHospitalList != null){
				HospitalBanner banner = new HospitalBanner();
				banner.setIsMainImg("Y");
				for(UserFollowHospital ufh : userFollowHospitalList){
					//得到默认显示图片
					List<HospitalBanner> bannerList = 
							this.organManagerService.getHospitalBannerList(ufh.getHospitalBasicInfo(), banner);
					if(bannerList != null){
						ufh.getHospitalBasicInfo().setShowImgPath(bannerList.get(0).getImgPath());
					}
				}
			}
		}
		/**
		 * 得到社区机构banner
		 */
		else if(action.equals("getAllHospitalBanner")){
			this.hospitalBannerList = this.organManagerService.getHospitalBannerList(hospitalBasicInfo, null);
		}
		/**
		 * 解绑机构关联基础档案信息
		 */
		else if(action.equals("relieveArchivesInfo")){
			ArchivesInfo old = this.organManagerService.getArchivesInfoById(archivesInfo.getId());
			//old.setUserInfo(null);
			old.setIsRelation("N");
			//if(ArchivesInfo.USER_TYPE_RETAIL.equals(old.getUserType())){//散户要解除机构绑定
				old.setHospitalBasicInfo(null);
			//}
			this.organManagerService.saveOrUpdateArchivesInfo(old);
		}
		/**
		 * 判断用户绑定的机构是否对其开放相关服务（页面重构后引发的问题解决方案）
		 */
		else if(action.equals("checkArchivesInfoServices")){
			ArchivesInfo old = this.organManagerService.getArchivesInfoById(archivesInfo.getId());
			//机构开展业务集合
			List<OrgBusinessRelation> orgBusinessList = this.organManagerService.getOrgBusinessRelationList(old.getHospitalBasicInfo());
			if(orgBusinessList != null){
				for(OrgBusinessRelation obr : orgBusinessList){
					OrgOpenBusiness openBuniss = obr.getOrgOpenBusiness();
					if(openBuniss.getBusinessName().trim().equals(businessName.trim()) && obr.getUserTypeIds() != null){//判断服务的用户类型是什么
						List<String> userTypeNameList = this.getUserTypeList(obr.getUserTypeIds());
						if(old != null && !userTypeNameList.contains(old.getUserType())){
							this.mes = "关联机构"+old.getHospitalBasicInfo().getHospitalLname()+
									"未为对"+old.getUserType()+"开通"+businessName+"服务";
							break;
						}
					}
				}
			}
		}
		
		return "success";
	}
	
	/**
	 * 得到医生门诊预约时间集合（门诊医生列表页面显示用）
	 * @param doctorInfo 医生信息
	 * @return 门诊时间显示信息集合
	 */
	private List<String> getBookingDate(HospitalBasicInfo hospital,Long userId){
		List<String> list = new ArrayList<String>();
		DoctorMorePractice doctorMorePractice = new DoctorMorePractice();
		doctorMorePractice.setClinicOrg(hospital.getHospitalLname());
		List<DoctorMorePractice> doctorMorePracticeList = 
				this.doctorClinicService.getDoctorMorePracticeList(doctorMorePractice, null);
		if(doctorMorePracticeList != null){
			Map<String,String> map = new LinkedHashMap<String,String>();
			UserType exitUserType = this.getUserTypeByCurrentUser(userId);
			for(DoctorMorePractice dmp : doctorMorePracticeList){
				String clinicDate = dmp.getClinicDate();
				String nowDate = DateManage.getDateStr("yyyy-MM-dd");
				if(clinicDate.equals(nowDate)){
					nowDate = DateManage.getDateStr("MM月dd日");
					if(StringUtils.isNotEmpty(dmp.getUserTypeIds())){
						String[] userTypeIds = dmp.getUserTypeIds().split(",");
						if(exitUserType != null && Arrays.asList(userTypeIds).contains(exitUserType.getId()+"")){
							map.put("今天"+nowDate+"可约", "今天"+nowDate+"可约");
						}
					}else{
						map.put("今天"+nowDate+"可约", "今天"+nowDate+"可约");
					}
				}else{
					Date bookingDate = DateManage.getStrToDate(clinicDate);
					Date nowD =  DateManage.getStrToDate(nowDate);
					String dateStr = DateManage.getDateToStr(bookingDate, "MM月dd日");
					//计算差多少天
					long days = DateManage.getDaysBetween(bookingDate, nowD);
					if(days == 1L){
						if(StringUtils.isNotEmpty(dmp.getUserTypeIds())){
							String[] userTypeIds = dmp.getUserTypeIds().split(",");
							if(exitUserType != null && Arrays.asList(userTypeIds).contains(exitUserType.getId()+"")){
								//list.add("明天"+dateStr+"可约");
								map.put("明天"+dateStr+"可约", "明天"+dateStr+"可约");
							}
						}else{
							//list.add("明天"+dateStr+"可约");
							map.put("明天"+dateStr+"可约", "明天"+dateStr+"可约");
						}
					}else if(days == 2L){
						if(StringUtils.isNotEmpty(dmp.getUserTypeIds())){
							String[] userTypeIds = dmp.getUserTypeIds().split(",");
							if(exitUserType != null && Arrays.asList(userTypeIds).contains(exitUserType.getId()+"")){
								//list.add("后天"+dateStr+"可约");
								map.put("后天"+dateStr+"可约", "后天"+dateStr+"可约");
							}
						}else{
							//list.add("后天"+dateStr+"可约");
							map.put("后天"+dateStr+"可约", "后天"+dateStr+"可约");
						}
					}else{
						String week = DateManage.getWeekByZhou(bookingDate);
						if(StringUtils.isNotEmpty(dmp.getUserTypeIds())){
							String[] userTypeIds = dmp.getUserTypeIds().split(",");
							if(exitUserType != null && Arrays.asList(userTypeIds).contains(exitUserType.getId()+"")){
								//list.add(week+dateStr+"可约");
								map.put(week+dateStr+"可约", week+dateStr+"可约");
							}
						}else{
							//list.add(week+dateStr+"可约");
							map.put(week+dateStr+"可约", week+dateStr+"可约");
						}
					}
				}
			}
			for (Map.Entry<String,String> entry : map.entrySet()) {  
				int count = list.size();
				if(count == 2) break;
				list.add(entry.getKey());
			}
		}
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	/**
	 * 得到用户类型列表
	 * @param ids
	 * @return
	 */
	private List<String> getUserTypeList(String ids){
		List<String> list = new ArrayList<>();
		String typeIds[] = ids.split(",");
		for(int i=0;i<typeIds.length;i++){
			UserType ut = this.organManagerService.getUserTypeById(Long.valueOf(typeIds[i]));
			list.add(ut.getTypeName());
		}
		return list;
	}
	public static void main(String[] args) {
		String clinicDate = "2016-04-08";
		String nowDate = DateManage.getDateStr("yyyy-MM-dd");
		Date bookingDate = DateManage.getStrToDate(clinicDate);
		Date nowD =  DateManage.getStrToDate(nowDate);
		//计算差多少天
		long days = DateManage.getDaysBetween(bookingDate, nowD);
		System.out.println(clinicDate +"与"+new Date()+ "差" + days);
	}
	/**
	 * 根据当前用户得到用户身份
	 * @return
	 */
	private UserType getUserTypeByCurrentUser(Long userId){
		UserInfo user = this.userInfoBo.getUserById(userId);
		//先得到用户身份
		ArchivesInfo archivesInfo = this.vaccineManageService.getCurrentUserIdentity(userId,null);
		//没有身份的怎么弄（散户？），这样的话就是开放资源里要求匹配用户类型的机构，当用户约该机构儿科时就要管身份，没有的初始化为散户
		if(archivesInfo == null){//没有绑定或没有身份信息
			//再判断是否注册过
			ArchivesInfo aiByPhone = this.vaccineManageService.getCurrentUserIdentity(null,user.getPhone());
			if(aiByPhone == null){//没有身份信息，系统自动初始化为散户
				ArchivesInfo aiNew = new ArchivesInfo();
				aiNew.setUserType(ArchivesInfo.USER_TYPE_RETAIL);
				archivesInfo = aiNew;
			}else{
				archivesInfo = aiByPhone;
			}
		}
		String userTypeName = archivesInfo.getUserType();
		UserType userType = new UserType();
		userType.setTypeName(userTypeName);
		List<UserType> userTypeList = this.doctorClinicService.getUserTypeList(userType);
		if(userTypeList != null){
			return userTypeList.get(0);
		}
		return null;
	}
	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public List<HospitalBasicInfo> getHospitalBasicInfoList() {
		return hospitalBasicInfoList;
	}

	public void setHospitalBasicInfoList(
			List<HospitalBasicInfo> hospitalBasicInfoList) {
		this.hospitalBasicInfoList = hospitalBasicInfoList;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public List<HospitalBanner> getHospitalBannerList() {
		return hospitalBannerList;
	}

	public void setHospitalBannerList(List<HospitalBanner> hospitalBannerList) {
		this.hospitalBannerList = hospitalBannerList;
	}

	public List<OrgOpenBusiness> getOrgOpenBusinessList() {
		return orgOpenBusinessList;
	}

	public void setOrgOpenBusinessList(List<OrgOpenBusiness> orgOpenBusinessList) {
		this.orgOpenBusinessList = orgOpenBusinessList;
	}

	public UserFollowHospital getUserFollowHospital() {
		return userFollowHospital;
	}

	public void setUserFollowHospital(UserFollowHospital userFollowHospital) {
		this.userFollowHospital = userFollowHospital;
	}

	public Long getFollows() {
		return follows;
	}

	public ArchivesInfo getArchivesInfo() {
		return archivesInfo;
	}

	public void setArchivesInfo(ArchivesInfo archivesInfo) {
		this.archivesInfo = archivesInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<UserFollowHospital> getUserFollowHospitalList() {
		return userFollowHospitalList;
	}

	public void setUserFollowHospitalList(
			List<UserFollowHospital> userFollowHospitalList) {
		this.userFollowHospitalList = userFollowHospitalList;
	}

	public void setLng_current(Double lng_current) {
		this.lng_current = lng_current;
	}

	public void setLat_current(Double lat_current) {
		this.lat_current = lat_current;
	}

	public Double getLng_current() {
		return lng_current;
	}

	public Double getLat_current() {
		return lat_current;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
}
