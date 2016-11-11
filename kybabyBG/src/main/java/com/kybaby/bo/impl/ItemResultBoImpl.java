package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.ItemResultBo;
import com.kybaby.dao.ItemResultDao;
import com.kybaby.domain.ItemResult;

public class ItemResultBoImpl  implements ItemResultBo{

	ItemResultDao itemResultDao;
//	//2.8.3 项目结果
//	List<ItemResult> getAllItemResult();//显示所有项目结果
//	ItemResult getItemResultByName(String name);//通过name找到该项目结果实例
//	ItemResult getItemResultById(long id); //通过id找到该项目结果实例

	@Override
	public List getAllItemResult() {
		// TODO Auto-generated method stub
		return itemResultDao.getAllItemResult();
	}

	@Override
	public ItemResult getItemResultByName(String name) {
		// TODO Auto-generated method stub
		return itemResultDao.getItemResultByName(name);
	}

	@Override
	public ItemResult getItemResultById(long id) {
		// TODO Auto-generated method stub
		return itemResultDao.getItemResultById(id);
	}

	public ItemResultDao getItemResultDao() {
		return itemResultDao;
	}

	public void setItemResultDao(ItemResultDao itemResultDao) {
		this.itemResultDao = itemResultDao;
	}
}
