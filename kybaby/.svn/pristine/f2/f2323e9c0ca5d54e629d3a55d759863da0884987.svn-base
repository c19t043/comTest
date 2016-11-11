package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.GrowthRecordDao;
import com.kybaby.domain.GrowthRecord;

/**
 * @ClassName:GrowthRecordDaoImpl
 * @Description:成长记录数据管理实现
 * @author Hoolee
 * @date 2015年10月13日下午10:52:46
 */
public class GrowthRecordDaoImpl extends HibernateDaoSupport implements GrowthRecordDao {

	public List<GrowthRecord> getGrowthRecordByUserId(long userId) {
		List<GrowthRecord> list=getHibernateTemplate().find("from GrowthRecord where userId=? order by id desc ",userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public void addNewRecord(GrowthRecord growthRecord) {
		if(growthRecord.getId() == null){
			getHibernateTemplate().save(growthRecord);
		}else{
			getHibernateTemplate().update(growthRecord);
		}
	}

	@Override
	public GrowthRecord getGrowthRecordById(Long id) {
		return getHibernateTemplate().get(GrowthRecord.class, id);
	}

}
