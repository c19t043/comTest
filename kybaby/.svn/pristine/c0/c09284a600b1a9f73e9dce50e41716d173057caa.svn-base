package com.kybaby.bo.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kybaby.bo.DoctorAccountBo;
import com.kybaby.dao.DoctorAccountDao;
import com.kybaby.domain.DoctorAccount;

/**
 * @ClassName:DoctorAccountBoImpl
 * @Description:医生账户变动事物管理接口实现
 * @author Hoolee
 * @date 2015年9月28日上午11:25:21
 */
public class DoctorAccountBoImpl implements DoctorAccountBo {
	
	DoctorAccountDao doctorAccountDao;
	
	public void addNewDoctorAccount(long doctorId, double amount, String type,
			String accountDesc) {
		DoctorAccount doctorAccount=new DoctorAccount();
		doctorAccount.setDoctorId(doctorId);
		doctorAccount.setAmount(amount);
		doctorAccount.setType(type);
		doctorAccount.setAccountDesc(accountDesc);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date rightNow=new Date(System.currentTimeMillis());
		String submitTime=sdf.format(rightNow);
		doctorAccount.setUpdateTime(submitTime);
		doctorAccountDao.addNewDoctorAccount(doctorAccount);
	}

	public DoctorAccountDao getDoctorAccountDao() {
		return doctorAccountDao;
	}

	public void setDoctorAccountDao(DoctorAccountDao doctorAccountDao) {
		this.doctorAccountDao = doctorAccountDao;
	}

}
