package com.java.familydoctor.archivesinfo.action;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.archivesinfo.service.ArchivesInfoService;
import com.java.familydoctor.archivesinfo.vo.ArchivesInfo;
import com.java.familydoctor.archivesinfo.vo.UserType;
import com.java.platform.core.Action;

public class ArchivesInfoAction extends Action{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 档案信息服务方法
	 */
	private ArchivesInfoService archivesInfoService;
	
	/**
	 * 档案信息实体类
	 */
	private ArchivesInfo archivesInfo;
	
	/**
	 * 用户类型实体类
	 */
	private UserType userType;

	public ArchivesInfoService getArchivesInfoService() {
		return archivesInfoService;
	}

	public void setArchivesInfoService(ArchivesInfoService archivesInfoService) {
		this.archivesInfoService = archivesInfoService;
	}

	public ArchivesInfo getArchivesInfo() {
		return archivesInfo;
	}

	public void setArchivesInfo(ArchivesInfo archivesInfo) {
		this.archivesInfo = archivesInfo;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	/**
	 * 跳转添加/编辑页面
	 */
	public String toJumpArchivesInfo(){
		if(archivesInfo != null && archivesInfo.getId() != null){
			this.archivesInfo= this.archivesInfoService.getArchivesInfoById(archivesInfo.getId());
		}
		return SUCCESS;
	}
	
	/**
	 * 档案信息添加方法
	 */
	public String saveOrUpdateArchivesInfo(){
		if(archivesInfo.getId()!=null){
			ArchivesInfo archivesInfoById = this.archivesInfoService.getArchivesInfoById(archivesInfo.getId());
			BeanUtils.copyProperties(archivesInfo, archivesInfoById, new String[]{"userInfo"});
			this.archivesInfo = archivesInfoById;
		}else{
			archivesInfo.setUserInfo(null);
		}
		this.archivesInfoService.saveOrUpdateArchivesInfo(archivesInfo);
		return redirectActionResult("getArchivesInfoList");
	}
	
	/**
	 * 档案信息列表方法
	 */
	public String getArchivesInfoList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(archivesInfo == null){
			archivesInfo = new ArchivesInfo();
		}
		List<ArchivesInfo> list = this.archivesInfoService.getArchivesInfoByPage(psm, archivesInfo);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	
	/**
	 * 用户类型列表
	 */
	public String getUserTypeList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(userType == null){
			userType = new UserType();
		}
		List<UserType> list = this.archivesInfoService.getUserTypeList(psm, userType);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	
	/**
	 * 跳转添加/编辑页面
	 */
	public String toJumpUserType(){
		
		return SUCCESS;
	}
}