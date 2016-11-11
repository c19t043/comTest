package com.java.medicalorgandbusiness.orgsetpro.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.java.common.lang.StringUtils;
import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.vo.OrganSetMeal;
import com.java.medicalorgandbusiness.orgsetmeal.vo.OrganSetMealHospital;
import com.java.medicalorgandbusiness.orgsetpro.service.OrganSetProService;
import com.java.medicalorgandbusiness.orgsetpro.vo.OrganSetPro;
import com.java.platform.user.service.ServiceImpl;

public class OrganSetProServiceImpl extends ServiceImpl implements
OrganSetProService {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}
	
	/**
	 * 查询套餐机构中间表所有记录
	 * @param model
	 * @param organSetMealHospital
	 * @return
	 */
	public List<OrganSetMealHospital> getOrganSetMealHospitalList(
			PageSortModel model, OrganSetMealHospital organSetMealHospital){
		
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		
		sb.append("from OrganSetMealHospital a where 1=1 and a.organSetMeal.isEnable='Y' ");
		
		if(organSetMealHospital!=null){
			if(organSetMealHospital.getHospitalBasicInfo()!=null
					&&StringUtils.isNotBlank(organSetMealHospital.getHospitalBasicInfo().getHospitalLname())){
				sb.append(" and a.hospitalBasicInfo.hospitalLname like :hospitalLname");
				params.put("hospitalLname", "%"+organSetMealHospital.getHospitalBasicInfo().getHospitalLname()+"%");
			}
			if(organSetMealHospital.getOrganSetMeal()!=null&&StringUtils.isNotBlank(organSetMealHospital.getOrganSetMeal().getPackageName())){
				sb.append(" and a.organSetMeal.packageName like :packageName");
				params.put("packageName", "%"+organSetMealHospital.getOrganSetMeal().getPackageName()+"%");
			}
			if(organSetMealHospital.getHospitalBasicInfo()!=null
					&&organSetMealHospital.getHospitalBasicInfo().getId() != null){
				sb.append(" and a.hospitalBasicInfo.id = :hospitalId");
				params.put("hospitalId", organSetMealHospital.getHospitalBasicInfo().getId());
			}
			if(organSetMealHospital.getOrganSetMeal()!=null&&organSetMealHospital.getOrganSetMeal().getId() != null){
				sb.append(" and a.organSetMeal.id = :packageId");
				params.put("packageId", organSetMealHospital.getOrganSetMeal().getId());
			}
		}
		List<OrganSetMealHospital> list = new ArrayList<>();
		if(model != null){
			list =  (List<OrganSetMealHospital>) super.listForEc(sb.toString(), model, params);
		}else{
			list =  super.list(sb.toString(), -1, -1, params);
		}
		if(list.isEmpty())return null;
		return list;
	}
	
	
	/**
	 * 根据条件，查询产品列表
	 * 条件
	 * 1.产品名称
	 * 2.套餐名称
	 * @param model
	 * @param organSetPro
	 * @return
	 */
	public List<OrganSetPro> getOrganSetProList(PageSortModel model,OrganSetPro organSetPro,boolean getParentFlag){
		
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		
		sb.append("from OrganSetPro a where 1=1 ");
		
		if(getParentFlag){
			sb.append(" and a.parentOrganSetPro.id is null ");
		}
		
		if(organSetPro!=null){
			if(StringUtils.isNotBlank(organSetPro.getProName())){
				sb.append(" and a.proName like :proName");
				params.put("proName", "%"+organSetPro.getProName()+"%");
			}
			if(organSetPro.getOrganSetMeal()!=null&&StringUtils.isNotBlank(organSetPro.getOrganSetMeal().getPackageName())){
				sb.append(" and a.organSetMeal.packageName like :packageName");
				params.put("packageName", "%"+organSetPro.getOrganSetMeal().getPackageName()+"%");
			}
		}
		
		List<OrganSetPro> list = (List<OrganSetPro>) super.listForEc(sb.toString(), model, params);
		if(list.isEmpty())return null;
		return list;
	}
	
	/**
	 * 根据id,查询产品
	 * @param id
	 * @return
	 */
	public OrganSetPro getOrganSetPro(Long id){
		OrganSetPro organSetPro = super.get(id, OrganSetPro.class);
		
		if(organSetPro==null)return null;
		return organSetPro;
	}
	/**
	 * 保存or更新产品
	 * @param organSetPro
	 * @return
	 */
	public boolean saveOrganSetPro(OrganSetPro organSetPro){
		super.add(organSetPro);
		
		if(organSetPro.getId()!=null) return true;
		else return false;
	}
	/**
	 * 更新产品
	 * @param organSetPro
	 */
	public void updateOrganSetPro(OrganSetPro organSetPro){
		OrganSetPro pro_query = super.get(organSetPro.getId(), OrganSetPro.class);
		if(StringUtils.isBlank(pro_query.getProImg())){
			BeanUtils.copyProperties(organSetPro, pro_query, new String[]{"id"});
		}else{
			BeanUtils.copyProperties(organSetPro, pro_query, new String[]{"id","proImg"});
		}
		super.edit(pro_query);
	}
	
}
