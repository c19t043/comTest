package com.java.publichealth.productionvisit.action;

import java.util.ArrayList;
import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.platform.core.Action;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.productionvisit.service.PhPostpartumFollowRecordService;
import com.java.publichealth.productionvisit.vo.PhPostpartumFollowRecord;
import com.java.publichealth.productionvisit.vo.PhPrenatalFollowRecordAfter;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;
/**
 * 产后随访控制器类
 * @author lihao
 *
 */
public class PhPostpartumFollowRecordAction extends Action{
	private static final long serialVersionUID = 1L;
	
	private Long accountId;
	
	private String day;
	
	/**
	 * 个人基本信息
	 */
	private PhPeopleBasicInfo phPeopleBasicInfo;
	private PhPostpartumFollowRecord phPostpartumFollowRecord;
	private PhPostpartumFollowRecordService phPostpartumFollowRecordService;
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public PhPeopleBasicInfo getPhPeopleBasicInfo() {
		return phPeopleBasicInfo;
	}
	public void setPhPeopleBasicInfo(PhPeopleBasicInfo phPeopleBasicInfo) {
		this.phPeopleBasicInfo = phPeopleBasicInfo;
	}
	public PhPostpartumFollowRecord getPhPostpartumFollowRecord() {
		return phPostpartumFollowRecord;
	}
	public void setPhPostpartumFollowRecord(
			PhPostpartumFollowRecord phPostpartumFollowRecord) {
		this.phPostpartumFollowRecord = phPostpartumFollowRecord;
	}
	public PhPostpartumFollowRecordService getPhPostpartumFollowRecordService() {
		return phPostpartumFollowRecordService;
	}
	public void setPhPostpartumFollowRecordService(
			PhPostpartumFollowRecordService phPostpartumFollowRecordService) {
		this.phPostpartumFollowRecordService = phPostpartumFollowRecordService;
	}
	/**
	 * 跳转到产后随访添加页面
	 * @return
	 */
	public String toAddPhPostpartumFollowRecord(){
		//-----修改的代码
		FamilyAccountInfo familyAccountInfo = this.phPostpartumFollowRecordService.get(accountId,FamilyAccountInfo.class);
		
		if(familyAccountInfo!=null){
			if(phPostpartumFollowRecord == null)
				phPostpartumFollowRecord = new PhPostpartumFollowRecord();
			phPostpartumFollowRecord.setFamilyAccountInfo(familyAccountInfo);	
			
			List<PhPostpartumFollowRecord> list = this.phPostpartumFollowRecordService.
					getPhPostpartumFollowRecordListByPeople(phPeopleBasicInfo, phPostpartumFollowRecord,null);
			
			if(!list.isEmpty())
				this.phPostpartumFollowRecord = list.get(0);
		}
			
		if(PhPostpartumFollowRecord.VISIT_40.equals(phPostpartumFollowRecord.getFollowUpOpportunity())){
			return "visit40";
		}else{
			return SUCCESS;
		}
	}
	
	public String toList(){
		//-----修改的代码
		FamilyAccountInfo familyAccountInfo = this.phPostpartumFollowRecordService.get(accountId,FamilyAccountInfo.class);
		
		PageSortModel model = new PageSortModel(this.getHttpServletRequest(),"list");
		
		if(familyAccountInfo!=null){
			//phPostpartumFollowRecord = new PhPostpartumFollowRecord();
			phPostpartumFollowRecord.setFamilyAccountInfo(familyAccountInfo);	
			
			List<PhPostpartumFollowRecord> list = this.phPostpartumFollowRecordService.
					getPhPostpartumFollowRecordListByPeople(phPeopleBasicInfo, phPostpartumFollowRecord,model);
			this.day = phPostpartumFollowRecord.getFollowUpOpportunity().equals("0")?"6":"40";
			putToRequest("list", list);
			putToRequest("day", day);
		}
		return SUCCESS;			
	}
	
	/**
	 * 保存添加信息
	 * @return
	 */
	public String saveOrUpdatePhPostpartumFollowRecord(){
		FamilyAccountInfo familyAccountInfo = phPostpartumFollowRecordService.get(accountId, FamilyAccountInfo.class);
		phPostpartumFollowRecord.setFamilyAccountInfo(familyAccountInfo);
		
		Long id = this.phPostpartumFollowRecordService.saveOrUpdatePhPostpartumFollowRecord(phPostpartumFollowRecord);
		this.phPostpartumFollowRecord = this.phPostpartumFollowRecordService.getPhPostpartumFollowRecordById(id);
		addActionMessage("操作成功");
		if(PhPostpartumFollowRecord.VISIT_40.equals(phPostpartumFollowRecord.getFollowUpOpportunity())){
			return "visit40";
		}else{
			return SUCCESS;
		}
	}
	/**
	 * 提交添加信息
	 * @return
	 */
	public String submitPhPostpartumFollowRecord(){
		this.phPostpartumFollowRecordService.saveOrUpdatePhPostpartumFollowRecord(phPostpartumFollowRecord);
		addActionMessage("操作成功");
		return redirectActionResult("mumList");
	}
}
