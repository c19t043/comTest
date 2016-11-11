package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorGoodField;
import com.kybaby.domain.Major;

public class MajorHandle extends BaseAction {

	private String mes="";
	private String action="";
	
	private String name;   //专业方向名字
	private String status; //专业方向的状态
	private String doctorType; //医生类型
	private long updateId; //修改专业方向名字Id
	
	private List allMajor=new ArrayList();//显示所有的专业方向
	/**
	 * 擅长领域集合
	 */
	private List<DoctorGoodField> doctorGoodFieldList = new ArrayList();
	/**
	 * 擅长领域
	 */
	private DoctorGoodField doctorGoodField;
	
	
	public String execute()
	{
		if(action.equals("all"))
		{
			System.out.println("majorHandle.action?action=all........");
			allMajor=majorBo.getAllMajor();
			mes="成功";
			return "success";
		}
		
		else if(action.equals("add"))
		{
			System.out.println("majorHandle.action?action=add........");
			Major mj=majorBo.getMajorByName(name);
			if(mj==null)
			{
				Major addMj=new Major();
				addMj.setMajor(name);
				addMj.setMajorStatus(status);
				addMj.setDoctorType(doctorType);
				baseBo.saveMajor(addMj);
				mes="添加成功";
				return "success";
			}
			else
			{
				mes="已存在该专业方向";
				return "fail";
			}
		}
		
		else if(action.equals("update"))
		{
			System.out.println("majorHandle.action?action=update........");
			Major mj=majorBo.getMajorByName(name);
			Major updateMj=majorBo.getMajorById(updateId);
			if(mj==null||updateMj.getMajor().equals(name))
			{
				updateMj.setMajor(name);
				updateMj.setMajorStatus(status);
				updateMj.setDoctorType(doctorType);
				baseBo.updateMajor(updateMj);
				mes="更新成功";
				return "success";
			}
			else
			{
				mes="已存在该专业方向";
				return "fail";
			}
		}
		/**
		 * 擅长领域列表
		 */
		else if(action.equals("getAllGoodField")){
			this.doctorGoodFieldList = this.majorBo.getAllGoodField(null);
			mes="成功";
		}
		/**
		 *	保存或更新擅长领域
		 */
		else if(action.equals("saveOrupdateGoodField")){
			this.majorBo.saveOrupdateGoodField(doctorGoodField);
			return "to_doctorGoodField_list";
		}
		
		return "success";
	}


	public String getMes() {
		return mes;
	}


	public List getAllMajor() {
		return allMajor;
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


	public List<DoctorGoodField> getDoctorGoodFieldList() {
		return doctorGoodFieldList;
	}


	public void setDoctorGoodFieldList(List<DoctorGoodField> doctorGoodFieldList) {
		this.doctorGoodFieldList = doctorGoodFieldList;
	}


	public DoctorGoodField getDoctorGoodField() {
		return doctorGoodField;
	}


	public void setDoctorGoodField(DoctorGoodField doctorGoodField) {
		this.doctorGoodField = doctorGoodField;
	}


	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
	}
}
