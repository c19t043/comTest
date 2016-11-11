package com.kybaby.newbussiness.doctorsign.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kybaby.common.CommonDaoImpl;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.RoleSelect;
import com.kybaby.newbussiness.doctorsign.dao.DoctorDataGatherDao;
import com.kybaby.newbussiness.doctorsign.domain.DoctorRegisterMaintenance;
import com.kybaby.newbussiness.doctorsign.domain.DoctorSignApprovalFlowRecord;

public class DoctorDataGatherDaoImpl  extends CommonDaoImpl implements DoctorDataGatherDao{
	@Override
	public DoctorSignApprovalFlowRecord getLaterFlowRecord(Long doctorID){
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorSignApprovalFlowRecord c where c.businessId  = :doctorID ");
		params.put("doctorID", doctorID);
		sb.append(" order by c.operateTime desc ");
		return super.getObjectByCondition(sb.toString(), params);
	}
	@Override
	public List<DoctorInfo> getMySignDoctorInfos(Long myUserID) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorInfo c where c.organOperator.id  = :id ");
		params.put("id", myUserID);
		sb.append(" order by c.registerTime desc ");
		return super.getObjectCollectionWithNoPage(sb.toString(), params, null);
	}

	@Override
	public List<DoctorRegisterMaintenance> getDistribute2MeOfDoctorInfos(Long myUserID) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorRegisterMaintenance c where c.maintenId  = :id ");
		params.put("id", myUserID);
		return super.getObjectCollectionWithNoPage(sb.toString(), params, null);
	}

	@Override
	public List<DoctorInfo> getDoctorInfosByIDs(String DoctorIDs) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorInfo c where c.id in ("+DoctorIDs+")");
		return super.getObjectCollectionWithNoPage(sb.toString(), params, null);
	}

	@Override
	public List<DoctorInfo> getDoctorInfosByPhone(String phones) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorInfo c where c.doctorPhone in ("+phones+") order by c.registerTime desc ");
		return super.getObjectCollectionWithNoPage(sb.toString(), params, null);
	}
	
	@Override
	public List<RoleSelect> getRoleSelects(String professionalFlag) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from RoleSelect c where c.roleType = :roleType");
		params.put("roleType", professionalFlag);
		return super.getObjectCollectionWithNoPage(sb.toString(), params, null);
	}
}
