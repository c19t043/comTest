package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.SymptomTag;

/**
 * @ClassName:SymptomTagDao
 * @Description:症状标签数据管理接口
 * @author Hoolee
 * @date 2015年10月12日下午3:29:53
 */
public interface SymptomTagDao {

	/**
	 * 
	 * @author HooLee
	 * @Discription:通过症状标签的ID获取到症状标签实例
	 * @data: 2015年10月12日下午3:36:26
	 * @param id 症状标签的ID
	 * @return 症状标签的实例
	 */
	SymptomTag getSymptomTagInstanceById(long id);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取平台上所有有效的症状标签列表（symptomStatus字段的值为‘Y’）
	 * @data: 2015年10月12日16:40:11
	 * @return 平台上所有有效的症状标签列表
	 */
	List<SymptomTag> getAllSymptomTag();
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新咨询标签的实例
	 * @data: 2015年10月12日下午6:04:35
	 * @param symptomTag 咨询标签实例
	 */
	void updateSymptomTagInstance(SymptomTag symptomTag);
}
