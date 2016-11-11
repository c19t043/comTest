package com.kybaby.action.main;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.BabyBasicData;
import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.DoctorPoints;
import com.kybaby.domain.HealthPath;
import com.kybaby.domain.HealthPlan;
import com.kybaby.domain.HealthPlanRemind;
import com.kybaby.domain.ItemResult;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.OrderResults;
import com.kybaby.domain.Product;
import com.kybaby.domain.ProductItem;
import com.kybaby.domain.RecommentAwardRecord;
import com.kybaby.domain.UserAccount;
import com.kybaby.domain.UserCoupon;
import com.kybaby.domain.UserInfo;
import com.kybaby.domain.UserPoints;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author sujiantang
 *
 */
public class OrderManage extends BaseAction{
	
/**
	 * @return the someItemShowNameList
	 */
	public List<String> getSomeItemShowNameList() {
		return someItemShowNameList;
	}

	//	private List<OrderInfo> orderInfoList;
	private OrderInfo orderInfo;
	private DoctorInfo doctorInfo;
	private String mes;
	private List<List> someOrderList;
	private Long orderId;
	private List<List> orderDetailList;
	private BabyBasicData babyBasicData;
	private String itemIds;
	private ProductItem productItem;
	private List<HealthPlan> healthPlanList;
	private List<ItemResult> itemResultList;
	private List<List> healthPathList;
	private List<String> someItemList;
	private String orderStatus;
	private Long apgarOfFiveMinutes;
	private Long apgarOfOneMinute;
	private Long apgarOfTenMinutes;
	private String babyBirthday;
	private String babyName;
	private String babySex;
	private String birthBodyLength;
	private String birthWeight;
	private String bornHospital;
	private String bornWay;
	private String degreeOfFatherEducation;
	private String degreeOfMotherEducation;
//	private Long doctorId;
	private String drugAllergyHistory;
	private String embryoNumber;
	private String familyHistory;
	private String fatherName;
	private String geneticHeight;
	private Long gestationalAge;
	private String heightOfFather;
	private String heightOfMother;
	private String mainCaregivers;
	private String motherName;
	private String nation;
	private String origin;
	private Long para;
	private Long parity;
	private String permanentAddress;
	private String phoneOfFather;
	private String phoneOfMother;
	private String twinBrotherAndSister;
//	private Long userId;
	private String data_str;
	private String time;
	private List<String> someItemShowNameList;
	
	@Override
	public String execute(){
		new DoctorInfo();
		Date date = new Date();
//		DateFormat df = DateFormat.getDateTimeInstance();//可以精确到时分秒
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time=format.format(date);
		if(action.equals("all")){
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo==null){
				mes="请登录";
				return "fail";
			}
			someOrderList = new ArrayList<List>();
			//someOrderList = orderBo.getOrderListByDoctorId(doctorInfo.getId());
			/*
			 * update by HooLee 
			 * 2015年10月28日21:13:48 
			 * 对于用户没有付款成功的订单，在医生端是不显示的
			 * */
			someOrderList=orderBo.newGetOrderListByDoctorId(doctorInfo.getId());
			mes="成功";
			return "success";
		}
		if(action.equals("detail")){
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo==null){
				mes="请登录";
				return "fail";
			}
			orderDetailList = new ArrayList<List>();
			orderDetailList = orderBo.getOrderDetailByOrderId(orderId);
			if(orderDetailList==null){
				mes="无数据";
				return "fail";
			}
			Long userId = orderBo.getUserIdByOrderId(orderId);
			babyBasicData = orderBo.getBabyBasicDataByUserId(userId);
			if(babyBasicData==null){
				mes="第一次儿保";
				return "fail";
			}
			mes="成功";
			return "success";
		}
		if(action.equals("item")){
			if(itemIds!=""&&itemIds!=null){
				itemResultList = new ArrayList<ItemResult>();
				healthPlanList = new ArrayList<HealthPlan>();
				healthPathList = new ArrayList<List>();
				someItemList = new ArrayList<String>();
				someItemShowNameList=new ArrayList<String>();//反馈给前端的显示名称列表
				
				//added by zhong at 2015-11-01, DUMMY项目放在后面
				String[] itemIdListTemp = itemIds.split("::");
				String sqlItemId="";
				for(int k=0;k<itemIdListTemp.length;k++){
					sqlItemId+=","+itemIdListTemp[k];
				}
				
				String[] itemIdList = null;
				List<Long> sIdList;
				sqlItemId="("+sqlItemId.substring(1)+")";
				sIdList=orderBo.getOrderedItemId(sqlItemId);
				
				if(sIdList!=null){
					itemIdList =  new String[sIdList.size()];
						/*int i=0;
						for(Object[] object:sIdList){
				    	   itemIdList[i]=(String)object[0];
				    	   i++;
				       }*/
					for(int i=0;i<sIdList.size();i++){
						itemIdList[i]=String.valueOf(sIdList.get(i));
					}
				}
				
				
				
				for(int i=0;i<itemIdList.length;i++){
					productItem = orderBo.getItemById(Long.valueOf(itemIdList[i]));
					if(productItem!=null){
						String someItem = productItem.getItemName();
						someItemShowNameList.add(productItem.getComments());//增加显示项目的显示名称 add by HooLee 2015年10月30日08:59:33
						someItem += "::"+productItem.getHandleUrl();
						someItem += "::"+productItem.getId();
						someItemList.add(someItem);
						ItemResult itemResul= orderBo.getItemResultById(productItem.getItemResultId());
						itemResultList.add(itemResul);
						HealthPlan healthPlan = orderBo.getHealthPlanById(productItem.getHealthPlanId());
						if(healthPlan!=null){
							healthPlanList.add(healthPlan);
							if(healthPlan.getHealthPathIds()!=""&&healthPlan.getHealthPathIds()!=null){
								String[] pathIds = healthPlan.getHealthPathIds().split("::");
								List<HealthPath> someHealthPathList = new ArrayList<HealthPath>();
								for(int j=0;j<pathIds.length;j++){
									HealthPath oneHealthPathList= orderBo.getHealthPathById(Long.valueOf(pathIds[j]));
									if(oneHealthPathList!=null){
										someHealthPathList.add(oneHealthPathList);
									}else{
										someHealthPathList.add(null);
									}
								}
								healthPathList.add(someHealthPathList);
							}else{
								healthPathList.add(null);
							}
							
							
						}else{
							healthPlanList.add(null);
							healthPathList.add(null);
						}
						
					}
					
				}
				mes="成功";
				return "success";
			}
			mes="无项目";
			return "fail";
		}
		if(action.equals("cancel")){
			OrderInfo orderInf = orderBo.getOrderByOrderId(orderId);
			if(orderInf!=null){
				orderInf.setOrderStatus("医生取消");
				orderInf.setUpdateTime(time);
				orderBo.update(orderInf);
				mes="成功";
				return "success";
			}
			mes="无数据";
			return "fail";
		}
		if(action.equals("change")){
			if(orderStatus.equals("签到")){
				OrderInfo orderInf = orderBo.getOrderByOrderId(orderId);
				if(orderInf!=null){
					BabyBasicData babyBasicData = new BabyBasicData();
					babyBasicData.setApgarOfFiveMinutes(apgarOfFiveMinutes);
					babyBasicData.setApgarOfOneMinute(apgarOfOneMinute);
					babyBasicData.setApgarOfTenMinutes(apgarOfTenMinutes);
					babyBasicData.setBabyBirthday(babyBirthday);
					babyBasicData.setBabyName(babyName);
					babyBasicData.setBabySex(babySex);
					babyBasicData.setBirthBodyLength(birthBodyLength);
					babyBasicData.setBirthWeight(birthWeight);
					babyBasicData.setBornHospital(bornHospital);
					babyBasicData.setBornWay(bornWay);
					babyBasicData.setDegreeOfFatherEducation(degreeOfFatherEducation);
					babyBasicData.setDegreeOfMotherEducation(degreeOfMotherEducation);
					babyBasicData.setDoctorId(orderInf.getDoctorId());
					babyBasicData.setDrugAllergyHistory(drugAllergyHistory);
					babyBasicData.setEmbryoNumber(embryoNumber);
					babyBasicData.setFamilyHistory(familyHistory);
					babyBasicData.setFatherName(fatherName);
					babyBasicData.setGeneticHeight(geneticHeight);
					babyBasicData.setGestationalAge(gestationalAge);
					babyBasicData.setHeightOfFather(heightOfFather);
					babyBasicData.setHeightOfMother(heightOfMother);
					babyBasicData.setMainCaregivers(mainCaregivers);
					babyBasicData.setMotherName(motherName);
					babyBasicData.setNation(nation);
					babyBasicData.setOrigin(origin);
					babyBasicData.setPara(para);
					babyBasicData.setParity(parity);
					babyBasicData.setPermanentAddress(permanentAddress);
					babyBasicData.setPhoneOfFather(phoneOfFather);
					babyBasicData.setPhoneOfMother(phoneOfMother);
					babyBasicData.setTwinBrotherAndSister(twinBrotherAndSister);
					babyBasicData.setUserId(orderInf.getUserId());
					orderBo.saveBabyBasicData(babyBasicData);
					orderInf.setOrderStatus("已签到");
					orderInf.setUpdateTime(time);
					orderBo.update(orderInf);
					doctorInfo = orderBo.getDoctorByDoctorId(orderInf.getDoctorId());
					doctorInfo.setVisitedTimes(doctorInfo.getVisitedTimes()+1);
					orderBo.updateDoctorInfo(doctorInfo);
					Product product = orderBo.getProductById(orderInf.getProductId());
					product.setProductUseTime(product.getProductUseTime()+1);
					orderBo.updateProduct(product);
					List<RecommentAwardRecord> recommentAwardRecordUser = orderBo.getRecommentAwardRecordByUserId(orderInf.getUserId());
					List<RecommentAwardRecord> recommentAwardRecordDoctor = orderBo.getRecommentAwardRecordByDoctorId(orderInf.getDoctorId());
					if(recommentAwardRecordUser==null&&recommentAwardRecordDoctor==null){
						mes="无奖励";
						return "success";
					}
					date = new Date();
//					DateFormat df = DateFormat.getDateTimeInstance();//可以精确到时分秒
					format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String awardTime = format.format(date);
					if(recommentAwardRecordUser!=null){
						for(int i=0;i<recommentAwardRecordUser.size();i++){
							if(recommentAwardRecordUser.get(i).getRecommendUserId()!=null&&recommentAwardRecordUser.get(i).getRecommendUserId()!=0){
								UserInfo userInfo = orderBo.getUserByUserId(recommentAwardRecordUser.get(i).getRecommendUserId());
								UserInfo beenUserInfo = orderBo.getUserByUserId(orderInf.getUserId());
								if(userInfo!=null&&beenUserInfo!=null){
									if(recommentAwardRecordUser.get(i).getAmount()!=null&&recommentAwardRecordUser.get(i).getAmount()!=0){
										userInfo.setAccountBalance(userInfo.getAccountBalance()+recommentAwardRecordUser.get(i).getAmount());
										beenUserInfo.setAccountBalance(beenUserInfo.getAccountBalance()+recommentAwardRecordUser.get(i).getAmount());
										UserAccount userAccount = new UserAccount();
										userAccount.setAccountDesc("推荐奖励");
										userAccount.setAmount(recommentAwardRecordUser.get(i).getAmount());
										userAccount.setType("+");
										userAccount.setUpdateTime(Timestamp.valueOf(awardTime));
										userAccount.setUserId(userInfo.getId());
										orderBo.saveUserAccount(userAccount);
										UserAccount userAccount1 = new UserAccount();
										userAccount1.setAccountDesc("推荐奖励");
										userAccount1.setAmount(recommentAwardRecordUser.get(i).getAmount());
										userAccount1.setType("+");
										userAccount1.setUpdateTime(Timestamp.valueOf(awardTime));
										userAccount1.setUserId(beenUserInfo.getId());
										orderBo.saveUserAccount(userAccount1);
									}
									if(recommentAwardRecordUser.get(i).getPointsAmount()!=null&&recommentAwardRecordUser.get(i).getPointsAmount()!=0){
										userInfo.setAccountPoints(userInfo.getAccountPoints()+recommentAwardRecordUser.get(i).getPointsAmount());
										beenUserInfo.setAccountPoints(beenUserInfo.getAccountPoints()+recommentAwardRecordUser.get(i).getPointsAmount());
										UserPoints userPoints = new UserPoints();
										userPoints.setPoints(recommentAwardRecordUser.get(i).getPointsAmount());
										userPoints.setPointsDes("推荐奖励");
										userPoints.setType("+");
										userPoints.setUpdateTime(Timestamp.valueOf(awardTime));
										userPoints.setUserId(userInfo.getId());
										orderBo.saveUserPoints(userPoints);
										UserPoints userPoints1 = new UserPoints();
										userPoints1.setPoints(recommentAwardRecordUser.get(i).getPointsAmount());
										userPoints1.setPointsDes("推荐奖励");
										userPoints1.setType("+");
										userPoints1.setUpdateTime(Timestamp.valueOf(awardTime));
										userPoints1.setUserId(userInfo.getId());
										orderBo.saveUserPoints(userPoints1);
									}
									if(recommentAwardRecordUser.get(i).getCouponId()!=null&&recommentAwardRecordUser.get(i).getCouponId()!=0){
										Long activityId = orderBo.getActivityIdByCouponId(recommentAwardRecordUser.get(i).getCouponId());
										UserCoupon userCoupon = new UserCoupon();
										userCoupon.setActivityId(activityId);
										userCoupon.setComments("");
										userCoupon.setCouponId(recommentAwardRecordUser.get(i).getCouponId());
										userCoupon.setCouponStatus("N");
										userCoupon.setUserId(userInfo.getId());
										orderBo.saveUserCoupon(userCoupon);
										userCoupon.setActivityId(activityId);
										userCoupon.setComments("");
										userCoupon.setCouponId(recommentAwardRecordUser.get(i).getCouponId());
										userCoupon.setCouponStatus("N");
										userCoupon.setUserId(beenUserInfo.getId());
										orderBo.saveUserCoupon(userCoupon);
									}
									recommentAwardRecordUser.get(i).setAwardTime(awardTime);
									recommentAwardRecordUser.get(i).setIsGrant("Y");
									orderBo.updateAwared(recommentAwardRecordUser.get(i));
									orderBo.updateUserInfo(userInfo);
									orderBo.updateUserInfo(beenUserInfo);
								}
								
							}
							if(recommentAwardRecordUser.get(i).getRecommendDoctorId()!=null&&recommentAwardRecordUser.get(i).getRecommendDoctorId()!=0){
								DoctorInfo dInfo = orderBo.getDoctorByDoctorId(recommentAwardRecordUser.get(i).getRecommendDoctorId());
								UserInfo beenUserInfo = orderBo.getUserByUserId(orderInf.getUserId());
								if(dInfo!=null&&beenUserInfo!=null){
									if(recommentAwardRecordUser.get(i).getAmount()!=null&&recommentAwardRecordUser.get(i).getAmount()!=0){
										dInfo.setDoctorBalance(dInfo.getDoctorBalance()+recommentAwardRecordUser.get(i).getAmount());
										beenUserInfo.setAccountBalance(beenUserInfo.getAccountBalance()+recommentAwardRecordUser.get(i).getAmount());
										UserAccount userAccount = new UserAccount();
										userAccount.setAccountDesc("推荐奖励");
										userAccount.setAmount(recommentAwardRecordUser.get(i).getAmount());
										userAccount.setType("+");
										userAccount.setUpdateTime(Timestamp.valueOf(awardTime));
										userAccount.setUserId(beenUserInfo.getId());
										orderBo.saveUserAccount(userAccount);
										DoctorAccount doctorAccount = new DoctorAccount();
										doctorAccount.setAccountDesc("推荐奖励");
										doctorAccount.setAmount(recommentAwardRecordUser.get(i).getAmount());
										doctorAccount.setDoctorId(dInfo.getId());
										doctorAccount.setType("+");
										doctorAccount.setUpdateTime(awardTime);
										orderBo.saveDoctorAccount(doctorAccount);
									}
									if(recommentAwardRecordUser.get(i).getPointsAmount()!=null&&recommentAwardRecordUser.get(i).getPointsAmount()!=0){
										dInfo.setDoctorPoints(dInfo.getDoctorPoints()+recommentAwardRecordUser.get(i).getPointsAmount());
										beenUserInfo.setAccountPoints(beenUserInfo.getAccountPoints()+recommentAwardRecordUser.get(i).getPointsAmount());
				 						UserPoints userPoints = new UserPoints();
				 						userPoints.setPoints(recommentAwardRecordUser.get(i).getPointsAmount());
				 						userPoints.setPointsDes("推荐奖励");
				 						userPoints.setType("+");
				 						userPoints.setUpdateTime(Timestamp.valueOf(awardTime));
				 						userPoints.setUserId(beenUserInfo.getId());
				 						orderBo.saveUserPoints(userPoints);
				 						DoctorPoints doctorPoints = new DoctorPoints();
				 						doctorPoints.setDoctorId(doctorInfo.getId());
				 						doctorPoints.setPoints(recommentAwardRecordUser.get(i).getPointsAmount());
				 						doctorPoints.setPointsDes("推荐奖励");
				 						doctorPoints.setType("+");
				 						doctorPoints.setUpdateTime(awardTime);
				 						orderBo.saveDoctorPoints(doctorPoints);
									}
									if(recommentAwardRecordUser.get(i).getCouponId()!=null&&recommentAwardRecordUser.get(i).getCouponId()!=0){
										Long activityId = orderBo.getActivityIdByCouponId(recommentAwardRecordUser.get(i).getCouponId());
										UserCoupon userCoupon = new UserCoupon();
										userCoupon.setActivityId(activityId);
										userCoupon.setComments("");
										userCoupon.setCouponId(recommentAwardRecordUser.get(i).getCouponId());
										userCoupon.setCouponStatus("N");
										userCoupon.setUserId(beenUserInfo.getId());
										orderBo.saveUserCoupon(userCoupon);
									}
									recommentAwardRecordUser.get(i).setAwardTime(awardTime);
									recommentAwardRecordUser.get(i).setIsGrant("Y");
									orderBo.updateAwared(recommentAwardRecordUser.get(i));
									orderBo.updateUserInfo(beenUserInfo);
									orderBo.updateDoctorInfo(doctorInfo);
								}
							}
						}
					}
					if(recommentAwardRecordDoctor!=null){
						for(int j=0;j<recommentAwardRecordDoctor.size();j++){
							if(recommentAwardRecordDoctor.get(j).getRecommendDoctorId()!=null&&recommentAwardRecordDoctor.get(j).getRecommendDoctorId()!=0){
								DoctorInfo drInfo = orderBo.getDoctorByDoctorId(recommentAwardRecordDoctor.get(j).getRecommendDoctorId());
								DoctorInfo beendoctorInfo = orderBo.getDoctorByDoctorId(orderInf.getDoctorId());
								if(drInfo!=null&&beendoctorInfo!=null){
									if(recommentAwardRecordDoctor.get(j).getAmount()!=null&&recommentAwardRecordDoctor.get(j).getAmount()!=0){
										drInfo.setDoctorBalance(drInfo.getDoctorBalance()+recommentAwardRecordDoctor.get(j).getAmount());
										beendoctorInfo.setDoctorBalance(drInfo.getDoctorBalance()+recommentAwardRecordDoctor.get(j).getAmount());
										DoctorAccount doctorAccount = new DoctorAccount();
										doctorAccount.setAccountDesc("推荐奖励");
										doctorAccount.setAmount(recommentAwardRecordDoctor.get(j).getAmount());
										doctorAccount.setDoctorId(drInfo.getId());
										doctorAccount.setType("+");
										doctorAccount.setUpdateTime(awardTime);
										orderBo.saveDoctorAccount(doctorAccount);
										DoctorAccount doctorAccount1 = new DoctorAccount();
										doctorAccount1.setAccountDesc("推荐奖励");
										doctorAccount1.setAmount(recommentAwardRecordDoctor.get(j).getAmount());
										doctorAccount1.setDoctorId(beendoctorInfo.getId());
										doctorAccount1.setType("+");
										doctorAccount1.setUpdateTime(awardTime);
										orderBo.saveDoctorAccount(doctorAccount1);
									}
									if(recommentAwardRecordDoctor.get(j).getPointsAmount()!=null&&recommentAwardRecordDoctor.get(j).getPointsAmount()!=0){
										drInfo.setDoctorPoints(drInfo.getDoctorPoints()+recommentAwardRecordDoctor.get(j).getPointsAmount());
										beendoctorInfo.setDoctorBalance(drInfo.getDoctorBalance()+recommentAwardRecordDoctor.get(j).getAmount());
										DoctorPoints doctorPoints = new DoctorPoints();
										doctorPoints.setDoctorId(drInfo.getId());
										doctorPoints.setPoints(recommentAwardRecordDoctor.get(j).getPointsAmount());
										doctorPoints.setPointsDes("推荐奖励");
										doctorPoints.setType("+");
										doctorPoints.setUpdateTime(awardTime);
										orderBo.saveDoctorPoints(doctorPoints);
										DoctorPoints doctorPoints1 = new DoctorPoints();
										doctorPoints1.setDoctorId(beendoctorInfo.getId());
										doctorPoints1.setPoints(recommentAwardRecordDoctor.get(j).getPointsAmount());
										doctorPoints1.setPointsDes("推荐奖励");
										doctorPoints1.setType("+");
										doctorPoints1.setUpdateTime(awardTime);
										orderBo.saveDoctorPoints(doctorPoints1);
									}
									recommentAwardRecordDoctor.get(j).setAwardTime(awardTime);
									recommentAwardRecordDoctor.get(j).setIsGrant("Y");
									orderBo.updateAwared(recommentAwardRecordDoctor.get(j));
									orderBo.updateDoctorInfo(drInfo);
									orderBo.updateDoctorInfo(beendoctorInfo);
								}
							}
						}
						
					}
					
					mes="无奖励";
					return "success";
				}
				
			}		
			if(orderStatus.equals("完成")){
				
				OrderInfo orderInf = orderBo.getOrderByOrderId(orderId);
				if(orderInf!=null){
					System.out.println(data_str);
					String[] allStr = data_str.split(",");
					String linshiStr = "";
					for(int i=0;i<allStr.length;i+=2){
						String[] someStr = allStr[i].split("::");
						
						if(someStr[4].equals("")||someStr[4].equals(null)){
							OrderResults orderResults = new OrderResults();
							orderResults.setComments("");
							orderResults.setDoctorId(orderInf.getDoctorId());
							orderResults.setItemId(Long.valueOf(someStr[0]));
							orderResults.setItemResultName(someStr[1]);
							orderResults.setOrderNum(orderInf.getOrderNum());
							orderResults.setPlanId(someStr[5]);
							orderResults.setProductId(orderInf.getProductId());
							orderResults.setResultUnit(someStr[3]);
							orderResults.setResultValue(someStr[2]);
							orderResults.setUserId(orderInf.getUserId());
							orderResults.setWriteDate(time);
							orderBo.saveOrderResults(orderResults);
						}
						else{
							OrderResults orderResults = new OrderResults();
							orderResults.setComments("");
							orderResults.setDoctorId(orderInf.getDoctorId());
							orderResults.setItemId(Long.valueOf(someStr[0]));
							orderResults.setItemResultName(someStr[1]);
							orderResults.setOrderNum(orderInf.getOrderNum());
							orderResults.setPlanId(someStr[5]);
							orderResults.setProductId(orderInf.getProductId());
							orderResults.setResultUnit(someStr[3]);
							orderResults.setResultValue(someStr[2]);
							orderResults.setUserId(orderInf.getUserId());
							orderResults.setWriteDate(time);
							orderBo.saveOrderResults(orderResults);
							if(i==allStr.length-1){
								linshiStr += someStr[4];
							}else{
								linshiStr += someStr[4]+",";
							}
						}
					}
					orderInf.setRemindInfo(linshiStr);
					orderInf.setOrderStatus("已完成");
					orderInf.setUpdateTime(time);
					orderBo.update(orderInf);
					for(int j=1;j<allStr.length;j+=2){
						String[] someString = allStr[j].split("&&");
						
						for(int n=0;n<someString.length;n++){
							String[] miniSring = someString[n].split("::");
							if(miniSring.length<3){
								
							}else{
								String statusList = "";
								String timeList = "";
								for(int m=0;m<Long.valueOf(miniSring[2]);m++){
									Calendar c=Calendar.getInstance();   
									DateFormat df=new SimpleDateFormat("yyyy-MM-dd");   
									c.setTime(new Date());   
									c.add(Calendar.DATE,(int) (Long.valueOf(miniSring[1])*(m+1)));   
									Date d2=c.getTime();
									timeList += df.format(d2)+",";
									statusList += "N::";
//										timeList += df.format(d2)+",";
//										statusList += "N";
									
								}
								String[] pStr = allStr[j-1].split("::");
								Long planId = Long.valueOf(pStr[5]);
								HealthPlanRemind healthPlanRemind = new HealthPlanRemind();
								healthPlanRemind.setExecuteDateList(timeList);
								healthPlanRemind.setExecuteResult(statusList);
								healthPlanRemind.setHealthPathId(Long.valueOf(miniSring[0]));
								healthPlanRemind.setHealthPlanId(planId);
								healthPlanRemind.setOrderNum(orderInf.getOrderNum());
								healthPlanRemind.setUserId(orderInf.getUserId());
								Calendar c=Calendar.getInstance();   
								DateFormat df=new SimpleDateFormat("yyyy-MM-dd");   
								c.setTime(new Date());   
								c.add(Calendar.DATE,(int) (Long.valueOf(miniSring[1])*Long.valueOf(miniSring[2])));   
								Date d2=c.getTime();
								String oneTime = df.format(d2);
								healthPlanRemind.setClosingDate(oneTime);
								orderBo.saveHealthPlanRemind(healthPlanRemind);
							}
						}
					}
					mes="成功";
					return "success";
				}
				mes="无数据";
				return "fail";
			}
		}
		
//		orderInfo.setOrderStatus("已签到");
//		orderBo.update(orderInfo);
		return "success";
	}
//	public List<OrderInfo> getOrderInfoList() {
//		return orderInfoList;
//	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	@Override
	public String getMes() {
		return mes;
	}

	public List<List> getSomeOrderList() {
		return someOrderList;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public List<List> getOrderDetailList() {
		return orderDetailList;
	}

	public BabyBasicData getBabyBasicData() {
		return babyBasicData;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public List<ItemResult> getItemResultList() {
		return itemResultList;
	}

	public List<HealthPlan> getHealthPlanList() {
		return healthPlanList;
	}

	public List<List> getHealthPathList() {
		return healthPathList;
	}

	public List<String> getSomeItemList() {
		return someItemList;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setApgarOfFiveMinutes(Long apgarOfFiveMinutes) {
		this.apgarOfFiveMinutes = apgarOfFiveMinutes;
	}

	public void setApgarOfOneMinute(Long apgarOfOneMinute) {
		this.apgarOfOneMinute = apgarOfOneMinute;
	}

	public void setApgarOfTenMinutes(Long apgarOfTenMinutes) {
		this.apgarOfTenMinutes = apgarOfTenMinutes;
	}

	public void setBabyBirthday(String babyBirthday) {
		this.babyBirthday = babyBirthday;
	}

	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}

	public void setBabySex(String babySex) {
		this.babySex = babySex;
	}

	public void setBirthBodyLength(String birthBodyLength) {
		this.birthBodyLength = birthBodyLength;
	}

	public void setBirthWeight(String birthWeight) {
		this.birthWeight = birthWeight;
	}

	public void setBornHospital(String bornHospital) {
		this.bornHospital = bornHospital;
	}

	public void setBornWay(String bornWay) {
		this.bornWay = bornWay;
	}

	public void setDegreeOfFatherEducation(String degreeOfFatherEducation) {
		this.degreeOfFatherEducation = degreeOfFatherEducation;
	}

	public void setDegreeOfMotherEducation(String degreeOfMotherEducation) {
		this.degreeOfMotherEducation = degreeOfMotherEducation;
	}

	public void setDrugAllergyHistory(String drugAllergyHistory) {
		this.drugAllergyHistory = drugAllergyHistory;
	}

	public void setEmbryoNumber(String embryoNumber) {
		this.embryoNumber = embryoNumber;
	}

	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public void setGeneticHeight(String geneticHeight) {
		this.geneticHeight = geneticHeight;
	}

	public void setGestationalAge(Long gestationalAge) {
		this.gestationalAge = gestationalAge;
	}

	public void setHeightOfFather(String heightOfFather) {
		this.heightOfFather = heightOfFather;
	}

	public void setHeightOfMother(String heightOfMother) {
		this.heightOfMother = heightOfMother;
	}

	public void setMainCaregivers(String mainCaregivers) {
		this.mainCaregivers = mainCaregivers;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setPara(Long para) {
		this.para = para;
	}

	public void setParity(Long parity) {
		this.parity = parity;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public void setPhoneOfFather(String phoneOfFather) {
		this.phoneOfFather = phoneOfFather;
	}

	public void setPhoneOfMother(String phoneOfMother) {
		this.phoneOfMother = phoneOfMother;
	}

	public void setTwinBrotherAndSister(String twinBrotherAndSister) {
		this.twinBrotherAndSister = twinBrotherAndSister;
	}

	public void setData_str(String data_str) {
		this.data_str = data_str;
	}

	public String getTime() {
		return time;
	}
}
