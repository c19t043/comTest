package com.java.familydoctor.servicetimes.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.servicetimes.vo.FdServiceTimes;
import com.java.platform.user.service.Service;
/**
 * 服务包服务时间信息服务方法
 *
 */
public interface FdServiceTimesService extends Service{
	
	/**
	 * 家庭医生服务团队信息添加和修改方法
	 */
	Long saveOrUpdateFdServiceTimes(FdServiceTimes fdServiceTimes);
	
	/**
	 * 家庭医生服务团队信息通过id查询一条数据
	 */
	FdServiceTimes getFdServiceTimesById(Long id);
	
	/**
	 * 家庭医生服务团队信息列表方法
	 */
	List<FdServiceTimes> getFdServiceTimesByPage(PageSortModel psm,FdServiceTimes fdServiceTimes);
}
