package com.java.medicalorgandbusiness.orgsetmeal.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.common.lang.StringUtils;
import com.java.ec.common.PageSortModel;
import com.java.familydoctor.archivesinfo.service.ArchivesInfoService;
import com.java.familydoctor.archivesinfo.vo.ArchivesInfo;
import com.java.medicalorgandbusiness.orgsetmeal.service.MealOrderService;
import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.medicalorgandbusiness.orgsetmeal.vo.OrganSetMeatOrder;
import com.java.platform.user.service.ServiceImpl;

public class MealOrderServiceImpl extends ServiceImpl implements
MealOrderService {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}
	
	private ArchivesInfoService archivesInfoServiceImpl;
	
	private final String ORDER_COMPLETE = "已付款";
	
	private final String USER_FROM = "线上套餐用户";
	
	/**
	 * 修改订单状态
	 * @param organSetMeatOrder
	 */
	public void updateOrderStatus(OrganSetMeatOrder organSetMeatOrder){
		OrganSetMeatOrder order_query = super.get(organSetMeatOrder.getId(), OrganSetMeatOrder.class);
		
		if(order_query==null) throw new RuntimeException();
		
		order_query.setOrderStatus(ORDER_COMPLETE);
		super.edit(order_query);
		
		//同步注册用户信息archivesInfo
		
		/*StringBuilder sb = new StringBuilder();
		if(order_query.getUserInfo()!=null&&order_query.getUserInfo().getPhone()!=null)
			sb.append("from ArchivesInfo where archivesMobile = "+order_query.getUserInfo().getPhone());
		
		List<ArchivesInfo> list = super.list(sb.toString(), -1, -1, null);
		ArchivesInfo archivesInfo = null;
		if(list.isEmpty())  archivesInfo = new ArchivesInfo();
		else archivesInfo = list.get(0);
		packageArchivesInfo(order_query,archivesInfo);
		
		archivesInfoServiceImpl.saveOrUpdateArchivesInfo(archivesInfo);*/
	}
	
	private void packageArchivesInfo(OrganSetMeatOrder order_queryo,ArchivesInfo archivesInfo){
		
		if(order_queryo.getUserInfo()!=null&&order_queryo.getUserInfo().getPhone()!=null)
			archivesInfo.setArchivesMobile(order_queryo.getUserInfo().getPhone());
		
		if(order_queryo.getOrganSetPro()!=null&&order_queryo.getOrganSetPro().getProName()!=null)
			archivesInfo.setInoculationCode(order_queryo.getOrganSetPro().getProName());
		
		if(order_queryo.getBabyInfo()!=null){
			ConsultBabyInfo babyInfo = order_queryo.getBabyInfo();
			archivesInfo.setChildrenName(babyInfo.getBabyName());
			archivesInfo.setChildrenBirthday(babyInfo.getBirthday());
		}
		
		if(order_queryo.getOrganSetPro()!=null&&order_queryo.getOrganSetPro().getHospitalBasicInfo()!=null)
		archivesInfo.setHospitalBasicInfo(order_queryo.getOrganSetPro().getHospitalBasicInfo());
		
		if(order_queryo.getUserInfo()!=null)
			archivesInfo.setUserInfo(order_queryo.getUserInfo());
		
		if(order_queryo.getOrganSetPro()!=null&&order_queryo.getOrganSetPro().getProCode()!=null)
			archivesInfo.setUserType(order_queryo.getOrganSetPro().getProCode());
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		archivesInfo.setModifyTime(time);
		archivesInfo.setCreatTime(time);
		
		archivesInfo.setIsRelation("Y");
		
		if(order_queryo.getServiceEndTime()!=null)
			archivesInfo.setExpireTime(order_queryo.getServiceEndTime());
		
		if(order_queryo.getOrganSetPro()!=null&&order_queryo.getOrganSetPro().getMaxMonthAge()!=null)
			archivesInfo.setMaxMonthAge(order_queryo.getOrganSetPro().getMaxMonthAge());
		
		archivesInfo.setUserFrom(USER_FROM);
		
	}
	
	/**
	 * 获取套餐订单信息
	 * @param model
	 * @param organSetMeatOrder
	 * @return
	 */
	public List<OrganSetMeatOrder> getOrganSetMeatOrderList(PageSortModel model,
			OrganSetMeatOrder organSetMeatOrder){
		
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		
		sb.append("from OrganSetMeatOrder a where 1=1")
			.append(" and a.orderStatus <> '未付款'");
		if(organSetMeatOrder!=null){
			if(StringUtils.isNotBlank(organSetMeatOrder.getOrderStatus())){
				sb.append(" and a.orderStatus = :orderStatus");
				params.put("orderStatus", organSetMeatOrder.getOrderStatus());
			}
			if(StringUtils.isNotBlank(organSetMeatOrder.getOrderNum())){
				sb.append(" and a.orderNum like :orderNum");
				params.put("orderNum", "%"+organSetMeatOrder.getOrderNum()+"%");
			}
			if(StringUtils.isNotBlank(organSetMeatOrder.getUserInfo().getPhone())){
				sb.append(" and a.userInfo.phone like :phone");
				params.put("phone", "%"+organSetMeatOrder.getUserInfo().getPhone()+"%");
			}
			if(StringUtils.isNotBlank(organSetMeatOrder.getOrganSetPro().getHospitalBasicInfo().getHospitalLname())){
				sb.append(" and a.organSetMeal.hospitalBasicInfo.hospitalLname like :hospitalLname");
				params.put("hospitalLname", "%"+organSetMeatOrder.getOrganSetPro().getHospitalBasicInfo().getHospitalLname()+"%");
			}
		}
		
		List<OrganSetMeatOrder> listForEc = (List<OrganSetMeatOrder>) super.listForEc(sb.toString(), model, params);
		
		if(listForEc.isEmpty()) return null;
		
		return listForEc;
	}



	public ArchivesInfoService getArchivesInfoServiceImpl() {
		return archivesInfoServiceImpl;
	}

	public void setArchivesInfoServiceImpl(
			ArchivesInfoService archivesInfoServiceImpl) {
		this.archivesInfoServiceImpl = archivesInfoServiceImpl;
	}
}
