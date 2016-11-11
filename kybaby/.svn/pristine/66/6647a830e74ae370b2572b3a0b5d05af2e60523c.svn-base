package com.kybaby.bo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.kybaby.bo.DoctorInfoBo;
import com.kybaby.dao.DoctorInfoDao;
import com.kybaby.dao.DoctorProductDao;
import com.kybaby.dao.ProductDao;
import com.kybaby.dao.UserInfoDao;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.TimeInit;
import com.kybaby.util.GetDistance;

/**
 * @ClassName:DoctorInfoBoImpl
 * @Description:医生事物管理接口实现
 * @author Hoolee
 * @date 2015年9月27日下午5:04:50
 */
public class DoctorInfoBoImpl implements DoctorInfoBo {
	
	DoctorInfoDao doctorInfoDao;
	UserInfoDao userInfoDao;
	ProductDao productDao;
	DoctorProductDao doctorProductDao;
	
	public List<DoctorInfo> getDoctorInfoList() {
		return null;
	}

	public Double doctorDistanceList(long doctorId, long userId) {
		List doctorLngLat=doctorInfoDao.getDoctorLngLatById(doctorId);
		List userLngLat=userInfoDao.getUserLngLatById(userId);
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

	public DoctorInfo getDoctorInfoByDoctorId(long doctorId) {
		return doctorInfoDao.getDoctorInfoByDoctorId(doctorId);
	}

	public List<DoctorInfo> getServiceDoctorList(long userId,
			String productName, String serviceDate, String serviceTime) {
		return null;
	}

	public List<DoctorInfo> getSomeProdcutServiceDoctorList(long userId,long productId) {
		List<DoctorInfo> doctorList=doctorInfoDao.getSomeProductServiceDoctorList(productId);
		if(doctorList!=null){
			for(int i =0;i<doctorList.size();i++){
				DoctorInfo doctor=doctorList.get(i);
				long doctorId=doctor.getId();
				Double distance=this.doctorDistanceList(doctorId, userId);
				Long serviceDistance=doctor.getServiceArea();
				if(distance!=null&&distance>serviceDistance){
					doctorList.remove(i);
				} else if(distance==null){
					doctorList.remove(i);
				}
			}
			return doctorList;
		}
		return null;
	}

	public List getDoctorSomeInfoList() {
		return doctorInfoDao.getDoctorSomeInfoList();
	}

	public void addSomeDoctorBalance(long doctorId, double amount) {
		
	}

	public void updateDoctorService(long doctorId, String serviceResult) {
		
	}

	public List<DoctorInfo> getConsultationDoctorList() {
		return null;
	}

	public List getSomeDoctorInfoById(long doctorId) {
		return null;
	}

	public List<String> getSomeDoctorMajorNameList(long doctorId) {
		return null;
	}

	public boolean isCanbeUsed(String phoneNumber) {
		DoctorInfo doctor=doctorInfoDao.getSomeDoctorInfoByPhone(phoneNumber);
		if(doctor==null){
			return false;
		}
		return true;
	}

	public DoctorInfo getDoctorInfoByPhone(String phone) {
		return doctorInfoDao.getDoctorInfoByPhone(phone);
	}

	public void updateDoctorInstance(DoctorInfo doctor) {
		doctorInfoDao.updateDoctorInstance(doctor);
	}

	public List<DoctorInfo> getSomeDateServiceDoctorList(long productId,String serviceDate, String serviceTime, long userId) {
		if(productDao.getProductServiceTimeById(productId)!=null){
			String serviceTimes="(";
			long timeLength=productDao.getProductServiceTimeById(productId);
			for(long i=0;i<=timeLength;i++){
				serviceTimes=serviceTimes+(Long.valueOf(serviceTime)+i)+",";
			}
			int strLen=serviceTimes.length();
			serviceTimes=serviceTimes.substring(0, strLen-1);
			serviceTimes=serviceTimes+")";
			List<Long> doctorIdsList=doctorProductDao.getDoctorIdByDteAndTime(serviceDate, serviceTimes);
			List<DoctorInfo> doctorList=new ArrayList<DoctorInfo>();
			if(doctorIdsList!=null){
				for(int i=0;i<doctorIdsList.size();i++){
					long doctorId=doctorIdsList.get(i);
					long servers=doctorProductDao.getSomeDoctorServiceTimeAmount(doctorId, serviceDate, serviceTimes);
					long serviceDistance=doctorInfoDao.getDoctorServiceAreaById(doctorId);
					boolean isProvide=doctorInfoDao.isServiceSomeProduct(doctorId, productId);
					if(servers==(timeLength+1)&&this.doctorDistanceList(doctorId, userId)!=null&&serviceDistance>=this.doctorDistanceList(doctorId, userId)&&isProvide){
						DoctorInfo someDoctor=doctorInfoDao.getSomeCanUserDoctor(doctorId);
						if(someDoctor!=null){
							doctorList.add(someDoctor);
						}
					}
				}
				return doctorList;
			}
			return null;
		}
		return null;
	}
	
	public Long getDoctorRankByName(String positionName){
		return doctorInfoDao.getDoctorRankByName(positionName);
	}

	public List<Object[]> getConsulationDoctoSomeInfo() {
		return doctorInfoDao.getConsulationDoctoSomeInfo();
	}

	public List<TimeInit> getTimeInitList() {
		return doctorInfoDao.getTimeInitList();
	}
	
	public DoctorInfoDao getDoctorInfoDao() {
		return doctorInfoDao;
	}

	public void setDoctorInfoDao(DoctorInfoDao doctorInfoDao) {
		this.doctorInfoDao = doctorInfoDao;
	}

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public DoctorProductDao getDoctorProductDao() {
		return doctorProductDao;
	}

	public void setDoctorProductDao(DoctorProductDao doctorProductDao) {
		this.doctorProductDao = doctorProductDao;
	}

	public List getDoctorIdByUserId(Long userId) {
		return doctorInfoDao.getDoctorIdByUserId(userId);
	}

	public String checkIsInService(Long userId, Long doctorId) {
		Long afterServiceTime = doctorInfoDao.getAfterServiceTime(userId, doctorId);
		String yuyueTime = doctorInfoDao.getBespokeDate(userId,doctorId);
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
		Date time = new Date();
		if(afterServiceTime==null)
		{
			return "否";
		}
		if(yuyueTime==null)
		{
			return "否";
		}
		try{
			time = sdf.parse(yuyueTime);
		}catch(Exception e){
			return "否";
		}
		Calendar calendar = Calendar.getInstance();  
        Date date = new Date();
		calendar.setTime(date);  
		if(afterServiceTime==null){
			return "否";
		}
        calendar.add(Calendar.DAY_OF_MONTH, (int) - afterServiceTime);  
        
        date = calendar.getTime();
		System.out.println(date);
		if(date.before(time)){
			return "是";
		}else{
			return "否";
		}
		
	}

	public String getMajorNameByMajorId(Long id) {
		return doctorInfoDao.getMajorNameByMajorId(id);
	}


}
