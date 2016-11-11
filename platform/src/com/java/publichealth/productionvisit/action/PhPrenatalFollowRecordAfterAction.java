package com.java.publichealth.productionvisit.action;

import java.util.ArrayList;
import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.core.Action;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.productionvisit.service.PhPrenatalFollowRecordAfterService;
import com.java.publichealth.productionvisit.vo.PhPrenatalFollowRecordAfter;
import com.java.publichealth.productionvisit.vo.PhPrenatalFollowRecordFirst;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

public class PhPrenatalFollowRecordAfterAction extends Action{
	private static final long serialVersionUID = 1L;
	/**
	 * 个人基本信息
	 */
	private PhPeopleBasicInfo phPeopleBasicInfo;
	/**
	 * 产前第2-9次
	 */
	private PhPrenatalFollowRecordAfter phPrenatalFollowRecordAfter;
	/**
	 * 产前第2-9次服务类
	 */
	private PhPrenatalFollowRecordAfterService phPrenatalFollowRecordAfterService;
	
	private Long accountId;
	

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
	public PhPrenatalFollowRecordAfter getPhPrenatalFollowRecordAfter() {
		return phPrenatalFollowRecordAfter;
	}
	public void setPhPrenatalFollowRecordAfter(
			PhPrenatalFollowRecordAfter phPrenatalFollowRecordAfter) {
		this.phPrenatalFollowRecordAfter = phPrenatalFollowRecordAfter;
	}
	public PhPrenatalFollowRecordAfterService getPhPrenatalFollowRecordAfterService() {
		return phPrenatalFollowRecordAfterService;
	}
	public void setPhPrenatalFollowRecordAfterService(
			PhPrenatalFollowRecordAfterService phPrenatalFollowRecordAfterService) {
		this.phPrenatalFollowRecordAfterService = phPrenatalFollowRecordAfterService;
	}
	/**
	 * 跳转到2-9次产前随访列表信息
	 * @return
	 */
	public String toPhPrenatalFollowRecordAfterList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
	
		FamilyAccountInfo familyAccountInfo = this.phPrenatalFollowRecordAfterService.get(accountId,FamilyAccountInfo.class);
		
		List<PhPrenatalFollowRecordAfter> list = new ArrayList<PhPrenatalFollowRecordAfter>();
		
		if(familyAccountInfo!=null){
			phPrenatalFollowRecordAfter = new PhPrenatalFollowRecordAfter();
			phPrenatalFollowRecordAfter.setFamilyAccountInfo(familyAccountInfo);	
			
			list = this.phPrenatalFollowRecordAfterService.
				getPhPrenatalFollowRecordAfterListByPeople(psm,phPeopleBasicInfo, phPrenatalFollowRecordAfter);
		}
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 跳转添加2-9次产前随访
	 * @return
	 */
	public String toAddPhPrenatalFollowRecordAfter(){
		if(phPrenatalFollowRecordAfter == null){
			phPrenatalFollowRecordAfter = new PhPrenatalFollowRecordAfter(); 
		}else if(phPrenatalFollowRecordAfter.getId() != null){
			this.phPrenatalFollowRecordAfter = 
					this.phPrenatalFollowRecordAfterService.
					getPhPrenatalFollowRecordAfterById(phPrenatalFollowRecordAfter.getId());
		}
		phPrenatalFollowRecordAfter.setPeopleBasicInfo(phPeopleBasicInfo);
		
		//phPrenatalFollowRecordAfter.setKyUserInfo(new KyUserInfo(phPeopleBasicInfo.getId()));
		
		return SUCCESS;
	}
	/**
	 * 保存或更新2-9次产前随访
	 * @return
	 */
	public String saveOrUpdatePhPrenatalFollowRecordAfter(){
		Long id = this.phPrenatalFollowRecordAfterService.saveOrUpdatePhPrenatalFollowRecordAfter(phPrenatalFollowRecordAfter);
		this.phPrenatalFollowRecordAfter = this.phPrenatalFollowRecordAfterService.getPhPrenatalFollowRecordAfterById(id);
		addActionMessage("操作成功");
		return SUCCESS;
	}
	/**
	 * 提交2-9次产前随访
	 * @return
	 */
	public String submitPhPrenatalFollowRecordAfter(){
		Long id = this.phPrenatalFollowRecordAfterService.saveOrUpdatePhPrenatalFollowRecordAfter(phPrenatalFollowRecordAfter);
		this.phPrenatalFollowRecordAfter = this.phPrenatalFollowRecordAfterService.getPhPrenatalFollowRecordAfterById(id);
		addActionMessage("操作成功");
		return redirectActionResult("phPrenatalFollowRecordAfterList");
	}
	/**
	 * 查看2-9次产前随访明细
	 * @return
	 */
	public String viewPhPrenatalFollowRecordAfter(){
		this.phPrenatalFollowRecordAfter = this.phPrenatalFollowRecordAfterService.getPhPrenatalFollowRecordAfterById(phPrenatalFollowRecordAfter.getId());
		return SUCCESS;
	}
}
