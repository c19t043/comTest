package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.VersionManageDao;
import com.kybaby.domain.VersionManage;

/**
 * 版本管理DAO接口实现类
 * @author xiongchao
 */
public class VersionManageDaoImpl extends HibernateDaoSupport implements VersionManageDao {

	@Override
	public VersionManage getNewVersionCode() {
		@SuppressWarnings("unchecked")
		List<VersionManage> list = getHibernateTemplate().find("from VersionManage where 1=1 and versionType=? order by id desc", VersionManage.VERSION_TYPE_USER);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

}
