package com.kybaby.action.main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.SymptomTag;
import com.kybaby.domain.UserConsultDoctor;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.commondiseaseanddrug.domain.CommonDisease;
import com.kybaby.util.CalculationMethod;
import com.kybaby.util.DateManage;
import com.kybaby.util.EncryptUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:ConsultDoctorManage
 * @Description:用户咨询医生相关的
 * @author Hoolee
 * @date 2015年10月12日下午10:20:13
 */
public class ConsultDoctorManage extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private String typeMethod;//儿保前还是儿保后的咨询
	private long doctorId;//医生的ID
	private List<UserConsultDoctor> histConsultList=new ArrayList<UserConsultDoctor>();//用户咨询医生的历史记录
	private List<SymptomTag> lastSymptomTagList=new ArrayList<SymptomTag>();//上一次咨询的标签列表
	private String doctorName;//医生的名称
	private String doctorImage;//医生的头像
	private String babyName;//宝宝的名称
	private String babySex;//宝宝的性别
	private String babyMonthYear;//宝宝的月龄
	private String userImage;//用户头像
	private String msgType;//消息的类型
	private long msgId;//最后一条消息的ID
	private long logId;//会话的ID
	private List<Object> latestConsultList;//sjt add
	private List<Object[]> newLatestConsultList; // fkn  add
	private List<Long> newMesList;
	private List<List> doctorSomeMsgList;
	private List<String> tagNameList;
	private List<UserConsultDoctor> someConsultList;
	private Object doctorImg;
	private String userImg;
	private String content;
	private String imagedata;//前端传递的图片数据
	/**
	 * 用户类型（0:普通用户咨询；1：家庭医生签约用户咨询；）
	 */
	private String userType;
	/**
	 * 签约服务包id
	 */
	private Long fdPackageId;
	private String symptomImage;
	/**
	 * 病情印象
	 */
	private List<CommonDisease> commonDiseaseList;
	
	public String execute() throws ParseException{
		if(action.equals("getHist")){
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				List<UserConsultDoctor>  histConsultList_first = userConsultDoctorBo.
						getConsultListBySomething(userId, doctorId, 
								logId, userType, "","N", "",fdPackageId);
				UserConsultDoctor consult = histConsultList_first==null?null:histConsultList_first.get(0);
				if(consult!=null){
					String symptomTagIds=consult.getSymptomTagIds();
					if(symptomTagIds!=null&&!symptomTagIds.equals("")){
						lastSymptomTagList=symptomTagBo.getSymptomTagInstanceList(symptomTagIds);
					}
				}
				this.histConsultList = userConsultDoctorBo.
						getConsultListBySomething(userId, doctorId, 
								logId, userType, "","", "",fdPackageId);
				if(histConsultList != null){
					for(UserConsultDoctor ucd : histConsultList){
						ucd.setIsUserAlreadyRead("Y");
						userConsultDoctorBo.updateSomeConsult(ucd);
					}
				}
				DoctorInfo doctor=doctorInfoBo.getDoctorInfoByDoctorId(doctorId);
				doctorName=doctor.getDoctorName();
				doctorImage=doctor.getDoctorImage();
				UserInfo user=userInfoBo.getUserById(userId);
				babyName=user.getBabyName();
				babySex=user.getSex();
				userImage=user.getUserImage();
				//当前的日期
				String babyBirthday=user.getBirthday();
				String rightNow=CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
				babyMonthYear=String.valueOf(CalculationMethod.getMonthSpace(babyBirthday, rightNow));
				//查询病情印象记录
				this.commonDiseaseList = this.userConsultDoctorBo.getCommonDiseaseListByLogId(logId);
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "fail";
		} else if(action.equals("add")){
			System.out.println("Add new consult...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				//判断对话是否已结束
				List<UserConsultDoctor> consultList = 
						this.userConsultDoctorBo.getConsultListBySomething(userId, doctorId, logId, userType, "N", null, "", fdPackageId);
				if(consultList!=null){
					String fileName="";
					//上传咨询图片
					if(imagedata != null && !"".equals(imagedata)){
						String current= DateManage.getDateStr("yyyyMMddhhmmss");
						fileName=userId +"image" + current +".jpg";
						String fileAllName="../kybabyBG/admin/images/consultPicture/"+fileName;
						String dir = ServletActionContext.getServletContext().getRealPath(fileAllName);
						try {
							if(EncryptUtil.uploadImage(imagedata,dir)){
								this.symptomImage = fileName;
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					userConsultDoctorBo.addNewUserConsultDoctor(userId, doctorId, "", fileName, content, msgType, logId,"Y",userType,fdPackageId);
					histConsultList=this.userConsultDoctorBo.getConsultListBySomething(userId, doctorId, logId, userType, "N", "N", "", fdPackageId);
					mes="操作成功";
					return "success";
				}else{
					mes="已结束";
					return "success";
				}
			}
			mes="未登录";
			return "fail";
		} else if(action.equals("end")){
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				histConsultList=this.userConsultDoctorBo.getConsultListBySomething(userId, doctorId, logId, userType, "N", "", "", fdPackageId);
				for(int i =0;i<histConsultList.size();i++){
					UserConsultDoctor consult=histConsultList.get(i);
					consult.setIsEnd("Y");
					userConsultDoctorBo.updateSomeConsult(consult);
				}
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "fail";
		}
		//add by sjt 2015-10-15 11:23 获取咨询列表 和咨询历史消息
		else if(action.equals("getList")){
			if(ActionContext.getContext().getSession().get("userId")==null){
				mes="未登录";
				return "fail";
			}
			Long userId = (Long) ActionContext.getContext().getSession().get("userId");
			List<Long> doctorIds = new ArrayList<Long>();
			doctorIds = userConsultDoctorBo.getHistoryDoctorIdList(userId);
			if(doctorIds!=null){
				doctorSomeMsgList = new ArrayList<List>();
				newMesList = new ArrayList<Long>();
				latestConsultList = new ArrayList<Object>();
				Long doctId = doctorIds.get(0);
				for(int i=1;i<doctorIds.size();i++){
					if(doctId.equals(doctorIds.get(i))){
						doctorIds.remove(i);
						i--;
					}else{
						doctId=doctorIds.get(i);
					}
				}
				for(int j=0;j<doctorIds.size();j++){
//					List doctorSomeMsg = userConsultDoctorBo.getDoctorNameAndImage(doctorIds.get(j));
					//update by fkn 
//					List doctorSomeMsg = userConsultDoctorBo.getDoctorIdAndNameAndImage(doctorIds.get(j));
//					if(doctorSomeMsg!=null){
//						doctorSomeMsgList.add(doctorSomeMsg);
//					}else{
//						doctorSomeMsgList.add(null);
//					}
					//update by fkn 
					List doctorSomeMsg = userConsultDoctorBo.getDoctorIdAndNameAndImage(doctorIds.get(j)); //
					if(doctorSomeMsg!=null){
						doctorSomeMsgList.add(doctorSomeMsg);
					}else{
						doctorSomeMsgList.add(null);
					}
					doctorSomeMsg = userConsultDoctorBo.getDoctorIdAndNameAndImage(doctorIds.get(j));
					
					String doctorTime = userConsultDoctorBo.getLastDoctorTime(userId, doctorIds.get(j));
					//update by fkn
//					String doctorTime=userConsultDoctorBo.getLastDoctorTimeReplace(userId, doctorIds.get(j));
					if(doctorTime!=null){
						long newMes = userConsultDoctorBo.countNewMessage(userId, doctorIds.get(j),doctorTime);
//						long newMes = userConsultDoctorBo.countNewMessageReplace(userId, doctorIds.get(j),doctorTime);			
						newMesList.add(newMes);
					}else{
						newMesList.add(0L);
					}
					Object latestConsult = userConsultDoctorBo.getSomeUserConsultDoctor(userId, doctorIds.get(j));
					if(latestConsult!=null){
						latestConsultList.add(latestConsult);
					}else{
						latestConsultList.add(null);
					}
				}
				mes="成功";
				return "success";
			}
			mes="无数据";
			return "fail";
		}
		
		//  add by fkn 2015-10-23 11:24
		else if(action.equals("getListMsg"))
		{
			if(ActionContext.getContext().getSession().get("userId")==null){
				mes="未登录";
				return "fail";
			}
			Long userId = (Long) ActionContext.getContext().getSession().get("userId");
			latestConsultList = new ArrayList<Object>();
			newLatestConsultList=userConsultDoctorBo.getAllLastMsgInfo(userId);
			List<Long> doctorIds = new ArrayList<Long>();
			List<String> types=new ArrayList<String>();
			if(latestConsultList!=null){
				newMesList = new ArrayList<Long>();
				doctorSomeMsgList = new ArrayList<List>();
				for(Object[] a:newLatestConsultList)
				{
					Long doctId=Long.valueOf(String.valueOf(a[5]));
					String type=String.valueOf(a[6]);
					if(null!=doctId)
					{
						doctorIds.add(doctId);
						types.add(type);
					}
				}
				Long doctId = doctorIds.get(0);
				for(int j=0;j<doctorIds.size();j++){
					
					List doctorSomeMsg = userConsultDoctorBo.getDoctorIdAndNameAndImage(doctorIds.get(j)); //
					if(doctorSomeMsg!=null){
						doctorSomeMsgList.add(doctorSomeMsg);
					}else{
						doctorSomeMsgList.add(null);
					}					
					String doctorTime = userConsultDoctorBo.getLastDoctorTimeByType(userId, doctorIds.get(j),types.get(j));
					//update by fkn
//					String doctorTime=userConsultDoctorBo.getLastDoctorTimeReplace(userId, doctorIds.get(j));
					if(doctorTime!=null){
						long newMes = userConsultDoctorBo.countNewMessageByType(userId, doctorIds.get(j),doctorTime,types.get(j));
//						long newMes = userConsultDoctorBo.countNewMessageReplace(userId, doctorIds.get(j),doctorTime);			
						newMesList.add(newMes);
					}else{
						newMesList.add(0L);
					}
					
					
				}
				mes="成功";
				return "success";
			}
			mes="无数据";
			return "fail";
		}
		
		
		
		else if(action.equals("detail")){
//			userImg = new ArrayList<Object>();
			tagNameList = new ArrayList<String>();
			someConsultList = new ArrayList<UserConsultDoctor>();
			someConsultList = userConsultDoctorBo.getSomeUserConsultDoctor(logId);
			if(someConsultList!=null){
				String[] tagList = someConsultList.get(0).getSymptomTagIds().split("::");
				if(tagList!=null){
					for(int i=0;i<tagList.length;i++){
						String tagName = userConsultDoctorBo.getSymptomTagNameById(Long.valueOf(tagList[i]));
						tagNameList.add(tagName);
					}
				}
				Long doctId = someConsultList.get(0).getDoctorId();
				Long usId = someConsultList.get(0).getUserId();
				doctorImg = userConsultDoctorBo.getDoctorNameAndImage(doctId);
				userImg = userConsultDoctorBo.getUserImgByUserId(usId);
				mes="成功";
				return "success";
			}
			mes="无数据";
			return "fail";
		}
		//add by sjt 2015-10-15 11:23 获取咨询列表 和咨询历史消息
		return "fail";
	}

	public String getMes() {
		return mes;
	}

	public List<UserConsultDoctor> getHistConsultList() {
		return histConsultList;
	}

	public void setTypeMethod(String typeMethod) {
		this.typeMethod = typeMethod;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
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
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	/**
	 * @param logId the logId to set
	 */
	public void setLogId(long logId) {
		this.logId = logId;
	}

	public List<Object> getLatestConsultList() {
		return latestConsultList;
	}

	public List<Long> getNewMesList() {
		return newMesList;
	}

	public List<List> getDoctorSomeMsgList() {
		return doctorSomeMsgList;
	}

	public List<String> getTagNameList() {
		return tagNameList;
	}

	public List<UserConsultDoctor> getSomeConsultList() {
		return someConsultList;
	}

	public Object getDoctorImg() {
		return doctorImg;
	}

	public String getUserImg() {
		return userImg;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Object[]> getNewLatestConsultList() {
		return newLatestConsultList;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setFdPackageId(Long fdPackageId) {
		this.fdPackageId = fdPackageId;
	}

	public void setImagedata(String imagedata) {
		this.imagedata = imagedata;
	}

	public String getSymptomImage() {
		return symptomImage;
	}

	public List<CommonDisease> getCommonDiseaseList() {
		return commonDiseaseList;
	}
	
}
