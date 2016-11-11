package com.kybaby.newbussiness.mommyring.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.mommyring.bo.MommyPostReplyBo;
import com.kybaby.newbussiness.mommyring.dao.MommyPostReplyDao;
import com.kybaby.newbussiness.mommyring.domain.MommyPostReply;
import com.kybaby.util.DateManage;

/**
 * @ClassName:PostReplyBoImpl
 * @Description:帖子回复事物管理接口实现
 * @author Hoolee
 * @date 2015年12月13日下午6:06:29
 */
public class MommyPostReplyBoImpl implements MommyPostReplyBo {

	MommyPostReplyDao mommyPostReplyDao;
	
	public List<MommyPostReply> getSomePostInfoReply(long postInfoId) {
		return mommyPostReplyDao.getSomePostInfoReply(postInfoId);
	}

	public List<MommyPostReply> getSomeUserGiveStar(long postInfoId, long userId) {
		return mommyPostReplyDao.getSomeUserGiveStar(postInfoId, userId);
	}

	public void addNewPostReply(long postId, long replyId, String isPoint,
			long createPerson, String isAllowReply, String isAudit,
			String dataStatus, String replyContent) {
		String auditStatus="Y";
		if(isAudit.equals("Y")){
			auditStatus="N";
		}
		String createTime=DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
		MommyPostReply postReply=new MommyPostReply();
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
		mommyPostReplyDao.addNewPostReply(postReply);
	}
	
	public boolean deleteSomePostReply(long postInfoId, long userId) {
		boolean flag=false;
		List<MommyPostReply> postReplyList=mommyPostReplyDao.getSomeUserGiveStar(postInfoId, userId);
		if(postReplyList!=null){
			mommyPostReplyDao.deleteSomePostReply(postReplyList.get(0));
			flag=true;
		}
		return flag;
	}

	public MommyPostReply getPostReplyInstanceById(long replyId) {
		return mommyPostReplyDao.getPostReplyInstanceById(replyId);
	}

	public MommyPostReplyDao getMommyPostReplyDao() {
		return mommyPostReplyDao;
	}

	public void setMommyPostReplyDao(MommyPostReplyDao mommyPostReplyDao) {
		this.mommyPostReplyDao = mommyPostReplyDao;
	}
}
