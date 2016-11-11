package com.java.publichealth.productionvisit.vo;

import java.sql.Timestamp;

import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.core.BaseDomain;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

/**
 * 第2-5次产前随访信息实体
 * @author lihao
 *
 */

public class PhPrenatalFollowRecordAfter extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	//------------------
	private PhPeopleBasicInfo peopleBasicInfo;
	private KyUserInfo kyUserInfo;
	
	private FamilyAccountInfo familyAccountInfo;
	
	//------------------
	private String gestationalWeek;
	private String mainSuit;
	private String weight;
	private String fundusHeight;
	private String widerBellies;
	private String fetal;
	private String fetalHeartRate;
	private String bloodPressureHigh;
	private String bloodPressureLow;
	private String hemoglobin;
	private String urineProtein;
	private String otherAuxiliaryExaminations;
	private String classify;
	private String classifyOther;
	private String advice;
	private String adviceOther;
	private String isReferral;
	private String ireferralReason;
	private String ireferralOrg;
	private Timestamp nextFollowDate;
	private String followTimes;


	
	public FamilyAccountInfo getFamilyAccountInfo() {
		return familyAccountInfo;
	}

	public void setFamilyAccountInfo(FamilyAccountInfo familyAccountInfo) {
		this.familyAccountInfo = familyAccountInfo;
	}

	public KyUserInfo getKyUserInfo() {
		return kyUserInfo;
	}

	public void setKyUserInfo(KyUserInfo kyUserInfo) {
		this.kyUserInfo = kyUserInfo;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PhPeopleBasicInfo getPeopleBasicInfo() {
		return peopleBasicInfo;
	}

	public void setPeopleBasicInfo(PhPeopleBasicInfo peopleBasicInfo) {
		this.peopleBasicInfo = peopleBasicInfo;
	}

	public String getGestationalWeek() {
		return this.gestationalWeek;
	}

	public void setGestationalWeek(String gestationalWeek) {
		this.gestationalWeek = gestationalWeek;
	}

	public String getMainSuit() {
		return this.mainSuit;
	}

	public void setMainSuit(String mainSuit) {
		this.mainSuit = mainSuit;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getFundusHeight() {
		return this.fundusHeight;
	}

	public void setFundusHeight(String fundusHeight) {
		this.fundusHeight = fundusHeight;
	}

	public String getWiderBellies() {
		return this.widerBellies;
	}

	public void setWiderBellies(String widerBellies) {
		this.widerBellies = widerBellies;
	}

	public String getFetal() {
		return this.fetal;
	}

	public void setFetal(String fetal) {
		this.fetal = fetal;
	}

	public String getFetalHeartRate() {
		return this.fetalHeartRate;
	}

	public void setFetalHeartRate(String fetalHeartRate) {
		this.fetalHeartRate = fetalHeartRate;
	}

	public String getBloodPressureHigh() {
		return this.bloodPressureHigh;
	}

	public void setBloodPressureHigh(String bloodPressureHigh) {
		this.bloodPressureHigh = bloodPressureHigh;
	}

	public String getBloodPressureLow() {
		return this.bloodPressureLow;
	}

	public void setBloodPressureLow(String bloodPressureLow) {
		this.bloodPressureLow = bloodPressureLow;
	}

	public String getHemoglobin() {
		return this.hemoglobin;
	}

	public void setHemoglobin(String hemoglobin) {
		this.hemoglobin = hemoglobin;
	}

	public String getUrineProtein() {
		return this.urineProtein;
	}

	public void setUrineProtein(String urineProtein) {
		this.urineProtein = urineProtein;
	}

	public String getOtherAuxiliaryExaminations() {
		return this.otherAuxiliaryExaminations;
	}

	public void setOtherAuxiliaryExaminations(String otherAuxiliaryExaminations) {
		this.otherAuxiliaryExaminations = otherAuxiliaryExaminations;
	}

	public String getClassify() {
		return this.classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getClassifyOther() {
		return this.classifyOther;
	}

	public void setClassifyOther(String classifyOther) {
		this.classifyOther = classifyOther;
	}

	public String getAdvice() {
		return this.advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getAdviceOther() {
		return this.adviceOther;
	}

	public void setAdviceOther(String adviceOther) {
		this.adviceOther = adviceOther;
	}

	public String getIsReferral() {
		return this.isReferral;
	}

	public void setIsReferral(String isReferral) {
		this.isReferral = isReferral;
	}

	public String getIreferralReason() {
		return this.ireferralReason;
	}

	public void setIreferralReason(String ireferralReason) {
		this.ireferralReason = ireferralReason;
	}

	public String getIreferralOrg() {
		return this.ireferralOrg;
	}

	public void setIreferralOrg(String ireferralOrg) {
		this.ireferralOrg = ireferralOrg;
	}

	public Timestamp getNextFollowDate() {
		return this.nextFollowDate;
	}

	public void setNextFollowDate(Timestamp nextFollowDate) {
		this.nextFollowDate = nextFollowDate;
	}

	public String getFollowTimes() {
		return this.followTimes;
	}

	public void setFollowTimes(String followTimes) {
		this.followTimes = followTimes;
	}

}