package com.java.familydoctor.doctorrole.action;
import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.doctorrole.service.FdRoleInfoService;
import com.java.familydoctor.doctorrole.vo.FdRoleInfo;
import com.java.platform.core.Action;


public class FdRoleInfoAction extends Action{
	private static final long serialVersionUID = 1L;

	/**
	 * 家庭医生角色服务方法
	 */
	private FdRoleInfoService fdRoleInfoService;
	
	/**
	 * 家庭医生角色实体类
	 */
	private FdRoleInfo fdRoleInfo;  

	public FdRoleInfoService getFdRoleInfoService() {
		return fdRoleInfoService;
	}

	public void setFdRoleInfoService(FdRoleInfoService fdRoleInfoService) {
		this.fdRoleInfoService = fdRoleInfoService;
	}

	public FdRoleInfo getFdRoleInfo() {
		return fdRoleInfo;
	}

	public void setFdRoleInfo(FdRoleInfo fdRoleInfo) {
		this.fdRoleInfo = fdRoleInfo;
	}

	/**
	 * 添加/编辑页面
	 */
	public String toJumpFdRoleInfo(){
		System.out.println("fdRoleInfo  :" + fdRoleInfo);
		if(fdRoleInfo != null && fdRoleInfo.getId() != null){
			this.fdRoleInfo = this.fdRoleInfoService.getFdRoleInfoById(fdRoleInfo.getId());
		}
		return SUCCESS;
	}
	
	/**
	 * 家庭医生角色列表方法
	 */
	public String getFdRoleInfList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(fdRoleInfo == null){
			fdRoleInfo = new FdRoleInfo();
		}
		List<FdRoleInfo> list = this.fdRoleInfoService.getFdRoleInfoListByPage(psm, fdRoleInfo);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	
	
	/**
	 * 保存方法
	 */
	public String saveOrUpdateFdRoleInfo(){
		System.out.println("添加方法!!!!!!!!");
		Long id = this.fdRoleInfoService.saveOrUpdateFdRoleInfo(fdRoleInfo);
		return redirectActionResult("fdRoleInfListList");
	}
}
