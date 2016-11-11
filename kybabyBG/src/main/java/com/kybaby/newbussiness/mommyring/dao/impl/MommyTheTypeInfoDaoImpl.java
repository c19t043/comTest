package com.kybaby.newbussiness.mommyring.dao.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.mommyring.dao.MommyTheTypeInfoDao;
import com.kybaby.newbussiness.mommyring.domain.MommyRingType;
import com.kybaby.newbussiness.mommyring.domain.MommyTheTypeInfo;

/**
 * @ClassName:MommyTheTypeInfoDaoImpl
 * @Description:医生圈分类数据管理接口实现
 * @author Hoolee
 * @date 2015年12月10日下午4:42:30
 */
public class MommyTheTypeInfoDaoImpl extends HibernateDaoSupport implements MommyTheTypeInfoDao {

	public List<Object[]> getAllRingCategory() {
		
		String hql="select id,typeName,isMark from MommyTheTypeInfo where isAvailable='Y' and isMark='N' order by typeSort ";
		List<Object[]> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()||list.get(0)==null){
			return null;
		}
		return list;
	}

	public Object[] getRemarkRingCategory() {
		String hql="select id,typeName,isMark from MommyTheTypeInfo where isAvailable='Y' and isMark='Y'";
		List<Object[]> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()||list.get(0)==null){
			return null;
		}
		return list.get(0);
	}

	public MommyTheTypeInfo getSomeCategoryInstanceById(long categoryId) {
		String hql="from MommyTheTypeInfo where id= "+categoryId;
		List<MommyTheTypeInfo> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<MommyTheTypeInfo> getMommyTheTypeInfoList(MommyTheTypeInfo theTypeInfo) {
		StringBuffer hql = new StringBuffer(" from MommyTheTypeInfo a where 1=1");
		if(theTypeInfo != null){
			//拼where条件
			if(theTypeInfo.getTypeName() != null && !"".equals(theTypeInfo.getTypeName().trim())){
				hql.append(" and a.typeName='"+theTypeInfo.getTypeName().trim()+"'");
			}
		}
		hql.append(" order by a.typeSort");
		List<MommyTheTypeInfo> list = getHibernateTemplate().find(hql.toString());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long saveOrUpdateMommyTheTypeInfo(MommyTheTypeInfo theTypeInfo) {
		Long id = null;
		if(theTypeInfo == null){
			return null;
		}else{
			if(theTypeInfo.getId() != null){
				MommyTheTypeInfo old = getHibernateTemplate().get(MommyTheTypeInfo.class, theTypeInfo.getId());
				BeanUtils.copyProperties(theTypeInfo, old, new String[]{"id"});
				getHibernateTemplate().update(old);
				id = theTypeInfo.getId();
			}else{
				id = (Long) getHibernateTemplate().save(theTypeInfo);
			}
		}
		return id;
	}

	@Override
	public List<MommyRingType> getMommyRingTypeListByMommyTheTypeInfoId(
			MommyTheTypeInfo theTypeInfo) {
		StringBuffer hql = new StringBuffer(" from MommyRingType a where 1=1");
		if(theTypeInfo != null){
			//拼where条件
			if(theTypeInfo.getId() != null){
				hql.append(" and a.typeId=").append(theTypeInfo.getId());
			}
		}
		List<MommyRingType> list = getHibernateTemplate().find(hql.toString());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long saveOrUpdateMommyRingType(MommyRingType userRingType) {
		Long id = null;
		if(userRingType == null){
			return null;
		}else{
			if(userRingType.getId() != null){
				MommyRingType old = getHibernateTemplate().get(MommyRingType.class, userRingType.getId());
				BeanUtils.copyProperties(userRingType, old,
						new String[]{"id","subscribeNum","browseNum","modifyPersonId","typeAdmin","topImg","isSubscribe"});
				old.setModifyTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				getHibernateTemplate().update(old);
				id = userRingType.getId();
			}else{
				userRingType.setCreateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				id = (Long) getHibernateTemplate().save(userRingType);
			}
		}
		return id;
	}

}
