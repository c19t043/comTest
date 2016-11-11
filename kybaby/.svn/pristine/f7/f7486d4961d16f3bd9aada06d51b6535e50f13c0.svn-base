package com.kybaby.newbussiness.mommyring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.mommyring.dao.MommyPostInfoDao;
import com.kybaby.newbussiness.mommyring.domain.MommyPostInfo;

/**
 * @ClassName:PostInfoDaoImpl
 * @Description:用户发帖数据管理接口实现
 * @author Hoolee
 * @date 2015年12月13日下午1:55:20
 */
public class MommyPostInfoDaoImpl extends HibernateDaoSupport implements MommyPostInfoDao {

	public List<MommyPostInfo> getSomeCategoryPostInfo(long categoryId) {
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

	public MommyPostInfo getPostInfoInstanceById(long postInfoId) {
		String hql="from MommyPostInfo where id= "+postInfoId;
		List<MommyPostInfo> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public void updatePostInfoInstance(MommyPostInfo postInfoInstance) {
		getHibernateTemplate().update(postInfoInstance);
	}

	public long addNewPostInfoInstance(MommyPostInfo postInfoInstance) {
		Object replaceInstance=getHibernateTemplate().save(postInfoInstance);
		Long instanceId=0L;
		try{
			instanceId=Long.valueOf(replaceInstance.toString());
		}catch(Exception e){
			instanceId=0L;
		}
		return instanceId;
	}

	public String getAdminNameById(long adminId) {
		String hql="select contactName from Admin where id="+adminId;
		List<String> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<MommyPostInfo> getMySendPostInfoList(Long userId) {
		String hql="from MommyPostInfo where createPerson= "+userId+" and isTop='Y' and ((isAudit='Y' and auditStatus='Y' and dataStatus='Y') or (isAudit='N' and dataStatus='Y')) order by postTime desc ";
		String newHql="from MommyPostInfo where createPerson= "+userId+" and isTop='N' and ((isAudit='Y' and auditStatus='Y' and dataStatus='Y') or (isAudit='N' and dataStatus='Y')) order by postTime desc ";
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

	@Override
	public List<MommyPostInfo> getMyReplyPostInfoList(Long userId) {
		String hql="select p1 from MommyPostInfo p1 where p1.id in (select p2.postId from MommyPostReply p2 where p2.createPerson="+userId+" and p2.isPoint='N') and p1.isTop='Y' and ((p1.isAudit='Y' and p1.auditStatus='Y' and p1.dataStatus='Y') or (p1.isAudit='N' and p1.dataStatus='Y')) order by p1.postTime desc ";
		String newHql="select p1 from MommyPostInfo p1 where p1.id in (select p2.postId from MommyPostReply p2 where p2.createPerson="+userId+" and p2.isPoint='N') and p1.isTop='N' and ((p1.isAudit='Y' and p1.auditStatus='Y' and p1.dataStatus='Y') or (p1.isAudit='N' and p1.dataStatus='Y')) order by p1.postTime desc ";
		List<MommyPostInfo> topList = null;
		try {
			topList=getHibernateTemplate().find(hql);
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
		} catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return topList;
	}

}
