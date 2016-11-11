package com.java.publichealth.familyaccount.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.java.common.lang.StringUtils;
import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.core.Action;
import com.java.publichealth.familyaccount.service.FamilyAccountInfoService;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.util.JsonUtil;

public class FamilyAccountInfoAction extends Action {

	private static final long serialVersionUID = 6021554794701077618L;
	
	private FamilyAccountInfoService familyAccountInfoService;
	
	private FamilyAccountInfo familyAccountInfo;
	
	private ConsultBabyInfo consultBabyInfo;
	
	private String accountId;
	
	private String mes;
	
	private String page_id;
	
	
	public String toList(){
		PageSortModel model = new PageSortModel(this.getHttpServletRequest(),"list");
		List<FamilyAccountInfo> list = familyAccountInfoService.getFamilyAccountInfoList(model,familyAccountInfo);
		putToRequest("list", list);
		return SUCCESS;
	}
	
	public String toChange(){
		return getBabyInfoList();
	}
	
	public String toAdd(){
		/*
		 * 1.如果familyAccountInfo为空，跳转到添加页面
		 * 2.不为空,获取家庭开户信息
		 */
		//不为空,获取家庭开户信息
		if(familyAccountInfo!=null){
			/*
			 * 1.获取开户信息
			 * 2.获取家庭开户关联的宝宝信息
			 * 3.组合分配设置宝宝信息，回显
			 */
			this.familyAccountInfo = familyAccountInfoService.getFamilyAccountInfo(familyAccountInfo.getId());
			familyAccountInfo.setConsultBabyInfos(
					familyAccountInfoService.getObjectListOfBabyByPage(familyAccountInfo.getId()));
			//setFamilyBabyInfo(familyAccountInfo);
		}
		return SUCCESS;
	}
	
	private void setFamilyBabyInfo(FamilyAccountInfo familyAccountInfo){
		/*
		 * 1.获取babyid数组
		 * 2.根据babyid信息，获取宝宝信息
		 * 3.组装宝宝信息串，赋值到familyAccountInfo，用于页面回显
		 */
		String babyIds = familyAccountInfo.getBabyIds();
		if(StringUtils.isNotBlank(babyIds)){
			String [] babyIdArr = null;
			if(babyIds.contains(","))
				babyIdArr = babyIds.split(",");
			else
				babyIdArr = new String[]{babyIds};
			
			String babyNames = "";
			String babySexs = "";
			String babyBirthdays = "";
			
			for(int i=0;i<babyIdArr.length;i++){
				ConsultBabyInfo consultBabyInfo = familyAccountInfoService.get(Long.parseLong(babyIdArr[i]), ConsultBabyInfo.class);
				babyNames+=consultBabyInfo.getBabyName()+",";
				babySexs+=consultBabyInfo.getSex()+",";
				babyBirthdays+=consultBabyInfo.getBirthday()+",";
			}
			
			familyAccountInfo.setBabyNames(babyNames.substring(0,babyNames.length()-1));
			familyAccountInfo.setBabySexs(babySexs.substring(0,babySexs.length()-1));
			familyAccountInfo.setBabyBirthdays(babyBirthdays.substring(0,babyBirthdays.length()-1));
		}
	}
	
	public String saveOrUpdate(){
		Map<String, String> validateField_isEmpty = familyAccountInfo.validateField_isEmpty();
		if(validateField_isEmpty.isEmpty()){
			if(familyAccountInfo.getId()!=null){
				familyAccountInfoService.updateFamilyAccountInfo(familyAccountInfo);
				
				if("true".equals(familyAccountInfo.getIsSubmit())) return redirectActionResult("toList");
			}else{
				familyAccountInfoService.saveFamilyAccountInfo(familyAccountInfo);
			}
		}
		return redirectActionResult("toAdd");
	}
	public String toHealthInfoHandle(){
		return SUCCESS;
	}
	
	public String getFamilyInfo(){
		FamilyAccountInfo info_query = familyAccountInfoService.get(Long.parseLong(accountId), FamilyAccountInfo.class);
		JSONObject jo = new JSONObject();
		String username = "";
		String phone = "";
		if(StringUtils.isNotBlank(info_query.getFatherName())){
			username += info_query.getFatherName()+"/";
			phone += info_query.getFatherPhone()+"/";
		}
		if(StringUtils.isNotBlank(info_query.getMotherName())){
			username += info_query.getMotherName();
			phone += info_query.getMotherPhone();
		}
		jo.put("username", username);
		jo.put("phone", phone);
		jo.put("fatherName", StringUtils.isNotBlank(info_query.getFatherName())?info_query.getFatherName():"");
		jo.put("fatherPhone", StringUtils.isNotBlank(info_query.getFatherPhone())?info_query.getFatherPhone():"");
		jo.put("motherName", info_query.getMotherName());
		jo.put("motherPhone", info_query.getMotherPhone());
		jo.put("babyIds", info_query.getBabyIds());
		JsonUtil.writeJson(ServletActionContext.getResponse(), jo.toString());
		return NONE;
	}
	
	public String getBabyInfo(){
		
		FamilyAccountInfo info_query = familyAccountInfoService.get(Long.parseLong(accountId), FamilyAccountInfo.class);
		ConsultBabyInfo consultBabyInfo = familyAccountInfoService.get(Long.parseLong(info_query.getBabyIds()), ConsultBabyInfo.class);
		JSONObject jo = JSONObject.fromObject(consultBabyInfo);
		JsonUtil.writeJson(getResponseValue(), jo.toString());
		return NONE;
	}
	
	public String getBabyInfoList(){
		/*
		 * 1.根据家庭开户信息id，获取家庭开户信息
		 * 2.根据家庭开户信息中的babyids串，获取关联的宝宝信息
		 */
		PageSortModel model = new PageSortModel(this.getHttpServletRequest(),"list");
		
		List<ConsultBabyInfo> consultBabyInfoList = 
				familyAccountInfoService.getObjectListOfBabyByPage(model,Long.parseLong(accountId),consultBabyInfo,page_id);
		
		putToRequest("list", consultBabyInfoList);
		
		return SUCCESS;
	}
	
	
	public String getPage_id() {
		return page_id;
	}

	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setFamilyAccountInfoService(
			FamilyAccountInfoService familyAccountInfoService) {
		this.familyAccountInfoService = familyAccountInfoService;
	}

	public FamilyAccountInfo getFamilyAccountInfo() {
		return familyAccountInfo;
	}

	public void setFamilyAccountInfo(FamilyAccountInfo familyAccountInfo) {
		this.familyAccountInfo = familyAccountInfo;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public ConsultBabyInfo getConsultBabyInfo() {
		return consultBabyInfo;
	}

	public void setConsultBabyInfo(ConsultBabyInfo consultBabyInfo) {
		this.consultBabyInfo = consultBabyInfo;
	}
}
