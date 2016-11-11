package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.HealthPath;

public interface HealthPathBo {
    // 2.8.5 健康路径管理
	List<HealthPath> getAllHealthPath();//得到所有的健康路径
	HealthPath getHealthPathByName(String name);//通过健康路径名字得到该实例
	HealthPath getHealthPathById(long id);      //通过健康路径id得到该实例
	
	//2.8.4 健康计划管理
	List getIdAndNameOfHealthPathByStatus(String status); //通过status找到该健康路径的id,与healthPathName
}
