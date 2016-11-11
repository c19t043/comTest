package com.kybaby.newbussiness.doctorring.dao.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorRingType;
import com.kybaby.domain.TheTypeInfo;
import com.kybaby.newbussiness.doctorring.dao.TheTypeInfoDao;
import com.kybaby.newbussiness.doctorring.util.DateManage;

/**
 * @ClassName:TheTypeInfoDaoImpl
 * @Description:医生圈分类数据管理接口实现
 * @author Hoolee
 * @date 2015年12月10日下午4:42:30
 */
public class TheTypeInfoDaoImpl extends HibernateDaoSupport implements TheTypeInfoDao {

	public List<Object[]> getAllRingCategory() {
		
		String hql="select id,typeName,isMark from TheTypeInfo where isAvailable='Y' and isMark='N' order by typeSort ";
		List<Object[]> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()||list.get(0)==null){
			return null;
		}
		return list;
	}

	public Object[] getRemarkRingCategory() {
		String hql="select id,typeName,isMark from TheTypeInfo where isAvailable='Y' and isMark='Y'";
		List<Object[]> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()||list.get(0)==null){
			return null;
		}
		return list.get(0);
	}

	public TheTypeInfo getSomeCategoryInstanceById(long categoryId) {
		String hql="from TheTypeInfo where id= "+categoryId;
		List<TheTypeInfo> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<TheTypeInfo> getTheTypeInfoList(TheTypeInfo theTypeInfo) {
		StringBuffer hql = new StringBuffer(" from TheTypeInfo a where 1=1");
		if(theTypeInfo != null){
			//拼where条件
			if(theTypeInfo.getTypeName() != null && !"".equals(theTypeInfo.getTypeName().trim())){
				hql.append(" and a.typeName='"+theTypeInfo.getTypeName().trim()+"'");
			}
		}
		hql.append(" order by a.typeSort");
		List<TheTypeInfo> list = getHibernateTemplate().find(hql.toString());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long saveOrUpdateTheTypeInfo(TheTypeInfo theTypeInfo) {
		Long id = null;
		if(theTypeInfo == null){
			return null;
		}else{
			if(theTypeInfo.getId() != null){
				TheTypeInfo old = getHibernateTemplate().get(TheTypeInfo.class, theTypeInfo.getId());
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
	public List<DoctorRingType> getDoctorRingTypeListByTheTypeInfoId(
			TheTypeInfo theTypeInfo) {
		StringBuffer hql = new StringBuffer(" from DoctorRingType a where 1=1");
		if(theTypeInfo != null){
			//拼where条件
			if(theTypeInfo.getId() != null){
				hql.append(" and a.typeId=").append(theTypeInfo.getId());
			}
		}
		List<DoctorRingType> list = getHibernateTemplate().find(hql.toString());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long saveOrUpdateDoctorRingType(DoctorRingType doctorRingType) {
		Long id = null;
		if(doctorRingType == null){
			return null;
		}else{
			if(doctorRingType.getId() != null){
				DoctorRingType old = getHibernateTemplate().get(DoctorRingType.class, doctorRingType.getId());
				BeanUtils.copyProperties(doctorRingType, old, 
						new String[]{"id","subscribeNum","browseNum","modifyPersonId","typeAdmin","topImg","isSubscribe"});
				old.setModifyTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				getHibernateTemplate().update(old);
				id = doctorRingType.getId();
			}else{
				doctorRingType.setCreateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				id = (Long) getHibernateTemplate().save(doctorRingType);
			}
		}
		return id;
	}

}
