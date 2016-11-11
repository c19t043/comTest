package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.DoctorArticleComment;

/**
 * @ClassName:DoctorArticleCommentDao
 * @Description:医生个人专栏数据管理接口
 * @author Hoolee
 * @date 2015年10月6日下午7:22:41
 */
public interface DoctorArticleCommentDao {

	/**
	 * 
	 * @author HooLee
	 * @Discription:通过文章的ID获取到文档的评论，包括评论人姓名、评论人头像、评论时间、评论的内容
	 * @data: 2015年9月21日上午11:40:04
	 * @param articleId
	 * @return 返回文档的评论，包括评论人姓名、评论人头像、评论时间、评论的内容
	 */
	List<Object[]> getSomeArticleComments(long articleId);
	
	void addNewComment(DoctorArticleComment comments);
}
