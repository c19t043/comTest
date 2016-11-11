package com.kybaby.newbussiness.mommyring.bo.impl;

import com.kybaby.newbussiness.mommyring.bo.MommyPostInfoLabelBo;
import com.kybaby.newbussiness.mommyring.dao.MommyPostInfoLabelDao;
import com.kybaby.newbussiness.mommyring.domain.MommyPostInfoLabel;

/**
 * @ClassName:PostInfoLabelBoImpl
 * @Description:帖子标签的事物管理接口实现
 * @author Hoolee
 * @date 2015年12月29日上午10:55:10
 */
public class MommyPostInfoLabelBoImpl implements MommyPostInfoLabelBo {

	MommyPostInfoLabelDao mommyPostInfoLabelDao;

	public MommyPostInfoLabel checkSomePostInfoLabel(long postInfoId, long labelId) {
		return mommyPostInfoLabelDao.checkSomePostInfoLabel(postInfoId, labelId);
	}
	
	public void addNewPostInfoLabelList(long postInfoId, String labelIds,String splitStr) {
		String[] labelIdArr=labelIds.split(splitStr);
		MommyPostInfoLabel postInfoLabelInstance=null;
		for(int arrLength=0;arrLength<labelIdArr.length;arrLength++){
			String labelIdStr=labelIdArr[arrLength];
			long labelId=Long.valueOf(labelIdStr);
			postInfoLabelInstance=new MommyPostInfoLabel();
			postInfoLabelInstance.setPostInfoId(postInfoId);
			postInfoLabelInstance.setRingLabelId(labelId);
			mommyPostInfoLabelDao.addNewPostInfoLabelInstance(postInfoLabelInstance);
		}
	}

	public MommyPostInfoLabelDao getMommyPostInfoLabelDao() {
		return mommyPostInfoLabelDao;
	}

	public void setMommyPostInfoLabelDao(MommyPostInfoLabelDao mommyPostInfoLabelDao) {
		this.mommyPostInfoLabelDao = mommyPostInfoLabelDao;
	}

}
