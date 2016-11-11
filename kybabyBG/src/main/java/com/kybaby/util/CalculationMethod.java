package com.kybaby.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName:CalculationMethod
 * @Description:数值计算相关的方法
 * @author Hoolee
 * @date 2015年9月29日下午3:04:19
 */
public class CalculationMethod {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:getMonthSpace方法是用于计算两个日期之间的月份差值的方法
	 * @data: 2015年9月29日下午3:06:37
	 * @param date1 
	 * @param date2
	 * @return 两个日期之间相差的月份数
	 * @throws ParseException
	 */
    public static int getMonthSpace(String date1, String date2)throws ParseException {

        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));
        
        result=(c1.get(c1.YEAR)-c2.get(c2.YEAR))*12+((c1.get(c1.MONTH)+1)-(c2.get(c2.MONTH)+1));
        
        return result == 0 ? 1 : Math.abs(result);
    }
    
    public static Date rightNowDate(){
    	return new java.sql.Date(System.currentTimeMillis());
    }
    
    /**
     * 
     * @author HooLee
     * @Discription:
     * @data: 2015年12月3日下午3:04:39
     * @param maxNum 日期列表的最大值
     * @param allDateList 需要查询的日期列表
     * @return 距离当天最近的日期
     */
    public static String getLatestDateStr(List<String> allDateList){
		String latestDate="";
		java.util.Date todayDate=new java.util.Date(System.currentTimeMillis());
		int maxSize=allDateList.size();
		for(int k =0;k<maxSize;k++){
			String dateStr=allDateList.get(maxSize-k-1);
			java.util.Date hisDate=java.sql.Date.valueOf(dateStr);
			if(hisDate.compareTo(todayDate)>0){
				latestDate=String.valueOf(hisDate);
				break;
			}
		}
    	return latestDate;
    }
    
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
    
}
