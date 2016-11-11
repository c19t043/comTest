package com.kybaby.newbussiness.doctorclinic.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Position;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorClinicTimeSegment;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgClinicdate;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMoreOrgTimeSetting;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePracticeOrgInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBanner;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgBusinessRelation;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;
import com.kybaby.util.EncryptUtil;

public class DoctorClinicAction extends NewBaseAction {
	Logger logger  =  Logger.getLogger(DoctorClinicAction.class);
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "操作成功";
	/**
	 * 医生信息
	 */
	private DoctorInfo doctorInfo;
	/**
	 * 医生信息
	 */
	private List<DoctorInfo> doctorInfoList = new ArrayList<DoctorInfo>();
	/**
	 * 多点执业机构
	 */
	private DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo;
	/**
	 * 多点执业机构集合
	 */
	private List<DoctorMorePracticeOrgInfo> morePracticeOrgList = new ArrayList<DoctorMorePracticeOrgInfo>();
	/**
	 * 多点执业机构日期信息
	 */
	private DoctorMoreOrgClinicdate doctorMoreOrgClinicdate;
	/**
	 * 多点执业机构日期配置集合
	 */
	private List<DoctorMoreOrgClinicdate> moreOrgClinicdateList = new ArrayList<DoctorMoreOrgClinicdate>();
	/**
	 * 多点执业机构时间配置信息集合
	 */
	private List<DoctorMoreOrgTimeSetting> morePracticeOrgTimeList = new ArrayList<DoctorMoreOrgTimeSetting>();
	/**
	 * 多点执业机构时间配置信息
	 */
	private DoctorMoreOrgTimeSetting doctorMoreOrgTimeSetting;
	/**
	 * 多点执业服务标签
	 */
	private List<DoctorServiceType> doctorServiceTypeList = new ArrayList<DoctorServiceType>();
	/**
	 * 多点执业分成比例
	 */
	private List<HospitalPosition> hospitalPositionList = new ArrayList<HospitalPosition>();
	/**
	 * 职称列表
	 */
	private List<Position> positionList = new ArrayList<Position>();
	/**
	 * 医院信息列表
	 */
	private List<HospitalBasicInfo> hospitalBasicInfoList = new ArrayList<HospitalBasicInfo>();
	/**
	 * 医院banner信息列表
	 */
	private List<HospitalBanner> hospitalBannerList = new ArrayList<HospitalBanner>();
	/**
	 * 医院信息
	 */
	private HospitalBasicInfo hospitalBasicInfo;
	/**
	 * 分成比例
	 */
	private HospitalPosition hospitalPosition;
	/**
	 * 医生多点设置信息列表
	 */
	private DoctorMorePractice doctorMorePractice;
	/**
	 * 分成比例
	 */
	private List<DoctorMorePractice> doctorMorePracticeList = new ArrayList<DoctorMorePractice>();
	/**
	 * 门诊订单列表
	 */
	private List<OrderInfoClinic> orderInfoClinicList = new ArrayList<OrderInfoClinic>();
	/**
	 * 门诊订单
	 */
	private OrderInfoClinic orderInfoClinic;
	/**
	 * 时间间隔
	 */
	private Integer intervals;
	/**
	 * 多点设置时间分隔集合
	 */
	private List<DoctorClinicTimeSegment> doctorClinicTimeSegmentList = new ArrayList<DoctorClinicTimeSegment>();

	private String uploadDir = "admin/images/hospital";// 保存上传文件的目录

	public String execute() throws Exception {
		System.out.println("============DoctorClinic MG manage execute==============");
		/**
		 * 机构列表
		 */
		if (action.equals("getAllMoreOrgList")) {
			this.morePracticeOrgList = this.doctorClinicService
					.getDoctorMorePracticeOrgInfoList( doctorMorePracticeOrgInfo);
		}
		/**
		 * 保存或更新机构信息
		 */
		else if (action.equals("saveOrUpdateMoreOrg")) {
			Long orgId = this.doctorClinicService
					.saveOrUpdateDoctorMorePracticeOrgInfo(doctorMorePracticeOrgInfo);
			// 保存开放日期
			this.doctorClinicService.saveOrUpdateDoctorMoreOrgClinicdateList(
					moreOrgClinicdateList, orgId);
			// 保存开放时间段
			this.doctorClinicService.saveOrUpdateDoctorMoreOrgTimeSettingList(
					morePracticeOrgTimeList, orgId);
			return "toMoreOrgList";
		}
		/**
		 * 得到机构开放日期和时间段信息集合
		 */
		else if (action.equals("getMoreOrgOpenInfo")) {
			Long orgId = doctorMorePracticeOrgInfo.getId();
			this.moreOrgClinicdateList = this.doctorClinicService
					.getOpenDateByOrgId(orgId);
			this.morePracticeOrgTimeList = this.doctorClinicService
					.getOpenTimeByOrgId(orgId);
		}
		/**
		 * 得到机构分成比例
		 */
		else if (action.equals("getHospitalPositionList")) {
			this.hospitalPositionList = this.doctorClinicService
					.getHospitalPositionList(hospitalPosition);
		}
		/**
		 * 保存或更新机构保底及比例
		 */
		else if (action.equals("saveOrUpdateHospitalPosition")) {
			this.doctorClinicService
					.saveOrUpdateHospitalPosition(hospitalPosition);
			return "toHospitalPositionList";
		}
		/**
		 * 得到需要下拉选择的列表
		 */
		else if (action.equals("getSelectList")) {
			this.positionList = this.positionBo.getAllPosition();
			this.hospitalBasicInfoList = this.doctorClinicService
					.getHospitalBasicInfoList(hospitalBasicInfo);
			this.morePracticeOrgList = this.doctorClinicService
					.getDoctorMorePracticeOrgInfoList(doctorMorePracticeOrgInfo);
		}
		/**
		 * 得到医生门诊设置信息列表
		 */
		else if (action.equals("getDoctorMorePracticeList")) {
			this.doctorMorePracticeList = this.doctorClinicService
					.getDoctorMorePracticeList(doctorMorePractice);
			if(doctorMorePracticeList != null){
				UserType ut = new UserType();
				for (DoctorMorePractice dmp : doctorMorePracticeList) {
					String userTypeNames = "";
					if(StringUtils.isNotEmpty(dmp.getUserTypeIds())){
						ut.setIds(dmp.getUserTypeIds());
						List<UserType> utList = this.doctorMoneyRecordService.getUserTypeList(ut);
						if(utList != null){
							for(UserType userType : utList){
								userTypeNames += userType.getTypeName() + ",";
							}
						}
					}
					dmp.setUserTypeNames(userTypeNames);
				}
			}
		}
		/**
		 * 得到所有可用医生
		 */
		else if (action.equals("getAllDoctor")) {
			this.doctorInfoList = this.doctorClinicService
					.getAllDoctor(doctorInfo);
		}
		/**
		 * 得到医院信息列表
		 */
		else if (action.equals("getHospitalList")) {
			this.hospitalBasicInfoList = this.doctorClinicService
					.getHospitalBasicInfoList(hospitalBasicInfo);
			if(hospitalBasicInfoList != null){
				for(HospitalBasicInfo hb : hospitalBasicInfoList){
					String open_business_id = "";
					String open_business_name = "";
					//得到机构的开放业务
					List<OrgBusinessRelation> orgBusinessRelationList = 
							this.organBusinessService.getOrgBusinessRelationList(hb,null);
					if(orgBusinessRelationList != null){
						for(OrgBusinessRelation obr : orgBusinessRelationList){
							open_business_id += obr.getOrgOpenBusiness().getId() + "::";
							open_business_name += obr.getOrgOpenBusiness().getBusinessName() + "::";
						}
					}
					hb.setOpen_business_id(open_business_id.equals("")?"":
						open_business_id.substring(0, open_business_id.lastIndexOf("::")));
					hb.setOpen_business_name(open_business_name.equals("")?"":
						open_business_name.substring(0, open_business_name.lastIndexOf("::")));
				}
			}
		}
		/**
		 * 得到医院banner
		 */
		else if (action.equals("getHospitalBannerList")) {
			this.hospitalBannerList = this.doctorClinicService
					.getHospitalBannerList(hospitalBasicInfo);
		}
		/**
		 * 保存或更新医院信息
		 */
		else if (action.equals("saveOrUpdateHospitalBasicInfo")) {
			Long hosId = this.doctorClinicService
					.saveOrUpdateHospitalBasicInfo(hospitalBasicInfo);
			// 保存banner
			HospitalBasicInfo old = new HospitalBasicInfo();
			old.setId(hosId);
			for (HospitalBanner banner : hospitalBannerList) {
				banner.setHospitalBasicInfo(old);
				String tempDir = "";

				if (banner.getId() != null) {
					tempDir = banner.getImgPath();
				} else {
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
					String current = df.format(new Date());
					String bannerName = "hospital" + current + ".jpg";
					tempDir = uploadDir + "/" + bannerName;
					banner.setImgPath(tempDir);
				}
				if (banner.getImgBase64() != null
						&& !"".equals(banner.getImgBase64().trim())) {
					String directory = ServletActionContext.getServletContext()
							.getRealPath(uploadDir + "/");
					File cacheDir = new File(directory);
					// 如果文件夹不存在则创建
					if (!cacheDir.exists() && !cacheDir.isDirectory()) {
						System.out.println("//不存在");
						cacheDir.mkdir();
					} else {
						System.out.println("//目录存在");
					}
					// 上传图片
					String dir = ServletActionContext.getServletContext()
							.getRealPath(tempDir);
					EncryptUtil.uploadImage(banner.getImgBase64(), dir);
					Thread.sleep(1500);
				}
				this.doctorClinicService.saveOrUpdateHospitalBanner(banner);
			}
			//保存或更新开放服务
			if(hospitalBasicInfo.getOpen_business_id() != null && !"".equals(hospitalBasicInfo.getOpen_business_id())){
				String [] openBusinessId = hospitalBasicInfo.getOpen_business_id().split("::");
				organBusinessService.saveOrupdateOrgBusinessRelation(Arrays.asList(openBusinessId),hosId);
			}
			return "toHospitalInfoList";
		}
		/**
		 * 得到门诊订单信息列表
		 */
		else if (action.equals("getOrderInfoClinicList")) {
			this.orderInfoClinicList = this.doctorClinicService
					.getOrderInfoClinicList(orderInfoClinic);
		}
		/**
		 * 保存或更新订单信息
		 */
		else if (action.equals("saveOrUpdateOrderInfoClinic")) {
			if (doctorInfo != null && doctorInfo.getId() != null) {
				DoctorInfo oldDoc = this.doctorInfoBo
						.getDoctorInfoById(doctorInfo.getId());
				orderInfoClinic.setDoctorInfo(oldDoc);
			}
			this.doctorClinicService
					.saveOrUpdateOrderInfoClinic(orderInfoClinic);
			return "toOrderInfoClinicList";
		}
		/**
		 * 得到可约机构和时间
		 */
		else if (action.equals("getCanClinicOrgAndDateList")) {
			List<DoctorMoreOrgClinicdate> list = this.doctorClinicService
					.getDoctorMoreOrgClinicdateList();
			if (list != null) {
				for (DoctorMoreOrgClinicdate dmoc : list) {
//					// 将已被占的去掉
//					DoctorMorePractice dmp = new DoctorMorePractice();
//					// 已经约了的时间集合
//					dmp.setClinicOrgType(DoctorMorePractice.CLINIC_ORG_TYPE_1);
//					dmp.setClinicDate(dmoc.getCanClinicDate());
//					dmp.setDoctorMorePracticeOrgInfo(dmoc
//							.getDoctorMorePracticeOrgInfo());
//					List<DoctorMorePractice> dmpList = this.doctorClinicService
//							.getDoctorMorePracticeList(dmp);
//					if (dmpList == null) {
//						this.moreOrgClinicdateList.add(dmoc);
//					}
					//过滤出今天及以后的
					String curDate = DateManage.getDateStr("yyyy-MM-dd") + " 00:00:00";
					String clinicDate = dmoc.getCanClinicDate() + " 00:00:00";
					if (curDate.equals(clinicDate) || 
							DateManage.isCompareDates(DateManage.getStrToDateTime(clinicDate), new Date())) {
						this.moreOrgClinicdateList.add(dmoc);
					}
				}
			}
		}
		/**
		 * 保存医生多点执业设置信息（此方法可控制分配多点医生）
		 */
		else if (action.equals("saveOrUpdateDoctorMorePractice")) {
			DoctorMoreOrgClinicdate dmocd = this.doctorClinicService
					.getDoctorMoreOrgClinicdateById(doctorMoreOrgClinicdate
							.getId());
			// 如果为多点执业门诊，门诊地址则存储当前执业机构的名称和地址，还需要保存DoctorMorePracticeOrgInfo对象字段
			doctorMorePractice.setDoctorMorePracticeOrgInfo(dmocd
					.getDoctorMorePracticeOrgInfo());
			doctorMorePractice.setClinicOrg(dmocd
					.getDoctorMorePracticeOrgInfo().getOrgName());
			doctorMorePractice.setClinicAddress(dmocd
					.getDoctorMorePracticeOrgInfo().getOrgAddress());
			doctorMorePractice.setClinicDate(dmocd.getCanClinicDate());
			doctorMorePractice
					.setClinicOrgType(DoctorMorePractice.CLINIC_ORG_TYPE_1);
			doctorMorePractice.setOperationTime(DateManage
					.getDateStr("yyyy-MM-dd HH:mm:ss"));
			doctorMorePractice.setIsRepeat("N");
			doctorMorePractice.setOrgClinicStatus("已预约");
			doctorMorePractice.setOrgClinicTimeIds(doctorMorePractice.getOrgClinicTimeIds().replace(" ", ""));
			this.doctorClinicService.saveOrUpdateDoctorClinicSet(doctorMorePractice, intervals);
			//保存医生薪酬记录
			return "toDoctorclinicSetRecordList";
		}
		/**
		 * 根据机构id和开放时间得到机构开放日期信息
		 */
		else if(action.equals("getDoctorMoreOrgClinicdateByDateAndOrgId")){
			List<DoctorMoreOrgClinicdate> list = this.doctorClinicService.getDoctorMoreOrgClinicdateList();
			if(list != null){
				for(DoctorMoreOrgClinicdate dmoc : list){
					if(dmoc.getDoctorMorePracticeOrgInfo().getId() == doctorMoreOrgClinicdate.getDoctorMorePracticeOrgInfo().getId()
							&& dmoc.getCanClinicDate().equals(doctorMoreOrgClinicdate.getCanClinicDate())){
						this.doctorMoreOrgClinicdate = dmoc;
						break;
					}
				}
			}
		}
		/**
		 * 得到多点设置的时间分段信息
		 */
		else if(action.equals("getDoctorClinicTimeSegmentList")){
			this.doctorClinicTimeSegmentList = this.doctorClinicService.getDoctorClinicTimeSegmentList(doctorMorePractice);
		}
		/*
		 * 修改医生门诊多点工作记录
		 */
		else if(action.equals("updateDoctorMorePracticeRecord")){
			boolean updateFlag = this.doctorClinicService.updateDoctorMorePracticeRecord(doctorMorePractice);
			if(updateFlag)
				mes="true";
			else 
				mes="false";
		}
		
		return "success";
	}

	

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public DoctorMorePracticeOrgInfo getDoctorMorePracticeOrgInfo() {
		return doctorMorePracticeOrgInfo;
	}

	public void setDoctorMorePracticeOrgInfo(
			DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo) {
		this.doctorMorePracticeOrgInfo = doctorMorePracticeOrgInfo;
	}

	public List<DoctorMorePracticeOrgInfo> getMorePracticeOrgList() {
		return morePracticeOrgList;
	}

	public void setMorePracticeOrgList(
			List<DoctorMorePracticeOrgInfo> morePracticeOrgList) {
		this.morePracticeOrgList = morePracticeOrgList;
	}

	public List<DoctorMoreOrgTimeSetting> getMorePracticeOrgTimeList() {
		return morePracticeOrgTimeList;
	}

	public void setMorePracticeOrgTimeList(
			List<DoctorMoreOrgTimeSetting> morePracticeOrgTimeList) {
		this.morePracticeOrgTimeList = morePracticeOrgTimeList;
	}

	public List<DoctorServiceType> getDoctorServiceTypeList() {
		return doctorServiceTypeList;
	}

	public void setDoctorServiceTypeList(
			List<DoctorServiceType> doctorServiceTypeList) {
		this.doctorServiceTypeList = doctorServiceTypeList;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public List<DoctorMoreOrgClinicdate> getMoreOrgClinicdateList() {
		return moreOrgClinicdateList;
	}

	public void setMoreOrgClinicdateList(
			List<DoctorMoreOrgClinicdate> moreOrgClinicdateList) {
		this.moreOrgClinicdateList = moreOrgClinicdateList;
	}

	public List<HospitalPosition> getHospitalPositionList() {
		return hospitalPositionList;
	}

	public void setHospitalPositionList(
			List<HospitalPosition> hospitalPositionList) {
		this.hospitalPositionList = hospitalPositionList;
	}

	public HospitalPosition getHospitalPosition() {
		return hospitalPosition;
	}

	public void setHospitalPosition(HospitalPosition hospitalPosition) {
		this.hospitalPosition = hospitalPosition;
	}

	public List<Position> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<Position> positionList) {
		this.positionList = positionList;
	}

	public List<HospitalBasicInfo> getHospitalBasicInfoList() {
		return hospitalBasicInfoList;
	}

	public void setHospitalBasicInfoList(
			List<HospitalBasicInfo> hospitalBasicInfoList) {
		this.hospitalBasicInfoList = hospitalBasicInfoList;
	}

	public DoctorMorePractice getDoctorMorePractice() {
		return doctorMorePractice;
	}

	public void setDoctorMorePractice(DoctorMorePractice doctorMorePractice) {
		this.doctorMorePractice = doctorMorePractice;
	}

	public List<DoctorMorePractice> getDoctorMorePracticeList() {
		return doctorMorePracticeList;
	}

	public void setDoctorMorePracticeList(
			List<DoctorMorePractice> doctorMorePracticeList) {
		this.doctorMorePracticeList = doctorMorePracticeList;
	}

	public List<DoctorInfo> getDoctorInfoList() {
		return doctorInfoList;
	}

	public void setDoctorInfoList(List<DoctorInfo> doctorInfoList) {
		this.doctorInfoList = doctorInfoList;
	}

	public List<OrderInfoClinic> getOrderInfoClinicList() {
		return orderInfoClinicList;
	}

	public void setOrderInfoClinicList(List<OrderInfoClinic> orderInfoClinicList) {
		this.orderInfoClinicList = orderInfoClinicList;
	}

	public OrderInfoClinic getOrderInfoClinic() {
		return orderInfoClinic;
	}

	public void setOrderInfoClinic(OrderInfoClinic orderInfoClinic) {
		this.orderInfoClinic = orderInfoClinic;
	}

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public List<HospitalBanner> getHospitalBannerList() {
		return hospitalBannerList;
	}

	public void setHospitalBannerList(List<HospitalBanner> hospitalBannerList) {
		this.hospitalBannerList = hospitalBannerList;
	}

	public DoctorMoreOrgClinicdate getDoctorMoreOrgClinicdate() {
		return doctorMoreOrgClinicdate;
	}

	public void setDoctorMoreOrgClinicdate(
			DoctorMoreOrgClinicdate doctorMoreOrgClinicdate) {
		this.doctorMoreOrgClinicdate = doctorMoreOrgClinicdate;
	}

	public DoctorMoreOrgTimeSetting getDoctorMoreOrgTimeSetting() {
		return doctorMoreOrgTimeSetting;
	}

	public void setDoctorMoreOrgTimeSetting(
			DoctorMoreOrgTimeSetting doctorMoreOrgTimeSetting) {
		this.doctorMoreOrgTimeSetting = doctorMoreOrgTimeSetting;
	}

	public Integer getIntervals() {
		return intervals;
	}

	public void setIntervals(Integer intervals) {
		this.intervals = intervals;
	}

	public List<DoctorClinicTimeSegment> getDoctorClinicTimeSegmentList() {
		return doctorClinicTimeSegmentList;
	}

	public void setDoctorClinicTimeSegmentList(
			List<DoctorClinicTimeSegment> doctorClinicTimeSegmentList) {
		this.doctorClinicTimeSegmentList = doctorClinicTimeSegmentList;
	}
}
