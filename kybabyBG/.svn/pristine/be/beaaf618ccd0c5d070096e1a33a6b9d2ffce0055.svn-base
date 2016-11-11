package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.SubsidyBo;
import com.kybaby.dao.SubsidyDao;
import com.kybaby.domain.Subsidy;

public class SubsidyBoImpl  implements SubsidyBo {

	SubsidyDao  subsidyDao;
	@Override
	public Subsidy getSubsidyById(long id) {
		// TODO Auto-generated method stub
		return subsidyDao.getSubsidyById(id);
	}

	@Override
	public List<Subsidy> getAllSubsidy() {
		// TODO Auto-generated method stub
		return subsidyDao.getAllSubsidy();
	}

	public SubsidyDao getSubsidyDao() {
		return subsidyDao;
	}

	public void setSubsidyDao(SubsidyDao subsidyDao) {
		this.subsidyDao = subsidyDao;
	}

//	//2.3.5 补贴规则
//	Subsidy getSubsidyById(long id);//通过id找到该补贴规则实例
//	List<Subsidy> getAllSubsidy();//返回状态Y和N的补贴规则
}
