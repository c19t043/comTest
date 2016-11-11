package com.java.publichealth.productionvisit.action;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.core.Action;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.productionvisit.service.PhPrenatalFollowRecordFirstService;
import com.java.publichealth.productionvisit.vo.PhPrenatalFollowRecordFirst;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

public class PhPrenatalFollowRecordFirstAction extends Action{
	private static final long serialVersionUID = 1L;
	/**
	 * 个人基本信息
	 */
	private PhPeopleBasicInfo phPeopleBasicInfo;
	/**
	 * 第一次产检记录信息
	 */
	private PhPrenatalFollowRecordFirst phPrenatalFollowRecordFirst;
	/**
	 * 第一次产检服务类
	 */
	private PhPrenatalFollowRecordFirstService phPrenatalFollowRecordFirstService;
	
	
	private Long accountId;
	
	public PhPrenatalFollowRecordFirstService getPhPrenatalFollowRecordFirstService() {
		return phPrenatalFollowRecordFirstService;
	}
	public void setPhPrenatalFollowRecordFirstService(
			PhPrenatalFollowRecordFirstService phPrenatalFollowRecordFirstService) {
		this.phPrenatalFollowRecordFirstService = phPrenatalFollowRecordFirstService;
	}
	public PhPrenatalFollowRecordFirst getPhPrenatalFollowRecordFirst() {
		return phPrenatalFollowRecordFirst;
	}
	public void setPhPrenatalFollowRecordFirst(
			PhPrenatalFollowRecordFirst phPrenatalFollowRecordFirst) {
		this.phPrenatalFollowRecordFirst = phPrenatalFollowRecordFirst;
	}
	public PhPeopleBasicInfo getPhPeopleBasicInfo() {
		return phPeopleBasicInfo;
	}
	public void setPhPeopleBasicInfo(PhPeopleBasicInfo phPeopleBasicInfo) {
		this.phPeopleBasicInfo = phPeopleBasicInfo;
	}
	
	public String toList(){
		PageSortModel model = new PageSortModel(this.getHttpServletRequest(),"list");
		
		FamilyAccountInfo familyAccountInfo = this.phPrenatalFollowRecordFirstService.get(accountId,FamilyAccountInfo.class);
		
		if(familyAccountInfo!=null){
			if(phPrenatalFollowRecordFirst==null)
				phPrenatalFollowRecordFirst = new PhPrenatalFollowRecordFirst();
			
			phPrenatalFollowRecordFirst.setFamilyAccountInfo(familyAccountInfo);
			
			List<PhPrenatalFollowRecordFirst> list = this.phPrenatalFollowRecordFirstService.
					getObjectListOfFirstRecordByPage(model, phPrenatalFollowRecordFirst);
			
			putToRequest("list", list);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 跳转到第一次产检信息页面
	 * @return
	 */
	public String toAddPhPrenatalFollowRecordFirst(){
		
		
		FamilyAccountInfo familyAccountInfo = this.phPrenatalFollowRecordFirstService.get(accountId,FamilyAccountInfo.class);
		
		if(familyAccountInfo!=null){
			phPrenatalFollowRecordFirst = new PhPrenatalFollowRecordFirst();
			phPrenatalFollowRecordFirst.setFamilyAccountInfo(familyAccountInfo);
			
			List<PhPrenatalFollowRecordFirst> list = this.phPrenatalFollowRecordFirstService.
					getPhPrenatalFollowRecordFirstListByPeople(phPeopleBasicInfo, phPrenatalFollowRecordFirst);
			if(!list.isEmpty())
				this.phPrenatalFollowRecordFirst = list.get(0);
		}
		return SUCCESS;
	}
	/**
	 * 保存添加信息
	 * @return
	 */
	public String saveOrUpdatePhPrenatalFollowRecordFirst(){
		Long id = this.phPrenatalFollowRecordFirstService.saveOrUpdatePhPrenatalFollowRecordFirst(phPrenatalFollowRecordFirst);
		this.phPrenatalFollowRecordFirst = this.phPrenatalFollowRecordFirstService.getPhPrenatalFollowRecordFirstById(id);
		return SUCCESS;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
}
