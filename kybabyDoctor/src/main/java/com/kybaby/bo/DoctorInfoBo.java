package com.kybaby.bo;

import com.kybaby.domain.DoctorInfo;

/**
 * @author sujiantang
 *
 */
public interface DoctorInfoBo {

	//通过电话查找医生信息
	DoctorInfo getDoctorInfoByPhone(String phone);
	//新增医生信息记录
	void save(DoctorInfo doctorInfo);
	//更新医生信息
	void update(DoctorInfo doctorInfo);
	//通过openId查找医生信息
	DoctorInfo getDoctorInfoByOpenId(String openId);
	//注册专用
	DoctorInfo getDoctorInfoByPhoneNum(String phone);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过医生的ID获取到医生的实例
	 * @data: 2015年11月17日下午4:14:03
	 * @param id 医生的ID
	 * @return 该ID对应的医生实例
	 */
	DoctorInfo getDoctorInfoBoById(long id);
	
}
