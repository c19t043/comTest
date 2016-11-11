package com.java.publichealth.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 数值计算相关方法
 * @author lihao
 *
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
    public static int getMonthSpace(Date date1, Date date2)throws ParseException {

        int result = 0;

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(date1);
        c2.setTime(date2);
        
        result=(c1.get(c1.YEAR)-c2.get(c2.YEAR))*12+((c1.get(c1.MONTH)+1)-(c2.get(c2.MONTH)+1));
        
        return result == 0 ? 1 : Math.abs(result);
    }
}
