package com.java.doctorinfo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.java.consultmanager.consultdoctormanager.vo.Position;
import com.java.doctorinfo.service.DoctorInfoService;
import com.java.doctorinfo.vo.DoctorCardInfo;
import com.java.doctorinfo.vo.DoctorLifeInfo;
import com.java.doctorinfo.vo.DoctorServiceType;
import com.java.doctorinfo.vo.OrganOperator;
import com.java.doctorinfo.vo.RecommendRule;
import com.java.doctorinfo.vo.RecommentAwardRecord;
import com.java.doctormanager.vo.DoctorMajor;
import com.java.doctormanager.vo.DoctorRegisterMaintenance;
import com.java.doctormanager.vo.DoctorSignApprovalFlowRecord;
import com.java.ec.common.PageSortModel;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.platform.user.service.ServiceImpl;
import com.java.util.DateManage;
import com.java.util.EncryptUtil;
import com.java.util.LogUtil;
import com.java.util.MsgUtil;

public class DoctorInfoServiceImpl extends ServiceImpl implements DoctorInfoService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}
	@Override
	public DoctorSignApprovalFlowRecord getLaterFlowRecord(Long doctorID) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorSignApprovalFlowRecord c where c.businessId  = :doctorID ");
		params.put("doctorID", doctorID);
		sb.append(" order by c.operateTime desc ");
		List<Object> list = super.list(sb.toString(), -1, -1, params);
		if(list.isEmpty()){
			return null;
		}
		return (DoctorSignApprovalFlowRecord) list.get(0);
	}
	@Override
	public List<DoctorLifeInfo> getDoctorLifeInfos(Long doctorID) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorLifeInfo c where c.doctorInfo.id = :dctID ");
		params.put("dctID", doctorID);
		return super.list(sb.toString(), -1, -1, params);
	}
	@Override
	public List<DoctorCardInfo> getDoctorCardInfos(Long doctorID) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorCardInfo c where c.doctorInfo.id = :dctID ");
		params.put("dctID", doctorID);
		sb.append(" order by c.imgType");
		return super.list(sb.toString(), -1, -1, params);
	}
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
		return super.list(sb.toString(), -1, -1, params);
	}
	@Override
	public DoctorCardInfo saveOrUpdateDoctorCardInfo(
			DoctorCardInfo doctorCardInfo) {
		Long id = doctorCardInfo.getId();
		if(id==null){
			super.add(doctorCardInfo);
		}else{
			super.edit(doctorCardInfo);
		}
		return doctorCardInfo;
	}
	@Override
	public List<Position> getAllPosition() {
		List list=super.list("from Position where positionStatus='Y' order by rank desc", -1, -1, null);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}
	@Override
	public void deleteDoctorCardInfo(Long id){
		DoctorCardInfo doctorCardInfo = super.get(id, DoctorCardInfo.class);
		super.delete(doctorCardInfo);
	}
	@Override
	public boolean checkDoctorPhone(String doctorPhone) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorInfo c where c.doctorPhone = :doctorPhone");
		params.put("doctorPhone", doctorPhone);
		List<Object> objectCollectionWithNoPage = super.list(sb.toString(), -1, -1, params);
		if(!objectCollectionWithNoPage.isEmpty()){
			MsgUtil.set("已存在该电话!");
			return false;
		}
		return true;
	}
	@Override
	public void saveRecommentAwardRecord(RecommentAwardRecord recommentAwardRecord) {		
		super.add(recommentAwardRecord);
	}
	//注册专用
	@Override
	public DoctorInfo getDoctorInfoByPhoneNum(String phone) {
		return super.getPersistProxy().getOrmPersistence()
				.findObjectByHQL("from DoctorInfo where doctorPhone=?", new Object[]{phone});
	}
	@Override
	public RecommendRule getSomeCanUseRule(String ruleName) {
		return super.getPersistProxy().getOrmPersistence()
				.findObjectByHQL("from RecommendRule where ruleName=? and ruleStatus='Y'", new Object[]{ruleName});
	}
	@Override
	public DoctorLifeInfo saveOrUpdateDoctorLifeInfo(
			DoctorLifeInfo doctorLifeInfo) {
		Long id = doctorLifeInfo.getId();
		if(id==null){
			super.add(doctorLifeInfo);
		}else{
			super.edit(doctorLifeInfo);
		}
		return doctorLifeInfo;
	}
	@Override
	public DoctorInfo saveOrUpdateDoctorInfo(DoctorInfo doctorInfo) {
		Long id = doctorInfo.getId();
		if(id==null){
			doctorInfo.setDoctorStatus("N");
			doctorInfo.setAuthentication("未申请");
			doctorInfo.setFlowStatus("未提交");
			doctorInfo.setRegisterTime(DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(new Date()));
			doctorInfo.setDoctorPassword(EncryptUtil.getMD5Str("123"));
			super.add(doctorInfo);
			LogUtil.debug("创建注册医生,ID为"+doctorInfo.getId());
		}else{
			DoctorInfo qryDoctorInfo = super.get(id, DoctorInfo.class);
			if("未提交,已驳回".contains(qryDoctorInfo.getFlowStatus())){//所有字段都可以修改
				qryDoctorInfo.allCopy(doctorInfo);
			}else{//只能修改不必填字段
				qryDoctorInfo.noAllCopy(doctorInfo);
			}
			super.edit(qryDoctorInfo);
			return qryDoctorInfo;
		}
		return doctorInfo;
	}
	@Override
	public List<DoctorInfo> getDoctorInfoListByPage(PageSortModel psm,
			DoctorInfo doctorInfo) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM DoctorInfo u where 1=1");
		hql.append(" and u.doctorName is not null and u.doctorName <>''");
		hql.append(" and u.authentication not in ('未申请','未通过') ");
		
		
		if(doctorInfo != null){
			if(StringUtils.isNotEmpty(doctorInfo.getDoctorName())){
				hql.append(" and u.doctorName like :doctorName");
				param.put("doctorName","%"+ doctorInfo.getDoctorName() + "%");
			}
			if(StringUtils.isNotEmpty(doctorInfo.getDoctorPhone())){
				hql.append(" and u.doctorPhone like :doctorPhone");
				param.put("doctorPhone","%"+ doctorInfo.getDoctorPhone() + "%");
			}
			if(StringUtils.isNotEmpty(doctorInfo.getFlowStatus())){
				hql.append(" and u.flowStatus = :flowStatus");
				param.put("flowStatus",doctorInfo.getFlowStatus());
			}
			if(StringUtils.isNotEmpty(doctorInfo.getAuthentication())){
				hql.append(" and u.authentication = :authentication");
				param.put("authentication",doctorInfo.getAuthentication());
			}
		}
		
		hql.append(" order by u.registerTime desc");
		List<DoctorInfo> list = (List<DoctorInfo>) listForEc(hql.toString(),psm, param);
		
		for (DoctorInfo dct : list) {
			List<OrganOperator> doctorRegisterMaintenance = this.getDoctorRegisterMaintenance(dct.getId());
			if(doctorRegisterMaintenance.isEmpty()) continue;
			StringBuilder names = new StringBuilder();
			for (OrganOperator organOperator : doctorRegisterMaintenance) {
				names.append(organOperator.getShowName()).append(",");
			}
			dct.setDoctorRegisterMaintenance(names.toString());
		}
		
		return list;
	}
	@Override
	public List<HospitalBasicInfo> getHospitalBasicInfos(
			HospitalBasicInfo hospitalBasicInfo) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from HospitalBasicInfo c where 1=1 ");
		if(hospitalBasicInfo!=null){
			if(StringUtils.isNotBlank(hospitalBasicInfo.getHospitalLname())){
				sb.append(" and c.hospitalLname like :hospitalLname");
				params.put("hospitalLname", "%"+hospitalBasicInfo.getHospitalLname()+"%");
			}
		}
		return super.list(sb.toString(), -1, -1, params);
	}
	@Override
	public List<DoctorServiceType> getAllDoctorServiceTypes() {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorServiceType c where c.isAvailable = 'Y' and c.parentDoctorServiceType <> null ");
		return super.list(sb.toString(), -1, -1, null);
	}
	@Override
	public <T> T save(T entity) {
		super.add(entity);
		return entity;
	}
	@Override
	public <T> T update(T entity) {
		super.edit(entity);
		return entity;
	}
	/**
	 * 根据操作人ID批量操作维护人
	 * @param doctorID 医生id
	 * @param ids
	 */
	@Override
	public void saveMaintenanceWithBath(Long doctorID,String ids){
		deleteMaintenaceByDoctorID(doctorID);
		if(StringUtils.isNotBlank(ids)){
			String[] idArr = (ids+",").split(",");
			for (String id : idArr) {
				DoctorRegisterMaintenance main = new DoctorRegisterMaintenance();
				main.setDoctorId(doctorID);
				main.setMaintenId(Long.parseLong(id));
				super.add(main);
			}
		}
	}
	/**
	 * 通过医生id删除维护人
	 * @param doctorID
	 */
	private void deleteMaintenaceByDoctorID(final Long doctorID){
		super.getPersistProxy().getOrmPersistence().getHibernateTemp().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query createQuery = session.createQuery("delete DoctorRegisterMaintenance c where c.doctorId = :doctorId");
				createQuery.setLong("doctorId", doctorID);
				return createQuery.executeUpdate();
			}
		});
	}
	/**
	 * 获取医生数据维护人
	 * @param doctorID
	 * @return
	 */
	@Override
	public List<OrganOperator> getDoctorRegisterMaintenance(Long doctorID){
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorRegisterMaintenance c where c.doctorId = :doctorId");
		params.put("doctorId",doctorID);
		List<DoctorRegisterMaintenance> mainTenance = super.list(sb.toString(), -1, -1, params);
		
		if(!mainTenance.isEmpty()){
			StringBuilder dctIDs = new StringBuilder();
			for (DoctorRegisterMaintenance doctorRegisterMaintenance : mainTenance) {
				dctIDs.append(doctorRegisterMaintenance.getMaintenId()).append(",");
			}
			if(dctIDs.length()>0)dctIDs = dctIDs.deleteCharAt(dctIDs.length()-1);
			return getOrganOperator(dctIDs.toString());
		}else{
			return new ArrayList<OrganOperator>();
		}
	}
	/**
	 * 根据人员ID串查询对应操作人员
	 * @param ids
	 * @return
	 */
	private List<OrganOperator> getOrganOperator(String ids){
		if(StringUtils.isBlank(ids)) return new ArrayList<OrganOperator>();
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from OrganOperator c where c.id in ("+ids+") ");
		return super.list(sb.toString(), -1, -1, null);
	}
	@Override
	public List<OrganOperator> getOrganOperator(PageSortModel model,
			OrganOperator organOperator) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from OrganOperator c where c.hospitalBasicInfo = null  and c.isEnable = 'Y'");
		if(organOperator!=null){
			if(StringUtils.isNotBlank(organOperator.getShowName())){
				sb.append(" and c.showName like :showName");
				params.put("showName", "%"+organOperator.getShowName()+"%");
			}
		}
		return (List<OrganOperator>) super.listForEc(sb.toString(), model, params);
	}
}
