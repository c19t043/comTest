package com.kybaby.newbussiness.ordermanager.dao.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.HealthPath;
import com.kybaby.domain.HealthPlan;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.OrderResults;
import com.kybaby.domain.ProductItem;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.ordermanager.dao.OrderManagerDao;
import com.kybaby.newbussiness.ordermanager.domain.BabyBasicData2;
import com.kybaby.newbussiness.ordermanager.domain.DoctorSignRecord;
import com.kybaby.newbussiness.ordermanager.domain.HealthPlanRemindIssued;
import com.kybaby.newbussiness.ordermanager.domain.OrderNodeTrack;
import com.kybaby.util.CalculationMethod;

public class OrderManagerDaoImpl extends HibernateDaoSupport implements OrderManagerDao {
	@Override
	public void saveOrUpdateDoctorSignRecord(DoctorSignRecord doctorSignRecord) {
		//doctorSignRecord.setId(null);
		if(doctorSignRecord.getId() == null){
			doctorSignRecord.setSignTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			doctorSignRecord.setSignNumber(1L);
			getHibernateTemplate().save(doctorSignRecord);
		}else{
			DoctorSignRecord old = getHibernateTemplate().get(DoctorSignRecord.class, doctorSignRecord.getId());
			old.setSignTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			old.setSignAddress(doctorSignRecord.getSignAddress());
			old.setSignNumber(old.getSignNumber().longValue() + 1);
			getHibernateTemplate().update(old);
		}
	}

	@Override
	public DoctorSignRecord getDoctorSignRecordByOrderId(Long orderId) {
		StringBuffer hql = new StringBuffer("from DoctorSignRecord a where a.orderInfo.id=").append(orderId);
		List<DoctorSignRecord> list = getHibernateTemplate().find(hql.toString());
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null; 
	}

	@Override
	public Long saveOrUpdateBabyBasicData(BabyBasicData2 babyBasicData) {
		Long id = null;
		if (babyBasicData == null ) return null;
		if(babyBasicData.getId() == null){
			id = (Long) getHibernateTemplate().save(babyBasicData);
		}else{
			id = babyBasicData.getId();
			BabyBasicData2 old = getHibernateTemplate().get(BabyBasicData2.class, id);
			BeanUtils.copyProperties(babyBasicData, old, new String[]{"id","userId","doctorId"});
			getHibernateTemplate().update(old);
		}
		return id;
	}

	@Override
	public List<BabyBasicData2> getBabyBasicData2ListByUserId(Long userId) {
		StringBuffer hql = new StringBuffer("from BabyBasicData2 a where a.userId=").append(userId);
		List<BabyBasicData2> list = getHibernateTemplate().find(hql.toString());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public void saveOrUpdateOrderResult(OrderResults orderResults,OrderInfo orderInf) {
		if (orderResults == null ) return;
		OrderResults old = this.getOrderResultsByOrderNumAndItemId(orderResults);
		if(old == null){
			getHibernateTemplate().save(orderResults);
		}else{
			Long id = old.getId();
			old.setWriteDate(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			old.setResultValue(orderResults.getResultValue());
			old.setResultRemark(orderResults.getResultRemark());
			getHibernateTemplate().update(old);
		} 
	}
	@Override
	public OrderResults getOrderResultsByOrderNumAndItemId(OrderResults orderResults){
		if(orderResults==null) return null;
		//根据订单信息及节点信息判断订单结果记录是要更新还是添加
		String  hql = "from OrderResults p where p.orderNum='"+orderResults.getOrderNum()+"' and p.itemId="+orderResults.getItemId();
		List<OrderResults> list = getHibernateTemplate().find(hql);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void saveOrUpdateOrderNodeTrack(OrderNodeTrack orderNodeTrack) {
		if (orderNodeTrack == null ) return;
		String  hql = "from OrderNodeTrack p where p.flowNodeId="+orderNodeTrack.getFlowNodeId()+" and p.orderInfoId="+orderNodeTrack.getOrderInfoId();
		List<OrderNodeTrack> list = getHibernateTemplate().find(hql);
		if(!list.isEmpty()){
			OrderNodeTrack old = list.get(0);
			BeanUtils.copyProperties(orderNodeTrack, old, new String[]{"id"});
			old.setCreateTime(new Date());
			getHibernateTemplate().update(old);
		}else{
			orderNodeTrack.setCreateTime(new Date());
			getHibernateTemplate().save(orderNodeTrack);
		}
	}
	
	@Override
	public ProductItem getItemByResultId(Long resultId) {
		List list=getHibernateTemplate().find("from ProductItem where itemResultId=? and itemStatus='Y'",resultId);
		if(list.isEmpty()==true){
			return null;
		}
		return (ProductItem)list.get(0);
	}

	@Override
	public List<HealthPath> getHealthPathByUserInfo(UserInfo userInfo) {
		int monthAge = 0;
		String date1 = userInfo.getBirthday();
		String date2 = DateManage.getDateStr("yyyy-MM-dd");
		try {
			monthAge = CalculationMethod.getMonthSpace(date1, date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String hql = "from HealthPath p where p.healthPathStatus='Y' and "+monthAge+">=p.minAge and p.maxAge >= "+monthAge+" and (p.sex = ? or p.sex ='全部')";
		List<HealthPath> list = getHibernateTemplate().find(hql,userInfo.getSex());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public HealthPlan getHealthPlanById(Long id) {
		return getHibernateTemplate().get(HealthPlan.class, id);
	}
	
	@Override
	public HealthPath getHealthPathById(Long id) {
		return getHibernateTemplate().get(HealthPath.class, id);
	}

	@Override
	public Long saveOrUpdateHealthPlanRemindIssued(
			HealthPlanRemindIssued healthPlanRemindIssued) {
		Long id = null;
		if(healthPlanRemindIssued.getId() == null){
			id = (Long) getHibernateTemplate().save(healthPlanRemindIssued);
		}else{
			id = healthPlanRemindIssued.getId();
			getHibernateTemplate().update(healthPlanRemindIssued);
		}
		return id;
	}
	/**
	 * 删除某订单下面旧的健康指导记录
	 * @param orderNum
	 */
	@Override
	public void delHealthPlanRemindIssued(String orderNum){
		//保存前先将旧数据删除
		String delSql = "delete from health_plan_remind_issued where orderNum='"+orderNum+"'";
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.createSQLQuery(delSql).executeUpdate();
		session.close();
	}
}
