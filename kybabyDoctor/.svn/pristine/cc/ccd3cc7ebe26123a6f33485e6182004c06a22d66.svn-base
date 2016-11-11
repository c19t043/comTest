package com.kybaby.newbussiness.doctorclinic.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgClinicdate;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgTimeSetting;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePracticeOrgInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.util.ConstantManage;
import com.opensymphony.xwork2.ActionContext;

public class DoctorClinicAction extends NewBaseAction{
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
	 * 医生门诊及多点执业设置信息
	 */
	private DoctorMorePractice doctorMorePractice;
	/**
	 * 医生门诊及多点执业设置信息
	 */
	private List<DoctorMorePractice> doctorMorePracticeList = new ArrayList<DoctorMorePractice>();
	/**
	 * 医生门诊及多点执业地址列表
	 */
	private List<String> addressList = new ArrayList<String>();
	/**
	 *  多点执业机构
	 */
	private DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo;
	/**
	 * 多点执业机构集合
	 */
	private List<DoctorMorePracticeOrgInfo>  morePracticeOrgList = new ArrayList<DoctorMorePracticeOrgInfo>();
	/**
	 * 多点执业机构时间配置信息集合
	 */
	private List<DoctorMoreOrgTimeSetting>  morePracticeOrgTimeList = new ArrayList<DoctorMoreOrgTimeSetting>();
	/**
	 * 多点执业服务标签
	 */
	private List<DoctorServiceType>  doctorServiceTypeList = new ArrayList<DoctorServiceType>();
	/**
	 * 多点执业机构时间id  json串
	 */
	private String allDayMoreOrgTimeSetIdJson;
	/**
	 * 医院信息
	 */
	private HospitalBasicInfo hospitalBasicInfo;
	/**
	 * 当前日期
	 */
	private String currentTime;
	
	@Override
	public String execute(){
		System.out.println("============DoctorClinicAction execute==============");
		doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
		if(doctorInfo==null){
			mes="请登录";
			return "fail";
		}
		this.doctorInfo = this.doctorInfoBo.getDoctorInfoBoById(doctorInfo.getId());
		/**
		 * 保存或更新医生设置的门诊信息
		 */
		if(action.equals("saveOrUpdateDoctorMorePractice")){
			//检查门诊时间是否有人预约(检查是否有用户预约)
			Boolean flag = this.doctorClinicService.checkTimeIsUsed(doctorMorePractice.getClinicDate(), null, doctorInfo,doctorMorePractice.getClinicOrgType());
			if(flag){
				this.mes = "已有用户预约";
				return "fail";
			}
			doctorMorePractice.setDoctorInfo(doctorInfo);
			//如果为本单位门诊，门诊地址则存储当前医生的工作单位名称和门诊具体地址
			if(ConstantManage.CLINIC_ORG_TYPE_0.equals(doctorMorePractice.getClinicOrgType())) {
				//检查设置的日期是否被多点占用
				DoctorMorePractice dmp = new DoctorMorePractice();
				dmp.setClinicOrgType(ConstantManage.CLINIC_ORG_TYPE_1);
				dmp.setClinicDate(doctorMorePractice.getClinicDate());
				List<DoctorMorePractice> list_1 = this.doctorClinicService.getDoctorMorePracticeList(dmp, doctorInfo);
				if(list_1 != null){
					this.mes = "多点机构已约";
					return "fail";
				}
				HospitalBasicInfo hosp = this.doctorClinicService.getHospitalBasicInfoByNameOrId(doctorInfo.getDoctorEmployer(), null);
				doctorMorePractice.setClinicOrg(hosp==null?"":hosp.getHospitalLname());
				if(doctorMorePractice.getClinicAddress() == null || "".equals(doctorMorePractice.getClinicAddress())){
					doctorMorePractice.setClinicAddress(hosp.getAddress());
				}
			} else {
				//检查设置的日期是否被工作单位占用
				DoctorMorePractice dmp = new DoctorMorePractice();
				dmp.setClinicOrgType(ConstantManage.CLINIC_ORG_TYPE_0);
				dmp.setClinicDate(doctorMorePractice.getClinicDate());
				List<DoctorMorePractice> list_0 = this.doctorClinicService.getDoctorMorePracticeList(dmp, doctorInfo);
				if(list_0 != null){
					this.mes = "工作单位已约";
					return "fail";
				}
				//检查设置的日期是否被多点占用
				dmp.setClinicOrgType(ConstantManage.CLINIC_ORG_TYPE_1);
				dmp.setClinicDate(doctorMorePractice.getClinicDate());
				List<DoctorMorePractice> list_1 = this.doctorClinicService.getDoctorMorePracticeList(dmp, doctorInfo);
				if(list_1 != null){
					this.mes = "多点机构已约";
					return "fail";
				}
				//如果为多点执业门诊，门诊地址则存储当前执业机构的名称和地址，还需要保存DoctorMorePracticeOrgInfo对象字段
				if (doctorMorePractice.getDoctorMorePracticeOrgInfo()!=null && doctorMorePractice.getDoctorMorePracticeOrgInfo().getId()!=null) {
					doctorMorePractice.setDoctorMorePracticeOrgInfo(doctorClinicService.getDoctorMorePracticeOrgInfoByid(doctorMorePractice.getDoctorMorePracticeOrgInfo().getId()));
				}
			}
			//保存门诊设置信息
			doctorMorePractice.setOrgClinicStatus(ConstantManage.HASE_BOOKED_CLINIC_ORDER);
			Long id = this.doctorClinicService.saveOrUpdateDoctorMorePractice(doctorMorePractice);
			
			this.doctorMorePractice = this.doctorClinicService.getDoctorMorePracticeById(id);
			//将门诊时间分段保存
			if(ConstantManage.CLINIC_ORG_TYPE_0.equals(doctorMorePractice.getClinicOrgType())){
				this.doctorClinicService.saveOrUpdateDoctorClinicTimeSegment(doctorMorePractice);
			}else if(ConstantManage.CLINIC_ORG_TYPE_1.equals(doctorMorePractice.getClinicOrgType())){
				Long num = this.doctorClinicService.addMoreOrgTimeSegment(allDayMoreOrgTimeSetIdJson, doctorMorePractice);
				String timeIds = "";
				doctorMorePractice.setCanClinicNum(num);
				JSONArray array = JSONArray.fromObject(allDayMoreOrgTimeSetIdJson); 
				for(int i = 0; i < array.size(); i++){ 
					JSONObject jo = array.getJSONObject(i);
					String timeId = jo.get("id").toString();
					timeIds += timeId+",";
				}
				doctorMorePractice.setOrgClinicTimeIds(timeIds.substring(0, timeIds.length()-1));
				this.doctorClinicService.saveOrUpdateDoctorMorePractice(doctorMorePractice);
			}
		}
		/**
		 * 得到医生设置的门诊信息列表
		 */
		else if(action.equals("getDoctorMorePracticeList")){
			this.hospitalBasicInfo = this.doctorClinicService.getHospitalBasicInfoByNameOrId(null, doctorInfo.getHospitalId());
			this.doctorMorePracticeList = this.doctorClinicService.getDoctorMorePracticeList(doctorMorePractice, doctorInfo);
		}
		/**
		 * 得到医生设置的门诊历史地址
		 */
		else if(action.equals("getClinicAddressList")){
			this.addressList = this.doctorClinicService.getClinicAddress(doctorMorePractice, doctorInfo);
		}
		/**
		 * 多点执业服务tab标签
		 */
		else if(action.equals("getMorePractice")){
			doctorServiceTypeList = this.doctorClinicService.getDoctorServiceTypeByDoctorId(doctorInfo);
		}
		/**
		 * 得到医生的门诊设置信息
		 */
		else if(action.equals("getDoctorMorePractice")){
			this.doctorMorePractice = this.doctorClinicService.getDoctorMorePracticeById(doctorMorePractice.getId());
		}
		/**
		 * 得到多点机构信息
		 */
		else if(action.equals("getMoreOrgList")){
			//this.morePracticeOrgList = this.doctorClinicService.getDoctorMorePracticeOrgInfoList();
			List<DoctorMoreOrgClinicdate> moreOrgClinicDateList = this.doctorClinicService.getDoctorMoreOrgClinicdateList();
			if(moreOrgClinicDateList != null){
				for(DoctorMoreOrgClinicdate moreOrgDate : moreOrgClinicDateList){
					DoctorMorePracticeOrgInfo moreOrg = new DoctorMorePracticeOrgInfo();
					BeanUtils.copyProperties(moreOrgDate.getDoctorMorePracticeOrgInfo(), moreOrg);
					moreOrg.setCanClinicDate(moreOrgDate.getCanClinicDate());
					List<DoctorMoreOrgTimeSetting> timeList =
							this.doctorClinicService.getMoreOrgTimeSettingList(moreOrg);
					//检查每个机构的可用时间
					if(timeList != null){
						int size = 0;
						for(DoctorMoreOrgTimeSetting dmots : timeList){
							if(dmots.getDoctorMorePracticeOrgInfo().getId().longValue() == moreOrg.getId().longValue()){
								DoctorMorePractice dmp = new DoctorMorePractice();
								dmp.setClinicOrgType(ConstantManage.CLINIC_ORG_TYPE_1);//门诊类型
								dmp.setClinicDate(moreOrgDate.getCanClinicDate());//门诊日期
								dmp.setOrgClinicTimeIds(dmots.getId().toString());
								List<DoctorMorePractice> dmpList = 
										this.doctorClinicService.getDoctorMorePracticeList(dmp, null);
								if(dmpList != null){
									//当前机构的当前时间被抢
									size++;
								}
							}
						}
						if(size == timeList.size()){//机构时间被抢光
							moreOrg.setIsCanClinic(false);
						}
					}
					this.morePracticeOrgList.add(moreOrg);
				}
			}
		}
		/**
		 * 得到可用的多点机构门诊时间分段信息（临时解决方案）
		 */
		else if(action.equals("getMoreOrgTimeList")) {
			this.morePracticeOrgTimeList = this.doctorClinicService.getMoreOrgTimeSettingList(doctorMorePracticeOrgInfo);
			//检查每个机构的可用时间
			if(morePracticeOrgTimeList != null){
				String clinicDate = doctorMorePracticeOrgInfo.getCanClinicDate();
				//循环对象
				List<DoctorMoreOrgTimeSetting>  loopMorePracticeOrgTimeList = new ArrayList<DoctorMoreOrgTimeSetting>();
				loopMorePracticeOrgTimeList = morePracticeOrgTimeList;
						
				DoctorMorePractice dmp = new DoctorMorePractice();
				dmp.setClinicOrgType(ConstantManage.CLINIC_ORG_TYPE_1);//门诊类型
				dmp.setClinicDate(clinicDate);//门诊日期
				dmp.setDoctorMorePracticeOrgInfo(doctorMorePracticeOrgInfo);
				List<DoctorMorePractice> dmpList = this.doctorClinicService.getDoctorMorePracticeList(dmp, null);
				if(dmpList != null && dmpList.size() > 0) {
					if (dmpList.size()==2) {
						//说明两个人分别选定了上午和下午的门诊
						//当前机构的当前时间被抢完（上午、下午）
						morePracticeOrgTimeList = null;
					}else if (dmpList.size()==1) {
						String orgClinicTimeIds = dmpList.get(0).getOrgClinicTimeIds();
						if (orgClinicTimeIds!=null) {
							String[] arrayOrgClinicTimeIds = orgClinicTimeIds.split(",");
							if (arrayOrgClinicTimeIds.length==2) {
								//说明一个人选定了上午和下午的门诊
								//当前机构的当前时间被抢完（上午、下午）
								morePracticeOrgTimeList = null;
							} else {
								//判断是去掉上午还是下午
								String doctorMoreOrgTimeSettingId = arrayOrgClinicTimeIds[0];
								for (int j=0; j<loopMorePracticeOrgTimeList.size(); j++) {
									if (doctorMoreOrgTimeSettingId.equals(String.valueOf(loopMorePracticeOrgTimeList.get(j).getId()))) {
										morePracticeOrgTimeList.remove(j);
									}
								}
							}
						}
					}
					
				}
			}
		}
		/**
		 * 得到系统当前时间
		 */
		else if(action.equals("getCurrentTime")){
			this.currentTime = DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
		}
		/**
		 * 记录医生门诊执业上下班信息
		 */
		else if(action.equals("saveWorkRecord")){
			this.doctorClinicService.saveDoctorMorePracticeByRecord(doctorMorePractice);
			//下班操作。//判断是否达到最低会诊数，按比例分成
			if (DoctorMorePractice.END_WORK.equals(doctorMorePractice.getStartEndFlag())) {
				DoctorMorePractice old = this.doctorClinicService.getDoctorMorePracticeById(doctorMorePractice.getId());
				//得到配置的保底门诊数
				OrderInfoClinic order = new OrderInfoClinic();
				order.setClinicOrgType(ConstantManage.CLINIC_ORG_TYPE_1);
				order.setClinicAddress(old.getDoctorMorePracticeOrgInfo().getId().toString());
				HospitalPosition currentDoctorPosition = 
						doctorClinicOrderService.getHospitalPositionByDoctor(doctorInfo,order);
				//得到门诊日所完成的总门诊数量
				Long orderCount = this.doctorClinicService.getOrderCountByDoctor(doctorInfo, old.getClinicDate());
				//得到医生配置的多点机构设置信息
				Long requireClinicNum = 0L;
				if(old != null){
					DoctorMorePracticeOrgInfo moreOrg = old.getDoctorMorePracticeOrgInfo();
					//设置时保存的外机构时间id（一个说明是上午或下午；，隔开说明多个时间段）
					String outOrgIds = old.getOrgClinicTimeIds()==null?"":old.getOrgClinicTimeIds();
					List<DoctorMoreOrgTimeSetting> timeList = doctorClinicService.getMoreOrgTimeSettingList(moreOrg);
					if(timeList != null) {
						for(DoctorMoreOrgTimeSetting dmots : timeList) {
							if(outOrgIds.indexOf(dmots.getId().toString()) > -1){
								requireClinicNum += Long.valueOf(dmots.getRequireClinicNum()==null?"0":dmots.getRequireClinicNum());
							}
						}
					}
					//计算保底薪酬
					double baodi = 0D;
					DoctorAccount doctorAccount_baodi = new DoctorAccount();
					doctorAccount_baodi.setAccountDesc("多点门诊服务保底薪酬");
					if(outOrgIds.indexOf(",")>-1){//说明是全天
						baodi = Double.valueOf(currentDoctorPosition.getBaseSalary());
					}else{//半天
						baodi = Double.valueOf(currentDoctorPosition.getHalfDayMoney());
					}
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
				
				//计算额外提成
				//orderCount = 26L;
				if(orderCount > requireClinicNum){
					String commission = currentDoctorPosition == null?null:currentDoctorPosition.getCommissionPerCase();
					if(commission == null || "".equals(commission)){
						commission = "0.75";
					}
					//计算每单的提成
					double commissionMoney = Double.valueOf(currentDoctorPosition.getClinicMoneyOut())*Double.valueOf(commission);
					commissionMoney = commissionMoney*(orderCount-requireClinicNum);//总提成数
					
					DoctorAccount doctorAccount = new DoctorAccount();
					doctorAccount.setAccountDesc("多点门诊服务单笔提成");
					doctorAccount.setAmount(commissionMoney);
					doctorAccount.setDoctorId(doctorInfo.getId());
					doctorAccount.setType("+");
					doctorAccount.setUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
					accountBo.saveDoctorAccount(doctorAccount);
					//医生余额
					double doctorBalance = doctorInfo.getDoctorBalance() + commissionMoney;
					doctorInfo.setDoctorBalance(doctorBalance);
					//更新医生余额信息
					doctorInfoBo.update(doctorInfo);
				}
			}
		}
		return "success";
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
	public DoctorMorePractice getDoctorMorePractice() {
		return doctorMorePractice;
	}
	public void setDoctorMorePractice(DoctorMorePractice doctorMorePractice) {
		this.doctorMorePractice = doctorMorePractice;
	}
	public List<DoctorMorePractice> getDoctorMorePracticeList() {
		return doctorMorePracticeList;
	}
	public void setDoctorMorePracticeList(
			List<DoctorMorePractice> doctorMorePracticeList) {
		this.doctorMorePracticeList = doctorMorePracticeList;
	}
	public List<String> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}
	public List<DoctorMorePracticeOrgInfo> getMorePracticeOrgList() {
		return morePracticeOrgList;
	}
	public void setMorePracticeOrgList(
			List<DoctorMorePracticeOrgInfo> morePracticeOrgList) {
		this.morePracticeOrgList = morePracticeOrgList;
	}
	public List<DoctorMoreOrgTimeSetting> getMorePracticeOrgTimeList() {
		return morePracticeOrgTimeList;
	}
	public void setMorePracticeOrgTimeList(
			List<DoctorMoreOrgTimeSetting> morePracticeOrgTimeList) {
		this.morePracticeOrgTimeList = morePracticeOrgTimeList;
	}
	public List<DoctorServiceType> getDoctorServiceTypeList() {
		return doctorServiceTypeList;
	}
	public void setDoctorServiceTypeList(
			List<DoctorServiceType> doctorServiceTypeList) {
		this.doctorServiceTypeList = doctorServiceTypeList;
	}
	public DoctorMorePracticeOrgInfo getDoctorMorePracticeOrgInfo() {
		return doctorMorePracticeOrgInfo;
	}
	public void setDoctorMorePracticeOrgInfo(
			DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo) {
		this.doctorMorePracticeOrgInfo = doctorMorePracticeOrgInfo;
	}
	public String getAllDayMoreOrgTimeSetIdJson() {
		return allDayMoreOrgTimeSetIdJson;
	}
	public void setAllDayMoreOrgTimeSetIdJson(String allDayMoreOrgTimeSetIdJson) {
		this.allDayMoreOrgTimeSetIdJson = allDayMoreOrgTimeSetIdJson;
	}
	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}
	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}
	public String getCurrentTime() {
		return currentTime;
	}
}
