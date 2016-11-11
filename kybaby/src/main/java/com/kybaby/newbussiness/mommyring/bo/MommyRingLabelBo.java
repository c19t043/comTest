package com.kybaby.newbussiness.mommyring.bo;

import java.util.List;

import com.kybaby.newbussiness.mommyring.domain.MommyRingLabel;

/**
 * @ClassName:RingLabelBo
 * @Description:医生圈标签事务管理接口
 * @author Hoolee
 * @date 2015年12月28日下午5:34:29
 */
public interface MommyRingLabelBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过医生圈帖子的ID获取到帖子包含的标签名称列表
	 * @data: 2015年12月29日下午4:23:14
	 * @param postInfoId 医生圈帖子的ID
	 * @return 医生圈帖子包含的标签名称列表
	 */
	List<String> getSomePostInfoRingLabelNameList(long postInfoId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据帖子的ID获取某篇帖子下面标签的ID和名称
	 * @data: 2015年12月30日上午9:48:36
	 * @param postInfoId 帖子的ID
	 * @return 某篇帖子下面标签的ID和名称
	 */
	List<Object[]> getSomePostInfoRingLabelIdAndName(long postInfoId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取系统中某一分类的标签列表
	 * @data: 2015年12月28日下午5:49:31
	 * @param ringCategory 
	 * @return
	 */
	List<MommyRingLabel> getSomeCategoryRingLabelList(String ringCategory);
}
