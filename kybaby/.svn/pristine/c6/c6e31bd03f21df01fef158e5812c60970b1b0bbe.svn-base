package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorAssessmentTagDao;

/**
 * @ClassName:DoctorAssessmentTagDaoImpl
 * @Description:医生评价标签数据管理接口实现
 * @author Hoolee
 * @date 2015年10月6日下午3:44:55
 */
public class DoctorAssessmentTagDaoImpl extends HibernateDaoSupport implements DoctorAssessmentTagDao  {

	public List<Object[]> getDoctorAssessmentTagByDoctorId(long doctorId) {
		List<Object[]> list=getHibernateTemplate().find("select b.tagName , a.hitCount from DoctorAssessmentTag a,AssessmentTag b where b.id=a.tagId and a.doctorId=?", doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

}
