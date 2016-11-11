package com.kybaby.domain;

/**
 * HealthRecord entity. @author MyEclipse Persistence Tools
 */

public class HealthRecord implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private String babyName;
	private String sex;
	private String birthday;
	private String childCareDate;
	private Long productId;
	private Long itemId;
	private Long itemResultId;
	private Long resultPlanId;
	private Long planPathId;
	private String resultValue;
	private String executePathResult;
	private Long doctorId;
	private String orderNum;

	// Constructors

	/** default constructor */
	public HealthRecord() {
	}

	/** full constructor */
	public HealthRecord(Long userId, String babyName, String sex,
			String birthday, String childCareDate, Long productId, Long itemId,
			Long itemResultId, Long resultPlanId, Long planPathId,
			String resultValue, String executePathResult, Long doctorId,
			String orderNum) {
		this.userId = userId;
		this.babyName = babyName;
		this.sex = sex;
		this.birthday = birthday;
		this.childCareDate = childCareDate;
		this.productId = productId;
		this.itemId = itemId;
		this.itemResultId = itemResultId;
		this.resultPlanId = resultPlanId;
		this.planPathId = planPathId;
		this.resultValue = resultValue;
		this.executePathResult = executePathResult;
		this.doctorId = doctorId;
		this.orderNum = orderNum;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getBabyName() {
		return this.babyName;
	}

	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getChildCareDate() {
		return this.childCareDate;
	}

	public void setChildCareDate(String childCareDate) {
		this.childCareDate = childCareDate;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getItemId() {
		return this.itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getItemResultId() {
		return this.itemResultId;
	}

	public void setItemResultId(Long itemResultId) {
		this.itemResultId = itemResultId;
	}

	public Long getResultPlanId() {
		return this.resultPlanId;
	}

	public void setResultPlanId(Long resultPlanId) {
		this.resultPlanId = resultPlanId;
	}

	public Long getPlanPathId() {
		return this.planPathId;
	}

	public void setPlanPathId(Long planPathId) {
		this.planPathId = planPathId;
	}

	public String getResultValue() {
		return this.resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}

	public String getExecutePathResult() {
		return this.executePathResult;
	}

	public void setExecutePathResult(String executePathResult) {
		this.executePathResult = executePathResult;
	}

	public Long getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

}