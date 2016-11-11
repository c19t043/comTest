package com.kybaby.newbussiness.userconsult.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Evaluate;
import com.kybaby.domain.UserConsultDoctor;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.commondiseaseanddrug.domain.CommonDisease;
import com.kybaby.newbussiness.familydoctor.domain.DoctorWorkTime;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceMember;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceTeams;
import com.kybaby.newbussiness.userconsult.domain.ConsultBabyInfo;
import com.kybaby.newbussiness.userconsult.domain.ConsultBabySet;
import com.kybaby.newbussiness.userconsult.domain.ConsultDoctorInfo;
import com.kybaby.newbussiness.userconsult.domain.ConsultOrderInfo;
import com.kybaby.newbussiness.userconsult.fo.ConsultHistoryFo;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.EncryptUtil;
import com.kybaby.util.MyMath;
import com.kybaby.util.SendSms;
/**
 * 咨询会话管理
 * @author lihao
 *
 */
public class ConsultDialogAction extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	private String dir="../kybabyBG/admin/images/consultPicture";
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 前端传递的图片数据
	 */
	private String imagedata;
	/**
	 * 会话记录id（也就是orderId）
	 */
	private Long orderId;
	/**
	 * 家庭医生服务包
	 */
	private Long fdPackageId;
	/**
	 * 输入的症状描述
	 */
	private String description;
	/**
	 * 聊天关联宝宝id
	 */
	private Long babyId;
	/**
	 * 咨询用户类型（0:普通用户咨询；1：家庭医生签约用户咨询；2:收费咨询服务）
	 */
	private String userType;
	/**
	 * 用户咨询医生的历史记录
	 */
	private List<UserConsultDoctor> histConsultList=new ArrayList<UserConsultDoctor>();
	/**
	 * 医生信息
	 */
	private DoctorInfo doctorInfo;
	/**
	 * 用户的头像
	 */
	private String userImage;
	/**
	 * 咨询图片
	 */
	private String symptomImage;
	/**
	 * 用户咨询记录列表
	 */
	private Evaluate evaluate;
	/**
	 * 病情印象
	 */
	private List<CommonDisease> commonDiseaseList;
	/**
	 * 家庭医生团队列表
	 */
	private List<FdServiceTeams> fdServiceTeamsList = new ArrayList<>();
	
	public Evaluate getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}
	
	private List<ConsultHistoryFo>  consultHistoryFoList = new ArrayList<>();
	
	public String execute() throws Exception {
		Long userId = this.userInfoBo.getLoginUserInfoId();
		if(userId == null){
			mes="请登录";
			return "fail";
		}
		//发起会话
		if(action.equals("initDialog")){
			//判断会话是否已经结束
			List<UserConsultDoctor> histConsultListEnd = 
					userConsultDoctorBo.getConsultListBySomething(userId, null,orderId, ConstantManage.CONSULT_USER_TYPE_CHARGE, "Y","", "desc",fdPackageId);
			if(histConsultListEnd != null){
				this.mes = "已结束";
				return "fail";
			}
			String fileName="";
			if(StringUtils.isNotBlank(imagedata)){
				fileName= userId+"Uimg" + DateManage.getDateStr("yyyyMMddhhmmss") +".jpg";
				String fileAllName=dir+"/"+fileName;
				dir = ServletActionContext.getServletContext().getRealPath(fileAllName);
				if(!EncryptUtil.uploadImage(imagedata,dir)){
					fileName="";
				}
			}
			ConsultOrderInfo consultOrderInfo = this.consultDoctorInfoService.getConsultOrderInfoById(orderId);
			//得到下单时默认生成的空对话记录
			List<UserConsultDoctor> consultList = userConsultDoctorBo.
					getConsultListBySomething(userId, consultOrderInfo.getConsultDoctorInfo().getDoctorInfo().getId(), 
							orderId, ConstantManage.CONSULT_USER_TYPE_CHARGE, "N","N", "",fdPackageId);
			if(consultList != null){
				//将默认生成的对话更新为有用对话
				UserConsultDoctor firstEmpty = consultList.get(0);
				firstEmpty.setSymptomDescribe(description);
				firstEmpty.setSymptomImage(fileName);
				firstEmpty.setSubmitTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				userConsultDoctorBo.updateSomeConsult(firstEmpty);
			}else{
				//初始化第一条对话记录
				userConsultDoctorBo.addNewUserConsultDoctor(userId, 
						consultOrderInfo.getConsultDoctorInfo().getDoctorInfo().getId(), "",
						fileName, description, "1", orderId, null, ConstantManage.CONSULT_USER_TYPE_CHARGE, null);
			}
			//添加关联宝宝记录
			if(babyId != null){
				ConsultBabySet consultBabySet = new ConsultBabySet();
				ConsultBabyInfo baby = new ConsultBabyInfo();
				baby.setId(babyId);
				consultBabySet.setLogId(orderId);
				consultBabySet.setConsultBabyInfo(baby);
				consultDoctorInfoService.addConsultBabySet(consultBabySet);
			}
			//发送短信提醒
			UserInfo usr = this.userInfoBo.getUserById(userId);
			DoctorInfo doc = consultOrderInfo.getConsultDoctorInfo().getDoctorInfo();
			Boolean flag = this.getWorkDoctorIds(doc);
			if(flag){
				String toDoctorSms="亲爱的"+doc.getDoctorName()+"医生，用户" + (usr.getBabyName()==null ? "" : usr.getBabyName()) +"正在康优宝贝平台向您咨询问题，请尽快登录康优宝贝平台进行处理，请务必在15分钟内给予用户回复，谢谢！";
				System.out.print("付费咨询 toDoctor=="+toDoctorSms);
				SendSms sendSms = new SendSms();
				sendSms.sendInfo(doc.getDoctorPhone(), toDoctorSms);
			}
			
		}
		//得到对话列表
		else if(action.equals("getList")){
			List<Long> logIds = this.consultDoctorInfoService.getConsultDoctorIdsByUser(userId,userType,null);
			if(logIds!=null){
				for(int j=0;j<logIds.size();j++){
					//得到用户对当前医生的最后一次发送时间
					List<UserConsultDoctor> consultList = userConsultDoctorBo.
							getConsultListBySomething(userId, null, 
									logIds.get(j), userType, "N","", "desc",fdPackageId);
					if(consultList != null){
						UserConsultDoctor lastOne = consultList.get(0);
						ConsultHistoryFo fo = new ConsultHistoryFo();
						fo.setLogId(lastOne.getLogId());
						fo.setIsCurrentDialog("Y");
						fo.setFdPackageId(lastOne.getFdPackageId());
						//大于用户最后发送时间的医生发送消息为未读消息
						List<UserConsultDoctor> consultList_user = userConsultDoctorBo.
								getConsultListBySomething(userId, null, 
										logIds.get(j), userType, "N","N", "desc",fdPackageId);
						
						List<UserConsultDoctor> consultList_doctor = userConsultDoctorBo.
								getConsultListBySomething(userId, lastOne.getDoctorId(), 
										lastOne.getLogId(), userType, "N","Y", "desc",fdPackageId);
						
						long newMes=0L;
						if(consultList_user != null){
							String lastDoctorTime = consultList_user.get(0).getSubmitTime();
							newMes = consultDoctorInfoService.countNewMessage(userId, lastOne.getDoctorId(),lastDoctorTime,userType);
						}else if(consultList_doctor!=null && !"Y".equals(consultList_doctor.get(0).getIsUserAlreadyRead())){//用户没说，全是医生说的
							newMes = consultList_doctor.size();
						}
						fo.setNewMesNum(newMes);
						DoctorInfo doctor = this.doctorInfoBo.getDoctorInfoByDoctorId(lastOne.getDoctorId());
						//判断医生是否上线
						ConsultDoctorInfo cdi = consultDoctorInfoService.getConsultDoctorInfoById(null, doctor.getId());
						fo.setIsOnline(cdi==null?"N":cdi.getIsOnline());
						fo.setDoctorInfo(doctor);
						
						fo.setLastSubmitTime(lastOne.getSubmitTime());
						if("Y".equals(lastOne.getIsReply())){//医生说的
							if(StringUtils.isNotEmpty(lastOne.getDoctorReplyImg())){
								fo.setLastConsult((lastOne.getDoctorReply()==null?"":lastOne.getDoctorReply()) + "[图片]");
							}else{
								fo.setLastConsult(lastOne.getDoctorReply());
							}
						}else{//用户说的
							if(StringUtils.isEmpty(lastOne.getSymptomImage()) && StringUtils.isEmpty(lastOne.getSymptomDescribe())){//初始化会话
								fo.setLastConsult("");
							}else{
								if(StringUtils.isNotEmpty(lastOne.getSymptomImage()))
									fo.setLastConsult((lastOne.getSymptomDescribe()==null?"":lastOne.getSymptomDescribe()) + "[图片]");
								else{
									fo.setLastConsult(lastOne.getSymptomDescribe());
								}
							}
						}
						this.consultHistoryFoList.add(fo);
					}
					//得到已结束会话
					List<UserConsultDoctor> consultList_end = userConsultDoctorBo.
							getConsultListBySomething(userId, null, 
									logIds.get(j), userType, "Y","", "desc",fdPackageId);
					if(consultList_end != null){
						String imgFlag = "";
						ConsultHistoryFo fo = new ConsultHistoryFo();
						UserConsultDoctor lastOne = consultList_end.get(0);
						
						if("N".equals(lastOne.getIsReply())){
							if(StringUtils.isNotEmpty(lastOne.getSymptomImage()))
								imgFlag = "[图片]";
							fo.setLastConsult((lastOne.getSymptomDescribe()==null?"":lastOne.getSymptomDescribe()) + imgFlag);
						}else{
							if(StringUtils.isNotEmpty(lastOne.getDoctorReplyImg()))
								imgFlag = "[图片]";
							fo.setLastConsult((lastOne.getDoctorReply()==null?"":lastOne.getDoctorReply()) + imgFlag);
						}
						fo.setLogId(lastOne.getLogId());
						DoctorInfo doctor = this.doctorInfoBo.getDoctorInfoByDoctorId(lastOne.getDoctorId());
						fo.setDoctorInfo(doctor);
						fo.setLastSubmitTime(lastOne.getSubmitTime());
						fo.setIsCurrentDialog("N");
						if(ConstantManage.CONSULT_USER_TYPE_CHARGE.equals(userType)){
							//已结束会话，找到待评价咨询订单
							ConsultOrderInfo consultOrderInfo = this.consultDoctorInfoService.getConsultOrderInfoById(lastOne.getLogId());
							if(consultOrderInfo != null && ConstantManage.WAIT_EVALUATED.equals(consultOrderInfo.getOrderStatus())){
								fo.setIsCurrentDialog("Y");
								fo.setConsultStatus(ConstantManage.WAIT_EVALUATED);
								double price = Double.valueOf(consultOrderInfo.getRealPrice()==null?"0":consultOrderInfo.getRealPrice()) + 
										Double.valueOf(consultOrderInfo.getUseRemainBalance()==null?"0":consultOrderInfo.getUseRemainBalance());
								fo.setConsultPrice(String.valueOf(price));
							}
						}
						this.consultHistoryFoList.add(fo);
					}
				}
			}
		}
		//得到一组会话详细信息记录列表
		else if(action.equals("getHist")){
			ConsultOrderInfo consultOrderInfo = this.consultDoctorInfoService.getConsultOrderInfoById(orderId);
			this.histConsultList = userConsultDoctorBo.
					getConsultListBySomething(userId, null, 
							orderId, userType, "","", "",fdPackageId);
			if(histConsultList != null){
				for(UserConsultDoctor ucd : histConsultList){
					ucd.setIsUserAlreadyRead("Y");
					userConsultDoctorBo.updateSomeConsult(ucd);
				}
			}
			this.doctorInfo = consultOrderInfo.getConsultDoctorInfo().getDoctorInfo();
			UserInfo usr = this.userInfoBo.getUserById(userId);
			this.userImage = usr.getUserImage();
			//查询病情印象记录
			this.commonDiseaseList = this.userConsultDoctorBo.getCommonDiseaseListByLogId(orderId);
		}
		//添加一条对话信息
		else if(action.equals("add")){
			//判断会话是否已经结束
			List<UserConsultDoctor> histConsultListEnd = 
					userConsultDoctorBo.getConsultListBySomething(userId, null,orderId, userType, "Y","", "desc",fdPackageId);
			if(histConsultListEnd != null){
				this.mes = "已结束";
				return "fail";
			}
			ConsultOrderInfo consultOrderInfo = this.consultDoctorInfoService.getConsultOrderInfoById(orderId);
			//上传咨询图片
			String fileName="";
			if(imagedata != null && !"".equals(imagedata)){
				String current= DateManage.getDateStr("yyyyMMddhhmmss");
				fileName=userId +"image" + current +".jpg";
				String fileAllName="../kybabyBG/admin/images/consultPicture/"+fileName;
				String dir = ServletActionContext.getServletContext().getRealPath(fileAllName);
				if(EncryptUtil.uploadImage(imagedata,dir)){
					this.symptomImage = fileName;
				}
			}
			userConsultDoctorBo.addNewUserConsultDoctor(userId, 
					consultOrderInfo.getConsultDoctorInfo().getDoctorInfo().getId(), "",
					fileName, description, "1", orderId, null, userType, null);
			this.histConsultList = 
					userConsultDoctorBo.getConsultListBySomething(userId, null,orderId, userType, "N","N", "desc",fdPackageId);
		}
		//结束一条对话信息
		else if(action.equals("end")){
			List<UserConsultDoctor> histConsultList = userConsultDoctorBo.
					getConsultListBySomething(userId, null, 
							orderId, null, "","", "",fdPackageId);
			if(histConsultList != null){
				//判断是否结束会话了，没结束，当前结束算钱
				if("N".equals(histConsultList.get(0).getIsEnd())){
					//修改订单信息为待评价
					ConsultOrderInfo consultOrderInfo = this.consultDoctorInfoService.getConsultOrderInfoById(orderId);
					consultOrderInfo.setOrderStatus(ConstantManage.WAIT_EVALUATED);
					this.consultDoctorInfoService.saveOrUpdateConsultOrderInfo(consultOrderInfo);
					//修改医生余额信息
					ConsultDoctorInfo cd = consultOrderInfo.getConsultDoctorInfo();
					DoctorInfo doctorInfo = cd.getDoctorInfo();
					//计算薪酬
					double baodi =cd.getConsultMoney()  * cd.getConsultCommission();
					baodi = MyMath.round(baodi, 2);
					doctorAccountBo.addNewDoctorAccount(doctorInfo.getId(), baodi, "+", "在线咨询服务费");
					//医生余额
					double doctorBalance = doctorInfo.getDoctorBalance() + baodi;
					doctorBalance = MyMath.round(doctorBalance, 2);
					doctorInfo.setDoctorBalance(doctorBalance);
					//更新医生余额信息
					this.doctorInfoBo.updateDoctorInstance(doctorInfo);
				}
				for(UserConsultDoctor consult : histConsultList){
					consult.setIsEnd("Y");
					userConsultDoctorBo.updateSomeConsult(consult);
				}
			}
		}
		//评价咨询服务
		else if(action.equals("assess")){
			ConsultOrderInfo consultOrderInfo = this.consultDoctorInfoService.getConsultOrderInfoById(orderId);
			DoctorInfo doctorInfo = consultOrderInfo.getConsultDoctorInfo().getDoctorInfo();
			evaluate.setSubmitTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			evaluate.setUserId(userId);
			evaluate.setDoctorId(doctorInfo.getId());
			evaluate.setOrderNum(consultOrderInfo.getOrderNum());
			this.orderInfoBo.saveEvaluate(evaluate);
			//修改订单状态
			consultOrderInfo.setOrderStatus(ConstantManage.HASE_EVALUATED_CLINIC_ORDER);
			this.consultDoctorInfoService.saveOrUpdateConsultOrderInfo(consultOrderInfo);
			//修改医生服务kpi
			long seiviceStarTotal = doctorInfo.getSeiviceStarTotal()+evaluate.getServiceStarLevel();
			long dutyStarTotal = doctorInfo.getDutyStarTotal()+evaluate.getDutyStarLevel();
			long onTimeStarTotal = doctorInfo.getOnTimeStarTotal()+evaluate.getOnTimeStarLevel();
			
			long linshi1 = (seiviceStarTotal)/(doctorInfo.getSeiviceStarHitCount()+1);
			long linshi2 = (dutyStarTotal)/(doctorInfo.getDutyStarLevelHitCount()+1);
			long linshi3 = (onTimeStarTotal)/(doctorInfo.getOnTimeStarLevelHitCount()+1);
			
			doctorInfo.setSeiviceStarHitCount(doctorInfo.getSeiviceStarHitCount()+1);
			doctorInfo.setDutyStarLevelHitCount(doctorInfo.getDutyStarLevelHitCount()+1);
			doctorInfo.setOnTimeStarLevelHitCount(doctorInfo.getOnTimeStarLevelHitCount()+1);
			doctorInfo.setSeiviceStarTotal(seiviceStarTotal);
			doctorInfo.setDutyStarTotal(dutyStarTotal);
			doctorInfo.setOnTimeStarTotal(onTimeStarTotal);
			String zhonghe1 = String.valueOf(new BigDecimal(linshi1).setScale(0, BigDecimal.ROUND_HALF_UP));
			String zhonghe2 = String.valueOf(new BigDecimal(linshi2).setScale(0, BigDecimal.ROUND_HALF_UP));
			String zhonghe3 = String.valueOf(new BigDecimal(linshi3).setScale(0, BigDecimal.ROUND_HALF_UP));
			doctorInfo.setSeiviceStarLevel(Long.valueOf(zhonghe1));
			doctorInfo.setDutyStarLevel(Long.valueOf(zhonghe2));
			doctorInfo.setOnTimeStarLevel(Long.valueOf(zhonghe3));
			orderInfoBo.updateDoctorInfo(doctorInfo);
		}
		//得到咨询服务医生信息
		else if(action.equals("getDoctorByConsult")){
			ConsultOrderInfo consultOrderInfo = this.consultDoctorInfoService.getConsultOrderInfoById(orderId);
			this.doctorInfo = consultOrderInfo.getConsultDoctorInfo().getDoctorInfo();
		}
		//得到医生所在的家庭医生专家团队列表
		else if(action.equals("getDoctorAtFdTeams")){
			List<FdServiceMember> memberList = this.fdServiceItemsService.getFdServiceMemberList(null, null);
			if(memberList != null){
				ConsultOrderInfo consultOrderInfo = this.consultDoctorInfoService.getConsultOrderInfoById(orderId);
				DoctorInfo doctorInfo = consultOrderInfo.getConsultDoctorInfo().getDoctorInfo();
				for(FdServiceMember fdMember : memberList){
					if(fdMember.getDoctorInfo() != null && fdMember.getFdServiceTeams().getFdServicePackage() == null &&
							fdMember.getDoctorInfo().getId().longValue() == doctorInfo.getId().longValue()){
						FdServiceTeams team = new FdServiceTeams();
						BeanUtils.copyProperties(fdMember.getFdServiceTeams(), team, 
								new String[]{"fdServicePackage","remark","doctorRoleIds","doctorRoleNames"});
						this.fdServiceTeamsList.add(team);
					}
				}
			}
		}
		/**
		 * 在对话当前页面将消息设置为已读状态
		 */
		else if(action.equals("updateUserAlreadyRead")){
			//判断会话是否已经结束
			List<UserConsultDoctor> histConsultListEnd = 
					userConsultDoctorBo.getConsultListBySomething(userId, null,orderId, null, "N","", "desc",fdPackageId);
			if(histConsultListEnd != null){
				for(UserConsultDoctor ucd : histConsultListEnd){
					if(!"Y".equals(ucd.getIsUserAlreadyRead())){
						ucd.setIsUserAlreadyRead("Y");
						userConsultDoctorBo.updateSomeConsult(ucd);
					}
				}
			}
		}
		//定时器测试
		else if(action.equals("testCloseConsult")){
			consultDoctorInfoService.closeConsultOrderPromptTask();
		}
		return SUCCESS;
	}
    /**
     * 得到上班时间的医生列表
     * @return
     */
	private Boolean getWorkDoctorIds(DoctorInfo doctor){
		Boolean flag = true;
		List<DoctorWorkTime> doctorWorkTimeList = this.userConsultDoctorBo.getDoctorWorkTimeList(null,doctor);
		if(doctorWorkTimeList != null){
			String nowTime = DateManage.getDateStr("HHmm");
			for(DoctorWorkTime dwt : doctorWorkTimeList){
				//看当前时间是否在工作时间内
				String startTime = dwt.getOnWorkTime();
				startTime = startTime.replaceAll(" ", "").replaceAll("-", "").replaceAll(":", "");
				String endTime = dwt.getOffWorkTime();
				endTime = endTime.replaceAll(" ", "").replaceAll("-", "").replaceAll(":", "");
				if(Long.valueOf(nowTime).longValue() >= Long.valueOf(startTime).longValue() && 
						Long.valueOf(nowTime).longValue() <= Long.valueOf(endTime).longValue() ){
					flag = true;
					break;
				}else{
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
	public String getMes() {
		return mes;
	}

	public void setImagedata(String imagedata) {
		this.imagedata = imagedata;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setBabyId(Long babyId) {
		this.babyId = babyId;
	}

	public List<ConsultHistoryFo> getConsultHistoryFoList() {
		return consultHistoryFoList;
	}

	public List<UserConsultDoctor> getHistConsultList() {
		return histConsultList;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public String getSymptomImage() {
		return symptomImage;
	}

	public void setSymptomImage(String symptomImage) {
		this.symptomImage = symptomImage;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setFdPackageId(Long fdPackageId) {
		this.fdPackageId = fdPackageId;
	}

	public List<CommonDisease> getCommonDiseaseList() {
		return commonDiseaseList;
	}

	public List<FdServiceTeams> getFdServiceTeamsList() {
		return fdServiceTeamsList;
	}

}
