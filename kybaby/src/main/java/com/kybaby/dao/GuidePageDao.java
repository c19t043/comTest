package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.GuidePage;

/**
 * @ClassName:GuidePageDao
 * @Description:引导页事物管理数据管理接口
 * @author Hoolee
 * @date 2015年9月29日上午11:16:17
 */
public interface GuidePageDao {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取引导页有效的实例列表
	 * @data: 2015年9月29日11:17:04
	 * @return 引导页有效的实例列表
	 */
	List<GuidePage> getGuidePictureList();
}
