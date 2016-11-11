package com.kybaby.newbussiness.mommyring.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.mommyring.bo.MommyPostInfoBo;
import com.kybaby.newbussiness.mommyring.dao.MommyPostInfoDao;
import com.kybaby.newbussiness.mommyring.domain.MommyPostInfo;
import com.kybaby.util.DateManage;

/**
 * @ClassName:PostInfoBoImpl
 * @Description:用户发帖事物管理接口实现
 * @author Hoolee
 * @date 2015年12月13日下午1:53:24
 */
public class MommyPostInfoBoImpl implements MommyPostInfoBo {
	MommyPostInfoDao mommyPostInfoDao;
	
	public List<MommyPostInfo> getSomeCategoryPostInfo(long categoryId) {
		return mommyPostInfoDao.getSomeCategoryPostInfo(categoryId);
	}

	public MommyPostInfo getPostInfoInstanceById(long postInfoId) {
		return mommyPostInfoDao.getPostInfoInstanceById(postInfoId);
	}

	public void updatePostInfoInstance(MommyPostInfo postInfoInstance) {
		mommyPostInfoDao.updatePostInfoInstance(postInfoInstance);
	}
	
	public long addNewPostInfoInstance(long typeId, String title,
			String content, String postImg, String recordingVoiceUrl,
			String externalVideoUrl, String uploadVideoUrl,
			String isAllowReply, String createPerson, String isAudit) {
		String postTime=DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
		String auditStatus="Y";
		if(isAudit.equals("Y")){
			auditStatus="N";
		}
		MommyPostInfo postInfoInstance=new MommyPostInfo();
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
		return mommyPostInfoDao.addNewPostInfoInstance(postInfoInstance);
	}

	public String getAdminNameById(long adminId) {
		return mommyPostInfoDao.getAdminNameById(adminId);
	}

	public MommyPostInfoDao getMommyPostInfoDao() {
		return mommyPostInfoDao;
	}

	public void setMommyPostInfoDao(MommyPostInfoDao mommyPostInfoDao) {
		this.mommyPostInfoDao = mommyPostInfoDao;
	}

	@Override
	public List<MommyPostInfo> getMySendPostInfoList(Long userId) {
		return mommyPostInfoDao.getMySendPostInfoList(userId);
	}

	@Override
	public List<MommyPostInfo> getMyReplyPostInfoList(Long userId) {
		return mommyPostInfoDao.getMyReplyPostInfoList(userId);
	}
}
