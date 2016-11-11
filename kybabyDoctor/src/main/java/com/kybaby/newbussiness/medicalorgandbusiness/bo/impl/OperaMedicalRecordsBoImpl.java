package com.kybaby.newbussiness.medicalorgandbusiness.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.medicalorgandbusiness.bo.OperaMedicalRecordsBo;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.OperaMedicalRecordsDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OperaMedicalRecords;

public class OperaMedicalRecordsBoImpl implements OperaMedicalRecordsBo{
	private OperaMedicalRecordsDao operaMedicalRecordsDao;

	public OperaMedicalRecordsDao getOperaMedicalRecordsDao() {
		return operaMedicalRecordsDao;
	}

	public void setOperaMedicalRecordsDao(
			OperaMedicalRecordsDao operaMedicalRecordsDao) {
		this.operaMedicalRecordsDao = operaMedicalRecordsDao;
	}

	@Override
	public List<OperaMedicalRecords> getOperaMedicalRecordsList(
			OperaMedicalRecords operaMedicalRecords) {
		return operaMedicalRecordsDao.getOperaMedicalRecordsList(operaMedicalRecords);
	}

	@Override
	public void saveOrUpdateOperaMedicalRecords(
			OperaMedicalRecords operaMedicalRecords) {
		operaMedicalRecordsDao.saveOrUpdateOperaMedicalRecords(operaMedicalRecords);
	}

	@Override
	public OperaMedicalRecords getOperaMedicalRecordsById(Long id) {
		return operaMedicalRecordsDao.getOperaMedicalRecordsById(id);
	}
}
