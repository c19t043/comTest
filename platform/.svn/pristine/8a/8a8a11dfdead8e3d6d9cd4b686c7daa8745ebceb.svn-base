package com.java.publichealth.residentsfile.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.common.lang.StringUtils;
import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.core.Action;
import com.java.platform.core.GlobalSysInfo;
import com.java.platform.user.vo.User;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.residentsfile.service.ResidentsFileService;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPastHistoryBloodTransfusion;
import com.java.publichealth.residentsfile.vo.PhPastHistoryIllness;
import com.java.publichealth.residentsfile.vo.PhPastHistoryOperation;
import com.java.publichealth.residentsfile.vo.PhPastHistoryTrauma;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

public class ResidentsFileAction extends Action {
	private static final long serialVersionUID = 1L;
	protected static final Logger log = Logger.getLogger(ResidentsFileAction.class);
	/**
	 * 个人基本信息服务
	 */
	private ResidentsFileService residentsFileService;
	/**
	 * 个人基本信息
	 */
	private PhPeopleBasicInfo phPeopleBasicInfo;
	
	/**
	 * 康优宝贝注册用户信息
	 */
	private KyUserInfo kyUserInfo;
	
	/**
	 * 既往史疾病列表
	 */
	private List<PhPastHistoryIllness> phPastHistoryIllnessList = new ArrayList<PhPastHistoryIllness>();
	/**
	 * 既往史手术列表
	 */
	private List<PhPastHistoryOperation> phPastHistoryOperationList = new ArrayList<PhPastHistoryOperation>();
	/**
	 * 既往史外伤列表
	 */
	private List<PhPastHistoryTrauma> phPastHistoryTraumaList = new ArrayList<PhPastHistoryTrauma>();
	/**
	 * 既往史输血列表
	 */
	private List<PhPastHistoryBloodTransfusion> phPastHistoryBloodTransfusionList = new ArrayList<PhPastHistoryBloodTransfusion>();
	
	private Long accountId;
	private Long babyId;
	
	
	public Long getBabyId() {
		return babyId;
	}
	public void setBabyId(Long babyId) {
		this.babyId = babyId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public ResidentsFileService getResidentsFileService() {
		return residentsFileService;
	}
	public void setResidentsFileService(ResidentsFileService residentsFileService) {
		this.residentsFileService = residentsFileService;
	}
	
	public PhPeopleBasicInfo getPhPeopleBasicInfo() {
		return phPeopleBasicInfo;
	}
	public void setPhPeopleBasicInfo(PhPeopleBasicInfo phPeopleBasicInfo) {
		this.phPeopleBasicInfo = phPeopleBasicInfo;
	}
	public List<PhPastHistoryIllness> getPhPastHistoryIllnessList() {
		return phPastHistoryIllnessList;
	}
	public void setPhPastHistoryIllnessList(
			List<PhPastHistoryIllness> phPastHistoryIllnessList) {
		this.phPastHistoryIllnessList = phPastHistoryIllnessList;
	}
	public List<PhPastHistoryOperation> getPhPastHistoryOperationList() {
		return phPastHistoryOperationList;
	}
	public void setPhPastHistoryOperationList(
			List<PhPastHistoryOperation> phPastHistoryOperationList) {
		this.phPastHistoryOperationList = phPastHistoryOperationList;
	}
	public List<PhPastHistoryTrauma> getPhPastHistoryTraumaList() {
		return phPastHistoryTraumaList;
	}
	public void setPhPastHistoryTraumaList(
			List<PhPastHistoryTrauma> phPastHistoryTraumaList) {
		this.phPastHistoryTraumaList = phPastHistoryTraumaList;
	}
	public List<PhPastHistoryBloodTransfusion> getPhPastHistoryBloodTransfusionList() {
		return phPastHistoryBloodTransfusionList;
	}
	public void setPhPastHistoryBloodTransfusionList(
			List<PhPastHistoryBloodTransfusion> phPastHistoryBloodTransfusionList) {
		this.phPastHistoryBloodTransfusionList = phPastHistoryBloodTransfusionList;
	}
	
	public KyUserInfo getKyUserInfo() {
		return kyUserInfo;
	}
	public void setKyUserInfo(KyUserInfo kyUserInfo) {
		this.kyUserInfo = kyUserInfo;
	}
	/**
	 * 跳转到新增页面(儿童)
	 * @return
	 */
	public String toAddPhPeopleBasicInfo() {
		if("child".equals(phPeopleBasicInfo.getInfoOwner())){
			return SUCCESS;
		}else{
			return "mum";
		}
	}
	/**
	 * 保存新增个人基本信息
	 * @param tableInfo
	 * @return
	 */
	public String saveOrUpdatePhPeopleBasicInfo() {

		if(accountId!=null){
			FamilyAccountInfo familyAccountInfo = residentsFileService.get(accountId, FamilyAccountInfo.class);
			if(familyAccountInfo!=null){
				phPeopleBasicInfo.setFamilyAccountInfo(familyAccountInfo);
			}
		}
		if(babyId!=null){
			ConsultBabyInfo consultBabyInfo = residentsFileService.get(babyId, ConsultBabyInfo.class);
			if(consultBabyInfo!=null){
				phPeopleBasicInfo.setConsultBabyInfo(consultBabyInfo);
			}
		}
		
		Long id = residentsFileService.saveOrUpdatePhPeopleBasicInfo(phPeopleBasicInfo);
		
		String msg =  this.residentsFileService.saveAllPhPeopleBasicInfo(phPeopleBasicInfo, 
				phPastHistoryIllnessList, phPastHistoryOperationList, 
				phPastHistoryTraumaList, phPastHistoryBloodTransfusionList);
		this.phPeopleBasicInfo = this.residentsFileService.getPhPeopleBasicInfoById(id);
		//this.phPeopleBasicInfo = bssicInfo;
		addActionMessage(msg);
		return "phPeopleBasicInfoPageUpdate";
	}
	/**
	 * 确认提交个人基本信息
	 * @param tableInfo
	 * @return
	 */
	public String submitPhPeopleBasicInfo() {
		
		/*	
		 * 1.判断存不存在家庭开户信息
		 * 2.如果存在，保存开户信息，则与档案信息进行关联
		 * 3.如果不存在，返回不存在家庭信息（正常情况不会发生）
		 */
		if(accountId!=null){
			FamilyAccountInfo familyAccountInfo = residentsFileService.get(accountId, FamilyAccountInfo.class);
			if(familyAccountInfo!=null){
				phPeopleBasicInfo.setFamilyAccountInfo(familyAccountInfo);
			}
		}
		if(babyId!=null){
			ConsultBabyInfo consultBabyInfo = residentsFileService.get(babyId, ConsultBabyInfo.class);
			if(consultBabyInfo!=null){
				phPeopleBasicInfo.setConsultBabyInfo(consultBabyInfo);
			}
		}
		
		//Long id = 
		residentsFileService.saveOrUpdatePhPeopleBasicInfo(phPeopleBasicInfo);
		
		/*
		 * 保存既往病史
		 */
		String msg =  this.residentsFileService.saveAllPhPeopleBasicInfo(phPeopleBasicInfo, 
				phPastHistoryIllnessList, phPastHistoryOperationList, 
				phPastHistoryTraumaList, phPastHistoryBloodTransfusionList);
		
		/*
		 * 获取档案信息
		 */
		//this.phPeopleBasicInfo = this.residentsFileService.getPhPeopleBasicInfoById(id);
		
		addActionMessage(msg);
		if("mum".equals(phPeopleBasicInfo.getInfoOwner())){
			return redirectActionResult("phMumPeopleBasicInfoPageList");
		}
		return redirectActionResult("phPeopleBasicInfoPageList");
	}
	/**
	 * 得到个人信息列表
	 * @return
	 */
	public String getPhPeopleBasicInfoList(){
		
		FamilyAccountInfo familyAccountInfo = residentsFileService.get(accountId, FamilyAccountInfo.class);
		
		List<PhPeopleBasicInfo> list = new ArrayList<PhPeopleBasicInfo>();
		
		if(familyAccountInfo!=null){
			String tableId = "list";
			PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
			
			if(phPeopleBasicInfo == null){
				phPeopleBasicInfo = new PhPeopleBasicInfo();
			}
			phPeopleBasicInfo.setInfoOwner("child");
			phPeopleBasicInfo.setFamilyAccountInfo(familyAccountInfo);
			
			list = this.residentsFileService.getPhPeopleBasicInfoListByPage(psm, phPeopleBasicInfo);
		}
		
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 得到个人信息列表(母亲)
	 * @return
	 */
	public String getMumPhPeopleBasicInfoList(){
		
		FamilyAccountInfo familyAccountInfo = residentsFileService.get(accountId, FamilyAccountInfo.class);
		
		List<PhPeopleBasicInfo> list = new ArrayList<PhPeopleBasicInfo>();
		
		if(familyAccountInfo!=null){
		
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		if(phPeopleBasicInfo == null){
			phPeopleBasicInfo = new PhPeopleBasicInfo();
		}
		
		phPeopleBasicInfo.setInfoOwner("mum");
		phPeopleBasicInfo.setFamilyAccountInfo(familyAccountInfo);
		
		list = this.residentsFileService.getPhPeopleBasicInfoListByPage(psm, phPeopleBasicInfo);
		}
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 跳转编辑
	 * @return
	 */
	public String toUpdatePhPeopleBasicInfo(){
		this.phPeopleBasicInfo = this.residentsFileService.getPhPeopleBasicInfoById(phPeopleBasicInfo.getId());
		if("mum".equals(phPeopleBasicInfo.getInfoOwner())){
			return "mum";
		}else{
			return SUCCESS;
		}
	}
	/**
	 * 跳转查看
	 * @return
	 */
	public String toViewPhPeopleBasicInfo(){
		this.phPeopleBasicInfo = this.residentsFileService.getPhPeopleBasicInfoById(phPeopleBasicInfo.getId());
		if("mum".equals(phPeopleBasicInfo.getInfoOwner())){
			return "mum";
		}else{
			return SUCCESS;
		}
	}
	/**
	 * 查看
	 * @return
	 */
	public String viewPhPeopleBasicInfo(){
		this.phPeopleBasicInfo = this.residentsFileService.getPhPeopleBasicInfoById(phPeopleBasicInfo.getId());
		return SUCCESS;
	}
	
}
