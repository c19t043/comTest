package com.kybaby.bo;

import java.util.List;

/**
 * @ClassName:DoctorArticleCommentBo
 * @Description:医生专栏评论事务管理接口
 * @author Hoolee
 * @date 2015年9月21日上午11:37:09
 */
public interface DoctorArticleCommentBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过文章的ID获取到文档的评论，包括评论人姓名、评论人头像、评论时间、评论的内容
	 * @data: 2015年9月21日上午11:40:04
	 * @param articleId
	 * @return 返回文档的评论，包括评论人姓名、评论人头像、评论时间、评论的内容
	 */
	List<Object[]> getSomeArticleComments(long articleId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加某个用户对某篇文章的‘待审核’的评论
	 * @data: 2015年9月21日上午11:49:50
	 * @param userId 评论用户的ID
	 * @param articleId 文章的ID
	 * @param userComments 评论的内容
	 */
	void addNewArticleComments(long userId,long articleId,String userComments);
}
