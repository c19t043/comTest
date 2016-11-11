package com.kybaby.newbussiness.doctorclinic.dao;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorClinicTimeSegment;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgClinicdate;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgTimeSetting;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePracticeOrgInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBanner;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;

public interface DoctorClinicDao {
	/**
	 * 得到多点医疗机构信息
	 * @return
	 */
	List<DoctorMorePracticeOrgInfo> getDoctorMorePracticeOrgInfoList(DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo);
	/**
	 * 根据id得到多点机构信息
	 * @param id
	 * @return
	 */
	DoctorMorePracticeOrgInfo getDoctorMorePracticeOrgInfoByid(Long id);
	/**
	 * 得到其他机构时间设置关系表集合
	 * @param orgId 其他机构id
	 * @return
	 */
	List<DoctorMoreOrgClinicdate> getDoctorMoreOrgClinicdateList(Long orgId);
	/**
	 * 得到某机构的开放时间段
	 * @param orgId
	 * @return
	 */
	List<DoctorMoreOrgTimeSetting> getOpenTimeByOrgId(Long orgId);
	/**
	 * 保存其他机构
	 * @param doctorMorePracticeOrgInfo
	 * @return
	 */
	Long saveDoctorMorePracticeOrgInfo(DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo);
	/**
	 * 更新其他机构
	 * @param doctorMorePracticeOrgInfo
	 */
	void updateDoctorMorePracticeOrgInfo(DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo);
	/**
	 * 保存其他机构时间关系表
	 * @param doctorMoreOrgClinicdate
	 * @return
	 */
	Long saveDoctorMoreOrgClinicdate(DoctorMoreOrgClinicdate doctorMoreOrgClinicdate);
	/**
	 * 更新其他机构时间关系表
	 * @param doctorMoreOrgClinicdate
	 * @return
	 */
	void updateDoctorMoreOrgClinicdate(DoctorMoreOrgClinicdate doctorMoreOrgClinicdate);
	/**
	 * 保存或更新机构时间段信息
	 * @param doctorMoreOrgTimeSetting
	 * @return
	 */
	Long saveOrUpdateDoctorMoreOrgTimeSetting(DoctorMoreOrgTimeSetting doctorMoreOrgTimeSetting);
	/**
	 * 多点执业分成保底钱列表
	 * @param hospitalPosition
	 * @return
	 */
	List<HospitalPosition> getHospitalPositionList(HospitalPosition hospitalPosition);
	/**
	 * 保存或更新机构分成比例
	 * @param hospitalPosition
	 */
	void saveOrUpdateHospitalPosition(HospitalPosition hospitalPosition);
	/**
	 *得到医院信息列表
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<HospitalBasicInfo> getHospitalBasicInfoList(HospitalBasicInfo hospitalBasicInfo);
	/**
	 * 医院banner
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<HospitalBanner> getHospitalBannerList(HospitalBasicInfo hospitalBasicInfo);
	/**
	 * 保存或更新机构分成比例
	 * @param hospitalPosition
	 */
	Long saveOrUpdateHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo);
	/**
	 * 保存或更新banner
	 * @param hospitalBanner
	 */
	Long saveOrUpdateHospitalBanner(HospitalBanner hospitalBanner);
	/**
	 * 得到医生门诊设置信息列表
	 * @param doctorMorePractice
	 * @return
	 */
	List<DoctorMorePractice>  getDoctorMorePracticeList(DoctorMorePractice doctorMorePractice);
	/**
	 * 得到所有可用医生
	 * @param doctorInfo
	 * @return
	 */
	List<DoctorInfo> getAllDoctor(DoctorInfo doctorInfo);
	/**
	 * 得到门诊订单信息列表
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<OrderInfoClinic> getOrderInfoClinicList(OrderInfoClinic orderInfoClinic);
	/**
	 * 保存或更新门诊订单信息
	 * @param orderInfoClinic
	 */
	void saveOrUpdateOrderInfoClinic(OrderInfoClinic orderInfoClinic);
	/**
	 * 保存或更新多点执业设置
	 * @param doctorMorePractice
	 */
	Long saveOrUpdateDoctorMorePractice(DoctorMorePractice doctorMorePractice);
	/**
	 * 得到其他机构时间设置关系表集合
	 * @return
	 */
	List<DoctorMoreOrgClinicdate> getDoctorMoreOrgClinicdateList();
	/**
	 * 根据id得到机构开放日期信息
	 * @param id
	 * @return
	 */
	DoctorMoreOrgClinicdate getDoctorMoreOrgClinicdateById(Long id);
	/**
	 * 得到时间点设置信息
	 * @param id
	 * @return
	 */
	DoctorMoreOrgTimeSetting getDoctorMoreOrgTimeSettingById(Long id);
	/**
	 *  保存医生开放时间点设置
	 * @param doctorMoreOrgTimeSetting
	 */
	void saveOrUpdateDoctorClinicTimeSegment(DoctorClinicTimeSegment doctorClinicTimeSegment);
	/**
	 * 得到多点设置时间段集合
	 * @param doctorMorePractice
	 * @return
	 */
	List<DoctorClinicTimeSegment> getDoctorClinicTimeSegmentList(DoctorMorePractice doctorMorePractice);
	/**
	 * 根据id得到多线设置信息
	 * @param id
	 * @return
	 */
	DoctorMorePractice getDoctorMorePracticeById(Long id);
	/**
	 * 删除原有时间点开放设置信息
	 * @param doctorMorePractice
	 */
	void delDoctorClinicTimeSegment(DoctorMorePractice doctorMorePractice);
	/**
	 * 根据门诊id，查询门诊记录
	 * @param id
	 * @return
	 */
	DoctorMorePractice findDoctorMorePractice(Long id);
	/**
	 * 根据门诊日期，门诊地址，查询门诊订单
	 * @param clinicDate 门诊日期
	 * @param clinicAddress 门诊地址
	 * @return
	 */
	List<OrderInfoClinic> findClinicOrder(String clinicDate, String clinicAddress);
	/**
	 * 修改门诊订单医生id
	 * @param clinicOrder
	 */
	void updateClinicOrder(OrderInfoClinic clinicOrder);
}
