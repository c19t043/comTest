package com.kybaby.newbussiness.diseasesanddrug.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.diseasesanddrug.dao.DrugInfoDao;
import com.kybaby.newbussiness.diseasesanddrug.domain.DrugClassification;
import com.kybaby.newbussiness.diseasesanddrug.domain.DrugInfo;

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
		StringBuffer hql = new StringBuffer(" from DrugInfo p where 1=1 ");
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
		hql.append(" order by p.drugClassification.className,p.commonName");
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

	@Override
	public Long saveOrUpdateDrugInfo(DrugInfo drugInfo) {
		if(drugInfo == null) return null;
		Long id = null;
		if(drugInfo.getId() == null){
			id = (Long) this.getHibernateTemplate().save(drugInfo);
		}else{
			id = drugInfo.getId() ;
			this.getHibernateTemplate().update(drugInfo);
		}
		return id;
	}

	@Override
	public Long saveOrUpdateDrugClassification(
			DrugClassification drugClassification) {
		if(drugClassification == null) return null;
		Long id = null;
		if(drugClassification.getId() == null){
			id = (Long) this.getHibernateTemplate().save(drugClassification);
		}else{
			id = drugClassification.getId() ;
			this.getHibernateTemplate().update(drugClassification);
		}
		return id;
	}

}
