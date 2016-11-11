package com.java.doctormanager.service;

import com.java.platform.user.service.Service;

public interface DoctorOrderGathrerService extends Service {
	/**
	 * 汇总当天医生的订单数据
	 */
	void saveGatherDoctorOrderData();
}
