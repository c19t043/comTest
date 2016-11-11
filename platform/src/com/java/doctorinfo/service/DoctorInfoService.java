package com.java.doctorinfo.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.operationmanage.vo.DoctorInfo;

public interface DoctorInfoService {
	/**
	 * 分页列表
	 * @param psm
	 * @param doctorInfo
	 * @return
	 */
	List<DoctorInfo> getDoctorInfoListByPage(PageSortModel psm,DoctorInfo doctorInfo);
}
