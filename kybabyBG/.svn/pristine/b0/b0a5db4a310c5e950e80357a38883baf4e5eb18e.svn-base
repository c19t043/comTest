package com.kybaby.action.admin;

import org.apache.commons.lang.StringUtils;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.Admin;
import com.kybaby.util.EncryptUtil;
import com.opensymphony.xwork2.ActionContext;

public class SelfCenter extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mes="";
	private String action="";
	
	private Admin admin; //返回的管理员信息  
	private String pwd="";  //密码
	private String contactPhone=""; //联系电话
	
	public String execute()
	{
		System.out.println(StringUtils.swapCase(this.getClass().getSimpleName().substring(0,1))+this.getClass().getSimpleName().substring(1,this.getClass().getSimpleName().length())+".action?action="+action+"......");
        if(action.equals("myInfo"))
        {
         Admin adminOne=(Admin)ActionContext.getContext().getSession().get("landUser");
         if(adminOne!=null)
         {
        	 long id=adminOne.getId();
        	 admin=adminBo.findAdminById(id);
        	 mes="成功";
        	 return "success";
         }
         else
         {
        	 mes="请重新登录";
        	 return "fail";
         }
        	
        }
        
        if(action.equals("update"))
        {
        	Admin adminOne=(Admin)ActionContext.getContext().getSession().get("landUser");
        	if(adminOne!=null)
        	{
        	   long id=adminOne.getId();
        	   Admin updateAdmin=adminBo.findAdminById(id);
        	if(!pwd.equals("")&&!contactPhone.equals(""))
        	{
        		if(updateAdmin.getPassword().equals(pwd))
        		{
        			updateAdmin.setContactPhone(contactPhone);
        			
        		}
        		else
        		{
        			updateAdmin.setPassword(EncryptUtil.getMD5Str(pwd));
        			updateAdmin.setContactPhone(contactPhone);
        		}
        		baseBo.updateAdmin(updateAdmin);
        		ActionContext.getContext().getSession().remove("landUser");
    			ActionContext.getContext().getSession().put("landUser", updateAdmin);
    			mes="成功";
    			return "success";
        	}
        	else
        	{
        		mes="传参错误";
        		return "fail";
        	}
        	}
        	else
        	{
        	 mes="请重新登录";
           	 return "fail";
        	}
        }
        
		return "success";
	}

	public String getMes() {
		return mes;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
}
