package com.kybaby.operationbussiness.redpacket.util;

import java.util.HashMap;
import java.util.Map;

public class PayTest {
	final static  String url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	public static void main(String[] args) {
		String orderNNo =  MoneyUtils.getOrderNo() ; 
		Map<String, Object> map = new HashMap<String, Object>();
		/*map.put("nonce_str", MoneyUtils.buildRandom());//随机字符串
		map.put("mch_billno", orderNNo);//商户订单
		map.put("mch_id", "1280255901");//商户号
		//map.put("wxappid", "wxc592f8c1fcf1ba64");//商户appid
		map.put("wxappid", "wxb98b4ec8b25cdaaa");//商户appid
		map.put("nick_name", "康优宝贝");//提供方名称
		map.put("send_name", "康优宝贝");//用户名
		map.put("re_openid", "oNkGHwGUeGQS7nmcx5vouD2F3AtE");//用户openid
		map.put("total_amount", 100);//付款金额(单位：分)
		map.put("min_value", 100);//最小红包
		map.put("max_value", 100);//最大红包
		map.put("total_num", 1);//红包发送总人数
		map.put("wishing", "恭喜发财，身体健康！");//红包祝福语
		map.put("client_ip", "127.0.0.1");//ip地址
		map.put("act_name", "领取红包");//活动名称
		map.put("remark", "领取红包");//备注
		map.put("sign", MoneyUtils.createSign(map));//签名
		*/
		//map.put("mch_appid", "wxb98b4ec8b25cdaaa");//商户appid
		map.put("mch_appid", "wxc592f8c1fcf1ba64");//商户appid
		map.put("mchid", "1280255901");//商户号
		map.put("device_info", "013467007045764");//设备号
		map.put("nonce_str", MoneyUtils.buildRandom());//随机字符串
		map.put("partner_trade_no", orderNNo);//商户订单号
		map.put("openid", "oNkGHwMCh8NTskK3Yp1Hd9cjWX7w");//商户appid下，某用户的openid，13699080687在订阅号的openid
		map.put("check_name", "OPTION_CHECK");//校验用户姓名选项
		map.put("re_user_name", "熊超");//收款用户姓名
		map.put("amount", "100");//金额
		map.put("desc", "活动奖励");//企业付款描述信息
		map.put("spbill_create_ip", "127.0.0.1");//ip地址
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
