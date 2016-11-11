package com.kybaby.newbussiness.mommyring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.mommyring.dao.MommyPostReplyDao;
import com.kybaby.newbussiness.mommyring.domain.MommyPostReply;

/**
 * @ClassName:PostReplyDaoImpl
 * @Description:帖子回复数据管理接口实现
 * @author Hoolee
 * @date 2015年12月13日下午6:07:11
 */
public class MommyPostReplyDaoImpl extends HibernateDaoSupport implements MommyPostReplyDao {

	public List<MommyPostReply> getSomePostInfoReply(long postInfoId) {
		String hql="from MommyPostReply where postId= "+postInfoId +" and replyContent<>'' and ((isAudit='Y' and auditStatus='Y' and dataStatus='Y' ) or (isAudit='N' and dataStatus='Y' )) ";
		List<MommyPostReply> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<MommyPostReply> getSomeUserGiveStar(long postInfoId, long doctorId) {
		String hql="from MommyPostReply where postId= "+postInfoId+" and replyId="+postInfoId+" and isPoint='Y' and createPerson="+doctorId;
		List<MommyPostReply> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public void addNewPostReply(MommyPostReply postReply) {
		getHibernateTemplate().save(postReply);
	}

	public void deleteSomePostReply(MommyPostReply postReply) {
		getHibernateTemplate().delete(postReply);
	}
	public MommyPostReply getPostReplyInstanceById(long replyId) {
		String hql="from MommyPostReply where id="+replyId;
		List<MommyPostReply> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

}
