package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.DoctorArticle;

/**
 * @ClassName:DoctorArticleBo
 * @Description:医生文章事务管理接口
 * @author Hoolee
 * @date 2015年9月14日下午11:20:44
 */
public interface DoctorArticleBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某医生个人专栏实例列表
	 * @data: 2015年9月14日下午11:21:38
	 * @param doctorId 医生的ID
	 * @return 某医生个人专栏实例列表
	 */
	List<DoctorArticle> getSomeDoctorArticleList(long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据文章的ID更新文章的浏览次数
	 * @data: 2015年9月21日上午11:07:30
	 * @param articleId 文章的ID
	 */
	void addArticleHitCount(long articleId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据文章的ID获取到文章的实例
	 * @data: 2015年9月21日上午11:08:35
	 * @param articleId 文章的ID
	 * @return 文章的实例
	 */
	DoctorArticle getDoctorArticleInstance(long articleId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新医生个人专栏实例
	 * @data: 2015年10月6日下午7:04:53
	 * @param doctorArticle 医生个人专栏实例
	 */
	void updateArticleInstance(DoctorArticle doctorArticle);
}
