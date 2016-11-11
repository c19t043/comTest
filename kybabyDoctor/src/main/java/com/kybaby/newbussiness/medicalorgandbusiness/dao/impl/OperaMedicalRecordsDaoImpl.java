package com.kybaby.newbussiness.medicalorgandbusiness.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.medicalorgandbusiness.dao.OperaMedicalRecordsDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OperaMedicalRecords;

public class OperaMedicalRecordsDaoImpl extends HibernateDaoSupport implements OperaMedicalRecordsDao{

	@Override
	public List<OperaMedicalRecords> getOperaMedicalRecordsList(
			OperaMedicalRecords operaMedicalRecords) {
		List params = new ArrayList<>();
		StringBuffer hql = new StringBuffer(" from OperaMedicalRecords p where 1=1 ");
		if(operaMedicalRecords != null){
			if(StringUtils.isNotEmpty(operaMedicalRecords.getOperateTime())){
				hql.append(" and p.operateTime like ?");
				params.add("%" + operaMedicalRecords.getOperateTime() + "%");
			}
			if(operaMedicalRecords.getOrganOperator() != null){
				if(operaMedicalRecords.getOrganOperator().getId() != null){
					hql.append(" and p.organOperator.id = ?");
					params.add( operaMedicalRecords.getOrganOperator().getId() );
				}
			}
		}
		hql.append(" order by p.operateTime desc");
		List<OperaMedicalRecords> list =  this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public void saveOrUpdateOperaMedicalRecords(
			OperaMedicalRecords operaMedicalRecords) {
		this.getHibernateTemplate().saveOrUpdate(operaMedicalRecords);
	}

	@Override
	public OperaMedicalRecords getOperaMedicalRecordsById(Long id) {
		return this.getHibernateTemplate().get(OperaMedicalRecords.class, id);
	}

}
