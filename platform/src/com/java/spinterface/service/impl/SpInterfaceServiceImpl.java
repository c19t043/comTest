 package com.java.spinterface.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.ServiceImpl;
import com.java.spinterface.generatecode.ToolInterfaceSoap;
import com.java.spinterface.service.SpInterfaceService;
import com.java.spinterface.vo.SpAppointmentSchedule;
import com.java.spinterface.vo.SpDoctorInfo;
import com.java.util.LogUtil;
import com.java.util.SpInterfaceUtil;

public class SpInterfaceServiceImpl extends ServiceImpl implements
SpInterfaceService {
	
	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}
	private ToolInterfaceSoap spInterface;
	/**   查询苏坡挂号信息   */
	@SuppressWarnings("unchecked")
	public List<SpAppointmentSchedule> getRegisterSchedules(
			PageSortModel model, SpAppointmentSchedule spAppointmentSchedule){
		
		String maxTime = getMaxOptime();
		
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from SpAppointmentSchedule c where 1=1 ")
		.append(" and c.optime = '"+maxTime+"'")
		.append(" and c.depName IN ('儿科','儿保室')  ");
		//.append(" and c.orgID = '"+SP_HOSPITAL_CODE+"'");
		if(spAppointmentSchedule!=null){
			if(StringUtils.isNotBlank(spAppointmentSchedule.getDepName())){
				sb.append(" and c.depName = :depName");
				params.put("depName", spAppointmentSchedule.getDepName());
			}
			if(StringUtils.isNotBlank(spAppointmentSchedule.getOrgID())){
				sb.append(" and c.orgID = :orgID");
				params.put("orgID", spAppointmentSchedule.getOrgID());
			}
			if(StringUtils.isNotBlank(spAppointmentSchedule.getDoctor())){
				sb.append(" and c.doctor = :doctor");
				params.put("doctor", spAppointmentSchedule.getDoctor());
			}
		}
		
		sb.append(" order by c.orgID desc,c.depName");
		
		return (List<SpAppointmentSchedule>) super.listForEc(sb.toString(), model, params);
	}
	
	private String getMaxOptime(){
		Object maxTime = super.getPersistProxy().getOrmPersistence()
				.findObjectByHQL("select max(optime) from SpAppointmentSchedule", new Object[]{});
		return maxTime==null?"":maxTime.toString();
	}
	
	private List<SpDoctorInfo> saveDoctorInfos(){
		
		String maxTime = getMaxOptime();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT new SpDoctorInfo(a.doctorID,a.doctor,a.depID,a.depName)")
		.append(" FROM SpAppointmentSchedule a ")
		.append(" WHERE  1=1 ")
		.append(" and a.optime = '"+maxTime+"'")
		.append(" AND a.depName IN ('儿科','儿保室') ");
		//.append(" and a.orgID = '"+SP_HOSPITAL_CODE+"'");
		List<SpDoctorInfo> spDoctorInfos = super.list(sb.toString(), -1, -1, null);
		
		for (SpDoctorInfo spDoctorInfo : spDoctorInfos) {
			List<SpDoctorInfo> retDoctorInfo = this.list("from SpDoctorInfo c where c.doctorName = '" + spDoctorInfo.getDoctorName() +"'", -1, -1, null);
			if(retDoctorInfo.isEmpty()){
				super.add(spDoctorInfo);
			}
		}
		return spDoctorInfos;
	}
	
	@Override
	public List<SpAppointmentSchedule> saveSpAppointmentSchedules(){
		SpAppointmentSchedule appointment = new SpAppointmentSchedule();
		appointment.setSp_OperType("101");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String optime = sdf.format(new Date());
		//苏坡社区
		List<SpAppointmentSchedule> saveDatas = new ArrayList<SpAppointmentSchedule>();
		appointment.setSp_OrgCode(SP_HOSPITAL_CODE);
		List<SpAppointmentSchedule> reqData = getReqData(appointment,SpAppointmentSchedule.class);
		for (SpAppointmentSchedule spAppointmentSchedule : reqData) {
			spAppointmentSchedule.setOrgID(appointment.getSp_OrgCode());
			spAppointmentSchedule.setOptime(optime);
		}
		saveDatas.addAll(reqData);
		
		appointment.setSp_OrgCode(NINTH_HOSPITAL_CODE);
		reqData = getReqData(appointment,SpAppointmentSchedule.class);
		for (SpAppointmentSchedule spAppointmentSchedule : reqData) {
			spAppointmentSchedule.setOrgID(appointment.getSp_OrgCode());
			spAppointmentSchedule.setOptime(optime);
		}
		saveDatas.addAll(reqData);
		
		for (SpAppointmentSchedule spAppointmentSchedule : saveDatas) {
			if(StringUtils.isNotBlank(spAppointmentSchedule.getRegisterID())){
				super.add(spAppointmentSchedule);
			}
		}
		
		saveDoctorInfos();
		
		return saveDatas;
	}
	@Override
	public List<SpDoctorInfo> saveSPDoctorInfos(){
		/*
		 * 获取挂号排版医生数据
		 */
		List<SpDoctorInfo> doctorInfos = this.getDoctorInfoofSpappointmentschedule();
		for (SpDoctorInfo spDoctorInfo : doctorInfos) {
			List<SpDoctorInfo> retDoctorInfo = 
					this.list("from SpDoctorInfo c where c.doctorName = '" + spDoctorInfo.getDoctorName() +"'", -1, -1, null);
			if(retDoctorInfo.isEmpty()){
				super.add(spDoctorInfo);
			}else{
				SpDoctorInfo doctorInfo = retDoctorInfo.get(0);
				BeanUtils.copyProperties(spDoctorInfo, doctorInfo, new String[]{"id"});
				super.edit(doctorInfo);
			}
		}
		return doctorInfos;
	}
	@SuppressWarnings("unchecked")
	private List<SpDoctorInfo> getDoctorInfoofSpappointmentschedule(){
		StringBuilder sb  = new StringBuilder();
		String maxTime = getMaxOptime();
		
		sb.append("SELECT distinct new SpDoctorInfo(a.doctorID,a.doctor,a.depID,a.depName,a.orgID,b.id)")
		.append(" FROM SpAppointmentSchedule a,DoctorInfo b ")
		.append(" WHERE  1=1 ")
		.append(" and a.doctor = b.doctorName")
		.append(" and a.optime = '"+maxTime+"'")
		.append(" AND a.depName IN ('儿科','儿保室') ");
		//.append(" and a.orgID = '"+SP_HOSPITAL_CODE+"'");
		final String hql = sb.toString();
		return (List<SpDoctorInfo>) this.getPersistProxy().getOrmPersistence().getHibernateTemp().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query createQuery = session.createQuery(hql);
				return createQuery.list();
			}
		});
	}
	/**
	 * 获取苏坡所有科室名字
	 */
	@SuppressWarnings("unchecked")
	public List<String> getdeptNames(){
		final String maxTime = getMaxOptime();
		final String sql = "select distinct a.depName FROM SpAppointmentSchedule a WHERE a.optime = :optime ";
		return (List<String>) this.getPersistProxy().getOrmPersistence().getHibernateTemp().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				createSQLQuery.setString("optime", maxTime);
				return createSQLQuery.list();
			}
		});
	}
	
	/**
	 * 获取所有医生
	 */
	public List<String> getNameOfDoctorInfos(){
		String hql = "select distinct c.doctorName from SpDoctorInfo c";
		return super.getPersistProxy().getOrmPersistence().findByHQLQuery(hql);
	}
	
	private <T> List<T> getReqData(Object obj,Class<T> clazz){
		String request = SpInterfaceUtil.createXml(obj);
		List<T> list = new ArrayList<T>();
		try {
			String res = spInterface.interactionOperating(request);
			list = SpInterfaceUtil.xml2JavaBean(res,clazz);
		} catch (Exception e) {
			LogUtil.error(e.getMessage(), e);
		}
		return list;
	}

	public void setSpInterface(ToolInterfaceSoap spInterface) {
		this.spInterface = spInterface;
	}
}
