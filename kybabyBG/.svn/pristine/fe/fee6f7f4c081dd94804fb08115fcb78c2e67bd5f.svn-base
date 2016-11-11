package com.kybaby.kyinterface.pagetest;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.OrderInfo;
import com.kybaby.kyinterface.util.HttpClientUtil;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;

@SuppressWarnings("all")
public class PageTestServiceImpl extends HibernateDaoSupport{
	
	public void setSessionFactoryDI(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	public List<Object[]> getOrderInfo(OrderInfo orderInfo){
		StringBuilder sb = new StringBuilder();
		sb.append("select * from order_info a");
		sb.append(" LEFT JOIN kyorder2myorder b ON a.id = b.id");
		sb.append(" LEFT JOIN user_info c ON a.userid = c.id");
		sb.append(" where 1=1 AND b.id IS NOT NULL ");
		
		if(orderInfo!=null){
			if(StringUtils.isNotBlank(orderInfo.getOrderNum())){
				sb.append(" and orderNum like '%"+ orderInfo.getOrderNum() +"%'");
			}
			if(StringUtils.isNotBlank(orderInfo.getOrderStatus())){
				sb.append(" and orderStatus = '"+ orderInfo.getOrderStatus() +"'");
			}
		}
		
		sb.append(" ORDER BY submittime DESC");
		sb.append(" LIMIT 0,10");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		SQLQuery createSQLQuery = session.createSQLQuery(sb.toString());
		
		return createSQLQuery.list();
	}
	
	public String doSMFWOrder(OrderInfo orderInfo) {
		StringBuilder sb = new StringBuilder();
		sb.append("select a.* from kyorder2myorder a");
		sb.append(" LEFT JOIN order_info b ON b.id = a.id");
		sb.append(" where b.orderNum = '" +orderInfo.getOrderNum()+"'");
		sb.append(" ORDER BY submittime DESC");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		SQLQuery createSQLQuery = session.createSQLQuery(sb.toString());
		List list = createSQLQuery.list();
		
		if(list.isEmpty()) return "没有该订单";
		
		Object[] uniqueResult = (Object[]) list.get(0);
		
		String kyId = uniqueResult[1].toString();
		String info = "";
		String error = "";
		try {
			info = HttpClientUtil.getInstance().SMFWEdit(kyId, orderInfo.getDoctorId()+"");
			
			if(info.equals("true")){
				String sql = "update order_info a set "
						+ "a.orderStatus ='已接单',"
						+ "a.doctorId = " +orderInfo.getDoctorId()
						+ " where a.id = "+uniqueResult[0].toString(); 
				SQLQuery execute = session.createSQLQuery(sql);
				execute.executeUpdate();
			}
			
		} catch (Exception e) {
			error = e.toString();
			System.out.println(error);
		}finally{
			if(StringUtils.isNotBlank(error))
				info +=":::错误信息" +error;
		}
		return info;
	}

	public List<Object[]> getInnoculationInfo(
			UserInoculationAppointmentInfo innoculationInfo) {
		
		StringBuilder sb = new StringBuilder();
/*		sb.append("SELECT a.id,c.parentName \"username\",a.opt_time \"submitDate\",");
		sb.append(" b.open_date \"openDate\",CONCAT(b.open_start_time,'-',b.open_end_time) \"bespeak\",");
		sb.append(" d.hospital_lname \"orgName\",a.status \"orderStatus\"");*/
		sb.append(" select a.id,c.parentName,a.opt_time,b.open_date,CONCAT(b.open_start_time,'-',b.open_end_time),");
		sb.append(" d.hospital_lname,a.status");
		sb.append(" FROM user_inoculation_appointment_info a ");
		sb.append(" LEFT JOIN user_info c ON a.user_id = c.id ");
		sb.append(" LEFT JOIN organ_inoculation_open_resources_detail b ON a.open_resources_detail_id = b.id ");
		sb.append(" LEFT JOIN organ_inoculation_open_resources e ON e.id = b.open_resources_id ");
		sb.append(" LEFT JOIN hospital_basic_info d ON d.id = e.ascription_organ");
		sb.append(" LEFT JOIN kyinoculationorder2myorder f ON f.id = a.id");
		sb.append(" WHERE f.id IS NOT NULL ");
		sb.append(" AND a.status IS NOT NULL");
		
		
		if(innoculationInfo!=null){
			if(StringUtils.isNotBlank(innoculationInfo.getStatus())){
				sb.append(" and a.status = '"+ innoculationInfo.getStatus() +"'");
			}
			if(StringUtils.isNotBlank(innoculationInfo.getOrgName())){
				sb.append(" and d.hospital_lname like '%"+ innoculationInfo.getOrgName() +"%'");
			}
			if(StringUtils.isNotBlank(innoculationInfo.getOpenDate())){
				sb.append(" and b.open_date = '"+ innoculationInfo.getOpenDate() +"'");
			}
		}
		
		sb.append(" ORDER BY b.open_date DESC,a.opt_time desc");
		sb.append(" LIMIT 0,10");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		SQLQuery createSQLQuery = session.createSQLQuery(sb.toString());
		List list = createSQLQuery.list();
		return list;
	}

	public String doInnoculationOrder(
			UserInoculationAppointmentInfo innoculationInfo) {
		String result = "";
		String error_ids = "";
		if(innoculationInfo!=null){
			Object [] arr = null;
			if(innoculationInfo.getId()!=null){
				arr = new Long[]{innoculationInfo.getId()};
			}else if(innoculationInfo.getIds()!=null){
				arr = innoculationInfo.getIds().split(",");
			}
			for(int i=0,len=arr.length;i<len;i++){
				Long id = Long.parseLong(arr[i].toString());
				result = InnoculationOrderHandler(id);
				if(!result.equals("true")){
					error_ids+=id+",";
				}
			}
			if(StringUtils.isNotBlank(error_ids)){
				result = "更新失败原因::"+result+",,id::" +error_ids;
			}
		}
		return result;
	}
	private String InnoculationOrderHandler(Long id){
		
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id,kyid FROM kyinoculationorder2myorder WHERE id = "+id);
		SQLQuery createSQLQuery = session.createSQLQuery(sb.toString());
		List list = createSQLQuery.list();
		
		if(list.isEmpty()) return "没有该订单";
		
		Object[] uniqueResult = (Object[]) list.get(0);
		
		String kyId = uniqueResult[1].toString();
		String info = "";
		String error = "";
		
		try {
			info = HttpClientUtil.inocalutionOrderEdit(kyId, 3+"");
			
			if(info.equals("true")){
				String sql = "UPDATE user_inoculation_appointment_info a "
						+ " SET a.status = '已登记'"
						+ " where a.id = "+uniqueResult[0].toString(); 
				SQLQuery execute = session.createSQLQuery(sql);
				execute.executeUpdate();
			}
		} catch (Exception e) {
			error = e.toString();
		}finally{
			if(StringUtils.isNotBlank(error)){
				info=":::错误信息："+error;
			}
		}
		return info;
	}

	public List<Object> getOrgName() {
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct a.hospital_lname from hospital_basic_info a where a.hospital_type = '社区医院' ");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		SQLQuery createSQLQuery = session.createSQLQuery(sb.toString());
		return createSQLQuery.list();
	}
}
