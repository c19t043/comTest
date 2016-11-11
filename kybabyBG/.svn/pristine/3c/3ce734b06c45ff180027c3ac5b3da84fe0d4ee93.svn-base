package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;

public class HealthRecordData extends BaseAction {

	private String mes="";
	private String action="";
	
	private long userId;//用户Id
	
	private List basicInfo=new ArrayList(); //宝宝基础数据
	private List growthInfo=new ArrayList();
	private List babyProductResult=new ArrayList();
	private List babyCaseClip=new ArrayList();
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public  String execute()
    {
    	if(action.equals("getBasicInfo"))
    	{
    		System.out.println("healthRecordData.action?action=getBasicInfo.........");
    		basicInfo=userInfoBo.getBasicInfoByUserId(userId);
    		mes="成功";
    		return "success";
    	}
    	
    	if(action.equals("getGrowthInfo"))
    	{
    		System.out.println("healthRecordData.action?action=getGrowthInfo.........");
    		growthInfo=growthRecordBo.getGrowthRecordByUserId(userId);
    		mes="成功";
    		return "success";
    	}
    	
    	if(action.equals("getBabyCaseClip"))
    	{
    		System.out.println("healthRecordData.action?action=getBabyCaseClip.........");
    		babyCaseClip=caseClipBo.getBaByCaseClipByUserId(userId);
    		mes="成功";
    		return "success";
    	}
    	
    	if(action.equals("geBabytProductResult"))
    	{
    		System.out.println("healthRecordData.action?action=geBabytProductResult.........");
    		List secondList=new ArrayList();
			List<Object[]> allBabyProductResult = new ArrayList();
    		allBabyProductResult=orderResultsBo.getOrderAndPathResult(userId);
			boolean secondAddFlag=true;
			boolean firstAddFlag=false;
    		for(int j=0;j<allBabyProductResult.size();j++)
    		{
    			Object[] objNow=allBabyProductResult.get(j);
    		
    			if(j==0)
    			{
    				secondList.add(allBabyProductResult.get(0));
    				secondAddFlag=false;
    			}
    			else
    			{	
    			Object[] objLast=allBabyProductResult.get(j-1);
    			String orderNumNow=String.valueOf(objNow[0]);
    			String orderNumLast=String.valueOf(objLast[0]);	
    			if(orderNumNow.equals(orderNumLast))
    			{
    				secondAddFlag=true;
    				firstAddFlag=false;
    			}
    			else
    			{
    				secondAddFlag=false;
    				firstAddFlag=true;
    			}
    			
    			if(j==allBabyProductResult.size()-1)
    			{
    				firstAddFlag=true;
    			}
    			
    			if(secondAddFlag)
    			{
    				secondList.add(objNow);
    			}
    			
    			
    			if(firstAddFlag)
    			{
    				babyProductResult.add(secondList);
    				secondList=null;
    				secondList=new ArrayList();
    				secondList.add(objNow);
    			}
 		      }
    		  

    		
//    		
//        		for(Object[] obj:allBabyProductResult)
//    		{
//    			boolean secondFlag=true;
//    			String orderNum=String.valueOf(obj[0]);
//    			
//    		}  		
//    		babyProductResult=orderResultsBo.getOrderAndPathResult(userId);
//    		List <String> orderNumList=orderResultsBo.getUserOrderNumListString(userId);
//    		if(orderNumList!=null)
//    		{
//    			for(int i=0;i<orderNumList.size();i++)
//    			{
//    			 String orderNum=orderNumList.get(i);
//    			 if(orderNum!=null||orderNum!="")
//    			 {
//    				 
//    			 }
//    			}
//    		}
    		
    		}
    		mes="成功";
    		return "success";
    	  }
    	return "success";
    }

	public String getMes() {
		return mes;
	}

	public List getGrowthInfo() {
		return growthInfo;
	}

	public List getBabyProductResult() {
		return babyProductResult;
	}

	public List getBabyCaseClip() {
		return babyCaseClip;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List getBasicInfo() {
		return basicInfo;
	}
	
}
