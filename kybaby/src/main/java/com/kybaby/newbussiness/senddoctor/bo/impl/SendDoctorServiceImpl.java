package com.kybaby.newbussiness.senddoctor.bo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.kybaby.bo.DoctorInfoBo;
import com.kybaby.dao.DoctorInfoDao;
import com.kybaby.dao.UserInfoDao;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.User;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.senddoctor.bo.SendDoctorService;
import com.kybaby.newbussiness.senddoctor.dao.SendDoctorDao;
import com.kybaby.newbussiness.senddoctor.domain.RulesConfigureRecord;
import com.kybaby.newbussiness.senddoctor.fo.DoctorInfoForSort;
import com.kybaby.util.SendSms;
import com.kybaby.util.SortListUtil;

public class SendDoctorServiceImpl implements SendDoctorService{
	private SendDoctorDao sendDoctorDao;
	private DoctorInfoDao doctorInfoDao;
	private DoctorInfoBo doctorInfoBo;
	private UserInfoDao userInfoDao;


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

	public DoctorInfoBo getDoctorInfoBo() {
		return doctorInfoBo;
	}

	public void setDoctorInfoBo(DoctorInfoBo doctorInfoBo) {
		this.doctorInfoBo = doctorInfoBo;
	}

	public SendDoctorDao getSendDoctorDao() {
		return sendDoctorDao;
	}

	public void setSendDoctorDao(SendDoctorDao sendDoctorDao) {
		this.sendDoctorDao = sendDoctorDao;
	}


	@Override
	@SuppressWarnings("all")
	public DoctorInfo autoSendDoctorByRule(OrderInfo orderInfo) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT DISTINCT   d.id, (CASE WHEN orderTemp.orderSum IS NULL THEN 0 ELSE orderTemp.orderSum END) orderSum, openTemp.openSum ");
		sql.append(" FROM doctor_info d ");
		sql.append(" left JOIN (SELECT ");
		sql.append(" o.doctorId          AS orderDoctorId, ");
		sql.append(" DATE_FORMAT(NOW(),'%Y-%m') AS mon, ");
		sql.append("  COUNT(1)         AS orderSum ");
		sql.append(" FROM order_info o ");
		sql.append("  WHERE o.doctorId IS NOT NULL ");
		sql.append("  AND o.orderStatus NOT IN('医生取消','用户取消','已接单') ");//订单状态
		sql.append(" GROUP BY orderDoctorId,mon) orderTemp ");
		sql.append(" ON d.id = orderTemp.orderDoctorId ");
		sql.append(" left JOIN (SELECT");
		sql.append(" dp.doctorId         AS openDoctorId, ");
		sql.append("  DATE_FORMAT(NOW(),'%Y-%m') AS mon, ");
		sql.append(" COUNT(1)        AS openSum "); 
		sql.append("  FROM doctor_product dp ");  
		sql.append(" WHERE dp.doctorId IS NOT NULL ");
		sql.append("  GROUP BY openDoctorId,mon) openTemp ");
		sql.append(" ON d.id = openTemp.openDoctorId ");
		sql.append(" left JOIN doctor_product dp ON d.id=dp.doctorId ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND dp.isProvide = 'N'     ");//医生服务时间未占用
		sql.append("  AND dp.serviceDate = '"+orderInfo.getBespokeDate()+"' ");//上门日期
		String bespokeTime = orderInfo.getBespokeTime();
//		String startTime = bespokeTime.split(":00")[0];
//		String endTime = bespokeTime.split(":00")[2];
		sql.append("  AND dp.serviceTimes IN ").append(bespokeTime);//上门时间
		sql.append("  AND d.authentication = '已通过' ");//医生认证状态
		sql.append("  AND d.doctorStatus='Y' ");//医生状态(Y/N)
		sql.append(" AND d.serviceMode IN('上门服务','全部') ");//服务方式
		Long productId = orderInfo.getProductId();
		sql.append(" AND (d.productIds LIKE '"+productId+"::%' OR d.productIds LIKE '%::"+productId+"::%' "
				+ "OR d.productIds LIKE '%::"+productId+"') ");//产品id
//		sql.append(" AND openTemp.mon=DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH),'%Y-%m')")//时间开放取上个月
//		.append(" AND orderTemp.mon=DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH),'%Y-%m')");//订单取上个月
		//上面动态参数都从订单里取
		List<DoctorInfoForSort>  doctorInfoForSortList = this.sendDoctorDao.getDoctorInfoForSortList(sql.toString());
		//添加排序字段,考虑如何动态
		List<RulesConfigureRecord> rcrList = sendDoctorDao.getRulesConfigureRecordList();
		if(doctorInfoForSortList == null || rcrList == null){
//			//给运营发短信提示（没有匹配医生或没有配置规则）
//			SendSms send = new SendSms();
//			send.sendInfo("13541280713", "没有匹配医生或没有配置规则，请及时处理。");
//			//给客户发短信
//			UserInfo user = this.userInfoDao.getUserById(orderInfo.getUserId());
//			send.sendInfo(user.getPhone(), "平台正在为您匹配合适的医生，请耐心等待");
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
		//orderInfo.setDoctorId(doctorId);
		//this.sendDoctorDao.updateOrderInfo(orderInfo);
		return this.doctorInfoDao.getDoctorInfoByDoctorId(doctorId);
	}
	/**
	 * 得到排序属性数组
	 * @param list
	 * @return
	 */
	private String[] getArray(List<RulesConfigureRecord> list){
		String[] strArr = new String[list.size()];
		for(int i=0;i<list.size();i++){
			String getMethed = list.get(i).getRulesFieldBasic().getGetMethodName();
			strArr[i] = getMethed;
		}
		return  strArr;
	}
	/**
	 * 设置医生与用户间距离
	 * @param orderInfo
	 * @param doctorInfoForSortList
	 */
	
	private void setDistance(OrderInfo orderInfo,List<DoctorInfoForSort>  doctorInfoForSortList ){
		for(DoctorInfoForSort doctorSort:doctorInfoForSortList){
			double distance = this.doctorInfoBo.doctorDistanceList(doctorSort.getDoctorId(), orderInfo.getUserId());
			if(distance>0 && distance<=5){
				doctorSort.setDistance(5D);
			}else if(distance>5 && distance<=10){
				doctorSort.setDistance(10D);
			}else if(distance>10 && distance<=15){
				doctorSort.setDistance(15D);
			}else{
				doctorSort.setDistance(20D);
			}
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


	
}
