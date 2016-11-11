package com.kybaby.newbussiness.senddoctor.fo;

public class DoctorInfoForSort {
	/**医生id
	 * 
	 */
	private Long doctorId;
	/**
	 * 订单量
	 */
	private Long orderSum;
	/**
	 * 开放时间数
	 */
	private Long openTimeSum;
	/**
	 * 距离
	 */
	private Double distance;
	
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public Long getOrderSum() {
		return orderSum;
	}
	public void setOrderSum(Long orderSum) {
		this.orderSum = orderSum;
	}
	public Long getOpenTimeSum() {
		return openTimeSum;
	}
	public void setOpenTimeSum(Long openTimeSum) {
		this.openTimeSum = openTimeSum;
	}
	public DoctorInfoForSort(Long doctorId, Long orderSum, Long openTimeSum,
			Double distance) {
		super();
		this.doctorId = doctorId;
		this.orderSum = orderSum;
		this.openTimeSum = openTimeSum;
		this.distance = distance;
	}
	public DoctorInfoForSort() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
