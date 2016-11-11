package com.java.publichealth.neonatalvisit.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.core.Action;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.neonatalvisit.service.PhNeonatalVisitRecordService;
import com.java.publichealth.neonatalvisit.vo.PhNeonatalVisitRecord;
import com.java.publichealth.residentsfile.action.ResidentsFileAction;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

public class PhNeonatalVisitRecordAction extends Action {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 日志
	 */
	protected static final Logger log = Logger.getLogger(ResidentsFileAction.class);
	
	/**
	 * 新生儿访视业务逻辑类
	 */
	private PhNeonatalVisitRecordService phNeonatalVisitRecordService;
	/**
	 * 新生儿访视实体类
	 */
	private PhNeonatalVisitRecord phNeonatalVisitRecord;
	/**
	 * 个人基本信息
	 */
	private PhPeopleBasicInfo phPeopleBasicInfo;
	
	private Long accountId;
	private Long babyId;
	
	
	public PhNeonatalVisitRecordService getPhNeonatalVisitRecordService() {
		return phNeonatalVisitRecordService;
	}
	public void setPhNeonatalVisitRecordService(
			PhNeonatalVisitRecordService phNeonatalVisitRecordService) {
		this.phNeonatalVisitRecordService = phNeonatalVisitRecordService;
	}
	public PhNeonatalVisitRecord getPhNeonatalVisitRecord() {
		return phNeonatalVisitRecord;
	}
	public void setPhNeonatalVisitRecord(PhNeonatalVisitRecord phNeonatalVisitRecord) {
		this.phNeonatalVisitRecord = phNeonatalVisitRecord;
	}
	
	public PhPeopleBasicInfo getPhPeopleBasicInfo() {
		return phPeopleBasicInfo;
	}
	public void setPhPeopleBasicInfo(PhPeopleBasicInfo phPeopleBasicInfo) {
		this.phPeopleBasicInfo = phPeopleBasicInfo;
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	
	public String toList(){
		/*
		 * 1、判断家庭开户信息是否存在
		 * 2、不存在，页面回显信息为空
		 * 3、存在,新生儿随访关联家庭开户信息
		 * 4、获取随访记录
		 */
		FamilyAccountInfo familyAccountInfo = phNeonatalVisitRecordService.get(accountId, FamilyAccountInfo.class);
		
		List<PhNeonatalVisitRecord> list = new ArrayList<PhNeonatalVisitRecord>();
		
		if(familyAccountInfo!=null){
			String tableId = "list";
			PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
			
			if(phNeonatalVisitRecord == null){
				phNeonatalVisitRecord = new PhNeonatalVisitRecord();
			}
			phNeonatalVisitRecord.setFamilyAccountInfo(familyAccountInfo);
			
			list = this.phNeonatalVisitRecordService.getPhNeonatalVisitRecordServiceByPeople(phPeopleBasicInfo, phNeonatalVisitRecord,psm);
		
		}
		this.putToRequest("list", list);
		
		return SUCCESS;
	}
	
	public String toDetail(){
		return toAddPhNeonatalVisitRecord();
	}
	
	/**
	 * 新生儿家庭访视记录信息添加方法
	 */
	public String saveOrUpdatePhNeonatalVisitRecord(){
		Long id = phNeonatalVisitRecordService.saveOrUpdatePhNeonatalVisitRecord(phNeonatalVisitRecord);
		this.phNeonatalVisitRecord = phNeonatalVisitRecordService.getPhNeonatalVisitRecordById(id);
		return SUCCESS;
	}
	public String doSubmit(){
		Long id = phNeonatalVisitRecordService.saveOrUpdatePhNeonatalVisitRecord(phNeonatalVisitRecord);
		this.phNeonatalVisitRecord = phNeonatalVisitRecordService.getPhNeonatalVisitRecordById(id);
		return redirectActionResult("toList");
	}
	/**
	 * 跳转到添加新生儿访视页面
	 * @return
	 */
	public String toAddPhNeonatalVisitRecord(){
		//----------------------------------修改的代码					  
		FamilyAccountInfo familyAccountInfo = this.phNeonatalVisitRecordService.get(accountId,FamilyAccountInfo.class);
		
		//ConsultBabyInfo consultBabyInfo = this.phNeonatalVisitRecordService.get(babyId,ConsultBabyInfo.class);
		//----------------------------------
		if(familyAccountInfo!=null/*&&consultBabyInfo!=null*/){
			
			if(phNeonatalVisitRecord==null)
				phNeonatalVisitRecord = new PhNeonatalVisitRecord();
			//phNeonatalVisitRecord.setConsultBabyInfo(consultBabyInfo);
			
			phNeonatalVisitRecord.setFamilyAccountInfo(familyAccountInfo);
			
			List<PhNeonatalVisitRecord> list = this.phNeonatalVisitRecordService.getPhNeonatalVisitRecordServiceByPeople(phPeopleBasicInfo, phNeonatalVisitRecord,null);
			
			if(!list.isEmpty())
				this.phNeonatalVisitRecord = list.get(0);
		}
		return SUCCESS;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getBabyId() {
		return babyId;
	}
	public void setBabyId(Long babyId) {
		this.babyId = babyId;
	}
	
}
