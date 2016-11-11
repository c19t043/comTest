package com.kybaby.newbussiness.doctorring.dao;

import java.util.List;

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
	 * @Discription:添加医生圈标签的实例
	 * @data: 2015年12月29日上午11:10:05
	 * @param postInfoLabelInstance 待添加的医生圈标签的实例
	 */
	void addNewPostInfoLabelInstance(PostInfoLabel postInfoLabelInstance);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:取消某个标签相关联的帖子与标签的关联关系
	 * @data: 2015年12月29日下午2:01:48
	 * @param labelId 标签的ID
	 */
	void deleteSomePostInfoLabelLinkes(long labelId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据帖子的ID获取到帖子关联的标签ID列表
	 * @data: 2015年12月29日下午2:52:25
	 * @param postInfoLabelId 帖子的ID
	 * @return 帖子关联的标签ID列表
	 */
	List<Long> getSomePostInfoLabelList(long postInfoLabelId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:删除某个帖子关联的标签
	 * @data: 2015年12月29日下午3:16:15
	 * @param postInfoId 某个帖子的ID
	 */
	void deleteSomePostInfoLabels(long postInfoId);
}
