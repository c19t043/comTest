package com.kybaby.newbussiness.doctorclinic.action;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.domain.DoctorAddress;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorServiceType;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.opensymphony.xwork2.ActionContext;
/**
 * 家庭医生服务设置action
 * @author lihao
 *
 */
public class FamilyDoctorServeAction extends NewBaseAction {
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 医生信息
	 */
	private DoctorInfo doctorInfo;
	/**
	 * 医生地址信息
	 */
	private DoctorAddress doctorAddress;
	/**
	 * 医生服务类型集合
	 */
	private List<DoctorServiceType> doctorServiceTypeList = new ArrayList<DoctorServiceType>();
	
	@Override
	public String execute() {
		if((DoctorInfo)ActionContext.getContext().getSession().get("Doctor")==null){
			mes="请登录";
			return "fail";
		}
		/**
		 * 初始化家庭医生服务设置
		 */
		if(action.equals("initFamilyDoctorServe")) {
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
//			doctorInfo = this.doctorInfoBo.getDoctorInfoBoById(doctorInfo.getId());
			System.out.println("======initFamilyDoctorServe=====");
			//得到医生的默认地址
			List<DoctorAddress> doctorAddressList = this.addressManageBo.getAllAddressByDoctorId(doctorInfo.getId());
			if(doctorAddressList != null){
				for(DoctorAddress da : doctorAddressList){
					if("Y".equals(da.getAddressStatus())){
						this.doctorAddress = da;
						break;
					}
				}
			}
			if(doctorAddress != null){
				//重定向到设置时间去
				this.mes = "设置时间";
			}
			//this.doctorServiceTypeList = this.familyDoctorServeService.getDoctorServiceTypeList("家庭医生", doctorInfo);
		}
		/**
		 * 保存家庭医生服务设置
		 */
		else if(action.equals("saveFamilyDoctorServe")) {
			DoctorInfo old = this.doctorInfoBo.getDoctorInfoBoById(doctorInfo.getId());
			old.setServiceArea(doctorInfo.getServiceArea());
			old.setComeMethod(doctorInfo.getComeMethod());
			this.doctorInfoBo.update(old);
			doctorAddress.setDoctorId(doctorInfo.getId());
			if(doctorAddress.getId() == null){
				this.addressManageBo.save(doctorAddress);
			}else{
				this.addressManageBo.update(doctorAddress);
			}
		}
		/**
		 * 得到家庭医生服务设置信息
		 */
		else if(action.equals("getFamilyDoctorServe")) {
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			doctorInfo = this.doctorInfoBo.getDoctorInfoBoById(doctorInfo.getId());
			//得到医生的默认地址
			List<DoctorAddress> doctorAddressList = this.addressManageBo.getAllAddressByDoctorId(doctorInfo.getId());
			if(doctorAddressList != null){
				for(DoctorAddress da : doctorAddressList){
					if("Y".equals(da.getAddressStatus())){
						this.doctorAddress = da;
						break;
					}
				}
			}
			this.doctorServiceTypeList = this.familyDoctorServeService.getDoctorServiceTypeList("家庭医生", doctorInfo);
		}
		return "success";
	}
	
	
	
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	@Override
	public String getMes() {
		return mes;
	}
	public DoctorAddress getDoctorAddress() {
		return doctorAddress;
	}
	public void setDoctorAddress(DoctorAddress doctorAddress) {
		this.doctorAddress = doctorAddress;
	}
	public List<DoctorServiceType> getDoctorServiceTypeList() {
		return doctorServiceTypeList;
	}
	
}
