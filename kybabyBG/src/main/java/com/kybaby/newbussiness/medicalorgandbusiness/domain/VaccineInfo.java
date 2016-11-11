package com.kybaby.newbussiness.medicalorgandbusiness.domain;

/**
 * VaccineInfo entity. @author MyEclipse Persistence Tools
 */

public class VaccineInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String vaccineName;
	private String inoculationMonth;
	private String vaccineType;
	private String vaccinePrice;
	private String diseasePrevention;
	private String diseaseHazard;
	private String mattersNeedingAttentionBefore;
	private String mattersNeedingAttentionAfter;
	private String vaccinationProgram;
	private String inoculumNumber;

	// Constructors

	/** default constructor */
	public VaccineInfo() {
	}

	/** full constructor */
	public VaccineInfo(String vaccineName, String inoculationMonth,
			String vaccineType, String vaccinePrice, String diseasePrevention,
			String diseaseHazard, String mattersNeedingAttentionBefore,
			String mattersNeedingAttentionAfter, String vaccinationProgram,
			String inoculumNumber) {
		this.vaccineName = vaccineName;
		this.inoculationMonth = inoculationMonth;
		this.vaccineType = vaccineType;
		this.vaccinePrice = vaccinePrice;
		this.diseasePrevention = diseasePrevention;
		this.diseaseHazard = diseaseHazard;
		this.mattersNeedingAttentionBefore = mattersNeedingAttentionBefore;
		this.mattersNeedingAttentionAfter = mattersNeedingAttentionAfter;
		this.vaccinationProgram = vaccinationProgram;
		this.inoculumNumber = inoculumNumber;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVaccineName() {
		return this.vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getInoculationMonth() {
		return this.inoculationMonth;
	}

	public void setInoculationMonth(String inoculationMonth) {
		this.inoculationMonth = inoculationMonth;
	}

	public String getVaccineType() {
		return this.vaccineType;
	}

	public void setVaccineType(String vaccineType) {
		this.vaccineType = vaccineType;
	}

	public String getVaccinePrice() {
		return this.vaccinePrice;
	}

	public void setVaccinePrice(String vaccinePrice) {
		this.vaccinePrice = vaccinePrice;
	}

	public String getDiseasePrevention() {
		return this.diseasePrevention;
	}

	public void setDiseasePrevention(String diseasePrevention) {
		this.diseasePrevention = diseasePrevention;
	}

	public String getDiseaseHazard() {
		return this.diseaseHazard;
	}

	public void setDiseaseHazard(String diseaseHazard) {
		this.diseaseHazard = diseaseHazard;
	}

	public String getMattersNeedingAttentionBefore() {
		return this.mattersNeedingAttentionBefore;
	}

	public void setMattersNeedingAttentionBefore(
			String mattersNeedingAttentionBefore) {
		this.mattersNeedingAttentionBefore = mattersNeedingAttentionBefore;
	}

	public String getMattersNeedingAttentionAfter() {
		return this.mattersNeedingAttentionAfter;
	}

	public void setMattersNeedingAttentionAfter(
			String mattersNeedingAttentionAfter) {
		this.mattersNeedingAttentionAfter = mattersNeedingAttentionAfter;
	}

	public String getVaccinationProgram() {
		return this.vaccinationProgram;
	}

	public void setVaccinationProgram(String vaccinationProgram) {
		this.vaccinationProgram = vaccinationProgram;
	}

	public String getInoculumNumber() {
		return this.inoculumNumber;
	}

	public void setInoculumNumber(String inoculumNumber) {
		this.inoculumNumber = inoculumNumber;
	}

}