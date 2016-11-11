package com.kybaby.newbussiness.doctorclinic.fo;

import java.util.List;

import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
/**
 * 用户预约门诊，医生门诊信息展示
 * @author lihao
 *
 */
public class DoctorMorePracticeFo {
	/**
	 * 门诊日期
	 */
	private String clinicDate;
	/**
	 * 医生门诊设置信息
	 */
	List<DoctorMorePractice> doctorMorePracticeList;
	public String getClinicDate() {
		return clinicDate;
	}
	public void setClinicDate(String clinicDate) {
		this.clinicDate = clinicDate;
	}
	public List<DoctorMorePractice> getDoctorMorePracticeList() {
		return doctorMorePracticeList;
	}
	public void setDoctorMorePracticeList(
			List<DoctorMorePractice> doctorMorePracticeList) {
		this.doctorMorePracticeList = doctorMorePracticeList;
	}
}
