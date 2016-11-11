package com.kybaby.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.AccountDao;
import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.DoctorPoints;
import com.kybaby.domain.DoctorWithdrawals;
import com.kybaby.domain.RecommendRule;
import com.kybaby.domain.RecommentAwardRecord;

/**
 * @author sujiantang
 *
 */
public class AccountDaoImpl extends HibernateDaoSupport implements AccountDao{

	@Override
	public Double getDoctorBalanceByDoctorId() {
		return null;
	}

	@Override
	public List<DoctorAccount> getAmountDetailByDoctorId(Long id) {
		List list=getHibernateTemplate().find("from DoctorAccount where doctorId=?",id);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public List<DoctorPoints> getPointDetailByDoctorId(Long id) {
		List list=getHibernateTemplate().find("from DoctorPoints where doctorId=?",id);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public void updateDoctorInfo(DoctorInfo doctorInfo) {
		getHibernateTemplate().update(doctorInfo);
	}

	@Override
	public void saveDoctorPoint(DoctorPoints doctorPoints) {
		getHibernateTemplate().save(doctorPoints);
		
	}

	@Override
	public void saveDoctorAccount(DoctorAccount doctorAccount) {
		getHibernateTemplate().save(doctorAccount);
		
	}
	
	@Override
	public RecommendRule getSomeCanUseRule(String ruleName) {
		List<RecommendRule> list=getHibernateTemplate().find("from RecommendRule where ruleName=? and ruleStatus='Y'",ruleName);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	@Override
	public void saveRecommentAwardRecord(RecommentAwardRecord recommentAwardRecord) {
		getHibernateTemplate().save(recommentAwardRecord);
	}

	@Override
	public void saveDoctorWithdrawals(DoctorWithdrawals doctorWithDraw) {
		getHibernateTemplate().save(doctorWithDraw);
	}

	@Override
	public Double getAccountNowMonthByDoctorId(Long doctorId) {
		StringBuffer sql = new StringBuffer("SELECT SUM(amount) FROM doctor_account WHERE 1=1");
		sql.append(" and TYPE='+'");
		sql.append(" AND doctorid=:doctorId ");
		sql.append(" AND DATE_FORMAT(updateTime,'%Y-%m')=:preMonth");
		//获取当月
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
		String time = format.format(c.getTime());
		
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql.toString());
		query.setLong("doctorId", doctorId);
		query.setString("preMonth", time);
		List list = query.list();
		session.close();
		if(!list.isEmpty()){
			return list.get(0)==null?0D:(Double) list.get(0);
		}
		return 0D;
	}
	
	
	public static void main(String[] args) {
		//获取上月
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat format =  new SimpleDateFormat("MM");
		String time = format.format(c.getTime());
		System.out.println(time);
	}
	
}
