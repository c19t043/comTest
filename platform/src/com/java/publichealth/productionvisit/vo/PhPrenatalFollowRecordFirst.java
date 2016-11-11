package com.java.publichealth.productionvisit.vo;

import java.util.Date;

import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.core.BaseDomain;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;


/**
 * 第一次产访信息实体类
 * @author lihao
 *
 */

public class PhPrenatalFollowRecordFirst extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	//
	private PhPeopleBasicInfo peopleBasicInfo;
	private KyUserInfo kyUserInfo;
	
	private FamilyAccountInfo familyAccountInfo;
	
	//
	private String gestationalWeek;
	private String maternalAge;
	private String husbandName;
	private String husbandAge;
	private String husbandPhone;
	private String pregnantTimes;
	private String vaginalDeliveryTimes;
	private String cesareanSectionTimes;
	private String lastMenstruation;
	private String expectedBirthDate;
	private String pastHistory;
	private String pastHistoryOther;
	private String familyHistory;
	private String familyHistoryOther;
	private String personalHistory;
	private String personalHistoryOther;
	private String gynaecologyOperationHis;
	private String isGynaecologyOperationHis;
	private String gestationHisAbortion;
	private String gestationHisDeadFetus;
	private String gestationHisDeadBirth;
	private String gestationHisNeonatalDeath;
	private String gestationHisBirthDefects;
	private String height;
	private String weight;
	private String bodyMassIndex;
	private String highBlood;
	private String lowBlood;
	private String heart;
	private String heartExceptions;
	private String lungs;
	private String lungsExceptions;
	private String vulva;
	private String vulvaExceptions;
	private String vagina;
	private String vaginaExceptions;
	private String cervical;
	private String cervicalExceptions;
	private String uterus;
	private String uterusExceptions;
	private String appendix;
	private String appendixExceptions;
	private String bloodHemoglobin;
	private String bloodWhiteCellCount;
	private String bloodPlateletCount;
	private String bloodOther;
	private String urineProtein;
	private String urineSugar;
	private String urineKetone;
	private String urineBlood;
	private String urineOther;
	private String bloodType;
	private String bloodTypeRh;
	private String bloodSugar;
	private String liverSgpt;
	private String liverSgot;
	private String liverAlbumin;
	private String liverBilirubin;
	private String liverConjugatedBilirubin;
	private String liverSerumCreatinine;
	private String liverBun;
	private String vaginalDischarge;
	private String vaginalDischargeExceptions;
	private String vaginalCleanliness;
	private String hepatitisBSurfaceAntigen;
	private String hepatitisBSurfaceAntibody;
	private String hepatitisBEAntigen;
	private String hepatitisBEAntibody;
	private String hepatitisBCoreAntibody;
	private String serologicalTest;
	private String hivAntibodyTest;
	private String BUltrasonic;
	private String overallEvaluation;
	private String overallEvaluationExceptions;
	private String healthCareGuidance;
	private String healthCareGuidanceOther;
	private String isReferral;
	private String ireferralReason;
	private String ireferralOrg;
	private Date nextFollowDate;

	
	
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

	public String getMaternalAge() {
		return this.maternalAge;
	}

	public void setMaternalAge(String maternalAge) {
		this.maternalAge = maternalAge;
	}

	public String getHusbandName() {
		return this.husbandName;
	}

	public void setHusbandName(String husbandName) {
		this.husbandName = husbandName;
	}

	public String getHusbandAge() {
		return this.husbandAge;
	}

	public void setHusbandAge(String husbandAge) {
		this.husbandAge = husbandAge;
	}

	public String getHusbandPhone() {
		return this.husbandPhone;
	}

	public void setHusbandPhone(String husbandPhone) {
		this.husbandPhone = husbandPhone;
	}

	public String getPregnantTimes() {
		return this.pregnantTimes;
	}

	public void setPregnantTimes(String pregnantTimes) {
		this.pregnantTimes = pregnantTimes;
	}

	public String getVaginalDeliveryTimes() {
		return this.vaginalDeliveryTimes;
	}

	public void setVaginalDeliveryTimes(String vaginalDeliveryTimes) {
		this.vaginalDeliveryTimes = vaginalDeliveryTimes;
	}

	public String getCesareanSectionTimes() {
		return this.cesareanSectionTimes;
	}

	public void setCesareanSectionTimes(String cesareanSectionTimes) {
		this.cesareanSectionTimes = cesareanSectionTimes;
	}

	public String getLastMenstruation() {
		return this.lastMenstruation;
	}

	public void setLastMenstruation(String lastMenstruation) {
		this.lastMenstruation = lastMenstruation;
	}

	public String getExpectedBirthDate() {
		return this.expectedBirthDate;
	}

	public void setExpectedBirthDate(String expectedBirthDate) {
		this.expectedBirthDate = expectedBirthDate;
	}

	public String getPastHistory() {
		return this.pastHistory;
	}

	public void setPastHistory(String pastHistory) {
		this.pastHistory = pastHistory;
	}

	public String getPastHistoryOther() {
		return this.pastHistoryOther;
	}

	public void setPastHistoryOther(String pastHistoryOther) {
		this.pastHistoryOther = pastHistoryOther;
	}

	public String getFamilyHistory() {
		return this.familyHistory;
	}

	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}

	public String getFamilyHistoryOther() {
		return this.familyHistoryOther;
	}

	public void setFamilyHistoryOther(String familyHistoryOther) {
		this.familyHistoryOther = familyHistoryOther;
	}

	public String getPersonalHistory() {
		return this.personalHistory;
	}

	public void setPersonalHistory(String personalHistory) {
		this.personalHistory = personalHistory;
	}

	public String getPersonalHistoryOther() {
		return this.personalHistoryOther;
	}

	public void setPersonalHistoryOther(String personalHistoryOther) {
		this.personalHistoryOther = personalHistoryOther;
	}

	public String getGynaecologyOperationHis() {
		return this.gynaecologyOperationHis;
	}

	public void setGynaecologyOperationHis(String gynaecologyOperationHis) {
		this.gynaecologyOperationHis = gynaecologyOperationHis;
	}

	public String getIsGynaecologyOperationHis() {
		return this.isGynaecologyOperationHis;
	}

	public void setIsGynaecologyOperationHis(String isGynaecologyOperationHis) {
		this.isGynaecologyOperationHis = isGynaecologyOperationHis;
	}

	public String getGestationHisAbortion() {
		return this.gestationHisAbortion;
	}

	public void setGestationHisAbortion(String gestationHisAbortion) {
		this.gestationHisAbortion = gestationHisAbortion;
	}

	public String getGestationHisDeadFetus() {
		return this.gestationHisDeadFetus;
	}

	public void setGestationHisDeadFetus(String gestationHisDeadFetus) {
		this.gestationHisDeadFetus = gestationHisDeadFetus;
	}

	public String getGestationHisDeadBirth() {
		return this.gestationHisDeadBirth;
	}

	public void setGestationHisDeadBirth(String gestationHisDeadBirth) {
		this.gestationHisDeadBirth = gestationHisDeadBirth;
	}

	public String getGestationHisNeonatalDeath() {
		return this.gestationHisNeonatalDeath;
	}

	public void setGestationHisNeonatalDeath(String gestationHisNeonatalDeath) {
		this.gestationHisNeonatalDeath = gestationHisNeonatalDeath;
	}

	public String getGestationHisBirthDefects() {
		return this.gestationHisBirthDefects;
	}

	public void setGestationHisBirthDefects(String gestationHisBirthDefects) {
		this.gestationHisBirthDefects = gestationHisBirthDefects;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getBodyMassIndex() {
		return this.bodyMassIndex;
	}

	public void setBodyMassIndex(String bodyMassIndex) {
		this.bodyMassIndex = bodyMassIndex;
	}

	public String getHighBlood() {
		return this.highBlood;
	}

	public void setHighBlood(String highBlood) {
		this.highBlood = highBlood;
	}

	public String getLowBlood() {
		return this.lowBlood;
	}

	public void setLowBlood(String lowBlood) {
		this.lowBlood = lowBlood;
	}

	public String getHeart() {
		return this.heart;
	}

	public void setHeart(String heart) {
		this.heart = heart;
	}

	public String getHeartExceptions() {
		return this.heartExceptions;
	}

	public void setHeartExceptions(String heartExceptions) {
		this.heartExceptions = heartExceptions;
	}

	public String getLungs() {
		return this.lungs;
	}

	public void setLungs(String lungs) {
		this.lungs = lungs;
	}

	public String getLungsExceptions() {
		return this.lungsExceptions;
	}

	public void setLungsExceptions(String lungsExceptions) {
		this.lungsExceptions = lungsExceptions;
	}

	public String getVulva() {
		return this.vulva;
	}

	public void setVulva(String vulva) {
		this.vulva = vulva;
	}

	public String getVulvaExceptions() {
		return this.vulvaExceptions;
	}

	public void setVulvaExceptions(String vulvaExceptions) {
		this.vulvaExceptions = vulvaExceptions;
	}

	public String getVagina() {
		return this.vagina;
	}

	public void setVagina(String vagina) {
		this.vagina = vagina;
	}

	public String getVaginaExceptions() {
		return this.vaginaExceptions;
	}

	public void setVaginaExceptions(String vaginaExceptions) {
		this.vaginaExceptions = vaginaExceptions;
	}

	public String getCervical() {
		return this.cervical;
	}

	public void setCervical(String cervical) {
		this.cervical = cervical;
	}

	public String getCervicalExceptions() {
		return this.cervicalExceptions;
	}

	public void setCervicalExceptions(String cervicalExceptions) {
		this.cervicalExceptions = cervicalExceptions;
	}

	public String getUterus() {
		return this.uterus;
	}

	public void setUterus(String uterus) {
		this.uterus = uterus;
	}

	public String getUterusExceptions() {
		return this.uterusExceptions;
	}

	public void setUterusExceptions(String uterusExceptions) {
		this.uterusExceptions = uterusExceptions;
	}

	public String getAppendix() {
		return this.appendix;
	}

	public void setAppendix(String appendix) {
		this.appendix = appendix;
	}

	public String getAppendixExceptions() {
		return this.appendixExceptions;
	}

	public void setAppendixExceptions(String appendixExceptions) {
		this.appendixExceptions = appendixExceptions;
	}

	public String getBloodHemoglobin() {
		return this.bloodHemoglobin;
	}

	public void setBloodHemoglobin(String bloodHemoglobin) {
		this.bloodHemoglobin = bloodHemoglobin;
	}

	public String getBloodWhiteCellCount() {
		return this.bloodWhiteCellCount;
	}

	public void setBloodWhiteCellCount(String bloodWhiteCellCount) {
		this.bloodWhiteCellCount = bloodWhiteCellCount;
	}

	public String getBloodPlateletCount() {
		return this.bloodPlateletCount;
	}

	public void setBloodPlateletCount(String bloodPlateletCount) {
		this.bloodPlateletCount = bloodPlateletCount;
	}

	public String getBloodOther() {
		return this.bloodOther;
	}

	public void setBloodOther(String bloodOther) {
		this.bloodOther = bloodOther;
	}

	public String getUrineProtein() {
		return this.urineProtein;
	}

	public void setUrineProtein(String urineProtein) {
		this.urineProtein = urineProtein;
	}

	public String getUrineSugar() {
		return this.urineSugar;
	}

	public void setUrineSugar(String urineSugar) {
		this.urineSugar = urineSugar;
	}

	public String getUrineKetone() {
		return this.urineKetone;
	}

	public void setUrineKetone(String urineKetone) {
		this.urineKetone = urineKetone;
	}

	public String getUrineBlood() {
		return this.urineBlood;
	}

	public void setUrineBlood(String urineBlood) {
		this.urineBlood = urineBlood;
	}

	public String getUrineOther() {
		return this.urineOther;
	}

	public void setUrineOther(String urineOther) {
		this.urineOther = urineOther;
	}

	public String getBloodType() {
		return this.bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getBloodTypeRh() {
		return this.bloodTypeRh;
	}

	public void setBloodTypeRh(String bloodTypeRh) {
		this.bloodTypeRh = bloodTypeRh;
	}

	public String getBloodSugar() {
		return this.bloodSugar;
	}

	public void setBloodSugar(String bloodSugar) {
		this.bloodSugar = bloodSugar;
	}

	public String getLiverSgpt() {
		return this.liverSgpt;
	}

	public void setLiverSgpt(String liverSgpt) {
		this.liverSgpt = liverSgpt;
	}

	public String getLiverSgot() {
		return this.liverSgot;
	}

	public void setLiverSgot(String liverSgot) {
		this.liverSgot = liverSgot;
	}

	public String getLiverAlbumin() {
		return this.liverAlbumin;
	}

	public void setLiverAlbumin(String liverAlbumin) {
		this.liverAlbumin = liverAlbumin;
	}

	public String getLiverBilirubin() {
		return this.liverBilirubin;
	}

	public void setLiverBilirubin(String liverBilirubin) {
		this.liverBilirubin = liverBilirubin;
	}

	public String getLiverConjugatedBilirubin() {
		return this.liverConjugatedBilirubin;
	}

	public void setLiverConjugatedBilirubin(String liverConjugatedBilirubin) {
		this.liverConjugatedBilirubin = liverConjugatedBilirubin;
	}

	public String getLiverSerumCreatinine() {
		return this.liverSerumCreatinine;
	}

	public void setLiverSerumCreatinine(String liverSerumCreatinine) {
		this.liverSerumCreatinine = liverSerumCreatinine;
	}

	public String getLiverBun() {
		return this.liverBun;
	}

	public void setLiverBun(String liverBun) {
		this.liverBun = liverBun;
	}

	public String getVaginalDischarge() {
		return this.vaginalDischarge;
	}

	public void setVaginalDischarge(String vaginalDischarge) {
		this.vaginalDischarge = vaginalDischarge;
	}

	public String getVaginalDischargeExceptions() {
		return this.vaginalDischargeExceptions;
	}

	public void setVaginalDischargeExceptions(String vaginalDischargeExceptions) {
		this.vaginalDischargeExceptions = vaginalDischargeExceptions;
	}

	public String getVaginalCleanliness() {
		return this.vaginalCleanliness;
	}

	public void setVaginalCleanliness(String vaginalCleanliness) {
		this.vaginalCleanliness = vaginalCleanliness;
	}

	public String getHepatitisBSurfaceAntigen() {
		return this.hepatitisBSurfaceAntigen;
	}

	public void setHepatitisBSurfaceAntigen(String hepatitisBSurfaceAntigen) {
		this.hepatitisBSurfaceAntigen = hepatitisBSurfaceAntigen;
	}

	public String getHepatitisBSurfaceAntibody() {
		return this.hepatitisBSurfaceAntibody;
	}

	public void setHepatitisBSurfaceAntibody(String hepatitisBSurfaceAntibody) {
		this.hepatitisBSurfaceAntibody = hepatitisBSurfaceAntibody;
	}

	public String getHepatitisBEAntigen() {
		return this.hepatitisBEAntigen;
	}

	public void setHepatitisBEAntigen(String hepatitisBEAntigen) {
		this.hepatitisBEAntigen = hepatitisBEAntigen;
	}

	public String getHepatitisBEAntibody() {
		return this.hepatitisBEAntibody;
	}

	public void setHepatitisBEAntibody(String hepatitisBEAntibody) {
		this.hepatitisBEAntibody = hepatitisBEAntibody;
	}

	public String getHepatitisBCoreAntibody() {
		return this.hepatitisBCoreAntibody;
	}

	public void setHepatitisBCoreAntibody(String hepatitisBCoreAntibody) {
		this.hepatitisBCoreAntibody = hepatitisBCoreAntibody;
	}

	public String getSerologicalTest() {
		return this.serologicalTest;
	}

	public void setSerologicalTest(String serologicalTest) {
		this.serologicalTest = serologicalTest;
	}

	public String getHivAntibodyTest() {
		return this.hivAntibodyTest;
	}

	public void setHivAntibodyTest(String hivAntibodyTest) {
		this.hivAntibodyTest = hivAntibodyTest;
	}

	public String getBUltrasonic() {
		return this.BUltrasonic;
	}

	public void setBUltrasonic(String BUltrasonic) {
		this.BUltrasonic = BUltrasonic;
	}

	public String getOverallEvaluation() {
		return this.overallEvaluation;
	}

	public void setOverallEvaluation(String overallEvaluation) {
		this.overallEvaluation = overallEvaluation;
	}

	public String getOverallEvaluationExceptions() {
		return this.overallEvaluationExceptions;
	}

	public void setOverallEvaluationExceptions(
			String overallEvaluationExceptions) {
		this.overallEvaluationExceptions = overallEvaluationExceptions;
	}

	public String getHealthCareGuidance() {
		return this.healthCareGuidance;
	}

	public void setHealthCareGuidance(String healthCareGuidance) {
		this.healthCareGuidance = healthCareGuidance;
	}

	public String getHealthCareGuidanceOther() {
		return this.healthCareGuidanceOther;
	}

	public void setHealthCareGuidanceOther(String healthCareGuidanceOther) {
		this.healthCareGuidanceOther = healthCareGuidanceOther;
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

	public Date getNextFollowDate() {
		return this.nextFollowDate;
	}

	public void setNextFollowDate(Date nextFollowDate) {
		this.nextFollowDate = nextFollowDate;
	}

	public FamilyAccountInfo getFamilyAccountInfo() {
		return familyAccountInfo;
	}

	public void setFamilyAccountInfo(FamilyAccountInfo familyAccountInfo) {
		this.familyAccountInfo = familyAccountInfo;
	}

}