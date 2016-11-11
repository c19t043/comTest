package com.kybaby.bo;

import com.kybaby.domain.Subsidy;

/**
 * @ClassName:SubsidyBo
 * @Description:补贴规则的事务管理接口
 * @author Hoolee
 * @date 2015年9月21日下午3:04:19
 */
public interface SubsidyBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取在下单日期的有效的交通补贴金额
	 * @data: 2015年9月21日下午3:08:00
	 * @param strDate 下单的日期
	 * @return 交通补贴金额
	 */
	double getSomeTrafficInstance(String strDate);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取在下单日期的有效的活跃区补贴金额
	 * @data: 2015年9月21日下午3:21:43
	 * @param doctorId 医生ID
	 * @param strDate 下单的日期
	 * @return 活跃度补贴规则
	 */
	double getSomeActivityInstance(long doctorId,String strDate);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取当前日期中有效的交通补贴规则中补贴的最大金额
	 * @data: 2015年10月9日上午9:46:55
	 * @return 当前日期有效的最大交通补贴金额
	 */
	double getMaxAmountTracficAmount();
}
