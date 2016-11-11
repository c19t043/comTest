package com.kybaby.newbussiness.mommyring.dao;

import java.util.List;

import com.kybaby.newbussiness.mommyring.domain.MommyPostReply;

/**
 * @ClassName:MommyPostReplyDao
 * @Description:帖子回复数据管理接口
 * @author Hoolee
 * @date 2015年12月13日下午6:05:27
 */
public interface MommyPostReplyDao {

	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某一个帖子的回复内容列表
	 * @data: 2015年12月13日下午6:14:19
	 * @param mommyPostInfoId 某一个帖子的ID
	 * @return 某一个帖子的回复内容列表
	 */
	List<MommyPostReply> getSomeMommyPostInfoReply(long mommyPostInfoId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据医生的ID和所属帖子的ID获取到医生点赞帖子的实例列表
	 * @data: 2015年12月14日下午6:33:17
	 * @param mommyPostInfoId 所属帖子的ID
	 * @param userId 医生的ID
	 * @return  医生点赞某一个帖子的实例列表
	 */
	List<MommyPostReply> getSomeUserGiveStar(long mommyPostInfoId,long userId );
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加一条新的回复
	 * @data: 2015年12月15日上午8:57:22
	 * @param postReply 待增加的回复
	 */
	void addNewMommyPostReply(MommyPostReply postReply);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:删除用户的某一个回复记录
	 * @data: 2015年12月15日上午11:15:16
	 * @param postReply 即将删除的回复记录
	 */
	void deleteSomeMommyPostReply(MommyPostReply postReply);
}
