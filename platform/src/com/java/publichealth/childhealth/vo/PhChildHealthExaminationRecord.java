package com.java.publichealth.childhealth.vo;

import java.sql.Timestamp;
import java.util.Date;

import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.core.BaseDomain;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

/**
 * 儿童体格检查记录实体信息
 * @author lihao
 *
 */

public class PhChildHealthExaminationRecord extends BaseDomain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	//----------
	private PhPeopleBasicInfo peopleBasicInfo;
	private KyUserInfo kyUserInfo;
	private FamilyAccountInfo familyAccountInfo;
	private ConsultBabyInfo consultBabyInfo;
	//----------
	private String weight;
	private String height;
	private String headSize;
	private String physicalDevelopEvaluation;
	private String face;
	private String skin;
	private String brine;
	private String bregmaMultiplier;
	private String bregmaMultiplicand;
	private String neckMass;
	private String eyeAppearance;
	private String earAppearance;
	private String vision;   //视觉
	private String hearing;
	private String teethNumber;
	private String cariesNumber;
	private String oralCavity;
	private String heartLung;
	private String abdomen;
	private String region;
	private String armsAndLegs;
	private String gait;
	private String signsRickets;//可疑佝偻病体征
	private String signsSymptom;//可疑佝偻病症状
	private String anusExternalGenital;
	private String hemoglobinValue;
	private String outdoorActivities;
	private String takeVitaminD;
	private String developmentalAssessment;
	private String twoCasesFollow;
	private String twoVisitIllLung;// '两次随访患病肺炎',
	private String twoVisitIllDiarrhea ; // '两次随访患病腹泻',
	private String twoVisitIllTrauma ; // '两次随访患病外伤',
	private String twoVisitIllOther  ;// '两次随访其他',
	private String others;
	private String isReferral;
	private String ireferralReason;
	private String ireferralOrg;
	private String advice;
	private Date nextFollowDate;
	private String monthAge;

	
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

	public String getTwoVisitIllLung() {
		return twoVisitIllLung;
	}

	public void setTwoVisitIllLung(String twoVisitIllLung) {
		this.twoVisitIllLung = twoVisitIllLung;
	}

	public String getTwoVisitIllDiarrhea() {
		return twoVisitIllDiarrhea;
	}

	public void setTwoVisitIllDiarrhea(String twoVisitIllDiarrhea) {
		this.twoVisitIllDiarrhea = twoVisitIllDiarrhea;
	}

	public String getTwoVisitIllTrauma() {
		return twoVisitIllTrauma;
	}

	public void setTwoVisitIllTrauma(String twoVisitIllTrauma) {
		this.twoVisitIllTrauma = twoVisitIllTrauma;
	}

	public String getTwoVisitIllOther() {
		return twoVisitIllOther;
	}

	public void setTwoVisitIllOther(String twoVisitIllOther) {
		this.twoVisitIllOther = twoVisitIllOther;
	}

	public String getBregmaMultiplier() {
		return bregmaMultiplier;
	}

	public void setBregmaMultiplier(String bregmaMultiplier) {
		this.bregmaMultiplier = bregmaMultiplier;
	}

	public String getBregmaMultiplicand() {
		return bregmaMultiplicand;
	}

	public void setBregmaMultiplicand(String bregmaMultiplicand) {
		this.bregmaMultiplicand = bregmaMultiplicand;
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

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getHeadSize() {
		return this.headSize;
	}

	public void setHeadSize(String headSize) {
		this.headSize = headSize;
	}

	public String getPhysicalDevelopEvaluation() {
		return this.physicalDevelopEvaluation;
	}

	public void setPhysicalDevelopEvaluation(String physicalDevelopEvaluation) {
		this.physicalDevelopEvaluation = physicalDevelopEvaluation;
	}

	public String getFace() {
		return this.face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getSkin() {
		return this.skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getBrine() {
		return this.brine;
	}

	public void setBrine(String brine) {
		this.brine = brine;
	}

	public String getNeckMass() {
		return this.neckMass;
	}

	public void setNeckMass(String neckMass) {
		this.neckMass = neckMass;
	}

	public String getEyeAppearance() {
		return this.eyeAppearance;
	}

	public void setEyeAppearance(String eyeAppearance) {
		this.eyeAppearance = eyeAppearance;
	}

	public String getEarAppearance() {
		return this.earAppearance;
	}

	public void setEarAppearance(String earAppearance) {
		this.earAppearance = earAppearance;
	}

	public String getVision() {
		return this.vision;
	}

	public void setVision(String vision) {
		this.vision = vision;
	}

	public String getHearing() {
		return this.hearing;
	}

	public void setHearing(String hearing) {
		this.hearing = hearing;
	}

	public String getTeethNumber() {
		return this.teethNumber;
	}

	public void setTeethNumber(String teethNumber) {
		this.teethNumber = teethNumber;
	}

	public String getCariesNumber() {
		return this.cariesNumber;
	}

	public void setCariesNumber(String cariesNumber) {
		this.cariesNumber = cariesNumber;
	}

	public String getOralCavity() {
		return this.oralCavity;
	}

	public void setOralCavity(String oralCavity) {
		this.oralCavity = oralCavity;
	}

	public String getHeartLung() {
		return this.heartLung;
	}

	public void setHeartLung(String heartLung) {
		this.heartLung = heartLung;
	}

	public String getAbdomen() {
		return this.abdomen;
	}

	public void setAbdomen(String abdomen) {
		this.abdomen = abdomen;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getArmsAndLegs() {
		return this.armsAndLegs;
	}

	public void setArmsAndLegs(String armsAndLegs) {
		this.armsAndLegs = armsAndLegs;
	}

	public String getGait() {
		return this.gait;
	}

	public void setGait(String gait) {
		this.gait = gait;
	}

	public String getSignsRickets() {
		return this.signsRickets;
	}

	public void setSignsRickets(String signsRickets) {
		this.signsRickets = signsRickets;
	}

	public String getAnusExternalGenital() {
		return this.anusExternalGenital;
	}

	public void setAnusExternalGenital(String anusExternalGenital) {
		this.anusExternalGenital = anusExternalGenital;
	}

	public String getHemoglobinValue() {
		return this.hemoglobinValue;
	}

	public void setHemoglobinValue(String hemoglobinValue) {
		this.hemoglobinValue = hemoglobinValue;
	}

	public String getOutdoorActivities() {
		return this.outdoorActivities;
	}

	public void setOutdoorActivities(String outdoorActivities) {
		this.outdoorActivities = outdoorActivities;
	}

	public String getTakeVitaminD() {
		return this.takeVitaminD;
	}

	public void setTakeVitaminD(String takeVitaminD) {
		this.takeVitaminD = takeVitaminD;
	}

	public String getDevelopmentalAssessment() {
		return this.developmentalAssessment;
	}

	public void setDevelopmentalAssessment(String developmentalAssessment) {
		this.developmentalAssessment = developmentalAssessment;
	}

	public String getTwoCasesFollow() {
		return this.twoCasesFollow;
	}

	public void setTwoCasesFollow(String twoCasesFollow) {
		this.twoCasesFollow = twoCasesFollow;
	}

	public String getOthers() {
		return this.others;
	}

	public void setOthers(String others) {
		this.others = others;
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

	public String getSignsSymptom() {
		return signsSymptom;
	}

	public void setSignsSymptom(String signsSymptom) {
		this.signsSymptom = signsSymptom;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public Date getNextFollowDate() {
		return nextFollowDate;
	}

	public void setNextFollowDate(Date nextFollowDate) {
		this.nextFollowDate = nextFollowDate;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getMonthAge() {
		return this.monthAge;
	}

	public void setMonthAge(String monthAge) {
		this.monthAge = monthAge;
	}

	@Override
	public String toString() {
		return "PhChildHealthExaminationRecord [id=" + id
				+ ", peopleBasicInfoId="  + ", weight="
				+ weight + ", height=" + height + ", headSize=" + headSize
				+ ", physicalDevelopEvaluation=" + physicalDevelopEvaluation
				+ ", face=" + face + ", skin=" + skin + ", brine=" + brine
				+ ", neckMass=" + neckMass + ", eyeAppearance=" + eyeAppearance
				+ ", earAppearance=" + earAppearance + ", vision=" + vision
				+ ", hearing=" + hearing + ", teethNumber=" + teethNumber
				+ ", cariesNumber=" + cariesNumber + ", oralCavity="
				+ oralCavity + ", heartLung=" + heartLung + ", abdomen="
				+ abdomen + ", region=" + region + ", armsAndLegs="
				+ armsAndLegs + ", gait=" + gait + ", signsRickets="
				+ signsRickets + ", anusExternalGenital=" + anusExternalGenital
				+ ", hemoglobinValue=" + hemoglobinValue
				+ ", outdoorActivities=" + outdoorActivities
				+ ", takeVitaminD=" + takeVitaminD
				+ ", developmentalAssessment=" + developmentalAssessment
				+ ", twoCasesFollow=" + twoCasesFollow + ", others=" + others
				+ ", isReferral=" + isReferral + ", ireferralReason="
				+ ireferralReason + ", ireferralOrg=" + ireferralOrg
				+ ", advice=" + advice + ", nextFollowDate=" + nextFollowDate
				+ ", createPerson=" + createPerson + ", createTime="
				+ createTime + ", modifyPerson=" + modifyPerson
				+ ", modifyTime=" + modifyTime + ", monthAge=" + monthAge + "]";
	}
	
}