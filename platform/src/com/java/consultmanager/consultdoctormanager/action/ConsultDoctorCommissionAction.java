package com.java.consultmanager.consultdoctormanager.action;

import java.util.List;

import com.java.consultmanager.consultdoctormanager.service.ConsultDoctorCommissionService;
import com.java.consultmanager.consultdoctormanager.vo.ConsultDoctorCommission;
import com.java.consultmanager.consultdoctormanager.vo.Position;
import com.java.ec.common.PageSortModel;
import com.java.familydoctor.archivesinfo.vo.UserType;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.core.Action;

public class ConsultDoctorCommissionAction extends Action{
	private static final long serialVersionUID = 1852094731493920752L;
	
	private ConsultDoctorCommissionService consultDoctorCommissionService;
	
	private ConsultDoctorCommission commission; 
	
	private Position position;
	
	private String commissionId;
	
	private String doctorids;
	
	private String message;
	
	private DoctorInfo doctorInfo;
	
	private String positionName;
	/**
	 * 批量添加医生信息
	 * @return
	 */
	public String addDoctorInfos(){
		boolean addFlag = consultDoctorCommissionService.addDoctorInfos(doctorids.split(","),commissionId);
		if(addFlag)
			message = "success";
		else 
			message = "error";
		return SUCCESS;
	}
	
	
	/**
	 * 得到医生信息列表
	 * @return
	 */
	public String getDoctorInfoList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		if(doctorInfo == null){
			doctorInfo = new DoctorInfo();
		}
		List<DoctorInfo> list = 
			this.consultDoctorCommissionService.getDoctorInfoListByPage(psm, doctorInfo,positionName);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	public String edit(){
		ConsultDoctorCommission doctorCommissionInfo = consultDoctorCommissionService.getDoctorCommissionInfo(commission.getId());
		this.putToRequest("commissioninfos", doctorCommissionInfo);
		return SUCCESS;
	}
	
	public String update(){
		consultDoctorCommissionService.updateDoctorCommissionInfo(commission);
		return redirectActionResult("list");
	}
	
	/**
	 * 保存信息
	 * @return
	 */
	public String addConsultDoctorCommission(){
		boolean validateCommision = validateCommision();
		if(validateCommision){
			consultDoctorCommissionService.addDoctorCommissionInfo(commission);
		}
		return redirectActionResult("list");
	}
	
	private boolean validateCommision(){
		if(commission.getPosition().getId()==null) return false;
		if(commission.getIsEnable()==null) return false;
		if(commission.getConsultCommission()==null) return false;
		if(commission.getConsultMoney()==null) return false;
		return true;
	}	

	/**
	 * 跳转到添加页面
	 */
	public String add(){
		List<Position> positionInfo = this.consultDoctorCommissionService.getPositionInfo();
		this.putToRequest("positionInfo", positionInfo);
		return SUCCESS;
	}
	
	/**
	 * 跳转到咨询医生级别报酬分配展示页面
	 * 职称名字：commission.name
	 * 1.如果name 为空，查询所有信息
	 * 2.如果name 有值,查询对应职称的信息
	 * @return
	 */
	public String list(){
		String tableId = "list";
		PageSortModel model = new PageSortModel(this.getHttpServletRequest(),tableId);
		List<ConsultDoctorCommission> list = this.consultDoctorCommissionService.getDoctorCommissionInfoList(model,commission);
		this.putToRequest("list", list);
		return SUCCESS;
	}

	
	
	
	
	
	
	
	
	
	
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public String getPositionName() {
		return positionName;
	}


	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}


	public void setConsultDoctorCommissionService(
			ConsultDoctorCommissionService consultDoctorCommissionService) {
		this.consultDoctorCommissionService = consultDoctorCommissionService;
	}
	public ConsultDoctorCommission getCommission() {
		return commission;
	}

	public void setCommission(ConsultDoctorCommission commission) {
		this.commission = commission;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	public String getDoctorids() {
		return doctorids;
	}

	public void setDoctorids(String doctorids) {
		this.doctorids = doctorids;
	}

	public String getCommissionId() {
		return commissionId;
	}

	public void setCommissionId(String commissionId) {
		this.commissionId = commissionId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
