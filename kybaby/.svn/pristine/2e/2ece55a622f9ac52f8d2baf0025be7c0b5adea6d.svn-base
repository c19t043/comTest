package com.kybaby.newbussiness.userconsult.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.userconsult.dao.ConsultBabyInfoDao;
import com.kybaby.newbussiness.userconsult.domain.ConsultBabyInfo;

public class ConsultBabyInfoDaoImpl extends HibernateDaoSupport implements ConsultBabyInfoDao{
	/**
	 * 增加一条baby信息
	 * @param babyInfo 宝宝对象
	 * @throws Exception
	 */
	public void addBabyInfo(ConsultBabyInfo babyInfo)throws Exception{
		this.getHibernateTemplate().save(babyInfo);
	}
	
	/**
	 * 删除宝宝记录
	 * @param babyId 宝宝记录
	 * @throws Exception
	 */
	public void deleteBabyInfo(ConsultBabyInfo babyInfo) throws Exception{
		this.getHibernateTemplate().delete(babyInfo);
	}
	
	/**
	 * 更新宝宝记录
	 * @param babyInfo 宝宝记录
	 * @throws Exception
	 */
	public void updateBabyInfo(ConsultBabyInfo babyInfo)throws Exception{
		this.getHibernateTemplate().update(babyInfo);
	}
	
	/**
	 * 根据宝宝id，获取宝宝记录
	 * @param babyId 宝宝id
	 * @return
	 * @throws Exception
	 */
	public ConsultBabyInfo getBabyInfoByID(Long babyId) throws Exception{
		return this.getHibernateTemplate().get(ConsultBabyInfo.class, babyId);
	}
	
	/**
	 * 根据父母id，获取所有宝宝记录
	 * @return
	 * @throws Exception
	 */
	public List<ConsultBabyInfo> getBabyInfoListByParentId(Long parentId) throws Exception{
		String hql = "from ConsultBabyInfo a where a.userInfo.id = ?";
		return this.getHibernateTemplate().find(hql, parentId);
	}
}
