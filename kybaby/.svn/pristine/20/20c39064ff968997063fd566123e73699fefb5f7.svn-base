package com.kybaby.newbussiness.medicalorgandbusiness.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicDiscountInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProject;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.HospitalBanner;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganServicePlaceSet;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.fo.OrganChildcareOpenResourcesFo;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetMeatOrder;
import com.kybaby.newbussiness.spservice.bo.SpInterfaceService;
import com.kybaby.newbussiness.spservice.common.SpServiceConstant;
import com.kybaby.newbussiness.spservice.domain.SpRegisterOrderDetail;
import com.kybaby.util.CalculationMethod;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.SendSms;
import com.opensymphony.xwork2.ActionContext;

public class OpenBusinessManagerAction extends NewBaseAction{
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
	 * 儿保预约信息
	 */
	private UserChildcareAppointmentInfo userChildcareAppointmentInfo;
	/**
	 * 儿保资源
	 */
	private OrganChildcareOpenResources organChildcareOpenResources;
	/**
	 * 儿保资源开放主信息
	 */
	private List<OrganChildcareOpenResources> organChildcareOpenResourcesList = 
			new ArrayList<OrganChildcareOpenResources>();
	/**
	 * 儿保资源开放主信息集合
	 */
	private List<OrganChildcareOpenResourcesFo> organChildcareOpenResourcesFoList = 
			new ArrayList<OrganChildcareOpenResourcesFo>();
	/**
	 * 用户预约儿保信息列表
	 */
	private List<UserChildcareAppointmentInfo> userChildcareAppointmentInfoList = 
			new ArrayList<UserChildcareAppointmentInfo>();
	/**
	 * 儿保资源开放明细信息
	 */
	private List<OrganChildcareOpenResourcesDatail> organChildcareOpenResourcesDatailList = 
			new ArrayList<>();
	/**
	 * 当前用户身份信息
	 */
	private ArchivesInfo archivesInfoCueent;
	/**
	 * 用户信息
	 */
	private UserInfo userInfo;
	/**
	 * 医生信息
	 */
	private DoctorInfo doctorInfo;
	/**
	 * 儿保收费项目
	 */
	private ChildcareProject childcareProject;
	/**
	 * 套餐订单id
	 */
	private Long organSetMealOrderId;
	
	public String execute() throws Exception{
		Long userId = (Long) ActionContext.getContext().getSession().get("userId");
		if(userId!=null){
			this.userInfo = this.userInfoBo.getUserById(userId);
		}
		if(userId==null){
			mes="请登录";
			return "fail";
		}
		/**
		 * 初始化儿保开放资源信息
		
		else if(action.equals("initOrganChildcare")){
			//先得到用户身份
			ArchivesInfo archivesInfo = this.vaccineManageService.getCurrentUserIdentity(userId,null);
			this.archivesInfoCueent = archivesInfo;
			//区分用户身份，指派归属诊室
			OrganServicePlaceSet organServicePlaceSet = new OrganServicePlaceSet();
			
			this.hospitalBasicInfo = this.organManagerService.getHospitalBasicInfoById(hospitalBasicInfo.getId());
			//得到当前用户适合的儿保项目内容
			this.getChildcareProjectByAge(hospitalBasicInfo);
			//得到默认显示图片
			HospitalBanner banner = new HospitalBanner();
			banner.setIsMainImg("Y");
			List<HospitalBanner> bannerList = this.organManagerService.getHospitalBannerList(hospitalBasicInfo, banner);
			if(bannerList != null){
				this.hospitalBasicInfo.setShowImgPath(bannerList.get(0).getImgPath());
			}
			this.organChildcareOpenResourcesList = 
					this.openBusinessManagerService.
					getOrganChildcareOpenResourceslList(hospitalBasicInfo,archivesInfo);
			if(organChildcareOpenResourcesList != null){
				//当前时间,用于判断可预约时间是否过期
				String nowTime = DateManage.getDateStr("yyyyMMddHH:mm").replaceAll("-", "").replaceAll(":", "");
				for(OrganChildcareOpenResources ocor : organChildcareOpenResourcesList){
					//得到坐诊医生列表
					String doctorIds = ocor.getDoctorIds();
					if(doctorIds != null && !"".equals(doctorIds)){
						List<DoctorInfo> doctorList = new ArrayList<>();
						String[] doctorArr = doctorIds.split(",");
						for(int i=0; i<doctorArr.length; i++){
							DoctorInfo doctorInfo = this.doctorInfoBo.getDoctorInfoByDoctorId(Long.valueOf(doctorArr[i]));
							//儿保医生薪酬记录
							DoctorMoneyRecord dmr = this.openBusinessManagerService.getDoctorMoneyRecordBySomething
									(null, doctorInfo, ocor.getOpenDate());
							//DoctorInfo doctor = this.doctorInfoBo.getDoctorInfoByDoctorId(Long.valueOf(doctorArr[i]));
							if(dmr.getUserType() != null &&
									archivesInfo.getUserType().equals(dmr.getUserType().getTypeName())){
								DoctorInfo doctor = dmr.getDoctorInfo();
								doctorList.add(doctor);
							}
						}
						ocor.setDoctorInfoList(doctorList);
					}
					List<OrganChildcareOpenResourcesDatail> organChildcareOpenResourcesDatailList = 
							this.openBusinessManagerService.getOrganChildcareOpenResourcesDatailList(ocor);
					if(organChildcareOpenResourcesDatailList != null){
						for(OrganChildcareOpenResourcesDatail ocord : organChildcareOpenResourcesDatailList){
							String segment = (ocor.getOpenDate()+ocord.getSegment()).replaceAll("-", "").replaceAll(":", "");
							if("时间点".equals(ocor.getTimeDivisionNeed())){
								segment = (ocor.getOpenDate()+ocord.getSegment()).replaceAll("-", "").replaceAll(":", "");
								if(Long.valueOf(segment) < Long.valueOf(nowTime)){ //儿保时间小于当前时间
									ocord.setIsCanUse("N");
								}
							}else if("时间段".equals(ocor.getTimeDivisionNeed())){
								segment = (ocor.getOpenDate()+ocord.getOpenEndTime()).replaceAll("-", "").replaceAll(":", "");
								if(Long.valueOf(segment) < Long.valueOf(nowTime)){ //儿保时间小于当前时间
									ocord.setIsCanUse("N");
								}else{
									ocord.setIsCanUse("Y");
								}
							}
						}
					}
					ocor.setOrganChildcareOpenResourcesDatailList(organChildcareOpenResourcesDatailList);
				}
			}
		}
		 */
		/**
		 * 初始化儿保开放资源信息（时间跟着医生走改造）
		 */
		else if(action.equals("initOrganChildcare")){
			//先得到用户身份
			ArchivesInfo archivesInfo = this.vaccineManageService.getCurrentUserIdentity(userId,null);
			if(this.organSetMealOrderId != null){//套餐id进来的用户，用户类型从套餐产品里获取
				OrganSetMeatOrder setOrder = this.organSetMealService.getOrganSetMeatOrderById(organSetMealOrderId);
				archivesInfo.setUserType(setOrder.getOrganSetPro().getUserType().getTypeName());
			}
			this.archivesInfoCueent = archivesInfo;
			this.hospitalBasicInfo = this.organManagerService.getHospitalBasicInfoById(hospitalBasicInfo.getId());
			//得到当前用户适合的儿保项目内容
			this.getChildcareProjectByAge(hospitalBasicInfo);
			//得到默认显示图片
			HospitalBanner banner = new HospitalBanner();
			banner.setIsMainImg("Y");
			List<HospitalBanner> bannerList = this.organManagerService.getHospitalBannerList(hospitalBasicInfo, banner);
			if(bannerList != null){
				this.hospitalBasicInfo.setShowImgPath(bannerList.get(0).getImgPath());
			}
			List<OrganChildcareOpenResources> organChildcareOpenResourcesList = 
					this.openBusinessManagerService.getOrganChildcareOpenResourceslList(hospitalBasicInfo,archivesInfo);
			if(organChildcareOpenResourcesList != null){
				//组装门诊日期-医生关系
				Map<String,List<OrganChildcareOpenResources>> map = new HashMap<String,List<OrganChildcareOpenResources>>();
				for(OrganChildcareOpenResources ocor : organChildcareOpenResourcesList){
					//得到坐诊医生
					String doctorIds = ocor.getDoctorIds();
					if(doctorIds != null && !"".equals(doctorIds)){
						DoctorInfo doctorInfo = this.doctorInfoBo.getDoctorInfoByDoctorId(Long.valueOf(doctorIds));
						//儿保医生薪酬记录
						DoctorMoneyRecord dmr = this.openBusinessManagerService.getDoctorMoneyRecordBySomething
								(null, doctorInfo, ocor.getOpenDate());
						if(dmr.getUserType() != null &&
								archivesInfo.getUserType().equals(dmr.getUserType().getTypeName())){
							map.put(ocor.getOpenDate(), null);
							ocor.setDoctorInfo(doctorInfo);
						}
					}
				}
				for (Map.Entry<String,List<OrganChildcareOpenResources>> entry : map.entrySet()) {  
					OrganChildcareOpenResourcesFo fo = new OrganChildcareOpenResourcesFo();
					List<OrganChildcareOpenResources> newList = new ArrayList<>();
					fo.setOpenDate(entry.getKey());
					for(OrganChildcareOpenResources ocor : organChildcareOpenResourcesList){
						if(ocor.getOpenDate().equals(entry.getKey())){
							//得到坐诊医生
							String doctorIds = ocor.getDoctorIds();
							if(doctorIds != null && !"".equals(doctorIds)){
								DoctorInfo doctorInfo = this.doctorInfoBo.getDoctorInfoByDoctorId(Long.valueOf(doctorIds));
								//儿保医生薪酬记录
								DoctorMoneyRecord dmr = this.openBusinessManagerService.getDoctorMoneyRecordBySomething
										(null, doctorInfo, ocor.getOpenDate());
								if(dmr.getUserType() != null &&
										archivesInfo.getUserType().equals(dmr.getUserType().getTypeName())){
									newList.add(ocor);
								}
							}
						}
					}
					fo.setOrganChildcareOpenResourcesList(newList);
					this.organChildcareOpenResourcesFoList.add(fo);
				}
			}
		}
		/**
		 * 得到家庭医生儿保医生开放时间
		 */
		else if(action.equals("getChildCareOpenDatail")){
			OrganChildcareOpenResources ocor = this.openBusinessManagerService.
					getOrganChildcareOpenResourcesById(organChildcareOpenResources.getId());
			this.organChildcareOpenResourcesDatailList = 
					this.openBusinessManagerService.getOrganChildcareOpenResourcesDatailList(ocor);
			if(organChildcareOpenResourcesDatailList != null){
				//当前时间,用于判断可预约时间是否过期
				String nowTime = DateManage.getDateStr("yyyyMMddHH:mm").replaceAll("-", "").replaceAll(":", "");
				for(OrganChildcareOpenResourcesDatail ocord : organChildcareOpenResourcesDatailList){
					String segment = (ocor.getOpenDate()+ocord.getSegment()).replaceAll("-", "").replaceAll(":", "");
					if("时间点".equals(ocor.getTimeDivisionNeed())){
						segment = (ocor.getOpenDate()+ocord.getSegment()).replaceAll("-", "").replaceAll(":", "");
						if(Long.valueOf(segment) < Long.valueOf(nowTime)){ //儿保时间小于当前时间
							ocord.setIsCanUse("N");
						}
					}else if("时间段".equals(ocor.getTimeDivisionNeed())){
						segment = (ocor.getOpenDate()+ocord.getOpenEndTime()).replaceAll("-", "").replaceAll(":", "");
						if(Long.valueOf(segment) < Long.valueOf(nowTime)){ //儿保时间小于当前时间
							ocord.setIsCanUse("N");
						}else{
							ocord.setIsCanUse("Y");
						}
					}
				}
			}
		}
		/**
		 * 保存儿保预约信息
		 */
		else if(action.equals("saveOrUpdateUserChildcareAppointmentInfo")){
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
				this.mes = "您已预约过！";
				return "fail";
			}
			Long detailId =	userChildcareAppointmentInfo.getOrganChildcareOpenResourcesDatail().getId();
			OrganChildcareOpenResourcesDatail oldDetail = 
					this.openBusinessManagerService.getOrganChildcareOpenResourcesDatailById(detailId);
			
			//先得到用户身份
			ArchivesInfo archivesInfo = this.vaccineManageService.getCurrentUserIdentity(userId,null);
			//判断是否有可用号源
			if("时间点".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				if("N".equals(oldDetail.getIsCanUse())){
					this.mes = "所选时间已被预约";
					return "fail";
				}
			}else if("时间段".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				//判断是否有可用号源
				if(ArchivesInfo.USER_TYPE_GOLDEN_CARD.equals(archivesInfo.getUserType())){//金卡
					if("0".equals(oldDetail.getGreenChannelSurplusNum())){
						this.mes = "没有号源可约";
						return "fail";
					}
				}else if(ArchivesInfo.USER_TYPE_PUKA.equals(archivesInfo.getUserType()) ||
						ArchivesInfo.USER_TYPE_RETAIL.equals(archivesInfo.getUserType())){//普卡,散户，看普通资源
					if("0".equals(oldDetail.getGeneralSurplusNum())){
						this.mes = "没有号源可约";
						return "fail";
					}
				}
			}
			//分配用户诊室，分配到同类型诊室下人最少的一个诊室去
			OrganServicePlaceSet organServicePlaceSet = 
					this.openBusinessManagerService.getMinNumOrganServicePlaceSet(archivesInfo, hospitalBasicInfo);
			userChildcareAppointmentInfo.setOrganServicePlaceSet(organServicePlaceSet);
			userChildcareAppointmentInfo.setStatus(ConstantManage.HASE_BOOKED_CLINIC_ORDER);
			userChildcareAppointmentInfo.setHospitalBasicInfo(hospitalBasicInfo);
			userChildcareAppointmentInfo.setUserInfo(user);
			userChildcareAppointmentInfo.setOperationTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			//生成预约编号(在dao层里实现)
			Long id = this.openBusinessManagerService.saveOrUpdateUserChildcareAppointmentInfo(userChildcareAppointmentInfo);
			//将儿保资源设置为已用,或将剩余号源减去1
			if("时间点".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				oldDetail.setIsCanUse("N");
			}else if("时间段".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				if(ArchivesInfo.USER_TYPE_GOLDEN_CARD.equals(archivesInfo.getUserType())){//金卡
					Long greenChannelSurplusNum = Long.valueOf(oldDetail.getGreenChannelSurplusNum());
					greenChannelSurplusNum = greenChannelSurplusNum.longValue() - 1;
					oldDetail.setGreenChannelSurplusNum(String.valueOf(greenChannelSurplusNum<=0L?0L:greenChannelSurplusNum));
				}else if(ArchivesInfo.USER_TYPE_PUKA.equals(archivesInfo.getUserType()) || 
						ArchivesInfo.USER_TYPE_RETAIL.equals(archivesInfo.getUserType())){//普卡，散户
					Long generalSurplusNum = Long.valueOf(oldDetail.getGeneralSurplusNum());
					generalSurplusNum = generalSurplusNum.longValue() - 1;
					oldDetail.setGeneralSurplusNum(String.valueOf(generalSurplusNum<=0L?0L:generalSurplusNum));
				}
			}
			this.openBusinessManagerService.saveOrUpdateOrganChildcareOpenResourcesDatail(oldDetail);
			UserChildcareAppointmentInfo oldInfo = this.openBusinessManagerService.getUserChildcareAppointmentInfoById(id);
			userChildcareAppointmentInfo.setId(id);
			//发送短信通知
			SendSms ss = new SendSms();
			String contecnt = "亲爱的用户，您已预约"+oldInfo.getHospitalBasicInfo().getHospitalLname()+"的儿保服务，请于"+
					oldInfo.getOrganChildcareOpenResources().getOpenDate() + " ";
			if("时间点".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				contecnt +=  oldInfo.getOrganChildcareOpenResourcesDatail().getSegment() + "之前";
			}else if("时间段".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				contecnt += oldInfo.getOrganChildcareOpenResourcesDatail().getOpenStartTime() + "到" +
						oldInfo.getOrganChildcareOpenResourcesDatail().getOpenEndTime() + "之间";
								
			}
			contecnt += "前往，地址：" + oldInfo.getHospitalBasicInfo().getAddress()+"。详情请查‘我的预约’";	
			ss.sendInfo(user.getPhone(), contecnt.toString());
		}
		/**
		 * 得到儿保预约信息
		 */
		else if(action.equals("getUserChildcareAppointmentInfo")){
			this.userChildcareAppointmentInfo = 
					this.openBusinessManagerService.getUserChildcareAppointmentInfoById(userChildcareAppointmentInfo.getId());
		}
		/**
		 * 用户取消儿保设置信息
		 */
		else if(action.equals("cancelUserChildcareAppointmentInfo")){
			UserChildcareAppointmentInfo old = 
					this.openBusinessManagerService.getUserChildcareAppointmentInfoById(userChildcareAppointmentInfo.getId());
			old.setStatus(ConstantManage.USER_CANCLE_CLINIC_ORDER);
			this.openBusinessManagerService.saveOrUpdateUserChildcareAppointmentInfo(old);
			UserInfo user = this.userInfoBo.getUserById(userId);
			//先得到用户身份
			ArchivesInfo archivesInfo = this.vaccineManageService.getCurrentUserIdentity(userId,null);
			//将占用资源释放
			OrganChildcareOpenResourcesDatail oldDetail = old.getOrganChildcareOpenResourcesDatail();
			if("时间点".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				oldDetail.setIsCanUse("Y");
			}else if("时间段".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				if(ArchivesInfo.USER_TYPE_GOLDEN_CARD.equals(archivesInfo.getUserType())){//金卡
					Long greenChannelSurplusNum = Long.valueOf(oldDetail.getGreenChannelSurplusNum());
					greenChannelSurplusNum = greenChannelSurplusNum.longValue() + 1;
					oldDetail.setGreenChannelSurplusNum(String.valueOf(greenChannelSurplusNum<=0L?0L:greenChannelSurplusNum));
				}else if(ArchivesInfo.USER_TYPE_PUKA.equals(archivesInfo.getUserType()) ||
						ArchivesInfo.USER_TYPE_RETAIL.equals(archivesInfo.getUserType())){//普卡，散户
					Long generalSurplusNum = Long.valueOf(oldDetail.getGeneralSurplusNum());
					generalSurplusNum = generalSurplusNum.longValue() + 1;
					oldDetail.setGeneralSurplusNum(String.valueOf(generalSurplusNum<=0L?0L:generalSurplusNum));
				}
			}
			
			this.openBusinessManagerService.saveOrUpdateOrganChildcareOpenResourcesDatail(oldDetail);
		}
		/**
		 * 用户儿保预约列表
		 */
		else if(action.equals("getUserChildcareAppointmentInfoList")){
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			this.userChildcareAppointmentInfoList = 
					this.openBusinessManagerService.getUserChildcareAppointmentInfoList(userInfo, null,userChildcareAppointmentInfo);
			if(userChildcareAppointmentInfoList != null){
				for(UserChildcareAppointmentInfo uca : userChildcareAppointmentInfoList){
					//得到默认显示图片
					HospitalBanner banner = new HospitalBanner();
					banner.setIsMainImg("Y");
					List<HospitalBanner> bannerList = this.organManagerService.getHospitalBannerList(uca.getHospitalBasicInfo(), banner);
					if(bannerList != null){
						uca.getHospitalBasicInfo().setShowImgPath(bannerList.get(0).getImgPath());
					}
					//判断是否中联接口订单
					SpRegisterOrderDetail spRegisterOrderDetail = 
							this.spInterfaceService.getSpRegisterOrderDetail(uca.getId(), SpServiceConstant.CHILD_CARE_FLAG);
					if(spRegisterOrderDetail!=null){
						uca.setIs_zhonglian("Y");
					}
				}
			}
		}
		return "success";
	}
	/**
	 * 得到当前用户适合的儿保项目内容
	 * @param hospitalBasicInfo
	 */
	private void getChildcareProjectByAge(HospitalBasicInfo hospitalBasicInfo){
		//得到用户月龄
		String babyBirthday=userInfo.getBirthday();
		if(this.organSetMealOrderId != null){//套餐id进来的用户，用户类型从套餐产品里获取
			OrganSetMeatOrder setOrder = this.organSetMealService.getOrganSetMeatOrderById(organSetMealOrderId);
			babyBirthday = setOrder.getBabyInfo().getBirthday();
		}else{
			//先得到用户身份
			ArchivesInfo archivesInfo = this.vaccineManageService.getCurrentUserIdentity(userInfo.getId(),null);
			if(archivesInfo != null){
				babyBirthday=archivesInfo.getChildrenBirthday();
			}
		}
		String rightNow=CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
		String monthAge="";
		try {
			monthAge = String.valueOf(CalculationMethod.getMonthSpace(babyBirthday, rightNow));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.childcareProject = this.childCareChargeService.
				getChildcareProjectBySomething(null, hospitalBasicInfo, monthAge);
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public UserChildcareAppointmentInfo getUserChildcareAppointmentInfo() {
		return userChildcareAppointmentInfo;
	}

	public void setUserChildcareAppointmentInfo(
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		this.userChildcareAppointmentInfo = userChildcareAppointmentInfo;
	}

	public List<OrganChildcareOpenResources> getOrganChildcareOpenResourcesList() {
		return organChildcareOpenResourcesList;
	}

	public void setOrganChildcareOpenResourcesList(
			List<OrganChildcareOpenResources> organChildcareOpenResourcesList) {
		this.organChildcareOpenResourcesList = organChildcareOpenResourcesList;
	}

	public List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList() {
		return userChildcareAppointmentInfoList;
	}

	public void setUserChildcareAppointmentInfoList(
			List<UserChildcareAppointmentInfo> userChildcareAppointmentInfoList) {
		this.userChildcareAppointmentInfoList = userChildcareAppointmentInfoList;
	}

	public ArchivesInfo getArchivesInfoCueent() {
		return archivesInfoCueent;
	}

	public void setArchivesInfoCueent(ArchivesInfo archivesInfoCueent) {
		this.archivesInfoCueent = archivesInfoCueent;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public ChildcareProject getChildcareProject() {
		return childcareProject;
	}

	public void setChildcareProject(ChildcareProject childcareProject) {
		this.childcareProject = childcareProject;
	}
	public OrganChildcareOpenResources getOrganChildcareOpenResources() {
		return organChildcareOpenResources;
	}
	public void setOrganChildcareOpenResources(
			OrganChildcareOpenResources organChildcareOpenResources) {
		this.organChildcareOpenResources = organChildcareOpenResources;
	}
	public List<OrganChildcareOpenResourcesFo> getOrganChildcareOpenResourcesFoList() {
		return organChildcareOpenResourcesFoList;
	}
	public void setOrganChildcareOpenResourcesFoList(
			List<OrganChildcareOpenResourcesFo> organChildcareOpenResourcesFoList) {
		this.organChildcareOpenResourcesFoList = organChildcareOpenResourcesFoList;
	}
	public List<OrganChildcareOpenResourcesDatail> getOrganChildcareOpenResourcesDatailList() {
		return organChildcareOpenResourcesDatailList;
	}
	public void setOrganChildcareOpenResourcesDatailList(
			List<OrganChildcareOpenResourcesDatail> organChildcareOpenResourcesDatailList) {
		this.organChildcareOpenResourcesDatailList = organChildcareOpenResourcesDatailList;
	}
	public void setOrganSetMealOrderId(Long organSetMealOrderId) {
		this.organSetMealOrderId = organSetMealOrderId;
	}
}
