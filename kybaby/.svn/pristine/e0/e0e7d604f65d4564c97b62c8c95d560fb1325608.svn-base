package com.kybaby.bo.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.kybaby.bo.RecommentAwardRecordBo;
import com.kybaby.dao.RecommentAwardRecordDao;
import com.kybaby.domain.RecommentAwardRecord;

/**
 * @ClassName:RecommentAwardRecordBoImpl
 * @Description:推荐奖励事务管理接口实现
 * @author Hoolee
 * @date 2015年9月28日下午1:42:39
 */
public class RecommentAwardRecordBoImpl implements RecommentAwardRecordBo {

	RecommentAwardRecordDao recommentAwardRecordDao;
	
	public void addNewUserRecommentAwardRecord(String recommendType,
			long recommendUserId, long beenRecommendUserId, long pointsAmount,
			double amount, long couponId, String whenToGrant,String isGrant) {
		RecommentAwardRecord recommentAwardRecord=new RecommentAwardRecord();
		recommentAwardRecord.setRecommendType(recommendType);
		recommentAwardRecord.setRecommendUserId(recommendUserId);
		recommentAwardRecord.setBeenRecommendUserId(beenRecommendUserId);
		recommentAwardRecord.setPointsAmount(pointsAmount);
		recommentAwardRecord.setAmount(amount);
		recommentAwardRecord.setCouponId(couponId);
		recommentAwardRecord.setWhenToGrant(whenToGrant);
		recommentAwardRecord.setIsGrant(isGrant);
		recommentAwardRecordDao.addNewRecommentAwardRecord(recommentAwardRecord);
	}

	public void addNewDoctorRecommentAwardRecord(String recommendType,
			long recommendDoctorId, long beenRecommendUserId,
			long pointsAmount, double amount, long couponId, String whenToGrant,String isGrant) {
		RecommentAwardRecord recommentAwardRecord=new RecommentAwardRecord();
		recommentAwardRecord.setRecommendType(recommendType);
		recommentAwardRecord.setRecommendDoctorId(recommendDoctorId);
		recommentAwardRecord.setBeenRecommendUserId(beenRecommendUserId);
		recommentAwardRecord.setPointsAmount(pointsAmount);
		recommentAwardRecord.setAmount(amount);
		recommentAwardRecord.setCouponId(couponId);
		recommentAwardRecord.setWhenToGrant(whenToGrant);
		recommentAwardRecord.setIsGrant(isGrant);
		recommentAwardRecordDao.addNewRecommentAwardRecord(recommentAwardRecord);
	}

	public List<RecommentAwardRecord> getSomeUserRecommentAwardRecord(long beenRecommendUserId, String isGrant, String whenToGrant) {
		return recommentAwardRecordDao.getSomeUserRecommentAwardRecord(beenRecommendUserId, isGrant, whenToGrant);
	}

	public void updateRecommentAwardRecord(RecommentAwardRecord record) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date rightNow=new Date(System.currentTimeMillis());
		String dateNow=sdf.format(rightNow);
		record.setAwardTime(dateNow);
		record.setIsGrant("Y");
		recommentAwardRecordDao.updateRecommentAwardRecord(record);
	}

	public RecommentAwardRecordDao getRecommentAwardRecordDao() {
		return recommentAwardRecordDao;
	}

	public void setRecommentAwardRecordDao(
			RecommentAwardRecordDao recommentAwardRecordDao) {
		this.recommentAwardRecordDao = recommentAwardRecordDao;
	}


}
