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

import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:UserImageManage
 * @Description:用户上传头像
 * @author Hoolee
 * @date 2015年10月15日上午10:36:50
 */
public class UserImageManage extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String mes;//反馈到前端的提示信息、
	
	private File productSmallFileElem;//上传的头像
	private String dir="../kybabyBG/admin/images/userFaceIcon";
	private File di;
	
	private SimpleDateFormat df;
	private String current;
	
	public String execute(){
		if(action.equals("inputIcon")){
			System.out.println("InputIcon is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				String fileName="";
				if(productSmallFileElem!=null){
					df = new SimpleDateFormat("yyyyMMddhhmmss");
					current=df.format(new Date());
					fileName=userId +"image" + current +".jpg";
					String tempDir = dir + "/" + fileName;
					dir = ServletActionContext.getServletContext().getRealPath(tempDir);
					di = new File(dir);
					copy(productSmallFileElem, di); 
					UserInfo usr=userInfoBo.getUserById(userId);
					if(usr!=null){
						usr.setUserImage(fileName);
						userInfoBo.updateUser(usr);
					}
					mes="操作成功";
					return "success";
				}
				mes="无图片";
				return "fail";
			}
			mes="未登录";
			return "success";
		}
		return "fail";
	}
	
    private static void copy(File src, File dst) {
        InputStream in = null; 
        OutputStream out = null; 
        try { 
            in = new BufferedInputStream(new FileInputStream(src), 2048); 
            out = new BufferedOutputStream(new FileOutputStream(dst),2048); 
            byte[] buffer = new byte[2048]; 
            int len = 0; 
            while ((len = in.read(buffer)) > 0) { 
                out.write(buffer, 0, len); 

            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally { 
            if (null != in) { 
                try { 
                    in.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                } 
            } 
            if (null != out) { 
                try { 
                    out.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                } 
            } 
        } 
    }

	public void setProductSmallFileElem(File productSmallFileElem) {
		this.productSmallFileElem = productSmallFileElem;
	}

	public String getMes() {
		return mes;
	} 
	
}
