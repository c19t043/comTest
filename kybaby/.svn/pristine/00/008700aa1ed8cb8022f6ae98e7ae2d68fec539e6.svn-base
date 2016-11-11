package com.kybaby.action.main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.CaseClip;
import com.kybaby.domain.UserInfo;
import com.kybaby.util.EncryptUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:CaseClipManage
 * @Description:病历夹管理
 * @author Hoolee
 * @date 2015年10月14日上午2:00:25
 */
public class CaseClipManage extends BaseAction {

	
	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private String picType;//上传图片的类型
	private String tagIdsStr;//前端传递的症状标签ID字符串
	
	private String dir="../kybabyBG/admin/images/caseclipPicture";
	private SimpleDateFormat df;
	private String current;
	
	private String imagedata;//用来接收上传图片转化成的base64字符串
	private String fileName="";
	
	public String execute() throws IOException{
		System.out.println(StringUtils.swapCase(this.getClass().getSimpleName().substring(0,1))+this.getClass().getSimpleName().substring(1,this.getClass().getSimpleName().length())+".action?action="+action+"......");
		if(action.equals("add")){
			System.out.println("ADD is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				//才用base64字符串的方式上传图片
				UserInfo userInfo = userInfoBo.getUserById(userId);
				if(userInfo!=null){
					df = new SimpleDateFormat("yyyyMMddhhmmss");
					current=df.format(new Date());
					fileName=userId +"image" + current +".jpg";
					String fileAllName=dir+"/"+fileName;
					dir = ServletActionContext.getServletContext().getRealPath(fileAllName);
					CaseClip clip=new CaseClip();
					clip.setUserId(userId);
					clip.setSymptomTagIds(tagIdsStr);
					if(EncryptUtil.uploadImage(imagedata,dir)){
						if(picType.equals("症状图")){
							clip.setSymptomImage(fileName);
							clip.setDrugImage("");
							clip.setPrescribeImage("");
						}else if(picType.equals("处方图")){
							clip.setSymptomImage("");
							clip.setDrugImage("");
							clip.setPrescribeImage(fileName);
						}else if(picType.equals("药物图")){
							clip.setSymptomImage("");
							clip.setDrugImage(fileName);
							clip.setPrescribeImage("");
						}else{
							clip.setSymptomImage("");
							clip.setDrugImage("");
							clip.setPrescribeImage("");
						}
					}
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date rightNow=new Date(System.currentTimeMillis());
					String dateNow=sdf.format(rightNow);
					clip.setSubmitTime(dateNow);
					userInfoBo.addNewCaseClip(clip);
					if(!tagIdsStr.equals("")){
						symptomTagBo.updateSymptomStatusByIds(tagIdsStr);
					}
					mes="操作成功";
					return "success";
				}
				mes="无此用户";
				return "fail";
				/*String fileName="";
				if(productSmallFileElem3!=null){
					df = new SimpleDateFormat("yyyyMMddhhmmss");
					current=df.format(new Date());
					fileName=userId+ "pic" + current +".jpg";
					String tempDir = dir + "/" + fileName;
					dir = ServletActionContext.getServletContext().getRealPath(tempDir);
					di = new File(dir);
					copy(productSmallFileElem3, di); 
				}
				CaseClip clip=new CaseClip();
				clip.setUserId(userId);
				clip.setSymptomTagIds(tagIdsStr);
				if(picType.equals("症状图")){
					clip.setSymptomImage(fileName);
					clip.setDrugImage("");
					clip.setPrescribeImage("");
				}else if(picType.equals("处方图")){
					clip.setSymptomImage("");
					clip.setDrugImage("");
					clip.setPrescribeImage(fileName);
				}else if(picType.equals("药物图")){
					clip.setSymptomImage("");
					clip.setDrugImage(fileName);
					clip.setPrescribeImage("");
				}else{
					clip.setSymptomImage("");
					clip.setDrugImage("");
					clip.setPrescribeImage("");
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date rightNow=new Date(System.currentTimeMillis());
				String dateNow=sdf.format(rightNow);
				clip.setSubmitTime(dateNow);
				userInfoBo.addNewCaseClip(clip);
				if(!tagIdsStr.equals("")){
					symptomTagBo.updateSymptomStatusByIds(tagIdsStr);
				}
				mes="操作成功";
				return "success";*/
			}
			mes="未登录";
			return "fail";
		}
		return "fail";
	}
	
	public String getMes() {
		return mes;
	}
	public void setPicType(String picType) {
		this.picType = picType;
	}
	public void setProductSmallFileElem3(File productSmallFileElem3) {
	}
	public void setTagIdsStr(String tagIdsStr) {
		this.tagIdsStr = tagIdsStr;
	}
	
	public void setImagedata(String imagedata) {
		this.imagedata = imagedata;
	}
	
}
