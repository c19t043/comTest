package com.java.publichealth.childhealth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.user.service.ServiceImpl;
import com.java.publichealth.childhealth.service.ChildHealthSerivce;
import com.java.publichealth.childhealth.vo.PhChildHealthExaminationRecord;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

public class ChildHealthServiceImpl extends ServiceImpl implements ChildHealthSerivce {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {

		return null;
	}

	/**
	 * 添加修改
	 */
	@Override
	public Long saveOrUpdateChildHealth(PhChildHealthExaminationRecord childHealthExaminationRecord) {
		if (childHealthExaminationRecord == null) {
			return null;
		}
		Long id = null;
		
		boolean isSave = childHealthExaminationRecord.getId()==null?true:false;
		
		if (isSave) {
			childHealthExaminationRecord.setFamilyAccountInfo(super.get(
					childHealthExaminationRecord.getFamilyAccountInfo().getId(),FamilyAccountInfo.class));
			childHealthExaminationRecord.setConsultBabyInfo(super.get(
					childHealthExaminationRecord.getConsultBabyInfo().getId(),ConsultBabyInfo.class));
			id = (Long)super.add(childHealthExaminationRecord);
		} else {
			id = childHealthExaminationRecord.getId();
			super.edit(childHealthExaminationRecord);
		}
		return id;
	}

	@Override
	public Long deleteChildHealth(Long id) {

		return null;
	}

	@Override
	public PhChildHealthExaminationRecord getChildHealthById(Long id) {
		return super.get(id, PhChildHealthExaminationRecord.class);
	}

	@Override
	public List<PhChildHealthExaminationRecord> getPhChildHealthExaminationRecordListByPeople(
			PageSortModel psm, PhPeopleBasicInfo phPeopleBasicInfo,
			PhChildHealthExaminationRecord phChildHealthExaminationRecord) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM PhChildHealthExaminationRecord c WHERE 1=1 ");
	
		
		if(phChildHealthExaminationRecord!=null){
			if(phChildHealthExaminationRecord.getConsultBabyInfo()!=null&&phChildHealthExaminationRecord.getConsultBabyInfo().getId()!=null){
				hql.append(" and c.consultBabyInfo.id = "+ phChildHealthExaminationRecord.getConsultBabyInfo().getId());
			}
			if(phChildHealthExaminationRecord.getFamilyAccountInfo()!=null&&phChildHealthExaminationRecord.getFamilyAccountInfo().getId()!=null){
				hql.append(" and c.familyAccountInfo.id = "+ phChildHealthExaminationRecord.getFamilyAccountInfo().getId());
			}
		}
		
		List<PhChildHealthExaminationRecord> list =  this.list(String.valueOf(hql), -1, -1, params);
		return list;
	}


}
