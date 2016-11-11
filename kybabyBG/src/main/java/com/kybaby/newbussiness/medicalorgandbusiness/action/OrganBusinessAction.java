package com.kybaby.newbussiness.medicalorgandbusiness.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProject;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProjectType;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgOpenBusiness;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenDoctor;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResourcesDetail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganServicePlaceSet;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;
import com.kybaby.util.ConstantManage;

public class OrganBusinessAction extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	private String mes="操作成功";
	/**
	 * 计免开放设置信息
	 */
	private OrganInoculationOpenResources organInoculationOpenResources;
	/**
	 * 机构计免配置信息列表
	 */
	private List<OrganInoculationOpenResources> organInoculationOpenResourcesList = new ArrayList<OrganInoculationOpenResources>();
	/**
	 * 计免明细信息集合
	 */
	private List<OrganInoculationOpenResourcesDetail> organInoculationOpenResourcesDetailList = new ArrayList<OrganInoculationOpenResourcesDetail>();
	/**
	* 机构列表
	*/
	private List<HospitalBasicInfo> hospitalBasicInfoList = new ArrayList<HospitalBasicInfo>();
	/**
	 * 儿保开放设置信息
	 */
	private OrganChildcareOpenResources organChildcareOpenResources;
	/**
	 * 儿保开放设置信息列表
	 */
	private List<OrganChildcareOpenResources> organChildcareOpenResourcesList = new ArrayList<OrganChildcareOpenResources>();
	/**
	 * 儿保开放设置明细信息列表
	 */
	private List<OrganChildcareOpenResourcesDatail> organChildcareOpenResourcesDatailList = new ArrayList<OrganChildcareOpenResourcesDatail>();
	/**
	 * 机构服务场所
	 */
	private OrganServicePlaceSet organServicePlaceSet;
	/**
	 * 机构服务场所列表
	 */
	private List<OrganServicePlaceSet> organServicePlaceSetList = new ArrayList<OrganServicePlaceSet>();
	/**
	 * 机构服务场所列表
	 */
	private List<OrgOpenBusiness>  orgOpenBusinessList = new ArrayList<OrgOpenBusiness>();
	/**
	 * 机构开放服务
	 */
	private OrgOpenBusiness orgOpenBusiness;
	/**
	 * 医生薪酬记录列表
	 */
	private List<DoctorMoneyRecord> doctorMoneyRecordList = new ArrayList<>();
	/**
	 * 医生薪酬记录
	 */
	private DoctorMoneyRecord doctorMoneyRecord;
	/**
	 * 儿保项目分类
	 */
	private ChildcareProjectType childcareProjectType;
	/**
	 * 儿保项目信息
	 */
	private ChildcareProject childcareProject;
	/**
	 * 儿保项目信息列表
	 */
	private List<ChildcareProject> childcareProjectList = new ArrayList<>();
	/**
	 * 儿保项目分类列表
	 */
	private List<ChildcareProjectType> childcareProjectTypeList = new ArrayList<>();
	/**
	 * 用户类型列表
	 */
	private List<UserType> userTypeList = new ArrayList<>();
	/**
	 * 用户类型
	 */
	private UserType userType;
	
	/**
	 * 儿保开放资源明细
	 */
	private OrganChildcareOpenResourcesDatail updateChildCareDetail;
	
	/**
	 * 社区机构id
	 */
	private Long orgId;
	/**
	 * 医生id
	 */
	private Long doctorId;
	/**
	 * 业务分类（0：门诊；1：儿保）
	 */
	private String businessType;
	/**
	 * 医院职称分成记录 
	 */
	private HospitalPosition hospitalPosition;
	
	public String execute() {
		/**
		 * 机构计免配置信息列表
		 */
		if(action.equals("getOrganInoculationOpenResourcesList")){
			this.organInoculationOpenResourcesList = 
					this.organBusinessService.getOrganInoculationOpenResourcesList(organInoculationOpenResources);
		}
		/**
		 * 机构计免配置明细信息列表
		 */
		if(action.equals("getOrganInoculationOpenResourcesDetailList")){
			this.organInoculationOpenResourcesDetailList = 
					this.organBusinessService.getOrganInoculationOpenResourcesDetailList(organInoculationOpenResources);
		}
		/**
		 * 社区机构列表
		 */
		else if(action.equals("getHospitalBasicInfoList")){
			HospitalBasicInfo hospitalBasicInfo = new HospitalBasicInfo();
			//hospitalBasicInfo.setHospitalType("社区医院");
			this.hospitalBasicInfoList = this.organBusinessService.getHospitalBasicInfoList(hospitalBasicInfo);
		}
		/**
		 * 保存或更新计免设置信息
		 */
		else if(action.equals("saveOrUpdateOrganInoculationOpenResources")){
			this.organBusinessService.saveOrUpdateOrganInoculationOpenResources(organInoculationOpenResources);
			//保存设置明细
			return "orgbo_vaccine_manage";
		}
		/**
		 * 保存或更新儿保设置信息
		 */
		else if(action.equals("saveOrUpdateOrganChildcareOpenResources")){
			Long id = this.organBusinessService.saveOrUpdateOrganChildcareOpenResources(organChildcareOpenResources);
			//保存医生薪酬记录
//			if("Y".equals(organChildcareOpenResources.getIsMoney())){//收费
				OrganChildcareOpenResources ocor = this.organBusinessService.getOrganChildcareOpenResourcesById(id);
				String doctorIds = ocor.getDoctorIds();
				if(doctorIds != null && !doctorIds.isEmpty()){
					String [] doctorIdArr = doctorIds.split(",");
					for(int i=0; i<doctorIdArr.length; i++){
						DoctorInfo doctor = this.doctorInfoBo.getDoctorInfoById(Long.valueOf(doctorIdArr[i]));
						//添加医生薪酬记录信息
						HospitalPosition hp = this.doctorMoneyRecordService.
								getHospitalPositionByDoctor(doctor, ConstantManage.TYPE_CHILDCARE, ocor.getHospitalBasicInfo().getId());
						DoctorMoneyRecord doctorMoneyRecord = new DoctorMoneyRecord();
						DoctorMoneyRecord oldDmr = this.doctorMoneyRecordService.
								getDoctorMoneyRecordBySomething(null, doctor, ocor.getOpenDate());
						if(oldDmr != null){
							doctorMoneyRecord.setId(oldDmr.getId());
						}
						doctorMoneyRecord.setDoctorInfo(doctor);
						doctorMoneyRecord.setHospitalPosition(hp);
						doctorMoneyRecord.setMoney(hp.getBaseSalary());
						doctorMoneyRecord.setOperateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
						doctorMoneyRecord.setRemark("");
						doctorMoneyRecord.setWorkDate(ocor.getOpenDate());
						doctorMoneyRecord.setWorkStartTime(ocor.getOpenStartTime());
						doctorMoneyRecord.setWorkEndTime(ocor.getOpenEndTime());
						doctorMoneyRecord.setMoneyPer(hp.getClinicMoneyOut());
						this.doctorMoneyRecordService.saveOrUpdateDoctorMoneyRecord(doctorMoneyRecord);
						//添加开放医生明细记录
						OrganChildcareOpenDoctor organChildcareOpenDoctor = new OrganChildcareOpenDoctor();
						OrganChildcareOpenDoctor oldOrganChildcareOpenDoctor = this.doctorMoneyRecordService.
								getOrganChildcareOpenDoctorBySomeThing(null, doctor.getId(), ocor.getId());
						if(oldOrganChildcareOpenDoctor != null){
							BeanUtils.copyProperties(oldOrganChildcareOpenDoctor, organChildcareOpenDoctor);
						}else{
							organChildcareOpenDoctor.setWorkStatus("准备中");
						}
						organChildcareOpenDoctor.setDoctorInfo(doctor);
						organChildcareOpenDoctor.setOperateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
						organChildcareOpenDoctor.setOrganChildcareOpenResources(ocor);
						this.doctorMoneyRecordService.saveOrUpdateOrganChildcareOpenDoctor(organChildcareOpenDoctor);
					}
				}
//			}
			return "orgbo_childcare_manage";
		}
		/**
		 * 获得儿保设置信息明细列表
		 */
		else if(action.equals("getOrganChildcareOpenResourcesDatailList")){
			this.organChildcareOpenResourcesDatailList = 
					this.organBusinessService.getOrganChildcareOpenResourcesDatailList(organChildcareOpenResources);
		}
		/**
		 * 获得儿保设置信息列表
		 */
		else if(action.equals("getOrganChildcareOpenResourcesList")){
			this.organChildcareOpenResourcesList = 
					this.organBusinessService.getOrganChildcareOpenResourcesList(organChildcareOpenResources);
			if(organChildcareOpenResourcesList != null){
				for(OrganChildcareOpenResources ocor : organChildcareOpenResourcesList){
					String doctorIds = ocor.getDoctorIds();
					String doctorNames = "";
					ocor.setDoctorNames(doctorNames);
					if(doctorIds != null && !"".equals(doctorIds)){
						String [] ids = doctorIds.split(",");
						for(int i=0; i< ids.length; i++){
							DoctorInfo doctor = this.doctorInfoBo.getDoctorInfoById(Long.valueOf(ids[i]));
							doctorNames += doctor.getDoctorName() + ",";
						}
						ocor.setDoctorNames(doctorNames.substring(0, doctorNames.lastIndexOf(",")));
					}
				}
			}
		}
		/**
		 * 获得机构服务场所信息列表
		 */
		else if(action.equals("getOrganServicePlaceSetList")){
			this.organServicePlaceSetList = 
					this.organBusinessService.getOrganServicePlaceSetList(organServicePlaceSet);
		}
		/**
		 * 保存或更新机构服务场所信息
		 */
		else if(action.equals("saveOrupdateOrganServicePlaceSet")){
			this.organBusinessService.saveOrupdateOrganServicePlaceSet(organServicePlaceSet);
			return "organServicePlaceSetList";
		}
		/**
		 * 得到机构开放业务列表
		 */
		else if(action.equals("getOrgOpenBusinessList")){
			this.orgOpenBusinessList = this.organBusinessService.getOrgOpenBusinessList(orgOpenBusiness);
		}
		/**
		 * 得到机构开放业务列表
		 */
		else if(action.equals("saveOrupdateOrgOpenBusiness")){
			this.organBusinessService.saveOrupdateOrgOpenBusiness(orgOpenBusiness);
			return "org_open_business_list";
		}
		/**
		 * 得到医生薪酬记录列表
		 */
		else if(action.equals("getDoctorMoneyRecordList")){
			this.doctorMoneyRecordList = this.doctorMoneyRecordService.getDoctorMoneyRecordList(doctorMoneyRecord);
		}
		/**
		 * 更新医生薪酬记录
		 */
		else if(action.equals("updateDoctorMoneyRecord")){
			DoctorMoneyRecord dmr = this.doctorMoneyRecordService.getDoctorMoneyRecordBySomething(doctorMoneyRecord.getId(), null, null);
			dmr.setMoney(doctorMoneyRecord.getMoney());
			dmr.setMoneyPer(doctorMoneyRecord.getMoneyPer());
			dmr.setUserType(doctorMoneyRecord.getUserType());
			this.doctorMoneyRecordService.saveOrUpdateDoctorMoneyRecord(dmr);
			return "doctorMoneyRecordList";
		}
		/**
		 * 得到儿保项目分类列表
		 */
		else if(action.equals("getChildcareProjectTypeList")){
			this.childcareProjectTypeList = this.doctorMoneyRecordService.getChildcareProjectTypeList(childcareProjectType);
		}
		/**
		 * 保存儿保项目分类
		 */
		else if(action.equals("saveOrUpdateChildcareProjectType")){
			this.doctorMoneyRecordService.saveOrUpdateChildcareProjectType(childcareProjectType);
			return "childcare_project_type";
		}
		/**
		 * 得到儿保项目列表
		 */
		else if(action.equals("getChildcareProjectList")){
			this.childcareProjectList = this.doctorMoneyRecordService.getChildcareProjectList(childcareProject);
		}
		/**
		 * 保存儿保项目
		 */
		else if(action.equals("saveOrUpdateChildcareProject")){
			this.doctorMoneyRecordService.saveOrUpdateChildcareProject(childcareProject);
			return "childcare_project";
		}
		/**
		 * 用户类型列表
		 */
		else if(action.equals("getUserTypeList")){
			this.userTypeList = this.doctorMoneyRecordService.getUserTypeList(userType);
		}
		/*
		 * 查看儿保资源设置中是否已有预约
		 */
		else if(action.equals("checkExistOrder")){
			List<UserChildcareAppointmentInfo> list = this.organBusinessService.findChildCareOrder(organChildcareOpenResources.getId());
			if(list.isEmpty())
				mes="false";
			else
				mes="true";
		}
		/*
		 * 修改儿保明细剩余资源数据
		 */
		else if(action.equals("updateChildCareDetailData")){
			boolean updateFlag = this.organBusinessService.updateChildCareDetailData(updateChildCareDetail);
			if(updateFlag)
				mes="true";
			else
				mes="false";
		}
		/*
		 * 修改儿保开放资源数据
		 */
		else if(action.equals("updateChildCareData")){
			boolean updateFlag = this.organBusinessService.updateChildCareData(organChildcareOpenResources);
			if(updateFlag)
				mes="true";
			else
				mes="false";
		}
		/*
		 * 查看医院职称分成记录
		 */
		else if(action.equals("findHospitalPosition")){
			this.hospitalPosition = organBusinessService.findHospitalPosition(orgId, doctorId,businessType);
		}
		return "success";
	}
	
	public String getBusinessType() {
		return businessType;
	}


	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}


	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public OrganInoculationOpenResources getOrganInoculationOpenResources() {
		return organInoculationOpenResources;
	}

	public void setOrganInoculationOpenResources(
			OrganInoculationOpenResources organInoculationOpenResources) {
		this.organInoculationOpenResources = organInoculationOpenResources;
	}

	public List<OrganInoculationOpenResources> getOrganInoculationOpenResourcesList() {
		return organInoculationOpenResourcesList;
	}

	public void setOrganInoculationOpenResourcesList(
			List<OrganInoculationOpenResources> organInoculationOpenResourcesList) {
		this.organInoculationOpenResourcesList = organInoculationOpenResourcesList;
	}

	public List<HospitalBasicInfo> getHospitalBasicInfoList() {
		return hospitalBasicInfoList;
	}

	public void setHospitalBasicInfoList(
			List<HospitalBasicInfo> hospitalBasicInfoList) {
		this.hospitalBasicInfoList = hospitalBasicInfoList;
	}

	public List<OrganInoculationOpenResourcesDetail> getOrganInoculationOpenResourcesDetailList() {
		return organInoculationOpenResourcesDetailList;
	}

	public void setOrganInoculationOpenResourcesDetailList(
			List<OrganInoculationOpenResourcesDetail> organInoculationOpenResourcesDetailList) {
		this.organInoculationOpenResourcesDetailList = organInoculationOpenResourcesDetailList;
	}

	public OrganChildcareOpenResources getOrganChildcareOpenResources() {
		return organChildcareOpenResources;
	}

	public void setOrganChildcareOpenResources(
			OrganChildcareOpenResources organChildcareOpenResources) {
		this.organChildcareOpenResources = organChildcareOpenResources;
	}

	public List<OrganChildcareOpenResources> getOrganChildcareOpenResourcesList() {
		return organChildcareOpenResourcesList;
	}

	public void setOrganChildcareOpenResourcesList(
			List<OrganChildcareOpenResources> organChildcareOpenResourcesList) {
		this.organChildcareOpenResourcesList = organChildcareOpenResourcesList;
	}

	public List<OrganChildcareOpenResourcesDatail> getOrganChildcareOpenResourcesDatailList() {
		return organChildcareOpenResourcesDatailList;
	}

	public void setOrganChildcareOpenResourcesDatailList(
			List<OrganChildcareOpenResourcesDatail> organChildcareOpenResourcesDatailList) {
		this.organChildcareOpenResourcesDatailList = organChildcareOpenResourcesDatailList;
	}

	public OrganServicePlaceSet getOrganServicePlaceSet() {
		return organServicePlaceSet;
	}

	public void setOrganServicePlaceSet(OrganServicePlaceSet organServicePlaceSet) {
		this.organServicePlaceSet = organServicePlaceSet;
	}

	public List<OrganServicePlaceSet> getOrganServicePlaceSetList() {
		return organServicePlaceSetList;
	}

	public void setOrganServicePlaceSetList(
			List<OrganServicePlaceSet> organServicePlaceSetList) {
		this.organServicePlaceSetList = organServicePlaceSetList;
	}

	public List<OrgOpenBusiness> getOrgOpenBusinessList() {
		return orgOpenBusinessList;
	}

	public void setOrgOpenBusinessList(List<OrgOpenBusiness> orgOpenBusinessList) {
		this.orgOpenBusinessList = orgOpenBusinessList;
	}

	public OrgOpenBusiness getOrgOpenBusiness() {
		return orgOpenBusiness;
	}

	public void setOrgOpenBusiness(OrgOpenBusiness orgOpenBusiness) {
		this.orgOpenBusiness = orgOpenBusiness;
	}

	public List<DoctorMoneyRecord> getDoctorMoneyRecordList() {
		return doctorMoneyRecordList;
	}

	public void setDoctorMoneyRecordList(
			List<DoctorMoneyRecord> doctorMoneyRecordList) {
		this.doctorMoneyRecordList = doctorMoneyRecordList;
	}

	public DoctorMoneyRecord getDoctorMoneyRecord() {
		return doctorMoneyRecord;
	}

	public void setDoctorMoneyRecord(DoctorMoneyRecord doctorMoneyRecord) {
		this.doctorMoneyRecord = doctorMoneyRecord;
	}

	public ChildcareProjectType getChildcareProjectType() {
		return childcareProjectType;
	}

	public void setChildcareProjectType(ChildcareProjectType childcareProjectType) {
		this.childcareProjectType = childcareProjectType;
	}

	public ChildcareProject getChildcareProject() {
		return childcareProject;
	}

	public void setChildcareProject(ChildcareProject childcareProject) {
		this.childcareProject = childcareProject;
	}

	public List<ChildcareProject> getChildcareProjectList() {
		return childcareProjectList;
	}

	public void setChildcareProjectList(List<ChildcareProject> childcareProjectList) {
		this.childcareProjectList = childcareProjectList;
	}

	public List<ChildcareProjectType> getChildcareProjectTypeList() {
		return childcareProjectTypeList;
	}

	public void setChildcareProjectTypeList(
			List<ChildcareProjectType> childcareProjectTypeList) {
		this.childcareProjectTypeList = childcareProjectTypeList;
	}

	public List<UserType> getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(List<UserType> userTypeList) {
		this.userTypeList = userTypeList;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public OrganChildcareOpenResourcesDatail getUpdateChildCareDetail() {
		return updateChildCareDetail;
	}

	public void setUpdateChildCareDetail(
			OrganChildcareOpenResourcesDatail updateChildCareDetail) {
		this.updateChildCareDetail = updateChildCareDetail;
	}
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public HospitalPosition getHospitalPosition() {
		return hospitalPosition;
	}

	public void setHospitalPosition(HospitalPosition hospitalPosition) {
		this.hospitalPosition = hospitalPosition;
	}
}
