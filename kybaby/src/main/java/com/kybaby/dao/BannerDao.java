package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.Banner;

/**
 * @ClassName:BannerDao
 * @Description:banner区域图片数据管理接口
 * @author Hoolee
 * @date 2015年9月29日上午11:57:02
 */
public interface BannerDao {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取有效的banner区域的实例列表
	 * @data: 2015年9月29日11:57:44
	 * @return 有效的banner区域的实例列表
	 */
	List<Banner> getBannerPictureList();
}
