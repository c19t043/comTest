package com.kybaby.newbussiness.doctorring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.PostInfo;
import com.kybaby.newbussiness.doctorring.dao.PostInfoDao;

/**
 * @ClassName:PostInfoDaoImpl
 * @Description:用户发帖数据管理接口实现
 * @author Hoolee
 * @date 2015年12月13日下午1:55:20
 */
public class PostInfoDaoImpl extends HibernateDaoSupport implements PostInfoDao {

	public List<PostInfo> getSomeCategoryPostInfo(long categoryId) {
		String hql="from PostInfo where typeId= "+categoryId+" and isTop='Y' and ((isAudit='Y' and auditStatus='Y' and dataStatus='Y') or (isAudit='N' and dataStatus='Y')) order by postTime desc ";
		String newHql="from PostInfo where typeId= "+categoryId+" and isTop='N' and ((isAudit='Y' and auditStatus='Y' and dataStatus='Y') or (isAudit='N' and dataStatus='Y')) order by postTime desc ";
		List<PostInfo> topList=getHibernateTemplate().find(hql);
		List<PostInfo> folList=getHibernateTemplate().find(newHql);
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

	public PostInfo getPostInfoInstanceById(long postInfoId) {
		String hql="from PostInfo where id= "+postInfoId;
		List<PostInfo> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public void updatePostInfoInstance(PostInfo postInfoInstance) {
		getHibernateTemplate().update(postInfoInstance);
	}

	public long addNewPostInfoInstance(PostInfo postInfoInstance) {
		Object replaceInstance=getHibernateTemplate().save(postInfoInstance);
		Long instanceId=0L;
		try{
			instanceId=Long.valueOf(replaceInstance.toString());
		}catch(Exception e){
			instanceId=0L;
		}
		return instanceId;
	}

	public List<PostInfo> getAllPostInfo() {
		String hql="from PostInfo";
		List<PostInfo> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

}
