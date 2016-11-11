package com.kybaby.action.main;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.HeightWeightHeadRecord;
import com.kybaby.domain.UserInfo;
import com.kybaby.util.CalculationMethod;
import com.kybaby.util.DateManage;
import com.opensymphony.xwork2.ActionContext;
import com.kybaby.util.CookieManage;


/**
 * @ClassName:GetUserInfo
 * @Description:用户信息相关
 * @author Hoolee
 * @date 2015年9月26日下午12:29:23
 */
public class GetUserInfo extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	//通用属性
	private String mes;//反馈给页面的提示信息
	
	//用户登录相关
	private String username;//用户名,即手机号
	private String userPassword;//用户密码
	
	private String userPhone;//反馈到前端的用户手机号码
	
	private String babySex;//宝宝性别
	private String babyMonthYear;//宝宝月龄
	
	private String recordType;//记录类型
	private String recordValue;//记录值
	
	private UserInfo usr;//反馈给前端的用户对象
	
	private List<HeightWeightHeadRecord> recordHis=new ArrayList<HeightWeightHeadRecord>();
	
	private String tips;//反馈给前端的提示信息
	private String ranges;//反馈给前端的正常范围
	
	private long recordId;//前端提供的记录的ID
	
	public String execute() throws ParseException{
		if(action.equals("login")){
			System.out.println("UserLogin is begining...");
			UserInfo user=userInfoBo.getUserInfoByPhone(username);
			if(user!=null){
				if(user.getUserStatus().equals("Y")){
					if(userInfoBo.isPasswordTrue(username, userPassword)){
						user.setIsLogin("Y");
						long userId=user.getId();
						ActionContext.getContext().getSession().put("userId",userId);
						if(user.getOpenId()==null||user.getOpenId().equals("")){
							if(ActionContext.getContext().getSession().get("openId")!=null){
								String openId=(String)ActionContext.getContext().getSession().get("openId");
								user.setOpenId(openId);
							}
						} else {
							ActionContext.getContext().getSession().put("openId",user.getOpenId());
						}
						userInfoBo.updateUser(user);
						CookieManage.pwdAndPhoneCookie(userPassword, username);
						mes="操作成功";
						return "success";
					}
					mes="密码错误";
					return "fail";
				}
				mes="登录错误";
				return "fail";
			}
			mes="未注册";
			return "fail";
		} else if(action.equals("sexAndYear")){
			System.out.println("SexAndYear is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				UserInfo usr=userInfoBo.getUserById(userId);
				if(usr!=null){
					babySex=usr.getSex();
					String babyBirthday=usr.getBirthday();
					String rightNow=CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
					babyMonthYear=String.valueOf(CalculationMethod.getMonthSpace(babyBirthday, rightNow));
					
				}
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "fail";
		} else if(action.equals("addNewRecord")){
			System.out.println("AddNewRecord is begining... ");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				userInfoBo.addNewRecord(userId, recordType, recordValue,babyMonthYear);
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "fail";
		} else if(action.equals("recordHist")){
			System.out.println("RecordHist is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				recordHis=userInfoBo.getHistRecord(userId, recordType);
				usr=userInfoBo.getUserById(userId);
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "fail";
		} else if(action.equals("getUserPhone")){
			System.out.println("GetUserPhone is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				userPhone=userInfoBo.getUserPhoneById(userId);
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "fail";
		} else if(action.equals("getUser")){
			System.out.println("GetUser is begining...");
			System.out.println("GetUserPhone is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				usr=userInfoBo.getUserById(userId);
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "fail";
		}
		/**
		 * 更新用户地址
		 */
		else if(action.equals("updateUserAddress")){
			Long userId = (Long) ActionContext.getContext().getSession().get("userId");
			if( userId==null){
				mes="未登录";
				return "fail";
			}
			UserInfo oldUser = this.userInfoBo.getUserById(userId);
			oldUser.setUserProvince(usr.getUserProvince());
			oldUser.setUserCity(usr.getUserCity());
			oldUser.setUserArea(usr.getUserArea());
			oldUser.setUserStreet(usr.getUserStreet());
			oldUser.setDetailAddress(usr.getDetailAddress());
			oldUser.setUserLat(usr.getUserLat());
			oldUser.setUserLng(usr.getUserLng());
			this.userInfoBo.updateUser(oldUser);
			mes="操作成功";
			return "success";
		}
		else if(action.equals("latest")){
			if(ActionContext.getContext().getSession().get("userId")!=null){
				String[] result=userInfoBo.getNormalRangeAndTips(babyMonthYear, babySex, recordType, recordValue);
				if(result!=null){
					tips=result[1];
					ranges=result[0];
					mes="操作成功";
					return "success";
				}
				mes="无值";
				return "fail";
			}
			mes="未登录";
			return "fail";
		} else if(action.equals("updateCord")){
			System.out.println("UpdateCord is begining... ");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				HeightWeightHeadRecord record=userInfoBo.getRecordById(recordId);
				if(record!=null){
					UserInfo user=userInfoBo.getUserById(userId);
					if(user!=null){
						String babySex=user.getSex();
						long monthYear=Long.valueOf(record.getBabyMonthYear());
						String recordStatus=userInfoBo.getRecordStatus(babySex, monthYear, recordType, recordValue);
						record.setRecordStatus(recordStatus);
						String dateStr=DateManage.getDateStr("yyyy-MM-dd");
						//record.setRecordTime(dateStr);
						if(recordType.equals("height")){//身高
							record.setHeight(recordValue);
						}else if(recordType.equals("weight")){//体重
							record.setWeight(recordValue);
						}else if(recordType.equals("head")){//头围
							record.setHeadLength(recordValue);
						}
						userInfoBo.updateSomeRecord(record);
					}
					mes="成功";
					return "success";
				}
				mes="错误";
				return "success";
			}
			mes="未登录";
			return "fail";
		}
		return "fail";
	}

	public String getMes() {
		return mes;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getBabySex() {
		return babySex;
	}

	public String getBabyMonthYear() {
		return babyMonthYear;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public void setRecordValue(String recordValue) {
		this.recordValue = recordValue;
	}

	public List<HeightWeightHeadRecord> getRecordHis() {
		return recordHis;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setBabyMonthYear(String babyMonthYear) {
		this.babyMonthYear = babyMonthYear;
	}

	public UserInfo getUsr() {
		return usr;
	}
	
	public void setUsr(UserInfo usr) {
		this.usr = usr;
	}

	public void setBabySex(String babySex) {
		this.babySex = babySex;
	}

	public String getTips() {
		return tips;
	}

	public String getRanges() {
		return ranges;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	
}
