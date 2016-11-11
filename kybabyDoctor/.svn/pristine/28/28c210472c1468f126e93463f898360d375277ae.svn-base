package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.DoctorGoodField;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Major;
import com.kybaby.domain.Position;
import com.kybaby.domain.SymptomTag;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.fo.DoctorServiceTypeFo;

/**
 * @author sujiantang
 *
 */
public interface DoctorIdentifyBo {

	//通过医生手机号查找医生信息
	DoctorInfo getDoctorInfoByDoctorId();
	//更新医生信息表
	void update(DoctorInfo doctorInfo);
	//获取医生所有职称
	List<Position> getAllPosition();
	//获取对应职称可以服务的产品
	List getProductNameByRank(Long rank);
	//获取所有专业方向
	List<Major> getAllMajor();
	/**
	 *  得到擅长领域
	 * @return
	 */
	List<DoctorGoodField> getAllDoctorGoodField();
	//获取所有咨询标签
	List<SymptomTag> getAllSymptomTag();
	//获取医生职称名
	String getOptionById(Long doctorTitle);
	/**
	 * 得到医院信息列表
	 * @param hospitalBasicInfo
	 * @return
	 */
	List<HospitalBasicInfo> getHospitalBasicInfoList(HospitalBasicInfo hospitalBasicInfo);
	/**
	 * 得到所有可服务标签数结构
	 * @return
	 */
	List<DoctorServiceTypeFo> getAllDoctorServiceTypeFo();
	/**
	 * 保存医生服务项目关系表
	 * @param doctorInfo
	 * @param serviceTypeIds
	 */
	void saveDoctorServiceContent(DoctorInfo doctorInfo,String serviceTypeIds);
}
