package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.HealthPathDao;
import com.kybaby.domain.HealthPath;

/**
 * @ClassName:HealthPathDaoImpl
 * @Description:健康计划路径数据管理实现
 * @author Hoolee
 * @date 2015年9月30日上午10:10:54
 */
public class HealthPathDaoImpl extends HibernateDaoSupport implements HealthPathDao {

	public HealthPath getHealthPathById(long pathId) {
		List<HealthPath> list=getHibernateTemplate().find("from HealthPath where id=?", pathId);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public String getPathNameById(long pathId) {
		List<String> list=getHibernateTemplate().find("select healthPathName from HealthPath where id=? ", pathId);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

}
