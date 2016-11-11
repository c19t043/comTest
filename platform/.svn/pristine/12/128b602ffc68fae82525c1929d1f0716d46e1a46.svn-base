package com.java.publichealth.neonatalvisit.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.Service;
import com.java.publichealth.childhealth.vo.PhChildHealthExaminationRecord;
import com.java.publichealth.neonatalvisit.vo.PhNeonatalVisitRecord;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

public interface PhNeonatalVisitRecordService extends Service{
	
	/**
	 * 新生儿家庭访视记录添加方法
	 */
	Long saveOrUpdatePhNeonatalVisitRecord(PhNeonatalVisitRecord phNeonatalVisitRecord);

	/**
	 * 新生儿家庭访视记录删除方法
	 */
	Long deletePhNeonatalVisitRecord(Long id);
	
	/**
	 * 新生儿家庭访视记录根据id查询一条数据
	 */
	PhNeonatalVisitRecord getPhNeonatalVisitRecordById(Long id);
	
	/**
	 * 新生儿家庭访视记录查询所以信息方法
	 */
	List<PhNeonatalVisitRecord> getPhNeonatalVisitRecordServiceByPeople(PhPeopleBasicInfo phPeopleBasicInfo,PhNeonatalVisitRecord phNeonatalVisitRecord,PageSortModel psm);
}
