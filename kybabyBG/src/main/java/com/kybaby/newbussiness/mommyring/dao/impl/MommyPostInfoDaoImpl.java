package com.kybaby.newbussiness.mommyring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.mommyring.dao.MommyPostInfoDao;
import com.kybaby.newbussiness.mommyring.domain.MommyPostInfo;

/**
 * @ClassName:MommyPostInfoDaoImpl
 * @Description:用户发帖数据管理接口实现
 * @author Hoolee
 * @date 2015年12月13日下午1:55:20
 */
public class MommyPostInfoDaoImpl extends HibernateDaoSupport implements MommyPostInfoDao {

	public List<MommyPostInfo> getSomeCategoryMommyPostInfo(long categoryId) {
		String hql="from MommyPostInfo where typeId= "+categoryId+" and isTop='Y' and ((isAudit='Y' and auditStatus='Y' and dataStatus='Y') or (isAudit='N' and dataStatus='Y')) order by postTime desc ";
		String newHql="from MommyPostInfo where typeId= "+categoryId+" and isTop='N' and ((isAudit='Y' and auditStatus='Y' and dataStatus='Y') or (isAudit='N' and dataStatus='Y')) order by postTime desc ";
		List<MommyPostInfo> topList=getHibernateTemplate().find(hql);
		List<MommyPostInfo> folList=getHibernateTemplate().find(newHql);
		if(topList.isEmpty()&&folList.isEmpty()){
			return null;
		}else if(topList.isEmpty()&&!(folList.isEmpty())){
			return folList;
		}else if(!(topList.isEmpty())&&folList.isEmpty()){
			return topList;
		}else{
			topList.addAll(folList);
			return topList;
		}
	}

	public MommyPostInfo getMommyPostInfoInstanceById(long mommyPostInfoId) {
		String hql="from MommyPostInfo where id= "+mommyPostInfoId;
		List<MommyPostInfo> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public void updateMommyPostInfoInstance(MommyPostInfo mommyPostInfoInstance) {
		getHibernateTemplate().update(mommyPostInfoInstance);
	}

	public long addNewMommyPostInfoInstance(MommyPostInfo mommyPostInfoInstance) {
		Object replaceInstance=getHibernateTemplate().save(mommyPostInfoInstance);
		Long instanceId=0L;
		try{
			instanceId=Long.valueOf(replaceInstance.toString());
		}catch(Exception e){
			instanceId=0L;
		}
		return instanceId;
	}

	public List<MommyPostInfo> getAllMommyPostInfo() {
		String hql="from MommyPostInfo";
		List<MommyPostInfo> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

}
