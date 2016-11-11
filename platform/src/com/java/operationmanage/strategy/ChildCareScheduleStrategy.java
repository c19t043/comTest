package com.java.operationmanage.strategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.java.familydoctor.archivesinfo.vo.UserType;
import com.java.medicalorgandbusiness.vo.OrganChildcareOpenResources;
import com.java.medicalorgandbusiness.vo.OrganChildcareOpenResourcesDatail;
import com.java.medicalorgandbusiness.vo.UserChildcareAppointmentInfo;
import com.java.operationmanage.common.BooleanMsg;
import com.java.operationmanage.common.CBSMConstant;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.operationmanage.vo.DoctorMoneyRecord;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.operationmanage.vo.HospitalPosition;
import com.java.operationmanage.vo.OperaDoctorSchedule;
import com.java.operationmanage.vo.OrganChildcareOpenDoctor;
import com.java.platform.user.service.ServiceImpl;
import com.java.util.DateManage;

public class ChildCareScheduleStrategy extends ScheduleStrategy {

	public ChildCareScheduleStrategy(ServiceImpl service) {
		super(service);
	}
	@Override
	public BooleanMsg isPassWithPublishCondition(OperaDoctorSchedule operaDoctorSchedule) {
		BooleanMsg booleanMsg = this.validateScheduleConditionIsOK(operaDoctorSchedule);
		Long srcID = operaDoctorSchedule.getSrcID();
		if(srcID!=null){
			String hql = "from UserChildcareAppointmentInfo c where c.organChildcareOpenResources.id = ?";
			List<UserChildcareAppointmentInfo> infos = 
					service.getPersistProxy().getOrmPersistence().findByHQLQuery(hql, new Object[]{srcID});
			if(!infos.isEmpty()){
				booleanMsg.isTrue(false);
				booleanMsg.setMsg("该排班已预约了"+infos.size()+"次,不能修改");
			}
		}
		return booleanMsg;
	}
	@Override
	public Long addSchedule(OperaDoctorSchedule operaDoctorSchedule) {
		Long resID = null;
		/*
		 * 计算资源明细
		 */
		List<OrganChildcareOpenResourcesDatail> details = calculateChildCareTotalSourceDetail(operaDoctorSchedule);
		/*
		 * 组装儿保资源数据
		 */
		OrganChildcareOpenResources childcareOpenResources = packageChildCareResource(details.size()+"", operaDoctorSchedule);
		
		//添加资源
		resID =(Long) service.add(childcareOpenResources);
		//添加资源明细
		for(OrganChildcareOpenResourcesDatail detail : details){
			detail.setOrganChildcareOpenResources(childcareOpenResources);
			service.add(detail);
		}
		//添加医生薪酬记录
		addDoctorMoneyRecord(operaDoctorSchedule,childcareOpenResources);
		//添加医生上班记录
		addChildCareOpenDoctor(operaDoctorSchedule, childcareOpenResources);
		return resID;
	}
	@Override
	public Long updateSchedule(OperaDoctorSchedule operaDoctorSchedule) {
		Long srcID = operaDoctorSchedule.getSrcID();
		/*
		 * 计算资源明细
		 */
		List<OrganChildcareOpenResourcesDatail> details = calculateChildCareTotalSourceDetail(operaDoctorSchedule);
		/*
		 * 组装儿保资源数据
		 */
		OrganChildcareOpenResources childcareOpenResources = packageChildCareResource(details.size()+"", operaDoctorSchedule);
		/*
		 * 查询原有资源信息
		 */
		OrganChildcareOpenResources query_opensrc = service.get(srcID, OrganChildcareOpenResources.class);
		OrganChildcareOpenResources tmp_src = new OrganChildcareOpenResources();
		BeanUtils.copyProperties(query_opensrc, tmp_src);
		/*
		 * 修改儿保资源
		 */
		BeanUtils.copyProperties(childcareOpenResources, query_opensrc, new String[]{"id"});
		service.edit(query_opensrc);
		/*
		 * 修改医生薪酬记录
		 */
		updateDoctorMoneyRecord(tmp_src, operaDoctorSchedule,query_opensrc);
		/*
		 * 修改医生上班记录
		 */
		updateChildCareOpenDoctor(tmp_src, operaDoctorSchedule, query_opensrc);
		/*
		 * 修改儿保资源明细
		 */
		updateChildCareResourceDetails(details, query_opensrc);
		return srcID;
	}
	/**
	 * 修改儿保资源明细
	 * @param details
	 * @param query_opensrc
	 */
	private void updateChildCareResourceDetails(List<OrganChildcareOpenResourcesDatail> details,OrganChildcareOpenResources query_opensrc){
		/*
		 * 修改明细表
		 * 保存现有明细和原有明细个数一致
		 * 1.如果新增加的明细数量<原有明细数量:原有明细多出的明细isDel设置为Y
		 * 2.如果新增加的明细数量>原有明细数量:原有明细增加多出个数的明细对象
		 */
		List<OrganChildcareOpenResourcesDatail> queryChildcareOpenResourcesDatails =
				queryChildcareOpenResourcesDatails(query_opensrc.getId());
		int targetDetailSize =  details.size();
		int srcDetailSize = queryChildcareOpenResourcesDatails.size();
		if(targetDetailSize<srcDetailSize){
			for(int i=targetDetailSize;i<srcDetailSize;i++){
				OrganChildcareOpenResourcesDatail doctorClinicTimeSegment = queryChildcareOpenResourcesDatails.get(i);
				doctorClinicTimeSegment.setIsDel("Y");
			}
		}else if(targetDetailSize>srcDetailSize){
			for(int i=0,len=(targetDetailSize-srcDetailSize);i<len;i++){
				queryChildcareOpenResourcesDatails.add(new OrganChildcareOpenResourcesDatail());
			}
		}
		for(int i=0;i<targetDetailSize;i++){
			OrganChildcareOpenResourcesDatail organChildcareOpenResourcesDatail = details.get(i);
			organChildcareOpenResourcesDatail.setOrganChildcareOpenResources(query_opensrc);
			organChildcareOpenResourcesDatail.setIsDel("N");
			
			OrganChildcareOpenResourcesDatail childCareSrcDetail = queryChildcareOpenResourcesDatails.get(i);
			BeanUtils.copyProperties(organChildcareOpenResourcesDatail, childCareSrcDetail, new String[]{"id"});
			if(childCareSrcDetail.getId()==null){
				service.add(childCareSrcDetail);
			}else{
				service.edit(childCareSrcDetail);
			}
		}
	}
	private List<OrganChildcareOpenResourcesDatail> queryChildcareOpenResourcesDatails(Long resID){
		String hql  = "from OrganChildcareOpenResourcesDatail c where c.organChildcareOpenResources.id = ?";
		return service.getPersistProxy().getOrmPersistence().findByHQLQuery(hql, new Object[]{resID});
	}
	/**
	 * 修改医生薪酬记录
	 * @param query_opensrc 原有儿保资源
	 * @param operaDoctorSchedule 医生排班记录
	 * @param childcareOpenResources 组装的儿保资源
	 */
	private void updateDoctorMoneyRecord(OrganChildcareOpenResources query_opensrc,
			OperaDoctorSchedule operaDoctorSchedule,
			OrganChildcareOpenResources childcareOpenResources) {
		HospitalBasicInfo org = operaDoctorSchedule.getOperaBaseSchedule().getHospitalBasicInfo();
		/*
		 * 查找原有薪酬记录
		 */
		Long doctorID = Long.parseLong(query_opensrc.getDoctorIds());
		DoctorInfo doctorInfo = service.get(doctorID, DoctorInfo.class);
		HospitalPosition src_hp = this.findHospitalPosition(doctorInfo,org,CBSMConstant.BUSINESSTYPE_CHILDCARE); 
		DoctorMoneyRecord src_dmr = findDoctorMoneyRecord(src_hp.getId(),doctorID,query_opensrc.getOpenDate());
		/*
		 * 现有薪酬记录对象
		 */
		doctorInfo = operaDoctorSchedule.getDoctorInfo();
		HospitalPosition hp = this.findHospitalPosition(doctorInfo,org,CBSMConstant.BUSINESSTYPE_CHILDCARE);
		UserType userType = service.get(Long.parseLong(operaDoctorSchedule.getUserType()), UserType.class);
		DoctorMoneyRecord dmr = new DoctorMoneyRecord(hp,operaDoctorSchedule.getDoctorInfo(),
				childcareOpenResources.getOpenDate(),childcareOpenResources.getOpenStartTime(),childcareOpenResources.getOpenEndTime());
		dmr.setUserType(userType);
		dmr.setMoney(hp.getBaseSalary());
		dmr.setMoneyPer(hp.getClinicMoneyOut());
		dmr.setOperateTime(DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(new Date()));
		if(src_dmr==null){
			service.add(dmr);
		}
		else{
			BeanUtils.copyProperties(dmr, src_dmr, new String[]{"id"});
			service.edit(src_dmr);
		}
	}
	/**
	 * 查询医生薪酬记录
	 * @param hPId 医院职称分成id
	 * @param doctorid 医生id
	 * @param openDate 资源开放时间
	 * @return
	 */
	private DoctorMoneyRecord findDoctorMoneyRecord(Long hPId, Long doctorid,
			String openDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" from DoctorMoneyRecord where hospitalPosition.id = ? ");
		sb.append(" and doctorInfo.id = ?");
		sb.append(" and workDate = ?");
		return service.getPersistProxy().getOrmPersistence().findObjectByHQL(sb.toString(), new Object[]{hPId,doctorid,openDate});
	}
	/**
	 * 修改医生上班记录
	 * @param query_opensrc 原有资源
	 * @param operaDoctorSchedule 医生排班记录
	 * @param childcareOpenResources 组装的儿保资源
	 */
	private void updateChildCareOpenDoctor(OrganChildcareOpenResources query_opensrc,
			OperaDoctorSchedule operaDoctorSchedule,
			OrganChildcareOpenResources childcareOpenResources) {
		String hql = "from OrganChildcareOpenDoctor c where c.organChildcareOpenResources.id = ? and c.doctorInfo.id = ?";
		
		OrganChildcareOpenDoctor ocod = service.getPersistProxy().getOrmPersistence().findObjectByHQL(hql, 
				new Object[]{query_opensrc.getId(),Long.parseLong(query_opensrc.getDoctorIds())});
		
		ocod.setOrganChildcareOpenResources(childcareOpenResources);
		ocod.setDoctorInfo(operaDoctorSchedule.getDoctorInfo());
		ocod.setOperateTime(DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(new Date()));
		service.edit(ocod);
	}
	/**
	 * 添加医生上班记录
	 * @param operaDoctorSchedule 医生排班记录
	 * @param childcareOpenResources 组装的儿保资源
	 */
	private void addChildCareOpenDoctor(
			OperaDoctorSchedule operaDoctorSchedule,
			OrganChildcareOpenResources childcareOpenResources) {
		OrganChildcareOpenDoctor ocod = new OrganChildcareOpenDoctor();
		ocod.setOrganChildcareOpenResources(childcareOpenResources);
		ocod.setDoctorInfo(operaDoctorSchedule.getDoctorInfo());
		ocod.setWorkStatus("准备中");
		ocod.setOperateTime(DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(new Date()));
		service.add(ocod);
	}
	/**
	 * 添加医生薪酬记录
	 * @param operaDoctorSchedule 医生排班记录
	 * @param booleanMsg 验证是否可以发布方法返回值
	 * @param childcareOpenResources 组装的儿保资源
	 */
	private void addDoctorMoneyRecord(OperaDoctorSchedule operaDoctorSchedule,
			OrganChildcareOpenResources childcareOpenResources) {
		DoctorInfo doctorInfo = operaDoctorSchedule.getDoctorInfo();
		HospitalBasicInfo hospitalBasicInfo = operaDoctorSchedule.getOperaBaseSchedule().getHospitalBasicInfo();
		HospitalPosition hp = findHospitalPosition(doctorInfo, hospitalBasicInfo, CBSMConstant.BUSINESSTYPE_CHILDCARE);
		
		UserType userType = service.get(Long.parseLong(operaDoctorSchedule.getUserType()), UserType.class);
		DoctorMoneyRecord dmr = new DoctorMoneyRecord(hp,doctorInfo,
				childcareOpenResources.getOpenDate(),childcareOpenResources.getOpenStartTime(),childcareOpenResources.getOpenEndTime());
		dmr.setUserType(userType);
		dmr.setMoney(hp.getBaseSalary());
		dmr.setMoneyPer(hp.getClinicMoneyOut());
		dmr.setOperateTime(DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(new Date()));
		service.add(dmr);
	}
	/**
	 * 组装儿保资源
	 * @param resource
	 * @param operaDoctorSchedule
	 * @return
	 */
	private OrganChildcareOpenResources packageChildCareResource(String resource,OperaDoctorSchedule operaDoctorSchedule){
		OrganChildcareOpenResources childcareOpenResources = new OrganChildcareOpenResources();
		childcareOpenResources.setGeneralNum(resource);
		childcareOpenResources.setGreenChannelNum(resource);
		childcareOpenResources.setGeneralSurplusNum(resource);
		childcareOpenResources.setGreenChannelSurplusNum(resource);
		
		childcareOpenResources.setHospitalBasicInfo(operaDoctorSchedule.getOperaBaseSchedule().getHospitalBasicInfo());
		childcareOpenResources.setOpenDate(operaDoctorSchedule.getOperaBaseSchedule().getOpenDate());
		
		childcareOpenResources.setOpenStartTime(operaDoctorSchedule.getWorkBeginTime());
		childcareOpenResources.setOpenEndTime(operaDoctorSchedule.getWorkEndTime());
		childcareOpenResources.setRestStartTime(operaDoctorSchedule.getRestBeginTime());
		childcareOpenResources.setRestEndTime(operaDoctorSchedule.getRestEndTime());
		
		childcareOpenResources.setTimeDivisionValue(operaDoctorSchedule.getTimeSegment());
		childcareOpenResources.setIsAvailable("Y");
		childcareOpenResources.setTimeDivisionNeed("时间段");
		childcareOpenResources.setIsMoney("Y");
		
		childcareOpenResources.setDoctorIds(operaDoctorSchedule.getDoctorInfo().getId()+"");
		childcareOpenResources.setIsUseDeadline(operaDoctorSchedule.getIsDeadLine());
		if("Y".equals(operaDoctorSchedule.getIsDeadLine())){
			childcareOpenResources.setDeadline(operaDoctorSchedule.getDeadLine());
		}
		return childcareOpenResources;
	}
	/**
	 * 计算儿保号源
	 */
	private List<OrganChildcareOpenResourcesDatail> calculateChildCareSource(String startTime,String endTime,String segmentTime,String source){
		List<OrganChildcareOpenResourcesDatail> details = new ArrayList<OrganChildcareOpenResourcesDatail>();
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
			endTime = sdf_seg.format(c.getTime());
			if(c.getTimeInMillis()>tmp_endTime.getTime()){
				finishFlag = false;
				endTime = sdf_seg.format(tmp_endTime.getTime());
			};
			details.add(new OrganChildcareOpenResourcesDatail(startTime,endTime,source,source,source,source));
		}
		
		return details;
	}
	/**
	 * 计算儿保资源明细
	 * @param operaDoctorSchedule
	 * @return
	 */
	private List<OrganChildcareOpenResourcesDatail> calculateChildCareTotalSourceDetail(OperaDoctorSchedule operaDoctorSchedule){
		String openDate = operaDoctorSchedule.getOperaBaseSchedule().getOpenDate();
		List<OrganChildcareOpenResourcesDatail> details = null;
		//休息前
		String startTime = openDate+" "+operaDoctorSchedule.getWorkBeginTime();
		String endTime = openDate+" "+operaDoctorSchedule.getRestBeginTime();
		details = calculateChildCareSource(startTime, endTime, operaDoctorSchedule.getTimeSegment(),operaDoctorSchedule.getSegmentationSourse());
		//休息后
		startTime = openDate+" "+operaDoctorSchedule.getRestEndTime();
		endTime = openDate+" "+operaDoctorSchedule.getWorkEndTime();
		details.addAll(
				calculateChildCareSource(startTime, endTime, operaDoctorSchedule.getTimeSegment(),operaDoctorSchedule.getSegmentationSourse()));
		return details;
	}
}
