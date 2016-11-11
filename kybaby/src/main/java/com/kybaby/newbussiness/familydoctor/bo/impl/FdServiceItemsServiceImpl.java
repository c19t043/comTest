package com.kybaby.newbussiness.familydoctor.bo.impl;

import java.util.List;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.familydoctor.bo.FdServiceItemsService;
import com.kybaby.newbussiness.familydoctor.dao.FdServiceItemsDao;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceItems;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceMember;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceOrder;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceTeams;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceTimes;
import com.kybaby.newbussiness.familydoctor.domain.FdUserBuyRecord;

public class FdServiceItemsServiceImpl implements FdServiceItemsService{

	/**
	 * 注入Dao服务
	 */
	private FdServiceItemsDao fdServiceItemsDao;
	
	public void setFdServiceItemsDao(FdServiceItemsDao fdServiceItemsDao) {
		this.fdServiceItemsDao = fdServiceItemsDao;
	}
	
	
	@Override
	public List<FdUserBuyRecord> getFdUserBuyRecordList(UserInfo userInfo,FdServicePackage fdServicePackage){
		return fdServiceItemsDao.getFdUserBuyRecordList(userInfo,fdServicePackage);
	}

	@Override
	public FdServiceItems getFdServiceItemsById(Long id) {
		return fdServiceItemsDao.getFdServiceItemsById(id);
	}

	@Override
	public List<FdServiceItems> getAllFdServiceItems(FdServiceItems fdServiceItems) {
		return fdServiceItemsDao.getAllFdServiceItems(fdServiceItems);
	}


	@Override
	public List<FdServicePackage> getFdServicePackageList(Long hospitalId) {
		return fdServiceItemsDao.getFdServicePackageList(hospitalId);
	}


	@Override
	public List<FdServiceTeams> getFdServiceTeamsList(
			FdServicePackage fdServicePackage) {
		return fdServiceItemsDao.getFdServiceTeamsList(fdServicePackage);
	}


	@Override
	public List<FdServiceTimes> getFdServiceTimesList(
			FdServicePackage fdServicePackage) {
		return fdServiceItemsDao.getFdServiceTimesList(fdServicePackage);
	}


	@Override
	public Long saveOrUpdateFdUserBuyRecord(FdUserBuyRecord fdUserBuyRecord) {
		return fdServiceItemsDao.saveOrUpdateFdUserBuyRecord(fdUserBuyRecord);
	}


	@Override
	public List<FdServiceMember> getFdServiceMemberList(
			FdServiceTeams fdServiceTeams,HospitalBasicInfo hospitalBasicInfo) {
		return fdServiceItemsDao.getFdServiceMemberList(fdServiceTeams,hospitalBasicInfo);
	}


	@Override
	public Long saveOrUpdateFdServiceOrder(FdServiceOrder fdServiceOrder) {
		return fdServiceItemsDao.saveOrUpdateFdServiceOrder(fdServiceOrder);
	}


	@Override
	public FdServiceOrder getFdServiceOrderById(Long id) {
		return fdServiceItemsDao.getFdServiceOrderById(id);
	}


	@Override
	public List<FdServiceOrder> getFdServiceOrderList(
			FdServicePackage fdServicePackage, FdServiceTeams fdServiceTeams,
			FdServiceTimes fdServiceTimes,UserInfo userInfo,FdServiceOrder fdServiceOrder) {
		return fdServiceItemsDao.getFdServiceOrderList(fdServicePackage, fdServiceTeams,fdServiceTimes,userInfo,fdServiceOrder);
	}


	@Override
	public FdServiceTeams getFdServiceTeamsById(Long id) {
		return fdServiceItemsDao.getFdServiceTeamsById(id);
	}


	@Override
	public FdServicePackage getFdServicePackageById(Long id) {
		return fdServiceItemsDao.getFdServicePackageById(id);
	}

}
