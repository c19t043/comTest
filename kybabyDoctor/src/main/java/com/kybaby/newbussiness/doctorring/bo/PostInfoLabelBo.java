package com.kybaby.newbussiness.doctorring.bo;

import com.kybaby.domain.PostInfoLabel;

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
	 * @Discription:根据发帖的ID和帖子标签的ID字符串，增加发帖的标签实例
	 * @data: 2015年12月29日上午11:07:19
	 * @param postInfoId 发帖的ID
	 * @param labelIds 帖子标签的ID字符串
	 * @param splitStr 拆分字符串
	 */
	void addNewPostInfoLabelList(long postInfoId,String labelIds,String splitStr);
}
