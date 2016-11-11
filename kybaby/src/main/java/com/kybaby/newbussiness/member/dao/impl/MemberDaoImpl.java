package com.kybaby.newbussiness.member.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.Product;
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
	public MemberType getMemberTypeById(Long id) {
		return this.getHibernateTemplate().get(MemberType.class, id);
	}

	@Override
	public List<MemberManage> getMemberManageList(UserInfo userInfo) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from MemberManage p where 1=1");
		hql.append(" and p.effectEndDate >= CURRENT_DATE()");
		if(userInfo != null){
			if(userInfo.getId() != null){
				hql.append(" and p.userInfo.id=?");
				params.add(userInfo.getId());
			}
		}
		List<MemberManage> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
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
	public MemberBuyInfo getMemberBuyInfoByOrderNum(String orderNum) {
		StringBuffer hql = new StringBuffer(" from MemberBuyInfo p where 1=1 and orderNum=?");
		List<MemberBuyInfo> list = this.getHibernateTemplate().find(hql.toString(), orderNum);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Product getProductByMemberType(MemberType memberType) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from Product p where 1=1 and productStatus='Y'");
		if(memberType != null){
			if(memberType.getId() != null){
				hql.append(" and p.memberTypeId=?");
				params.add(memberType.getId());
			}
		}
		List<Product> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}
