package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.UserInfo;

public class UserInfoHandle extends BaseAction {

	private String mes="";
	private String action="";
	
	private long userId; //用户Id
	private String status;//用户状态
	
	private List userBabyInfo=new ArrayList();
	private List<UserInfo> allUserInfo=new ArrayList();//用户管理中，所有用户的信息
	private UserInfo userInfo; //某一个用户的详细信息
	
	private String phone="";
	private String parentName="";
	private String babyName="";
	private String startTime="";
	private String endTime="";
	
	public String execute()
	{
		if(action.equals("getUserBabyInfo"))
		{
			System.out.println("userInfoHandle.action?action=getUserBabyInfo...........");
			userBabyInfo=userInfoBo.getUserBabyInfo();
			mes="成功";
			return "success";
		}
		if(action.equals("allUserInfo"))
		{
			System.out.println("userInfoHandle.action?action=allUserInfo...........");
			allUserInfo=userInfoBo.UserInfo();
			mes="成功";
			return "success";
		}
		
		if(action.equals("update"))
		{
			System.out.println("userInfoHandle.action?action=allUserInfo...........");
			UserInfo updateUser=userInfoBo.getUserInfoById(userId);
			if(updateUser!=null)
			{
				updateUser.setUserStatus(status);
				baseBo.updateUserInfo(updateUser);
				mes="成功";
				return "success";
			}
			else
			{
				mes="查无此人";
				return "fail";
			}
		}
		
		if(action.equals("search"))
		{
			System.out.println("userInfoHandle.action?action=search...........");
			if(startTime.equals(""))
			{
				startTime="0";
			}		
			if(endTime.equals(""))
			{
				endTime="999999999";
			}
			allUserInfo=userInfoBo.searchUser(phone, parentName, babyName, startTime, endTime);
			mes="成功";
			return "success";
			
		}
		return "success";
		
		
		
	}

	public String getMes() {
		return mes;
	}

	public List getUserBabyInfo() {
		return userBabyInfo;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<UserInfo> getAllUserInfo() {
		return allUserInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
