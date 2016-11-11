package com.kybaby.newbussiness.familydoctor.domain;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;

/**
 * 咨询医生信息
 * @author lihao
 *
 */

public class ConsultDoctorInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private DoctorInfo doctorInfo;
	private Long serviceLength;
	private Double consultMoney;
	private Double consultCommission;
	private String isEnable;
	/**
	 * 是否推荐（Y/N）
	 */
	private String isRecommend;
	/**
	 * 是否上线（Y/N）医生自己设置的，上线则表示能及时回复
	 */
	private String isOnline;
	
	//页面使用
	private HospitalBasicInfo hospitalBasicInfo;
	/**
	 * 专业方向名称集合
	 */
	private List<String> majorNameList;
	/**
	 * 擅长领域名称集合
	 */
	private List<String> goodFieldNameList;


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

	public Long getServiceLength() {
		return this.serviceLength;
	}

	public void setServiceLength(Long serviceLength) {
		this.serviceLength = serviceLength;
	}

	public Double getConsultMoney() {
		return this.consultMoney;
	}

	public void setConsultMoney(Double consultMoney) {
		this.consultMoney = consultMoney;
	}

	public Double getConsultCommission() {
		return this.consultCommission;
	}

	public void setConsultCommission(Double consultCommission) {
		this.consultCommission = consultCommission;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public List<String> getMajorNameList() {
		return majorNameList;
	}

	public void setMajorNameList(List<String> majorNameList) {
		this.majorNameList = majorNameList;
	}

	public List<String> getGoodFieldNameList() {
		return goodFieldNameList;
	}

	public void setGoodFieldNameList(List<String> goodFieldNameList) {
		this.goodFieldNameList = goodFieldNameList;
	}

	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

}