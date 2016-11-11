package com.kybaby.newbussiness.mommyring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.mommyring.dao.MommyPostInfoLabelDao;
import com.kybaby.newbussiness.mommyring.domain.MommyPostInfoLabel;

/**
 * @ClassName:PostInfoLabelDaoImpl
 * @Description:帖子标签的数据管理接口实现
 * @author Hoolee
 * @date 2015年12月29日上午10:57:47
 */
public class MommyPostInfoLabelDaoImpl extends HibernateDaoSupport implements MommyPostInfoLabelDao {

	public MommyPostInfoLabel checkSomePostInfoLabel(long postInfoId, long labelId) {
		String hql="from MommyPostInfoLabel where postInfoId= "+postInfoId+" and ringLabelId= "+labelId;
		List<MommyPostInfoLabel> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public void addNewPostInfoLabelInstance(MommyPostInfoLabel postInfoLabelInstance) {
		getHibernateTemplate().save(postInfoLabelInstance);
	}
	
}
