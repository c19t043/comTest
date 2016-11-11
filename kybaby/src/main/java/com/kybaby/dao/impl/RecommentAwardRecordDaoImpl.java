package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.RecommentAwardRecordDao;
import com.kybaby.domain.RecommentAwardRecord;

/**
 * @ClassName:RecommentAwardRecordDaoImpl
 * @Description:推荐奖励数据管理实现
 * @author Hoolee
 * @date 2015年9月28日下午1:45:25
 */
public class RecommentAwardRecordDaoImpl extends HibernateDaoSupport implements RecommentAwardRecordDao {

	public void addNewRecommentAwardRecord(RecommentAwardRecord recommentAwardRecord) {
		getHibernateTemplate().save(recommentAwardRecord);
	}

	public List<RecommentAwardRecord> getSomeUserRecommentAwardRecord(long beenRecommendUserId, String isGrant, String whenToGrant) {
		List<RecommentAwardRecord> list=getHibernateTemplate().find("from RecommentAwardRecord where beenRecommendUserId=? and isGrant=? and whenToGrant=? ", new Object[]{beenRecommendUserId,isGrant,whenToGrant});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public void updateRecommentAwardRecord(RecommentAwardRecord record) {
		getHibernateTemplate().update(record);
	}

}
