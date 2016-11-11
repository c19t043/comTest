package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.FunctionList;

public class FunctionManage extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String action;	
	private String functionName;
	private List<FunctionList> functionList = new ArrayList<FunctionList>();
	private List<FunctionList> functionParentList = new ArrayList<FunctionList>();
	
    private String mes;
    private String roleName;
    private String functionLists;
    private Long id;
    private String functionStatus;
    
    private List allSencondfunction=new ArrayList();

	public String execute(){

		
		if(action.equals("add")){
			FunctionList function=adminBo.findFunctionByName(functionName);
			if (function!=null){
				mes="该功能已经存在";
				return "success";
			} else{
			    adminBo.addFunction(functionName);
				mes="功能添加成功";
				return "success";
			}
			
		} if(action.equals("update")) {
			adminBo.updateFunctionByFunctionId(id, functionStatus);
			mes="更新成功";
			return "success";
		} 
		
		if(action.equals("show"))
		{
			allSencondfunction=adminBo.getAllFunction();
			if(allSencondfunction!=null)
			{
				mes="成功";
				return "success";
			}
			else 
			{
				mes="失败";
				return "fail";
			}
		}
		return "success";
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}

	public List<FunctionList> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<FunctionList> functionList) {
		this.functionList = functionList;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getFunctionLists() {
		return functionLists;
	}

	public void setFunctionLists(String functionLists) {
		this.functionLists = functionLists;
	}

	public void setFunctionStatus(String functionStatus) {
		this.functionStatus = functionStatus;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<FunctionList> getFunctionParentList() {
		return functionParentList;
	}

	public void setFunctionParentList(List<FunctionList> functionParentList) {
		this.functionParentList = functionParentList;
	}

	public String getFunctionName() {
		return functionName;
	}

	public List getAllSencondfunction() {
		return allSencondfunction;
	}

}
