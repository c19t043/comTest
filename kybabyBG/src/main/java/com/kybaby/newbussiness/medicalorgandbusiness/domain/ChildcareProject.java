package com.kybaby.newbussiness.medicalorgandbusiness.domain;


public class ChildcareProject implements java.io.Serializable {

	private static final long serialVersionUID = -5442628911220107053L;
	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 所属项目分类
	 */
	private ChildcareProjectType childcareProjectType;
	/**
	 * 适用最小月龄
	 */
	private String minMonthAge;
	/**
	 * 适用最大月龄
	 */
	private String maxMonthAge;
	/**
	 * 项目标题
	 */
	private String projectTitle;
	/**
	 * 项目内容
	 */
	private String projectContent;
	/**
	 * 排序号
	 */
	private int sort;
	/**
	 * 备注信息
	 */
	private String remark;
	/**
	 * 项目费用
	 */
	private String itemMoney;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ChildcareProjectType getChildcareProjectType() {
		return childcareProjectType;
	}
	public void setChildcareProjectType(ChildcareProjectType childcareProjectType) {
		this.childcareProjectType = childcareProjectType;
	}
	public String getMinMonthAge() {
		return minMonthAge;
	}
	public void setMinMonthAge(String minMonthAge) {
		this.minMonthAge = minMonthAge;
	}
	public String getMaxMonthAge() {
		return maxMonthAge;
	}
	public void setMaxMonthAge(String maxMonthAge) {
		this.maxMonthAge = maxMonthAge;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public String getProjectContent() {
		return projectContent;
	}
	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getItemMoney() {
		return itemMoney;
	}
	public void setItemMoney(String itemMoney) {
		this.itemMoney = itemMoney;
	}
	
}
