package com.kybaby.newbussiness.doctorring.bo.impl;

import java.util.List;

import com.kybaby.domain.PostInfo;
import com.kybaby.newbussiness.doctorring.bo.PostInfoBo;
import com.kybaby.newbussiness.doctorring.dao.PostInfoDao;
import com.kybaby.newbussiness.doctorring.util.DateManage;

/**
 * @ClassName:PostInfoBoImpl
 * @Description:用户发帖事物管理接口实现
 * @author Hoolee
 * @date 2015年12月13日下午1:53:24
 */
public class PostInfoBoImpl implements PostInfoBo {
	PostInfoDao postInfoDao;
	
	@Override
	public List<PostInfo> getSomeCategoryPostInfo(long categoryId) {
		return postInfoDao.getSomeCategoryPostInfo(categoryId);
	}

	@Override
	public PostInfo getPostInfoInstanceById(long postInfoId) {
		return postInfoDao.getPostInfoInstanceById(postInfoId);
	}

	@Override
	public void updatePostInfoInstance(PostInfo postInfoInstance) {
		postInfoDao.updatePostInfoInstance(postInfoInstance);
	}
	
	@Override
	public long addNewPostInfoInstance(long typeId, String title,
			String content, String postImg, String recordingVoiceUrl,
			String externalVideoUrl, String uploadVideoUrl,
			String isAllowReply, String createPerson, String isAudit) {
		String postTime=DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
		String auditStatus="Y";
		if(isAudit.equals("Y")){
			auditStatus="N";
		}
		PostInfo postInfoInstance=new PostInfo();
		postInfoInstance.setTypeId(typeId);
		postInfoInstance.setTitle(title);
		postInfoInstance.setContent(content);
		postInfoInstance.setPostImg(postImg);
		postInfoInstance.setRecordingVoiceUrl(recordingVoiceUrl);
		postInfoInstance.setExternalVideoUrl(externalVideoUrl);
		postInfoInstance.setUploadVideoUrl(uploadVideoUrl);
		postInfoInstance.setIsAllowReply(isAllowReply);
		postInfoInstance.setCreatePerson(createPerson);
		postInfoInstance.setLastReplyPerson(Long.valueOf(createPerson));
		postInfoInstance.setIsAudit(isAudit);
		postInfoInstance.setAuditStatus(auditStatus);
		postInfoInstance.setPostTime(postTime);
		postInfoInstance.setCreateTime(postTime);
		postInfoInstance.setLastReplyTime(postTime);
		postInfoInstance.setIsTop("N");
		postInfoInstance.setPointNum(0L);
		postInfoInstance.setBrowseNum(0L);
		postInfoInstance.setReplyNum(0L);
		postInfoInstance.setDataStatus("Y");
		return postInfoDao.addNewPostInfoInstance(postInfoInstance);
	}

	@Override
	public String getAdminNameById(long adminId) {
		return postInfoDao.getAdminNameById(adminId);
	}

	public PostInfoDao getPostInfoDao() {
		return postInfoDao;
	}

	public void setPostInfoDao(PostInfoDao postInfoDao) {
		this.postInfoDao = postInfoDao;
	}

	@Override
	public List<PostInfo> getMySendPostInfoList(Long userId) {
		return postInfoDao.getMySendPostInfoList(userId);
	}

	@Override
	public List<PostInfo> getMyReplyPostInfoList(Long userId) {
		return postInfoDao.getMyReplyPostInfoList(userId);
	}
}
