package com.kybaby.action.main;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.SymptomTag;

/**
 * @ClassName:GetSymptomTagInfo
 * @Description:症状标签相关的
 * @author Hoolee
 * @date 2015年10月14日上午1:45:11
 */
public class GetSymptomTagInfo extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private List<SymptomTag> allSymptomTag=new ArrayList<SymptomTag>();
	
	public String execute(){
		if(action.equals("allTrue")){
			System.out.println("AllTrue is begining...");
			allSymptomTag=symptomTagBo.getAllSymptomTag();
			mes="操作成功";
			return "success";
		}
		return "fail";
	}
	
	public String getMes() {
		return mes;
	}
	public List<SymptomTag> getAllSymptomTag() {
		return allSymptomTag;
	}
	
	
	
}
