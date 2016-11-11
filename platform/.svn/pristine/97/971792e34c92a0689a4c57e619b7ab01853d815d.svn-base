package com.java.operationmanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.commons.lang.StringUtils;

import com.java.ec.common.PageSortModel;
import com.java.operationmanage.service.OperationModelService;
import com.java.operationmanage.vo.OperaDoctorSchedule;
import com.java.platform.user.service.ServiceImpl;

public class OperationModelServiceImpl extends ServiceImpl implements OperationModelService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperaDoctorSchedule> getOperaDoctorSchedulesListByPage(
			PageSortModel psm, OperaDoctorSchedule operaDoctorSchedule) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM OperaDoctorSchedule u where 1=1 and u.operaBaseSchedule.id is null");
		if(operaDoctorSchedule != null){
			if(StringUtils.isNotEmpty(operaDoctorSchedule.getRemarks())){
				hql.append(" and u.remarks like :remarks");
				param.put("remarks","%"+ operaDoctorSchedule.getRemarks()+"%");
			}
			if(operaDoctorSchedule.getHospitalBasicInfo()!=null&&
					StringUtils.isNotEmpty(operaDoctorSchedule.getHospitalBasicInfo().getHospitalLname())){
				hql.append(" and u.hospitalBasicInfo.hospitalLname like :hospitalLname");
				param.put("hospitalLname","%"+ operaDoctorSchedule.getHospitalBasicInfo().getHospitalLname()+"%");
			}
		}
		hql.append(" order by u.scheduleType,u.modifyTime desc");
		List<OperaDoctorSchedule> list = (List<OperaDoctorSchedule>) listForEc(hql.toString(),psm, param);
		return list;
	}

}
