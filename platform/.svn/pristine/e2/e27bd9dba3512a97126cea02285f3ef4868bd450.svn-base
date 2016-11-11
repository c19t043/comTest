package com.java.familydoctor.servicetimes.action;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.serviceitem.vo.FdServiceItems;
import com.java.familydoctor.servicepackage.service.FdServicePackageService;
import com.java.familydoctor.servicepackage.vo.FdServicePackage;
import com.java.familydoctor.servicetimes.service.FdServiceTimesService;
import com.java.familydoctor.servicetimes.vo.FdServiceTimes;
import com.java.platform.core.Action;

public class FdServiceTimesAction extends Action{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 服务包服务时间信息服务方法
	 */
	private FdServiceTimesService fdServiceTimesService;
	
	/**
	 * 服务包服务时间信息实体类
	 */
	private FdServiceTimes fdServiceTimes;
	
	/**
	 * 家庭医生服务包服务类
	 */
	private FdServicePackageService fdServicePackageService;
	
	/**
	 * 家庭医生服务包实体类
	 */
	private FdServicePackage fdServicePackage;

	public FdServicePackageService getFdServicePackageService() {
		return fdServicePackageService;
	}

	public void setFdServicePackageService(
			FdServicePackageService fdServicePackageService) {
		this.fdServicePackageService = fdServicePackageService;
	}

	public FdServicePackage getFdServicePackage() {
		return fdServicePackage;
	}

	public void setFdServicePackage(FdServicePackage fdServicePackage) {
		this.fdServicePackage = fdServicePackage;
	}

	public FdServiceTimesService getFdServiceTimesService() {
		return fdServiceTimesService;
	}

	public void setFdServiceTimesService(FdServiceTimesService fdServiceTimesService) {
		this.fdServiceTimesService = fdServiceTimesService;
	}

	public FdServiceTimes getFdServiceTimes() {
		return fdServiceTimes;
	}

	public void setFdServiceTimes(FdServiceTimes fdServiceTimes) {
		this.fdServiceTimes = fdServiceTimes;
	}
	
	/**
	 *  跳转添加/编辑页面
	 */
	public String toJumpFdServiceTimes(){
		if(fdServiceTimes != null && fdServiceTimes.getId() != null){
			this.fdServiceTimes = fdServiceTimesService.getFdServiceTimesById(fdServiceTimes.getId());
		}
		return SUCCESS;
	}
	
	/**
	 * 服务包服务时间信息的添加方法和修改方法
	 */
	public String saveOrUpdateFdServiceTimes(){
		this.fdServiceTimesService.saveOrUpdateFdServiceTimes(fdServiceTimes);
		return redirectActionResult("getFdServiceTimesList");
	}
	
	/**
	 * 服务包服务时间信息列表页面
	 */
	public String getFdServiceTimesList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(fdServiceTimes == null){
			fdServiceTimes = new FdServiceTimes();
		}
		List<FdServiceTimes> list = this.fdServiceTimesService.getFdServiceTimesByPage(psm, fdServiceTimes);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	
	/**
	 *  跳转服务包main页面
	 */
	public String toJumpServicePackageMain(){

		return SUCCESS;
	}
	
	/**
	 *  加载iframe家庭医生服务包信息
	 */
	public String getServicePackageList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		if(fdServicePackage == null){
			fdServicePackage = new FdServicePackage();
		}
		List<FdServicePackage> list = 
			this.fdServicePackageService.getFdServicePackageByPage(psm, fdServicePackage);
		this.putToRequest("list", list);
		
		return SUCCESS;
	}
}
