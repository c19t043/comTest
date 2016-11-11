package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.SymptomTag;

public class SymptomTagHandle extends BaseAction {

	private String mes="";
	private String action="";
	private String addName=""; //新增症状标签名字
	private String status="";  //症状标签状态(增加与修改共用)
	private Long   updateId;   //更新症状Id
	private String updateName="";// 更新的症状标签名字
	
	private List SymptomTagIdAndName=new ArrayList();
	
	List allSymptomTag =new ArrayList(); //所有症状标签
	
	
	public String execute()
	{
		if(action.equals("all"))
		{
			System.out.println("symptomTagHandle.action?action=all........");
			allSymptomTag=symptomTagBo.getAllSymptomTag();
			mes="成功";
			return "success";
		}
		if(action.equals("add"))
		{
			System.out.println("symptomTagHandle.action?action=add........");
			SymptomTag st=symptomTagBo.getSymptomTagByName(addName);
			if(st==null)
			{
				SymptomTag addSt=new SymptomTag();
				addSt.setSymptomName(addName);
				addSt.setSymptomStatus(status);
				addSt.setHitCount(0L);
				baseBo.saveSymptomTag(addSt);
				mes="添加成功";
				return "success";
			}
			else
			{
				mes="已有此标签";
				return "fail";
			}
		}
		
		if(action.equals("update"))
		{
			System.out.println("symptomTagHandle.action?action=update........");
			SymptomTag updateSt=symptomTagBo.getSymptomTagById(updateId);
		    SymptomTag st=symptomTagBo.getSymptomTagByName(updateName);
			if(st==null||updateSt.getSymptomName().equals(updateName))
			{
				updateSt.setSymptomName(updateName);
				updateSt.setSymptomStatus(status);
				baseBo.updateSymptomTag(updateSt);
				mes="更新成功";
				return "success";
			}
			else
			{
				mes="已有此标签";
				return "fail";
			}
		}
		
		if(action.equals("getSymptomTagIdAndName"))
		{
			SymptomTagIdAndName=symptomTagBo.getSymptomTagIdAndName();
			mes="成功";
			return "success";
		}
		
		
		
		return "success";
	}


	public String getMes() {
		return mes;
	}


	public List getAllSymptomTag() {
		return allSymptomTag;
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


	public void setAction(String action) {
		this.action = action;
	}


	public List getSymptomTagIdAndName() {
		return SymptomTagIdAndName;
	}
	
}
