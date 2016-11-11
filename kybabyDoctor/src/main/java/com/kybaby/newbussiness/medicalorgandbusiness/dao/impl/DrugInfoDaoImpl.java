package com.kybaby.newbussiness.medicalorgandbusiness.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.medicalorgandbusiness.dao.DrugInfoDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DrugClassification;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DrugInfo;

public class DrugInfoDaoImpl extends HibernateDaoSupport implements DrugInfoDao {

	@Override
	public List<DrugClassification> getDrugClassificationList(
			DrugClassification drugClassification) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer(" from DrugClassification p where 1=1 ");
		if(drugClassification != null){
			if(drugClassification.getParentClass() != null && drugClassification.getParentClass().getId() != null){
				hql.append(" and p.parentClass.id=?");
				params.add(drugClassification.getParentClass().getId());
			}
		}
		hql.append(" order by p.className");
		List<DrugClassification> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<DrugInfo> getDrugInfoList(
			DrugClassification drugClassification, DrugInfo drugInfo) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer(" from DrugInfo p where 1=1 and isSale='Y'");
		if(drugClassification != null){
			if(drugClassification.getId() != null){
				hql.append(" and p.drugClassification.id=?");
				params.add(drugClassification.getId());
			}
		}
		if(drugInfo != null){
			if(drugInfo.getCommonName() != null && !"".equals(drugInfo.getCommonName().trim())){
				hql.append(" and p.commonName like ?");
				params.add("%"+drugInfo.getCommonName().trim()+"%");
			}
		}
		hql.append(" order by p.commonName");
		List<DrugInfo> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public DrugInfo getDrugInfoById(Long id) {
		return this.getHibernateTemplate().get(DrugInfo.class, id);
	}

}
