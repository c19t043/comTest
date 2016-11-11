package com.kybaby.newbussiness.medicalorgandbusiness.domain;

/**
 * DrugInfo entity. @author MyEclipse Persistence Tools
 */

public class DrugInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long commonDiseaseId;
	private Long drugstoreInfoId;
	private DrugClassification drugClassification;
	private String commonName;
	private String goodsName;
	private String drugImg;
	private String englishName;
	private String factory;
	private String element;
	private String indication;
	private String standard;
	private String dosage;
	private String taboo;
	private String notice;
	private String untowardEffect;
	private String drugInteraction;
	private String fda;
	private String isSale;
	private String remark;
	private String drugType;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCommonDiseaseId() {
		return this.commonDiseaseId;
	}

	public void setCommonDiseaseId(Long commonDiseaseId) {
		this.commonDiseaseId = commonDiseaseId;
	}

	public Long getDrugstoreInfoId() {
		return this.drugstoreInfoId;
	}

	public void setDrugstoreInfoId(Long drugstoreInfoId) {
		this.drugstoreInfoId = drugstoreInfoId;
	}

	public String getCommonName() {
		return this.commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getDrugImg() {
		return this.drugImg;
	}

	public void setDrugImg(String drugImg) {
		this.drugImg = drugImg;
	}

	public String getEnglishName() {
		return this.englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getFactory() {
		return this.factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getElement() {
		return this.element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getIndication() {
		return this.indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}

	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getDosage() {
		return this.dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getTaboo() {
		return this.taboo;
	}

	public void setTaboo(String taboo) {
		this.taboo = taboo;
	}

	public String getNotice() {
		return this.notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getUntowardEffect() {
		return this.untowardEffect;
	}

	public void setUntowardEffect(String untowardEffect) {
		this.untowardEffect = untowardEffect;
	}

	public String getDrugInteraction() {
		return this.drugInteraction;
	}

	public void setDrugInteraction(String drugInteraction) {
		this.drugInteraction = drugInteraction;
	}

	public String getFda() {
		return this.fda;
	}

	public void setFda(String fda) {
		this.fda = fda;
	}

	public String getIsSale() {
		return this.isSale;
	}

	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public DrugClassification getDrugClassification() {
		return drugClassification;
	}

	public void setDrugClassification(DrugClassification drugClassification) {
		this.drugClassification = drugClassification;
	}

	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

}