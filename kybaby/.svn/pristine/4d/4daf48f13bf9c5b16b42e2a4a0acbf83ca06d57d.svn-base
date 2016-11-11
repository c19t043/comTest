package com.kybaby.newbussiness.userconsult.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.userconsult.bo.ConsultBabyInfoService;
import com.kybaby.newbussiness.userconsult.dao.ConsultBabyInfoDao;
import com.kybaby.newbussiness.userconsult.domain.ConsultBabyInfo;

public class ConsultBabyInfoServiceImpl implements ConsultBabyInfoService{
	
	private ConsultBabyInfoDao consultBabyInfoDao;
	
	/**
	 * 增加一条baby信息
	 * @param babyInfo 宝宝对象
	 * @throws Exception
	 */
	public boolean addBabyInfo(ConsultBabyInfo babyInfo)throws Exception{
		consultBabyInfoDao.addBabyInfo(babyInfo);
		if(babyInfo.getId()!=null) return true;
		else return false;
	}
	
	/**
	 * 根据宝宝id，删除宝宝记录
	 * @param babyId 宝宝id
	 * @throws Exception
	 */
	public boolean deleteBabyInfoByID(Long babyId) throws Exception{
		ConsultBabyInfo babyInfo = consultBabyInfoDao.getBabyInfoByID(babyId);
		if(babyInfo!=null){
			this.consultBabyInfoDao.deleteBabyInfo(babyInfo);
			babyInfo = consultBabyInfoDao.getBabyInfoByID(babyId);
			if(babyInfo==null) return true;
			else return false;
		}else 
			return false;
	}
	
	/**
	 * 更新宝宝记录
	 * @param babyInfo 宝宝记录
	 * @throws Exception
	 */
	public boolean updateBabyInfo(ConsultBabyInfo babyInfo)throws Exception{
		try{
			this.consultBabyInfoDao.updateBabyInfo(babyInfo);
		}catch(Exception e){
			return false;
		}
		return true;
	} 
	
	/**
	 * 根据宝宝id，获取宝宝记录
	 * @param babyId 宝宝id
	 * @return
	 * @throws Exception
	 */
	public ConsultBabyInfo getBabyInfoByID(Long babyId) throws Exception{
		return this.consultBabyInfoDao.getBabyInfoByID(babyId);
	}
	
	/**
	 * 根据父母id，获取所有宝宝记录
	 * @return
	 * @throws Exception
	 */
	public List<ConsultBabyInfo> getBabyInfoListByParentId(Long parentId) throws Exception{
		return this.consultBabyInfoDao.getBabyInfoListByParentId(parentId);
	}
	
	
	
	
	
	
	
	
	
	
	public ConsultBabyInfoDao getConsultBabyInfoDao() {
		return consultBabyInfoDao;
	}

	public void setConsultBabyInfoDao(ConsultBabyInfoDao consultBabyInfoDao) {
		this.consultBabyInfoDao = consultBabyInfoDao;
	}
}
