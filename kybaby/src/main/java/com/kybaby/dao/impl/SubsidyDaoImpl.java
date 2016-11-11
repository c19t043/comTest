package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.SubsidyDao;

/**
 * @ClassName:SubsidyDaoImpl
 * @Description:补贴相关的数据管理接口实现
 * @author Hoolee
 * @date 2015年10月9日上午9:50:48
 */
public class SubsidyDaoImpl extends HibernateDaoSupport implements SubsidyDao {

	public double getMaxAmountTracficAmount(String subsidyMethod,String serviceDate) {
		String hqlStr="select amount from Subsidy where subsidyMethod='"+subsidyMethod+"' and startDate<='"+serviceDate+"' and endDate >='"+serviceDate+"' and subsidyStatus='Y' order by amount desc ";
		List<Double> list=getHibernateTemplate().find(hqlStr);
		if(list.isEmpty()==true){
			return 0;
		}
		return list.get(0);
	}

	
	
}
