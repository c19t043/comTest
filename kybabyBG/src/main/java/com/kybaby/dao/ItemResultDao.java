package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.ItemResult;

public interface ItemResultDao {

	//2.8.3 项目结果
	List  getAllItemResult();//显示所有项目结果
	ItemResult getItemResultByName(String name);//通过name找到该项目结果实例
	ItemResult getItemResultById(long id); //通过id找到该项目结果实例
}
