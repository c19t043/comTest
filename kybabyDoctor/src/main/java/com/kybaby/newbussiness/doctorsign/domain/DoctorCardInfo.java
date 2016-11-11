package com.kybaby.newbussiness.doctorsign.domain;

import com.kybaby.domain.DoctorInfo;

/** 医生证书信息
 * DoctorCardInfo entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class DoctorCardInfo implements java.io.Serializable {

	private Long id;
	private DoctorInfo doctorInfo;
	private String imgPath;
	private String imgType;

	private String imgBase64;
	//执业证书base64
	private String zyCertificateBase64;
	//资格证书
	private String zgCertificateBase64;
	//职称证书
	private String zcCertificateBase64;
	
	// Constructors

	/** default constructor */
	public DoctorCardInfo() {
	}

	/** full constructor */
	public DoctorCardInfo(Long doctorId, String imgPath, String imgType) {
		this.imgPath = imgPath;
		this.imgType = imgType;
	}
	// Property accessors

	public String getZyCertificateBase64() {
		return zyCertificateBase64;
	}

	public String getImgBase64() {
		return imgBase64;
	}

	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}

	public void setZyCertificateBase64(String zyCertificateBase64) {
		this.zyCertificateBase64 = zyCertificateBase64;
	}

	public String getZgCertificateBase64() {
		return zgCertificateBase64;
	}

	public void setZgCertificateBase64(String zgCertificateBase64) {
		this.zgCertificateBase64 = zgCertificateBase64;
	}

	public String getZcCertificateBase64() {
		return zcCertificateBase64;
	}

	public void setZcCertificateBase64(String zcCertificateBase64) {
		this.zcCertificateBase64 = zcCertificateBase64;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public String getImgPath() {
		return this.imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getImgType() {
		return this.imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

}