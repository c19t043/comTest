package com.kybaby.dao;

import com.kybaby.domain.Properties;

public interface PropertiesDao {

	//2.3.3基础分成比例和提醒时间
	Properties	getPropertiesByName(String Key); //找到该配置数据实例(name为scale时基础分成比例,everydayRemindTime为提醒时间)
}
