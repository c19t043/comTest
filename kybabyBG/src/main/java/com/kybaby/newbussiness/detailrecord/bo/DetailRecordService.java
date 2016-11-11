package com.kybaby.newbussiness.detailrecord.bo;

import java.util.List;

import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.RecommentAwardRecord;

public interface DetailRecordService {
	/**
	 * 得到推荐记录信息列表
	 * @param recommentAwardRecord
	 * @return
	 */
	List<RecommentAwardRecord> getAllRecommentAwardRecord(RecommentAwardRecord recommentAwardRecord);
	/**
	 * 医生账户记录列表
	 * @param doctorAccount
	 * @return
	 */
	List<DoctorAccount> getAllDoctorAccountList(DoctorAccount doctorAccount);
}
