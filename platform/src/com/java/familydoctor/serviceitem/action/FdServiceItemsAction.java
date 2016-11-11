package com.java.familydoctor.serviceitem.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.memberskill.service.FdMemberSkillService;
import com.java.familydoctor.memberskill.vo.FdMemberSkill;
import com.java.familydoctor.serviceitem.service.FdServiceItemsService;
import com.java.familydoctor.serviceitem.vo.FdServiceItems;
import com.java.platform.core.Action;
import com.java.util.EncryptUtil;

public class FdServiceItemsAction extends Action{

	private static final long serialVersionUID = 1L;
	/**
	 * 服务项目
	 */
	private FdServiceItems fdServiceItems;
	/**
	 * 服务项目服务类
	 */
	private FdServiceItemsService fdServiceItemsService;
	/**
	 * 技能服务类
	 */
	private FdMemberSkillService fdMemberSkillService;
	/**
	 * 技能实体类
	 */
	private FdMemberSkill fdMemberSkill;
	
	private String uploadDir = "admin/images/familydoctor";// 保存上传文件的目录
	public FdServiceItems getFdServiceItems() {
		return fdServiceItems;
	}
	public void setFdServiceItems(FdServiceItems fdServiceItems) {
		this.fdServiceItems = fdServiceItems;
	}
	public FdServiceItemsService getFdServiceItemsService() {
		return fdServiceItemsService;
	}
	public void setFdServiceItemsService(FdServiceItemsService fdServiceItemsService) {
		this.fdServiceItemsService = fdServiceItemsService;
	}
	
	public FdMemberSkillService getFdMemberSkillService() {
		return fdMemberSkillService;
	}
	public void setFdMemberSkillService(FdMemberSkillService fdMemberSkillService) {
		this.fdMemberSkillService = fdMemberSkillService;
	}
	public FdMemberSkill getFdMemberSkill() {
		return fdMemberSkill;
	}
	public void setFdMemberSkill(FdMemberSkill fdMemberSkill) {
		this.fdMemberSkill = fdMemberSkill;
	}
	
	/**
	 * 跳转添加/编辑页面
	 * @return
	 */
	public String toJumpFdServiceItems(){
		if(fdServiceItems != null && fdServiceItems.getId() != null){
			this.fdServiceItems = this.fdServiceItemsService.getFdServiceItemsById(fdServiceItems.getId());
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转页面
	 * @return
	 */
	public String toJumpFdServiceItemsPage(){
		System.out.println("进入方法了吗？");
		return SUCCESS;
	}
	
	
	/**
	 * 得到家庭医生服务项目列表
	 * @return
	 */
	public String getFdServiceItemsList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		if(fdServiceItems == null){
			fdServiceItems = new FdServiceItems();
		}
		List<FdServiceItems> list = 
			this.fdServiceItemsService.getFdServiceItemsListByPage(psm, fdServiceItems);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 保存信息
	 * @return
	 */
	public String saveOrUpdateFdServiceItems(){
		String tempDir = "";
		if (fdServiceItems.getId() != null) {
			tempDir = fdServiceItems.getImagePath();
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			String current = df.format(new Date());
			String bannerName = "fd" + current + ".jpg";
			tempDir = uploadDir + "/" + bannerName;
			fdServiceItems.setImagePath(tempDir);
		}
		if (fdServiceItems.getImgBase64() != null
				&& !"".equals(fdServiceItems.getImgBase64().trim())) {
			String directory = ServletActionContext.getServletContext()
					.getRealPath(uploadDir + "/");
			File cacheDir = new File(directory);
			// 如果文件夹不存在则创建
			
			if (!cacheDir.exists() && !cacheDir.isDirectory()) {
				System.out.println("//不存在");
				cacheDir.mkdirs();
			} else {
				System.out.println("//目录存在");
			}
			// 上传图片
			String dir = ServletActionContext.getServletContext()
					.getRealPath(tempDir);
			try {
				EncryptUtil.uploadImage(fdServiceItems.getImgBase64(), dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.fdServiceItemsService.saveOrUpdateFdServiceItems(fdServiceItems);
		addActionMessage("操作成功");
		return redirectActionResult("fdServiceItemsList");
	}
	
	/**
	 * 回去技能列表方法
	 */
	
	public String getFdMemberSkillList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(fdMemberSkill == null){
			fdMemberSkill = new FdMemberSkill();
		}
		List<FdMemberSkill> list = this.fdMemberSkillService.getFdMemberSkillByPage(psm, fdMemberSkill);
		this.putToRequest("list", list);
		return SUCCESS;
	}
}
