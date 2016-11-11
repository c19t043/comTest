package com.kybaby.newbussiness.mommyring.dao;

import java.util.List;

import com.kybaby.newbussiness.mommyring.domain.MommyPostInfo;

/**
 * @ClassName:MommyPostInfoDao
 * @Description:用户发帖数据管理接口
 * @author Hoolee
 * @date 2015年12月13日下午1:52:41
 */
public interface MommyPostInfoDao {

	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某一个医生圈下的帖子列表
	 * @data: 2015年12月13日下午2:25:50
	 * @param categoryId 医生圈或者子类的ID
	 * @return 某一个医生圈下的帖子列表
	 */
	List<MommyPostInfo> getSomeCategoryMommyPostInfo(long categoryId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过帖子的ID获取到帖子的实例
	 * @data: 2015年12月13日下午5:22:12
	 * @param mommyPostInfoId 帖子的ID
	 * @return 帖子的实例
	 */
	MommyPostInfo getMommyPostInfoInstanceById(long mommyPostInfoId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新医生圈发帖的实例
	 * @data: 2015年12月15日下午4:48:01
	 * @param mommyPostInfoInstance 待更新的医生圈帖子实例
	 */
	void updateMommyPostInfoInstance(MommyPostInfo mommyPostInfoInstance);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加医生圈发帖的实例
	 * @data: 2015年12月16日上午11:28:54
	 * @param mommyPostInfoInstance 待增加的医生圈发帖实例
	 */
	long addNewMommyPostInfoInstance(MommyPostInfo mommyPostInfoInstance);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取平台上所有的发帖
	 * @data: 2015年12月23日下午4:40:19
	 * @return 平台上的所有发帖
	 */
	List<MommyPostInfo> 	getAllMommyPostInfo();
}
