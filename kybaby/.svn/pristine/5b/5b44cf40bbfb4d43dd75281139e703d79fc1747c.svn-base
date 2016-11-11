package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.SymptomTag;

/**
 * @ClassName:SymptomTagBo
 * @Description:症状标签事物管理接口
 * @author Hoolee
 * @date 2015年9月22日上午10:05:28
 */
public interface SymptomTagBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过症状标签的ID组成的字符串，获取到症状标签的实例列表
	 * @data: 2015年9月22日上午10:06:46
	 * @param tagIds 症状标签组合成的字符串
	 * @return 症状标签实例列表
	 */
	List<SymptomTag> getSymptomTagInstanceList(String tagIds);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取平台上所有有效的症状标签列表（symptomStatus字段的值为‘Y’）
	 * @data: 2015年9月22日下午11:43:58
	 * @return 平台上所有有效的症状标签列表
	 */
	List<SymptomTag> getAllSymptomTag();
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新平台上被点击的症状标签的被点击次数
	 * @data: 2015年9月22日下午11:47:01
	 * @param tagIds 被点击症状标签ID组成的列表
	 */
	void updateSymptomStatusByIds(String tagIds);
}
