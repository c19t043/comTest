package com.kybaby.newbussiness.doctorring.bo.impl;

import com.kybaby.domain.PostInfoLabel;
import com.kybaby.newbussiness.doctorring.bo.PostInfoLabelBo;
import com.kybaby.newbussiness.doctorring.dao.PostInfoLabelDao;

/**
 * @ClassName:PostInfoLabelBoImpl
 * @Description:帖子标签的事物管理接口实现
 * @author Hoolee
 * @date 2015年12月29日上午10:55:10
 */
public class PostInfoLabelBoImpl implements PostInfoLabelBo {

	PostInfoLabelDao postInfoLabelDao;

	@Override
	public PostInfoLabel checkSomePostInfoLabel(long postInfoId, long labelId) {
		return postInfoLabelDao.checkSomePostInfoLabel(postInfoId, labelId);
	}
	
	@Override
	public void addNewPostInfoLabelList(long postInfoId, String labelIds,String splitStr) {
		String[] labelIdArr=labelIds.split(splitStr);
		PostInfoLabel postInfoLabelInstance=null;
		for(int arrLength=0;arrLength<labelIdArr.length;arrLength++){
			String labelIdStr=labelIdArr[arrLength];
			long labelId=Long.valueOf(labelIdStr);
			postInfoLabelInstance=new PostInfoLabel();
			postInfoLabelInstance.setPostInfoId(postInfoId);
			postInfoLabelInstance.setRingLabelId(labelId);
			postInfoLabelDao.addNewPostInfoLabelInstance(postInfoLabelInstance);
		}
	}

	public PostInfoLabelDao getPostInfoLabelDao() {
		return postInfoLabelDao;
	}

	public void setPostInfoLabelDao(PostInfoLabelDao postInfoLabelDao) {
		this.postInfoLabelDao = postInfoLabelDao;
	}

}
