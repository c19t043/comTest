package com.kybaby.bo;

import com.kybaby.domain.Properties;

/**
 * @ClassName:PropertiesBo
 * @Description:配置信息事务管理接口
 * @author Hoolee
 * @date 2015年9月14日下午10:59:54
 */
public interface PropertiesBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:返回“健康管理方法”静态图片地址
	 * @data: 2015年9月14日下午11:01:17
	 * @param propertyName 属性名字
	 * @return “健康管理方法”静态图片地址
	 */
	String getHealthMethodStr(String propertyName);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取积分的兑换规则实例
	 * @data: 2015年9月14日下午11:34:35
	 * @param propertiesName 属性名字
	 * @return 积分的兑换规则实例
	 */
	Properties getPointsProperties(String propertiesName);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取消费金额换积分的兑换规则实例
	 * @data: 2015年9月16日下午10:48:12
	 * @param propertiesName 属性的名字
	 * @return 消费金额兑换积分你的兑换规则实例
	 */
	Properties getReturnPointsProperties(String propertiesName);
}
