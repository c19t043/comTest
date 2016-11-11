package com.java.publichealth.childhealth.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.Service;
import com.java.publichealth.childhealth.vo.PhChildHealthExaminationRecord;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

public interface ChildHealthSerivce extends Service{
	
	/**
	 * 儿童体格检查记录添加方法
	 */
	Long saveOrUpdateChildHealth(PhChildHealthExaminationRecord childHealthExaminationRecord);

	/**
	 * 儿童体格检查记录删除方法
	 */
	Long deleteChildHealth(Long id);
	
	/**
	 * 儿童体格检查记录根据id查询一条数据
	 */
	PhChildHealthExaminationRecord getChildHealthById(Long id);
	
	/**
	 * 得到某儿童的健康体检信息列表
	 * @param psm
	 * @param phPeopleBasicInfo
	 * @param phChildHealthExaminationRecord
	 * @return
	 */
	List<PhChildHealthExaminationRecord> getPhChildHealthExaminationRecordListByPeople(PageSortModel psm,
			PhPeopleBasicInfo phPeopleBasicInfo,PhChildHealthExaminationRecord phChildHealthExaminationRecord);
	
	
}
