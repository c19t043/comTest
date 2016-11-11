package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.HealthPath;

public class HealthPathHandle extends BaseAction {

	 private String mes="";
	 private String action="";
	 
	 private String name;//健康路径名字
	 private String status;//健康路径状态
	 private long updateId;//修改健康路径的名字
	 private String comments;//健康路径内容
	 private String minAge;
	 private String maxAge;
	 private String sex;
	 private String isJudgeAge;
	 private String isJudgeSex;
	 
	 private List allHealthPath=new ArrayList();
     
	 public String execute()
	 {
		 if(action.equals("all"))
		 {
			 System.out.println("healthPathHandle.action?action=all......");
			 allHealthPath=healthPathBo.getAllHealthPath();
			 mes="成功";
			 return "success";
		 }
		 
		 if(action.equals("add"))
		 {
			 System.out.println("healthPathHandle.action?action=add......");
			 HealthPath hP=healthPathBo.getHealthPathByName(name);
			 hP=null;
			 if(hP==null)
			 {
				 HealthPath addHP=new  HealthPath("",name,comments,status);
				 addHP.setSex(sex);
				 addHP.setMinAge(minAge);
				 addHP.setMaxAge(maxAge);
				 addHP.setIsJudgeAge(isJudgeAge);
				 addHP.setIsJudgeSex(isJudgeSex);
				 baseBo.saveHealthPath(addHP);
				 mes="成功";
				 return "success";
			 }
			 else
			 {
				 mes="名字重复";
				 return "fail";
			 }
		 }
		 if(action.equals("update"))
		 {
			 System.out.println("healthPathHandle.action?action=update......");
			 HealthPath hP=healthPathBo.getHealthPathByName(name);
			 HealthPath updateHP=healthPathBo.getHealthPathById(updateId);
			 if(updateHP!=null)
			 {
				 if(hP==null||updateHP.getHealthPathName().equals(name))
			 {
				 updateHP.setHealthPathName(name);
				 updateHP.setHealthPathStatus(status);
				 updateHP.setComments(comments);
				 updateHP.setSex(sex);
				 updateHP.setMinAge(minAge);
				 updateHP.setMaxAge(maxAge);
				 updateHP.setIsJudgeAge(isJudgeAge);
				 updateHP.setIsJudgeSex(isJudgeSex);
				 baseBo.updateHealthPath(updateHP);
				 mes="成功";
				 return "success";
				 
			 }
			 else
			 {
				 mes="名字重复";
				 return "fail";
			 }
		     }
			 else
			 {
				 mes="查无此要更改健康路径";
				 return "fail";
			 }
		 }
		 
		 return "success";
	 }

	public String getMes() {
		return mes;
	}

	public List getAllHealthPath() {
		return allHealthPath;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUpdateId(long updateId) {
		this.updateId = updateId;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setMinAge(String minAge) {
		this.minAge = minAge;
	}

	public void setMaxAge(String maxAge) {
		this.maxAge = maxAge;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setIsJudgeAge(String isJudgeAge) {
		this.isJudgeAge = isJudgeAge;
	}

	public void setIsJudgeSex(String isJudgeSex) {
		this.isJudgeSex = isJudgeSex;
	}
}
