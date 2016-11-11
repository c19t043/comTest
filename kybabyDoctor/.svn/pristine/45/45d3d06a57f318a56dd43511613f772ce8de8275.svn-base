package com.kybaby.newbussiness.doctorring.bo;

import java.util.List;

import com.kybaby.domain.PostReply;

/**
 * @ClassName:PostReplyBo
 * @Description:帖子回复事物管理接口
 * @author Hoolee
 * @date 2015年12月13日下午6:05:04
 */
public interface PostReplyBo {

	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某一个帖子的回复内容列表
	 * @data: 2015年12月13日下午6:14:19
	 * @param postInfoId 某一个帖子的ID
	 * @return 某一个帖子的回复内容列表
	 */
	List<PostReply> getSomePostInfoReply(long postInfoId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据医生的ID和所属帖子的ID获取到医生点赞帖子的实例列表
	 * @data: 2015年12月14日下午6:33:17
	 * @param postInfoId 所属帖子的ID
	 * @param doctorId 医生的ID
	 * @return  医生点赞某一个帖子的实例列表
	 */
	List<PostReply> getSomeUserGiveStar(long postInfoId,long doctorId );
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据提交的数据，添加一条新的回复记录、或者点赞记录
	 * @data: 2015年12月14日下午6:51:53
	 * @param postId 回复所属帖子的ID
	 * @param replyId 回复的帖子的ID
	 * @param isPoint 是否点赞
	 * @param createPerson 回复人ID
	 * @param isAllowReply 是否允许回复
	 * @param isAudit 是否开启审核
	 * @param dataStatus 帖子数据状态
	 * @param replyContent 回复的内容
	 */
	void addNewPostReply(long postId,long replyId,String isPoint,long createPerson,String isAllowReply,String isAudit,String dataStatus,String replyContent);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据医生的ID和回复的帖子的ID，删除用户的点赞
	 * @data: 2015年12月15日上午11:10:13
	 * @param postInfoId 回复的帖子的ID
	 * @param doctorId 医生的ID
	 * @return 删除用户点赞的状态，即删除是否成功
	 */
	boolean deleteSomePostReply(long postInfoId,long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据发帖回复的ID获取到发帖回复的实例
	 * @data: 2015年12月22日下午1:51:32
	 * @param replyId 发帖回复的ID
	 * @return 发帖回复的实例
	 */
	PostReply getPostReplyInstanceById(long replyId);
}
