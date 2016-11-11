package com.kybaby.operationbussiness.redpacket.util;

import java.util.HashMap;
import java.util.Map;

public class MoneyTest {
	final static  String url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	public static void main(String[] args) {
		String orderNNo =  MoneyUtils.getOrderNo() ; 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nonce_str", MoneyUtils.buildRandom());//随机字符串
		map.put("mch_billno", orderNNo);//商户订单
		map.put("mch_id", "1280255901");//商户号
		map.put("wxappid", "wxc592f8c1fcf1ba64");//商户appid
		//map.put("wxappid", "wxb98b4ec8b25cdaaa");//商户appid
		map.put("nick_name", "康优宝贝");//提供方名称
		map.put("send_name", "康优宝贝");//用户名
		//map.put("re_openid", "oNkGHwGUeGQS7nmcx5vouD2F3AtE");//用户openid,18228080896在订阅号的openid
		map.put("re_openid", "oNkGHwMCh8NTskK3Yp1Hd9cjWX7w");//用户openid,13699080687在订阅号的openid
		map.put("total_amount", 100);//付款金额(单位：分)
		map.put("min_value", 100);//最小红包
		map.put("max_value", 100);//最大红包
		map.put("total_num", 1);//红包发送总人数
		map.put("wishing", "恭喜发财，身体健康！");//红包祝福语
		map.put("client_ip", "127.0.0.1");//ip地址
		map.put("act_name", "领取红包");//活动名称
		map.put("remark", "领取红包");//备注
		map.put("sign", MoneyUtils.createSign(map));//签名
		
		String result = "";
		try {
			result = MoneyUtils.doSendMoney(url, MoneyUtils.createXML(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("result:"+result);
	}
}
