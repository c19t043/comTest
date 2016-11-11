package com.kybaby.newbussiness.doctoroperateflow.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.doctoroperateflow.dao.OperationFlowDao;
import com.kybaby.newbussiness.doctoroperateflow.domain.OperationFlowBasicInfo;
import com.kybaby.newbussiness.doctoroperateflow.domain.OperationFlowNode;

public class OperationFlowDaoImpl extends HibernateDaoSupport implements OperationFlowDao {

	@Override
	public List<OperationFlowBasicInfo> getOperationFlowBasicInfoList(
			OperationFlowBasicInfo operationFlowBasicInfo) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer("from OperationFlowBasicInfo where 1=1");
		if(operationFlowBasicInfo != null){
			if(operationFlowBasicInfo.getIsStart() != null && !"".equals(operationFlowBasicInfo.getIsStart().trim())){
				params.add(operationFlowBasicInfo.getIsStart().trim());
				hql.append(" and isStart=?");
			}
		}
		List<OperationFlowBasicInfo> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<OperationFlowNode> getOperationFlowNodeListByBasicId(
			OperationFlowBasicInfo basic,OperationFlowNode node) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer("from OperationFlowNode p where 1=1");
		if(basic != null){
			if(basic.getFlowBasicId() != null){
				hql.append(" and p.flowBasicInfo.flowBasicId=?");
				params.add(basic.getFlowBasicId());
			}
		}
		if(node != null){
			if(node.getIsStart() != null && !"".equals(node.getIsStart().trim())){
				hql.append(" and p.isStart=?");
				params.add(node.getIsStart().trim());
			}
		}
		hql.append(" order by p.flowBasicInfo.flowBasicId,p.sort");
		List<OperationFlowNode> list = this.getHibernateTemplate().find(hql.toString(),params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long saveOrUpdateOperationFlowBasicInfo(
			OperationFlowBasicInfo operationFlowBasicInfo) {
		Long id = null;
		if(operationFlowBasicInfo == null) return null;
		if(operationFlowBasicInfo.getFlowBasicId() == null){
			id = (Long) this.getHibernateTemplate().save(operationFlowBasicInfo);
		}else{
			id = operationFlowBasicInfo.getFlowBasicId();
			OperationFlowBasicInfo old = this.getHibernateTemplate().get(OperationFlowBasicInfo.class, id);
			BeanUtils.copyProperties(operationFlowBasicInfo, old, new String[]{"flowBasicId","createTime"});
			this.getHibernateTemplate().update(old);
		}
		return id;
	}

	@Override
	public Long saveOrUpdateOperationFlowNode(
			OperationFlowNode operationFlowNode) {
		Long id = null;
		if(operationFlowNode == null) return null;
		if(operationFlowNode.getFlowNodeId() == null){
			id = (Long) this.getHibernateTemplate().save(operationFlowNode);
		}else{
			id = operationFlowNode.getFlowNodeId();
			OperationFlowNode old = this.getHibernateTemplate().get(OperationFlowNode.class, id);
			BeanUtils.copyProperties(operationFlowNode, old, new String[]{"flowNodeId","flowBasicInfo"});
			this.getHibernateTemplate().update(old);
		}
		return id;
	}

}
