package com.kybaby.bo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.kybaby.bo.OrderInfoBo;
import com.kybaby.dao.DoctorInfoDao;
import com.kybaby.dao.OrderInfoDao;
import com.kybaby.dao.UserInfoDao;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.util.SendSms;

import freemarker.template.SimpleDate;

public class  OrderInfoBoImpl  implements OrderInfoBo {

	OrderInfoDao orderInfoDao;
	
	//医生接口
	DoctorInfoDao doctorInfoDao;
	//用户接口
	UserInfoDao userInfoDao;
	@Override
	public long getCurrentMonthOrderInfoNum() {
		// TODO Auto-generated method stub
		return orderInfoDao.getCurrentMonthOrderInfoNum();
	}

	@Override
	public long getAllOrderInfoNum() {
		// TODO Auto-generated method stub
		return orderInfoDao.getAllOrderInfoNum();
	}

	@Override
	public long getNumOfOrder(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return orderInfoDao.getNumOfOrder(startDate, endDate);
	}

	@Override
	public double getTradeMoney(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return orderInfoDao.getTradeMoney(startDate, endDate);
	}

	@Override
	public long getTotalServiceTimeOfDoctor(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List getAllOrderInfo() {
		// TODO Auto-generated method stub
		return orderInfoDao.getAllOrderInfo();
	}

	@Override
	public List getSomeOrderInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderInfo getOrderInfoById(long id) {
		// TODO Auto-generated method stub
		return orderInfoDao.getOrderInfoById(id);
	}

	public OrderInfoDao getOrderInfoDao() {
		return orderInfoDao;
	}

	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}

	@Override
	public long getActiveUserNum(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return orderInfoDao.getActiveUserNum(startDate, endDate);
	}

	@Override
	public long getActiveDoctorNum(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return orderInfoDao.getActiveDoctorNum(startDate, endDate);
	}

	@Override
	public List getSearchOrderBy(String babyName, String doctorName,String productName, String orderStatus, String startTime,String endTime) {
		// TODO Auto-generated method stub
		return orderInfoDao.getSearchOrderBy(babyName, doctorName, productName, orderStatus, startTime, endTime);
	}

	@Override
	public List<String> getProductName() {
		// TODO Auto-generated method stub
		return orderInfoDao.getProductName();
	}

	@Override
	public void orderPromptTask() {
		System.out.println("====上门定时任务执行。。。。。。");
		StringBuffer contecnt = new StringBuffer("");
		List orderList = orderInfoDao.queryOrderInfoByStatus("'已接单','已签到'");
		if(orderList != null && !orderList.isEmpty()){
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String nowDate = sd.format(date);
			Calendar c = Calendar.getInstance();
			int nowHour = c.get(Calendar.HOUR_OF_DAY);
			int endHour = nowHour + 2;//上门前2小时提醒
			//前一天18点提醒
			int YY = c.get(Calendar.YEAR) ;
			int MM = c.get(Calendar.MONTH)+1;
			int DD = c.get(Calendar.DATE)+1;
			int HH = c.get(Calendar.HOUR);
			String beforeDate = String.valueOf(YY)+"-"+String.valueOf(MM)+"-"+String.valueOf(DD);
			try {
				beforeDate = sd.format(sd.parse(beforeDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			//c.get(Calendar.);
			for(int i=0; i<orderList.size(); i++){
				OrderInfo order = (OrderInfo) orderList.get(i);
				String bespokeTime = order.getBespokeTime();
				String bespokeDate = order.getBespokeDate();
				if("已接单,已签到".indexOf(order.getOrderStatus()) > -1 && order.getDoctorId() != null){
					if(bespokeTime != null && !"".equals(bespokeTime)){
						String bespokeTimeStart = bespokeTime.split(":")[0];
						if((nowDate.equals(bespokeDate) && String.valueOf(endHour).equals(bespokeTimeStart)) 
								|| (beforeDate.equals(bespokeDate) && 18==HH)){//服务前一天18点或服务当天前2个小时
							//短信提醒
							DoctorInfo doctor = this.doctorInfoDao.getDoctorInfoById(order.getDoctorId());
							UserInfo user = this.userInfoDao.getUserInfoById(order.getUserId());
							contecnt.append("亲爱的")
							.append(doctor.getDoctorName()).append("医生，").append(user.getParentName())
							.append("预约您").append(order.getBespokeDate()).append("，").append(bespokeTime)
							.append("点上门服务，请注意查看订单。");
							SendSms ss = new SendSms();
							ss.sendInfo(doctor.getDoctorPhone(), contecnt.toString());
							System.out.println(contecnt);
						}
					}
				}
			}
		}
	}

public static void main(String[] args) {
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	Calendar c=Calendar.getInstance();
	int nowHour = c.get(Calendar.DATE);
	System.out.println(nowHour);
	try {
		System.out.println(sd.format(sd.parse("2015-2-5")));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public DoctorInfoDao getDoctorInfoDao() {
	return doctorInfoDao;
}

public void setDoctorInfoDao(DoctorInfoDao doctorInfoDao) {
	this.doctorInfoDao = doctorInfoDao;
}

public UserInfoDao getUserInfoDao() {
	return userInfoDao;
}

public void setUserInfoDao(UserInfoDao userInfoDao) {
	this.userInfoDao = userInfoDao;
}

//	//2.1控制台
//   long getCurrentMonthOrderInfoNum();// 得到当月的订单量
//   long getAllOrderInfoNum();         //得到全部的订单量
//   
//   //2.5.1 运营管理
//	long getNumOfOrder(String startDate,String endDate);//得到起止日期的订单量
//	double getTradeMoney(String startDate,String endDate);//得到起止日期的交易额
//	long getTotalServiceTimeOfDoctor(String startDate,String endDate);//得到医生服务的总小时数
//	
//	//2.9.1订单管理
//	List getAllOrderInfo();//从订单信息表，产品表，用户表，医生表，选择字段id,订单编号，下单时间，订单金额，产品名字，医生名字，用户名字，订单状态，电话号码
//	List getSomeOrderInfoById(long id);//从订单信息表，项目表，用户表，医生表，选择字段id,订单编号，医生名字，下单时间，用户名字，订单金额，手机号，订单状态，项目Id集合
//	OrderInfo getOrderInfoById(long id);//通过订单id找到次订单实例
	
	

}
