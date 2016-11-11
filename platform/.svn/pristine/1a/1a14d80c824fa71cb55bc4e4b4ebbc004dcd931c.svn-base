package com.java.publichealth.childhealth.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.core.Action;
import com.java.publichealth.childhealth.service.ChildHealthSerivce;
import com.java.publichealth.childhealth.vo.PhChildHealthExaminationRecord;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.residentsfile.action.ResidentsFileAction;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;
import com.java.publichealth.util.CalculationMethod;

public class ChildHealthAction extends Action{
	private static final long serialVersionUID = 1L;
	/**
	 * 日志
	 */
	protected static final Logger log = Logger.getLogger(ResidentsFileAction.class);
	
	/**
	 * 儿童体检个人信息的服务方法
	 */
	private ChildHealthSerivce childHealthSerivce;

	/**
	 * 儿童体检个人信息
	 */
	private PhChildHealthExaminationRecord childHealthExaminationRecord;
	/**
	 * 个人基本信息
	 */
	private PhPeopleBasicInfo phPeopleBasicInfo;
	
	/**
	 * 家庭开户信息id
	 */
	private Long accountId;
	
	private Long babyId;
	
	/**
	 * 儿童体检个人信息的服务方法的注入
	 */
	public void setChildHealthSerivce(ChildHealthSerivce childHealthSerivce) {
		this.childHealthSerivce = childHealthSerivce;
	}
	/**
	 * 儿童体检个人信息的get和set
	 */
	public PhChildHealthExaminationRecord getChildHealthExaminationRecord() {
		return childHealthExaminationRecord;
	}

	public void setChildHealthExaminationRecord(
			PhChildHealthExaminationRecord childHealthExaminationRecord) {
		this.childHealthExaminationRecord = childHealthExaminationRecord;
	}
	
	public PhPeopleBasicInfo getPhPeopleBasicInfo() {
		return phPeopleBasicInfo;
	}
	public void setPhPeopleBasicInfo(PhPeopleBasicInfo phPeopleBasicInfo) {
		this.phPeopleBasicInfo = phPeopleBasicInfo;
	}
	/**
	 * 跳转到儿童体检列表信息
	 * @return
	 */
	public String toPhChildHealthExaminationRecordList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		/*
		 * 判断用户是否存在
		 * 1.根据开户id，查找开户信息， 
		 * 2.根据babyid,查找宝宝信息
		 * 3.如果开户信息，和宝宝信息不存在，返回null
		 * 4.根据开户id，babyid查找体检信息
		 */
		FamilyAccountInfo familyAccountInfo = this.childHealthSerivce.get(accountId,FamilyAccountInfo.class);
		
		//ConsultBabyInfo consultBabyInfo = this.childHealthSerivce.get(babyId, ConsultBabyInfo.class);
		
		List<PhChildHealthExaminationRecord> list = new ArrayList<PhChildHealthExaminationRecord>();
		if(familyAccountInfo!=null /*&& consultBabyInfo!=null*/){ 
			
			childHealthExaminationRecord = new PhChildHealthExaminationRecord();
			
			childHealthExaminationRecord.setFamilyAccountInfo(familyAccountInfo);
			/*childHealthExaminationRecord.setConsultBabyInfo(consultBabyInfo);*/
			
			list = this.childHealthSerivce.
					getPhChildHealthExaminationRecordListByPeople(psm, phPeopleBasicInfo, childHealthExaminationRecord);
		}
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 跳转到添加体检信息页面
	 * @return
	 * @throws Exception 
	 */
	public String toAddPhChildHealthExaminationRecord() throws Exception{
		if(childHealthExaminationRecord == null){
			/*
			 * 根据宝宝id，获取宝宝信息，计算宝宝月龄
			 */
			childHealthExaminationRecord = new PhChildHealthExaminationRecord(); 
			ConsultBabyInfo babyInfo_query = this.childHealthSerivce.get(phPeopleBasicInfo.getId(), ConsultBabyInfo.class);
			//计算月龄
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			int monthAge = CalculationMethod.getMonthSpace(sdf.parse(babyInfo_query.getBirthday()), new Date());
			childHealthExaminationRecord.setMonthAge(String.valueOf(monthAge));
		}else if(childHealthExaminationRecord.getId() != null){
			this.childHealthExaminationRecord = 
					this.childHealthSerivce.get(childHealthExaminationRecord.getId(), PhChildHealthExaminationRecord.class);
		}
		/*childHealthExaminationRecord.setPeopleBasicInfo(null);*/
		return SUCCESS;
	}
	/**
	 * 保存健康体检信息，但是不提交页面
	 */
	public String saveOrUpdateChildHealth(){
		Long id = childHealthSerivce.saveOrUpdateChildHealth(childHealthExaminationRecord);
		this.childHealthExaminationRecord = childHealthSerivce.getChildHealthById(id);
		return SUCCESS;
	}
	/**
	 * 保存健康体检信息,提交页面
	 */
	public String submitChildHealth(){
		childHealthSerivce.saveOrUpdateChildHealth(childHealthExaminationRecord);
		return redirectActionResult("childHealthList");
	}
	/**
	 * 查看健康体检信息
	 */
	public String viewChildHealth(){
		this.childHealthExaminationRecord = childHealthSerivce.getChildHealthById(childHealthExaminationRecord.getId());
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
