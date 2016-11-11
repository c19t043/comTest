package com.kybaby.newbussiness.medicalorgandbusiness.bo.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Position;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.OrganBusinessService;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.OrganBusinessDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgBusinessRelation;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgOpenBusiness;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResourcesDetail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganServicePlaceSet;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;

public class OrganBusinessServiceImpl implements OrganBusinessService{
	private OrganBusinessDao organBusinessDao;

	public OrganBusinessDao getOrganBusinessDao() {
		return organBusinessDao;
	}

	public void setOrganBusinessDao(OrganBusinessDao organBusinessDao) {
		this.organBusinessDao = organBusinessDao;
	}

	@Override
	public List<OrganInoculationOpenResources> getOrganInoculationOpenResourcesList(
			OrganInoculationOpenResources organInoculationOpenResources) {
		return this.organBusinessDao.getOrganInoculationOpenResourcesList(organInoculationOpenResources);
	}

	@Override
	public List<HospitalBasicInfo> getHospitalBasicInfoList(
			HospitalBasicInfo hospitalBasicInfo) {
		return this.organBusinessDao.getHospitalBasicInfoList(hospitalBasicInfo);
	}

	@Override
	public Long saveOrUpdateOrganInoculationOpenResources(OrganInoculationOpenResources organInoculationOpenResources) {
		organInoculationOpenResources.setGeneralSurplusNum(organInoculationOpenResources.getGeneralNum());
		organInoculationOpenResources.setGreenChannelSurplusNum(organInoculationOpenResources.getGreenChannelNum());
		Long id = this.organBusinessDao.saveOrUpdateOrganInoculationOpenResources(organInoculationOpenResources);
		OrganInoculationOpenResources old = new OrganInoculationOpenResources();
		old.setId(id);
		String beginTime = organInoculationOpenResources.getOpenStartTime();
		String endTime = organInoculationOpenResources.getRestStartTime();
		String timeInterval = organInoculationOpenResources.getTimeDivisionValue();
		List<String> timeSegmentList_0 = this.getTimeSegment(beginTime, endTime, Integer.valueOf(timeInterval));
		//第二段时间
		beginTime = organInoculationOpenResources.getRestEndTime();
		endTime = organInoculationOpenResources.getOpenEndTime();
		List<String> timeSegmentList_1 = this.getTimeSegment(beginTime, endTime, Integer.valueOf(timeInterval));
		//得到总段数
		int allSize = 0;
		if(timeSegmentList_0 != null){
			allSize += (timeSegmentList_0.size()-1);
		}
		if(timeSegmentList_1 != null){
			allSize += (timeSegmentList_1.size()-1);
		}
		int generalNum = Integer.valueOf(organInoculationOpenResources.getGeneralNum());
		int greenChannelNum = Integer.valueOf(organInoculationOpenResources.getGreenChannelNum());
		int generalNumPer = generalNum/allSize;
		int generalResidue = generalNum%allSize;//余数（将余数平均分配）
		//int generalNumLast = generalNum%allSize + generalNumPer;
		int greenChannelNumPer = greenChannelNum/allSize;
		int greenResidue = greenChannelNum%allSize;//余数（将余数平均分配）
		//int greenChannelNumLast = greenChannelNum%allSize + greenChannelNumPer;
		//删除原有明细记录
		this.delOldOrganInoculationOpenResourcesDetail(old);
		if(timeSegmentList_0 != null){
			int size = timeSegmentList_0.size();
			int fenduan = size - 1;
			for(int i=0; i<fenduan; i++){
				OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail =
						new OrganInoculationOpenResourcesDetail();
				organInoculationOpenResourcesDetail.setOrganInoculationOpenResources(old);
				organInoculationOpenResourcesDetail.setOpenDate(organInoculationOpenResources.getOpenDate());
				int j = i;
				organInoculationOpenResourcesDetail.setOpenStartTime(timeSegmentList_0.get(j)); 
				organInoculationOpenResourcesDetail.setOpenEndTime(timeSegmentList_0.get(j+1)); 
				//处理普通通道
				if(generalResidue > 0){
					organInoculationOpenResourcesDetail.setGeneralSurplusNum(String.valueOf(generalNumPer+1));
					organInoculationOpenResourcesDetail.setGeneralNum(String.valueOf(generalNumPer+1));
					generalResidue--;
				}else{
					organInoculationOpenResourcesDetail.setGeneralSurplusNum(String.valueOf(generalNumPer));
					organInoculationOpenResourcesDetail.setGeneralNum(String.valueOf(generalNumPer));
				}
				//处理绿色通道
				if(greenResidue > 0){
					organInoculationOpenResourcesDetail.setGreenChannelNum(String.valueOf(greenChannelNumPer+1));
					organInoculationOpenResourcesDetail.setGreenChannelSurplusNum(String.valueOf(greenChannelNumPer+1));
					greenResidue--;
				}else{
					organInoculationOpenResourcesDetail.setGreenChannelNum(String.valueOf(greenChannelNumPer));
					organInoculationOpenResourcesDetail.setGreenChannelSurplusNum(String.valueOf(greenChannelNumPer));
				}
				this.saveOrUpdateOrganInoculationOpenResourcesDetail(organInoculationOpenResourcesDetail);
			}
		}
		if(timeSegmentList_1 != null){
			int size = timeSegmentList_1.size();
			int fenduan = size - 1;
			for(int i=0; i<fenduan; i++){
				OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail =
						new OrganInoculationOpenResourcesDetail();
				organInoculationOpenResourcesDetail.setOrganInoculationOpenResources(old);
				organInoculationOpenResourcesDetail.setOpenDate(organInoculationOpenResources.getOpenDate());
				int j = i;
				organInoculationOpenResourcesDetail.setOpenStartTime(timeSegmentList_1.get(j)); 
				organInoculationOpenResourcesDetail.setOpenEndTime(timeSegmentList_1.get(j+1)); 
				//处理普通通道
				if(generalResidue > 0){
					organInoculationOpenResourcesDetail.setGeneralSurplusNum(String.valueOf(generalNumPer+1));
					organInoculationOpenResourcesDetail.setGeneralNum(String.valueOf(generalNumPer+1));
					generalResidue--;
				}else{
					organInoculationOpenResourcesDetail.setGeneralSurplusNum(String.valueOf(generalNumPer));
					organInoculationOpenResourcesDetail.setGeneralNum(String.valueOf(generalNumPer));
				}
				//处理绿色通道
				if(greenResidue > 0){
					organInoculationOpenResourcesDetail.setGreenChannelNum(String.valueOf(greenChannelNumPer+1));
					organInoculationOpenResourcesDetail.setGreenChannelSurplusNum(String.valueOf(greenChannelNumPer+1));
					greenResidue--;
				}else{
					organInoculationOpenResourcesDetail.setGreenChannelNum(String.valueOf(greenChannelNumPer));
					organInoculationOpenResourcesDetail.setGreenChannelSurplusNum(String.valueOf(greenChannelNumPer));
				}
				this.saveOrUpdateOrganInoculationOpenResourcesDetail(organInoculationOpenResourcesDetail);
			}
		}
		return id;
	}

	@Override
	public Long saveOrUpdateOrganInoculationOpenResourcesDetail(
			OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail) {
		return  this.organBusinessDao.saveOrUpdateOrganInoculationOpenResourcesDetail(organInoculationOpenResourcesDetail);
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
                       System.out.println(currentPrintDay.compareTo(endDay));
                       if (currentPrintDay.compareTo(endDay) >= 0) {  
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
        timeSegmentList.add(endTime);
        if(timeSegmentList.isEmpty()){
        	return null;
        }
        System.out.println(timeSegmentList.toString());
		return timeSegmentList;
	}

	@Override
	public List<OrganInoculationOpenResourcesDetail> getOrganInoculationOpenResourcesDetailList(
			OrganInoculationOpenResources organInoculationOpenResources) {
		return this.organBusinessDao.getOrganInoculationOpenResourcesDetailList(organInoculationOpenResources);
	}

	@Override
	public void delOldOrganInoculationOpenResourcesDetail(
			OrganInoculationOpenResources organInoculationOpenResources) {
		this.organBusinessDao.delOldOrganInoculationOpenResourcesDetail(organInoculationOpenResources);
	}

	@Override
	public Long saveOrUpdateOrganChildcareOpenResources(
		OrganChildcareOpenResources organChildcareOpenResources) {
		organChildcareOpenResources.setGeneralSurplusNum(organChildcareOpenResources.getGeneralNum());
		organChildcareOpenResources.setGreenChannelSurplusNum(organChildcareOpenResources.getGreenChannelNum());
		if("时间段".equals(organChildcareOpenResources.getTimeDivisionNeed())){
			organChildcareOpenResources.setTimeDivisionType(null);
		}else if("时间点".equals(organChildcareOpenResources.getTimeDivisionNeed())){
			organChildcareOpenResources.setGeneralNum(null);
			organChildcareOpenResources.setGreenChannelNum(null);
			organChildcareOpenResources.setGeneralSurplusNum(null);
			organChildcareOpenResources.setGreenChannelSurplusNum(null);
		}
		Long id = this.organBusinessDao.saveOrUpdateOrganChildcareOpenResources(organChildcareOpenResources);
		OrganChildcareOpenResources old = new OrganChildcareOpenResources();
		old.setId(id);
		String beginTime = organChildcareOpenResources.getOpenStartTime();
		String endTime = organChildcareOpenResources.getRestStartTime();
		String timeInterval = organChildcareOpenResources.getTimeDivisionValue();
		List<String> timeSegmentList_0 = this.getTimeSegment(beginTime, endTime, Integer.valueOf(timeInterval));
		//第二段时间
		beginTime = organChildcareOpenResources.getRestEndTime();
		endTime = organChildcareOpenResources.getOpenEndTime();
		List<String> timeSegmentList_1 = this.getTimeSegment(beginTime, endTime, Integer.valueOf(timeInterval));
		
		if("时间段".equals(organChildcareOpenResources.getTimeDivisionNeed())){
			//得到总段数
			int allSize = 0;
			if(timeSegmentList_0 != null){
				allSize += (timeSegmentList_0.size()-1);
			}
			if(timeSegmentList_1 != null){
				allSize += (timeSegmentList_1.size()-1);
			}
			int generalNum = Integer.valueOf(organChildcareOpenResources.getGeneralNum());
			int greenChannelNum = Integer.valueOf(organChildcareOpenResources.getGreenChannelNum());
			int generalNumPer = generalNum/allSize;
			int generalResidue = generalNum%allSize;//余数（将余数平均分配）
			//int generalNumLast = generalNum%allSize + generalNumPer;
			int greenChannelNumPer = greenChannelNum/allSize;
			int greenResidue = greenChannelNum%allSize;//余数（将余数平均分配）
			//int greenChannelNumLast = greenChannelNum%allSize + greenChannelNumPer;
			//删除原有明细记录
			this.delOldOrganChildcareOpenResourcesDatail(old);
			if(timeSegmentList_0 != null){
				int size = timeSegmentList_0.size();
				int fenduan = size - 1;
				for(int i=0; i<fenduan; i++){
					OrganChildcareOpenResourcesDatail organChildcareOpenResourcesDatail =
							new OrganChildcareOpenResourcesDatail();
					organChildcareOpenResourcesDatail.setOrganChildcareOpenResources(old);
					int j = i;
					organChildcareOpenResourcesDatail.setOpenStartTime(timeSegmentList_0.get(j)); 
					organChildcareOpenResourcesDatail.setOpenEndTime(timeSegmentList_0.get(j+1)); 
					//处理普通通道
					if(generalResidue > 0){
						organChildcareOpenResourcesDatail.setGeneralSurplusNum(String.valueOf(generalNumPer+1));
						organChildcareOpenResourcesDatail.setGeneralNum(String.valueOf(generalNumPer+1));
						generalResidue--;
					}else{
						organChildcareOpenResourcesDatail.setGeneralSurplusNum(String.valueOf(generalNumPer));
						organChildcareOpenResourcesDatail.setGeneralNum(String.valueOf(generalNumPer));
					}
					//处理绿色通道
					if(greenResidue > 0){
						organChildcareOpenResourcesDatail.setGreenChannelNum(String.valueOf(greenChannelNumPer+1));
						organChildcareOpenResourcesDatail.setGreenChannelSurplusNum(String.valueOf(greenChannelNumPer+1));
						greenResidue--;
					}else{
						organChildcareOpenResourcesDatail.setGreenChannelNum(String.valueOf(greenChannelNumPer));
						organChildcareOpenResourcesDatail.setGreenChannelSurplusNum(String.valueOf(greenChannelNumPer));
					}
					this.saveOrUpdateOrganChildcareOpenResourcesDatail(organChildcareOpenResourcesDatail);
				}
			}
			if(timeSegmentList_1 != null){
				int size = timeSegmentList_1.size();
				int fenduan = size - 1;
				for(int i=0; i<fenduan; i++){
					OrganChildcareOpenResourcesDatail organChildcareOpenResourcesDatail =
							new OrganChildcareOpenResourcesDatail();
					organChildcareOpenResourcesDatail.setOrganChildcareOpenResources(old);
					int j = i;
					organChildcareOpenResourcesDatail.setOpenStartTime(timeSegmentList_1.get(j)); 
					organChildcareOpenResourcesDatail.setOpenEndTime(timeSegmentList_1.get(j+1)); 
					//处理普通通道
					if(generalResidue > 0){
						organChildcareOpenResourcesDatail.setGeneralSurplusNum(String.valueOf(generalNumPer+1));
						organChildcareOpenResourcesDatail.setGeneralNum(String.valueOf(generalNumPer+1));
						generalResidue--;
					}else{
						organChildcareOpenResourcesDatail.setGeneralSurplusNum(String.valueOf(generalNumPer));
						organChildcareOpenResourcesDatail.setGeneralNum(String.valueOf(generalNumPer));
					}
					//处理绿色通道
					if(greenResidue > 0){
						organChildcareOpenResourcesDatail.setGreenChannelNum(String.valueOf(greenChannelNumPer+1));
						organChildcareOpenResourcesDatail.setGreenChannelSurplusNum(String.valueOf(greenChannelNumPer+1));
						greenResidue--;
					}else{
						organChildcareOpenResourcesDatail.setGreenChannelNum(String.valueOf(greenChannelNumPer));
						organChildcareOpenResourcesDatail.setGreenChannelSurplusNum(String.valueOf(greenChannelNumPer));
					}
					this.saveOrUpdateOrganChildcareOpenResourcesDatail(organChildcareOpenResourcesDatail);
				}
			}
		}else if("时间点".equals(organChildcareOpenResources.getTimeDivisionNeed())){
			//删除原有明细记录
			this.delOldOrganChildcareOpenResourcesDatail(old);
			if(timeSegmentList_0 != null && timeSegmentList_1 != null){
				for(int i=0; i<timeSegmentList_1.size(); i++){
					timeSegmentList_0.add(timeSegmentList_1.get(i));
				}
				for(int i=0; i<timeSegmentList_0.size(); i++){
					OrganChildcareOpenResourcesDatail organChildcareOpenResourcesDatail =
							new OrganChildcareOpenResourcesDatail();
					organChildcareOpenResourcesDatail.setOrganChildcareOpenResources(old);
					organChildcareOpenResourcesDatail.setSegment(timeSegmentList_0.get(i));
					organChildcareOpenResourcesDatail.setIsCanUse("Y");
					this.saveOrUpdateOrganChildcareOpenResourcesDatail(organChildcareOpenResourcesDatail);
				}
			}
		}
		return id;
	}

	@Override
	public Long saveOrUpdateOrganChildcareOpenResourcesDatail(
			OrganChildcareOpenResourcesDatail organChildcareOpenResourcesDatail) {
		return this.organBusinessDao.saveOrUpdateOrganChildcareOpenResourcesDatail(organChildcareOpenResourcesDatail);
	}

	@Override
	public void delOldOrganChildcareOpenResourcesDatail(
			OrganChildcareOpenResources organChildcareOpenResources) {
		this.organBusinessDao.delOldOrganChildcareOpenResourcesDatail(organChildcareOpenResources);
	}

	@Override
	public List<OrganChildcareOpenResources> getOrganChildcareOpenResourcesList(
			OrganChildcareOpenResources organChildcareOpenResources) {
		return this.organBusinessDao.getOrganChildcareOpenResourcesList(organChildcareOpenResources);
	}

	@Override
	public List<OrganChildcareOpenResourcesDatail> getOrganChildcareOpenResourcesDatailList(
			OrganChildcareOpenResources organChildcareOpenResources) {
		return this.organBusinessDao.getOrganChildcareOpenResourcesDatailList(organChildcareOpenResources);
	}

	@Override
	public List<OrganServicePlaceSet> getOrganServicePlaceSetList(
			OrganServicePlaceSet organServicePlaceSet) {
		return this.organBusinessDao.getOrganServicePlaceSetList(organServicePlaceSet);
	}

	@Override
	public Long saveOrupdateOrganServicePlaceSet(
			OrganServicePlaceSet organServicePlaceSet) {
		return organBusinessDao.saveOrupdateOrganServicePlaceSet(organServicePlaceSet);
	}

	@Override
	public List<OrgBusinessRelation> getOrgBusinessRelationList(
			HospitalBasicInfo hospitalBasicInfo, OrgOpenBusiness orgOpenBusiness) {
		return organBusinessDao.getOrgBusinessRelationList(hospitalBasicInfo, orgOpenBusiness);
	}

	@Override
	public List<OrgOpenBusiness> getOrgOpenBusinessList(
			OrgOpenBusiness orgOpenBusiness) {
		return organBusinessDao.getOrgOpenBusinessList(orgOpenBusiness);
	}

	@Override
	public void saveOrupdateOrgBusinessRelation(
			List openBusinessId,Long orgId) {
		//删除原有明细记录
		this.organBusinessDao.delOldOrgBusinessRelationByOrgId(orgId);
		if(openBusinessId != null){
			for(int i=0; i<openBusinessId.size(); i++){
				OrgBusinessRelation orgBusinessRelation =	new OrgBusinessRelation();
				OrgOpenBusiness orgOpenBusiness = new OrgOpenBusiness();
				orgOpenBusiness.setId(Long.valueOf(openBusinessId.get(i).toString()));
				HospitalBasicInfo hospitalBasicInfo = new HospitalBasicInfo();
				hospitalBasicInfo.setId(orgId);
				orgBusinessRelation.setHospitalBasicInfo(hospitalBasicInfo);
				orgBusinessRelation.setOrgOpenBusiness(orgOpenBusiness);
				this.organBusinessDao.saveOrupdateOrgBusinessRelation(orgBusinessRelation);
			}
		}
	}

	@Override
	public Long saveOrupdateOrgOpenBusiness(OrgOpenBusiness orgOpenBusiness) {
		orgOpenBusiness.setOperatTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
		return organBusinessDao.saveOrupdateOrgOpenBusiness(orgOpenBusiness);
	}

	@Override
	public OrganChildcareOpenResources getOrganChildcareOpenResourcesById(
			Long id) {
		return organBusinessDao.getOrganChildcareOpenResourcesById(id);
	}
	/**
	 * 修改儿保明细剩余资源
	 * @param updateChildCareDetail
	 * @return
	 */
	public boolean updateChildCareDetailData(
			OrganChildcareOpenResourcesDatail updateChildCareDetail){
		//1.根据id查询儿保明细数据是否存在
		OrganChildcareOpenResourcesDatail detail = organBusinessDao.findChildCareDetail(updateChildCareDetail.getId());
		//2.如果存在则修改
		if(detail!=null){
			detail.setGeneralSurplusNum(updateChildCareDetail.getGeneralSurplusNum());
			detail.setGreenChannelSurplusNum(updateChildCareDetail.getGreenChannelSurplusNum());
			organBusinessDao.updateChildCareDetail(detail);
			return true;
		}
		//3.失败返回false
		return false;
	}
	/**
	 * 修改儿保资源医生记录
	 * @param doctorID 原儿保医生ID
	 * @param organChildcareOpenResources
	 * @return
	 */
	public boolean updateChildCareData(
			OrganChildcareOpenResources organChildcareOpenResources){
		Long resourceID = organChildcareOpenResources.getId();
		OrganChildcareOpenResources openresource = organBusinessDao.getOrganChildcareOpenResourcesById(resourceID);
		
		Long doctorid = 0L;
		Long old_doctorID = 0L;//原资源表医生ID
		Long HPId = organChildcareOpenResources.getHospitalPositionId();
		try{
			doctorid = Long.parseLong(organChildcareOpenResources.getDoctorIds());
			old_doctorID = Long.parseLong(openresource.getDoctorIds());
		}catch(Exception e){
			return false;
		}
		Long old_HpID = this.findHospitalPosition(openresource.getHospitalBasicInfo().getId(), old_doctorID, "1").getId();
		
		/*
		 * 1.修改儿保资源记录
		 * 2.如果儿保明细资源有预约，修改订单中医生id
		 * 3.添加医生薪酬记录
		 */
		//如果儿保明细资源有预约，修改订单中医生id
		
		List<UserChildcareAppointmentInfo> childcareOrder = this.findChildCareOrder(resourceID);
		if(!childcareOrder.isEmpty()){
			for(UserChildcareAppointmentInfo order : childcareOrder){
				organBusinessDao.updateChildCareOrderByBatch(order.getId(), doctorid);
			}
		}

		//修改儿保资源记录
		OrganChildcareOpenResources res = organBusinessDao.findChildCare(organChildcareOpenResources.getId());
		if(res==null){ return false;}
		res.setDoctorIds(organChildcareOpenResources.getDoctorIds());
		organBusinessDao.updateChildCare(res);
		//修改儿保医生薪酬记录，医生信息
		/*
		 * 添加医生薪酬记录
		 * 1.根据医院职称分成id，医生id,资源开放时间，查找医生薪酬记录
		 * 2.没有记录，添加记录
		 * 3.如果有记录，不做修改
		 */
		//根据社区医院,工作时间查找薪酬记录
		/*
		 * 新增代码
		 * 修改医生薪酬记录
		 * 查询原有医生薪酬记录,将要修改的医生保底薪酬更新到原有记录中
		 */
		DoctorMoneyRecord doctorMoneyRecord = 
				organBusinessDao.findDoctorMoneyRecord(old_HpID,old_doctorID,res.getOpenDate());
		/*DoctorMoneyRecord doctorMoneyRecord = 
				organBusinessDao.findDoctorMoneyRecord(HPId,doctorid,res.getOpenDate());*/
		//根据医院职称分成id,查找医院职称分成记录
		HospitalPosition hospitalPosition =  organBusinessDao.findHospitalPositionById(HPId);
		if(doctorMoneyRecord==null) {
			/*
			 * 添加医生薪酬记录
			 * 1.根据医院职称分成id,查找医院职称分成记录
			 * 2.组装医生薪酬记录
			 * 3.添加记录
			 */
			//组装医生薪酬记录
			DoctorMoneyRecord record = PackageDoctorMoneyRecord(hospitalPosition,doctorid,res);
			//添加记录
			organBusinessDao.addDoctorMoneyRecord(record);
		}else{
			doctorMoneyRecord.setHospitalPosition(hospitalPosition);
			doctorMoneyRecord.setDoctorInfo(organBusinessDao.findHospitalIdWithDoctor(doctorid));
			
			doctorMoneyRecord.setMoney(hospitalPosition.getBaseSalary());
			doctorMoneyRecord.setMoneyPer(hospitalPosition.getClinicMoneyOut());
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			doctorMoneyRecord.setOperateTime(df.format(new Date()));
		}
		return true;
	}
	
	private DoctorMoneyRecord PackageDoctorMoneyRecord(HospitalPosition hospitalPosition,Long doctorid,OrganChildcareOpenResources res){
		DoctorMoneyRecord record = new DoctorMoneyRecord();
		
/*		HospitalPosition hospitalPosition_new = new HospitalPosition();
		hospitalPosition_new.setId(hospitalPosition.getId());*/
		record.setHospitalPosition(hospitalPosition);
		
		DoctorInfo doctorInfo = new DoctorInfo();
		doctorInfo.setId(doctorid);
		record.setDoctorInfo(doctorInfo);
		
		record.setWorkDate(res.getOpenDate());
		record.setWorkStartTime(res.getOpenStartTime());
		record.setWorkEndTime(res.getOpenEndTime());
		
		record.setMoney(hospitalPosition.getBaseSalary());
		record.setMoneyPer(hospitalPosition.getClinicMoneyOut());
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setOperateTime(df.format(new Date()));
		
		return record;
	}
	
	/**
	 * 根据儿保资源id,查找是否存在儿保订单
	 * @param id
	 * @return
	 */
	public List<UserChildcareAppointmentInfo> findChildCareOrder(Long id){
		return organBusinessDao.findChildCareOrder(id);
	}
	/**
	 * 根据社区机构id,医生Id，查找医院职称分成记录
	 * @param orgId 社区机构id
	 * @param doctorId 医生Id
	 * @param businessType 业务类型
	 * @return
	 */
	public HospitalPosition findHospitalPosition(Long orgId,Long doctorId,String businessType){
		if(orgId==null||doctorId==null||businessType==null) return null;
		//查找医生的职称信息
		Position position = organBusinessDao.findPosition(doctorId);
		//医生所在医院id
	    DoctorInfo doctorInfo = organBusinessDao.findHospitalIdWithDoctor(doctorId);
		HospitalPosition hospitalPosition = organBusinessDao.findHospitalPosition(doctorInfo.getHospitalId(), position.getId(), orgId, businessType);
		if(hospitalPosition==null) return null;
		return hospitalPosition;
	}
}
