package com.kybaby.action.main;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.GrowthRecord;
import com.kybaby.domain.UserInfo;
import com.kybaby.util.CalculationMethod;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:GetGrowthRecordInfo
 * @Description:获取成长记录数据
 * @author Hoolee
 * @date 2015年10月13日下午10:42:46
 */
public class GetGrowthRecordInfo extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private String userImage;
	private String babyName;
	private String babySex;
	private String babyBirthday;
	private String babyMonthYear;

	private List<GrowthRecord> growthRecordList=new ArrayList<GrowthRecord>();

	public String execute() throws ParseException{
		if(action.equals("getGrowthRecord")){
			System.out.println("GetGrowthRecord is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				UserInfo usr=userInfoBo.getUserById(userId);
				if(usr!=null){
					userImage=usr.getUserImage();
					babyName=usr.getBabyName();
					babySex=usr.getSex();
					babyBirthday=usr.getBirthday();
					String rightNow=CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
					babyMonthYear=String.valueOf(CalculationMethod.getMonthSpace(babyBirthday, rightNow));
				}
				growthRecordList=growthRecordBo.getGrowthRecordByUserId(userId);
				mes="操作成功";
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

	public String getUserImage() {
		return userImage;
	}

	public String getBabyName() {
		return babyName;
	}

	public String getBabySex() {
		return babySex;
	}

	public String getBabyBirthday() {
		return babyBirthday;
	}

	public String getBabyMonthYear() {
		return babyMonthYear;
	}

	public List<GrowthRecord> getGrowthRecordList() {
		return growthRecordList;
	}

}
