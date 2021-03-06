package com.kybaby.newbussiness.doctorring.bo.impl;

import java.util.List;

import com.kybaby.domain.PostReply;
import com.kybaby.newbussiness.doctorring.bo.PostReplyBo;
import com.kybaby.newbussiness.doctorring.dao.PostReplyDao;
import com.kybaby.newbussiness.doctorring.util.DateManage;

/**
 * @ClassName:PostReplyBoImpl
 * @Description:帖子回复事物管理接口实现
 * @author Hoolee
 * @date 2015年12月13日下午6:06:29
 */
public class PostReplyBoImpl implements PostReplyBo {

	PostReplyDao postReplyDao;
	
	@Override
	public List<PostReply> getSomePostInfoReply(long postInfoId) {
		return postReplyDao.getSomePostInfoReply(postInfoId);
	}

	@Override
	public List<PostReply> getSomeUserGiveStar(long postInfoId, long doctorId) {
		return postReplyDao.getSomeUserGiveStar(postInfoId, doctorId);
	}

	@Override
	public void addNewPostReply(long postId, long replyId, String isPoint,
			long createPerson, String isAllowReply, String isAudit,
			String dataStatus, String replyContent) {
		String auditStatus="Y";
		if(isAudit.equals("Y")){
			auditStatus="N";
		}
		String createTime=DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
		PostReply postReply=new PostReply();
		postReply.setPostId(postId);
		postReply.setReplyId(replyId);
		postReply.setIsPoint(isPoint);
		postReply.setCreatePerson(createPerson);
		postReply.setIsAllowReply(isAllowReply);
		postReply.setIsAudit(isAudit);
		postReply.setDataStatus(dataStatus);
		postReply.setReplyContent(replyContent);
		postReply.setAuditStatus(auditStatus);
		postReply.setCreateTime(createTime);
		postReplyDao.addNewPostReply(postReply);
	}
	
	@Override
	public boolean deleteSomePostReply(long postInfoId, long doctorId) {
		boolean flag=false;
		List<PostReply> postReplyList=postReplyDao.getSomeUserGiveStar(postInfoId, doctorId);
		if(postReplyList!=null){
			postReplyDao.deleteSomePostReply(postReplyList.get(0));
			flag=true;
		}
		return flag;
	}

	@Override
	public PostReply getPostReplyInstanceById(long replyId) {
		return postReplyDao.getPostReplyInstanceById(replyId);
	}

	public PostReplyDao getPostReplyDao() {
		return postReplyDao;
	}

	public void setPostReplyDao(PostReplyDao postReplyDao) {
		this.postReplyDao = postReplyDao;
	}
}
