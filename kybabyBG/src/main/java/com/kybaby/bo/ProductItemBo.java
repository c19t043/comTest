package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.ProductItem;

public interface ProductItemBo {

	//2.8.2 项目管理
	List  getAllProductItem();//所有项目显示
	ProductItem getProductItemByName(String name); //通过项目名字查找到项目的实例
	ProductItem getProductItemById(long id); //通过项目id找到该实例
	
	//2.8.3 项目结果管理
	List  getIdAndNameOfProductItemOfUnused();//得到项目结果中未绑定项目结果的项目id与名字
	List  getIdAndNameOfProductItemOfAll();   //得到所有项目id与名字
	ProductItem getProductItemByItemResultName(String name);// 通过项目结果名字找到该项目实例
	ProductItem getProductItemByItemResultId(long id);
	
	//2.8.4 健康计划管理
	List getIdAndNameOfProductItemOfUnboundOfPlan(); //得到健康计划中未绑定项目结果的项目id与名字
	ProductItem getProductItemByHealthPlanId(long id);
	List getIdAndNameOfItem();//得到所有的项目id,与名字
}
