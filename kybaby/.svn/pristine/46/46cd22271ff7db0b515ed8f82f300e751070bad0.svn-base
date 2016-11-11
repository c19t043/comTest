package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.UserAccountDao;
import com.kybaby.domain.UserAccount;

/**
 * @ClassName:UserAccountDaoImpl
 * @Description:用户账户变动数据管理接口实现
 * @author Hoolee
 * @date 2015年9月28日上午11:17:28
 */
public class UserAccountDaoImpl extends HibernateDaoSupport implements UserAccountDao {

	public void addNewUserAccount(UserAccount userAccount) {
		getHibernateTemplate().save(userAccount);
	}

	public List<UserAccount> getSomeUserAccountDetail(long userId) {
		return null;
	}

}
