package com.kybaby.newbussiness.doctorclinic.bo.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.bo.DoctorClinicService;
import com.kybaby.newbussiness.doctorclinic.dao.DoctorClinicDao;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorClinicTimeSegment;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgClinicdate;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgTimeSetting;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePracticeOrgInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.util.ConstantManage;

public class DoctorClinicServiceImpl implements DoctorClinicService {
	private DoctorClinicDao doctorClinicDao;

	public DoctorClinicDao getDoctorClinicDao() {
		return doctorClinicDao;
	}

	public void setDoctorClinicDao(DoctorClinicDao doctorClinicDao) {
		this.doctorClinicDao = doctorClinicDao;
	}

	@Override
	public Long saveOrUpdateDoctorMorePractice(
			DoctorMorePractice doctorMorePractice) {
		doctorMorePractice.setOperationTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
		return this.doctorClinicDao.saveOrUpdateDoctorMorePractice(doctorMorePractice);
	}
	
	@Override
	public Long saveDoctorMorePracticeByRecord(DoctorMorePractice doctorMorePractice) {
		return doctorClinicDao.saveDoctorMorePracticeByRecord(doctorMorePractice);
	}
	
	@Override
	public void saveOrUpdateDoctorClinicTimeSegment(DoctorMorePractice doctorMorePractice){
		if(doctorMorePractice == null) return;
		//删除原有时间段
		this.doctorClinicDao.deleteClinicTimeSegment(doctorMorePractice);
		//保存新的时间段
		String beginTime = doctorMorePractice.getClinicBeganTime();
		String endTime = doctorMorePractice.getClinicEndTime();
		List<String> timeSegmentList = this.getTimeSegment(beginTime, endTime, ConstantManage.TIME_INTERVAL);
		if(timeSegmentList != null){
			for(String time : timeSegmentList){
				DoctorClinicTimeSegment doctorClinicTimeSegment = new DoctorClinicTimeSegment();
				doctorClinicTimeSegment.setDoctorMorePractice(doctorMorePractice);
				doctorClinicTimeSegment.setIsCanUse("Y");
				doctorClinicTimeSegment.setSegment(time);
				this.doctorClinicDao.saveOrUpdateDoctorClinicTimeSegment(doctorClinicTimeSegment);
			}
		}
	}
	@Override
	public Long addMoreOrgTimeSegment(String allDayMoreOrgTimeSetIdJson,DoctorMorePractice doctorMorePractice){
		Long num = 0L;
		JSONArray array = JSONArray.fromObject(allDayMoreOrgTimeSetIdJson); 
		System.out.println(array);
		for(int i = 0; i < array.size(); i++){ 
			JSONObject jo = array.getJSONObject(i);
			String timeId = jo.get("id").toString();
			DoctorMoreOrgTimeSetting timeSetting = 
					this.getDoctorMoreOrgTimeSettingById(Long.valueOf(timeId));
			String beginTime = timeSetting.getStartTime();
			String endTime = timeSetting.getEndTime();
			List<String> timeSegmentList = this.getTimeSegment(beginTime, endTime, ConstantManage.TIME_INTERVAL);
			if(timeSegmentList != null){
				for(String time : timeSegmentList){
					num++;
					DoctorClinicTimeSegment doctorClinicTimeSegment = new DoctorClinicTimeSegment();
					doctorClinicTimeSegment.setDoctorMorePractice(doctorMorePractice);
					doctorClinicTimeSegment.setIsCanUse("Y");
					doctorClinicTimeSegment.setSegment(time);
					this.doctorClinicDao.saveOrUpdateDoctorClinicTimeSegment(doctorClinicTimeSegment);
				}
			}
		}
		return num;
	}
	public static void main(String[] args){
		DoctorClinicServiceImpl dc = new DoctorClinicServiceImpl();
		dc.getTimeSegment("08:00", "09:45", ConstantManage.TIME_INTERVAL);
	}
	/**
	 * 计算开始时间到结束时间中间，每隔一定时间的分段时间集合
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param timeInterval 间隔时间
	 * @return
	 */
	private List<String> getTimeSegment(String beginTime,String endTime,Integer timeInterval){
		List<String> timeSegmentList = new ArrayList<String>();
		DateFormat FORMATTER = new SimpleDateFormat("HH:mm"); 
		Calendar startDay = Calendar.getInstance();  
        Calendar endDay = Calendar.getInstance(); 
        timeSegmentList.add(beginTime);
        try {
			startDay.setTime(FORMATTER.parse(beginTime));
			endDay.setTime(FORMATTER.parse(endTime));  
			if(!beginTime.equals(endTime)){  
				if(startDay.compareTo(endDay)<=0){  
					//现在打印中的日期  
                    Calendar currentPrintDay = startDay;  
                    while (true){  
                       // 时间加  
                       currentPrintDay.add(Calendar.MINUTE, timeInterval);  
                       // 日期加一后判断是否达到终了日，达到则终止打印  
                       if (currentPrintDay.compareTo(endDay) == 0) {  
                           break;  
                       }  
                       timeSegmentList.add(FORMATTER.format(currentPrintDay.getTime()));  
                   }  
                 
				}
			}
			// timeSegmentList.add(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        if(timeSegmentList.isEmpty()){
        	return null;
        }
        System.out.println(timeSegmentList.toString());
		return timeSegmentList;
	}

	@Override
	public DoctorMorePractice getDoctorMorePracticeById(Long id) {
		return this.doctorClinicDao.getDoctorMorePracticeById(id);
	}

	@Override
	public List<DoctorMorePractice> getDoctorMorePracticeList(
			DoctorMorePractice doctorMorePractice,DoctorInfo doctorInfo) {
		return this.doctorClinicDao.getDoctorMorePracticeList(doctorMorePractice,doctorInfo);
	}

	@Override
	public List<String> getClinicAddress(DoctorMorePractice doctorMorePractice,
			DoctorInfo doctorInfo) {
		List<DoctorMorePractice> doctorMorePracticeList = 
				this.doctorClinicDao.getDoctorMorePracticeList(doctorMorePractice,doctorInfo);
		if(doctorMorePracticeList == null) return null;
		List<String> list = new ArrayList<String>();
		for(DoctorMorePractice dp : doctorMorePracticeList){
			list.add(dp.getClinicAddress());
		}
		return list;
	}

	@Override
	public List<DoctorServiceType> getDoctorServiceTypeByDoctorId(
			DoctorInfo doctorInfo) {
		return this.doctorClinicDao.getDoctorServiceTypeByDoctorId(doctorInfo);
	}

	@Override
	public List<DoctorMorePracticeOrgInfo> getDoctorMorePracticeOrgInfoList() {
		return this.doctorClinicDao.getDoctorMorePracticeOrgInfoList();
	}

	@Override
	public Boolean checkTimeIsUsed(String date, String time,
			DoctorInfo doctorInfo,String clinicOrgType) {
		return this.doctorClinicDao.checkTimeIsUsed(date, time, doctorInfo, clinicOrgType);
	}

	@Override
	public List<DoctorMoreOrgTimeSetting> getMoreOrgTimeSettingList(
			DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo) {
		return this.doctorClinicDao.getMoreOrgTimeSettingList(doctorMorePracticeOrgInfo);
	}

	@Override
	public DoctorMorePracticeOrgInfo getDoctorMorePracticeOrgInfoByid(Long id) {
		return this.doctorClinicDao.getDoctorMorePracticeOrgInfoByid(id);
	}

	@Override
	public DoctorMoreOrgTimeSetting getDoctorMoreOrgTimeSettingById(Long id) {
		return this.doctorClinicDao.getDoctorMoreOrgTimeSettingById(id);
	}

	@Override
	public Long getOrderCountByDoctor(DoctorInfo doctorInfo,String appointmentDate) {
		return this.doctorClinicDao.getOrderCountByDoctor(doctorInfo, appointmentDate);
	}

	@Override
	public HospitalBasicInfo getHospitalBasicInfoByNameOrId(String name, Long id) {
		return this.doctorClinicDao.getHospitalBasicInfoByNameOrId(name, id);
	}

	@Override
	public List<DoctorMoreOrgClinicdate> getDoctorMoreOrgClinicdateList() {
		return this.doctorClinicDao.getDoctorMoreOrgClinicdateList();
	}

}
