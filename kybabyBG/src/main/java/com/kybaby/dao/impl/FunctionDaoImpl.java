package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.FunctionDao;
import com.kybaby.domain.FunctionList;
import com.kybaby.domain.FunctionParent;

/**
 * @ClassName:FunctionDaoImpl
 * @Description:功能的数据管理实现
 * @author Hoolee
 * @date 2015年9月5日下午12:12:06
 */
public class FunctionDaoImpl extends HibernateDaoSupport implements FunctionDao {

	public List<FunctionParent> getAllFunctionParentList() {
		List<FunctionParent> list=getHibernateTemplate().find("from FunctionParent where status='Y'");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<FunctionList> getSomeFunctionListByParentId(long id) {
		List<FunctionList> list=getHibernateTemplate().find("from FunctionList where parentId=? and status='Y' ",id );
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}
	
}
