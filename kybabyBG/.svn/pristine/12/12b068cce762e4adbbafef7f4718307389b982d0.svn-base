package com.kybaby.newbussiness.doctorring.util;

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
	
	public static String getDateDifferInMinutes(String date,int maxMinute){
		String dateDiffer="";
		int oldYear=1970;
		int oldMonth=1;
		int oldDay=1;
		int oldHour=0;
		int oldMinu=0;
		if(date.length()==10){//该地方应使用正则表达式进行完善
			Date before=java.sql.Date.valueOf(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(before);
			oldYear=calendar.get(calendar.YEAR);
			oldMonth=calendar.get(calendar.MONTH);
			oldDay=calendar.get(calendar.DAY_OF_MONTH);
			oldHour=calendar.get(calendar.HOUR_OF_DAY);
			oldMinu=calendar.get(calendar.MINUTE);
		}else{
			String[] dateSplit=date.split(" ");
			String yearMonthDay=dateSplit[0];
			String hourMinu=dateSplit[1];
			String[] yearMonthDayArr=yearMonthDay.split("-");
			String[] hourMinuArr=hourMinu.split(":");
			oldYear=Integer.valueOf(yearMonthDayArr[0]);
			oldMonth=Integer.valueOf(yearMonthDayArr[1]);
			oldDay=Integer.valueOf(yearMonthDayArr[2]);
			oldHour=Integer.valueOf(hourMinuArr[0]);
			oldMinu=Integer.valueOf(hourMinuArr[1]);
		}
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(new Date(System.currentTimeMillis()));
		int newYear=nowCalendar.get(nowCalendar.YEAR);
		int newMonth=nowCalendar.get(nowCalendar.MONTH)+1;
		int newDay=nowCalendar.get(nowCalendar.DAY_OF_MONTH);
		int newHour=nowCalendar.get(nowCalendar.HOUR_OF_DAY);
		int newMinu=nowCalendar.get(nowCalendar.MINUTE);
		if(oldYear==newYear&&oldMonth==newMonth&&oldDay==newDay&&oldHour==newHour){
			int difMinute=Math.abs(newMinu-oldMinu);
			if(difMinute<=maxMinute){
				dateDiffer=difMinute+"分钟前";
			}else{
				dateDiffer=date;
			}  
		}else{
			dateDiffer=date;
		}
		return dateDiffer;
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
	public static void main(String[] args) {
		boolean a = DateManage.isCompareDates(DateManage.getStrToDateTime("2016-05-30 00:00:00"), new Date());
		System.out.println(a);
	}
}
