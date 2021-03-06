package com.kybaby.newbussiness.ordermanager.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.ItemResult;
import com.kybaby.domain.OrderResults;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.ordermanager.dao.OperationFlowDao;
import com.kybaby.newbussiness.ordermanager.domain.OperationFlowBasicInfo;
import com.kybaby.newbussiness.ordermanager.domain.OperationFlowNode;
import com.kybaby.newbussiness.ordermanager.domain.OrderNodeTrack;
import com.kybaby.util.CalculationMethod;


public class OperationFlowDaoImpl extends HibernateDaoSupport implements OperationFlowDao {

	@Override
	public List<OperationFlowBasicInfo> getOperationFlowBasicInfoList(
			OperationFlowBasicInfo operationFlowBasicInfo) {
		StringBuffer hql = new StringBuffer("from OperationFlowBasicInfo where 1=1");
		if(operationFlowBasicInfo != null){
			
		}
		List<OperationFlowBasicInfo> list = this.getHibernateTemplate().find(hql.toString());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<OperationFlowNode> getOperationFlowNodeListByBasicId(
			Long basicId) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer("from OperationFlowNode p where 1=1 and p.isStart='Y' and p.flowBasicInfo.isStart='Y'");
		if(basicId != null){
			hql.append(" and p.flowBasicInfo.flowBasicId=?");
			params.add(basicId);
		}
		hql.append(" order by p.sort");
		List<OperationFlowNode> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<OrderNodeTrack> getOrderNodeTrackListByOrderId(Long orderId) {
		StringBuffer hql = new StringBuffer("from OrderNodeTrack p where 1=1 and p.orderInfoId=")
		.append(orderId).append(" order by p.createTime desc");
		List<OrderNodeTrack> list = this.getHibernateTemplate().find(hql.toString());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<OrderResults> getOrderResultByOrderNumAndNodeId(String orderNum,
			Long flowNodeId) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer("select a")
		.append(" FROM OrderResults a")
		.append("  , ProductItem b")
		.append(" , OperationFlowNode c")
		.append(" WHERE 1=1 and a.itemId = b.id and b.flowNodeId = c.flowNodeId");
		if(orderNum != null && !"".equals(orderNum.trim())){
			hql.append(" and a.orderNum = ?");
			params.add(orderNum.trim());
		}
		if(flowNodeId != null){
			hql.append(" AND c.flowNodeId = ?");
			params.add(flowNodeId);
		}
		
		List<OrderResults> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<ItemResult> getItemResultByProductIdAndNodeId(
			Long flowNodeId,UserInfo userInfo) {
		int monthAge = 0;
		String date1 = userInfo.getBirthday();
		String date2 = DateManage.getDateStr("yyyy-MM-dd");
		try {
			monthAge = CalculationMethod.getMonthSpace(date1, date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer("select a")
		.append(" FROM ItemResult a")
		.append("  , ProductItem b")
		.append(" , OperationFlowNode c")
		.append(" WHERE 1=1 and a.id = b.itemResultId ")
		.append(" and b.flowNodeId = c.flowNodeId ")
		.append(" and (b.whatFitForSex=? or b.whatFitForSex='全部') ")
		.append(" and b.itemStatus='Y' ")
		.append(" and b.whatFitForMonth<=? and b.whatFitForMonth_max>=? ");
		params.add(userInfo.getSex());
		params.add(Long.valueOf(monthAge));
		params.add(Long.valueOf(monthAge));
		if(flowNodeId != null){
			hql.append(" AND c.flowNodeId = ?");
			params.add(flowNodeId);
		}
		
		List<ItemResult> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	

}
