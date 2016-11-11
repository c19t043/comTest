package com.java.operationmanage.action;


import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.archivesinfo.vo.UserType;
import com.java.operationmanage.service.OperationModelService;
import com.java.operationmanage.service.OperationmanageService;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.operationmanage.vo.OperaBaseSchedule;
import com.java.operationmanage.vo.OperaBusinessType;
import com.java.operationmanage.vo.OperaDoctorSchedule;
import com.java.platform.core.Action;
import com.java.util.JsonUtil;

@SuppressWarnings("serial")
public class OperationModelManagerAction extends Action  {
	private OperationmanageService operationmanageService;
	private OperationModelService operationModelService;
	/**
	 * 社区医生排班信息列表
	 */
	private List<OperaDoctorSchedule> operaDoctorSchedule_list;
	private OperaDoctorSchedule operaDoctorSchedule;
	private List<OperaBusinessType> operaBusinessType_list;
	private OperaBusinessType operaBusinessType;
	private List<UserType> userTypes_list;
	
	//模板选择
	private String templateFlag;
	private String queryFLag;
	private OperaDoctorSchedule preOperaDoctorSchedule;//添加医生排班的医生排班
	private OperaBaseSchedule operaBaseSchedule;
	/**
	 * 模板列表
	 * @return
	 */
	public String toModelList(){
		String tableId = "list";
		if(operaBaseSchedule!=null){
			Long id = operaBaseSchedule.getId();
			if(id!=null) {
				if(!(StringUtils.isNotBlank(queryFLag)&&queryFLag.equals("query"))){
					operaBaseSchedule = operationmanageService.get(id, OperaBaseSchedule.class);
					if(operaDoctorSchedule==null) operaDoctorSchedule = new OperaDoctorSchedule();
					operaDoctorSchedule.setHospitalBasicInfo(operaBaseSchedule.getHospitalBasicInfo());
				}
			}
		}
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		List<OperaDoctorSchedule> operaDoctorScheduleModel_list = operationModelService.getOperaDoctorSchedulesListByPage(psm, operaDoctorSchedule);
		//组装面向用户类型
		for (OperaDoctorSchedule operaDoctorSchedule : operaDoctorScheduleModel_list) {
			String userType = operaDoctorSchedule.getUserType();
			StringBuilder userTypeNames = new StringBuilder();
			if(StringUtils.isNotEmpty(userType)){
				String[] userTypeArr = userType.split(",");
				for (String userType_str : userTypeArr) {
					long userTypeID = Long.parseLong(userType_str);
					UserType ut = operationmanageService.get(userTypeID, UserType.class);
					userTypeNames.append(ut.getTypeName()).append(",");
				}
			}
			operaDoctorSchedule.setUserTypes(userTypeNames.toString());
		}
		this.putToRequest("list", operaDoctorScheduleModel_list);
		return SUCCESS;
	}
	/**
	 * 保存or更新排班模板信息
	 */
	public String saveOrUpdateDoctorSchedule(){
		operaDoctorSchedule.setOperaBaseSchedule(null);
		String[] userTypeArr = operaDoctorSchedule.getUserTypeArr();
		for (String userType : userTypeArr) {
			operaDoctorSchedule.setUserType(
					(JsonUtil.isNotEmpty(operaDoctorSchedule.getUserType())?operaDoctorSchedule.getUserType()+",":"")+userType);
		}
		
		long orgID = Long.parseLong(operaBaseSchedule.getOrgIDs().split(",")[0]);
		HospitalBasicInfo hospitalBasicInfo = operationmanageService.get(orgID, HospitalBasicInfo.class);
		operaDoctorSchedule.setHospitalBasicInfo(hospitalBasicInfo);
		
		operationmanageService.saveOrUpdateOperaDoctorSchedule(operaDoctorSchedule);
		addActionMessage("操作成功");
		return redirectActionResult("toModelList");
	}
	/**
	 * 新增模板
	 * @return
	 */
	public String toAddModel() {
		operaBusinessType_list = operationmanageService.getOperaBusinessTypes();
		userTypes_list = operationmanageService.getUserTypes();
		return SUCCESS;
	}
	/**
	 * 跳转编辑模板
	 * @return
	 */
	public String toEditModel() {
		operaBusinessType_list = operationmanageService.getOperaBusinessTypes();
		userTypes_list = operationmanageService.getUserTypes();
		this.operaDoctorSchedule = this.operationmanageService.get(operaDoctorSchedule.getId(),OperaDoctorSchedule.class);
		return SUCCESS;
	}
	/**
	 * 选择模板
	 * @return
	 */
	public String selectTemplate(){
		
		operaBusinessType_list = operationmanageService.getOperaBusinessTypes();
		userTypes_list = operationmanageService.getUserTypes();
		
		operaBaseSchedule = operationmanageService.get(operaBaseSchedule.getId(), OperaBaseSchedule.class);
		operaDoctorSchedule = operationmanageService.get(operaDoctorSchedule.getId(), OperaDoctorSchedule.class);
		if(preOperaDoctorSchedule!=null&&preOperaDoctorSchedule.getId()!=null){
			operaDoctorSchedule.setId(preOperaDoctorSchedule.getId());
		}else{
			operaDoctorSchedule.setId(null);
		}
		return SUCCESS;
	}
	public String toBack(){
		Long baseID = operaBaseSchedule.getId();
		operaBaseSchedule = operationmanageService.get(baseID, OperaBaseSchedule.class);
		operaBusinessType_list = operationmanageService.getOperaBusinessTypes();
		userTypes_list = operationmanageService.getUserTypes();
		
		if(preOperaDoctorSchedule!=null){
			Long id = preOperaDoctorSchedule.getId();
			if(id!=null) operaDoctorSchedule = operationmanageService.get(id, OperaDoctorSchedule.class);
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public String getQueryFLag() {
		return queryFLag;
	}
	public void setQueryFLag(String queryFLag) {
		this.queryFLag = queryFLag;
	}
	public OperaBaseSchedule getOperaBaseSchedule() {
		return operaBaseSchedule;
	}
	public void setOperaBaseSchedule(OperaBaseSchedule operaBaseSchedule) {
		this.operaBaseSchedule = operaBaseSchedule;
	}
	public OperaDoctorSchedule getPreOperaDoctorSchedule() {
		return preOperaDoctorSchedule;
	}
	public void setPreOperaDoctorSchedule(OperaDoctorSchedule preOperaDoctorSchedule) {
		this.preOperaDoctorSchedule = preOperaDoctorSchedule;
	}
	public String getTemplateFlag() {
		return templateFlag;
	}
	public void setTemplateFlag(String templateFlag) {
		this.templateFlag = templateFlag;
	}
	public OperationmanageService getOperationmanageService() {
		return operationmanageService;
	}
	public void setOperationmanageService(
			OperationmanageService operationmanageService) {
		this.operationmanageService = operationmanageService;
	}
	public List<OperaDoctorSchedule> getOperaDoctorSchedule_list() {
		return operaDoctorSchedule_list;
	}
	public void setOperaDoctorSchedule_list(
			List<OperaDoctorSchedule> operaDoctorSchedule_list) {
		this.operaDoctorSchedule_list = operaDoctorSchedule_list;
	}
	public OperaDoctorSchedule getOperaDoctorSchedule() {
		return operaDoctorSchedule;
	}
	public void setOperaDoctorSchedule(OperaDoctorSchedule operaDoctorSchedule) {
		this.operaDoctorSchedule = operaDoctorSchedule;
	}
	public List<OperaBusinessType> getOperaBusinessType_list() {
		return operaBusinessType_list;
	}
	public void setOperaBusinessType_list(
			List<OperaBusinessType> operaBusinessType_list) {
		this.operaBusinessType_list = operaBusinessType_list;
	}
	public OperaBusinessType getOperaBusinessType() {
		return operaBusinessType;
	}
	public void setOperaBusinessType(OperaBusinessType operaBusinessType) {
		this.operaBusinessType = operaBusinessType;
	}
	public List<UserType> getUserTypes_list() {
		return userTypes_list;
	}
	public void setUserTypes_list(List<UserType> userTypes_list) {
		this.userTypes_list = userTypes_list;
	}
	public OperationModelService getOperationModelService() {
		return operationModelService;
	}
	public void setOperationModelService(OperationModelService operationModelService) {
		this.operationModelService = operationModelService;
	}
}
