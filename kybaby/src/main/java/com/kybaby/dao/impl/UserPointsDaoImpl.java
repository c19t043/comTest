package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.UserPointsDao;
import com.kybaby.domain.UserPoints;

/**
 * @ClassName:UserPointsDaoImpl
 * @Description:用户积分数据管理实现
 * @author Hoolee
 * @date 2015年9月28日上午10:39:29
 */
public class UserPointsDaoImpl extends HibernateDaoSupport implements UserPointsDao {

	public void addNewUserPoints(UserPoints userPoints) {
		getHibernateTemplate().save(userPoints);
	}

	public List<UserPoints> getSomeUserPointsDetail(long userId) {
		return null;
	}
	
}
