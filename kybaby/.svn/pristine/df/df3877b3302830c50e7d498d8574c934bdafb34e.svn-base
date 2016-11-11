package com.kybaby.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.bo.ProductBo;
import com.kybaby.dao.ProductDao;
import com.kybaby.domain.Product;

/**
 * @ClassName:ProductBoImpl
 * @Description:商品事物管理接口实现
 * @author Hoolee
 * @date 2015年9月29日下午1:57:39
 */
public class ProductBoImpl implements ProductBo {
	
	ProductDao productDao;
	
	public List<Product> specialProductList(String whatFitForSex,String whatFitForMonth) {
		return productDao.specialProductList(whatFitForSex, whatFitForMonth);
	}

	public List<Product> newSpecialProductList(String whatFitForMonth) {
		return productDao.newSpecialProductList(whatFitForMonth);
	}
	
	public List<Product> featureProductList(String isFeatures,String productName) {
		return productDao.featureProductList(isFeatures,productName);
	}

	public List<Product> someBabySpecialProductList(String whatFitForSex,
			String whatFitForMonth, String productCategory) {
		return productDao.someBabySpecialProductList(whatFitForSex, whatFitForMonth, productCategory);
	}

	public List<Product> someCategoryProductList(String productCategory,String productName) {
		return productDao.someCategoryProductList(productCategory, productName);
	}

	public Product getProductByName(String productName) {
		return productDao.getProductByName(productName);
	}

	public List<Product> getSomeDoctorProdcutListByDoctorId(long doctorId) {
		return null;
	}

	public Product getProductById(long productId) {
		return productDao.getProdcutById(productId);
	}

	public String getProductNameById(long productId) {
		return productDao.getProductNameById(productId);
	}

	public List<Product> getSomeDoctorServiceProdcutList(String productIds) {
		List<Product> serviceProductList=new ArrayList<Product>();
		String[] productIdsList=productIds.split("::");
		for(int i =0;i<productIdsList.length;i++){
			long productId=Long.valueOf(productIdsList[i]);
			Product someProduct=productDao.getProdcutById(productId);
			if(someProduct!=null){
				serviceProductList.add(someProduct);
			}
		}
		return serviceProductList;
	}

	public Long getProductServiceTimeById(long productId) {
		return productDao.getProductServiceTimeById(productId);
	}
	
	public double getProductPriceById(long productId) {
		return productDao.getProductPriceById(productId);
	}

	public List<Product> newSomeBabySpecialProductList(String whatFitForMonth,String productCategory,String productName) {
		return productDao.newSomeBabySpecialProductList(whatFitForMonth, productCategory, productName);
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<Product> getProdcutList(Product product) {
		return productDao.getProdcutList(product);
	}

}
