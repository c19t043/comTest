package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.AccountBo;
import com.kybaby.dao.AccountDao;
import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.DoctorPoints;
import com.kybaby.domain.DoctorWithdrawals;
import com.kybaby.domain.RecommendRule;
import com.kybaby.domain.RecommentAwardRecord;

/**
 * @author sujiantang
 *
 */
public class AccountBoImpl implements AccountBo{

	private AccountDao accountDao;
	
	@Override
	public Double getDoctorBalanceByDoctorId() {
		return accountDao.getDoctorBalanceByDoctorId();
	}

	
	@Override
	public List<DoctorAccount> getAmountDetailByDoctorId(Long id) {
		return accountDao.getAmountDetailByDoctorId(id);
	}
	
	@Override
	public List<DoctorPoints> getPointDetailByDoctorId(Long id) {
		return accountDao.getPointDetailByDoctorId(id);
	}

	@Override
	public void updateDoctorInfo(DoctorInfo doctorInfo) {
		accountDao.updateDoctorInfo(doctorInfo);
	}

	@Override
	public void saveDoctorPoint(DoctorPoints doctorPoints) {
		accountDao.saveDoctorPoint(doctorPoints);
	}
	
	@Override
	public void saveDoctorAccount(DoctorAccount doctorAccount) {
		accountDao.saveDoctorAccount(doctorAccount);
	}

	@Override
	public RecommendRule getSomeCanUseRule(String ruleName) {
		return accountDao.getSomeCanUseRule(ruleName);
	}

	@Override
	public void saveRecommentAwardRecord(RecommentAwardRecord recommentAwardRecord) {		
		accountDao.saveRecommentAwardRecord(recommentAwardRecord);
	}

	@Override
	public void saveDoctorWithdrawals(DoctorWithdrawals doctorWithDraw) {
		accountDao.saveDoctorWithdrawals(doctorWithDraw);
	}
	
	public AccountDao getAccountDao() {
		return accountDao;
	}


	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}


	@Override
	public Double getAccountNowMonthByDoctorId(Long doctorId) {
		return accountDao.getAccountNowMonthByDoctorId(doctorId);
	}


}
