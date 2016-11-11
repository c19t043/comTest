package com.kybaby.util;

/**
 * @ClassName:GetDistance
 * @Description:根据两点之间的经纬度获取两点之间的距离，单位是M
 * @author Hoolee
 * @date 2015年10月6日上午11:12:44
 */
public class GetDistance {
	
	private static final double EARTH_RADIUS = 6378137;//地球半径
    
	private static double rad(double d){
       return d * Math.PI / 180.0;
    }
	
	public static double GetDistanceMethod(double lng1, double lat1, double lng2, double lat2){
       double radLat1 = rad(lat1);
       double radLat2 = rad(lat2);
       double a = radLat1 - radLat2;
       double b = rad(lng1) - rad(lng2);
       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
        Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
       s = s * EARTH_RADIUS;
       return getSecondBits(s/1000);
    }
	
	public static double getSecondBits(double price){
		return (double)Math.round(price*100)/100;
	}
}
