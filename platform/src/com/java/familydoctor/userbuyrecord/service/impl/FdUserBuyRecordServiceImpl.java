package com.java.familydoctor.userbuyrecord.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.userbuyrecord.service.FdUserBuyRecordService;
import com.java.familydoctor.userbuyrecord.vo.FdUserBuyRecord;
import com.java.platform.user.service.ServiceImpl;

public class FdUserBuyRecordServiceImpl extends ServiceImpl implements FdUserBuyRecordService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		
		return null;
	}

	@Override
	public List<FdUserBuyRecord> getFdUserBuyRecordByPage(PageSortModel psm,FdUserBuyRecord fdUserBuyRecord) {
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM FdUserBuyRecord f where 1=1");
		if(fdUserBuyRecord != null){
			if(fdUserBuyRecord.getFdServiceOrder() != null && 
						fdUserBuyRecord.getFdServiceOrder().getOrderNum().trim() != null && 
							!"".equals(fdUserBuyRecord.getFdServiceOrder().getOrderNum().trim())){
				params.put("orderNum","%" + fdUserBuyRecord.getFdServiceOrder().getOrderNum() + "%");
				hql.append(" AND f.fdServiceOrder.orderNum LIKE :orderNum");
			}
		}
		List<FdUserBuyRecord> list = (List<FdUserBuyRecord>) listForEc(String.valueOf(hql),psm,params);
		return list;
	}

}
