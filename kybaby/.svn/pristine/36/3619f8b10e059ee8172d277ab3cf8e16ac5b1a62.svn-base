package com.kybaby.action.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.HospitalBanner;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgBusinessRelation;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrgOpenBusiness;
import com.kybaby.util.DateManage;
import com.kybaby.util.GetDistance;
import com.opensymphony.xwork2.ActionContext;

public class InterfaceManageAction extends NewBaseAction {
	
	private static final long serialVersionUID = 2946043995774105467L;
	/**
	 * 反馈给页面的提示信息
	 */
	private String mes = "成功";
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
	Double lng_current;
	/**
	 * 纬度
	 */
	Double lat_current;
	
	public String execute() {
		if(action.equals("getHospitalInfoList")) {
			this.hospitalBasicInfoList = this.organManagerService.getHospitalBasicInfoList(hospitalBasicInfo);
			List<HospitalBasicInfo> openServiceHosList = new ArrayList<HospitalBasicInfo>();
			if(hospitalBasicInfoList != null){
				Long userId = (Long) ActionContext.getContext().getSession().get("userId");
				double lng1;
				double lat1;
				if(userId==null) {
					lng1 = 104.0722270000;
					lat1 = 30.6634560000;
				} else {
					UserInfo user = this.userInfoBo.getUserById(userId);
					lng1 = Double.valueOf(user.getUserLng()==null?"0":user.getUserLng());
					lat1 = Double.valueOf(user.getUserLat()==null?"0":user.getUserLat());
				}
//				System.out.println("lng_current===="+lng_current);
//				System.out.println("lat_current===="+lat_current);
				HospitalBanner banner = new HospitalBanner();
				banner.setIsMainImg("Y");
				for(HospitalBasicInfo hospital : hospitalBasicInfoList) {
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
					//得到机构开展业务
					List<OrgBusinessRelation> orgBusinessList = this.organManagerService.getOrgBusinessRelationList(hospital);
					List<String> businessNameList = new ArrayList<String>();
					if(orgBusinessList != null){
						for(OrgBusinessRelation obr : orgBusinessList){
							OrgOpenBusiness openBuniss = obr.getOrgOpenBusiness();
							businessNameList.add(openBuniss.getBusinessName());
						}
					}else{
						businessNameList.add("暂无服务");
					}
					hospital.setBusinessNameList(businessNameList);
					//得到机构能够坐诊的日期
					List<String> clinicDateList = this.getBookingDate(hospital);
					hospital.setClinicBookingDate(clinicDateList);
					if(orgBusinessList != null){
						openServiceHosList.add(hospital);
					}
				}
				this.hospitalBasicInfoList = openServiceHosList;
				// 按距离顺序  
		        Collections.sort(hospitalBasicInfoList, new Comparator<HospitalBasicInfo>() {  
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
		        HttpServletRequest request = ServletActionContext.getRequest();
		        String listNum = request.getParameter("listNum");
		        if(listNum != null && !"".equals(listNum)){
		        	List<HospitalBasicInfo> hospitalBasicInfoList_new = new ArrayList<HospitalBasicInfo>();
		        	int count = 0;
		        	for(HospitalBasicInfo hospital : hospitalBasicInfoList){
		        		if(count < Integer.valueOf(listNum).intValue()){
		        			hospitalBasicInfoList_new.add(hospital);
		        		}
		        		count++;
		        	}
		        	this.hospitalBasicInfoList = hospitalBasicInfoList_new;
		        }
			}
		}
		return "success";
	}
	
	/**
	 * 得到医生门诊预约时间集合（门诊医生列表页面显示用）
	 * @param doctorInfo 医生信息
	 * @return 门诊时间显示信息集合
	 */
	private List<String> getBookingDate(HospitalBasicInfo hospital) {
		List<String> list = new ArrayList<String>();
		DoctorMorePractice doctorMorePractice = new DoctorMorePractice();
		doctorMorePractice.setClinicOrg(hospital.getHospitalLname());
		List<DoctorMorePractice> doctorMorePracticeList = 
				this.doctorClinicService.getDoctorMorePracticeList(doctorMorePractice, null);
		if(doctorMorePracticeList != null){
			int count = 0;
			for(DoctorMorePractice dmp : doctorMorePracticeList){
				if(count == 2) break;
				String clinicDate = dmp.getClinicDate();
				String nowDate = DateManage.getDateStr("yyyy-MM-dd");
				if(clinicDate.equals(nowDate)){
					nowDate = DateManage.getDateStr("MM月dd日");
					list.add("今天"+nowDate+"可约");
				}else{
					Date bookingDate = DateManage.getStrToDate(clinicDate);
					Date nowD =  DateManage.getStrToDate(nowDate);
					String dateStr = DateManage.getDateToStr(bookingDate, "MM月dd日");
					//计算差多少天
					long days = DateManage.getDaysBetween(bookingDate, nowD);
					if(days == 1L){
						list.add("明天"+dateStr+"可约");
					}else if(days == 2L){
						list.add("后天"+dateStr+"可约");
					}else{
						String week = DateManage.getWeekByZhou(bookingDate);
						list.add(week+dateStr+"可约");
					}
				}
				count++;
			}
		}
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
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

}
