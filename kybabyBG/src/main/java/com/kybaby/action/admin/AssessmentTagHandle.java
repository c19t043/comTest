package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.AssessmentTag;

public class AssessmentTagHandle extends BaseAction {

	private String mes="";
	
	private String addName=""; //新增评价标签名字
	private String status="";  //评价标签状态(增加与修改共用)
	private Long   updateId;   //更新评价Id
	private String updateName="";// 更新的评价标签名字
	private String comments="";  //质量指控类标签标志(service,duty,onTime)
	
	private List allAssessmentTag=new ArrayList();  //所有评价标签集合
	
	public String execute()
	{
		if(action.equals("all"))
		{
			System.out.println("assessmentTagHandle.action.action?action=all..........");
			allAssessmentTag=assessmentTagBo.getAllAssessmentTag();
			if(allAssessmentTag==null)
			{
				mes="没有评价标签";
				return "fail";
			}
			else 
			{   
				mes="成功";
				return "success";
			}
		}
		
		
		if(action.equals("add"))
		{
			System.out.println("assessmentTagHandle.action.action?action=add..........");
			AssessmentTag at=assessmentTagBo.getAssessmentTagByName(addName);
			if(at==null)
			{
				AssessmentTag addAt=new AssessmentTag();
				addAt.setTagName(addName);
				addAt.setTagStatus(status);
				addAt.setTagType("社交评价标签");
				addAt.setHitCount(0L);
				baseBo.saveAssessmentTag(addAt);
				mes="添加成功";
				return "success";
			}
			else
			{
				mes="已有此评价标签";
				return "fail";
			}
		}
		
		if(action.equals("update"))
		{
			System.out.println("assessmentTagHandle.action.action?action=update..........");
			AssessmentTag updateAt=assessmentTagBo.getAssessmentTagById(updateId);
			AssessmentTag at=assessmentTagBo.getAssessmentTagByName(updateName);
			if(at==null||updateAt.getTagName().equals(updateName))
			{
				updateAt.setTagName(updateName);
				updateAt.setTagStatus(status);
				baseBo.updateAssessmentTag(updateAt);
				mes="更新成功";
				return "success";
			}
			else
			{
				mes="已有此评价标签";
				return "fail";
			}
			
		}
		
		return "success";
	}

	public String getMes() {
		return mes;
	}

	public List getAllAssessmentTag() {
		return allAssessmentTag;
	}


	public void setAddName(String addName) {
		this.addName = addName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
