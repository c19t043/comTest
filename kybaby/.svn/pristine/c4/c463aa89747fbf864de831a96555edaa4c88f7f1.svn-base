package com.kybaby.newbussiness.senddoctor.dao;

import java.util.List;






import com.kybaby.domain.OrderInfo;
import com.kybaby.newbussiness.senddoctor.domain.RuleBasic;
import com.kybaby.newbussiness.senddoctor.domain.RulesConfigureRecord;
import com.kybaby.newbussiness.senddoctor.domain.RulesFieldBasic;
import com.kybaby.newbussiness.senddoctor.fo.DoctorInfoForSort;

public interface SendDoctorDao {
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
	List<RulesConfigureRecord> getRulesConfigureRecordList();
	/**
	 * 更新订单信息
	 * @param orderInfo
	 */
	void updateOrderInfo(OrderInfo orderInfo);
}
