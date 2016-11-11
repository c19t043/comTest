package com.java.publichealth.residentsfile.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.ServiceImpl;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.residentsfile.service.ResidentsFileService;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPastHistoryBloodTransfusion;
import com.java.publichealth.residentsfile.vo.PhPastHistoryIllness;
import com.java.publichealth.residentsfile.vo.PhPastHistoryOperation;
import com.java.publichealth.residentsfile.vo.PhPastHistoryTrauma;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;

@SuppressWarnings("unchecked")
public class ResidentsFileServiceImpl extends ServiceImpl implements ResidentsFileService{
	protected static final Logger log = Logger.getLogger(ResidentsFileServiceImpl.class);
	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}
	@Override
	public Long saveOrUpdatePhPeopleBasicInfo(PhPeopleBasicInfo phPeopleBasicInfo) {
		if(phPeopleBasicInfo == null) return null;
		Long id = null;
		/*
		 * 1.如果id为空，则增加档案
		 * 2.id不为空,则修改档案
		 * 3.返回档案id
		 */
		if(phPeopleBasicInfo.getId() == null){
			id = (Long) super.add(phPeopleBasicInfo);
		}else{
			id = phPeopleBasicInfo.getId();
			PhPeopleBasicInfo old = this.getPhPeopleBasicInfoById(id);
			BeanUtils.copyProperties(phPeopleBasicInfo, old, new String[]{"createPerson","createTime"});
			super.edit(old);
		}
		return id;
	}
	
	@Override
	public List<PhPeopleBasicInfo> getPhPeopleBasicInfoListByPage(
			PageSortModel psm, PhPeopleBasicInfo phPeopleBasicInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM PhPeopleBasicInfo c WHERE 1=1 ");
		// 条件查询
		if (phPeopleBasicInfo != null) {
			
			hql.append(" and c.familyAccountInfo.id = " +phPeopleBasicInfo.getFamilyAccountInfo().getId());
			
			if (phPeopleBasicInfo.getInfoOwner() != null && !"".equals(phPeopleBasicInfo.getInfoOwner().trim())) {
				params.put("infoOwner", phPeopleBasicInfo.getInfoOwner().trim() );
				hql.append(" AND c.infoOwner = :infoOwner");
			}
			if (phPeopleBasicInfo.getName() != null && !"".equals(phPeopleBasicInfo.getName().trim())) {
				params.put("name", "%" + phPeopleBasicInfo.getName().trim() + "%");
				hql.append(" AND c.name LIKE :name");
			}
			if (phPeopleBasicInfo.getIdCardNum() != null && !"".equals(phPeopleBasicInfo.getIdCardNum().trim())) {
				params.put("idCardNum", "%" + phPeopleBasicInfo.getIdCardNum().trim() + "%");
				hql.append(" AND c.idCardNum LIKE :idCardNum");
			}
			
		}
		List<PhPeopleBasicInfo> list = (List<PhPeopleBasicInfo>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}
	@Override
	public PhPeopleBasicInfo getPhPeopleBasicInfoById(Long id) {
		return super.get(id,PhPeopleBasicInfo.class);
	}
	@Override
	public Long saveOrUpdatePhPastHistoryIllness(
			PhPastHistoryIllness phPastHistoryIllness) {
		if(phPastHistoryIllness == null) return null;
		Long id = null;
		if(phPastHistoryIllness.getId() == null){
			id = (Long) super.add(phPastHistoryIllness);
		}else{
			id = phPastHistoryIllness.getId();
			super.edit(phPastHistoryIllness);
		}
		return id;
	}
	@Override
	public Long saveOrUpdatePhPastHistoryOperation(
			PhPastHistoryOperation phPastHistoryOperation) {
		if(phPastHistoryOperation == null) return null;
		Long id = null;
		if(phPastHistoryOperation.getId() == null){
			id = (Long) super.add(phPastHistoryOperation);
		}else{
			id = phPastHistoryOperation.getId();
			super.edit(phPastHistoryOperation);
		}
		return id;
	}
	@Override
	public Long saveOrUpdatePhPastHistoryTrauma(
			PhPastHistoryTrauma phPastHistoryTrauma) {
		if(phPastHistoryTrauma == null) return null;
		Long id = null;
		if(phPastHistoryTrauma.getId() == null){
			id = (Long) super.add(phPastHistoryTrauma);
		}else{
			id = phPastHistoryTrauma.getId();
			super.edit(phPastHistoryTrauma);
		}
		return id;
	}
	@Override
	public Long saveOrUpdatePhPastHistoryBloodTransfusion(
			PhPastHistoryBloodTransfusion phPastHistoryBloodTransfusion) {
		if(phPastHistoryBloodTransfusion == null) return null;
		Long id = null;
		if(phPastHistoryBloodTransfusion.getId() == null){
			id = (Long) super.add(phPastHistoryBloodTransfusion);
		}else{
			id = phPastHistoryBloodTransfusion.getId();
			super.edit(phPastHistoryBloodTransfusion);
		}
		return id;
	}
	@Override
	public String saveAllPhPeopleBasicInfo(
			PhPeopleBasicInfo phPeopleBasicInfo,
			List<PhPastHistoryIllness> phPastHistoryIllnessList,
			List<PhPastHistoryOperation> phPastHistoryOperationList,
			List<PhPastHistoryTrauma> phPastHistoryTraumaList,
			List<PhPastHistoryBloodTransfusion> phPastHistoryBloodTransfusionList) {
		
		FamilyAccountInfo familyAccountInfo = phPeopleBasicInfo.getFamilyAccountInfo();
		
		if(phPastHistoryIllnessList != null){
			//先删除原有数据
			this.deletePhPastHistoryIllnessByBasicId(familyAccountInfo.getId());
			for(PhPastHistoryIllness phPastHistoryIllness : phPastHistoryIllnessList){
				
				phPastHistoryIllness.setFamilyAccountInfo(familyAccountInfo);
				this.add(phPastHistoryIllness);
			}
		}
		if(phPastHistoryOperationList != null){
			for(PhPastHistoryOperation phPastHistoryOperation : phPastHistoryOperationList){
				
				phPastHistoryOperation.setFamilyAccountInfo(familyAccountInfo);
				
				this.saveOrUpdatePhPastHistoryOperation(phPastHistoryOperation);
			}
		}
		if(phPastHistoryTraumaList != null){
			for(PhPastHistoryTrauma phPastHistoryTrauma : phPastHistoryTraumaList){
				
				
				phPastHistoryTrauma.setFamilyAccountInfo(familyAccountInfo);
				this.saveOrUpdatePhPastHistoryTrauma(phPastHistoryTrauma);
			}
		}
		if(phPastHistoryBloodTransfusionList != null){
			for(PhPastHistoryBloodTransfusion phPastHistoryBloodTransfusion : phPastHistoryBloodTransfusionList){
				
				phPastHistoryBloodTransfusion.setFamilyAccountInfo(familyAccountInfo);
				
				this.saveOrUpdatePhPastHistoryBloodTransfusion(phPastHistoryBloodTransfusion);
			}
		}
		return "操作成功";
	}
	@Override
	public String deletePhPastHistoryIllnessByBasicId(Long basicId) {
		StringBuffer hql = new StringBuffer("from PhPastHistoryIllness p where p.familyAccountInfo.id = ?");
		List<PhPastHistoryIllness> illList = persistProxy.getOrmPersistence().findByHQLQuery(hql.toString(), new Object[]{basicId});
		if(illList!=null && illList.size()>0) {
			for(PhPastHistoryIllness ill : illList){
				super.delete(ill);
			}
		}
		return "操作成功";
	}
	
	/**
	 * 查询电话是否存在方法
	 */
	@Override
	public List<KyUserInfo> findByHql(PhPeopleBasicInfo phPeopleBasicInfo) {
		String hql = "select k from KyUserInfo k where phone="+phPeopleBasicInfo.getContactPhone();
		//String hql = "select k from KyUserInfo k where id="+phPeopleBasicInfo.getId();
		List<KyUserInfo> list = this.list(hql.toString(), -1, -1, null);
		return list;
	}
	
	@Override
	public Long saveKyUserInfo(KyUserInfo kyUserInfo) {
		Long id = null;
		if(kyUserInfo == null){
			return null;
		}
		if(kyUserInfo.getId() == null){
			id = (Long) super.add(kyUserInfo);
		}
		return id;
	}
	
}
