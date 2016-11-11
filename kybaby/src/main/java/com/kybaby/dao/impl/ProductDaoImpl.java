package com.kybaby.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.ProductDao;
import com.kybaby.domain.Product;
import com.kybaby.util.ConstantManage;

/**
 * @ClassName:ProductDaoImpl
 * @Description:商品数据管理接口实现
 * @author Hoolee
 * @date 2015年9月29日下午2:00:26
 */
public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao {

	public List<Product> featureProductList(String isFeatures,String productName) {
		StringBuffer hql = new StringBuffer("from Product where 1=1 and productStatus='Y' ");
		List params = new ArrayList<>();
		if(isFeatures != null && !"".equals(isFeatures)){
			hql.append(" and isFeatures=?");
			params.add(isFeatures);
		}
		if(productName != null && !"".equals(productName.trim())){
			hql.append(" and name like ?");
			params.add("%"+productName+"%");
		}
		List<Product> list=getHibernateTemplate().find(hql.toString(),params.toArray());
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<Product> specialProductList(String whatFitForSex,String whatFitForMonth) {
		List<Product> list=getHibernateTemplate().find("from Product where whatFitForSex=? and whatFitForMonth=? and productStatus='Y' ", new Object[]{whatFitForSex,whatFitForMonth});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}
	
	public List<Product> newSpecialProductList(String whatFitForMonth) {
		//List<Product> list=getHibernateTemplate().find("from Product where whatFitForMonth=? and productCategory='基础产品' and productStatus='Y' ", whatFitForMonth);
		List<Product> list=getHibernateTemplate().find("from Product where 1=1 and productCategory='基础产品' and productStatus='Y' ");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<Product> someBabySpecialProductList(String whatFitForSex,
			String whatFitForMonth, String productCategory) {
		List<Product> list=getHibernateTemplate().find("from Product where whatFitForMonth=? and whatFitForSex=? and productCategory=? and productStatus='Y'",new Object[]{whatFitForMonth,whatFitForSex,productCategory});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}
	
	public List<Product> newSomeBabySpecialProductList(String whatFitForMonth,String productCategory,String productName) {
		StringBuffer hql = new StringBuffer("from Product where 1=1");
		List params = new ArrayList<>();
		hql.append(" and productType=?");
		params.add(ConstantManage.PRODUCT_TYPE_HEALTH);
		if(whatFitForMonth != null && !"".equals(whatFitForMonth)){
			hql.append(" and whatFitForMonth=?");
			params.add(whatFitForMonth);
		}
		if(productCategory != null && !"".equals(productCategory)){
			hql.append(" and productCategory=?");
			params.add(productCategory);
		}
		if(productName != null && !"".equals(productName.trim())){
			hql.append(" and name like ?");
			params.add("%"+productName+"%");
		}
		List<Product> list=getHibernateTemplate().find(hql.toString(),params.toArray());
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<Product> someCategoryProductList(String productCategory,String productName) {
		StringBuffer hql = new StringBuffer("from Product where 1=1 and productStatus='Y'");
		List params = new ArrayList<>();
		hql.append(" and productType=?");
		params.add(ConstantManage.PRODUCT_TYPE_HEALTH);
		if(productCategory != null && !"".equals(productCategory.trim())){
			hql.append(" and productCategory=?");
			params.add(productCategory);
		}
		if(productName != null && !"".equals(productName.trim())){
			hql.append(" and name like ?");
			params.add("%"+productName+"%");
		}
		List<Product> list=getHibernateTemplate().find(hql.toString(),params.toArray());
		
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public Product getProductByName(String productName) {
		List<Product> list=getHibernateTemplate().find("from Product where name=?",productName);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public Product getProdcutById(long prodcutId) {
		List<Product> list=getHibernateTemplate().find("from Product where id=? and productStatus='Y' ", prodcutId);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public Long getProductServiceTimeById(long productId) {
		List<Long> list=getHibernateTemplate().find("select serviceTime from Product where id=? ",productId);
		if(list.isEmpty()==true||list.get(0)==null){
			return null;
		}
		return list.get(0);
	}

	public double getProductPriceById(long productId) {
		List<Double> list=getHibernateTemplate().find("select totalPrice from Product where id=? ",productId);
		if(list.isEmpty()==true){
			return 0;
		}
		return list.get(0);
	}

	public String getProductNameById(long productId) {
		List<String> list=getHibernateTemplate().find("select name from Product where id=? ", productId);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<Product> getProdcutList(Product product) {
		StringBuffer hql = new StringBuffer("from Product where 1=1 and productStatus='Y' ");
		List params = new ArrayList<>();
		if(product != null){
			if(product.getProductType() != null && !"".equals(product.getProductType().trim())){
				hql.append(" and productType=?");
				params.add(product.getProductType().trim());
			}
			if(product.getName() != null && !"".equals(product.getName().trim())){
				hql.append(" and name like ?");
				params.add("%"+product.getName().trim()+"%");
			}
		}
		List<Product> list=getHibernateTemplate().find(hql.toString(),params.toArray());
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

}
