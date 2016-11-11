package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.Banner;

/**
 * @ClassName:BannerBo
 * @Description:banner事物管理接口
 * @author Hoolee
 * @date 2015年9月14日下午10:50:22
 */
public interface BannerBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取有效的banner区域的实例列表
	 * @data: 2015年9月14日下午10:53:09
	 * @return 有效的banner区域的实例列表
	 */
	List<Banner> getBannerPictureList();
}
