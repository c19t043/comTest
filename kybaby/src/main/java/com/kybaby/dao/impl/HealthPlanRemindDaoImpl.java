package com.kybaby.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.HealthPlanRemindDao;
import com.kybaby.domain.HealthPath;
import com.kybaby.domain.HealthPlanRemind;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctoroperateflow.domain.HealthPlanRemindIssued;

/**
 * @ClassName:HealthPlanRemindDaoImpl
 * @Description:健康提醒数据管理实现
 * @author Hoolee
 * @date 2015年9月29日下午3:36:49
 */
public class HealthPlanRemindDaoImpl extends HibernateDaoSupport implements HealthPlanRemindDao {

	public List<HealthPlanRemind> todayHealthPlanRemind(long userId,String strDate) {
		//String hqlStr="FROM HealthPlanRemind WHERE userId='"+userId+"' AND FIND_IN_SET('"+strDate+"',executeDateList)";
		String hqlStr="FROM HealthPlanRemind WHERE userId= '"+userId+"'AND executeDateList LIKE '%"+strDate+"%'";
		List<HealthPlanRemind> list=getHibernateTemplate().find(hqlStr);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<Long> getSomeOrderNumPlanIdList(String orderNum, long userId) {
		List<Long> list=getHibernateTemplate().find("select healthPlanId from HealthPlanRemind where orderNum=? and userId=? ", new Object[]{orderNum,userId});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<Long> getSomeHealthPlanPathIdList(String orderNum, long userId,
			long healthPlanId) {
		List<Long> list=getHibernateTemplate().find("select healthPathId from HealthPlanRemind where userId=? and orderNum=? and healthPlanId=?  ", new Object[]{userId,orderNum,healthPlanId});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public HealthPlanRemind getSomeDateHealthPlanReamin(String orderNum,
			long userId, long planId, long pathId) {
		List<HealthPlanRemind> list=getHibernateTemplate().find("from HealthPlanRemind where orderNum=? and userId=? and healthPlanId=? and healthPathId=?", new Object[]{orderNum,userId,planId,pathId});
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public long getSomePathAmount(long userId, String orderNum, long pathId,long planId) {
		//update by fkn 
		Session session=getHibernateTemplate().getSessionFactory().openSession();
		String sqlQuery="SELECT  CAST(LENGTH(g.executeResult)-LENGTH(REPLACE(g.executeResult,'Y','')) AS CHAR) FROM health_plan_remind g WHERE g.userId="+userId+" and g.orderNum='"+orderNum+"' and g.healthPlanId="+planId+" and g.healthPathId="+pathId;
		Query query=session.createSQLQuery(sqlQuery);
		List list=query.list();
		if(list.isEmpty()==true){
			return 0;
		}
		return Long.valueOf(String.valueOf(list.get(0)));
	}

	public List<HealthPlanRemind> getDateStr(long userId, String orderNum) {
		List<HealthPlanRemind> list=getHibernateTemplate().find("from HealthPlanRemind where orderNum=? and userId=? order by id ", new Object[]{orderNum,userId});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public void updateSomeRemain(HealthPlanRemind remain) {
		getHibernateTemplate().update(remain);
	}

	@Override
	public List<HealthPlanRemindIssued> getHealthPlanRemindIssuedByUser(
			UserInfo userInfo) {
		List<HealthPlanRemindIssued> list =
				getHibernateTemplate().find("from HealthPlanRemindIssued where userId=? order by createTime", new Object[]{userInfo.getId()});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public List<HealthPath> getHealthPathListByOrderNum(String orderNum) {
		String hql = "select b from HealthPlanRemindIssued a,HealthPath b where a.healthPathId=b.id and healthPathStatus='Y' and a.orderNum=?";
		List<HealthPath> list = getHibernateTemplate().find(hql.toString(),orderNum);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

}
