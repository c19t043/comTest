package com.kybaby.operationbussiness.investigation.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.operationbussiness.investigation.dao.InvestigationDao;
import com.kybaby.operationbussiness.investigation.domain.InvestigationMessage;
import com.kybaby.operationbussiness.investigation.domain.InvestigationOption;

public class InvestigationDaoImpl extends HibernateDaoSupport implements InvestigationDao {

	@Override
	public Long saveInvestigationMessage(InvestigationMessage investigationMessage) {
		return (Long) this.getHibernateTemplate().save(investigationMessage);
	}

	@Override
	public void saveInvestigationOption(InvestigationOption investigationOption) {
		this.getHibernateTemplate().save(investigationOption);
	}

	@Override
	public List<InvestigationMessage> getInvestigationMessageList(String showStatus) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer("from InvestigationMessage where 1=1");
		if(showStatus != null) {
			if (InvestigationMessage.SHOW_ALL.equals(showStatus.trim())) {
				params.add("0");
				hql.append(" and isShow != ?");
			} else if (InvestigationMessage.SHOW_FRONT.equals(showStatus.trim())) {
				params.add("2");
				hql.append(" and isShow = ?");
			}
		}
		hql.append(" order by optTime desc");
		List<InvestigationMessage> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

}
