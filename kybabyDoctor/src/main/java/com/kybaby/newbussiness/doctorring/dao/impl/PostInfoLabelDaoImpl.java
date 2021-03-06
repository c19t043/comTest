package com.kybaby.newbussiness.doctorring.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.PostInfoLabel;
import com.kybaby.newbussiness.doctorring.dao.PostInfoLabelDao;

/**
 * @ClassName:PostInfoLabelDaoImpl
 * @Description:帖子标签的数据管理接口实现
 * @author Hoolee
 * @date 2015年12月29日上午10:57:47
 */
public class PostInfoLabelDaoImpl extends HibernateDaoSupport implements PostInfoLabelDao {

	@Override
	public PostInfoLabel checkSomePostInfoLabel(long postInfoId, long labelId) {
		String hql="from PostInfoLabel where postInfoId= "+postInfoId+" and ringLabelId= "+labelId;
		List<PostInfoLabel> list=getHibernateTemplate().find(hql);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public void addNewPostInfoLabelInstance(PostInfoLabel postInfoLabelInstance) {
		getHibernateTemplate().save(postInfoLabelInstance);
	}
	
}
