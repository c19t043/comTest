package com.kybaby.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.FeedbackDao;
import com.kybaby.domain.Feedback;

/**
 * @ClassName:FeedbackDaoImpl
 * @Description:意见反馈数据管理接口实现
 * @author Hoolee
 * @date 2015年10月14日下午6:38:39
 */
public class FeedbackDaoImpl extends HibernateDaoSupport implements FeedbackDao {

	public void addNewFeedback(Feedback feedback) {
		getHibernateTemplate().save(feedback);
	}

}
