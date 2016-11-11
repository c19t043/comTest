package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.Product;

public interface ProductDao {

	//2.8.1 产品管理
	List getAllProduct();//显示所有产品
	Product getProductByName(String name);//通过名字找到该产品实例
	Product getProductById(long id);//通过产品id找到该实例
	
}
