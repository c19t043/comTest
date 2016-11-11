package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.FunctionBo;
import com.kybaby.dao.FunctionDao;
import com.kybaby.domain.FunctionList;
import com.kybaby.domain.FunctionParent;

/**
 * @ClassName:FunctionBoImpl
 * @Description:功能的事务管理实现
 * @author Hoolee
 * @date 2015年9月5日下午12:11:15
 */
public class FunctionBoImpl implements FunctionBo {
	FunctionDao functionDao;
	
	public List<FunctionParent> getAllFunctionParentList() {
		return functionDao.getAllFunctionParentList();
	}

	public List<FunctionList> getSomeFunctionListByParentId(long id) {
		return functionDao.getSomeFunctionListByParentId(id);
	}

	/**
	 * @return the functionDao
	 */
	public FunctionDao getFunctionDao() {
		return functionDao;
	}

	/**
	 * @param functionDao the functionDao to set
	 */
	public void setFunctionDao(FunctionDao functionDao) {
		this.functionDao = functionDao;
	}
}
