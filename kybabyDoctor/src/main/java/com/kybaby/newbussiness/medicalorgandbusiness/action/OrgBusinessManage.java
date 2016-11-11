package com.kybaby.newbussiness.medicalorgandbusiness.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgBusinessRelation;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgOpenBusiness;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenDoctor;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganModuleInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganOperator;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.VaccineInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.fo.OrderCountInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.fo.UserChildcareAppointmentInfoFo;
import com.kybaby.newbussiness.medicalorgandbusiness.fo.UserInoculationAppointmentInfoFo;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.HttpClientUtil;
import com.opensymphony.xwork2.ActionContext;

public class OrgBusinessManage extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 机构信息
	 */
	private HospitalBasicInfo hospitalBasicInfo;
	/**
	 * 登录人信息
	 */
	private OrganOperator organOperator;
	/**
	 * 儿保预约信息
	 */
	private UserChildcareAppointmentInfo userChildcareAppointmentInfo;
	/** 
	 * 用户预约儿保信息列表
	 */
	private List<UserChildcareAppointmentInfo> userChildcareAppointmentInfoList = 
			new ArrayList<UserChildcareAppointmentInfo>();
	/**
	 * 业务名称列表
	 */
	private List<String> businessNameList = new ArrayList<String>();
	/**
	 * 计免预约信息
	 */
	private UserInoculationAppointmentInfo userInoculationAppointmentInfo;
	/**
	 * 计免预约列表
	 */
	List<UserInoculationAppointmentInfo> userInoculationAppointmentInfoList = 
			new ArrayList<UserInoculationAppointmentInfo>(); 
	/**
	 * 计免预约列表(页面显示)
	 */
	List<UserInoculationAppointmentInfoFo> userInoculationAppointmentInfoShowList = 
			new ArrayList<UserInoculationAppointmentInfoFo>(); 
	/**
	 * 儿保预约列表(页面显示)
	 */
	List<UserChildcareAppointmentInfoFo> userChildcareAppointmentInfoFoShowList = 
			new ArrayList<UserChildcareAppointmentInfoFo>(); 
	/**
	 * 疫苗信息列表
	 */
	List<VaccineInfo> vaccineInfoList =	new ArrayList<VaccineInfo>(); 
	/**
	 * 功能菜单列表
	 */
	private List<OrganModuleInfo> organModuleInfoList = new ArrayList<OrganModuleInfo>();
	/**
	 * 机构儿保医生信息
	 */
	private OrganChildcareOpenDoctor organChildcareOpenDoctor;
	/**
	 * 数量统计信息
	 */
	private OrderCountInfo orderCountInfo = new OrderCountInfo();
	
	@Override
	public String execute() {
		System.out.println("execute===="+ActionContext.getContext().getSession().get("organOperator"));
		organOperator = (OrganOperator)ActionContext.getContext().getSession().get("organOperator");
		if(organOperator==null){
			mes="请登录";
			return "fail";
		}
		if(action.equals("getBusinessNameList")){
			System.out.println("登录成功进业务了===="+ActionContext.getContext().getSession().get("organOperator"));
			//得到机构开展业务
			List<OrgBusinessRelation> orgBusinessList = this.orgBusinessManageService.getOrgBusinessRelationList(organOperator.getHospitalBasicInfo());
			if(orgBusinessList != null){
				for(OrgBusinessRelation obr : orgBusinessList){
					OrgOpenBusiness openBuniss = obr.getOrgOpenBusiness();
					businessNameList.add(openBuniss.getBusinessName());
				}
			}else{
				businessNameList.add("暂无服务");
			}
			//得到登录人的角色页面
			this.organModuleInfoList = this.orgBusinessManageService.getOrganRoleModuleList(organOperator);
		}
		/**
		 * 儿保预约列表(导医台用)
		 */
		else if(action.equals("getChildCareAppointmentListForReception")){
			if(userChildcareAppointmentInfo == null){
				userChildcareAppointmentInfo = new UserChildcareAppointmentInfo();
			}
			userChildcareAppointmentInfo.setIsMoney("N");
			//userChildcareAppointmentInfo.setStatus(ConstantManage.HASE_BOOKED_CLINIC_ORDER);
			//统计数量
			this.getChildCareCount(userChildcareAppointmentInfo,true);
			//用时间段分段显示
			this.userChildcareAppointmentInfoList = 
					this.orgBusinessManageService.getUserChildcareAppointmentInfoList(organOperator.getHospitalBasicInfo(), userChildcareAppointmentInfo,true);
			if(organChildcareOpenDoctor.getId() != null){
				this.organChildcareOpenDoctor = childCareChargeService.getOrganChildcareOpenDoctorById(organChildcareOpenDoctor.getId());
			}
//			if(userChildcareAppointmentInfoList != null && organChildcareOpenDoctor != null){//免费儿保订单，得到某个医生的订单
//				List<UserChildcareAppointmentInfo> freeOrderList = new ArrayList<>();
//				for(UserChildcareAppointmentInfo ucaOld : userChildcareAppointmentInfoList){
//					if(ucaOld.getOrganChildcareOpenResources().getId() == 
//							organChildcareOpenDoctor.getOrganChildcareOpenResources().getId()){
//						freeOrderList.add(ucaOld);
//					}
//				}
//				this.userChildcareAppointmentInfoList = freeOrderList;
//			}
			if(userChildcareAppointmentInfoList != null){
				Map<String,UserChildcareAppointmentInfoFo> userChildcareAppointmentInfoFoMap = 
						new LinkedHashMap<String,UserChildcareAppointmentInfoFo>(); 
				for(UserChildcareAppointmentInfo uiai : userChildcareAppointmentInfoList){
					UserChildcareAppointmentInfoFo uiaiFo = new UserChildcareAppointmentInfoFo();
					String key = uiai.getOrganChildcareOpenResources().getOpenDate() + "  " ;
					if("时间点".equals(uiai.getOrganChildcareOpenResources().getTimeDivisionNeed())){
						key += uiai.getOrganChildcareOpenResourcesDatail().getSegment();
						uiaiFo.setOpenStartTime(uiai.getOrganChildcareOpenResourcesDatail().getOpenStartTime());
					}else if("时间段".equals(uiai.getOrganChildcareOpenResources().getTimeDivisionNeed())){
						key += uiai.getOrganChildcareOpenResourcesDatail().getOpenStartTime() + "~" +
								uiai.getOrganChildcareOpenResourcesDatail().getOpenEndTime();
						uiaiFo.setOpenStartTime(uiai.getOrganChildcareOpenResourcesDatail().getOpenStartTime());
						uiaiFo.setOpenEndTime(uiai.getOrganChildcareOpenResourcesDatail().getOpenEndTime());
					}
					uiaiFo.setOpenDate(uiai.getOrganChildcareOpenResources().getOpenDate());
					userChildcareAppointmentInfoFoMap.put(key, uiaiFo);
				}
				for (Map.Entry<String,UserChildcareAppointmentInfoFo> entry : userChildcareAppointmentInfoFoMap.entrySet()) { 
					List<UserChildcareAppointmentInfo> uiaiList = new ArrayList<UserChildcareAppointmentInfo>(); 
					UserChildcareAppointmentInfoFo uiaiFo = entry.getValue();
					for(UserChildcareAppointmentInfo uiai : userChildcareAppointmentInfoList){
						String key = uiai.getOrganChildcareOpenResources().getOpenDate() + "  " ;
						if("时间点".equals(uiai.getOrganChildcareOpenResources().getTimeDivisionNeed())){
							key += uiai.getOrganChildcareOpenResourcesDatail().getSegment();
							if(key.equals(entry.getKey())){
								uiaiList.add(uiai);
							}
						}else if("时间段".equals(uiai.getOrganChildcareOpenResources().getTimeDivisionNeed())){
							key += uiai.getOrganChildcareOpenResourcesDatail().getOpenStartTime() + "~" +
									uiai.getOrganChildcareOpenResourcesDatail().getOpenEndTime();
							if(key.equals(entry.getKey())){
								uiaiList.add(uiai);
							}
						}
					}
					uiaiFo.setUserChildcareAppointmentInfoList(uiaiList);
					this.userChildcareAppointmentInfoFoShowList.add(uiaiFo);
				}
			}
		}
		/**
		 * 儿保现场登记
		 */
		else if(action.equals("saveOrUpdateUserChildcareAppointmentInfo")){
			Long id = userChildcareAppointmentInfo.getId();
			UserChildcareAppointmentInfo old = this.orgBusinessManageService.getUserChildcareAppointmentInfoById(id);
			if(old != null && old.getStatus().equals(userChildcareAppointmentInfo.getStatus())){
				this.mes="重复操作";
				return "fail";
			}else{
				old.setStatus(userChildcareAppointmentInfo.getStatus());
				this.orgBusinessManageService.saveOrUpdateUserChildcareAppointmentInfo(old);
			}
		}
		/**
		 * 儿保预约列表(大屏显示用)
		 */
		else if(action.equals("getChildCareAppointmentListForShow")){
			UserChildcareAppointmentInfo ucai = new UserChildcareAppointmentInfo();
			ucai.setIsMoney("N");
			ucai.setStatus(ConstantManage.HASE_REGISTER_VACCINE);
			this.userChildcareAppointmentInfoList = 
					this.orgBusinessManageService.getUserChildcareAppointmentInfoList(organOperator.getHospitalBasicInfo(), ucai,true);
		}
		/**
		 * 儿保列表(查询列表用)
		 */
		else if(action.equals("getChildCareAppointmentList")){
			//统计数量
			this.getChildCareCount(userChildcareAppointmentInfo,false);
			this.userChildcareAppointmentInfoList = 
					this.orgBusinessManageService.getUserChildcareAppointmentInfoList(organOperator.getHospitalBasicInfo(), userChildcareAppointmentInfo,false);
		}
		/**
		 * 计免列表(查询列表用)
		 */
		else if(action.equals("getUserInoculationAppointmentInfoList")){
			//统计数量
			this.getJiMianCount(false,userInoculationAppointmentInfo);
			this.userInoculationAppointmentInfoList = 
					this.orgBusinessManageService.getUserInoculationAppointmentInfoList(organOperator.getHospitalBasicInfo(), userInoculationAppointmentInfo,false);
		}
		/**
		 * 计免预约列表(导医台用)
		 */
		else if(action.equals("getUserInoculationAppointmentInfoListForReception")){
			if(userInoculationAppointmentInfo == null){
				userInoculationAppointmentInfo = new UserInoculationAppointmentInfo();
			}
			//统计数量
			this.getJiMianCount(true,userInoculationAppointmentInfo);
			//用时间段分段显示
			this.userInoculationAppointmentInfoList = this.orgBusinessManageService.
					getUserInoculationAppointmentInfoList(organOperator.getHospitalBasicInfo(), userInoculationAppointmentInfo,true);
			if(userInoculationAppointmentInfoList != null){
				Map<String,UserInoculationAppointmentInfoFo> userInoculationAppointmentInfoMap = 
						new LinkedHashMap<String,UserInoculationAppointmentInfoFo>(); 
				for(UserInoculationAppointmentInfo uiai : userInoculationAppointmentInfoList){
					UserInoculationAppointmentInfoFo uiaiFo = new UserInoculationAppointmentInfoFo();
					String key = uiai.getOrganInoculationOpenResources().getOpenDate() + "  " + 
							uiai.getOrganInoculationOpenResourcesDetail().getOpenStartTime() + "~" +
							uiai.getOrganInoculationOpenResourcesDetail().getOpenEndTime();
					uiaiFo.setOpenDate(uiai.getOrganInoculationOpenResources().getOpenDate());
					uiaiFo.setOpenStartTime(uiai.getOrganInoculationOpenResourcesDetail().getOpenStartTime());
					uiaiFo.setOpenEndTime(uiai.getOrganInoculationOpenResourcesDetail().getOpenEndTime());
					userInoculationAppointmentInfoMap.put(key, uiaiFo);
				}
				
				for (Map.Entry<String,UserInoculationAppointmentInfoFo> entry : userInoculationAppointmentInfoMap.entrySet()) { 
					List<UserInoculationAppointmentInfo> uiaiList = new ArrayList<UserInoculationAppointmentInfo>(); 
					UserInoculationAppointmentInfoFo uiaiFo = entry.getValue();
					for(UserInoculationAppointmentInfo uiai : userInoculationAppointmentInfoList){
						String key = uiai.getOrganInoculationOpenResources().getOpenDate() + "  " + 
								uiai.getOrganInoculationOpenResourcesDetail().getOpenStartTime() + "~" +
								uiai.getOrganInoculationOpenResourcesDetail().getOpenEndTime();
						if(key.equals(entry.getKey())){
							uiaiList.add(uiai);
						}
					}
					uiaiFo.setUserInoculationAppointmentInfoList(uiaiList);
					this.userInoculationAppointmentInfoShowList.add(uiaiFo);
				}
			}
			
		}
		/**
		 * 计免现场登记
		 */
		else if(action.equals("saveOrUpdateUserInoculationAppointmentInfo")){
			Long id = userInoculationAppointmentInfo.getId();
			UserInoculationAppointmentInfo old = this.orgBusinessManageService.getUserInoculationAppointmentInfoById(id);
			//判断是否已操作过
			if(userInoculationAppointmentInfo.getStatus().equals(old.getStatus())){//想要修改数据和已存在的数据一致，说明是重复操作了
				this.mes="重复操作";
				return "fail";
			}else{
				/*
				 *1.当计免对象的id不为null是为修改订单状态 
				 *2.根据订单id判断该订单是否为快医公司计免订单
				 *3.如果是快医公司订单，获取计免对象的订单状态，和快医计免的订单id，
				 *4.修改快医计免订单状态	
				 */
				boolean isKyInnoculationOrder = false;
				String result_return = "";
				if(userInoculationAppointmentInfo.getStatus().equals("已登记")){
					
					Object uniqueResult = orgBusinessManageService.isKyInnoculationOrder(id);
					
					if(uniqueResult!=null) {
						isKyInnoculationOrder = true;
						String kyId = uniqueResult.toString();
						try {
							result_return = HttpClientUtil.inocalutionOrderEdit(kyId, 3+"");
						} catch (Exception e) {
							System.out.println("巴蜀快医计免接口调用失败原因："+ e.toString());
						}finally{
							if(!result_return.equals("true")){
								mes="巴蜀快医计免接口调用失败";
								System.out.println("巴蜀快医计免接口调用失败原因："+ result_return);
								return "fail";
							}
						}
					}
				}
				
				if((!isKyInnoculationOrder) //不是快医公司计免订单
						|| (isKyInnoculationOrder&&result_return.equals("true")))//是快医公司计免订单，且返回结果是true
				{
					old.setStatus(userInoculationAppointmentInfo.getStatus());
					old.setMeasuringTemperature(userInoculationAppointmentInfo.getMeasuringTemperature());
					old.setNextVaccinationDate(userInoculationAppointmentInfo.getNextVaccinationDate());
					old.setVaccineInfo(userInoculationAppointmentInfo.getVaccineInfo());
					this.orgBusinessManageService.saveOrUpdateUserInoculationAppointmentInfo(old);
				}
			}
		}
		/**
		 * 疫苗列表
		 */
		else if(action.equals("getVaccineInfoList")){
			this.vaccineInfoList = this.orgBusinessManageService.getVaccineInfoList(userInoculationAppointmentInfo);
		}
		/**
		 * 保存或更新机构儿保医生信息
		 */
		else if(action.equals("saveOrUpdateOrganChildcareOpenDoctor")){
			OrganChildcareOpenDoctor old = childCareChargeService.getOrganChildcareOpenDoctorById(organChildcareOpenDoctor.getId());
			DoctorInfo doctorInfo = old.getDoctorInfo();
//			UserChildcareAppointmentInfo uca = new UserChildcareAppointmentInfo();
//			uca.setIsMoney("N");
//			uca.setStatus(ConstantManage.HASE_BOOKED_CLINIC_ORDER);
//			this.userChildcareAppointmentInfoList = this.orgBusinessManageService.
//					getUserChildcareAppointmentInfoList(organOperator.getHospitalBasicInfo(), uca,true);
			if("已上班".equals(organChildcareOpenDoctor.getWorkStatus())){
				old.setStartTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			}else if("已下班".equals(organChildcareOpenDoctor.getWorkStatus())){
				String endDate = old.getOrganChildcareOpenResources().getOpenDate()+" "+
						old.getOrganChildcareOpenResources().getOpenEndTime()+":00";
				if (DateManage.isCompareDates(DateManage.getStrToDateTime(endDate), new Date())) {
					this.mes="未到下班时间："+endDate;
					return "fail";
				}
				old.setEndTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				//添加医生收入信息
				DoctorMoneyRecord dmr = this.childCareChargeService.
						getDoctorMoneyRecordBySomething(null, doctorInfo, DateManage.getDateStr("yyyy-MM-dd"));
				//计算保底薪酬
				double baodi = Double.valueOf(dmr.getMoney());
				if(baodi > 0d){
					DoctorAccount doctorAccount_baodi = new DoctorAccount();
					doctorAccount_baodi.setAccountDesc("儿保服务保底薪酬");
					doctorAccount_baodi.setAmount(baodi);
					doctorAccount_baodi.setDoctorId(doctorInfo.getId());
					doctorAccount_baodi.setType("+");
					doctorAccount_baodi.setUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
					accountBo.saveDoctorAccount(doctorAccount_baodi);
					//医生余额
					double doctorBalance = doctorInfo.getDoctorBalance() + baodi;
					doctorInfo.setDoctorBalance(doctorBalance);
					//更新医生余额信息
					doctorInfoBo.update(doctorInfo);
				}
			}
			old.setWorkStatus(organChildcareOpenDoctor.getWorkStatus());
			old.setOperateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			this.childCareChargeService.saveOrUpdateOrganChildcareOpenDoctor(old);
		}
		return "success";
	}
	/**
	 * 计免列表统计
	 * @param allList
	 */
	private void getJiMianCount(Boolean flag,UserInoculationAppointmentInfo uiaiInfo){
		UserInoculationAppointmentInfo ui = new UserInoculationAppointmentInfo();
		if(uiaiInfo!=null){
			BeanUtils.copyProperties(uiaiInfo, ui);
			ui.setStatus(null);
		}
		//得到机构所有订单
		List<UserInoculationAppointmentInfo> allList = this.orgBusinessManageService.
				getUserInoculationAppointmentInfoList(organOperator.getHospitalBasicInfo(), ui,flag);
		// 已预月人数
		int bookingSum = 0;
		// 已预检人数
		int havePreSum = 0;
		 //已登记人数
		int registeredSum = 0;
		 // 已完成人数
		int finishedSum = 0;
		// 已会面人数
		int meetingSum = 0;
		if(allList != null){
			for(UserInoculationAppointmentInfo uiai : allList){
				//统计各种状态的订单数量
				if(ConstantManage.HASE_BOOKED_VACCINE.equals(uiai.getStatus())){
					bookingSum ++;
				}
				if(ConstantManage.HASE_PREVIEW_VACCINE.equals(uiai.getStatus())){
					havePreSum ++;
				}
				if(ConstantManage.HASE_REGISTER_VACCINE.equals(uiai.getStatus())){
					registeredSum ++;
				}
				if(ConstantManage.HASE_FINISHED_CLINIC_ORDER.equals(uiai.getStatus())){
					finishedSum ++;
				}
				if(ConstantManage.HASE_MEET_CLINIC_ORDER.equals(uiai.getStatus())){
					meetingSum ++;
				}
			}
		}
		this.orderCountInfo.setAllSum(bookingSum+havePreSum+registeredSum+finishedSum+meetingSum);
		this.orderCountInfo.setBookingSum(bookingSum);
		this.orderCountInfo.setHavePreSum(havePreSum);
		this.orderCountInfo.setRegisteredSum(registeredSum);
		this.orderCountInfo.setFinishedSum(finishedSum);
		this.orderCountInfo.setMeetingSum(meetingSum);
	}
	/**
	 * 儿保列表统计
	 * @param allList
	 */
	private void getChildCareCount(UserChildcareAppointmentInfo ucai,Boolean flag){
		UserChildcareAppointmentInfo uc = new UserChildcareAppointmentInfo();
		if(ucai!=null){
			BeanUtils.copyProperties(ucai, uc);
			uc.setStatus(null);
		}
		//得到机构所有订单
		List<UserChildcareAppointmentInfo> allList = this.orgBusinessManageService.
				getUserChildcareAppointmentInfoList(organOperator.getHospitalBasicInfo(), uc, flag);
		// 已预月人数
		int bookingSum = 0;
		// 已预检人数
		int havePreSum = 0;
		 //已登记人数
		int registeredSum = 0;
		 // 已完成人数
		int finishedSum = 0;
		// 已会面人数
		int meetingSum = 0;
		if(allList != null){
			for(UserChildcareAppointmentInfo uiai : allList){
				//统计各种状态的订单数量
				if(ConstantManage.HASE_BOOKED_VACCINE.equals(uiai.getStatus())){
					bookingSum ++;
				}
				if(ConstantManage.HASE_PREVIEW_VACCINE.equals(uiai.getStatus())){
					havePreSum ++;
				}
				if(ConstantManage.HASE_REGISTER_VACCINE.equals(uiai.getStatus())){
					registeredSum ++;
				}
				if(ConstantManage.HASE_FINISHED_CLINIC_ORDER.equals(uiai.getStatus())){
					finishedSum ++;
				}
				if(ConstantManage.HASE_MEET_CLINIC_ORDER.equals(uiai.getStatus())){
					meetingSum ++;
				}
			}
		}
		this.orderCountInfo.setAllSum(bookingSum+havePreSum+registeredSum+finishedSum+meetingSum);
		this.orderCountInfo.setBookingSum(bookingSum);
		this.orderCountInfo.setHavePreSum(havePreSum);
		this.orderCountInfo.setRegisteredSum(registeredSum);
		this.orderCountInfo.setFinishedSum(finishedSum);
		this.orderCountInfo.setMeetingSum(meetingSum);
	}
	@Override
	public String getMes() {
		return mes;
	}
	@Override
	public void setMes(String mes) {
		this.mes = mes;
	}
	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}
	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}
	public OrganOperator getOrganOperator() {
		return organOperator;
	}
	public void setOrganOperator(OrganOperator organOperator) {
		this.organOperator = organOperator;
	}
	public UserChildcareAppointmentInfo getUserChildcareAppointmentInfo() {
		return userChildcareAppointmentInfo;
	}
	public void setUserChildcareAppointmentInfo(
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		this.userChildcareAppointmentInfo = userChildcareAppointmentInfo;
	}
	public List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList() {
		return userChildcareAppointmentInfoList;
	}
	public void setUserChildcareAppointmentInfoList(
			List<UserChildcareAppointmentInfo> userChildcareAppointmentInfoList) {
		this.userChildcareAppointmentInfoList = userChildcareAppointmentInfoList;
	}
	public List<String> getBusinessNameList() {
		return businessNameList;
	}
	public void setBusinessNameList(List<String> businessNameList) {
		this.businessNameList = businessNameList;
	}
	public UserInoculationAppointmentInfo getUserInoculationAppointmentInfo() {
		return userInoculationAppointmentInfo;
	}
	public void setUserInoculationAppointmentInfo(
			UserInoculationAppointmentInfo userInoculationAppointmentInfo) {
		this.userInoculationAppointmentInfo = userInoculationAppointmentInfo;
	}
	public List<UserInoculationAppointmentInfo> getUserInoculationAppointmentInfoList() {
		return userInoculationAppointmentInfoList;
	}
	public void setUserInoculationAppointmentInfoList(
			List<UserInoculationAppointmentInfo> userInoculationAppointmentInfoList) {
		this.userInoculationAppointmentInfoList = userInoculationAppointmentInfoList;
	}
	public List<VaccineInfo> getVaccineInfoList() {
		return vaccineInfoList;
	}
	public void setVaccineInfoList(List<VaccineInfo> vaccineInfoList) {
		this.vaccineInfoList = vaccineInfoList;
	}
	public List<UserInoculationAppointmentInfoFo> getUserInoculationAppointmentInfoShowList() {
		return userInoculationAppointmentInfoShowList;
	}
	public void setUserInoculationAppointmentInfoShowList(
			List<UserInoculationAppointmentInfoFo> userInoculationAppointmentInfoShowList) {
		this.userInoculationAppointmentInfoShowList = userInoculationAppointmentInfoShowList;
	}
	public List<OrganModuleInfo> getOrganModuleInfoList() {
		return organModuleInfoList;
	}
	public void setOrganModuleInfoList(List<OrganModuleInfo> organModuleInfoList) {
		this.organModuleInfoList = organModuleInfoList;
	}
	public List<UserChildcareAppointmentInfoFo> getUserChildcareAppointmentInfoFoShowList() {
		return userChildcareAppointmentInfoFoShowList;
	}
	public void setUserChildcareAppointmentInfoFoShowList(
			List<UserChildcareAppointmentInfoFo> userChildcareAppointmentInfoFoShowList) {
		this.userChildcareAppointmentInfoFoShowList = userChildcareAppointmentInfoFoShowList;
	}
	public OrganChildcareOpenDoctor getOrganChildcareOpenDoctor() {
		return organChildcareOpenDoctor;
	}
	public void setOrganChildcareOpenDoctor(
			OrganChildcareOpenDoctor organChildcareOpenDoctor) {
		this.organChildcareOpenDoctor = organChildcareOpenDoctor;
	}
	public OrderCountInfo getOrderCountInfo() {
		return orderCountInfo;
	}
	public void setOrderCountInfo(OrderCountInfo orderCountInfo) {
		this.orderCountInfo = orderCountInfo;
	}
}
