package com.kybaby.newbussiness.doctorring.dao;

import java.util.List;

import com.kybaby.domain.RingLabel;

/**
 * @ClassName:RingLabelDao
 * @Description:医生圈标签事物管理接口
 * @author Hoolee
 * @date 2015年12月28日下午5:36:15
 */
public interface RingLabelDao {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取系统中某一分类的标签列表
	 * @data: 2015年12月28日下午5:49:31
	 * @param ringCategory 
	 * @return
	 */
	List<RingLabel> getSomeCategoryRingLabelList(String ringCategory);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过圈子标签的类型和名称获取到圈子标签的实例
	 * @data: 2015年12月28日下午7:00:00
	 * @param ringCategory 圈子标签的类型
	 * @param ringLabelName 圈子标签的名称
	 * @return 圈子标签的实例
	 */
	RingLabel getRingLabelInstance(String ringCategory,String ringLabelName);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:添加新的圈子标签实例
	 * @data: 2015年12月28日下午7:08:48
	 * @param ringLabelInstance 待添加的圈子标签实例
	 */
	void addNewRingLabelInstance(RingLabel ringLabelInstance);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过圈子标签的ID查询到圈子标签的实例
	 * @data: 2015年12月28日下午7:30:46
	 * @param labelId 圈子标签的ID
	 * @return 圈子标签的实例
	 */
	RingLabel getRingLabelInstanceById(long labelId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新医生圈标签实例
	 * @data: 2015年12月28日下午7:39:44
	 * @param ringLabelInstance 医生圈标签实例
	 */
	void ringLabelInstanceUpdate(RingLabel ringLabelInstance);
}
