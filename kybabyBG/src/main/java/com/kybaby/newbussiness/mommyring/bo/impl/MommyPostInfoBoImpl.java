package com.kybaby.newbussiness.mommyring.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.mommyring.bo.MommyPostInfoBo;
import com.kybaby.newbussiness.mommyring.dao.MommyPostInfoDao;
import com.kybaby.newbussiness.mommyring.domain.MommyPostInfo;

/**
 * @ClassName:MommyPostInfoBoImpl
 * @Description:用户发帖事物管理接口实现
 * @author Hoolee
 * @date 2015年12月13日下午1:53:24
 */
public class MommyPostInfoBoImpl implements MommyPostInfoBo {
	MommyPostInfoDao mommyPostInfoDao;
	
	public List<MommyPostInfo> getSomeCategoryMommyPostInfo(long categoryId) {
		return mommyPostInfoDao.getSomeCategoryMommyPostInfo(categoryId);
	}

	public MommyPostInfo getMommyPostInfoInstanceById(long mommyPostInfoId) {
		return mommyPostInfoDao.getMommyPostInfoInstanceById(mommyPostInfoId);
	}

	public void updateMommyPostInfoInstance(MommyPostInfo mommyPostInfoInstance) {
		mommyPostInfoDao.updateMommyPostInfoInstance(mommyPostInfoInstance);
	}
	
	public long addNewMommyPostInfoInstance(long typeId, String title,
			String content, String postImg, String recordingVoiceUrl,
			String externalVideoUrl, String uploadVideoUrl,
			String isAllowReply, String createPerson, String isAudit,String isTop,String dataStatus) {
		String postTime=DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
		String auditStatus="Y";
		if(isAudit.equals("Y")){
			auditStatus="N";
		}
		MommyPostInfo mommyPostInfoInstance=new MommyPostInfo();
		mommyPostInfoInstance.setTypeId(typeId);
		mommyPostInfoInstance.setTitle(title);
		mommyPostInfoInstance.setContent(content);
		mommyPostInfoInstance.setPostImg(postImg);
		mommyPostInfoInstance.setRecordingVoiceUrl(recordingVoiceUrl);
		mommyPostInfoInstance.setExternalVideoUrl(externalVideoUrl);
		mommyPostInfoInstance.setUploadVideoUrl(uploadVideoUrl);
		mommyPostInfoInstance.setIsAllowReply(isAllowReply);
		mommyPostInfoInstance.setCreatePerson(createPerson);
		mommyPostInfoInstance.setLastReplyPerson(0L);
		mommyPostInfoInstance.setIsAudit(isAudit);
		mommyPostInfoInstance.setAuditStatus(auditStatus);
		mommyPostInfoInstance.setPostTime(postTime);
		mommyPostInfoInstance.setCreateTime(postTime);
		mommyPostInfoInstance.setLastReplyTime(postTime);
		mommyPostInfoInstance.setIsTop(isTop);
		mommyPostInfoInstance.setPointNum(0L);
		mommyPostInfoInstance.setBrowseNum(0L);
		mommyPostInfoInstance.setReplyNum(0L);
		mommyPostInfoInstance.setDataStatus(dataStatus);
		mommyPostInfoInstance.setIsTop(isTop);
		return mommyPostInfoDao.addNewMommyPostInfoInstance(mommyPostInfoInstance);
	}

	public List<MommyPostInfo> getAllMommyPostInfo() {
		return mommyPostInfoDao.getAllMommyPostInfo();
	}

	public MommyPostInfoDao getMommyPostInfoDao() {
		return mommyPostInfoDao;
	}

	public void setMommyPostInfoDao(MommyPostInfoDao mommyPostInfoDao) {
		this.mommyPostInfoDao = mommyPostInfoDao;
	}
}
