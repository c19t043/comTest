package com.kybaby.newbussiness.familydoctor.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.familydoctor.dao.FdServiceItemsDao;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceItems;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceMember;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceOrder;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceTeams;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceTimes;
import com.kybaby.newbussiness.familydoctor.domain.FdUserBuyRecord;
@SuppressWarnings("unchecked")
public class FdServiceItemsDaoImpl extends HibernateDaoSupport implements
		FdServiceItemsDao {

	@Override
	public FdServiceItems getFdServiceItemsById(Long id) {
		return this.getHibernateTemplate().get(FdServiceItems.class, id);
	}

	@Override
	public List<FdServiceItems> getAllFdServiceItems(FdServiceItems fdServiceItems) {
		List<FdServiceItems> fdServiceItemsList = new ArrayList<>();
		StringBuffer hql = new StringBuffer(" from FdServiceItems f where 1=1 and f.isEnable='Y'");
		hql.append(" order by f.sort");
		fdServiceItemsList = this.getHibernateTemplate().find(hql.toString(),fdServiceItemsList.toArray());
		return fdServiceItemsList;
	}

	@Override
	public List<FdUserBuyRecord> getFdUserBuyRecordList(UserInfo userInfo,FdServicePackage fdServicePackage) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from FdUserBuyRecord p where 1=1");
		if(userInfo != null){
			if(userInfo.getId() != null){
				hql.append(" and p.userInfo.id = ?");
				params.add(userInfo.getId());
			}
			hql.append(" and p.serviceEndTime >= CURDATE()");
		}
		if(fdServicePackage != null){
			if(fdServicePackage.getId() != null){
				hql.append(" and p.servicePackage.id = ?");
				params.add(fdServicePackage.getId());
			}
		}
		List<FdUserBuyRecord> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public List<FdServicePackage> getFdServicePackageList(Long hospitalId) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from FdServicePackage p where 1=1");
		if(hospitalId != null){
			if(hospitalId != null){
				hql.append(" and p.hospitalBasicInfo.id = ?");
				params.add(hospitalId);
			}
		}
		hql.append(" and p.isEnable = 'Y'");
		List<FdServicePackage> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public List<FdServiceTeams> getFdServiceTeamsList(
			FdServicePackage fdServicePackage) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from FdServiceTeams p where 1=1");
		if(fdServicePackage != null){
			if(fdServicePackage.getId() != null){
				hql.append(" and p.fdServicePackage.id = ?");
				params.add(fdServicePackage.getId());
			}
		}
		List<FdServiceTeams> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public List<FdServiceTimes> getFdServiceTimesList(
			FdServicePackage fdServicePackage) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from FdServiceTimes p where 1=1");
		if(fdServicePackage != null){
			if(fdServicePackage.getId() != null){
				hql.append(" and p.fdServicePackage.id = ?");
				params.add(fdServicePackage.getId());
			}
		}
		hql.append("order by p.servicePrice +0,p.effectiveTime +0");
		List<FdServiceTimes> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public Long saveOrUpdateFdUserBuyRecord(FdUserBuyRecord fdUserBuyRecord) {
		if(fdUserBuyRecord == null) return null;
		Long id = null;
		if(fdUserBuyRecord.getId() == null){
			id = (Long) this.getHibernateTemplate().save(fdUserBuyRecord);
		}else{
			id = fdUserBuyRecord.getId();
			this.getHibernateTemplate().update(fdUserBuyRecord);
		}
		return id;
	}

	@Override
	public List<FdServiceMember> getFdServiceMemberList(
			FdServiceTeams fdServiceTeams,HospitalBasicInfo hospitalBasicInfo) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from FdServiceMember p where 1=1");
		if(fdServiceTeams != null){
			if(fdServiceTeams.getId() != null){
				hql.append(" and p.fdServiceTeams.id = ?");
				params.add(fdServiceTeams.getId());
			}
		}
		if(hospitalBasicInfo != null){
			if(hospitalBasicInfo.getId() != null){
				hql.append(" and p.fdServiceTeams.fdServicePackage.hospitalBasicInfo.id = ?");
				params.add(hospitalBasicInfo.getId());
			}
		}
		List<FdServiceMember> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public Long saveOrUpdateFdServiceOrder(FdServiceOrder fdServiceOrder) {
		if(fdServiceOrder == null) return null;
		if(fdServiceOrder.getFdServiceTeams() != null && fdServiceOrder.getFdServiceTeams().getId() == null){
			fdServiceOrder.setFdServiceTeams(null);
		}
		Long id = null;
		if(fdServiceOrder.getId() == null){
			id = (Long) this.getHibernateTemplate().save(fdServiceOrder);
		}else{
			id = fdServiceOrder.getId();
			this.getHibernateTemplate().update(fdServiceOrder);
		}
		return id;
	}

	@Override
	public FdServiceOrder getFdServiceOrderById(Long id) {
		return this.getHibernateTemplate().get(FdServiceOrder.class, id);
	}

	@Override
	public List<FdServiceOrder> getFdServiceOrderList(
			FdServicePackage fdServicePackage, FdServiceTeams fdServiceTeams,
			FdServiceTimes fdServiceTimes,UserInfo userInfo,FdServiceOrder fdServiceOrder) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from FdServiceOrder p where 1=1");
		if(fdServicePackage != null){
			if(fdServicePackage.getId() != null){
				hql.append(" and p.fdServicePackage.id = ?");
				params.add(fdServicePackage.getId());
			}
		}
		if(fdServiceTimes != null){
			if(fdServiceTimes.getId() != null){
				hql.append(" and p.fdServiceTimes.id = ?");
				params.add(fdServiceTimes.getId());
			}
		}
		if(fdServiceTeams != null){
			if(fdServiceTeams.getId() != null){
				hql.append(" and p.fdServiceTeams.id = ?");
				params.add(fdServiceTeams.getId());
			}
		}
		if(userInfo != null){
			if(userInfo.getId() != null){
				hql.append(" and p.userInfo.id = ?");
				params.add(userInfo.getId());
			}
		}
		if(fdServiceOrder != null){
			if(StringUtils.isNotEmpty(fdServiceOrder.getOrderStatus())){
				hql.append(" and p.orderStatus = ?");
				params.add(fdServiceOrder.getOrderStatus());
			}
		}
		List<FdServiceOrder> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty())
			return list;
		return null;
	}

	@Override
	public FdServiceTeams getFdServiceTeamsById(Long id) {
		return this.getHibernateTemplate().get(FdServiceTeams.class, id);
	}

	@Override
	public FdServicePackage getFdServicePackageById(Long id) {
		return this.getHibernateTemplate().get(FdServicePackage.class, id);
	}

}
