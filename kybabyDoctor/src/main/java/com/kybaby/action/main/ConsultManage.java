package com.kybaby.action.main;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.BabyBasicData;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.HealthRecord;
import com.kybaby.domain.UserConsultDoctor;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.commondiseaseanddrug.domain.CommonDisease;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.familydoctor.domain.ConsultBabyInfo;
import com.kybaby.newbussiness.familydoctor.domain.ConsultDoctorInfo;
import com.kybaby.newbussiness.familydoctor.domain.ConsultFastReplay;
import com.kybaby.newbussiness.familydoctor.domain.ConsultIllRecord;
import com.kybaby.util.EncryptUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author sujiantang
 *
 */
public class ConsultManage extends BaseAction{
	private static final long serialVersionUID = 1L;
	private UserConsultDoctor consult;
	private List<Object[]> consultList;
	private List<UserConsultDoctor> someConsultList;
	private List<HealthRecord> healthRecordList;
	private String mes;
	private ArrayList<Long> newMesNumList;
	private ArrayList<String> userInfoStrList;
	private ArrayList<String> someCousultStrList;
	private Long logId;
	private String userImg;
	private String doctorImg;
	private List<String> tagNameList;
	private BabyBasicData babyBasicData;
	private String doctorReply;
	private String doctorReplyImg;
	private String time;
	private String msgType="";
	private String isEnd="";
	private String userType="";
	private String imagedata; // 定义接收图片的base64字符串
	private String uploadDir="../kybabyBG/admin/images/consultPicture";//保存上传文件的目录
	private ConsultBabyInfo consultBabyInfo;//关联宝宝信息
	private List<CommonDisease> commonDiseaseList=new ArrayList<CommonDisease>();//所有病情标签列表
	private String symptomTagIds;//病情标签ids
	private List<ConsultIllRecord> consultIllRecordList;//病情记录列表
	private List<ConsultFastReplay> consultFastReplayList;//快捷回复列表
	private ConsultFastReplay consultFastReplay;//快捷回复列表
	/**
	 * 咨询医生信息
	 */
	private ConsultDoctorInfo consultDoctorInfo;
	
	@Override
	public String execute(){
		if(action.equals("getAll")){
			DoctorInfo doctorInfo = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo==null){
				mes="请登录";
				return "success";
			}
			//判断是否开放接单按钮（配置为咨询医生的可以有按钮）
			List<ConsultDoctorInfo> consultDoctorInfoList = consultBo.getConsultDoctorInfoList(doctorInfo);
			if(consultDoctorInfoList != null){
				this.consultDoctorInfo = consultDoctorInfoList.get(0);
			}
			consultList = new ArrayList<Object[]>();
			consultList = consultBo.getConsultByDoctorId(doctorInfo.getId(), isEnd,userType);
			
			if(consultList==null){
				mes="无数据";
				return "success";
			}else{
				//判断用户的第一句话是否为null
				Object[] someObj = consultList.get(0);
				if(someObj[4]==null && someObj[5]==null){
					mes="无数据";
					return "success";
				}
			}
			//排序，当前未结束会话放前面，结束回话放后面
			// 按距离顺序  
	        Collections.sort(consultList, new Comparator<Object[]>() {  
	            @Override
				public int compare(Object[] arg0, Object[] arg1) {  
	                double hits0 = "Y".equals(String.valueOf(arg0[11]))?1D:0D;  
	                double hits1 = "Y".equals(String.valueOf(arg1[11]))?1D:0D;    
	                if (hits1 > hits0) {  
	                    return -1;  
	                } else if (hits1 == hits0) {  
	                    return 0;  
	                } else {  
	                    return 1;  
	                }  
	            }  
	        });
			newMesNumList = new ArrayList<Long>();
			userInfoStrList = new ArrayList<String>();
			someCousultStrList = new ArrayList<String>();
			
			for(int j=0;j<consultList.size();j++){
				Long linsUserId = null;
				Object[] someObj = consultList.get(j);
				linsUserId = Long.valueOf(String.valueOf(someObj[1]));
				Long logId = Long.valueOf(String.valueOf(someObj[12]));
				//区分是否添加过病情印象
				String symptomTagIds = String.valueOf(someObj[3]);
				String isReply = String.valueOf(someObj[10]);
				String isEnd = String.valueOf(someObj[11]);
				
				String latistDoctor = consultBo.getLatistTime(doctorInfo.getId(),linsUserId, isEnd, userType);
				Long newMes = 0L;
				if("N".equals(isEnd)){
					if(StringUtils.isEmpty(latistDoctor)){
						newMes = consultBo.getNewMes(doctorInfo.getId(),linsUserId, isEnd,userType);
					}else{
						newMes = consultBo.getNewMes(doctorInfo.getId(),linsUserId,latistDoctor, isEnd,userType);
					}
				}
				newMesNumList.add(newMes);
				UserInfo userInfo = consultBo.getSomeUserInfoById(linsUserId);
				if(userInfo!=null){
					String userInfoStr = userInfo.getBabyName()+"::"+userInfo.getUserImage();
					userInfoStrList.add(userInfoStr);
				}
				consult = consultBo.getOneConsultByDoctorAndUserId(doctorInfo.getId(),linsUserId,logId,isEnd);
				String someConultStr = "";
				if(consult!=null){
					String packId = "";
					if(consult.getFdServicePackage()!=null){
						packId = consult.getFdServicePackage().getId().toString();
					}
					if(consult.getIsReply().equals("Y")){
						someConultStr = consult.getLogId()+"::"+consult.getDoctorReply()+"::"+
										consult.getSubmitTime()+"::"+consult.getUserType()+"::"+consult.getDoctorReplyImg() +
										"::" +packId;
					}else if(consult.getIsReply().equals("N")){
						someConultStr = consult.getLogId()+"::"+consult.getSymptomDescribe()+
										"::"+consult.getSubmitTime()+"::"+consult.getUserType()+"::"+consult.getSymptomImage() +
										"::" +packId;
					}
					
					if("Y".equals(isEnd)){//医生回复，病情标签
						List illRecodeList = this.consultBo.getConsultIllRecordList(logId);
						if(illRecodeList == null){
							someConultStr += "::待添加病情::要显示";
						}else{
							someConultStr += "::已添加病情::不显示";
						}
					}else{
						someConultStr += "::未添加病情::要显示";
					}
					someCousultStrList.add(someConultStr);
				}
			}
			mes="成功";
			return "success";
		}
		if(action.equals("detail")){
			tagNameList = new ArrayList<String>();
			someConsultList = new ArrayList<UserConsultDoctor>();
			someConsultList = consultBo.getSomeUserConsultDoctor(logId, isEnd);
			if(someConsultList!=null){
				for(UserConsultDoctor ucd : someConsultList){
					ucd.setIsDoctorAlreadyRead("Y");
					consultBo.updateUserConsultDoctor(ucd);
				}
				String tagIds = someConsultList.get(0).getSymptomTagIds();
				if(tagIds != null && !"".equals(tagIds)){
					String[] tagList = tagIds.split("::");
					if(tagList!=null){
						for(int i=0;i<tagList.length;i++){
							String tagName = consultBo.getSymptomTagNameById(Long.valueOf(tagList[i]));
							tagNameList.add(tagName);
						}
					}
				}
				Long doctId = someConsultList.get(0).getDoctorId();
				Long usId = someConsultList.get(0).getUserId();
				doctorImg = consultBo.getDoctorImgByDoctorId(doctId);
				userImg = consultBo.getUserImgByUserId(usId);
				this.consultBabyInfo = consultBo.getConsultBabyInfoByLogId(logId);
				//得到病情记录列表
				this.consultIllRecordList = this.consultBo.getConsultIllRecordList(logId);
				mes="成功";
				return "success";
			}
			mes="无数据";
			return "fail";
		}
		if(action.equals("after")){
			
			tagNameList = new ArrayList<String>();
			someConsultList = new ArrayList<UserConsultDoctor>();
			someConsultList = consultBo.getSomeUserConsultDoctor(logId, isEnd);
			if(someConsultList!=null){
				for(UserConsultDoctor ucd : someConsultList){
					ucd.setIsDoctorAlreadyRead("Y");
					consultBo.updateUserConsultDoctor(ucd);
				}
				String tagIds = someConsultList.get(0).getSymptomTagIds();
				if(tagIds != null && !"".equals(tagIds)){
					String[] tagList = tagIds.split("::");
					if(tagList!=null){
						for(int i=0;i<tagList.length;i++){
							String tagName = consultBo.getSymptomTagNameById(Long.valueOf(tagList[i]));
							tagNameList.add(tagName);
						}
					}
				}
				Long doctId = someConsultList.get(0).getDoctorId();
				Long usId = someConsultList.get(0).getUserId();
				doctorImg = consultBo.getDoctorImgByDoctorId(doctId);
				userImg = consultBo.getUserImgByUserId(usId);
				babyBasicData = consultBo.getBabyBasicDataByUserId(usId);
				//得到病情记录列表
				this.consultIllRecordList = this.consultBo.getConsultIllRecordList(logId);
				mes="成功";
				return "success";
			}
			mes="无数据";
			return "fail";
		}
		if(action.equals("replays")){
			DoctorInfo doctorInfo = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo==null){
				mes="请登录";
				return "fail";
			}
			if(logId==null){
				mes="无数据";
				return "fail";
			}
			UserConsultDoctor userConsult = consultBo.getUserConsultDoctorByLogId(logId);
			if(userConsult==null){
				mes="无数据";
				return "fail";
			}
			UserConsultDoctor userConsultDoctor = new UserConsultDoctor();
			Date date = new Date();
//			DateFormat df = DateFormat.getDateTimeInstance();//可以精确到时分秒
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			time=format.format(date);
			userConsultDoctor.setDoctorId(doctorInfo.getId());
			userConsultDoctor.setDoctorReply(doctorReply);
			if(this.imagedata != null && !"".equals(imagedata)){
				String current = DateManage.getDateStr("yyyyMMddhhmmss");
				String fileName=doctorInfo.getId() +"image" + current +".jpg";
				String fileAllName=uploadDir+"/"+fileName;
				String dir = ServletActionContext.getServletContext().getRealPath(fileAllName);
				try {
					if(EncryptUtil.uploadImage(imagedata,dir)){
						userConsultDoctor.setDoctorReplyImg(fileName);
						this.doctorReplyImg = fileName;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			userConsultDoctor.setIsBefore(userConsult.getIsBefore());
			userConsultDoctor.setUserType(userConsult.getUserType());
			userConsultDoctor.setFdServicePackage(userConsult.getFdServicePackage());
			userConsultDoctor.setIsEnd("N");
			userConsultDoctor.setIsReply("Y");
			userConsultDoctor.setLogId(logId);
			//userConsultDoctor.setMsgType("1");
			userConsultDoctor.setMsgType(msgType);
			userConsultDoctor.setSubmitTime(time);
			userConsultDoctor.setUserId(userConsult.getUserId());
			userConsultDoctor.setSymptomDescribe("");
			userConsultDoctor.setSymptomImage("");
			userConsultDoctor.setSymptomTagIds(symptomTagIds);
			userConsultDoctor.setComments("");
			consultBo.save(userConsultDoctor);
			mes="成功";
			return "success";
		}
		/**
		 * 家庭医生结束咨询
		 */
		if(action.equals("toEnd")){
			DoctorInfo doctorInfo = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo==null){
				mes="请登录";
				return "fail";
			}
			if(logId==null){
				mes="无数据";
				return "fail";
			}
			UserConsultDoctor userConsult = consultBo.getUserConsultDoctorByLogId(logId);
			if(userConsult==null){
				mes="无数据";
				return "fail";
			}
			long doctorId=doctorInfo.getId();
			List<UserConsultDoctor> allConsultion=consultBo.getAllConsulation(doctorId, logId, userConsult.getUserType());
			for(int i =0;i<allConsultion.size();i++){
				consultBo.updateSomeConsultById(allConsultion.get(i));
			}
			mes="成功";
			return "success";
		}
		/**
		 * 得到病情标签
		 */
		if(action.equals("getAllSymptomTag")){
			commonDiseaseList=consultBo.getAllCommonDisease();
			mes="成功";
			return "success";
		}
		/**
		 * 添加病情印象
		 */
		if(action.equals("addSymptomTag")){
			DoctorInfo doctorInfo = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo==null){
				mes="请登录";
				return "fail";
			}
			List<UserConsultDoctor> someConsultList = consultBo.getSomeUserConsultDoctor(logId, "Y");
			if(someConsultList != null && StringUtils.isNotEmpty(this.symptomTagIds)){
				Long userId = someConsultList.get(0).getUserId();
				UserInfo userInfo = new UserInfo();
				userInfo.setId(userId);
				String [] tagIds = symptomTagIds.split("::");
				for (int i = 0; i < tagIds.length; i++) {
					String tagId = tagIds[i];
					ConsultIllRecord illRecord = new ConsultIllRecord();
					CommonDisease commonDisease = new CommonDisease();
					commonDisease.setId(Long.valueOf(tagId));
					illRecord.setLogId(logId);
					illRecord.setCreateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
					illRecord.setDoctorInfo(doctorInfo);
					illRecord.setCommonDisease(commonDisease);
					illRecord.setUserInfo(userInfo);
					consultBo.addConsultIllRecord(illRecord);
				}
			}
			mes="成功";
			return "success";
		}
		/**
		 * 得到快捷回复列表
		 */
		if(action.equals("getConsultFastReplayList")){
			DoctorInfo doctorInfo = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo==null){
				mes="请登录";
				return "fail";
			}
			this.consultFastReplayList = this.consultBo.getConsultFastReplayList(doctorInfo);
			mes="成功";
		}
		/**
		 * 保存或更新快捷回复列表
		 */
		if(action.equals("saveOrUpdateConsultFastReplay")){
			DoctorInfo doctorInfo = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo==null){
				mes="请登录";
				return "fail";
			}
			consultFastReplay.setDoctorInfo(doctorInfo);
			this.consultBo.saveOrUpdateConsultFastReplay(consultFastReplay);
			mes="成功";
		}
		/**
		 * 保存或更新咨询医生信息
		 */
		if("saveOrUpdateConsultDoctorInfo".equals(action)){
			ConsultDoctorInfo consultDoctorInfoOld = this.consultBo.getConsultDoctorInfoById(consultDoctorInfo.getId());
			consultDoctorInfoOld.setIsOnline(consultDoctorInfo.getIsOnline());
			consultBo.saveOrUpdateConsultDoctorInfo(consultDoctorInfoOld);
			mes="成功";
		}
		/**
		 * 在对话页面时更新医生已读消息
		 */
		if("updateDoctorAlreadyRead".equals(action)){
			List<UserConsultDoctor> someConsultList = consultBo.getSomeUserConsultDoctor(logId, "N");
			if(someConsultList != null){
				for(UserConsultDoctor ucd : someConsultList){
					if(!"Y".equals(ucd.getIsUserAlreadyRead())){
						ucd.setIsDoctorAlreadyRead("Y");
						consultBo.updateUserConsultDoctor(ucd);
					}
				}
			}
			mes="成功";
		}
		
		return "success";
		
	}

//	public UserConsultDoctor getConsult() {
//		return consult;
//	}

//	public List<UserConsultDoctor> getConsultList() {
//		return consultList;
//	}

	public List<HealthRecord> getHealthRecordList() {
		return healthRecordList;
	}

	@Override
	public String getMes() {
		return mes;
	}

	public ArrayList<Long> getNewMesNumList() {
		return newMesNumList;
	}

	public ArrayList<String> getUserInfoStrList() {
		return userInfoStrList;
	}

	public ArrayList<String> getSomeCousultStrList() {
		return someCousultStrList;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public List<UserConsultDoctor> getSomeConsultList() {
		return someConsultList;
	}

	public String getUserImg() {
		return userImg;
	}

	public String getDoctorImg() {
		return doctorImg;
	}

	public List<String> getTagNameList() {
		return tagNameList;
	}

	public BabyBasicData getBabyBasicData() {
		return babyBasicData;
	}

	public void setDoctorReply(String doctorReply) {
		this.doctorReply = doctorReply;
	}

	public String getTime() {
		return time;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public void setDoctorReplyImg(String doctorReplyImg) {
		this.doctorReplyImg = doctorReplyImg;
	}

	public String getDoctorReplyImg() {
		return doctorReplyImg;
	}

	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}

	public void setImagedata(String imagedata) {
		this.imagedata = imagedata;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public ConsultBabyInfo getConsultBabyInfo() {
		return consultBabyInfo;
	}

	public List<CommonDisease> getCommonDiseaseList() {
		return commonDiseaseList;
	}

	public void setSymptomTagIds(String symptomTagIds) {
		this.symptomTagIds = symptomTagIds;
	}

	public List<ConsultIllRecord> getConsultIllRecordList() {
		return consultIllRecordList;
	}

	public List<ConsultFastReplay> getConsultFastReplayList() {
		return consultFastReplayList;
	}

	public void setConsultFastReplay(ConsultFastReplay consultFastReplay) {
		this.consultFastReplay = consultFastReplay;
	}

	public ConsultFastReplay getConsultFastReplay() {
		return consultFastReplay;
	}

	public ConsultDoctorInfo getConsultDoctorInfo() {
		return consultDoctorInfo;
	}

	public void setConsultDoctorInfo(ConsultDoctorInfo consultDoctorInfo) {
		this.consultDoctorInfo = consultDoctorInfo;
	}
}
