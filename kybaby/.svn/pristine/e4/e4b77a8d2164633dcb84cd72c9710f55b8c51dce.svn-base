package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.BabyBasicData;
import com.kybaby.domain.BabyBasicData2;
import com.kybaby.domain.CaseClip;
import com.kybaby.domain.HeightWeightHeadRecord;
import com.kybaby.domain.UserInfo;

/**
 * 
 * @ClassName:UserInfoBo
 * @Description:用户事物管理接口
 * @author Hoolee
 * @date 2015年9月14日下午11:24:40
 */
public interface UserInfoBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription: 通过用户的ID获取用户实例
	 * @data: 2015年9月14日下午11:25:19
	 * @param userId 用户的ID
	 * @return 用户的实例
	 */
	UserInfo getUserById(long userId);
	
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
	 * @Discription:电话号码是否已经注册
	 * @data: 2015年9月23日上午12:26:34
	 * @param phoneNumber 电话号码
	 * @return 电话号码的注册状态
	 */
	UserInfo isRegist(String phoneNumber);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:电话号码对应的密码是否正确
	 * @data: 2015年9月23日上午12:32:09
	 * @param phoneNumber 电话号码
	 * @param password 密码(未加密)
	 * @return 电话号码对应的密码是否正确
	 */
	boolean isPasswordTrue(String phoneNumber,String password);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新用户的密码
	 * @data: 2015年9月23日上午10:10:35
	 * @param phone 电话号码
	 * @param newPassword 用户重置的密码（未使用MD5加密）
	 */
	void updateUserPassword(String phone,String newPassword);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:完成用户的注册
	 * @data: 2015年9月23日上午10:57:58
	 * @param openId 用户的openId
	 * @param password 密码（未使用MD5加密）
	 * @param birthday 宝宝生日
	 * @param sex 宝宝性别
	 * @param phone 电话号码
	 * @param userLng 经度
	 * @param userLat 纬度
	 * @param userProvince 省
	 * @param userCity 市
	 * @param userArea 区
	 * @param userStreet 街道
	 * @param detailAddress 详细地址
	 * @param babyName 宝宝姓名
	 */
	void userRegist(String openId,String password,String birthday,String sex,String phone,String userLng,String userLat,String userProvince,String userCity,String userArea,String userStreet,String detailAddress,String babyName);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:验证用户的手机号是否正确
	 * @data: 2015年9月23日下午3:15:57
	 * @param userId 用户的ID
	 * @param userPhone 用户的电话号码
	 * @return 用户的手机号是否正确
	 */
	boolean userPhoneIsTrue(long userId,String userPhone);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新用户的手机号码
	 * @data: 2015年9月23日下午3:24:07
	 * @param userId 用户的ID
	 * @param userPhone 用户的电话号码
	 */
	void updateUserPhone(long userId,String userPhone);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过用户的ID获取到用户的手机号
	 * @data: 2015年9月23日下午3:41:39
	 * @param userId 用户的ID
	 * @return 用户的手机号
	 */
	String getUserPhoneById(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:
	 * @data: 2015年9月23日下午4:59:50
	 * @param userId 用户ID
	 * @param nickname 父母姓名
	 * @param babyName 宝宝姓名
	 * @param birthday 生日
	 * @param sex 性别
	 * @param userLng 经度
	 * @param userLat 纬度
	 * @param userProvince 省
	 * @param userCity 市
	 * @param userArea 区
	 * @param userStreet 街道
	 * @param detailAddress 详细地址
	 */
	void updateUserSomeInfo(long userId,String nickname ,String babyName ,String birthday ,String sex ,String userLng ,String userLat ,String userProvince ,String userCity ,String userArea ,String userStreet ,String detailAddress );
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过用户的电话号码查询到用户
	 * @data: 2015年9月25日上午10:31:41
	 * @param phone 用户的电话号码
	 * @return 用户的实例
	 */
	UserInfo getUserInfoByPhone(String phone);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:查询该号码的用户账户是否已经存在并且不是黑名单用户
	 * @data: 2015年9月25日下午1:47:59
	 * @param phoneNumber 用户的电话号码
	 * @return 用户账户是否已经存在并且不是黑名单用户
	 */
	boolean isCanbeUsed(String phoneNumber);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过openId获取到用户的实例
	 * @data: 2015年9月29日上午9:32:45
	 * @param openId 用户的openId
	 * @return 用户的实例
	 */
	UserInfo getUserInfoByOpenId(String openId);
	
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
	 * @param recordType 增加的数据类型 
	 * @param recordValue 增加的数据类型的值
	 */
	void addNewRecord(long userId,String recordType,String recordValue, String babyMothYear);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过用户的ID和需要获取记录的类型，获取到记录列表
	 * @data: 2015年10月14日上午12:41:10
	 * @param userId 用户的ID
	 * @param recordType 记录的类型
	 * @return 记录的列表
	 */
	List<HeightWeightHeadRecord> getHistRecord(long userId,String recordType );
	
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
	 * @Discription:通过宝宝的月龄、性别、记录类型、记录的值获取到当前记录的正常范围以及当前记录的提示信息
	 * @data: 2015年10月20日下午6:01:03
	 * @param babyMonthYear 宝宝的月龄
	 * @param babySex 宝宝的性别
	 * @param recordType 记录的类型
	 * @param recordValue 记录的值
	 * @return
	 */
	String[] getNormalRangeAndTips(String babyMonthYear,String babySex,String recordType,String recordValue );
	
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
	 * @Discription:通过宝宝的性别和月龄以及记录的类型和值判断记录的状态
	 * @data: 2015年12月8日下午2:50:33
	 * @param babySex 宝宝的性别
	 * @param monthYear 宝宝的月龄
	 * @param recordType 记录的类型
	 * @param recordValue 记录的值
	 * @return 该记录的状态（偏高、偏低）
	 */
	String getRecordStatus(String babySex,long monthYear,String recordType,String recordValue);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新记录实例
	 * @data: 2015年12月8日下午3:26:37
	 * @param record 待更新的记录实例
	 */
	void updateSomeRecord(HeightWeightHeadRecord record);
	/**
	 * 获取登录用户信息,用于判断是否需要登录
	 * @return null 跳转登录页面
	 */
	public Long getLoginUserInfoId();
}

	