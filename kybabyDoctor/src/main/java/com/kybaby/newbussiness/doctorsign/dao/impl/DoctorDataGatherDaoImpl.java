package com.kybaby.newbussiness.doctorsign.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.kybaby.common.CommonDaoImpl;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.RoleSelect;
import com.kybaby.newbussiness.doctorsign.dao.DoctorDataGatherDao;
import com.kybaby.newbussiness.doctorsign.domain.DoctorOrderSummary;
import com.kybaby.newbussiness.doctorsign.domain.DoctorRegisterMaintenance;
import com.kybaby.newbussiness.doctorsign.domain.DoctorSignApprovalFlowRecord;

public class DoctorDataGatherDaoImpl  extends CommonDaoImpl implements DoctorDataGatherDao{
	/**
	 * 根据医生ID删除服务类容
	 * @param dctID
	 */
	@Override
	public void deleteDoctorServiceContent(final Long dctID){
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query createQuery = session.createQuery("delete DoctorServiceContent c where c.doctorInfo.id = :dctID");
				createQuery.setLong("dctID", dctID);
				return createQuery.executeUpdate();
			}
		});
	}
	/**
	 * 根据医生id串获取医生订单汇总数据
	 * @param ids
	 */
	@Override
	public List<DoctorOrderSummary> getDoctorOrderGather(final String ids){
		return this.getHibernateTemplate().execute(new HibernateCallback<List<DoctorOrderSummary>>() {
			@Override
			public List<DoctorOrderSummary> doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT *,COUNT(1)  FROM doctor_order_summary c where 1=1");
				sb.append(" and c.doctor_id  in ("+ids+") ");
				sb.append(" GROUP BY c.doctor_id,c.visit_date");
				SQLQuery createSQLQuery = session.createSQLQuery(sb.toString());
				List<DoctorOrderSummary> dctOrderGathers = new ArrayList<DoctorOrderSummary>();
				List<Object> list = createSQLQuery.list();
				for (Object object : list) {
					Object[] objArr = (Object[]) object;
					DoctorOrderSummary dctOrderGather = new DoctorOrderSummary();
					if(objArr[1]!=null){
						DoctorInfo dctInfo = getObjectByID(Long.parseLong(objArr[1].toString()), DoctorInfo.class);
						dctOrderGather.setDoctorInfo(dctInfo);
					}
					if(objArr[2]!=null){
						dctOrderGather.setVisitDate(objArr[2].toString());
					}
					if(objArr[5]!=null){
						dctOrderGather.setCount(objArr[5].toString());
					}
					dctOrderGathers.add(dctOrderGather);
				}
				return dctOrderGathers;
			}
		});
	}
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
		if(StringUtils.isBlank(DoctorIDs)){
			return new ArrayList<DoctorInfo>();
		}
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from DoctorInfo c where c.id in ("+DoctorIDs+")");
		return super.getObjectCollectionWithNoPage(sb.toString(), params, null);
	}

	@Override
	public List<DoctorInfo> getDoctorInfosByPhone(String phones) {
		if(StringUtils.isBlank(phones)){
			return new ArrayList<DoctorInfo>();
		}
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
