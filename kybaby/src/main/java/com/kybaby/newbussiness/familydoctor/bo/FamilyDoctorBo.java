package com.kybaby.newbussiness.familydoctor.bo;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceMember;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceTeams;

public interface FamilyDoctorBo {
	/**
	 * 根据id得到服务包信息
	 * @param id
	 * @return
	 */
	FdServicePackage getFdServicePackageById(Long id);
	/**
	 * 得到开放了家庭医生服务包的机构列表
	 * @return
	 */
	List<HospitalBasicInfo> getHospitalBasicInfoListByPackage(FdServicePackage fdServicePackage);
	/**
	 * 检查家庭医生门诊订单是否存在
	 * @param date
	 * @param time
	 * @param doctorInfo
	 * @return true表示已有    false表示没有
	 */
	Boolean checkOrderIsExist(String date,String time,String clinicAddress,DoctorInfo doctorInfo,UserInfo userInfo);
}
