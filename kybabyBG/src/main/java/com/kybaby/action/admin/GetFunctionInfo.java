package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.Admin;
import com.kybaby.domain.FunctionList;
import com.kybaby.domain.FunctionParent;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:GetFunctionInfo
 * @Description:获取后台界面上的父类功能以及对应的子类功能列表
 * @author Hoolee
 * @date 2015年9月5日下午12:02:03
 */
public class GetFunctionInfo extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String mes;//反馈给前端的提示信息
	private List<FunctionParent> allFunctionParentList=new ArrayList<FunctionParent>();//反馈给前端的父类功能列表
	private List<List<FunctionList>> allFunctionList=new ArrayList<List<FunctionList>>();//反馈给前端的子类功能列表
	public String execute(){
		if(action.equals("getAllFunctionList")){
			System.out.println("GetAllFunctionList is begining...");
			Admin admin = (Admin) ActionContext.getContext().getSession().get("landUser");
			if(admin == null){
				mes="请登录";
				return "fail";
			}
			admin = this.adminBo.findAdminById(admin.getId());
			allFunctionParentList=functionBo.getAllFunctionParentList();
			List<FunctionParent> allFunctionParentList_new = new ArrayList<FunctionParent>();
			if(allFunctionParentList!=null){
				//处理父菜单
				if(admin != null && !"admin".equals(admin.getName())){
					String[] funArr = admin.getFunctionList().split(":");
					List<String> arr = Arrays.asList(funArr);
					Map<Long,FunctionParent> tempMap = new HashMap<>();
					for(FunctionParent parent : allFunctionParentList){
						List<FunctionList> someFunctionList=functionBo.getSomeFunctionListByParentId(parent.getId());
						if(someFunctionList != null){
							for(FunctionList child : someFunctionList){
								if(arr.contains(child.getId().toString())){
									tempMap.put(parent.getId(), parent);
								}
							}
						}
					}
					for (Map.Entry<Long,FunctionParent> entry : tempMap.entrySet()) {
						allFunctionParentList_new.add(entry.getValue());
					}
					this.allFunctionParentList = allFunctionParentList_new;
				}
				for(int i =0 ;i < allFunctionParentList.size();i++){
					FunctionParent someFunctionParent=allFunctionParentList.get(i);
					long functionParentId=someFunctionParent.getId();
					List<FunctionList> someFunctionList=functionBo.getSomeFunctionListByParentId(functionParentId);
					if(admin != null && !"admin".equals(admin.getName())){
						if(StringUtils.isNotEmpty(admin.getFunctionList()) && someFunctionList != null){
							String[] funArr = admin.getFunctionList().split(":");
							List<String> arr = Arrays.asList(funArr);
							List<FunctionList> someNewFunctionList=new ArrayList<>();
							for(FunctionList fun : someFunctionList){
								if(arr.contains(fun.getId().toString())){
									someNewFunctionList.add(fun);
								}
							}
							allFunctionList.add(someNewFunctionList);
						}
					}else{
						allFunctionList.add(someFunctionList);
					}
				}
				if(allFunctionList==null){
					mes="无子类";
					return "fail";
				}
			}else{
				mes="无父类";
				return "fail";
			}
			mes="操作成功";
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
	 * @param mes the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}
	/**
	 * @return the allFunctionParentList
	 */
	public List<FunctionParent> getAllFunctionParentList() {
		return allFunctionParentList;
	}
	/**
	 * @param allFunctionParentList the allFunctionParentList to set
	 */
	public void setAllFunctionParentList(List<FunctionParent> allFunctionParentList) {
		this.allFunctionParentList = allFunctionParentList;
	}
	/**
	 * @return the allFunctionList
	 */
	public List<List<FunctionList>> getAllFunctionList() {
		return allFunctionList;
	}
	/**
	 * @param allFunctionList the allFunctionList to set
	 */
	public void setAllFunctionList(List<List<FunctionList>> allFunctionList) {
		this.allFunctionList = allFunctionList;
	}
}
