package com.java.doctormanager.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.consultmanager.consultdoctormanager.vo.ConsultOrderInfo;
import com.java.doctormanager.service.DoctorExamineApproveFlowService;
import com.java.doctormanager.vo.DoctorOrderSummary;
import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.vo.OrderInfoClinic;
import com.java.medicalorgandbusiness.vo.UserChildcareAppointmentInfo;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.user.service.ServiceImpl;
import com.java.tohomebusiness.vo.OrderInfo;
import com.java.util.DateManage;

public class DoctorExamineApproveFlowServiceImpl extends ServiceImpl implements DoctorExamineApproveFlowService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}
}
