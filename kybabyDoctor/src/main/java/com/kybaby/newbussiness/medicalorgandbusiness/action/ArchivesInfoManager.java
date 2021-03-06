package com.kybaby.newbussiness.medicalorgandbusiness.action;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganOperator;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.PageBean;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;
import com.opensymphony.xwork2.ActionContext;

public class ArchivesInfoManager extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 登录人信息
	 */
	private OrganOperator organOperator;
	/**
	 * 用户身份信息列表
	 */
	private List<ArchivesInfo> archivesInfoList = new ArrayList<>();
	/**
	 * 用户身份信息
	 */
	private ArchivesInfo archivesInfo;
	/**
	 * 翻页信息
	 */
	private PageBean pageBean;
	/**
	 * 用户类型列表
	 */
	private List<UserType> userTypeList = new ArrayList<>();
	
	@Override
	public String execute() {
		organOperator = (OrganOperator)ActionContext.getContext().getSession().get("organOperator");
		if(organOperator==null){
			mes="请登录";
			return "fail";
		}
		/**
		 * 得到当前机构的用户身份信息列表
		 */
		if(action.equals("getArchivesInfoList")){
			if(archivesInfo == null){
				archivesInfo = new ArchivesInfo();
			}
			archivesInfo.setHospitalBasicInfo(organOperator.getHospitalBasicInfo());
			this.archivesInfoList = this.archivesInfoService.getArchivesInfoList(archivesInfo,pageBean,organOperator);
			System.out.println("fenye");
		}
		/**
		 * 保存或更新用户身份信息
		 */
		else if(action.equals("saveOrUpateArchivesInfo")){
			//新增数据时，检查当前机构是否已存在用户身份信息
			if(archivesInfo.getId() == null){
				ArchivesInfo ai = new ArchivesInfo();
				ai.setArchivesMobile(archivesInfo.getArchivesMobile());
				List<ArchivesInfo> archivesInfoListOld = this.archivesInfoService.getArchivesInfoList(ai,null,organOperator);
				if(archivesInfoListOld != null){
					this.mes = archivesInfo.getArchivesMobile()+
							"用户在"+organOperator.getHospitalBasicInfo().getHospitalLname()+
							"已存在";
					return "fail";
				}
			}
			archivesInfo.setHospitalBasicInfo(organOperator.getHospitalBasicInfo());
			archivesInfo.setOrganOperator(organOperator);
			this.archivesInfoService.saveOrUpateArchivesInfo(archivesInfo);
		}
		/**
		 * 得到身份信息
		 */
		else if(action.equals("getArchivesInfo")){
			this.archivesInfo = this.archivesInfoService.getArchivesInfoById(archivesInfo.getId());
		}
		/**
		 * 得到用户类型信息
		 */
		else if(action.equals("getUserTypeList")){
			this.userTypeList = this.archivesInfoService.getUserTypeList(null);
		}
		return "success";
	}
	@Override
	public String getMes() {
		return mes;
	}
	@Override
	public void setMes(String mes) {
		this.mes = mes;
	}
	public OrganOperator getOrganOperator() {
		return organOperator;
	}
	public void setOrganOperator(OrganOperator organOperator) {
		this.organOperator = organOperator;
	}
	public List<ArchivesInfo> getArchivesInfoList() {
		return archivesInfoList;
	}
	public void setArchivesInfoList(List<ArchivesInfo> archivesInfoList) {
		this.archivesInfoList = archivesInfoList;
	}
	public ArchivesInfo getArchivesInfo() {
		return archivesInfo;
	}
	public void setArchivesInfo(ArchivesInfo archivesInfo) {
		this.archivesInfo = archivesInfo;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public List<UserType> getUserTypeList() {
		return userTypeList;
	}
	public void setUserTypeList(List<UserType> userTypeList) {
		this.userTypeList = userTypeList;
	}
}
