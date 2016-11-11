package com.java.publichealth.familyaccount.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.common.lang.StringUtils;
import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.core.BaseDomain;
import com.java.publichealth.residentsfile.vo.KyUserInfo;

/**
 * 家庭开户信息
 */
public class FamilyAccountInfo extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 6936729902300844955L;
	
	public FamilyAccountInfo() {
	}
	
	private Long id;//'家庭开户信息主键',
	private String fatherName;//'父亲姓名',
	private String motherName;//'母亲姓名',
	private String province;//'所在省',
	private String city;//'所在市',
	private String area;//'所在区',
	private String street;//'所在街道',
	private String address;//'详细地址',
	private String fatherPhone;//'父亲联系电话',
	private String motherPhone;//'母亲联系电话',
	private String establishUnit;//'建档单位',
	private String committeeName;//'村(居)委会名称',
	private String establishHuman;//'建档人',
	private String responsibleDoctor;//'责任医生',
/*	private String createPerson;//'创建人',
	private Date createTime;//'创建时间',
	private String updatePerson;//'修改人',
	private Date updateTime;//'修改时间',
*/	private String isEnable;//'是否有效(Y/N)',

	private KyUserInfo userInfo;
	private String babyIds;
	
	//-------------------------额外的字段
	private String name;//父亲 or 母亲名称
	private String phone;//父亲 or 母亲电话
	
	private String babyNames;
	private String babySexs;
	private String babyBirthdays;
	
	private String isSubmit;//提交操作
	private List<ConsultBabyInfo> consultBabyInfos;
	
	private String registPhone;//注册康优宝贝的电话号码
	private String parentName;//注册康优宝贝的家长姓名
	//-------------------------
	
	public Map<String,String> validateField_isEmpty(){
		Map<String,String> errorMap = new HashMap<String,String>();
		if(StringUtils.isBlank(fatherName)&&StringUtils.isBlank(motherName)){
			errorMap.put("parentName", "双亲姓名不能都为空");
		}
		if(StringUtils.isBlank(fatherPhone)&&StringUtils.isBlank(motherPhone)){
			errorMap.put("parentPhone", "双亲的电话号码不能都为空");
		}
		return errorMap;
	}
	//-------------------------------------获取宝宝信息数组
	public String[] getBabyNameArr(){
		String[] babyNameArr = null;
		if(StringUtils.isNotBlank(babyNames)){
			if(babyNames.contains(","))
				babyNameArr = babyNames.split(",");
			else
				babyNameArr = new String[]{babyNames};
		}
		return babyNameArr;
	}
	public String[] getBabySexArr(){
		String[] babySexArr = null;
		if(StringUtils.isNotBlank(babySexs)){
			if(babySexs.contains(","))
				babySexArr = babySexs.split(",");
			else
				babySexArr = new String[]{babySexs};
		}
		return babySexArr;
	}
	public String[] getBabyBirthdayArr(){
		String[] babyBirthdayArr = null;
		if(StringUtils.isNotBlank(babyBirthdays)){
			if(babyBirthdays.contains(","))
				babyBirthdayArr = babyBirthdays.split(",");
			else
				babyBirthdayArr = new String[]{babyBirthdays};
		}
		return babyBirthdayArr;
	}
	//-------------------------------------
	public List<ConsultBabyInfo> getConsultBabyInfos() {
		return consultBabyInfos;
	}
	public String getIsSubmit() {
		return isSubmit;
	}
	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}
	public void setConsultBabyInfos(List<ConsultBabyInfo> consultBabyInfos) {
		this.consultBabyInfos = consultBabyInfos;
	}
	public String getBabyNames() {
		return babyNames;
	}
	public void setBabyNames(String babyNames) {
		this.babyNames = babyNames;
	}
	
	public String getBabySexs() {
		return babySexs;
	}


	public void setBabySexs(String babySexs) {
		this.babySexs = babySexs;
	}


	public String getBabyBirthdays() {
		return babyBirthdays;
	}


	public void setBabyBirthdays(String babyBirthdays) {
		this.babyBirthdays = babyBirthdays;
	}


	public KyUserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(KyUserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getBabyIds() {
		return babyIds;
	}
	public void setBabyIds(String babyIds) {
		this.babyIds = babyIds;
	}
	public Long getId() {
		return id;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFatherPhone() {
		return fatherPhone;
	}
	public void setFatherPhone(String fatherPhone) {
		this.fatherPhone = fatherPhone;
	}
	public String getMotherPhone() {
		return motherPhone;
	}
	public void setMotherPhone(String motherPhone) {
		this.motherPhone = motherPhone;
	}
	public String getEstablishUnit() {
		return establishUnit;
	}
	public void setEstablishUnit(String establishUnit) {
		this.establishUnit = establishUnit;
	}
	public String getCommitteeName() {
		return committeeName;
	}
	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}
	public String getEstablishHuman() {
		return establishHuman;
	}
	public void setEstablishHuman(String establishHuman) {
		this.establishHuman = establishHuman;
	}
	public String getResponsibleDoctor() {
		return responsibleDoctor;
	}
	public void setResponsibleDoctor(String responsibleDoctor) {
		this.responsibleDoctor = responsibleDoctor;
	}
	public String getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	public String getRegistPhone() {
		return registPhone;
	}
	public void setRegistPhone(String registPhone) {
		this.registPhone = registPhone;
	}
}