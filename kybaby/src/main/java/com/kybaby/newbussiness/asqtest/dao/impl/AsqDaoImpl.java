package com.kybaby.newbussiness.asqtest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.asqtest.dao.AsqDao;
import com.kybaby.newbussiness.asqtest.domain.AsqAnswnerChecked;
import com.kybaby.newbussiness.asqtest.domain.AsqBeenOptions;
import com.kybaby.newbussiness.asqtest.domain.AsqParentChildActivity;
import com.kybaby.newbussiness.asqtest.domain.AsqQuestionRecord;
import com.kybaby.newbussiness.asqtest.domain.AsqQuestions;
import com.kybaby.newbussiness.asqtest.domain.AsqResultScoreEx;
import com.kybaby.newbussiness.asqtest.domain.AsqResultScoreExUser;
import com.kybaby.newbussiness.asqtest.domain.AsqTaoti;
import com.kybaby.newbussiness.asqtest.domain.AsqTaotiAge;
import com.kybaby.newbussiness.asqtest.domain.AsqTestUserInfo;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoods;
@SuppressWarnings("all")
public class AsqDaoImpl extends HibernateDaoSupport implements AsqDao{

	@Override
	public List<AsqTaotiAge> getAsqTaotiAgeList(Integer monthAge,AsqTaoti parentTaoti) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from AsqTaotiAge p where 1=1  and p.taoti.isdelete=0");
		
		if(monthAge != null){
			params.add(monthAge);
			params.add(monthAge);
			hql.append(" and p.applyMinMonthAge <= ? and p.applyMaxMonthAge >= ?");
		}
		if(parentTaoti != null){
			if(parentTaoti.getId() != null){
				params.add(parentTaoti.getId());
				hql.append(" and p.taoti.id=?");
			}
			if(StringUtils.isNotEmpty(parentTaoti.getTitalName())){
				params.add(parentTaoti.getTitalName());
				hql.append(" and p.taoti.titalName=?");
			}
			
		}
		List<AsqTaotiAge> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<AsqTaoti> getChildAsqTaotiList(Long parentTaotiId) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from AsqTaoti p where 1=1 and isdelete=0 ");
		
		if(parentTaotiId != null){
			params.add(parentTaotiId);
			hql.append(" and p.parent.id = ?");
		}else{
			hql.append(" and p.parent.id is null");
		}
		hql.append(" order by sort");
		List<AsqTaoti> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long saveOrUpdateAsqQuestionRecord(
			AsqQuestionRecord asqQuestionRecord) {
		Long id = null;
		if(asqQuestionRecord == null) return id;
		if(asqQuestionRecord.getId() == null){
			id = (Long) this.getHibernateTemplate().save(asqQuestionRecord);
		}else{
			id = asqQuestionRecord.getId();
			this.getHibernateTemplate().update(asqQuestionRecord);
		}
		return id;
	}

	@Override
	public Long saveOrUpdateAsqResultScoreEx(AsqResultScoreEx asqResultScoreEx) {
		Long id = null;
		if(asqResultScoreEx == null) return id;
		if(asqResultScoreEx.getId() == null){
			id = (Long) this.getHibernateTemplate().save(asqResultScoreEx);
		}else{
			id = asqResultScoreEx.getId();
			this.getHibernateTemplate().update(asqResultScoreEx);
		}
		return id;
	}

	@Override
	public AsqBeenOptions getAsqBeenOptionsById(Long id) {
		return this.getHibernateTemplate().get(AsqBeenOptions.class, id);
	}

	@Override
	public AsqQuestions getAsqQuestionsById(Long id) {
		return this.getHibernateTemplate().get(AsqQuestions.class, id);
	}

	@Override
	public List<AsqResultScoreEx> getAsqResultScoreExList(
			AsqResultScoreEx asqResultScoreEx,Long b2cGoodsOrderId,Long fdServicePackageId) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from AsqResultScoreEx p where 1=1 ");
		
		if(asqResultScoreEx != null){
			if(asqResultScoreEx.getUserInfo() != null){
				if(asqResultScoreEx.getUserInfo().getId() != null){
					params.add(asqResultScoreEx.getUserInfo().getId());
					hql.append(" and p.userInfo.id = ?");
				}
			}
			if(asqResultScoreEx.getAsqTestUserInfo() != null){
				if(asqResultScoreEx.getAsqTestUserInfo().getId() != null){
					params.add(asqResultScoreEx.getAsqTestUserInfo().getId());
					hql.append(" and p.asqTestUserInfo.id = ?");
				}
			}
			if(asqResultScoreEx.getAsqTaotiAge() != null){
				if(asqResultScoreEx.getAsqTaotiAge().getId() != null){
					params.add(asqResultScoreEx.getAsqTaotiAge().getId());
					hql.append(" and p.asqTaotiAge.id = ?");
				}
			}
			
		}
		if(b2cGoodsOrderId != null){
			params.add(b2cGoodsOrderId);
			hql.append(" and p.asqTestUserInfo.b2cGoodsOrderId = ?");
		}
		if(fdServicePackageId != null){
			params.add(fdServicePackageId);
			hql.append(" and p.asqTestUserInfo.fdPackageId = ?");
		}
		hql.append(" order by modifyTime desc");
		List<AsqResultScoreEx> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public AsqResultScoreEx getAsqResultScoreExById(Long id) {
		return this.getHibernateTemplate().get(AsqResultScoreEx.class, id);
	}

	@Override
	public List<AsqQuestionRecord> getAsqQuestionRecordList(
			AsqQuestionRecord asqQuestionRecord) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from AsqQuestionRecord p where 1=1 ");
		
		if(asqQuestionRecord != null){
			if(asqQuestionRecord.getAsqTestUserInfo() != null){
				if(asqQuestionRecord.getAsqTestUserInfo().getId() != null){
					params.add(asqQuestionRecord.getAsqTestUserInfo().getId());
					hql.append(" and p.asqTestUserInfo.id = ?");
				}
				if(asqQuestionRecord.getAsqQuestionsId() != null){
					params.add(asqQuestionRecord.getAsqQuestionsId());
					hql.append(" and p.asqQuestionsId = ?");
				}
			}
			
		}
		hql.append(" order by modifyTime ");
		List<AsqQuestionRecord> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<AsqParentChildActivity> getAsqParentChildActivityList(
			AsqParentChildActivity asqParentChildActivity,AsqTestUserInfo asqTestUserInfo) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from AsqParentChildActivity p where 1=1");
		
		if(asqTestUserInfo != null){
			if(asqTestUserInfo.getCurrentMonthAge() != null){
				params.add(asqTestUserInfo.getCurrentMonthAge());
				params.add(asqTestUserInfo.getCurrentMonthAge());
				hql.append(" and p.applyMinMonthAge <= ? and p.applyMaxMonthAge >= ?");
			}
		}
		if(asqParentChildActivity != null){
			if(asqParentChildActivity.getAsqTaotiId() != null){
				params.add(asqParentChildActivity.getAsqTaotiId());
				hql.append(" and p.asqTaotiId =?");
			}
		}
		List<AsqParentChildActivity> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long getTestCountByUser(UserInfo userInfo ,Long b2cOrderId) {
		StringBuffer sql = new StringBuffer(" SELECT COUNT(DISTINCT a.asq_test_user_info_id) ");
		sql.append(" FROM asq_result_score_ex a,asq_test_user_info b") ;
		sql.append(" WHERE 1=1 AND a.asq_test_user_info_id=b.id ") ;
		if(userInfo != null){
			if(userInfo.getId() != null ){
				sql.append(" and a.user_id=:user_id");
			}
		}
		sql.append(" and b.b2c_goods_order_id=:b2cOrderId");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		List list =  session.createSQLQuery(sql.toString()).setLong("user_id", userInfo.getId())
				.setLong("b2cOrderId", b2cOrderId)
				.list();
		session.close();
		if(!list.isEmpty()){
			return Long.valueOf(list.get(0).toString());
		}
		return 0L;
	}

	@Override
	public List<AsqAnswnerChecked> getAsqAnswnerCheckedList(Long asqTaotiAgeId) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from AsqAnswnerChecked p where 1=1  and p.isEnable='Y'");
		if(asqTaotiAgeId != null){
			hql.append(" and p.asqTaotiAgeId=?");
			params.add(asqTaotiAgeId);
		}
		List<AsqAnswnerChecked> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public AsqTaotiAge getAsqTaotiAgeId(Long id) {
		return this.getHibernateTemplate().get(AsqTaotiAge.class, id);
	}

	@Override
	public Long saveOrUpdateAsqResultScoreExUser(
			AsqResultScoreExUser asqResultScoreExUser) {
		Long id = null;
		if(asqResultScoreExUser == null) return id;
		if(asqResultScoreExUser.getId() == null){
			id = (Long) this.getHibernateTemplate().save(asqResultScoreExUser);
		}else{
			id = asqResultScoreExUser.getId();
			this.getHibernateTemplate().update(asqResultScoreExUser);
		}
		return id;
	}
	

}
