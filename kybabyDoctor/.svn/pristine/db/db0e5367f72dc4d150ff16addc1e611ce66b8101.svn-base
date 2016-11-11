package com.kybaby.newbussiness.medicalorgandbusiness.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.ArchivesInfoDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganOperator;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.PageBean;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;

public class ArchivesInfoDaoImpl extends HibernateDaoSupport implements ArchivesInfoDao{

	@Override
	@SuppressWarnings("unchecked")
	public List<ArchivesInfo> getArchivesInfoList(ArchivesInfo archivesInfo,PageBean pageBean,OrganOperator organOperator) {
		StringBuffer hql = new StringBuffer("from ArchivesInfo p where 1=1 ");
		StringBuffer hqlCount = new StringBuffer("select count(*) as allRows from ArchivesInfo p where 1=1 ");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		if(archivesInfo != null){
			if(archivesInfo.getArchivesMobile() != null && !"".equals(archivesInfo.getArchivesMobile().trim())){
				hql.append(" and p.archivesMobile like :archivesMobile");
				
				hqlCount.append(" and p.archivesMobile like :archivesMobile");
			}
			if(archivesInfo.getChildrenBirthday() != null && !"".equals(archivesInfo.getChildrenBirthday().trim())){
				hql.append(" and p.childrenBirthday like :childrenBirthday");
				
				hqlCount.append(" and p.childrenBirthday like :childrenBirthday");
			}
			if(archivesInfo.getChildrenName() != null && !"".equals(archivesInfo.getChildrenName().trim())){
				hql.append(" and p.childrenName like :childrenName");
				
				hqlCount.append(" and p.childrenName like :childrenName");
			}
			if(archivesInfo.getUserType() != null && !"".equals(archivesInfo.getUserType().trim())){
				hql.append(" and p.userType = :userType");
				
				hqlCount.append(" and p.userType = :userType");
			}
			if(archivesInfo.getIsRelation() != null && !"".equals(archivesInfo.getIsRelation().trim())){
				hql.append(" and p.isRelation = :isRelation");
				
				hqlCount.append(" and p.isRelation = :isRelation");
			}
			if(archivesInfo.getHospitalBasicInfo() != null && !"".equals(archivesInfo.getHospitalBasicInfo().getId().toString())){
				hql.append(" and p.hospitalBasicInfo.id=:hospitalId");
				
				hqlCount.append(" and p.hospitalBasicInfo.id=:hospitalId");
			}
		}
		if(organOperator != null){
			if(organOperator.getHospitalBasicInfo() != null && organOperator.getHospitalBasicInfo().getId() != null){
				hql.append(" and p.organOperator.hospitalBasicInfo.id=:hospitalId");
				
				hqlCount.append(" and p.organOperator.hospitalBasicInfo.id=:hospitalId");
			}
		}
		hql.append(" order by p.modifyTime desc");
		
		Query query = session.createQuery(hql.toString());
		Query queryCount = session.createQuery(hqlCount.toString());
		if(archivesInfo != null){
			if(archivesInfo.getArchivesMobile() != null && !"".equals(archivesInfo.getArchivesMobile().trim())){
				query.setString("archivesMobile", "%"+archivesInfo.getArchivesMobile().trim()+"%");
				queryCount.setString("archivesMobile", "%"+archivesInfo.getArchivesMobile().trim()+"%");
			}
			if(archivesInfo.getChildrenBirthday() != null && !"".equals(archivesInfo.getChildrenBirthday().trim())){
				query.setString("childrenBirthday", "%"+archivesInfo.getChildrenBirthday().trim()+"%");
				queryCount.setString("childrenBirthday", "%"+archivesInfo.getChildrenBirthday().trim()+"%");
			}
			if(archivesInfo.getChildrenName() != null && !"".equals(archivesInfo.getChildrenName().trim())){
				query.setString("childrenName", "%"+archivesInfo.getChildrenName().trim()+"%");
				queryCount.setString("childrenName", "%"+archivesInfo.getChildrenName().trim()+"%");
			}
			if(archivesInfo.getUserType() != null && !"".equals(archivesInfo.getUserType().trim())){
				query.setString("userType", archivesInfo.getUserType().trim());
				queryCount.setString("userType", archivesInfo.getUserType().trim());
			}
			if(archivesInfo.getIsRelation() != null && !"".equals(archivesInfo.getIsRelation().trim())){
				query.setString("isRelation", archivesInfo.getIsRelation().trim());
				queryCount.setString("isRelation", archivesInfo.getIsRelation().trim());
			}
			if(archivesInfo.getHospitalBasicInfo() != null && !"".equals(archivesInfo.getHospitalBasicInfo().getId().toString())){
				query.setLong("hospitalId", archivesInfo.getHospitalBasicInfo().getId());
				queryCount.setLong("hospitalId", archivesInfo.getHospitalBasicInfo().getId());
			}
		}
		if(organOperator != null){
			if(organOperator.getHospitalBasicInfo() != null && organOperator.getHospitalBasicInfo().getId() != null){
				query.setLong("hospitalId", organOperator.getHospitalBasicInfo().getId());
				queryCount.setLong("hospitalId", organOperator.getHospitalBasicInfo().getId());
			}
		}
		//得到总条数
		if(pageBean != null){
			List listCount = queryCount.list();
			Iterator iter = listCount.iterator();  
			if(iter.hasNext()){ 
				Integer rows = Integer.valueOf(iter.next().toString());
				pageBean.setRowsCount(rows.intValue());
			}
			query.setFirstResult((pageBean.getCurPage()-1)*pageBean.getPageSize());
			query.setMaxResults(pageBean.getPageSize());
		}
		List<ArchivesInfo> list =  query.list();
		session.close();
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long saveOrUpateArchivesInfo(ArchivesInfo archivesInfo) {
		if(archivesInfo == null) return null;
		Long id = null;
		if(archivesInfo.getId() == null){
			archivesInfo.setCreatTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			id = (Long) this.getHibernateTemplate().save(archivesInfo);
		}else{
			id = archivesInfo.getId();
			archivesInfo.setModifyTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			this.getHibernateTemplate().update(archivesInfo);
		}
		return id;
	}

	@Override
	public ArchivesInfo getArchivesInfoById(Long id) {
		return this.getHibernateTemplate().get(ArchivesInfo.class, id);
	}
	@Override
	public List<UserType> getUserTypeList(UserType userType) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from UserType p where 1=1 ");
		if(userType != null){
			
		}
		hql.append(" order by p.typeName ");
		List<UserType> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	
}
