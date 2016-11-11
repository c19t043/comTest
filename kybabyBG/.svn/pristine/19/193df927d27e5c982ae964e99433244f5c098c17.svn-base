package com.kybaby.action.admin;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorAddress;
import com.kybaby.domain.DoctorGoodField;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Position;
import com.kybaby.domain.RecommentAwardRecord;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.util.EncryptUtil;
import com.opensymphony.xwork2.ActionContext;

public class DoctorHandle extends BaseAction {

	private String mes="";
	private String action="";
	
	private long doctorId;//医生Id
	private long updateId; //更新医生信息的Id
	private String status; //更改的状态
	private String authentication; //认证状态
	private String comments; //未审核通过的说明
	private String goodFieldIds; //擅长领域ids
	private String serviceTypeIds; //开放服务ids
	private String isRecommend; //是否推荐
	private String bankCard; //银行卡号
	private String headImgBase64; //头像
	private String licenseImageBase64; //资质
	private String doctorTitle; //职称
	/**
	 * 医生信息
	 */
	private DoctorInfo doctorInfo;
	
	private List briefDoctorInfo=new ArrayList(); //简要信息
	private List detailDoctorInfo=new ArrayList(); //详细信息
	private List<DoctorAddress>  doctorAddress=new ArrayList<DoctorAddress>();
	/**
	 * 职称列表
	 */
	private List<Position>  allPosition=new ArrayList<Position>();
	/**
	 * 擅长领域
	 */
	private List<DoctorGoodField>  doctorGoodFieldList=new ArrayList<DoctorGoodField>();
	/**
	 * 开放服务
	 */
	private List<DoctorServiceType>  doctorServiceTypeList=new ArrayList<DoctorServiceType>();
	
	private List allDoctor=new ArrayList();
	
	
	
	
	public String  execute() throws Exception 
	{
		if(action.equals("getBriefDoctor"))
		{
			System.out.println("doctorHandle.action?action=getBriefDoctor..........");
			briefDoctorInfo=doctorInfoBo.getAllBriefDoctorInfo();
			mes="成功";
			return "success";
		}
		
		if(action.equals("getDetailDoctor"))
		{
			System.out.println("doctorHandle.action?action=getDetailDoctor..........");
			//detailDoctorInfo=(List) doctorInfoBo.getDetailDoctorInfoById(doctorId);
			/*
			 * update by HooLee
			 * 2015年11月6日09:59:00
			 * 正确的数据也是获取不到医生用户的信息
			 * */
			detailDoctorInfo=(List)doctorInfoBo.newGetDetailDoctorInfoById(doctorId);
			doctorAddress=doctorAddressBo.getDoctorAddressById(doctorId);
			this.allPosition = this.positionBo.getAllPosition();
			mes="成功";
			return "success";
		}
		/**
		 * 得到所有擅长领域
		 */
		if(action.equals("getDoctorGoodFieldList")){
			DoctorGoodField dgf = new DoctorGoodField();
			dgf.setIsStart("Y");
			this.doctorGoodFieldList = this.majorBo.getAllGoodField(dgf);
			mes="成功";
			return "success";
		}
		/**
		 * 得到所有服务内容
		 */
		if(action.equals("getDoctorServiceTypeList")){
			this.doctorServiceTypeList = this.majorBo.getDoctorServiceTypeList(null);
			mes="成功";
			return "success";
		}
		
		if(action.equals("all"))
		{
			System.out.println("doctorHandle.action?action=all..........");
			allDoctor=doctorInfoBo.getAllDoctor();
			mes="成功";
			return "success";
		}
		
		if(action.equals("update"))
		{
			System.out.println("doctorHandle.action?action=update..........");
			DoctorInfo updateDoctor=doctorInfoBo.getDoctorInfoById(updateId);
			if(updateDoctor!=null)
			{
				updateDoctor.setDoctorStatus(status);
				updateDoctor.setAuthentication(authentication);
				updateDoctor.setGoodAtField(goodFieldIds);
				updateDoctor.setServiceTypeIds(serviceTypeIds);
				updateDoctor.setIsRecommend(isRecommend);
				updateDoctor.setBankCard(bankCard);
				updateDoctor.setDoctorTitle(doctorTitle);
				//医生姓名
				updateDoctor.setDoctorName(doctorInfo.getDoctorName());
				//医生手机
				updateDoctor.setDoctorPhone(doctorInfo.getDoctorPhone());
				//医生性别
				updateDoctor.setDoctorSex(doctorInfo.getDoctorSex());
				//医生工作单位
				updateDoctor.setDoctorEmployer(doctorInfo.getDoctorEmployer());
				//医生工作单位id
				updateDoctor.setHospitalId(doctorInfo.getHospitalId());
				//医生资质证号
				updateDoctor.setIdCard(doctorInfo.getIdCard());
				//医生经验
				updateDoctor.setClinicalExperience(doctorInfo.getClinicalExperience());
				//医生简介
				updateDoctor.setDoctorComment(doctorInfo.getDoctorComment());
				//科室
				updateDoctor.setDepartment(doctorInfo.getDepartment());
				//银行
				updateDoctor.setBankAccountName(doctorInfo.getBankAccountName());
				//医生类型
				updateDoctor.setDoctorType(doctorInfo.getDoctorType());
				
				if(authentication.equals("已通过")){
					//审核通过之后就让给医生以及推荐他的医生获取注册后发放的奖励
					RecommentAwardRecord awardRecord=doctorInfoBo.getSomeAwardRecord("医生推荐医生", updateId, "N", "注册后");
					if(awardRecord!=null){
						//首先给用户注册奖励
						if(awardRecord.getAmount()!=null){
							double amount=awardRecord.getAmount();
							updateDoctor.setDoctorBalance(getSecondBits(getSecondBits(updateDoctor.getDoctorBalance())+getSecondBits(amount)));
							doctorInfoBo.addNewDoctorAccount(updateId, getSecondBits(amount), "+", "注册余额奖励");
						}
						
						if(awardRecord.getPointsAmount()!=null){
							long pointsAmount=awardRecord.getPointsAmount();
							updateDoctor.setDoctorPoints(updateDoctor.getDoctorPoints()+pointsAmount);
							doctorInfoBo.addNewUserPoints(updateId, pointsAmount, "+", "注册积分奖励");
						}
						
						Long recommendDoctorId=awardRecord.getRecommendDoctorId();
						if(recommendDoctorId!=null){
							DoctorInfo doctor=doctorInfoBo.getDoctorInfoById(recommendDoctorId);
							if(doctor!=null&&doctor.getDoctorStatus().equals("Y")&&doctor.getAuthentication().equals("已通过")){
								long doctorId=doctor.getId();
								if(awardRecord.getAmount()!=null){
									double amount=awardRecord.getAmount();
									doctor.setDoctorBalance(getSecondBits(getSecondBits(doctor.getDoctorBalance())+getSecondBits(amount)));
									doctorInfoBo.addNewDoctorAccount(doctorId, getSecondBits(amount), "+", "注册余额奖励");
								}
								
								if(awardRecord.getPointsAmount()!=null){
									long pointsAmount=awardRecord.getPointsAmount();
									doctor.setDoctorPoints(doctor.getDoctorPoints()+pointsAmount);
									doctorInfoBo.addNewUserPoints(doctorId, pointsAmount, "+", "注册积分奖励");
								}
								
								baseBo.updateDoctorInfo(doctor);
								
							}
						}
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date rightNow=new Date(System.currentTimeMillis());
						String dateNow=sdf.format(rightNow);
						awardRecord.setAwardTime(dateNow);
						awardRecord.setIsGrant("Y");
						baseBo.updateRecommentAwardRecord(awardRecord);
						
					}
				}
				
				if(authentication!=null||!authentication.equals(""))
				{
					updateDoctor.setComments(comments);
				}
				//保存头像图片
				if(headImgBase64 != null && !"".equals(headImgBase64.trim())){
					String uploadDir_head="../kybabyBG/admin/images/doctorFaceIcon";
					this.creatDir(uploadDir_head);
					String fileName=updateDoctor.getDoctorImage();
					if(null==fileName||fileName.equals("")){
						String current = DateManage.getDateStr("yyyyMMddhhmmss");
						fileName="DH" + current +".jpg";
					}
					String fileAllName=uploadDir_head+"/"+fileName;
					String dir = ServletActionContext.getServletContext().getRealPath(fileAllName);
					if(EncryptUtil.uploadImage(headImgBase64,dir)){
						updateDoctor.setDoctorImage(fileName);
					}
				}
				//保存资质
				if(licenseImageBase64 != null && !"".equals(licenseImageBase64.trim())){
					String uploadDir_license="../kybabyBG/admin/images/doctorCertifiedPicture";
					this.creatDir(uploadDir_license);
					String fileName=updateDoctor.getLicenseImage();
					if(null==fileName||fileName.equals("")){
						String current = DateManage.getDateStr("yyyyMMddhhmmss");
						fileName="DI" + current +".jpg";	
					}
					String fileAllName=uploadDir_license+"/"+fileName;
					String dir = ServletActionContext.getServletContext().getRealPath(fileAllName);
					if(EncryptUtil.uploadImage(licenseImageBase64,dir)){
						updateDoctor.setLicenseImage(fileName);
					}
				}
				baseBo.updateDoctorInfo(updateDoctor);
				//保存服务类型医生关系表
				if(serviceTypeIds != null && !"".equals(serviceTypeIds)){
					this.majorBo.saveDoctorServiceContent(updateDoctor, serviceTypeIds);
				}
				
				mes="成功";
				return "success";
			}
			else
			{
				mes="查无此人";
				return "fail";
			}
		}
		return "success";
	}
	/**
	 *  检查目录是否存在，不存在创建
	 * @param filePath 目录路径
	 */
	private void creatDir(String filePath){
		String directory = ServletActionContext.getServletContext()
				.getRealPath(filePath + "/");
		File cacheDir = new File(directory);
		// 如果文件夹不存在则创建
		if (!cacheDir.exists() && !cacheDir.isDirectory()) {
			System.out.println("//不存在");
			cacheDir.mkdir();
		} else {
			System.out.println("//目录存在");
		}
	}
	
	public static double getSecondBits(double price){
		return (double)Math.round(price*100)/100;
	}

	public String getMes() {
		return mes;
	}

	public List getBriefDoctorInfo() {
		return briefDoctorInfo;
	}

	public List getDetailDoctorInfo() {
		return detailDoctorInfo;
	}

	public List<DoctorAddress> getDoctorAddress() {
		return doctorAddress;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public void setUpdateId(long updateId) {
		this.updateId = updateId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List getAllDoctor() {
		return allDoctor;
	}

	public List<DoctorGoodField> getDoctorGoodFieldList() {
		return doctorGoodFieldList;
	}

	public void setDoctorGoodFieldList(List<DoctorGoodField> doctorGoodFieldList) {
		this.doctorGoodFieldList = doctorGoodFieldList;
	}

	public void setGoodFieldIds(String goodFieldIds) {
		this.goodFieldIds = goodFieldIds;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

	public void setServiceTypeIds(String serviceTypeIds) {
		this.serviceTypeIds = serviceTypeIds;
	}

	public List<DoctorServiceType> getDoctorServiceTypeList() {
		return doctorServiceTypeList;
	}

	public void setDoctorServiceTypeList(
			List<DoctorServiceType> doctorServiceTypeList) {
		this.doctorServiceTypeList = doctorServiceTypeList;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public void setHeadImgBase64(String headImgBase64) {
		this.headImgBase64 = headImgBase64;
	}

	public void setLicenseImageBase64(String licenseImageBase64) {
		this.licenseImageBase64 = licenseImageBase64;
	}
	public void setDoctorTitle(String doctorTitle) {
		this.doctorTitle = doctorTitle;
	}
	public List<Position> getAllPosition() {
		return allPosition;
	}
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
}
