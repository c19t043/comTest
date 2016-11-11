package com.kybaby.newbussiness.medicalorgandbusiness.action;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.domain.SymptomTag;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ClinicMedicalRecords;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DrugInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.HospitalAddressInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganOperator;
import com.kybaby.newbussiness.medicalorgandbusiness.fo.OrderCountInfo;
import com.kybaby.newbussiness.ordermanager.domain.BabyBasicData2;
import com.kybaby.util.ConstantManage;
import com.opensymphony.xwork2.ActionContext;

/**
 * 机构门诊管理
 * @author lihao
 *
 */
public class OrgClinicManager  extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 登录人信息
	 */
	private OrganOperator organOperator;
	/**
	 * 门诊订单列表
	 */
	private List<OrderInfoClinic> orderInfoClinicList = new ArrayList<>();
	/**
	 * 门诊订单信息
	 */
	private OrderInfoClinic orderInfoClinic;
	/**
	 * 用户信息
	 */
	private UserInfo userInfo;
	/**
	 * 就诊记录信息
	 */
	private ClinicMedicalRecords clinicMedicalRecords;
	/**
	 * 就诊记录列表
	 */
	private List<ClinicMedicalRecords> clinicMedicalRecordsList = new ArrayList<>();
	/**
	 * 健康档案
	 */
	private BabyBasicData2 babyBasicData;
	/**
	 * 诊状标签列表
	 */
	private List<SymptomTag> symptomTagList = new ArrayList<>();
	/**
	 * 保存数据标示
	 */
	private String flag;
	private String currentTime;
	private String currentWeekDate;
	/**
	 * 数量统计信息
	 */
	private OrderCountInfo orderCountInfo = new OrderCountInfo();
	@Override
	public String execute() {
		organOperator = (OrganOperator)ActionContext.getContext().getSession().get("organOperator");
		if(organOperator==null){
			mes="请登录";
			return "fail";
		}
		/**
		 * 得到门诊订单信息列表
		 */
		if(action.equals("getOrderInfoClinicList")){
			if(orderInfoClinic == null){
				orderInfoClinic = new OrderInfoClinic();
				orderInfoClinic.setAppointmentDate(DateManage.getDateStr("yyyy-MM-dd"));//默认查当天的数据
			}
			//得到机构地址列表
			List<HospitalAddressInfo> getHospitalAddressInfoList = 
					orgClinicService.getHospitalAddressInfoList(organOperator.getHospitalBasicInfo());
			if(getHospitalAddressInfoList != null){
				for(HospitalAddressInfo address : getHospitalAddressInfoList){
					orderInfoClinic.setClinicAddress(address.getAddress());
					List<OrderInfoClinic> orderList = this.orgClinicService.getOrderInfoClinicList(orderInfoClinic);
					if(orderList != null){
						this.orderInfoClinicList.addAll(orderList);
					}
				}
			}else{
				orderInfoClinic.setClinicAddress(organOperator.getHospitalBasicInfo().getHospitalLname());
				this.orderInfoClinicList = this.orgClinicService.getOrderInfoClinicList(orderInfoClinic);
			}
			this.getOrderCount(orderInfoClinicList);
		}
		/**
		 * 得到用户的所有门诊订单信息列表
		 */
		if(action.equals("getOrderInfoClinicListByUser")){
			this.orderInfoClinicList = this.orgClinicService.getOrderInfoClinicList(orderInfoClinic);
		}
		/**
		 * 得到患者的就诊记录
		 */
		if(action.equals("getClinicMedicalRecordsListByUser")){
			this.clinicMedicalRecordsList = this.orgClinicService.getClinicMedicalRecordsList(null, userInfo);
		}
		/**
		 * 得到系统当前时间
		 */
		else if(action.equals("getCurrentTime")){
			this.currentTime = DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
			//this.currentWeekDate = DateManage.getWeekByZhou(new Date());
		}
		/**
		 * 保存或更新门诊就诊记录信息
		 */
		else if(action.equals("saveOrUpdateClinicMedicalRecords")){
			ClinicMedicalRecords old = this.orgClinicService.getClinicMedicalRecordsById(clinicMedicalRecords.getId());
			if("诊断信息".equals(flag)){
				old.setDiagnosticInformation(clinicMedicalRecords.getDiagnosticInformation());
				old.setDrugIds(clinicMedicalRecords.getDrugIds());
			}else if("症状描述".equals(flag)){
				old.setSymptomDescribe(clinicMedicalRecords.getSymptomDescribe());
				old.setSymptomTagIds(clinicMedicalRecords.getSymptomTagIds());
			}
			this.orgClinicService.saveOrUpdateClinicMedicalRecords(old);
		}
		/**
		 * 门诊就诊订单信息
		 */
		else if(action.equals("getOrderContactsInfoByOrderId")){
			this.clinicMedicalRecords = this.orgClinicService.getClinicMedicalRecords(orderInfoClinic.getId());
			this.orderInfoClinic = this.doctorClinicOrderService.getOrderInfoClinicById(orderInfoClinic.getId());
			if(clinicMedicalRecords == null){//初始化一条记录
				ClinicMedicalRecords initObject = new ClinicMedicalRecords();
				initObject.setOrderInfoClinic(orderInfoClinic);
				Long id = this.orgClinicService.saveOrUpdateClinicMedicalRecords(initObject);
				this.clinicMedicalRecords = this.orgClinicService.getClinicMedicalRecords(id);
			}
			this.clinicMedicalRecordsList = this.orgClinicService.getClinicMedicalRecordsList(null, orderInfoClinic.getUserInfo());
			String nowDate = DateManage.getDateStr("yyyy-MM-dd");
			for(ClinicMedicalRecords cmr : clinicMedicalRecordsList){
				if(nowDate.equals(cmr.getOrderInfoClinic().getAppointmentDate())){
					cmr.setHandleFlag("update");
				}else{
					cmr.setHandleFlag("view");
				}
			}
		}
		/**
		 * 门诊就诊记录信息
		 */
		else if(action.equals("getClinicMedicalRecords")){
			this.clinicMedicalRecords = this.orgClinicService.getClinicMedicalRecords(orderInfoClinic.getId());
			if(clinicMedicalRecords != null && clinicMedicalRecords.getDrugIds() != null && 
					!"".equals(clinicMedicalRecords.getDrugIds())){
				List<DrugInfo> drugInfoOldList = new ArrayList<>();
				String drugIds[] = clinicMedicalRecords.getDrugIds().split(",");
				for(int i=0;i<drugIds.length; i++){
					DrugInfo drug = this.drugInfoService.getDrugInfoById(Long.valueOf(drugIds[i]));
					drugInfoOldList.add(drug);
				}
				clinicMedicalRecords.setDrugInfoList(drugInfoOldList);
			}
		}
		/**
		 * 得到宝宝健康档案信息
		 */
		else if(action.equals("getBabyBasicData2")){
			OrderInfoClinic oldOrder = this.doctorClinicOrderService.getOrderInfoClinicById(orderInfoClinic.getId());
			Long userId = oldOrder.getUserInfo().getId();
			List<BabyBasicData2> babyList = this.orderManagerService.getBabyBasicData2ListByUserId(userId);
			if(babyList != null){
				babyBasicData = babyList.get(0);
			}
		}
		/**
		 * 保存或更新健康档案
		 */
		else if(action.equals("saveOrUpdateBabyBasicData")){
			OrderInfoClinic order = this.doctorClinicOrderService.getOrderInfoClinicById(orderInfoClinic.getId());
			babyBasicData.setUserId(order.getUserInfo().getId());
			babyBasicData.setDoctorId(order.getDoctorInfo().getId());
			orderManagerService.saveOrUpdateBabyBasicData(babyBasicData);
		}
		/**
		 * 得到症状标签
		 */
		else if(action.equals("getAllSymptomTag")){
			this.symptomTagList = this.orgClinicService.getAllSymptomTag(null);
		}
		return "success";
	}
	/**
	 * 儿保列表统计
	 * @param allList
	 */
	private void getOrderCount(List<OrderInfoClinic> orderInfoClinicList){
		// 已预月人数
		int bookingSum = 0;
		// 已预检人数
		int havePreSum = 0;
		 //已登记人数
		int registeredSum = 0;
		 // 已完成人数
		Integer finishedSum = 0;
		// 已会面人数
		int meetingSum = 0;
		if(orderInfoClinicList != null){
			for(OrderInfoClinic uiai : orderInfoClinicList){
				//统计各种状态的订单数量
				if(ConstantManage.HASE_BOOKED_VACCINE.equals(uiai.getOrderStatus())){
					bookingSum ++;
				}
				if(ConstantManage.HASE_PREVIEW_VACCINE.equals(uiai.getOrderStatus())){
					havePreSum ++;
				}
				if(ConstantManage.HASE_REGISTER_VACCINE.equals(uiai.getOrderStatus())){
					registeredSum ++;
				}
				if(ConstantManage.HASE_FINISHED_CLINIC_ORDER.equals(uiai.getOrderStatus())){
					finishedSum ++;
				}
				if(ConstantManage.HASE_MEET_CLINIC_ORDER.equals(uiai.getOrderStatus())){
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

	public List<OrderInfoClinic> getOrderInfoClinicList() {
		return orderInfoClinicList;
	}

	public void setOrderInfoClinicList(List<OrderInfoClinic> orderInfoClinicList) {
		this.orderInfoClinicList = orderInfoClinicList;
	}

	public OrderInfoClinic getOrderInfoClinic() {
		return orderInfoClinic;
	}

	public void setOrderInfoClinic(OrderInfoClinic orderInfoClinic) {
		this.orderInfoClinic = orderInfoClinic;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public ClinicMedicalRecords getClinicMedicalRecords() {
		return clinicMedicalRecords;
	}

	public void setClinicMedicalRecords(ClinicMedicalRecords clinicMedicalRecords) {
		this.clinicMedicalRecords = clinicMedicalRecords;
	}

	public OrganOperator getOrganOperator() {
		return organOperator;
	}

	public void setOrganOperator(OrganOperator organOperator) {
		this.organOperator = organOperator;
	}

	public BabyBasicData2 getBabyBasicData() {
		return babyBasicData;
	}

	public void setBabyBasicData(BabyBasicData2 babyBasicData) {
		this.babyBasicData = babyBasicData;
	}

	public List<SymptomTag> getSymptomTagList() {
		return symptomTagList;
	}

	public void setSymptomTagList(List<SymptomTag> symptomTagList) {
		this.symptomTagList = symptomTagList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public String getCurrentWeekDate() {
		return currentWeekDate;
	}

	public void setCurrentWeekDate(String currentWeekDate) {
		this.currentWeekDate = currentWeekDate;
	}

	public List<ClinicMedicalRecords> getClinicMedicalRecordsList() {
		return clinicMedicalRecordsList;
	}

	public void setClinicMedicalRecordsList(
			List<ClinicMedicalRecords> clinicMedicalRecordsList) {
		this.clinicMedicalRecordsList = clinicMedicalRecordsList;
	}
	public OrderCountInfo getOrderCountInfo() {
		return orderCountInfo;
	}
	public void setOrderCountInfo(OrderCountInfo orderCountInfo) {
		this.orderCountInfo = orderCountInfo;
	}

}
