package com.kybaby.newbussiness.doctorclinic.bo;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;

public interface FamilyDoctorServeService {
	/**
	 * 得到某个服务类型集合
	 * @param parentServiceType 父服务类型
	 * @return
	 */
	List<DoctorServiceType> getDoctorServiceTypeList(String parentServiceType,DoctorInfo doctorInfo);
}
