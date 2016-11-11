package com.kybaby.bo.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.kybaby.bo.DoctorProductBo;
import com.kybaby.dao.DoctorProductDao;
import com.kybaby.domain.DoctorProduct;

/**
 * @ClassName:DoctorProductBoImpl
 * @Description:医生服务产品实物管理接口实现
 * @author Hoolee
 * @date 2015年10月7日下午7:15:28
 */
public class DoctorProductBoImpl implements DoctorProductBo {
	
	DoctorProductDao doctorProductDao;

	public List<String> getServiceDate(long doctorId, long productId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date rightNow=new Date(System.currentTimeMillis());
		String dateNow=sdf.format(rightNow);
		return doctorProductDao.getServiceDate(doctorId, productId, dateNow);
	}

	public List<String> getServiceTime(long doctorId, long productId,String serviceDate) {
		return doctorProductDao.getServiceTime(doctorId, serviceDate);
	}

	public void updateDoctorProduct(long doctorId, long productId,String serviceDate, String serviceTimes) {
		
	}

	public void updateDoctorSomeProductStatus(long doctorId,String serviceDate, String serviceTimes) {
		
	}

	public long getSomeDoctorServiceTimeAmount(long doctorId,String serviceDate, String serviceTimes) {
		return doctorProductDao.getSomeDoctorServiceTimeAmount(doctorId, serviceDate, serviceTimes);
	}
	
	public void updateSomeDoctorServiceTime(long doctorId, String serviceDate,String serviceTimes) {
		List<DoctorProduct> someDoctorProduct=doctorProductDao.getSomeDoctorProduct(doctorId, serviceDate, serviceTimes);
		if(someDoctorProduct!=null){
			for(int i =0;i<someDoctorProduct.size();i++){
				DoctorProduct doctorProdcut=someDoctorProduct.get(i);
				doctorProdcut.setIsProvide("Y");
				doctorProductDao.updatDoctorProductInstance(doctorProdcut);
			}
		}
	}

	public List<String> canBeUserDateList() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date rightNow=new Date(System.currentTimeMillis());
		String dateNow=sdf.format(rightNow);
		return doctorProductDao.canBeUserDateList(dateNow);
	}
	
	public List<String> canBeUserServiceTimeList(String serviceDate) {
		return doctorProductDao.canBeUserServiceTimeList(serviceDate);
	}
	
	public void returnSomeDoctorTime(long doctorId, String serviceDate,
			String serviceTimes) {
		List<DoctorProduct> someDoctorProduct=doctorProductDao.getSomeDoctorProduct(doctorId, serviceDate, serviceTimes);
		if(someDoctorProduct!=null){
			for(int i =0;i<someDoctorProduct.size();i++){
				DoctorProduct doctorProdcut=someDoctorProduct.get(i);
				doctorProdcut.setIsProvide("N");
				doctorProductDao.updatDoctorProductInstance(doctorProdcut);
			}
		}
	}

	public DoctorProductDao getDoctorProductDao() {
		return doctorProductDao;
	}

	public void setDoctorProductDao(DoctorProductDao doctorProductDao) {
		this.doctorProductDao = doctorProductDao;
	}


}
