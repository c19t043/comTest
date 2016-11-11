package com.kybaby.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.bo.ProductItemBo;
import com.kybaby.dao.ProductItemDao;
import com.kybaby.domain.ProductItem;

/**
 * @ClassName:ProductItemBoImpl
 * @Description:服务产品包含的项目的事物管理接口实现
 * @author Hoolee
 * @date 2015年10月5日上午11:32:15
 */
public class ProductItemBoImpl implements ProductItemBo {
	
	ProductItemDao productItemDao;
	
	public List<ProductItem> getSomeProductItemsListById(long productId) {
		return null;
	}

	public ProductItem getProductItemInstanceByName(String itemNmae) {
		return productItemDao.getProductItemInstanceByName(itemNmae);
	}

	public String getItemNameById(long itemId) {
		return productItemDao.getItemNameById(itemId);
	}
	
	public List<String> getSomeProductItems(String itemIds) {
		List<String> nameList=new ArrayList<String>();
		String[] itemIdsList=itemIds.split("::");
		for(int i =0;i<itemIdsList.length;i++){
			long itemId=Long.valueOf(itemIdsList[i]);
			String itemName=productItemDao.getItemNameById(itemId);
			if(itemName!=null){
				nameList.add(itemName);
			}
		}
		return nameList;
	}

	public List<String> getSomeProductItemShowNameList(String itemIds) {
		List<String> nameList=new ArrayList<String>();
		String[] itemIdsList=itemIds.split("::");
		for(int i =0;i<itemIdsList.length;i++){
			long itemId=Long.valueOf(itemIdsList[i]);
			String itemName=productItemDao.getItemShowNameById(itemId);
			if(itemName!=null){
				nameList.add(itemName);
			}
		}
		return nameList;
	}
	
	public ProductItemDao getProductItemDao() {
		return productItemDao;
	}

	public void setProductItemDao(ProductItemDao productItemDao) {
		this.productItemDao = productItemDao;
	}

}
