package com.kybaby.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.ProductItemDao;
import com.kybaby.domain.ProductItem;

public class ProductItemDaoImpl extends HibernateDaoSupport implements ProductItemDao {

	@Override
	public List  getAllProductItem() {
		Session session=getHibernateTemplate().getSessionFactory().openSession();
		String sqlQueryStr=
 " SELECT " +
 " a.id, " +
 " a.itemName, " +
 " a.whatFitForMonth, " +
 " a.whatFitForSex, " +
 " a.serviceTime, " +
 " d.name, " +
 " b.itemResultName, " +
 " c.healthPlan, " +
 " a.handleUrl ," +
 " a.itemStatus, " +
 " a.comments, " +
 " a.flow_node_id, " +
 " e.flow_node_name, " +
 " a.whatFitForMonth_max " +
" FROM  product_item a " +
 " LEFT JOIN  item_result b " +
  "  ON a.itemResultId = b.id " +
  " LEFT JOIN  health_plan c " +
  "  ON a.healthPlanId = c.id " +
  " LEFT JOIN  operation_flow_node e " +
  "  ON a.flow_node_id = e.flow_node_id " +
  " LEFT JOIN  position d " +
  "  ON a.positionId = d.id";
		Query query=session.createSQLQuery(sqlQueryStr);
		List list=query.list();
		if(list.isEmpty()==true)
		return null;
		else return list;
	}

	@Override
	public ProductItem getProductItemByName(String name) {
		List list=getHibernateTemplate().find("FROM ProductItem WHERE itemName = ? ",name);
		if(list.isEmpty()==true)
			return null;
			else return (ProductItem)list.get(0);
	}

	@Override
	public ProductItem getProductItemById(long id) {
		List list=getHibernateTemplate().find("FROM ProductItem WHERE id = ? ",id);
		if(list.isEmpty()==true)
			return null;
			else return (ProductItem)list.get(0);
	}

	@Override
	public List getIdAndNameOfProductItemOfUnused() {
		List list=getHibernateTemplate().find("SELECT id , itemName FROM ProductItem WHERE  itemResultId IS NULL OR itemResultId=0 AND itemStatus='Y' ");
		if(list.isEmpty()==true)
		return null;
		else return list;
	}

	@Override
	public List getIdAndNameOfProductItemOfAll() {
		List list=getHibernateTemplate().find("SELECT id , itemName FROM ProductItem WHERE  itemStatus='Y'");
		if(list.isEmpty()==true)
		return null;
		else return list;
	}

	@Override
	public ProductItem getProductItemByItemResultName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getIdAndNameOfProductItemOfUnboundOfPlan() {
		List list=getHibernateTemplate().find("SELECT id , itemName FROM ProductItem WHERE  healthPlanId IS NULL OR itemResultId=0 AND itemStatus='Y' ");
		if(list.isEmpty()==true)
		return null;
		else return list;
	}

	@Override
	public List getIdAndNameOfItem() {
		List list=getHibernateTemplate().find("SELECT id,itemName FROM ProductItem WHERE itemStatus='Y' ");
		if(list.isEmpty()==true)
			return null;
		else return list;
	}

	@Override
	public ProductItem getProductItemByItemResultId(long id) {
		List list=getHibernateTemplate().find("FROM ProductItem WHERE itemResultId = ? ",id);
		if(list.isEmpty()==true)
			return null;
		else return (ProductItem)list.get(0);
	}

	@Override
	public ProductItem getProductItemByHealthPlanId(long id) {
		List list=getHibernateTemplate().find("FROM ProductItem WHERE healthPlanId = ? ",id);
		if(list.isEmpty()==true)
			return null;
		else return (ProductItem)list.get(0);
	}

}
