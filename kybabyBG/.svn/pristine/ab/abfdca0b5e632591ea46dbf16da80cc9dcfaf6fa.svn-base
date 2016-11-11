package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.DoctorGoodField;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Major;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;

public interface MajorBo {

	//2.3.7 专业方向
	List<Major> getAllMajor();//显示所有专业方向
	Major getMajorByName(String name);//通过name查找该实例，查找重复专业方向名字
	Major getMajorById(long id);//通过专业方向id找到该实例
	/**
	 * 得到所有的擅长领域列表
	 * @return
	 */
	List<DoctorGoodField> getAllGoodField(DoctorGoodField doctorGoodField);
	/**
	 * 保存或更新擅长领域
	 * @param doctorGoodField
	 * @return
	 */
	Long saveOrupdateGoodField(DoctorGoodField doctorGoodField);
	/**
	 * 得到开放服务内容列表
	 * @param doctorServiceType
	 * @return
	 */
	List<DoctorServiceType> getDoctorServiceTypeList(DoctorServiceType doctorServiceType);
	/**
	 * 保存医生服务项目关系表
	 * @param doctorInfo
	 * @param serviceTypeIds
	 */
	void saveDoctorServiceContent(DoctorInfo doctorInfo,String serviceTypeIds);
}
