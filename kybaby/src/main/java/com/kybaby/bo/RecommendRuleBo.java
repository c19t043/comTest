package com.kybaby.bo;

import com.kybaby.domain.RecommendRule;

/**
 * @ClassName:RecommendRuleBo
 * @Description:推荐规则的事物管理接口
 * @author Hoolee
 * @date 2015年9月25日下午1:52:42
 */
public interface RecommendRuleBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过推荐规则的名称获取到推荐规则的实例
	 * @data: 2015年9月25日下午1:54:17
	 * @param ruleName 推荐规则名称
	 * @return 推荐规则的实例
	 */
	RecommendRule getSomeCanUseRule(String ruleName);
}
