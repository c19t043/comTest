package com.kybaby.newbussiness.doctorclinic.bo;

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

public interface DoctorClinicService {
	/**
	 * 得到多点医疗机构信息列表
	 * @return
	 */
	List<DoctorMorePracticeOrgInfo> getDoctorMorePracticeOrgInfoList(DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo);
	/**
	 * 保存或更新多点机构信息
	 * @param doctorMorePracticeOrgInfo
	 * @return
	 */
	Long saveOrUpdateDoctorMorePracticeOrgInfo(DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo);
	/**
	 * 保存或更新多点机构开放日期
	 * @param moreOrgClinicdateList
	 * @param orgId
	 */
	void saveOrUpdateDoctorMoreOrgClinicdateList(List<DoctorMoreOrgClinicdate>  moreOrgClinicdateList,Long orgId);
	/**
	 * 保存或更新多点机构开放时间段
	 * @param morePracticeOrgTimeList
	 * @param orgId
	 */
	void saveOrUpdateDoctorMoreOrgTimeSettingList(List<DoctorMoreOrgTimeSetting>  morePracticeOrgTimeList,Long orgId);
	/**
	 * 得到某机构的开放日期
	 * @param orgId
	 * @return
	 */
	List<DoctorMoreOrgClinicdate> getOpenDateByOrgId(Long orgId);
	/**
	 * 得到某机构的开放时间段
	 * @param orgId
	 * @return
	 */
	List<DoctorMoreOrgTimeSetting> getOpenTimeByOrgId(Long orgId);
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
	 * 得到所有可用医生
	 * @param doctorInfo
	 * @return
	 */
	List<DoctorInfo> getAllDoctor(DoctorInfo doctorInfo);
	/**
	 * 得到医生门诊设置信息列表
	 * @param doctorMorePractice
	 * @return
	 */
	List<DoctorMorePractice>  getDoctorMorePracticeList(DoctorMorePractice doctorMorePractice);
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
	 * 保存或更新医生门诊设置信息
	 * @param doctorMorePractice
	 */
	void saveOrUpdateDoctorClinicSet(DoctorMorePractice doctorMorePractice,Integer intervals);
	/**
	 * 门诊订单到时短信提醒任务
	 */
	void orderClinicPromptTask();
	/**
	 * 修改医生门诊多点工作记录
	 * 1.修改医生门诊多点工作记录
	 * 2.修改涉及门诊订单
	 * @param doctorMorePractice
	 * @return
	 */
	public boolean updateDoctorMorePracticeRecord(DoctorMorePractice doctorMorePractice);
}
