package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.UserAccount;

/**
 * 
 * @ClassName:UserAccountBo
 * @Description:用户账户记录的事物管理
 * @author Hoolee
 * @date 2015年9月14日下午11:47:18
 */
public interface UserAccountBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:添加一条新的用户余额消费记录 
	 * @data: 2015年9月14日下午11:47:55
	 * @param userId 用户的ID
	 * @param amount 消费的金额
	 * @param type 消费类型
	 * @param accountDesc 消费描述
	 * @param orderNum 消费的订单号
	 */
	void addNewUserAccount(long userId,double amount,String type,String accountDesc,String orderNum);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某个用户的账户余额变动记录列表（需要按照时间进行逆序排序）
	 * @data: 2015年9月23日上午11:59:47
	 * @param userId 用户的ID
	 * @return 用户的账户余额变动记录
	 */
	List<UserAccount> getSomeUserAccountDetail(long userId);
}
