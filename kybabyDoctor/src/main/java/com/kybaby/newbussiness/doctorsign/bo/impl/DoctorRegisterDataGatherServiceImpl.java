package com.kybaby.newbussiness.doctorsign.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.kybaby.common.CommonServiceImpl;
import com.kybaby.domain.DoctorGoodField;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Position;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceContent;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.doctorsign.bo.DoctorRegisterDataGatherService;
import com.kybaby.newbussiness.doctorsign.dao.DoctorDataGatherDao;
import com.kybaby.newbussiness.doctorsign.domain.DoctorCardInfo;
import com.kybaby.newbussiness.doctorsign.domain.DoctorLifeInfo;
import com.kybaby.newbussiness.doctorsign.domain.DoctorMajor;
import com.kybaby.newbussiness.doctorsign.domain.DoctorOrderSummary;
import com.kybaby.newbussiness.doctorsign.domain.DoctorRegisterMaintenance;
import com.kybaby.newbussiness.doctorsign.domain.DoctorSignApprovalFlowRecord;
import com.kybaby.util.EncryptUtil;
import com.kybaby.util.LogUtil;
import com.kybaby.util.MsgUtil;

public class DoctorRegisterDataGatherServiceImpl extends CommonServiceImpl implements DoctorRegisterDataGatherService{
	
	private DoctorDataGatherDao doctorDataGatherDao;
	@Override
	public DoctorSignApprovalFlowRecord getLaterFlowRecord(Long doctorID) {
		return doctorDataGatherDao.getLaterFlowRecord(doctorID);
	}
	@Override
	public DoctorInfo saveOrUpdateDoctorInfo(DoctorInfo doctorInfo) {
		Long id = doctorInfo.getId();
		if(doctorInfo.getMajor()!=null&&doctorInfo.getMajor().getId()==null){
			doctorInfo.setMajor(null);
		}
		String formatDateStr_yyyy_MM_dd_HH_mm_ss = DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(new Date());
		doctorInfo.setOpTime(formatDateStr_yyyy_MM_dd_HH_mm_ss);
		if(id==null){
			doctorInfo.setServiceMode("全部");
			doctorInfo.setServiceArea(10L);
			doctorInfo.setDoctorStatus("N");
			doctorInfo.setAuthentication("未申请");
			doctorInfo.setFlowStatus("未提交");
			doctorInfo.setRegisterTime(formatDateStr_yyyy_MM_dd_HH_mm_ss);
			doctorInfo.setDoctorPassword(EncryptUtil.getMD5Str("123"));
			doctorDataGatherDao.saveObject(doctorInfo);
			//添加医生服务类型
			addDoctorServiceContent(doctorInfo.getId(), doctorInfo.getServiceTypeIds());
			LogUtil.debug("创建注册医生,ID为"+doctorInfo.getId());
		}else{
			DoctorInfo qryDoctorInfo = doctorDataGatherDao.getObjectByID(id, DoctorInfo.class);
			if("未提交,已驳回".contains(qryDoctorInfo.getFlowStatus())){//所有字段都可以修改
				qryDoctorInfo.allCopy(doctorInfo);
				//根据医生ID删除服务类容
				deleteDoctorServiceContent(qryDoctorInfo.getId());
				//添加医生服务类型
				addDoctorServiceContent(qryDoctorInfo.getId(), doctorInfo.getServiceTypeIds());
			}else{//只能修改不必填字段
				qryDoctorInfo.noAllCopy(doctorInfo);
			}
			super.update(qryDoctorInfo);
			return qryDoctorInfo;
		}
		return doctorInfo;
	}
	/**
	 * 添加医生服务类型
	 * @param dctID 医生id
	 * @param serviceTypeIds 服务类容串
	 */
	private void addDoctorServiceContent(Long dctID,String serviceTypeIds){
		String[] serviceTypeIdArr = (serviceTypeIds+"::").split("::");
		for (String serviceTypeId : serviceTypeIdArr) {
			DoctorInfo doctorInfo = this.get(dctID, DoctorInfo.class);
			DoctorServiceType doctorServiceType = this.get(Long.parseLong(serviceTypeId), DoctorServiceType.class);
			if(doctorServiceType!=null&&doctorInfo!=null){
				DoctorServiceContent dsc = new DoctorServiceContent();
				dsc.setDoctorInfo(doctorInfo);
				dsc.setDoctorServiceType(doctorServiceType);
				this.save(dsc);
			} 
		}
	}
	/**
	 * 根据医生ID删除服务类容
	 * @param dctID
	 */
	private void deleteDoctorServiceContent(Long dctID){
		doctorDataGatherDao.deleteDoctorServiceContent(dctID);
	}
	@Override
	public List<DoctorInfo> queryMaintenanceAbleDoctorInfos(Long logonUserID){
		//查询当前用户分配维护的医生数据
		List<DoctorRegisterMaintenance> distribute2MeOfDoctorInfos = doctorDataGatherDao.getDistribute2MeOfDoctorInfos(logonUserID);
		//所有维护的医生ID
		StringBuilder distribute2MeDoctorIDs = new StringBuilder();
		for(DoctorRegisterMaintenance dct : distribute2MeOfDoctorInfos){
			distribute2MeDoctorIDs.append(dct.getDoctorId()).append(",");
		}
		if(distribute2MeDoctorIDs.length()!=0){
			distribute2MeDoctorIDs = distribute2MeDoctorIDs.deleteCharAt(distribute2MeDoctorIDs.length()-1);
		}
		return doctorDataGatherDao.getDoctorInfosByIDs(distribute2MeDoctorIDs.toString());
	}
	@Override
	public List<DoctorInfo> queryViewableDoctorInfos(Long logonUserID) {
		//1.查询当前用户签约的医生数据
		List<DoctorInfo> mySignDoctorInfos = doctorDataGatherDao.getMySignDoctorInfos(logonUserID);
		//2.查询当前用户分配维护的医生数据
		List<DoctorRegisterMaintenance> distribute2MeOfDoctorInfos = doctorDataGatherDao.getDistribute2MeOfDoctorInfos(logonUserID);
		//3.排除重复的数据
		return getDistinctDoctorInfo(mySignDoctorInfos, distribute2MeOfDoctorInfos);
	}
	@Override
	public List<DoctorInfo> getMySignDoctorInfos(Long logonUserID) {
		//1.查询当前用户签约的医生数据
		return doctorDataGatherDao.getMySignDoctorInfos(logonUserID);
	}
	/**
	 * 获取不重复的医生数据
	 * @param mySignDoctorInfos 查询当前用户签约的医生数据
	 * @param distribute2MeOfDoctorInfos 查询当前用户分配维护的医生数据
	 */
	private List<DoctorInfo> getDistinctDoctorInfo(List<DoctorInfo> mySignDoctorInfos,List<DoctorRegisterMaintenance> distribute2MeOfDoctorInfos){
		//所有签约医生id
		StringBuilder mySignDoctorIDs = new StringBuilder();
		for(DoctorInfo dct : mySignDoctorInfos){
			mySignDoctorIDs.append(dct.getId()).append(",");
		}
		
		//所有维护的医生ID
		StringBuilder distribute2MeDoctorIDs = new StringBuilder();
		for(DoctorRegisterMaintenance dct : distribute2MeOfDoctorInfos){
			distribute2MeDoctorIDs.append(dct.getDoctorId()).append(",");
		}
		
		if(distribute2MeDoctorIDs.length()!=0){
			//剔除维护医生记录中,是当前签约人的记录
			String noRepeatDoctorID = removeRepeatDoctor(mySignDoctorIDs.toString(), distribute2MeDoctorIDs.toString());
			List<DoctorInfo> doctorInfos = doctorDataGatherDao.getDoctorInfosByIDs(noRepeatDoctorID);
			mySignDoctorInfos.addAll(doctorInfos);
		}
		return mySignDoctorInfos;
	}
	/**
	 * 剔除维护医生记录中,是当前签约人的记录
	 * @param mySignDoctorIDs 所有签约医生id
	 * @param distribute2MeDoctorIDs 所有维护的医生ID
	 * @return
	 */
	private String removeRepeatDoctor(String mySignDoctorIDs,String distribute2MeDoctorIDs){
		StringBuilder maintenanceDoctor = new StringBuilder();
		String[] doctorIDsArr= distribute2MeDoctorIDs.split(",");
		for(String tmp_ID : doctorIDsArr){
			if(!mySignDoctorIDs.contains(tmp_ID+",")){
				maintenanceDoctor.append(tmp_ID).append(",");
			}
		}
		if(maintenanceDoctor.length()!=0) maintenanceDoctor = maintenanceDoctor.deleteCharAt(maintenanceDoctor.length()-1);
		return maintenanceDoctor.toString();
	}
	@Override
	public boolean checkDoctorPhone(String doctorPhone) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorInfo c where c.doctorPhone = :doctorPhone");
		params.put("doctorPhone", doctorPhone);
		List<Object> objectCollectionWithNoPage = doctorDataGatherDao.getObjectCollectionWithNoPage(sb.toString(), params, null);
		if(!objectCollectionWithNoPage.isEmpty()){
			MsgUtil.set("已存在该电话!");
			return false;
		}
		return true;
	}
	@Override
	public List<DoctorOrderSummary> getDoctorOrderSummarys(Long id) {
		//获取当前操作人有关联的医生数据
		List<DoctorInfo> doctorInfoByLogonUser = this.queryViewableDoctorInfos(id);
		
		StringBuilder ids = new StringBuilder();
		for (DoctorInfo doctorInfo : doctorInfoByLogonUser) {
			ids.append(doctorInfo.getId()).append(",");
		}
		if(ids.length()!=0){
			ids = ids.deleteCharAt(ids.length()-1);
		}
		if(ids.length()==0){
			return new ArrayList<DoctorOrderSummary>();
		}
		//根据医生id串获取医生订单汇总数据
		return doctorDataGatherDao.getDoctorOrderGather(ids.toString());
	}
	@Override
	public void deleteDoctorCardInfo(Long id){
		doctorDataGatherDao.deleteObject(id, DoctorCardInfo.class);
	}
	@Override
	public List<DoctorGoodField> getDoctorGoodFields(String professionalFlag) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorGoodField c where c.isStart = 'Y' and c.doctorType =:doctorType ");
		params.put("doctorType", professionalFlag);
		return doctorDataGatherDao.getObjectCollectionWithNoPage(sb.toString(), params, null);
	}
	@Override
	public List<DoctorLifeInfo> getDoctorLifeInfos(Long doctorID) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorLifeInfo c where c.doctorInfo.id = :dctID ");
		params.put("dctID", doctorID);
		return doctorDataGatherDao.getObjectCollectionWithNoPage(sb.toString(), params, null);
	}
	@Override
	public List<DoctorCardInfo> getDoctorCardInfos(Long doctorID) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorCardInfo c where c.doctorInfo.id = :dctID ");
		params.put("dctID", doctorID);
		return doctorDataGatherDao.getObjectCollectionWithNoPage(sb.toString(), params, null);
	}
	@Override
	public List<DoctorServiceType> getAllDoctorServiceTypes() {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorServiceType c where c.isAvailable = 'Y' and c.parentDoctorServiceType <> null ");
		return doctorDataGatherDao.getObjectCollectionWithNoPage(sb.toString(), params, null);
	}
/*	@Override
	public List<DoctorInfo> getDoctorinfos(DoctorInfo doctorInfo,
			String professionalFlag) {
		//查询对应注册医生(相应专业)的所有电话
		List<RoleSelect> roleSelects = doctorDataGatherDao.getRoleSelects(professionalFlag);
		//将所有医生电话组装成字符串
		String doctorPhones = getDoctorPhones(roleSelects);
		//根据医生电话串获取医生信息
		return doctorDataGatherDao.getDoctorInfosByPhone(doctorPhones);
	}*/
	/**
	 * 将所有医生电话组装成字符串
	 */
/*	private String getDoctorPhones(List<RoleSelect> roleSelects){
		StringBuilder sb = new StringBuilder();
		for (RoleSelect roleSelect : roleSelects) {
			sb.append("'")
			.append(roleSelect.getPhone())
			.append("'").append(",");
		}
		if(sb.length()==0){
			return null;
		}else{
			return sb.deleteCharAt(sb.length()-1).toString();
		}
	}*/
	@Override
	public List<DoctorMajor> getMajors(DoctorMajor major,String specialty) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorMajor c where 1=1 and c.majorStatus = 'Y' ");
		if(major!=null){
			if(StringUtils.isNotBlank(major.getDoctorType())){
				sb.append(" and c.doctorType = :doctorType");
				params.put("doctorType", major.getDoctorType());
			}
		}
		if(StringUtils.isNotBlank(specialty)){
			if("first".equals(specialty)){//主专业
				sb.append(" and c.parent.parent.parent = null ");
			}
			if("second".equals(specialty)){//亚专业
				sb.append(" and c.parent.parent.parent.parent = null");				
			}
			if("third".equals(specialty)){//病种专业
				sb.append(" and c.parent.parent.parent.parent.parent = null");
			}
		}
		return doctorDataGatherDao.getObjectCollectionWithNoPage(sb.toString(), params, null);
	}
	@Override
	public List<Position> getPositions(Position position) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from Position c where 1=1 and c.positionStatus = 'Y' ");
		if(position!=null){
			if(StringUtils.isNotBlank(position.getPositionStatus())){
				sb.append(" and c.name like :name");
				params.put("name", "%"+position.getPositionStatus()+"%");
			}
		}
		sb.append(" order by c.rank desc ");
		return doctorDataGatherDao.getObjectCollectionWithNoPage(sb.toString(), params, null);
	}
	@Override
	public List<HospitalBasicInfo> getHospitalBasicInfos(
			HospitalBasicInfo hospitalBasicInfo) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from HospitalBasicInfo c where 1=1 and c.isShowForUser = 'Y' ");
		if(hospitalBasicInfo!=null){
			if(StringUtils.isNotBlank(hospitalBasicInfo.getHospitalLname())){
				sb.append(" and c.hospitalLname like :hospitalLname");
				params.put("hospitalLname", "%"+hospitalBasicInfo.getHospitalLname()+"%");
			}
		}
		return doctorDataGatherDao.getObjectCollectionWithNoPage(sb.toString(), params, null);
	}
	@Override
	public List<DoctorCardInfo> getDoctorCardInfos(DoctorCardInfo doctorCardInfo) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorCardInfo c where 1=1 ");
		if(doctorCardInfo!=null){
			if(doctorCardInfo.getDoctorInfo()!=null&&doctorCardInfo.getDoctorInfo().getId()!=null){
				sb.append(" and c.doctorInfo.id = :dctID");
				params.put("dctID", doctorCardInfo.getDoctorInfo().getId());
			}
		}
		return doctorDataGatherDao.getObjectCollectionWithNoPage(sb.toString(), params, null);
	}

	@Override
	public DoctorCardInfo saveOrUpdateDoctorCardInfo(
			DoctorCardInfo doctorCardInfo) {
		Long id = doctorCardInfo.getId();
		if(id==null){
			doctorDataGatherDao.saveObject(doctorCardInfo);
		}else{
			doctorDataGatherDao.updateObject(doctorCardInfo);
		}
		return doctorCardInfo;
	}

	@Override
	public List<DoctorLifeInfo> getDoctorLifeInfos(DoctorLifeInfo doctorLifeInfo) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorLifeInfo c where 1=1 ");
		if(doctorLifeInfo!=null){
			if(doctorLifeInfo.getDoctorInfo()!=null&&doctorLifeInfo.getDoctorInfo().getId()!=null){
				sb.append(" and c.doctorInfo.id = :dctID");
				params.put("dctID", doctorLifeInfo.getDoctorInfo().getId());
			}
		}
		return doctorDataGatherDao.getObjectCollectionWithNoPage(sb.toString(), params, null);
	}

	@Override
	public DoctorLifeInfo saveOrUpdateDoctorLifeInfo(
			DoctorLifeInfo doctorLifeInfo) {
		Long id = doctorLifeInfo.getId();
		if(id==null){
			doctorDataGatherDao.saveObject(doctorLifeInfo);
		}else{
			doctorDataGatherDao.updateObject(doctorLifeInfo);
		}
		return doctorLifeInfo;
	}

	@Override
	public DoctorSignApprovalFlowRecord saveOrUpdateDoctorSignApprovalFlowRecord(
			DoctorSignApprovalFlowRecord doctorSignApprovalFlowRecord) {
		Long id = doctorSignApprovalFlowRecord.getId();
		if(id==null){
			doctorDataGatherDao.saveObject(doctorSignApprovalFlowRecord);
		}else{
			DoctorSignApprovalFlowRecord fowRecord = doctorDataGatherDao.getObjectByID(id, DoctorSignApprovalFlowRecord.class);
			fowRecord.setFlowStatus("已申请");
			doctorDataGatherDao.updateObject(fowRecord);
			return fowRecord;
		}
		return doctorSignApprovalFlowRecord;
	}
	public void setDoctorDataGatherDao(DoctorDataGatherDao doctorDataGatherDao) {
		this.doctorDataGatherDao = doctorDataGatherDao;
	}
}
