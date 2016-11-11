package com.java.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName:DateManage
 * @Description:日期管理相关的类
 * @author Hoolee
 * @date 2015年12月8日下午3:19:27
 */
public class DateManage {
	
	/**
	 * 获取上月最后一天
	 * @return
	 */
	public static String getLatestDayOfPreMonth(){
		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.DAY_OF_MONTH, 1);
		instance.add(Calendar.DATE, -1);
		return DateManage.formatDateStr_yyyy_MM_dd(instance.getTime());
	}
	/**
	 * 获取本月第一天
	 * @return
	 */
	public static String getFirstDayOfNowMonth(){
		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.DAY_OF_MONTH, 1);
		return DateManage.formatDateStr_yyyy_MM_dd(instance.getTime());
	}
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过传入的时间格式将当前时间格式化后的字符串返回
	 * @data: 2015年12月8日下午3:22:58
	 * @param dateType
	 * @return
	 */
	public static String getDateStr(String dateType){
		String dateStr="";
		SimpleDateFormat sdf = new SimpleDateFormat(dateType);
		Date rightNow=new Date(System.currentTimeMillis());
		dateStr=sdf.format(rightNow);
		return dateStr;
	}
	/**
	 * 根据日期取得星期几  
	 * @param date 输入日期
	 * @return
	 */
    public static String getWeek(Date date){   
    	//注：格式化字符串存在区分大小写
   	 // 对于创建SimpleDateFormat传入的参数：EEEE代表星期，如“星期四”；MMMM代表中文月份，如“十一月”；MM代表月份，如“11”；
   	 //yyyy代表年份，如“2010”；dd代表天，如“25”
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");  
        String week = sdf.format(date);  
        return week;  
    } 
    /**
     * 根据日期取得周几  
     * @param date
     * @return
     */
    public static String getWeekByZhou(Date date){  
        String[] weeks = {"周日","周一","周二","周三","周四","周五","周六"};  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;  
        if(week_index<0){  
            week_index = 0;  
        }   
        return weeks[week_index];  
    } 
    /**
     * 字符串转日期
     * @param dateStr 日期字符串(格式为 yyyy-MM-dd)
     * @return
     */
    public static Date getStrToDate(String strDate){  
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 定义日期格式  
    	Date date = null;
		try {
			date = format.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}// 将字符串转换为日期  
    	return date;
    }
    /**
     * 字符串转时间
     * @param dateStr 日期字符串(格式为 yyyy-MM-dd HH:mm:ss)
     * @return
     */
    public static Date getStrToDateTime(String strDate){  
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 定义日期格式  
    	Date date = null;
    	try {
    		date = format.parse(strDate);
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}// 将字符串转换为日期  
    	return date;
    }
    /**
     * 日期转字符串
     * @param date 需要转化的日期
     * @param dateType 格式化样式（如：yyyy-MM-dd）
     * @return
     */
    public static String getDateToStr(Date date,String dateType){
    	SimpleDateFormat format = new SimpleDateFormat(dateType);// 定义日期格式  
    	return format.format(date);
    }
    /**
     * 日期转字符串(yyyy-MM-dd)
     * @param date 需要转化的日期
     * @return
     */
    public static String formatDateStr_yyyy_MM_dd(Date date){
    	return getDateToStr(date, "yyyy-MM-dd");
    }
    /**
     * 日期转字符串(yyyy-MM-dd HH:mm:ss)
     * @param date 需要转化的日期
     * @return
     */
    public static String formatDateStr_yyyy_MM_dd_HH_mm_ss(Date date){
    	return getDateToStr(date, "yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 字符串(yyyy-MM-dd)转日期
     * @param date 需要转化的日期
     * @return
     */
    public static Date parseStr2Date_yyyy_MM_dd(String dateStr){
    	return getStrToDate(dateStr);
    }
    /**
     * 字符串(yyyy-MM-dd HH:mm:ss)转日期
     * @param date 需要转化的日期
     * @return
     */
    public static Date parseStr2Date_yyyy_MM_dd_HH_mm_ss(String dateStr){
    	return getStrToDateTime(dateStr);
    }
    /**
     * 取得两个日期之间的相差多少天  
     * @param date0
     * @param date1
     * @return
     */
    public static long getDaysBetween(Date date0, Date date1) {  
        long daysBetween = (date0.getTime() - date1.getTime() + 1000000) / 86400000;// 86400000=3600*24*1000  用立即数，减少乘法计算的开销  
        return daysBetween;  
    }
	/**
	 * 根据特定Pattern的字符串转为时间
	 * @param aMask //yyyy-MM-dd HH:mm:ss
	 * @param aDate
	 * @return
	 */
	public static String getDateTimeStr(String aMask, Date aDate)
	{
		SimpleDateFormat df = new SimpleDateFormat(aMask);
		return df.format(aDate);
	}
	/**
	 * 判断某日期和当前日期的差是否超过了n分钟
	 * @param date
	 * @param minute
	 * @return
	 */
	public static boolean isInRangeDates(Date date, int minute) {
		return new Date().getTime() - date.getTime() > minute * 60 * 1000;
	}
	/**
	 * 判断A时间是否大于B时间
	 * @param aDate
	 * @param bDate
	 * @return
	 */
	public static boolean isCompareDates(Date aDate, Date bDate) {
		return aDate.getTime() - bDate.getTime() > 0;
	}
	/**
	 * 得到几天前或几天后的日期
	 * @param days +表示后几天，-表示前几天
	 * @return 返回"yyyy-MM-dd"日期
	 */
	public static String getBeforeOrAfter(int days){   
		Calendar c=Calendar.getInstance();   
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");   
		c.setTime(new Date());   
		c.add(Calendar.DATE,days);   
		Date d2=c.getTime();   
		String s=df.format(d2);   
		return s;   
	} 
	/**
	 * 得到从某个日期开始计算，几天前或几天后的日期
	 * @param oneDate 开始计算的日期
	 * @param days +表示后几天，-表示前几天
	 * @return 返回"yyyy-MM-dd"日期
	 */
	public static String getBeforeOrAfterByOneDate(Date oneDate,int days){   
		Calendar c=Calendar.getInstance();   
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");   
		c.setTime(oneDate);   
		c.add(Calendar.DATE,days);   
		Date d2=c.getTime();   
		String s=df.format(d2);   
		return s;   
	} 
	/**
	 * 得到几小时前或几小时后的日期
	 * @param days +表示后几小时，-表示前几小时
	 * @return 返回"yyyy-MM-dd HH:mm:ss"日期
	 */
	public static String getBeforeOrAfterMinute(int minute){   
		Calendar c=Calendar.getInstance();   
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		c.setTime(new Date());   
		c.add(Calendar.MINUTE,minute);   
		Date d2=c.getTime();   
		String s=df.format(d2);   
		return s;   
	} 
	public static void main(String[] args) {
		String s = DateManage.getBeforeOrAfterMinute(24*60);
		System.out.println(s);
	}
}
