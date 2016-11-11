package com.kybaby.newbussiness.mommyring.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.mommyring.bo.MommyPostReplyBo;
import com.kybaby.newbussiness.mommyring.dao.MommyPostReplyDao;
import com.kybaby.newbussiness.mommyring.domain.MommyPostReply;

/**
 * @ClassName:MommyPostReplyBoImpl
 * @Description:帖子回复事物管理接口实现
 * @author Hoolee
 * @date 2015年12月13日下午6:06:29
 */
public class MommyPostReplyBoImpl implements MommyPostReplyBo {

	MommyPostReplyDao postReplyDao;
	
	public List<MommyPostReply> getSomeMommyPostInfoReply(long mommyPostInfoId) {
		return postReplyDao.getSomeMommyPostInfoReply(mommyPostInfoId);
	}

	public List<MommyPostReply> getSomeUserGiveStar(long mommyPostInfoId, long userId) {
		return postReplyDao.getSomeUserGiveStar(mommyPostInfoId, userId);
	}

	public void addNewMommyPostReply(long postId, long replyId, String isPoint,
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
		postReplyDao.addNewMommyPostReply(postReply);
	}
	
	public boolean deleteSomeMommyPostReply(long mommyPostInfoId, long userId) {
		boolean flag=false;
		List<MommyPostReply> postReplyList=postReplyDao.getSomeUserGiveStar(mommyPostInfoId, userId);
		if(postReplyList!=null){
			postReplyDao.deleteSomeMommyPostReply(postReplyList.get(0));
			flag=true;
		}
		return flag;
	}

	public MommyPostReplyDao getMommyPostReplyDao() {
		return postReplyDao;
	}

	public void setMommyPostReplyDao(MommyPostReplyDao postReplyDao) {
		this.postReplyDao = postReplyDao;
	}

}
