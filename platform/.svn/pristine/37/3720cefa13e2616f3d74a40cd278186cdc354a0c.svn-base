package com.java.familydoctor.serviceteams.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.java.common.lang.StringUtils;
import com.java.ec.common.PageSortModel;
import com.java.familydoctor.doctorrole.service.FdRoleInfoService;
import com.java.familydoctor.doctorrole.vo.FdRoleInfo;
import com.java.familydoctor.servicepackage.service.FdServicePackageService;
import com.java.familydoctor.servicepackage.vo.FdServicePackage;
import com.java.familydoctor.serviceteams.service.FdServiceTeamsService;
import com.java.familydoctor.serviceteams.vo.FdServiceTeams;
import com.java.platform.core.Action;
import com.java.util.EncryptUtil;

public class FdServiceTeamsAction extends Action{
	private static final long serialVersionUID = 1L;

	/**
	 * 家庭医生服务团队信息的服务方法
	 */
	private FdServiceTeamsService fdServiceTeamsService;
	
	/**
	 * 家庭医生服务团队信息实体类
	 */
	private FdServiceTeams fdServiceTeams;
	
	/**
	 * 家庭医生服务包服务类
	 */
	private FdServicePackageService fdServicePackageService;
	
	/**
	 * 家庭医生服务包实体类
	 */
	private FdServicePackage fdServicePackage;
	
	/**
	 * 家庭医生角色服务类
	 */
	private FdRoleInfoService fdRoleInfoService;
	
	/**
	 * 家庭医生角色实体类
	 */
	private FdRoleInfo fdRoleInfo;
	private String uploadDir = "admin/images/familydoctor";// 保存上传文件的目录
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

	public FdServiceTeamsService getFdServiceTeamsService() {
		return fdServiceTeamsService;
	}

	public void setFdServiceTeamsService(FdServiceTeamsService fdServiceTeamsService) {
		this.fdServiceTeamsService = fdServiceTeamsService;
	}

	public FdServiceTeams getFdServiceTeams() {
		return fdServiceTeams;
	}

	public void setFdServiceTeams(FdServiceTeams fdServiceTeams) {
		this.fdServiceTeams = fdServiceTeams;
	}

	/**
	 *  跳转添加/编辑页面
	 */
	public String toJumpFdServiceTeams(){
		if(fdServiceTeams != null && fdServiceTeams.getId() != null){
			this.fdServiceTeams = fdServiceTeamsService.getFdServiceTeamsById(fdServiceTeams.getId());
			if(fdServiceTeams.getFdServicePackageSet() != null){
				StringBuilder packageIds = new StringBuilder();
				StringBuilder packageNames = new StringBuilder();
				Iterator<FdServicePackage> itFdServicePackage = fdServiceTeams.getFdServicePackageSet().iterator();
				while (itFdServicePackage.hasNext()) {
					FdServicePackage pack = itFdServicePackage.next();
					packageIds.append(pack.getId()).append(",");
					packageNames.append(pack.getPackageShowName()).append(",");
				}
				fdServiceTeams.setPackageNames(packageNames.toString());
				fdServiceTeams.setPackageIds(packageIds.toString());
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 家庭医生团队服务信息的添加方法
	 */
	public String saveOrUpdateFdSerivceTeams(){
		String tempDir = "";
		if (fdServiceTeams.getId() != null) {
			tempDir = fdServiceTeams.getTeamImgPath();
		}
		if(StringUtils.isNotEmpty(tempDir)){
			
		}else {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			String current = df.format(new Date());
			String bannerName = "fdteam" + current + ".jpg";
			tempDir = uploadDir + "/" + bannerName;
			fdServiceTeams.setTeamImgPath(tempDir);
		}
		if (fdServiceTeams.getImgBase64() != null
				&& !"".equals(fdServiceTeams.getImgBase64().trim())) {
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
				EncryptUtil.uploadImage(fdServiceTeams.getImgBase64(), dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(fdServiceTeams.getFdServicePackage()!=null&&fdServiceTeams.getFdServicePackage().getId()==null){
			fdServiceTeams.setFdServicePackage(null);
		}
		
		this.fdServiceTeamsService.saveOrUpdateFdServiceTeams(fdServiceTeams);
		addActionMessage("操作成功");
		return redirectActionResult("getFdSerivceTeamsList");
	}
	
	/**
	 * 家庭医生团队服务信息列表页面
	 */
	public String getFdSerivceTeamsList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(fdServiceTeams == null){
			fdServiceTeams = new FdServiceTeams();
		}
		List<FdServiceTeams> list = this.fdServiceTeamsService.getFdServiceTeamsByPage(psm, fdServiceTeams);
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
	
	/**
	 *  跳转医生团队服务包main页面 
	 */
	public String toJumpDoctorTeamPackageMain(){

		return SUCCESS;
	}
	
	/**
	 *  跳转医生团队服务包list页面,加载数据
	 */
	public String getDoctorTeamPackageList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		if(fdServicePackage == null){
			fdServicePackage = new FdServicePackage();
		}
		fdServicePackage.setTeamPackage(true);
		List<FdServicePackage> list = 
			this.fdServicePackageService.getFdServicePackageByPage(psm, fdServicePackage);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	
	/**
	 * 查询家庭医生角色列表
	 */				
	public String getFdRoleInfoList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		List<FdRoleInfo> list = this.fdRoleInfoService.getFdRoleInfoListByPage(psm, fdRoleInfo);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	
}
