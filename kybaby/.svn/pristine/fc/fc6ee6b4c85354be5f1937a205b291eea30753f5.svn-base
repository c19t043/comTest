package com.kybaby.newbussiness.familydoctor.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorClinicTimeSegment;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.doctorclinic.fo.DoctorMorePracticeFo;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceMember;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceTeams;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.HospitalBanner;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.GetDistance;
import com.opensymphony.xwork2.ActionContext;

public class FamilyDoctorManage extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 家庭医生服务包
	 */
	private FdServicePackage fdServicePackage;
	/**
	 * 团队id
	 */
	private Long fdTeamId;
	/**
	 * 医生信息
	 */
	private DoctorInfo doctorInfo;
	/**
	 * 医生列表
	 */
	private List<DoctorInfo> doctorInfoList = new ArrayList<>();
	/**
	 * 门诊开放信息
	 */
	private DoctorMorePractice doctorMorePractice;
	/**
	 * 门诊订单信息
	 */
	private OrderInfoClinic orderInfoClinic;
	/**
	 * 医生门诊及多点执业设置信息(用户端显示处理)
	 */
	private List<DoctorMorePracticeFo> doctorMorePracticeFoList = new ArrayList<DoctorMorePracticeFo>();
	/**
	 * 坐诊时间列表
	 */
	private List<DoctorClinicTimeSegment> doctorClinicTimeSegmentList = new ArrayList<DoctorClinicTimeSegment>();
	/**
	 * 医院机构列表
	 */
	private List<HospitalBasicInfo> hospitalBasicInfoList = new ArrayList<HospitalBasicInfo>();
	/**
	 * 医院机构信息
	 */
	private HospitalBasicInfo hospitalBasicInfo;
	/**
	 * 经度
	 */
	private Double lng_current;
	/**
	 * 纬度
	 */
	private Double lat_current;
	
	public String execute(){
		Long userId = (Long) ActionContext.getContext().getSession().get("userId");
		if(userId==null){
			mes="请登录";
			return "fail";
		}
		/**
		 * 家庭医生门诊
		 */
		if(action.equals("fdClinicDate")){
			List<DoctorMorePractice> doctorMorePracticeList = new ArrayList<>();
			//得到可用门诊集合
			List<DoctorMorePractice> doctorMorePracticeListOld = this.doctorClinicService.getDoctorMorePracticeList(null, null);
			//得到当前用户的家庭医生团队
			FdServicePackage oldPackage = this.familyDoctorBo.getFdServicePackageById(fdServicePackage.getId());
			this.hospitalBasicInfo = oldPackage.getHospitalBasicInfo();
			Iterator<FdServiceTeams> teamsIt = oldPackage.getFdServiceTeamsSet().iterator();  
			List<FdServiceMember> memberList = null;
			while(teamsIt.hasNext()) { 
	        	memberList = this.fdServiceItemsService.getFdServiceMemberList(teamsIt.next(),null);
	        	break;
	        }
			if(memberList != null && !memberList.isEmpty() && doctorMorePracticeListOld != null){
				//过滤出家庭医生团队成员做门诊的信息
				for(DoctorMorePractice dmp : doctorMorePracticeListOld){
					for(FdServiceMember member : memberList){
						if(dmp.getClinicOrg().equals(oldPackage.getHospitalBasicInfo().getHospitalLname()) 
								&& member.getDoctorInfo().getId().longValue() == dmp.getDoctorInfo().getId().longValue() &&
								member.getSkilNames() != null && member.getSkilNames().indexOf(ConstantManage.FD_menzhen) > -1){
							doctorMorePracticeList.add(dmp);
						}
					}
				}
				if(doctorMorePracticeList.isEmpty())
					return "fail";
				//组装门诊日期-医生关系
				Map<String,List<DoctorMorePractice>> map = new HashMap<String,List<DoctorMorePractice>>();
				for(DoctorMorePractice dmp : doctorMorePracticeList){
					map.put(dmp.getClinicDate(), null);
				}
				for (Map.Entry<String,List<DoctorMorePractice>> entry : map.entrySet()) {  
					DoctorMorePracticeFo fo = new DoctorMorePracticeFo();
					List<DoctorMorePractice> newList = new ArrayList<>();
					fo.setClinicDate(entry.getKey());
					for(DoctorMorePractice dmp : doctorMorePracticeList){
						if(dmp.getClinicDate().equals(entry.getKey())){
							newList.add(dmp);
							fo.setDoctorMorePracticeList(newList);
						}
					}
					this.doctorMorePracticeFoList.add(fo);
				}
			}
		}
		/**
		 * 得到团队包下的团队成员
		 */
		else if(action.equals("getClinicDoctorsByTeamPack")){
			//得到当前用户的家庭医生团队
			FdServiceTeams oldTeam = this.fdServiceItemsService.getFdServiceTeamsById(fdTeamId);
			if(oldTeam == null){
				return "fail";
			}
			List<FdServiceMember> memberList = this.fdServiceItemsService.getFdServiceMemberList(oldTeam,null);
			if(memberList != null && !memberList.isEmpty()){
				for(FdServiceMember member : memberList){
					if(member.getSkilNames() != null && member.getSkilNames().indexOf(ConstantManage.FD_menzhen) > -1){
						member.getDoctorInfo().setDoctorPhone("");
						this.doctorInfoList.add(member.getDoctorInfo());
					}
				}
			}
		}
		/**
		 * 得到家庭医生门诊服务时间
		 */
		else if(action.equals("getFdClinicTimeSegmentList")){
			this.doctorMorePractice = this.doctorClinicService.getDoctorMorePracticeById(doctorMorePractice.getId());
			//得到医生可被预约的时间
			this.doctorClinicTimeSegmentList = this.getCanUseClinicTime(doctorMorePractice,doctorMorePractice.getDoctorInfo());
			//有加号的需要判断加号剩余数
			OrderInfoClinic orderClinic = new OrderInfoClinic();
			orderClinic.setClinicOrgType(ConstantManage.CLINIC_ORG_TYPE_1);
			orderClinic.setIsPlus("Y");
			orderClinic.setDoctorInfo(doctorMorePractice.getDoctorInfo());
			orderClinic.setAppointmentDate(doctorMorePractice.getClinicDate());
			Long hadAddClinic = this.getCanPlusNum(orderClinic);
			Long doctorAddNum = doctorMorePractice.getIsAddClinic()==null?0L:Long.valueOf(doctorMorePractice.getIsAddClinic());
			Long canAdd = doctorAddNum-hadAddClinic;
			doctorMorePractice.setIsAddClinic(canAdd.toString());
		}
		/**
		 * 得到家庭医生服务医疗机构列表
		 */
		else if(action.equals("getFdHospitalList")){
			UserInfo user = this.userInfoBo.getUserById(userId);
			double lng1 = Double.valueOf(user.getUserLng()==null?"0":user.getUserLng());
			double lat1 = Double.valueOf(user.getUserLat()==null?"0":user.getUserLat());
			//得到有家庭医生服务的机构
			List<HospitalBasicInfo> havePackageHosList = this.familyDoctorBo.getHospitalBasicInfoListByPackage(null);
			if(havePackageHosList != null){
				Map<Long,HospitalBasicInfo> hospitalMap = new HashMap<Long,HospitalBasicInfo>();
				for(HospitalBasicInfo hospital : havePackageHosList){
					hospitalMap.put(hospital.getId(), hospital);
				}
				List<HospitalBasicInfo> newPackageHosList = new ArrayList<HospitalBasicInfo>();
				HospitalBanner banner = new HospitalBanner();
				banner.setIsMainImg("Y");
				for (Map.Entry<Long,HospitalBasicInfo> entry : hospitalMap.entrySet()) {
				//for(HospitalBasicInfo hospital : havePackageHosList){
					HospitalBasicInfo hospital = entry.getValue();
					//计算与当前用户的距离
					double lng2 = Double.valueOf(hospital.getHospitalLng()==null?"0":hospital.getHospitalLng());
					double lat2 = Double.valueOf(hospital.getHospitalLat()==null?"0":hospital.getHospitalLat());
					double distance = 
							GetDistance.GetDistanceMethod(lng_current==null?lng1:lng_current, lat_current==null?lat1:lat_current,
									lng2, lat2);
					hospital.setToUserDistance(distance);
					//得到默认显示图片
					List<HospitalBanner> bannerList = this.organManagerService.getHospitalBannerList(hospital, banner);
					if(bannerList != null){
						hospital.setShowImgPath(bannerList.get(0).getImgPath());
					}
					hospital.setIsFamilydoctor("Y");
					//得到家庭医生数量
					List memberList = this.fdServiceItemsService.getFdServiceMemberList(null,hospital);
					hospital.setDoctors(memberList==null?"0":String.valueOf(memberList.size()));
					newPackageHosList.add(hospital);
				}
				// 按距离顺序  家庭医生机构
		        Collections.sort(newPackageHosList, new Comparator<HospitalBasicInfo>() {  
		            public int compare(HospitalBasicInfo arg0, HospitalBasicInfo arg1) {  
		                double hits0 = arg0.getToUserDistance();  
		                double hits1 = arg1.getToUserDistance();  
		                if (hits1 > hits0) {  
		                    return -1;  
		                } else if (hits1 == hits0) {  
		                    return 0;  
		                } else {  
		                    return 1;  
		                }  
		            }  
		        });
		        for(HospitalBasicInfo hospital : newPackageHosList){
		        	this.hospitalBasicInfoList.add(hospital);
		        }
			}
			//得到能够展示给用户的机构
			if(hospitalBasicInfo == null)
				hospitalBasicInfo = new HospitalBasicInfo();
			hospitalBasicInfo.setIsShowForUser("Y");
			List<HospitalBasicInfo> showToUserHosList = this.organManagerService.getHospitalBasicInfoList(hospitalBasicInfo);
			if(showToUserHosList != null){
				List<HospitalBasicInfo> openServiceHosList = new ArrayList<HospitalBasicInfo>();
				HospitalBanner banner = new HospitalBanner();
				banner.setIsMainImg("Y");
				for(HospitalBasicInfo hospital : showToUserHosList){
					//计算与当前用户的距离
					double lng2 = Double.valueOf(hospital.getHospitalLng()==null?"0":hospital.getHospitalLng());
					double lat2 = Double.valueOf(hospital.getHospitalLat()==null?"0":hospital.getHospitalLat());
					double distance = 
							GetDistance.GetDistanceMethod(lng_current==null?lng1:lng_current, lat_current==null?lat1:lat_current,
									lng2, lat2);
					hospital.setToUserDistance(distance);
					//得到默认显示图片
					List<HospitalBanner> bannerList = this.organManagerService.getHospitalBannerList(hospital, banner);
					if(bannerList != null){
						hospital.setShowImgPath(bannerList.get(0).getImgPath());
					}
					if(hospitalBasicInfoList != null && !hospitalBasicInfoList.contains(hospital)){
						openServiceHosList.add(hospital);
					}
				}
				// 按距离顺序  
		        Collections.sort(openServiceHosList, new Comparator<HospitalBasicInfo>() {  
		            public int compare(HospitalBasicInfo arg0, HospitalBasicInfo arg1) {  
		                double hits0 = arg0.getToUserDistance();  
		                double hits1 = arg1.getToUserDistance();  
		                if (hits1 > hits0) {  
		                    return -1;  
		                } else if (hits1 == hits0) {  
		                    return 0;  
		                } else {  
		                    return 1;  
		                }  
		            }  
		        }); 
		        for(HospitalBasicInfo hospital : openServiceHosList){
		        	this.hospitalBasicInfoList.add(hospital);
		        }
			}
		}
		/**
		 * 保存门诊预约信息
		 */
		else if(action.equals("saveOrUpdateClinicOrder")){
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			this.doctorInfo = this.doctorInfoBo.getDoctorInfoByDoctorId(doctorInfo.getId());
			if(orderInfoClinic.getId() == null) { //保存添加
//				//检查当前用户的未付款订单中是否已存在相同条件的订单
//				Boolean flag = this.familyDoctorBo.checkOrderIsExist
//						(orderInfoClinic.getAppointmentDate(), orderInfoClinic.getAppointmentBeganTime(),
//								orderInfoClinic.getClinicAddress(), doctorInfo, userInfo);
//				if(flag){
//					mes = "家庭医生服务门诊订单已存在，请查看‘我的订单’";
//					return "fail";
//				}
				//一天只能有三个有效订单(包括普通和套餐一起)
				long count = this.getClinicLimited(userInfo);
				if(count >= 3L){
					mes = "您今天的门诊预约量已超过规定次数";
					return "fail";
				}
				String orderNum = String.valueOf(System.currentTimeMillis());
				orderInfoClinic.setOrderNum(orderNum);
				orderInfoClinic.setDoctorInfo(doctorInfo);
				orderInfoClinic.setUserInfo(userInfo);
				//得到加号门诊费用
				OrderInfoClinic order = new OrderInfoClinic();
				DoctorMorePractice doctorMorePractice = new DoctorMorePractice();
				
				doctorMorePractice.setClinicAddress(orderInfoClinic.getClinicAddress());
				doctorMorePractice.setClinicDate(orderInfoClinic.getAppointmentDate());
				DoctorMorePractice old = this.doctorClinicService.getDoctorMorePracticeList(doctorMorePractice, doctorInfo).get(0);
				
				order.setClinicOrgType(orderInfoClinic.getClinicOrgType());
				if(ConstantManage.CLINIC_ORG_TYPE_1.equals(orderInfoClinic.getClinicOrgType())){
					//判断坐诊医生是否下班，下班后不能再约
					if(ConstantManage.HASE_FINISHED_CLINIC_ORDER.equals(old.getOrgClinicStatus())){
						mes = "医生已下班请选择其他时间预约";
						return "fail";
					}
					order.setClinicAddress(old.getDoctorMorePracticeOrgInfo().getId().toString());
				}
				HospitalPosition position = this.clinicOrderService.getHospitalPositionByDoctor(doctorInfo,order);
				String totalPrice = "";
				String commission = "";
				//0为本单位门诊，1为外单位门诊
				if (ConstantManage.CLINIC_ORG_TYPE_0.equals(orderInfoClinic.getClinicOrgType())) {
					totalPrice = (position == null?"0":position.getClinicMoney());
					commission = (position == null?"0":position.getCommission());
				} else {
					totalPrice = (position == null?"0":position.getClinicMoneyOut());
					commission = (position == null?"0":position.getCommissionPerCase());
				}
				orderInfoClinic.setHistoryPrice(totalPrice);
				orderInfoClinic.setTotalPrice(totalPrice);
				orderInfoClinic.setCommission(commission);
				orderInfoClinic.setPayMethod(ConstantManage.FD_PAY);//用户使用的支付方式
				orderInfoClinic.setOrderType("家庭医生套餐");
				orderInfoClinic.setUseRemainBalance("0");//用户使用的余额数
				orderInfoClinic.setRealPrice("0");//实付金额
				orderInfoClinic.setDiscountMoney(String.valueOf("0"));//福利优惠抵扣数
				//保存或更新订单
				orderInfoClinic.setOrderStatus(ConstantManage.HASE_BOOKED_CLINIC_ORDER);
				Long orderId = this.clinicOrderService.saveClinicOrder(orderInfoClinic);
				//将已预约时间设置为不可用
				this.clinicOrderService.updateDoctorClinicTimeSegmentStatus(doctorInfo, "N",
						orderInfoClinic.getAppointmentDate(), orderInfoClinic.getAppointmentBeganTime());
			}
		}
		return SUCCESS;
	}
	/**
	 * 得到医生可被预约的时间
	 * @param doctorInfo
	 */
	private List<DoctorClinicTimeSegment> getCanUseClinicTime(DoctorMorePractice dmp,DoctorInfo doctorInfo){
		
		String clinicDate = dmp.getClinicDate();
		DoctorClinicTimeSegment doctorClinicTimeSegment = new DoctorClinicTimeSegment();
		doctorClinicTimeSegment.setDoctorMorePractice(dmp);
		List<DoctorClinicTimeSegment> timeSegmentCanUsedList = 
				this.clinicOrderService.getDoctorClinicTimeSegmentList(doctorClinicTimeSegment, doctorInfo);
		if(timeSegmentCanUsedList == null) return null;
		for(DoctorClinicTimeSegment dcts : timeSegmentCanUsedList){
			//当前时间,用于判断可预约时间是否过期
			String nowDate = DateManage.getDateStr("yyyy-MM-ddHH:mm").replaceAll("-", "").replaceAll(":", "");
			if("Y".equals(dcts.getIsCanUse())){//排除没被占用的过期预约时间
				String clinicTime = (clinicDate+dcts.getSegment()).replaceAll("-", "").replaceAll(":", "");
				if(Long.valueOf(clinicTime) < Long.valueOf(nowDate)){ //门诊时间小于当前时间
					dcts.setIsCanUse("N");
				}
			}
		}
		return timeSegmentCanUsedList;
	}
	/**
	 * 用户预约门诊时得到有效加号订单数
	 * @param orderClinic
	 * @return
	 */
	private Long getCanPlusNum(OrderInfoClinic orderClinic){
		String flag = ConstantManage.HASE_BOOKED_CLINIC_ORDER+","+
				ConstantManage.HASE_FINISHED_CLINIC_ORDER+","+
				ConstantManage.HASE_MEET_CLINIC_ORDER+","+
				ConstantManage.HASE_EVALUATED_CLINIC_ORDER;
		long count=0;
		List<OrderInfoClinic> plusOrderList = this.clinicOrderService.getOrderInfoClinicList(orderClinic);
		if(plusOrderList == null){
			return 0L;
		}
		for(OrderInfoClinic oic : plusOrderList){
			String status = oic.getOrderStatus();
			if(flag.indexOf(status) > -1){
				count++;
			}
		}
		return count;
	}
	/**
	 * 得到用户一天的门诊预约次数
	 * @param userInfo
	 * @return
	 */
	private long getClinicLimited(UserInfo userInfo){
		String flagLimt3 = ConstantManage.HASE_BOOKED_CLINIC_ORDER+","+
				ConstantManage.HASE_FINISHED_CLINIC_ORDER+","+
				ConstantManage.HASE_MEET_CLINIC_ORDER+","+
				ConstantManage.HASE_EVALUATED_CLINIC_ORDER+"," +
				ConstantManage.USER_CANCLE_CLINIC_ORDER;
		long count=0;
		OrderInfoClinic oic = new OrderInfoClinic();
		oic.setAppointmentDate(orderInfoClinic.getAppointmentDate());
		oic.setUserInfo(userInfo);
		List<OrderInfoClinic> orderList = this.clinicOrderService.getOrderInfoClinicList(oic);
		if(orderList != null){
			for(OrderInfoClinic order : orderList){
				if(flagLimt3.indexOf(order.getOrderStatus()) > -1){
					count++;
				}
			}
		}
		return count;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public FdServicePackage getFdServicePackage() {
		return fdServicePackage;
	}
	public void setFdServicePackage(FdServicePackage fdServicePackage) {
		this.fdServicePackage = fdServicePackage;
	}
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	public List<DoctorMorePracticeFo> getDoctorMorePracticeFoList() {
		return doctorMorePracticeFoList;
	}
	public void setDoctorMorePracticeFoList(
			List<DoctorMorePracticeFo> doctorMorePracticeFoList) {
		this.doctorMorePracticeFoList = doctorMorePracticeFoList;
	}
	public DoctorMorePractice getDoctorMorePractice() {
		return doctorMorePractice;
	}
	public void setDoctorMorePractice(DoctorMorePractice doctorMorePractice) {
		this.doctorMorePractice = doctorMorePractice;
	}
	public List<DoctorClinicTimeSegment> getDoctorClinicTimeSegmentList() {
		return doctorClinicTimeSegmentList;
	}
	public void setDoctorClinicTimeSegmentList(
			List<DoctorClinicTimeSegment> doctorClinicTimeSegmentList) {
		this.doctorClinicTimeSegmentList = doctorClinicTimeSegmentList;
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
	public Double getLng_current() {
		return lng_current;
	}
	public void setLng_current(Double lng_current) {
		this.lng_current = lng_current;
	}
	public Double getLat_current() {
		return lat_current;
	}
	public void setLat_current(Double lat_current) {
		this.lat_current = lat_current;
	}
	public OrderInfoClinic getOrderInfoClinic() {
		return orderInfoClinic;
	}
	public void setOrderInfoClinic(OrderInfoClinic orderInfoClinic) {
		this.orderInfoClinic = orderInfoClinic;
	}
	public List<DoctorInfo> getDoctorInfoList() {
		return doctorInfoList;
	}
	public void setDoctorInfoList(List<DoctorInfo> doctorInfoList) {
		this.doctorInfoList = doctorInfoList;
	}
	public Long getFdTeamId() {
		return fdTeamId;
	}
	public void setFdTeamId(Long fdTeamId) {
		this.fdTeamId = fdTeamId;
	}
}
