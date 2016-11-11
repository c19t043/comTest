package com.kybaby.bo.impl;

import java.sql.Timestamp;
import java.util.List;

import com.kybaby.bo.UserPointsBo;
import com.kybaby.dao.UserPointsDao;
import com.kybaby.domain.UserPoints;

/**
 * @ClassName:UserPointsBoImpl
 * @Description:用户积分事物管理接口实现
 * @author Hoolee
 * @date 2015年9月28日上午10:37:35
 */
public class UserPointsBoImpl implements UserPointsBo {
	
	UserPointsDao userPointsDao;

	public void addNewUserPoints(long userId, long points, String type,String pointsDes) {
		UserPoints userPoints=new UserPoints();
		userPoints.setUserId(userId);
		userPoints.setPoints(points);
		userPoints.setType(type);
		userPoints.setPointsDes(pointsDes);
		userPoints.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		userPointsDao.addNewUserPoints(userPoints);
	}

	public List<UserPoints> getSomeUserPointsDetail(long userId) {
		return null;
	}

	/**
	 * @return the userPointsDao
	 */
	public UserPointsDao getUserPointsDao() {
		return userPointsDao;
	}

	/**
	 * @param userPointsDao the userPointsDao to set
	 */
	public void setUserPointsDao(UserPointsDao userPointsDao) {
		this.userPointsDao = userPointsDao;
	}
	
}
