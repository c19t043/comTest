package com.java.medicalorgandbusiness.orgsetmeal.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.java.common.lang.StringUtils;
import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.service.SetMealService;
import com.java.medicalorgandbusiness.orgsetmeal.vo.OrganSetMeal;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.platform.core.Action;
import com.java.util.EncryptUtil;

public class SetMealAction extends Action  {

	private static final long serialVersionUID = -5815335939255196581L;
	
	private SetMealService setMealService;
	
	private OrganSetMeal organSetMeal;
	
	private HospitalBasicInfo hospitalBasicInfo;
	
	private String uploadDir = "admin/images/familydoctor";// 保存上传文件的目录
	/**
	 * 保存or更新
	 * @return
	 */
	public String saveOrUpdate(){
		
		String tempDir = "";
		String bannerName = null;
		if (organSetMeal.getId() != null) {
			OrganSetMeal meal_query = setMealService.get(organSetMeal.getId(), OrganSetMeal.class);
			tempDir = uploadDir + "/" +meal_query.getPackageImg();
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			String current = df.format(new Date());
			bannerName = "setMeal" + current + ".jpg";
			tempDir = uploadDir + "/" + bannerName;
		}
		
		if (organSetMeal.getPackageImg() != null
				&& !"".equals(organSetMeal.getPackageImg().trim())) {
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
				EncryptUtil.uploadImage(organSetMeal.getPackageImg(), dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		organSetMeal.setPackageImg(bannerName);
		if(organSetMeal.getId()!=null)
			setMealService.updateSetMeal(organSetMeal);
		else
			setMealService.saveSetMeal(organSetMeal);
		return redirectActionResult("toList");
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String toAdd(){
		
		return SUCCESS;
	}
	
	/**
	 * 跳转到编辑页面
	 */
	public String toEdit(){
		this.organSetMeal = setMealService.getOrganSetMeal(organSetMeal.getId());
		
		return SUCCESS;
	}
	
	/**
	 * 获取所有套餐信息
	 * @return
	 */
	public String toList(){
		
		PageSortModel model = new PageSortModel(this.getHttpServletRequest(),"list");
		
		List<OrganSetMeal> organSetMealList = setMealService.getOrganSetMeatList(model,organSetMeal);
		if(organSetMealList != null){
			for(OrganSetMeal os : organSetMealList ){
				String orgNames = "";
				List<HospitalBasicInfo> hospitalBasicInfoList = this.setMealService.getHospitalBasicInfoList(null, null, os.getId());
				if(hospitalBasicInfoList != null && hospitalBasicInfoList.size() >0){
					for(HospitalBasicInfo info : hospitalBasicInfoList){
						orgNames+=info.getHospitalLname()+",";
					}
					orgNames = orgNames.substring(0, orgNames.length()-1);
				}
				os.setOrgNames(orgNames);
			}
		}
		putToRequest("list", organSetMealList);
		
		return SUCCESS;
	}
	
	/**
	 * 获取机构列表
	 * @return
	 */
	public String getOrgList(){
		
		PageSortModel model = new PageSortModel(this.getHttpServletRequest(),"list");
		
		List<HospitalBasicInfo> hospitalBasicInfoList = setMealService.getHospitalBasicInfoList(model,hospitalBasicInfo,null);
		
		putToRequest("list", hospitalBasicInfoList);
		
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public OrganSetMeal getOrganSetMeal() {
		return organSetMeal;
	}
	public void setOrganSetMeal(OrganSetMeal organSetMeal) {
		this.organSetMeal = organSetMeal;
	}
	public SetMealService getSetMealService() {
		return setMealService;
	}

	public void setSetMealService(SetMealService setMealService) {
		this.setMealService = setMealService;
	}
	
}
