package com.kybaby.newbussiness.detailrecord.action;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.domain.Coupon;
import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.RecommentAwardRecord;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;

public class DetailRecordManager extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	private String mes = "操作成功";
	/**
	 * 推荐记录列表
	 */
	private List<RecommentAwardRecord>  recommentAwardRecordList = new ArrayList<>();
	/**
	 * 推荐记录
	 */
	private RecommentAwardRecord recommentAwardRecord;
	/**
	 * 医生账户明细列表
	 */
	private List<DoctorAccount>  doctorAccountList = new ArrayList<>();
	/**
	 * 医生明细记录
	 */
	private DoctorAccount doctorAccount;
	public String execute() throws Exception {
		/**
		 * 得到注册推荐记录表
		 */
		if (action.equals("getRecommentAwardRecordList")) {
			this.recommentAwardRecordList = this.detailRecordService.getAllRecommentAwardRecord(recommentAwardRecord);
			if(recommentAwardRecordList != null){
				for(RecommentAwardRecord rar : recommentAwardRecordList){
					 if(rar.getRecommendUserId() != null){
						 UserInfo recommendUser = this.userInfoBo.getUserInfoById(rar.getRecommendUserId());
						 rar.setRecommendUser(recommendUser);
					 }
					 if(rar.getBeenRecommendUserId() != null){
						 UserInfo beenRecommendUser = this.userInfoBo.getUserInfoById(rar.getBeenRecommendUserId());
						 rar.setBeenRecommendUser(beenRecommendUser);
					 }		 
					if(rar.getRecommendDoctorId() != null){
						 DoctorInfo recommendDoctor = this.doctorInfoBo.getDoctorInfoById(rar.getRecommendDoctorId());
						rar.setRecommendDoctor(recommendDoctor);
					}
					if(rar.getBeenRecommendDoctorId() != null){
						 DoctorInfo beenRecommendDoctor =  this.doctorInfoBo.getDoctorInfoById(rar.getBeenRecommendDoctorId());
						rar.setBeenRecommendDoctor(beenRecommendDoctor);
					}
					if(rar.getCouponId() != null){
						 Coupon coupon = this.couponManageBo.getCouponById(rar.getCouponId());
						 rar.setCoupon(coupon);
					}
				}
			}
		}
		/**
		 * 得到注册推荐记录表
		 */
		else if (action.equals("getAllDoctorAccountList")) {
			this.doctorAccountList = this.detailRecordService.getAllDoctorAccountList(doctorAccount);
			if(doctorAccountList != null){
				for(DoctorAccount doct : doctorAccountList){
					 DoctorInfo doctor = this.doctorInfoBo.getDoctorInfoById(doct.getDoctorId());
					 doct.setDoctorInfo(doctor);
				}
			}
		}
		return "success";
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public List<RecommentAwardRecord> getRecommentAwardRecordList() {
		return recommentAwardRecordList;
	}
	public void setRecommentAwardRecordList(
			List<RecommentAwardRecord> recommentAwardRecordList) {
		this.recommentAwardRecordList = recommentAwardRecordList;
	}
	public RecommentAwardRecord getRecommentAwardRecord() {
		return recommentAwardRecord;
	}
	public void setRecommentAwardRecord(RecommentAwardRecord recommentAwardRecord) {
		this.recommentAwardRecord = recommentAwardRecord;
	}
	public List<DoctorAccount> getDoctorAccountList() {
		return doctorAccountList;
	}
	public void setDoctorAccountList(List<DoctorAccount> doctorAccountList) {
		this.doctorAccountList = doctorAccountList;
	}
	public DoctorAccount getDoctorAccount() {
		return doctorAccount;
	}
	public void setDoctorAccount(DoctorAccount doctorAccount) {
		this.doctorAccount = doctorAccount;
	}
}
