package com.kybaby.newbussiness.doctorring.dao;

import com.kybaby.domain.PostInfoLabel;

/**
 * @ClassName:PostInfoLabelDao
 * @Description:帖子标签的数据管理接口
 * @author Hoolee
 * @date 2015年12月29日上午10:56:46
 */
public interface PostInfoLabelDao{
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据帖子的ID和标签的ID获取到帖子绑定标签的实例
	 * @data: 2015年12月30日上午11:26:50
	 * @param postInfoId 帖子的ID
	 * @param labelId 标签的ID
	 * @return 帖子绑定标签的实例
	 */
	PostInfoLabel checkSomePostInfoLabel(long postInfoId,long labelId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:添加医生圈标签的实例
	 * @data: 2015年12月29日上午11:10:05
	 * @param postInfoLabelInstance 待添加的医生圈标签的实例
	 */
	void addNewPostInfoLabelInstance(PostInfoLabel postInfoLabelInstance);
}
