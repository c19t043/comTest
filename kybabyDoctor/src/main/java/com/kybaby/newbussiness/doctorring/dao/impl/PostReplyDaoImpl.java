package com.kybaby.newbussiness.doctorring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.PostReply;
import com.kybaby.newbussiness.doctorring.dao.PostReplyDao;

/**
 * @ClassName:PostReplyDaoImpl
 * @Description:帖子回复数据管理接口实现
 * @author Hoolee
 * @date 2015年12月13日下午6:07:11
 */
public class PostReplyDaoImpl extends HibernateDaoSupport implements PostReplyDao {

	@Override
	public List<PostReply> getSomePostInfoReply(long postInfoId) {
		String hql="from PostReply where postId= "+postInfoId +" and replyContent<>'' and ((isAudit='Y' and auditStatus='Y' and dataStatus='Y' ) or (isAudit='N' and dataStatus='Y' )) ";
		List<PostReply> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public List<PostReply> getSomeUserGiveStar(long postInfoId, long doctorId) {
		String hql="from PostReply where postId= "+postInfoId+" and replyId="+postInfoId+" and isPoint='Y' and createPerson="+doctorId;
		List<PostReply> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public void addNewPostReply(PostReply postReply) {
		getHibernateTemplate().save(postReply);
	}

	@Override
	public void deleteSomePostReply(PostReply postReply) {
		getHibernateTemplate().delete(postReply);
	}
	@Override
	public PostReply getPostReplyInstanceById(long replyId) {
		String hql="from PostReply where id="+replyId;
		List<PostReply> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

}
