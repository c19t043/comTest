package com.java.publichealth.productionvisit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.user.service.ServiceImpl;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.productionvisit.service.PhPrenatalFollowRecordAfterService;
import com.java.publichealth.productionvisit.vo.PhPrenatalFollowRecordAfter;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;
/**
 * 产前第2-9次随访服务类
 * @author lihao
 *
 */
public class PhPrenatalFollowRecordAfterServiceImpl extends ServiceImpl implements PhPrenatalFollowRecordAfterService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long saveOrUpdatePhPrenatalFollowRecordAfter(
			PhPrenatalFollowRecordAfter phPrenatalFollowRecordAfter) {
		if(phPrenatalFollowRecordAfter == null) return null;
		Long id = null;
		if(phPrenatalFollowRecordAfter.getId() == null){

			FamilyAccountInfo familyAccountInfo = super.get(
					phPrenatalFollowRecordAfter.getFamilyAccountInfo().getId(),FamilyAccountInfo.class);
			
			phPrenatalFollowRecordAfter.setFamilyAccountInfo(familyAccountInfo);
			
			id = (Long) super.add(phPrenatalFollowRecordAfter);
		}else{
			id = phPrenatalFollowRecordAfter.getId();
			PhPrenatalFollowRecordAfter old = this.get(id, PhPrenatalFollowRecordAfter.class);
			BeanUtils.copyProperties(phPrenatalFollowRecordAfter, old, new String[]{"createPerson","createTime"});
			super.edit(old);
		}
		return id;
	}

	@Override
	public PhPrenatalFollowRecordAfter getPhPrenatalFollowRecordAfterById(
			Long id) {
		return super.get(id, PhPrenatalFollowRecordAfter.class);
	}

	@Override
	public List<PhPrenatalFollowRecordAfter> getPhPrenatalFollowRecordAfterListByPeople(
			PageSortModel psm,
			PhPeopleBasicInfo phPeopleBasicInfo,
			PhPrenatalFollowRecordAfter phPrenatalFollowRecordAfter) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM PhPrenatalFollowRecordAfter c WHERE 1=1 ");
		// 条件查询
		
		hql.append(" and c.familyAccountInfo.id = "+ phPrenatalFollowRecordAfter.getFamilyAccountInfo().getId());
		
		List<PhPrenatalFollowRecordAfter> list =  this.list(String.valueOf(hql), -1, -1, params);
		return list;
	}

}
