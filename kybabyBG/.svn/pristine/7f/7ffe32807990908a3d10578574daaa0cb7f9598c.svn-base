package com.kybaby.action.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.DoctorProduct;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.Product;
import com.kybaby.domain.UserAccount;
import com.kybaby.domain.UserCoupon;
import com.kybaby.domain.UserInfo;
import com.kybaby.domain.UserPoints;
import com.kybaby.kyinterface.util.HttpClientUtil;
import com.kybaby.util.EncryptUtil;
import com.kybaby.util.SendSms;

public class OrderInfoHandle extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mes="";
	private String action="";
	
	private long updateId; //更改订单信息的Id
	private String status; //更改订单信息状态
	private long doctorId; //要修改的医生Id
	
	private String babyName="";
	private String doctorName="";
	private String productName="";
	private String orderStatus="";
	private String startTime="";
	private String endTime="";
	
	
	private List AllOrderInfo=new ArrayList();
	private List detailOrderInfo=new ArrayList();
	private List doctorList=new ArrayList();
	private List<String> prodcutNameList=new ArrayList<String>();
	private List oneDoctorList=new ArrayList();
	

	@SuppressWarnings({ "unchecked", "null" })
	public String execute()
	{
		
		if(action.equals("search"))
		{
			System.out.println("orderInfoHandle.action?action=search......");
			if(startTime.trim().equals(""))
			{startTime="0";}
			if(endTime.trim().equals(""))
			{endTime="999999999";}
			AllOrderInfo=orderInfoBo.getSearchOrderBy(babyName,doctorName,productName,orderStatus,startTime,endTime);
			mes="成功";
			return "success";
		}
		
		if(action.equals("getProductName"))
		{
			System.out.println("orderInfoHandle.action?action=getProductName......");
			prodcutNameList=orderInfoBo.getProductName();
		}
		
		if(action.equals("getAllOrderInfo"))
		{
			System.out.println("orderInfoHandle.action?action=getAllOrderInfo......");
			AllOrderInfo=orderInfoBo.getAllOrderInfo();
			mes="成功";
			return "success";
		} 
		
		if(action.equals("getDoctor"))
		{
			System.out.println("orderInfoHandle.action?action=getDoctor......");	
			OrderInfo  updateOrder=orderInfoBo.getOrderInfoById(updateId);
			if(updateOrder!=null)
			{
            String serviceDate=updateOrder.getBespokeDate();
            String times=updateOrder.getBespokeTime();
            Long serviceTimes=Long.valueOf(times.split(":")[0]).longValue();
            Long productId=updateOrder.getProductId();
            if(serviceDate!=null&&serviceTimes!=null&&productId!=null&&times.contains(":"))
            {
            Product product=productBo.getProductById(productId);
            Long hour=product.getServiceTime();
            List<Long> bigDoctorList=doctorInfoBo.getBigDoctorList(serviceDate,serviceTimes,hour);
            if(bigDoctorList!=null)
            {
            boolean flag=true;
            for(Long a:bigDoctorList)
            {
            List<String> isProvideList=doctorInfoBo.getOneDoctorSomeDayInfo(a,serviceDate,serviceTimes,hour);
            if(isProvideList!=null)
            {
            for(String b:isProvideList)	
            {
            	if(b.equals("Y"))
            	{
            		flag=false;
            		break;
            	}
            }
            if(flag)  //update by fkn 距离满足
            {
            DoctorInfo oneDoctor=doctorInfoBo.getDoctorInfoById(a);
            
            boolean productFlag=false;  //是否能过提供该产品标志
            
            List<String> allProductIds=Arrays.asList(oneDoctor.getProductIds().split("::"));
            if(allProductIds.contains(String.valueOf(productId)))
            {
            	productFlag=true;
            }
            
            if(productFlag)   //提供产品满足
            {
            
            	boolean distanceFlag=false;
            	Double distancetwo=doctorInfoBo.doctorDistanceList(updateOrder.getUserId(),updateOrder.getDoctorId());
            	if(distancetwo!=null)
            	{
            	Long distance=oneDoctor.getServiceArea();
            	if(distancetwo<=distance)
            	{
            		distanceFlag=true;
            	}
            	}
            	
            if(distanceFlag)
            {
            Long docId=oneDoctor.getId();
            String docName=oneDoctor.getDoctorName();
            String phone=oneDoctor.getDoctorPhone();
            oneDoctorList.add(docId);
            oneDoctorList.add(docName);
            oneDoctorList.add(phone);
			doctorList.add(oneDoctorList);
			oneDoctorList=new ArrayList();
            }
            }
            }
            }
            }
            mes="成功";
            return "success";
            }
            else
            {
            	mes="无可用医生";
            	return "fail";
            }
            }
            
			}
			else
            {
            	mes="订单信息有误";
            	return "success";
            }
		}
		/**
		 * 得到所有可用医生，供运营指派
		 */
		if(action.equals("getAllDoctor")){
			doctorList = this.doctorInfoBo.getDoctorIdAndName(0L);
			if(doctorList == null){
				mes="无可用医生";
            	return "fail";
			}
			mes="成功";
        	return "success";
		}
		if(action.equals("update"))
		{
			
			System.out.println("orderInfoHandle.action?action=update......");
			if(status.equals("已接单"))
			{
				OrderInfo  updateOrder=orderInfoBo.getOrderInfoById(updateId);
				boolean flag=false;
				if(updateOrder!=null)
				{
					if(!Long.valueOf(doctorId).equals(null))
					{
					List<Object[]> availableDoctorList=doctorInfoBo.getDoctorIdAndName(updateId);
					for(int i=0;i<availableDoctorList.size();i++)
					{
						Object[] obj=availableDoctorList.get(i);
						String docId=String.valueOf(obj[0]); //得到可用医生Id
						if(Long.valueOf(docId)==doctorId)
						{
							flag=true;
							break;
						}
					}
					
					if(flag)
					{
						Long oldDoctorId=updateOrder.getDoctorId();
						String serviceDate=updateOrder.getBespokeDate();
			            String times=updateOrder.getBespokeTime();
			            Long serviceTimes=Long.valueOf(times.split(":")[0]).longValue();
			            Long productId=updateOrder.getProductId();
			            Product product=productBo.getProductById(productId);
			            Long hour=product.getServiceTime();
			            if(oldDoctorId != null){//有医生，换医生
			            	 List<Object[]> doctorProductList=doctorInfoBo.getOneDoctorSomeDayInfoIdAndStatus(oldDoctorId, serviceDate, serviceTimes, hour);
					            for(Object[] a:doctorProductList)
					            {
					            	Long someId=Long.valueOf(String.valueOf(a[0]));
					            	String isProvide=String.valueOf(a[1]);
		                            if(isProvide.equals("Y"))
		                            {
		                            	DoctorProduct doctorProduct=doctorInfoBo.getDoctorProductById(someId);
		                            	if(null!=doctorProduct)
		                            	{
		                            		doctorProduct.setIsProvide("N");
		                            		baseBo.updateDoctorProduct(doctorProduct);
		                            	}
		                            }
					            }
			            }else{//没有医生，派医生
			            	//系统自动在用户端实现
			            }
			 			updateOrder.setDoctorId(doctorId);
						updateOrder.setOrderStatus("已接单");
						//---------------------------------------新增加代码
						
						/*
						 *1.当订单对象的id不为null,订单状态为‘已接单’，医生Id不能为空
						 *2.根据订单id判断该订单是否为快医公司上门服务订单
						 *3.如果是快医公司上门服务订单，修改快医公司上门服务订单医生数据
						 */
						boolean isBSKYOrder = false;
						String result_return =  "";
						
						if(updateOrder!=null
								&&updateOrder.getId()!=null
								&&updateOrder.getOrderStatus().equals("已接单")
								&&updateOrder.getDoctorId()!=null){
							
							String uniqueResult = baseBo.kyInterfaceInvoke(updateOrder);
							
							if(uniqueResult==null) {
								isBSKYOrder = false;
							}else{
								isBSKYOrder = true;
								
								String kyId = uniqueResult.toString();
								try {
									result_return = HttpClientUtil.SMFWEdit(kyId, updateOrder.getDoctorId()+"");
								} catch (Exception e) {
									System.out.println("巴蜀快医上门服务接口调用失败原因："+ e.toString());
								}finally{
									if(!result_return.equals("true")){
										mes="巴蜀快医上门服务接口调用失败";
										System.out.println("巴蜀快医上门服务接口调用失败原因："+ result_return);
										return "fail";
									}
								}
							}
						}
						
						if((!isBSKYOrder)  //不是快医公司订单
								|| (isBSKYOrder&&result_return.equals("true")) //是快医公司订单，切返回结果是true
								){
						//-------------------------原有代码
						baseBo.updateOrderInfo(updateOrder);
						//将新医生的服务时间设置为不可用
						doctorInfoBo.updateDoctorProductByOrder(updateOrder);
						//给用户和医生发短信提醒
						SendSms sms = new SendSms();
						UserInfo user = this.userInfoBo.getUserInfoById(updateOrder.getUserId());
						String toUserContecnt="亲爱的用户，您所预约专业医生将于"+serviceDate+" "+times+"上门服务。详情请查'我的订单'";
						sms.sendInfo(user.getPhone(), toUserContecnt);
						DoctorInfo doctor = this.doctorInfoBo.getDoctorInfoById(doctorId);
						String toDoctorContecnt="亲爱的"+doctor.getDoctorName()+"医生，"+user.getBabyName()+"预约您"+serviceDate+" "+times+"上门服务，请注意查看订单。";
						sms.sendInfo(doctor.getDoctorPhone(), toDoctorContecnt);
						mes="成功";
						return "success";
						//-----------------------原有代码
						}
					}
					else
					{
						mes="该医生已不可用,请重新选择";
						return "fail";
					}
					}
				}
			}
			
			if(status.equals("已退款"))
			{
				OrderInfo  updateOrder=orderInfoBo.getOrderInfoById(updateId);
				
				if(updateOrder!=null)
				{
					updateOrder.setOrderStatus("已退款");
					
					Long oldDoctorId=updateOrder.getDoctorId();
					String serviceDate=updateOrder.getBespokeDate();
		            String times=updateOrder.getBespokeTime();
		            Long serviceTimes=Long.valueOf(times.split(":")[0]).longValue();
		            Long productId=updateOrder.getProductId();
		            Product product=productBo.getProductById(productId);
		            Long hour=product.getServiceTime();
		            List<Object[]> doctorProductList=doctorInfoBo.getOneDoctorSomeDayInfoIdAndStatus(oldDoctorId, serviceDate, serviceTimes, hour);
		            for(Object[] a:doctorProductList)
		            {
		            	Long someId=Long.valueOf(String.valueOf(a[0]));
		            	String isProvide=String.valueOf(a[1]);
                        if(isProvide.equals("Y"))
                        {
                        	DoctorProduct doctorProduct=doctorInfoBo.getDoctorProductById(someId);
                        	if(null!=doctorProduct)
                        	{
                        		doctorProduct.setIsProvide("N");
                        		baseBo.updateDoctorProduct(doctorProduct);
                        	}
                        }
		            }
							
					Long userId=updateOrder.getUserId();
					if(userId!=null)
					{
					UserInfo theUser=userInfoBo.getUserInfoById(userId);
					if(theUser!=null)
					{
					Double backMoney=updateOrder.getTotalPrice();
					if(backMoney!=null)
					{
					    Double nowBlance=theUser.getAccountBalance();
						theUser.setAccountBalance(EncryptUtil.getSecondBits(EncryptUtil.getSecondBits(nowBlance)+EncryptUtil.getSecondBits(backMoney)));
						UserAccount newUserAccout=new UserAccount(userId, backMoney, "+", "平台退款", new Timestamp(System.currentTimeMillis()));
						baseBo.saveUserAccount(newUserAccout);
					}
					Long couponId=updateOrder.getCouponId();
					if(couponId!=null)
					{
						UserCoupon userCoupon=couponManageBo.getUserCouponByUserIdAndCouponId(userId, couponId);
						if(userCoupon!=null)
						{
							String userCouponStatus=userCoupon.getCouponStatus();
							if(userCouponStatus.equals("Y"))
							{
								userCoupon.setCouponStatus("N");
								baseBo.updateUserCoupon(userCoupon);
							}
						}
					}
					
					Long usePoints=updateOrder.getUsePoints();
					if(usePoints!=null||usePoints!=0L)
					{
						Long nowPoints=theUser.getAccountPoints();
						theUser.setAccountPoints(nowPoints+usePoints);
	    				UserPoints newUserPoints=new UserPoints(userId, usePoints, "+", "平台退积分", new Timestamp(System.currentTimeMillis()));
	    				baseBo.saveUserPoints(newUserPoints);
					}
					baseBo.updateOrderInfo(updateOrder);
					baseBo.updateUserInfo(theUser);
					mes="成功";
					return "success";
					}
					else
					{
					  mes="没有找到该用户";
					  return "fail";
					}
					}
					else
					{
						mes="此订单里没有用户Id";
					}
				}
				else
				{
					mes="订单Id为"+updateId+",没有找到此订单";
					return "fail";
				}
				
			}
		}
		
		return "success";
	}

	
	public String getMes() {
		return mes;
	}

	public List getAllOrderInfo() {
		return AllOrderInfo;
	}

	public List getDetailOrderInfo() {
		return detailOrderInfo;
	}

	public List getDoctorList() {
		return doctorList;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setUpdateId(long updateId) {
		this.updateId = updateId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<String> getProdcutNameList() {
		return prodcutNameList;
	}
}
