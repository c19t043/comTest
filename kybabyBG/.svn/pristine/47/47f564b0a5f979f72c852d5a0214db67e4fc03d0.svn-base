package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.AssessmentTagBo;
import com.kybaby.dao.AssessmentTagDao;
import com.kybaby.domain.AssessmentTag;

public class AssessmentTagBoImpl implements  AssessmentTagBo {

	AssessmentTagDao assessmentTagDao;
	@Override
	public List<AssessmentTag> getAllAssessmentTag() {
		// TODO Auto-generated method stub
		return assessmentTagDao.getAllAssessmentTag();
	}

	@Override
	public AssessmentTag getAssessmentTagById(long id) {
		// TODO Auto-generated method stub
		return assessmentTagDao.getAssessmentTagById(id);
	}

	@Override
	public AssessmentTag getAssessmentTagByComments(String comments) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssessmentTag getAssessmentTagByName(String name) {
		// TODO Auto-generated method stub
		return assessmentTagDao.getAssessmentTagByName(name);
	}

	public AssessmentTagDao getAssessmentTagDao() {
		return assessmentTagDao;
	}

	public void setAssessmentTagDao(AssessmentTagDao assessmentTagDao) {
		this.assessmentTagDao = assessmentTagDao;
	}

//	// 2.3.2 评价标签管理
//	List<AssessmentTag> getAllAssessmentTag();  // 得到所有的评价标签
//	AssessmentTag getAssessmentTagById(long id);// 通过id得到该评价标签实例
//	AssessmentTag getAssessmentTagByComments(String comments); //通过comments找到对应的质量控制类标签
//	AssessmentTag getAssessmentTagByName(String name);//通过评价标签判断是否存在该标签,不为null表示存在
}
