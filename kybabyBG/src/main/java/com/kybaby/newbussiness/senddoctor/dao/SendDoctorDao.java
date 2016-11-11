package com.kybaby.newbussiness.senddoctor.dao;

import java.util.List;






import com.kybaby.domain.OrderInfo;
import com.kybaby.newbussiness.senddoctor.domain.RuleBasic;
import com.kybaby.newbussiness.senddoctor.domain.RulesConfigureRecord;
import com.kybaby.newbussiness.senddoctor.domain.RulesFieldBasic;
import com.kybaby.newbussiness.senddoctor.fo.DoctorInfoForSort;

public interface SendDoctorDao {
	/**
	 * 保存规则基本信息
	 * @param ruleBasic
	 */
	Long saveRuleBasic(RuleBasic ruleBasic);
	/**
	 * 保存或更新规则设置
	 * @param rulesConfigureRecord
	 * @return
	 */
	Long saveOrUpdateRulesConfigureRecord(RulesConfigureRecord rulesConfigureRecord);
	/**
	 * 更新规则基本信息
	 * @param ruleBasic
	 */
	void updateRuleBasic(RuleBasic ruleBasic);
	/**
	 * 得到规则基本信息列表
	 * @param ruleBasic
	 */
	List<RuleBasic> getRuleBasicList(RuleBasic ruleBasic);
	/**
	 * 根据id得到规则基本信息
	 * @param ruleBasicId
	 * @return
	 */
	RuleBasic getRuleBasicById(Long ruleBasicId);
	/**
	 * 得到规则字段基础表
	 * @param rulesFieldBasic
	 * @return
	 */
	List<RulesFieldBasic> getRulesFieldBasicList(RulesFieldBasic rulesFieldBasic);
	/**
	 * 根据提供的sql得到需要排序的医生信息
	 * @param sql
	 * @return
	 */
	List<DoctorInfoForSort> getDoctorInfoForSortList(String sql);
	/**
	 * 得到排好序（运营后台配置）的规则列表
	 * @return
	 */
	List<RulesConfigureRecord> getRulesConfigureRecordList(RuleBasic ruleBasic);
	/**
	 * 更新订单信息
	 * @param orderInfo
	 */
	void updateOrderInfo(OrderInfo orderInfo);
	/**
	 * sql更新规则列表
	 * @param orderInfo
	 */
	void updateRuleBasicBySql(RuleBasic ruleBasic);
}
