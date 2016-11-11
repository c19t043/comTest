package com.kybaby.newbussiness.senddoctor.bo;

import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.OrderInfo;
import com.kybaby.newbussiness.senddoctor.domain.RuleBasic;
import com.kybaby.newbussiness.senddoctor.domain.RulesConfigureRecord;
import com.kybaby.newbussiness.senddoctor.domain.RulesFieldBasic;

public interface SendDoctorService {
	/**
	 * 保存规则信息
	 * @param ruleBasic
	 */
	void saveRule(RuleBasic ruleBasic,List<RulesConfigureRecord> rulesConfigureRecordList);
	/**
	 * 保存规则设置记录
	 * @param rulesConfigureRecordList
	 */
	void saveOrUpdateRulesConfigureRecordList(List<RulesConfigureRecord> rulesConfigureRecordList,Long ruleBasicId);
	/**
	 * 更新规则基本信息
	 * @param ruleBasic
	 */
	void updateRule(RuleBasic ruleBasic,List<RulesConfigureRecord> rulesConfigureRecordList);
	/**
	 * 得到规则基本信息列表
	 * @param ruleBasic
	 */
	List<RuleBasic> getRuleBasicList(RuleBasic ruleBasic);
	/**
	 * 得到基础属性字段列表
	 * @param rulesFieldBasic
	 * @return
	 */
	List<RulesFieldBasic> getRulesFieldBasicList(RulesFieldBasic rulesFieldBasic);
	/**
	 * 根据配置规则得到医生，没有医生给运营发短信
	 * @param orderInfo
	 */
	DoctorInfo autoSendDoctorByRule(OrderInfo orderInfo);
	/**
	 * 根据头信息得到规则字段排序信息
	 * @param ruleBasic
	 * @return
	 */
	List<RulesConfigureRecord> getRulesConfigureRecordListByRuleBasicId(RuleBasic ruleBasic);
}
