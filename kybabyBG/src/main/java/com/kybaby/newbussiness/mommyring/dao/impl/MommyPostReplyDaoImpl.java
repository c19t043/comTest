package com.kybaby.newbussiness.mommyring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.mommyring.dao.MommyPostReplyDao;
import com.kybaby.newbussiness.mommyring.domain.MommyPostReply;

/**
 * @ClassName:MommyPostReplyDaoImpl
 * @Description:帖子回复数据管理接口实现
 * @author Hoolee
 * @date 2015年12月13日下午6:07:11
 */
public class MommyPostReplyDaoImpl extends HibernateDaoSupport implements MommyPostReplyDao {

	public List<MommyPostReply> getSomeMommyPostInfoReply(long mommyPostInfoId) {
		String hql="from MommyPostReply where postId= "+mommyPostInfoId +" and replyContent<>'' and ((isAudit='Y' and auditStatus='Y' and dataStatus='Y' ) or (isAudit='N' and dataStatus='Y' )) ";
		List<MommyPostReply> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<MommyPostReply> getSomeUserGiveStar(long mommyPostInfoId, long userId) {
		String hql="from MommyPostReply where postId= "+mommyPostInfoId+" and replyId="+mommyPostInfoId+" and isPoint='Y' and createPerson="+userId;
		List<MommyPostReply> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public void addNewMommyPostReply(MommyPostReply postReply) {
		getHibernateTemplate().save(postReply);
	}

	public void deleteSomeMommyPostReply(MommyPostReply postReply) {
		getHibernateTemplate().delete(postReply);
	}

}
