package com.kybaby.newbussiness.senddoctor.bo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.kybaby.dao.DoctorAddressDao;
import com.kybaby.dao.DoctorInfoDao;
import com.kybaby.dao.UserInfoDao;
import com.kybaby.domain.DoctorAddress;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.senddoctor.bo.SendDoctorService;
import com.kybaby.newbussiness.senddoctor.dao.SendDoctorDao;
import com.kybaby.newbussiness.senddoctor.domain.RuleBasic;
import com.kybaby.newbussiness.senddoctor.domain.RulesConfigureRecord;
import com.kybaby.newbussiness.senddoctor.domain.RulesFieldBasic;
import com.kybaby.newbussiness.senddoctor.fo.DoctorInfoForSort;
import com.kybaby.util.GetDistance;
import com.kybaby.util.SendSms;
import com.kybaby.util.SortListUtil;

public class SendDoctorServiceImpl implements SendDoctorService{
	private SendDoctorDao sendDoctorDao;
	private UserInfoDao userInfoDao;
	private DoctorInfoDao doctorInfoDao;
	private DoctorAddressDao doctorAddressDao;

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public DoctorInfoDao getDoctorInfoDao() {
		return doctorInfoDao;
	}

	public void setDoctorInfoDao(DoctorInfoDao doctorInfoDao) {
		this.doctorInfoDao = doctorInfoDao;
	}

	public DoctorAddressDao getDoctorAddressDao() {
		return doctorAddressDao;
	}

	public void setDoctorAddressDao(DoctorAddressDao doctorAddressDao) {
		this.doctorAddressDao = doctorAddressDao;
	}

	public SendDoctorDao getSendDoctorDao() {
		return sendDoctorDao;
	}

	public void setSendDoctorDao(SendDoctorDao sendDoctorDao) {
		this.sendDoctorDao = sendDoctorDao;
	}

	@Override
	public void saveRule(RuleBasic ruleBasic,List<RulesConfigureRecord> rulesConfigureRecordList) {
		if(ruleBasic.getIsStart() != null && "Y".equals(ruleBasic.getIsStart().trim())){
			//如果新加一条生效数据则将之前的数据全部设置为无效
			this.sendDoctorDao.updateRuleBasicBySql(null);
		}
		Long ruleBasicId = this.sendDoctorDao.saveRuleBasic(ruleBasic);
		this.saveOrUpdateRulesConfigureRecordList(rulesConfigureRecordList, ruleBasicId);
	}

	@Override
	public void updateRule(RuleBasic ruleBasic,List<RulesConfigureRecord> rulesConfigureRecordList) {
		if(ruleBasic.getIsStart() != null && "Y".equals(ruleBasic.getIsStart().trim())){
			//如果更新一条生效数据则将除此之外的的数据全部设置为无效
			this.sendDoctorDao.updateRuleBasicBySql(ruleBasic);
		}
		this.sendDoctorDao.updateRuleBasic(ruleBasic);
		this.saveOrUpdateRulesConfigureRecordList(rulesConfigureRecordList, ruleBasic.getRuleBasicId());
	}

	@Override
	public List<RuleBasic> getRuleBasicList(RuleBasic ruleBasic) {
		// TODO Auto-generated method stub
		return this.sendDoctorDao.getRuleBasicList(ruleBasic);
	}

	@Override
	@SuppressWarnings("all")
	public DoctorInfo autoSendDoctorByRule(OrderInfo orderInfo) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT DISTINCT   d.id, orderTemp.orderSum, openTemp.openSum ");
		sql.append(" FROM doctor_info d ");
		sql.append(" INNER JOIN (SELECT ");
		sql.append(" o.doctorId          AS orderDoctorId, ");
		sql.append(" DATE_FORMAT(o.bespokeDate,'%Y-%m') AS mon, ");
		sql.append("  COUNT(1)         AS orderSum ");
		sql.append(" FROM order_info o ");
		sql.append("  WHERE o.doctorId IS NOT NULL ");
		sql.append("  AND o.orderStatus NOT IN('医生取消','用户取消','已接单') ");//订单状态
		sql.append(" GROUP BY orderDoctorId,mon) orderTemp ");
		sql.append(" ON d.id = orderTemp.orderDoctorId ");
		sql.append(" INNER JOIN (SELECT");
		sql.append(" dp.doctorId         AS openDoctorId, ");
		sql.append("  DATE_FORMAT(dp.serviceDate,'%Y-%m') AS mon, ");
		sql.append(" COUNT(1)        AS openSum "); 
		sql.append("  FROM doctor_product dp ");  
		sql.append(" WHERE dp.doctorId IS NOT NULL ");
		sql.append("  GROUP BY openDoctorId,mon) openTemp ");
		sql.append(" ON d.id = openTemp.openDoctorId ");
		sql.append(" INNER JOIN doctor_product dp ON d.id=dp.doctorId ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND dp.isProvide = 'N'     ");//医生服务时间未占用
		sql.append("  AND dp.serviceDate = '"+orderInfo.getBespokeDate()+"' ");//上门日期
		String bespokeTime = orderInfo.getBespokeTime();
		String startTime = bespokeTime.split(":00")[0];
		String endTime = bespokeTime.split(":00")[2];
		sql.append("  AND dp.serviceTimes IN("+startTime+","+endTime+") ");//上门时间
		sql.append("  AND d.authentication = '已通过' ");//医生认证状态
		sql.append(" AND d.serviceMode IN('上门儿保','全部') ");//服务方式
		Long productId = orderInfo.getProductId();
		sql.append(" AND (d.productIds LIKE '"+productId+"::%' OR d.productIds LIKE '%::"+productId+"::%' "
				+ "OR d.productIds LIKE '%::"+productId+"') ");//产品id
		//上面动态参数都从订单里取
		List<DoctorInfoForSort>  doctorInfoForSortList = this.sendDoctorDao.getDoctorInfoForSortList(sql.toString());
		//添加排序字段,考虑如何动态
		List<RulesConfigureRecord> rcrList = sendDoctorDao.getRulesConfigureRecordList(null);
		if(doctorInfoForSortList == null || rcrList == null){
			//给运营发短信提示（没有匹配医生或没有配置规则）
			SendSms send = new SendSms();
			send.sendInfo("13541280713", "没有匹配医生或没有配置规则");
			return null;
		}
		//设置距离
		this.setDistance(orderInfo, doctorInfoForSortList);
		//按规则排序
		//this.sortDoctor(doctorInfoForSortList);
		SortListUtil<DoctorInfoForSort> sortList = new SortListUtil<DoctorInfoForSort>(); 
		//进行排序
//        sortList.Sort(doctorInfoForSortList, new String[]{"getOrderSum","getOpenTimeSum"}, "");
		sortList.Sort(doctorInfoForSortList,this.getArray(rcrList),null);
        //排序后取第一个元素
		Long doctorId = doctorInfoForSortList.get(0).getDoctorId();
		//更新订单信息
		orderInfo.setDoctorId(doctorId);
		this.sendDoctorDao.updateOrderInfo(orderInfo);
		return this.doctorInfoDao.getDoctorInfoById(doctorId);
	}
	/**
	 * 得到排序属性数组
	 * @param list
	 * @return
	 */
	private String[] getArray(List<RulesConfigureRecord> list){
		List arr = new ArrayList();
		for(RulesConfigureRecord rcr : list){
			String getMethed = rcr.getRulesFieldBasic().getGetMethodName();
			arr.add(getMethed);
		}
		return (String[]) arr.toArray();
	}
	/**
	 * 设置医生与用户间距离
	 * @param orderInfo
	 * @param doctorInfoForSortList
	 */
	
	private void setDistance(OrderInfo orderInfo,List<DoctorInfoForSort>  doctorInfoForSortList ){
		UserInfo user = this.userInfoDao.getUserInfoById(orderInfo.getUserId());
		DoctorAddress doctorAddressUsed = null;
		double lng1 = Double.valueOf(user.getUserLng());
		double lat1 = Double.valueOf(user.getUserLat());
		for(DoctorInfoForSort doctorSort:doctorInfoForSortList){
			List<DoctorAddress> doctorAddressList = this.doctorAddressDao.getDoctorAddressById(doctorSort.getDoctorId());
			if(doctorAddressList != null){
				for(DoctorAddress doctorAddress : doctorAddressList){
					if("Y".equals(doctorAddress.getAddressStatus())){
						doctorAddressUsed = doctorAddress;
						break;
					}
				}
				doctorAddressUsed = null;
			}
			double lng2 = Double.valueOf(doctorAddressUsed.getDoctorLng());
			double lat2 = Double.valueOf(doctorAddressUsed.getDoctorLat());
			double distance = GetDistance.GetDistanceMethod(lng1, lat1, lng2, lat2);
			doctorSort.setDistance(distance);
		}
	}
	/**
	 * 对医生进行规则排序（写死的，暂时不用）
	 * @param doctorInfoForSortList
	 */
	private void sortDoctor(List<DoctorInfoForSort> doctorInfoForSortList ){
		 Comparator<DoctorInfoForSort> comparator = new Comparator<DoctorInfoForSort>() {
	            public int compare(DoctorInfoForSort s1, DoctorInfoForSort s2) {
	            	//按距离排
	            	if(s1.getDistance().doubleValue() != s2.getDistance().doubleValue()){
	            		 return s1.getDistance().compareTo(s2.getDistance());
	            	}
	                //排订单量
	            	else if (s1.getOrderSum().longValue() != s2.getOrderSum().longValue()) {
	                    return s1.getOrderSum().intValue() - s2.getOrderSum().intValue();
	                } else if (s1.getOpenTimeSum() != s2.getOpenTimeSum()) {
	                    // 月订单量相同则按月开放时间排序
	                    return s1.getOpenTimeSum().compareTo(s2.getOpenTimeSum());
	                } else {
	                    // 开放时间也相同则按doctorId排序
	                    return s1.getDoctorId().intValue() - s2.getDoctorId().intValue();
	                }
	            }
	        };
	        Collections.sort(doctorInfoForSortList,comparator);	
	}
	/**
	 * 测试main方法
	 * @param args
	 */
	public static void main(String[] args) {
		 Comparator<DoctorInfoForSort> comparator = new Comparator<DoctorInfoForSort>() {
	            public int compare(DoctorInfoForSort s1, DoctorInfoForSort s2) {
	                //先排订单量
	                if (s1.getOrderSum().longValue() != s2.getOrderSum().longValue()) {
	                    return s1.getOrderSum().intValue() - s2.getOrderSum().intValue();
	                } else if (s1.getOpenTimeSum() != s2.getOpenTimeSum()) {
	                    // 月订单量相同则按月开放时间排序
	                    return s1.getOpenTimeSum().compareTo(s2.getOpenTimeSum());
	                } else {
	                    // 开放时间也相同则按doctorId排序
	                    return s1.getDoctorId().intValue() - s2.getDoctorId().intValue();
	                }
	            }
	        };
	     DoctorInfoForSort stu1 = new DoctorInfoForSort (1L,56L,28l,33d);
         DoctorInfoForSort stu2 = new DoctorInfoForSort (2L,5L,19l,33d);
         DoctorInfoForSort stu3 = new DoctorInfoForSort (3l,56l,10l,37d);
         DoctorInfoForSort stu4 = new DoctorInfoForSort (4l,79l,19l,8d);
         DoctorInfoForSort stu5 = new DoctorInfoForSort (5l,78l,33l,39d);

          ArrayList<DoctorInfoForSort> list = new ArrayList<DoctorInfoForSort>();
          list.add(stu1);
          list.add(stu2);
          list.add(stu3);
          list.add(stu4);
          list.add(stu5); 
          for(DoctorInfoForSort stu : list){
        	  stu.setDoctorId(stu.getDoctorId()==1L?2222L:stu.getDoctorId());
          }
          //定义的规则
          List<String> ruleList = new ArrayList<String>();
          ruleList.add("getOrderSum");
          ruleList.add("getOpenTimeSum");
          //这里就会自动根据规则进行排序
//          Collections.sort(list,comparator);
          SortListUtil<DoctorInfoForSort> sortList = new SortListUtil<DoctorInfoForSort>(); 
         // for(String method : ruleList){
        	  sortList.Sort(list, new String[]{"getOrderSum","getOpenTimeSum"}, "");
         // }
          for(int i=0;i<list.size();i++){
              DoctorInfoForSort stu=list.get(i);
              System.out.println("订单量:"+stu.getOrderSum()+"   开放量:"+stu.getOpenTimeSum()+"   id:"+stu.getDoctorId());
          }

    }

	@Override
	public List<RulesFieldBasic> getRulesFieldBasicList(
			RulesFieldBasic rulesFieldBasic) {
		return this.sendDoctorDao.getRulesFieldBasicList(rulesFieldBasic);
	}

	@Override
	public void saveOrUpdateRulesConfigureRecordList(
			List<RulesConfigureRecord> rulesConfigureRecordList,Long ruleBasicId) {
		if(rulesConfigureRecordList == null){
			return;
		}
		RuleBasic ruleBasic = this.sendDoctorDao.getRuleBasicById(ruleBasicId);
		for(RulesConfigureRecord rulesConfigureRecord:rulesConfigureRecordList){
			rulesConfigureRecord.setRuleBasic(ruleBasic);
			this.sendDoctorDao.saveOrUpdateRulesConfigureRecord(rulesConfigureRecord);
		}
	}

	@Override
	public List<RulesConfigureRecord> getRulesConfigureRecordListByRuleBasicId(
			RuleBasic ruleBasic) {
		return this.sendDoctorDao.getRulesConfigureRecordList(ruleBasic);
	}


	
}
