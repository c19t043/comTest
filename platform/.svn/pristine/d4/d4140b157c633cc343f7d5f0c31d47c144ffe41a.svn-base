package com.java.doctorinfo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.java.b2cGoods.vo.B2cGoodsDeliver;
import com.java.doctorinfo.service.DoctorInfoService;
import com.java.ec.common.PageSortModel;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.user.service.ServiceImpl;

public class DoctorInfoServiceImpl extends ServiceImpl implements DoctorInfoService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DoctorInfo> getDoctorInfoListByPage(PageSortModel psm,
			DoctorInfo doctorInfo) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM DoctorInfo u where 1=1");
		if(doctorInfo != null){
			if(StringUtils.isNotEmpty(doctorInfo.getDoctorName())){
				param.put("doctorName","%"+ doctorInfo.getDoctorName() + "%");
				hql.append(" and u.doctorName like :doctorName");
			}
		}
		hql.append(" and u.doctorName is not null and u.doctorName <>''");
		List<DoctorInfo> list = (List<DoctorInfo>) listForEc(hql.toString(),psm, param);
		return list;
	}

}
