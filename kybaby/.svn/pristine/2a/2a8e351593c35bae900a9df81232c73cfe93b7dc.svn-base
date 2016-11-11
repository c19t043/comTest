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
	public List<RulesConfigureRecord> getRulesConfigureRecordList() {
		StringBuffer hql = new StringBuffer(" from RulesConfigureRecord p where 1=1  ");
		hql.append(" and p.rulesFieldBasic.isStart='Y' ")
		.append(" and p.ruleBasic.isStart='Y' ")
		.append(" order by p.sort");
		List<RulesConfigureRecord> list = getHibernateTemplate().find(hql.toString());
		if(list.isEmpty())
			return null;
		return list;
	}

	@Override
	public void updateOrderInfo(OrderInfo orderInfo) {
		getHibernateTemplate().update(orderInfo);
	}


}
