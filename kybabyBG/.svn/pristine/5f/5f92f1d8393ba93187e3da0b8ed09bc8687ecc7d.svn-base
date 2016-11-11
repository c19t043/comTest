package com.kybaby.newbussiness.member.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.member.dao.MemberDao;
import com.kybaby.newbussiness.member.domain.MemberBuyInfo;
import com.kybaby.newbussiness.member.domain.MemberManage;
import com.kybaby.newbussiness.member.domain.MemberRule;
import com.kybaby.newbussiness.member.domain.MemberType;
import com.kybaby.newbussiness.member.domain.MemberTypeRule;

public class MemberDaoImpl extends HibernateDaoSupport implements MemberDao{

	@Override
	public Long saveOrUpdateMemberBuyInfo(MemberBuyInfo memberBuyInfo) {
		Long id = null;
		if(memberBuyInfo.getId() == null){
			id = (Long) this.getHibernateTemplate().save(memberBuyInfo);
		}else{
			id = memberBuyInfo.getId();
			this.getHibernateTemplate().update(memberBuyInfo);
		}
		return id;
	}

	@Override
	public Long saveOrUpdateMemberManage(MemberManage memberManage) {
		Long id = null;
		if(memberManage.getId() == null){
			id = (Long) this.getHibernateTemplate().save(memberManage);
		}else{
			id = memberManage.getId();
			this.getHibernateTemplate().update(memberManage);
		}
		return id;
	}

	@Override
	public MemberBuyInfo getMemberBuyInfoById(Long id) {
		return this.getHibernateTemplate().get(MemberBuyInfo.class, id);
	}

	@Override
	public MemberManage getMemberManageBySomething(Long keyId,
			UserInfo userInfo, MemberType memberType) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from MemberManage p where 1=1");
		if(keyId != null){
			hql.append(" and p.id=?");
			params.add(keyId);
		}
		if(userInfo != null){
			if(userInfo.getId() != null){
				hql.append(" and p.userInfo.id=?");
				params.add(userInfo.getId());
			}
		}
		if(memberType != null){
			if(userInfo.getId() != null){
				hql.append(" and p.memberType.id=?");
				params.add(memberType.getId());
			}
		}
		List<MemberManage> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<MemberType> getMemberTypeList(MemberType memberType) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from MemberType p where 1=1");
		List<MemberType> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<MemberManage> getMemberManageList(MemberManage memberManage) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from MemberManage p where 1=1");
		List<MemberManage> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(memberManage != null){
			if(memberManage.getMemberCode() != null && !"".equals(memberManage.getMemberCode().trim())){
				hql.append(" and p.memberCode like ?");
				params.add("%"+memberManage.getMemberCode().trim()+"%");
			}
			if(memberManage.getUserPhone() != null && !"".equals(memberManage.getUserPhone().trim())){
				hql.append(" and p.userPhone like ?");
				params.add("%"+memberManage.getUserPhone().trim()+"%");
			}
		}
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<MemberRule> getMemberRuleList(MemberRule memberRule) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from MemberRule p where 1=1");
		if(memberRule != null){
			if(memberRule.getRuleName() != null && !"".equals(memberRule.getRuleName().trim())){
				hql.append(" and p.ruleName like ?");
				params.add("%"+memberRule.getRuleName().trim()+"%");
			}
		}
		List<MemberRule> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long saveOrUpdateMemberRule(MemberRule memberRule) {
		Long id = null;
		if(memberRule.getId() == null){
			id = (Long) this.getHibernateTemplate().save(memberRule);
		}else{
			id = memberRule.getId();
			this.getHibernateTemplate().update(memberRule);
		}
		return id;
	}

	@Override
	public Long saveOrUpdateMemberType(MemberType memberType) {
		Long id = null;
		if(memberType.getId() == null){
			id = (Long) this.getHibernateTemplate().save(memberType);
		}else{
			id = memberType.getId();
			this.getHibernateTemplate().update(memberType);
		}
		return id;
	}

	@Override
	public List<MemberTypeRule> getMemberTypeRuleList(MemberType memberType,
			MemberRule memberRule) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from MemberTypeRule p where 1=1");
		if(memberType != null){
			if(memberType.getId() != null){
				hql.append(" and p.memberType.id=?");
				params.add(memberType.getId());
			}
		}
		if(memberRule != null){
			if(memberRule.getId() != null){
				hql.append(" and p.memberRule.id=?");
				params.add(memberRule.getId());
			}
		}
		List<MemberTypeRule> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public void saveOrUpdateMemberTypeRule(MemberTypeRule memberTypeRule) {
		Long id;
		if(memberTypeRule.getId() == null){
			id = (Long) this.getHibernateTemplate().save(memberTypeRule);
		}else{
			id = memberTypeRule.getId();
			this.getHibernateTemplate().update(memberTypeRule);
		}
	}

	@Override
	public void delMemberTypeRule(MemberType memberType) {
		StringBuffer sql = new StringBuffer("delete from member_type_rule where 1=1")
		.append(" and member_type_id=").append(memberType.getId());
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query queryupdate = session.createSQLQuery(sql.toString());
		queryupdate.executeUpdate();
		session.close();
	}

}
