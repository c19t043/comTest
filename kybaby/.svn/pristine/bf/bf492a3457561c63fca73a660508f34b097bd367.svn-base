package com.kybaby.bo.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.kybaby.bo.SubsidyBo;
import com.kybaby.dao.SubsidyDao;

/**
 * @ClassName:SubsidyBoImpl
 * @Description:补贴规则事物管理接口实现
 * @author Hoolee
 * @date 2015年10月9日上午9:48:31
 */
public class SubsidyBoImpl  implements SubsidyBo {

	SubsidyDao subsidyDao;
	
	public double getSomeTrafficInstance(String strDate) {
		return 0;
	}

	public double getSomeActivityInstance(long doctorId, String strDate) {
		return 0;
	}

	public double getMaxAmountTracficAmount() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date rightNow=new Date(System.currentTimeMillis());
		String dateNow=sdf.format(rightNow);
		return subsidyDao.getMaxAmountTracficAmount("交通补贴", dateNow);
	}

	public SubsidyDao getSubsidyDao() {
		return subsidyDao;
	}

	public void setSubsidyDao(SubsidyDao subsidyDao) {
		this.subsidyDao = subsidyDao;
	}

}
