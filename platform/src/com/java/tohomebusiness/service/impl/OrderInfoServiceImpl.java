package com.java.tohomebusiness.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.ServiceImpl;
import com.java.tohomebusiness.service.OrderInfoService;
import com.java.tohomebusiness.vo.OrderInfo;


public class OrderInfoServiceImpl extends ServiceImpl implements OrderInfoService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderInfo> getOrderInfoListByPage(PageSortModel psm,
			OrderInfo orderInfo) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM OrderInfo u where 1=1");
		if(orderInfo != null){
			if(StringUtils.isNotEmpty(orderInfo.getOrderNum())){
				param.put("orderNum","%"+ orderInfo.getOrderNum() + "%");
				hql.append(" and u.orderNum like :orderNum");
			}
			if(StringUtils.isNotEmpty(orderInfo.getBespokeDate())){
				param.put("bespokeDate","%"+ orderInfo.getBespokeDate() + "%");
				hql.append(" and u.bespokeDate like :bespokeDate");
			}
			if(StringUtils.isNotEmpty(orderInfo.getOrderStatus())){
				param.put("orderStatus","%"+ orderInfo.getOrderStatus() + "%");
				hql.append(" and u.orderStatus like :orderStatus");
			}
			if(orderInfo.getDoctorInfo() != null){
				if(StringUtils.isNotEmpty(orderInfo.getDoctorInfo().getDoctorName())){
					param.put("doctorName","%"+orderInfo.getDoctorInfo().getDoctorName()+"%");
					hql.append(" and u.doctorInfo.doctorName like :doctorName");
				}
			}
			if(orderInfo.getUserInfo() != null){
				if(StringUtils.isNotEmpty(orderInfo.getUserInfo().getBabyName())){
					param.put("babyName","%"+orderInfo.getUserInfo().getBabyName()+"%");
					hql.append(" and u.userInfo.babyName like :babyName");
				}
			}
		}
		hql.append(" order by u.bespokeDate desc, u.submitTime desc");
		List<OrderInfo> list = (List<OrderInfo>) listForEc(hql.toString(),psm, param);
		return list;
	}
	
}
