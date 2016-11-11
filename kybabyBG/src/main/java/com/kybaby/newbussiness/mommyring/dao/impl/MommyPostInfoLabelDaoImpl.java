package com.kybaby.newbussiness.mommyring.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.mommyring.dao.MommyPostInfoLabelDao;
import com.kybaby.newbussiness.mommyring.domain.MommyPostInfoLabel;

/**
 * @ClassName:MommyPostInfoLabelDaoImpl
 * @Description:帖子标签的数据管理接口实现
 * @author Hoolee
 * @date 2015年12月29日上午10:57:47
 */
public class MommyPostInfoLabelDaoImpl extends HibernateDaoSupport implements MommyPostInfoLabelDao {

	public void addNewMommyPostInfoLabelInstance(MommyPostInfoLabel mommyPostInfoLabelInstance) {
		getHibernateTemplate().save(mommyPostInfoLabelInstance);
	}

	public void deleteSomeMommyPostInfoLabelLinkes(long labelId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();//打开session
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "delete from MommyPostInfoLabel where ringLabelId="+labelId;
		session.createQuery(hqlUpdate).executeUpdate();
		tx.commit();
		session.close();
	}

	public List<Long> getSomeMommyPostInfoLabelList(long mommyPostInfoLabelId) {
		String hql="select ringLabelId from MommyPostInfoLabel where mommyPostInfoId= "+mommyPostInfoLabelId;
		List<Long> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

	public void deleteSomeMommyPostInfoLabels(long mommyPostInfoId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();//打开session
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "delete from MommyPostInfoLabel where mommyPostInfoId="+mommyPostInfoId;
		session.createQuery(hqlUpdate).executeUpdate();
		tx.commit();
		session.close();
	}

}
