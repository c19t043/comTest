package com.kybaby.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.ProductDao;
import com.kybaby.domain.Product;

public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao {

	@Override
	public List  getAllProduct() {
		
		Session session = getHibernateTemplate().getSessionFactory().openSession();
	    String sqlQueryStr=    		
	    		"SELECT " +
                       " a.id, " +
                       " a.name, " +
                       " b.positionName , "+
                       " a.serviceTime, " +
                       " a.afterServiceTime, " +
                       " a.totalPrice, " +
                       " a.whatFitForMonth, " +
                       " a.whatFitForSex, " +
                       " a.isFeatures, " +
                       " a.productProperty, " +
                       " a.productCategory, " +
                       " a.introduction, " +
                       " a.smallPicture, " +
                       " a.bigPictureOne, " +
                       " a.bigPictureTwo, " +
                       " a.bigPictureThree, " +
                       " GROUP_CONCAT(DISTINCT(c.itemName) SEPARATOR '::') ," +
                       " a.productStatus ,"+
                       " a.flow_basic_id," +
                       " a.is_judge_age," +
                       " a.is_judge_sex," +
                       " a.max_age, " +
                       " a.product_type, " +
                       " a.member_type_id, " +
                       " a.short_introduction " +
                  " FROM  product a, " +
                      "  (SELECT B.id,B.name AS positionName FROM position B) AS b ," +
                      "   product_item c " +
                 " WHERE a.positionId = b.id " +
                       " AND FIND_IN_SET(c.id,REPLACE(a.itemIds,'::',',')) GROUP BY a.id ";
//		String sqlQueryStr="SELECT a.id,a.name,B.positionName FROM  Product a,(SELECT b.id,b.name AS positionName  FROM Position b) AS B WHERE a.positionId=b.id";
	   Query query = session.createSQLQuery(sqlQueryStr);
	   List list=query.list();
		if(list.isEmpty()==true)
			return null;	
		else return list;
	}

	@Override
	public Product getProductByName(String name) {
		List list=getHibernateTemplate().find("FROM Product WHERE name = ? " , name);
		if(list.isEmpty()==true)
			return null;	
		else return (Product)list.get(0);
	}

	@Override
	public Product getProductById(long id) {
		List list=getHibernateTemplate().find("FROM Product WHERE id = ? " , id);
		if(list.isEmpty()==true)
			return null;	
		else return (Product)list.get(0);
	}

}
