package com.kybaby.bo;

import java.util.List;

public interface CaseClipBo {

	//2.1控制台
	long getCurrentMonthCaseClipNum(); //得到当月病历夹量
	long getAllCaseClip();             //得到所有的病历夹
	
	//2.5.2  健康档案管理
	List getBaByCaseClipByUserId(long userId);//通过用户Id得到用户上传的病历夹，按时间逆序排
}
