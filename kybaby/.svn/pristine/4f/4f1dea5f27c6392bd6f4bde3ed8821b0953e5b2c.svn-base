package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.UserAccount;

/**
 * @ClassName:UserAccountDao
 * @Description:用户账户变动记录数据管理接口
 * @author Hoolee
 * @date 2015年9月28日上午11:16:38
 */
public interface UserAccountDao {
	/**
	 * 
	 * @author HooLee
	 * @Discription:添加一条新的用户余额消费记录 
	 * @data: 2015年9月28日11:19:04
	 */
	void addNewUserAccount(UserAccount userAccount);
	
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
