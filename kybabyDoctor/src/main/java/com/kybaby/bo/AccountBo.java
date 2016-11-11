package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.DoctorPoints;
import com.kybaby.domain.DoctorWithdrawals;
import com.kybaby.domain.RecommendRule;
import com.kybaby.domain.RecommentAwardRecord;

/**
 * @author Administrator
 *
 */
public interface AccountBo {

	//获取医生余额
	Double getDoctorBalanceByDoctorId();
	//获取医生余额明细
	List<DoctorAccount> getAmountDetailByDoctorId(Long id);
	//获取医生积分明细
	List<DoctorPoints> getPointDetailByDoctorId(Long id);
	//更新医生信息表
	void updateDoctorInfo(DoctorInfo doctorInfo);
	//新增一条医生积分明细记录
	void saveDoctorPoint(DoctorPoints doctorPoints);
	//新增一条医生余额明细记录
	void saveDoctorAccount(DoctorAccount doctorAccount);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过推荐规则的名称获取到推荐规则的实例
	 * @data: 2015年11月10日14:27:14
	 * @param ruleName 推荐规则名称
	 * @return 推荐规则的实例
	 */
	RecommendRule getSomeCanUseRule(String ruleName);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加新的奖励记录 
	 * @data: 2015年11月10日下午2:55:24
	 * @param recommentAwardRecord 需要增加的奖励记录实例
	 */
	void saveRecommentAwardRecord(RecommentAwardRecord recommentAwardRecord);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:
	 * @data: 2015年11月20日下午6:55:09
	 * @param doctorWithDraw
	 */
	void saveDoctorWithdrawals(DoctorWithdrawals doctorWithDraw);
	/**
	 * 得到上个月的余额总金额
	 * @return
	 */
	Double getAccountNowMonthByDoctorId(Long doctorId);
}
