package com.kybaby.action.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorAddress;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.DoctorProduct;
import com.kybaby.domain.TimeInit;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author sujiantang
 *
 */
public class SetServiceTime extends BaseAction{
	
	private List<DoctorAddress> doctorAddress;
	private List<DoctorProduct> doctorProductList;
	private List<TimeInit> timeInit;
	private List<String> dateNowStr;
	private String mes;
	private Long addressId;
	private String someTimeStr;
	
	@Override
	public String execute(){
		if(action.equals("getAll")){
			DoctorInfo doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo!=null){
				dateNowStr = new ArrayList<String>();
				for(int j=0;j<14;j++){
					Date date = new Date();  
//				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					c.setTime(date);
					c.add(Calendar.DAY_OF_YEAR, j);
					dateNowStr.add(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
				}
//			    dateNowStr = sdf.format(date); 
				doctorAddress = new ArrayList<DoctorAddress>();
				timeInit = new ArrayList<TimeInit>();
				doctorAddress = setServiceTimeBo.getDoctorAddressByDoctorId(doctorInfo.getId());
				if(doctorAddress==null){
					mes="无地址";
					return "success";
				}
				for(int i=0;i<doctorAddress.size();i++){
					if(!doctorAddress.get(i).getAddressStatus().equals("0")){
						doctorProductList = new ArrayList<DoctorProduct>();
						doctorProductList = setServiceTimeBo.getInUseTimeByAddressIdAndDoctorId(doctorAddress.get(i).getId(),doctorInfo.getId());
					}
				}
				timeInit = setServiceTimeBo.getServiceTimeList();
				mes="成功";
				return "success";
			}
			mes="请登录";
			return "fail";
		}
		if(action.equals("setSome")){
			DoctorInfo doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo==null){
				mes="请登录";
				return "fail";
			}
			if(someTimeStr.equals("")||someTimeStr.equals(null)){
				mes="无数据";
				return "fail";
			}
			Calendar cld=Calendar.getInstance();
			String year = String.valueOf(cld.get(Calendar.YEAR));//得到年
			String[] someTimeStrList = someTimeStr.split("@@");
			setServiceTimeBo.deleteSomeTime(Long.valueOf(someTimeStrList[0]));
			String[] dateAndTime = someTimeStrList[1].split("::");
			for(int i=0;i<dateAndTime.length;i++){
				String[] dateOrtime = dateAndTime[i].split("&&");
				Long timeId = Long.valueOf(dateOrtime[0]);
				TimeInit someTimeInit = setServiceTimeBo.getSomeTimeInitById(timeId);
				for(Long j=someTimeInit.getStartTime();j<someTimeInit.getEndTime();j++){
					DoctorProduct doctorProduct = new  DoctorProduct();
					doctorProduct.setAddressId(Long.valueOf(someTimeStrList[0]));
					doctorProduct.setDoctorId(doctorInfo.getId());
					doctorProduct.setIsProvide("N");
					doctorProduct.setServiceDate(year+"-"+dateOrtime[1]); 
					doctorProduct.setServiceTimes(j);
					doctorProduct.setTimeInitId(timeId);
					setServiceTimeBo.saveDoctorProduct(doctorProduct);
				}
			}
			mes="成功";
			return "success";
			
		}
		if(action.equals("selected")){
			DoctorInfo doctorInfo = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			doctorProductList = new ArrayList<DoctorProduct>();
			doctorProductList = setServiceTimeBo.getDoctorProduct(doctorInfo.getId(),addressId);
		}
		
		//added by zhong at 2015-11-01:列出医生所有的地址设置过的时间
		if(action.equals("selectedTime")){
			DoctorInfo doctorInfo = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			doctorProductList = new ArrayList<DoctorProduct>();
			//doctorProductList = setServiceTimeBo.getDoctorProduct(doctorInfo.getId(),addressId);
			doctorProductList=setServiceTimeBo.getDoctorServiceTime(doctorInfo.getId());
		}
		return "success";
		
	}

	public List<DoctorAddress> getDoctorAddress() {
		return doctorAddress;
	}

	public List<TimeInit> getTimeInit() {
		return timeInit;
	}

	public List<String> getDateNowStr() {
		return dateNowStr;
	}

	@Override
	public String getMes() {
		return mes;
	}

	public List<DoctorProduct> getDoctorProductList() {
		return doctorProductList;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public void setSomeTimeStr(String someTimeStr) {
		this.someTimeStr = someTimeStr;
	}
}
