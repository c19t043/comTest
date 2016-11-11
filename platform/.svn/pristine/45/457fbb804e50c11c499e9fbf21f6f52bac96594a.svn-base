package com.java.operationmanage.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.archivesinfo.vo.UserType;
import com.java.familydoctor.servicepackage.service.FdServicePackageService;
import com.java.operationmanage.common.BooleanMsg;
import com.java.operationmanage.common.CBSMConstant;
import com.java.operationmanage.service.OperationmanageService;
import com.java.operationmanage.vo.Customer;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.operationmanage.vo.OpenClinicInfo;
import com.java.operationmanage.vo.OperaBaseSchedule;
import com.java.operationmanage.vo.OperaBusinessType;
import com.java.operationmanage.vo.OperaDoctorSchedule;
import com.java.operationmanage.vo.OperaWorkerSchedule;
import com.java.platform.core.Action;
import com.java.platform.user.vo.User;
import com.java.util.EncryptUtil;
import com.java.util.JsonUtil;

@SuppressWarnings("serial")
public class OperationmanageAction extends Action  {
	private OperationmanageService operationmanageService;
	
	private OpenClinicInfo openClinicInfo;
	/**
	 * 医生
	 */
	private DoctorInfo doctorInfo;
	/**
	 * 上午医生助手列表
	 */
	private User amDoctorHelperUser;
	/**
	 * 上午推广人员列表
	 */
	private User amExtensionUser;
	/**
	 * 下午医生助手列表
	 */
	private User pmDoctorHelperUser;
	/**
	 * 下午推广人员列表
	 */
	private User pmExtensionUser;
	/**
	 * 客户信息对象
	 */
	private Customer customer;
	/**
	 * 医院列表
	 */
	private List<HospitalBasicInfo> hospitalBasicInfoList = new ArrayList<HospitalBasicInfo>();
	/**
	 * 医院对象
	 */
	private HospitalBasicInfo hospitalBasicInfo;
	
	private String uploadDir = "admin/images/familydoctor";// 保存上传文件的目录
	
	private FdServicePackageService fdServicePackageService;
	
	/**
	 * 社区排班基本信息
	 */
	private OperaBaseSchedule operaBaseSchedule;
	private List<OperaDoctorSchedule> operaDoctorSchedule_list;
	private OperaDoctorSchedule operaDoctorSchedule;
	private List<OperaWorkerSchedule> operaWorkerSchedule_list;
	private OperaWorkerSchedule operaWorkerSchedule;
	private List<OperaBusinessType> operaBusinessType_list;
	private OperaBusinessType operaBusinessType;
	private List<UserType> userTypes_list;
	/**
	 * 状态标识:详情detail,编辑edit
	 */
	private String statusFlag;
	private String msg;
	
	public String batchPublishSchedule(){
		String publishIDs = operaBaseSchedule.getPublishIDs();
		String[] publishIDArr = (publishIDs+",").split(",");
		int count = 0;
		for (String publishID : publishIDArr) {
			OperaBaseSchedule obs = operationmanageService.get(Long.parseLong(publishID), OperaBaseSchedule.class);
			if(!CBSMConstant.PUBLISH_STATUS_BASE_NO.equals(obs.getPublishStatus())){
				JsonUtil.writeText(getResponseValue(), "只能批量发布（未发布）的排班");
				return NONE;
			}
		}
		for (String publishID : publishIDArr) {
			
			List<OperaDoctorSchedule> operaDoctorSchedules = operationmanageService.getOperaDoctorSchedules(Long.parseLong(publishID));
			
			if(operaDoctorSchedules.isEmpty()) count++;
			
			for (OperaDoctorSchedule operaDoctorSchedule : operaDoctorSchedules) {
				BooleanMsg booleanMsg = operationmanageService.savePublishDoctorSchedule(operaDoctorSchedule.getId());
				
				if(booleanMsg.isTrue()) msg = "发布成功";
				else{
					count++;
				}
			}
		}
		if(count!=0){
			msg="有"+count+"条记录发布失败";
		}
		JsonUtil.writeText(getResponseValue(), msg);
		return NONE;
	}
	
	public String publishSchedule(){
		if(operaDoctorSchedule!=null) {
			BooleanMsg booleanMsg = operationmanageService.savePublishDoctorSchedule(operaDoctorSchedule.getId());
			if(booleanMsg.isTrue()){
				msg = "发布成功";
			}else{
				String errorMsg = booleanMsg.getMsg();
				if(JsonUtil.isNotEmpty(errorMsg)){
					msg = errorMsg;
				}else{
					msg = "发布失败";
				}
			}
			JsonUtil.writeText(getResponseValue(), msg);
		}
		return NONE;
	}
	
	/**
	 * 查询排班基本信息列表
	 */
	public String queryBaseSchedule(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		List<OperaBaseSchedule> list = operationmanageService.getOperaBaseSchedules(psm, operaBaseSchedule);
		operaBusinessType_list = operationmanageService.getOperaBusinessTypes();
		OperaBusinessType e1 = new OperaBusinessType();
		e1.setName("儿科");
		operaBusinessType_list.add(e1);
		OperaBusinessType e2 = new OperaBusinessType();
		e2.setName("儿保");
		operaBusinessType_list.add(e2);
		this.putToRequest("businessTypeList", operaBusinessType_list);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 跳转到排班基础信息添加or编辑页面
	 */
	public String toAddWithBaseSchedule(){
		if(operaBaseSchedule!=null){
			Long id = operaBaseSchedule.getId();
			if(id!=null){
				operaBaseSchedule = operationmanageService.get(id, OperaBaseSchedule.class);
			}
		}
		return SUCCESS;
	}
	/**
	 * 保存or更新排班基本信息
	 */
	public String saveOrUpdateBaseSchedule(){
		Long id = operaBaseSchedule.getId();
		BooleanMsg booleanMsg = isExistOfBaseSchedule(operaBaseSchedule);
		if(!booleanMsg.isTrue()) {
			msg = booleanMsg.getMsg();
			return SUCCESS;
		}
		String orgIDs =  operaBaseSchedule.getOrgIDs();
		if(JsonUtil.isNotEmpty(orgIDs)){
			String [] orgArr = orgIDs.split(",");
			for (String orgID : orgArr) {
				OperaBaseSchedule obs = new  OperaBaseSchedule();
				BeanUtils.copyProperties(operaBaseSchedule, obs);
				hospitalBasicInfo = operationmanageService.get(Long.parseLong(orgID), HospitalBasicInfo.class);
				obs.setHospitalBasicInfo(hospitalBasicInfo);
				
				operationmanageService.saveOrUpdateOperaBaseSchedule(obs);
				if(id!=null){
					break;
				}
			}
		}
		return redirectActionResult("queryOperaBaseSchedule");
	}
	private BooleanMsg isExistOfBaseSchedule(OperaBaseSchedule operaBaseSchedule){
		Long id = operaBaseSchedule.getId();
		BooleanMsg booleanMsg = new BooleanMsg();
		if(id==null){
			booleanMsg = operationmanageService.isExistOfBaseSchedule(operaBaseSchedule);
		}else{
			OperaBaseSchedule obs = operationmanageService.get(id, OperaBaseSchedule.class);
			if(!obs.equals(operaBaseSchedule)){
				booleanMsg = operationmanageService.isExistOfBaseSchedule(operaBaseSchedule);
			}
		}
		return booleanMsg;
	}
	/**
	 * 跳转到社区排班明细页面
	 * @return
	 */
	public String toCBMDetailPage(){
		Long baseID = operaBaseSchedule.getId();
		
		operaBaseSchedule = operationmanageService.get(baseID, OperaBaseSchedule.class);
		operaDoctorSchedule_list = operationmanageService.getOperaDoctorSchedules(baseID);
		operaWorkerSchedule_list = operationmanageService.getOperaWorkerSchedules(baseID);
		
		//组装面向用户类型
		for (OperaDoctorSchedule operaDoctorSchedule : operaDoctorSchedule_list) {
			String userType = operaDoctorSchedule.getUserType();
			StringBuilder userTypeIDs = new StringBuilder();
			String[] userTypeArr = (userType+",").split(",");
			for (String userType_str : userTypeArr) {
				long userTypeID = Long.parseLong(userType_str);
				UserType ut = operationmanageService.get(userTypeID, UserType.class);
				userTypeIDs.append(ut.getTypeName()).append(",");
			}
			operaDoctorSchedule.setUserTypes(userTypeIDs.toString());
		}
		
		return SUCCESS;
	}
	
	/**
	 * 查询排班医生信息
	 * @return
	 */
	public String queryDoctorSchedule(){
		Long baseID = operaBaseSchedule.getId();
		operaBaseSchedule = operationmanageService.get(baseID, OperaBaseSchedule.class);
		operaBusinessType_list = operationmanageService.getOperaBusinessTypes();
		userTypes_list = operationmanageService.getUserTypes();
		
		if(operaDoctorSchedule!=null){
			Long id = operaDoctorSchedule.getId();
			if(id!=null) operaDoctorSchedule = operationmanageService.get(id, OperaDoctorSchedule.class);
		}
		return SUCCESS;
	}
	/**
	 * 保存or更新排班医生信息
	 */
	public String saveOrUpdateDoctorSchedule(){
		operaDoctorSchedule.setOperaBaseSchedule(operaBaseSchedule);
		String[] userTypeArr = operaDoctorSchedule.getUserTypeArr();
		for (String userType : userTypeArr) {
			operaDoctorSchedule.setUserType(
					(JsonUtil.isNotEmpty(operaDoctorSchedule.getUserType())?operaDoctorSchedule.getUserType()+",":"")+userType);
		}
		operationmanageService.saveOrUpdateOperaDoctorSchedule(operaDoctorSchedule);
		//页面状态,保存成功后继续跳转到添加页面,当状态为statusFlag = "reload",页面初始化调用关闭弹出窗口代码,关闭窗口
		statusFlag = "reload";
		return SUCCESS;
	}
	/**
	 * 查询排班工作人员信息
	 * @return
	 */
	public String queryWorkerSchedule(){
		Long baseID = operaBaseSchedule.getId();
		operaBusinessType_list = operationmanageService.getOperaBusinessTypes();
		operaBaseSchedule = operationmanageService.get(baseID, OperaBaseSchedule.class);
		if(operaWorkerSchedule!=null){
			Long id = operaWorkerSchedule.getId();
			if(id!=null) operaWorkerSchedule = operationmanageService.get(id, OperaWorkerSchedule.class);
		}
		return SUCCESS;
	}
	/**
	 * 保存or更新排班工作人员信息
	 */
	public String saveOrUpdateWorkerSchedule(){
		operaWorkerSchedule.setOperaBaseSchedule(operaBaseSchedule);
		String userId = operaWorkerSchedule.getUser().getUserId();
		if(JsonUtil.isNotEmpty(userId)){
			String[] userIDArr = (userId+",").split(",");
			for (String userID : userIDArr) {
				if(StringUtils.isBlank(userID)) continue;
				OperaWorkerSchedule ws = new OperaWorkerSchedule();
				BeanUtils.copyProperties(operaWorkerSchedule, ws);
				ws.getUser().setUserId(userID);
				operationmanageService.saveOrUpdateOperaWorkerSchedule(ws);
			}
		}
		//页面状态,保存成功后继续跳转到添加页面,当状态为statusFlag = "reload",页面初始化调用关闭弹出窗口代码,关闭窗口
		statusFlag = "reload";
		return SUCCESS;
	}
	/**
	 * 查询排班工作人员信息
	 * @return
	 */
	public String queryBusinessType(){
		operaBusinessType_list = operationmanageService.getOperaBusinessTypes();
		return SUCCESS;
	}
	/**
	 * 保存or更新业务类型
	 */
	public String saveOrUpdateBusinessType(){
		operaBusinessType = operationmanageService.saveOrUpdateOperaBusinessType(operaBusinessType);
		return SUCCESS;
	}
	/**
	 * 得到医院信息列表
	 * @return
	 */
	public String queryHospitalInfoList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		List<HospitalBasicInfo> list = 
			this.fdServicePackageService.getHospitalInfoList(psm, hospitalBasicInfo);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * excel导出
	 * @return
	 * @throws Exception
	 */
	public InputStream getInputStream() throws Exception {
		String inputPath = operationmanageService.exportDoctorSchedule2Excel(operaBaseSchedule);
		int index = inputPath.lastIndexOf(File.separator)==-1?inputPath.lastIndexOf("/"):inputPath.lastIndexOf(File.separator);
		operaBaseSchedule.setFileName(inputPath.substring(index+1));
		return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	//-----------------------------------------------------------------------
	
	/***
	 * 开通的业务信息列表，带分页的信息
	 * @param psm
	 * @param openClinicInfo
	 * @return
	 */
	public String getOpenClinicInfoPageList() {
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		List<OpenClinicInfo> list = operationmanageService.getOpenClinicInfoPageList(psm, openClinicInfo);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	
	/***
	 * 客户信息列表，带分页的信息
	 * @return
	 */
	public String getCustomerPageList() {
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		List<Customer> list = operationmanageService.getCustomerPageList(psm, customer);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	
	/**
	 * 新增开通的业务
	 * @return
	 */
	public String openClinicInfoAdd() {
		return SUCCESS;
	}
	
	/**
	 * 新增客户信息
	 * @return
	 */
	public String customerAdd() {
		hospitalBasicInfoList = operationmanageService.getHospitalBasicInfoList(hospitalBasicInfo);
		return SUCCESS;
	}
	
	/**
	 * 选择组件主页（单个医生）
	 * @return
	 */
	public String selectSingleDoctorMain() {
		return SUCCESS;
	}
	
	/**
	 * 选择组件列表（单个医生）
	 * @return
	 */
	public String selectSingleDoctorList() {
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(), tableId);
		List<DoctorInfo> list = operationmanageService.getDoctorInfoList(psm, doctorInfo);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	
	/**
	 * 保存新增开通的业务
	 * @return
	 */
	public String addingOpenClinicInfo() {
		String tempDir = "";
		if (openClinicInfo.getId() != null) {
			tempDir = openClinicInfo.getImagePath();
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			String current = df.format(new Date());
			String bannerName = "fdpac" + current + ".jpg";
			tempDir = uploadDir + "/" + bannerName;
			openClinicInfo.setImagePath(tempDir);
		}
		if (openClinicInfo.getImgBase64() != null
				&& !"".equals(openClinicInfo.getImgBase64().trim())) {
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
				EncryptUtil.uploadImage(openClinicInfo.getImgBase64(), dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		addActionMessage(operationmanageService.saveOpenClinicInfo(openClinicInfo, doctorInfo, amDoctorHelperUser, amExtensionUser, pmDoctorHelperUser, pmExtensionUser));
		return redirectActionResult("list");
	}
	
	/**
	 * 保存客户信息
	 * @return
	 */
	public String addingCustomer() {
		addActionMessage(operationmanageService.saveCustomer(customer));
		return redirectActionResult("list");
	}
	
	/**
	 * 进入更新客户界面
	 * @return
	 */
	public String updateCustomer() {
		hospitalBasicInfoList = operationmanageService.getHospitalBasicInfoList(hospitalBasicInfo);
		customer = operationmanageService.get(customer.getId(), Customer.class);
		return SUCCESS;
	}
	
	/**
	 * 保存更新的客户信息
	 * @return
	 */
	public String updateingCustomer() {
		addActionMessage(operationmanageService.updateCustomer(customer));
		return redirectActionResult("list");
	}
	
	/**
	 * 查询开通业务详细信息
	 * @return
	 */
	public String openClinicInfoDetail() {
		openClinicInfo = operationmanageService.get(openClinicInfo.getId(), OpenClinicInfo.class);
		
		if (openClinicInfo.getDoctorSet()!=null && openClinicInfo.getDoctorSet().size() > 0) {
			Iterator<DoctorInfo> itDoctorInfo = openClinicInfo.getDoctorSet().iterator();
			StringBuilder doctorInfoIds = new StringBuilder();
			StringBuilder doctorInfoNames = new StringBuilder();
			while (itDoctorInfo.hasNext()) {
				DoctorInfo doctorInfo = (DoctorInfo) itDoctorInfo.next();
				doctorInfoIds.append(doctorInfo.getId());
				doctorInfoNames.append(doctorInfo.getDoctorName());
			}
			if (doctorInfo == null) {
				doctorInfo = new DoctorInfo();
			}
			doctorInfo.setId(Long.valueOf(doctorInfoIds.toString()));
			doctorInfo.setDoctorName(doctorInfoNames.toString());
		}
		if (openClinicInfo.getAmDoctorHelperSet()!=null && openClinicInfo.getAmDoctorHelperSet().size() > 0) {
			Iterator<User> itUser = openClinicInfo.getAmDoctorHelperSet().iterator();
			StringBuilder userIds = new StringBuilder();
			StringBuilder userNames = new StringBuilder();
			while (itUser.hasNext()) {
				User user = (User) itUser.next();
				userIds.append(user.getUserId()).append(",");
				userNames.append(user.getUserInfo().getName()).append(",");
			}
			if (amDoctorHelperUser == null) {
				amDoctorHelperUser = new User();
			}
			amDoctorHelperUser.setUserId(userIds.toString());
			amDoctorHelperUser.setUserName(userNames.toString());
		}
		if (openClinicInfo.getPmDoctorHelperSet()!=null && openClinicInfo.getPmDoctorHelperSet().size() > 0) {
			Iterator<User> itUser = openClinicInfo.getPmDoctorHelperSet().iterator();
			StringBuilder userIds = new StringBuilder();
			StringBuilder userNames = new StringBuilder();
			while (itUser.hasNext()) {
				User user = (User) itUser.next();
				userIds.append(user.getUserId()).append(",");
				userNames.append(user.getUserInfo().getName()).append(",");
			}
			if (pmDoctorHelperUser == null) {
				pmDoctorHelperUser = new User();
			}
			pmDoctorHelperUser.setUserId(userIds.toString());
			pmDoctorHelperUser.setUserName(userNames.toString());
		}
		if (openClinicInfo.getAmExtensionSet()!=null && openClinicInfo.getAmExtensionSet().size() > 0) {
			Iterator<User> itUser = openClinicInfo.getAmExtensionSet().iterator();
			StringBuilder userIds = new StringBuilder();
			StringBuilder userNames = new StringBuilder();
			while (itUser.hasNext()) {
				User user = (User) itUser.next();
				userIds.append(user.getUserId()).append(",");
				userNames.append(user.getUserInfo().getName()).append(",");
			}
			if (amExtensionUser == null) {
				amExtensionUser = new User();
			}
			amExtensionUser.setUserId(userIds.toString());
			amExtensionUser.setUserName(userNames.toString());
		}
		if (openClinicInfo.getPmExtensionSet()!=null && openClinicInfo.getPmExtensionSet().size() > 0) {
			Iterator<User> itUser = openClinicInfo.getPmExtensionSet().iterator();
			StringBuilder userIds = new StringBuilder();
			StringBuilder userNames = new StringBuilder();
			while (itUser.hasNext()) {
				User user = (User) itUser.next();
				userIds.append(user.getUserId()).append(",");
				userNames.append(user.getUserInfo().getName()).append(",");
			}
			if (pmExtensionUser == null) {
				pmExtensionUser = new User();
			}
			pmExtensionUser.setUserId(userIds.toString());
			pmExtensionUser.setUserName(userNames.toString());
		}
		
		return SUCCESS;
	}
	
	/**
	 * 编辑开通的业务
	 * @return
	 */
	public String openClinicInfoEdit() {
		openClinicInfo = operationmanageService.get(openClinicInfo.getId(), OpenClinicInfo.class);
		
		if (openClinicInfo.getDoctorSet()!=null && openClinicInfo.getDoctorSet().size() > 0) {
			Iterator<DoctorInfo> itDoctorInfo = openClinicInfo.getDoctorSet().iterator();
			StringBuilder doctorInfoIds = new StringBuilder();
			StringBuilder doctorInfoNames = new StringBuilder();
			while (itDoctorInfo.hasNext()) {
				DoctorInfo doctorInfo = (DoctorInfo) itDoctorInfo.next();
				doctorInfoIds.append(doctorInfo.getId());
				doctorInfoNames.append(doctorInfo.getDoctorName());
			}
			if (doctorInfo == null) {
				doctorInfo = new DoctorInfo();
			}
			doctorInfo.setId(Long.valueOf(doctorInfoIds.toString()));
			doctorInfo.setDoctorName(doctorInfoNames.toString());
		}
		if (openClinicInfo.getAmDoctorHelperSet()!=null && openClinicInfo.getAmDoctorHelperSet().size() > 0) {
			Iterator<User> itUser = openClinicInfo.getAmDoctorHelperSet().iterator();
			StringBuilder userIds = new StringBuilder();
			StringBuilder userNames = new StringBuilder();
			while (itUser.hasNext()) {
				User user = (User) itUser.next();
				userIds.append(user.getUserId()).append(",");
				userNames.append(user.getUserInfo().getName()).append(",");
			}
			if (amDoctorHelperUser == null) {
				amDoctorHelperUser = new User();
			}
			amDoctorHelperUser.setUserId(userIds.toString());
			amDoctorHelperUser.setUserName(userNames.toString());
		}
		if (openClinicInfo.getPmDoctorHelperSet()!=null && openClinicInfo.getPmDoctorHelperSet().size() > 0) {
			Iterator<User> itUser = openClinicInfo.getPmDoctorHelperSet().iterator();
			StringBuilder userIds = new StringBuilder();
			StringBuilder userNames = new StringBuilder();
			while (itUser.hasNext()) {
				User user = (User) itUser.next();
				userIds.append(user.getUserId()).append(",");
				userNames.append(user.getUserInfo().getName()).append(",");
			}
			if (pmDoctorHelperUser == null) {
				pmDoctorHelperUser = new User();
			}
			pmDoctorHelperUser.setUserId(userIds.toString());
			pmDoctorHelperUser.setUserName(userNames.toString());
		}
		if (openClinicInfo.getAmExtensionSet()!=null && openClinicInfo.getAmExtensionSet().size() > 0) {
			Iterator<User> itUser = openClinicInfo.getAmExtensionSet().iterator();
			StringBuilder userIds = new StringBuilder();
			StringBuilder userNames = new StringBuilder();
			while (itUser.hasNext()) {
				User user = (User) itUser.next();
				userIds.append(user.getUserId()).append(",");
				userNames.append(user.getUserInfo().getName()).append(",");
			}
			if (amExtensionUser == null) {
				amExtensionUser = new User();
			}
			amExtensionUser.setUserId(userIds.toString());
			amExtensionUser.setUserName(userNames.toString());
		}
		if (openClinicInfo.getPmExtensionSet()!=null && openClinicInfo.getPmExtensionSet().size() > 0) {
			Iterator<User> itUser = openClinicInfo.getPmExtensionSet().iterator();
			StringBuilder userIds = new StringBuilder();
			StringBuilder userNames = new StringBuilder();
			while (itUser.hasNext()) {
				User user = (User) itUser.next();
				userIds.append(user.getUserId()).append(",");
				userNames.append(user.getUserInfo().getName()).append(",");
			}
			if (pmExtensionUser == null) {
				pmExtensionUser = new User();
			}
			pmExtensionUser.setUserId(userIds.toString());
			pmExtensionUser.setUserName(userNames.toString());
		}
		
		return SUCCESS;
	}
	
	/**
	 * 编辑保存新增开通的业务
	 * @return
	 */
	public String updateingOpenClinicInfo() {
		addActionMessage(operationmanageService.updateOpenClinicInfo(openClinicInfo, doctorInfo, amDoctorHelperUser, amExtensionUser, pmDoctorHelperUser, pmExtensionUser));
		return redirectActionResult("list");
	}
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:getMonthSpace方法是用于计算两个日期之间的月份差值的方法
	 * @data: 2015年9月29日下午3:06:37
	 * @param date1 
	 * @param date2
	 * @return 两个日期之间相差的月份数
	 * @throws ParseException
	 */
    public static int getMonthSpace(String date1, String date2) throws ParseException {
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));
        result=(c1.get(c1.YEAR)-c2.get(c2.YEAR))*12+((c1.get(c1.MONTH)+1)-(c2.get(c2.MONTH)+1));
        return result == 0 ? 1 : Math.abs(result);
    }

	public void setOperationmanageService(
			OperationmanageService operationmanageService) {
		this.operationmanageService = operationmanageService;
	}

	public OpenClinicInfo getOpenClinicInfo() {
		return openClinicInfo;
	}

	public void setOpenClinicInfo(OpenClinicInfo openClinicInfo) {
		this.openClinicInfo = openClinicInfo;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public User getAmDoctorHelperUser() {
		return amDoctorHelperUser;
	}

	public void setAmDoctorHelperUser(User amDoctorHelperUser) {
		this.amDoctorHelperUser = amDoctorHelperUser;
	}

	public User getAmExtensionUser() {
		return amExtensionUser;
	}

	public void setAmExtensionUser(User amExtensionUser) {
		this.amExtensionUser = amExtensionUser;
	}

	public User getPmDoctorHelperUser() {
		return pmDoctorHelperUser;
	}

	public void setPmDoctorHelperUser(User pmDoctorHelperUser) {
		this.pmDoctorHelperUser = pmDoctorHelperUser;
	}

	public User getPmExtensionUser() {
		return pmExtensionUser;
	}

	public void setPmExtensionUser(User pmExtensionUser) {
		this.pmExtensionUser = pmExtensionUser;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<HospitalBasicInfo> getHospitalBasicInfoList() {
		return hospitalBasicInfoList;
	}

	public void setHospitalBasicInfoList(
			List<HospitalBasicInfo> hospitalBasicInfoList) {
		this.hospitalBasicInfoList = hospitalBasicInfoList;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	public OperaBaseSchedule getOperaBaseSchedule() {
		return operaBaseSchedule;
	}
	public void setOperaBaseSchedule(OperaBaseSchedule operaBaseSchedule) {
		this.operaBaseSchedule = operaBaseSchedule;
	}
	public List<OperaDoctorSchedule> getOperaDoctorSchedule_list() {
		return operaDoctorSchedule_list;
	}
	public void setOperaDoctorSchedule_list(
			List<OperaDoctorSchedule> operaDoctorSchedule_list) {
		this.operaDoctorSchedule_list = operaDoctorSchedule_list;
	}
	public OperaDoctorSchedule getOperaDoctorSchedule() {
		return operaDoctorSchedule;
	}
	public void setOperaDoctorSchedule(OperaDoctorSchedule operaDoctorSchedule) {
		this.operaDoctorSchedule = operaDoctorSchedule;
	}
	public List<OperaWorkerSchedule> getOperaWorkerSchedule_list() {
		return operaWorkerSchedule_list;
	}
	public void setOperaWorkerSchedule_list(
			List<OperaWorkerSchedule> operaWorkerSchedule_list) {
		this.operaWorkerSchedule_list = operaWorkerSchedule_list;
	}
	public OperaWorkerSchedule getOperaWorkerSchedule() {
		return operaWorkerSchedule;
	}
	public void setOperaWorkerSchedule(OperaWorkerSchedule operaWorkerSchedule) {
		this.operaWorkerSchedule = operaWorkerSchedule;
	}
	public List<OperaBusinessType> getOperaBusinessType_list() {
		return operaBusinessType_list;
	}
	public void setOperaBusinessType_list(
			List<OperaBusinessType> operaBusinessType_list) {
		this.operaBusinessType_list = operaBusinessType_list;
	}
	public OperaBusinessType getOperaBusinessType() {
		return operaBusinessType;
	}
	public void setOperaBusinessType(OperaBusinessType operaBusinessType) {
		this.operaBusinessType = operaBusinessType;
	}
	public List<UserType> getUserTypes_list() {
		return userTypes_list;
	}
	public void setUserTypes_list(List<UserType> userTypes_list) {
		this.userTypes_list = userTypes_list;
	}
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	public void setFdServicePackageService(
			FdServicePackageService fdServicePackageService) {
		this.fdServicePackageService = fdServicePackageService;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
