package com.kybaby.newbussiness.detailrecord.bo.impl;

import java.util.List;

import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.RecommentAwardRecord;
import com.kybaby.newbussiness.detailrecord.bo.DetailRecordService;
import com.kybaby.newbussiness.detailrecord.dao.DetailRecordDao;

public class DetailRecordServiceImpl implements DetailRecordService {
	private DetailRecordDao detailRecordDao;

	public DetailRecordDao getDetailRecordDao() {
		return detailRecordDao;
	}

	public void setDetailRecordDao(DetailRecordDao detailRecordDao) {
		this.detailRecordDao = detailRecordDao;
	}

	@Override
	public List<RecommentAwardRecord> getAllRecommentAwardRecord(
			RecommentAwardRecord recommentAwardRecord) {
		return detailRecordDao.getAllRecommentAwardRecord(recommentAwardRecord);
	}

	@Override
	public List<DoctorAccount> getAllDoctorAccountList(
			DoctorAccount doctorAccount) {
		return detailRecordDao.getAllDoctorAccountList(doctorAccount);
	}
}
