package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.RecommendRuleDao;
import com.kybaby.domain.RecommendRule;

/**
 * @ClassName:RecommendRuleDaoImpl
 * @Description:推荐规则数据管理接口实现
 * @author Hoolee
 * @date 2015年9月27日下午5:33:20
 */
public class RecommendRuleDaoImpl extends HibernateDaoSupport implements RecommendRuleDao {

	public RecommendRule getSomeCanUseRule(String ruleName) {
		List<RecommendRule> list=getHibernateTemplate().find("from RecommendRule where ruleName=? and ruleStatus='Y'",ruleName);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

}
