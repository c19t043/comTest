package com.java.publichealth.productionvisit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.user.service.ServiceImpl;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.productionvisit.service.PhPrenatalFollowRecordFirstService;
import com.java.publichealth.productionvisit.vo.PhPrenatalFollowRecordFirst;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;
@SuppressWarnings("unchecked")
public class PhPrenatalFollowRecordFirstServiceImpl extends ServiceImpl implements PhPrenatalFollowRecordFirstService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}

	@Override
	public List<PhPrenatalFollowRecordFirst> getPhPrenatalFollowRecordFirstListByPage(
			PageSortModel psm,
			PhPrenatalFollowRecordFirst phPrenatalFollowRecordFirst) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM PhPrenatalFollowRecordFirst c WHERE 1=1 ");
		// 条件查询
		if (phPrenatalFollowRecordFirst != null) {
//			if (phPeopleBasicInfo.getName() != null && !"".equals(phPeopleBasicInfo.getName().trim())) {
//				params.put("name", "%" + phPeopleBasicInfo.getName().trim() + "%");
//				hql.append(" AND c.name LIKE :name");
//			}
//			if (phPeopleBasicInfo.getIdCardNum() != null && !"".equals(phPeopleBasicInfo.getIdCardNum().trim())) {
//				params.put("idCardNum", "%" + phPeopleBasicInfo.getIdCardNum().trim() + "%");
//				hql.append(" AND c.idCardNum LIKE :idCardNum");
//			}
		}
		List<PhPrenatalFollowRecordFirst> list = (List<PhPrenatalFollowRecordFirst>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}

	@Override
	public Long saveOrUpdatePhPrenatalFollowRecordFirst(
			PhPrenatalFollowRecordFirst phPrenatalFollowRecordFirst) {
		if(phPrenatalFollowRecordFirst == null) return null;
		Long id = null;
		if(phPrenatalFollowRecordFirst.getId() == null){
			
			FamilyAccountInfo familyAccountInfo = super.get(
					phPrenatalFollowRecordFirst.getFamilyAccountInfo().getId(),FamilyAccountInfo.class);
			
			phPrenatalFollowRecordFirst.setFamilyAccountInfo(familyAccountInfo);
			
			id = (Long) super.add(phPrenatalFollowRecordFirst);
		}else{
			id = phPrenatalFollowRecordFirst.getId();
			PhPrenatalFollowRecordFirst old = this.get(id, PhPrenatalFollowRecordFirst.class);
			BeanUtils.copyProperties(phPrenatalFollowRecordFirst, old, new String[]{"createPerson","createTime"});
			super.edit(old);
		}
		return id;
	}

	@Override
	public PhPrenatalFollowRecordFirst getPhPrenatalFollowRecordFirstById(
			Long id) {
		return this.get(id, PhPrenatalFollowRecordFirst.class);
	}

	@Override
	public List<PhPrenatalFollowRecordFirst> getPhPrenatalFollowRecordFirstListByPeople(
			PhPeopleBasicInfo phPeopleBasicInfo,
			PhPrenatalFollowRecordFirst phPrenatalFollowRecordFirst) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM PhPrenatalFollowRecordFirst c WHERE 1=1 ");
		// 条件查询
		
		hql.append(" and c.familyAccountInfo.id = "+ phPrenatalFollowRecordFirst.getFamilyAccountInfo().getId());
		
		List<PhPrenatalFollowRecordFirst> list =  this.list(String.valueOf(hql), -1, -1, params);
		return list;
	}

	@Override
	public List<PhPrenatalFollowRecordFirst> getObjectListOfFirstRecordByPage(
			PageSortModel model,
			PhPrenatalFollowRecordFirst phPrenatalFollowRecordFirst) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM PhPrenatalFollowRecordFirst c WHERE 1=1 ");
		// 条件查询
		
		hql.append(" and c.familyAccountInfo.id = "+ phPrenatalFollowRecordFirst.getFamilyAccountInfo().getId());
		
		List<PhPrenatalFollowRecordFirst> list =  (List<PhPrenatalFollowRecordFirst>) this.listForEc(hql.toString(), model, params);
		
		return list;
	}
	
}
