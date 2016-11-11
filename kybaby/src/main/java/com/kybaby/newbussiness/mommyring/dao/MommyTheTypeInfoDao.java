package com.kybaby.newbussiness.mommyring.dao;

import java.util.List;

import com.kybaby.newbussiness.mommyring.domain.MommyTheTypeInfo;

/**
 * @ClassName:TheTypeInfoDao
 * @Description:医生圈分类数据管理接口
 * @author Hoolee
 * @date 2015年12月10日下午4:41:51
 */
public interface MommyTheTypeInfoDao {

	/**
	 * 
	 * @author HooLee
	 * @Discription:获取系统中所有有效的、按照分类typeSort字段从小到大排序的医生圈的分类名称和ID
	 * @data: 2015年12月10日16:57:01
	 * @return 系统中所有有效的、按照分类typeSort字段从小到大排序的医生圈的分类名称和ID
	 */
	List<Object[]> getAllRingCategory();
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取系统中被标记的医生圈
	 * @data: 2015年12月10日下午5:33:47
	 * @return 系统中被标记的医生圈
	 */
	Object[] getRemarkRingCategory();
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过分类的ID获取分类的实例
	 * @data: 2015年12月10日19:15:39
	 * @param categoryId 医生圈分类的ID
	 * @return 医生圈分类的实例
	 */
	MommyTheTypeInfo getSomeCategoryInstanceById(long categoryId);
}
