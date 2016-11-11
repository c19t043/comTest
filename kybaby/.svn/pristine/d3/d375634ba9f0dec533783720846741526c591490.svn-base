package com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceOrder;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProject;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.dao.OrganSetMealDao;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetChildCareRecode;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetMeal;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetMealHospital;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetMeatOrder;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetPro;
@SuppressWarnings("all")
public class OrganSetMealDaoImpl extends HibernateDaoSupport implements OrganSetMealDao{

	@Override
	public List<OrganSetMeal> getOrganSetMealList(OrganSetMeal organSetMeal) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from OrganSetMeal p where 1=1");
		if(organSetMeal != null){
			
		}
		hql.append(" and p.isEnable = 'Y' ");
		List<OrganSetMeal> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public List<OrganSetMeatOrder> getOrganSetMeatOrderList(
			OrganSetMeal organSetMeal, OrganSetPro organSetPro,
			UserInfo userInfo,OrganSetMeatOrder organSetMeatOrder) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from OrganSetMeatOrder p where 1=1");
		if(organSetMeal != null){
			if(organSetMeal.getId() != null){
				hql.append(" and p.organSetMeal.id = ?");
				params.add(organSetMeal.getId());
			}
		}
		if(organSetPro != null){
			if(organSetPro.getId() != null){
				hql.append(" and p.organSetPro.id = ?");
				params.add(organSetPro.getId());
			}
		}
		if(userInfo != null){
			if(userInfo.getId() != null){
				hql.append(" and p.userInfo.id = ?");
				params.add(userInfo.getId());
			}
		}
		if(organSetMeatOrder != null){
			if(StringUtils.isNotEmpty(organSetMeatOrder.getOrderStatus())){
				hql.append(" and p.orderStatus = ?");
				params.add(organSetMeatOrder.getOrderStatus());
			}
		}
		List<OrganSetMeatOrder> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public Long saveOrUpdateOrganSetMeatOrder(
			OrganSetMeatOrder organSetMeatOrder) {
		Long id = null;
		if(organSetMeatOrder == null)
			return id;
		if(organSetMeatOrder.getId() == null){
			id = (Long) this.getHibernateTemplate().save(organSetMeatOrder);
		}else{
			id = organSetMeatOrder.getId() ;
			this.getHibernateTemplate().update(organSetMeatOrder);
		}
		return id;
	}

	@Override
	public OrganSetMeatOrder getOrganSetMeatOrderById(Long id) {
		return getHibernateTemplate().get(OrganSetMeatOrder.class, id);
	}

	@Override
	public OrganSetMeal getOrganSetMealById(Long id) {
		return getHibernateTemplate().get(OrganSetMeal.class, id);
	}

	@Override
	public List<OrganSetPro> getOrganSetProList(OrganSetPro parent,OrganSetPro organSetPro,
			 HospitalBasicInfo hospitalBasicInfo,OrganSetMeal organSetMeal) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from OrganSetPro p where 1=1");
		if(parent != null){
			if(parent.getId() != null){
				hql.append(" and p.parentOrganSetPro.id = ?");
				params.add(parent.getId());
			}
		}else{
			hql.append(" and p.parentOrganSetPro.id is null");
		}
		if(organSetPro != null){
			if(organSetPro.getId() != null){
				hql.append(" and p.id = ?");
				params.add(organSetPro.getId());
			}
		}
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null){
				hql.append(" and p.hospitalBasicInfo.id = ?");
				params.add(hospitalBasicInfo.getId());
			}
		}
		if(organSetMeal != null){
			if(organSetMeal.getId() != null){
				hql.append(" and p.organSetMeal.id = ?");
				params.add(organSetMeal.getId());
			}
		}
		hql.append("order by serviceTimeLength");
		List<OrganSetPro> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public List<ChildcareProject> getChildcareProjectListBySome(
			HospitalBasicInfo hospitalBasicInfo, String minMonthAge,
			String maxMonthAge) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from ChildcareProject p where 1=1 and p.childcareProjectType.isEnable='Y' ");
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null){
				hql.append(" and p.childcareProjectType.ascriptionOrgan.id=?");
				params.add(hospitalBasicInfo.getId());
			}
		}
		if(minMonthAge != null && !"".equals(minMonthAge.trim())){
			hql.append(" and p.maxMonthAge >= "+minMonthAge);
		}
		if(maxMonthAge != null && !"".equals(maxMonthAge.trim())){
			hql.append(" and p.maxMonthAge <= "+maxMonthAge);
		}
		List<ChildcareProject> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public OrganSetPro getOrganSetProById(Long id) {
		return getHibernateTemplate().get(OrganSetPro.class, id);
	}

	@Override
	public void saveOrUpdateOrganSetChildCareRecode(
			OrganSetChildCareRecode organSetChildCareRecode) {
		if(organSetChildCareRecode == null)
			return ;
		if(organSetChildCareRecode.getId() == null){
			 this.getHibernateTemplate().save(organSetChildCareRecode);
		}else{
			this.getHibernateTemplate().update(organSetChildCareRecode);
		}
	}

	@Override
	public List<OrganSetMealHospital> getOrganSetMealHospitalList(
			OrganSetMeal organSetMeal, HospitalBasicInfo hospitalBasicInfo) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from OrganSetMealHospital p where 1=1");
		if(organSetMeal != null){
			if(organSetMeal.getId() != null){
				hql.append(" and p.organSetMeal.id = ?");
				params.add(organSetMeal.getId());
			}
		}
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null){
				hql.append(" and p.hospitalBasicInfo.id = ?");
				params.add(hospitalBasicInfo.getId());
			}
		}
		hql.append(" and p.organSetMeal.isEnable = 'Y' ");
		List<OrganSetMealHospital> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

}
