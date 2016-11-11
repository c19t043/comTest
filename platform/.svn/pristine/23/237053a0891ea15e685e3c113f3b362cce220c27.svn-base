package com.java.medicalorgandbusiness.doctorclinic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.doctorclinic.service.DoctorClinicOrderService;
import com.java.medicalorgandbusiness.vo.OrderInfoClinic;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.user.service.ServiceImpl;
import com.java.publichealth.residentsfile.vo.KyUserInfo;

public class DoctorClinicOrderServiceImpl extends ServiceImpl implements DoctorClinicOrderService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderInfoClinic> getOrderInfoClinicList(PageSortModel psm,
			OrderInfoClinic orderInfoClinic) {
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuffer hql = new StringBuffer("from OrderInfoClinic p where 1=1 ");
		if(orderInfoClinic != null){
			DoctorInfo doctorInfo = orderInfoClinic.getDoctorInfo();
			KyUserInfo userInfo = orderInfoClinic.getUserInfo();
			if(doctorInfo != null){
				if(doctorInfo.getDoctorName() != null && !"".equals(doctorInfo.getDoctorName().trim())){
					hql.append(" and p.doctorInfo.doctorName like :doctorName");
					params.put("doctorName","%"+doctorInfo.getDoctorName().trim()+"%");
				}
			}
			if(userInfo != null){
				if(userInfo.getBabyName() != null && !"".equals(userInfo.getBabyName().trim())){
					hql.append(" and p.userInfo.babyName like :babyName");
					params.put("babyName","%"+userInfo.getBabyName().trim()+"%");
				}
			}
			if(orderInfoClinic.getOrderStatus() != null && !"".equals(orderInfoClinic.getOrderStatus().trim())){
				hql.append(" and p.orderStatus = :orderStatus");
				params.put("orderStatus",orderInfoClinic.getOrderStatus().trim());
			}
			if(orderInfoClinic.getAppointmentDate() != null && !"".equals(orderInfoClinic.getAppointmentDate().trim())){
				hql.append(" and p.appointmentDate like :appointmentDate");
				params.put("appointmentDate","%"+orderInfoClinic.getAppointmentDate().trim()+"%");
			}
			if(orderInfoClinic.getOrderNum() != null && !"".equals(orderInfoClinic.getOrderNum().trim())){
				hql.append(" and p.orderNum like :orderNum");
				params.put("orderNum","%"+orderInfoClinic.getOrderNum().trim()+"%");
			}
		}
		hql.append(" order by p.orderTime desc,p.orderStatus");
		List<OrderInfoClinic> list = (List<OrderInfoClinic>) listForEc(hql.toString(),psm, params);
		return list;
	}

	@Override
	public void saveOrUpdateOrderInfoClinic(OrderInfoClinic orderInfoClinic) {
		// TODO Auto-generated method stub
		
	}

}
