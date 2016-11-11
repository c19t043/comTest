package com.kybaby.action.main;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.SymptomTag;
import com.kybaby.domain.UserConsultDoctor;
import com.kybaby.domain.UserInfo;
import com.kybaby.util.CalculationMethod;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:GetUserConsultDoctorInfo
 * @Description:
 * @author Hoolee
 * @date 2015年10月12日下午1:46:52
 */
public class GetUserConsultDoctorInfo extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private long doctorId;//医生的ID
	private List<UserConsultDoctor> lastConsultLogList=new ArrayList<UserConsultDoctor>();//上一次咨询实例列表
	private List<SymptomTag> lastSymptomTagList=new ArrayList<SymptomTag>();//上一次咨询的标签列表
	private String doctorName;//医生的名称
	private String doctorImage;//医生的头像
	private String babyName;//宝宝的名称
	private String babySex;//宝宝的性别
	private String babyMonthYear;//宝宝的月龄
	private String userType;//签约用户表示
	/**
	 * 家庭医生服务包
	 */
	private Long fdPackageId;
	private Long logId;
	
	private List<SymptomTag> allSymptomTagList=new ArrayList<SymptomTag>();//上一次咨询的标签列表
	
	public String execute() throws ParseException{
		if(action.equals("getLastConsultation")){
			System.out.println("GetLastConsultation is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				
				UserConsultDoctor consult=null;
				List<UserConsultDoctor>  consultList = userConsultDoctorBo.
						getConsultListBySomething(userId, doctorId, null, userType, "N", null, "desc",fdPackageId);
				if(consultList != null){
					this.logId = consultList.get(0).getLogId();
					mes="操作成功";
					return "success";
				}else{
					mes="已结束";
					return "fail";
				}
			}
			mes="未登录";
			return "fail";
		} else if(action.equals("getAllSymptomTagList")){
			System.out.println("GetAllSymptomTagList is begining...");
			allSymptomTagList=symptomTagBo.getAllSymptomTag();
			mes="操作成功";
			return "success";
		}
		
		//add by fkn 
		else if(action.equals("getLastAfterConsultation")){

			System.out.println("getLastAfterConsultation is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				UserConsultDoctor consult=userConsultDoctorBo.getLastUserConsultDoctorSessionByTypeN(userId, doctorId);
				if(consult!=null){
					//上一次咨询还没有结束
					long logId=consult.getLogId();//上一次会话的会话ID
					lastConsultLogList=userConsultDoctorBo.lastConsultationLog(userId, doctorId, logId);
					String symptomTagIds=consult.getSymptomTagIds();
					if(symptomTagIds!=null&&!symptomTagIds.equals("")){
						lastSymptomTagList=symptomTagBo.getSymptomTagInstanceList(symptomTagIds);
					}
					DoctorInfo doctor=doctorInfoBo.getDoctorInfoByDoctorId(doctorId);
					doctorName=doctor.getDoctorName();
					doctorImage=doctor.getDoctorImage();
					UserInfo user=userInfoBo.getUserById(userId);
					babyName=user.getBabyName();
					babySex=user.getSex();
					//当前的日期
					String babyBirthday=user.getBirthday();
					String rightNow=CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
					babyMonthYear=String.valueOf(CalculationMethod.getMonthSpace(babyBirthday, rightNow));
					mes="操作成功";
					return "success";
				}
				//上一次咨询已经结束
				mes="已结束";
				return "fail";
			}
			mes="未登录";
			return "fail";
		
		}
		return "fail";
	}

	public String getMes() {
		return mes;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * @return the lastConsultLogList
	 */
	public List<UserConsultDoctor> getLastConsultLogList() {
		return lastConsultLogList;
	}

	/**
	 * @return the lastSymptomTagList
	 */
	public List<SymptomTag> getLastSymptomTagList() {
		return lastSymptomTagList;
	}

	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}

	/**
	 * @return the doctorImage
	 */
	public String getDoctorImage() {
		return doctorImage;
	}

	/**
	 * @return the babyName
	 */
	public String getBabyName() {
		return babyName;
	}

	/**
	 * @return the babySex
	 */
	public String getBabySex() {
		return babySex;
	}

	/**
	 * @return the babyMonthYear
	 */
	public String getBabyMonthYear() {
		return babyMonthYear;
	}

	/**
	 * @return the allSymptomTagList
	 */
	public List<SymptomTag> getAllSymptomTagList() {
		return allSymptomTagList;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setFdPackageId(Long fdPackageId) {
		this.fdPackageId = fdPackageId;
	}

	public Long getLogId() {
		return logId;
	}
	
}