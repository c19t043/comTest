package com.java.familydoctor.worktimeset.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.worktimeset.vo.DoctorWorkTime;

public interface DoctorWorkTimeService {
	/**
	 * 分页列表
	 * @param psm
	 * @param DoctorWorkTime
	 * @return
	 */
	List<DoctorWorkTime> getDoctorWorkTimeByPage(PageSortModel psm,DoctorWorkTime doctorWorkTime);
	/**
	 * 根据id得到实体
	 * @param id
	 * @return
	 */
	DoctorWorkTime getDoctorWorkTimeById(Long id);
	/**
	 * 保存更新实体
	 * @param b2cGoodsDeliver
	 * @return
	 */
	Long saveOrUpdateDoctorWorkTime(DoctorWorkTime doctorWorkTime);
}
