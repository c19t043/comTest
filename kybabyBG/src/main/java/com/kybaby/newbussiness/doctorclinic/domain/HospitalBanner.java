package com.kybaby.newbussiness.doctorclinic.domain;

import java.io.File;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;

/**
 * HospitalBanner entity. @author MyEclipse Persistence Tools
 */

public class HospitalBanner implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private HospitalBasicInfo hospitalBasicInfo;
	private String imgName;
	private String imgPath;
	private String imgRemark;
	private String imgContent;
	private String isMainImg;
	
	
	private File pictureFile;//图片文件
	private String imgBase64;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public String getImgName() {
		return this.imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgPath() {
		return this.imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getIsMainImg() {
		return isMainImg;
	}

	public void setIsMainImg(String isMainImg) {
		this.isMainImg = isMainImg;
	}

	public String getImgRemark() {
		return this.imgRemark;
	}

	public void setImgRemark(String imgRemark) {
		this.imgRemark = imgRemark;
	}

	public String getImgContent() {
		return this.imgContent;
	}

	public void setImgContent(String imgContent) {
		this.imgContent = imgContent;
	}

	public File getPictureFile() {
		return pictureFile;
	}

	public void setPictureFile(File pictureFile) {
		this.pictureFile = pictureFile;
	}

	public String getImgBase64() {
		return imgBase64;
	}

	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}

}