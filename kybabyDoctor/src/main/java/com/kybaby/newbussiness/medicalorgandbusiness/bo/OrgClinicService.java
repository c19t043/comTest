package com.kybaby.newbussiness.medicalorgandbusiness.bo;

import java.util.List;

import com.kybaby.domain.SymptomTag;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ClinicMedicalRecords;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.HospitalAddressInfo;

public interface OrgClinicService {
	/**
	 * 得到门诊订单列表
	 * @param orderInfoClinic
	 * @return
	 */
	List<OrderInfoClinic> getOrderInfoClinicList(
			OrderInfoClinic orderInfoClinic) ;
	/**
	 * 保存或更新就诊记录信息
	 * @param clinicMedicalRecords
	 * @return
	 */
	Long saveOrUpdateClinicMedicalRecords(
			ClinicMedicalRecords clinicMedicalRecords) ;
	/**
	 * 根据订单id得到就诊信息
	 * @param clinicOrderId
	 * @return
	 */
	ClinicMedicalRecords getClinicMedicalRecords(Long clinicOrderId);
	/**
	 * 根据id得到就诊信息
	 * @param clinicOrderId
	 * @return
	 */
	ClinicMedicalRecords getClinicMedicalRecordsById(Long id);
	/**
	 * 得到就诊记录列表
	 * @param ClinicMedicalRecords
	 * @param UserInfo
	 * @return
	 */
	List<ClinicMedicalRecords> getClinicMedicalRecordsList(ClinicMedicalRecords clinicMedicalRecords,UserInfo userInfo);
	/**
	 * 得到症状标签列表
	 * @param symptomTag
	 * @return
	 */
	List<SymptomTag> getAllSymptomTag(SymptomTag symptomTag);
	/**
	 * 得到医院地址列表
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<HospitalAddressInfo> getHospitalAddressInfoList(HospitalBasicInfo hospitalBasicInfo) ;
}
