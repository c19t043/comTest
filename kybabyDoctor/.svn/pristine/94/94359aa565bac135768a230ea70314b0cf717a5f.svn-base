package com.kybaby.action.main;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorAddress;
import com.kybaby.domain.DoctorInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author sujiantang
 *
 */
public class AddressManage extends BaseAction{
	
	private List<DoctorAddress> doctorAddress;
	private String mes;
	private String detailAddress;
	private String doctorStreet;
	private String doctorArea;
	private String doctorCity;
	private String doctorLat;
	private String doctorLng;
	private String doctorProvince;
	private Long addressId;
	private DoctorAddress doctAddress;
	
	@Override
	public String execute(){
		if(action.equals("all")){
			DoctorInfo doctorInfo = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo==null){
				mes="请登录";
				return "fail";
			}
			doctorAddress = new ArrayList<DoctorAddress>();
			doctorAddress = addressManageBo.getAllAddressByDoctorId(doctorInfo.getId());
			if(doctorAddress!=null){
				for(int i=0;i<doctorAddress.size();i++){
					if(doctorAddress.get(i).getAddressStatus().equals("Y")){
						ActionContext.getContext().getSession().put("Address", doctorAddress.get(i));
					}
				}
				mes="成功";
				return "success";
			}
			mes="无数据";
			return "fail";
		}
		if(action.equals("add")){
			DoctorInfo doctorInfo = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo==null){
				mes="请登录";
				return "fail";
			}
			doctorAddress = new ArrayList<DoctorAddress>();
			doctorAddress = addressManageBo.getAllAddressByDoctorId(doctorInfo.getId());
			if(doctorAddress==null){
				DoctorAddress doctorAddr = new DoctorAddress();
				doctorAddr.setAddressStatus("Y");
				doctorAddr.setDetailAddress(detailAddress);
				doctorAddr.setDoctorArea(doctorArea);
				doctorAddr.setDoctorCity(doctorCity);
				doctorAddr.setDoctorId(doctorInfo.getId());
				doctorAddr.setDoctorLat(doctorLat);
				doctorAddr.setDoctorLng(doctorLng);
				doctorAddr.setDoctorProvince(doctorProvince);
				doctorAddr.setDoctorStreet(doctorStreet);
				doctorAddr.setComments("");
				addressManageBo.save(doctorAddr);
			}else{
				DoctorAddress doctorAddr = new DoctorAddress();
				doctorAddr.setAddressStatus("N");
				doctorAddr.setDetailAddress(detailAddress);
				doctorAddr.setDoctorArea(doctorArea);
				doctorAddr.setDoctorCity(doctorCity);
				doctorAddr.setDoctorId(doctorInfo.getId());
				doctorAddr.setDoctorLat(doctorLat);
				doctorAddr.setDoctorLng(doctorLng);
				doctorAddr.setDoctorProvince(doctorProvince);
				doctorAddr.setDoctorStreet(doctorStreet);
				doctorAddr.setComments("");
				addressManageBo.save(doctorAddr);
			}
			mes="成功";
			return "success";
		}
		if(action.equals("one")){
			System.out.println("one");
			doctAddress = addressManageBo.getAddressById(addressId);
			if(doctAddress==null){
				mes="地址不存在";
				return "fail";
			}
			mes="成功";
			return "success";
		}
		if(action.equals("modify")){
			DoctorAddress doctorAddr = addressManageBo.getAddressById(addressId);
			if(doctorAddr==null){
				mes="地址不存在";
				return "fail";
			}
			doctorAddr.setDetailAddress(detailAddress);
			doctorAddr.setDoctorArea(doctorArea);
			doctorAddr.setDoctorCity(doctorCity);
			doctorAddr.setDoctorLat(doctorLat);
			doctorAddr.setDoctorLng(doctorLng);
			doctorAddr.setDoctorProvince(doctorProvince);
			doctorAddr.setDoctorStreet(doctorStreet);
			addressManageBo.update(doctorAddr);
			mes="成功";
			return "success";
		}
		if(action.equals("delete")){
			DoctorAddress doctorAddr = addressManageBo.getAddressById(addressId);
			if(doctorAddr==null){
				mes="地址不存在";
				return "fail";
			}
			/*
			 * update by HooLee
			 * time  2015年11月25日16:37:39
			 * 如果删除的地址是最后一个地址的话，我们就不让用户删除
			 * */
			long doctorId=doctorAddr.getDoctorId();
			List<DoctorAddress> doctorAddressList=addressManageBo.getAllAddressByDoctorId(doctorId);
			if(doctorAddressList==null||doctorAddressList.size()==1){
				mes="修改";
				return "fail";
			}
			String addressStatus=doctorAddr.getAddressStatus();
			if(doctorAddr.getAddressStatus().equals("Y")){
				ActionContext.getContext().getSession().remove("Address");
			}
			doctorAddr.setAddressStatus("0");
			addressManageBo.update(doctorAddr);
			/*
			 * update by HooLee
			 * time 2015年11月25日14:52:25
			 * 删除地址之后就应该将该地址相关的没有被预约的时间删除掉
			 * */
			addressManageBo.deleteSomeDoctorAddress(addressId, "N");
			
			if(addressStatus.equals("Y")){
				/*
				 * update by HooLee
				 * time  2015年11月25日16:36:00
				 * 如果删除的地址是默认地址就给该医生重新选择一个默认地址
				 * */
				doctorAddressList=addressManageBo.getAllAddressByDoctorId(doctorId);
				if(doctorAddressList!=null){
					DoctorAddress address=doctorAddressList.get(0);
					if(address!=null){
						address.setAddressStatus("Y");
						addressManageBo.update(address);
						ActionContext.getContext().getSession().put("Address", address);
					}
				}
			}
			mes="成功";
			return "success";
		}
		if(action.equals("click")){
			DoctorAddress doctorAddress = (DoctorAddress) ActionContext.getContext().getSession().get("Address");
			DoctorAddress doctorAddr = addressManageBo.getAddressById(addressId);
			if(doctorAddress!=null){
				doctorAddress.setAddressStatus("N");
				addressManageBo.update(doctorAddress);
			}
			if(doctorAddr==null){
				mes="地址不存在";
				return "fail";
			}
			doctorAddr.setAddressStatus("Y");
			addressManageBo.update(doctorAddr);
			ActionContext.getContext().getSession().put("Address", doctorAddr);
			mes="成功";
			return "success";
		}
		return "success";
		
	}

	public List<DoctorAddress> getDoctorAddress() {
		return doctorAddress;
	}

	@Override
	public String getMes() {
		return mes;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public void setDoctorArea(String doctorArea) {
		this.doctorArea = doctorArea;
	}

	public void setDoctorCity(String doctorCity) {
		this.doctorCity = doctorCity;
	}

	public void setDoctorLat(String doctorLat) {
		this.doctorLat = doctorLat;
	}

	public void setDoctorLng(String doctorLng) {
		this.doctorLng = doctorLng;
	}

	public void setDoctorProvince(String doctorProvince) {
		this.doctorProvince = doctorProvince;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public DoctorAddress getDoctAddress() {
		return doctAddress;
	}

	public void setDoctorStreet(String doctorStreet) {
		this.doctorStreet = doctorStreet;
	}

}
