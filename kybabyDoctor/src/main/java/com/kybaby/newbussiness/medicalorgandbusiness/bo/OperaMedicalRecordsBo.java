package com.kybaby.newbussiness.medicalorgandbusiness.bo;

import java.util.List;

import com.kybaby.newbussiness.medicalorgandbusiness.domain.OperaMedicalRecords;

/**
 * 就诊记录信息业务处理
 * @author lihao
 *
 */
public interface OperaMedicalRecordsBo {
	/**
	 * 得到就诊登记记录列表
	 * @param operaMedicalRecords
	 * @return
	 */
	List<OperaMedicalRecords> getOperaMedicalRecordsList(OperaMedicalRecords operaMedicalRecords);
	/**
	 * 保存或更新就诊登记记录
	 * @param operaMedicalRecords
	 */
	void saveOrUpdateOperaMedicalRecords(OperaMedicalRecords operaMedicalRecords);
	/**
	 * 得到就诊登记记录根据id
	 * @param id
	 * @return
	 */
	OperaMedicalRecords getOperaMedicalRecordsById(Long id);
}
