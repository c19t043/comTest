package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.CaseClipBo;
import com.kybaby.dao.CaseClipDao;

public class CaseClipBoImpl implements CaseClipBo{

	CaseClipDao caseClipDao;
	@Override
	public long getCurrentMonthCaseClipNum() {
		// TODO Auto-generated method stub
		return caseClipDao.getCurrentMonthCaseClipNum();
	}

	@Override
	public long getAllCaseClip() {
		// TODO Auto-generated method stub
		return caseClipDao.getAllCaseClip();
	}

	@Override
	public List getBaByCaseClipByUserId(long userId) {
		// TODO Auto-generated method stub
		return caseClipDao.getBaByCaseClipByUserId(userId);
	}

	public CaseClipDao getCaseClipDao() {
		return caseClipDao;
	}

	public void setCaseClipDao(CaseClipDao caseClipDao) {
		this.caseClipDao = caseClipDao;
	}

//	//2.1控制台
//	long getCurrentMonthCaseClipNum(); //得到当月病历夹量
//	long getAllCaseClip();             //得到所有的病历夹
//	
//	//2.5.2  健康档案管理
//	List getBaByCaseClipByUserId(long userId);//通过用户Id得到用户上传的病历夹，按时间逆序排
}
