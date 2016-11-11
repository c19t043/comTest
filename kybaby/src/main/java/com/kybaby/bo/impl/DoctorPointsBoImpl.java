package com.kybaby.bo.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kybaby.bo.DoctorPointsBo;
import com.kybaby.dao.DoctorPointsDao;
import com.kybaby.domain.DoctorPoints;

/**
 * @ClassName:DoctorPointsBoImpl
 * @Description:医生积分事物管理实现
 * @author Hoolee
 * @date 2015年9月28日上午10:48:56
 */
public class DoctorPointsBoImpl implements DoctorPointsBo {
	
	DoctorPointsDao doctorPointsDao;
	
	public void addNewUserPoints(long doctorId, long points, String type,String pointsDes) {
		DoctorPoints doctorPoints=new DoctorPoints();
		doctorPoints.setDoctorId(doctorId);
		doctorPoints.setPoints(points);
		doctorPoints.setType(type);
		doctorPoints.setPointsDes(pointsDes);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date rightNow=new Date(System.currentTimeMillis());
		String submitTime=sdf.format(rightNow);
		doctorPoints.setUpdateTime(submitTime);
		doctorPointsDao.addNewUserPoints(doctorPoints);
	}

	/**
	 * @return the doctorPointsDao
	 */
	public DoctorPointsDao getDoctorPointsDao() {
		return doctorPointsDao;
	}

	/**
	 * @param doctorPointsDao the doctorPointsDao to set
	 */
	public void setDoctorPointsDao(DoctorPointsDao doctorPointsDao) {
		this.doctorPointsDao = doctorPointsDao;
	}

}
