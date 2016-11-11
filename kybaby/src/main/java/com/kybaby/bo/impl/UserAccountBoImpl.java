package com.kybaby.bo.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.kybaby.bo.UserAccountBo;
import com.kybaby.dao.UserAccountDao;
import com.kybaby.domain.UserAccount;

/**
 * @ClassName:UserAccountBoImpl
 * @Description:用户的账户金额变动事物管理实现
 * @author Hoolee
 * @date 2015年9月28日上午11:15:21
 */
public class UserAccountBoImpl implements UserAccountBo {
	
	UserAccountDao userAccountDao;
	
	public void addNewUserAccount(long userId, double amount, String type,
			String accountDesc, String orderNum) {
		UserAccount userAccount=new UserAccount();
		userAccount.setUserId(userId);
		userAccount.setAmount(amount);
		userAccount.setType(type);
		userAccount.setAccountDesc(accountDesc);
		userAccount.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		userAccountDao.addNewUserAccount(userAccount);
	}

	public List<UserAccount> getSomeUserAccountDetail(long userId) {
		return null;
	}

	public UserAccountDao getUserAccountDao() {
		return userAccountDao;
	}

	public void setUserAccountDao(UserAccountDao userAccountDao) {
		this.userAccountDao = userAccountDao;
	}

}
