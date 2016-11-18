package com.java.doctorinfo.action;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.java.consultmanager.consultdoctormanager.vo.Position;
import com.java.doctorinfo.service.DoctorInfoService;
import com.java.doctorinfo.vo.DoctorCardInfo;
import com.java.doctorinfo.vo.DoctorLifeInfo;
import com.java.doctorinfo.vo.DoctorServiceType;
import com.java.doctorinfo.vo.OrganOperator;
import com.java.doctorinfo.vo.RecommendRule;
import com.java.doctorinfo.vo.RecommentAwardRecord;
import com.java.doctormanager.vo.DoctorMajor;
import com.java.doctormanager.vo.DoctorSignApprovalFlowRecord;
import com.java.ec.common.PageSortModel;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.platform.core.Action;
import com.java.util.DateManage;
import com.java.util.EncryptUtil;
import com.java.util.JsonUtil;
import com.java.util.MsgUtil;

public class DoctorInfoAction extends  Action{
	private static final long serialVersionUID = 1852094731493920752L;
	private String uploadDir = "../kybabyBG/admin/images/doctorFaceIcon";// 保存上传文件的目录
	private DoctorInfoService doctorInfoService;
	private DoctorInfo doctorInfo;
	private DoctorLifeInfo doctorLifeInfo;
	private String action;
	private String mes;
	private List<DoctorMajor> firstMajors;
	private List<DoctorMajor> secondMajors;
	private List<DoctorMajor> thirdMajors;
	private DoctorCardInfo doctorCardInfo;
	private List<Position>  positionList;
	private String professionFlag;
	private DoctorSignApprovalFlowRecord laterFlowRecord;
	private DoctorMajor major;
	private List<DoctorCardInfo> doctorCardInfos;
	private OrganOperator organOperator;
	private String organOperatorIDs;
	/**
	 * 跳转到添加页面传递的标识
	 * 添加:save
	 * 编辑:update
	 * 审核:verify
	 */
	private String pageFlag;
	/**
	 * 医院信息集合
	 */
	private List<HospitalBasicInfo> hospitalBasicInfoList;
	/**
	 * 服务内容集合
	 */
	private List<DoctorServiceType> doctorServiceTypes;
	@Override
	public String execute() throws Exception {
		/*
		 * 添加or更新医生基础信息
		 */
		if("saveOrUpdateDoctorBasicInfo".equals(action)){
			if("verify".equals(pageFlag)){//审核
				commitApprove();
			}else{//保存基础信息
				saveOrUpdateDoctorBasicInfo();
			}
			if(StringUtils.isNotBlank(mes)) return SUCCESS;//有错误信息将错误信息返回到原页面
			else return redirectActionResult("getDoctorInfoList");//成功跳转到列表页面
		}
		/*
		 * 跳转到签医生页面 
		 */
		else if("initDoctorInfoPage".equals(action)){
			initDoctorInfoPage();return SUCCESS;
		}
		/*
		 * 上传医生资格证书图片
		 */
		else if("uploadDoctorImageInfo".equals(action)){
			uploadDoctorImageInfo();
		}
		/*
		 * 删除医生资格证书图片
		 */
		else if("removeDoctorImageInfo".equals(action)){
			removeDoctorImageInfo();
		}
		/*
		 * 检查医生电话是否重复
		 */
		else if("checkDoctorPhone".equals(action)){
			checkDoctorPhone();
			if(StringUtils.isBlank(mes)) {mes = "成功";}
			JsonUtil.writeText(getResponseValue(), mes);
			return NONE;
		}
		/*
		 * 查询操作人列表
		 */
		else if("getOrganOparatorList".equals(action)){
			getOrganOparatorList();
			return "oparatorList";
		}
		if(StringUtils.isBlank(mes)) {
			mes = "成功";
		}
		return SUCCESS;
	}
	/**
	 * 操作人列表
	 */
	public String getOrganOparatorList(){ 	
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		List<OrganOperator> list = doctorInfoService.getOrganOperator(psm, organOperator);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 *	获取医生数据维护人
	 */
	public String getMaintenancePerson(){
		List<OrganOperator> doctorRegisterMaintenance = doctorInfoService.getDoctorRegisterMaintenance(doctorInfo.getId());
		JSONArray ja = JSONArray.fromObject(doctorRegisterMaintenance);
		JsonUtil.writeJson(this.getResponseValue(), ja.toString());
		return NONE;
	}
	/**
	 *	添加医生数据维护人
	 */
	public String addMaintenancePerson(){
		doctorInfoService.saveMaintenanceWithBath(doctorInfo.getId(), organOperatorIDs);
		JSONObject jo = new JSONObject();
		jo.put("mes", "成功");
		JsonUtil.writeJson(this.getResponseValue(), jo.toString());
		return NONE;
	}
	/**
	 * 提交审批
	 */
	private void commitApprove(){
		//修改医生表中流程记录
		DoctorInfo dctInfo = doctorInfoService.get(doctorInfo.getId(), DoctorInfo.class);
/*		List<String> requiredFieldIsEmpty = dctInfo.requiredFieldIsEmpty();
		if(!requiredFieldIsEmpty.isEmpty()){
			StringBuilder sb= new StringBuilder();
			for (String str : requiredFieldIsEmpty) {
				sb.append(str).append(",");
			}
			sb.append("不能为空,请补全资料后再保存");
			mes = sb.toString();
			return;
		}*/
		dctInfo.setDoctorComment(doctorInfo.getDoctorComment());
		dctInfo.setAuthentication(doctorInfo.getAuthentication());
		Date date = new Date();
		dctInfo.setOpTime(DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(date));
		if(StringUtils.isNotBlank(doctorInfo.getAuthentication())&&"已通过".equals(doctorInfo.getAuthentication())){
			dctInfo.setAuthenticationTime(DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(date));
			dctInfo.setDoctorStatus("Y");
		}else{
			dctInfo.setAuthenticationTime(null);
			dctInfo.setDoctorStatus("N");
		}
		dctInfo.setFlowStatus(doctorInfo.getFlowStatus());
		doctorInfoService.update(dctInfo);
		//添加流程记录
		laterFlowRecord = new DoctorSignApprovalFlowRecord();
		laterFlowRecord.setFlowStatus(doctorInfo.getFlowStatus());
		laterFlowRecord.setBusinessId(dctInfo.getId());//医生ID
		laterFlowRecord.setOperateTime(new Timestamp(date.getTime()));
		laterFlowRecord.setOrganOperatorId(dctInfo.getOrganOperator()==null?null:dctInfo.getOrganOperator().getId());//提交审批人
		laterFlowRecord.setOrganReceverId(null);//流程审批人
		doctorInfoService.save(laterFlowRecord);
	}
	/**
	 * 检查医生电话是否重复
	 */
	private void checkDoctorPhone(){
		boolean checkDoctorPhone = doctorInfoService.checkDoctorPhone(doctorInfo.getDoctorPhone());
		if(!checkDoctorPhone) this.mes = (String) MsgUtil.get(); 
	}
	/**
	 * 删除医生资格证书图片
	 */
	private void removeDoctorImageInfo(){
		doctorInfoService.deleteDoctorCardInfo(doctorCardInfo.getId());
	}
	/**
	 * 上传医生资格证书图片
	 */
	private void uploadDoctorImageInfo(){
		//上传证书图片
		doctorCardInfo.setImgPath(uploadImage(doctorCardInfo.getId(), doctorCardInfo.getImgPath(), doctorCardInfo.getImgBase64()));
		doctorInfoService.saveOrUpdateDoctorCardInfo(doctorCardInfo);
	}
	/**
	 * 添加or更新医生基础信息
	 */
	private void saveOrUpdateDoctorBasicInfo(){
		DoctorInfo dctInfo =  doctorInfoService.get(doctorInfo.getId(), DoctorInfo.class);
		if("未提交,已驳回".contains(dctInfo.getFlowStatus())){
			//保存图片
			String uploadImage = uploadImage(doctorInfo.getId(), dctInfo.getDoctorImage(), doctorInfo.getImgBase64());
			doctorInfo.setDoctorImage(uploadImage);
		}
		if(dctInfo.getOrganOperator()==null){
			doctorInfo.setOrganOperator(null);
		}
		doctorInfo = doctorInfoService.saveOrUpdateDoctorInfo(doctorInfo);
		//保存推荐人
		if(StringUtils.isNotBlank(doctorInfo.getRecommendPhone())){
			saveRecommedPerson(doctorInfo.getRecommendPhone());
		}
		//添加or更新医生生活信息 
		saveOrUpdateDoctorLifInfo();
	}
	/**
	 * 初始化加载页面必要数据
	 */
	private void initPageData(){
		if(major==null){
			major = new DoctorMajor();
			major.setDoctorType(professionFlag);
		}
		//医院基本信息
		positionList = doctorInfoService.getAllPosition();//医生职称
		doctorServiceTypes = doctorInfoService.getAllDoctorServiceTypes();//医生服务列表
		hospitalBasicInfoList = doctorInfoService.getHospitalBasicInfos(null);//医院列表
		firstMajors = doctorInfoService.getMajors(major, "first");
		secondMajors = doctorInfoService.getMajors(major, "second");
		thirdMajors = doctorInfoService.getMajors(major, "third");
		//doctorGoodFields = super.doctorRegisterDataGatherService.getDoctorGoodFields(professionFlag);
	}
	/**
	 * 跳转到签医生页面 
	 */
	private void initDoctorInfoPage(){
		//初始化加载页面必要数据
		initPageData();
		if(doctorInfo!=null){
			Long dctID = doctorInfo.getId();
			if(dctID!=null){
				//医生基础信息
				doctorInfo = doctorInfoService.get(dctID, DoctorInfo.class);
				//身份证明
				doctorCardInfos = doctorInfoService.getDoctorCardInfos(doctorInfo.getId());
				//生活信息
				List<DoctorLifeInfo> doctorLifeInfos = doctorInfoService.getDoctorLifeInfos(doctorInfo.getId());
				if(doctorLifeInfos.isEmpty()) doctorLifeInfo = null;
				else doctorLifeInfo = doctorLifeInfos.get(0);
				//审批信息
				this.laterFlowRecord = doctorInfoService.getLaterFlowRecord(dctID);
			}
		}
	}
	/**
	 * 添加or更新医生生活信息 
	 */
	private void saveOrUpdateDoctorLifInfo(){
		if(doctorLifeInfo!=null){
			DoctorInfo dctInfo =  doctorInfoService.get(doctorInfo.getId(), DoctorInfo.class);
			doctorLifeInfo.setDoctorInfo(dctInfo);
			doctorInfoService.saveOrUpdateDoctorLifeInfo(doctorLifeInfo);
		}
	}
	/**
	 * 保存推荐人
	 * @param recommendPhone
	 */
	private void saveRecommedPerson(String recommendPhone){
		//注册成功之后，缺少推荐奖励的实现
		//填写了推荐人信息，客户需求做到医生推荐医生，对于其他的情况，暂不处理，只需要添加记录
		RecommendRule userRule = doctorInfoService.getSomeCanUseRule("医生推荐医生");
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
			if(!(recommendPhone.equals(""))){
				DoctorInfo referDoc=doctorInfoService.getDoctorInfoByPhoneNum(recommendPhone);
				if(referDoc!=null){
					ruleCord.setRecommendDoctorId(referDoc.getId());
				}else{
					ruleCord.setComments("推荐人不是平台注册用户,推荐人电话:"+recommendPhone);
				}
			}
			ruleCord.setBeenRecommendDoctorId(doctorInfo.getId());
			//增加记录
			doctorInfoService.saveRecommentAwardRecord(ruleCord);
		}
	}
	/**
	 * 上传图片
	 * @param id 对象ID 
	 * @param imagePath 已有的图片路径
	 * @param imgBase64 base64编码后的图片
	 * @return
	 */
	private String uploadImage(Long id,String imagePath,String imgBase64){
		String tempDir = "";
		if (id != null && StringUtils.isNotBlank(imagePath)) {
			tempDir = uploadDir + "/" + imagePath;
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			String current = df.format(new Date());
			String bannerName = "fd" + current + ".jpg";
			tempDir = uploadDir + "/" + bannerName;
			imagePath = tempDir;
		}
		if (StringUtils.isNotBlank(imgBase64)) {
			String directory = ServletActionContext.getServletContext()
					.getRealPath(uploadDir + "/");
			File cacheDir = new File(directory);
			// 如果文件夹不存在则创建
			if (!cacheDir.exists() && !cacheDir.isDirectory()) {
				System.out.println("//不存在");
				cacheDir.mkdirs();
			} else {
				System.out.println("//目录存在");
			}
			// 上传图片
			String dir = ServletActionContext.getServletContext()
					.getRealPath(tempDir);
			try {
				EncryptUtil.uploadImage(imgBase64, dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(imagePath.indexOf("/")!=-1) imagePath = imagePath.substring(imagePath.lastIndexOf("/")+1);
		return imagePath;
	}
	/**
	 * 医生基本信息列表
	 * @return
	 */
	public String getDoctorInfoList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(doctorInfo == null){
			doctorInfo = new DoctorInfo();
		}
		List<DoctorInfo> list = doctorInfoService.getDoctorInfoListByPage(psm, doctorInfo);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	
	//----------------------------setter/getter
	public DoctorInfoService getDoctorInfoService() {
		return doctorInfoService;
	}
	public void setDoctorInfoService(DoctorInfoService doctorInfoService) {
		this.doctorInfoService = doctorInfoService;
	}
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	public String getUploadDir() {
		return uploadDir;
	}
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	public DoctorLifeInfo getDoctorLifeInfo() {
		return doctorLifeInfo;
	}
	public void setDoctorLifeInfo(DoctorLifeInfo doctorLifeInfo) {
		this.doctorLifeInfo = doctorLifeInfo;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public List<DoctorMajor> getFirstMajors() {
		return firstMajors;
	}
	public void setFirstMajors(List<DoctorMajor> firstMajors) {
		this.firstMajors = firstMajors;
	}
	public List<DoctorMajor> getSecondMajors() {
		return secondMajors;
	}
	public void setSecondMajors(List<DoctorMajor> secondMajors) {
		this.secondMajors = secondMajors;
	}
	public List<DoctorMajor> getThirdMajors() {
		return thirdMajors;
	}
	public void setThirdMajors(List<DoctorMajor> thirdMajors) {
		this.thirdMajors = thirdMajors;
	}
	public DoctorCardInfo getDoctorCardInfo() {
		return doctorCardInfo;
	}
	public void setDoctorCardInfo(DoctorCardInfo doctorCardInfo) {
		this.doctorCardInfo = doctorCardInfo;
	}
	public List<Position> getPositionList() {
		return positionList;
	}
	public void setPositionList(List<Position> positionList) {
		this.positionList = positionList;
	}
	public String getProfessionFlag() {
		return professionFlag;
	}
	public void setProfessionFlag(String professionFlag) {
		this.professionFlag = professionFlag;
	}
	public DoctorSignApprovalFlowRecord getLaterFlowRecord() {
		return laterFlowRecord;
	}
	public void setLaterFlowRecord(DoctorSignApprovalFlowRecord laterFlowRecord) {
		this.laterFlowRecord = laterFlowRecord;
	}
	public DoctorMajor getMajor() {
		return major;
	}
	public void setMajor(DoctorMajor major) {
		this.major = major;
	}
	public List<DoctorCardInfo> getDoctorCardInfos() {
		return doctorCardInfos;
	}
	public void setDoctorCardInfos(List<DoctorCardInfo> doctorCardInfos) {
		this.doctorCardInfos = doctorCardInfos;
	}
	public List<HospitalBasicInfo> getHospitalBasicInfoList() {
		return hospitalBasicInfoList;
	}
	public void setHospitalBasicInfoList(
			List<HospitalBasicInfo> hospitalBasicInfoList) {
		this.hospitalBasicInfoList = hospitalBasicInfoList;
	}
	public List<DoctorServiceType> getDoctorServiceTypes() {
		return doctorServiceTypes;
	}
	public void setDoctorServiceTypes(List<DoctorServiceType> doctorServiceTypes) {
		this.doctorServiceTypes = doctorServiceTypes;
	}
	public OrganOperator getOrganOperator() {
		return organOperator;
	}
	public void setOrganOperator(OrganOperator organOperator) {
		this.organOperator = organOperator;
	}
	public String getOrganOperatorIDs() {
		return organOperatorIDs;
	}
	public void setOrganOperatorIDs(String organOperatorIDs) {
		this.organOperatorIDs = organOperatorIDs;
	}
	public String getPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}
}
