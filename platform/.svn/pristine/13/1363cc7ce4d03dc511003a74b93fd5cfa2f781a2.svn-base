package com.java.publichealth.productionvisit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.ServiceImpl;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.productionvisit.service.PhPostpartumFollowRecordService;
import com.java.publichealth.productionvisit.vo.PhPostpartumFollowRecord;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;
/**
 * 产后随访业务控制实现类
 * @author lihao
 *
 */
public class PhPostpartumFollowRecordServiceImpl extends ServiceImpl implements PhPostpartumFollowRecordService{
	protected static final Logger log = Logger.getLogger(PhPostpartumFollowRecordServiceImpl.class);
	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Long saveOrUpdatePhPostpartumFollowRecord(
			PhPostpartumFollowRecord phPostpartumFollowRecord) {
		if(phPostpartumFollowRecord == null) return null;
		Long id = null;
		if(phPostpartumFollowRecord.getId() == null){
			
			FamilyAccountInfo familyAccountInfo = super.get(
					phPostpartumFollowRecord.getFamilyAccountInfo().getId(),FamilyAccountInfo.class);
			
			phPostpartumFollowRecord.setFamilyAccountInfo(familyAccountInfo);
			
			id = (Long) super.add(phPostpartumFollowRecord);
		}else{
			id = phPostpartumFollowRecord.getId();
			PhPostpartumFollowRecord old = this.get(id, PhPostpartumFollowRecord.class);
			BeanUtils.copyProperties(phPostpartumFollowRecord, old, new String[]{"createPerson","createTime"});
			super.edit(old);
		}
		return id;
	}
	@Override
	public PhPostpartumFollowRecord getPhPostpartumFollowRecordById(Long id) {
		return this.get(id, PhPostpartumFollowRecord.class);
	}
	@Override
	public List<PhPostpartumFollowRecord> getPhPostpartumFollowRecordListByPeople(
			PhPeopleBasicInfo phPeopleBasicInfo,
			PhPostpartumFollowRecord phPostpartumFollowRecord,PageSortModel model) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM PhPostpartumFollowRecord c WHERE 1=1 ");
		// 条件查询
		//-----------------------------修改的代码 
		
		hql.append(" and c.familyAccountInfo.id = "+ phPostpartumFollowRecord.getFamilyAccountInfo().getId());
		
		//-----------------------------
		if (phPostpartumFollowRecord != null) {
			if (phPostpartumFollowRecord.getFollowUpOpportunity() != null && 
					!"".equals(phPostpartumFollowRecord.getFollowUpOpportunity().trim())) {
				params.put("followUpOpportunity", phPostpartumFollowRecord.getFollowUpOpportunity());
				hql.append(" AND c.followUpOpportunity = :followUpOpportunity");
			}
		}
		List<PhPostpartumFollowRecord> list =null;
		if(model==null){
			list =  this.list(String.valueOf(hql), -1, -1, params);
		}else{
			list =  (List<PhPostpartumFollowRecord>) this.listForEc(String.valueOf(hql), model, params);
		}
		return list;
	}

}
