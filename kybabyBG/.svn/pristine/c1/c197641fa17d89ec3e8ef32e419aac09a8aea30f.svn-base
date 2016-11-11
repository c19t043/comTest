package com.kybaby.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorInfoDao;
import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.DoctorPoints;
import com.kybaby.domain.DoctorProduct;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.RecommentAwardRecord;
public class DoctorInfoDaoImpl extends HibernateDaoSupport implements DoctorInfoDao {

	@Override
	public long getNumofDoctor(String startDate, String endDate) {
		String hql="SELECT COUNT(id) FROM DoctorInfo WHERE registerTime >'"+ startDate+"' AND registerTime<'"+endDate+"' ";
		List list=getHibernateTemplate().find(hql);
		return Long.valueOf(String.valueOf(list.get(0)));
	}

	@Override
	public List getAllBriefDoctorInfo() {
		String hql="SELECT id,doctorName,doctorSex,doctorImage,doctorTitle,doctorEmployer,doctorPhone,authentication,serviceArea FROM DoctorInfo";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
	    return null;
		else return list;
	}

	@Override
	public List getDetailDoctorInfoById(long id) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
	    String sqlQueryStr=
  " SELECT a.*,"+
  " GROUP_CONCAT(DISTINCT b.symptomName SEPARATOR '::'),"+
  " GROUP_CONCAT(DISTINCT c.name SEPARATOR '::'),"+
  " GROUP_CONCAT(DISTINCT d.major SEPARATOR '::')"+
  " FROM"+ 
  "  doctor_info a , symptom_tag b ,  product c, major d"+
  " WHERE"+ 
  " FIND_IN_SET(b.id, REPLACE(a.advisoryLabelIds,'::',',')) AND"+
  " FIND_IN_SET(c.id,REPLACE(a.productIds,'::',',')) AND "+
  " FIND_IN_SET(d.id,REPLACE(a.majorId,'::',',')) AND a.id='"+id+"'"+
  " GROUP BY a.id";
	   Query query = session.createSQLQuery(sqlQueryStr);
	   List list=query.list();
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}

	@Override
	public List newGetDetailDoctorInfoById(long id) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
	    String sqlQueryStr=
  " SELECT "+
	"		a.id  ,	a.idCard  ,	a.doctorName  ,"+
	"		a.doctorSex  ,a.doctorImage , a.doctorPhone  ,"+ 
	"		a.doctorTitle , a.doctorEmployer , a.comments  ,"+ 
	"		a.doctorStatus,  a.authentication , a.registerTime  ,"+ 
	"		a.doctorPassword,  a.doctorBalance  ,a.advisoryLabelIds  ,"+ 
	"		a.serviceMode  ,a.bankAccountName  ,a.bankCard  ,"+ 
	"		a.nickName  ,	a.comeMethod  ,	a.serviceArea  ,	a.defaultAddressId  ,"+
	"		a.visitedTimes  ,	a.seiviceStarLevel  ,	a.withdrawalsPassword  ,"+
	"		a.seiviceStarHitCount  ,	a.dutyStarLevel  ,	a.dutyStarLevelHitCount  ,"+
	"		a.onTimeStarLevel  ,	a.onTimeStarLevelHitCount  ,	a.doctorPoints  ,"+
	"		a.majorId  ,	a.seiviceStarTotal  ,	a.dutyStarTotal  ,	a.onTimeStarTotal  ,"+
	"		a.productIds  ,	a.licenseImage  ,	a.isLogin  ,	a.openId  ,	a.doctor_comment  ,"+
	"		a.is_recommend  ,	a.hospital_id  ,	a.department  ,	a.good_at_field  ,"+
	"		a.clinical_experience  ,	a.service_type_ids , "+  
  " GROUP_CONCAT(DISTINCT b.symptomName SEPARATOR '::') as symptomName,"+
  " GROUP_CONCAT(DISTINCT c.name SEPARATOR '::') as productName,"+
  " GROUP_CONCAT(DISTINCT d.major SEPARATOR '::') as majorName,"+
  " GROUP_CONCAT(DISTINCT e.name SEPARATOR '::') as goodfieldName,"
  + " a.doctor_type,"
  + " a.service_add_type"+
  " FROM"+ 
  "  doctor_info a , symptom_tag b ,  product c, major d, doctor_good_field e"+
  " WHERE"+ 
  " FIND_IN_SET(c.id,REPLACE(a.productIds,'::',',')) AND "+
  " FIND_IN_SET(e.id,REPLACE(a.good_at_field,'::',',')) AND "+
  " FIND_IN_SET(d.id,REPLACE(a.majorId,'::',',')) AND a.id='"+id+"'";
	   Query query = session.createSQLQuery(sqlQueryStr);
	   List list=query.list();
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}
	
	@Override
	public List getAllBriefInfoOfDoctorPlan() {
		String hql=" SELECT a.id,a.doctorName,a.doctorSex,a.doctorImage,a.doctorPhone,b.doctorProvince,b.doctorCity,b.doctorArea,b.doctorStreet,b.detailAddress FROM DoctorInfo a,DoctorAddress b WHERE a.id=b.doctorId AND b.addressStatus='Y' AND a.doctorName<>''";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true){
			return null;
		}
			return list;
	}

	@Override
	public List getDoctorIdAndName(long updateOrderId) {
		List list=getHibernateTemplate().find("SELECT id,doctorName,doctorPhone FROM DoctorInfo WHERE authentication='已通过' AND doctorStatus='Y' ");
		if(list.isEmpty()==true)
		{
			return null;
		}
		else
		{
			return list;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public DoctorInfo getDoctorInfoById(long id) {
		List list=getHibernateTemplate().find("FROM DoctorInfo WHERE id = ? ",id);
		if(list.isEmpty()==true)
			return null;	
		else return (DoctorInfo)list.get(0);
	}

	@Override
	public List getAllDoctor() {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
	    String sqlQueryStr=
	    		" SELECT " +
	    		"  a.*, " +
	    		  " GROUP_CONCAT(DISTINCT b.symptomName SEPARATOR '::'), " +
	    		  " GROUP_CONCAT(DISTINCT c.name SEPARATOR '::'), " +
	    		  " GROUP_CONCAT(DISTINCT d.major SEPARATOR '::'), " +
	    		  " GROUP_CONCAT(DISTINCT e.service_type_name SEPARATOR '::') " +
 	    		" FROM  doctor_info a LEFT JOIN " + 
	    		 "  symptom_tag b ON   FIND_IN_SET(b.id, REPLACE(a.advisoryLabelIds,'::',',')) LEFT JOIN " + 
	    		 "  product c     ON   FIND_IN_SET(c.id,REPLACE(a.productIds,'::',',')) AND FIND_IN_SET(c.id,REPLACE(a.productIds,'::',',')) LEFT JOIN " + 
	    		 "  major d       ON   FIND_IN_SET(d.id,REPLACE(a.majorId,'::',',')) " +
	    		 "  LEFT JOIN doctor_service_type e ON FIND_IN_SET(e.id,REPLACE(a.service_type_ids,'::',',')) " +
	    		 " GROUP BY a.id " ;

	   Query query = session.createSQLQuery(sqlQueryStr);
	   List list=query.list();
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}

	@Override
	public List<Long> getBigDoctorList(String serviceDate, Long serviceTimes,Long hour) {

		List<Long> list=getHibernateTemplate().find("SELECT doctorId FROM DoctorProduct WHERE serviceDate=? AND isProvide='N' AND serviceTimes>? GROUP BY doctorId,serviceDate HAVING COUNT(serviceTimes)>? ",serviceDate,serviceTimes,hour-1);
		if(list.isEmpty()==true)
		{
			return null;
		}
		else 
		{
			return (List<Long>)list;
		}
	}

	@Override
	public List getOneDoctorSomeDayInfo(Long a, String serviceDate,Long serviceTimes, Long hour) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		 String sqlQueryStr="SELECT isProvide FROM doctor_product WHERE doctorId="+a+" AND serviceDate='"+serviceDate+"' AND serviceTimes>"+serviceTimes+" ORDER BY serviceTimes LIMIT "+hour;
//		List<String> list=getHibernateTemplate().find("SELECT isProvide FROM DoctorProduct WHERE doctorId=? AND serviceDate=? AND serviceTimes>? ORDER BY serviceTimes LIMIT ? ",a,serviceDate,serviceTimes,hour);
		   Query query = session.createSQLQuery(sqlQueryStr);
		   List list=query.list();
		   if(list.isEmpty()==true)
		   {
			   return null;
		   }
		else
		{
			return (List<String>)list;
		}
	}

	@Override
	public List getOneDoctorSomeDayInfoIdAndStatus(Long oldDoctorId,String serviceDate, Long serviceTimes, Long hour) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String sqlQueryStr="SELECT id,isProvide FROM doctor_product WHERE doctorId="+oldDoctorId+" AND serviceDate='"+serviceDate+"' AND serviceTimes>"+serviceTimes+" ORDER BY serviceTimes LIMIT " +hour ;
		Query query = session.createSQLQuery(sqlQueryStr);
		List list=query.list();
//		List list=getHibernateTemplate().find("SELECT id,isProvide FROM DoctorProduct WHERE doctorId=? AND serviceDate=? AND serviceTimes>? ORDER BY serviceTimes LIMIT ? ",oldDoctorId,serviceDate,serviceTimes,hour);
		if(list.isEmpty()==true)
		{
		return null;
		}
		else
		{
			return list;
		}
	}

	@Override
	public DoctorProduct getDoctorProductById(Long someId) {
		List list=getHibernateTemplate().find("FROM DoctorProduct WHERE id=?",someId);
		if(list.isEmpty()==true)
		{
		return null;
		}
		else
		{
		return (DoctorProduct)list.get(0);
		}
	}

	@Override
	public List getDoctorLngLatById(long doctorId) {
		List list=getHibernateTemplate().find(" select doctorLng , doctorLat from DoctorAddress where doctorId=? and addressStatus='Y' ", doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public List getUserLngLatById(long userId) {
		List list=getHibernateTemplate().find("select userLng , userLat from UserInfo where id=? ", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public RecommentAwardRecord getSomeAwardRecord(String recommendType,long beenRecommendDoctorId, String isGrant, String whenToGrant) {
		List<RecommentAwardRecord> list=getHibernateTemplate().find("from RecommentAwardRecord where beenRecommendDoctorId=? and recommendType=? and isGrant=? and whenToGrant=? ", new Object[]{beenRecommendDoctorId,recommendType,isGrant,whenToGrant});
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public void addNewDoctorAccount(DoctorAccount doctorAccount) {
		getHibernateTemplate().save(doctorAccount);
	}

	public void addNewUserPoints(DoctorPoints doctorPoints) {
		getHibernateTemplate().save(doctorPoints);
	}

	@Override
	public void updateDoctorProductByOrder(OrderInfo orderInfo) {
		// TODO Auto-generated method stub
		if(orderInfo == null) return;
		StringBuffer sql = new StringBuffer("update doctor_product set isProvide='Y' where 1=1");
		if(orderInfo != null){
			if(orderInfo.getDoctorId() != null ){
				sql.append(" and doctorId='"+orderInfo.getDoctorId()+"'");
			}
			if(orderInfo.getBespokeDate() != null && "".equals(orderInfo.getBespokeDate().trim())){
				sql.append(" and serviceDate='"+orderInfo.getBespokeDate().trim()+"'");
			}
			if(orderInfo.getBespokeTime() != null && "".equals(orderInfo.getBespokeTime().trim())){
				String bespokeTime = orderInfo.getBespokeTime();
				String startTime = bespokeTime.split(":00")[0];
				String endTime = bespokeTime.split(":00")[2];
				sql.append("  AND serviceTimes IN("+startTime+","+endTime+") ");//上门时间
			}
		}
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.createSQLQuery(sql.toString()).executeUpdate();
		session.close();
	}

}
