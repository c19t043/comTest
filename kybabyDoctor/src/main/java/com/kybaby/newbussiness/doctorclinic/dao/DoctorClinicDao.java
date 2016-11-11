package com.kybaby.newbussiness.doctorclinic.dao;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorClinicTimeSegment;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgClinicdate;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgTimeSetting;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePracticeOrgInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;

public interface DoctorClinicDao {
	/**
	 * 保存或更新医生门诊执业设置信息
	 * @param doctorMorePractice
	 * @return
	 */
	Long saveOrUpdateDoctorMorePractice(DoctorMorePractice doctorMorePractice);
	/**
	 * 记录医生门诊执业上下班信息
	 * @param doctorMorePractice
	 * @return
	 */
	Long saveDoctorMorePracticeByRecord(DoctorMorePractice doctorMorePractice);
	/**
	 * 删除原有时间分段信息
	 * @param doctorMorePractice
	 */
	void deleteClinicTimeSegment(DoctorMorePractice doctorMorePractice);
	/**
	 * 保存或更新医生门诊执业设置时间分段信息
	 * @param doctorMorePractice
	 * @return
	 */
	Long saveOrUpdateDoctorClinicTimeSegment(DoctorClinicTimeSegment doctorClinicTimeSegment);
	/**
	 * 根据主键得到门诊执业设置信息
	 * @param id
	 * @return
	 */
	DoctorMorePractice getDoctorMorePracticeById(Long id);
	/**
	 * 门诊执业设置信息列表
	 * @param doctorMorePractice
	 * @return
	 */
	List<DoctorMorePractice> getDoctorMorePracticeList(DoctorMorePractice doctorMorePractice,DoctorInfo doctorInfo);
	/**
	 * 根据医生信息得到医生客服务类型
	 * @param doctorInfo
	 * @return
	 */
	List<DoctorServiceType> getDoctorServiceTypeByDoctorId(DoctorInfo doctorInfo);
	/**
	 * 得到多点医疗机构信息
	 * @return
	 */
	List<DoctorMorePracticeOrgInfo> getDoctorMorePracticeOrgInfoList();
	/**
	 * 根据id得到多点机构信息
	 * @param id
	 * @return
	 */
	DoctorMorePracticeOrgInfo getDoctorMorePracticeOrgInfoByid(Long id);
	/**
	 * 检查预约时间是否被占用
	 * @param date
	 * @param time
	 * @param doctorInfo
	 * @return true表示占用了    false表示没占用
	 */
	Boolean checkTimeIsUsed(String date,String time,DoctorInfo doctorInfo,String clinicOrgType);
	/**
	 * 得到多点机构的时间配置信息集合
	 * @param doctorMorePracticeOrgInfo
	 * @return
	 */
	List<DoctorMoreOrgTimeSetting> getMoreOrgTimeSettingList(DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo);
	/**
	 * 根据id得到多点机构的时间配置信息
	 * @param id
	 * @return
	 */
	DoctorMoreOrgTimeSetting getDoctorMoreOrgTimeSettingById(Long id);
	/**
	 * 得到医生的完成订单数(外部单位)
	 * @param doctorInfo
	 * @return
	 */
	Long getOrderCountByDoctor(DoctorInfo doctorInfo,String appointmentDate);
	/**
	 * 根据名称或id得到医院信息
	 * @param name
	 * @param id
	 * @return
	 */
	HospitalBasicInfo getHospitalBasicInfoByNameOrId(String name,Long id);
	/**
	 * 得到其他机构时间设置关系表集合
	 * @return
	 */
	List<DoctorMoreOrgClinicdate> getDoctorMoreOrgClinicdateList();
}
