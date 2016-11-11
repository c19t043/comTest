 package com.java.publichealth.neonatalvisit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.java.common.lang.StringUtils;
import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.user.service.ServiceImpl;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.neonatalvisit.service.PhNeonatalVisitRecordService;
import com.java.publichealth.neonatalvisit.vo.PhNeonatalVisitRecord;
import com.java.publichealth.residentsfile.service.impl.ResidentsFileServiceImpl;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

public class PhNeonatalVisitRecordServiceImpl extends ServiceImpl implements
		PhNeonatalVisitRecordService {
	protected static final Logger log = Logger.getLogger(ResidentsFileServiceImpl.class);

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {

		return null;
	}

	@Override
	public Long saveOrUpdatePhNeonatalVisitRecord(PhNeonatalVisitRecord phNeonatalVisitRecord) {
		if (phNeonatalVisitRecord == null) {
			return null;
		}
		
		Long id = null;
		if (phNeonatalVisitRecord.getId() == null) {
			FamilyAccountInfo familyAccountInfo = super.get(
					phNeonatalVisitRecord.getFamilyAccountInfo().getId(),FamilyAccountInfo.class);
			
			ConsultBabyInfo consultBabyInfo = super.get(
					phNeonatalVisitRecord.getConsultBabyInfo().getId(),ConsultBabyInfo.class);
			
			phNeonatalVisitRecord.setConsultBabyInfo(consultBabyInfo);
			phNeonatalVisitRecord.setFamilyAccountInfo(familyAccountInfo);
			
			id = (Long) super.add(phNeonatalVisitRecord);
		}else{
			
			id = phNeonatalVisitRecord.getId();
			
			PhNeonatalVisitRecord Record_query = super.get(id, PhNeonatalVisitRecord.class);
			BeanUtils.copyProperties(phNeonatalVisitRecord, Record_query, new String[]{"id","consultBabyInfo","familyAccountInfo"});
			
			super.edit(Record_query);
		}
		return id;
	}

	@Override
	public Long deletePhNeonatalVisitRecord(Long id) {
		return null;
	}

	@Override
	public PhNeonatalVisitRecord getPhNeonatalVisitRecordById(Long id) {
		
		return super.get(id, PhNeonatalVisitRecord.class);
	}

	@Override
	public List<PhNeonatalVisitRecord> getPhNeonatalVisitRecordServiceByPeople(
			PhPeopleBasicInfo phPeopleBasicInfo,
			PhNeonatalVisitRecord phNeonatalVisitRecord,
			PageSortModel psm) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM PhNeonatalVisitRecord c WHERE 1=1 ");
		//--------------------------------修改的代码
		if(phNeonatalVisitRecord!=null){
			/*
			 * 查看儿童明细信息
			 */
			if(phNeonatalVisitRecord.getConsultBabyInfo()!=null&&phNeonatalVisitRecord.getConsultBabyInfo().getId()!=null){
				hql.append(" and c.consultBabyInfo.id = "+ phNeonatalVisitRecord.getConsultBabyInfo().getId());
			}
			/*
			 * 条件查询
			 */
			if(StringUtils.isNotBlank(phNeonatalVisitRecord.getName())){
				hql.append(" and c.name like :name");
				params.put("name", "'%"+ phNeonatalVisitRecord.getName()+"%'");
			}
			if(phNeonatalVisitRecord.getId()!=null){
				hql.append(" and c.id = "+ phNeonatalVisitRecord.getId());
			}
		}
		
		hql.append(" and c.familyAccountInfo.id = "+ phNeonatalVisitRecord.getFamilyAccountInfo().getId());
		
		//--------------------------------
		List<PhNeonatalVisitRecord> list = null;
		if(psm!=null)
			list =  (List<PhNeonatalVisitRecord>) this.listForEc(hql.toString(), psm, params);
		else{
			list =  this.list(hql.toString(), -1, -1, params);
		}
		return list;
	}

}
