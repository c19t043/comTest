package com.kybaby.newbussiness.doctorsign.action;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kybaby.domain.DoctorGoodField;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Position;
import com.kybaby.domain.RecommendRule;
import com.kybaby.domain.RecommentAwardRecord;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.doctorsign.domain.DoctorCardInfo;
import com.kybaby.newbussiness.doctorsign.domain.DoctorLifeInfo;
import com.kybaby.newbussiness.doctorsign.domain.DoctorMajor;
import com.kybaby.newbussiness.doctorsign.domain.DoctorOrderSummary;
import com.kybaby.newbussiness.doctorsign.domain.DoctorSignApprovalFlowRecord;
import com.kybaby.newbussiness.doctorsign.vo.TableList;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganOperator;
import com.kybaby.util.EncryptUtil;
import com.kybaby.util.MsgUtil;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class DoctorDataGatherAction extends NewBaseAction{
	
	private String uploadDir = "../kybabyBG/admin/images/doctorFaceIcon";// 保存上传文件的目录
	private DoctorInfo doctorInfo;
	private DoctorCardInfo doctorCardInfo;
	private DoctorLifeInfo doctorLifeInfo;
	private HospitalBasicInfo hospitalBasicInfo;
	private List<Position>  positionList;
	private DoctorMajor major;
	private List<DoctorMajor> firstMajors;
	private List<DoctorMajor> secondMajors;
	private List<DoctorMajor> thirdMajors;
	private String mes;
	private List<DoctorCardInfo> doctorCardInfos;
	private String professionFlag;
	private List<TableList> SignDoctors;
	private List<TableList> doctorOrderSummarys;
	private List<DoctorGoodField> doctorGoodFields;
	private OrganOperator organOperator;
	private DoctorSignApprovalFlowRecord laterFlowRecord;
	/**
	 * 服务内容集合
	 */
	private List<DoctorServiceType> doctorServiceTypes;
	/**
	 * 医院信息集合
	 */
	private List<HospitalBasicInfo> hospitalBasicInfoList;
	
	@Override
	public String execute() throws Exception {
		organOperator = new OrganOperator();
		organOperator.setId(6L);
/*		organOperator = queryLogonUser();
		if(organOperator==null){
			mes="请登录";
			return SUCCESS;
		}*/
		/*
		 * 添加or更新医生基础信息
		 */
		if("saveOrUpdateDoctorBasicInfo".equals(action)){
			saveOrUpdateDoctorBasicInfo();
		}
		/*
		 * 提交审批
		 */
		else if("commitApprove".equals(action)){
			commitApprove();
		}
		/*
		 * 跳转到签医生页面 
		 */
		else if("initDoctorInfoPage".equals(action)){
			initDoctorInfoPage();
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
		}
		/*
		 * 开单查询
		 */
		else if("queryDoctorOrderSummary".equals(action)){
			queryDoctorOrderSummary();
		}
		/*
		 * 维护医生信息查询
		 */
		else if("maintenanceDoctor".equals(action)){
			maintenanceDoctor();
		}
		/*
		 * 要签约的医生信息查询
		 */
		else if("querySignDoctor".equals(action)){
			querySignDoctor();
		}
		else{
			mes = "没有此方法";
		}
		if(StringUtils.isBlank(mes)) mes = "成功";
		return SUCCESS;
	}
	/**
	 * 维护医生信息查询
	 */
	private void maintenanceDoctor(){
		Long logonUserID = organOperator.getId();
		//查询签约的人
		List<DoctorInfo> doctorinfos = doctorRegisterDataGatherService.queryMaintenanceAbleDoctorInfos(logonUserID);
		//格式化要显示的医生数据
		formatTableList(doctorinfos);
	}
	/**
	 * 检查医生电话是否重复
	 */
	private void checkDoctorPhone(){
		boolean checkDoctorPhone = doctorRegisterDataGatherService.checkDoctorPhone(doctorInfo.getDoctorPhone());
		if(!checkDoctorPhone) this.mes = (String) MsgUtil.get(); 
	}
	/**
	 * 开单查询
	 */
	private void queryDoctorOrderSummary(){
		Long logonUserID = organOperator.getId();
		//获取当前操作人有关联的医生数据
		List<DoctorInfo> queryViewableDoctorInfos = doctorRegisterDataGatherService.queryViewableDoctorInfos(logonUserID);
		//开单记录
		List<DoctorOrderSummary> doctorOrderSummarys = doctorRegisterDataGatherService.getDoctorOrderSummarys(logonUserID);
		this.doctorOrderSummarys = new ArrayList<TableList>();
		//格式化组装医生开单数据
		Map<String, Object> packageDoctorOrders = packageDoctorOrders(doctorOrderSummarys);
		for (String key : packageDoctorOrders.keySet()) {
			List<DoctorOrderSummary> dos = (List<DoctorOrderSummary>)packageDoctorOrders.get(key);
			this.doctorOrderSummarys.add(new TableList(key, dos.size()+"", dos));
		}
		//筛选出未开单的医生
		queryViewableDoctorInfos = selectNoOrder(queryViewableDoctorInfos,doctorOrderSummarys);
		//格式化组装医生信息
		formatTableList(queryViewableDoctorInfos);
	}
	/**
	 * 筛选出未开单的医生
	 */
	private List<DoctorInfo> selectNoOrder(List<DoctorInfo> doctorInfoByLogonUser,List<DoctorOrderSummary> doctorOrderSummarys){
		StringBuilder ids = new StringBuilder();
		for(DoctorOrderSummary doctorOrderSummary : doctorOrderSummarys){
			ids.append(doctorOrderSummary.getDoctorInfo().getId()).append(",");
		}
		String tmp_ids = ids.toString();
		List<DoctorInfo> dctInfos = new ArrayList<DoctorInfo>();
		if(!doctorInfoByLogonUser.isEmpty()){
			for (DoctorInfo doctorInfo : doctorInfoByLogonUser) {
				if(!tmp_ids.contains(doctorInfo.getId()+",")){
					dctInfos.add(doctorInfo);
				}
			}
		}
		return dctInfos;
	}
	/**
	 * 格式化组装医生开单数据
	 * @param doctorinfos
	 * @return
	 */
	private Map<String,Object> packageDoctorOrders(List<DoctorOrderSummary> doctorOrderSummarys){
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		for (DoctorOrderSummary doctorOrder : doctorOrderSummarys) {
			Date tmp_specify_date =  DateManage.parseStr2Date_yyyy_MM_dd(doctorOrder.getVisitDate());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
			String key = sdf.format(tmp_specify_date);
			Object object = map.get(key);				
			if(object==null){
				object = new ArrayList<DoctorInfo>();
				map.put(key, object);
			}
			((List)object).add(doctorOrder);
		}
		return map;
	}
	/**
	 * 提交审批
	 */
	private void commitApprove(){
		//修改医生表中流程记录
		doctorInfo = doctorRegisterDataGatherService.get(doctorInfo.getId(), DoctorInfo.class);
		List<String> requiredFieldIsEmpty = doctorInfo.requiredFieldIsEmpty();
		if(!requiredFieldIsEmpty.isEmpty()){
			StringBuilder sb= new StringBuilder();
			for (String str : requiredFieldIsEmpty) {
				sb.append(str).append(",");
			}
			sb.append("不能为空,请补全资料后再提交");
			mes = sb.toString();
			return;
		}
		doctorInfo.setAuthentication("已申请");
		doctorInfo.setFlowStatus("已提交");
		doctorRegisterDataGatherService.update(doctorInfo);
		//添加流程记录
		addFlowRecord();
	}
	/**
	 * 添加流程记录
	 */
	private void addFlowRecord(){
		DoctorSignApprovalFlowRecord flowRecord = new DoctorSignApprovalFlowRecord();
		flowRecord.setBusinessId(doctorInfo.getId());//医生ID
		flowRecord.setFlowStatus("已提交");//流程状态
		flowRecord.setOperateTime(new Timestamp(new Date().getTime()));
		flowRecord.setOrganOperatorId(organOperator.getId());//提交审批人
		flowRecord.setOrganReceverId(null);//流程审批人
		doctorRegisterDataGatherService.save(flowRecord);
	}
	/**
	 * 删除医生资格证书图片
	 */
	private void removeDoctorImageInfo(){
		doctorRegisterDataGatherService.deleteDoctorCardInfo(doctorCardInfo.getId());
	}
	/**
	 * 上传医生资格证书图片
	 */
	private void uploadDoctorImageInfo(){
		if(doctorCardInfo.getDoctorInfo()==null||(doctorCardInfo.getDoctorInfo()!=null&&doctorCardInfo.getDoctorInfo().getId()==null)){
			this.doctorInfo = new DoctorInfo();
			doctorInfo.setOrganOperator(organOperator);
			doctorRegisterDataGatherService.saveOrUpdateDoctorInfo(doctorInfo);
			doctorCardInfo.setDoctorInfo(this.doctorInfo);
		}
		//上传证书图片
		doctorCardInfo.setImgPath(uploadImage(doctorCardInfo.getId(), doctorCardInfo.getImgPath(), doctorCardInfo.getImgBase64()));
		doctorRegisterDataGatherService.saveOrUpdateDoctorCardInfo(doctorCardInfo);
	}
	/**
	 * 添加or更新医生生活信息 
	 */
	private void saveOrUpdateDoctorLifInfo(){
		if(doctorLifeInfo!=null){
			DoctorInfo dctInfo =  doctorRegisterDataGatherService.get(doctorInfo.getId(), DoctorInfo.class);
			doctorLifeInfo.setDoctorInfo(dctInfo);
			super.doctorRegisterDataGatherService.saveOrUpdateDoctorLifeInfo(doctorLifeInfo);
		}
	}
	/**
	 * 要签约的医生信息查询
	 */
	private void querySignDoctor(){
		Long logonUserID = organOperator.getId();
		//查询签约的人
		List<DoctorInfo> doctorinfos = doctorRegisterDataGatherService.getMySignDoctorInfos(logonUserID);
		//格式化要显示的医生数据
		formatTableList(doctorinfos);
	}
	/**
	 * 格式化要显示的医生数据
	 */
	private void formatTableList(List<DoctorInfo> doctorinfos){
		SignDoctors = new ArrayList<TableList>();
		//格式化组装医生基础信息
		Map<String, Object> packageDoctorInfos = packageDoctorInfos(doctorinfos);
		for (String key : packageDoctorInfos.keySet()) {
			List<DoctorInfo> dcts = (List<DoctorInfo>)packageDoctorInfos.get(key);
			SignDoctors.add(new TableList(key, dcts.size()+"", dcts));
		}
	}
	/**
	 * 格式化组装医生基础信息
	 * @param doctorinfos
	 * @return
	 */
	private Map<String,Object> packageDoctorInfos(List<DoctorInfo> doctorinfos){
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		for (DoctorInfo doctorInfo : doctorinfos) {
			Date tmp_specify_date =  DateManage.parseStr2Date_yyyy_MM_dd(doctorInfo.getRegisterTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
			String key = sdf.format(tmp_specify_date);
			Object object = map.get(key);				
			if(object==null){
				object = new ArrayList<DoctorInfo>();
				map.put(key, object);
			}
			((List)object).add(doctorInfo);
		}
		return map;
	}
	/**
	 * 查询登陆人
	 * @return
	 */
	private OrganOperator queryLogonUser(){
		return (OrganOperator)ActionContext.getContext().getSession().get("organOperator");
	}
	/**
	 * 添加or更新医生基础信息
	 */
	private void saveOrUpdateDoctorBasicInfo(){
		Long id = doctorInfo.getId();
		String uploadImage = "";
		if(id!=null){
			DoctorInfo dctInfo =  doctorRegisterDataGatherService.get(doctorInfo.getId(), DoctorInfo.class);
			String tmp_status = StringUtils.isNotBlank(dctInfo.getFlowStatus())?dctInfo.getFlowStatus():"未知";
			if("未提交,已驳回".contains(tmp_status)){
				//保存图片
				uploadImage = uploadImage(doctorInfo.getId(), dctInfo.getDoctorImage(), doctorInfo.getImgBase64());
			}
		}else{
			uploadImage = uploadImage(doctorInfo.getId(), doctorInfo.getDoctorImage(), doctorInfo.getImgBase64());
		}
		doctorInfo.setDoctorImage(uploadImage);
		doctorInfo.setOrganOperator(organOperator);
		doctorInfo = doctorRegisterDataGatherService.saveOrUpdateDoctorInfo(doctorInfo);
		//保存推荐人
		if(StringUtils.isNotBlank(doctorInfo.getRecommendPhone())){
			saveRecommedPerson(doctorInfo.getRecommendPhone());
		}
		//添加or更新医生生活信息 
		saveOrUpdateDoctorLifInfo();
	}
	/**
	 * 保存推荐人
	 * @param recommendPhone
	 */
	private void saveRecommedPerson(String recommendPhone){
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
			if(!(recommendPhone.equals(""))){
				DoctorInfo referDoc=doctorInfoBo.getDoctorInfoByPhoneNum(recommendPhone);
				if(referDoc!=null){
					ruleCord.setRecommendDoctorId(referDoc.getId());
				}else{
					ruleCord.setComments("推荐人不是平台注册用户,推荐人电话:"+recommendPhone);
				}
			}
			ruleCord.setBeenRecommendDoctorId(doctorInfo.getId());
			//增加记录
			accountBo.saveRecommentAwardRecord(ruleCord);
		}
	}
	/**
	 * 跳转到签医生页面 
	 */
	private void initDoctorInfoPage(){
		//初始化加载页面必要数据
		initPageData();
		Long dctID = doctorInfo.getId();
		if(dctID!=null){
			//医生基础信息
			doctorInfo = doctorRegisterDataGatherService.get(dctID, DoctorInfo.class);
			//身份证明
			doctorCardInfos = super.doctorRegisterDataGatherService.getDoctorCardInfos(doctorInfo.getId());
			//生活信息
			List<DoctorLifeInfo> doctorLifeInfos = super.doctorRegisterDataGatherService.getDoctorLifeInfos(doctorInfo.getId());
			if(doctorLifeInfos.isEmpty()) doctorLifeInfo = null;
			else doctorLifeInfo = doctorLifeInfos.get(0);
			//审批信息
			this.laterFlowRecord = super.doctorRegisterDataGatherService.getLaterFlowRecord(dctID);
		}
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
		positionList = doctorIdentifyBo.getAllPosition();//医生职称
		doctorServiceTypes = super.doctorRegisterDataGatherService.getAllDoctorServiceTypes();//医生服务列表
		hospitalBasicInfoList = super.doctorRegisterDataGatherService.getHospitalBasicInfos(null);//医院列表
		firstMajors = super.doctorRegisterDataGatherService.getMajors(major, "first");
		secondMajors = super.doctorRegisterDataGatherService.getMajors(major, "second");
		thirdMajors = super.doctorRegisterDataGatherService.getMajors(major, "third");
		//doctorGoodFields = super.doctorRegisterDataGatherService.getDoctorGoodFields(professionFlag);
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
	
	
	
	
	
	
	//----------------setter/getter---------------------------------------
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public DoctorMajor getMajor() {
		return major;
	}
	public void setMajor(DoctorMajor major) {
		this.major = major;
	}
	@Override
	public String getMes() {
		return mes;
	}
	@Override
	public void setMes(String mes) {
		this.mes = mes;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	public DoctorCardInfo getDoctorCardInfo() {
		return doctorCardInfo;
	}
	public void setDoctorCardInfo(DoctorCardInfo doctorCardInfo) {
		this.doctorCardInfo = doctorCardInfo;
	}
	public DoctorLifeInfo getDoctorLifeInfo() {
		return doctorLifeInfo;
	}
	public void setDoctorLifeInfo(DoctorLifeInfo doctorLifeInfo) {
		this.doctorLifeInfo = doctorLifeInfo;
	}
	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}
	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}
	public List<Position> getPositionList() {
		return positionList;
	}
	public void setPositionList(List<Position> positionList) {
		this.positionList = positionList;
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
	public List<DoctorServiceType> getDoctorServiceTypes() {
		return doctorServiceTypes;
	}
	public void setDoctorServiceTypes(List<DoctorServiceType> doctorServiceTypes) {
		this.doctorServiceTypes = doctorServiceTypes;
	}
	public List<HospitalBasicInfo> getHospitalBasicInfoList() {
		return hospitalBasicInfoList;
	}
	public void setHospitalBasicInfoList(
			List<HospitalBasicInfo> hospitalBasicInfoList) {
		this.hospitalBasicInfoList = hospitalBasicInfoList;
	}
	public String getUploadDir() {
		return uploadDir;
	}
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	public List<DoctorCardInfo> getDoctorCardInfos() {
		return doctorCardInfos;
	}
	public void setDoctorCardInfos(List<DoctorCardInfo> doctorCardInfos) {
		this.doctorCardInfos = doctorCardInfos;
	}
	public String getProfessionFlag() {
		return professionFlag;
	}
	public void setProfessionFlag(String professionFlag) {
		this.professionFlag = professionFlag;
	}
	public List<TableList> getSignDoctors() {
		return SignDoctors;
	}
	public void setSignDoctors(List<TableList> signDoctors) {
		SignDoctors = signDoctors;
	}
	public List<DoctorGoodField> getDoctorGoodFields() {
		return doctorGoodFields;
	}
	public void setDoctorGoodFields(List<DoctorGoodField> doctorGoodFields) {
		this.doctorGoodFields = doctorGoodFields;
	}
	public OrganOperator getOrganOperator() {
		return organOperator;
	}
	public void setOrganOperator(OrganOperator organOperator) {
		this.organOperator = organOperator;
	}
	public List<TableList> getDoctorOrderSummarys() {
		return doctorOrderSummarys;
	}
	public void setDoctorOrderSummarys(List<TableList> doctorOrderSummarys) {
		this.doctorOrderSummarys = doctorOrderSummarys;
	}
	public DoctorSignApprovalFlowRecord getLaterFlowRecord() {
		return laterFlowRecord;
	}
	public void setLaterFlowRecord(DoctorSignApprovalFlowRecord laterFlowRecord) {
		this.laterFlowRecord = laterFlowRecord;
	}
}
