package com.kybaby.bo.impl;

import com.kybaby.bo.PropertiesBo;
import com.kybaby.dao.PropertiesDao;
import com.kybaby.domain.Properties;

/**
 * @ClassName:PropertiesBoImpl
 * @Description:配置事务管理接口实现
 * @author Hoolee
 * @date 2015年9月29日下午2:16:28
 */
public class PropertiesBoImpl implements PropertiesBo {
	
	PropertiesDao propertiesDao;

	public String getHealthMethodStr(String propertyName) {
		return propertiesDao.getHealthMethodStr(propertyName);
	}

	public Properties getPointsProperties(String propertiesName) {
		return propertiesDao.getPointsProperties(propertiesName);
	}

	public Properties getReturnPointsProperties(String propertiesName) {
		return propertiesDao.getPointsProperties(propertiesName);
	}

	public PropertiesDao getPropertiesDao() {
		return propertiesDao;
	}

	public void setPropertiesDao(PropertiesDao propertiesDao) {
		this.propertiesDao = propertiesDao;
	}

}
