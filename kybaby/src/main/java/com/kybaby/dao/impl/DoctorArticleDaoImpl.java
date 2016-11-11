package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorArticleDao;
import com.kybaby.domain.DoctorArticle;

/**
 * @ClassName:DoctorArticleDaoImpl
 * @Description:医生个人专栏数据管理实现
 * @author Hoolee
 * @date 2015年10月6日下午4:27:00
 */
public class DoctorArticleDaoImpl extends HibernateDaoSupport implements DoctorArticleDao {

	public List<DoctorArticle> getSomeDoctorArticleList(long doctorId) {
		List<DoctorArticle> list=getHibernateTemplate().find("from DoctorArticle where doctorId=? and articleStatus = '通过' order by publishTime desc ", doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public void updateArticleInstance(DoctorArticle doctorArticle) {
		getHibernateTemplate().update(doctorArticle);
	}

	public DoctorArticle getDoctorArticleInstance(long articleId) {
		List<DoctorArticle> list=getHibernateTemplate().find("from DoctorArticle where id=?",articleId);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

}
