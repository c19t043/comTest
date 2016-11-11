package com.java.medicalorgandbusiness.orgsetpro.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.java.common.lang.StringUtils;
import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.service.SetMealService;
import com.java.medicalorgandbusiness.orgsetmeal.vo.OrganSetMealHospital;
import com.java.medicalorgandbusiness.orgsetpro.service.OrganSetProService;
import com.java.medicalorgandbusiness.orgsetpro.vo.OrganSetPro;
import com.java.platform.core.Action;
import com.java.util.EncryptUtil;

public class OrganSetProAction extends Action  {
	private static final long serialVersionUID = 5292190909294618818L;
	
	private OrganSetProService organSetProService;
	
	private SetMealService setMealService;
	
	private OrganSetPro organSetPro;
	
	private OrganSetMealHospital organSetMealHospital;
	
	private String uploadDir = "admin/images/familydoctor";// 保存上传文件的目录
	
	private String message;
	
	public String toList(){
	
		PageSortModel model = new PageSortModel(getHttpServletRequest(), "list");
		
		List<OrganSetPro> organSetProList = organSetProService.getOrganSetProList(model, organSetPro,false);
		
		putToRequest("list", organSetProList);
		
		return SUCCESS;
	}
	
	public String getParentPro(){
		PageSortModel model = new PageSortModel(getHttpServletRequest(), "list");
		
		List<OrganSetPro> organSetProList = organSetProService.getOrganSetProList(model, organSetPro,true);
		
		putToRequest("list", organSetProList);
		
		return SUCCESS;
	}
	
	
	public String toAddorEdit(){
		if(organSetPro!=null){
			organSetPro = organSetProService.getOrganSetPro(organSetPro.getId());
			/*
			 * 查看是否关联有套餐 
			 */
			if(organSetPro != null && organSetPro.getId() != null){
				OrganSetMealHospital organSetMealHospital = new OrganSetMealHospital();
				organSetMealHospital.setHospitalBasicInfo(organSetPro.getHospitalBasicInfo());
				organSetMealHospital.setOrganSetMeal(organSetPro.getOrganSetMeal());
				List<OrganSetMealHospital> list = 
						organSetProService.getOrganSetMealHospitalList(null,organSetMealHospital);
				if(list != null){
					organSetPro.setMiddleID(list.get(0).getId());
				}
			}
		}
		return SUCCESS;
	}
	
	public String saveOrUpdate(){
		
		String tempDir = "";
		if (organSetPro.getId() != null) {
			tempDir = organSetPro.getProImg();
			if(StringUtils.isBlank(tempDir)){
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
				String current = df.format(new Date());
				String bannerName = "fd" + current + ".jpg";
				tempDir = uploadDir + "/" + bannerName;
				organSetPro.setProImg(tempDir);
			}
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			String current = df.format(new Date());
			String bannerName = "fd" + current + ".jpg";
			tempDir = uploadDir + "/" + bannerName;
			organSetPro.setProImg(tempDir);
		}
		
		if (organSetPro.getImgBase64() != null
				&& !"".equals(organSetPro.getImgBase64().trim())) {
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
				EncryptUtil.uploadImage(organSetPro.getImgBase64(), dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//---------object references an unsaved transient instance - save the transient instance before flushing
		if(organSetPro.getParentOrganSetPro()!=null&&organSetPro.getParentOrganSetPro().getId()==null){
			organSetPro.setParentOrganSetPro(null);
		}
		//---------
		if(organSetPro.getId()!=null){
			organSetProService.updateOrganSetPro(organSetPro);
		}else{
			organSetProService.saveOrganSetPro(organSetPro);
		}
		
		return redirectActionResult("toList");
	}
	
	public String getMealList(){
		
		PageSortModel model = new PageSortModel(getHttpServletRequest(), "list");
		
		List<OrganSetMealHospital> OrganSetMealHospitalList = organSetProService.getOrganSetMealHospitalList(model, organSetMealHospital);
		
		putToRequest("list", OrganSetMealHospitalList);
		
		return SUCCESS;
	}
	
	
	public String getRelMealAndOrg(){
		
		if(organSetPro.getId()!=null){
			organSetPro = organSetProService.get(organSetPro.getId(), OrganSetPro.class);
		}
		
		JSONObject jo = JSONObject.fromObject(organSetPro);
		try {
			 HttpServletResponse response = ServletActionContext.getResponse();
			 response.setCharacterEncoding("UTF-8");
			 response.setContentType("text/json;charset=utf-8");
			 PrintWriter writer = response.getWriter();
			 writer.write(jo.toString());
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OrganSetMealHospital getOrganSetMealHospital() {
		return organSetMealHospital;
	}

	public void setOrganSetMealHospital(OrganSetMealHospital organSetMealHospital) {
		this.organSetMealHospital = organSetMealHospital;
	}

	public OrganSetProService getOrganSetProService() {
		return organSetProService;
	}

	public void setOrganSetProService(OrganSetProService organSetProService) {
		this.organSetProService = organSetProService;
	}

	public OrganSetPro getOrganSetPro() {
		return organSetPro;
	}

	public void setOrganSetPro(OrganSetPro organSetPro) {
		this.organSetPro = organSetPro;
	}

	public SetMealService getSetMealService() {
		return setMealService;
	}

	public void setSetMealService(SetMealService setMealService) {
		this.setMealService = setMealService;
	}

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
}
