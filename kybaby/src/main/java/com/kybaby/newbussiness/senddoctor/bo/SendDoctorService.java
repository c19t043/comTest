package com.kybaby.newbussiness.senddoctor.bo;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.OrderInfo;
import com.kybaby.newbussiness.senddoctor.domain.RuleBasic;
import com.kybaby.newbussiness.senddoctor.domain.RulesConfigureRecord;
import com.kybaby.newbussiness.senddoctor.domain.RulesFieldBasic;

public interface SendDoctorService {
	/**
	 * 根据配置规则得到医生，没有医生给运营发短信
	 * @param orderInfo
	 */
	DoctorInfo autoSendDoctorByRule(OrderInfo orderInfo);
}
