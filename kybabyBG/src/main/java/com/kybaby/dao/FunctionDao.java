package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.FunctionList;
import com.kybaby.domain.FunctionParent;

/**
 * @ClassName:FunctionDao
 * @Description:功能的数据管理接口
 * @author Hoolee
 * @date 2015年9月5日下午12:10:15
 */
public interface FunctionDao {
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取平台上的所有有效的父类功能（功能状态为Y）列表
	 * @data: 2015年9月5日12:17:19
	 * @return 平台上的所有有效的父类功能（功能状态为Y）列表
	 */
	List<FunctionParent> getAllFunctionParentList();
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:返回某一个父类模块下的有效的功能（功能状态为Y）列表
	 * @data: 2015年9月5日12:17:23
	 * @param id 功能所属的父类模块的ID
	 * @return 某一个父类模块下的有效的功能（功能状态为Y）列表
	 */
	List<FunctionList> getSomeFunctionListByParentId(long id);
}
