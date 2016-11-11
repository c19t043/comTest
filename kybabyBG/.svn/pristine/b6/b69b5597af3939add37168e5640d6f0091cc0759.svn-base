package com.kybaby.newbussiness.doctorring.util;

import java.util.List;

/**
 * @ClassName:ListManage
 * @Description:列表管理相关的方法
 * @author Hoolee
 * @date 2015年12月14日下午2:23:17
 */
public class ListManage {

	public static String listToStr(List<Long> doctorRingIdList){
		String str=null;
		if(doctorRingIdList!=null){
			str="(";
			for(int listSize=0;listSize<doctorRingIdList.size();listSize++){
				Object object=doctorRingIdList.get(listSize);
				String objStr=String.valueOf(object).toString();
				str=str+objStr+",";
			}
			str=str.substring(0, str.length()-1);
			str+=")";
		}
		return str;
	}
}
