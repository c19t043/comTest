package com.kybaby.newbussiness.mommyring.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.mommyring.dao.MommyRingLabelDao;
import com.kybaby.newbussiness.mommyring.domain.MommyRingLabel;

/**
 * @ClassName:RingLabelDaoImpl
 * @Description:医生圈标签数据管理接口实现
 * @author Hoolee
 * @date 2015年12月28日下午5:36:49
 */
public class MommyRingLabelDaoImpl extends HibernateDaoSupport implements MommyRingLabelDao {

	public List<String> getSomePostInfoRingLabelNameList(long postInfoId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String sqlQueryStr="SELECT b.ringLabelName FROM mommy_post_info_label a,ring_label b WHERE a.postInfoId="+postInfoId+" AND a.ringLabelId=b.id;";
		Query query = session.createSQLQuery(sqlQueryStr);
		List<String> list=query.list();
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<Object[]> getSomePostInfoRingLabelIdAndName(long postInfoId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String sqlQueryStr="SELECT b.id, b.ringLabelName FROM mommy_post_info_label a,ring_label b WHERE a.postInfoId="+postInfoId+" AND a.ringLabelId=b.id;";
		Query query = session.createSQLQuery(sqlQueryStr);
		List<Object[]> list=query.list();
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}
	
	public List<MommyRingLabel> getSomeCategoryRingLabelList(String ringCategory) {
		String hql="from MommyRingLabel where ringCategory='"+ringCategory+"' and ringLabelStatus = 'Y' ";
		List<MommyRingLabel> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}

}
