package com.kybaby.newbussiness.doctorring.util;

import com.kybaby.domain.DoctorInfo;
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
		long doctorId=0;
		DoctorInfo doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get(sessionKey);
		if(doctorInfo!=null){
			doctorId=doctorInfo.getId();
		}
		return doctorId;
	}
	
	public static void addSomeSession(String sessionKey,DoctorInfo doctorInfo){
		ActionContext.getContext().getSession().put(sessionKey, doctorInfo);
	}
	
}
