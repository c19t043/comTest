package com.java.familydoctor.servicepackage.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
















import org.apache.struts2.ServletActionContext;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.serviceitem.service.FdServiceItemsService;
import com.java.familydoctor.serviceitem.vo.FdServiceItems;
import com.java.familydoctor.servicepackage.service.FdServicePackageService;
import com.java.familydoctor.servicepackage.vo.FdServicePackage;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.platform.core.Action;
import com.java.util.EncryptUtil;

public class FdServicePackageAction extends Action{
	private static final long serialVersionUID = 1L;
	
	/**
	 *	家庭医生服务包信息服务方法
	 */
	private FdServicePackageService fdServicePackageService;
	
	/**
	 * 家庭医生服务包信息实体类
	 */
	private FdServicePackage fdServicePackage;
	
	/**
	 * 服务项目服务类
	 */
	private FdServiceItemsService fdServiceItemsService;
	
	/**
	 * 服务项目实体类
	 */
	private FdServiceItems fdServiceItems;
	/**
	 * 医院信息
	 */
	private HospitalBasicInfo hospitalBasicInfo;
	
	private String uploadDir = "admin/images/familydoctor";// 保存上传文件的目录
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
	
	public FdServiceItemsService getFdServiceItemsService() {
		return fdServiceItemsService;
	}

	public void setFdServiceItemsService(FdServiceItemsService fdServiceItemsService) {
		this.fdServiceItemsService = fdServiceItemsService;
	}
	
	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public FdServiceItems getFdServiceItems() {
		return fdServiceItems;
	}

	public void setFdServiceItems(FdServiceItems fdServiceItems) {
		this.fdServiceItems = fdServiceItems;
	}

	/**
	 * 跳转添加/编辑页面
	 * @return
	 */
	public String toJumpFdServicePackage(){
		if(fdServicePackage != null && fdServicePackage.getId() != null){
			this.fdServicePackage = this.fdServicePackageService.getFdServicePackageById(fdServicePackage.getId());
		}
		return SUCCESS;
	}
	
	/**
	 * 家庭医生服务包信息列表方法
	 * @return
	 */
	public String getFdServicePackageList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		if(fdServicePackage == null){
			fdServicePackage = new FdServicePackage();
		}
		List<FdServicePackage> list = this.fdServicePackageService.getFdServicePackageByPage(psm, fdServicePackage);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	
	/**
	 * 家庭医生服务包信息添加方法
	 * @return
	 */
	public String saveOrUpdateFdServicePackage(){
		String tempDir = "";
		if (fdServicePackage.getId() != null) {
			tempDir = fdServicePackage.getImagePath();
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			String current = df.format(new Date());
			String bannerName = "fdpac" + current + ".jpg";
			tempDir = uploadDir + "/" + bannerName;
			fdServicePackage.setImagePath(tempDir);
		}
		if (fdServicePackage.getImgBase64() != null
				&& !"".equals(fdServicePackage.getImgBase64().trim())) {
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
				EncryptUtil.uploadImage(fdServicePackage.getImgBase64(), dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		this.fdServicePackageService.saveOrUpdateFdServicePackage(fdServicePackage);//
		addActionMessage("操作成功");
		return redirectActionResult("fdServicePackageList");
	}
	
	/**
	 * 跳转页面
	 */
	public String toJumpFdServiceItemsPage(){
		return SUCCESS;
	}
	
	/**
	 * 跳转服务项目选择页面
	 */
	public String toJumpFdServiceItems(){
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
	 * 得到医院信息列表
	 * @return
	 */
	public String getHospitalInfoList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		List<HospitalBasicInfo> list = 
			this.fdServicePackageService.getHospitalInfoList(psm, hospitalBasicInfo);
		this.putToRequest("list", list);
		return SUCCESS;
	}
}
