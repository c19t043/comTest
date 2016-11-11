package com.kybaby.domain;

/**
 * GrowthRecord entity. @author MyEclipse Persistence Tools
 */

public class GrowthRecord implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private String babyName;
	private String recordDate;
	private String sleepHour;
	private String everyBreastfeeding;
	private Long breastfeedingTimes;
	private Long assistFoodsTimes;
	private Long defecateTimes;
	private String exerciseTimes;
	private String uploadImage;

	// Constructors

	/** default constructor */
	public GrowthRecord() {
	}

	/** full constructor */
	public GrowthRecord(Long userId, String babyName, String recordDate,
			String sleepHour, String everyBreastfeeding,
			Long breastfeedingTimes, Long assistFoodsTimes, Long defecateTimes,
			String exerciseTimes, String uploadImage) {
		this.userId = userId;
		this.babyName = babyName;
		this.recordDate = recordDate;
		this.sleepHour = sleepHour;
		this.everyBreastfeeding = everyBreastfeeding;
		this.breastfeedingTimes = breastfeedingTimes;
		this.assistFoodsTimes = assistFoodsTimes;
		this.defecateTimes = defecateTimes;
		this.exerciseTimes = exerciseTimes;
		this.uploadImage = uploadImage;
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

	public String getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getSleepHour() {
		return this.sleepHour;
	}

	public void setSleepHour(String sleepHour) {
		this.sleepHour = sleepHour;
	}

	public String getEveryBreastfeeding() {
		return this.everyBreastfeeding;
	}

	public void setEveryBreastfeeding(String everyBreastfeeding) {
		this.everyBreastfeeding = everyBreastfeeding;
	}

	public Long getBreastfeedingTimes() {
		return this.breastfeedingTimes;
	}

	public void setBreastfeedingTimes(Long breastfeedingTimes) {
		this.breastfeedingTimes = breastfeedingTimes;
	}

	public Long getAssistFoodsTimes() {
		return this.assistFoodsTimes;
	}

	public void setAssistFoodsTimes(Long assistFoodsTimes) {
		this.assistFoodsTimes = assistFoodsTimes;
	}

	public Long getDefecateTimes() {
		return this.defecateTimes;
	}

	public void setDefecateTimes(Long defecateTimes) {
		this.defecateTimes = defecateTimes;
	}

	public String getExerciseTimes() {
		return this.exerciseTimes;
	}

	public void setExerciseTimes(String exerciseTimes) {
		this.exerciseTimes = exerciseTimes;
	}

	public String getUploadImage() {
		return this.uploadImage;
	}

	public void setUploadImage(String uploadImage) {
		this.uploadImage = uploadImage;
	}

}