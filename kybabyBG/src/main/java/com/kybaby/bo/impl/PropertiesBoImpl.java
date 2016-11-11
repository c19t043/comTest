package com.kybaby.bo.impl;

import com.kybaby.bo.PropertiesBo;
import com.kybaby.dao.PropertiesDao;
import com.kybaby.domain.Properties;

public class PropertiesBoImpl  implements PropertiesBo{

	PropertiesDao propertiesDao;
	@Override
	public Properties getPropertiesByName(String Key) {
		// TODO Auto-generated method stub
		return propertiesDao.getPropertiesByName(Key);
	}
	public PropertiesDao getPropertiesDao() {
		return propertiesDao;
	}
	public void setPropertiesDao(PropertiesDao propertiesDao) {
		this.propertiesDao = propertiesDao;
	}

//	//2.3.3基础分成比例和提醒时间
//	Properties	getPropertiesByName(String Key); //找到该配置数据实例(name为scale时基础分成比例,everydayRemindTime为提醒时间)
}
