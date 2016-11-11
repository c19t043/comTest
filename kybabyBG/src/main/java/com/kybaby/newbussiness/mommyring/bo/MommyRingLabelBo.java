package com.kybaby.newbussiness.mommyring.bo;

import java.util.List;

import com.kybaby.newbussiness.mommyring.domain.MommyRingLabel;

/**
 * @ClassName:MommyRingLabelBo
 * @Description:医生圈标签事务管理接口
 * @author Hoolee
 * @date 2015年12月28日下午5:34:29
 */
public interface MommyRingLabelBo {

	/**
	 * 
	 * @author HooLee
	 * @Discription:获取系统中某一分类的标签列表
	 * @data: 2015年12月28日下午5:49:31
	 * @param ringCategory 
	 * @return
	 */
	List<MommyRingLabel> getSomeCategoryMommyRingLabelList(String ringCategory);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:添加新的圈子标签
	 * @data: 2015年12月28日下午6:56:50
	 * @param ringCategory 圈子标签的类型
	 * @param ringLabelName 圈子标签的名称
	 * @param ringLabelStatus 圈子标签的状态
	 * @return 添加是否成功,重复添加返回false
	 */
	boolean addNewMommyRingLabel(String ringCategory,String ringLabelName,String ringLabelStatus);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过圈子标签的分类和名称获取到圈子标签的实例
	 * @data: 2015年12月28日下午7:14:12
	 * @param ringCategory 圈子标签的分类
	 * @param ringLabelName 圈子标签的名称
	 * @return 圈子标签的实例
	 */
	MommyRingLabel getMommyRingLabelInstance(String ringCategory,String ringLabelName);

	/**
	 * 
	 * @author HooLee
	 * @Discription:通过圈子标签的ID查询到圈子标签的实例
	 * @data: 2015年12月28日下午7:30:46
	 * @param labelId 圈子标签的ID
	 * @return 圈子标签的实例
	 */
	MommyRingLabel getMommyRingLabelInstanceById(long labelId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新医生圈标签实例
	 * @data: 2015年12月28日下午7:39:44
	 * @param ringLabelInstance 医生圈标签实例
	 */
	void ringLabelInstanceUpdate(MommyRingLabel ringLabelInstance);
}
