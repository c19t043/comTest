package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.MajorDao;
import com.kybaby.domain.DoctorGoodField;

/**
 * @ClassName:MajorDaoImpl
 * @Description:专长方向数据管理接口实现
 * @author Hoolee
 * @date 2015年10月6日上午10:44:58
 */
public class MajorDaoImpl extends HibernateDaoSupport implements MajorDao {

	public String getMajorNameById(long id) {
		List<String> list=getHibernateTemplate().find("select major from Major where id=?", id);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	@Override
	public DoctorGoodField getDoctorGoodFieldById(long id) {
		return getHibernateTemplate().get(DoctorGoodField.class, id);
	}

}
