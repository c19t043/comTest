package com.kybaby.dao;

import com.kybaby.domain.RecommendRule;

/**
 * @ClassName:RecommendRuleDao
 * @Description:推荐规则数据管理接口
 * @author Hoolee
 * @date 2015年9月27日下午5:31:48
 */
public interface RecommendRuleDao {
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过推荐规则的名称获取到推荐规则的实例
	 * @data: 2015年9月27日17:32:51
	 * @param ruleName 推荐规则名称
	 * @return 推荐规则的实例
	 */
	RecommendRule getSomeCanUseRule(String ruleName);
}
