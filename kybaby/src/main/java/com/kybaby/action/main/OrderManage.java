package com.kybaby.action.main;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bcloud.msg.http.HttpSender;
import com.kybaby.action.BaseAction;
import com.kybaby.bo.DoctorInfoBo;
import com.kybaby.domain.AssessmentTag;
import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorAssessmentTag;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Evaluate;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.Properties;
import com.kybaby.domain.RecommentAwardRecord;
import com.kybaby.domain.UserCoupon;
import com.kybaby.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:OrderManage
 * @Description:订单管理相关的
 * @author Hoolee
 * @date 2015年10月9日下午3:50:12
 */
public class OrderManage extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private String orderNum;//前端提供的订单编号
	private String orderStatus;//页面传来的订单状态
	private Long orderId;//页面传来的订单ID
	private String active;//页面传来的行为
	private List<AssessmentTag> assessmentTagList;
	private String someIds;
	
	String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
	String account = "003024";//账号
	String pswd = "Qiyiguo1919";//密码

	//String mobiles = "13541280713,15208361596";//手机号码，多个号码使用","分割
	//String content = "i love you  forever";//短信内容
	String contecnt="";
	boolean needstatus = true;//是否需要状态报告，需要true，不需要false
	String product = "";//产品ID(不用填写)
	String extno = "";//扩展码(不用填写)
	
	public String execute(){
		if(action.equals("paySuccess")){
			System.out.println("PaySuccess is begining...");
			OrderInfo newOrder=orderInfoBo.getOrderInstanceByOrderNum(orderNum);
			orderInfoBo.updateOrderByOrderNum(orderNum, "已接单");//更新订单状态
			if(newOrder!=null){
				String serviceDate=newOrder.getBespokeDate();
				String serviceTime=newOrder.getBespokeTime();
				String[] serviceTimeList=serviceTime.split(":");
				long startTime=Long.valueOf(serviceTimeList[0]);
				long productId=newOrder.getProductId();
				long timeLength=productBo.getProductServiceTimeById(productId);
				String serviceTimes="(";
				for(long i=0;i<=timeLength;i++){
					serviceTimes=serviceTimes+(Long.valueOf(startTime)+i)+",";
				}
				int strLen=serviceTimes.length();
				serviceTimes=serviceTimes.substring(0,strLen-1);
				serviceTimes=serviceTimes+")";
				long doctorId=newOrder.getDoctorId();
				doctorProductBo.updateSomeDoctorServiceTime(doctorId, serviceDate, serviceTimes);
				long userId=newOrder.getUserId();
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
					double productPrice=newOrder.getTotalPrice();
					//long returnPointsAmount=(long) ((productPrice-productPrice%value)/value);
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
			}
			mes="操作成功";
			return "success";
		}
		
		//add by sjt 2015-10-20 09:23
		if(action.equals("change")){
			if(ActionContext.getContext().getSession().get("userId")==null){
				mes="未登录";
				return "fail";
			}
			Long userId = (Long) ActionContext.getContext().getSession().get("userId");
			if(orderStatus.equals("取消")){
				OrderInfo orderInf= orderInfoBo.getOneOrderByOrderId(orderId);
				if(orderInf!=null){
					if(orderInf.getCouponId()!=0){
						UserCoupon userCoupon = orderInfoBo.getCouponByCouponIdAndUserId(orderInf.getCouponId(),userId);
						if(userCoupon!=null){
							userCoupon.setCouponStatus("N");
							orderInfoBo.updateUserCoupon(userCoupon);
						}
					}
					if(orderInf.getUsePoints()!=0){
						UserInfo userInf = orderInfoBo.getUserInfoByUserId(userId);
						if(userInf!=null){
							userInf.setAccountPoints(userInf.getAccountPoints()+orderInf.getUsePoints());
							orderInfoBo.updateUserInfo(userInf);
							//update by HooLee 2015年11月1日13:53:37 取消订单返还积分应该有积分记录
							userPointsBo.addNewUserPoints(userId, orderInf.getUsePoints(), "+", "取消订单返还积分");
						}
					}
					if(orderInf.getUseRemainBalance()!=0){
						UserInfo userInf = orderInfoBo.getUserInfoByUserId(userId);
						if(userInf!=null){
							userInf.setAccountBalance(userInf.getAccountBalance()+orderInf.getUseRemainBalance());
							orderInfoBo.updateUserInfo(userInf);
							userAccountBo.addNewUserAccount(userId, orderInf.getUseRemainBalance(), "+", "取消订单返还金额", orderNum);
							
						}
					}
					orderInf.setOrderStatus("用户取消");
					orderInfoBo.updateOrderInfo(orderInf);
					
					Long doctorId=orderInf.getDoctorId();
					if(doctorId != null){
						 DoctorInfo doctor=doctorInfoBo.getDoctorInfoByDoctorId(doctorId);
						    String doctorName=doctor.getDoctorName();
						    String orderNum=orderInf.getOrderNum();
						    //用户取消了订单需要将医生被预约的时间的状态重新置为N
						    long productId=orderInf.getProductId();
						    long service=productBo.getProductServiceTimeById(productId);
						    String serviceTime=orderInf.getBespokeTime();
						    String serviceDate=orderInf.getBespokeDate();
						    String[] timeArr=serviceTime.split(":");
						    serviceTime=timeArr[0];
						    String serviceTimes="(";
							for(long i=0;i<=service;i++){
								serviceTimes=serviceTimes+(Long.valueOf(serviceTime)+i)+",";
							}
							int strLen=serviceTimes.length();
							serviceTimes=serviceTimes.substring(0,strLen-1);
							serviceTimes=serviceTimes+")";
						    doctorProductBo.returnSomeDoctorTime(doctorId, serviceDate, serviceTimes);
						    
							//取消订单需要给医生发送短信，update by HooLee 2015年11月1日14:03:56
							contecnt="亲爱的"+doctorName+"医生，您订单号为"+orderNum+"的订单，用户已取消！";
							String userPhone=doctor.getDoctorPhone();
							System.out.println("content=="+contecnt);
							try {
								String returnString = HttpSender.send(uri, account, pswd, userPhone, contecnt, needstatus, product, extno);
								System.out.println(returnString);
							} catch (Exception e) {
								e.printStackTrace();
							}
					}
					mes="成功";
					return "success";
				}
			}
			if(orderStatus.equals("确认")){
				OrderInfo orderInf= orderInfoBo.getOneOrderByOrderId(orderId);
				if(orderInf!=null){
					DoctorInfo docInf = orderInfoBo.getDoctorInfoById(orderInf.getDoctorId());
					if(docInf!=null){
						DoctorInfo newDoctorInfo = new DoctorInfo();
						Date date = new Date();
//						DateFormat df = DateFormat.getDateTimeInstance();//可以精确到时分秒
						DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time=format.format(date);
						docInf.setDoctorBalance(docInf.getDoctorBalance()+orderInf.getTotalPrice()*orderInf.getSplitRatio()+orderInf.getTrafficSubsidyMoney());
						DoctorAccount doctorAccount = new DoctorAccount();
						doctorAccount.setAccountDesc("儿保服务");
						doctorAccount.setAmount(orderInf.getTotalPrice()*orderInf.getSplitRatio()+orderInf.getTrafficSubsidyMoney());
						doctorAccount.setDoctorId(orderInf.getDoctorId());
						doctorAccount.setType("+");
						doctorAccount.setUpdateTime(time);
						orderInfoBo.updateDoctorInfo(docInf);
						orderInfoBo.saveDoctorAccount(doctorAccount);
					}
					orderInf.setOrderStatus("已确认");
					orderInfoBo.updateOrderInfo(orderInf);
					mes="成功";
					return "success";
				}
			}
			mes="失败";
			return "fail";
		}
		if(action.equals("assess")){
			if(active.equals("tag")){
				assessmentTagList = new ArrayList<AssessmentTag>();
				assessmentTagList = orderInfoBo.getAssessmentTag();
				mes="成功";
				return "success";
			}
			if(active.equals("submite")){
				OrderInfo ordInfo = orderInfoBo.getOneOrderByOrderId(orderId);
				System.out.println("orderId=="+orderId);
				if(ordInfo!=null){
					DoctorInfo doctorInfo = orderInfoBo.getDoctorInfoById(ordInfo.getDoctorId());
					if(doctorInfo!=null){
						String[] someId = someIds.split("::");
						String[] stars = someId[0].split("&");
						String[] tagIds = someId[1].split("@");
						Evaluate evaluate = new Evaluate();
						evaluate.setComments("");
						evaluate.setContent("");
						evaluate.setDoctorId(doctorInfo.getId());
						evaluate.setDutyStarLevel(Long.valueOf(stars[1]));
						evaluate.setOnTimeStarLevel(Long.valueOf(stars[2]));
						evaluate.setOrderNum(ordInfo.getOrderNum());
						evaluate.setServiceStarLevel(Long.valueOf(stars[0]));
						
						long linshi1 = (doctorInfo.getSeiviceStarTotal()+Long.valueOf(stars[0]))/(doctorInfo.getSeiviceStarHitCount()+1);
						long linshi2 = (doctorInfo.getDutyStarTotal()+Long.valueOf(stars[1]))/(doctorInfo.getDutyStarLevelHitCount()+1);
						long linshi3 = (doctorInfo.getOnTimeStarTotal()+Long.valueOf(stars[2]))/(doctorInfo.getOnTimeStarLevelHitCount()+1);
						doctorInfo.setSeiviceStarHitCount(doctorInfo.getSeiviceStarHitCount()+1);
						doctorInfo.setDutyStarLevelHitCount(doctorInfo.getDutyStarLevelHitCount()+1);
						doctorInfo.setOnTimeStarLevelHitCount(doctorInfo.getOnTimeStarLevelHitCount()+1);
						doctorInfo.setSeiviceStarTotal(doctorInfo.getSeiviceStarTotal()+Long.valueOf(stars[0]));
						doctorInfo.setDutyStarTotal(doctorInfo.getDutyStarTotal()+Long.valueOf(stars[1]));
						doctorInfo.setOnTimeStarTotal(doctorInfo.getOnTimeStarTotal()+Long.valueOf(stars[2]));
						String zhonghe1 = String.valueOf(new BigDecimal(linshi1).setScale(0, BigDecimal.ROUND_HALF_UP));
						String zhonghe2 = String.valueOf(new BigDecimal(linshi2).setScale(0, BigDecimal.ROUND_HALF_UP));
						String zhonghe3 = String.valueOf(new BigDecimal(linshi3).setScale(0, BigDecimal.ROUND_HALF_UP));
						doctorInfo.setSeiviceStarLevel(Long.valueOf(zhonghe1));
						doctorInfo.setDutyStarLevel(Long.valueOf(zhonghe2));
						doctorInfo.setOnTimeStarLevel(Long.valueOf(zhonghe3));
						orderInfoBo.updateDoctorInfo(doctorInfo);
						String someTagId = "";
						for(int i=0;i<tagIds.length;i++){
							if(i==tagIds.length-1){
								someTagId += tagIds[i];
							}else{
								someTagId += tagIds[i]+"::";
							}
							DoctorAssessmentTag doctorAssessmentTag = orderInfoBo.getDoctorAssessmentTagById(Long.valueOf(tagIds[i]),ordInfo.getDoctorId());
							if(doctorAssessmentTag!=null){
								doctorAssessmentTag.setHitCount(doctorAssessmentTag.getHitCount()+1);
								orderInfoBo.updateDoctorAssessmentTag(doctorAssessmentTag);
							}else{
								DoctorAssessmentTag doctAssessmentTag = new DoctorAssessmentTag();
								doctAssessmentTag.setComments("");
								doctAssessmentTag.setDoctorId(ordInfo.getDoctorId());
								doctAssessmentTag.setHitCount(1L);
								doctAssessmentTag.setTagId(Long.valueOf(tagIds[i]));
								orderInfoBo.saveDoctorAssessmentTag(doctAssessmentTag);
							}
						}
						evaluate.setTagIds(someTagId);
						evaluate.setUserId(ordInfo.getUserId());
						orderInfoBo.saveEvaluate(evaluate);
						ordInfo.setOrderStatus("已评价");
						orderInfoBo.updateOrderInfo(ordInfo);
						mes="成功";
						return "success";
					}
					mes="无数据";
					return "fail";
				}
				mes="无数据";
				return "fail";
			}
		}
		//add by sjt 2015-10-20 09:23
		return "fail";
	}

	public String getMes() {
		return mes;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public List<AssessmentTag> getAssessmentTagList() {
		return assessmentTagList;
	}

	public void setSomeIds(String someIds) {
		this.someIds = someIds;
	}
	
}
