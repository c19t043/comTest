package com.kybaby.newbussiness.mommyring.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.mommyring.bo.MommyPostInfoLabelBo;
import com.kybaby.newbussiness.mommyring.dao.MommyPostInfoLabelDao;
import com.kybaby.newbussiness.mommyring.domain.MommyPostInfoLabel;

/**
 * @ClassName:MommyPostInfoLabelBoImpl
 * @Description:帖子标签的事物管理接口实现
 * @author Hoolee
 * @date 2015年12月29日上午10:55:10
 */
public class MommyPostInfoLabelBoImpl implements MommyPostInfoLabelBo {

	MommyPostInfoLabelDao mommyPostInfoLabelDao;

	public void addNewMommyPostInfoLabelList(long mommyPostInfoId, String labelIds,String splitStr) {
		String[] labelIdArr=labelIds.split(splitStr);
		MommyPostInfoLabel mommyPostInfoLabelInstance=null;
		for(int arrLength=0;arrLength<labelIdArr.length;arrLength++){
			String labelIdStr=labelIdArr[arrLength];
			long labelId=Long.valueOf(labelIdStr);
			mommyPostInfoLabelInstance=new MommyPostInfoLabel();
			mommyPostInfoLabelInstance.setPostInfoId(mommyPostInfoId);
			mommyPostInfoLabelInstance.setRingLabelId(labelId);
			mommyPostInfoLabelDao.addNewMommyPostInfoLabelInstance(mommyPostInfoLabelInstance);
		}
	}

	public void deleteSomeMommyPostInfoLabelLinkes(long labelId) {
		mommyPostInfoLabelDao.deleteSomeMommyPostInfoLabelLinkes(labelId);
	}

	public List<Long> getSomeMommyPostInfoLabelList(long mommyPostInfoLabelId) {
		return mommyPostInfoLabelDao.getSomeMommyPostInfoLabelList(mommyPostInfoLabelId);
	}

	public void deleteSomeMommyPostInfoLabels(long mommyPostInfoId, String labelIds,String splitStr) {
		mommyPostInfoLabelDao.deleteSomeMommyPostInfoLabels(mommyPostInfoId);
		this.addNewMommyPostInfoLabelList(mommyPostInfoId, labelIds, splitStr);
	}

	public MommyPostInfoLabelDao getMommyPostInfoLabelDao() {
		return mommyPostInfoLabelDao;
	}

	public void setMommyPostInfoLabelDao(MommyPostInfoLabelDao mommyPostInfoLabelDao) {
		this.mommyPostInfoLabelDao = mommyPostInfoLabelDao;
	}

}
