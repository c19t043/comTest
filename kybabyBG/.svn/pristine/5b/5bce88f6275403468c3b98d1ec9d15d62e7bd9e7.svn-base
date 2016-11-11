package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.ProductItemBo;
import com.kybaby.dao.ProductItemDao;
import com.kybaby.domain.ProductItem;

public class  ProductItemBoImpl implements   ProductItemBo{
	ProductItemDao  productItemDao;
	
	@Override
	public List  getAllProductItem() {
		// TODO Auto-generated method stub
		return productItemDao.getAllProductItem();
	}

	@Override
	public ProductItem getProductItemByName(String name) {
		// TODO Auto-generated method stub
		return productItemDao.getProductItemByName(name);
	}

	@Override
	public ProductItem getProductItemById(long id) {
		// TODO Auto-generated method stub
		return productItemDao.getProductItemById(id);
	}

	@Override
	public List getIdAndNameOfProductItemOfUnused() {
		// TODO Auto-generated method stub
		return productItemDao.getIdAndNameOfProductItemOfUnused();
	}

	@Override
	public List getIdAndNameOfProductItemOfAll() {
		// TODO Auto-generated method stub
		return productItemDao.getIdAndNameOfProductItemOfAll();
	}

	@Override
	public ProductItem getProductItemByItemResultName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getIdAndNameOfProductItemOfUnboundOfPlan() {
		// TODO Auto-generated method stub
		return productItemDao.getIdAndNameOfProductItemOfUnboundOfPlan();
	}

	public ProductItemDao getProductItemDao() {
		return productItemDao;
	}

	public void setProductItemDao(ProductItemDao productItemDao) {
		this.productItemDao = productItemDao;
	}

	@Override
	public List getIdAndNameOfItem() {
		// TODO Auto-generated method stub
		return productItemDao.getIdAndNameOfItem();
	}

	@Override
	public ProductItem getProductItemByItemResultId(long id) {
		// TODO Auto-generated method stub
		return productItemDao.getProductItemByItemResultId(id);
	}

	@Override
	public ProductItem getProductItemByHealthPlanId(long id) {
		// TODO Auto-generated method stub
		return productItemDao.getProductItemByHealthPlanId(id);
	}

//	//2.8.2 项目管理
//	List<ProductItem> getAllProductItem();//所有项目显示
//	ProductItem getProductItemByName(String name); //通过项目名字查找到项目的实例
//	ProductItem getProductItemById(long id); //通过项目id找到该实例
//	
//	//2.8.3 项目结果管理
//	List  getIdAndNameOfProductItemOfUnused();//得到项目结果中未绑定项目结果的项目id与名字
//	List  getIdAndNameOfProductItemOfAll();   //得到所有项目id与名字
//	ProductItem getProductItemByItemResultName(String name);// 通过项目结果名字找到该项目实例
//	
//	//2.8.4 健康计划管理
//	List getIdAndNameOfProductItemOfUnboundOfPlan(); //得到健康计划中未绑定项目结果的项目id与名字
	
}
