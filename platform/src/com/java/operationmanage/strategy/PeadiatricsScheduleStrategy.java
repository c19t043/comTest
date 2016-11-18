package com.java.operationmanage.strategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.java.medicalorgandbusiness.vo.OrderInfoClinic;
import com.java.operationmanage.common.BooleanMsg;
import com.java.operationmanage.vo.DoctorClinicTimeSegment;
import com.java.operationmanage.vo.DoctorMorePractice;
import com.java.operationmanage.vo.DoctorMorePracticeOrgInfo;
import com.java.operationmanage.vo.OperaDoctorSchedule;
import com.java.platform.user.service.ServiceImpl;
import com.java.util.DateManage;

public class PeadiatricsScheduleStrategy extends ScheduleStrategy {

	public PeadiatricsScheduleStrategy(ServiceImpl service) {
		super(service);
	}

	@Override
	public BooleanMsg isPassWithPublishCondition(OperaDoctorSchedule operaDoctorSchedule) {
		BooleanMsg booleanMsg = this.validateScheduleConditionIsOK(operaDoctorSchedule);
		//查看是否有用户已经预约
		Long srcID = operaDoctorSchedule.getSrcID();
		if(srcID!=null){
			DoctorMorePractice query_dmp = service.get(srcID, DoctorMorePractice.class);
			String clinicDate = query_dmp.getClinicDate();
			String clinicAddress = query_dmp.getClinicAddress();
			String hql = "from OrderInfoClinic c where c.appointmentDate = ? and c.clinicAddress = ?";
			List<OrderInfoClinic> infos = 
					service.getPersistProxy().getOrmPersistence().findByHQLQuery(hql, new Object[]{clinicDate,clinicAddress});
			if(!infos.isEmpty()){
				booleanMsg.isTrue(false);
				String msg = booleanMsg.getMsg();
				booleanMsg.setMsg(StringUtils.isNotBlank(msg)?msg+";":";"+"该排班已预约了"+infos.size()+"次,不能修改");
			}
		}
		return booleanMsg;
	}
	@Override
	public Long addSchedule(OperaDoctorSchedule operaDoctorSchedule) {
		Long resID = null;
		/*
		 * 计算号源
		 */
		List<DoctorClinicTimeSegment> details = calculatePeadiatricsTotalSourceDetail(operaDoctorSchedule);
		/*
		 * 组装儿科资源数据 
		 */
		DoctorMorePractice doctorMorePractice = packagePeadiatricsResource(Long.parseLong(details.size()+""), operaDoctorSchedule);
		
		resID = (Long) service.add(doctorMorePractice);
		for (DoctorClinicTimeSegment doctorClinicTimeSegment : details) {
			doctorClinicTimeSegment.setDoctorMorePractice(doctorMorePractice);
			service.add(doctorClinicTimeSegment);
		}
		return resID;
	}

	@Override
	public Long updateSchedule(OperaDoctorSchedule operaDoctorSchedule) {
		Long srcID = operaDoctorSchedule.getSrcID();
		/*
		 * 计算号源
		 */
		List<DoctorClinicTimeSegment> details = calculatePeadiatricsTotalSourceDetail(operaDoctorSchedule);
		/*
		 * 组装儿科资源数据 
		 */
		DoctorMorePractice doctorMorePractice = packagePeadiatricsResource(Long.parseLong(details.size()+""), operaDoctorSchedule);
		/*
		 * 修改主表信息
		 */
		DoctorMorePractice query_dmp = service.get(srcID, DoctorMorePractice.class);
		BeanUtils.copyProperties(doctorMorePractice, query_dmp, new String[]{"id"});
		service.edit(query_dmp);
		/*
		 * 修改明细表
		 */
		updatePeadiatricsDetails(details, query_dmp);
		return srcID;
	}
	/**
	 * 修改儿科明细表
	 * @param details 现有资源明细
	 * @param query_dmp 原有资源
	 */
	private void updatePeadiatricsDetails(List<DoctorClinicTimeSegment> details,DoctorMorePractice query_dmp){
		/*
		 * 修改明细表
		 * 保存现有明细和原有明细个数一致
		 * 1.如果新增加的明细数量<原有明细数量:原有明细多出的明细isDel设置为Y
		 * 2.如果新增加的明细数量>原有明细数量:原有明细增加多出个数的明细对象
		 */
		List<DoctorClinicTimeSegment> queryPeadiatricsResourceDetails = queryPeadiatricsResourceDetails(query_dmp.getId());
		int targetDetailSize =  details.size();
		int srcDetailSize = queryPeadiatricsResourceDetails.size();
		if(targetDetailSize<srcDetailSize){
			for(int i=targetDetailSize;i<srcDetailSize;i++){
				DoctorClinicTimeSegment doctorClinicTimeSegment = queryPeadiatricsResourceDetails.get(i);
				doctorClinicTimeSegment.setIsDel("Y");
			}
		}else if(targetDetailSize>srcDetailSize){
			for(int i=0,len=(targetDetailSize-srcDetailSize);i<len;i++){
				queryPeadiatricsResourceDetails.add(new DoctorClinicTimeSegment());
			}
		}
		for(int i=0;i<targetDetailSize;i++){
			DoctorClinicTimeSegment doctorClinicTimeSegment = details.get(i);
			doctorClinicTimeSegment.setDoctorMorePractice(query_dmp);
			doctorClinicTimeSegment.setIsDel("N");
			
			DoctorClinicTimeSegment dcts = queryPeadiatricsResourceDetails.get(i);
			BeanUtils.copyProperties(doctorClinicTimeSegment, dcts, new String[]{"id"});
			if(dcts.getId()==null){
				service.add(dcts);
			}else{
				service.edit(dcts);
			}
		}
	}
	/**
	 * 获取儿科资源明细信息
	 * @param mID 主表ID
	 * @return
	 */
	private List<DoctorClinicTimeSegment> queryPeadiatricsResourceDetails(Long mID){
		String hql = "from DoctorClinicTimeSegment c where c.doctorMorePractice.id = ? order by c.id";
		return service.getPersistProxy().getOrmPersistence().findByHQLQuery(hql, new Object[]{mID});
	}
	
	/**
	 * 组装儿科资源
	 * @param resource
	 * @param operaDoctorSchedule
	 * @return
	 */
	private DoctorMorePractice packagePeadiatricsResource(Long resource,OperaDoctorSchedule operaDoctorSchedule){
		DoctorMorePractice doctorMorePractice = new DoctorMorePractice();
		
		doctorMorePractice.setCanClinicNum(resource);
		
		doctorMorePractice.setDoctorInfo(operaDoctorSchedule.getDoctorInfo());
		doctorMorePractice.setClinicDate(operaDoctorSchedule.getOperaBaseSchedule().getOpenDate());
		doctorMorePractice.setClinicBeganTime(operaDoctorSchedule.getWorkBeginTime());
		doctorMorePractice.setClinicEndTime(operaDoctorSchedule.getWorkEndTime());
		
		DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo = 
				this.getDoctorMorePracticeOrgInfo(operaDoctorSchedule.getOperaBaseSchedule().getHospitalBasicInfo().getId());
		
		doctorMorePractice.setClinicOrg(doctorMorePracticeOrgInfo.getOrgName());
		doctorMorePractice.setClinicAddress(doctorMorePracticeOrgInfo.getOrgAddress());
		
		doctorMorePractice.setIsRepeat("N");
		doctorMorePractice.setClinicOrgType("1");
		doctorMorePractice.setOrgClinicStatus("已预约");
		
		String additiveSource = operaDoctorSchedule.getAdditiveSource();
		if(StringUtils.isNotBlank(additiveSource)){
			doctorMorePractice.setIsAddClinic(additiveSource);
		}else{
			doctorMorePractice.setIsAddClinic("0");
		}
		
		doctorMorePractice.setOperationTime(DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(new Date()));
		doctorMorePractice.setDoctorMorePracticeOrgInfo(doctorMorePracticeOrgInfo);
		
		doctorMorePractice.setUserTypeIds(operaDoctorSchedule.getUserType());
		
		return doctorMorePractice;
	}
	
	/**
	 * 计算儿科资源明细
	 * @param operaDoctorSchedule
	 * @return
	 */
	private List<DoctorClinicTimeSegment> calculatePeadiatricsTotalSourceDetail(OperaDoctorSchedule operaDoctorSchedule){
		String openDate = operaDoctorSchedule.getOperaBaseSchedule().getOpenDate();
		List<DoctorClinicTimeSegment> details = null;
		//休息前
		String startTime = openDate+" "+operaDoctorSchedule.getWorkBeginTime();
		String endTime = openDate+" "+operaDoctorSchedule.getRestBeginTime();
		details = calculatePeadiatricsSource(startTime, endTime, operaDoctorSchedule.getTimeSegment());
		//休息后
		startTime = openDate+" "+operaDoctorSchedule.getRestEndTime();
		endTime = openDate+" "+operaDoctorSchedule.getWorkEndTime();
		details.addAll(
				calculatePeadiatricsSource(startTime, endTime, operaDoctorSchedule.getTimeSegment()));
		return details;
	}
	/**
	 * 计算儿科号源
	 * @param startTime
	 * @param endTime
	 * @param segmentTime
	 * @return
	 */
	private List<DoctorClinicTimeSegment> calculatePeadiatricsSource(String startTime,String endTime,String segmentTime){
		List<DoctorClinicTimeSegment> details = new ArrayList<DoctorClinicTimeSegment>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf_seg = new SimpleDateFormat("HH:mm");
		Calendar c = Calendar.getInstance();
		int segmentValue = Integer.parseInt(segmentTime);
		Date tmp_startTime = null;
		Date tmp_endTime = null;
		try {
			tmp_startTime = sdf.parse(startTime);
			tmp_endTime = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		c.setTime(tmp_startTime);
		
		boolean finishFlag = true;
		while(finishFlag){
			if(c.getTimeInMillis()==tmp_endTime.getTime()) break;
			startTime = sdf_seg.format(c.getTime());
			c.add(Calendar.MINUTE, segmentValue);
			if(c.getTimeInMillis()>tmp_endTime.getTime()){
				finishFlag = false;
			};
			details.add(new DoctorClinicTimeSegment(startTime,"Y"));
		}
		
		return details;
	}
}
