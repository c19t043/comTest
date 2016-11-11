package com.kybaby.bo.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.kybaby.bo.FeedbackBo;
import com.kybaby.dao.FeedbackDao;
import com.kybaby.domain.Feedback;

/**
 * @ClassName:FeedbackBoImpl
 * @Description:意见反馈事物管理接口
 * @author Hoolee
 * @date 2015年10月14日下午6:35:47
 */
public class FeedbackBoImpl implements FeedbackBo {

	FeedbackDao feedbackDao;
	
	public void addNewFeedback(String userPhone, String content) {
		Feedback feedback=new Feedback();
		feedback.setPhone(userPhone);
		feedback.setContent(content);
		feedback.setIsHandle("N");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date rightNow=new Date(System.currentTimeMillis());
		String dateNow=sdf.format(rightNow);
		feedback.setSubmitTime(dateNow);
		feedbackDao.addNewFeedback(feedback);
	}
	
	public FeedbackDao getFeedbackDao() {
		return feedbackDao;
	}
	
	public void setFeedbackDao(FeedbackDao feedbackDao) {
		this.feedbackDao = feedbackDao;
	}

}
