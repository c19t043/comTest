package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.EvaluateBo;
import com.kybaby.dao.EvaluateDao;

public class EvaluateBoImpl implements EvaluateBo{

	EvaluateDao evaluateDao;
	public List getEvaluateByOrderNum(String orderNum) {
		// TODO Auto-generated method stub
		return null;
	}
	public EvaluateDao getEvaluateDao() {
		return evaluateDao;
	}
	public void setEvaluateDao(EvaluateDao evaluateDao) {
		this.evaluateDao = evaluateDao;
	}

	//2.9.1评价管理
//	List getEvaluateByOrderNum(String orderNum);//从评价表，社交标签表找到标签与3个质量控制星级
	
}
