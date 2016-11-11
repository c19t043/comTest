package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.SymptomTag;

public interface SymptomTagDao {
    
	List<SymptomTag> getAllSymptomTag();  //所有标签集合
	SymptomTag getSymptomTagById(long id);       //通过标签Id找到该实例
	SymptomTag getSymptomTagByName(String name);
	List getSymptomTagIdAndName();
}
