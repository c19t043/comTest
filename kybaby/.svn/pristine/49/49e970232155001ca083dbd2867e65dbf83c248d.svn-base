package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.DoctorAssessmentTag;

/**
 * @ClassName:DoctorAssessmentTagBo
 * @Description:医生评价标签事物管理接口
 * @author Hoolee
 * @date 2015年9月14日下午11:13:16
 */
public interface DoctorAssessmentTagBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某一个医生的评价标签名字和被评论的次数
	 * @data:2015年10月6日15:41:24
	 * @param doctorId 医生的ID
	 * @return 某一个医生的评价标签名字和被评论的次数
	 */
	List<Object[]> getDoctorAssessmentTagByDoctorId(long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新医生的对应社交标签的记录
	 * @data: 2015年9月21日下午4:23:41
	 * @param doctorId 医生的ID
	 * @param tagIds 医生社交标签组合成的字符串
	 */
	void updateDoctorAssessmentTag(long doctorId,String tagIds);
}
