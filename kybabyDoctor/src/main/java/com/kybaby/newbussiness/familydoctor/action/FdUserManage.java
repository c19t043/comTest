package com.kybaby.newbussiness.familydoctor.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserConsultDoctor;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.familydoctor.domain.ConsultDoctorInfo;
import com.kybaby.newbussiness.familydoctor.domain.ConsultOrderInfo;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceMember;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceTeams;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ClinicMedicalRecords;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;
import com.kybaby.newbussiness.ordermanager.domain.BabyBasicData2;
import com.kybaby.util.CalculationMethod;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.EncryptUtil;
import com.kybaby.util.MyMath;
import com.opensymphony.xwork2.ActionContext;

/**
 * 家庭医生签约用户管理
 * @author lihao
 *
 */
public class FdUserManage extends NewBaseAction{
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
	 * 就诊记录列表
	 */
	private List<ClinicMedicalRecords> clinicMedicalRecordsList = new ArrayList<>();
	/**
	 * 用户预约儿保信息列表
	 */
	private List<UserChildcareAppointmentInfo> userChildcareAppointmentInfoList = 
			new ArrayList<UserChildcareAppointmentInfo>();
	/**
	 * 用户接种疫苗预约信息列表
	 */
	private List<UserInoculationAppointmentInfo> userInoculationAppointmentInfoList = new ArrayList<UserInoculationAppointmentInfo>();
	private BabyBasicData2 babyBasicData;
	private String isEnd="";
	
	private List<UserConsultDoctor> someConsultList = new ArrayList<>();
	private List<String> tagNameList = new ArrayList<>();;
	private String userImg;
	private String doctorImg;
	private UserConsultDoctor consult;
	/**
	 * base64
	 */
	private String imagedata;
	private String uploadDir="../kybabyBG/admin/images/consultPicture";//保存上传文件的目录
	/**
	 * 医生回复
	 */
	private String doctorReply;
	/**
	 * 家庭医生团队列表
	 */
	private List<FdServiceTeams> fdServiceTeamsList = new ArrayList<>();
	
	@Override
	public String execute() {
		doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
		if(doctorInfo==null){
			mes="请登录";
			return "fail";
		}
		this.doctorInfo = this.doctorInfoBo.getDoctorInfoBoById(doctorInfo.getId());
		/**
		 *  得到用户就诊记录列表
		 */
		if(action.equals("getClinicMedicalRecordsListByUser")){
			this.clinicMedicalRecordsList = this.orgClinicService.getClinicMedicalRecordsList(null, userInfo);
		}
		/**
		 * 用户儿保预约列表
		 */
		else if(action.equals("getUserChildcareAppointmentInfoList")){
			UserChildcareAppointmentInfo userChildcareAppointmentInfo = new UserChildcareAppointmentInfo();
			userChildcareAppointmentInfo.setUserInfo(userInfo);
			userChildcareAppointmentInfo.setStatus(ConstantManage.HASE_MEET_CLINIC_ORDER);
			this.userChildcareAppointmentInfoList = 
					this.orgBusinessManageService.getUserChildcareAppointmentInfoList(null, userChildcareAppointmentInfo, false,null);
		}
		/**
		 * 得到当前用户的计免预约列表
		 */
		else if("getUserInoculationAppointmentInfoList".equals(action)) {
			UserInoculationAppointmentInfo userInoculationAppointmentInfo = new UserInoculationAppointmentInfo();
			userInoculationAppointmentInfo.setUserInfo(userInfo);
			userInoculationAppointmentInfo.setStatus(ConstantManage.HASE_FINISHED_CLINIC_ORDER);
			this.userInoculationAppointmentInfoList = this.orgBusinessManageService.
					getUserInoculationAppointmentInfoList(null, userInoculationAppointmentInfo, false);
		}
		/**
		 * 得到用户档案信息
		 */
		else if("getBabyBasicDataAtDoctor".equals(action)) {
			List<BabyBasicData2> babyList = orderManagerService.getBabyBasicData2ListByUserId(userInfo.getId());
			if(babyList != null){
				babyBasicData = babyList.get(0);
			}
		}
		/**
		 * 得到用户基本信息
		 */
		else if("getUserInfoAtDoctor".equals(action)) {
			this.userInfo = this.orderBo.getUserByUserId(userInfo.getId());
			if(userInfo != null){
				String nowDate = DateManage.getDateStr("yyyy-MM-dd");
				String birthday = userInfo.getBirthday();
				//把当前时间和生日相减，得到月龄的时长
				int space;
				try {
					space = CalculationMethod.getMonthSpace(nowDate,birthday);
					userInfo.setMoonSage(String.valueOf(space));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		/**
		 * 得到医生用户为结束回话记录
		 */
		else if("getConsultListAtDoctor".equals(action)) {
			consult=consultBo.getLastUserConsultDoctorSessionByType(userInfo.getId(),doctorInfo.getId(),"N");
			if(consult != null){//有未结束会话
				someConsultList = new ArrayList<UserConsultDoctor>();
				someConsultList = consultBo.getSomeUserConsultDoctor(consult.getLogId(), "N");
				if(someConsultList!=null){
					String tagIds = someConsultList.get(0).getSymptomTagIds();
					if(tagIds != null && !"".equals(tagIds)){
						String[] tagList = tagIds.split("::");
						if(tagList!=null){
							for(int i=0;i<tagList.length;i++){
								String tagName = consultBo.getSymptomTagNameById(Long.valueOf(tagList[i]));
								tagNameList.add(tagName);
							}
						}
					}
					Long doctId = someConsultList.get(0).getDoctorId();
					Long usId = someConsultList.get(0).getUserId();
					doctorImg = consultBo.getDoctorImgByDoctorId(doctId);
					userImg = consultBo.getUserImgByUserId(usId);
					mes="成功";
				}
			}else{
				this.mes = "会话结束";
			}
		}
		/**
		 * 医生对签约用户发起会话
		 */
		else if(action.equals("replaysFirst")){
			UserConsultDoctor userConsultDoctor = new UserConsultDoctor();
			Date date = new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			userConsultDoctor.setDoctorId(doctorInfo.getId());
			userConsultDoctor.setDoctorReply(doctorReply);
			if(this.imagedata != null && !"".equals(imagedata)){
				String current = DateManage.getDateStr("yyyyMMddhhmmss");
				String fileName=doctorInfo.getId() +"image" + current +".jpg";
				String fileAllName=uploadDir+"/"+fileName;
				String dir = ServletActionContext.getServletContext().getRealPath(fileAllName);
				try {
					if(EncryptUtil.uploadImage(imagedata,dir)){
						userConsultDoctor.setDoctorReplyImg(fileName);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			userConsultDoctor.setIsBefore("N");
			userConsultDoctor.setUserType("1");
			userConsultDoctor.setFdServicePackage(consult.getFdServicePackage());
			userConsultDoctor.setIsEnd("N");
			userConsultDoctor.setIsReply("Y");
			userConsultDoctor.setLogId(null);
			userConsultDoctor.setMsgType("1");
//			userConsultDoctor.setMsgType(msgType);
			userConsultDoctor.setSubmitTime(time);
			userConsultDoctor.setUserId(userInfo.getId());
			userConsultDoctor.setSymptomDescribe("");
			userConsultDoctor.setSymptomImage("");
			userConsultDoctor.setSymptomTagIds("");
			userConsultDoctor.setComments("");
			Long id = consultBo.save(userConsultDoctor);
			//得到第一次会话id作为logid
			UserConsultDoctor consultFirst=consultBo.getConsultByConsultId(id);
			consultFirst.setLogId(consultFirst.getId());
			consultBo.updateUserConsultDoctor(consultFirst);
			this.consult.setLogId(id);
			mes="成功";
			return "success";
		}
		//医生结束回话
		else if(action.equals("toEnd")){
			System.out.println("The Calculation is ToEnd....");
			DoctorInfo doctorInfo = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo!=null){
				long doctorId=doctorInfo.getId();
				List<UserConsultDoctor> allConsultion=consultBo.getAllConsulation(doctorId, consult.getLogId(), consult.getUserType());
				if(allConsultion!=null){
					//判断是否结束会话了，没结束，当前结束算钱
					if("N".equals(allConsultion.get(0).getIsEnd())){
						//结算薪资(收费咨询服务)
						if("2".equals(consult.getUserType())){
							//更新订单
							//修改订单信息为待评价
							ConsultOrderInfo consultOrderInfo = this.fdServiceMemberService.getConsultOrderInfoById(consult.getLogId());
							consultOrderInfo.setOrderStatus("待评价");
							this.fdServiceMemberService.saveOrUpdateConsultOrderInfo(consultOrderInfo);
							ConsultDoctorInfo cd = this.fdServiceMemberService.getConsultDoctorInfoById(null, doctorId);
							if(cd != null){
								//计算薪酬
								double baodi =cd.getConsultMoney()  * cd.getConsultCommission();
								baodi = MyMath.round(baodi, 2);
								DoctorAccount doctorAccount_baodi = new DoctorAccount();
								doctorAccount_baodi.setAccountDesc("在线咨询服务费");
								doctorAccount_baodi.setAmount(baodi);
								doctorAccount_baodi.setDoctorId(doctorInfo.getId());
								doctorAccount_baodi.setType("+");
								doctorAccount_baodi.setUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
								accountBo.saveDoctorAccount(doctorAccount_baodi);
								//医生余额
								double doctorBalance = doctorInfo.getDoctorBalance() + baodi;
								doctorBalance = MyMath.round(doctorBalance, 2);
								doctorInfo.setDoctorBalance(doctorBalance);
								//更新医生余额信息
								doctorInfoBo.update(doctorInfo);
							}
						}
					}
					
					for(int i =0;i<allConsultion.size();i++){
						consultBo.updateSomeConsultById(allConsultion.get(i));
					}
				}
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "success";
		}
		//得到医生所在的家庭医生专家团队列表
		else if(action.equals("getDoctorAtFdTeams")){
			DoctorInfo doctorInfo = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo == null){
				mes="未登录";
				return "success";
			}
			List<FdServiceMember> memberList = this.fdServiceMemberService.getAllFdServiceMember(doctorInfo);
			if(memberList != null){
				for(FdServiceMember fdMember : memberList){
					if(fdMember.getFdServiceTeams() != null && fdMember.getFdServiceTeams().getFdServicePackage() == null ){
						FdServiceTeams team = new FdServiceTeams();
						BeanUtils.copyProperties(fdMember.getFdServiceTeams(), team, 
								new String[]{"fdServicePackage","remark","doctorRoleIds","doctorRoleNames"});
						this.fdServiceTeamsList.add(team);
					}
				}
			}
			mes="操作成功";
		}
		return SUCCESS;
	}

	@Override
	public String getMes() {
		return mes;
	}

	@Override
	public void setMes(String mes) {
		this.mes = mes;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<ClinicMedicalRecords> getClinicMedicalRecordsList() {
		return clinicMedicalRecordsList;
	}

	public void setClinicMedicalRecordsList(
			List<ClinicMedicalRecords> clinicMedicalRecordsList) {
		this.clinicMedicalRecordsList = clinicMedicalRecordsList;
	}

	public List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList() {
		return userChildcareAppointmentInfoList;
	}

	public void setUserChildcareAppointmentInfoList(
			List<UserChildcareAppointmentInfo> userChildcareAppointmentInfoList) {
		this.userChildcareAppointmentInfoList = userChildcareAppointmentInfoList;
	}

	public List<UserInoculationAppointmentInfo> getUserInoculationAppointmentInfoList() {
		return userInoculationAppointmentInfoList;
	}

	public void setUserInoculationAppointmentInfoList(
			List<UserInoculationAppointmentInfo> userInoculationAppointmentInfoList) {
		this.userInoculationAppointmentInfoList = userInoculationAppointmentInfoList;
	}

	public BabyBasicData2 getBabyBasicData() {
		return babyBasicData;
	}

	public void setBabyBasicData(BabyBasicData2 babyBasicData) {
		this.babyBasicData = babyBasicData;
	}

	public String getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}

	public List<UserConsultDoctor> getSomeConsultList() {
		return someConsultList;
	}

	public List<String> getTagNameList() {
		return tagNameList;
	}

	public String getUserImg() {
		return userImg;
	}

	public String getDoctorImg() {
		return doctorImg;
	}

	public UserConsultDoctor getConsult() {
		return consult;
	}

	public void setConsult(UserConsultDoctor consult) {
		this.consult = consult;
	}

	public void setImagedata(String imagedata) {
		this.imagedata = imagedata;
	}

	public void setDoctorReply(String doctorReply) {
		this.doctorReply = doctorReply;
	}

	public List<FdServiceTeams> getFdServiceTeamsList() {
		return fdServiceTeamsList;
	}
	
	
}
