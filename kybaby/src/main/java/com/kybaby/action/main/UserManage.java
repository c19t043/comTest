package com.kybaby.action.main;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.RecommendRule;
import com.kybaby.domain.UserInfo;
import com.kybaby.util.CookieManage;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:UserManage
 * @Description:用户管理相关
 * @author Hoolee
 * @date 2015年9月27日上午11:32:15
 */
public class UserManage extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String mes;//反馈给前端的提示信息

	private String userPhone;
	private String password;
	private String babyName;
	private String babySex;
	private String babyBirthDay;
	private String userLng;
	private String userLat;
	private String userProvince;
	private String userCity;
	private String userArea;
	private String userStreet;
	private String detailAddress;
	private String refereeUserPhone;

	private String userImage;//用户头像
	private String userName;
	private long userId;//用户的ID
	
	private String parentName;
	

	public String execute(){
		/**
		 * 得到用户手机或医生手机
		 */
		if(action.equals("getPhone")){
			if(refereeUserPhone.toUpperCase().indexOf("U=") > -1){//用户
				String userId_ = refereeUserPhone.split("=")[1];
				UserInfo usr = userInfoBo.getUserById(Long.valueOf(userId_));
				refereeUserPhone = usr==null?"":usr.getPhone();
			}else if(refereeUserPhone.toUpperCase().indexOf("D=") > -1){//医生
				String userId_ = refereeUserPhone.split("=")[1];
				DoctorInfo	doctorInfo = doctorInfoBo.getDoctorInfoByDoctorId(Long.valueOf(userId_));
				refereeUserPhone = doctorInfo==null?"":doctorInfo.getDoctorPhone();
			}
			mes="操作成功";
			return "success";
		}
		else if(action.equals("regist")){
			try {
				System.out.println("Regist is begining...");
				String openId="";
				if(ActionContext.getContext().getSession().get("openId")!=null){
					openId=(String)ActionContext.getContext().getSession().get("openId");
				}
				//String oldPhone=(String)ActionContext.getContext().getSession().get("userPhone");
				UserInfo user=userInfoBo.getUserInfoByPhone(userPhone);
				if(user==null){
					userInfoBo.userRegist(openId, password, babyBirthDay, babySex, userPhone, userLng, userLat, userProvince, userCity, userArea, userStreet, detailAddress, babyName);
					user=userInfoBo.getUserInfoByPhone(userPhone);
					if(user!=null){
						user.setComments(refereeUserPhone);
						userInfoBo.updateUser(user);
						long userId=user.getId();
						ActionContext.getContext().getSession().put("userId",userId );
						if(!"".equals(refereeUserPhone)){
							if(doctorInfoBo.isCanbeUsed(refereeUserPhone)){//推荐人手机号是医生的手机号码
								RecommendRule someRecommendRule=recommendRuleBo.getSomeCanUseRule("医生推荐用户");
								if(someRecommendRule!=null){//存在有效的医生推荐用户的推荐规则
									DoctorInfo someDoctor=doctorInfoBo.getDoctorInfoByPhone(refereeUserPhone);
									long doctorId=someDoctor.getId();
									if(someRecommendRule.getPoints()==null){
										someRecommendRule.setPoints(0L);
									}
									if(someRecommendRule.getAmount()==null){
										someRecommendRule.setAmount(0D);
									}
									if(someRecommendRule.getCoupon()==null){
										someRecommendRule.setCoupon(0L);
									}
									if(someRecommendRule.getRewardTime().equals("注册后")){//下单后就发放奖励
										//处理奖励中的积分

										if(someRecommendRule.getPoints()>0){//有积分奖励
											user.setAccountPoints(user.getAccountPoints()+someRecommendRule.getPoints());
											//用户的积分记录
											userPointsBo.addNewUserPoints(userId, someRecommendRule.getPoints(), "+", "医生推荐用户赠送");
											if(someDoctor.getDoctorPoints()==null){
												someDoctor.setDoctorPoints(0L);
											}
											someDoctor.setDoctorPoints(someDoctor.getDoctorPoints()+someRecommendRule.getPoints());
											//医生的积分记录
											doctorPointsBo.addNewUserPoints(doctorId, someRecommendRule.getPoints(), "+", "医生推荐用户赠送");
										}

										if(someRecommendRule.getAmount()>0){//有金额的奖励
											user.setAccountBalance(user.getAccountBalance()+someRecommendRule.getAmount());
											//用户的金额记录
											userAccountBo.addNewUserAccount(userId, someRecommendRule.getAmount(), "+", "医生推荐用户赠送", "");
											if(someDoctor.getDoctorBalance()==null){
												someDoctor.setDoctorBalance(0D);
											}
											someDoctor.setDoctorBalance(someDoctor.getDoctorBalance()+someRecommendRule.getAmount());
											//医生的金额记录
											doctorAccountBo.addNewDoctorAccount(doctorId, someRecommendRule.getAmount(), "+", "医生推荐用户赠送");
										}

										if(someRecommendRule.getCoupon()>0){
											userCouponBo.addNewUserCoupon(0, userId, someRecommendRule.getCoupon());
										}

										//更新用户和医生的实例
										userInfoBo.updateUser(user);
										doctorInfoBo.updateDoctorInstance(someDoctor);

										//added by zhong at 2015-10-06:更新表recomment_award_record
										recommentAwardRecordBo.addNewDoctorRecommentAwardRecord(someRecommendRule.getRuleName(), doctorId, userId, someRecommendRule.getPoints(), someRecommendRule.getAmount(), someRecommendRule.getCoupon(), someRecommendRule.getRewardTime(),"Y");

									}else{//奖励将在用户下单后者医生接单后发放
										recommentAwardRecordBo.addNewDoctorRecommentAwardRecord(someRecommendRule.getRuleName(), doctorId, userId, someRecommendRule.getPoints(), someRecommendRule.getAmount(), someRecommendRule.getCoupon(), someRecommendRule.getRewardTime(),"N");
									}
								}else{
									RecommendRule someRecommendRule2=recommendRuleBo.getSomeCanUseRule("用户推荐用户");
									if(someRecommendRule2!=null){//存在有效的用户推荐用户的推荐规则
										if(someRecommendRule2.getPoints()==null){
											someRecommendRule2.setPoints(0L);
										}
										if(someRecommendRule2.getAmount()==null){
											someRecommendRule2.setAmount(0D);
										}
										if(someRecommendRule2.getCoupon()==null){
											someRecommendRule2.setCoupon(0L);
										}
										if(someRecommendRule2.getRewardTime().equals("注册后")){//注册后发放
											if(someRecommendRule2.getPoints()>0){//有积分奖励
												user.setAccountPoints(user.getAccountPoints()+someRecommendRule2.getPoints());
												userPointsBo.addNewUserPoints(userId, someRecommendRule2.getPoints(), "+", "用户推荐用户赠送");
											}

											if(someRecommendRule2.getAmount()>0){//有金额奖励
												user.setAccountBalance(user.getAccountBalance()+someRecommendRule2.getAmount());
												userAccountBo.addNewUserAccount(userId, someRecommendRule2.getAmount(), "+", "用户推荐用户赠送", "");
											}

											if(someRecommendRule2.getCoupon()>0){
												//added by zhong at 2015-10-06,根据coupon Id 取activityId
												userCouponBo.addNewUserCoupon(0, userId, someRecommendRule2.getCoupon());
											}
											userInfoBo.updateUser(user);
											recommentAwardRecordBo.addNewUserRecommentAwardRecord(someRecommendRule2.getRuleName(), 0, userId,someRecommendRule2.getPoints() , someRecommendRule2.getAmount(), someRecommendRule2.getCoupon(), someRecommendRule2.getRewardTime(),"Y");
										}else{//非注册后发放
											recommentAwardRecordBo.addNewUserRecommentAwardRecord(someRecommendRule2.getRuleName(), 0, userId,someRecommendRule2.getPoints() , someRecommendRule2.getAmount(), someRecommendRule2.getCoupon(), someRecommendRule2.getRewardTime(),"N");
										}
									}
								}
							}else{//推荐人手机号不是医生手机号码							
								//updated by zhong at 2015-10-06
								UserInfo refereeUser=userInfoBo.getUserInfoByPhone(refereeUserPhone);
								if(refereeUser!=null&&refereeUser.getUserStatus().equals("Y")){
									//if(userInfoBo.isCanbeUsed(refereeUserPhone)){//该推荐是可用的用户号码
									//UserInfo refereeUser=userInfoBo.getUserInfoByPhone(refereeUserPhone);
									long refereeUserId=refereeUser.getId();
									RecommendRule someRecommendRule2=recommendRuleBo.getSomeCanUseRule("用户推荐用户");
									if(someRecommendRule2!=null){//存在有效的用户推荐用户的推荐规则
										if(someRecommendRule2.getPoints()==null){
											someRecommendRule2.setPoints(0L);
										}
										if(someRecommendRule2.getAmount()==null){
											someRecommendRule2.setAmount(0D);
										}
										if(someRecommendRule2.getCoupon()==null){
											someRecommendRule2.setCoupon(0L);
										}
										if(someRecommendRule2.getRewardTime().equals("注册后")){//注册后发放
											if(someRecommendRule2.getPoints()>0){//有积分奖励
												user.setAccountPoints(user.getAccountPoints()+someRecommendRule2.getPoints());
												if(refereeUser.getAccountPoints()==null){
													refereeUser.setAccountPoints(0L);
												}
												refereeUser.setAccountPoints(refereeUser.getAccountPoints()+someRecommendRule2.getPoints());
												userPointsBo.addNewUserPoints(userId, someRecommendRule2.getPoints(), "+", "用户推荐用户赠送");
												userPointsBo.addNewUserPoints(refereeUserId, someRecommendRule2.getPoints(), "+", "用户推荐用户赠送");
											}

											if(someRecommendRule2.getAmount()>0){//有金额奖励
												user.setAccountBalance(user.getAccountBalance()+someRecommendRule2.getAmount());
												if(refereeUser.getAccountBalance()==null){
													refereeUser.setAccountBalance(0D);
												}
												refereeUser.setAccountBalance(refereeUser.getAccountBalance()+someRecommendRule2.getAmount());
												userAccountBo.addNewUserAccount(userId, someRecommendRule2.getAmount(), "+", "用户推荐用户赠送", "");
												userAccountBo.addNewUserAccount(refereeUserId, someRecommendRule2.getAmount(), "+", "用户推荐用户赠送", "");
											}

											if(someRecommendRule2.getCoupon()>0){
												//added by zhong at 2015-10-06,根据coupon Id 取activityId
												userCouponBo.addNewUserCoupon(0, userId, someRecommendRule2.getCoupon());
												userCouponBo.addNewUserCoupon(0, refereeUserId, someRecommendRule2.getCoupon());
											}

											userInfoBo.updateUser(user);
											userInfoBo.updateUser(refereeUser);

											//added by zhong at 2015-10-06:更新表recomment_award_record
											recommentAwardRecordBo.addNewUserRecommentAwardRecord(someRecommendRule2.getRuleName(), refereeUserId, userId,someRecommendRule2.getPoints() , someRecommendRule2.getAmount(), someRecommendRule2.getCoupon(), someRecommendRule2.getRewardTime(),"Y");

										}else{//非注册后发放

											//added by zhong at 2015-10-06:需要记录奖励是否发放:isGrant, 
											//recommentAwardRecordBo.addNewUserRecommentAwardRecord(someRecommendRule2.getRuleName(), refereeUserId, userId,someRecommendRule2.getPoints() , someRecommendRule2.getAmount(), someRecommendRule2.getCoupon(), someRecommendRule2.getRewardTime());
											recommentAwardRecordBo.addNewUserRecommentAwardRecord(someRecommendRule2.getRuleName(), refereeUserId, userId,someRecommendRule2.getPoints() , someRecommendRule2.getAmount(), someRecommendRule2.getCoupon(), someRecommendRule2.getRewardTime(),"N");
										}
									}
								}else{
									RecommendRule someRecommendRule2=recommendRuleBo.getSomeCanUseRule("用户推荐用户");
									if(someRecommendRule2!=null){//存在有效的用户推荐用户的推荐规则
										if(someRecommendRule2.getPoints()==null){
											someRecommendRule2.setPoints(0L);
										}
										if(someRecommendRule2.getAmount()==null){
											someRecommendRule2.setAmount(0D);
										}
										if(someRecommendRule2.getCoupon()==null){
											someRecommendRule2.setCoupon(0L);
										}
										if(someRecommendRule2.getRewardTime().equals("注册后")){//注册后发放
											if(someRecommendRule2.getPoints()>0){//有积分奖励
												user.setAccountPoints(user.getAccountPoints()+someRecommendRule2.getPoints());
												userPointsBo.addNewUserPoints(userId, someRecommendRule2.getPoints(), "+", "用户推荐用户赠送");
											}

											if(someRecommendRule2.getAmount()>0){//有金额奖励
												user.setAccountBalance(user.getAccountBalance()+someRecommendRule2.getAmount());
												userAccountBo.addNewUserAccount(userId, someRecommendRule2.getAmount(), "+", "用户推荐用户赠送", "");
											}

											if(someRecommendRule2.getCoupon()>0){
												//added by zhong at 2015-10-06,根据coupon Id 取activityId
												userCouponBo.addNewUserCoupon(0, userId, someRecommendRule2.getCoupon());
											}
											userInfoBo.updateUser(user);
											recommentAwardRecordBo.addNewUserRecommentAwardRecord(someRecommendRule2.getRuleName(), 0, userId,someRecommendRule2.getPoints() , someRecommendRule2.getAmount(), someRecommendRule2.getCoupon(), someRecommendRule2.getRewardTime(),"Y");
										}else{//非注册后发放
											recommentAwardRecordBo.addNewUserRecommentAwardRecord(someRecommendRule2.getRuleName(), 0, userId,someRecommendRule2.getPoints() , someRecommendRule2.getAmount(), someRecommendRule2.getCoupon(), someRecommendRule2.getRewardTime(),"N");
										}
									}
								}
							}
						}else{
							RecommendRule someRecommendRule2=recommendRuleBo.getSomeCanUseRule("用户推荐用户");
							if(someRecommendRule2!=null){//存在有效的用户推荐用户的推荐规则
								if(someRecommendRule2.getPoints()==null){
									someRecommendRule2.setPoints(0L);
								}
								if(someRecommendRule2.getAmount()==null){
									someRecommendRule2.setAmount(0D);
								}
								if(someRecommendRule2.getCoupon()==null){
									someRecommendRule2.setCoupon(0L);
								}
								if(someRecommendRule2.getRewardTime().equals("注册后")){//注册后发放
									if(someRecommendRule2.getPoints()>0){//有积分奖励
										user.setAccountPoints(user.getAccountPoints()+someRecommendRule2.getPoints());
										userPointsBo.addNewUserPoints(userId, someRecommendRule2.getPoints(), "+", "用户推荐用户赠送");
									}

									if(someRecommendRule2.getAmount()>0){//有金额奖励
										user.setAccountBalance(user.getAccountBalance()+someRecommendRule2.getAmount());
										userAccountBo.addNewUserAccount(userId, someRecommendRule2.getAmount(), "+", "用户推荐用户赠送", "");
									}

									if(someRecommendRule2.getCoupon()>0){
										//added by zhong at 2015-10-06,根据coupon Id 取activityId
										userCouponBo.addNewUserCoupon(0, userId, someRecommendRule2.getCoupon());
									}
									userInfoBo.updateUser(user);
									recommentAwardRecordBo.addNewUserRecommentAwardRecord(someRecommendRule2.getRuleName(), 0, userId,someRecommendRule2.getPoints() , someRecommendRule2.getAmount(), someRecommendRule2.getCoupon(), someRecommendRule2.getRewardTime(),"Y");
								}else{//非注册后发放
									recommentAwardRecordBo.addNewUserRecommentAwardRecord(someRecommendRule2.getRuleName(), 0, userId,someRecommendRule2.getPoints() , someRecommendRule2.getAmount(), someRecommendRule2.getCoupon(), someRecommendRule2.getRewardTime(),"N");
								}
							}
						}
					}
					CookieManage.pwdAndPhoneCookie(password, userPhone);
					mes="操作成功";
					return "success";
				}
				mes="手机号已注册";
				return "fail";
			} catch (Exception e) {
				System.out.println("register error============================");
				e.printStackTrace();
			}
		}else if(action.equals("forgetPassword")){
			System.out.println("ForgetPassword isbegining...");
			String oldPhone=(String)ActionContext.getContext().getSession().get("userPhone");
			if(oldPhone.equals(userPhone)){
				userInfoBo.updateUserPassword(userPhone, password);
				mes="操作成功";
				return "success";
			}
			mes="错误";
			return "fail";
		}else if(action.equals("logout")){
			System.out.println("Logout is begining...");
			long userId=(Long)ActionContext.getContext().getSession().get("userId");
			UserInfo user=userInfoBo.getUserById(userId);
			if(user!=null){
				user.setIsLogin("N");
				userInfoBo.updateUser(user);
			}
			
			CookieManage.removeCookie();

			ActionContext.getContext().getSession().remove("openId");
			ActionContext.getContext().getSession().remove("userId");
			
			mes="操作成功";
			return "success";
		} else if(action.equals("personZone")){
			System.out.println("Go to PersonZone is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				userId=(Long)ActionContext.getContext().getSession().get("userId");
				UserInfo user=userInfoBo.getUserById(userId);
				if(user!=null){
					userImage=user.getUserImage();
					userName=user.getBabyName();
					babyBirthDay=user.getBirthday();
					babySex=user.getSex();
					userPhone=user.getPhone();
					mes="操作成功";
					return "success";
				}
			}
			mes="未登录";
			return "success";
		} else if(action.equals("updateUserPhone")){
			System.out.println("UpdateUserPhone is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				userId=(Long)ActionContext.getContext().getSession().get("userId");
				String oldPhone=(String)ActionContext.getContext().getSession().get("userPhone");
				if(oldPhone.equals(userPhone)){
					userInfoBo.updateUserPhone(userId, userPhone);
					mes="操作成功";
					return "success";
				}
				mes="错误";
				return "fail";
			}
			mes="未登录";
			return "fail";
		} else if(action.equals("updatePassword")){
			System.out.println("UpdatePassword is beginig...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				userId=(Long)ActionContext.getContext().getSession().get("userId");
				String oldPhone=(String)ActionContext.getContext().getSession().get("userPhone");
				if(oldPhone.equals(userPhone)){
					userInfoBo.updateUserPassword(userPhone, password);
					mes="操作成功";
					return "success";
				}
				mes="错误";
				return "fail";
			}
			mes="未登录";
			return "fail";
		} else if(action.equals("changeInfo")){
			System.out.println("ChangeInfo is begining... ");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				userId=(Long)ActionContext.getContext().getSession().get("userId");
				userInfoBo.updateUserSomeInfo(userId, parentName, babyName, babyBirthDay, babySex, userLng, userLat, userProvince, userCity, userArea, userStreet, detailAddress);
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "fail";
		} else if (action.equals("autoLogin")){
			System.out.println("AutoLogin is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				mes="操作成功";
				return "success";
			}else{
				System.out.println("AutoLogin is begining  from Cookie...");
				HttpServletRequest request = ServletActionContext.getRequest();
				javax.servlet.http.Cookie[] cookies = request.getCookies();
				int j = 0;
				String Original_CookiesPwd = null;//cookie中的密码
				String Original_CookiesPhone=null;//cookie中的电话号码
				javax.servlet.http.Cookie c = null;
				
				if(cookies!=null){//是否存在cookie
					for(int i =0;i<cookies.length;i++){
						c=cookies[i];
						if(c!=null){
							if (c.getName().equalsIgnoreCase("userpwd")&& c.getValue() != null) {
								Original_CookiesPwd = c.getValue();
							}
							if(c.getName().equalsIgnoreCase("userphone")&&c.getValue()!=null){
								Original_CookiesPhone=c.getValue();
							}
						}
						c=null;
					}
				}
				
				if(Original_CookiesPwd!=null&&Original_CookiesPhone!=null){
					UserInfo user=userInfoBo.getUserInfoByPhone(Original_CookiesPhone);
					if(user!=null&&Original_CookiesPwd.equals(user.getPassword())){
						user.setIsLogin("Y");
						userInfoBo.updateUser(user);
						long userId=user.getId();
						System.out.println(" from Cookie userId==..."+userId);
						ActionContext.getContext().getSession().put("userId",userId);
						mes="操作成功";
						return "success";
					}
				}
			}
			mes="未登录";
			return "fail";
		}
		return "fail";
	}
	
	private boolean isSomeActivityCanBeUsed(String activityName){
		boolean flag=false;
		
		
		
		return flag;
	}

    public String getMes() {
		return mes;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}

	public void setBabySex(String babySex) {
		this.babySex = babySex;
	}

	public void setBabyBirthDay(String babyBirthDay) {
		this.babyBirthDay = babyBirthDay;
	}

	public void setUserLng(String userLng) {
		this.userLng = userLng;
	}

	public void setUserLat(String userLat) {
		this.userLat = userLat;
	}

	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}

	public void setUserStreet(String userStreet) {
		this.userStreet = userStreet;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public void setRefereeUserPhone(String refereeUserPhone) {
		this.refereeUserPhone = refereeUserPhone;
	}

	public String getBabySex() {
		return babySex;
	}

	public String getBabyBirthDay() {
		return babyBirthDay;
	}

	public String getUserImage() {
		return userImage;
	}

	public String getUserName() {
		return userName;
	}

	public long getUserId() {
		return userId;
	}

	/**
	 * @param parentName the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * @return the userPhone
	 */
	public String getUserPhone() {
		return userPhone;
	}

	public String getRefereeUserPhone() {
		return refereeUserPhone;
	}

}
