package com.kybaby.dao;

import java.util.List;

public interface OrderResultsDao {

	    //2.5.2  健康档案管理
		//得到形如产品名字，项目名字，项目结果，项目结果值，项目计划名字，健康计划路径名字，执行情况（Y，N，Y）记录，其中项目名字与计划重复
		List getOrderAndPathResult(long userId);//通过用户Id得到一次订单的所有结果，细分到健康路径下
		
		List getUserOrderNumListString(long userId); //通过用户Id得到订单号
}
