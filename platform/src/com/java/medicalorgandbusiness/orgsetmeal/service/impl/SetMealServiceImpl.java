package com.java.medicalorgandbusiness.orgsetmeal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.java.common.lang.StringUtils;
import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.service.SetMealService;
import com.java.medicalorgandbusiness.orgsetmeal.vo.OrganSetMeal;
import com.java.medicalorgandbusiness.orgsetmeal.vo.OrganSetMealHospital;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.platform.user.service.ServiceImpl;

public class SetMealServiceImpl extends ServiceImpl implements
SetMealService {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}
	/**
	 * 根据套餐id获取机构列表
	 * @param mealID 有套餐ID,查询套餐关联机构，否则查询所有机构
	 * @return
	 */
	public List<HospitalBasicInfo> getHospitalBasicInfoList(PageSortModel model,HospitalBasicInfo hospitalBasicInfo,Long mealID){
		
		List<HospitalBasicInfo> list = new ArrayList<HospitalBasicInfo>();
		
		if(mealID!=null){
			/*
			 * 1.根据套餐id,查询套餐机构中间表
			 * 2.返回机构列表
			 */
			List<OrganSetMealHospital> middle_list = super.list("from OrganSetMealHospital a where 1=1 "
					+ " and a.organSetMeal.id = "+ mealID, -1, -1, null);
			if(!middle_list.isEmpty()){
				for(OrganSetMealHospital middle : middle_list){
					HospitalBasicInfo info = middle.getHospitalBasicInfo();
					if(info!=null) list.add(info);
				}
			}
		}else{
			/*
			 * 查询所有机构
			 */
			StringBuilder sb = new StringBuilder();
			sb.append("from HospitalBasicInfo");
			if(hospitalBasicInfo!=null){
				if(StringUtils.isNotBlank(hospitalBasicInfo.getHospitalLname()))
					sb.append("  a where a.hospitalLname like '%"+hospitalBasicInfo.getHospitalLname()+"%'");
			}
			list = (List<HospitalBasicInfo>) super.listForEc(sb.toString(), model, null);
		}
		
		if(list.isEmpty()) return null;
		else return list;
	}
	
	/**
	 * 获取套餐列表
	 * 查询条件
	 * 1.套餐名称
	 * @param model
	 * @param organSetMeal
	 * @return
	 */
	public List<OrganSetMeal> getOrganSetMeatList(PageSortModel model,
			OrganSetMeal organSetMeal){
		
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		
		sb.append("from OrganSetMeal a where 1=1");
		if(organSetMeal!=null){
			if(StringUtils.isNotBlank(organSetMeal.getPackageName())){
				sb.append(" and a.packageName like :packageName");
				params.put("packageName", "%"+organSetMeal.getPackageName()+"%");
			}
		}
		
		List<OrganSetMeal> listForEc = (List<OrganSetMeal>) super.listForEc(sb.toString(), model, params);
		if(listForEc.isEmpty()) return null;
		
		return listForEc;
	}
	
	/**
	 * 根据套餐id,获取套餐信息(关联机构)
	 * @param id
	 * @return
	 */
	public OrganSetMeal getOrganSetMeal(Long id){
		if(id!=null){
			OrganSetMeal organSetMeal = super.get(id, OrganSetMeal.class);
			
			/*
			 * 查看套餐是否关联机构，如果关联了机构，将关联的机构回显，
			 * 隐藏关联的字段主键串，用于保存
			 */
			List<HospitalBasicInfo> hospitalBasicInfoList = getHospitalBasicInfoList(null,null,id);
			if(hospitalBasicInfoList!=null&&hospitalBasicInfoList.size()>0){
				//机构名称回显
				organSetMeal.setHospitalBasicInfoList(hospitalBasicInfoList);
				
				/*
				 * 用于保存机构
				 */
				String orgIDs = "";
				String orgNames = "";
				for(HospitalBasicInfo info : hospitalBasicInfoList){
					orgIDs+=info.getId()+",";
					orgNames+=info.getHospitalLname()+",";
				}
//				orgIDs = orgIDs.substring(0,orgIDs.length()-1);
//				orgNames = orgNames.substring(0,orgNames.length()-1);
				organSetMeal.setOrgIDs(orgIDs);
				organSetMeal.setOrgNames(orgNames);
			}
			
			
			return organSetMeal;
		}
		return null;
	}
	
	/**
	 * 保存套餐信息
	 * @param organSetMeal
	 * @return
	 */
	public boolean saveSetMeal(OrganSetMeal organSetMeal){
		if(organSetMeal!=null){
			super.add(organSetMeal);
			saveSetMeal2Org(organSetMeal);
		}
		if(organSetMeal.getId()!=null) return true;
		else return false;
	}
	
	private void saveSetMeal2Org(OrganSetMeal organSetMeal){
		
		/*
		 * 查询是否有关联的机构,如果有就删除
		 */
		List<OrganSetMealHospital> list = super.list("from OrganSetMealHospital a where a.organSetMeal.id = "+organSetMeal.getId(), -1, -1, null);
		if(list.size()>0){
			for(OrganSetMealHospital middle : list){
				super.delete(middle);
			};
		}
		
		/*
		 * 如果分配了机构,就对套餐和机构进行关联
		 */
		String orgIDs = organSetMeal.getOrgIDs();
		if(StringUtils.isNotBlank(orgIDs)){
			String[] orgIDArr = null;
			if(orgIDs.contains(","))
				orgIDArr = orgIDs.split(",");
			else
				orgIDArr = new String[]{orgIDs};
			
			for(int i=0;i<orgIDArr.length;i++){
				HospitalBasicInfo hospitalBasicInfo = super.get(Long.parseLong(orgIDArr[i]), HospitalBasicInfo.class);
				if(hospitalBasicInfo!=null){
					OrganSetMealHospital middle = new OrganSetMealHospital();
					middle.setOrganSetMeal(organSetMeal);
					middle.setHospitalBasicInfo(hospitalBasicInfo);
					super.add(middle);
				}
			}
		}
	};
	
	/**
	 * 更细套餐信息
	 * @param organSetMeal
	 * @return
	 */
	public void updateSetMeal(OrganSetMeal organSetMeal){
		OrganSetMeal meal_query = super.get(organSetMeal.getId(), OrganSetMeal.class);
		String[] arr = null;
		
		/*
		 * 如果没有图片名称,就增加图片
		 * 有,则不改变
		 */
		if(meal_query.getPackageImg()!=null){
			arr = new String[]{"id","packageImg"};
		}
		else arr = new String[]{"id"};
		
		BeanUtils.copyProperties(organSetMeal, meal_query, arr);
		super.edit(meal_query);
		saveSetMeal2Org(organSetMeal);
	}
}
