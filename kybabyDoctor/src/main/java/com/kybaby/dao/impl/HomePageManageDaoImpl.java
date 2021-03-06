package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.HomePageManageDao;
import com.kybaby.domain.DoctorArticle;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Product;

/**
 * @author sujiantang
 *
 */
public class HomePageManageDaoImpl extends HibernateDaoSupport implements HomePageManageDao{

	@Override
	public Product getProductById(Long id) {
		List list=getHibernateTemplate().find("from Product where id=? and productStatus='Y'",id);
		if(list.isEmpty()==true){
			return null;
		}
		return (Product)list.get(0);
	}

	@Override
	public List getDoctorAssessmentTagByDoctorId(Long id) {
		List list=getHibernateTemplate().find("select a.hitCount, b.tagName from DoctorAssessmentTag a, AssessmentTag b where a.id=? AND b.id=a.tagId and b.tagStatus='Y'",id);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public List<DoctorArticle> getDoctorArticleByDoctorId(Long id) {
		List list=getHibernateTemplate().find("from DoctorArticle where doctorId=? order by publishTime desc",id);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public String getMajorNameById(Long id) {
		List list=getHibernateTemplate().find("select major from Major where id=? and majorStatus='Y'",id);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

	@Override
	public DoctorInfo getDoctorInfoByDoctorId() {
		List list=getHibernateTemplate().find("from DoctorInfo where doctorPhone=? and doctorStatus='Y'");
		if(list.isEmpty()==true){
			return null;
		}
		return (DoctorInfo)list.get(0);
	}

	
}
