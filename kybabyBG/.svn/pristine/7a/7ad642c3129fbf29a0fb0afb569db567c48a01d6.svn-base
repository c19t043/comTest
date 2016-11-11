package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorArticle;

public class DoctorArticleHandle extends BaseAction {

	 private String mes="";
	 private String action="";
	 
	 private long doctorArticleId;// 医生文章Id
	 private String articleStatus;//文章状态
	 
	 private List allDoctorArticle=new ArrayList();//所有医生文章显示按发表时间逆序排
	 private List oneDoctorArticle=new ArrayList();//某条专栏的详细信息

	 
	 
	 public String execute()
	 {
		 if(action.equals("getAllArticle"))
		 {
			 System.out.println("doctorArticleHandle.action?action=getAllArticle......");
			 allDoctorArticle=doctorArticleBo.getAllDoctorArticle();
			 mes="成功";
			 return "success";
		 }
		 
		 if(action.equals("getOneArticle"))
		 {
			 System.out.println("doctorArticleHandle.action?action=getOneArticle......");
			 oneDoctorArticle=doctorArticleBo.getOneDoctorArticleById(doctorArticleId);
			 mes="成功";
			 return "success";
			 
		 }
		 
		 if(action.equals("update"))
		 {
			 System.out.println("doctorArticleHandle.action?action=update......");
			 DoctorArticle updateDoctorArticle=doctorArticleBo.getDoctorArticleById(doctorArticleId);
			 if(updateDoctorArticle!=null)
			 {
				 updateDoctorArticle.setArticleStatus(articleStatus);
				 baseBo.updateDoctorArticle(updateDoctorArticle);
				 mes="成功";
				 return "success";
			 }
			 else
			 {
				 mes="查无此文章";
				 return "fail";
			 }
		 }
		 return "success";
	 }



	public String getMes() {
		return mes;
	}



	public List getAllDoctorArticle() {
		return allDoctorArticle;
	}



	public List getOneDoctorArticle() {
		return oneDoctorArticle;
	}



	public void setAction(String action) {
		this.action = action;
	}



	public void setDoctorArticleId(long doctorArticleId) {
		this.doctorArticleId = doctorArticleId;
	}



	public void setArticleStatus(String articleStatus) {
		this.articleStatus = articleStatus;
	}
}
