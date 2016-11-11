package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.AssessmentTag;

/**
 * @ClassName:AssessmentTagBo
 * @Description:评价标签事物管理接口
 * @author Hoolee
 * @date 2015年9月21日下午3:48:53
 */
public interface AssessmentTagBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过标签的comments获取到标签的名字，用于获取到质量控制类的对应标签的值
	 * @data: 2015年9月21日下午3:53:05字段的值到标签的名字
	 * @param comments 质量控制类的标志
	 * @return 标签的名字
	 */
	String getTagNameByComments(String comments);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取到系统中定义的有效的社交标签列表
	 * @data: 2015年9月21日下午3:58:10
	 * @return 有效的社交标签列表
	 */
	List<AssessmentTag> getSocialAssessmentTag();
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新社交标签被点击的次数
	 * @data: 2015年9月21日下午4:30:38
	 * @param tagIds 被点击的社交标签ID组合成的字符串
	 */
	void updateTagHitCount(String tagIds);
	
}
