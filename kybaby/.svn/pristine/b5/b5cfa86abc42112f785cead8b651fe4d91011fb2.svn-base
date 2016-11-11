package com.kybaby.action.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.Coupon;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.Product;
import com.kybaby.domain.ProductItem;
import com.kybaby.domain.Properties;
import com.kybaby.domain.RecommentAwardRecord;
import com.kybaby.domain.UserInfo;
import com.kybaby.util.CalculationMethod;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:GetOrderInfo
 * @Description:订单信息相关的
 * @author Hoolee
 * @date 2015年10月7日下午2:15:01
 */
public class GetOrderInfo extends BaseAction {

	/**
	 * @return the someProductItemShowNamesList
	 */
	public List<String> getSomeProductItemShowNamesList() {
		return someProductItemShowNamesList;
	}

	private static final long serialVersionUID = 1L;
	private String mes;//反馈给前端的提示信息
	private UserInfo someUser;//当前登录用户的实例
	private DoctorInfo someDoctor;//当前选择医生的实例
	private String productSmallPic;//选中商品的小图
	private double productPrice;//选中商品的价格
	private List<String> someProductItemsList=new ArrayList<String>();//选中商品包含的项目名称列表
	private List<Coupon> userCupnList=new ArrayList<Coupon>();//用户未使用，但是有效的优惠券列表
	private Properties pointsProperties;//平台的积分兑换实例
	
	private long doctorId;//页面传递的医生ID
	private String productName;//页面传递的选中的商品名称

	private long productId;//页面传递的选中的商品ID
	private String serviceDate;//页面传递的选中的服务日期
	private String serviceTime;//页面传递的选中的服务时间
	private long cuponId;//页面传递的使用的优惠券
	private long points;//页面传递的使用的积分数
	private double amount;//页面传递的使用的余额数
	private String payMethod;//页面传递的使用的支付方式

	private double payAmount;//传递到页面的需要使用的其他支付方式的支付金额
	private String orderNum;//页面传递到页面的需要使用的其他支付方式的支付订单号
	private List<List> someOrderList;
	private Long orderId;
	private List<List> orderDetail;
	private List<String> itemNameList;
	
	private List<String> someProductItemShowNamesList=new ArrayList<String>();//选中商品包含的项目显示名称列表

	private List<Object[]> someOrderResultList=new ArrayList<Object[]>();
	
	public String execute(){
		System.out.println(StringUtils.swapCase(this.getClass().getSimpleName().substring(0,1))+this.getClass().getSimpleName().substring(1,this.getClass().getSimpleName().length())+".action?action="+action+"......");
		if(action.equals("doctorCheck")){
			System.out.println("DoctorCheck is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				someUser=userInfoBo.getUserById(userId);
				Product someProduct=productBo.getProductByName(productName);
				productId=someProduct.getId();
				productSmallPic=someProduct.getSmallPicture();
				productPrice=someProduct.getTotalPrice();
				String itemIds=someProduct.getItemIds();
				someProductItemsList=productItemBo.getSomeProductItems(itemIds);
				someProductItemShowNamesList=productItemBo.getSomeProductItemShowNameList(itemIds);
				userCupnList=couponBo.getUserCoupnListById(userId);
				pointsProperties=propertiesBo.getPointsProperties("积分抵现");
				if(doctorId!=0){
					someDoctor=doctorInfoBo.getDoctorInfoByDoctorId(doctorId);
				}
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "fail";
		} else if(action.equals("addNewOrder")){
			System.out.println("AddNewOrder is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				/*首先检测该医生是否还可以提供服务*/
				long service=productBo.getProductServiceTimeById(productId);
				String serviceTimes="(";
				for(long i=0;i<=service;i++){
					serviceTimes=serviceTimes+(Long.valueOf(serviceTime)+i)+",";
				}
				int strLen=serviceTimes.length();
				serviceTimes=serviceTimes.substring(0,strLen-1);
				serviceTimes=serviceTimes+")";
				//这个OrderInfo需要传参数传进来，里面要有getBespokeDate，bespokeTime（13:00-14:00）,getProductId,getUserId
				OrderInfo orderInfo = new OrderInfo();
				orderInfo.setBespokeDate(serviceDate);
				orderInfo.setBespokeTime(serviceTimes);
				orderInfo.setProductId(productId);
				orderInfo.setUserId(Long.valueOf(ActionContext.getContext().getSession().get("userId").toString()));
				doctorId=this.getDoctorIdMethod(orderInfo);
//				long servicesLong=doctorProductBo.getSomeDoctorServiceTimeAmount(doctorId, serviceDate, serviceTimes);
//				if(servicesLong==(service+1)){
				if(true){
				
					long userId=(Long)ActionContext.getContext().getSession().get("userId");
					UserInfo user=userInfoBo.getUserById(userId);
					if(user.getAccountPoints()==null){
						user.setAccountPoints(0L);
					}
					//时间没有占用就开始进入正常的订单确认流程,
					//生成新的订单
					orderNum=String.valueOf(System.currentTimeMillis());
					long usePoints=0;
					double pointsAmount=0;
					if(points>=100&&points<=user.getAccountPoints()){//使用的积分数不小于100,同时不大于用户已有的积分数
						Properties pointsProperties=propertiesBo.getPointsProperties("积分抵现");
						double rate=Double.valueOf(pointsProperties.getValue());
						usePoints=points-points%100;//实际可以使用的积分数
						pointsAmount=getSecondBits(getSecondBits((points-points%100)/100)*getSecondBits(rate));
						user.setAccountPoints(user.getAccountPoints()-usePoints);
						userPointsBo.addNewUserPoints(userId, usePoints, "-", "积分抵现");
					}

					double couponAmount=0;
					if(cuponId>0&&couponBo.isCanBeUsed(userId, cuponId)){//用户的该优惠券还没有使用，同时优惠券还在有效期内
						userCouponBo.updateUserCoupon(userId, cuponId);
						couponAmount=couponBo.getCouponAmountById(cuponId);
					}else{
						cuponId=0;
					}

					if(user.getAccountBalance()==null){
						user.setAccountBalance(0D);
					}
					double useAmount=0;
					if(amount>0&&amount<=user.getAccountBalance()){
						useAmount=amount;
						user.setAccountBalance(user.getAccountBalance()-amount);
						userAccountBo.addNewUserAccount(userId, useAmount, "-", "余额支付订单", orderNum);
					}
					//获取当前的分成比例，交通补贴
					Properties someProperties = propertiesBo.getPointsProperties("scale");
					double splitRatio=0;
					if(someProperties!=null){
						splitRatio=getSecondBits(Double.valueOf(someProperties.getValue()));
					}
					double tracficAmount=subsidyBo.getMaxAmountTracficAmount();
					productPrice=productBo.getProductPriceById(productId);
					orderInfoBo.addNewOrder(orderNum, userId, doctorId, productId, serviceDate, serviceTime, cuponId, usePoints, useAmount, payMethod, productPrice, splitRatio, pointsAmount, tracficAmount);
					payAmount=getSecondBits(getSecondBits(getSecondBits(getSecondBits(productPrice)-getSecondBits(pointsAmount))-getSecondBits(couponAmount))-getSecondBits(useAmount));
					userInfoBo.updateUser(user);

					if(payAmount>0){//需要使用第三方的支付方式
						mes="在线支付";
						return "success";
					}else{
						payAmount=0;//不需要第三方的支付方式
						orderInfoBo.updateOrderByOrderNum(orderNum, "已接单");//更新订单状态
						//锁定医生的服务时间段
						if(doctorId != -1L){
							doctorProductBo.updateSomeDoctorServiceTime(doctorId, serviceDate, serviceTimes);
						}
						//是否存在有效的推荐奖励
						List<RecommentAwardRecord> someRecord =recommentAwardRecordBo.getSomeUserRecommentAwardRecord(userId, "N", "下单后");
						UserInfo newUser=userInfoBo.getUserById(userId);
						if(someRecord!=null){
							for(int i = 0;i<someRecord.size();i++){
								RecommentAwardRecord record=someRecord.get(i);
								if(record.getRecommendUserId()!=null&&record.getRecommendUserId()!=0){//推荐人是用户，双方均获得奖励
									long recommentUserId=record.getRecommendUserId();
									UserInfo recommentUser=userInfoBo.getUserById(recommentUserId);
									if(record.getPointsAmount()!=null&&record.getPointsAmount()!=0){
										//有积分奖励
										long recommentPoint=record.getPointsAmount();

										if(newUser.getAccountPoints()==null){
											newUser.setAccountPoints(0L);
										}
										newUser.setAccountPoints(newUser.getAccountPoints()+recommentPoint);
										userPointsBo.addNewUserPoints(userId, recommentPoint, "+", "推荐奖励");

										if(recommentUser.getAccountPoints()==null){
											recommentUser.setAccountPoints(0L);
										}
										recommentUser.setAccountPoints(recommentUser.getAccountPoints()+recommentPoint);
										userPointsBo.addNewUserPoints(recommentUserId, recommentPoint, "+", "推荐奖励");
									}

									if(record.getAmount()!=null&&record.getAmount()!=0){
										//有余额奖励
										double recommentAmount=record.getAmount();

										if(newUser.getAccountBalance()==null){
											newUser.setAccountBalance(0D);
										}
										newUser.setAccountBalance(newUser.getAccountBalance()+recommentAmount);
										userAccountBo.addNewUserAccount(userId, recommentAmount, "+", "推荐奖励", orderNum);

										if(recommentUser.getAccountBalance()==null){
											recommentUser.setAccountBalance(0D);
										}
										recommentUser.setAccountBalance(recommentUser.getAccountBalance()+recommentAmount);
										userAccountBo.addNewUserAccount(recommentUserId, recommentAmount, "+", "推荐奖励", orderNum);
									}

									if(record.getCouponId()!=null&&record.getCouponId()!=0){
										//有优惠券奖励
										long recommentCouponId=record.getCouponId();
										userCouponBo.addNewUserCoupon(0, userId, recommentCouponId);
										userCouponBo.addNewUserCoupon(0, recommentUserId, recommentCouponId);
									}
									userInfoBo.updateUser(newUser);
									userInfoBo.updateUser(recommentUser);
								}

								if(record.getRecommendDoctorId()!=null&&record.getRecommendDoctorId()!=0){//推荐人是医生，双方均获得奖励
									long recommendDoctorId=record.getRecommendDoctorId();
									DoctorInfo recommentDoctor=doctorInfoBo.getDoctorInfoByDoctorId(recommendDoctorId);
									
									if(record.getPointsAmount()!=null&&record.getPointsAmount()!=0){
										//有积分奖励
										long recommentPoint=record.getPointsAmount();

										if(newUser.getAccountPoints()==null){
											newUser.setAccountPoints(0L);
										}
										newUser.setAccountPoints(newUser.getAccountPoints()+recommentPoint);
										userPointsBo.addNewUserPoints(userId, recommentPoint, "+", "推荐奖励");
										
										if(recommentDoctor.getDoctorPoints()==null){
											recommentDoctor.setDoctorPoints(0L);
										}
										recommentDoctor.setDoctorPoints(recommentDoctor.getDoctorPoints()+recommentPoint);
										doctorPointsBo.addNewUserPoints(recommendDoctorId, recommentPoint, "+", "推荐奖励");
									}
									
									if(record.getAmount()!=null&&record.getAmount()!=0){
										//有余额奖励
										double recommentAmount=record.getAmount();

										if(newUser.getAccountBalance()==null){
											newUser.setAccountBalance(0D);
										}
										newUser.setAccountBalance(newUser.getAccountBalance()+recommentAmount);
										userAccountBo.addNewUserAccount(userId, recommentAmount, "+", "推荐奖励", orderNum);
										
										if(recommentDoctor.getDoctorBalance()==null){
											recommentDoctor.setDoctorBalance(0D);
										}
										recommentDoctor.setDoctorBalance(recommentDoctor.getDoctorBalance()+recommentAmount);
										doctorAccountBo.addNewDoctorAccount(recommendDoctorId, recommentAmount, "+", "推荐奖励");
									}
									
									if(record.getCouponId()!=null&&record.getCouponId()!=0){
										//有优惠券奖励
										long recommentCouponId=record.getCouponId();
										userCouponBo.addNewUserCoupon(0, userId, recommentCouponId);
									}
									
									userInfoBo.updateUser(newUser);
									doctorInfoBo.updateDoctorInstance(recommentDoctor);
								}
								
								//更新发放记录的更新时间
								recommentAwardRecordBo.updateRecommentAwardRecord(record);
							}
						}
						
						//消费返积分
						Properties PointsProperties = propertiesBo.getReturnPointsProperties("消费返积分");
						if(PointsProperties!=null){
							//long value=Long.valueOf(PointsProperties.getValue());
							Double value=Double.valueOf(PointsProperties.getValue());
							long returnPointsAmount=Math.round(productPrice/value);
							if(returnPointsAmount>0){
								UserInfo usr=userInfoBo.getUserById(userId);
								if(usr.getAccountPoints()==null){
									usr.setAccountPoints(0L);
								}
								usr.setAccountPoints(usr.getAccountPoints()+returnPointsAmount);
								userPointsBo.addNewUserPoints(userId, returnPointsAmount, "+", "消费返积分");
							}
						}
						mes="余额支付";
						return "success";
					}
				}
				mes="已预约";
				return "fail";
			}
			mes="未登录";
			return "fail";
		}
		//add by sjt 2015-10-14 11:06
		else if(action.equals("orderList")){
			if(ActionContext.getContext().getSession().get("userId")==null){
				mes="未登录";
				return "fail";
			}
			long userId=(Long)ActionContext.getContext().getSession().get("userId");
//			System.out.println(userId);
			someOrderList = new ArrayList<List>();
			someOrderList = orderInfoBo.getOrderListByUserId(userId);
			if(someOrderList==null){
				mes="无数据";
				return "success";
			}
			mes="成功";
			return "success";
		}
		else if(action.equals("orderDetail")){
			if(ActionContext.getContext().getSession().get("userId")==null){
				mes="未登录";
				return "fail";
			}
			long userId=(Long)ActionContext.getContext().getSession().get("userId");
			String productItemIds = orderInfoBo.getProductItemIds(orderId);
			someProductItemShowNamesList=productItemBo.getSomeProductItemShowNameList(productItemIds);
			if(!productItemIds.equals("")&&!productItemIds.equals(null)){
				itemNameList = new ArrayList<String>();
				String[] itemIds = productItemIds.split("::");
				for(int i=0;i<itemIds.length;i++){
					String itemName = orderInfoBo.getItemNameByItemId(Long.valueOf(itemIds[i]));
					if(!itemName.equals("")&&!itemName.equals(null)){
						itemNameList.add(itemName);
					}
				}
			}
			orderDetail = new ArrayList<List>();
			orderDetail = orderInfoBo.getOrderDetailById(orderId);
			mes="成功";
			return "success";
		} else if(action.equals("getOrderResult")){
			//实现获取订单中包含的项目以及结果
			HttpServletRequest request = ServletActionContext.getRequest();
			System.out.println(CalculationMethod.getIpAddr(request)+"join in");
			Long userId=(Long)ActionContext.getContext().getSession().get("userId");
			if(userId!=null){
				OrderInfo someOrder=orderInfoBo.getOrderInstanceByOrderNum(orderNum);
				String orderStatus=someOrder.getOrderStatus();
				if("已完成".equals(orderStatus)||"已评价".equals(orderStatus)||"已确认".equals(orderStatus)){
					someOrderResultList=orderResultsBo.getSomeOrderResult(orderNum, userId);
					if(someOrderResultList!=null){
						mes="操作成功";
						return "success";
					}
					mes="无结果";
					return "fail";
				}
				mes="错误操作";
				return "fail";
			}
			mes="未登录";
			return "fail";
		}
		//add by sjt 2015-10-14 11:06
		return "fail";
	}

	double getSecondBits(double price){//保留两位整数的方法
		return (double)Math.round(price*100)/100;
	}
	
	private long getDoctorIdMethod(OrderInfo orderInfo){
		/*
		 * 在该部分添加派医生的逻辑
		 * */
		//这个OrderInfo需要传参数传进来，里面要有getBespokeDate，bespokeTime（13:00-14:00）,getProductId,getUserId
		DoctorInfo doctor = this.sendDoctorService.autoSendDoctorByRule(orderInfo);
		if(doctor != null){
			return doctor.getId();
		}
		return -1L;
	}

	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @return the someUser
	 */
	public UserInfo getSomeUser() {
		return someUser;
	}

	/**
	 * @return the someDoctor
	 */
	public DoctorInfo getSomeDoctor() {
		return someDoctor;
	}

	/**
	 * @return the productSmallPic
	 */
	public String getProductSmallPic() {
		return productSmallPic;
	}

	/**
	 * @return the productPrice
	 */
	public double getProductPrice() {
		return productPrice;
	}

	/**
	 * @return the userCupnList
	 */
	public List<Coupon> getUserCupnList() {
		return userCupnList;
	}

	/**
	 * @return the pointsProperties
	 */
	public Properties getPointsProperties() {
		return pointsProperties;
	}

	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the someProductItemsList
	 */
	public List<String> getSomeProductItemsList() {
		return someProductItemsList;
	}

	/**
	 * @return the payAmount
	 */
	public double getPayAmount() {
		return payAmount;
	}

	/**
	 * @return the orderNum
	 */
	public String getOrderNum() {
		return orderNum;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}

	/**
	 * @param serviceDate the serviceDate to set
	 */
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	/**
	 * @param serviceTime the serviceTime to set
	 */
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	/**
	 * @param cuponId the cuponId to set
	 */
	public void setCuponId(long cuponId) {
		this.cuponId = cuponId;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(long points) {
		this.points = points;
	}

	/**
	 * @param payMethod the payMethod to set
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public long getProductId() {
		return productId;
	}

	public List<List> getSomeOrderList() {
		return someOrderList;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public List<List> getOrderDetail() {
		return orderDetail;
	}

	public List<String> getItemNameList() {
		return itemNameList;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return the someOrderResultList
	 */
	public List<Object[]> getSomeOrderResultList() {
		return someOrderResultList;
	}
}
