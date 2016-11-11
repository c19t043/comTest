package com.kybaby.action.admin;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;



import com.kybaby.action.BaseAction;
import com.kybaby.domain.Activity;
import com.kybaby.domain.Coupon;
import com.kybaby.domain.UserCoupon;
import com.kybaby.domain.UserInfo;

public class CouponManage extends BaseAction {

	private String mes="";
	private String action="";
	
	private String activityName; //活动名字
	private long   activityQuantity;//活动份数
	private String activityStartTime;//活动开始时间
	private String activityEndTime;//活动结束时间
	private String activityEvent;//事件名字
	private String activityStatus;//活动状态
	private long updateActivityId;//要更改活动的Id
	
	private String couponName;//优惠卷名字
	private String couponStartTime;//优惠卷使用开始时间
	private String couponEndTime;//优惠卷使用结束时间
	private double couponAmount;//优惠卷金额
	private long updateCouponId;//要更改优惠卷的Id
	
	
	List allActivityAndCopon=new ArrayList();//所有活动及对应的优惠卷
	private String activityLink;//活动链接
	
	private long activityId=0; //活动Id
	private String phone=""; //领取活动的人
	
	public String execute() throws UnknownHostException
	{
		System.out.println(StringUtils.swapCase(this.getClass().getSimpleName().substring(0,1))+this.getClass().getSimpleName().substring(1,this.getClass().getSimpleName().length())+".action?action="+action+"......");
		if(action.equals("all"))
		{
		  allActivityAndCopon=couponManageBo.getActivityAndCoupon();
		  mes="成功";
		  return "success";
		}
		
		if(action.equals("add"))
		{
			Activity checkAc=couponManageBo.getActivityByName(activityName);
			Coupon   checkCp=couponManageBo.getCouponByName(couponName);
			if(checkAc==null&&checkCp==null)
			{
				
			
			Coupon cp=new Coupon(couponName, couponStartTime, couponEndTime, couponAmount);
			baseBo.saveCoupon(cp);
			Coupon nowCp=couponManageBo.getCouponByName(couponName);
			if(nowCp!=null)
			{
			Activity ac=new Activity(activityName, activityQuantity, activityStartTime, activityEndTime, activityEvent, nowCp.getId(), activityStatus, activityQuantity, "");
			baseBo.saveActivity(ac);
			Activity nowAc=couponManageBo.getActivityByName(activityName);
			 if(nowAc!=null)
			 {
				      HttpServletRequest request = ServletActionContext.getRequest();
				      StringBuffer doMainName =request.getRequestURL();
                      activityLink=doMainName.toString()+"?action=goAndGet"+"&activityId="+String.valueOf(nowAc.getId());
                      nowAc.setActivityUrl(activityLink);
                      baseBo.updateActivity(nowAc);
                      if(nowAc.getActivityEvent().equals("统一发放"))
                      {
                    	  List<Long> userId=userInfoBo.getUserIdByStatus("Y");
                    	  if(userId!=null)
                    	  {
                    		  for(Long a:userId)
                    		  {
                    			  UserCoupon addUC=new UserCoupon(a,nowCp.getId(),"N","",nowAc.getId());
                    			  baseBo.saveUserCoupon(addUC);  
                    		  }
                    		  mes="成功";
                    		  return "success";
                    	  }
                    	  else
                    	  {
                    		  mes="还没有用户";
                    		  return "fail";
                    	  }
                      }
                      mes="成功";
                      return "success";

			 }
			 else
			 {
				 mes="未能添加活动";
				 return "fail";
			 }
			}
			else
			{
				mes="失败";
				return "fail";
			}
			
			}
			
			else
			{
				mes="活动名或优惠卷名字已存在";
				return "fail";
			}
			
			
		}
		
		if(action.equals("update"))
		{
			Activity AC=couponManageBo.getActivityById(updateActivityId);
			Coupon   CP=couponManageBo.getCouponById(updateCouponId);
			Activity checkAc=couponManageBo.getActivityByName(activityName);
			Coupon    checkCoupon=couponManageBo.getCouponByName(couponName);
			if((checkAc==null&&checkCoupon==null)||AC.getActivityName().equals(activityName)||CP.getCouponName().equals(couponName))
			{
				AC.setActivityName(activityName);
				AC.setActivityQuantity(activityQuantity);
				AC.setActivityRemainQuan(activityQuantity);
				AC.setActivityStatus(activityStatus);
				AC.setStartTime(activityStartTime);
				AC.setEndTime(activityEndTime);
				baseBo.updateActivity(AC);
				CP.setCouponAmount(couponAmount);
				CP.setCouponName(couponName);
				CP.setStartTime(couponStartTime);
				CP.setEndTime(couponEndTime);
				baseBo.updateCoupon(CP);
				mes="成功";
				return "success";
			}
			else
			{
				mes="活动名或优惠卷名字已存在";
				return "fail";
			}
		}
				
				
		if(action.equals("goAndGet"))
		{
			//领取活动的过程，验证电话号码
            if(!phone.equals("")&&(activityId!=0))
            {
            	UserInfo user=userInfoBo.getUserInfoByPhone(phone);
            	if(user!=null)
            	{
            		Activity activity=couponManageBo.getActivityById(activityId);
            		if(activity!=null&&activity.getActivityEvent().equals("点击领取")&&activity.getActivityRemainQuan()>0)
            		{
            			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        				String nowTime=formatter.format(new Date());
            			if(activity.getStartTime().compareTo(nowTime)<0&&activity.getEndTime().compareTo(nowTime)>0&&activity.getActivityStatus().equals("Y"))
            			{
            				long couponId=activity.getCouponId();
            				UserCoupon uc=couponManageBo.getUserCouponByUserIdAndCouponId(user.getId(),couponId);
            				if(uc==null)
            				{
            					UserCoupon addUC=new UserCoupon(user.getId(),couponId,"N","",activity.getId());
            					activity.setActivityRemainQuan(activity.getActivityRemainQuan()-1);
            					baseBo.updateActivity(activity);
            					baseBo.saveUserCoupon(addUC);
            					mes="成功";
            					return "success";
            				}
            				else
            				{
            					mes="你已经领取了该优惠卷";
            					return "fail";
            				}
            			}
            			else
            			{
            				mes="该活动已过期";
            			}
            		}
            		else
            		{
            			mes="没有此活动或者活动数量不足";
            			return "fail";
            		}
            	}
            	else
            	{
            		mes="请注册后再领取";
            		return "fail";
            	}
            }
		    
		}
		
		if(action.equals("test"))
		{
			HttpServletRequest request = ServletActionContext.getRequest(); 
			StringBuffer url =request.getRequestURL(); 
		    System.out.println(url.toString());
		}
		
		return "success";
	}

	public String getMes() {
		return mes;
	}

	public List getAllActivityAndCopon() {
		return allActivityAndCopon;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public void setActivityQuantity(long activityQuantity) {
		this.activityQuantity = activityQuantity;
	}

	public void setActivityStartTime(String activityStartTime) {
		this.activityStartTime = activityStartTime;
	}

	public void setActivityEndTime(String activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	public void setActivityEvent(String activityEvent) {
		this.activityEvent = activityEvent;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	public void setUpdateActivityId(long updateActivityId) {
		this.updateActivityId = updateActivityId;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public void setCouponStartTime(String couponStartTime) {
		this.couponStartTime = couponStartTime;
	}

	public void setCouponEndTime(String couponEndTime) {
		this.couponEndTime = couponEndTime;
	}

	public void setCouponAmount(double couponAmount) {
		this.couponAmount = couponAmount;
	}

	public void setUpdateCouponId(long updateCouponId) {
		this.updateCouponId = updateCouponId;
	}

	public String getActivityLink() {
		return activityLink;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
