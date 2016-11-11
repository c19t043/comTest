package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.BabyBasicData;
import com.kybaby.domain.BabyBasicData2;
import com.kybaby.domain.CaseClip;
import com.kybaby.domain.HeightWeightHeadRecord;
import com.kybaby.domain.NormalData;
import com.kybaby.domain.UserInfo;

/**
 * @ClassName:UserInfoDao
 * @Description:用户数据管理接口
 * @author Hoolee
 * @date 2015年9月26日上午10:18:16
 */
public interface UserInfoDao {
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过用户的电话号码查询到用户
	 * @data: 2015年9月26日12:39:34
	 * @param phone 用户的电话号码
	 * @return 用户的实例
	 */
	UserInfo getUserInfoByPhone(String phone);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription: 更新用户的实例
	 * @data: 2015年9月14日下午11:46:18
	 * @param user 用户的实例
	 */
	void updateUser(UserInfo user);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:添加新的用户
	 * @data: 2015年9月27日上午11:55:20
	 * @param user 用户实例
	 */
	void addNewUserInfo(UserInfo user);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过openId获取到用户的实例
	 * @data: 2015年9月29日09:33:48
	 * @param openId 用户的openId
	 * @return 用户的实例
	 */
	UserInfo getUserInfoByOpenId(String openId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription: 通过用户的ID获取用户实例
	 * @data: 2015年9月29日10:51:00
	 * @param userId 用户的ID
	 * @return 用户的实例
	 */
	UserInfo getUserById(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:
	 * @data: 2015年10月6日上午11:40:10
	 * @param userId
	 * @return
	 */
	List getUserLngLatById(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过用户的ID获取到用户宝宝的基础信息
	 * @data: 2015年10月13日下午3:42:40
	 * @param userId 用户的ID
	 * @return 用户宝宝基础信息的实例
	 */
	BabyBasicData getBabyBasicDataByUserId(long userId);
	/**
	 * 根据用户id得到宝宝健康档案
	 * @param userId
	 * @return
	 */
	List<BabyBasicData2> getBabyBasicData2ListByUserId(Long userId);
	/**
	 * 保存或更新宝宝健康档案信息
	 * @param babyBasicData
	 * @return
	 */
	Long saveOrUpdateBabyBasicData(BabyBasicData2 babyBasicData);
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加一条新的数据记录
	 * @data: 2015年10月14日上午12:22:36
	 * @param 插入数据的实例
	 */
	void addNewRecord(HeightWeightHeadRecord eightWeightHeadRecord);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:
	 * @data: 2015年10月14日上午12:44:55
	 * @param userId
	 * @return
	 */
	List<HeightWeightHeadRecord> getHeightRecordList(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:
	 * @data: 2015年10月14日上午12:44:55
	 * @param userId
	 * @return
	 */
	List<HeightWeightHeadRecord> getHeadRecordList(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:
	 * @data: 2015年10月14日上午12:44:55
	 * @param userId
	 * @return
	 */
	List<HeightWeightHeadRecord> getweightRecordList(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过用户的ID获取到用户的历史病历夹
	 * @data: 2015年10月14日上午1:18:28
	 * @param userId 用户的ID
	 * @return 用户的历史病历夹
	 */
	List<CaseClip> getHistCaseClip(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:添加新的病历夹
	 * @data: 2015年10月14日上午2:19:31
	 * @param clip 病历夹实例
	 */
	void addNewCaseClip(CaseClip clip);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过宝宝的性别和月龄获取到正常数据的实例
	 * @data: 2015年10月20日下午6:10:16
	 * @param sex 宝宝的性别
	 * @param monthYear 宝宝的月龄
	 * @return 正常数据的实例
	 */
	NormalData getNormalDataByBabySexAndMonth(String sex,long monthYear);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过记录的ID获取到记录的实例
	 * @data: 2015年12月8日下午2:24:56
	 * @param recordId 身高头围体重的记录ID
	 * @return 身高头围体重记录的实例
	 */
	HeightWeightHeadRecord getRecordById(long recordId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新记录实例
	 * @data: 2015年12月8日下午3:26:37
	 * @param record 待更新的记录实例
	 */
	void updateSomeRecord(HeightWeightHeadRecord record);
}
