package com.kybaby.newbussiness.doctorclinic.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorClinicTimeSegment;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePracticeOrgInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.doctorclinic.fo.DoctorInfoFo;
import com.kybaby.newbussiness.doctorclinic.fo.DoctorMorePracticeFo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.opensymphony.xwork2.ActionContext;
import com.sun.org.apache.bcel.internal.generic.DMUL;

public class DoctorClinicAction extends NewBaseAction{
	private static Logger logger = Logger.getLogger(DoctorClinicAction.class);   
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 医生信息
	 */
	private DoctorInfo doctorInfo;
	/**
	 * 用户信息
	 */
	private UserInfo userInfo;
	/**
	 * 医生门诊及多点执业设置信息
	 */
	private DoctorMorePractice doctorMorePractice;
	/**
	 * 医生信息集合
	 */
	private List<DoctorInfo>  doctorInfoList = new ArrayList<DoctorInfo>();
	/**
	 * 医生门诊及多点执业设置信息(用户端显示处理)
	 */
	private List<DoctorMorePracticeFo> doctorMorePracticeFoList = new ArrayList<DoctorMorePracticeFo>();
	/**
	 * 门诊医生信息列表
	 */
	private List<DoctorInfoFo> doctorInfoFoList = new ArrayList<DoctorInfoFo>();
	/**
	 * 医生信息(页面显示用)
	 */
	private DoctorInfoFo doctorInfoFo;
	/**
	 * 当前日期
	 */
	private String currentTime;
	/**
	 * 当前星期几
	 */
	private String currentWeekDate;
	/**
	 * 门诊医生列表跳转页面标示（byOrg:通过机构看医生）
	 */
	private String toClinicListFlag;
	
	public String execute(){
		try {
			System.out.println("============userClinicAction execute==============");
//			Long userId = (Long) ActionContext.getContext().getSession().get("userId");
			Long userId = this.userInfoBo.getLoginUserInfoId();
			System.out.println("====ActionContext.getContext().getSession().get====="+userId);
			if(userId==null){
				if(userInfo != null && userInfo.getId() != null){
					userId = userInfo.getId();
					System.out.println("====本地存储userInfo.getId();====="+userId);
				}else{
					mes="请登录";
					return "fail";
				}
			}
			/**
			 * 得到系统当前时间
			 */
			if(action.equals("getCurrentTime")){
				this.currentTime = DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
				this.currentWeekDate = DateManage.getWeekByZhou(new Date());
			}
			
			/**
			 * 得到医生设置的门诊信息列表
			 */
			else if(action.equals("getDoctorMorePracticeList")){
				System.out.println("getDoctorMorePracticeList===="+doctorInfo.getId());
				this.doctorInfo = this.doctorInfoBo.getDoctorInfoByDoctorId(doctorInfo.getId());
				this.userInfo = this.userInfoBo.getUserById(userId);
				List<DoctorMorePractice> doctorMorePracticeList = null;
//				if("byOrg".equals(this.toClinicListFlag)){
					doctorMorePracticeList = this.doctorClinicService.getDoctorMorePracticeList(doctorMorePractice, doctorInfo);
//				}else{
//					doctorMorePracticeList = this.doctorClinicService.getDoctorMorePracticeList(null, doctorInfo);
//				}
				if(doctorMorePracticeList != null){
					UserType exitUserType = this.getUserTypeByCurrentUser(userId);
					Map<String,List<DoctorMorePractice>> map = new HashMap<String,List<DoctorMorePractice>>();
					for(DoctorMorePractice dmp : doctorMorePracticeList){
						//需要看身份的对身份进行匹配判断，只有身份匹配的才能约
						if(StringUtils.isNotEmpty(dmp.getUserTypeIds())){
							String[] userTypeIds = dmp.getUserTypeIds().split(",");
							if(exitUserType != null && Arrays.asList(userTypeIds).contains(exitUserType.getId()+"")){
								map.put(dmp.getClinicDate(), null);
							}
						}else{
							map.put(dmp.getClinicDate(), null);
						}
					}
					for (Map.Entry<String,List<DoctorMorePractice>> entry : map.entrySet()) {  
						DoctorMorePracticeFo fo = new DoctorMorePracticeFo();
						List<DoctorMorePractice> newList = new ArrayList<DoctorMorePractice>();
						fo.setClinicDate(entry.getKey());
						for(DoctorMorePractice dmp : doctorMorePracticeList){
							if(dmp.getClinicDate().equals(entry.getKey())){
								//得到医生可被预约的时间
								List doctorClinicTimeSegmentList = this.getCanUseClinicTime(dmp,doctorInfo);
								dmp.setDoctorClinicTimeSegmentList(doctorClinicTimeSegmentList);
								//有加号的需要判断加号剩余数
								OrderInfoClinic orderClinic = new OrderInfoClinic();
								orderClinic.setClinicOrgType(ConstantManage.CLINIC_ORG_TYPE_1);
								orderClinic.setIsPlus("Y");
								orderClinic.setDoctorInfo(doctorInfo);
								orderClinic.setAppointmentDate(fo.getClinicDate());
								Long hadAddClinic = this.getCanPlusNum(orderClinic);
								Long doctorAddNum = dmp.getIsAddClinic()==null?0L:Long.valueOf(dmp.getIsAddClinic());
								Long canAdd = doctorAddNum-hadAddClinic;
								dmp.setIsAddClinic(canAdd.toString());
								newList.add(dmp);
								fo.setDoctorMorePracticeList(newList);
							}
						}
						this.doctorMorePracticeFoList.add(fo);
					}
				}
				System.out.println("getDoctorMorePracticeList   list ===" + doctorMorePracticeFoList.size());
			}
			/**
			 * 得到推荐医生信息列表（首页用）
			 */
			else if(action.equals("recommendedDoctorList")){
				this.doctorInfoList = this.doctorClinicService.getDoctorInfoListRecommended();
			}
			/**
			 * 得到门诊医生信息列表
			 */
			else if(action.equals("getClinicDoctorInfo")){
				List<DoctorInfo> doctorList = null;
				if("byOrg".equals(this.toClinicListFlag)){
					//HospitalBasicInfo hospitalBasicInfo = this.organManagerService.getHospitalBasicInfoById(doctorInfo.getHospitalId());
					HospitalBasicInfo hospitalBasicInfo = new HospitalBasicInfo();
					hospitalBasicInfo.setId(doctorInfo.getHospitalId());
					//doctorList = this.openBusinessManagerService.getClinicDoctorInfoListByOrg(doctorInfo,hospitalBasicInfo);
					//通过机构进入的看用户身份，不同的身份对应的医生不一样
					doctorList = this.getDoctorInfoListByCurrentUserType(userId, hospitalBasicInfo);
				}else{
					doctorList = this.doctorClinicService.getClinicDoctorInfoList(doctorInfo);
				}
				if(doctorList != null){
					Map<Long,DoctorInfo> doctorMap = new HashMap<Long,DoctorInfo>();
					for(DoctorInfo doctor : doctorList){
						doctorMap.put(doctor.getId(), doctor);
					}
					for (Map.Entry<Long,DoctorInfo> entry : doctorMap.entrySet()) {  
					//for(DoctorInfo doctor : doctorList){
						DoctorInfo doctor = entry.getValue();
						doctor.setDoctorPhone("");
						DoctorInfoFo doctorFo = new DoctorInfoFo();
//						List<DoctorMorePractice> doctorMorePracticeList = 
//								this.doctorClinicService.getDoctorMorePracticeList(null, doctorInfo);
//						if(doctorMorePracticeList != null){
//							for(DoctorMorePractice dmp : doctorMorePracticeList){
//								if(ConstantManage.CLINIC_ORG_TYPE_1.equals(dmp.getClinicOrgType())){
//									
//								}
//							}
//						}
						doctorFo.setDoctorInfo(doctor);
						//医生专业方向
						List<String> majorNameList=majorBo.getMajorNameListByIdStr(doctor.getMajorId());
						doctorFo.setMajorNameList(majorNameList);
						//医生擅长领域
						List<String> goodFieldNameList=majorBo.getGoodFieldNameListByIdStr(doctor.getGoodAtField());
						doctorFo.setGoodFieldNameList(goodFieldNameList);
						
						//医院等级
						HospitalBasicInfo hosp = this.doctorClinicService.getHospitalBasicInfoById(doctor.getHospitalId());
						doctorFo.setHospitalLeval("");
						if(hosp != null){
							doctorFo.setHospitalLeval(hosp.getHospitalLevel()==null?"":hosp.getHospitalLevel());
						}
						//医生配置的可预约时间信息
						List<String> clinicBookingDateList = this.getBookingDate(doctor,userId);
						doctorFo.setClinicBookingDateList(clinicBookingDateList);
						this.doctorInfoFoList.add(doctorFo);
					}
					//对门诊医生列表进行排序（有预约时间的放在前面）
					if(doctorInfo == null){
						   Collections.sort(doctorInfoFoList, new Comparator<DoctorInfoFo>() {  
					            public int compare(DoctorInfoFo arg0, DoctorInfoFo arg1) {  
					                int hits0 = arg0.getClinicBookingDateList()==null?0:arg0.getClinicBookingDateList().size();  
					                int hits1 = arg1.getClinicBookingDateList()==null?0:arg1.getClinicBookingDateList().size();    
					                if (hits1 < hits0) {  
					                    return -1;  
					                } else if (hits1 == hits0) {  
					                    return 0;  
					                } else {  
					                    return 1;  
					                }  
					            }  
					        }); 
					}
					if(doctorInfo != null){
						this.doctorInfoFo = doctorInfoFoList.get(0);
					}
				}
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * 得到医生门诊预约时间集合（门诊医生列表页面显示用）
	 * @param doctorInfo 医生信息
	 * @return 门诊时间显示信息集合
	 */
	private List<String> getBookingDate(DoctorInfo doctorInfo,Long userId){
		List<String> list = new ArrayList<String>();
		List<DoctorMorePractice> doctorMorePracticeList = null;
		UserType exitUserType = this.getUserTypeByCurrentUser(userId);
		if("byOrg".equals(this.toClinicListFlag)){
			doctorMorePracticeList = this.doctorClinicService.getDoctorMorePracticeList(doctorMorePractice, doctorInfo);
		}else{
			doctorMorePracticeList = this.doctorClinicService.getDoctorMorePracticeList(null, doctorInfo);
		}
		if(doctorMorePracticeList != null){
			for(DoctorMorePractice dmp : doctorMorePracticeList){
				int count = list.size();
				if(count == 2) break;
				String clinicDate = dmp.getClinicDate();
				String nowDate = DateManage.getDateStr("yyyy-MM-dd");
				if(clinicDate.equals(nowDate)){
					nowDate = DateManage.getDateStr("MM月dd日");
					if(StringUtils.isNotEmpty(dmp.getUserTypeIds())){
						String[] userTypeIds = dmp.getUserTypeIds().split(",");
						if(exitUserType != null && Arrays.asList(userTypeIds).contains(exitUserType.getId()+"")){
							list.add("今天"+nowDate+"可约");
						}
					}else{
						list.add("今天"+nowDate+"可约");
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
								list.add("明天"+dateStr+"可约");
							}
						}else{
							list.add("明天"+dateStr+"可约");
						}
					}else if(days == 2L){
						if(StringUtils.isNotEmpty(dmp.getUserTypeIds())){
							String[] userTypeIds = dmp.getUserTypeIds().split(",");
							if(exitUserType != null && Arrays.asList(userTypeIds).contains(exitUserType.getId()+"")){
								list.add("后天"+dateStr+"可约");
							}
						}else{
							list.add("后天"+dateStr+"可约");
						}
					}else{
						String week = DateManage.getWeekByZhou(bookingDate);
						if(StringUtils.isNotEmpty(dmp.getUserTypeIds())){
							String[] userTypeIds = dmp.getUserTypeIds().split(",");
							if(exitUserType != null && Arrays.asList(userTypeIds).contains(exitUserType.getId()+"")){
								list.add(week+dateStr+"可约");
							}
						}else{
							list.add(week+dateStr+"可约");
						}
					}
				}
			}
		}
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	/**
	 * 得到医生可被预约的时间
	 * @param doctorInfo
	 */
	private List<DoctorClinicTimeSegment> getCanUseClinicTime(DoctorMorePractice dmp,DoctorInfo doctorInfo){
		
		String clinicDate = dmp.getClinicDate();
		DoctorClinicTimeSegment doctorClinicTimeSegment = new DoctorClinicTimeSegment();
//		doctorClinicTimeSegment.setIsCanUse("Y");
		doctorClinicTimeSegment.setDoctorMorePractice(dmp);
		List<DoctorClinicTimeSegment> timeSegmentCanUsedList = 
				this.clinicOrderService.getDoctorClinicTimeSegmentList(doctorClinicTimeSegment, doctorInfo);
		if(timeSegmentCanUsedList == null) return null;
		for(DoctorClinicTimeSegment dcts : timeSegmentCanUsedList){
			//当前时间,用于判断可预约时间是否过期
			String nowDate = DateManage.getDateStr("yyyy-MM-ddHH:mm").replaceAll("-", "").replaceAll(":", "");
			if("Y".equals(dcts.getIsCanUse())){//排除没被占用的过期预约时间
				String clinicTime = (clinicDate+dcts.getSegment()).replaceAll("-", "").replaceAll(":", "");
				if(Long.valueOf(clinicTime) < Long.valueOf(nowDate)){ //门诊时间小于当前时间
					dcts.setIsCanUse("N");
				}
			}
		}
		return timeSegmentCanUsedList;
	}
	/**
	 * 用户预约门诊时得到有效加号订单数
	 * @param orderClinic
	 * @return
	 */
	private Long getCanPlusNum(OrderInfoClinic orderClinic){
		String flag = ConstantManage.HASE_BOOKED_CLINIC_ORDER+","+
				ConstantManage.HASE_FINISHED_CLINIC_ORDER+","+
				ConstantManage.HASE_MEET_CLINIC_ORDER+","+
				ConstantManage.HASE_EVALUATED_CLINIC_ORDER;
		long count=0;
		List<OrderInfoClinic> plusOrderList = this.clinicOrderService.getOrderInfoClinicList(orderClinic);
		if(plusOrderList == null){
			return 0L;
		}
		for(OrderInfoClinic oic : plusOrderList){
			String status = oic.getOrderStatus();
			if(flag.indexOf(status) > -1){
				count++;
			}
		}
		return count;
	}
	/**
	 * 根据当前用户身份得到匹配的医生列表
	 * @param hospitalBasicInfo
	 * @return
	 */
	private List<DoctorInfo> getDoctorInfoListByCurrentUserType(Long userId,HospitalBasicInfo hospitalBasicInfo){
		List<DoctorInfo> doctorListByUserType = new ArrayList<>();
		DoctorMorePractice doctorMorePractice = new DoctorMorePractice();
		DoctorMorePracticeOrgInfo dmpOrg = new DoctorMorePracticeOrgInfo();
		dmpOrg.setHospitalBasicInfo(hospitalBasicInfo);
		doctorMorePractice.setDoctorMorePracticeOrgInfo(dmpOrg);
		//doctorMorePractice.setUserTypeIds("配了用户类型的");
		List<DoctorMorePractice> dmpList = this.doctorClinicService.getDoctorMorePracticeList(doctorMorePractice, null);
		if(dmpList != null){
			UserType exitUserType = this.getUserTypeByCurrentUser(userId);
			for(DoctorMorePractice dmp : dmpList){
				if(StringUtils.isNotEmpty(dmp.getUserTypeIds())){
					String[] userTypeIds = dmp.getUserTypeIds().split(",");
					if(exitUserType != null && Arrays.asList(userTypeIds).contains(exitUserType.getId()+"")){
						doctorListByUserType.add(dmp.getDoctorInfo());
					}
				}else{
					doctorListByUserType.add(dmp.getDoctorInfo());
				}
			}
		}
		return doctorListByUserType;
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
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	public DoctorMorePractice getDoctorMorePractice() {
		return doctorMorePractice;
	}
	public void setDoctorMorePractice(DoctorMorePractice doctorMorePractice) {
		this.doctorMorePractice = doctorMorePractice;
	}
	public List<DoctorInfo> getDoctorInfoList() {
		return doctorInfoList;
	}
	public void setDoctorInfoList(List<DoctorInfo> doctorInfoList) {
		this.doctorInfoList = doctorInfoList;
	}
	public List<DoctorMorePracticeFo> getDoctorMorePracticeFoList() {
		return doctorMorePracticeFoList;
	}
	public void setDoctorMorePracticeFoList(
			List<DoctorMorePracticeFo> doctorMorePracticeFoList) {
		this.doctorMorePracticeFoList = doctorMorePracticeFoList;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public List<DoctorInfoFo> getDoctorInfoFoList() {
		return doctorInfoFoList;
	}
	public DoctorInfoFo getDoctorInfoFo() {
		return doctorInfoFo;
	}
	public String getCurrentTime() {
		return currentTime;
	}
	public void setToClinicListFlag(String toClinicListFlag) {
		this.toClinicListFlag = toClinicListFlag;
	}
	public String getCurrentWeekDate() {
		return currentWeekDate;
	}
}
