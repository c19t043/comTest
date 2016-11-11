package com.kybaby.newbussiness.mommyring.bo;

import java.util.List;

import com.kybaby.newbussiness.mommyring.domain.MommyRingType;

/**
 * 
 * @ClassName:MommyRingTypeBo
 * @Description:医生圈类型事物管理接口
 * @author Hoolee
 * @date 2015年12月10日下午3:22:10
 */
public interface MommyRingTypeBo {
 
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
	 * @Discription:获取某个用户订阅的医生圈分类实例列表
	 * @data: 2015年12月13日上午9:08:25
	 * @param userId 用户的ID
	 * @return 某个用户订阅的医生圈实例列表
	 */
	List<MommyRingType> getSomeUserDoctoring(long userId);
	
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
	 * @param userId 医生的ID
	 * @return 推荐给该医生的推荐医生圈列表
	 */
	List<MommyRingType> getSomeMommyRingType(long userId);
	
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
