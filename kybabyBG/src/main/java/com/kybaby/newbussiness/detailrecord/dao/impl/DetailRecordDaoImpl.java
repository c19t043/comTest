package com.kybaby.newbussiness.detailrecord.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.RecommentAwardRecord;
import com.kybaby.newbussiness.detailrecord.dao.DetailRecordDao;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicOtherContactsInfo;

public class DetailRecordDaoImpl extends HibernateDaoSupport implements DetailRecordDao {

	@Override
	public List<RecommentAwardRecord> getAllRecommentAwardRecord(
			RecommentAwardRecord recommentAwardRecord) {
		StringBuffer sql = new StringBuffer("");
//		sql.append(" SELECT a.recommendType,a.recommendUserId,a.beenRecommendUserId, ");
//		sql.append(" a.recommendDoctorId,a.beenRecommendDoctorId,a.awardTime, ");
//		sql.append("  a.pointsAmount,a.amount,a.couponId,a.comments,a.isGrant,a.whenToGrant ");
		sql.append("  SELECT a.* ");
		sql.append(" FROM recomment_award_record a ");
		sql.append(" LEFT JOIN user_info beenUser ");
		sql.append(" ON a.beenRecommendUserId = beenUser.id ");
		sql.append("  LEFT JOIN user_info us ");
		sql.append("  ON a.recommendUserId = us.id ");
		sql.append(" LEFT JOIN doctor_info beenDoctor ");
		sql.append("  ON a.beenRecommendDoctorId = beenDoctor.id ");
		sql.append(" LEFT JOIN doctor_info doctor ");
		sql.append(" ON a.recommendDoctorId = doctor.id ");
		sql.append("  LEFT JOIN coupon cou  ");
		sql.append(" ON cou.id = a.couponId ");
		sql.append(" WHERE 1 = 1 ");
		
		if(recommentAwardRecord != null){
			if(recommentAwardRecord.getIsGrant() != null && !"".equals(recommentAwardRecord.getIsGrant())){//是否发放
				sql.append(" and a.isGrant= '"+recommentAwardRecord.getIsGrant()+"'");
			}
			if(recommentAwardRecord.getRecommendType() != null && !"".equals(recommentAwardRecord.getRecommendType().trim())){
				sql.append(" and a.recommendType like '%"+recommentAwardRecord.getRecommendType().trim()+"%'");
			}
			if(recommentAwardRecord.getAwardTime() != null && !"".equals(recommentAwardRecord.getAwardTime().trim())){
				sql.append(" and a.awardTime like '%"+recommentAwardRecord.getAwardTime().trim()+"%'");
			}
			if(recommentAwardRecord.getRecommendUser() != null && 
					recommentAwardRecord.getRecommendUser().getPhone() != null &&
					!"".equals(recommentAwardRecord.getRecommendUser().getPhone().trim())){
				sql.append(" and us.phone like '%"+recommentAwardRecord.getRecommendUser().getPhone().trim()+"%'");
			}
			if(recommentAwardRecord.getBeenRecommendUser() != null && 
					recommentAwardRecord.getBeenRecommendUser().getPhone() != null &&
					!"".equals(recommentAwardRecord.getBeenRecommendUser().getPhone().trim())){
				sql.append(" and beenUser.phone like '%"+recommentAwardRecord.getBeenRecommendUser().getPhone().trim()+"%'");
			}
			if(recommentAwardRecord.getRecommendDoctor()!= null && 
					recommentAwardRecord.getRecommendDoctor().getDoctorPhone() != null &&
					!"".equals(recommentAwardRecord.getRecommendDoctor().getDoctorPhone().trim())){
				sql.append(" and doctor.doctorPhone like '%"+recommentAwardRecord.getRecommendDoctor().getDoctorPhone().trim()+"%'");
			}
			if(recommentAwardRecord.getRecommendDoctor()!= null && 
					recommentAwardRecord.getRecommendDoctor().getDoctorName() != null &&
					!"".equals(recommentAwardRecord.getRecommendDoctor().getDoctorName().trim())){
				sql.append(" and doctor.doctorName like '%"+recommentAwardRecord.getRecommendDoctor().getDoctorName().trim()+"%'");
			}
			if(recommentAwardRecord.getBeenRecommendDoctor()!= null && 
					recommentAwardRecord.getBeenRecommendDoctor().getDoctorPhone() != null &&
					!"".equals(recommentAwardRecord.getBeenRecommendDoctor().getDoctorPhone().trim())){
				sql.append(" and beenDoctor.doctorPhone like '%"+recommentAwardRecord.getBeenRecommendDoctor().getDoctorPhone().trim()+"%'");
			}
			if(recommentAwardRecord.getBeenRecommendDoctor()!= null && 
					recommentAwardRecord.getBeenRecommendDoctor().getDoctorPhone() != null &&
					!"".equals(recommentAwardRecord.getBeenRecommendDoctor().getDoctorName().trim())){
				sql.append(" and beenDoctor.doctorName like '%"+recommentAwardRecord.getBeenRecommendDoctor().getDoctorName().trim()+"%'");
			}
		}
		sql.append(" order by a.id");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		List<RecommentAwardRecord> list = session.createSQLQuery(sql.toString()).addEntity("a", RecommentAwardRecord.class).list();
		releaseSession(session); //释放session
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	@Override
	public List<DoctorAccount> getAllDoctorAccountList(
			DoctorAccount doctorAccount) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer("select p from DoctorAccount p, DoctorInfo d where 1=1");
		hql.append(" and p.doctorId=d.id");
		if(doctorAccount != null){
			if(doctorAccount.getDoctorInfo() != null && doctorAccount.getDoctorInfo().getDoctorName() != null && 
					!"".equals(doctorAccount.getDoctorInfo().getDoctorName().trim())){
				hql.append(" and d.doctorName like ?");
				params.add("%"+doctorAccount.getDoctorInfo().getDoctorName().trim()+"%");
			}
			if(doctorAccount.getType() != null && !"".equals(doctorAccount.getType())){
				hql.append(" and p.type like ?");
				params.add("%"+doctorAccount.getType().trim()+"%");
			}
			if(doctorAccount.getAccountDesc() != null && !"".equals(doctorAccount.getAccountDesc().trim())){
				hql.append(" and p.accountDesc like ?");
				params.add("%"+doctorAccount.getAccountDesc().trim()+"%");
			}
		}
		hql.append(" order by p.updateTime desc");
		List<DoctorAccount> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

}
