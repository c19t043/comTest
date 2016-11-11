package com.kybaby.newbussiness.senddoctor.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.OrderInfo;
import com.kybaby.newbussiness.senddoctor.dao.SendDoctorDao;
import com.kybaby.newbussiness.senddoctor.domain.RuleBasic;
import com.kybaby.newbussiness.senddoctor.domain.RulesConfigureRecord;
import com.kybaby.newbussiness.senddoctor.domain.RulesFieldBasic;
import com.kybaby.newbussiness.senddoctor.fo.DoctorInfoForSort;

public class SendDoctorDaoImpl extends HibernateDaoSupport implements SendDoctorDao{

	@Override
	public Long saveRuleBasic(RuleBasic ruleBasic) {
		return (Long) getHibernateTemplate().save(ruleBasic);
		
	}

	@Override
	public void updateRuleBasic(RuleBasic ruleBasic) {
		RuleBasic old = getHibernateTemplate().get(RuleBasic.class, ruleBasic.getRuleBasicId());
		BeanUtils.copyProperties(ruleBasic, old,new String[]{"createTime"});
		getHibernateTemplate().update(old);
		
	}

	@SuppressWarnings("unchecked")
	public List<RuleBasic> getRuleBasicList(RuleBasic ruleBasic) {
		StringBuffer hql = new StringBuffer("from RuleBasic p where 1=1");
		if(ruleBasic != null){
			if(ruleBasic.getIsStart() != null && !"".equals(ruleBasic.getIsStart().trim())){
				hql.append(" and p.isStart='"+ruleBasic.getIsStart().trim()+"'");
			}
			if(ruleBasic.getRuleName() != null && !"".equals(ruleBasic.getRuleName().trim())){
				hql.append(" and p.ruleName like '%"+ruleBasic.getRuleName().trim()+"%'");
			}
		}
		List<RuleBasic> list = getHibernateTemplate().find(hql.toString());
		if(list.isEmpty()){
			return null;
		}else{
			return list;
		}
	}

	@Override
	public List<RulesFieldBasic> getRulesFieldBasicList(
			RulesFieldBasic rulesFieldBasic) {
		StringBuffer hql = new StringBuffer("from RulesFieldBasic p where 1=1 and p.isStart='Y'");
		List<RulesFieldBasic> list = getHibernateTemplate().find(hql.toString());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@SuppressWarnings("all")
	public List<DoctorInfoForSort> getDoctorInfoForSortList(String sql) {
		List<DoctorInfoForSort> doctorInfoForSortList = new ArrayList<DoctorInfoForSort>();
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql).
				setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//返回一个map,KEY:为DB中名称一致（大小写一致）
		List list =  query.list();
		if(list.isEmpty()){
			return null;
		}
		for(int i=0;i<list.size();i++){
			DoctorInfoForSort difs = new DoctorInfoForSort();
			//遍历list时就可以
			Map map = (Map)list.get(i);
			Long doctorId = map.get("id")==null?null:Long.valueOf(map.get("id").toString());
			difs.setDoctorId(doctorId);
			Long orderSum =  map.get("orderSum")==null?null:Long.valueOf(map.get("orderSum").toString());
			difs.setOrderSum(orderSum);
			Long openSum = map.get("openSum")==null?null:Long.valueOf(map.get("openSum").toString());
			difs.setOpenTimeSum(openSum);
			doctorInfoForSortList.add(difs);
		}
		return doctorInfoForSortList;
	}

	@Override
	public List<RulesConfigureRecord> getRulesConfigureRecordList(RuleBasic ruleBasic) {
		StringBuffer hql = new StringBuffer(" from RulesConfigureRecord p where 1=1");
		if(ruleBasic != null){
			if(ruleBasic.getRuleBasicId() != null){
				hql.append("  and p.ruleBasic.ruleBasicId=").append(ruleBasic.getRuleBasicId());
			}
		}
		hql.append(" order by p.sort");
		List<RulesConfigureRecord> list = getHibernateTemplate().find(hql.toString());
		if(list.isEmpty())
			return null;
		return list;
	}

	@Override
	public void updateOrderInfo(OrderInfo orderInfo) {
		getHibernateTemplate().update(orderInfo);
	}

	@Override
	public Long saveOrUpdateRulesConfigureRecord(
			RulesConfigureRecord rulesConfigureRecord) {
		Long id = null;
		if(rulesConfigureRecord == null) return id;
		if(rulesConfigureRecord.getId() == null){
			id = (Long) this.getHibernateTemplate().save(rulesConfigureRecord);
		}else{
			id = rulesConfigureRecord.getId();
			RulesConfigureRecord old = getHibernateTemplate().get(RulesConfigureRecord.class, id);
			old.setSort(rulesConfigureRecord.getSort());
			this.getHibernateTemplate().update(old);
		}
		return id;
	}

	@Override
	public RuleBasic getRuleBasicById(Long ruleBasicId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(RuleBasic.class, ruleBasicId);
	}

	@Override
	public void updateRuleBasicBySql(RuleBasic ruleBasic) {
		StringBuffer sql = new StringBuffer(" update rule_basic p set is_start='N' where 1=1 ");
		if(ruleBasic != null){
			if(ruleBasic.getRuleBasicId() != null){
				sql.append(" and p.rule_basic_id !='"+ruleBasic.getRuleBasicId()+"'");
			}
		}
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.createSQLQuery(sql.toString()).executeUpdate();
		session.close();
	}

}
