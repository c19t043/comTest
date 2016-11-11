package com.kybaby.newbussiness.mommyring.dao;

import java.util.List;

import com.kybaby.newbussiness.mommyring.domain.MommyRingType;

/**
 * 
 * @ClassName:MommyRingTypeDao
 * @Description:医生圈类型数据管理接口
 * @author Hoolee
 * @date 2015年12月10日下午3:22:25
 */
public interface MommyRingTypeDao {

	/**
	 * 
	 * @author HooLee
	 * @Discription:通过医生圈的分类ID（the_type_info表中的ID）,获取该分类下的子分类列表
	 * @data: 2015年12月11日下午1:19:51
	 * @param someCategoryId 医生圈的分类ID（the_type_info表中的ID）
	 * @return 该分类下的子分类列表
	 */
	List<MommyRingType> getSomeCategoryDoctorRing(long someCategoryId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过用户的ID和医生订阅的医生圈ID字符串，获取医生订阅的医生圈实例列表
	 * @data: 2015年12月14日下午2:33:11
	 * @param userRingIdStr 医生订阅的医生圈ID字符串
	 * @return 医生订阅的医生圈实例列表
	 */
	List<MommyRingType> getSomeUserDoctoring(String userRingIdStr);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过医生圈子分类的ID获取到子分类的实例
	 * @data: 2015年12月13日上午9:52:58
	 * @param ringTypeId 医生圈子分类的ID
	 * @return 医生圈子分类的实例
	 */
	MommyRingType getSomeMommyRingTypeInstance(long ringTypeId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据医生的ID推荐给该医生的推荐医生圈列表
	 * @data: 2015年12月13日上午11:19:05
	 * @param userRingIdStr 医生已经订阅了的医生圈的ID字符串
	 * @return 推荐给该医生的推荐医生圈列表
	 */
	List<MommyRingType> getSomeMommyRingType(String userRingIdStr);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取系统的所有推荐医生圈
	 * @data: 2015年12月13日上午11:27:30
	 * @return 系统的所有推荐推荐医生圈实例列表
	 */
	List<MommyRingType> getOsRecommendRingType();
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新医生圈实例
	 * @data: 2015年12月15日下午12:25:18
	 * @param userRingType 待更新的医生圈实例
	 */
	void updateSomeMommyRingType(MommyRingType userRingType);
}
