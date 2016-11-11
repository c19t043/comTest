package com.kybaby.bo.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.kybaby.bo.DoctorInfoBo;
import com.kybaby.dao.DoctorInfoDao;
import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.DoctorPoints;
import com.kybaby.domain.DoctorProduct;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.RecommentAwardRecord;
import com.kybaby.util.GetDistance;

public class DoctorInfoBoImpl implements  DoctorInfoBo{

	DoctorInfoDao doctorInfoDao;
	public long getNumofDoctor(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return doctorInfoDao.getNumofDoctor(startDate, endDate);
	}

	@Override
	public List getAllBriefDoctorInfo() {
		// TODO Auto-generated method stub
		return doctorInfoDao.getAllBriefDoctorInfo();
	}

	

	@Override
	public List getAllBriefInfoOfDoctorPlan() {
		// TODO Auto-generated method stub
		return doctorInfoDao.getAllBriefInfoOfDoctorPlan();
	}

	@Override
	public List getDoctorIdAndName(long updateOrderId) {
		// TODO Auto-generated method stub
		return doctorInfoDao.getDoctorIdAndName(updateOrderId);
	}

	public DoctorInfoDao getDoctorInfoDao() {
		return doctorInfoDao;
	}

	public void setDoctorInfoDao(DoctorInfoDao doctorInfoDao) {
		this.doctorInfoDao = doctorInfoDao;
	}

	@Override
	public List getDetailDoctorInfoById(long id) {
		// TODO Auto-generated method stub
		return doctorInfoDao.getDetailDoctorInfoById(id);
	}

	@Override
	public DoctorInfo getDoctorInfoById(long id) {
		// TODO Auto-generated method stub
		return doctorInfoDao.getDoctorInfoById(id);
	}

	@Override
	public List getAllDoctor() {
		// TODO Auto-generated method stub
		return doctorInfoDao.getAllDoctor();
	}

	@Override
	public List<Long> getBigDoctorList(String serviceDate, Long serviceTimes,
			Long hour) {
		// TODO Auto-generated method stub
		return doctorInfoDao.getBigDoctorList(serviceDate, serviceTimes, hour);
	}

	@Override
	public List getOneDoctorSomeDayInfo(Long a, String serviceDate,
			Long serviceTimes, Long hour) {
		// TODO Auto-generated method stub
		return doctorInfoDao.getOneDoctorSomeDayInfo(a, serviceDate, serviceTimes, hour);
	}

	@Override
	public List getOneDoctorSomeDayInfoIdAndStatus(Long oldDoctorId,String serviceDate, Long serviceTimes, Long hour) {
		// TODO Auto-generated method stub
		return doctorInfoDao.getOneDoctorSomeDayInfoIdAndStatus(oldDoctorId, serviceDate, serviceTimes, hour);
	}

	@Override
	public DoctorProduct getDoctorProductById(Long someId) {
		// TODO Auto-generated method stub
		return doctorInfoDao.getDoctorProductById(someId);
	}



	@Override
	public List getDoctorLngLatById(long doctorId) {
		// TODO Auto-generated method stub
		return doctorInfoDao.getDoctorLngLatById(doctorId);
	}

	@Override
	public List getUserLngLatById(long userId) {
		// TODO Auto-generated method stub
		return doctorInfoDao.getUserLngLatById(userId);
	}

	@Override
	public Double doctorDistanceList(Long userId, Long doctorId) {
		List doctorLngLat=doctorInfoDao.getDoctorLngLatById(doctorId);
		List userLngLat=doctorInfoDao.getUserLngLatById(userId);
		if(doctorLngLat!=null&&userLngLat!=null){
			Object[] doctorObj=(Object[]) doctorLngLat.get(0);
			Object[] userObj=(Object[]) userLngLat.get(0);
			double doctorLng=Double.valueOf((String) doctorObj[0]);//医生的经度
			double doctorLat=Double.valueOf((String) doctorObj[1]);//医生的纬度
			double userLng=Double.valueOf((String) userObj[0]);//用户的经度
			double userLat=Double.valueOf((String) userObj[1]);//用户的纬度
			double distance= GetDistance.GetDistanceMethod(doctorLng, doctorLat, userLng, userLat);
			return distance;
		}
		return null;
	}

	public List newGetDetailDoctorInfoById(long id) {
		return doctorInfoDao.newGetDetailDoctorInfoById(id);
	}

	public RecommentAwardRecord getSomeAwardRecord(String recommendType,long beenRecommendDoctorId, String isGrant, String whenToGrant) {
		return doctorInfoDao.getSomeAwardRecord(recommendType, beenRecommendDoctorId, isGrant, whenToGrant);
	}
	
	public void addNewDoctorAccount(long doctorId, double amount, String type,
			String accountDesc) {
		DoctorAccount doctorAccount=new DoctorAccount();
		doctorAccount.setDoctorId(doctorId);
		doctorAccount.setAmount(amount);
		doctorAccount.setType(type);
		doctorAccount.setAccountDesc(accountDesc);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date rightNow=new Date(System.currentTimeMillis());
		String dateNow=sdf.format(rightNow);
		doctorAccount.setUpdateTime(dateNow);
		//doctorAccountDao.addNewDoctorAccount(doctorAccount);
		doctorInfoDao.addNewDoctorAccount(doctorAccount);
	}
	
	public void addNewUserPoints(long doctorId, long points, String type,String pointsDes) {
		DoctorPoints doctorPoints=new DoctorPoints();
		doctorPoints.setDoctorId(doctorId);
		doctorPoints.setPoints(points);
		doctorPoints.setType(type);
		doctorPoints.setPointsDes(pointsDes);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date rightNow=new Date(System.currentTimeMillis());
		String dateNow=sdf.format(rightNow);
		doctorPoints.setUpdateTime(dateNow);
		//doctorPointsDao.addNewUserPoints(doctorPoints);
		doctorInfoDao.addNewUserPoints(doctorPoints);
	}

	@Override
	public void updateDoctorProductByOrder(OrderInfo orderInfo) {
		// TODO Auto-generated method stub
		doctorInfoDao.updateDoctorProductByOrder(orderInfo);
	}

//	//2.5.1 运营管理
//	long getNumofDoctor(String startDate,String endDate);//按起止日期统计注册的医生数
//	
//	//2.7.1 查看医生
//	List getAllBriefDoctorInfo();//获取所有医生的简要信息
//	DoctorInfo getDetailDoctorInfoById(long id); //获取某个医生的详细信息
//	
//	//2.7.2查看医生计划
//	 List getAllBriefInfoOfDoctorPlan();//获取医生的简要信息(在医生计划页面)
//	
//	//2.9.1订单管理
//	List getDoctorIdAndName();//得到所有的认证状态通过的，状态为Y的医生,选择id和名字和电话号码
//	
}
