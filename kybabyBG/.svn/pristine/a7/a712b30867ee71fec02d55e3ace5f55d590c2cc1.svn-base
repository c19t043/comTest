package com.kybaby.newbussiness.mommyring.bo;

import java.util.List;

/**
 * @ClassName:MommyPostInfoLabelBo
 * @Description:帖子标签的事物管理接口
 * @author Hoolee
 * @date 2015年12月29日上午10:54:30
 */
public interface MommyPostInfoLabelBo {

	/**
	 * 
	 * @author HooLee
	 * @Discription:根据发帖的ID和帖子标签的ID字符串，增加发帖的标签实例
	 * @data: 2015年12月29日上午11:07:19
	 * @param mommyPostInfoId 发帖的ID
	 * @param labelIds 帖子标签的ID字符串
	 * @param splitStr 拆分字符串
	 */
	void addNewMommyPostInfoLabelList(long mommyPostInfoId,String labelIds,String splitStr);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:取消某个标签相关联的帖子与标签的关联关系
	 * @data: 2015年12月29日下午2:01:48
	 * @param labelId 标签的ID
	 */
	void deleteSomeMommyPostInfoLabelLinkes(long labelId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据帖子的ID获取到帖子关联的标签ID列表
	 * @data: 2015年12月29日下午2:52:25
	 * @param mommyPostInfoLabelId 帖子的ID
	 * @return 帖子关联的标签ID列表
	 */
	List<Long> getSomeMommyPostInfoLabelList(long mommyPostInfoLabelId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新某一个帖子关联的标签
	 * @data: 2015年12月29日下午3:11:50
	 * @param mommyPostInfoId 某一个帖子的ID
	 * @param labelIds 某一个帖子的标签ID字符串
	 * @param splitStr 某一个帖子关联的标签id组合成的字符串拆分的字符串
	 */
	void deleteSomeMommyPostInfoLabels(long mommyPostInfoId,String labelIds,String splitStr);
}
