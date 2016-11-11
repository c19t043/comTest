package com.kybaby.newbussiness.mommyring.util;

import java.util.List;

/**
 * @ClassName:ListManage
 * @Description:列表管理相关的方法
 * @author Hoolee
 * @date 2015年12月14日下午2:23:17
 */
public class ListManage {

	public static String listToStr(List<Long> userRingIdList){
		String str=null;
		if(userRingIdList!=null){
			str="(";
			for(int listSize=0;listSize<userRingIdList.size();listSize++){
				Object object=userRingIdList.get(listSize);
				String objStr=String.valueOf(object).toString();
				str=str+objStr+",";
			}
			str=str.substring(0, str.length()-1);
			str+=")";
		}
		return str;
	}
}
