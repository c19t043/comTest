package com.kybaby.newbussiness.doctorring.bo;

import java.util.List;

/**
 * @ClassName:PostInfoLabelBo
 * @Description:帖子标签的事物管理接口
 * @author Hoolee
 * @date 2015年12月29日上午10:54:30
 */
public interface PostInfoLabelBo {

	/**
	 * 
	 * @author HooLee
	 * @Discription:根据发帖的ID和帖子标签的ID字符串，增加发帖的标签实例
	 * @data: 2015年12月29日上午11:07:19
	 * @param postInfoId 发帖的ID
	 * @param labelIds 帖子标签的ID字符串
	 * @param splitStr 拆分字符串
	 */
	void addNewPostInfoLabelList(long postInfoId,String labelIds,String splitStr);
	
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
	 * @Discription:更新某一个帖子关联的标签
	 * @data: 2015年12月29日下午3:11:50
	 * @param postInfoId 某一个帖子的ID
	 * @param labelIds 某一个帖子的标签ID字符串
	 * @param splitStr 某一个帖子关联的标签id组合成的字符串拆分的字符串
	 */
	void deleteSomePostInfoLabels(long postInfoId,String labelIds,String splitStr);
}
