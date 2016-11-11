package com.java.familydoctor.serviceitem.vo;

import com.java.platform.core.BaseDomain;

/**
 * 家庭医生服务，服务项目实体信息
 * @author lihao
 *
 */

public class FdServiceItems extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String itemShowName;
	private String itemCode;
	private String imagePath;
	private String imageName;
	private String itemDescription;
	private String isEnable;
	private String url;
	private String serviceSkillNames;
	private String serviceSkillIds;
	//页面传值使用
	private String imgBase64;
	private String sort;  //用来到时候设置服务包谁在前谁在后
	// Constructors

	/** default constructor */
	public FdServiceItems() {
	}

	/** full constructor */
	public FdServiceItems(String itemShowName, String itemCode,
			String imagePath, String itemDescription, String isEnable) {
		this.itemShowName = itemShowName;
		this.itemCode = itemCode;
		this.imagePath = imagePath;
		this.itemDescription = itemDescription;
		this.isEnable = isEnable;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemShowName() {
		return this.itemShowName;
	}

	public void setItemShowName(String itemShowName) {
		this.itemShowName = itemShowName;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getItemDescription() {
		return this.itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImgBase64() {
		return imgBase64;
	}

	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}

	public String getServiceSkillNames() {
		return serviceSkillNames;
	}

	public void setServiceSkillNames(String serviceSkillNames) {
		this.serviceSkillNames = serviceSkillNames;
	}

	public String getServiceSkillIds() {
		return serviceSkillIds;
	}

	public void setServiceSkillIds(String serviceSkillIds) {
		this.serviceSkillIds = serviceSkillIds;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	

}