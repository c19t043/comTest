package com.java.asqtest.vo;

import com.java.publichealth.residentsfile.vo.KyUserInfo;

/**
 * AsqTestUserInfo entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class AsqTestUserInfo implements java.io.Serializable {

	private Long id;
	private Long b2cGoodsOrderId;
	private Long fdPackageId;
	private KyUserInfo userInfo;
	private String asqUserName;
	private String asqUserSex;
	private String asqUserBirthday;
	private Long gestationalWeeks;
	private Long gestationalDays;
	private String birthCondition;
	private String optionTime;
	private String babyLifeAge;
	private String setRightAge;
	private String doctorReading;
	private Integer currentMonthAge;

	// Constructors

	/** default constructor */
	public AsqTestUserInfo() {
	}
	/** default constructor */
	public AsqTestUserInfo(Long id) {
		this.id = id;
	}
	/** full constructor */
	public AsqTestUserInfo(Long userId, String asqUserName, String asqUserSex,
			String asqUserBirthday, Long gestationalWeeks,
			Long gestationalDays, String birthCondition, String optionTime) {
		this.asqUserName = asqUserName;
		this.asqUserSex = asqUserSex;
		this.asqUserBirthday = asqUserBirthday;
		this.gestationalWeeks = gestationalWeeks;
		this.gestationalDays = gestationalDays;
		this.birthCondition = birthCondition;
		this.optionTime = optionTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public KyUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(KyUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getAsqUserName() {
		return this.asqUserName;
	}

	public void setAsqUserName(String asqUserName) {
		this.asqUserName = asqUserName;
	}

	public String getAsqUserSex() {
		return this.asqUserSex;
	}

	public void setAsqUserSex(String asqUserSex) {
		this.asqUserSex = asqUserSex;
	}

	public String getAsqUserBirthday() {
		return this.asqUserBirthday;
	}

	public void setAsqUserBirthday(String asqUserBirthday) {
		this.asqUserBirthday = asqUserBirthday;
	}

	public Long getGestationalWeeks() {
		return this.gestationalWeeks;
	}

	public void setGestationalWeeks(Long gestationalWeeks) {
		this.gestationalWeeks = gestationalWeeks;
	}

	public Long getGestationalDays() {
		return this.gestationalDays;
	}

	public void setGestationalDays(Long gestationalDays) {
		this.gestationalDays = gestationalDays;
	}

	public String getBirthCondition() {
		return this.birthCondition;
	}

	public void setBirthCondition(String birthCondition) {
		this.birthCondition = birthCondition;
	}

	public String getOptionTime() {
		return this.optionTime;
	}

	public void setOptionTime(String optionTime) {
		this.optionTime = optionTime;
	}

	public String getBabyLifeAge() {
		return babyLifeAge;
	}

	public void setBabyLifeAge(String babyLifeAge) {
		this.babyLifeAge = babyLifeAge;
	}

	public String getSetRightAge() {
		return setRightAge;
	}

	public void setSetRightAge(String setRightAge) {
		this.setRightAge = setRightAge;
	}

	public Integer getCurrentMonthAge() {
		return currentMonthAge;
	}

	public void setCurrentMonthAge(Integer currentMonthAge) {
		this.currentMonthAge = currentMonthAge;
	}

	public String getDoctorReading() {
		return doctorReading;
	}

	public void setDoctorReading(String doctorReading) {
		this.doctorReading = doctorReading;
	}

	public Long getB2cGoodsOrderId() {
		return b2cGoodsOrderId;
	}

	public void setB2cGoodsOrderId(Long b2cGoodsOrderId) {
		this.b2cGoodsOrderId = b2cGoodsOrderId;
	}

	public Long getFdPackageId() {
		return fdPackageId;
	}

	public void setFdPackageId(Long fdPackageId) {
		this.fdPackageId = fdPackageId;
	}

}