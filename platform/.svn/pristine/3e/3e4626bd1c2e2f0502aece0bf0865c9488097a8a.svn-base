package com.java.medicalorgandbusiness.childcare.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.childcare.service.ChildCareOrderService;
import com.java.medicalorgandbusiness.vo.UserChildcareAppointmentInfo;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.user.service.ServiceImpl;

public class ChildCareOrderServiceImpl extends ServiceImpl implements ChildCareOrderService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList(
			PageSortModel psm,
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM UserChildcareAppointmentInfo u where 1=1");
		if(userChildcareAppointmentInfo != null){
			if(StringUtils.isNotEmpty(userChildcareAppointmentInfo.getOrderNum())){
				param.put("orderNum","%"+ userChildcareAppointmentInfo.getOrderNum() + "%");
				hql.append(" and u.orderNum like :orderNum");
			}
			if(StringUtils.isNotEmpty(userChildcareAppointmentInfo.getStatus())){
				param.put("status","%"+ userChildcareAppointmentInfo.getStatus() + "%");
				hql.append(" and u.orderNum like :status");
			}
			if(userChildcareAppointmentInfo.getDoctorInfo() != null){
				if(StringUtils.isNotEmpty(userChildcareAppointmentInfo.getDoctorInfo().getDoctorName())){
					param.put("doctorName","%"+userChildcareAppointmentInfo.getDoctorInfo().getDoctorName()+"%");
					hql.append(" and u.doctorInfo.doctorName like :doctorName");
				}
			}
			if(userChildcareAppointmentInfo.getUserInfo() != null){
				if(StringUtils.isNotEmpty(userChildcareAppointmentInfo.getUserInfo().getBabyName())){
					param.put("babyName","%"+userChildcareAppointmentInfo.getUserInfo().getBabyName()+"%");
					hql.append(" and u.userInfo.babyName like :babyName");
				}
			}
			if(userChildcareAppointmentInfo.getOrganChildcareOpenResources() != null){
				if(StringUtils.isNotEmpty(userChildcareAppointmentInfo.getOrganChildcareOpenResources().getOpenDate())){
					param.put("openDate","%"+userChildcareAppointmentInfo.getOrganChildcareOpenResources().getOpenDate()+"%");
					hql.append(" and u.organChildcareOpenResources.openDate like :openDate");
				}
			}
		}
		hql.append(" order by u.organChildcareOpenResources.openDate desc, u.operationTime desc");
		List<UserChildcareAppointmentInfo> list = (List<UserChildcareAppointmentInfo>) listForEc(hql.toString(),psm, param);
		return list;
	}

}
