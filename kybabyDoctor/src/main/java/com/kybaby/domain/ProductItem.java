package com.kybaby.domain;

/**
 * ProductItem entity. @author MyEclipse Persistence Tools
 */

public class ProductItem implements java.io.Serializable {

	// Fields

	private Long id;
	private String itemNum;
	private String itemName;
	private String itemStatus;
	private Long whatFitForMonth;
	private String whatFitForSex;
	private Long serviceTime;
	private Long positionId;
	private String handleUrl;
	private String comments;
	private String tipInformation;
	private Long healthPlanId;
	private Long itemResultId;
	/**
	 * 流程节点id
	 */
	private Long flowNodeId;
	/**警示信息主键
	 * 
	 */
	private Long wearing_id;
	/**适合年龄最大值
	 * 
	 */
	private Long whatFitForMonth_max;

	// Constructors

	/** default constructor */
	public ProductItem() {
	}

	/** full constructor */
	public ProductItem(String itemNum, String itemName, String itemStatus,
			Long whatFitForMonth, String whatFitForSex, Long serviceTime,
			Long positionId, String handleUrl, String comments,
			String tipInformation, Long healthPlanId, Long itemResultId) {
		this.itemNum = itemNum;
		this.itemName = itemName;
		this.itemStatus = itemStatus;
		this.whatFitForMonth = whatFitForMonth;
		this.whatFitForSex = whatFitForSex;
		this.serviceTime = serviceTime;
		this.positionId = positionId;
		this.handleUrl = handleUrl;
		this.comments = comments;
		this.tipInformation = tipInformation;
		this.healthPlanId = healthPlanId;
		this.itemResultId = itemResultId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemNum() {
		return this.itemNum;
	}

	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemStatus() {
		return this.itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Long getWhatFitForMonth() {
		return this.whatFitForMonth;
	}

	public void setWhatFitForMonth(Long whatFitForMonth) {
		this.whatFitForMonth = whatFitForMonth;
	}

	public String getWhatFitForSex() {
		return this.whatFitForSex;
	}

	public void setWhatFitForSex(String whatFitForSex) {
		this.whatFitForSex = whatFitForSex;
	}

	public Long getServiceTime() {
		return this.serviceTime;
	}

	public void setServiceTime(Long serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Long getPositionId() {
		return this.positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getHandleUrl() {
		return this.handleUrl;
	}

	public void setHandleUrl(String handleUrl) {
		this.handleUrl = handleUrl;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTipInformation() {
		return this.tipInformation;
	}

	public void setTipInformation(String tipInformation) {
		this.tipInformation = tipInformation;
	}

	public Long getHealthPlanId() {
		return this.healthPlanId;
	}

	public void setHealthPlanId(Long healthPlanId) {
		this.healthPlanId = healthPlanId;
	}

	public Long getItemResultId() {
		return this.itemResultId;
	}

	public void setItemResultId(Long itemResultId) {
		this.itemResultId = itemResultId;
	}

	public Long getFlowNodeId() {
		return flowNodeId;
	}

	public void setFlowNodeId(Long flowNodeId) {
		this.flowNodeId = flowNodeId;
	}

	public Long getWearing_id() {
		return wearing_id;
	}

	public void setWearing_id(Long wearing_id) {
		this.wearing_id = wearing_id;
	}

	public Long getWhatFitForMonth_max() {
		return whatFitForMonth_max;
	}

	public void setWhatFitForMonth_max(Long whatFitForMonth_max) {
		this.whatFitForMonth_max = whatFitForMonth_max;
	}

}