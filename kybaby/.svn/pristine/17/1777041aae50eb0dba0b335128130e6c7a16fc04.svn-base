package com.kybaby.newbussiness.mommyring.dao;

import java.util.List;

import com.kybaby.newbussiness.mommyring.domain.MommyRingType;

/**
 * 
 * @ClassName:DoctorRingTypeDao
 * @Description:妈妈圈类型数据管理接口
 * @author Hoolee
 * @date 2015年12月10日下午3:22:25
 */
public interface MommyRingTypeDao {

	/**
	 * 
	 * @author HooLee
	 * @Discription:通过妈妈圈的分类ID（the_type_info表中的ID）,获取该分类下的子分类列表
	 * @data: 2015年12月11日下午1:19:51
	 * @param someCategoryId 妈妈圈的分类ID（the_type_info表中的ID）
	 * @return 该分类下的子分类列表
	 */
	List<MommyRingType> getSomeCategoryMommyRing(long someCategoryId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过用户的ID和妈妈订阅的妈妈圈ID字符串，获取妈妈订阅的妈妈圈实例列表
	 * @data: 2015年12月14日下午2:33:11
	 * @param doctorRingIdStr 妈妈订阅的妈妈圈ID字符串
	 * @return 妈妈订阅的妈妈圈实例列表
	 */
	List<MommyRingType> getSomeUserMommyring(String mommyRingIdStr);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过妈妈圈子分类的ID获取到子分类的实例
	 * @data: 2015年12月13日上午9:52:58
	 * @param ringTypeId 妈妈圈子分类的ID
	 * @return 妈妈圈子分类的实例
	 */
	MommyRingType getSomeMommyRingTypeInstance(long ringTypeId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据妈妈的ID推荐给该妈妈的推荐妈妈圈列表
	 * @data: 2015年12月13日上午11:19:05
	 * @param doctorRingIdStr 妈妈已经订阅了的妈妈圈的ID字符串
	 * @return 推荐给该妈妈的推荐妈妈圈列表
	 */
	List<MommyRingType> getSomeMommyRingType(String mommyRingIdStr);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取系统的所有推荐妈妈圈
	 * @data: 2015年12月13日上午11:27:30
	 * @return 系统的所有推荐推荐妈妈圈实例列表
	 */
	List<MommyRingType> getOsRecommendRingType();
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新妈妈圈实例
	 * @data: 2015年12月15日下午12:25:18
	 * @param doctorRingType 待更新的妈妈圈实例
	 */
	void updateSomeMommyRingType(MommyRingType mommyRingType);
}
