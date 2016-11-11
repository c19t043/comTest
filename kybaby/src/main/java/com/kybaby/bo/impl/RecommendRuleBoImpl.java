package com.kybaby.bo.impl;

import com.kybaby.bo.RecommendRuleBo;
import com.kybaby.dao.RecommendRuleDao;
import com.kybaby.domain.RecommendRule;

/**
 * @ClassName:RecommendRuleBoImpl
 * @Description:推荐规则事务管理实现
 * @author Hoolee
 * @date 2015年9月27日下午5:30:11
 */
public class RecommendRuleBoImpl implements RecommendRuleBo {
	
	RecommendRuleDao recommendRuleDao;
	
	public RecommendRule getSomeCanUseRule(String ruleName) {
		return recommendRuleDao.getSomeCanUseRule(ruleName);
	}

	public RecommendRuleDao getRecommendRuleDao() {
		return recommendRuleDao;
	}

	public void setRecommendRuleDao(RecommendRuleDao recommendRuleDao) {
		this.recommendRuleDao = recommendRuleDao;
	}

}
