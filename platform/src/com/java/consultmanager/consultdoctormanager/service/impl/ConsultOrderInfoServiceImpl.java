package com.java.consultmanager.consultdoctormanager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.common.lang.StringUtils;
import com.java.consultmanager.consultdoctormanager.service.ConsultOrderInfoService;
import com.java.consultmanager.consultdoctormanager.vo.ConsultOrderInfo;
import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.ServiceImpl;

public class ConsultOrderInfoServiceImpl extends ServiceImpl implements
ConsultOrderInfoService {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConsultOrderInfo> getObjectListOfConsultOrderInfoWithPage(
			PageSortModel model, ConsultOrderInfo consultOrderInfo) {
		
		List<ConsultOrderInfo> order_collection = new ArrayList<ConsultOrderInfo>();
		
		StringBuilder sb = new StringBuilder("");
		Map<String,Object> params = new HashMap<String,Object>();
		
		sb.append("from ConsultOrderInfo c where 1=1 ");
		
		if(consultOrderInfo!=null) {
			
			if(consultOrderInfo.getConsultDoctorInfo()!=null
					&&StringUtils.isNotBlank(consultOrderInfo.getConsultDoctorInfo().getDoctorInfo().getDoctorName())){
				sb.append(" and c.consultDoctorInfo.doctorInfo.doctorName like :doctorName");
				params.put("doctorName", "%"+consultOrderInfo.getConsultDoctorInfo().getDoctorInfo().getDoctorName()+"%");
			}
			
			if(consultOrderInfo.getUserInfo()!=null
					&&StringUtils.isNotBlank(consultOrderInfo.getUserInfo().getParentName())){
				sb.append(" and c.userInfo.parentName like :userName");
				params.put("userName", "%"+consultOrderInfo.getUserInfo().getParentName()+"%");
			}

			if(consultOrderInfo.getUserInfo()!=null
					&&StringUtils.isNotBlank(consultOrderInfo.getUserInfo().getPhone())){
				sb.append(" and c.userInfo.phone like :phone");
				params.put("phone", "%"+consultOrderInfo.getUserInfo().getPhone()+"%");
			}

			
			if(StringUtils.isNotBlank(consultOrderInfo.getOrderStatus())){
				sb.append(" and c.orderStatus = :orderStatus");
				params.put("orderStatus", consultOrderInfo.getOrderStatus());
			}

			if(StringUtils.isNotBlank(consultOrderInfo.getOrderTime())){
				sb.append(" and c.orderTime like :orderTime");
				params.put("orderTime", "%"+consultOrderInfo.getOrderTime()+"%");
			}
		}
		
		order_collection = (List<ConsultOrderInfo>) super.listForEc(sb.toString(), model, params);
		
		return order_collection;
	}
}
