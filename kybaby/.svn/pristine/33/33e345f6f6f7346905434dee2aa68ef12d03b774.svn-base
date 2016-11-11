package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.SymptomTagDao;
import com.kybaby.domain.SymptomTag;

/**
 * @ClassName:SymptomTagDaoImpl
 * @Description:症状标签数据管理接口实现
 * @author Hoolee
 * @date 2015年10月12日下午3:30:53
 */
public class SymptomTagDaoImpl extends HibernateDaoSupport implements SymptomTagDao {

	public SymptomTag getSymptomTagInstanceById(long id) {
		List<SymptomTag> list=getHibernateTemplate().find("from SymptomTag where id=? ", id);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public List<SymptomTag> getAllSymptomTag() {
		List<SymptomTag> list=getHibernateTemplate().find("from SymptomTag where symptomStatus='Y' ");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public void updateSymptomTagInstance(SymptomTag symptomTag) {
		getHibernateTemplate().update(symptomTag);
	}

}
