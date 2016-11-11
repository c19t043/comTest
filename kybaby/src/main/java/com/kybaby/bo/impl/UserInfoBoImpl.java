package com.kybaby.bo.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.kybaby.bo.UserInfoBo;
import com.kybaby.dao.UserInfoDao;
import com.kybaby.domain.BabyBasicData;
import com.kybaby.domain.BabyBasicData2;
import com.kybaby.domain.CaseClip;
import com.kybaby.domain.HeightWeightHeadRecord;
import com.kybaby.domain.NormalData;
import com.kybaby.domain.UserInfo;
import com.kybaby.util.EncryptUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:UserInfoBoImpl
 * @Description:用户事物管理实现
 * @author Hoolee
 * @date 2015年9月26日上午10:19:35
 */
public class UserInfoBoImpl implements UserInfoBo {

	UserInfoDao userInfoDao;

	public UserInfo getUserById(long userId) {
		return userInfoDao.getUserById(userId);
	}

	public void updateUser(UserInfo user) {
		userInfoDao.updateUser(user);
	}

	public UserInfo isRegist(String phoneNumber) {
		return userInfoDao.getUserInfoByPhone(phoneNumber);
	}

	public void updateUserPassword(String phone, String newPassword) {
		UserInfo user=userInfoDao.getUserInfoByPhone(phone);
		if(user!=null){
			user.setPassword(EncryptUtil.getMD5Str(newPassword));
			userInfoDao.updateUser(user);
		}
	}

	public void userRegist(String openId, String password, String birthday,
			String sex, String phone, String userLng, String userLat,
			String userProvince, String userCity, String userArea,
			String userStreet, String detailAddress, String babyName) {
		UserInfo user=new UserInfo();
		user.setOpenId(openId);
		user.setPassword(EncryptUtil.getMD5Str(password));
		user.setBirthday(birthday);
		user.setSex(sex);
		user.setPhone(phone);
		user.setUserLng(userLng);
		user.setUserLat(userLat);
		user.setUserProvince(userProvince);
		user.setUserCity(userCity);
		user.setUserArea(userArea);
		user.setUserStreet(userStreet);
		user.setDetailAddress(detailAddress);
		user.setBabyName(babyName);
		user.setAccountBalance(0D);
		user.setAccountPoints(0L);
		user.setUserStatus("Y");
		user.setUseAppTimes(1L);
		user.setIsLogin("Y");
		user.setTotalConsume(0D);
		//add by HooLee 2015年10月26日16:23:22 避免在订单确认页面显示宝宝的家长姓名为null
		user.setParentName("");
		user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
		userInfoDao.addNewUserInfo(user);
	}

	public boolean isCanbeUsed(String phoneNumber) {
		UserInfo user=userInfoDao.getUserInfoByPhone(phoneNumber);
		if(user!=null&&user.getUserStatus().equals("Y")){
			return true;
		}
		return false;
	}

	public boolean isPasswordTrue(String phoneNumber, String password) {
		UserInfo user=userInfoDao.getUserInfoByPhone(phoneNumber);
		if(user!=null&&user.getPassword().equals(EncryptUtil.getMD5Str(password))){
			return true;
		}
		return false;
	}

	public boolean userPhoneIsTrue(long userId, String userPhone) {
		UserInfo usr=userInfoDao.getUserById(userId);
		if(usr!=null&&usr.getPhone().equals(userPhone)){
			return true;
		}
		return false;
	}

	public void updateUserPhone(long userId, String userPhone) {
		UserInfo usr=userInfoDao.getUserById(userId);
		if(usr!=null){
			usr.setPhone(userPhone);
			userInfoDao.updateUser(usr);
		}
	}

	public String getUserPhoneById(long userId) {
		UserInfo usr=userInfoDao.getUserById(userId);
		if(usr!=null){
			return usr.getPhone();
		}
		return null;
	}

	public void updateUserSomeInfo(long userId, String nickname,
			String babyName, String birthday, String sex, String userLng,
			String userLat, String userProvince, String userCity,
			String userArea, String userStreet, String detailAddress) {
		UserInfo usr=userInfoDao.getUserById(userId);
		usr.setParentName(nickname);
		usr.setBabyName(babyName);
		usr.setBirthday(birthday);
		usr.setSex(sex);
		usr.setUserLng(userLng);
		usr.setUserLat(userLat);
		usr.setUserProvince(userProvince);
		usr.setUserCity(userCity);
		usr.setUserArea(userArea);
		usr.setUserStreet(userStreet);
		usr.setDetailAddress(detailAddress);
		userInfoDao.updateUser(usr);
	}

	public UserInfo getUserInfoByPhone(String phone) {
		return userInfoDao.getUserInfoByPhone(phone);
	}

	public UserInfo getUserInfoByOpenId(String openId) {
		return userInfoDao.getUserInfoByOpenId(openId);
	}

	public BabyBasicData getBabyBasicDataByUserId(long userId) {
		return userInfoDao.getBabyBasicDataByUserId(userId);
	}

	public void addNewRecord(long userId,String recordType, String recordValue, String babyMonthYear) {
		HeightWeightHeadRecord record=new HeightWeightHeadRecord();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date rightNow=new Date(System.currentTimeMillis());
		String dateNow=sdf.format(rightNow);
		record.setRecordTime(dateNow);
		record.setUserId(userId);
		record.setBabyMonthYear(babyMonthYear);
		UserInfo user=this.getUserById(userId);
		String babySex=user.getSex();
		String recordStatus=this.getRecordStatus(babySex,Long.valueOf(babyMonthYear), recordType, recordValue);
		record.setRecordStatus(recordStatus);
		if(recordType.equals("height")){
			record.setHeadLength("0");
			record.setWeight("0");
			record.setHeight(recordValue);
		}else if(recordType.equals("weight")){
			record.setWeight(recordValue);
			record.setHeight("0");
			record.setHeadLength("0");
		}else if(recordType.equals("head")){
			record.setHeadLength(recordValue);
			record.setWeight("0");
			record.setHeight("0");
		}
		userInfoDao.addNewRecord(record);
	}

	public List<HeightWeightHeadRecord> getHistRecord(long userId,String recordType) {
		List<HeightWeightHeadRecord> recordList=null;
		if(recordType.equals("height")){
			recordList=userInfoDao.getHeightRecordList(userId);
		}else if(recordType.equals("weight")){
			recordList=userInfoDao.getweightRecordList(userId);
		}else if(recordType.equals("head")){
			recordList=userInfoDao.getHeadRecordList(userId);
		}
		return recordList;
	}

	public void addNewCaseClip(CaseClip clip) {
		userInfoDao.addNewCaseClip(clip);
	}


	public List<CaseClip> getHistCaseClip(long userId) {
		return userInfoDao.getHistCaseClip(userId);
	}

	public String[] getNormalRangeAndTips(String babyMonthYear, String babySex,String recordType, String recordValue) {
		NormalData data=userInfoDao.getNormalDataByBabySexAndMonth(babySex, Long.valueOf(babyMonthYear));
		if(data!=null){
			String[] result=new String[2];
			String tips="";//提示信息
			String range="";//正常的范围
			String normalRange="";
			String tip="";
			if(recordType.equals("height")){//身高
				normalRange=data.getNormalHeight();
				tip=data.getHeightTips();
			}else if(recordType.equals("weight")){//体重
				normalRange=data.getNormalWeight();
				tip=data.getWeightTips();
			}else if(recordType.equals("head")){//头围
				normalRange=data.getNormalHead();
				tip=data.getHeadTips();
			}
			String[] rangeArr=normalRange.split("::");
			String[] tipArr=tip.split("::");
			range=rangeArr[0]+"~"+rangeArr[1];
			if(Double.valueOf(recordValue)>Double.valueOf(rangeArr[1])){
				tips=tipArr[2];
			}else if(Double.valueOf(recordValue)<Double.valueOf(rangeArr[0])){
				tips=tipArr[0];
			}else{
				tips=tipArr[1];
			}
			result[0]=range;
			result[1]=tips;
			return result;
		}
		return null;
	}

	public HeightWeightHeadRecord getRecordById(long recordId) {
		return userInfoDao.getRecordById(recordId);
	}

	public String getRecordStatus(String babySex, long monthYear,String recordType, String recordValue) {
		NormalData data=userInfoDao.getNormalDataByBabySexAndMonth(babySex,monthYear);
		String resultString="";
		if(data!=null){
			String normalRange="";
			double value=Double.valueOf(recordValue);
			if(recordType.equals("height")){//身高
				normalRange=data.getNormalHeight();
				String[] rangeArr=normalRange.split("::");
				double minNum=Double.valueOf(rangeArr[0]);
				double maxNum=Double.valueOf(rangeArr[1]);
				if(value<minNum){
					resultString="偏矮";
				}else if(value>maxNum){
					resultString="偏高";
				}else{
					resultString="正常";
				}
			}else if(recordType.equals("weight")){//体重
				normalRange=data.getNormalWeight();
				String[] rangeArr=normalRange.split("::");
				double minNum=Double.valueOf(rangeArr[0]);
				double maxNum=Double.valueOf(rangeArr[1]);
				if(value<minNum){
					resultString="偏轻";
				}else if(value>maxNum){
					resultString="偏重";
				}else{
					resultString="正常";
				}
			}else if(recordType.equals("head")){//头围
				normalRange=data.getNormalHead();
				String[] rangeArr=normalRange.split("::");
				double minNum=Double.valueOf(rangeArr[0]);
				double maxNum=Double.valueOf(rangeArr[1]);
				if(value<minNum){
					resultString="偏小";
				}else if(value>maxNum){
					resultString="偏大";
				}else{
					resultString="正常";
				}
			}
		}
		return resultString;
	}

	public void updateSomeRecord(HeightWeightHeadRecord record) {
		userInfoDao.updateSomeRecord(record);
	}

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	@Override
	public List<BabyBasicData2> getBabyBasicData2ListByUserId(Long userId) {
		return userInfoDao.getBabyBasicData2ListByUserId(userId);
	}

	@Override
	public Long saveOrUpdateBabyBasicData(BabyBasicData2 babyBasicData) {
		return userInfoDao.saveOrUpdateBabyBasicData(babyBasicData);
	}

	@Override
	public Long getLoginUserInfoId(){
		if(ActionContext.getContext().getSession().get("userId")!=null){
			return (Long) ActionContext.getContext().getSession().get("userId");
		}else{
			System.out.println("get userId  from Cookie...");
			HttpServletRequest request = ServletActionContext.getRequest();
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			System.out.println("request.getCookies()..." + cookies);
			int j = 0;
			String Original_CookiesPwd = null;//cookie中的密码
			String Original_CookiesPhone=null;//cookie中的电话号码
			javax.servlet.http.Cookie c = null;
			
			if(cookies!=null){//是否存在cookie
				for(int i =0;i<cookies.length;i++){
					c=cookies[i];
					if(c!=null){
						if (c.getName().equalsIgnoreCase("userpwd")&& c.getValue() != null) {
							Original_CookiesPwd = c.getValue();
						}
						if(c.getName().equalsIgnoreCase("userphone")&&c.getValue()!=null){
							Original_CookiesPhone=c.getValue();
						}
					}
					c=null;
				}
			}
			System.out.println("Original_CookiesPhone=====" + Original_CookiesPhone);
			if(Original_CookiesPwd!=null&&Original_CookiesPhone!=null){
				UserInfo user=this.getUserInfoByPhone(Original_CookiesPhone);
				if(user!=null&&Original_CookiesPwd.equals(user.getPassword())){
					user.setIsLogin("Y");
					this.updateUser(user);
					long userId=user.getId();
					System.out.println(" from Cookie userId==..."+userId);
					ActionContext.getContext().getSession().put("userId",userId);
					return userId;
				}
			}
		}
		return null;
	}

}
