package com.kybaby.action.main;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.CaseClip;
import com.kybaby.domain.SymptomTag;
import com.kybaby.domain.UserInfo;
import com.kybaby.util.CalculationMethod;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:GetAcheInf
 * @Description:病历夹相关的
 * @author Hoolee
 * @date 2015年10月14日上午1:04:46
 */
public class GetAcheInfo extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private String userImage;
	private String babyName;
	private String babySex;
	private String babyBirthday;
	private String babyMonthYear;
	
	List<CaseClip> histCaseClipList=new ArrayList<CaseClip>();//历史病历夹实例列表
	List<List<SymptomTag>> histTagList=new ArrayList<List<SymptomTag>>();//病历夹中症状标签的名称列表
	
	public String execute() throws ParseException{
		if(action.equals("getHis")){
			System.out.println("GetHis is begining...");
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
				histCaseClipList=userInfoBo.getHistCaseClip(userId);
				if(histCaseClipList!=null){
					for(int i =0;i<histCaseClipList.size();i++){
						String tagIds=histCaseClipList.get(i).getSymptomTagIds();
						List<SymptomTag> list=symptomTagBo.getSymptomTagInstanceList(tagIds);
						histTagList.add(list);
					}
					mes="操作成功";
					return "success";
				}
				mes="无病历夹";
				return "success";
			}
			mes="未登录";
			return "success";
		}
		return "fail";
	}


	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}


	/**
	 * @return the userImage
	 */
	public String getUserImage() {
		return userImage;
	}


	/**
	 * @return the babyName
	 */
	public String getBabyName() {
		return babyName;
	}


	/**
	 * @return the babySex
	 */
	public String getBabySex() {
		return babySex;
	}


	/**
	 * @return the babyBirthday
	 */
	public String getBabyBirthday() {
		return babyBirthday;
	}


	/**
	 * @return the babyMonthYear
	 */
	public String getBabyMonthYear() {
		return babyMonthYear;
	}


	/**
	 * @return the histCaseClipList
	 */
	public List<CaseClip> getHistCaseClipList() {
		return histCaseClipList;
	}


	/**
	 * @return the histTagList
	 */
	public List<List<SymptomTag>> getHistTagList() {
		return histTagList;
	}
	
}
