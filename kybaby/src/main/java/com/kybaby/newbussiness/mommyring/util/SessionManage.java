package com.kybaby.newbussiness.mommyring.util;

import com.kybaby.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:SessionManage
 * @Description:该工具包用于管理用户的登录状态的session
 * @author Hoolee
 * @date 2015年12月11日下午1:56:52
 */
public class SessionManage {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:
	 * @data: 2015年12月11日下午2:14:42
	 * @param sessionKey
	 * @return
	 */
	public static long checkSomeSession(String sessionKey){
		long userId = (long)ActionContext.getContext().getSession().get(sessionKey);
		return userId;
	}
	
	public static void addSomeSession(String sessionKey,UserInfo userInfo){
		ActionContext.getContext().getSession().put(sessionKey, userInfo);
	}
	
}
