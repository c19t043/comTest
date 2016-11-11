package com.kybaby.newbussiness.doctorring.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.PostInfoLabel;
import com.kybaby.newbussiness.doctorring.dao.PostInfoLabelDao;

/**
 * @ClassName:PostInfoLabelDaoImpl
 * @Description:帖子标签的数据管理接口实现
 * @author Hoolee
 * @date 2015年12月29日上午10:57:47
 */
public class PostInfoLabelDaoImpl extends HibernateDaoSupport implements PostInfoLabelDao {

	public void addNewPostInfoLabelInstance(PostInfoLabel postInfoLabelInstance) {
		getHibernateTemplate().save(postInfoLabelInstance);
	}

	public void deleteSomePostInfoLabelLinkes(long labelId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();//打开session
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "delete from PostInfoLabel where ringLabelId="+labelId;
		session.createQuery(hqlUpdate).executeUpdate();
		tx.commit();
		session.close();
	}

	public List<Long> getSomePostInfoLabelList(long postInfoLabelId) {
		String hql="select ringLabelId from PostInfoLabel where postInfoId= "+postInfoLabelId;
		List<Long> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	public void deleteSomePostInfoLabels(long postInfoId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();//打开session
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "delete from PostInfoLabel where postInfoId="+postInfoId;
		session.createQuery(hqlUpdate).executeUpdate();
		tx.commit();
		session.close();
	}

}
