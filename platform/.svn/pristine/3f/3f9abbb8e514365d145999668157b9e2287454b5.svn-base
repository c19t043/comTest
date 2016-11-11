package com.java.familydoctor.userbuyrecord.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.servicepackage.vo.FdServicePackage;
import com.java.familydoctor.servicetimes.vo.FdServiceTimes;
import com.java.familydoctor.userbuyrecord.service.FdServiceOrderService;
import com.java.familydoctor.userbuyrecord.vo.FdServiceOrder;
import com.java.familydoctor.userbuyrecord.vo.FdUserBuyRecord;
import com.java.platform.core.GlobalSysInfo;
import com.java.platform.organ.vo.Organ;
import com.java.platform.user.service.ServiceImpl;
import com.java.platform.user.vo.User;

public class FdServiceOrderServiceImpl extends ServiceImpl implements FdServiceOrderService {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		
		return null;
	}

	@Override
	public Long updateFdServiceOrder(FdServiceOrder fdServiceOrder,FdUserBuyRecord fdUserBuyRecord) {
		if(fdServiceOrder == null){
			return null;
		}
		Long id = null;
		if(fdServiceOrder.getId() != null){
			id = fdServiceOrder.getId();
			//通过页面传来的id先查询到老的数据--》必须先查询出来，因为前台服务包的id没有隐藏，传过来就丢失了
			FdServiceOrder fdServiceOrderById = getFdServiceOrderById(id);
			//设置需要更新的内容
			fdServiceOrderById.setOrderStatus(fdServiceOrder.getOrderStatus());
			//编辑修改的数据
			super.edit(fdServiceOrderById);
			//把订单的主键存入购买记录
			fdUserBuyRecord.setFdServiceOrder(fdServiceOrderById);
			//通过查询得到的当单对象，去拿服务包的对象
			FdServicePackage fdServicePackage = fdServiceOrderById.getFdServicePackage();
			//得到服务包的对象在把他设置进购买记录对象里面
			fdUserBuyRecord.setServicePackage(fdServicePackage);
			fdUserBuyRecord.setUserInfo(fdServiceOrderById.getUserInfo());
			//把外键放对象了，然后在执行添加把对象保存起来
			super.add(fdUserBuyRecord);
			
		}
		return id;
	}

	@Override
	public FdServiceOrder getFdServiceOrderById(Long id) {
		
		return super.get(id, FdServiceOrder.class);
	}

	@Override
	public List<FdServiceOrder> getFdServiceOrderByPage(PageSortModel psm,
			FdServiceOrder fdServiceOrder) {
		User currentUser = this.get(GlobalSysInfo.getCurrentUser().getUserId(), User.class);
		Organ organ = currentUser.getOrgan();
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM FdServiceOrder f where 1=1");
		if(fdServiceOrder != null){
			if(fdServiceOrder.getOrderNum() != null && !"".equals(fdServiceOrder.getOrderNum())){
				params.put("orderNum","%"+fdServiceOrder.getOrderNum()+"%");
				hql.append(" AND f.orderNum LIKE :orderNum");
			}
			if(fdServiceOrder.getPayMethod() != null && !"".equals(fdServiceOrder.getPayMethod())){
				params.put("payMethod","%"+fdServiceOrder.getPayMethod()+"%");
				hql.append(" AND f.payMethod LIKE :payMethod");
			}
			if(fdServiceOrder.getUserInfo() != null && StringUtils.isNotEmpty(fdServiceOrder.getUserInfo().getPhone())){
				params.put("phone","%"+fdServiceOrder.getUserInfo().getPhone()+"%");
				hql.append(" AND f.userInfo.phone LIKE :phone");
			}
			if(fdServiceOrder.getOrderStatus() != null && !"".equals(fdServiceOrder.getOrderStatus())){
				params.put("orderStatus", fdServiceOrder.getOrderStatus());
				hql.append(" AND f.orderStatus = :orderStatus");
			}
			if(fdServiceOrder.getFdServicePackage() != null && fdServiceOrder.getFdServicePackage().getHospitalBasicInfo() != null &&
					StringUtils.isNotEmpty(fdServiceOrder.getFdServicePackage().getHospitalBasicInfo().getHospitalLname())){
				params.put("hospitalLname", "%"+fdServiceOrder.getFdServicePackage().getHospitalBasicInfo().getHospitalLname()+"%");
				hql.append(" AND f.fdServicePackage.hospitalBasicInfo.hospitalLname like :hospitalLname");
			}
		}
		if (!User.ADMAIN_ID.equals(currentUser.getUserId()) && organ.getOrganCode() != null) {
			params.put("hospitalId", Long.valueOf(organ.getOrganCode()));
			hql.append(" AND f.fdServicePackage.hospitalBasicInfo.id = :hospitalId");
		}
		hql.append(" order by submitTime desc");
		List<FdServiceOrder> list = (List<FdServiceOrder>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}

	@Override
	public List<FdUserBuyRecord> getFdUserBuyRecordIdByFdServiceOrder(
			FdServiceOrder fdServiceOrder) {
		Map<String,Object> params =  new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder(" FROM FdUserBuyRecord f where 1=1");
		if(fdServiceOrder != null){
			if(fdServiceOrder.getId() != null){
				params.put("orderId",fdServiceOrder.getId());
				hql.append(" AND f.fdServiceOrder.id = :orderId");
			}
		}
		List<FdUserBuyRecord> list = list(hql.toString(), -1, -1, params);
		return list;
	}

	
}
