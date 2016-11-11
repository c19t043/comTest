package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.SymptomTagBo;
import com.kybaby.dao.SymptomTagDao;
import com.kybaby.domain.SymptomTag;

public class SymptomTagBoImpl implements SymptomTagBo{

	SymptomTagDao symptomTagDao;
	@Override
	public List<SymptomTag> getAllSymptomTag() {
		// TODO Auto-generated method stub
		return symptomTagDao.getAllSymptomTag();
	}

	@Override
	public SymptomTag getSymptomTagById(long id) {
		// TODO Auto-generated method stub
		return symptomTagDao.getSymptomTagById(id);
	}

	@Override
	public SymptomTag getSymptomTagByName(String name) {
		// TODO Auto-generated method stub
		return symptomTagDao.getSymptomTagByName(name);
	}

	public SymptomTagDao getSymptomTagDao() {
		return symptomTagDao;
	}

	public void setSymptomTagDao(SymptomTagDao symptomTagDao) {
		this.symptomTagDao = symptomTagDao;
	}

	@Override
	public List getSymptomTagIdAndName() {
		// TODO Auto-generated method stub
		return symptomTagDao.getSymptomTagIdAndName();
	}

//	//2.3.1症状标签管理
//	List<SymptomTag> getAllSymptomTag();  //所有标签集合
//	SymptomTag getSymptomTagById(long id);       //通过标签Id找到该实例
//	SymptomTag getSymptomTagByName(String name); //通过标签症状标签名字找到该实例，不为null则不重复
}
