package com.kybaby.newbussiness.spservice.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import sun.util.logging.resources.logging;

import com.kybaby.bo.UserAccountBo;
import com.kybaby.bo.UserInfoBo;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.bo.ClinicOrderService;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.ChildCareChargeService;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.spservice.bo.SpInterfaceService;
import com.kybaby.newbussiness.spservice.common.BooleanMsg;
import com.kybaby.newbussiness.spservice.common.SpServiceConstant;
import com.kybaby.newbussiness.spservice.dao.SpInterfaceDao;
import com.kybaby.newbussiness.spservice.domain.SpAppointmentRecord;
import com.kybaby.newbussiness.spservice.domain.SpAppointmentSchedule;
import com.kybaby.newbussiness.spservice.domain.SpCancelRegister;
import com.kybaby.newbussiness.spservice.domain.SpCheckRecord;
import com.kybaby.newbussiness.spservice.domain.SpCheckReport;
import com.kybaby.newbussiness.spservice.domain.SpCostInfo;
import com.kybaby.newbussiness.spservice.domain.SpDoctorAdviceInfo;
import com.kybaby.newbussiness.spservice.domain.SpDoctorAlias;
import com.kybaby.newbussiness.spservice.domain.SpExceptionMsg;
import com.kybaby.newbussiness.spservice.domain.SpHealthcardManager;
import com.kybaby.newbussiness.spservice.domain.SpInspectInfo;
import com.kybaby.newbussiness.spservice.domain.SpInspectRecord;
import com.kybaby.newbussiness.spservice.domain.SpRegisterOrderDetail;
import com.kybaby.newbussiness.spservice.domain.SpUserInfo;
import com.kybaby.newbussiness.spservice.domain.SpVisitRecord;
import com.kybaby.newbussiness.spservice.domain.SpclinicCharge;
import com.kybaby.newbussiness.spservice.exception.SpServiceException;
import com.kybaby.newbussiness.spservice.generatedFile.ToolInterfaceSoap;
import com.kybaby.newbussiness.spservice.util.SpInterfaceUtil;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.LogUtil;
import com.kybaby.util.MsgUtil;
import com.kybaby.util.MyMath;

public class SpInterfaceServiceImpl implements SpInterfaceService{
	
	private ToolInterfaceSoap spInterface;
	private SpInterfaceDao spInterfaceDao;
	/**
	 * 儿保收费业务
	 */
	protected ChildCareChargeService childCareChargeService;
	/**
	 * 门诊订单服务类
	 */
	private ClinicOrderService clinicOrderService;
	private UserAccountBo userAccountBo;
	private UserInfoBo userInfoBo;
	
	public static void main(String[] args) {
		long registerTime = DateManage.parseStr2Date_yyyy_MM_dd("2016-10-20 10:10:10").getTime();
		long nowTime =  DateManage.parseStr2Date_yyyy_MM_dd(DateManage.formatDateStr_yyyy_MM_dd(new Date())).getTime();
		if(registerTime==nowTime){
			System.out.println("挂号");
		}else{
			System.out.println("预约");
		}
	}
	
	
	public void gettest(){
/*		String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
				"<Body><Request><OrgCode>3d715fb3-5fd4-4f36-9be9-7cca29de01ca</OrgCode><OperType>101</OperType><QueryString></QueryString></Request></Body>";
		String interactionOperating = spInterface.interactionOperating(request);
		System.out.println(interactionOperating);*/
		SpUserInfo userInfo = new SpUserInfo();
		userInfo.setSp_OrgCode(SpServiceConstant.SP_HOSPITAL_CODE);
		userInfo.setSp_OperType("003");
		userInfo.setSp_BloodCD("5");
		userInfo.setSp_RHCD("2");
		List<SpUserInfo> reqData = this.getReqData(userInfo, SpUserInfo.class);
		System.out.println(reqData);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getObject(Long id,Class<?> clazz){
		return (T) spInterfaceDao.getObjectByID(id, clazz);
	}
	/**
	 * 通过身份证号查询用户信息,并如果有用户信息,则保存
	 * @param userID 用户ID
	 * @param visitCardInfo 查询信息 
	 * @return
	 */
	public BooleanMsg registerWithNoCard(SpHealthcardManager visitCardInfo){
		BooleanMsg booleaMsg = new BooleanMsg();
		visitCardInfo.setHealthcardNum(visitCardInfo.getIdentityCard());
		
		SpUserInfo spUserInfo = this.querySpUserInfo(visitCardInfo);
		
		String residentID = null;
		if(spUserInfo==null) {
			//没有查询到相关用户信息,调用接口向中联系统中增加一条用户记录
			SpUserInfo userInfo = new SpUserInfo();
			userInfo.setSp_OrgCode(SpServiceConstant.SP_HOSPITAL_CODE);
			userInfo.setSp_ResidentName(visitCardInfo.getName());
			userInfo.setSp_PaperNum(visitCardInfo.getIdentityCard());
			userInfo.setSp_BirthDay(visitCardInfo.getBirthday());
			userInfo.setSp_SelfPhone(visitCardInfo.getPhone());
			switch(visitCardInfo.getSex()){
				case "男":userInfo.setSp_SexCD("1");break;
				case "女":userInfo.setSp_SexCD("2");break;
				case "未知":userInfo.setSp_SexCD("9");break;
			}
			userInfo.setSp_OperType("003");
			userInfo.setSp_BloodCD("5");
			userInfo.setSp_RHCD("2");
			List<SpUserInfo> reqData = this.getReqData(userInfo, SpUserInfo.class);
			if(reqData.isEmpty()){
				booleaMsg.setMsg("无卡挂号操作失败,具体原因请联系管理员！");
				booleaMsg.isTrue(false);
			}else{
				residentID = reqData.get(0).getResidentID();
			}
		}else{
			residentID = spUserInfo.getResidentID();
		}
		//根据返回的个人ID,查询是否已保存就诊信息
		visitCardInfo.setResidentId(residentID);
		//visitCardInfo.setResidentId("EB76E095-2214-4A56-A134-B8B968AFFC17");
		booleaMsg.setObject(visitCardInfo);
		booleaMsg.isTrue(true);
		return booleaMsg;
	}
	/**
	 * 取消儿科订单状态
	 * @param userId 用户ID
	 * @param userInfo 用户信息
	 * @param oldOrder 要修改的儿科门诊订单
	 * @param orderStatus 修改后订单的状态
	 */
	public void cancelPeadiatricsOrder(Long userId, UserInfo userInfo,DoctorInfo doctorInfo,
			OrderInfoClinic oldOrder,String orderStatus) {
		/*
		 * 将已预约时间设置为可用
		 */
		this.clinicOrderService.updateDoctorClinicTimeSegmentStatus(doctorInfo, "Y", oldOrder.getAppointmentDate(), 
				oldOrder.getAppointmentBeganTime());
		/*
		 * 修改订单状态
		 */
		oldOrder.setOrderStatus(orderStatus);
		this.clinicOrderService.updateClinicOrder(oldOrder);
		/*
		 * 退还用户支付金额到余额中
		 */
		Double realPrice = oldOrder.getRealPrice()==null?0d:Double.valueOf(oldOrder.getRealPrice());
		//添加用户用钱记录
		if(realPrice != 0D){
			Double accountBalance = userInfo.getAccountBalance();
			accountBalance = accountBalance+realPrice;
			accountBalance = MyMath.round(accountBalance, 2);
			userInfo.setAccountBalance(accountBalance);
			this.userInfoBo.updateUser(userInfo);
			this.userAccountBo.addNewUserAccount(userId, realPrice, "+", "门诊订单退款", oldOrder.getOrderNum());
		}
		
		//将已预约时间设置为可用
		this.clinicOrderService.updateDoctorClinicTimeSegmentStatus(doctorInfo, "Y", oldOrder.getAppointmentDate(), 
				oldOrder.getAppointmentBeganTime());
		oldOrder.setOrderStatus(orderStatus);
		this.clinicOrderService.updateClinicOrder(oldOrder);
	}
	
	/**
	 * 取消挂号记录
	 * @param spQueryMessageID 本地订单ID
	 */
	public boolean cancelRegister(Long orderID,String deptFlag){
		//获取挂号订单明细
		SpRegisterOrderDetail orderDetail = getSpRegisterOrderDetail(orderID, deptFlag);
		if(orderDetail==null) {
			String orderType = "";
			if(SpServiceConstant.CHILD_CARE_FLAG.equals(deptFlag)) orderType = "儿保";
			else orderType = "儿科";
			MsgUtil.set("操作异常,请联系管理员");
			LogUtil.warn("错误信息:没有挂号订单明细,本地"+orderType+"订单ID为:"+orderID);
			return false;
		}
		/*
		 * 判断挂号订单是否可以取消
		 */
		boolean updateRegisterOrderIsEnableOK = updateRegisterOrderIsEnableOK(orderDetail);
		if(!updateRegisterOrderIsEnableOK) return false;
		
		SpCancelRegister cancelRegister = new SpCancelRegister();
		cancelRegister.setSp_OperType("114");
		cancelRegister.setSp_OrgCode(orderDetail.getOrgId());
		cancelRegister.setSp_EventID(orderDetail.getEventId());
		cancelRegister.setSp_UserID(SpServiceConstant.KYBABY_ID);
		cancelRegister.setSp_UserName(SpServiceConstant.KYBABY_NAME);
		/*
		 * 取消挂号
		 */
		List<SpCancelRegister> reqData = this.getReqData(cancelRegister, SpCancelRegister.class);
		if(reqData.isEmpty()) return false;
		/*
		 * 修改订单状态
		 */
		orderDetail.setOrderStatus(ConstantManage.USER_CANCLE_CLINIC_ORDER);
		spInterfaceDao.updateObject(orderDetail);
		return true;
	}
	/**
	 * 取消儿保订单状态
	 * @param userId 用户ID
	 * @param userInfo 用户信息
	 * @param oldOrder 儿保订单
	 * @param orderStatus 订单状态
	 */
	public Long cancelChildCareOrder(Long userId, UserInfo userInfo,
			UserChildcareAppointmentInfo oldOrder
			,String orderStatus) {
		/*
		 * 修改订单状态
		 */
		oldOrder.setStatus(orderStatus);
		Long id = this.childCareChargeService.saveOrUpdateUserChildcareAppointmentInfo(oldOrder);
		
		/*
		 * 退还用户支付金额到余额中
		 */
		Double realPrice = oldOrder.getRealPrice()==null?0d:Double.valueOf(oldOrder.getRealPrice());
		if(realPrice.doubleValue() != 0D){
			Double accountBalance = userInfo.getAccountBalance();
			accountBalance = accountBalance+realPrice;
			accountBalance = MyMath.round(accountBalance, 2);
			userInfo.setAccountBalance(accountBalance);
			this.userInfoBo.updateUser(userInfo);
			this.userAccountBo.addNewUserAccount(userId, realPrice, "+", "儿保订单退款", oldOrder.getOrderNum());
		}
		return id;
	}
	
	/**
	 * 是否允许取消挂号订单
	 * @param orderDetail
	 * @return
	 */
	private boolean updateRegisterOrderIsEnableOK(SpRegisterOrderDetail orderDetail){
		String registerDate = orderDetail.getRegisterDate();
		String[] split = registerDate.split(" ");
		String registerDay = split[0];
		//挂号时间
		long registerTime = DateManage.parseStr2Date_yyyy_MM_dd(registerDay).getTime();
		Date date = new Date();
		//现在时间
		long nowTime = date.getTime();
		//当天时间
		long nowDateTime = DateManage.parseStr2Date_yyyy_MM_dd(DateManage.formatDateStr_yyyy_MM_dd(date)).getTime();
		if(registerTime==nowDateTime){
			MsgUtil.set("取消挂号失败,不能取消今日的挂号");
			return false;
		}
		if(nowTime>registerTime){
			MsgUtil.set("取消挂号失败,不能取消以前的挂号");
			return false;
		}
		return true;
	}
	
	/**  查看就诊记录详细内容  */
	public SpVisitRecord getSpVisitRecord(Long spQueryMessageID){
		SpVisitRecord spVisitRecord = spInterfaceDao.getObjectByID(spQueryMessageID, SpVisitRecord.class);
		return spVisitRecord;
	}
	/**
	 * 获取用户就诊记录列表 
	 * @param visitCardID 就诊卡ID
	 * @param pageNo 要选的页号
	 * @param userID 用户ID
	 * @param timeFlag 查询时间标识,本月记录1,历史记录0
	 * @param spOrgID 苏坡机构ID
	 * @return
	 * <p>1.返回结果为空,标识没有就诊记录</p>
	 */
	@Override
	public List<SpVisitRecord> saveSpVisitRecords(Long visitCardID,Integer pageNo,Long userID,String timeFlag,String spOrgID){
		List<SpVisitRecord> spVisitRecords = new ArrayList<SpVisitRecord>();
		/*
		 * 只有在页面初始化的时候的调用中联接口
		 */
		if(pageNo<2){
			/*
			 * 获取用户拥有的健康卡号信息
			 */
			SpHealthcardManager visitCardInfo = spInterfaceDao.getObjectByID(visitCardID, SpHealthcardManager.class);
			if(visitCardInfo==null) throw new RuntimeException("没有查询到就诊卡信息,就诊卡ID为:"+visitCardID);
			
			String startTime = getStartTime(timeFlag);
			String endTime = getEndTime(timeFlag);
			/*
			 * 调用中联接口获取就诊记录
			 */
			spVisitRecords = getSpVisitRecords(visitCardInfo, startTime, endTime,spOrgID);
			if(spVisitRecords.isEmpty()) return  spVisitRecords;
			
			for (SpVisitRecord spVisitRecord : spVisitRecords) {
				spVisitRecord.setLocalUserID(userID);
			}
		}
		return spInterfaceDao.saveOrUpdateSpVisitRecords(spVisitRecords,userID,pageNo);
	}
	
	private String getStartTime(String timeFlag){
		/*
		 * 就诊记录时间判定,本月记录1,历史记录0
		 */
		if(SpServiceConstant.MONTH_HISTORY.equals(timeFlag)){
			return "1949-10-01";
		}else{
			return DateManage.getFirstDayOfNowMonth();
		}
	}
	private String getEndTime(String timeFlag){
		/*
		 * 就诊记录时间判定,本月记录1,历史记录0
		 */
		if(SpServiceConstant.MONTH_HISTORY.equals(timeFlag)){
			return DateManage.getLatestDayOfPreMonth();
		}else{
			return DateManage.formatDateStr_yyyy_MM_dd(new Date());
		}
	}
	/**
	 * 获取就诊记录
	 * @param visitCardInfo 就诊卡信心
	 * @param startTime 查询开始时间
	 * @param endTime 结束时间
	 * @param spOrgID 苏坡机构ID
	 * @return
	 */
	private List<SpVisitRecord> getSpVisitRecords(SpHealthcardManager visitCardInfo,String startTime,String endTime,String spOrgID){
		List<SpVisitRecord> tmp_list = new ArrayList<SpVisitRecord>();
		/*
		 * 参数组组装,调用接口
		 */
		SpVisitRecord spVisitRecord = new SpVisitRecord();
		spVisitRecord.setSp_OperType("103");
		spVisitRecord.setSp_OrgCode(spOrgID);
		spVisitRecord.setSp_ResidentID(visitCardInfo.getResidentId());
		spVisitRecord.setSp_BeginDT(startTime);
		spVisitRecord.setSp_EndDT(endTime);
		List<SpVisitRecord> reqData = this.getReqData(spVisitRecord, SpVisitRecord.class);
		/*
		 * 转换特定字符中文意思
		 */
		if(!reqData.isEmpty()&&StringUtils.isNotBlank(reqData.get(0).getEventID())){
			for (SpVisitRecord visitRecord : reqData) {
				
				visitRecord.setOrgID(spOrgID);
				String eventID = visitRecord.getEventID();
				
				String state = visitRecord.getState();
				switch(state){
					case "0":state="未就诊";break;
					case "1":state="就诊中";break;
					case "2":state="已完成";break;
					default:throw new RuntimeException("就诊记录状态转换未为成功：就诊ID:"+eventID+",状态编码："+state);
				}
				visitRecord.setState(state);
				
				String sexCD = visitRecord.getSexCD();
				switch(sexCD){
					case "1":sexCD="男";break;
					case "2":sexCD="女";break;
					case "9":sexCD="未知";break;
					default:throw new RuntimeException("就诊记录性别转换未为成功：就诊ID:"+eventID+",性别编码："+sexCD);
				}
				visitRecord.setSexCD(sexCD);
			}
			tmp_list.addAll(reqData);
		}
		return tmp_list;
	}
	/**
	 * 获取检查记录列表
	 * @param visitCardID 就诊卡ID
	 * @param pageNo 页码
	 * @param userID 用户ID
	 * @param timeFlag 查询时间标识,本月记录1,历史记录0
	 * @param orgID 苏坡机构ID
	 * @return
	 */
	@Override
	public List<SpCheckRecord> saveSpCheckRecords(Long visitCardID,Integer pageNo,Long userID,String timeFlag,String orgID){
		List<SpCheckRecord> spCheckRecords = new ArrayList<SpCheckRecord>();
	
		if(pageNo<2){
			SpHealthcardManager visitCardInfo = spInterfaceDao.getObjectByID(visitCardID, SpHealthcardManager.class);
			if(visitCardInfo==null) throw new RuntimeException("没有查询到就诊卡信息,就诊卡ID为:"+visitCardID);
			
			String startTime = this.getStartTime(timeFlag);
			String endTime = this.getEndTime(timeFlag);
			
			SpCheckRecord spCheckRecord = new SpCheckRecord();
			spCheckRecord.setSp_OperType("109");
			spCheckRecord.setSp_OrgCode(orgID);
			spCheckRecord.setSp_ResidentID(visitCardInfo.getResidentId());
			spCheckRecord.setSp_CheckSD(startTime+" 00:00:00");
			spCheckRecord.setSp_CheckED(endTime+" 23:59:59");
			List<SpCheckRecord> reqData = this.getReqData(spCheckRecord, SpCheckRecord.class);
			
			if(reqData.isEmpty()||StringUtils.isBlank(reqData.get(0).getCheckID())) return spCheckRecords;
			
			for (SpCheckRecord tmp_SpCheckRecord : reqData) {
				tmp_SpCheckRecord.setOrgID(orgID);
				tmp_SpCheckRecord.setLocalUserID(userID);
			}
			spCheckRecords.addAll(reqData);
		}
		return spInterfaceDao.saveOrUpdateSpCheckRecords(spCheckRecords,userID,pageNo);
	}
	/**   
	 * 获取检查记录详情和检查报告
	 * @param spQueryMessageID 检查记录表ID
	 */
	public SpCheckRecord saveSpCheckInfo(Long spQueryMessageID){
		SpCheckRecord spCheckRecord = spInterfaceDao.getObjectByID(spQueryMessageID, SpCheckRecord.class);
		
		SpCheckReport spCheckReport = new SpCheckReport();
		spCheckReport.setSp_OperType("110");
		spCheckReport.setSp_OrgCode(spCheckRecord.getOrgID());
		spCheckReport.setSp_CheckID(spCheckRecord.getCheckID());
		
		List<SpCheckReport> reqData = this.getReqData(spCheckReport, SpCheckReport.class);
		if(!reqData.isEmpty()&&(StringUtils.isNotBlank(reqData.get(0).getDesInfo())
				||StringUtils.isNotBlank(reqData.get(0).getProInfo()))){
			spInterfaceDao.saveOrUpdatespInspectReports(reqData,spCheckRecord);
			spCheckRecord.setSpCheckReports(reqData);
		}

		return spCheckRecord;
	}
	/**   
	 * 获取检验记录列表
	 * @param visitCardID 就诊卡ID
	 * @param pageNo 要选的页号
	 * @param userID 用户ID
	 * @param timeFlag 查询时间标识,本月记录1,历史记录0
	 * @param orgID 苏坡机构ID
	 */
	@Override
	public List<SpInspectRecord> saveSpInspectRecords(Long visitCardID,Integer pageNo,Long userID,String timeFlag,String orgID){
		List<SpInspectRecord> inspectRecords = new ArrayList<SpInspectRecord>();
		if(pageNo<2){
			SpHealthcardManager visitCardInfo = spInterfaceDao.getObjectByID(visitCardID, SpHealthcardManager.class);
			if(visitCardInfo==null) throw new RuntimeException("没有查询到就诊卡信息,就诊卡ID为:"+visitCardID);
			
			String startTime = this.getStartTime(timeFlag);
			String endTime = this.getEndTime(timeFlag);
			
			SpInspectRecord inspectRecord = new SpInspectRecord();
			inspectRecord.setSp_OperType("106");
			inspectRecord.setSp_OrgCode(orgID);
			inspectRecord.setSp_ResidentID(visitCardInfo.getResidentId());
			inspectRecord.setSp_CheckSD(startTime+" 00:00:00");
			inspectRecord.setSp_CheckED(endTime+" 23:59:59");
			List<SpInspectRecord> reqData = this.getReqData(inspectRecord, SpInspectRecord.class);
			
			if(reqData.isEmpty()||StringUtils.isBlank(reqData.get(0).getCheckID())) return inspectRecords;
			
			for (SpInspectRecord spInspectRecord : reqData) {
				spInspectRecord.setOrgID(orgID);
				spInspectRecord.setLocalUserID(userID);
			}
			inspectRecords.addAll(reqData);
		}
		return spInterfaceDao.saveOrUpdateSpInspectRecords(inspectRecords,userID,pageNo);
	}
	/**   
	 * 获取检验记录和检验结果
	 * @param spQueryMessageID 检验表ID
	 */
	public SpInspectRecord saveSpInspectInfo(Long spQueryMessageID){
		/*
		 * 获取检验记录
		 */
		SpInspectRecord inspectRecord = spInterfaceDao.getObjectByID(spQueryMessageID, SpInspectRecord.class);
		if(inspectRecord==null){
			throw new RuntimeException("根据检验表ID没有查询到对应的检验记录,检验表ID:"+spQueryMessageID);
		}
		
		SpInspectInfo inspectInfo = new SpInspectInfo();
		inspectInfo.setSp_OrgCode(inspectRecord.getOrgID());
		inspectInfo.setSp_CheckID(inspectRecord.getCheckID());
		inspectInfo.setSp_OperType("107");
		
		List<SpInspectInfo> reqData = this.getReqData(inspectInfo, SpInspectInfo.class);
		if(!reqData.isEmpty()&&StringUtils.isNotBlank(reqData.get(0).getItemName())){
			List<SpInspectInfo> inspectInfos = spInterfaceDao.saveOrUpdateSpInspectInfos(reqData,inspectRecord);
			inspectRecord.setSpInspectInfos(inspectInfos);
		}
		
		return inspectRecord;
	}
	/**  根据主键ID获取费用信息  */
	public SpCostInfo getSpCostInfo(Long spQueryMessageID){
		return spInterfaceDao.getObjectByID(spQueryMessageID, SpCostInfo.class);
	}
	/**  执行门诊收费  */
	public boolean saveSpClinicChargeInfo(Long spQueryMessageID){
		SpCostInfo costInfo = spInterfaceDao.getObjectByID(spQueryMessageID, SpCostInfo.class);
		
		SpclinicCharge clinicCharge = new SpclinicCharge();
		clinicCharge.setSp_No(costInfo.getDocumentNo());
		clinicCharge.setSp_OperType("113");
		clinicCharge.setSp_OrgCode(costInfo.getOrgID());
		clinicCharge.setSp_EventID(costInfo.getEventID());
		clinicCharge.setSp_UserID(SpServiceConstant.KYBABY_ID);
		clinicCharge.setSp_UserName(SpServiceConstant.KYBABY_NAME);
		
		List<SpclinicCharge> reqData = this.getReqData(clinicCharge, SpclinicCharge.class);
		String mess = "";
		boolean executeFlag = false;
		if(reqData.isEmpty()){
			mess = "收费失败";
		}else{
			mess = "收费成功";
			executeFlag = true;
		}
		costInfo.setPayStatus(mess);
		costInfo.setPayMoney(costInfo.getMoney());
		costInfo.setPayTime(DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(new Date()));
		spInterfaceDao.updateObject(costInfo);
		return executeFlag;
	}
	/**
	 * 判断费用记录是否已缴费
	 * @param spQueryMessageID
	 * @return
	 */
	public boolean querySpCostInfo_IsPaid(Long spQueryMessageID){
		SpCostInfo spCostInfo = this.getSpCostInfo(spQueryMessageID);
		SpVisitRecord spVisitRecord = spCostInfo.getSpVisitRecord();
		//未收费
		List<SpCostInfo> ret_CostInfos = this.getSpCostInfo(0L,spVisitRecord,SpServiceConstant.COST_TYPE_NO);
		if(!ret_CostInfos.isEmpty()&&StringUtils.isNotBlank(ret_CostInfos.get(0).getDocumentNo())){
			for (SpCostInfo tmp_SpCostInfo : ret_CostInfos) {
				if(tmp_SpCostInfo.getDocumentNo().equals(spCostInfo.getDocumentNo()))
					return false;
			}
		}
		return true;
	}
	/**
	 * 获取费用信息列表
	 * @param visitCardID 就诊卡ID
	 * @param pageNo
	 * @param userID
	 * @param timeFlag
	 * @param costType 费用类型(1已收费,0未收费)
	 * @param orgID
	 * @return
	 */
	@Override
	public List<SpCostInfo> saveSpCostInfos(Long visitCardID,Integer pageNo,Long userID,String timeFlag,String costType,String orgID){
		List<SpCostInfo> tmp_list = new ArrayList<SpCostInfo>();
		String opTime = null;
		if(pageNo<2){
			/*
			 * 获取用户就诊记录,
			 * 1.如果就诊记录为空，直接返回空集合
			 */
			List<SpVisitRecord> spVisitRecords = this.saveSpVisitRecords(visitCardID,pageNo, userID, timeFlag,orgID);
			if(spVisitRecords.isEmpty())	return tmp_list;
			/*
			 * 获取费用表中已有的记录,排除未收费的记录
			 */
			String eventIDsOfCostWithNoQuery = getEventIDsOfCostWithNoQuery(spVisitRecords, userID,costType);
			
			for(SpVisitRecord spVisitRecord : spVisitRecords){
				if(opTime == null ) opTime = spVisitRecord.getOptime();
				/*
				 * 排除已收费的eventID
				 */
				if(eventIDsOfCostWithNoQuery.contains(spVisitRecord.getEventID())) continue;
				
				List<SpCostInfo> spCostInfos_temp = new ArrayList<SpCostInfo>();
				List<SpCostInfo> ret_CostInfos  = new ArrayList<SpCostInfo>();
				if(SpServiceConstant.COST_TYPE_NO.equals(costType)){
					//未收费
					ret_CostInfos = this.getSpCostInfo(userID,spVisitRecord,SpServiceConstant.COST_TYPE_NO);
					if(!ret_CostInfos.isEmpty()){
						spCostInfos_temp.addAll(ret_CostInfos);
					}
				}else{
					//收费
					ret_CostInfos = this.getSpCostInfo(userID,spVisitRecord,SpServiceConstant.COST_TYPE_YES);
					if(!ret_CostInfos.isEmpty()){
						spCostInfos_temp.addAll(ret_CostInfos);
					}
				}
				if(!spCostInfos_temp.isEmpty()){
					tmp_list.addAll(spCostInfos_temp);
				}
			}
		}
		return spInterfaceDao.saveOrUpdateCostInfo(tmp_list,userID,pageNo,opTime);
	}
	/**
	 * 调用中联接口，获取费用清单
	 * @param eventID 就诊ID
	 * @param userID 用户ID
	 * @param spVisitRecord 就诊记录对象
	 * @param costType 费用类型
	 * @return
	 */
	private List<SpCostInfo> getSpCostInfo(Long userID,SpVisitRecord spVisitRecord,String costType){
		List<SpCostInfo> list = new ArrayList<SpCostInfo>();
		SpCostInfo spCostInfo = new SpCostInfo();
		
		String orgID = spVisitRecord.getOrgID();
		
		spCostInfo.setSp_OperType("105");
		spCostInfo.setSp_OrgCode(orgID);
		spCostInfo.setSp_EventID(spVisitRecord.getEventID());
		spCostInfo.setSp_Type(costType);
		List<SpCostInfo> reqData = this.getReqData(spCostInfo, SpCostInfo.class);
		
		if(!reqData.isEmpty()&&StringUtils.isNotBlank(reqData.get(0).getDocumentNo())){
			for (SpCostInfo costInfo : reqData) {
				
				costInfo.setOrgID(orgID);
				costInfo.setUpdateTime(spVisitRecord.getUpdateTime());
				costInfo.setOptime(spVisitRecord.getOptime());
				costInfo.setLocalUserID(userID);
				costInfo.setSpVisitRecord(spVisitRecord);
				
				if(SpServiceConstant.COST_TYPE_NO.equals(costType)){
					costInfo.setCostType(SpServiceConstant.COST_TYPE_NO_PAID);
				}
				if(SpServiceConstant.COST_TYPE_YES.equals(costType)){
					costInfo.setCostType(SpServiceConstant.COST_TYPE_ALREADY_PAID);
				}
			}
			list.addAll(reqData);
		}
		return list;
	}
	/**  根据主键ID获取医嘱信息  */
	public SpDoctorAdviceInfo getDoctorAdviseInfo(Long spQueryMessageID){
		SpDoctorAdviceInfo doctorAdvise = spInterfaceDao.getObjectByID(spQueryMessageID, SpDoctorAdviceInfo.class);
		if(doctorAdvise==null){
			throw new RuntimeException("根据医嘱表ID没有找到对应的医嘱记录,医嘱ID为"+spQueryMessageID);
		}
		return doctorAdvise;
	}
	private String getEventIDsOfAdviseWithNoQuery(List<SpVisitRecord> spVisitRecords,Long userID){
		/*
		 * 就诊记录里列表
		 * 1.选出已完成的历史就诊记录（状态已完成,更新时间和操作时间不一致）
		 */
		List<SpVisitRecord> tmp_visitRecords = new ArrayList<SpVisitRecord>();
		String orgID = null;
		for (SpVisitRecord spVisitRecord : spVisitRecords) {
			if(orgID==null) orgID = spVisitRecord.getOrgID();
			if(SpServiceConstant.VISIT_RECORD_STATUS_COMPLETE.equals(spVisitRecord.getState())
					&&!spVisitRecord.getOptime().equals(spVisitRecord.getUpdateTime()))
				tmp_visitRecords.add(spVisitRecord);
		}
		/*
		 * 医嘱列表
		 */
		List<SpDoctorAdviceInfo> spDoctorAdviceInfos = spInterfaceDao.getObjectByUserID(userID, SpDoctorAdviceInfo.class,orgID);
		if(spDoctorAdviceInfos.isEmpty()) return "";
		/*
		 * 筛选不需要查询的就诊记录
		 * 条件
		 * 1.就诊ID相等
		 * 2.更新时间一致
		 */
		StringBuilder eventIDs = new StringBuilder();
		for (SpDoctorAdviceInfo spDoctorAdviceInfo : spDoctorAdviceInfos) {
			
			String tmp_advise_eventID = spDoctorAdviceInfo.getEventID();
			
			for (SpVisitRecord spVisitRecord : tmp_visitRecords) {
				
				if(tmp_advise_eventID.equals(spVisitRecord.getEventID())&&StringUtils.isNotBlank(spDoctorAdviceInfo.getUpdateTime())&&
						spDoctorAdviceInfo.getUpdateTime().equals(spVisitRecord.getUpdateTime())){
					
					spDoctorAdviceInfo.setOptime(spVisitRecord.getOptime());
					spInterfaceDao.updateObject(spDoctorAdviceInfo);
					
					if(eventIDs.indexOf(tmp_advise_eventID)==-1) eventIDs.append(tmp_advise_eventID).append(",");
				}
			}
		}
		return eventIDs.toString();
	}
	private String getEventIDsOfCostWithNoQuery(List<SpVisitRecord> spVisitRecords,Long userID,String costType){
		/*
		 * 就诊记录里列表
		 * 1.选出已完成的历史就诊记录（状态已完成,更新时间和操作时间不一致）
		 */
		List<SpVisitRecord> tmp_visitRecords = new ArrayList<SpVisitRecord>();
		String orgID = null;
		for (SpVisitRecord spVisitRecord : spVisitRecords) {
			if(orgID==null) orgID = spVisitRecord.getOrgID();
			if(SpServiceConstant.VISIT_RECORD_STATUS_COMPLETE.equals(spVisitRecord.getState())
					&&!spVisitRecord.getOptime().equals(spVisitRecord.getUpdateTime()))
				tmp_visitRecords.add(spVisitRecord);
		}
		/*
		 * 费用列表 
		 */
		List<SpCostInfo> spCostInfos = spInterfaceDao.getObjectByUserID(userID, SpCostInfo.class,orgID);
		if(spCostInfos.isEmpty()) return "";
		//找出同一就诊记录,所有已收费的记录
		Map<String,String> costRecord = new HashMap<String,String>();
		for (SpCostInfo spCostInfo : spCostInfos) {
			String pre_costType = spCostInfo.getCostType();
			String eventID = spCostInfo.getEventID();
			if(costRecord.containsKey(eventID)){
				String tmp_costType = costRecord.get(eventID);
				costRecord.put(eventID,tmp_costType+","+pre_costType);
			}else{
				costRecord.put(eventID,pre_costType);
			}
		}
		
		String tmp_payID_cost = "";
		for (Entry<String, String> entry : costRecord.entrySet()) {
			String value = entry.getValue();
			if(!value.contains(SpServiceConstant.COST_TYPE_NO_PAID)){
				tmp_payID_cost+=entry.getKey()+",";
			}
		}
		/*
		 * 筛选不需要查询的就诊记录
		 * 条件
		 * 1.就诊ID相等
		 * 2.更新时间一致
		 */
		StringBuilder eventIDs = new StringBuilder();
		for (SpCostInfo spCostInfo : spCostInfos) {
			
			String tmp_advise_eventID = spCostInfo.getEventID();
			if(!tmp_payID_cost.contains(tmp_advise_eventID)){
				continue;
			}
			for (SpVisitRecord spVisitRecord : tmp_visitRecords) {
				
				if(tmp_advise_eventID.equals(spVisitRecord.getEventID())&&
						spCostInfo.getUpdateTime().equals(spVisitRecord.getUpdateTime())){
					
					if(SpServiceConstant.COST_TYPE_YES.equals(costType)){
						spCostInfo.setOptime(spVisitRecord.getOptime());
						spInterfaceDao.updateObject(spCostInfo);
					}
					if(eventIDs.indexOf(tmp_advise_eventID)==-1) eventIDs.append(tmp_advise_eventID).append(",");
				}
			}
		}
		return eventIDs.toString();
	}
	/**
	 * 获取医嘱信息列表
	 * @param visitCardID 就诊卡ID
	 * @param pageNo 页号
	 * @param userID 用户ID
	 * @param timeFlag  查询时间标识,本月记录1,历史记录0
	 * @param orgID 苏坡机构ID
	 * @return
	 */
	@Override
	public List<SpDoctorAdviceInfo> saveSpDoctorAdviceInfos(Long visitCardID,Integer pageNo,Long userID,String timeFlag,String orgID){
		List<SpDoctorAdviceInfo> tmp_SpDoctorAdviceInfo = new ArrayList<SpDoctorAdviceInfo>();
		
		String opTime = null;
		if(pageNo<2){
			List<SpVisitRecord> spVisitRecords = this.saveSpVisitRecords(visitCardID,pageNo, userID, timeFlag,orgID);
			if(spVisitRecords.isEmpty()) return tmp_SpDoctorAdviceInfo;
			
			String eventIDsOfAdviseWithNoQuery = getEventIDsOfAdviseWithNoQuery(spVisitRecords, userID);
			/*
			 * 调用中联接口,获取本次就诊对应的医嘱信息
			 */
			for (SpVisitRecord spVisitRecord : spVisitRecords) {
				
				if(opTime==null) opTime = spVisitRecord.getOptime();
				
				String eventID = spVisitRecord.getEventID();
				String optime =  spVisitRecord.getOptime();
				String updateTime = spVisitRecord.getUpdateTime();
				
				if(eventIDsOfAdviseWithNoQuery.contains(eventID)) continue;
				
				SpDoctorAdviceInfo doctorAdviseInfo = new SpDoctorAdviceInfo();
				doctorAdviseInfo.setSp_OrgCode(orgID);
				doctorAdviseInfo.setSp_OperType("104");
				doctorAdviseInfo.setSp_EventID(eventID);
				List<SpDoctorAdviceInfo> reqData = getReqData(doctorAdviseInfo, SpDoctorAdviceInfo.class);
				
				if(!reqData.isEmpty()&&StringUtils.isNotBlank(reqData.get(0).getMedicalID())){
					for (SpDoctorAdviceInfo spDoctorAdviceInfo : reqData) {
						
						spDoctorAdviceInfo.setOrgID(orgID);
						spDoctorAdviceInfo.setOptime(optime);
						spDoctorAdviceInfo.setUpdateTime(updateTime);
						spDoctorAdviceInfo.setLocalUserID(userID);
						spDoctorAdviceInfo.setSpVisitRecord(spVisitRecord);
						
						String type = spDoctorAdviceInfo.getType();
						switch(type){
							case "1":type="长期";break;
							case "2":type="临时";break;
							default:throw new RuntimeException("医嘱信息中类型字段转不成功,类型编码为"+type);
						}
						spDoctorAdviceInfo.setType(type);
					}
					tmp_SpDoctorAdviceInfo.addAll(reqData);
				}
			}
		}
		return spInterfaceDao.saveOrUpdateDoctorAdviseInfo(tmp_SpDoctorAdviceInfo,userID,pageNo,opTime);
	}
	/**
	 * 获取挂号订单明细 
	 * @param orderID 本地订单ID
	 * @param deptFlag 部门标识（1儿保，2儿科）
	 * @return
	 * <p>没有订单明细,返回null</p>
	 */
	public SpRegisterOrderDetail getSpRegisterOrderDetail(Long orderID,String deptFlag){
		StringBuilder sb = new StringBuilder();
		sb.append("from SpRegisterOrderDetail c where 1=1 ");
		sb.append(" and c.localOrderId = ? ");
		String orderType = "";
		if(SpServiceConstant.CHILD_CARE_FLAG.equals(deptFlag)){
			orderType = "儿保";
		}else{
			orderType = "儿科";
		}
		sb.append(" and c.orderType = ? ");
		return spInterfaceDao.getObjectByCondition(sb.toString(), new Object[]{orderID,orderType});
	}
	/**
	 * 创建挂号记录
	 * <P>1.无卡挂号:不传入就诊卡ID</P>
	 * <P>1.有卡挂号:传入visitCardInfo中的个人ID,和用户名字</P>
	 * @param orderID 订单ID
	 * @param deptFlag 科室标识
	 * @param visitCardID 健康卡表ID
	 * @param visitCardInfo 挂号信息
	 * @return 创建成功true,失败false
	 */
	public BooleanMsg saveSpAppointmentRecord(Long orderID,String deptFlag,Long visitCardID,SpHealthcardManager visitCardInfo){
		//获取挂号订单明细
		SpRegisterOrderDetail orderDetail = getSpRegisterOrderDetail(orderID, deptFlag);
		if(orderDetail == null) throw new RuntimeException("错误信息:没有该挂号订单明细,本地订单ID为:"+orderID);
		SpHealthcardManager spHealthcardManager = null;
		if(visitCardID!=null){
			//获取健康卡号信息,并设置将卡号用户信息赋值到明细数据中
			spHealthcardManager = spInterfaceDao.getObjectByID(visitCardID, SpHealthcardManager.class);
			if(spHealthcardManager==null) throw new RuntimeException("错误信息:没有健康卡号,健康卡号ID："+visitCardID);
		}else{
			spHealthcardManager = visitCardInfo; 
		}
		if(spHealthcardManager.getResidentId()==null) throw new RuntimeException("预约挂号没有传入,个人ID");
		return registerAppointment(orderDetail, spHealthcardManager);
	}
	
	/**
	 * 预约挂号
	 * @param orderDetail
	 * @param visitInfo
	 * @return
	 */
	private BooleanMsg registerAppointment(SpRegisterOrderDetail orderDetail,SpHealthcardManager visitInfo){
		BooleanMsg booleaMsg = new BooleanMsg();
		
		orderDetail.setResidentId(visitInfo.getResidentId());
		orderDetail.setResidentName(visitInfo.getName());
		
		//组装明细数据,调用中联接口
		SpAppointmentRecord record = this.packageRegisterRecord(orderDetail);
		/*
		 * 挂当天的号,选择挂号
		 * 挂当天以后的后,选择预约
		 */
		long registerTime = DateManage.parseStr2Date_yyyy_MM_dd(orderDetail.getRegisterDate()).getTime();
		long nowTime =  DateManage.parseStr2Date_yyyy_MM_dd(DateManage.formatDateStr_yyyy_MM_dd(new Date())).getTime();
		if(registerTime==nowTime){
			orderDetail.setRegisterType("挂号");
			record.setSp_Type("0");//0、挂号 1、预约
		}else{
			orderDetail.setRegisterType("预约");
			record.setSp_Type("1");//0、挂号 1、预约
		}
		record.setSp_IsFree("1");//0、免费 1、收费
		
		
		List<SpAppointmentRecord> reqData = getReqData(record, SpAppointmentRecord.class);

		if(reqData.isEmpty()||StringUtils.isBlank(reqData.get(0).getEventID())) {
			orderDetail.setOrderStatus("挂号失败");
			spInterfaceDao.updateObject(orderDetail);
			
			booleaMsg.setMsg("挂号失败");
			booleaMsg.isTrue(false);
		}else{
			SpAppointmentRecord retReqData = reqData.get(0);
			
			//接口调用成功，将接口返回的信息，反写到订单明细中
			orderDetail.setOrderStatus("挂号成功");
			orderDetail.setRegisterNo(retReqData.getRegisteNo());
			orderDetail.setNo(retReqData.getNo());
			orderDetail.setAdd(retReqData.getAdd());
			orderDetail.setEventId(retReqData.getEventID());
			spInterfaceDao.updateObject(orderDetail);
			
			booleaMsg.setMsg("挂号成功");
			booleaMsg.isTrue(true);
		}
		return booleaMsg;
	}
	
	/**  封装挂号记录  */
	private SpAppointmentRecord packageRegisterRecord(SpRegisterOrderDetail orderDetail){
		SpAppointmentRecord record = new SpAppointmentRecord();
		
		String registerDate = orderDetail.getRegisterDate();
		
		record.setSp_RegisterID(orderDetail.getRegisterId());
		record.setSp_RegisterName(orderDetail.getRegisterName());
		record.setSp_ResidentID(orderDetail.getResidentId());
		record.setSp_ResidentName(orderDetail.getResidentName());
		record.setSp_OperType("102");
		record.setSp_OrgCode(orderDetail.getOrgId());
	 	record.setSp_DepID(orderDetail.getDeptId());
	 	record.setSp_DepName(orderDetail.getDeptName());
		record.setSp_DoctorID(orderDetail.getDoctorId());
		record.setSp_Doctor(orderDetail.getDoctorName());
		record.setSp_RegisterDate(registerDate);
		record.setSp_UserID(SpServiceConstant.KYBABY_ID);
		record.setSp_UserName(SpServiceConstant.KYBABY_NAME);
		return record;
	}
	/**
	 * 获取用户绑定的所有健康卡号信息
	 * @param userID 用户ID
	 * @return 
	 * <p>1.如果返回的集合为空,用户没有绑定就诊卡</p>
	 */
	public List<SpHealthcardManager> getHealthCardOfUserOwneds(Long userID){
		
		List<SpHealthcardManager> healthCardOfUserOwneds = spInterfaceDao.getHealthCardOfUserOwneds(userID);
		
		for (SpHealthcardManager visitCardInfo : healthCardOfUserOwneds) {
			String healthcardNum = visitCardInfo.getHealthcardNum();
			if(StringUtils.isNotBlank(healthcardNum))
			visitCardInfo.setHealthcardNum(healthcardNum.substring(SpServiceConstant.HEALTH_CARD_PREFIX.length()));
		}
		
		return healthCardOfUserOwneds;
	}
	/**
	 * 绑定用户健康卡号 
	 * @param userID 用户ID
	 * @param visitCardInfo 就诊卡信息
	 * @param spOrgID 苏坡机构ID
	 * @param visitCardPrefix 就诊卡前缀
	 * @return 成功true,失败false
	 */
	public BooleanMsg saveHealthCard(Long userID,SpHealthcardManager visitCardInfo){
		if(visitCardInfo.isEmpty()) throw new IllegalArgumentException("绑定就诊卡,传入参数有误");
		
		visitCardInfo.setHealthcardNum(SpServiceConstant.HEALTH_CARD_PREFIX+visitCardInfo.getHealthcardNum());
		
		SpUserInfo spUserInfo = this.querySpUserInfo(visitCardInfo);
		BooleanMsg booleaMsg = new BooleanMsg();
		if(spUserInfo==null) {
			booleaMsg.setMsg("没有查到相关信息,请核对就诊卡号");
			booleaMsg.isTrue(false);
			return booleaMsg;
		}
		/*
		 * 查看是否已有用户绑定该卡
		 */
		String residentID = spUserInfo.getResidentID();
		String hql = "from SpHealthcardManager c where c.healthcardNum = ? ";
		SpHealthcardManager query_card = spInterfaceDao.getObjectByCondition(hql, new Object[]{visitCardInfo.getHealthcardNum()});
		if(query_card!=null) {
			booleaMsg.setMsg("就诊卡号已有用户绑定");
			booleaMsg.isTrue(false);
			return booleaMsg;
		}; 
		
		visitCardInfo.setUserid(userID);
		visitCardInfo.setResidentId(residentID);
		spInterfaceDao.saveObject(visitCardInfo);
		booleaMsg.isTrue(true);
		return booleaMsg;
	}
	/**
	 * 选择健康卡
	 * <p>1.传入就诊卡信息,是修改对应就诊卡信息</p>
	 * <p>2.不传入就诊卡信息,是修改用户默认就诊卡信息</p>
	 * @param userID 用户ID
	 * @param visitCardInfo	就诊卡信息
	 * @return 修改成功返回true,失败返回false
	 */
	public boolean updateHealthCardOfUserUsed(Long userID,SpHealthcardManager visitCardInfo){
		UserInfo userInfo = spInterfaceDao.getObjectByID(userID, UserInfo.class);
		if(visitCardInfo.isEmpty()){
			List<SpHealthcardManager> healthCardOfUserOwneds = this.getHealthCardOfUserOwneds(userID);
			for (SpHealthcardManager spHealthcardManager : healthCardOfUserOwneds) {
				spHealthcardManager.setDefaultSet("0");
				userInfo.setSpHealthcardManager(null);
				if(spHealthcardManager.getId()==visitCardInfo.getId()){
					if(SpServiceConstant.VISIT_CARD_DEFAULTSET_YES.equals(visitCardInfo.getDefaultSet())){
						spHealthcardManager.setDefaultSet(SpServiceConstant.VISIT_CARD_DEFAULTSET_YES);
						userInfo.setSpHealthcardManager(spHealthcardManager);
					}
				}
			}
			spInterfaceDao.updateObject(userInfo);
			spInterfaceDao.updateObjectWithBatch(healthCardOfUserOwneds);
		}else{
			SpHealthcardManager query_visitCardInfo = spInterfaceDao.getObjectByID(visitCardInfo.getId(), SpHealthcardManager.class);
			visitCardInfo.setHealthcardNum(SpServiceConstant.HEALTH_CARD_PREFIX+visitCardInfo.getHealthcardNum());
			SpUserInfo spUserInfo = this.querySpUserInfo(visitCardInfo);
			
			if(spUserInfo==null) return false;
			
			visitCardInfo.setUserid(userID);
			visitCardInfo.setResidentId(spUserInfo.getResidentID());
			setDefaultVisitCard(userID, query_visitCardInfo);
			//修改默认就诊卡
			SpHealthcardManager tmp_visitCardInfo = new SpHealthcardManager();
			BeanUtils.copyProperties(query_visitCardInfo, tmp_visitCardInfo);
			BeanUtils.copyProperties(visitCardInfo, query_visitCardInfo, new String[]{"id"});
			spInterfaceDao.updateObject(query_visitCardInfo);
			//设置默认后,修改用户信息里的默认就诊卡信息
			if(SpServiceConstant.VISIT_CARD_DEFAULTSET_YES.equals(tmp_visitCardInfo.getDefaultSet())){
				if(SpServiceConstant.VISIT_CARD_DEFAULTSET_YES.equals(visitCardInfo.getDefaultSet())){
					
				}else{
					userInfo.setSpResidentName(null);
					userInfo.setSpHealthcardManager(null);
					userInfo.setSpResidentID(null);
					userInfo.setSpHealthCardNum(null);
					spInterfaceDao.updateObject(userInfo);
				}
			}else{
				if(SpServiceConstant.VISIT_CARD_DEFAULTSET_YES.equals(visitCardInfo.getDefaultSet())){
					userInfo.setSpResidentName(query_visitCardInfo.getName());
					userInfo.setSpHealthcardManager(query_visitCardInfo);
					userInfo.setSpResidentID(query_visitCardInfo.getResidentId());
					userInfo.setSpHealthCardNum(query_visitCardInfo.getHealthcardNum());
					spInterfaceDao.updateObject(userInfo);
				}else{
				}
			}
		}
		return true;
	}
	
	/**
	 * 设置设置默认就诊信息
	 * @param userID
	 * @param visitCardInfo
	 */
	private void setDefaultVisitCard(Long userID,SpHealthcardManager visitCardInfo){
		List<SpHealthcardManager> healthCardOfUserOwneds = this.getHealthCardOfUserOwneds(userID);
		for (SpHealthcardManager spHealthcardManager : healthCardOfUserOwneds) {
			spHealthcardManager.setDefaultSet("0");
			if(spHealthcardManager.getId()==visitCardInfo.getId()){
				if(SpServiceConstant.VISIT_CARD_DEFAULTSET_YES.equals(visitCardInfo.getDefaultSet())){
					spHealthcardManager.setDefaultSet(SpServiceConstant.VISIT_CARD_DEFAULTSET_YES);
				}
			}
		}
		spInterfaceDao.updateObjectWithBatch(healthCardOfUserOwneds);
	}
	
	/**
	 * 保存苏坡挂号订单明细数据
	 * @param id 订单id
	 * @param deptFlag 部门标识：1为儿保2为儿科
	 * @return
	 */
	public BooleanMsg saveRegisterOrderData(Long id,String deptFlag,String spOrgID){
		
		SpRegisterOrderDetail orderDetail = new SpRegisterOrderDetail();
		
		if(SpServiceConstant.CHILD_CARE_FLAG.equals(deptFlag)){
			UserChildcareAppointmentInfo childCare = spInterfaceDao.getObjectByID(id, UserChildcareAppointmentInfo.class);
			if(childCare==null) throw new RuntimeException("没有查询到对应订单信息,儿保订单ID为:"+id);
			
			orderDetail = packageRegisterOrder(childCare);
			orderDetail.setOrderType("儿保");
			orderDetail.setDeptName(SpServiceConstant.DEPT_NAME_CHILD_CARE);
		}else{
			OrderInfoClinic peadiatrics = spInterfaceDao.getObjectByID(id, OrderInfoClinic.class);
			if(peadiatrics==null) throw new RuntimeException("没有查询到对应订单信息,儿科订单ID为:"+id);
			
			orderDetail = packageRegisterOrder(peadiatrics);
			orderDetail.setOrderType("儿科");
			orderDetail.setDeptName(SpServiceConstant.DEPT_NAME_PEADIATRICS);
		}
		
		orderDetail.setOrgId(spOrgID);
		if(SpServiceConstant.NINE_HOSPITAL_CODE.equals(spOrgID)){
			orderDetail.setOrgName("青羊区第九人民医院");
		}else{
			orderDetail.setOrgName("苏坡社区卫生服务中心");
		}
		//设置挂号排班信息
		orderDetail = setOrderRegisterInfo(orderDetail);
		
		BooleanMsg booleanMsg = new BooleanMsg();
		if(orderDetail.getRegisterId()==null){
			booleanMsg.setMsg("挂号失败,没有号源");
			booleanMsg.isTrue(false);
		}else{
			orderDetail.setOpUserID(SpServiceConstant.KYBABY_ID);
			orderDetail.setOpUserName(SpServiceConstant.KYBABY_NAME);
			spInterfaceDao.saveObject(orderDetail);
			booleanMsg.isTrue(true);
		}
		
		return booleanMsg;
	}
	
	private SpRegisterOrderDetail packageRegisterOrder(UserChildcareAppointmentInfo childCare){
		SpRegisterOrderDetail orderDetail = new SpRegisterOrderDetail();
		/*
		 * 用户数据
		 */
		orderDetail.setLocalUserId(childCare.getUserInfo().getId());
		/*
		 * 订单数据
		 */
		OrganChildcareOpenResources organChildcareOpenResources = childCare.getOrganChildcareOpenResources();
		 OrganChildcareOpenResourcesDatail organChildcareOpenResourcesDatail = childCare.getOrganChildcareOpenResourcesDatail();
		orderDetail.setRegisterDate(organChildcareOpenResources.getOpenDate()
				+" "+organChildcareOpenResourcesDatail.getOpenStartTime()+":00");
		orderDetail.setLocalOrderId(childCare.getId());
		orderDetail.setOrderStatus("待支付");
		orderDetail.setRegisterMoney(childCare.getTotalPrice());
		orderDetail.setOptime(childCare.getOperationTime());
		/*
		 * 医生数据
		 */
		DoctorInfo doctorInfo = childCare.getDoctorInfo();
		orderDetail.setDoctorName(doctorInfo.getDoctorName());
		
		return orderDetail;
	}
	private SpRegisterOrderDetail packageRegisterOrder(OrderInfoClinic peadiatrics){
		SpRegisterOrderDetail orderDetail = new SpRegisterOrderDetail();
		/*
		 * 用户数据
		 */
		orderDetail.setLocalUserId(peadiatrics.getUserInfo().getId());
		/*
		 * 订单数据
		 */
		peadiatrics.getAppointmentBeganTime();
		orderDetail.setRegisterDate(peadiatrics.getAppointmentDate()+" "+peadiatrics.getAppointmentBeganTime()+":00");
		orderDetail.setLocalOrderId(peadiatrics.getId());
		orderDetail.setOrderStatus("待支付");
		orderDetail.setRegisterMoney(peadiatrics.getTotalPrice());
		orderDetail.setOptime(peadiatrics.getOrderTime());
		/*
		 * 医生数据
		 */
		DoctorInfo doctorInfo = peadiatrics.getDoctorInfo();
		orderDetail.setDoctorName(doctorInfo.getDoctorName());
		
		return orderDetail;
	}
	/**
	 * 判断是否有号源
	 * @param orgID 苏坡机构ID
	 * @param deptName 苏坡部门名称
	 * @param doctorName 医生名称
	 * @return
	 */
	public boolean hasSourseWithRegister(String orgID,String deptName,String doctorName){
		SpRegisterOrderDetail orderDetail = new SpRegisterOrderDetail();
		orderDetail.setOrgId(orgID);
		orderDetail.setDeptName(deptName);
		orderDetail.setDoctorName(doctorName);
		
		orderDetail = setOrderRegisterInfo(orderDetail);
		
		if(orderDetail.getRegisterId()==null){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 查询对应的挂号排班信息
	 * <p>1.有医生ID,查找到对应的医生排班信息,设置该信息</p>
	 * <p>2.没有医生ID,查询kybaby专科医生排班信息,设置该信息</p>
	 */
	private SpRegisterOrderDetail setOrderRegisterInfo(SpRegisterOrderDetail orderDetail){
		String orgID = orderDetail.getOrgId();
		/*SpDoctorInfo spDoctorInfo = spInterfaceDao.getSpDoctorInfo(orgID,
				orderDetail.getDeptName(), orderDetail.getDoctorName());*/
		/*
		 * 1.如果有是苏坡医生,挂苏坡医生的号
		 * 2.如果没有找到医生,走虚拟全科医生
		 */
		SpAppointmentSchedule registerScheduleInfo = spInterfaceDao.getRegisterScheduleInfo(orgID, 
				orderDetail.getDeptName(), orderDetail.getDoctorName());
		if(registerScheduleInfo==null){
			//获取苏坡机构的虚拟医生名字
			SpDoctorAlias spDoctorAlias = spInterfaceDao.getSpDoctorAlias(orgID);
			if(spDoctorAlias==null) throw new RuntimeException(orderDetail.getOrgName()+",没有配置虚拟医生");
			else orderDetail.setDoctorName(spDoctorAlias.getDoctorName()+"-"+orderDetail.getDoctorName());
			registerScheduleInfo = 
					spInterfaceDao.getRegisterScheduleInfo(orgID,orderDetail.getDeptName(), spDoctorAlias.getDoctorName());
		}
		
		if(registerScheduleInfo!=null){
			orderDetail.setDeptId(registerScheduleInfo.getDepID());
			orderDetail.setDoctorId(registerScheduleInfo.getDoctorID());
			orderDetail.setRegisterId(registerScheduleInfo.getRegisterID());
			orderDetail.setRegisterName(registerScheduleInfo.getRegisterName());
		}
		
		return orderDetail;
	}
	/**
	 * 根据就诊卡号获取苏坡居名信息
	 * @param visitCardInfo 就诊卡信息
	 * @return
	 * <p>1.如果返回结果为空，表示没有查询到用户信息</p>
	 * <p>2.传入信息与获取居民信息做匹配,匹配不成功,返回null</p>
	 */
	public SpUserInfo querySpUserInfo(SpHealthcardManager visitCardInfo) {
		SpUserInfo userInfo = new SpUserInfo();
		userInfo.setSp_OperType("001");
		
		String [] orgIDs = new String[]{SpServiceConstant.NINE_HOSPITAL_CODE,SpServiceConstant.SP_HOSPITAL_CODE};
		SpUserInfo has_userInfo = null;
		
		for (String orgID : orgIDs) {
			userInfo.setSp_OrgCode(orgID);
			userInfo.setSp_QueryString(visitCardInfo.getHealthcardNum());
			List<SpUserInfo> reqData = this.getReqData(userInfo, SpUserInfo.class);
			
			for (SpUserInfo spUserInfo : reqData) {
				switch(spUserInfo.getSexCD()){
					case "1":spUserInfo.setSexCD("男");break;
					case "2":spUserInfo.setSexCD("女");break;
					case "9":spUserInfo.setSexCD("未知");break;
				}
				if(visitCardInfo.getName().equals(spUserInfo.getResidentName())
						&&visitCardInfo.getSex().equals(spUserInfo.getSexCD())){
					has_userInfo = spUserInfo;break;
				}
			}
			
			if(has_userInfo==null) LogUtil.warn("使用机构ID"+orgID+"下没有查询到用户信息,姓名:"+visitCardInfo.getName()+"，性别"+visitCardInfo.getSex());
			else break;
		}
		
		return has_userInfo;
	}
	/**
	 * 将对象obj中特殊的字段转换为xml串,调用中联接口,将返回数据转换为clazz对应的对象集合
	 * @param obj 需要组装成xml的对象(输入对象,接口请求参数)
	 * @param clazz 返回集合的对象的类型(输出对象,返回数据转换的对象类型)
	 * @return
	 * <P>1.接口调用失败,返回空集合</P>
	 * <P>2.接口调用成功,返回成功编码为0,--》接口调用失败,返回空集合</P>
	 * <P>3.接口调用成功,返回成功编码为1,但是必要数据为空--》接口调用失败,返回空集合::目前没抽出</P>
	 */
	private <T> List<T> getReqData(Object obj,Class<T> clazz){
		String request = SpInterfaceUtil.createXml(obj);
		List<T> list = new ArrayList<T>();
		String msg = "";
		String res = "";
		Throwable t = null;
		try {
			res = spInterface.interactionOperating(request);
			list = SpInterfaceUtil.xml2JavaBean(res,clazz);
		} catch (SpServiceException e) {
			msg="调用接口成功,返回错误编码,返回信息："+res;
			t = e;
		} catch (Exception e) {
			msg="调用接口失败,异常信息："+e.toString()+(StringUtils.isNotBlank(res)?res:"");
			t = e;
		}
		if(StringUtils.isNotBlank(msg)){
			SpExceptionMsg exMsg = new SpExceptionMsg(request,msg,DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(new Date()));
			spInterfaceDao.saveObject(exMsg);
			LogUtil.error(msg,t);
		}
		return list;
	}
	public void setSpInterfaceDao(SpInterfaceDao spInterfaceDao) {
		this.spInterfaceDao = spInterfaceDao;
	}
	public ToolInterfaceSoap getSpInterface() {
		return spInterface;
	}
	public void setSpInterface(ToolInterfaceSoap spInterface) {
		this.spInterface = spInterface;
	}
	public SpInterfaceDao getSpInterfaceDao() {
		return spInterfaceDao;
	}

	public void setClinicOrderService(ClinicOrderService clinicOrderService) {
		this.clinicOrderService = clinicOrderService;
	}

	public void setUserAccountBo(UserAccountBo userAccountBo) {
		this.userAccountBo = userAccountBo;
	}

	public void setUserInfoBo(UserInfoBo userInfoBo) {
		this.userInfoBo = userInfoBo;
	}

	public void setChildCareChargeService(
			ChildCareChargeService childCareChargeService) {
		this.childCareChargeService = childCareChargeService;
	}
}
