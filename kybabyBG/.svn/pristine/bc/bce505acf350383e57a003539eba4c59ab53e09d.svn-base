package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.AssessmentTag;

public interface AssessmentTagDao {

	// 2.3.2 评价标签管理
	List<AssessmentTag> getAllAssessmentTag();  // 得到所有的评价标签
	AssessmentTag getAssessmentTagById(long id);// 通过id得到该评价标签实例
	AssessmentTag getAssessmentTagByComments(String comments); //通过comments找到对应的质量控制类标签
	AssessmentTag getAssessmentTagByName(String name);//通过评价标签判断是否存在该标签,不为null表示存在
}
