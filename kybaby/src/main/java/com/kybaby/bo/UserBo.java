package com.kybaby.bo;

import com.kybaby.domain.User;

public interface UserBo {
	User findUserByUsrName(String userName);
	Boolean userRegist(String userName,String userPassword);
}
