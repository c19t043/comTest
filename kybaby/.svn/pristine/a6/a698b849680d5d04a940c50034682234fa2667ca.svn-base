package com.kybaby.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorInfoDao;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.TimeInit;

/**
 * @ClassName:DoctorInfoDaoImpl
 * @Description:医生数据管理实现
 * @author Hoolee
 * @date 2015年9月27日下午5:07:59
 */
public class DoctorInfoDaoImpl extends HibernateDaoSupport implements DoctorInfoDao {

	public List<DoctorInfo> getDoctorInfoList() {
		return null;
	}

	public Double doctorDistanceList(long doctorId, long userId) {
		return null;
	}

	public DoctorInfo getDoctorInfoByDoctorId(long doctorId) {
		List<DoctorInfo> list=getHibernateTemplate().find("from DoctorInfo where id=? ", doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public List<DoctorInfo> getServiceDoctorList(long userId,
			String productName, String serviceDate, String serviceTime) {
		return null;
	}

	public List<DoctorInfo> getSomeProdcutServiceDoctorList(long userId,
			long productId) {
		return null;
	}

	public List getDoctorSomeInfoList() {
		StringBuffer hql = new StringBuffer("select distinct a.id,a.majorId,a.doctorImage,a.doctorName,a.doctorTitle,");
		hql.append(" a.seiviceStarLevel,a.dutyStarLevel,a.onTimeStarLevel,a.visitedTimes,a.serviceArea")
		.append(" from DoctorInfo a,DoctorServiceContent b where 1=1")
		.append(" and a.id=b.doctorInfo.id and b.doctorServiceType.parentDoctorServiceType.serviceTypeName='多点执业'")
		.append(" and b.doctorServiceType.parentDoctorServiceType.isAvailable='Y'")
		.append(" and b.doctorServiceType.isAvailable='Y'")
		.append(" and a.doctorStatus='Y' and a.authentication='已通过' ");
		List list=getHibernateTemplate().find(hql.toString());
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}
	
	public Long getDoctorRankByName(String positionName){
		List list=getHibernateTemplate().find("select rank from Position where name=?",positionName);
		if(list.isEmpty()==true){
			return null;
		}
		return (Long) list.get(0);
	}

	public void addSomeDoctorBalance(long doctorId, double amount) {

	}

	public void updateDoctorService(long doctorId, String serviceResult) {

	}

	public List<DoctorInfo> getConsultationDoctorList() {
		return null;
	}

	public List getSomeDoctorInfoById(long doctorId) {
		return null;
	}

	public List<String> getSomeDoctorMajorNameList(long doctorId) {
		return null;
	}

	public boolean isCanbeUsed(String phoneNumber) {
		return false;
	}

	public DoctorInfo getDoctorInfoByPhone(String phone) {
		List<DoctorInfo> list=getHibernateTemplate().find("from DoctorInfo where doctorPhone=? ",phone);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public void updateDoctorInstance(DoctorInfo doctor) {
		getHibernateTemplate().update(doctor);
	}

	public DoctorInfo getSomeDoctorInfoByPhone(String phone) {
		List<DoctorInfo> list=getHibernateTemplate().find("from DoctorInfo where doctorPhone=? and authentication='已通过' and doctorStatus='Y'",phone);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public List getDoctorLngLatById(long doctorId) {
		List list=getHibernateTemplate().find(" select doctorLng , doctorLat from DoctorAddress where doctorId=? and addressStatus='Y' ", doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public Long getDoctorServiceAreaById(long doctorId) {
		List<Long> list=getHibernateTemplate().find("select serviceArea from DoctorInfo where id=? ", doctorId);
		if(list.isEmpty()==true||list.get(0)==null){
			return null;
		}
		return list.get(0);
	}

	public boolean isServiceSomeProduct(long doctorId, long productId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String sqlQueryStr="select * from doctor_info where id= "+doctorId+" and find_in_set( '"+productId+"',REPLACE(productIds,'::',',')) ";
		Query query = session.createSQLQuery(sqlQueryStr);
		List list=query.list();
		if(list.isEmpty()==true){
			return false;
		}
		return true;
	}

	public DoctorInfo getSomeCanUserDoctor(long doctorId) {
		List<DoctorInfo> list=getHibernateTemplate().find("from DoctorInfo where id=? and doctorStatus ='Y' and authentication='已通过' ",doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public List<DoctorInfo> getSomeProductServiceDoctorList(long productId) {
		List<DoctorInfo> list=getHibernateTemplate().find("from DoctorInfo where doctorStatus='Y' and authentication='已通过' ");
		if(!(list.isEmpty())){
			for(int i =0;i<list.size();i++){
				boolean flag=true;
				DoctorInfo doctor=list.get(i);
				String productIds=doctor.getProductIds();
				if(productIds!=null){
					String[] productIdArr=productIds.split("::");
					//List<String> arrList=Arrays.asList(productIdArr);
					for(int j=0;j<productIdArr.length;j++){
						long id=Long.valueOf(productIdArr[j]);
						if(productId==id){
							flag=false;
						}
					}
					if(flag){
						list.remove(i);
					}
				}
			}
			return list;
		}
		return null;
		
		//下面的方法总是会出现异常，现进行改写 
		//2015年10月13日10:27:41
		//update by HooLee
		/*Session session = getHibernateTemplate().getSessionFactory().openSession();
		//String sqlQueryStr="SELECT a.* FROM doctor_info a WHERE   a.doctorStatus='Y' AND a.authentication='已通过'  AND FIND_IN_SET( '"+productId+"',REPLACE(a.productIds,'::',',')) ";
		String sqlQueryStr="SELECT * FROM doctor_info WHERE  doctorStatus='Y' AND authentication='已通过' AND FIND_IN_SET('"+productId+"',REPLACE(productIds,'::',','))";
		
		Query query = session.createSQLQuery(sqlQueryStr);
		//try{
			List list=query.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		if(list.isEmpty()==true){
			return null;
		}
		return list;*/
		}

	public List<TimeInit> getTimeInitList() {
		List<TimeInit> list=getHibernateTemplate().find("from TimeInit where status='Y' order by startTime ");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<Object[]> getConsulationDoctoSomeInfo() {		
		List list=getHibernateTemplate().find("select a.id , a.majorId , a.doctorTitle , b.rank, a.visitedTimes , a.isLogin , a.doctorImage , a.doctorName , a.seiviceStarLevel , a.dutyStarLevel , a.onTimeStarLevel from DoctorInfo a , Position b where a.doctorTitle = b.name and a.authentication='已通过' and a.serviceMode in('全部','在线咨询') order by a.registerTime");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List getDoctorIdByUserId(Long userId) {
		List list=getHibernateTemplate().find("SELECT doctorId FROM OrderResults WHERE userId=?",userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public Long getAfterServiceTime(Long userId, Long doctorId) {
		List list=getHibernateTemplate().find("SELECT a.afterServiceTime FROM Product a, OrderInfo b WHERE b.userId=? AND b.doctorId=? AND a.id=b.productId ORDER BY b.bespokeDate DESC",userId,doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return (Long)list.get(0);
	}

	public String getBespokeDate(Long userId, Long doctorId) {
		List list=getHibernateTemplate().find("SELECT bespokeDate FROM OrderInfo WHERE userId=? AND doctorId=? ORDER BY bespokeDate DESC ",userId,doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

	public String getMajorNameByMajorId(Long id) {
		List list=getHibernateTemplate().find("SELECT major FROM Major WHERE id=? AND majorStatus='Y'",id);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

}
