package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.HealthPlanDao;
import com.kybaby.domain.HealthPlan;

/**
 * @ClassName:HealthPlanDaoImpl
 * @Description:健康管理计划数据管理接口实现
 * @author Hoolee
 * @date 2015年9月30日上午10:02:13
 */
public class HealthPlanDaoImpl extends HibernateDaoSupport implements HealthPlanDao {

	public HealthPlan getHealthPlanById(long planId) {
		List<HealthPlan> list=getHibernateTemplate().find("from HealthPlan where id=?", planId);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

}
