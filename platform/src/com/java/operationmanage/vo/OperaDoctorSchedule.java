package com.java.operationmanage.vo;

import java.util.List;

import com.java.familydoctor.servicepackage.vo.FdServicePackage;
import com.java.platform.core.BaseDomain;


/**
 * OperaDoctorSchedule entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class OperaDoctorSchedule extends BaseDomain  implements java.io.Serializable {

	// Fields

	private Long id;
	private OperaBaseSchedule operaBaseSchedule;
	private String workBeginTime;
	private String workEndTime;
	private String restBeginTime;
	private String restEndTime;
	private DoctorInfo doctorInfo;
	private String isFamilyDoctor;
	private OperaBusinessType operaBusinessType;
	private FdServicePackage fdServicePackage;
	private String familyDoctorPackage;
	private String userType;
	private String isStatistics;
	private String remarks;
	private String isEnable;
	
	private String scheduleType;//排班类型;儿科;儿保
	
	private Long srcID;//资源表ID
	private String publishStatus;//发布状态
	
	private String timeSegment;//时间分割值
	private String segmentationSourse;//时间段内号源
	private String additiveSource;//可加号源
	private String isNoFree;//是否收费;N免费;Y收费;
	private String openRequire;//时间开放需求;时间点;时间段
	private String isDeadLine;//是否需要截止时间
	private String deadLine;//截止时间
	
	//用于模板
	private HospitalBasicInfo hospitalBasicInfo;
	
	
	//用于页面数据展示
	private String workerNames;
	private String workTime;
	private String restTime;
	private String businessTypes;
	private String userTypes;
	
	private String[] userTypeArr;
	
	//用于excel导出、
	private List<OperaWorkerSchedule> operaWorkerSchedules;
	private String exportWorkerRemarks;
	private String exportWorkerNames;
	
	public Long getSrcID() {
		return srcID;
	}
	public void setSrcID(Long srcID) {
		this.srcID = srcID;
	}
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	/** default constructor */
	public OperaDoctorSchedule() {
	}
	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}
	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}
	public String getExportWorkerRemarks() {
		return exportWorkerRemarks;
	}
	public void setExportWorkerRemarks(String exportWorkerRemarks) {
		this.exportWorkerRemarks = exportWorkerRemarks;
	}
	public String getExportWorkerNames() {
		return exportWorkerNames;
	}
	public void setExportWorkerNames(String exportWorkerNames) {
		this.exportWorkerNames = exportWorkerNames;
	}
	public List<OperaWorkerSchedule> getOperaWorkerSchedules() {
		return operaWorkerSchedules;
	}
	public void setOperaWorkerSchedules(
			List<OperaWorkerSchedule> operaWorkerSchedules) {
		this.operaWorkerSchedules = operaWorkerSchedules;
	}
	public String getTimeSegment() {
		return timeSegment;
	}
	public void setTimeSegment(String timeSegment) {
		this.timeSegment = timeSegment;
	}
	public String getSegmentationSourse() {
		return segmentationSourse;
	}
	public void setSegmentationSourse(String segmentationSourse) {
		this.segmentationSourse = segmentationSourse;
	}
	public String getAdditiveSource() {
		return additiveSource;
	}
	public void setAdditiveSource(String additiveSource) {
		this.additiveSource = additiveSource;
	}
	public String getIsNoFree() {
		return isNoFree;
	}
	public void setIsNoFree(String isNoFree) {
		this.isNoFree = isNoFree;
	}
	public String getOpenRequire() {
		return openRequire;
	}
	public void setOpenRequire(String openRequire) {
		this.openRequire = openRequire;
	}
	public String getIsDeadLine() {
		return isDeadLine;
	}
	public void setIsDeadLine(String isDeadLine) {
		this.isDeadLine = isDeadLine;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	public String getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}

	public String getUserTypes() {
		return userTypes;
	}
	public OperaBusinessType getOperaBusinessType() {
		return operaBusinessType;
	}

	public void setOperaBusinessType(OperaBusinessType operaBusinessType) {
		this.operaBusinessType = operaBusinessType;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String[] getUserTypeArr() {
		return userTypeArr;
	}

	public void setUserTypeArr(String[] userTypeArr) {
		this.userTypeArr = userTypeArr;
	}

	public FdServicePackage getFdServicePackage() {
		return fdServicePackage;
	}

	public void setFdServicePackage(FdServicePackage fdServicePackage) {
		this.fdServicePackage = fdServicePackage;
	}

	public void setUserTypes(String userTypes) {
		this.userTypes = userTypes;
	}

	public String getBusinessTypes() {
		return businessTypes;
	}

	public void setBusinessTypes(String businessTypes) {
		this.businessTypes = businessTypes;
	}

	public String getWorkerNames() {
		return workerNames;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getRestTime() {
		return restTime;
	}

	public void setRestTime(String restTime) {
		this.restTime = restTime;
	}

	public void setWorkerNames(String workerNames) {
		this.workerNames = workerNames;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public OperaBaseSchedule getOperaBaseSchedule() {
		return operaBaseSchedule;
	}

	public void setOperaBaseSchedule(OperaBaseSchedule operaBaseSchedule) {
		this.operaBaseSchedule = operaBaseSchedule;
	}

	public String getWorkBeginTime() {
		return workBeginTime;
	}

	public void setWorkBeginTime(String workBeginTime) {
		this.workBeginTime = workBeginTime;
	}

	public String getWorkEndTime() {
		return workEndTime;
	}

	public void setWorkEndTime(String workEndTime) {
		this.workEndTime = workEndTime;
	}

	public String getRestBeginTime() {
		return restBeginTime;
	}

	public void setRestBeginTime(String restBeginTime) {
		this.restBeginTime = restBeginTime;
	}

	public String getRestEndTime() {
		return restEndTime;
	}

	public void setRestEndTime(String restEndTime) {
		this.restEndTime = restEndTime;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public String getIsFamilyDoctor() {
		return isFamilyDoctor;
	}

	public void setIsFamilyDoctor(String isFamilyDoctor) {
		this.isFamilyDoctor = isFamilyDoctor;
	}

	public String getFamilyDoctorPackage() {
		return familyDoctorPackage;
	}

	public void setFamilyDoctorPackage(String familyDoctorPackage) {
		this.familyDoctorPackage = familyDoctorPackage;
	}

	public String getIsStatistics() {
		return isStatistics;
	}

	public void setIsStatistics(String isStatistics) {
		this.isStatistics = isStatistics;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
}