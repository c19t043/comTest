package com.java.operationmanage.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Transient;

import com.java.platform.district.vo.District;

public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = -5520290446775702917L;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 家长姓名
	 */
	private String parentName;
	/**
	 * 宝宝姓名
	 */
	private String babyName;
	/**
	 * 宝宝性别
	 */
	private String babySex;
	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 用户地址
	 */
	private String address;
	/**
	 * 所属行政区
	 */
	private District district;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 来源   线上活动、线下活动、社区门诊、会议、讲座、其他
	 */
	private String source;
	/**
	 * 所属社区
	 */
	private HospitalBasicInfo hospitalBasicInfo;
	/**
	 * 得到宝宝的月龄
	 * @return
	 */
	@SuppressWarnings("static-access")
	@Transient
	public int getBabyMonth() throws ParseException {
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(new Date());
        c2.setTime(sdf.parse(this.birthday));
        result=(c1.get(c1.YEAR)-c2.get(c2.YEAR))*12+((c1.get(c1.MONTH)+1)-(c2.get(c2.MONTH)+1));
        return result == 0 ? 1 : Math.abs(result);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getBabyName() {
		return babyName;
	}
	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}
	public String getBabySex() {
		return babySex;
	}
	public void setBabySex(String babySex) {
		this.babySex = babySex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}
}
