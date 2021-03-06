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

	@Override
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

	@Override
	public PostInfo getPostInfoInstanceById(long postInfoId) {
		String hql="from PostInfo where id= "+postInfoId;
		List<PostInfo> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public void updatePostInfoInstance(PostInfo postInfoInstance) {
		getHibernateTemplate().update(postInfoInstance);
	}

	@Override
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

	@Override
	public String getAdminNameById(long adminId) {
		String hql="select contactName from Admin where id="+adminId;
		List<String> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<PostInfo> getMySendPostInfoList(Long userId) {
		String hql="from PostInfo where createPerson= "+userId+" and isTop='Y' and ((isAudit='Y' and auditStatus='Y' and dataStatus='Y') or (isAudit='N' and dataStatus='Y')) order by postTime desc ";
		String newHql="from PostInfo where createPerson= "+userId+" and isTop='N' and ((isAudit='Y' and auditStatus='Y' and dataStatus='Y') or (isAudit='N' and dataStatus='Y')) order by postTime desc ";
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

	@Override
	public List<PostInfo> getMyReplyPostInfoList(Long userId) {
		String hql="select p1 from PostInfo p1 where p1.id in (select p2.postId from PostReply p2 where p2.createPerson="+userId+" and p2.isPoint='N') and p1.isTop='Y' and ((p1.isAudit='Y' and p1.auditStatus='Y' and p1.dataStatus='Y') or (p1.isAudit='N' and p1.dataStatus='Y')) order by p1.postTime desc ";
		String newHql="select p1 from PostInfo p1 where p1.id in (select p2.postId from PostReply p2 where p2.createPerson="+userId+" and p2.isPoint='N') and p1.isTop='N' and ((p1.isAudit='Y' and p1.auditStatus='Y' and p1.dataStatus='Y') or (p1.isAudit='N' and p1.dataStatus='Y')) order by p1.postTime desc ";
		List<PostInfo> topList = null;
		try {
			topList=getHibernateTemplate().find(hql);
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
		} catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return topList;
	}

}
