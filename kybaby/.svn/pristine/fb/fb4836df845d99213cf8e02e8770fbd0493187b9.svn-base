package com.kybaby.newbussiness.familydoctor.dao;

import java.util.List;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceItems;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceMember;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceOrder;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceTeams;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceTimes;
import com.kybaby.newbussiness.familydoctor.domain.FdUserBuyRecord;

/**
 * 
 * 家庭医生服务，服务项目持久层信息
 *
 */

public interface FdServiceItemsDao {
	
	/**
	 * 得到用户购买记录列表
	 * @param userInfo
	 * @return
	 */
	List<FdUserBuyRecord> getFdUserBuyRecordList(UserInfo userInfo,FdServicePackage fdServicePackage);
	/**
	 * 家庭医生通过id查询一条数据方法
	 */
	FdServiceItems getFdServiceItemsById(Long id);
	
	/**
	 * 家庭医生查询所以显示页面
	 */
	List<FdServiceItems> getAllFdServiceItems(FdServiceItems fdServficeItems);
	/**
	 * 得到医疗机构服务包列表
	 * @param hospitalId
	 * @return
	 */
	List<FdServicePackage> getFdServicePackageList(Long hospitalId);
	/**
	 * 得到服务包下的服务团队信息
	 * @param fdServicePackage
	 * @return
	 */
	List<FdServiceTeams> getFdServiceTeamsList(FdServicePackage fdServicePackage);
	/**
	 * 得到服务包下的服务时间信息
	 * @param fdServicePackage
	 * @return
	 */
	List<FdServiceTimes> getFdServiceTimesList(FdServicePackage fdServicePackage);
	/**
	 * 保存或更新用户购买服务记录
	 * @param fdUserBuyRecord
	 * @return
	 */
	Long saveOrUpdateFdUserBuyRecord(FdUserBuyRecord fdUserBuyRecord);
	/**
	 * 保存或更新家庭服务订单信息
	 * @param fdUserBuyRecord
	 * @return
	 */
	Long saveOrUpdateFdServiceOrder(FdServiceOrder fdServiceOrder);
	/**
	 * 得到团队成员列表
	 * @param fdServiceTeams
	 * @return
	 */
	List<FdServiceMember> getFdServiceMemberList(FdServiceTeams fdServiceTeams,HospitalBasicInfo hospitalBasicInfo);
	/**
	 * 得到家庭医生服务包订单信息
	 * @param id
	 * @return
	 */
	FdServiceOrder getFdServiceOrderById(Long id);
	/**
	 * 得到服务团队根据id
	 * @param id
	 * @return
	 */
	FdServiceTeams getFdServiceTeamsById(Long id);
	/**
	 * 得到服务包根据id
	 * @param id
	 * @return
	 */
	FdServicePackage getFdServicePackageById(Long id);
	/**
	 * 根据包和时间查询订单列表信息
	 * @param fdServicePackage
	 * @param fdServiceTimes
	 * @return
	 */
	List<FdServiceOrder> getFdServiceOrderList(FdServicePackage fdServicePackage,FdServiceTeams fdServiceTeams,
			FdServiceTimes fdServiceTimes,UserInfo userInfo,FdServiceOrder fdServiceOrder);
}
