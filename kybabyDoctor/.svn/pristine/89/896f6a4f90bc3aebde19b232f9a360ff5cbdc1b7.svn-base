package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.DoctorArticle;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Product;

/**
 * @author sujiantang
 *
 */
public interface HomePageManageBo {

	//查找医生信息
	DoctorInfo getDoctorInfoByDoctorId();
	//查找医生服务产品
	Product getProductById(Long id);
	//查找医生社交标签
	List getDoctorAssessmentTagByDoctorId(Long id);
	//医生发布的专栏
	List<DoctorArticle> getDoctorArticleByDoctorId(Long id);
	//获取医生专业方向名称
	String getMajorNameById(Long id);
}
