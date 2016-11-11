package com.kybaby.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorProductDao;
import com.kybaby.domain.DoctorProduct;

/**
 * @ClassName:DoctorProductDaoImpl
 * @Description:医生提供的服务产品数据管理接口实现
 * @author Hoolee
 * @date 2015年10月7日下午10:08:21
 */
public class DoctorProductDaoImpl extends HibernateDaoSupport implements DoctorProductDao {

	public List<Long> getDoctorIdByDteAndTime(String serviCEDate,String serviceTimes) {
		String hqlStr="select doctorId from DoctorProduct where serviceDate= '"+serviCEDate+"' and serviceTimes in "+serviceTimes+" and isProvide= 'N' group by doctorId ";
		List<Long> list=getHibernateTemplate().find(hqlStr);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public long getSomeDoctorServiceTimeAmount(long doctorId, String serviceDate,String serviceTimes) {
		String hqlStr="select count(*) from DoctorProduct where doctorId="+doctorId+" and serviceDate='"+serviceDate+"' and serviceTimes in "+serviceTimes+"and isProvide='N'";
		List<Long> list=getHibernateTemplate().find(hqlStr);
		if(list.isEmpty()==true){
			return 0;
		}
		return list.get(0);
	}

	public List<String> getServiceDate(long doctorId, long productId,String rightNow) {
		String hql="select serviceDate from DoctorProduct where doctorId="+doctorId+" and serviceDate >='"+rightNow+"' and isProvide='N' group by serviceDate";
		List<String> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<String> getServiceTime(long doctorId, String serviceDate) {
		List<String> list=getHibernateTemplate().find("select serviceTimes from DoctorProduct where doctorId = ? and serviceDate = ? and isProvide = 'N' order by serviceTimes ", new Object[]{doctorId,serviceDate});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<DoctorProduct> getSomeDoctorProduct(long doctorId,String serviceDate, String serviceTimes) {
		
		/*Session session = getHibernateTemplate().getSessionFactory().openSession();
		String sqlQueryStr="from DoctorProduct where doctorId= "+doctorId+" and serviceDate='"+serviceDate+"'  and find_in_set( ' "+serviceTimes+"',serviceTimes) ";
		Query query = session.createSQLQuery(sqlQueryStr);
		List list=query.list();
		if(list.isEmpty()==true){
			return null;
		}
		return list;*/
		String hqlStr="from DoctorProduct where doctorId="+doctorId+" and serviceDate='"+serviceDate+"' and serviceTimes in"+serviceTimes;
		List<DoctorProduct> list=getHibernateTemplate().find(hqlStr);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public void updatDoctorProductInstance(DoctorProduct doctorProduct) {
		getHibernateTemplate().update(doctorProduct);
	}

	public List<String> canBeUserDateList(String rightNow) {
		String hql="select serviceDate from DoctorProduct where serviceDate>='"+rightNow+"' group by serviceDate order by serviceDate ";
		List<String> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<String> canBeUserServiceTimeList(String serviceDate) {
		List<String> list=getHibernateTemplate().find("select serviceTimes from DoctorProduct where serviceDate=? group by serviceTimes order by serviceTimes ", serviceDate);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

}
