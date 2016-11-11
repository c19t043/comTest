package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.GuidePage;

/**
 * @ClassName:GuidePageBo
 * @Description:引导页事务管理接口
 * @author Hoolee
 * @date 2015年9月14日下午10:48:05
 */
public interface GuidePageBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取引导页有效的实例列表
	 * @data: 2015年9月14日下午10:51:24
	 * @return 引导页有效的实例列表
	 */
	List<GuidePage> getGuidePictureList();
}
