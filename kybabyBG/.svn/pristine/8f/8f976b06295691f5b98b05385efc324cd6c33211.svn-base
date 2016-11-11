package com.kybaby.newbussiness.doctorclinic.bo.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.bo.DoctorClinicService;
import com.kybaby.newbussiness.doctorclinic.dao.DoctorClinicDao;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorClinicTimeSegment;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgClinicdate;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgTimeSetting;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePracticeOrgInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBanner;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.util.SendSms;

public class DoctorClinicServiceImpl implements DoctorClinicService {
	private DoctorClinicDao doctorClinicDao;

	public DoctorClinicDao getDoctorClinicDao() {
		return doctorClinicDao;
	}

	public void setDoctorClinicDao(DoctorClinicDao doctorClinicDao) {
		this.doctorClinicDao = doctorClinicDao;
	}

	@Override
	public List<DoctorMorePracticeOrgInfo> getDoctorMorePracticeOrgInfoList(DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo) {
		return this.doctorClinicDao.getDoctorMorePracticeOrgInfoList( doctorMorePracticeOrgInfo);
	}

	@Override
	public Long saveOrUpdateDoctorMorePracticeOrgInfo(
			DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo) {
		Long id;
		if(doctorMorePracticeOrgInfo.getId() == null){
			id = this.doctorClinicDao.saveDoctorMorePracticeOrgInfo(doctorMorePracticeOrgInfo);
		}else{
			id = doctorMorePracticeOrgInfo.getId();
			this.doctorClinicDao.updateDoctorMorePracticeOrgInfo(doctorMorePracticeOrgInfo);
		}
		return id;
	}

	@Override
	public List<DoctorMoreOrgClinicdate> getOpenDateByOrgId(Long orgId) {
		return doctorClinicDao.getDoctorMoreOrgClinicdateList(orgId);
	}

	@Override
	public List<DoctorMoreOrgTimeSetting> getOpenTimeByOrgId(Long orgId) {
		return doctorClinicDao.getOpenTimeByOrgId(orgId);
	}

	@Override
	public void saveOrUpdateDoctorMoreOrgClinicdateList(
			List<DoctorMoreOrgClinicdate> moreOrgClinicdateList, Long orgId) {
		if(!moreOrgClinicdateList.isEmpty()){
			DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo = new DoctorMorePracticeOrgInfo();
			doctorMorePracticeOrgInfo.setId(orgId);
			for(DoctorMoreOrgClinicdate dmocd : moreOrgClinicdateList){
				dmocd.setDoctorMorePracticeOrgInfo(doctorMorePracticeOrgInfo);
				if(dmocd.getId() == null){
					this.doctorClinicDao.saveDoctorMoreOrgClinicdate(dmocd);
				}else{
					this.doctorClinicDao.updateDoctorMoreOrgClinicdate(dmocd);
				}
			}
		}
		
	}

	@Override
	public void saveOrUpdateDoctorMoreOrgTimeSettingList(
			List<DoctorMoreOrgTimeSetting> morePracticeOrgTimeList, Long orgId) {
		if(!morePracticeOrgTimeList.isEmpty()){
			DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo = new DoctorMorePracticeOrgInfo();
			doctorMorePracticeOrgInfo.setId(orgId);
			for(DoctorMoreOrgTimeSetting dmots : morePracticeOrgTimeList){
				dmots.setDoctorMorePracticeOrgInfo(doctorMorePracticeOrgInfo);
				this.doctorClinicDao.saveOrUpdateDoctorMoreOrgTimeSetting(dmots);
			}
		}
	}

	@Override
	public List<HospitalPosition> getHospitalPositionList(
			HospitalPosition hospitalPosition) {
		return this.doctorClinicDao.getHospitalPositionList(hospitalPosition);
	}

	@Override
	public List<HospitalBasicInfo> getHospitalBasicInfoList(
			HospitalBasicInfo hospitalBasicInfo) {
		return doctorClinicDao.getHospitalBasicInfoList(hospitalBasicInfo);
	}

	@Override
	public void saveOrUpdateHospitalPosition(HospitalPosition hospitalPosition) {
		this.doctorClinicDao.saveOrUpdateHospitalPosition(hospitalPosition);
	}

	@Override
	public List<DoctorMorePractice> getDoctorMorePracticeList(
			DoctorMorePractice doctorMorePractice) {
		return doctorClinicDao.getDoctorMorePracticeList(doctorMorePractice);
	}

	@Override
	public List<DoctorInfo> getAllDoctor(DoctorInfo doctorInfo) {
		return doctorClinicDao.getAllDoctor(doctorInfo);
	}

	@Override
	public Long saveOrUpdateHospitalBasicInfo(
			HospitalBasicInfo hospitalBasicInfo) {
		Long hospitalId = doctorClinicDao.saveOrUpdateHospitalBasicInfo(hospitalBasicInfo);
		return hospitalId;
	}
	@Override
	public Long saveOrUpdateHospitalBanner(
			HospitalBanner hospitalBanner) {
		Long bannerId = doctorClinicDao.saveOrUpdateHospitalBanner(hospitalBanner);
		return bannerId;
	}

	@Override
	public List<OrderInfoClinic> getOrderInfoClinicList(
			OrderInfoClinic orderInfoClinic) {
		return doctorClinicDao.getOrderInfoClinicList(orderInfoClinic);
	}

	@Override
	public void saveOrUpdateOrderInfoClinic(OrderInfoClinic orderInfoClinic) {
		doctorClinicDao.saveOrUpdateOrderInfoClinic(orderInfoClinic);
	}

	@Override
	public List<HospitalBanner> getHospitalBannerList(
			HospitalBasicInfo hospitalBasicInfo) {
		return doctorClinicDao.getHospitalBannerList(hospitalBasicInfo);
	}

	@Override
	public Long saveOrUpdateDoctorMorePractice(
			DoctorMorePractice doctorMorePractice) {
		return doctorClinicDao.saveOrUpdateDoctorMorePractice(doctorMorePractice);
	}

	@Override
	public List<DoctorMoreOrgClinicdate> getDoctorMoreOrgClinicdateList() {
		return doctorClinicDao.getDoctorMoreOrgClinicdateList();
	}

	@Override
	public DoctorMoreOrgClinicdate getDoctorMoreOrgClinicdateById(Long id) {
		return doctorClinicDao.getDoctorMoreOrgClinicdateById(id);
	}

	@Override
	public DoctorMoreOrgTimeSetting getDoctorMoreOrgTimeSettingById(Long id) {
		return doctorClinicDao.getDoctorMoreOrgTimeSettingById(id);
	}

	@Override
	public void saveOrUpdateDoctorClinicTimeSegment(
			DoctorClinicTimeSegment doctorClinicTimeSegment) {
		doctorClinicDao.saveOrUpdateDoctorClinicTimeSegment(doctorClinicTimeSegment);
	}

	@Override
	public DoctorMorePractice getDoctorMorePracticeById(Long id) {
		return doctorClinicDao.getDoctorMorePracticeById(id);
	}

	@Override
	public void delDoctorClinicTimeSegment(DoctorMorePractice doctorMorePractice) {
		doctorClinicDao.delDoctorClinicTimeSegment(doctorMorePractice);
	}

	@Override
	public List<DoctorClinicTimeSegment> getDoctorClinicTimeSegmentList(
			DoctorMorePractice doctorMorePractice) {
		return this.doctorClinicDao.getDoctorClinicTimeSegmentList(doctorMorePractice);
	}
	@Override
	public void saveOrUpdateDoctorClinicSet(
			DoctorMorePractice doctorMorePractice,Integer intervals) {
		Long id = this.doctorClinicDao.saveOrUpdateDoctorMorePractice(doctorMorePractice);
		// 得到最新保存的多点设置信息
		doctorMorePractice = this.doctorClinicDao.getDoctorMorePracticeById(id);
		// 删除原有时间点开放信息
		this.delDoctorClinicTimeSegment(doctorMorePractice);
		Long num = 0L;
		if (doctorMorePractice.getOrgClinicTimeIds() != null
				&& !"".equals(doctorMorePractice.getOrgClinicTimeIds())) {
			String[] timeids = doctorMorePractice.getOrgClinicTimeIds().replace(" ", "").split(",");
			for (int i = 0; i < timeids.length; i++) {
				String timeId = timeids[i];
				DoctorMoreOrgTimeSetting timeSetting = this
						.getDoctorMoreOrgTimeSettingById(Long
								.valueOf(timeId.trim()));
				String beginTime = timeSetting.getStartTime();
				String endTime = timeSetting.getEndTime();
				if(i == 0){//取头和尾，始末时间
					doctorMorePractice.setClinicBeganTime(beginTime);
				}
				if(i == timeids.length-1){
					doctorMorePractice.setClinicEndTime(endTime);
				}
				List<String> timeSegmentList = this.getTimeSegment(
						beginTime, endTime, intervals);
				if (timeSegmentList != null) {
					for (String time : timeSegmentList) {
						num++;
						DoctorClinicTimeSegment doctorClinicTimeSegment = new DoctorClinicTimeSegment();
						doctorClinicTimeSegment
								.setDoctorMorePractice(doctorMorePractice);
						doctorClinicTimeSegment.setIsCanUse("Y");
						doctorClinicTimeSegment.setSegment(time);
						this.doctorClinicDao.saveOrUpdateDoctorClinicTimeSegment(doctorClinicTimeSegment);
					}
				}
			}
		}
		doctorMorePractice.setCanClinicNum(num);
		this.doctorClinicDao.saveOrUpdateDoctorMorePractice(doctorMorePractice);
		
	}

	/**
	 * 计算开始时间到结束时间中间，每隔一定时间的分段时间集合
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param timeInterval
	 *            间隔时间
	 * @return
	 */
	private List<String> getTimeSegment(String beginTime, String endTime,
			Integer timeInterval) {
		List<String> timeSegmentList = new ArrayList<String>();
		DateFormat FORMATTER = new SimpleDateFormat("HH:mm");
		Calendar startDay = Calendar.getInstance();
		Calendar endDay = Calendar.getInstance();
		timeSegmentList.add(beginTime);
		try {
			startDay.setTime(FORMATTER.parse(beginTime));
			endDay.setTime(FORMATTER.parse(endTime));
			if (!beginTime.equals(endTime)) {
				if (startDay.compareTo(endDay) <= 0) {
					// 现在打印中的日期
					Calendar currentPrintDay = startDay;
					while (true) {
						// 时间加
						currentPrintDay.add(Calendar.MINUTE, timeInterval);
						// 日期加一后判断是否达到终了日，达到则终止打印
						if (currentPrintDay.compareTo(endDay) >= 0) {
							break;
						}
						timeSegmentList.add(FORMATTER.format(currentPrintDay
								.getTime()));
					}

				}
			}
			// timeSegmentList.add(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (timeSegmentList.isEmpty()) {
			return null;
		}
		System.out.println(timeSegmentList.toString());
		return timeSegmentList;
	}

	@Override
	public void orderClinicPromptTask() {
		System.out.println("====门诊提前一小时提醒定时任务执行。。。。。。");
		OrderInfoClinic orderInfoClinic = new OrderInfoClinic();
		//orderInfoClinic.setOrderStatus("已预约");
		orderInfoClinic.setAppointmentDate(DateManage.getDateStr("yyyy-MM-dd"));
		List<OrderInfoClinic> list = this.doctorClinicDao.getOrderInfoClinicList(orderInfoClinic);
		if(list != null){
			//门诊结束状态集合
			String endFlag = OrderInfoClinic.HASE_FINISHED_CLINIC_ORDER+","+
					OrderInfoClinic.HASE_EVALUATED_CLINIC_ORDER+","+
					OrderInfoClinic.HASE_MEET_CLINIC_ORDER;
			Calendar c = Calendar.getInstance();
			int nowHour = c.get(Calendar.HOUR_OF_DAY);
			int endHour = nowHour - 1;//门诊前1小时提醒
			SendSms ss = new SendSms();
			for(OrderInfoClinic oic : list){
				String beginTime = oic.getAppointmentBeganTime();
				String endTime = oic.getAppointmentEndTime();
				int hour = Integer.valueOf(beginTime.split(":")[0]);
				int hour_end = Integer.valueOf(endTime.split(":")[0]);
				if(OrderInfoClinic.HASE_BOOKED_CLINIC_ORDER.equals(oic.getOrderStatus()) && endHour == hour){
					StringBuffer contecnt = new StringBuffer("");
					UserInfo user = oic.getUserInfo();
					DoctorInfo doctor = oic.getDoctorInfo();
					contecnt.append("亲爱的")
					.append("用户，您预约").append(doctor.getDoctorName()).append("医生的门诊服务，时间:")
					.append(oic.getAppointmentDate()).append(" ").append(oic.getAppointmentBeganTime())
					.append("，地址：").append(oic.getClinicAddress())
					.append("。详情请查‘我的订单’");
					ss.sendInfo(user.getPhone(), contecnt.toString());
					System.out.println(contecnt);
				}
				if(endFlag.indexOf(oic.getOrderStatus()) > -1 && (hour_end+1) == nowHour){//结束后一小时发短信
					UserInfo user = oic.getUserInfo();
					String content = "亲，感谢您在康优专家咨询，如有任何问题可关注“康优宝贝”官方微信在线咨询，祝宝宝健康成长。";
					ss.sendInfo(user.getPhone(), content.toString());
				}
			}
		}
	}
	/**
	 * 修改医生门诊多点工作记录
	 * 1.修改医生门诊多点工作记录
	 * 2.修改涉及门诊订单
	 * @param doctorMorePractice
	 * @return
	 */
	public boolean updateDoctorMorePracticeRecord(DoctorMorePractice doctorMorePractice){
		/*
		 * 1.修改医生门诊多点工作记录
		 * 2.修改涉及门诊订单
		 */
		
		//修改医生门诊多点工作记录
		/*
		 * 1.根据门诊id，查询门诊记录
		 * 2.修改门诊记录中，医生id
		 */
		DoctorMorePractice record = this.doctorClinicDao.findDoctorMorePractice(doctorMorePractice.getId());
		if(record==null) return false;
		Long doctorId = doctorMorePractice.getDoctorInfo().getId();
		DoctorInfo doctorInfo = new DoctorInfo();
		doctorInfo.setId(doctorId);
		record.setDoctorInfo(doctorInfo);
		this.doctorClinicDao.saveOrUpdateDoctorMorePractice(record);
		//修改涉及门诊订单
		/*
		 * 1.根据门诊日期，门诊地址，查询门诊订单
		 * 2.如果订单存在，修改门诊订单医生id
		 */
		List<OrderInfoClinic> clinicOrderList = this.doctorClinicDao.findClinicOrder(doctorMorePractice.getClinicDate(),
				doctorMorePractice.getClinicAddress());
		if(!clinicOrderList.isEmpty()){
			for(OrderInfoClinic clinicOrder : clinicOrderList){
				DoctorInfo doctorInfo_order = new DoctorInfo();
				doctorInfo_order.setId(doctorId);
				clinicOrder.setDoctorInfo(doctorInfo_order);
				this.doctorClinicDao.updateClinicOrder(clinicOrder);
			}
		}
		return true;
	}
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		int nowHour = c.get(Calendar.HOUR_OF_DAY);
		int endHour = nowHour - 11;//上门前2小时提醒
		int hour = Integer.valueOf("01");
		System.out.println(nowHour + "==="+hour+"==" +endHour);
	}
}
