package com.java.doctormanager.service;

import java.util.List;
import java.util.Map;

import com.java.doctormanager.vo.DoctorMajor;
import com.java.doctormanager.vo.Major;
import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.Service;


public interface MajorService extends Service{
	/*
	 * 专业
	 */
	List<DoctorMajor> getMajors(PageSortModel model,DoctorMajor major);
	DoctorMajor saveOrUpdateMajor(DoctorMajor major);
	public List<Map<String, Object>> getIndexTree();
}
