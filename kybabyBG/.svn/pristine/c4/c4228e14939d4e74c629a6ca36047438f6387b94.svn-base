package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.DoctorProduct;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.RecommentAwardRecord;

public interface DoctorInfoBo {

	//2.5.1 运营管理
	long getNumofDoctor(String startDate,String endDate);//按起止日期统计注册的医生数
	
	//2.7.1 查看医生
	List getAllBriefDoctorInfo();//获取所有医生的简要信息
	List getDetailDoctorInfoById(long id); //获取某个医生的详细信息
	
	List newGetDetailDoctorInfoById(long id);
	
	DoctorInfo getDoctorInfoById(long id);
	List getAllDoctor(); //返回所有医生详细信息
	//2.7.2查看医生计划
	 List getAllBriefInfoOfDoctorPlan();//获取医生的简要信息(在医生计划页面)
	
	//2.9.1订单管理
	List getDoctorIdAndName(long updateOrderId);//得到所有的认证状态通过的，状态为Y的医生,选择id和名字和电话号码

	List<Long> getBigDoctorList(String serviceDate, Long serviceTimes, Long hour);

	List getOneDoctorSomeDayInfo(Long a, String serviceDate, Long serviceTimes,
			Long hour);

	List getOneDoctorSomeDayInfoIdAndStatus(Long oldDoctorId,String serviceDate, Long serviceTimes, Long hour);

	DoctorProduct getDoctorProductById(Long someId);

	// 获取距离
	Double doctorDistanceList(Long userId, Long doctorId);
	List getDoctorLngLatById(long doctorId);
	List getUserLngLatById(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某个医生的被推荐的，注册后发放的，还没有发放的奖励
	 * @data: 2015年11月10日下午4:04:25
	 * @param recommendType  奖励的类型，在这里只是为“医生推荐医生”，用变量只是为了后期的扩展
	 * @param beenRecommendDoctorId 被推荐医生的ID 审核通过的医生ID
	 * @param isGrant 奖励是否发放 “N”
	 * @param whenToGrant 发放的时机 在这里只是指“注册后”
	 * @return 推荐奖励记录的实例
	 */
	RecommentAwardRecord getSomeAwardRecord(String recommendType,long beenRecommendDoctorId,String isGrant,String whenToGrant);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加一条新的医生账户变动记录
	 * @data: 2015年9月21日下午2:58:57
	 * @param doctorId 医生的ID
	 * @param amount 金额
	 * @param type 变动的类型
	 * @param accountDesc 变动的描述
	 */
	void addNewDoctorAccount(long doctorId,double amount,String type,String accountDesc);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加一条新的医生积分消费记录
	 * @data: 2015年9月14日下午11:50:23
	 * @param doctorId 医生的ID
	 * @param points 消费积分数
	 * @param type 消费的类型
	 * @param pointsDes 消费的描述
	 */
	void addNewUserPoints(long doctorId,long points,String type,String pointsDes);
	/**
	 * 根据订单信息更新医生服务时间可用状态
	 * @param orderInfo
	 */
	void updateDoctorProductByOrder(OrderInfo orderInfo);
}
