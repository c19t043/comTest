package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.ProductBo;
import com.kybaby.dao.ProductDao;
import com.kybaby.domain.Product;

public class ProductBoImpl  implements   ProductBo {

	ProductDao  productDao;
	@Override
	public List  getAllProduct() {
		// TODO Auto-generated method stub
		return productDao.getAllProduct();
	}

	@Override
	public Product getProductByName(String name) {
		// TODO Auto-generated method stub
		return productDao.getProductByName(name);
	}

	@Override
	public Product getProductById(long id) {
		// TODO Auto-generated method stub
		return productDao.getProductById(id);
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

//	//2.8.1 产品管理
//	List<Product> getAllProduct();//显示所有产品
//	Product getProductByName(String name);//通过名字找到该产品实例
//	Product getProductById(long id);//通过产品id找到该实例
	
}
