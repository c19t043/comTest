package com.kybaby.action.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.RecommendRule;
import com.kybaby.domain.RecommentAwardRecord;
import com.kybaby.util.CookieManage;
import com.kybaby.util.EncryptUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author sujiantang
 *
 */
public class DoctorRegist extends BaseAction{
	
	private DoctorInfo doctorInfo;
	private String phone;
	private String password;
	private String referPhone;
	private String mes;

	@Override
	public String execute(){
		
		if(action.equals("regist")){
			String radPhone = (String) ActionContext.getContext().getSession().get("Phone");
			if(!radPhone.equals(phone)){
				mes="不是获取验证码的手机号";
				return "fail";
			}
			String openId = (String)ActionContext.getContext().getSession().get("OpenId");
			//modify by xchao 修改openId有值时导致手机号码不能新注册的问题（界面提醒用户已注册）
			/*if(openId!=null){
				doctorInfo = doctorInfoBo.getDoctorInfoByOpenId(openId);
				if(doctorInfo!=null){
					mes="已注册";
					return "fail";
				}
			}*/
			doctorInfo = doctorInfoBo.getDoctorInfoByPhoneNum(phone);
			if(doctorInfo!=null){
				mes="已注册";
				return "fail";
			}
			
			DoctorInfo newDoctorInfo = new DoctorInfo();
			Date date = new Date();
//			DateFormat df = DateFormat.getDateTimeInstance();//可以精确到时分秒
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
//			String registerTime = df.format("yyyy-MM-dd HH:mm:ss");
//			System.out.println(time);
//			System.out.println(time.length());
			String doctorPassword = EncryptUtil.getMD5Str(password);
			if(openId!=null){
				newDoctorInfo.setOpenId(openId);
			}
			newDoctorInfo.setIsLogin("Y");
			newDoctorInfo.setDoctorStatus("Y");
			newDoctorInfo.setRegisterTime(time);
			newDoctorInfo.setVisitedTimes(0L);
			newDoctorInfo.setAdvisoryLabelIds("");
			newDoctorInfo.setAuthentication("未申请");
			newDoctorInfo.setBankAccountName("");
			newDoctorInfo.setBankCard("");
			newDoctorInfo.setComeMethod("");
			newDoctorInfo.setComments("");
			newDoctorInfo.setDefaultAddressId(0L);
			newDoctorInfo.setDoctorBalance(0.0);
			newDoctorInfo.setDoctorEmployer("");
			newDoctorInfo.setDoctorImage("");
			newDoctorInfo.setDoctorName("");
			newDoctorInfo.setDoctorPoints(0L);
			newDoctorInfo.setDoctorSex("");
			newDoctorInfo.setDoctorTitle("");
			newDoctorInfo.setDutyStarLevel(0L);
			newDoctorInfo.setDutyStarLevelHitCount(0L);
			newDoctorInfo.setDutyStarTotal(0L);
			newDoctorInfo.setLicenseImage("");
			newDoctorInfo.setMajorId("");
			newDoctorInfo.setNickName("");
			newDoctorInfo.setOnTimeStarLevel(0L);
			newDoctorInfo.setOnTimeStarLevelHitCount(0L);
			newDoctorInfo.setOnTimeStarTotal(0L);
			newDoctorInfo.setProductIds("");
			newDoctorInfo.setSeiviceStarHitCount(0L);
			newDoctorInfo.setSeiviceStarLevel(0L);
			newDoctorInfo.setSeiviceStarTotal(0L);
			newDoctorInfo.setServiceArea(0L);
			newDoctorInfo.setWithdrawalsPassword("");
			newDoctorInfo.setDoctorPhone(phone);
			newDoctorInfo.setDoctorPassword(doctorPassword);
			doctorInfoBo.save(newDoctorInfo); 
			ActionContext.getContext().getSession().put("Doctor", newDoctorInfo);
			CookieManage.pwdAndPhoneCookie(password, phone);
			//注册成功之后，缺少推荐奖励的实现
			//填写了推荐人信息，客户需求做到医生推荐医生，对于其他的情况，暂不处理，只需要添加记录
			RecommendRule userRule=accountBo.getSomeCanUseRule("医生推荐医生");
			if(userRule!=null){
				RecommentAwardRecord ruleCord=new RecommentAwardRecord();
				ruleCord.setRecommendType("医生推荐医生");
				ruleCord.setWhenToGrant(userRule.getRewardTime());
				ruleCord.setIsGrant("N");
				if(userRule.getAmount()!=null){
					ruleCord.setAmount(userRule.getAmount());
				}
				if(userRule.getPoints()!=null){
					ruleCord.setPointsAmount(userRule.getPoints());
				}
				if(userRule.getCoupon()!=null){
					ruleCord.setCouponId(userRule.getCoupon());
				}
				if(!(referPhone.equals(""))){
					DoctorInfo referDoc=doctorInfoBo.getDoctorInfoByPhoneNum(referPhone);
					if(referDoc!=null){
						ruleCord.setRecommendDoctorId(referDoc.getId());
					}
				}
				DoctorInfo beenReferDoc=doctorInfoBo.getDoctorInfoByPhoneNum(phone);
				if(beenReferDoc!=null){
					ruleCord.setBeenRecommendDoctorId(beenReferDoc.getId());
				}
				//增加记录
				accountBo.saveRecommentAwardRecord(ruleCord);
			}
			mes="成功";
			return "success";
		}
		return null;
		
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public void setReferPhone(String referPhone) {
		this.referPhone = referPhone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Override
	public String getMes() {
		return mes;
	}
}
