package com.java.familydoctor.archivesinfo.action;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.archivesinfo.service.UserTypeService;
import com.java.familydoctor.archivesinfo.vo.UserType;
import com.java.platform.core.Action;

public class UserTypeAction extends Action{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户类型服务方法
	 */
	private UserTypeService userTypeService;
	
	/**
	 * 用户类型实体类
	 */
	private UserType userType;

	public UserTypeService getUserTypeService() {
		return userTypeService;
	}

	public void setUserTypeService(UserTypeService userTypeService) {
		this.userTypeService = userTypeService;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	/**
	 * 跳转编辑和修改页面
	 * @return
	 */
	public String toJumpUserType(){
		if(userType != null && userType.getId() != null){
			this.userType = this.userTypeService.getUserTypeById(userType.getId());
		}
		return SUCCESS;
	}
	
	/**
	 *	用户类型添加方法
	 * @return
	 */
	public String saveOrUpdateUserType(){
		this.userTypeService.saveOrUpdateUserType(userType);
		return redirectActionResult("getUserTypeList");
	}
	
	/**
	 * 用户列表方法
	 * @return
	 */
	public String getUserTypeList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(userType == null){
			userType = new UserType();
		}
		List<UserType> list = this.userTypeService.getUserTypePage(psm, userType);
		putToRequest("list", list);
		return SUCCESS;
	}
}
