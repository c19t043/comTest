package com.kybaby.newbussiness.spservice.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.kybaby.common.CommonDaoImpl;
import com.kybaby.newbussiness.spservice.common.SpServiceConstant;
import com.kybaby.newbussiness.spservice.dao.SpInterfaceDao;
import com.kybaby.newbussiness.spservice.domain.SpAppointmentSchedule;
import com.kybaby.newbussiness.spservice.domain.SpCheckRecord;
import com.kybaby.newbussiness.spservice.domain.SpCheckReport;
import com.kybaby.newbussiness.spservice.domain.SpCostInfo;
import com.kybaby.newbussiness.spservice.domain.SpDoctorAdviceInfo;
import com.kybaby.newbussiness.spservice.domain.SpDoctorAlias;
import com.kybaby.newbussiness.spservice.domain.SpDoctorInfo;
import com.kybaby.newbussiness.spservice.domain.SpHealthcardManager;
import com.kybaby.newbussiness.spservice.domain.SpInspectInfo;
import com.kybaby.newbussiness.spservice.domain.SpInspectRecord;
import com.kybaby.newbussiness.spservice.domain.SpRegisterOrderDetail;
import com.kybaby.newbussiness.spservice.domain.SpVisitRecord;
import com.kybaby.util.DateManage;
import com.kybaby.util.LogUtil;

public class SpInterfaceDaoImpl extends CommonDaoImpl implements SpInterfaceDao{
	/**
	 * 根据用户ID获取特定表中的eventID
	 * @param userID
	 * @param clazz
	 * @param spOrgID 苏坡机构ID
	 * @return
	 */
	public <T> List<T> getObjectByUserID(Long userID,Class<T> clazz,String orgID){
		String simpleClassName = clazz.getSimpleName();
		StringBuilder hql = new StringBuilder("from "+simpleClassName+" c where 1=1 and c.localUserID = ?");
		Object[] objArr = null;
		if(orgID!=null){
			hql.append(" and c.orgID = ? ");
			objArr = new Object[]{userID,orgID};
		}else{
			objArr = new Object[]{userID};
		}
		return this.getObjectCollectionWithNoPage(hql.toString(), objArr, null);
	}
	
	/**  保存or更新检查报告 */
	public void saveOrUpdatespInspectReports(List<SpCheckReport> spCheckReports,SpCheckRecord spCheckRecord){
		modifySpcheckReportStatus(spCheckRecord);
		
		this.saveObjectWithBatch(spCheckReports);
	}
	/**  修改检查报告状态  */
	private void modifySpcheckReportStatus(SpCheckRecord spCheckRecord){
		final Long id = spCheckRecord.getId();
		this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "delete SpCheckReport c where c.spCheckRecord.id = id ";
				Query createQuery = session.createQuery(hql);
				createQuery.setLong("id", id);
				return createQuery.executeUpdate();
			}
		});
	}
	/**  保存or更新检验结果 */
	public List<SpInspectInfo> saveOrUpdateSpInspectInfos(List<SpInspectInfo> spInspectInfos,SpInspectRecord inspectRecord){
		List<SpInspectInfo> list_spInspectInfos = new ArrayList<SpInspectInfo>();
		for (SpInspectInfo spInspectInfo : spInspectInfos) {
			spInspectInfo.setSpInspectRecord(inspectRecord);
			final String checkID = inspectRecord.getCheckID();
			final String serialNo = spInspectInfo.getSerialNo();
			if(StringUtils.isBlank(checkID)) continue;
			SpInspectInfo query_SpInspectInfo = this.getHibernateTemplate().execute(new HibernateCallback<SpInspectInfo>() {
				@Override
				public SpInspectInfo doInHibernate(Session session)
						throws HibernateException, SQLException {
					String hql = "from SpInspectInfo c where c.spInspectRecord.checkID = :checkID ";
					Query createQuery = session.createQuery(hql);
					createQuery.setString("checkID", checkID);
					createQuery.setString("serialNo", serialNo);
					List<?> list = createQuery.list();
					if(list.isEmpty())return null;
					return (SpInspectInfo) list.get(0);
				}
			});
			if(query_SpInspectInfo==null){
				this.saveObject(spInspectInfo);
				list_spInspectInfos.add(spInspectInfo);
			}else{
				BeanUtils.copyProperties(spInspectInfo, query_SpInspectInfo,new String[]{"id","spInspectRecord"});
				this.updateObject(query_SpInspectInfo);
				list_spInspectInfos.add(query_SpInspectInfo);
			}
		}
		return list_spInspectInfos;
	}
	/**  保存or更新检查记录  */
	public List<SpCheckRecord> saveOrUpdateSpCheckRecords(List<SpCheckRecord> spCheckRecords,Long userID,Integer pageNo){
		String latestTime = DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(new Date());
		String optime = null;
		for (SpCheckRecord spCheckRecord : spCheckRecords) {
			final String checkID = spCheckRecord.getCheckID();
			final String orgID = spCheckRecord.getOrgID();
			SpCheckRecord query_SpCheckRecord = this.getHibernateTemplate().execute(new HibernateCallback<SpCheckRecord>() {
				@Override
				public SpCheckRecord doInHibernate(Session session)
						throws HibernateException, SQLException {
					String hql = "from SpCheckRecord c where c.checkID = :checkID and c.orgID = :orgID";
					Query createQuery = session.createQuery(hql);
					createQuery.setString("checkID", checkID);
					createQuery.setString("orgID", orgID);
					List<?> list = createQuery.list();
					if(list.isEmpty())return null;
					return (SpCheckRecord) list.get(0);
				}
			});
			spCheckRecord.setOptime(latestTime);
			if(query_SpCheckRecord==null){
				this.saveObject(spCheckRecord);
			}else{
				BeanUtils.copyProperties(spCheckRecord, query_SpCheckRecord,new String[]{"id"});
				this.updateObject(query_SpCheckRecord);
			}
			if(optime == null)optime = spCheckRecord.getOptime();
		}
		return getspCheckRecords(userID, pageNo,optime);
	}
	private List<SpCheckRecord> getspCheckRecords(Long userID,Integer pageNo,String optime){
		if(optime == null) optime = this.getMaxOptime(SpCheckRecord.class,null);
		
		StringBuilder hql = new StringBuilder();
		Map<String,String> orderby = new LinkedHashMap<String,String>();
		orderby.put("c.appTime", "desc");
		hql.append("from SpCheckRecord c where c.optime = ? and c.localUserID = ?");
		
		return this.getObjectCollectionWithPagination(hql.toString(), new Object[]{optime,userID}, orderby,pageNo);
	}
	
	/**  保存or更新检验记录  */
	@Override
	public List<SpInspectRecord> saveOrUpdateSpInspectRecords(List<SpInspectRecord> spInspectRecords,
			Long userID,Integer pageNo){
		String latestTime = DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(new Date());
		String opTime = null;
		for (SpInspectRecord spInspectRecord : spInspectRecords) {
			
			if(opTime==null) opTime = spInspectRecord.getOptime();
			
			final String checkID = spInspectRecord.getCheckID();
			final String orgID = spInspectRecord.getOrgID();
			if(StringUtils.isBlank(checkID)) continue;
			SpInspectRecord query_SpInspectRecord = this.getHibernateTemplate().execute(new HibernateCallback<SpInspectRecord>() {
				@Override
				public SpInspectRecord doInHibernate(Session session)
						throws HibernateException, SQLException {
					String hql = "from SpInspectRecord c where c.checkID = :checkID and c.orgID =:orgID";
					Query createQuery = session.createQuery(hql);
					createQuery.setString("checkID", checkID);
					createQuery.setString("orgID", orgID);
					List<?> list = createQuery.list();
					if(list.isEmpty()) return null;
					return (SpInspectRecord) list.get(0);
				}
			});
			spInspectRecord.setOptime(latestTime);
			if(query_SpInspectRecord==null){
				this.saveObject(spInspectRecord);
			}else{
				BeanUtils.copyProperties(spInspectRecord, query_SpInspectRecord,new String[]{"id"});
				this.updateObject(query_SpInspectRecord);
			}
		}
		return getinspectRecords(userID ,pageNo,opTime);
	}
	private List<SpInspectRecord> getinspectRecords(Long userID,Integer pageNo,String opTime){
		if(opTime==null) opTime = this.getMaxOptime(SpInspectRecord.class,null);
		
		StringBuilder hql = new StringBuilder();
		Map<String,String> orderby = new LinkedHashMap<String,String>();
		hql.append("from SpInspectRecord c where c.optime = ? and c.localUserID = ?");
		orderby.put("c.proTime", "desc");
		orderby.put("c.sampleNo", "desc");
		
		return this.getObjectCollectionWithPagination(hql.toString(), new Object[]{opTime,userID}, orderby,pageNo);
	}
	
	
	/**  通过就诊ID，获取就诊记录明细  */
	public SpVisitRecord getSpVisitRecord(final String eventID){
		return  this.getHibernateTemplate().execute(new HibernateCallback<SpVisitRecord>() {
			@Override
			public SpVisitRecord doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "from SpVisitRecord c where c.eventID = :eventID";
				Query createQuery = session.createQuery(sql);
				createQuery.setString("eventID", eventID);
				List<?> list = createQuery.list();
				if(list.isEmpty()) return null;
				return (SpVisitRecord) list.get(0);
			}
		});
	}
	/**  通过就诊ID，获取挂号记录明细  */
	public SpRegisterOrderDetail getSPSpRegisterOrderDetail(final String eventID){
		return this.getHibernateTemplate().execute(new HibernateCallback<SpRegisterOrderDetail>() {
			@Override
			public SpRegisterOrderDetail doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "from SpRegisterOrderDetail c where c.eventId = :eventID";
				Query createQuery = session.createQuery(sql);
				createQuery.setString("eventID", eventID);
				List<?> list = createQuery.list();
				if(list.isEmpty()) return null;
				return (SpRegisterOrderDetail) list.get(0);
			}
		});
	}
	/**  保存or更新就诊记录  */
	@Override
	public List<SpVisitRecord> saveOrUpdateSpVisitRecords(List<SpVisitRecord> spVisitRecords,Long userID,Integer pageNo){
		String latestTime = DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(new Date());
		for (SpVisitRecord spVisitRecord : spVisitRecords) {
			final String eventID = spVisitRecord.getEventID();
			final String orgID = spVisitRecord.getOrgID();
			SpVisitRecord query_SpVisitRecord = this.getHibernateTemplate().execute(new HibernateCallback<SpVisitRecord>() {
				@Override
				public SpVisitRecord doInHibernate(Session session)
						throws HibernateException, SQLException {
					String sql = "from SpVisitRecord c where c.eventID = :eventID and c.orgID = :orgID";
					Query createQuery = session.createQuery(sql);
					createQuery.setString("eventID", eventID);
					createQuery.setString("orgID", orgID);
					List<?> list = createQuery.list();
					if(list.isEmpty()) return null;
					return (SpVisitRecord) list.get(0);
				}
			});
			spVisitRecord.setOptime(latestTime);
			spVisitRecord.setUpdateTime(latestTime);
			if(query_SpVisitRecord==null){
				this.saveObject(spVisitRecord);
			}else{
				if(SpServiceConstant.VISIT_RECORD_STATUS_COMPLETE.equals(query_SpVisitRecord.getState())){
					spVisitRecord.setUpdateTime(query_SpVisitRecord.getUpdateTime());
				}
				BeanUtils.copyProperties(spVisitRecord, query_SpVisitRecord,new String[]{"id"});
				this.updateObject(query_SpVisitRecord);
			}
		}
		return getSpVisitRecords(userID,pageNo);
	}
	
	/**
	 * 获取用户的就诊记录
	 * @param ids 就诊ID串
	 * @return
	 */
	private List<SpVisitRecord> getSpVisitRecords(Long userID,Integer pageNo){
		String latestTime = this.getMaxOptime(SpVisitRecord.class,null);
		
		StringBuilder hql = new StringBuilder();
		Map<String,String> orderby = new LinkedHashMap<String,String>();
		hql.append("from SpVisitRecord c where c.optime = ? and c.localUserID = ?");
		orderby.put("c.visitTime", "desc");
		
		return this.getObjectCollectionWithPagination(hql.toString(), new Object[]{latestTime,userID}, orderby,pageNo);
	}
	
	/**  获取用户绑定的所有健康卡号信息  */
	public List<SpHealthcardManager> getHealthCardOfUserOwneds(Long userID){
		return this.getObjectCollectionWithNoPage("from SpHealthcardManager c where c.userid = ? order by c.defaultSet desc", new Object[]{userID}, null);
	}
	/**  保存or更新费用信息  */
	@Override
	public List<SpCostInfo> saveOrUpdateCostInfo(List<SpCostInfo> spCostInfos,Long userID,Integer pageNo,String opTime){
		for (SpCostInfo spCostInfo : spCostInfos) {
			final String documentNo = spCostInfo.getDocumentNo();
			final String orgID = spCostInfo.getOrgID();
			SpCostInfo query_costInfo =  this.getHibernateTemplate().execute(new HibernateCallback<SpCostInfo>() {
				@Override
				public SpCostInfo doInHibernate(Session session)
						throws HibernateException, SQLException {
					String hql = "from SpCostInfo c where  c.documentNo = :documentNo and c.orgID =:orgID";
					Query createQuery = session.createQuery(hql);
					createQuery.setString("documentNo", documentNo);
					createQuery.setString("orgID", orgID);
					List<?> list = createQuery.list();
					if(list.isEmpty()) return null;
					return (SpCostInfo) list.get(0);
				}
			});
			if(query_costInfo==null){
				spCostInfo.setFirstTime(spCostInfo.getOptime());
				spCostInfo.setLocalCostNo(System.currentTimeMillis()+"");
				this.saveObject(spCostInfo);
			}else{
				BeanUtils.copyProperties(spCostInfo, query_costInfo
						, new String[]{"id","localCostNo","payStatus","payMoney","payTime","firstTime"});
				this.updateObject(query_costInfo);
			}
		}
		return getSpCostInfos(userID,pageNo,opTime);
	}
	/**
	 * 获取费用记录
	 */
	private List<SpCostInfo> getSpCostInfos(Long userID,Integer pageNo,String opTime){
		StringBuilder hql = new StringBuilder();
		if(opTime==null) opTime = this.getMaxOptime(SpCostInfo.class,null);
		Map<String,String> orderby = new LinkedHashMap<String,String>();
		hql.append("from SpCostInfo c where c.optime = ? and c.localUserID = ?");
		orderby.put("c.spVisitRecord.visitTime", "desc");
		orderby.put("c.serialNo", "desc");
		
		return this.getObjectCollectionWithPagination(hql.toString(), new Object[]{opTime,userID}, orderby,pageNo);
	}
	/**  获取用户的挂号订单信息  */
	public List<SpRegisterOrderDetail> getSpRegisterOrderDetails(Long userID){
		StringBuilder sb = new StringBuilder("from SpRegisterOrderDetail c where 1=1 ");
		sb.append("and c.localUserId = "+ userID);
		Map<String,String> orderBy = new HashMap<String,String>();
		orderBy.put("c.optime", "desc");
		List<SpRegisterOrderDetail> orderDetails = this.getObjectCollectionWithNoPage(sb.toString(), null, orderBy);
		return orderDetails;
	}
	/**  保存或更新医嘱信息  */
	public List<SpDoctorAdviceInfo> saveOrUpdateDoctorAdviseInfo(List<SpDoctorAdviceInfo> doctorAdviseInfos,
			Long userID,Integer pageNo,String opTime){
		if(pageNo<2){
			for (SpDoctorAdviceInfo spDoctorAdviceInfo : doctorAdviseInfos) {
				final String medicalID = spDoctorAdviceInfo.getMedicalID();
				final String orgID = spDoctorAdviceInfo.getOrgID();
				SpDoctorAdviceInfo query_doctorAdvise = this.getHibernateTemplate().execute(new HibernateCallback<SpDoctorAdviceInfo>() {
					@Override
					public SpDoctorAdviceInfo doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "from SpDoctorAdviceInfo c where  c.medicalID = :medicalID and c.orgID = :orgID";
						Query createQuery = session.createQuery(hql);
						createQuery.setString("medicalID", medicalID);
						createQuery.setString("orgID", orgID);
						List<?> list = createQuery.list();
						if(list.isEmpty()) return null;
						return (SpDoctorAdviceInfo) list.get(0);
					}
				});
				if(query_doctorAdvise==null){
					this.saveObject(spDoctorAdviceInfo);
				}else{
					BeanUtils.copyProperties(spDoctorAdviceInfo, query_doctorAdvise, new String[]{"id"});
					this.updateObject(query_doctorAdvise);
				}
			}
		}
		return getDoctorAdviseInfos(userID, pageNo,opTime);
	}
	private List<SpDoctorAdviceInfo> getDoctorAdviseInfos(Long userID,Integer pageNo,String opTime){
		StringBuilder hql = new StringBuilder();
		Map<String,String> orderby = new LinkedHashMap<String,String>();
		
		if(opTime==null) opTime = this.getMaxOptime(SpDoctorAdviceInfo.class,null);
		
		hql.append("from SpDoctorAdviceInfo c where c.optime = ?  and c.localUserID = ?");
		orderby.put("c.spVisitRecord.visitTime", "desc");
		orderby.put("c.serialNo", "desc");
		
		return this.getObjectCollectionWithPagination(hql.toString(), new Object[]{opTime,userID}, orderby,pageNo);
	}
	/** 获取医生数据  */
	public SpDoctorInfo getSpDoctorInfo(final String orgID,final String deptName,final String doctorName){
		final String optime = this.getMaxOptime(SpAppointmentSchedule.class,orgID);
		return this.getHibernateTemplate().execute(new HibernateCallback<SpDoctorInfo>() {
				@Override
				public SpDoctorInfo doInHibernate(Session session)
						throws HibernateException, SQLException {
					StringBuilder sb = new StringBuilder();
					sb.append("select new SpDoctorInfo(c.depID,c.depName,c.doctorID,c.doctor) ");
					sb.append(" from SpAppointmentSchedule c where 1=1  ");
					sb.append(" and c.orgId = :orgId");
					sb.append(" and c.depName = :depName");
					sb.append(" and c.doctor = :doctor");
					sb.append(" and c.optime = :optime");
					Query createQuery = session.createQuery(sb.toString());
					createQuery.setString("orgId", orgID);
					createQuery.setString("depName", deptName);
					createQuery.setString("doctor", doctorName);
					createQuery.setString("optime", optime);
					List<?> list = createQuery.list();
					if(list.size()>1) LogUtil.warn("根据机构ID‘"+orgID+"’,部门名称‘"+deptName+"’,医生名字‘"+doctorName+"’查询出多个排班信息");
					if(list.isEmpty()) return null;
					return (SpDoctorInfo) list.get(0);
				}
		});
	}
	private String getMaxOptime(Class<?> clazz,String orgID){
		String simpleClassName = clazz.getSimpleName();
		StringBuilder hql = new StringBuilder();
		hql.append("select max(optime) from "+simpleClassName);
		if(orgID!=null){
			hql.append(" where orgID = '"+orgID+"'");
		}
		List<?> retMaxTimes = super.getHibernateTemplate().find(hql.toString());
		String maxTime = "";
		if(!retMaxTimes.isEmpty()){
			Object obj = retMaxTimes.get(0);
			if(obj!=null&&!obj.toString().equals("null")&&StringUtils.isNotBlank(obj.toString()))
				maxTime = obj.toString();
		}
		return maxTime;
	}
	
	/**
	 * 获取挂号排班信息
	 * @param spOrgID 苏坡机构ID
	 * @param spDeptName 部门名称
	 * @param spDoctorID 医生ID
	 * @return
	 */
	public SpAppointmentSchedule getRegisterScheduleInfo(final String spOrgID,final String spDeptName,final String spDoctorName){
		final String lastedTime = getMaxOptime(SpAppointmentSchedule.class,spOrgID);
		return this.getHibernateTemplate().execute(new HibernateCallback<SpAppointmentSchedule>() {
			@Override
			public SpAppointmentSchedule doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sb = new StringBuilder();
				sb.append(" from SpAppointmentSchedule c where 1=1  ");
				sb.append(" and c.orgId = :orgId");
				sb.append(" and c.depName = :depName");
				sb.append(" and c.doctor = :doctorName");
				sb.append(" and c.optime = '"+ lastedTime+"'");
				
				Query createQuery = session.createQuery(sb.toString());
				createQuery.setString("orgId", spOrgID);
				createQuery.setString("depName", spDeptName);
				createQuery.setString("doctorName", spDoctorName);
				List<?> list = createQuery.list();
				
				if(list.size()>1) LogUtil.warn("根据机构ID‘"+spOrgID+"’,部门名称‘"+spDeptName+"’,医生名字‘"+spDoctorName+"’查询出多个排班信息");
				if(list.isEmpty()) return null;
				SpAppointmentSchedule schedule = (SpAppointmentSchedule) list.get(0);
				return schedule;
			};
		});
	}
	/**  获取苏坡机构的虚拟医生名字   */
	public SpDoctorAlias getSpDoctorAlias(final String orgID){
		return this.getHibernateTemplate().execute(new HibernateCallback<SpDoctorAlias>() {
			@Override
			public SpDoctorAlias doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sb = new StringBuilder();
				sb.append(" from SpDoctorAlias c where 1=1 ");
				sb.append(" and c.orgID = :orgID");
				Query createQuery = session.createQuery(sb.toString());
				createQuery.setString("orgID", orgID);
				List<?> list = createQuery.list();
				
				if(list.isEmpty()) return null;
				SpDoctorAlias sda = (SpDoctorAlias) list.get(0);
				return sda;
			}
		});
	}
}
