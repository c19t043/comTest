package com.kybaby.newbussiness.messagecenter.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.messagecenter.dao.MessageCenterDao;
import com.kybaby.newbussiness.messagecenter.domain.MessageCenter;

/**
 * 消息中心Dao实现类
 * @author xiongchao
 */
public class MessageCenterDaoImpl  extends HibernateDaoSupport implements MessageCenterDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int messageNoReadCountByUser(Long userId) {
		if (userId == null) {
			return 0;
		}
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer("select count(*) from MessageCenter m where m.isDel='N' and m.isRead='N' ");
		hql.append(" and m.userInfo.id = ?");
		params.add(userId);
		Integer count = (Integer)this.getHibernateTemplate().find(hql.toString(), params.toArray()).listIterator().next();
		return count.intValue();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<MessageCenter> getMessageCenterList(MessageCenter messageCenter) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from MessageCenter m where 1=1");
		if(messageCenter != null){
			if(messageCenter.getUserInfo() != null){
				if(messageCenter.getUserInfo().getPhone() != null && !"".equals(messageCenter.getUserInfo().getPhone().trim())){
					hql.append(" and m.userInfo.phone like ?");
					params.add("%"+messageCenter.getUserInfo().getPhone().trim()+"%");
				}
				if(messageCenter.getUserInfo().getId() != null){
					hql.append(" and m.userInfo.id = ?");
					params.add(messageCenter.getUserInfo().getId());
				}
			}
		}
		
		List<MessageCenter> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	@Override
	public Long saveOrUpdateMessage(MessageCenter messageCenter) {
		if(messageCenter == null) return null;
		Long id = null;
		if(messageCenter.getId() == null){
			id = (Long) this.getHibernateTemplate().save(messageCenter);
		}else{
			id = messageCenter.getId() ;
			this.getHibernateTemplate().update(messageCenter);
		}
		return id;
	}

	@Override
	public List<ArchivesInfo> getArchivesInfoList(ArchivesInfo archivesInfo) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from ArchivesInfo m where 1=1 and isRelation='Y'");
		if(archivesInfo != null){
			if(archivesInfo.getHospitalBasicInfo() != null && 
					archivesInfo.getHospitalBasicInfo().getHospitalLname() != null &&
					!"".equals(archivesInfo.getHospitalBasicInfo().getHospitalLname().trim())
					){
				hql.append(" and m.hospitalBasicInfo.hospitalLname like ?");
				params.add("%"+archivesInfo.getHospitalBasicInfo().getHospitalLname().trim()+"%");
			}
			if(archivesInfo.getMinMonthAge() != null && !"".equals(archivesInfo.getMinMonthAge().trim())){
				hql.append(" and PERIOD_DIFF(date_format(now(),'%Y%m'),date_format(m.childrenBirthday,'%Y%m')) >= ? ");
				params.add(archivesInfo.getMinMonthAge().trim());
			}
			if(archivesInfo.getMaxMonthAge() != null && !"".equals(archivesInfo.getMaxMonthAge().trim())){
				hql.append(" and PERIOD_DIFF(date_format(now(),'%Y%m'),date_format(m.childrenBirthday,'%Y%m')) <= ? ");
				params.add(archivesInfo.getMaxMonthAge().trim());
			}
			if(archivesInfo.getUserType() != null){
				hql.append(" and m.userType = ? ");
				params.add(archivesInfo.getUserType().trim());
			}
		}
		List<ArchivesInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

}
