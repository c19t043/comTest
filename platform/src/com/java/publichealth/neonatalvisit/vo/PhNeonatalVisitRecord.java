package com.java.publichealth.neonatalvisit.vo;

import java.util.Date;

import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.core.BaseDomain;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

/**
 * 新生儿访视记录实体信息
 * @author lihao
 *
 */
 

public class PhNeonatalVisitRecord extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	//--------------------------
	private PhPeopleBasicInfo peopleBasicInfo;
	private KyUserInfo kyUserInfo;
	
	private ConsultBabyInfo consultBabyInfo;
	private FamilyAccountInfo familyAccountInfo;
	//--------------------------
	private String name;
	private String number;
	private String sex;
	private Date birthday;
	private String idCardNum;
	private String familyAddress;
	private String dadName;
	private String dadProfession;
	private String dadPhone;
	private String dadBirthday;
	private String mumName;
	private String mumProfession;
	private String mumPhone;
	private String mumBirthday;
	private String gestationWeeks;
	private String mumGestationIll;
	private String mumGestationIllOther;
	private String midwiferyOrganizationName;
	private String birthCondition;
	private String birthConditionOther;
	private String newbothAsphyxia;
	private String apgar;
	private String isAbnormal;
	private String abnormalRemark;
	private String hearingScreening;
	private String illScreening;
	private String illScreeningOther;
	private String bornWeight;
	private String nowWeight;
	private String bornHeight;
	private String feedingPatterns;
	private String feedingAmount;
	private String feedingTimes;
	private String vomit;
	private String stool;
	private String stoolTimes;
	private String temperature;
	private String pulseRate;
	private String breathingRate;
	private String face;
	private String faceOther;
	private String locationJaundice;
	private String bregmaMultiplier;
	private String bregmaMultiplicand;
	private String bregmaSelect;
	private String bregmaOther;
	private String eyeAppearance;
	private String eyeAppearanceException;
	private String limbActivity;
	private String limbActivityException;
	private String earAppearance;
	private String earAppearanceException;
	private String neckMass;
	private String neckMassRemark;
	private String nose;
	private String noseException;
	private String skin;
	private String skinOther;
	private String mouse;
	private String mouseException;
	private String anus;
	private String anusException;
	private String heartLungAuscultation;
	private String heartLungAuscultationException;
	private String aedea;
	private String aedeaException;
	private String abdominalTouch;
	private String abdominalTouchException;
	private String spine;
	private String spineException;
	private String umbilicalCord;
	private String umbilicalCordOther;
	private String isReferral;
	private String ireferralReason;
	private String ireferralOrg;
	private String advice;
	private Date nextFollowDate;
	private String doctorName;
	private String nextFollowAddress;

	
	
	public ConsultBabyInfo getConsultBabyInfo() {
		return consultBabyInfo;
	}

	public void setConsultBabyInfo(ConsultBabyInfo consultBabyInfo) {
		this.consultBabyInfo = consultBabyInfo;
	}

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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdCardNum() {
		return this.idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getDadName() {
		return this.dadName;
	}

	public void setDadName(String dadName) {
		this.dadName = dadName;
	}

	public String getDadProfession() {
		return this.dadProfession;
	}

	public void setDadProfession(String dadProfession) {
		this.dadProfession = dadProfession;
	}

	public String getDadPhone() {
		return this.dadPhone;
	}

	public void setDadPhone(String dadPhone) {
		this.dadPhone = dadPhone;
	}

	public String getDadBirthday() {
		return this.dadBirthday;
	}

	public void setDadBirthday(String dadBirthday) {
		this.dadBirthday = dadBirthday;
	}

	public String getMumName() {
		return this.mumName;
	}

	public void setMumName(String mumName) {
		this.mumName = mumName;
	}

	public String getMumProfession() {
		return this.mumProfession;
	}

	public void setMumProfession(String mumProfession) {
		this.mumProfession = mumProfession;
	}

	public String getMumPhone() {
		return this.mumPhone;
	}

	public void setMumPhone(String mumPhone) {
		this.mumPhone = mumPhone;
	}

	public String getMumBirthday() {
		return this.mumBirthday;
	}

	public void setMumBirthday(String mumBirthday) {
		this.mumBirthday = mumBirthday;
	}

	public String getGestationWeeks() {
		return this.gestationWeeks;
	}

	public void setGestationWeeks(String gestationWeeks) {
		this.gestationWeeks = gestationWeeks;
	}

	public String getMumGestationIll() {
		return this.mumGestationIll;
	}

	public void setMumGestationIll(String mumGestationIll) {
		this.mumGestationIll = mumGestationIll;
	}

	public String getMumGestationIllOther() {
		return this.mumGestationIllOther;
	}

	public void setMumGestationIllOther(String mumGestationIllOther) {
		this.mumGestationIllOther = mumGestationIllOther;
	}

	public String getMidwiferyOrganizationName() {
		return this.midwiferyOrganizationName;
	}

	public void setMidwiferyOrganizationName(String midwiferyOrganizationName) {
		this.midwiferyOrganizationName = midwiferyOrganizationName;
	}

	public String getBirthCondition() {
		return this.birthCondition;
	}

	public void setBirthCondition(String birthCondition) {
		this.birthCondition = birthCondition;
	}

	public String getBirthConditionOther() {
		return this.birthConditionOther;
	}

	public void setBirthConditionOther(String birthConditionOther) {
		this.birthConditionOther = birthConditionOther;
	}

	public String getNewbothAsphyxia() {
		return this.newbothAsphyxia;
	}

	public void setNewbothAsphyxia(String newbothAsphyxia) {
		this.newbothAsphyxia = newbothAsphyxia;
	}

	public String getApgar() {
		return this.apgar;
	}

	public void setApgar(String apgar) {
		this.apgar = apgar;
	}

	public String getIsAbnormal() {
		return this.isAbnormal;
	}

	public void setIsAbnormal(String isAbnormal) {
		this.isAbnormal = isAbnormal;
	}

	public String getAbnormalRemark() {
		return this.abnormalRemark;
	}

	public void setAbnormalRemark(String abnormalRemark) {
		this.abnormalRemark = abnormalRemark;
	}

	public String getHearingScreening() {
		return this.hearingScreening;
	}

	public void setHearingScreening(String hearingScreening) {
		this.hearingScreening = hearingScreening;
	}

	public String getIllScreening() {
		return this.illScreening;
	}

	public void setIllScreening(String illScreening) {
		this.illScreening = illScreening;
	}

	public String getIllScreeningOther() {
		return this.illScreeningOther;
	}

	public void setIllScreeningOther(String illScreeningOther) {
		this.illScreeningOther = illScreeningOther;
	}

	public String getBornWeight() {
		return this.bornWeight;
	}

	public void setBornWeight(String bornWeight) {
		this.bornWeight = bornWeight;
	}

	public String getNowWeight() {
		return this.nowWeight;
	}

	public void setNowWeight(String nowWeight) {
		this.nowWeight = nowWeight;
	}

	public String getBornHeight() {
		return this.bornHeight;
	}

	public void setBornHeight(String bornHeight) {
		this.bornHeight = bornHeight;
	}

	public String getFeedingPatterns() {
		return this.feedingPatterns;
	}

	public void setFeedingPatterns(String feedingPatterns) {
		this.feedingPatterns = feedingPatterns;
	}

	public String getFeedingAmount() {
		return this.feedingAmount;
	}

	public void setFeedingAmount(String feedingAmount) {
		this.feedingAmount = feedingAmount;
	}

	public String getFeedingTimes() {
		return this.feedingTimes;
	}

	public void setFeedingTimes(String feedingTimes) {
		this.feedingTimes = feedingTimes;
	}

	public String getVomit() {
		return this.vomit;
	}

	public void setVomit(String vomit) {
		this.vomit = vomit;
	}

	public String getStool() {
		return this.stool;
	}

	public void setStool(String stool) {
		this.stool = stool;
	}

	public String getStoolTimes() {
		return this.stoolTimes;
	}

	public void setStoolTimes(String stoolTimes) {
		this.stoolTimes = stoolTimes;
	}

	public String getTemperature() {
		return this.temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getPulseRate() {
		return this.pulseRate;
	}

	public void setPulseRate(String pulseRate) {
		this.pulseRate = pulseRate;
	}

	public String getBreathingRate() {
		return this.breathingRate;
	}

	public void setBreathingRate(String breathingRate) {
		this.breathingRate = breathingRate;
	}

	public String getFace() {
		return this.face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getFaceOther() {
		return this.faceOther;
	}

	public void setFaceOther(String faceOther) {
		this.faceOther = faceOther;
	}

	public String getLocationJaundice() {
		return this.locationJaundice;
	}

	public void setLocationJaundice(String locationJaundice) {
		this.locationJaundice = locationJaundice;
	}

	public String getBregmaMultiplier() {
		return this.bregmaMultiplier;
	}

	public void setBregmaMultiplier(String bregmaMultiplier) {
		this.bregmaMultiplier = bregmaMultiplier;
	}

	public String getBregmaMultiplicand() {
		return this.bregmaMultiplicand;
	}

	public void setBregmaMultiplicand(String bregmaMultiplicand) {
		this.bregmaMultiplicand = bregmaMultiplicand;
	}

	public String getBregmaSelect() {
		return this.bregmaSelect;
	}

	public void setBregmaSelect(String bregmaSelect) {
		this.bregmaSelect = bregmaSelect;
	}

	public String getBregmaOther() {
		return this.bregmaOther;
	}

	public void setBregmaOther(String bregmaOther) {
		this.bregmaOther = bregmaOther;
	}

	public String getEyeAppearance() {
		return this.eyeAppearance;
	}

	public void setEyeAppearance(String eyeAppearance) {
		this.eyeAppearance = eyeAppearance;
	}

	public String getEyeAppearanceException() {
		return this.eyeAppearanceException;
	}

	public void setEyeAppearanceException(String eyeAppearanceException) {
		this.eyeAppearanceException = eyeAppearanceException;
	}

	public String getLimbActivity() {
		return this.limbActivity;
	}

	public void setLimbActivity(String limbActivity) {
		this.limbActivity = limbActivity;
	}

	public String getLimbActivityException() {
		return this.limbActivityException;
	}

	public void setLimbActivityException(String limbActivityException) {
		this.limbActivityException = limbActivityException;
	}

	public String getEarAppearance() {
		return this.earAppearance;
	}

	public void setEarAppearance(String earAppearance) {
		this.earAppearance = earAppearance;
	}

	public String getEarAppearanceException() {
		return this.earAppearanceException;
	}

	public void setEarAppearanceException(String earAppearanceException) {
		this.earAppearanceException = earAppearanceException;
	}

	public String getNeckMass() {
		return this.neckMass;
	}

	public void setNeckMass(String neckMass) {
		this.neckMass = neckMass;
	}

	public String getNeckMassRemark() {
		return this.neckMassRemark;
	}

	public void setNeckMassRemark(String neckMassRemark) {
		this.neckMassRemark = neckMassRemark;
	}

	public String getNose() {
		return this.nose;
	}

	public void setNose(String nose) {
		this.nose = nose;
	}

	public String getNoseException() {
		return this.noseException;
	}

	public void setNoseException(String noseException) {
		this.noseException = noseException;
	}

	public String getSkin() {
		return this.skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getSkinOther() {
		return this.skinOther;
	}

	public void setSkinOther(String skinOther) {
		this.skinOther = skinOther;
	}

	public String getMouse() {
		return this.mouse;
	}

	public void setMouse(String mouse) {
		this.mouse = mouse;
	}

	public String getMouseException() {
		return this.mouseException;
	}

	public void setMouseException(String mouseException) {
		this.mouseException = mouseException;
	}

	public String getAnus() {
		return this.anus;
	}

	public void setAnus(String anus) {
		this.anus = anus;
	}

	public String getAnusException() {
		return this.anusException;
	}

	public void setAnusException(String anusException) {
		this.anusException = anusException;
	}

	public String getHeartLungAuscultation() {
		return this.heartLungAuscultation;
	}

	public void setHeartLungAuscultation(String heartLungAuscultation) {
		this.heartLungAuscultation = heartLungAuscultation;
	}

	public String getHeartLungAuscultationException() {
		return this.heartLungAuscultationException;
	}

	public void setHeartLungAuscultationException(
			String heartLungAuscultationException) {
		this.heartLungAuscultationException = heartLungAuscultationException;
	}

	public String getAedea() {
		return this.aedea;
	}

	public void setAedea(String aedea) {
		this.aedea = aedea;
	}

	public String getAedeaException() {
		return this.aedeaException;
	}

	public void setAedeaException(String aedeaException) {
		this.aedeaException = aedeaException;
	}

	public String getAbdominalTouch() {
		return this.abdominalTouch;
	}

	public void setAbdominalTouch(String abdominalTouch) {
		this.abdominalTouch = abdominalTouch;
	}

	public String getAbdominalTouchException() {
		return this.abdominalTouchException;
	}

	public void setAbdominalTouchException(String abdominalTouchException) {
		this.abdominalTouchException = abdominalTouchException;
	}

	public String getSpine() {
		return this.spine;
	}

	public void setSpine(String spine) {
		this.spine = spine;
	}

	public String getSpineException() {
		return this.spineException;
	}

	public void setSpineException(String spineException) {
		this.spineException = spineException;
	}

	public String getUmbilicalCord() {
		return this.umbilicalCord;
	}

	public void setUmbilicalCord(String umbilicalCord) {
		this.umbilicalCord = umbilicalCord;
	}

	public String getUmbilicalCordOther() {
		return this.umbilicalCordOther;
	}

	public void setUmbilicalCordOther(String umbilicalCordOther) {
		this.umbilicalCordOther = umbilicalCordOther;
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

	public String getAdvice() {
		return this.advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public Date getNextFollowDate() {
		return this.nextFollowDate;
	}

	public void setNextFollowDate(Date nextFollowDate) {
		this.nextFollowDate = nextFollowDate;
	}

	public String getDoctorName() {
		return this.doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	public String getFamilyAddress() {
		return familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}
	
	public String getNextFollowAddress() {
		return nextFollowAddress;
	}

	public void setNextFollowAddress(String nextFollowAddress) {
		this.nextFollowAddress = nextFollowAddress;
	}

	@Override
	public String toString() {
		return "PhNeonatalVisitRecord [id=" + id + ", peopleBasicInfo="
				+ peopleBasicInfo + ", name=" + name + ", number=" + number
				+ ", sex=" + sex + ", birthday=" + birthday + ", idCardNum="
				+ idCardNum + ", dadName=" + dadName + ", dadProfession="
				+ dadProfession + ", dadPhone=" + dadPhone + ", dadBirthday="
				+ dadBirthday + ", mumName=" + mumName + ", mumProfession="
				+ mumProfession + ", mumPhone=" + mumPhone + ", mumBirthday="
				+ mumBirthday + ", gestationWeeks=" + gestationWeeks
				+ ", mumGestationIll=" + mumGestationIll
				+ ", mumGestationIllOther=" + mumGestationIllOther
				+ ", midwiferyOrganizationName=" + midwiferyOrganizationName
				+ ", birthCondition=" + birthCondition
				+ ", birthConditionOther=" + birthConditionOther
				+ ", newbothAsphyxia=" + newbothAsphyxia + ", apgar=" + apgar
				+ ", isAbnormal=" + isAbnormal + ", abnormalRemark="
				+ abnormalRemark + ", hearingScreening=" + hearingScreening
				+ ", illScreening=" + illScreening + ", illScreeningOther="
				+ illScreeningOther + ", bornWeight=" + bornWeight
				+ ", nowWeight=" + nowWeight + ", bornHeight=" + bornHeight
				+ ", feedingPatterns=" + feedingPatterns + ", feedingAmount="
				+ feedingAmount + ", feedingTimes=" + feedingTimes + ", vomit="
				+ vomit + ", stool=" + stool + ", stoolTimes=" + stoolTimes
				+ ", temperature=" + temperature + ", pulseRate=" + pulseRate
				+ ", breathingRate=" + breathingRate + ", face=" + face
				+ ", faceOther=" + faceOther + ", locationJaundice="
				+ locationJaundice + ", bregmaMultiplier=" + bregmaMultiplier
				+ ", bregmaMultiplicand=" + bregmaMultiplicand
				+ ", bregmaSelect=" + bregmaSelect + ", bregmaOther="
				+ bregmaOther + ", eyeAppearance=" + eyeAppearance
				+ ", eyeAppearanceException=" + eyeAppearanceException
				+ ", limbActivity=" + limbActivity + ", limbActivityException="
				+ limbActivityException + ", earAppearance=" + earAppearance
				+ ", earAppearanceException=" + earAppearanceException
				+ ", neckMass=" + neckMass + ", neckMassRemark="
				+ neckMassRemark + ", nose=" + nose + ", noseException="
				+ noseException + ", skin=" + skin + ", skinOther=" + skinOther
				+ ", mouse=" + mouse + ", mouseException=" + mouseException
				+ ", anus=" + anus + ", anusException=" + anusException
				+ ", heartLungAuscultation=" + heartLungAuscultation
				+ ", heartLungAuscultationException="
				+ heartLungAuscultationException + ", aedea=" + aedea
				+ ", aedeaException=" + aedeaException + ", abdominalTouch="
				+ abdominalTouch + ", abdominalTouchException="
				+ abdominalTouchException + ", spine=" + spine
				+ ", spineException=" + spineException + ", umbilicalCord="
				+ umbilicalCord + ", umbilicalCordOther=" + umbilicalCordOther
				+ ", isReferral=" + isReferral + ", ireferralReason="
				+ ireferralReason + ", ireferralOrg=" + ireferralOrg
				+ ", advice=" + advice + ", nextFollowDate=" + nextFollowDate
				+ ", doctorName=" + doctorName + "]";
	}
}