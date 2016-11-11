package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorArticleCommentDao;
import com.kybaby.domain.DoctorArticleComment;

/**
 * @ClassName:DoctorArticleCommentDaoImpl
 * @Description:医生个人专栏数据管理接口实现
 * @author Hoolee
 * @date 2015年10月6日下午7:23:48
 */
public class DoctorArticleCommentDaoImpl extends HibernateDaoSupport implements DoctorArticleCommentDao {

	public List<Object[]> getSomeArticleComments(long articleId) {
		List<Object[]> list=getHibernateTemplate().find("select b.parentName,b.userImage,a.submitTime,a.userComments from DoctorArticleComment a,UserInfo b where a.articleId=? and a.userId=b.id and a.commentsStatus='通过'  ", articleId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public void addNewComment(DoctorArticleComment comments) {
		getHibernateTemplate().save(comments);
	}

}
