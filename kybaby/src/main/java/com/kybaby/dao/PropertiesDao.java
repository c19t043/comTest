package com.kybaby.dao;

import com.kybaby.domain.Properties;

/**
 * @ClassName:PropertiesDao
 * @Description:配置数据管理接口
 * @author Hoolee
 * @date 2015年9月29日下午2:18:23
 */
public interface PropertiesDao {
	
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
	 * @data: 2015年10月7日15:45:34
	 * @param propertiesName 属性名字
	 * @return 积分的兑换规则实例
	 */
	Properties getPointsProperties(String propertiesName);
	
	
	
}
