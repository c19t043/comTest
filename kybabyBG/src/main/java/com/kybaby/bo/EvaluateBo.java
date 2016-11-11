package com.kybaby.bo;

import java.util.List;

public interface EvaluateBo {

	//2.9.1评价管理
	List getEvaluateByOrderNum(String orderNum);//从评价表，社交标签表找到标签与3个质量控制星级
	
}
