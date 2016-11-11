package com.kybaby.action.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.UserInfo;
import com.kybaby.util.EncryptUtil;
import com.opensymphony.xwork2.ActionContext;


public class FileRecive extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uploadDir="../kybabyBG/admin/images/userFaceIcon";//保存上传文件的目录
	private String imagedata; // 定义接收图片的base64字符串
	private String message="";
	private String fileName="";
	private String dir="";
	private String mes="";
	private SimpleDateFormat df;
	private String current;
	public String execute() throws IOException
	{

			System.out.println("writeFile start---");
			if(ActionContext.getContext().getSession().get("userId")!=null)
			{
			long userId=(Long)ActionContext.getContext().getSession().get("userId");
			UserInfo userInfo = userInfoBo.getUserById(userId);
			if(null==userInfo){
				mes="没有此用户";
				return "fail";
			}
			fileName=userInfo.getUserImage();
			if(null==fileName||fileName.equals(""))
			{
				df = new SimpleDateFormat("yyyyMMddhhmmss");
				current=df.format(new Date());
				fileName=userId +"image" + current +".jpg";
				userInfo.setUserImage(fileName);
				userInfoBo.updateUser(userInfo);
			}
			String fileAllName=uploadDir+"/"+fileName;
			dir = ServletActionContext.getServletContext().getRealPath(fileAllName);
			if(EncryptUtil.uploadImage(imagedata,dir))
			{
			    mes="成功";
			    return "success";
			}
			else
			{
				mes="失败";
				return "fail";
			}
			}
			else
			{
				mes="未登录";
				return "fail";
			}

	}
	

	public String getMessage() {
		return message;
	}
    
	


	public void setImagedata(String imagedata) {
		this.imagedata = imagedata;
	}

	public String getMes() {
		return mes;
	}




}
