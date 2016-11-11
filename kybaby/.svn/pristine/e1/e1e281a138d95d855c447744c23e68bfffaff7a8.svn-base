package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.PropertiesDao;
import com.kybaby.domain.Properties;

/**
 * @ClassName:PropertiesDaoImpl
 * @Description:配置数据管理接口实现
 * @author Hoolee
 * @date 2015年9月29日下午2:20:08
 */
public class PropertiesDaoImpl extends HibernateDaoSupport implements PropertiesDao {

	public String getHealthMethodStr(String propertyName) {
		List<String> list=getHibernateTemplate().find("select value from Properties where name=? and status='Y' ", propertyName);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public Properties getPointsProperties(String propertiesName) {
		List<Properties> list=getHibernateTemplate().find("from Properties where name=? and status='Y' ", propertiesName);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

}
