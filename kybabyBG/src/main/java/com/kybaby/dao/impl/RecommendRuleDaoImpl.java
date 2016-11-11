package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.RecommendRuleDao;
import com.kybaby.domain.RecommendRule;

public class RecommendRuleDaoImpl extends HibernateDaoSupport implements RecommendRuleDao {

	@Override
	public List<RecommendRule> getAllRecommendRule() {
		List list=getHibernateTemplate().find("FROM RecommendRule");
		if(list.isEmpty()==true)
		{
			return null;
		}
		else
		{
			return list;
		}
	}

	@Override
	public RecommendRule getRecommendRuleById(long id) {
		List list=getHibernateTemplate().find("FROM RecommendRule WHERE id=?",id);
		if(list.isEmpty()==true)
		{
		return null;
		}
		else
		{
			return (RecommendRule)list.get(0);
		}
	}

	@Override
	public List getcountList() {
		String hql="SELECT ruleName, COUNT(amount),COUNT(points),COUNT(coupon) FROM  RecommendRule  WHERE ruleStatus='Y' GROUP BY ruleName";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
		{
			return null;
		}
		else
		{
			return list;
		}
	}

}
