package com.kybaby.newbussiness.familydoctor.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.doctorclinic.fo.DoctorMorePracticeFo;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceMember;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceTeams;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProject;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.fo.OrganChildcareOpenResourcesFo;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetChildCareRecode;
import com.kybaby.util.CalculationMethod;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.SendSms;
import com.opensymphony.xwork2.ActionContext;

/**
 * 家庭医生儿保管理
 * @author lihao
 *
 */
public class FamilydoctorChildCareManage extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 家庭医生服务包
	 */
	private FdServicePackage fdServicePackage;
	/**
	 * 儿保资源
	 */
	private OrganChildcareOpenResources organChildcareOpenResources;
	/**
	 * 儿保资源开放主信息
	 */
	private List<OrganChildcareOpenResourcesFo> organChildcareOpenResourcesFoList = 
			new ArrayList<OrganChildcareOpenResourcesFo>();
	/**
	 * 儿保资源开放明细信息
	 */
	private List<OrganChildcareOpenResourcesDatail> organChildcareOpenResourcesDatailList = 
			new ArrayList<>();
	/**
	 * 家医儿保预约记录
	 */
	private	UserChildcareAppointmentInfo userChildcareAppointmentInfo;
	/**
	 * 医生信息
	 */
	private DoctorInfo doctorInfo;
	
	public String execute() throws Exception{
		Long userId = (Long) ActionContext.getContext().getSession().get("userId");
		if(userId==null){
			mes="请登录";
			return "fail";
		}
		/**
		 * 得到家庭医生儿保资源开放信息
		 */
		if(action.equals("getFdChildCareOpenResources")){
			//得到当前用户的家庭医生团队
			FdServicePackage oldPackage = this.familyDoctorBo.getFdServicePackageById(fdServicePackage.getId());
			List<FdServiceMember> memberList = null;
			Iterator<FdServiceTeams> teamsIt = oldPackage.getFdServiceTeamsSet().iterator();  
			while(teamsIt.hasNext()) { 
	        	memberList = this.fdServiceItemsService.getFdServiceMemberList(teamsIt.next(),null);
	        	break;
	        }
			if(memberList == null || memberList.isEmpty()){
				this.organChildcareOpenResourcesFoList = null;
				return "fail";
			}
			List<OrganChildcareOpenResources> organChildcareOpenResourcesList = this.openBusinessManagerService.
					getOrganChildcareOpenResourceslList(oldPackage.getHospitalBasicInfo(),null);
			List<OrganChildcareOpenResources> organChildcareOpenResourcesList_new = new ArrayList<>();
			if(organChildcareOpenResourcesList != null){
				//过滤出家庭医生团队成员做门诊的信息
				for(OrganChildcareOpenResources ocor : organChildcareOpenResourcesList){
					//得到坐诊医生
					String doctorIds = ocor.getDoctorIds();
					if(doctorIds != null && !"".equals(doctorIds)){
						DoctorInfo doctorInfo = this.doctorInfoBo.getDoctorInfoByDoctorId(Long.valueOf(doctorIds));
						for(FdServiceMember member : memberList){
							if(ocor.getHospitalBasicInfo().getId().longValue() == 
									oldPackage.getHospitalBasicInfo().getId().longValue() &&
									member.getDoctorInfo().getId().longValue() == doctorInfo.getId().longValue() && 
									member.getSkilNames() != null && member.getSkilNames().indexOf(ConstantManage.FD_erbao) > -1){
								ocor.setDoctorInfo(doctorInfo);
								organChildcareOpenResourcesList_new.add(ocor);
							}
						}
					}
				}
				if(organChildcareOpenResourcesList_new.isEmpty())
					return "fail";
				//组装门诊日期-医生关系
				Map<String,List<OrganChildcareOpenResources>> map = new HashMap<String,List<OrganChildcareOpenResources>>();
				for(OrganChildcareOpenResources dmp : organChildcareOpenResourcesList_new){
					map.put(dmp.getOpenDate(), null);
				}
				for (Map.Entry<String,List<OrganChildcareOpenResources>> entry : map.entrySet()) {  
					OrganChildcareOpenResourcesFo fo = new OrganChildcareOpenResourcesFo();
					List<OrganChildcareOpenResources> newList = new ArrayList<>();
					fo.setOpenDate(entry.getKey());
					for(OrganChildcareOpenResources dmp : organChildcareOpenResourcesList_new){
						if(dmp.getOpenDate().equals(entry.getKey())){
							newList.add(dmp);
							fo.setOrganChildcareOpenResourcesList(newList);
						}
					}
					this.organChildcareOpenResourcesFoList.add(fo);
				}
			}
		}
		/**
		 * 得到家庭医生儿保医生开放时间
		 */
		else if(action.equals("getFdChildCareOpenDatail")){
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
		 * 家庭医生儿保付订单管理
		 */
		if(action.equals("handleUserChildcareAppointmentInfo")){
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			Long detailId =	userChildcareAppointmentInfo.getOrganChildcareOpenResourcesDatail().getId();
			OrganChildcareOpenResourcesDatail oldDetail = 
					this.openBusinessManagerService.getOrganChildcareOpenResourcesDatailById(detailId);
			OrganChildcareOpenResources oldOpenResources = 
					this.openBusinessManagerService.getOrganChildcareOpenResourcesById
					(userChildcareAppointmentInfo.getOrganChildcareOpenResources().getId());
			//儿保医生薪酬记录
			DoctorMoneyRecord dmr = this.openBusinessManagerService.getDoctorMoneyRecordBySomething
					(null, doctorInfo, oldOpenResources.getOpenDate());
			if("时间点".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				if("N".equals(oldDetail.getIsCanUse())){
					this.mes = "所选时间已被预约";
					return "fail";
				}
			}else if("时间段".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				if("0".equals(oldDetail.getGeneralSurplusNum())){
					this.mes = "没有号源可约";
					return "fail";
				}
			}
			//限制一天预约3次
			long count = this.getChildcareAppointmentInfoLimited(userInfo);
			if(count >= 3L){
				mes = "您今天的儿保预约量已超过规定次数";
				return "fail";
			}
			String orderNum = String.valueOf(System.currentTimeMillis());
			userChildcareAppointmentInfo.setOrderNum(orderNum);
			userChildcareAppointmentInfo.setDoctorInfo(doctorInfo);
			userChildcareAppointmentInfo.setUserInfo(userInfo);
			userChildcareAppointmentInfo.setStatus(ConstantManage.HASE_BOOKED_CLINIC_ORDER);
			userChildcareAppointmentInfo.setTotalPrice(dmr.getMoneyPer());
			userChildcareAppointmentInfo.setOperationTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			userChildcareAppointmentInfo.setDiscountMoney("0");
			userChildcareAppointmentInfo.setPayMethod(ConstantManage.FD_PAY);
			//得到宝宝月龄
			String rightNow = CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
			String monthAge = String.valueOf(CalculationMethod.getMonthSpace(userInfo.getBirthday(), rightNow));
			userChildcareAppointmentInfo.setCurrentMonthAge(monthAge);
			userChildcareAppointmentInfo.setOrderType(ConstantManage.FD_PAY);
			Long id = this.childCareChargeService.saveOrUpdateUserChildcareAppointmentInfo(userChildcareAppointmentInfo);
			userChildcareAppointmentInfo.setId(id);
			//将儿保资源设置为已用,或将剩余号源减去1
			if("时间点".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				oldDetail.setIsCanUse("N");
			}else if("时间段".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				Long generalSurplusNum = Long.valueOf(oldDetail.getGeneralSurplusNum());
				generalSurplusNum = generalSurplusNum.longValue() - 1;
				oldDetail.setGreenChannelSurplusNum(String.valueOf(generalSurplusNum<=0L?0L:generalSurplusNum));
				oldDetail.setGeneralSurplusNum(String.valueOf(generalSurplusNum<=0L?0L:generalSurplusNum));
			}
			this.openBusinessManagerService.saveOrUpdateOrganChildcareOpenResourcesDatail(oldDetail);
			//发送短信通知
			SendSms ss = new SendSms();
			String contecnt = "亲爱的用户，您已预约"+oldOpenResources.getHospitalBasicInfo().getHospitalLname()+"的儿保服务，请于"+
					oldOpenResources.getOpenDate() + " ";
			if("时间点".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				contecnt +=  oldDetail.getSegment() + "之前";
			}else if("时间段".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				contecnt += oldDetail.getOpenStartTime() + "到" +	oldDetail.getOpenEndTime() + "之间";
			}
			contecnt += "前往，地址：" + oldOpenResources.getHospitalBasicInfo().getAddress()+"。详情请查‘我的预约’";	
			ss.sendInfo(userInfo.getPhone(), contecnt.toString());
			//购买成功后添加应做儿保项目记录信息
			this.addOrganSetChildCareRecode(this.openBusinessManagerService.getUserChildcareAppointmentInfoById(id),userInfo);
		}
		return SUCCESS;
	}
	
	/**
	 * 得到用户一天的儿保预约次数
	 * @param userInfo
	 * @return
	 */
	private long getChildcareAppointmentInfoLimited(UserInfo userInfo){
		String flagLimt3 = ConstantManage.HASE_BOOKED_CLINIC_ORDER+","+
				ConstantManage.HASE_FINISHED_CLINIC_ORDER+","+
				ConstantManage.HASE_MEET_CLINIC_ORDER+","+
				ConstantManage.USER_CANCLE_CLINIC_ORDER+","+
				ConstantManage.HASE_EVALUATED_CLINIC_ORDER;
		long count=0;
		UserChildcareAppointmentInfo ucai = new UserChildcareAppointmentInfo();
		ucai.setOperationTime(DateManage.getDateStr("yyyy-MM-dd"));
		List<UserChildcareAppointmentInfo> userChildcareAppointmentInfoList = this.openBusinessManagerService.
				getUserChildcareAppointmentInfoList(userInfo, null,ucai);
		if(userChildcareAppointmentInfoList != null){
			for(UserChildcareAppointmentInfo order : userChildcareAppointmentInfoList){
				if(flagLimt3.indexOf(order.getStatus()) > -1){
					count++;
				}
			}
		}
		return count;
	}
	/**
	 * 添加当前购买儿保的适合做项目
	 * @param oldOrder
	 */
	private void addOrganSetChildCareRecode(UserChildcareAppointmentInfo oldOrder,UserInfo userInfo){
		OrganSetChildCareRecode organSetChildCareRecode = new OrganSetChildCareRecode();
		organSetChildCareRecode.setChildcareProject(
				this.getChildcareProjectByAge(oldOrder.getOrganChildcareOpenResources().getHospitalBasicInfo(),userInfo)
				);
		organSetChildCareRecode.setUserChildcareAppointmentInfo(oldOrder);
		this.organSetMealService.saveOrUpdateOrganSetChildCareRecode(organSetChildCareRecode);
	}
	/**
	 * 得到当前用户适合的儿保项目内容
	 * @param hospitalBasicInfo
	 */
	private ChildcareProject getChildcareProjectByAge(HospitalBasicInfo hospitalBasicInfo,UserInfo userInfo){
		//得到用户月龄
		String babyBirthday=userInfo.getBirthday();
		String rightNow=CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
		String monthAge="";
		try {
			monthAge = String.valueOf(CalculationMethod.getMonthSpace(babyBirthday, rightNow));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ChildcareProject childcareProject = this.childCareChargeService.
				getChildcareProjectBySomething(null, hospitalBasicInfo, monthAge);
		return childcareProject;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public List<OrganChildcareOpenResourcesFo> getOrganChildcareOpenResourcesFoList() {
		return organChildcareOpenResourcesFoList;
	}

	public void setOrganChildcareOpenResourcesFoList(
			List<OrganChildcareOpenResourcesFo> organChildcareOpenResourcesFoList) {
		this.organChildcareOpenResourcesFoList = organChildcareOpenResourcesFoList;
	}

	public FdServicePackage getFdServicePackage() {
		return fdServicePackage;
	}
	public void setFdServicePackage(FdServicePackage fdServicePackage) {
		this.fdServicePackage = fdServicePackage;
	}
	public List<OrganChildcareOpenResourcesDatail> getOrganChildcareOpenResourcesDatailList() {
		return organChildcareOpenResourcesDatailList;
	}
	public void setOrganChildcareOpenResourcesDatailList(
			List<OrganChildcareOpenResourcesDatail> organChildcareOpenResourcesDatailList) {
		this.organChildcareOpenResourcesDatailList = organChildcareOpenResourcesDatailList;
	}
	public OrganChildcareOpenResources getOrganChildcareOpenResources() {
		return organChildcareOpenResources;
	}
	public void setOrganChildcareOpenResources(
			OrganChildcareOpenResources organChildcareOpenResources) {
		this.organChildcareOpenResources = organChildcareOpenResources;
	}
	public UserChildcareAppointmentInfo getUserChildcareAppointmentInfo() {
		return userChildcareAppointmentInfo;
	}
	public void setUserChildcareAppointmentInfo(
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		this.userChildcareAppointmentInfo = userChildcareAppointmentInfo;
	}
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
}
