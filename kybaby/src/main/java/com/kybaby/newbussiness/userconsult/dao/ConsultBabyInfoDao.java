package com.kybaby.newbussiness.userconsult.dao;

import java.util.List;

import com.kybaby.newbussiness.userconsult.domain.ConsultBabyInfo;

public interface ConsultBabyInfoDao {
	
	/**
	 * 增加一条baby信息
	 * @param babyInfo 宝宝对象
	 * @throws Exception
	 */
	public void addBabyInfo(ConsultBabyInfo babyInfo)throws Exception;
	
	/**
	 * 删除宝宝记录
	 * @param babyInfo 宝宝对象
	 * @throws Exception
	 */
	public void deleteBabyInfo(ConsultBabyInfo babyInfo) throws Exception;
	
	/**
	 * 更新宝宝记录
	 * @param babyInfo 宝宝记录
	 * @throws Exception
	 */
	public void updateBabyInfo(ConsultBabyInfo babyInfo)throws Exception; 
	
	/**
	 * 根据宝宝id，获取宝宝记录
	 * @param babyId 宝宝id
	 * @return
	 * @throws Exception
	 */
	public ConsultBabyInfo getBabyInfoByID(Long babyId) throws Exception;
	
	/**
	 * 根据父母id，获取所有宝宝记录
	 * @return
	 * @throws Exception
	 */
	public List<ConsultBabyInfo> getBabyInfoListByParentId(Long parentId) throws Exception;
}
