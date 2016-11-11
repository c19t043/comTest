package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.RecommendRuleBo;
import com.kybaby.dao.RecommendRuleDao;
import com.kybaby.domain.RecommendRule;

public class RecommendRuleBoImpl  implements    RecommendRuleBo{

	RecommendRuleDao recommendRuleDao;
	@Override
	public List<RecommendRule> getAllRecommendRule() {
		// TODO Auto-generated method stub
		return recommendRuleDao.getAllRecommendRule();
	}

	@Override
	public RecommendRule getRecommendRuleById(long id) {
		// TODO Auto-generated method stub
		return recommendRuleDao.getRecommendRuleById(id);
	}

	public RecommendRuleDao getRecommendRuleDao() {
		return recommendRuleDao;
	}

	public void setRecommendRuleDao(RecommendRuleDao recommendRuleDao) {
		this.recommendRuleDao = recommendRuleDao;
	}

	@Override
	public List getcountList() {
		// TODO Auto-generated method stub
		return recommendRuleDao.getcountList();
	}

//	//2.10.2 推荐规则管理
//	List<RecommendRule> getAllRecommendRule();//得到所有已定义的推荐规则
//	RecommendRule getRecommendRuleById(long id);//通过推荐规则id得到该实例
	
}
